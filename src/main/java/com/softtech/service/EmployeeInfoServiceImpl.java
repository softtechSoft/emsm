package com.softtech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.EmployeeActionForm;
import com.softtech.actionForm.EmployeeInfoBean;
import com.softtech.actionForm.EmployeeInfoFormBean;
import com.softtech.entity.Employee;
import com.softtech.entity.EmployeeInfoEntity;
import com.softtech.mappers.EmployeeInfoMapper;
import com.softtech.util.DataUtil;

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
     * 概要:社員IDより、検索する
     * @param employeeID
     * @return:社員リスト
     * @author:スッ
     */
    public List<EmployeeInfoEntity> getEmployeeID(String employeeID) {
        List<EmployeeInfoEntity> employeeInfoEntity = changeSex( employeeInfoMapper.getEmployeeID(employeeID));
        return employeeInfoEntity;
    }
    /**
     * 概要:社員全量検索する
     * @param
     * @return:社員リスト
     * @author:スッ
     */
    public List<EmployeeInfoEntity> getEmployeeAll() {
        List<EmployeeInfoEntity> employeeInfoEntity = changeSex(employeeInfoMapper.getEmployeeAll());
        return employeeInfoEntity;
    }
    /**
     * 概要:性別変換
     * @param 社員リスト
     * @return:社員リスト
     * @author:スッ
     */
    public List<EmployeeInfoEntity> changeSex(List<EmployeeInfoEntity> list) {
        for (EmployeeInfoEntity employeeInfoEntity : list) {
            String sex = employeeInfoEntity.getSex();
            if (sex != null && !sex.isEmpty()) {
                if ("0".equals(sex)) {
                    employeeInfoEntity.setSex("男");
                } else {
                    employeeInfoEntity.setSex("女");
                }
            }
        }
        return list;
    }
    /**
     * 概要:更新画面への検索,表示される画面の社員IDにより、検索する
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
     * 概要:DB Entityからui使用のformへ変更,更新画面の生成の時使用
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
            employeeInfoFormBean.setMailAdress(employeeInfoEntity.getMailAdress());
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

            employeeInfoFormBean.setInsertDate(employeeInfoEntity.getInsertDate());
            employeeInfoFormBean.setUpdateDate(employeeInfoEntity.getUpdateDate());
        }
        return employeeInfoFormBean;
    }
    /**
     * 概要:更新用BeanToEntity
     * @param:[employeeInfoFormBean]
     * @return:com.softtech.entity.IncomeTaxInfoEntity
     * @author:スッ
     * @date:2023/10/20
     */
	public EmployeeInfoEntity transforBeanToEntityByInsert(EmployeeInfoBean employeeInfoFormBean) {
   	 EmployeeInfoEntity employeeInfoEntity = new EmployeeInfoEntity();
   	 employeeInfoEntity.setEmployeeID(employeeInfoFormBean.getEmployeeID());
   	 employeeInfoEntity.setEmployeeName(employeeInfoFormBean.getEmployeeName());
   	 employeeInfoEntity.setStatus(employeeInfoFormBean.getStatus());
     employeeInfoEntity.setPassword(employeeInfoFormBean.getPassword());
     employeeInfoEntity.setMailAdress(employeeInfoFormBean.getMailAdress());
     employeeInfoEntity.setAuthority(employeeInfoFormBean.getAuthority());
		 return employeeInfoEntity;
   }
	public void save(EmployeeInfoBean employeeInfoFormBean) {
   	//画面からデータをEntityに伝送
       EmployeeInfoEntity employeeInfoEntity = transforBeanToEntityByInsert(employeeInfoFormBean);
     //DBに登録
       employeeInfoMapper.save(employeeInfoEntity);
   }
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
         employeeInfoEntity.setPersonNumber(employeeInfoFormBean.getPersonNumber());
         employeeInfoEntity.setPhoneNumber(employeeInfoFormBean.getPhoneNumber());
         employeeInfoEntity.setAuthority(employeeInfoFormBean.getAuthority());
         employeeInfoEntity.setMailAdress(employeeInfoFormBean.getMailAdress());
         employeeInfoEntity.setInsertDate(employeeInfoFormBean.getInsertDate());
         employeeInfoEntity.setUpdateDate(employeeInfoFormBean.getUpdateDate());
         return employeeInfoEntity;
    }
	 public void update(EmployeeInfoFormBean employeeInfoFormBean) {
	    	//画面データをEntityに設定する
	        EmployeeInfoEntity employeeInfoEntity = transforBeanToEntityByUpDate(employeeInfoFormBean);
	     // DB登録
	        employeeInfoMapper.update(employeeInfoEntity);
	    }
    /**
     * 概要:update
     * @param employeeInfoFormBean
     * @return:
     * @author:スッ
     * @date:2023/10/20
     */

    /**
     * 概要:社員ID最大値+1を取得
     * @param:[employeeID]
     * @return:java.lang.String
     * @author:スッ
     * @date:2023/10/20
     */
    @Override
    public String getNextEmployeeID() {
        String maxEmployeeID = employeeInfoMapper.getMaxEmployeeID();
        String nextEmployeeID = DataUtil.getNextID(maxEmployeeID, 1);
        return nextEmployeeID;
    }
    /**
     * 概要:新規BeanToEntityByInsert
     * @param employeeInfoFormBean
     * @return:employeeInfoEntity
     * @author:スッ
     * @date:2023/10/20
     */


    /**
     * 概要:社員情報をactionformへ変換
     * @param:[employees]
     * @return:java.util.List<com.softtech.actionForm.EmployeeActionForm>
     * @author:スッ
     * @date:2023/10/20
     */
    public List<EmployeeActionForm> transferDBTOUI(List<Employee> employees) {

        List<EmployeeActionForm> employeeAactionForms = new ArrayList<EmployeeActionForm>();
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
     * @param:なし
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






