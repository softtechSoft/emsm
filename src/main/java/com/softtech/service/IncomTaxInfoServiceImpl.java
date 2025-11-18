package com.softtech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.EmployeeActionForm;
import com.softtech.actionForm.IncomeTaxInfoFormBean;
import com.softtech.common.IncomeTaxIDName;
import com.softtech.entity.Employee;
import com.softtech.entity.IncomeTaxInfoEntity;
import com.softtech.mappers.IncomeTaxInfoMapper;
import com.softtech.util.DataUtil;

/**
 * @program
 * @概要:
 * @作成者:孫曄
 * @作成日:2022-08-10
 * @return:
 */
@Service
public class IncomTaxInfoServiceImpl implements IncomTaxInfoService {

    //IOC Mapper
    @Autowired
    private IncomeTaxInfoMapper incomeTaxInfoMapper;

    /**
     * 概要:画面表示用のquery,入力の社員IDにより、検索する
     *
     * @param employeeID
     * @param:[year]
     * @return:java.util.List<com.softtech.entity.IncomeTaxInfoEntity>
     * @author:孫曄@SOFTTECH
     */
    @Override
    public List<IncomeTaxInfoEntity> getIncomeTaxByEmployeeID(String employeeID) {
        List<IncomeTaxInfoEntity> incomeTaxByEmployeeID = incomeTaxInfoMapper.getIncomeTaxByEmployeeID(employeeID);
        return incomeTaxByEmployeeID;
    }

    /**
     * 概要:更新画面への検索,表示される画面の所得税IDにより、検索する
     *
     * @param incomeTaxID
     * @param:[incomeTaxID]
     * @return:java.util.List<com.softtech.entity.IncomeTaxInfoEntity>
     * @author:孫曄@SOFTTECH
     * @date:2022/08/10
     */
    @Override
    public List<IncomeTaxInfoEntity> getUpdateIncomeTaxByIncomeTaxID(String incomeTaxID) {
        List<IncomeTaxInfoEntity> updateIncomeTaxByIncomeTaxID = incomeTaxInfoMapper.getUpdateIncomeTaxByIncomeTaxID(incomeTaxID);
        return updateIncomeTaxByIncomeTaxID;
    }

    /**
     * 概要:ID最大値+1を取得
     *
     * @param[]
     * @param:[incomeTaxID]
     * @return:java.lang.String
     * @author:孫曄@SOFTTECH
     * @date:2022/08/10
     */
    @Override
    public String getNextIncomeTaxID() {
        String maxIncomTaxID = incomeTaxInfoMapper.getMaxIncomeTaxID();
//        String nextIncomeTaxID = String.valueOf(Integer.parseInt(maxIncomTaxID) + 1);
        String nextIncomeTaxID = DataUtil.getNextID(maxIncomTaxID, 1);
        return nextIncomeTaxID;
    }

    /**
     * 概要:更新の時、更新画面の年度を表示する用
     *
     * @param[]
     * @param:[year]
     * @return:java.util.List<com.softtech.entity.IncomeTaxInfoEntity>
     * @author:孫曄@SOFTTECH
     * @date:2022/08/10
     */
    @Override
    public List<IncomeTaxIDName> getYear() {
        List<IncomeTaxIDName> incomeTaxIDNameList = new ArrayList<>();
        // DBから年度を取得
        incomeTaxIDNameList = incomeTaxInfoMapper.getYear();

        return incomeTaxIDNameList;

    }

    /**
     * 概要:DB Entityからui使用のformへ変更,更新画面の生成の時使用
     *
     * @param eList
     * @param:[eList]
     * @return:com.softtech.actionForm.IncomeTaxInfoFormBean
     * @author:孫曄@SOFTTECH
     * @date:2022/08/12
     */
    @Override
    public IncomeTaxInfoFormBean transforEntityToUI(List<IncomeTaxInfoEntity> eList) {
        IncomeTaxInfoFormBean incomeTaxInfoFormBean = new IncomeTaxInfoFormBean();
        for (IncomeTaxInfoEntity incomeTaxInfoEntity : eList) {
            incomeTaxInfoFormBean.setIncomeTaxID(incomeTaxInfoEntity.getIncomeTaxID());
            incomeTaxInfoFormBean.setEmployeeName(incomeTaxInfoEntity.getEmployeeName());
            incomeTaxInfoFormBean.setYear(incomeTaxInfoEntity.getYear());
            incomeTaxInfoFormBean.setEmployeeID(incomeTaxInfoEntity.getEmployeeID());
            incomeTaxInfoFormBean.setResidentTax1(incomeTaxInfoEntity.getResidentTax1());
            incomeTaxInfoFormBean.setResidentTax2(incomeTaxInfoEntity.getResidentTax2());
            incomeTaxInfoFormBean.setResidentTax3(incomeTaxInfoEntity.getResidentTax3());
            incomeTaxInfoFormBean.setResidentTax4(incomeTaxInfoEntity.getResidentTax4());
            incomeTaxInfoFormBean.setResidentTax5(incomeTaxInfoEntity.getResidentTax5());
            incomeTaxInfoFormBean.setResidentTax6(incomeTaxInfoEntity.getResidentTax6());
            incomeTaxInfoFormBean.setResidentTax7(incomeTaxInfoEntity.getResidentTax7());
            incomeTaxInfoFormBean.setResidentTax8(incomeTaxInfoEntity.getResidentTax8());
            incomeTaxInfoFormBean.setResidentTax9(incomeTaxInfoEntity.getResidentTax9());
            incomeTaxInfoFormBean.setResidentTax10(incomeTaxInfoEntity.getResidentTax10());
            incomeTaxInfoFormBean.setResidentTax11(incomeTaxInfoEntity.getResidentTax11());
            incomeTaxInfoFormBean.setResidentTax12(incomeTaxInfoEntity.getResidentTax12());
            incomeTaxInfoFormBean.setRental01(incomeTaxInfoEntity.getRental01());
            incomeTaxInfoFormBean.setRental02(incomeTaxInfoEntity.getRental02());
            incomeTaxInfoFormBean.setRental03(incomeTaxInfoEntity.getRental03());
            incomeTaxInfoFormBean.setRental04(incomeTaxInfoEntity.getRental04());
            incomeTaxInfoFormBean.setRental05(incomeTaxInfoEntity.getRental05());
            incomeTaxInfoFormBean.setRental06(incomeTaxInfoEntity.getRental06());
            incomeTaxInfoFormBean.setRental07(incomeTaxInfoEntity.getRental07());
            incomeTaxInfoFormBean.setRental08(incomeTaxInfoEntity.getRental08());
            incomeTaxInfoFormBean.setRental09(incomeTaxInfoEntity.getRental09());
            incomeTaxInfoFormBean.setRental10(incomeTaxInfoEntity.getRental10());
            incomeTaxInfoFormBean.setRental11(incomeTaxInfoEntity.getRental11());
            incomeTaxInfoFormBean.setRental12(incomeTaxInfoEntity.getRental12());
            incomeTaxInfoFormBean.setRentalMgmtFee01(incomeTaxInfoEntity.getRentalMgmtFee01());
            incomeTaxInfoFormBean.setRentalMgmtFee02(incomeTaxInfoEntity.getRentalMgmtFee02());
            incomeTaxInfoFormBean.setRentalMgmtFee03(incomeTaxInfoEntity.getRentalMgmtFee03());
            incomeTaxInfoFormBean.setRentalMgmtFee04(incomeTaxInfoEntity.getRentalMgmtFee04());
            incomeTaxInfoFormBean.setRentalMgmtFee05(incomeTaxInfoEntity.getRentalMgmtFee05());
            incomeTaxInfoFormBean.setRentalMgmtFee06(incomeTaxInfoEntity.getRentalMgmtFee06());
            incomeTaxInfoFormBean.setRentalMgmtFee07(incomeTaxInfoEntity.getRentalMgmtFee07());
            incomeTaxInfoFormBean.setRentalMgmtFee08(incomeTaxInfoEntity.getRentalMgmtFee08());
            incomeTaxInfoFormBean.setRentalMgmtFee09(incomeTaxInfoEntity.getRentalMgmtFee09());
            incomeTaxInfoFormBean.setRentalMgmtFee10(incomeTaxInfoEntity.getRentalMgmtFee10());
            incomeTaxInfoFormBean.setRentalMgmtFee11(incomeTaxInfoEntity.getRentalMgmtFee11());
            incomeTaxInfoFormBean.setRentalMgmtFee12(incomeTaxInfoEntity.getRentalMgmtFee12());
            incomeTaxInfoFormBean.setStatus(incomeTaxInfoEntity.getStatus());
            incomeTaxInfoFormBean.setUpdateDate(incomeTaxInfoEntity.getUpdateDate());
            incomeTaxInfoFormBean.setInsertDate(incomeTaxInfoEntity.getInsertDate());
        }
        return incomeTaxInfoFormBean;
    }


    /**
     * 概要:更新用BeanToEntity
     *
     * @param:[incomeTaxInfoFormBean]
     * @return:com.softtech.entity.IncomeTaxInfoEntity
     * @author:孫曄@SOFTTECH
     * @date:2022/08/12
     */
    public IncomeTaxInfoEntity transforUItoEntityByUpDate(IncomeTaxInfoFormBean incomeTaxInfoFormBean) {
        IncomeTaxInfoEntity incomeTaxInfoEntity = new IncomeTaxInfoEntity();
        incomeTaxInfoEntity.setIncomeTaxID(incomeTaxInfoFormBean.getIncomeTaxID());
        incomeTaxInfoEntity.setYear(incomeTaxInfoFormBean.getYear());
        incomeTaxInfoEntity.setEmployeeID(incomeTaxInfoFormBean.getEmployeeID());
        incomeTaxInfoEntity.setResidentTax1(incomeTaxInfoFormBean.getResidentTax1());
        incomeTaxInfoEntity.setResidentTax2(incomeTaxInfoFormBean.getResidentTax2());
        incomeTaxInfoEntity.setResidentTax3(incomeTaxInfoFormBean.getResidentTax3());
        incomeTaxInfoEntity.setResidentTax4(incomeTaxInfoFormBean.getResidentTax4());
        incomeTaxInfoEntity.setResidentTax5(incomeTaxInfoFormBean.getResidentTax5());
        incomeTaxInfoEntity.setResidentTax6(incomeTaxInfoFormBean.getResidentTax6());
        incomeTaxInfoEntity.setResidentTax7(incomeTaxInfoFormBean.getResidentTax7());
        incomeTaxInfoEntity.setResidentTax8(incomeTaxInfoFormBean.getResidentTax8());
        incomeTaxInfoEntity.setResidentTax9(incomeTaxInfoFormBean.getResidentTax9());
        incomeTaxInfoEntity.setResidentTax10(incomeTaxInfoFormBean.getResidentTax10());
        incomeTaxInfoEntity.setResidentTax11(incomeTaxInfoFormBean.getResidentTax11());
        incomeTaxInfoEntity.setResidentTax12(incomeTaxInfoFormBean.getResidentTax12());
        incomeTaxInfoEntity.setRental01(incomeTaxInfoFormBean.getRental01());
        incomeTaxInfoEntity.setRental02(incomeTaxInfoFormBean.getRental02());
        incomeTaxInfoEntity.setRental03(incomeTaxInfoFormBean.getRental03());
        incomeTaxInfoEntity.setRental04(incomeTaxInfoFormBean.getRental04());
        incomeTaxInfoEntity.setRental05(incomeTaxInfoFormBean.getRental05());
        incomeTaxInfoEntity.setRental06(incomeTaxInfoFormBean.getRental06());
        incomeTaxInfoEntity.setRental07(incomeTaxInfoFormBean.getRental07());
        incomeTaxInfoEntity.setRental08(incomeTaxInfoFormBean.getRental08());
        incomeTaxInfoEntity.setRental09(incomeTaxInfoFormBean.getRental09());
        incomeTaxInfoEntity.setRental10(incomeTaxInfoFormBean.getRental10());
        incomeTaxInfoEntity.setRental11(incomeTaxInfoFormBean.getRental11());
        incomeTaxInfoEntity.setRental12(incomeTaxInfoFormBean.getRental12());
        incomeTaxInfoEntity.setRentalMgmtFee01(incomeTaxInfoFormBean.getRentalMgmtFee01());
        incomeTaxInfoEntity.setRentalMgmtFee02(incomeTaxInfoFormBean.getRentalMgmtFee02());
        incomeTaxInfoEntity.setRentalMgmtFee03(incomeTaxInfoFormBean.getRentalMgmtFee03());
        incomeTaxInfoEntity.setRentalMgmtFee04(incomeTaxInfoFormBean.getRentalMgmtFee04());
        incomeTaxInfoEntity.setRentalMgmtFee05(incomeTaxInfoFormBean.getRentalMgmtFee05());
        incomeTaxInfoEntity.setRentalMgmtFee06(incomeTaxInfoFormBean.getRentalMgmtFee06());
        incomeTaxInfoEntity.setRentalMgmtFee07(incomeTaxInfoFormBean.getRentalMgmtFee07());
        incomeTaxInfoEntity.setRentalMgmtFee08(incomeTaxInfoFormBean.getRentalMgmtFee08());
        incomeTaxInfoEntity.setRentalMgmtFee09(incomeTaxInfoFormBean.getRentalMgmtFee09());
        incomeTaxInfoEntity.setRentalMgmtFee10(incomeTaxInfoFormBean.getRentalMgmtFee10());
        incomeTaxInfoEntity.setRentalMgmtFee11(incomeTaxInfoFormBean.getRentalMgmtFee11());
        incomeTaxInfoEntity.setRentalMgmtFee12(incomeTaxInfoFormBean.getRentalMgmtFee12());

        incomeTaxInfoEntity.setStatus(incomeTaxInfoFormBean.getStatus());
        incomeTaxInfoEntity.setUpdateDate(incomeTaxInfoFormBean.getUpdateDate());
        return incomeTaxInfoEntity;
    }

    /**
     * 概要:update
     *
     * @param incomeTaxInfoFormBean
     * @param:[incomeTaxInfoEntity]
     * @return:void
     * @author:孫曄@SOFTTECH
     * @date:2022/08/10
     */
    @Override
    public void updateIncomeTax(IncomeTaxInfoFormBean incomeTaxInfoFormBean) {
        IncomeTaxInfoEntity incomeTaxInfoEntity = transforUItoEntityByUpDate(incomeTaxInfoFormBean);
        incomeTaxInfoMapper.updateIncomeTax(incomeTaxInfoEntity);

    }

    public IncomeTaxInfoEntity transforUItoEntityByInsert(IncomeTaxInfoFormBean incomeTaxInfoFormBean) {
        IncomeTaxInfoEntity incomeTaxInfoEntity = new IncomeTaxInfoEntity();
        incomeTaxInfoEntity.setIncomeTaxID(incomeTaxInfoFormBean.getIncomeTaxID());
        incomeTaxInfoEntity.setYear(incomeTaxInfoFormBean.getYear());
        incomeTaxInfoEntity.setEmployeeID(incomeTaxInfoFormBean.getEmployeeID());
        incomeTaxInfoEntity.setResidentTax1(incomeTaxInfoFormBean.getResidentTax1());
        incomeTaxInfoEntity.setResidentTax2(incomeTaxInfoFormBean.getResidentTax2());
        incomeTaxInfoEntity.setResidentTax3(incomeTaxInfoFormBean.getResidentTax3());
        incomeTaxInfoEntity.setResidentTax4(incomeTaxInfoFormBean.getResidentTax4());
        incomeTaxInfoEntity.setResidentTax5(incomeTaxInfoFormBean.getResidentTax5());
        incomeTaxInfoEntity.setResidentTax6(incomeTaxInfoFormBean.getResidentTax6());
        incomeTaxInfoEntity.setResidentTax7(incomeTaxInfoFormBean.getResidentTax7());
        incomeTaxInfoEntity.setResidentTax8(incomeTaxInfoFormBean.getResidentTax8());
        incomeTaxInfoEntity.setResidentTax9(incomeTaxInfoFormBean.getResidentTax9());
        incomeTaxInfoEntity.setResidentTax10(incomeTaxInfoFormBean.getResidentTax10());
        incomeTaxInfoEntity.setResidentTax11(incomeTaxInfoFormBean.getResidentTax11());
        incomeTaxInfoEntity.setResidentTax12(incomeTaxInfoFormBean.getResidentTax12());
        incomeTaxInfoEntity.setRental01(incomeTaxInfoFormBean.getRental01());
        incomeTaxInfoEntity.setRental02(incomeTaxInfoFormBean.getRental02());
        incomeTaxInfoEntity.setRental03(incomeTaxInfoFormBean.getRental03());
        incomeTaxInfoEntity.setRental04(incomeTaxInfoFormBean.getRental04());
        incomeTaxInfoEntity.setRental05(incomeTaxInfoFormBean.getRental05());
        incomeTaxInfoEntity.setRental06(incomeTaxInfoFormBean.getRental06());
        incomeTaxInfoEntity.setRental07(incomeTaxInfoFormBean.getRental07());
        incomeTaxInfoEntity.setRental08(incomeTaxInfoFormBean.getRental08());
        incomeTaxInfoEntity.setRental09(incomeTaxInfoFormBean.getRental09());
        incomeTaxInfoEntity.setRental10(incomeTaxInfoFormBean.getRental10());
        incomeTaxInfoEntity.setRental11(incomeTaxInfoFormBean.getRental11());
        incomeTaxInfoEntity.setRental12(incomeTaxInfoFormBean.getRental12());
        incomeTaxInfoEntity.setRentalMgmtFee01(incomeTaxInfoFormBean.getRentalMgmtFee01());
        incomeTaxInfoEntity.setRentalMgmtFee02(incomeTaxInfoFormBean.getRentalMgmtFee02());
        incomeTaxInfoEntity.setRentalMgmtFee03(incomeTaxInfoFormBean.getRentalMgmtFee03());
        incomeTaxInfoEntity.setRentalMgmtFee04(incomeTaxInfoFormBean.getRentalMgmtFee04());
        incomeTaxInfoEntity.setRentalMgmtFee05(incomeTaxInfoFormBean.getRentalMgmtFee05());
        incomeTaxInfoEntity.setRentalMgmtFee06(incomeTaxInfoFormBean.getRentalMgmtFee06());
        incomeTaxInfoEntity.setRentalMgmtFee07(incomeTaxInfoFormBean.getRentalMgmtFee07());
        incomeTaxInfoEntity.setRentalMgmtFee08(incomeTaxInfoFormBean.getRentalMgmtFee08());
        incomeTaxInfoEntity.setRentalMgmtFee09(incomeTaxInfoFormBean.getRentalMgmtFee09());
        incomeTaxInfoEntity.setRentalMgmtFee10(incomeTaxInfoFormBean.getRentalMgmtFee10());
        incomeTaxInfoEntity.setRentalMgmtFee11(incomeTaxInfoFormBean.getRentalMgmtFee11());
        incomeTaxInfoEntity.setRentalMgmtFee12(incomeTaxInfoFormBean.getRentalMgmtFee12());

        incomeTaxInfoEntity.setStatus(incomeTaxInfoFormBean.getStatus());
        incomeTaxInfoEntity.setUpdateDate(incomeTaxInfoFormBean.getUpdateDate());
        incomeTaxInfoEntity.setInsertDate(incomeTaxInfoFormBean.getInsertDate());
        return incomeTaxInfoEntity;
    }

    /**
     * 概要:insert
     *
     * @param incomeTaxInfoFormBean
     * @param:[incomeTaxInfoEntity]
     * @return:void
     * @author:孫曄@SOFTTECH
     * @date:2022/08/10
     */
    @Override
    public void insertIncomeTaxInfo(IncomeTaxInfoFormBean incomeTaxInfoFormBean) {
        IncomeTaxInfoEntity incomeTaxInfoEntity = transforUItoEntityByInsert(incomeTaxInfoFormBean);
        incomeTaxInfoMapper.insertIncomeTaxInfo(incomeTaxInfoEntity);
    }

    /**
     * 概要:社員情報をactionformへ変換
     * @param:[employees]
     * @return:java.util.List<com.softtech.actionForm.EmployeeActionForm>
     * @author:孫曄@SOFTTECH
     * @date:2022/08/12
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
     * @author:孫曄@SOFTTECH
     * @date:2022/08/12
     */
    @Override
    public List<EmployeeActionForm> queryEmployeeInfo() {
        List<Employee> employee = incomeTaxInfoMapper.getEmployee();
        List<EmployeeActionForm> rtn = transferDBTOUI(employee);
        return rtn;
    }


    @Override
    public List<IncomeTaxInfoEntity> getIncomeTaxByCondition(String employeeID, String year) {
        return incomeTaxInfoMapper.getIncomeTaxByCondition(employeeID, year);
    }

    @Override
    public List<String> getDistinctYears() {
        return incomeTaxInfoMapper.getDistinctYears();
    }
}
