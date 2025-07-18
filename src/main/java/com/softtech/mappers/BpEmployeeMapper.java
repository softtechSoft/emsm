package com.softtech.mappers;

import com.softtech.entity.BpEmployee;
import java.util.List;

/**
 * BP社員管理用Mapper
 */
public interface BpEmployeeMapper {
    /**
     * 全社員リスト取得
     * @return 社員リスト
     */
    List<BpEmployee> getAllEmployees();

    /**
     * 社員IDで1件取得
     * @param employeeId 社員ID
     * @return 社員信息
     */
    BpEmployee getEmployeeById(String employeeId);

    /**
     * 新規登録
     * @param employee 社員信息
     */
    void insertEmployee(BpEmployee employee);

    /**
     * 更新
     * @param employee 社員信息
     */
    void updateEmployee(BpEmployee employee);

    /**
     * 削除
     * @param employeeId 社員ID
     */
    void deleteEmployee(String employeeId);
} 