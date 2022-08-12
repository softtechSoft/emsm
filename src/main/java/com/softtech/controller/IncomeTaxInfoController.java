package com.softtech.controller;

import com.softtech.actionForm.IncomeTaxInfoFormBean;
import com.softtech.common.IncomeTaxIDName;
import com.softtech.entity.IncomeTaxInfoEntity;
import com.softtech.service.IncomTaxInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    /**概要:年度の選択枠を設定する
    *@param:[model]
    *@return:java.lang.String
    *@author:孫曄@SOFTTECH
    *@date:2022/08/12
    */
    @RequestMapping("/initIncomeTaxInfoList")
    public String toinitIncomeTaxInfoList(Model model) {
        logger.info("start index()");
        //枠用の年度を生成
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        String format = formatter.format(now);
        String year = new BigDecimal(format).subtract(BigDecimal.ONE).toString();
        String year1 =
                new BigDecimal(format).subtract(BigDecimal.ONE).subtract(BigDecimal.ONE).toString();
        //枠用の年度リスト候補生成
        ArrayList<IncomeTaxIDName> incomeTaxIDNameList = new ArrayList<>();
        IncomeTaxIDName incomeTaxIDName = new IncomeTaxIDName();
        incomeTaxIDName.setYear(format);
        incomeTaxIDNameList.add(incomeTaxIDName);

        IncomeTaxIDName incomeTaxIDName1 = new IncomeTaxIDName();
        incomeTaxIDName1.setYear(year);
        incomeTaxIDNameList.add(incomeTaxIDName1);

        IncomeTaxIDName incomeTaxIDName2 = new IncomeTaxIDName();
        incomeTaxIDName2.setYear(year1);
        incomeTaxIDNameList.add(incomeTaxIDName2);

        IncomeTaxInfoFormBean incomeTaxInfoFormBean = new IncomeTaxInfoFormBean();
        //年度を任意設定
        incomeTaxInfoFormBean.setYear("2020");

        model.addAttribute("incomeTaxInfoFormBean", incomeTaxInfoFormBean);
        //年度リスト候補を画面へ渡す
        model.addAttribute("incomeTaxIDNameList", incomeTaxIDNameList);

        return "incomeTaxInfoList";
    }


    /**概要:年度により、検索する
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

        String year = incomeTaxInfoFormBean.getYear();

        //年度により、検索する
        List<IncomeTaxInfoEntity> bList =
                incomTaxInfoService.getIncomeTaxByYear(year);
        //年度リスト候補生成
        List<IncomeTaxIDName> incomeTaxIDNameList = incomTaxInfoService.getYear();

        model.addAttribute("incomeTaxIDNameList", incomeTaxIDNameList);
        model.addAttribute("incomeTaxInfoFormBean", incomeTaxInfoFormBean);
        model.addAttribute("list", bList);
        return "emplyinsrateInfoList";
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
        incomeTaxInfoFormBean.setYear("2020");
        return "incomeTaxInfoEdit";
    }

}
