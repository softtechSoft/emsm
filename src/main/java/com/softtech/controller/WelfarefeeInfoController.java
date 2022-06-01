package com.softtech.controller;

import com.softtech.actionForm.WelfarefeeInfoFormBean;
import com.softtech.common.WelfarefeeIDName;
import com.softtech.entity.WelfarefeeInfoEntity;
import com.softtech.service.LoginService;
import com.softtech.service.WelfarefeeInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
    @Resource
    private WelfarefeeInfoService welfarefeeInfoService;
    @Resource
    private LoginService loginService;

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
        //枠用の年度リスト候補生成
        List<WelfarefeeIDName> welfarefeeIDNameList = loginService.getYear();

        WelfarefeeInfoFormBean welfarefeeInfoFormBean = new WelfarefeeInfoFormBean();
        //年度を任意設定
        welfarefeeInfoFormBean.setYear("2020");

        model.addAttribute("welfarefeeInfoFormBean", welfarefeeInfoFormBean);
        //年度リスト候補を画面へ渡す
        model.addAttribute("welfarefeeIDNameList", welfarefeeIDNameList);

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
    @RequestMapping("/welfarefeeInfoList")
    public String WelfarefeeInfo(@ModelAttribute("welfarefeeInfoFormBean") WelfarefeeInfoFormBean welfarefeeInfoFormBean, Model model) {

        logger.debug("debug test");
        logger.info("info test");
        logger.warn("warn test");
        logger.error("error test");
        String year = welfarefeeInfoFormBean.getYear();
        String enterSalary = welfarefeeInfoFormBean.getEnterSalary();


        if (!StringUtils.isEmpty(year) && StringUtils.isEmpty(enterSalary)) {
            //年度により、検索する
            List<WelfarefeeInfoEntity> bList = welfarefeeInfoService.getWelfarefeeInfoByYear(year);

            //年度リスト候補生成
            List<WelfarefeeIDName> welfarefeeList = loginService.getYear();
            //年度リスト候補を画面へ渡す
            model.addAttribute("welfarefeeList", welfarefeeList);
            model.addAttribute("welfarefeeInfoFormBean", welfarefeeInfoFormBean);
            model.addAttribute("list", bList);


        } else if (!StringUtils.isEmpty(enterSalary)) {
            //enterSalaryと収入により、検索する
            List<WelfarefeeInfoEntity> eList =
                    welfarefeeInfoService.getWelfarefeeInfoByYearAndEnterSalary(enterSalary, year);
            //リスト候補を画面へ渡す
            model.addAttribute("welfarefeeInfoFormBean", welfarefeeInfoFormBean);
            model.addAttribute("list", eList);


        }
        return "welfarefeeInfoList";
    }

    /**
     * 概要:更新画面の初期表示
     *
     * @param:[welfarefeeInfoFormBean, model]
     * @return:welfarefeeInfoEdit
     * @author:孫曄@SOFTTECH
     * @date:2022/05/30
     */
    @RequestMapping("/toinitWelfarefeeInfo")
    public String initWelfarefeeInfoList(@ModelAttribute("welfarefeeInfoFormBean") WelfarefeeInfoFormBean welfarefeeInfoFormBean, Model model) {
        //IDを取得
        String welfarefeeID = welfarefeeInfoFormBean.getWelfarefeeID();

        //新規フラグを取得
        String insertFlg = welfarefeeInfoFormBean.getInsertFlg();
        //新規の場合
        if ("0".equals(insertFlg)) {
            WelfarefeeInfoFormBean welfarefeeInfoFormBean1 = new WelfarefeeInfoFormBean();

            //welfarefeeIDを採番する（既存の最大値＋１）
            String maxWelfarefeeID = welfarefeeInfoService.getNextWelfarefeeID();
            welfarefeeInfoFormBean1.setWelfarefeeID(maxWelfarefeeID);
            //新規
            welfarefeeInfoFormBean1.setInsertFlg(insertFlg);

            model.addAttribute("welfarefeeInfoFormBean", welfarefeeInfoFormBean1);

            //更新の場合
        } else {
            //選択された内容を取得する
            welfarefeeID = welfarefeeInfoFormBean.getWelfarefeeID();
            List<WelfarefeeInfoEntity> bList =
                    welfarefeeInfoService.getUpdateWelfarefeeInfoList(welfarefeeID);

            WelfarefeeInfoFormBean welfarefeeInfoFormBean2 =
                    welfarefeeInfoService.transforEntityToUI(bList);
            //更新
            welfarefeeInfoFormBean2.setInsertFlg(insertFlg);
            model.addAttribute("welfarefeeInfoFormBean", welfarefeeInfoFormBean2);
        }


        //年度リスト候補生成
        //只有年度选择框，输入收入的只是输入的数值，修改和更新都没有输入收入这一项
        List<WelfarefeeIDName> year = loginService.getYear();
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
            //年度リスト候補生成
            List<WelfarefeeIDName> year = loginService.getYear();
            model.addAttribute("year", year);

            //年度を任意設定
            welfarefeeInfoFormBean.setYear("2020");

            return "welfarefeeInfoEdit";
        }

        String insertFlg = welfarefeeInfoFormBean.getInsertFlg();
        //新規の場合
        if ("0".equals(insertFlg)) {
            welfarefeeInfoService.insertWelfarefeeInfo(welfarefeeInfoFormBean);
        } else {
            //DBに更新入力
            welfarefeeInfoService.updateWelfarefeeInfo(welfarefeeInfoFormBean);
        }

        //年度リスト候補生成
        List<WelfarefeeIDName> year = loginService.getYear();
        model.addAttribute("year", year);

        //年度を任意設定
        welfarefeeInfoFormBean.setYear("2020");
        return "welfarefeeInfoEdit";
    }
}
