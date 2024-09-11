package com.softtech.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softtech.actionForm.TblJournalDetailFormBean;
import com.softtech.entity.TblAccount;
import com.softtech.service.TblJournalDetailService;

@Controller
@RequestMapping("/journals")
public class JournalController {

    @Autowired
    private TblJournalDetailService journalDetailService;

    // ジャーナルページの表示
    @GetMapping
    public String showJournalPage(Model model) {

    	System.out.print(journalDetailService.getAllJournalDetails());
        model.addAttribute("resultSet", journalDetailService.getAllJournalDetails());
        List<TblAccount> accounts = journalDetailService.getAllAccounts();
        Map<String, String> accountMap = accounts.stream()
                .collect(Collectors.toMap(TblAccount::getUid, TblAccount::getName));
            model.addAttribute("accounts", accountMap);
            model.addAttribute("accounts1", journalDetailService.getAllAccounts());
        int maxLineNumber = journalDetailService.getMaxLineNumber();
        model.addAttribute("maxLineNumber", maxLineNumber);
        return "showJournals";
    }

    // 新しいジャーナルを追加する
    @PostMapping("/add")
    public String addJournal(@Valid @ModelAttribute TblJournalDetailFormBean tblJournalDetailFormBean,
                             BindingResult result, Model model) {

        String bookDateStr = tblJournalDetailFormBean.getBookDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(bookDateStr, formatter);
        Timestamp bookDateTimestamp = Timestamp.valueOf(localDate.atStartOfDay());

        tblJournalDetailFormBean.setBookDate(bookDateTimestamp.toString());

        // サービスを呼び出して詳細を追加する
        journalDetailService.addJournal(tblJournalDetailFormBean);

        model.addAttribute("resultSet", journalDetailService.getAllJournalDetails());
        List<TblAccount> accounts = journalDetailService.getAllAccounts();
        Map<String, String> accountMap = accounts.stream()
                .collect(Collectors.toMap(TblAccount::getUid, TblAccount::getName));
        model.addAttribute("accounts", accountMap);
        model.addAttribute("accounts1", journalDetailService.getAllAccounts());
        model.addAttribute("maxLineNumber", journalDetailService.getMaxLineNumber());
        model.addAttribute("reloadPage", true);
        model.addAttribute("successMessage", "データが保存完了です。");
        return "showJournals";
    }
}
