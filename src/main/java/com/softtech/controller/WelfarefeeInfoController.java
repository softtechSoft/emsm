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

import com.softtech.actionForm.WelfarefeeInfoFormBean;
import com.softtech.common.ListIDName;
import com.softtech.common.WelfarefeeIDName;
import com.softtech.entity.WelfarefeeInfoEntity;
//import com.softtech.service.LoginService;
import com.softtech.service.WelfarefeeInfoService;

/**
 * @program
 * @概要:
 * @作成者:孫曄
 * @作成日:2022-05-31
 * @return:
 */
@Controller
public class WelfarefeeInfoController {
    static protected Logger logger = LogManager.getLogger(WelfarefeeInfoController.class);
    //IOC
   // @Resource
  // private WelfarefeeInfoService welfarefeeInfoService;
//    @Resource
//    private LoginService loginService;
    @Autowired
    WelfarefeeInfoService welfarefeeInfoService;
    /**
     * 概要:年度の選択枠を設定する
     *
     * @param:[model]
     * @return:welfarefeeInfoList
     * @author:孫曄@SOFTTECH
     * @date:2022/05/29
     */
    @RequestMapping("/initWelfarefeeInfoList")
    public String toinitWelfarefeeInfoList(Model model) {
        logger.info("start index()");

        WelfarefeeInfoFormBean welfarefeeInfoFormBean = new WelfarefeeInfoFormBean();

       model.addAttribute("welfarefeeInfoFormBean", welfarefeeInfoFormBean);

     //枠用の年度を生成
       ArrayList<ListIDName> listIDNameList =
    		   welfarefeeInfoService.getOldYears(3);
       //年度リスト候補を画面へ渡す
       model.addAttribute("listIDNameList", listIDNameList);

        return "welfarefeeInfoList";

    }

    /**
     * 概要:年度と入力の収入を分別検索する
     *
     * @param:[welfarefeeInfoFormBean, model]
     * @return:welfarefeeInfoList
     * @author:孫曄@SOFTTECH
     * @date:2022/05/30
     */
//   @SuppressWarnings("deprecation")
	@RequestMapping("/welfarefeeInfoList")
    public String welfarefeeInfo(@ModelAttribute("welfarefeeInfoFormBean") WelfarefeeInfoFormBean welfarefeeInfoFormBean, Model model) {

        logger.debug("debug test");
        logger.info("info test");
        logger.warn("warn test");
        logger.error("error test");
        String year = welfarefeeInfoFormBean.getYear();

        	 //年度により、検索する
            List<WelfarefeeInfoEntity> bList =
            		welfarefeeInfoService.getWelfarefeeInfoByYear(year);
            //年度リスト候補生成
            ArrayList<ListIDName> listIDNameList =
            		welfarefeeInfoService.getOldYears(3);

            //年度リスト候補を画面へ渡す
            model.addAttribute("listIDNameList", listIDNameList);
            model.addAttribute("welfarefeeInfoFormBean", welfarefeeInfoFormBean);
            model.addAttribute("list", bList);
            return "welfarefeeInfoList";
    }

    /**
     * 概要:検索と更新画面の初期表示
     *
     * @param:[welfarefeeInfoFormBean, model]
     * @return:welfarefeeInfoEdit
     * @author:孫曄@SOFTTECH
     * @date:2022/05/30
     */
    @RequestMapping("/toinitWelfarefeeInfo")
    public String toinitWelfarefeeInfo(@ModelAttribute("welfarefeeInfoFormBean") WelfarefeeInfoFormBean welfarefeeInfoFormBean, Model model) {
        //IDを取得
    	String welfarefeeID = welfarefeeInfoFormBean.getWelfarefeeID();

        //新規フラグを取得
        String insertFlg = welfarefeeInfoFormBean.getInsertFlg();
        //新規の場合
        if ("0".equals(insertFlg)) {
            WelfarefeeInfoFormBean welfarefeeInfoFormBean1 = new WelfarefeeInfoFormBean();

            //获取当前时间，显示在页面上
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String format = formatter.format(now);

          //welfarefeeIDを採番する（既存の最大値＋１）
//            String maxWelfarefeeID = welfarefeeInfoService.getNextWelfarefeeID();
//            welfarefeeInfoFormBean1.setWelfarefeeID(maxWelfarefeeID);

            //新規
            welfarefeeInfoFormBean1.setInsertFlg(insertFlg);
            welfarefeeInfoFormBean1.setInsertDate(format);
            welfarefeeInfoFormBean1.setUpdateDate(format);

            model.addAttribute("welfarefeeInfoFormBean", welfarefeeInfoFormBean1);

            //更新の場合
        } else {
            //選択された内容を取得する
            welfarefeeID = welfarefeeInfoFormBean.getWelfarefeeID();
        	welfarefeeID = String.valueOf(welfarefeeInfoFormBean.getWelfarefeeID());
            List<WelfarefeeInfoEntity> bList =
                    welfarefeeInfoService.getUpdateWelfarefeeInfoList(welfarefeeID);

            WelfarefeeInfoFormBean welfarefeeInfoFormBean2 =
                    welfarefeeInfoService.transforEntityToUI(bList);

            //更新
            welfarefeeInfoFormBean2.setInsertFlg(insertFlg);
            model.addAttribute("welfarefeeInfoFormBean", welfarefeeInfoFormBean2);
        }

      //更新画面の年度を表示する用リスト候補生成
    	List<WelfarefeeIDName> year = welfarefeeInfoService.getYear();
    	model.addAttribute("year", year);

        return "welfarefeeInfoEdit";
    }

    /**
     * 概要:更新と新規ボタン
     *
     * @param:[welfarefeeInfoFormBean, result, session, model]
     * @return:welfarefeeInfoEdit
     * @author:孫曄@SOFTTECH
     * @date:2022/05/30
     */
    @RequestMapping(value = "/welfarefeeInfoEdit", method = RequestMethod.POST)
    public String registWelfarefeeInfo(@Valid @ModelAttribute("welfarefeeInfoFormBean") WelfarefeeInfoFormBean welfarefeeInfoFormBean, BindingResult result, HttpSession session, Model model) {

        //必須チェック
        if (result.hasErrors()) {
        	// エラーチェック用リスト
        	List<FieldError> errorlst = new ArrayList<FieldError>();

        	//エラーメッセージ。
        	errorlst.addAll(result.getFieldErrors());
        	//エラーメッセージ
        	model.addAttribute("errors", errorlst);
        	model.addAttribute("welfarefeeInfoFormBean",welfarefeeInfoFormBean);
            return "welfarefeeInfoEdit";
        }

        {
        String insertFlg = welfarefeeInfoFormBean.getInsertFlg();
        //新規の場合
        if ("0".equals(insertFlg)) {
            welfarefeeInfoService.insertWelfarefeeInfo(welfarefeeInfoFormBean);
        } else {
            //DBに更新入力
//        	String str = welfarefeeInfoFormBean.getWelfarefeeID();
            welfarefeeInfoService.updateWelfarefeeInfo(welfarefeeInfoFormBean);
        }
        //DBから年度リスト生成

       // List<WelfarefeeIDName> year =  welfarefeeInfoService.getYear();
       // model.addAttribute("year", year);
        String year = welfarefeeInfoFormBean.getYear();

//        return "welfarefeeInfoEdit";
        return "redirect:/welfarefeeInfoList?year=" + year;

        }
    }
    
    /**
     * 全量検索（全てのデータを表示）
     */
    @RequestMapping("/welfarefeeInfoListAll")
    public String welfarefeeInfoListAll(Model model) {
        
        // 全てのデータを検索
        List<WelfarefeeInfoEntity> allList = 
                welfarefeeInfoService.getAllWelfarefeeInfo();
        
        // 年度リスト候補生成
        ArrayList<ListIDName> listIDNameList = 
                welfarefeeInfoService.getOldYears(3);
        
        // 新しいFormBeanを作成（年度選択をクリア）
        WelfarefeeInfoFormBean welfarefeeInfoFormBean = new WelfarefeeInfoFormBean();
        
        // 画面へ渡す
        model.addAttribute("listIDNameList", listIDNameList);
        model.addAttribute("welfarefeeInfoFormBean", welfarefeeInfoFormBean);
        model.addAttribute("list", allList);
        
        return "welfarefeeInfoList";
    }
}
