package com.softtech.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtech.actionForm.EmployeeActionForm;
import com.softtech.actionForm.IncomeTaxInfoFormBean;
import com.softtech.common.IncomeTaxIDName;
import com.softtech.entity.IncomeTaxInfoEntity;
import com.softtech.service.IncomTaxInfoService;

/**
 * @program
 * @概要:
 * @作成者:孫曄
 * @作成日:2022-08-12
 * @return:
 */
@Controller
public class IncomeTaxInfoController {
    //IOC
    @Autowired
    private IncomTaxInfoService incomTaxInfoService;
    //logger
    static protected Logger logger = LogManager.getLogger(IncomeTaxInfoController.class);

    /**概要:社員IDの選択枠を設定する
    *@param:[model]
    *@return:java.lang.String
    *@author:孫曄@SOFTTECH
    *@date:2022/08/12
    */
    @RequestMapping("/initIncomeTaxInfoList")
    public String toinitIncomeTaxInfoList(Model model) {
        logger.info("start index()");

        //DBから社員情報を取得する,画面の社員ID選択,枠に表示する
        List<EmployeeActionForm> employeeList = incomTaxInfoService.queryEmployeeInfo();
        model.addAttribute("employeeList", employeeList);
        
        List<String> yearList = incomTaxInfoService.getDistinctYears();
        model.addAttribute("yearList", yearList);

        //画面初期表示用のFormBeam
        IncomeTaxInfoFormBean incomeTaxInfoFormBean = new IncomeTaxInfoFormBean();
        incomeTaxInfoFormBean.setEmployeeID("");
        incomeTaxInfoFormBean.setSearchYear("");
        
        model.addAttribute("incomeTaxInfoFormBean", incomeTaxInfoFormBean);
        return "incomeTaxInfoList";
    }


    /**概要:社員IDにより、検索する
    *@param:[incomeTaxInfoFormBean, model]
    *@return:java.lang.String
    *@author:孫曄@SOFTTECH
    *@date:2022/08/12
    */
    @RequestMapping("/incomeTaxInfoList")
    public String incomeTaxInfo(@ModelAttribute("incomeTaxInfoFormBean") IncomeTaxInfoFormBean incomeTaxInfoFormBean,
                                   Model model) {
        logger.debug("debug test");
        logger.info("info test");
        logger.warn("warn test");
        logger.error("error test");
        logger.fatal("fatal test");

        String employeeID = incomeTaxInfoFormBean.getEmployeeID();
        String searchYear = incomeTaxInfoFormBean.getSearchYear();
        //社員IDにより、検索する
//        List<IncomeTaxInfoEntity> bList =
//                incomTaxInfoService.getIncomeTaxByEmployeeID(employeeID);
        List<IncomeTaxInfoEntity> bList = 
                incomTaxInfoService.getIncomeTaxByCondition(employeeID, searchYear);
        //社員ID候補を取得する
        List<EmployeeActionForm> employeeList = incomTaxInfoService.queryEmployeeInfo();
        
        List<String> yearList = incomTaxInfoService.getDistinctYears();

        model.addAttribute("employeeList", employeeList);
        model.addAttribute("yearList", yearList);
        model.addAttribute("incomeTaxInfoFormBean", incomeTaxInfoFormBean);
        model.addAttribute("list", bList);
        return "incomeTaxInfoList";
    }

    /**概要:検索と更新画面の初期表示
    *@param:[incomeTaxInfoFormBean, model]
    *@return:java.lang.String
    *@author:孫曄@SOFTTECH
    *@date:2022/08/12
    */
    @RequestMapping("/toinitIncomeTaxInfo")
    public String toinitIncomeTaxInfo(@ModelAttribute("incomeTaxInfoFormBean") IncomeTaxInfoFormBean incomeTaxInfoFormBean,
                                      Model model) {
        //IDを取得
        String incomeTaxID = incomeTaxInfoFormBean.getIncomeTaxID();

        //新規フラグを取得
        String insertFlg = incomeTaxInfoFormBean.getInsertFlg();
        //新規の場合
        if ("0".equals(insertFlg)) {
            IncomeTaxInfoFormBean incomeTaxInfoFormBean1 = new IncomeTaxInfoFormBean();

            //incomeTaxIDを採番する（既存の最大値＋１）
            //获取当前时间，显示在页面上
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String format = formatter.format(now);

            String maxIncomeTaxID = incomTaxInfoService.getNextIncomeTaxID();
            incomeTaxInfoFormBean1.setIncomeTaxID(maxIncomeTaxID);
            //新規
            incomeTaxInfoFormBean1.setInsertFlg(insertFlg);
            incomeTaxInfoFormBean1.setInsertDate(format);
            incomeTaxInfoFormBean1.setUpdateDate(format);

            model.addAttribute("incomeTaxInfoFormBean", incomeTaxInfoFormBean1);

            //更新の場合
        } else {
            //選択された内容を取得する
            incomeTaxID = incomeTaxInfoFormBean.getIncomeTaxID();
            List<IncomeTaxInfoEntity> bList =
                    incomTaxInfoService.getUpdateIncomeTaxByIncomeTaxID(incomeTaxID);

            IncomeTaxInfoFormBean incomeTaxInfoFormBean2 =
                    incomTaxInfoService.transforEntityToUI(bList);

            //更新
            incomeTaxInfoFormBean2.setInsertFlg(insertFlg);
            model.addAttribute("incomeTaxInfoFormBean", incomeTaxInfoFormBean2);
        }
        //DBから社員情報を取得する,画面の社員ID選択,枠に表示する
        List<EmployeeActionForm> employeeList = incomTaxInfoService.queryEmployeeInfo();
        model.addAttribute("employeeList", employeeList);


        //更新画面の年度を表示する用リスト候補生成
        List<IncomeTaxIDName> year = incomTaxInfoService.getYear();
        model.addAttribute("year", year);

        return "incomeTaxInfoEdit";
    }

    /**概要:更新と新規ボタン
    *@param:[incomeTaxInfoFormBean, result, session, model]
    *@return:java.lang.String
    *@author:孫曄@SOFTTECH
    *@date:2022/08/12
    */
    @RequestMapping(value = "/incomeTaxInfoEdit", method = RequestMethod.POST)
    public String registIncomeTaxInfo(@Valid @ModelAttribute("incomeTaxInfoFormBean") IncomeTaxInfoFormBean incomeTaxInfoFormBean,
                                      BindingResult result, HttpSession session, Model model) {

        //必須チェック
        if (result.hasErrors()) {
            //DBから年度リスト生成
            List<IncomeTaxIDName> year = incomTaxInfoService.getYear();
            model.addAttribute("year", year);
            
            List<EmployeeActionForm> employeeList = incomTaxInfoService.queryEmployeeInfo();
            model.addAttribute("employeeList", employeeList);

            //年度を任意設定
            incomeTaxInfoFormBean.setYear("2020");

            return "incomeTaxInfoEdit";
        }

        String insertFlg = incomeTaxInfoFormBean.getInsertFlg();
        //新規の場合
        if ("0".equals(insertFlg)) {
            incomTaxInfoService.insertIncomeTaxInfo(incomeTaxInfoFormBean);
        } else {
            //DBに更新入力
            incomTaxInfoService.updateIncomeTax(incomeTaxInfoFormBean);
        }

        //DBから年度リスト生成
        List<IncomeTaxIDName> year = incomTaxInfoService.getYear();
        model.addAttribute("year", year);

        //年度を任意設定
        //incomeTaxInfoFormBean.setYear("2020");
//        return "incomeTaxInfoEdit";
        return "redirect:/incomeTaxInfoList";
    }

}
