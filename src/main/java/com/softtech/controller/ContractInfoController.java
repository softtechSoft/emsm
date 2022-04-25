package com.softtech.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 概要：契約情報リスト初期表示
 *
 * 作成者：テー@ソフトテク
 * 作成日：2022/4/25
 *
 */
@Controller
public class ContractInfoController {

	@RequestMapping("/initContractInfoList")
	public String toContractInfoList(Model model, String contractInfoList) {
		return "contractInfoList";


}
}