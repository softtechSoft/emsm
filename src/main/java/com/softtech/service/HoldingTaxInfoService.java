package com.softtech.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.softtech.actionForm.EmployeeActionForm;
import com.softtech.actionForm.HoldingTaxInfoFormBean;
import com.softtech.entity.HoldingTaxInfoEntity;

/**
 * @program
 * @概要:
 * @作成者:黄
 * @作成日:2025-11-05
 * @return:
 */
@Service
public interface HoldingTaxInfoService {
    /**
     * 概要:画面表示用のquery,入力の社員IDにより、検索する
     *
     * @param:[year]
     * @return:java.util.List<com.softtech.entity.HoldingTaxInfoEntity>
     * @author:孫曄@SOFTTECH
     * @date:2022/08/10
     */
    List<HoldingTaxInfoEntity> getHoldingTaxByEmployeeID(String employeeID);

    /**
     * 概要:更新画面への検索,表示される画面の所得税IDにより、検索する
     *
     * @param:[HoldingTaxID]
     * @return:java.util.List<com.softtech.entity.HoldingTaxInfoEntity>
     * @author:孫曄@SOFTTECH
     * @date:2022/08/10
     */
    List<HoldingTaxInfoEntity> getUpdateHoldingTaxByHoldingTaxID(String HoldingTaxID);

    /**
     * 概要:ID最大値+1を取得
     *
     * @param:[]
     * @return:java.lang.String
     * @author:黄
     * @date:2025-11-05
     */
    String getNextHoldingTaxID();

    /**概要:
     *@param:[eList]
     * @param:[]
     * @return:java.lang.String
     * @author:黄
     * @date:2025-11-05
     */
    HoldingTaxInfoFormBean transforEntityToUI(List<HoldingTaxInfoEntity> eList);

    /**
     * 概要:update
     *
     * @param:[HoldingTaxInfoEntity]
     * @return:void
     * @author:黄
     * @date:2025-11-05
     */
    void updateHoldingTax(HoldingTaxInfoFormBean HoldingTaxInfoFormBean);

    /**
     * 概要:insert
     *
     * @param:[HoldingTaxInfoEntity]
     * @return:void
     * @author:黄
     * @date:2025-11-05
     */
    void insertHoldingTaxInfo(HoldingTaxInfoFormBean HoldingTaxInfoFormBean);

    /**概要:DBから社員情報を取得する,画面の社員ID選択
    *@param:[]
    *@return:java.util.List<com.softtech.actionForm.EmployeeActionForm>
    * @author:黄
    * @date:2025-11-05
    */
    List<EmployeeActionForm> queryEmployeeInfo();

    List<HoldingTaxInfoEntity> getHoldingTaxByCondition(String employeeID, String year);

    List<String> getDistinctYears();
}
