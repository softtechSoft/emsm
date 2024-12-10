package com.softtech.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.entity.AdjustmentDetail;

/**
 * 年末調整詳細情報を操作するためのマッパーインターフェース
 * 
 * このインターフェースは、MyBatisを使用してデータベースとやり取りを行い、
 * 年末調整詳細情報（AdjustmentDetail）のCRUD操作および特定のクエリを提供します。
 */
@Mapper
public interface AdjustmentDetailMapper {
    
    /**
     * すべての年末調整詳細情報を取得します。
     * 
     * @return 年末調整詳細情報のリスト
     */
    List<AdjustmentDetail> selectAll();
    
    /**
     * 指定されたIDに基づいて年末調整詳細情報を取得します。
     * 
     * @param id 年末調整詳細情報のID
     * @return 指定されたIDに対応する年末調整詳細情報
     */
    AdjustmentDetail selectById(Integer id);
    
    /**
     * 新しい年末調整詳細情報をデータベースに挿入します。
     * 
     * @param adjustmentDetail 挿入する年末調整詳細情報のオブジェクト
     * @return 挿入に成功した場合は1、失敗した場合は0
     */
    int insert(AdjustmentDetail adjustmentDetail);
    
    /**
     * 既存の年末調整詳細情報を更新します。
     * 
     * @param adjustmentDetail 更新する年末調整詳細情報のオブジェクト
     * @return 更新に成功した場合は1、失敗した場合は0
     */
    int update(AdjustmentDetail adjustmentDetail);
    
    /**
     * 指定されたIDに基づいて年末調整詳細情報を削除します。
     * 
     * @param id 削除する年末調整詳細情報のID
     * @return 削除に成功した場合は1、失敗した場合は0
     */
    int deleteById(Integer id);

    /**
     * 従業員IDと年度に基づいて年末調整詳細情報を取得します。
     * 
     * @param employeeID 従業員のID
     * @param year       年末調整の年度
     * @return 指定された従業員IDと年度に対応する年末調整詳細情報
     */
    AdjustmentDetail findByEmployeeIdAndYear(@Param("employeeID") String employeeID, @Param("year") String year);
}
