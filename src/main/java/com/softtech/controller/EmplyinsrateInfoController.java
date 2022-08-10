package com.softtech.controller;

import com.softtech.actionForm.EmplyinsrateInfoFormBean;
import com.softtech.common.EmplyinsrateIDName;
import com.softtech.entity.EmplyinsrateInfoEntity;
import com.softtech.service.EmplyinsrateInfoService;
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
 * @作成日:2022-08-08
 * @return:
 */
@Controller
public class EmplyinsrateInfoController {
    //logger
    static protected Logger logger = LogManager.getLogger(EmplyinsrateInfoController.class);
    //IOC Service
    @Autowired
    EmplyinsrateInfoService emplyinsrateInfoService;

    /**
     * 概要:年度の選択枠を設定する
     *
     * @param:[model]
     * @return:java.lang.String
     * @author:孫曄@SOFTTECH
     * @date:2022/08/08
     */
    @RequestMapping("/initEmplyinsrateInfoList")
    public String toinitEmplyinsrateInfoList(Model model) {
        logger.info("start index()");
        //枠用の年度を生成
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        String format = formatter.format(now);
        String year = new BigDecimal(format).subtract(BigDecimal.ONE).toString();
        String year1 =
                new BigDecimal(format).subtract(BigDecimal.ONE).subtract(BigDecimal.ONE).toString();
        //枠用の年度リスト候補生成
        ArrayList<EmplyinsrateIDName> emplyinsrateIDNameList = new ArrayList<>();
        EmplyinsrateIDName emplyinsrateIDName = new EmplyinsrateIDName();
        emplyinsrateIDName.setYear(format);
        emplyinsrateIDNameList.add(emplyinsrateIDName);

        EmplyinsrateIDName emplyinsrateIDName1 = new EmplyinsrateIDName();
        emplyinsrateIDName1.setYear(year);
        emplyinsrateIDNameList.add(emplyinsrateIDName1);

        EmplyinsrateIDName emplyinsrateIDName2 = new EmplyinsrateIDName();
        emplyinsrateIDName2.setYear(year1);
        emplyinsrateIDNameList.add(emplyinsrateIDName2);

        EmplyinsrateInfoFormBean emplyinsrateInfoFormBean = new EmplyinsrateInfoFormBean();
        //年度を任意設定
        emplyinsrateInfoFormBean.setYear("2020");

        model.addAttribute("emplyinsrateInfoFormBean", emplyinsrateInfoFormBean);
        //年度リスト候補を画面へ渡す
        model.addAttribute("emplyinsrateIDNameList", emplyinsrateIDNameList);

        return "emplyinsrateInfoList";
    }

    /**
     * 概要:年度により、検索する
     *
     * @param:[emplyinsrateInfoFormBean, model]
     * @return:java.lang.String
     * @author:孫曄@SOFTTECH
     * @date:2022/08/08
     */
    @RequestMapping("/emplyinsrateInfoList")
    public String emplyinsrateInfo(@ModelAttribute("emplyinsrateInfoFormBean") EmplyinsrateInfoFormBean emplyinsrateInfoFormBean, Model model) {
        logger.debug("debug test");
        logger.info("info test");
        logger.warn("warn test");
        logger.error("error test");

        String year = emplyinsrateInfoFormBean.getYear();

        //年度により、検索する
        List<EmplyinsrateInfoEntity> bList =
                emplyinsrateInfoService.getEmplyinsrateInfoByYear(year);
        //年度リスト候補生成
        List<EmplyinsrateIDName> emplyinsrateIDNameList = emplyinsrateInfoService.getYear();

        model.addAttribute("emplyinsrateIDNameList", emplyinsrateIDNameList);
        model.addAttribute("emplyinsrateInfoFormBean", emplyinsrateInfoFormBean);
        model.addAttribute("list", bList);
        return "emplyinsrateInfoList";
    }

    /**
     * 概要:検索と更新画面の初期表示
     *
     * @param:[emplyinsrateInfoFormBean]
     * @return:java.lang.String
     * @author:孫曄@SOFTTECH
     * @date:2022/08/08
     */
    @RequestMapping("/toinitEmplyinsrateInfo")
    public String toinitEmplyinsrateInfo(@ModelAttribute("emplyinsrateInfoFormBean") EmplyinsrateInfoFormBean emplyinsrateInfoFormBean, Model model) {
        //IDを取得
        String emplyinsrateID = emplyinsrateInfoFormBean.getEmplyinsrateID();

        //新規フラグを取得
        String insertFlg = emplyinsrateInfoFormBean.getInsertFlg();
        //新規の場合
        if ("0".equals(insertFlg)) {
            EmplyinsrateInfoFormBean emplyinsrateInfoFormBean1 = new EmplyinsrateInfoFormBean();

            //emplyinsrateIDを採番する（既存の最大値＋１）
            //获取当前时间，显示在页面上
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String format = formatter.format(now);

            String maxEmplyinsrateID = emplyinsrateInfoService.getNextEmplyinsrateID();
            emplyinsrateInfoFormBean1.setEmplyinsrateID(maxEmplyinsrateID);
            //新規
            emplyinsrateInfoFormBean1.setInsertFlg(insertFlg);
            emplyinsrateInfoFormBean1.setInsertDate(format);
            emplyinsrateInfoFormBean1.setUpdateDate(format);

            model.addAttribute("emplyinsrateInfoFormBean", emplyinsrateInfoFormBean1);

            //更新の場合
        } else {
            //選択された内容を取得する
            emplyinsrateID = emplyinsrateInfoFormBean.getEmplyinsrateID();
            List<EmplyinsrateInfoEntity> bList =
                    emplyinsrateInfoService.getUpdateEmplyinsrateInfoList(emplyinsrateID);

            EmplyinsrateInfoFormBean emplyinsrateInfoFormBean2 =
                    emplyinsrateInfoService.transforEntityToUI(bList);
            //更新
            emplyinsrateInfoFormBean2.setInsertFlg(insertFlg);
            model.addAttribute("emplyinsrateInfoFormBean", emplyinsrateInfoFormBean2);
        }


        //更新画面の年度を表示する用リスト候補生成
        List<EmplyinsrateIDName> year = emplyinsrateInfoService.getYear();
        model.addAttribute("year", year);

        return "emplyinsrateInfoEdit";
    }

    /**
     * 概要:更新と新規ボタン
     *
     * @param:[emplyinsrateInfoFormBean, result, session, model]
     * @return:java.lang.String
     * @author:孫曄@SOFTTECH
     * @date:2022/08/08
     */
    @RequestMapping(value = "/emplyinsrateInfoEdit", method = RequestMethod.POST)
    public String registEmplyinsrateInfo(@Valid @ModelAttribute("emplyinsrateInfoFormBean") EmplyinsrateInfoFormBean emplyinsrateInfoFormBean, BindingResult result, HttpSession session, Model model) {

        //必須チェック
        if (result.hasErrors()) {
            //DBから年度リスト生成
            List<EmplyinsrateIDName> year = emplyinsrateInfoService.getYear();
            model.addAttribute("year", year);

            //年度を任意設定
            emplyinsrateInfoFormBean.setYear("2020");

            return "emplyinsrateInfoEdit";
        }

        String insertFlg = emplyinsrateInfoFormBean.getInsertFlg();
        //新規の場合
        if ("0".equals(insertFlg)) {
            emplyinsrateInfoService.insertEmplyinsrateInfo(emplyinsrateInfoFormBean);
        } else {
            //DBに更新入力
            emplyinsrateInfoService.updateEmplyinsrateInfo(emplyinsrateInfoFormBean);
        }

        //DBから年度リスト生成
        List<EmplyinsrateIDName> year = emplyinsrateInfoService.getYear();
        model.addAttribute("year", year);

        //年度を任意設定
        emplyinsrateInfoFormBean.setYear("2020");
        return "emplyinsrateInfoEdit";
    }

}

