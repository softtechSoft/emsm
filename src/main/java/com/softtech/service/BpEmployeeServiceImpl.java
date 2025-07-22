package com.softtech.service;

import com.softtech.entity.BpEmployee;
import com.softtech.mappers.BpEmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BpEmployeeServiceImpl implements BpEmployeeService {
    
    @Autowired
    private BpEmployeeMapper bpEmployeeMapper;

    @Override
    public List<BpEmployee> getAllEmployees() {
        return bpEmployeeMapper.getAllEmployees();
    }

    @Override
    public BpEmployee getEmployeeById(String employeeId) {
        return bpEmployeeMapper.getEmployeeById(employeeId);
    }

    @Override
    public void insertEmployee(BpEmployee employee) {
        bpEmployeeMapper.insertEmployee(employee);
    }

    @Override
    public void updateEmployee(BpEmployee employee) {
        bpEmployeeMapper.updateEmployee(employee);
    }

    @Override
    public void deleteEmployee(String employeeId) {
        bpEmployeeMapper.deleteEmployee(employeeId);
    }
} 