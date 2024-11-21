package com.softtech.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.entity.AdjustmentDetail;

@Mapper
public interface AdjustmentDetailMapper {
    List<AdjustmentDetail> selectAll();
    AdjustmentDetail selectById(Integer id);
    int insert(AdjustmentDetail adjustmentDetail);
    int update(AdjustmentDetail adjustmentDetail);
    int deleteById(Integer id);

    // 根据员工ID查询调整详情
    AdjustmentDetail findByEmployeeId(@Param("employeeID") String employeeID);
}
