package com.softtech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.EmployeeActionForm;
import com.softtech.actionForm.HoldingTaxInfoFormBean;
import com.softtech.entity.Employee;
import com.softtech.entity.HoldingTaxInfoEntity;
import com.softtech.mappers.HoldingTaxInfoMapper;
import com.softtech.util.DataUtil;

/**
 * @program
 * @概要:
 * @作成者:黄
 * @作成日:2025-11-05
 * @return:
 */
@Service
public class HoldingTaxInfoServiceImpl implements HoldingTaxInfoService {

    //IOC Mapper
    @Autowired
    private HoldingTaxInfoMapper HoldingTaxInfoMapper;

    /**
     * 概要:画面表示用のquery,入力の社員IDにより、検索する
     *
     * @param employeeID
     * @param:[year]
     * @return:java.util.List<com.softtech.entity.HoldingTaxInfoEntity>
     * @author:黄
     */
    @Override
    public List<HoldingTaxInfoEntity> getHoldingTaxByEmployeeID(String employeeID) {
        List<HoldingTaxInfoEntity> HoldingTaxByEmployeeID = HoldingTaxInfoMapper.getHoldingTaxByEmployeeID(employeeID);
        return HoldingTaxByEmployeeID;
    }

    /**
     * 概要:更新画面への検索,表示される画面の所得税IDにより、検索する
     *
     * @param HoldingTaxID
     * @param:[HoldingTaxID]
     * @return:java.util.List<com.softtech.entity.HoldingTaxInfoEntity>
     * @author:黄
     * @date:2025-11-05
     */
    @Override
    public List<HoldingTaxInfoEntity> getUpdateHoldingTaxByHoldingTaxID(String HoldingTaxID) {
        List<HoldingTaxInfoEntity> updateHoldingTaxByHoldingTaxID = HoldingTaxInfoMapper.getUpdateHoldingTaxByHoldingTaxID(HoldingTaxID);
        return updateHoldingTaxByHoldingTaxID;
    }

    /**
     * 概要:ID最大値+1を取得
     *
     * @param[]
     * @param:[HoldingTaxID]
     * @return:java.lang.String
     * @author:黄
     * @date:2025-11-05
     */
    @Override
    public String getNextHoldingTaxID() {
        String maxIncomTaxID = HoldingTaxInfoMapper.getMaxHoldingTaxID();
        String nextHoldingTaxID = DataUtil.getNextID(maxIncomTaxID, 1);
        return nextHoldingTaxID;
    }

    /**
     * 概要:DB Entityからui使用のformへ変更,更新画面の生成の時使用
     *
     * @param eList
     * @param:[eList]
     * @return:com.softtech.actionForm.HoldingTaxInfoFormBean
     * @author:黄
     * @date:2025-11-05
     */
    @Override
    public HoldingTaxInfoFormBean transforEntityToUI(List<HoldingTaxInfoEntity> eList) {
        HoldingTaxInfoFormBean HoldingTaxInfoFormBean = new HoldingTaxInfoFormBean();
        for (HoldingTaxInfoEntity HoldingTaxInfoEntity : eList) {
            HoldingTaxInfoFormBean.setHoldingTaxID(HoldingTaxInfoEntity.getHoldingTaxID());
            HoldingTaxInfoFormBean.setEmployeeName(HoldingTaxInfoEntity.getEmployeeName());
            HoldingTaxInfoFormBean.setYear(HoldingTaxInfoEntity.getYear());
            HoldingTaxInfoFormBean.setEmployeeID(HoldingTaxInfoEntity.getEmployeeID());
            HoldingTaxInfoFormBean.setIncomeTax1(HoldingTaxInfoEntity.getIncomeTax1());
            HoldingTaxInfoFormBean.setIncomeTax2(HoldingTaxInfoEntity.getIncomeTax2());
            HoldingTaxInfoFormBean.setIncomeTax3(HoldingTaxInfoEntity.getIncomeTax3());
            HoldingTaxInfoFormBean.setIncomeTax4(HoldingTaxInfoEntity.getIncomeTax4());
            HoldingTaxInfoFormBean.setIncomeTax5(HoldingTaxInfoEntity.getIncomeTax5());
            HoldingTaxInfoFormBean.setIncomeTax6(HoldingTaxInfoEntity.getIncomeTax6());
            HoldingTaxInfoFormBean.setIncomeTax7(HoldingTaxInfoEntity.getIncomeTax7());
            HoldingTaxInfoFormBean.setIncomeTax8(HoldingTaxInfoEntity.getIncomeTax8());
            HoldingTaxInfoFormBean.setIncomeTax9(HoldingTaxInfoEntity.getIncomeTax9());
            HoldingTaxInfoFormBean.setIncomeTax10(HoldingTaxInfoEntity.getIncomeTax10());
            HoldingTaxInfoFormBean.setIncomeTax11(HoldingTaxInfoEntity.getIncomeTax11());
            HoldingTaxInfoFormBean.setIncomeTax12(HoldingTaxInfoEntity.getIncomeTax12());

            HoldingTaxInfoFormBean.setStatus(HoldingTaxInfoEntity.getStatus());
            HoldingTaxInfoFormBean.setUpdateDate(HoldingTaxInfoEntity.getUpdateDate());
            HoldingTaxInfoFormBean.setInsertDate(HoldingTaxInfoEntity.getInsertDate());
        }
        return HoldingTaxInfoFormBean;
    }

    /**
     * 概要:update
     *
     * @param HoldingTaxInfoFormBean
     * @param:[HoldingTaxInfoEntity]
     * @return:void
     * @author:黄
     * @date:2025-11-05
     */
    @Override
    public void updateHoldingTax(HoldingTaxInfoFormBean HoldingTaxInfoFormBean) {
        HoldingTaxInfoEntity HoldingTaxInfoEntity = transforUItoEntity(HoldingTaxInfoFormBean);
        HoldingTaxInfoMapper.updateHoldingTax(HoldingTaxInfoEntity);

    }

    public HoldingTaxInfoEntity transforUItoEntity(HoldingTaxInfoFormBean HoldingTaxInfoFormBean) {
        HoldingTaxInfoEntity HoldingTaxInfoEntity = new HoldingTaxInfoEntity();
        HoldingTaxInfoEntity.setHoldingTaxID(HoldingTaxInfoFormBean.getHoldingTaxID());
        HoldingTaxInfoEntity.setYear(HoldingTaxInfoFormBean.getYear());
        HoldingTaxInfoEntity.setEmployeeID(HoldingTaxInfoFormBean.getEmployeeID());
        HoldingTaxInfoEntity.setIncomeTax1(HoldingTaxInfoFormBean.getIncomeTax1());
        HoldingTaxInfoEntity.setIncomeTax2(HoldingTaxInfoFormBean.getIncomeTax2());
        HoldingTaxInfoEntity.setIncomeTax3(HoldingTaxInfoFormBean.getIncomeTax3());
        HoldingTaxInfoEntity.setIncomeTax4(HoldingTaxInfoFormBean.getIncomeTax4());
        HoldingTaxInfoEntity.setIncomeTax5(HoldingTaxInfoFormBean.getIncomeTax5());
        HoldingTaxInfoEntity.setIncomeTax6(HoldingTaxInfoFormBean.getIncomeTax6());
        HoldingTaxInfoEntity.setIncomeTax7(HoldingTaxInfoFormBean.getIncomeTax7());
        HoldingTaxInfoEntity.setIncomeTax8(HoldingTaxInfoFormBean.getIncomeTax8());
        HoldingTaxInfoEntity.setIncomeTax9(HoldingTaxInfoFormBean.getIncomeTax9());
        HoldingTaxInfoEntity.setIncomeTax10(HoldingTaxInfoFormBean.getIncomeTax10());
        HoldingTaxInfoEntity.setIncomeTax11(HoldingTaxInfoFormBean.getIncomeTax11());
        HoldingTaxInfoEntity.setIncomeTax12(HoldingTaxInfoFormBean.getIncomeTax12());

        HoldingTaxInfoEntity.setStatus(HoldingTaxInfoFormBean.getStatus());
        HoldingTaxInfoEntity.setUpdateDate(HoldingTaxInfoFormBean.getUpdateDate());
        HoldingTaxInfoEntity.setInsertDate(HoldingTaxInfoFormBean.getInsertDate());
        return HoldingTaxInfoEntity;
    }

    /**
     * 概要:insert
     *
     * @param HoldingTaxInfoFormBean
     * @param:[HoldingTaxInfoEntity]
     * @return:void
     * @author:黄
     * @date:2025-11-05
     */
    @Override
    public void insertHoldingTaxInfo(HoldingTaxInfoFormBean HoldingTaxInfoFormBean) {
        HoldingTaxInfoEntity HoldingTaxInfoEntity = transforUItoEntity(HoldingTaxInfoFormBean);
        HoldingTaxInfoMapper.insertHoldingTaxInfo(HoldingTaxInfoEntity);
    }

    /**
     * 概要:社員情報をactionformへ変換
     * @param:[employees]
     * @return:java.util.List<com.softtech.actionForm.EmployeeActionForm>
     * @author:黄
     * @date:2025-11-05
     */
    public List<EmployeeActionForm> transferDBTOUI(List<Employee> employees) {

        List<EmployeeActionForm> employeeAactionForms = new ArrayList();
        for (Employee employee : employees) {
            EmployeeActionForm employeeAactionForm = new EmployeeActionForm();
            employeeAactionForm.setEmployeeID(employee.getEmployeeID());
            employeeAactionForm.setEmployeeName(employee.getEmployeeName());
            employeeAactionForms.add(employeeAactionForm);
        }
        return employeeAactionForms;
    }

    /**
     * 概要:DBから社員情報を取得する,画面の社員ID選択
     *
     * @param:[]
     * @return:java.util.List<com.softtech.actionForm.EmployeeActionForm>
     * @author:黄
     * @date:2025-11-05
     */
    @Override
    public List<EmployeeActionForm> queryEmployeeInfo() {
        List<Employee> employee = HoldingTaxInfoMapper.getEmployee();
        List<EmployeeActionForm> rtn = transferDBTOUI(employee);
        return rtn;
    }


    @Override
    public List<HoldingTaxInfoEntity> getHoldingTaxByCondition(String employeeID, String year) {
        return HoldingTaxInfoMapper.getHoldingTaxByCondition(employeeID, year);
    }

    @Override
    public List<String> getDistinctYears() {
        return HoldingTaxInfoMapper.getDistinctYears();
    }
}
