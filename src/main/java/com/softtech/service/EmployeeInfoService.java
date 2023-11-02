package com.softtech.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.softtech.actionForm.EmployeeActionForm;
import com.softtech.actionForm.EmployeeInfoFormBean;
import com.softtech.entity.EmployeeInfoEntity;

@Service
public interface EmployeeInfoService {

    /**
     * 概要:画面表示用のquery,入力の社員IDにより、検索する
     *
     * @param:[employeeID]
     * @return:java.util.List<com.softtech.entity.IncomeTaxInfoEntity>
     * @author:スッ
     * @date:2023/10/23
     */
	List<EmployeeInfoEntity> getEmployeeID(String employeeID);

	/**　全量検索　*/
	List<EmployeeInfoEntity> getEmployeeIDAll(String employeeID);

    /**
     * 概要:更新画面への検索,表示される画面の所得税IDにより、検索する
     *
     * @param:[incomeTaxID]
     * @return:java.util.List<com.softtech.entity.IncomeTaxInfoEntity>
     *@author:スッ
     * @date:2023/10/23
     */
	List<EmployeeInfoEntity> getUpdateEmployeeByEmployeeID(String employeeID);
    /**
     * 概要:ID最大値+1を取得
     *
     * @param:[]
     * @return:java.lang.String
     *@author:スッ
     * @date:2023/10/23
     */
	 String getNextEmployeeID() ;

	 /**概要:
	 *@param:[eList]
	 *@return:com.softtech.actionForm.IncomeTaxInfoFormBean
	 *@author:スッ
     * @date:2023/10/23
     */
	EmployeeInfoFormBean transforEntityToUI(List<EmployeeInfoEntity> eList);
	/**
     * 概要:update
     *
     * @param:[incomeTaxInfoEntity]
     * @return:void
     * @author:スッ
     * @date:2022/08/10
     */
	void updateEmployeeInfo(EmployeeInfoFormBean employeeInfoFormBean);
	/**
     * 概要:insert
     *
     * @param:[incomeTaxInfoEntity]
     * @return:void
     * @author:スッ
     * @date:2022/08/10
     */
	void insertEmployeeInfo(EmployeeInfoFormBean employeeInfoFormBean);
	/**概要:DBから社員情報を取得する,画面の社員ID選択
	 *@param:[]
	 *@return:java.util.List<com.softtech.actionForm.EmployeeActionForm>
	 *@author:孫曄@SOFTTECH
	 *@date:2022/08/12
	 */
	 List<EmployeeActionForm> queryEmployeeInfo();

}
