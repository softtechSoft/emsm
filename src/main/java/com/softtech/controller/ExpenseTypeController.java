package com.softtech.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.softtech.entity.ExpenseTypeEntity;
import com.softtech.service.ExpenseTypeService;

/**
 * 经费种别管理的控制器类。
 * 经费种别的一览表示、编辑、追加、删除的功能を提供する。
 */
@Controller
@RequestMapping("/expenseType")
public class ExpenseTypeController {

    @Autowired
    private ExpenseTypeService expenseTypeService;

    private String getCurrentUser(HttpSession session) {
        String loginUserName = (String) session.getAttribute("loginUserName");
        if (loginUserName == null) {
            throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED,
                "ユーザーがログインしていません。先にログインしてください。"
            );
        }
        return loginUserName;
    }

    /**
     * 经费种别一览画面を表示する。
     * 
     * @param model 模型
     * @return 一览画面のビュー名
     */
    @GetMapping
    public String list(Model model) {
        // 种别选项一览を取得（検索フォーム用）
        List<Map<String, String>> expenseTypeList = expenseTypeService.getAllExpenseTypeOptions();
        model.addAttribute("expenseTypeList", expenseTypeList);
        
        // 种别ごとのデータを取得
        Map<String, List<ExpenseTypeEntity>> expenseTypeGroups = expenseTypeService.getAllExpenseTypesByGroup();
        
        if (expenseTypeGroups != null && !expenseTypeGroups.isEmpty()) {
            model.addAttribute("expenseTypeGroups", expenseTypeGroups);
        }
        
        return "expenseType";
    }

    /**
     * 种别でフィルタリングした经费种别一览を表示する。
     * 
     * @param expensesType 经费种别代码
     * @param model 模型
     * @return 一览画面のビュー名
     */
    @GetMapping("/search")
    public String search(@RequestParam(value = "expensesType", required = false) String expensesType, Model model) {
        // 种别选项一览を取得（検索フォーム用）
        List<Map<String, String>> expenseTypeList = expenseTypeService.getAllExpenseTypeOptions();
        model.addAttribute("expenseTypeList", expenseTypeList);
        
        // 検索条件による絞り込み
        Map<String, List<ExpenseTypeEntity>> expenseTypeGroups;
        
        if (expensesType == null || expensesType.isEmpty()) {
            // 検索条件なしの場合は全てを表示
            expenseTypeGroups = expenseTypeService.getAllExpenseTypesByGroup();
        } else {
            // 特定の种别のみを表示
            expenseTypeGroups = expenseTypeService.getExpenseTypesByType(expensesType);
        }
        
        if (expenseTypeGroups != null && !expenseTypeGroups.isEmpty()) {
            model.addAttribute("expenseTypeGroups", expenseTypeGroups);
        }
        
        return "expenseType";
    }
    
    /**
     * 经费种别编辑画面を表示する
     * 
     * @param id 经费项目ID（任意）
     * @param type 经费种别代码（任意）
     * @param model 模型
     * @return 编辑画面のビュー名
     */
    @GetMapping("/edit")
    public String edit(@RequestParam(value = "id", required = false) String id, 
                       @RequestParam(value = "type", required = false) String type, 
                       Model model) {
        // 全ての经费种别を取得
        Map<String, List<ExpenseTypeEntity>> expenseTypeGroups = expenseTypeService.getAllExpenseTypesByGroup();
        model.addAttribute("expenseTypeGroups", expenseTypeGroups);
        
        int maxExpenseId = expenseTypeService.getMaxExpenseId();
        model.addAttribute("maxExpenseId", maxExpenseId);
        
        return "expenseTypeEdit";
    }
    
    /**
     * 新规经费项目を追加する
     * 
     */
    @PostMapping("/addItem")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> addItem(
            @RequestParam("expensesType") String expensesType,
            @RequestParam("expensesTypeName") String expensesTypeName,
            @RequestParam("expenseName") String expenseName,
            HttpSession session) {
        
        Map<String, Object> result = new HashMap<>();
        
        try {
            String currentUser = getCurrentUser(session);
            
            ExpenseTypeEntity newItem = expenseTypeService.addExpenseItem(expensesType, expensesTypeName, expenseName, currentUser);
            
            result.put("status", "ok");
            result.put("message", "经费项目を追加しました。");
            result.put("id", newItem.getId());
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", e.getMessage());
            
            return ResponseEntity.badRequest().body(result);
        }
    }
    
    /**
     * 经费项目を更新する
     * 
     */
    @PostMapping("/updateItem")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateItem(
            @RequestParam("id") Integer id,
            @RequestParam("expenseName") String expenseName,
            HttpSession session) {
        
        Map<String, Object> result = new HashMap<>();
        
        try {
            String currentUser = getCurrentUser(session);
            
            expenseTypeService.updateExpenseItem(id, expenseName, currentUser);
            
            result.put("status", "ok");
            result.put("message", "经费项目を更新しました。");
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", e.getMessage());
            
            return ResponseEntity.badRequest().body(result);
        }
    }
    
    /**
     * 经费项目を削除する
     * 
     */
    @PostMapping("/deleteItem/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteItem(@PathVariable("id") Integer id, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            String currentUser = getCurrentUser(session);
            
            expenseTypeService.deleteExpenseItem(id, currentUser);
            
            result.put("status", "ok");
            result.put("message", "经费项目を削除しました。");
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", e.getMessage());
            
            return ResponseEntity.badRequest().body(result);
        }
    }
    
    /**
     * 新规经费种别を追加する
     * 
     */
    @PostMapping("/addCategory")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> addCategory(
            @RequestParam("expenseName") String expenseName,
            @RequestParam("expenseItemName") String expenseItemName,
            HttpSession session) {
        
        Map<String, Object> result = new HashMap<>();
        
        try {
            String currentUser = getCurrentUser(session);
            
            String newCategoryId = expenseTypeService.addExpenseCategory(expenseName, expenseItemName, currentUser);
            
            result.put("status", "ok");
            result.put("message", "经费种别を追加しました。");
            result.put("categoryId", newCategoryId);
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", e.getMessage());
            
            return ResponseEntity.badRequest().body(result);
        }
    }
    
    /**
     * 经费种别を更新する
     *
     */
    @PostMapping("/updateCategory")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateCategory(
            @RequestParam("id") Integer id,
            @RequestParam("expenseName") String expenseName,
            HttpSession session) {

        Map<String, Object> result = new HashMap<>();

        try {
            String currentUser = getCurrentUser(session);
            
            expenseTypeService.updateExpenseCategory(id, expenseName, currentUser);

            result.put("status", "ok");
            result.put("message", "经费种别を更新しました。");
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }

    
    /**
     * 经费种别を削除する
     * 
     */
    @PostMapping("/deleteCategory/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteCategory(@PathVariable("id") Integer id, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            String currentUser = getCurrentUser(session);
            
            expenseTypeService.deleteExpenseCategory(id, currentUser);
            
            result.put("status", "ok");
            result.put("message", "经费种别を削除しました。");
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", e.getMessage());
            
            return ResponseEntity.badRequest().body(result);
        }
    }
}