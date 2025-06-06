package com.softtech.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.softtech.actionForm.CompanyInfoFormBean;
import com.softtech.entity.CompanyEntity;
@Service
public interface CompanyInfoService {
	 /**
     * 概要:画面表示用のquery,入力の取引先IDにより、検索する


     */
	public List<CompanyEntity> getCompanyID(Integer companyID);
	/**　全量検索　
	 * @param なし
	 */
	public List<CompanyEntity> getAllCompany();
	 List<CompanyInfoFormBean> queryCompanyInfo();
//	public Integer getNextCompanyID();
	public List<CompanyEntity> getCompanyInfo(Integer companyID);
	public CompanyInfoFormBean tranforEntitytoUI(List<CompanyEntity> sList);


	   void addCompany(CompanyInfoFormBean companyInfoFormBean);
	   boolean updateCompany(CompanyInfoFormBean companyInfoFormBean);


}