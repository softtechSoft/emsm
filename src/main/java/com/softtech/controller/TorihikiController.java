package com.softtech.controller;

import com.softtech.entity.Torihiki;
import com.softtech.service.TorihikiService;
import com.softtech.util.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import javax.validation.Valid; // 导入 @Valid 注解
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/torihiki")
public class TorihikiController {

    @Autowired
    private TorihikiService torihikiService;

    @GetMapping
    public String list(Model model) {
        List<Torihiki> torihikiList = torihikiService.getAllTorihiki();
        model.addAttribute("torihikiList", torihikiList);
        return "torihikiList";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String keyword, Model model) {
        try {
            // 只检查长度
            if (keyword != null && keyword.length() > 50) {
                model.addAttribute("error", "検索キーワードは50文字以内で入力してください。");
                return "torihikiList";
            }

            List<Torihiki> results = torihikiService.searchTorihiki(keyword);
            
            if (results == null || results.isEmpty()) {
                model.addAttribute("error", "該当データがありません。");
            } else {
                model.addAttribute("torihikiList", results);
            }
            
            model.addAttribute("keyword", keyword);
            return "torihikiList";
        } catch (Exception e) {
            model.addAttribute("error", "システムエラーが発生しました。");
            return "torihikiList";
        }
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        Torihiki torihiki = new Torihiki();
        String nextId = torihikiService.generateNewCompanyId();
        torihiki.setCompanyID(nextId);
        model.addAttribute("torihiki", torihiki);
        model.addAttribute("isNew", true);
        model.addAttribute("formatUtil", new FormatUtil());
        return "torihikiEdit";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") String companyID, Model model) {
        Torihiki torihiki = torihikiService.getTorihikiById(companyID);
        if (torihiki == null) {
            return "redirect:/torihiki";
        }
        

        
        model.addAttribute("torihiki", torihiki);
        model.addAttribute("isNew", false);
        return "torihikiEdit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("torihiki") @Valid Torihiki torihiki,
                      BindingResult result,
                      @RequestParam(value = "_isNew", required = false, defaultValue = "false") boolean isNew,
                      Model model,
                      RedirectAttributes redirectAttributes) {
        // 1. 数据清洗现在在Torihiki实体类的setter方法中自动完成

        // 2. 检查验证结果
        if (result.hasErrors()) {
            // 记录BindingResult中的所有错误
            result.getAllErrors().forEach(error -> {
                System.err.println("Validation Error: " + error.getDefaultMessage());
                if (error instanceof org.springframework.validation.FieldError) {
                    org.springframework.validation.FieldError fieldError = (org.springframework.validation.FieldError) error;
                    System.err.println("Field: " + fieldError.getField() + ", Rejected Value: " + fieldError.getRejectedValue());
                }
            });
            model.addAttribute("error", "入力内容に誤りがあります。詳細をログで確認してください。");
            model.addAttribute("isNew", isNew);
            return "torihikiEdit";
        }
        // 数据清洗：确保邮编、電話番号、契約日为纯数字，只在验证通过后执行
        torihiki.setPostCode(torihiki.getPostCode() != null ? torihiki.getPostCode().replaceAll("[^0-9]", "") : null);
        torihiki.setBasicContractDate(torihiki.getBasicContractDate() != null ? torihiki.getBasicContractDate().replaceAll("[^0-9]", "") : null);
        torihiki.setPhoneNumber(torihiki.getPhoneNumber() != null ? torihiki.getPhoneNumber().replaceAll("[^0-9]", "") : null);
        try {

            
            if (isNew) {
                torihikiService.insertTorihiki(torihiki);
                redirectAttributes.addFlashAttribute("message", "取引先を登録しました。");
            } else {
                torihikiService.updateTorihiki(torihiki);
                redirectAttributes.addFlashAttribute("message", "取引先を更新しました。");
            }
            return "redirect:/torihiki";
        } catch (Exception e) {
            // 记录完整的异常堆栈
            System.err.println("Error saving Torihiki: " + e.getMessage());
            e.printStackTrace(); // 打印堆栈信息
            model.addAttribute("error", "システムエラーが発生しました。詳細をログで確認してください。");
            model.addAttribute("isNew", isNew);
            return "torihikiEdit";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String companyID,
                        RedirectAttributes redirectAttributes) {
        try {
            if (companyID == null || companyID.trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "該当データがありません。");
                return "redirect:/torihiki";
            }
            
            Torihiki torihiki = torihikiService.getTorihikiById(companyID);
            if (torihiki == null) {
                redirectAttributes.addFlashAttribute("error", "該当データがありません。");
                return "redirect:/torihiki";
            }
            
            torihikiService.deleteTorihiki(companyID);
            redirectAttributes.addFlashAttribute("message", "取引先を削除しました。");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "システムエラーが発生しました。");
        }
        return "redirect:/torihiki";
    }
} 