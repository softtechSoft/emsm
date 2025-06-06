package com.softtech.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtech.actionForm.WelfareBabyInfoFormBean;
import com.softtech.common.ListIDName;
import com.softtech.common.RateIDName;
import com.softtech.entity.WelfareBabyInfoEntity;
import com.softtech.service.WelfareBabyInfoService;

/**
 * @program
 * @概要:
 * @作成者:孫曄
 * @作成日:2022-05-31
 * @return:
 */
@Controller
public class WelfareBabyInfoController {
	static protected Logger logger = LogManager.getLogger(WelfareBabyInfoController.class);
	 @Resource
	 private WelfareBabyInfoService welfareBabyInfoService;
	//private WelfareBabyInfoFormBean welfareBabyInfoFormBean;
	  /**
	     * 概要:年度の選択枠を設定する
	     *
	     * @param:[model]
	     * @return:welfareBabyInfoList
	     * @author:孫曄@SOFTTECH
	     * @date:2022/05/29
	     */
	    @RequestMapping("/initWelfareBabyInfoList")
	    public String toinitWelfareBabyInfoList(Model model) {
	        logger.info("start index()");

	        WelfareBabyInfoFormBean welfareBabyInfoFormBean = new WelfareBabyInfoFormBean();
	       model.addAttribute("welfareBabyInfoFormBean", welfareBabyInfoFormBean);

	     //枠用の年度を生成
	       ArrayList<ListIDName> listIDNameList =
	    		   welfareBabyInfoService.getOldYears(3);
	       //年度リスト候補を画面へ渡す
	       model.addAttribute("listIDNameList", listIDNameList);
	      // model.addAttribute("welfarefeeIDNameList", rateIDNameList);

	        return "welfareBabyInfoList";

	    }
	    /**
	     * 概要:年度と入力の収入を分別検索する
	     *
	     * @param:[welfareBabyInfoFormBean, model]
	     * @return:welfareBabyInfoList
	     * @author:孫曄@SOFTTECH
	     * @date:2023/09/13
	     */
	//   @SuppressWarnings("deprecation")
		@RequestMapping("/welfareBabyInfoList")
	    public String WelfareBabyInfo(@ModelAttribute("welfareBabyInfoFormBean") WelfareBabyInfoFormBean welfareBabyInfoFormBean, Model model) {

	        logger.debug("debug test");
	        logger.info("info test");
	        logger.warn("warn test");
	        logger.error("error test");
	        String year = welfareBabyInfoFormBean.getYear();

	        	 //年度により、検索する
	            List<WelfareBabyInfoEntity> bList =
	            		welfareBabyInfoService.getWelfareBabyInfoByYear(year);
	            //年度リスト候補生成
	            ArrayList<ListIDName> listIDNameList =
	            		welfareBabyInfoService.getOldYears(3);

	            //年度リスト候補を画面へ渡す
	            model.addAttribute("listIDNameList", listIDNameList);
	            model.addAttribute("welfareBabyInfoFormBean", welfareBabyInfoFormBean);
	            model.addAttribute("list", bList);
	            return "welfareBabyInfoList";
	    }
		 /**
	     * 概要:検索と更新画面の初期表示
	     *
	     * @param:[welfareBabyInfoFormBean, model]
	     * @return:welfareBabyInfoEdit
	     * @author:孫曄@SOFTTECH
	     * @date:2023/09/13
	     */
	    @RequestMapping("/toinitWelfareBabyInfo")
	    public String toinitWelfareBabyInfoList(@ModelAttribute("welfareBabyInfoFormBean") WelfareBabyInfoFormBean welfareBabyInfoFormBean, Model model) {
	        //IDを取得
	        String rateID = welfareBabyInfoFormBean.getRateID();

	        //新規フラグを取得
	        String insertFlg = welfareBabyInfoFormBean.getInsertFlg();
	        //新規の場合
	        if ("0".equals(insertFlg)) {
	        	WelfareBabyInfoFormBean welfareBabyInfoFormBean1 = new WelfareBabyInfoFormBean();

	            //获取当前时间，显示在页面上
	            LocalDateTime now = LocalDateTime.now();
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	            String format = formatter.format(now);

	          //RateIDを採番する（既存の最大値＋１）
//	            String maxRateID = welfareBabyInfoService.getNextRateID();
//	            welfareBabyInfoFormBean1.setRateID(maxRateID);

	            //新規
	            welfareBabyInfoFormBean1.setInsertFlg(insertFlg);
	            welfareBabyInfoFormBean1.setInsertDate(format);
	            welfareBabyInfoFormBean1.setUpdateDate(format);

	            model.addAttribute("welfareBabyInfoFormBean", welfareBabyInfoFormBean1);

	            //更新の場合
	        } else {
	            //選択された内容を取得する
	           rateID = welfareBabyInfoFormBean.getRateID();
	            List<WelfareBabyInfoEntity> bList =
	            		welfareBabyInfoService.getUpdateWelfareBabyInfoList(rateID);

	            WelfareBabyInfoFormBean welfareBabyInfoFormBean2 =
	            		welfareBabyInfoService.transforEntityToUI(bList);

	            //更新
	            welfareBabyInfoFormBean2.setInsertFlg(insertFlg);
	            model.addAttribute("welfareBabyInfoFormBean", welfareBabyInfoFormBean2);
	        }

	      //更新画面の年度を表示する用リスト候補生成
	    	List<RateIDName> year = welfareBabyInfoService.getYear();
	    	model.addAttribute("year", year);

	        return "welfareBabyInfoEdit";
	    }

	    /**
	     * 概要:更新と新規ボタン
	     *
	     * @param:[welfareBabyInfoFormBean, result, session, model]
	     * @return:welfareBabyInfoEdit
	     * @author:孫曄@SOFTTECH
	     * @date:2023/09/13
	     */
	    @RequestMapping(value = "/welfareBabyInfoEdit", method = RequestMethod.POST)
	    public String registWelfareBabyInfo(@Valid @ModelAttribute("welfareBabyInfoFormBean") WelfareBabyInfoFormBean WelfareBabyInfoFormBean, BindingResult result, HttpSession session, Model model) {

	        //必須チェック
	        if (result.hasErrors()) {
	        	// エラーチェック用リスト
	        	List<FieldError> errorlst = new ArrayList<FieldError>();

	        	//エラーメッセージ。
	        	errorlst.addAll(result.getFieldErrors());
	        	//エラーメッセージ
	        	model.addAttribute("errors", errorlst);
	        	model.addAttribute("welfareBabyInfoFormBean",WelfareBabyInfoFormBean);
	            return "welfareBabyInfoEdit";
	        }

	        {
	        String insertFlg = WelfareBabyInfoFormBean.getInsertFlg();
	        //新規の場合
	        if ("0".equals(insertFlg)) {
	        	welfareBabyInfoService.insertWelfareBabyInfo(WelfareBabyInfoFormBean);
	        } else {
	            //DBに更新入力
	        	welfareBabyInfoService.updateWelfareBabyInfo(WelfareBabyInfoFormBean);
	        }
	        //DBから年度リスト生成
	        List<RateIDName> year =  welfareBabyInfoService.getYear();
	        model.addAttribute("year", year);

	        return "welfareBabyInfoEdit";

	        }
	    }

}
