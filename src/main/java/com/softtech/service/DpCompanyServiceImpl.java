package com.softtech.service;

import com.softtech.entity.DpCompany;
import com.softtech.mappers.DpCompanyMapper;
import com.softtech.actionForm.DpCompanyFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DpCompanyServiceImpl implements DpCompanyService {

    @Autowired
    private DpCompanyMapper dpCompanyMapper;

    @Override
    public void insertCompany(DpCompany company) {
        dpCompanyMapper.insertCompany(company);
    }

    @Override
    public void updateCompany(DpCompany company) {
        dpCompanyMapper.updateCompany(company);
    }

    @Override
    public List<DpCompany> getAllCompanies() {
        return dpCompanyMapper.getAllCompanies();
    }

    @Override
    public DpCompany getCompanyById(String companyID) {
        return dpCompanyMapper.getCompanyById(companyID);
    }

    // FormBean转Entity
    private DpCompany formBeanToEntity(DpCompanyFormBean formBean) {
        DpCompany entity = new DpCompany();
        entity.setCompanyID(formBean.getCompanyID());
        entity.setCompanyName(formBean.getCompanyName());
        entity.setCompanyType(formBean.getCompanyType());
        entity.setPostCode(formBean.getPostCode());
        entity.setAddress(formBean.getAddress());
        entity.setPhoneNumber(formBean.getPhoneNumber());
        entity.setContactName(formBean.getContactName());
        entity.setContractStatus(formBean.getContractStatus());
        entity.setBasicContractDate(formBean.getBasicContractDate());
        entity.setMail(formBean.getMail());
        entity.setLevel(formBean.getLevel());
        // entity.setInsertDate(formBean.getInsertDate());
        // entity.setUpdateDate(formBean.getUpdateDate());
        return entity;
    }

    // 新增：支持FormBean参数
    public void insertCompany(DpCompanyFormBean formBean) {
        DpCompany entity = formBeanToEntity(formBean);
        dpCompanyMapper.insertCompany(entity);
    }

    // 更新：支持FormBean参数
    public void updateCompany(DpCompanyFormBean formBean) {
        DpCompany entity = formBeanToEntity(formBean);
        dpCompanyMapper.updateCompany(entity);
    }

    @Override
    public void deleteCompany(String companyId) {
        dpCompanyMapper.deleteCompany(companyId);
    }
}