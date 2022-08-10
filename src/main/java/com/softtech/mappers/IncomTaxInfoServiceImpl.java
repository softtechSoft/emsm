package com.softtech.mappers;

import com.softtech.entity.IncomeTaxInfoEntity;
import com.softtech.service.IncomTaxInfoService;
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
public class IncomTaxInfoServiceImpl implements IncomTaxInfoService {
    /**
     * 概要:画面表示用のquery,入力の年度により、検索する
     *
     * @param year
     * @param:[year]
     * @return:java.util.List<com.softtech.entity.IncomeTaxInfoEntity>
     * @author:孫曄@SOFTTECH
     * @date:2022/08/10
     */
    @Override
    public List<IncomeTaxInfoEntity> getIncomeTaxByYear(String year) {
        return null;
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
        return null;
    }

    /**
     * 概要:最大のIncomeTaxIDを取得、+1用
     *
     * @param incomeTaxID
     * @param:[incomeTaxID]
     * @return:java.lang.String
     * @author:孫曄@SOFTTECH
     * @date:2022/08/10
     */
    @Override
    public String getMaxIncomeTaxID(String incomeTaxID) {
        return null;
    }

    /**
     * 概要:更新の時、更新画面の年度を表示する用
     *
     * @param year
     * @param:[year]
     * @return:java.util.List<com.softtech.entity.IncomeTaxInfoEntity>
     * @author:孫曄@SOFTTECH
     * @date:2022/08/10
     */
    @Override
    public List<IncomeTaxInfoEntity> getYear(String year) {
        return null;
    }

    /**
     * 概要:update
     *
     * @param incomeTaxInfoEntity
     * @param:[incomeTaxInfoEntity]
     * @return:void
     * @author:孫曄@SOFTTECH
     * @date:2022/08/10
     */
    @Override
    public void updateIncomeTax(IncomeTaxInfoEntity incomeTaxInfoEntity) {

    }

    /**
     * 概要:insert
     *
     * @param incomeTaxInfoEntity
     * @param:[incomeTaxInfoEntity]
     * @return:void
     * @author:孫曄@SOFTTECH
     * @date:2022/08/10
     */
    @Override
    public void insertIncomeTaxInfo(IncomeTaxInfoEntity incomeTaxInfoEntity) {

    }
}
