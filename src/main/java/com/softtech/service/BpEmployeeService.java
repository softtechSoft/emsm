package com.softtech.service;

import com.softtech.entity.BpEmployee;
import java.util.List;

/**
 * BP社員管理用Service
 */
public interface BpEmployeeService {
    /**
     * 全社員リスト取得
     * @return 社員リスト
     */
    List<BpEmployee> getAllEmployees();

    /**
     * 社員IDで1件取得
     * @param employeeId 社員ID
     * @return 社員情報
     */
    BpEmployee getEmployeeById(String employeeId);

    /**
     * 新規登録
     * @param employee 社員情報
     */
    void insertEmployee(BpEmployee employee);

    /**
     * 更新
     * @param employee 社員情報
     */
    void updateEmployee(BpEmployee employee);

    /**
     * 削除
     * @param employeeId 社員ID
     */
    void deleteEmployee(String employeeId);
} 