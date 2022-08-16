package com.softtech.service;

import com.softtech.actionForm.EmployeeActionForm;
import com.softtech.actionForm.IncomeTaxInfoFormBean;
import com.softtech.common.IncomeTaxIDName;
import com.softtech.entity.IncomeTaxInfoEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program
 * @概要:
 * @作成者:孫曄
 * @作成日:2022-08-10
 * @return:
 */
@Service
public interface IncomTaxInfoService {
    /**
     * 概要:画面表示用のquery,入力の社員IDにより、検索する
     *
     * @param:[year]
     * @return:java.util.List<com.softtech.entity.IncomeTaxInfoEntity>
     * @author:孫曄@SOFTTECH
     * @date:2022/08/10
     */
    List<IncomeTaxInfoEntity> getIncomeTaxByEmployeeID(String employeeID);

    /**
     * 概要:更新画面への検索,表示される画面の所得税IDにより、検索する
     *
     * @param:[incomeTaxID]
     * @return:java.util.List<com.softtech.entity.IncomeTaxInfoEntity>
     * @author:孫曄@SOFTTECH
     * @date:2022/08/10
     */
    List<IncomeTaxInfoEntity> getUpdateIncomeTaxByIncomeTaxID(String incomeTaxID);

    /**
     * 概要:ID最大値+1を取得
     *
     * @param:[]
     * @return:java.lang.String
     * @author:孫曄@SOFTTECH
     * @date:2022/08/10
     */
    String getNextIncomeTaxID();

    /**
     * 概要:更新の時、更新画面の年度を表示する用
     *
     * @param:[year]
     * @return:java.util.List<com.softtech.entity.IncomeTaxInfoEntity>
     * @author:孫曄@SOFTTECH
     * @date:2022/08/10
     */
    List<IncomeTaxIDName> getYear();

    /**概要:
    *@param:[eList]
    *@return:com.softtech.actionForm.IncomeTaxInfoFormBean
    *@author:孫曄@SOFTTECH
    *@date:2022/08/12
    */
    IncomeTaxInfoFormBean transforEntityToUI(List<IncomeTaxInfoEntity> eList);

    /**
     * 概要:update
     *
     * @param:[incomeTaxInfoEntity]
     * @return:void
     * @author:孫曄@SOFTTECH
     * @date:2022/08/10
     */
    void updateIncomeTax(IncomeTaxInfoFormBean incomeTaxInfoFormBean);

    /**
     * 概要:insert
     *
     * @param:[incomeTaxInfoEntity]
     * @return:void
     * @author:孫曄@SOFTTECH
     * @date:2022/08/10
     */
    void insertIncomeTaxInfo(IncomeTaxInfoFormBean incomeTaxInfoFormBean);

    /**概要:DBから社員情報を取得する,画面の社員ID選択
    *@param:[]
    *@return:java.util.List<com.softtech.actionForm.EmployeeActionForm>
    *@author:孫曄@SOFTTECH
    *@date:2022/08/12
    */
    List<EmployeeActionForm> queryEmployeeInfo();
}
