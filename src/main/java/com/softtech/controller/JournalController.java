package com.softtech.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softtech.actionForm.TblJournalDetailFormBean;
import com.softtech.service.TblJournalDetailService;

@Controller
@RequestMapping("/journals")
public class JournalController {

    @Autowired
    private TblJournalDetailService journalDetailService;

    // ジャーナルページの表示
    @GetMapping
    public String showJournalPage(Model model) {
        model.addAttribute("resultSet", journalDetailService.getAllJournalDetails());
        model.addAttribute("accounts", journalDetailService.getAllAccounts());
        model.addAttribute("maxLineNumber", journalDetailService.getMaxLineNumber());
        return "showJournals";
    }

    // 新しいジャーナルを追加する
    @PostMapping("/add")
    public String addJournal(@Valid @ModelAttribute TblJournalDetailFormBean tblJournalDetailFormBean,
                             BindingResult result, Model model) {
        // エラーがある場合
        if (result.hasErrors()) {
            // エラーメッセージのリスト
            List<FieldError> errorlst = new ArrayList<>();
            errorlst.addAll(result.getFieldErrors());
            // エラーをモデルに追加
            model.addAttribute("errors", errorlst);
            return "showJournals";
        }

        // 会計日付の設定
        String bookDateStr = tblJournalDetailFormBean.getBookDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(bookDateStr, formatter);
        Timestamp bookDateTimestamp = Timestamp.valueOf(localDate.atStartOfDay());

        tblJournalDetailFormBean.setBookDate(bookDateTimestamp.toString());

        // ジャーナルの登録
        journalDetailService.addJournal(tblJournalDetailFormBean);
        model.addAttribute("successMessage", "データが正常に保存されました。");
        model.addAttribute("resultSet", journalDetailService.getAllJournalDetails());
        model.addAttribute("accounts", journalDetailService.getAllAccounts());
        model.addAttribute("maxLineNumber", journalDetailService.getMaxLineNumber());
        return "showJournals";
    }
}