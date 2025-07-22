package com.softtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.entity.BpCompany;
import com.softtech.mappers.BpCompanyMapper;

@Service
public class BpCompanyServiceImpl implements BpCompanyService {
    
    @Autowired
    private BpCompanyMapper bpCompanyMapper;

    @Override
    public List<BpCompany> getAllCompanies() {
        return bpCompanyMapper.getAllCompanies();
    }

    @Override
    public BpCompany getCompanyById(String companyId) {
        return bpCompanyMapper.getCompanyById(companyId);
    }

    @Override
    public void insertCompany(BpCompany company) {
        bpCompanyMapper.insertCompany(company);
    }

    @Override
    public void updateCompany(BpCompany company) {
        bpCompanyMapper.updateCompany(company);
    }

    @Override
    public void deleteCompany(String companyId) {
        bpCompanyMapper.deleteCompany(companyId);
    }
    
    
} 