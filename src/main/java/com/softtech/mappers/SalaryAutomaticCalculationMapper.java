package com.softtech.mappers;

import com.softtech.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program
 * @概要:
 * @作成者:孫曄
 * @作成日:2022-08-30
 * @return:
 */
@Mapper
public interface SalaryAutomaticCalculationMapper {

    /**
     * 概要:找到最大主键ID对应的数据，查出最新的一条关于厚生保险率的数据
     *
     * @param:[]
     * @return:void
     * @author:孫曄
     * @date:2022/08/30
     */
    List<EmplyinsrateInfoEntity> getWelfarefeeData();

    /**
     * 概要:SQL计算年龄：根据员工ID
     *
     * @param:[employeeID]
     * @return:void
     * @author:孫曄
     * @date:2022/08/30
     */
    String getAgeByEmployeeID(@Param("employeeID") String employeeID);

    /**
     * 概要:根据年份查雇佣保险，只有 雇用保険労働者負担料率‰ 参与计算员工工资
     * 直接根据当年时间计算即可
     *
     * @param:[]
     * @return:java.lang.String
     * @author:孫曄
     * @date:2022/08/30
     */
    String getLaborBurdenRate();

    /**
     * 概要:查询所得税，住民税，需要注意月份对应，因为不是每个月的住民税和所得税都一样，只能根据每个月单独算，所以是SELECT * 也就是把每个月数据全算出来然后java或者jsp一个个算
     *
     * @param:[]
     * @return:java.util.List<com.softtech.entity.IncomeTaxInfoEntity>
     * @author:孫曄
     * @date:2022/08/30
     */
    List<IncomeTaxInfoEntity> getIncomeTax();

    /**
     * 概要:厚生健康保险，厚生年金 为 给料From<基本给<=给料To 的情况下查找对应的标准报酬，然后标准报酬乘保险率：注意和你的实际收入没关系，只和对应的那一档标准报酬有关
     *
     * @param:[employeeID]
     * @return:java.util.List<com.softtech.entity.WelfarefeeInfoEntity>
     * @author:孫曄
     * @date:2022/08/30
     */
    List<WelfarefeeInfoEntity> getStandSalaryByEmployeeID(@Param("employeeID") String employeeID);

    /**
     * 概要:根据员工ID查他的基本给，残業単価和控除単価，残業単価和控除単価这俩必然有一个没有或者干脆都没有，在没有的情况下查出来的是0，不影响计算也就无所谓了
     *
     * @param:[employeeID]
     * @return:java.util.List<com.softtech.entity.BaseSalaryInfoEntity>
     * @author:孫曄
     * @date:2022/08/30
     */
    List<BaseSalaryInfoEntity> getBaseSalaryAndOvertimePayAndInsufficienttimePayByEmployeeID(@Param("employeeID") String employeeID);

    /**
     * 概要:查交通费和出张费要求是最新的一条
     *
     * @param:[employeeID]
     * @return:java.util.List<com.softtech.entity.TransportEntity>
     * @author:孫曄
     * @date:2022/08/30
     */
    List<TransportEntity> getTransportExpenseAndBusinessTripByEmployeeID(@Param("employeeID") String employeeID);

    /**概要:根据画面的年份检索员工ID
    *@param:[year]
    *@return:java.lang.String
    *@author:孫曄
    *@date:2022/08/30
    */
    String getEmployeeIDByYear(@Param("year") String year);
}
