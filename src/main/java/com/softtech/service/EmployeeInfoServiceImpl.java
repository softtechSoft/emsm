package com.softtech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.EmployeeActionForm;
import com.softtech.actionForm.EmployeeInfoFormBean;
import com.softtech.entity.Employee;
import com.softtech.entity.EmployeeInfoEntity;
import com.softtech.mappers.EmployeeInfoMapper;



/**
 * @program
 * @概要:
 * @作成者:スッ
 * @作成日:2023-10-20
 * @return:
 */
@Service
public class EmployeeInfoServiceImpl implements EmployeeInfoService {
	//IOC Mapper
    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;

    /**
     * 概要:画面表示用のquery,入力の社員IDにより、検索する
     *
     * @param employeeID
     * @return:java.util.List<com.softtech.entity.IncomeTaxInfoEntity>
     * @author:スッ
     */
    public List<EmployeeInfoEntity> getEmployeeID(String employeeID) {
        List<EmployeeInfoEntity> employeeByEmployeeID = employeeInfoMapper.getEmployeeID(employeeID);
        return employeeByEmployeeID;
    }

    public List<EmployeeInfoEntity> getEmployeeIDAll(String employeeID) {
        List<EmployeeInfoEntity> employeeByEmployeeID = employeeInfoMapper.getEmployeeIDAll(employeeID);
        return employeeByEmployeeID;
    }

    /**
     * 概要:更新画面への検索,表示される画面の所得税IDにより、検索する
     *
     * @param employeeID
     * @param:[employeeID]
     * @return:java.util.List<com.softtech.entity.EmployeeInfoEntity>
     * @author:スッ
     * @date:2023/10/20
     */
    @Override
    public List<EmployeeInfoEntity> getUpdateEmployeeByEmployeeID(String EmployeeID) {
        List<EmployeeInfoEntity> updateEmployeeByEmployeeID = employeeInfoMapper. getUpdateEmployeeByEmployeeID(EmployeeID);
        return updateEmployeeByEmployeeID;
    }

    /**
     * 概要:ID最大値+1を取得
     *
     * @param[]
     * @param:[employeeID]
     * @return:java.lang.String
     * @author:スッ
     * @date:2023/10/20
     */
    @Override
    public String getNextEmployeeID() {
        String maxEmployeeID = employeeInfoMapper.getMaxEmployeeID();
        String nextEmployeeID = String.valueOf(Integer.parseInt(getNextEmployeeID()) + 1);
        return nextEmployeeID;
    }

    /**
     * 概要:DB Entityからui使用のformへ変更,更新画面の生成の時使用
     *
     * @param eList
     * @param:[eList]
     * @return:com.softtech.actionForm.EmployeeInfoFormBean
     * @author:スッ
     * @date:2023/10/20
     */

	public EmployeeInfoFormBean transforEntityToUI(List<EmployeeInfoEntity> eList) {
        EmployeeInfoFormBean employeeInfoFormBean = new EmployeeInfoFormBean();
        for (EmployeeInfoEntity employeeInfoEntity : eList) {
            employeeInfoFormBean.setEmployeeID(employeeInfoEntity.getEmployeeID());
            employeeInfoFormBean.setEmployeeName(employeeInfoEntity.getEmployeeName());
            employeeInfoFormBean.setPassword(employeeInfoEntity.getPassword());
            employeeInfoFormBean.setStatus(employeeInfoEntity.getStatus());
            employeeInfoFormBean.setSex(employeeInfoEntity.getSex());
            employeeInfoFormBean.setEpType(employeeInfoEntity.getEpType());
            employeeInfoFormBean.setBirthday(employeeInfoEntity.getBirthday());
            employeeInfoFormBean.setAge(employeeInfoEntity.getAge());
            employeeInfoFormBean.setJoinedDate(employeeInfoEntity.getJoinedDate());
            employeeInfoFormBean.setJoinedTime(employeeInfoEntity.getJoinedTime());
            employeeInfoFormBean.setPostCode(employeeInfoEntity.getPostCode());
            employeeInfoFormBean.setAddress(employeeInfoEntity.getAddress());
            employeeInfoFormBean.setPhoneNumber(employeeInfoEntity.getPhoneNumber());
            employeeInfoFormBean.setAuthority(employeeInfoEntity.getAuthority());
            employeeInfoFormBean.setMailAdress(employeeInfoEntity.getMailAdress());
            employeeInfoFormBean.setInsertDate(employeeInfoEntity.getInsertDate());
            employeeInfoFormBean.setUpdateDate(employeeInfoEntity.getUpdateDate());

        }
        return employeeInfoFormBean;
    }

    /**
     * 概要:更新用BeanToEntity
     *
     * @param:[employeeInfoFormBean]
     * @return:com.softtech.entity.IncomeTaxInfoEntity
     * @author:スッ
     * @date:2023/10/20
     */

    public EmployeeInfoEntity transforBeanToEntityByUpDate(EmployeeInfoFormBean employeeInfoFormBean) {
    	 EmployeeInfoEntity employeeInfoEntity = new EmployeeInfoEntity();
    	 employeeInfoEntity.setEmployeeID(employeeInfoFormBean.getEmployeeID());
         employeeInfoEntity.setEmployeeName(employeeInfoFormBean.getEmployeeName());
         employeeInfoEntity.setPassword(employeeInfoFormBean.getPassword());
         employeeInfoEntity.setStatus(employeeInfoFormBean.getStatus());
         employeeInfoEntity.setSex(employeeInfoFormBean.getSex());
         employeeInfoEntity.setEpType(employeeInfoFormBean.getEpType());
         employeeInfoEntity.setBirthday(employeeInfoFormBean.getBirthday());
         employeeInfoEntity.setAge(employeeInfoFormBean.getAge());
         employeeInfoEntity.setJoinedDate(employeeInfoFormBean.getJoinedDate());
         employeeInfoEntity.setJoinedTime(employeeInfoFormBean.getJoinedTime());
         employeeInfoEntity.setPostCode(employeeInfoFormBean.getPostCode());
         employeeInfoEntity.setAddress(employeeInfoFormBean.getAddress());
         employeeInfoEntity.setPhoneNumber(employeeInfoFormBean.getPhoneNumber());
         employeeInfoEntity.setAuthority(employeeInfoFormBean.getAuthority());
         employeeInfoEntity.setMailAdress(employeeInfoFormBean.getMailAdress());
         employeeInfoEntity.setInsertDate(employeeInfoFormBean.getInsertDate());
         employeeInfoEntity.setUpdateDate(employeeInfoFormBean.getUpdateDate());
         return employeeInfoEntity;

    }
    /**
     * 概要:update
     *
     * @param employeeInfoFormBean
     * @param:[employeeInfoEntity]
     * @return:void
     * @author:スッ
     * @date:2023/10/20
     */
    public void updateEmployeeInfo(EmployeeInfoFormBean employeeInfoFormBean) {
        EmployeeInfoEntity employeeInfoEntity = transforBeanToEntityByUpDate(employeeInfoFormBean);
        employeeInfoMapper.updateEmployeeInfo(employeeInfoEntity);

    }

    public EmployeeInfoEntity transforBeanToEntityByInsert(EmployeeInfoFormBean employeeInfoFormBean) {
    	 EmployeeInfoEntity employeeInfoEntity = new EmployeeInfoEntity();
    	 employeeInfoEntity.setEmployeeID(employeeInfoFormBean.getEmployeeID());
    	 employeeInfoEntity.setEmployeeName(employeeInfoFormBean.getEmployeeName());
         employeeInfoEntity.setPassword(employeeInfoFormBean.getPassword());
         employeeInfoEntity.setStatus(employeeInfoFormBean.getStatus());
         employeeInfoEntity.setSex(employeeInfoFormBean.getSex());
         employeeInfoEntity.setEpType(employeeInfoFormBean.getEpType());
         employeeInfoEntity.setBirthday(employeeInfoFormBean.getBirthday());
         employeeInfoEntity.setAge(employeeInfoFormBean.getAge());
         employeeInfoEntity.setJoinedDate(employeeInfoFormBean.getJoinedDate());
         employeeInfoEntity.setJoinedTime(employeeInfoFormBean.getJoinedTime());
         employeeInfoEntity.setPostCode(employeeInfoFormBean.getPostCode());
         employeeInfoEntity.setAddress(employeeInfoFormBean.getAddress());
         employeeInfoEntity.setPhoneNumber(employeeInfoFormBean.getPhoneNumber());
         employeeInfoEntity.setAuthority(employeeInfoFormBean.getAuthority());
         employeeInfoEntity.setMailAdress(employeeInfoFormBean.getMailAdress());
         employeeInfoEntity.setInsertDate(employeeInfoFormBean.getInsertDate());
         employeeInfoEntity.setUpdateDate(employeeInfoFormBean.getUpdateDate());
		 return employeeInfoEntity;

    }

    /**
     * 概要:insert
     *
     * @param employeeInfoFormBean
     * @param:[employeeInfoEntity]
     * @return:void
     * @author:スッ
     * @date:2023/10/20
     */

    @Override
	public void insertEmployeeInfo(EmployeeInfoFormBean employeeInfoFormBean) {
        EmployeeInfoEntity employeeInfoEntity = transforBeanToEntityByInsert(employeeInfoFormBean);
        employeeInfoMapper.insertEmployeeInfo(employeeInfoEntity);
    }

    /**
     * 概要:社員情報をactionformへ変換
     * @param:[employees]
     * @return:java.util.List<com.softtech.actionForm.EmployeeActionForm>
     * @author:スッ
     * @date:2023/10/20
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
     * @author:スッ
     * @date:2023/10/20
     */
    @Override
    public List<EmployeeActionForm> queryEmployeeInfo() {
        List<Employee> employee = employeeInfoMapper.getEmployee();
        List<EmployeeActionForm> rtn = transferDBTOUI(employee);
        return rtn;
    }


}