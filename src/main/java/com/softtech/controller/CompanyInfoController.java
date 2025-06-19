package com.softtech.controller;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

import com.softtech.actionForm.CompanyInfoFormBean;
import com.softtech.entity.CompanyEntity;
import com.softtech.service.CompanyInfoService;
/**
 * 概要：契約情報リスト初期表示
 *
 * 作成者：テー@ソフトテク
 * 作成日：2022/4/25
 *
 */
@Controller
public class CompanyInfoController {
	static protected Logger logger = LogManager.getLogger(ContractInfoController.class);


    // 契約情報サービス
	@Autowired
	CompanyInfoService companyInfoService;

	/*
	 * 機能概要：契約情報リストの初期表示
	 *
	 * @param  model
	 * @author テー@it-softtech.com
	 *
	 */
	@RequestMapping("/company")
	public String company(Model model) {
		  logger.info("start index()");
		//社員IDリスト候補生成
		  List<CompanyInfoFormBean> companyList = companyInfoService.queryCompanyInfo();
		  model.addAttribute("companyList",companyList);
		CompanyInfoFormBean companyInfoBean = new CompanyInfoFormBean();


		model.addAttribute("companyInfoBean",companyInfoBean);


		return "companyInfoList";

	}
	/*
	 * 機能概要：契約情報検索
	 *
	 * @param  model
	 * @author テー@it-softtech.com
	 *
	 */
	@RequestMapping("/companyInfoList")
	public String contractInfo(@ModelAttribute("companyInfoBean") CompanyInfoFormBean companyInfoBean,
								Model model) {
		List<CompanyInfoFormBean> companyList = companyInfoService.queryCompanyInfo();
		model.addAttribute("companyList",companyList);
		// 検索flg
		String selectFlg = companyInfoBean.getSelectFlg();
		// 検索ボタン押す時selectFlg
		if ("0".equals(selectFlg)){
			//IDを取得

			String companyID = companyInfoBean.getCompanyID();
			List<CompanyEntity> list = companyInfoService.getCompanyID(companyID);
			model.addAttribute("list",list);

		}
		//全量検索ボタン押す時selectFlg　'1'
				else {
					List<CompanyEntity> listAll = companyInfoService.getAllCompany();
					companyInfoBean.setSelectFlg(selectFlg);
					model.addAttribute("list",listAll);

				}
		return "companyInfoList";

	}


	@RequestMapping("/toInitCompanyInfo")
	public String initContractInfoList(@ModelAttribute("companyInfoBean") CompanyInfoFormBean companyInfoBean,
			Model model) {






		//新規フラグを取得
		String insertFlg = companyInfoBean.getInsertFlg();
		//新規の場合
		if("0".equals(insertFlg)) {
			CompanyInfoFormBean companyInfoBeans = new CompanyInfoFormBean();

			// 契約情報を採番する（既存の最大値＋１）
			String maxCompanyID =companyInfoService.getNextCompanyID();
			companyInfoBeans.setCompanyID(maxCompanyID);
			
			 LocalDateTime now = LocalDateTime.now();
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	            String currentDate = formatter.format(now);

	            companyInfoBeans.setInsertDate(currentDate);
	            companyInfoBeans.setUpdateDate(currentDate);
				
			model.addAttribute("companyInfoBean",companyInfoBeans);
			return "companyAdd" ;

		//更新の場合
		} else {
			//　選択された契約の内容を取得する
			String companyID = companyInfoBean.getCompanyID();
			List<CompanyEntity> sList= companyInfoService.getCompanyInfo(companyID);

			CompanyInfoFormBean companyInfoBean1 = companyInfoService.tranforEntitytoUI(sList);
			//更新
//			contractInfoFormBean.setInsertFlg(insertFlg);

			model.addAttribute("companyInfoBean",companyInfoBean1);
			return "companyInfoEdit";
		}

	}
	 @RequestMapping(value = "/companyAdd", method = RequestMethod.POST)
	    public String addCompany(@Valid @ModelAttribute("companyInfoBean") CompanyInfoFormBean companyInfoFormBean,
	                             BindingResult result, Model model) {
	        if (result.hasErrors()) {

	            List<FieldError> errorList = new ArrayList<>();

	            errorList.addAll(result.getFieldErrors());

	            model.addAttribute("errors", errorList);
	            model.addAttribute("companyInfoBean", companyInfoFormBean);

	            return "companyAdd";
	        }

	        try {
	        	LocalDateTime now = LocalDateTime.now();
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	            String currentDate = formatter.format(now);
	            companyInfoFormBean.setInsertDate(currentDate);
	            companyInfoFormBean.setUpdateDate(currentDate);

	        	companyInfoService.addCompany(companyInfoFormBean);
	            model.addAttribute("message", "登録完了");
	            
	            return "redirect:/companyInfoList?selectFlg=1";
	            
	        } catch (Exception e) {

	            model.addAttribute("errors", "Error occurred while adding company: " + e.getMessage());
	        }
	        return "companyAdd";
	    }


	  @RequestMapping(value = "/updateCompany", method = RequestMethod.POST)
	    public String updateCompany(
	            @Valid @ModelAttribute("companyInfoBean") CompanyInfoFormBean companyInfoFormBean,
	            BindingResult result,
	            Model model) {

	        if (result.hasErrors()) {
	            // エラーがある場合は、エラーメッセージを含めて再度編集画面に戻る
	            model.addAttribute("errors", result.getFieldErrors());
	            return "companyInfoEdit";
	        }
	        LocalDateTime now = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	        String currentDate = formatter.format(now);
	        companyInfoFormBean.setUpdateDate(currentDate);

	        // 取引先情報を更新
	        boolean updateSuccess = companyInfoService.updateCompany(companyInfoFormBean);

	        if (updateSuccess) {
	            model.addAttribute("message", "更新に成功しました");
	            
	            return "redirect:/companyInfoList?selectFlg=1";
	            
	        } else {
	            model.addAttribute("message", "更新に失敗しました");
	        }

	        return "companyInfoEdit"; // 更新後に編集画面に戻る
	    }

}
