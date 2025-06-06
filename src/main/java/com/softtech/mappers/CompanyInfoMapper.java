package com.softtech.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.actionForm.CompanyInfoFormBean;
import com.softtech.entity.CompanyEntity;
@Mapper
public interface CompanyInfoMapper{
	 //全量検索する
	public List<CompanyEntity> getAllCompany();
	//検索用のquery,companyIDにより、検索する
	public List<CompanyEntity> getCompanyID(Object companyID);
	public List<CompanyEntity> getCompany();
//	public Integer getMaxCompanyID();

	 void insertCompany(CompanyEntity companyEntity);
	  int updateCompanyInfo(CompanyInfoFormBean companyInfoFormBean);
}
