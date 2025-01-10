package com.softtech.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softtech.entity.ExpenseListEntity;
import com.softtech.service.ExpenseListService;

@Controller
@RequestMapping("/expenseList")
public class ExpenseListController {

    @Autowired
    private ExpenseListService expenseListService;

    // ============ 1. 現在の年、月のデータを初期表示 ============
    @GetMapping
    public String init(Model model) {
        LocalDate now = LocalDate.now();
        int currentYear = now.getYear();
        int currentMonth = now.getMonthValue();

        // 年リスト、月リストを構築
        List<Integer> yearList = new ArrayList<>();
        for(int y = currentYear; y > currentYear - 10; y--) {
            yearList.add(y);
        }
        List<Integer> monthList = new ArrayList<>();
        for(int m = 1; m <= 12; m++) {
            monthList.add(m);
        }

        // データを検索
        List<ExpenseListEntity> expenseList = expenseListService.findExpensesByYearMonth(currentYear, currentMonth);

        double totalCost = expenseList.stream().mapToDouble(e -> e.getCost().doubleValue()).sum();

        model.addAttribute("yearList", yearList);
        model.addAttribute("monthList", monthList);
        model.addAttribute("currentYear", currentYear);
        model.addAttribute("currentMonth", currentMonth);
        model.addAttribute("expenseList", expenseList);
        model.addAttribute("totalCost", totalCost);

        return "expenseList";
    }

    // ============ 2. 指定の年、月を検索 ============
    @GetMapping("/search")
    public String search(@RequestParam("year") int year,
                         @RequestParam("month") int month,
                         Model model) {
        // 年リスト、月リストを同様に構築
        int currentYear = LocalDate.now().getYear();
        List<Integer> yearList = new ArrayList<>();
        for(int y = currentYear; y > currentYear - 10; y--) {
            yearList.add(y);
        }
        List<Integer> monthList = new ArrayList<>();
        for(int m = 1; m <= 12; m++) {
            monthList.add(m);
        }

        // データを検索
        List<ExpenseListEntity> expenseList = expenseListService.findExpensesByYearMonth(year, month);
        double totalCost = expenseList.stream().mapToDouble(e -> e.getCost().doubleValue()).sum();

        model.addAttribute("yearList", yearList);
        model.addAttribute("monthList", monthList);
        model.addAttribute("currentYear", year);
        model.addAttribute("currentMonth", month);
        model.addAttribute("expenseList", expenseList);
        model.addAttribute("totalCost", totalCost);

        return "expenseList";
    }

    // ============ 3. 削除（論理削除） ============
    @GetMapping("/delete/{expensesID}")
    public String delete(@PathVariable("expensesID") String expensesID) {
        expenseListService.deleteExpense(expensesID);
        return "redirect:/expenseList";
    }

    // ============ 4. 編集ページへ移動（オプション） ============
    @GetMapping("/edit")
    public String editForm(@RequestParam("expensesID") String expensesID, Model model) {
        ExpenseListEntity expense = expenseListService.findById(expensesID);
        model.addAttribute("expense", expense);
        return "editExpense";
    }

    // ============ 5. インライン編集の更新（AJAX） ============
    @PostMapping("/update")
    @ResponseBody // 文字列を返す、または ResponseEntity を使用
    public ResponseEntity<String> updateExpense(@RequestBody ExpenseListEntity expense) {
        try {
            System.out.println("updateExpense - Received: " + expense);
            expenseListService.updateExpense(expense);
            return ResponseEntity.ok("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("更新失敗: " + e.getMessage());
        }
    }

}
