package com.softtech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.CompanyInfoFormBean;
import com.softtech.entity.CompanyEntity;
import com.softtech.mappers.CompanyInfoMapper;
import com.softtech.util.DataUtil;

@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {
	@Autowired
	private CompanyInfoMapper companyInfoMapper ;

	@Override
	public List<CompanyEntity> getCompanyID(String companyID) {
		 List<CompanyEntity> companyEntity = companyInfoMapper.getCompanyID(companyID);
		return companyEntity;
	}

	@Override
	public List<CompanyEntity> getAllCompany() {
		List<CompanyEntity> companyEntity = companyInfoMapper.getAllCompany();
		return companyEntity;
	}
	public List<CompanyInfoFormBean> transferDBTOUI(List<CompanyEntity> companyEntitys){
		 List<CompanyInfoFormBean> companyInfoFormBeans = new ArrayList<CompanyInfoFormBean>();
		 for (CompanyEntity companyEntity : companyEntitys) {
			    CompanyInfoFormBean companyInfoFormBean = new CompanyInfoFormBean();

			    companyInfoFormBean.setCompanyID(companyEntity.getCompanyID());
			    companyInfoFormBean.setCompanyName(companyEntity.getCompanyName());
			    companyInfoFormBean.setCompanyType(companyEntity.getCompanyType());
			    companyInfoFormBean.setPostCode(companyEntity.getPostCode());
			    companyInfoFormBean.setAddress(companyEntity.getAddress());
			    companyInfoFormBean.setBasicContractDate(companyEntity.getBasicContractDate());
			    companyInfoFormBean.setPhoneNumber(companyEntity.getPhoneNumber());
			    companyInfoFormBean.setContactName(companyEntity.getContactName());
			    companyInfoFormBean.setMail(companyEntity.getMail());
			    companyInfoFormBean.setContractStatus(companyEntity.getContractStatus());
			    companyInfoFormBean.setLevel(companyEntity.getLevel());
			    companyInfoFormBean.setInsertDate(companyEntity.getInsertDate());
			    companyInfoFormBean.setUpdateDate(companyEntity.getUpdateDate());


			    companyInfoFormBeans.add(companyInfoFormBean);
			}
			return companyInfoFormBeans;
	}
	@Override
	public List<CompanyInfoFormBean> queryCompanyInfo() {
		List<CompanyEntity> cpn = companyInfoMapper.getCompany();
		List<CompanyInfoFormBean> CompanyInfoFormBeanToUI = transferDBTOUI(cpn);
	return CompanyInfoFormBeanToUI;
	}
	@Override
	public String getNextCompanyID() {
		String maxCompanyID = companyInfoMapper.getMaxCompanyID();
		String nextCompanyID = DataUtil.getNextID(maxCompanyID, 2);
		return nextCompanyID;
	}

	@Override
	public List<CompanyEntity> getCompanyInfo(String companyID) {
		List<CompanyEntity> companyInfo = companyInfoMapper.getCompanyID(companyID);
		return companyInfo;
	}

	@Override
	public CompanyInfoFormBean tranforEntitytoUI(List<CompanyEntity> sList) {
		CompanyInfoFormBean companyInfoFormBean = new CompanyInfoFormBean();
		 for (CompanyEntity companyEntity : sList) {


			    companyInfoFormBean.setCompanyID(companyEntity.getCompanyID());
			    companyInfoFormBean.setCompanyName(companyEntity.getCompanyName());
			    companyInfoFormBean.setCompanyType(companyEntity.getCompanyType());
			    companyInfoFormBean.setPostCode(companyEntity.getPostCode());
			    companyInfoFormBean.setAddress(companyEntity.getAddress());
			    companyInfoFormBean.setBasicContractDate(companyEntity.getBasicContractDate());
			    companyInfoFormBean.setPhoneNumber(companyEntity.getPhoneNumber());
			    companyInfoFormBean.setContactName(companyEntity.getContactName());
			    companyInfoFormBean.setMail(companyEntity.getMail());
			    companyInfoFormBean.setContractStatus(companyEntity.getContractStatus());
			    companyInfoFormBean.setLevel(companyEntity.getLevel());
			    companyInfoFormBean.setInsertDate(companyEntity.getInsertDate());
			    companyInfoFormBean.setUpdateDate(companyEntity.getUpdateDate());



			}
			return companyInfoFormBean;
	}
	   @Override
	    public void addCompany(CompanyInfoFormBean companyInfoFormBean) {

	        CompanyEntity companyEntity = new CompanyEntity();
	        companyEntity.setCompanyID(companyInfoFormBean.getCompanyID());
	        companyEntity.setCompanyName(companyInfoFormBean.getCompanyName());
	        companyEntity.setCompanyType(companyInfoFormBean.getCompanyType());
	        companyEntity.setPostCode(companyInfoFormBean.getPostCode());
	        companyEntity.setAddress(companyInfoFormBean.getAddress());
	        companyEntity.setBasicContractDate(companyInfoFormBean.getBasicContractDate());
	        companyEntity.setPhoneNumber(companyInfoFormBean.getPhoneNumber());
	        companyEntity.setContactName(companyInfoFormBean.getContactName());
	        companyEntity.setMail(companyInfoFormBean.getMail());
	        companyEntity.setContractStatus(companyInfoFormBean.getContractStatus());
	        companyEntity.setLevel(companyInfoFormBean.getLevel());
	        companyEntity.setInsertDate(companyInfoFormBean.getInsertDate());
	        companyEntity.setUpdateDate(companyInfoFormBean.getUpdateDate());


	        companyInfoMapper.insertCompany(companyEntity);
	    }

	   @Override
	    public boolean updateCompany(CompanyInfoFormBean companyInfoFormBean) {
	        try {
	            int result = companyInfoMapper.updateCompanyInfo(companyInfoFormBean);
	            return result > 0;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

}