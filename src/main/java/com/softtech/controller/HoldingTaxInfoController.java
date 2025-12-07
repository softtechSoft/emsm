package com.softtech.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtech.actionForm.EmployeeActionForm;
import com.softtech.actionForm.HoldingTaxInfoFormBean;
import com.softtech.entity.HoldingTaxInfoEntity;
import com.softtech.service.HoldingTaxInfoService;

/**
 * @program
 * @概要:
 * @作成者:黄
 * @作成日:2025-11-05
 * @return:
 */
@Controller
public class HoldingTaxInfoController {
    //IOC
    @Autowired
    private HoldingTaxInfoService holdingTaxInfoService;
    //logger
    static protected Logger logger = LogManager.getLogger(HoldingTaxInfoController.class);

    /**概要:社員IDの選択枠を設定する
    *@param:[model]
    *@return:java.lang.String
    *@author:黄
    *@date:2025-11-05
    */
    @RequestMapping("/initHoldingTaxInfoList")
    public String initHoldingTaxInfoList(Model model) {
        logger.info("start index()");

        //DBから社員情報を取得する,画面の社員ID選択,枠に表示する
        List<EmployeeActionForm> employeeList = holdingTaxInfoService.queryEmployeeInfo();
        model.addAttribute("employeeList", employeeList);

        List<String> yearList = holdingTaxInfoService.getDistinctYears();
        model.addAttribute("yearList", yearList);

        //画面初期表示用のFormBeam
        HoldingTaxInfoFormBean holdingTaxInfoFormBean = new HoldingTaxInfoFormBean();
        holdingTaxInfoFormBean.setEmployeeID("");
        holdingTaxInfoFormBean.setSearchYear("");

        model.addAttribute("holdingTaxInfoFormBean", holdingTaxInfoFormBean);
        return "holdingTaxInfoList";
    }

    /**概要:社員IDにより、検索する
    *@param:[holdingTaxInfoFormBean, model]
    *@return:java.lang.String
    *@author:黄
    *@date:2025-11-05
    */
    @RequestMapping("/holdingTaxInfoList")
    public String holdingTaxInfoList(@ModelAttribute("holdingTaxInfoFormBean") HoldingTaxInfoFormBean holdingTaxInfoFormBean, Model model) {

        String employeeID = holdingTaxInfoFormBean.getEmployeeID();
        String searchYear = holdingTaxInfoFormBean.getYear();

        //検索条件により、検索する
        List<HoldingTaxInfoEntity> bList = holdingTaxInfoService.getHoldingTaxByCondition(employeeID, searchYear);
        //社員ID候補を取得する
        List<EmployeeActionForm> employeeList = holdingTaxInfoService.queryEmployeeInfo();
        List<String> yearList = holdingTaxInfoService.getDistinctYears();

        model.addAttribute("employeeList", employeeList);
        model.addAttribute("yearList", yearList);
        model.addAttribute("holdingTaxInfoFormBean", holdingTaxInfoFormBean);
        model.addAttribute("list", bList);

        return "holdingTaxInfoList";
    }

    /**概要:検索と更新画面の初期表示
    *@param:[incomeTaxInfoFormBean, model]
    *@return:java.lang.String
    *@author:黄
    *@date:2025-11-05
    */
    @RequestMapping("/toInitHoldingTaxInfo")
    public String toInitHoldingTaxInfo(@ModelAttribute("holdingTaxInfoFormBean") HoldingTaxInfoFormBean holdingTaxInfoFormBean,
                                      Model model) {

        //新規フラグを取得
        String insertFlg = holdingTaxInfoFormBean.getInsertFlg();
        //新規の場合
        if ("0".equals(insertFlg)) {
        	HoldingTaxInfoFormBean holdingTaxInfoFormBean1 = new HoldingTaxInfoFormBean();

            //incomeTaxIDを採番する（既存の最大値＋１）
            //システム日付の取得
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String format = formatter.format(now);

            String maxIncomeTaxID = holdingTaxInfoService.getNextHoldingTaxID();
            holdingTaxInfoFormBean1.setHoldingTaxID(maxIncomeTaxID);
            //新規
            holdingTaxInfoFormBean1.setInsertFlg(insertFlg);
            holdingTaxInfoFormBean1.setInsertDate(format);
            holdingTaxInfoFormBean1.setUpdateDate(format);

            model.addAttribute("holdingTaxInfoFormBean", holdingTaxInfoFormBean1);

            //更新の場合
        } else {
            //選択された内容を取得する
        	String holdingTaxID = holdingTaxInfoFormBean.getHoldingTaxID();
            List<HoldingTaxInfoEntity> bList =holdingTaxInfoService.getUpdateHoldingTaxByHoldingTaxID(holdingTaxID);

            HoldingTaxInfoFormBean holdingTaxInfoFormBean2 =holdingTaxInfoService.transforEntityToUI(bList);

            //更新
            holdingTaxInfoFormBean2.setInsertFlg(insertFlg);
            model.addAttribute("holdingTaxInfoFormBean", holdingTaxInfoFormBean2);
        }
        //DBから社員情報を取得する,画面の社員ID選択,枠に表示する
        List<EmployeeActionForm> employeeList = holdingTaxInfoService.queryEmployeeInfo();
        model.addAttribute("employeeList", employeeList);


        //更新画面の年度を表示する用リスト候補生成
        List<String> yearList = holdingTaxInfoService.getDistinctYears();
        model.addAttribute("year", yearList);

        return "holdingTaxInfoEdit";
    }

    /**概要:更新と新規ボタン
    *@param:[incomeTaxInfoFormBean, result, session, model]
    *@return:java.lang.String
    *@author:黄
    *@date:2025-11-05
    */
    @RequestMapping(value = "/holdingTaxInfoEdit", method = RequestMethod.POST)
    public String holdingTaxInfoEdit(@Valid @ModelAttribute("holdingTaxInfoFormBean") HoldingTaxInfoFormBean holdingTaxInfoFormBean,
                                      BindingResult result, HttpSession session, Model model) {

        //必須チェック
        if (result.hasErrors()) {
		    // エラーチェック用リスト
			List<FieldError> errorlst = new ArrayList<FieldError>();

			//エラーメッセージ。
			errorlst.addAll(result.getFieldErrors());
			//エラーメッセージ
			model.addAttribute("errors", errorlst);

            //DBから年度リスト生成
			 List<String> yearList = holdingTaxInfoService.getDistinctYears();
            model.addAttribute("year", yearList);

            List<EmployeeActionForm> employeeList = holdingTaxInfoService.queryEmployeeInfo();
            model.addAttribute("employeeList", employeeList);

            return "holdingTaxInfoEdit";
        }

        String insertFlg = holdingTaxInfoFormBean.getInsertFlg();
        //新規の場合
        if ("0".equals(insertFlg)) {

    		// エラーチェック用リスト
    		List<FieldError> errorlst = new ArrayList<FieldError>();

            //新規の場合、該当年度、社員の情報がDBにあるかのチェック
            List<HoldingTaxInfoEntity> bList = holdingTaxInfoService.getHoldingTaxByCondition(holdingTaxInfoFormBean.getEmployeeID(), holdingTaxInfoFormBean.getYear());
            if (bList.size()>0) {
            	FieldError err1 = new FieldError("", "", "該当年度、社員の情報がDBにありました");
            	errorlst.add(err1);
            }

            //その他チェック
            if (errorlst.size()>0) {

    			//エラーメッセージ
    			model.addAttribute("errors", errorlst);

                //DBから年度リスト生成
   			   List<String> yearList = holdingTaxInfoService.getDistinctYears();
               model.addAttribute("year", yearList);

               List<EmployeeActionForm> employeeList = holdingTaxInfoService.queryEmployeeInfo();
               model.addAttribute("employeeList", employeeList);

            	return "holdingTaxInfoEdit";
            }

        	holdingTaxInfoService.insertHoldingTaxInfo(holdingTaxInfoFormBean);
        } else {
            //DBに更新入力
        	holdingTaxInfoService.updateHoldingTax(holdingTaxInfoFormBean);
        }

        return "redirect:/holdingTaxInfoList";
    }

}
