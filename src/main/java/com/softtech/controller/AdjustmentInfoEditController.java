package com.softtech.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.softtech.entity.AdjustmentFile;
import com.softtech.entity.Employee;
import com.softtech.service.AdjustmentInfoEditService;

/**
 * 調整情報編集コントローラー
 */
@Controller
@RequestMapping("/adjustmentInfoEdit")
public class AdjustmentInfoEditController {

    @Autowired
    private AdjustmentInfoEditService adjustmentInfoEditService;

    /**
     * 調整情報編集ページを表示する
     */
    @GetMapping
    public String showAdjustmentInfoEdit(@RequestParam("employeeId") String employeeId, Model model) {
        int currentYear = java.time.LocalDate.now().getYear();
        Employee employee = adjustmentInfoEditService.getEmployeeById(employeeId);
        if (employee == null) {
            model.addAttribute("errorMessage", "指定された社員が存在しません。");
            return "errorPage";
        }
        model.addAttribute("currentYear", currentYear);
        model.addAttribute("employeeName", employee.getEmployeeName());
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("employeeEmail", employee.getMailAdress());

        // detailTypeのファイルを取得
        List<AdjustmentFile> detailFiles = adjustmentInfoEditService.getFilesByTypeEmployeeAndYear("detailType",
                employee.getMailAdress(), currentYear);
        model.addAttribute("detailFiles", detailFiles);

        // resultTypeのファイルを取得
        List<AdjustmentFile> resultFiles = adjustmentInfoEditService.getFilesByTypeEmployeeAndYear("resultType",
                employee.getMailAdress(), currentYear);
        model.addAttribute("resultFiles", resultFiles);

        // 生成当前年份及往前推十年的年份列表
        List<Integer> yearList = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            yearList.add(currentYear - i);
        }
        model.addAttribute("yearList", yearList);

        return "adjustmentInfoEdit";
    }

    /**
     * 結果ファイルをアップロードする
     */
    @PostMapping("/uploadResultFiles")
    @ResponseBody
    public ResponseEntity<?> uploadResultFiles(@RequestParam("files") MultipartFile[] files,
            @RequestParam("employeeId") String employeeId) {
        try {
            adjustmentInfoEditService.uploadResultFiles(files, employeeId);
            return ResponseEntity.ok(createResponse("ファイルのアップロードに成功しました！"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createResponse("ファイルのアップロードに失敗しました: " + e.getMessage()));
        }
    }

    /**
     * ファイルをダウンロードする
     */
    @GetMapping("/download/{fileYear}/{employeeEmail}/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileYear") int fileYear,
            @PathVariable("employeeEmail") String employeeEmail, @PathVariable("filename") String filename) {
        try {
            Resource resource = adjustmentInfoEditService.loadFileAsResource(fileYear, employeeEmail, filename);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 指定した年度の過去ファイルを取得する
     */
    @GetMapping("/getPastFiles")
    @ResponseBody
    public Map<String, Object> getPastFiles(@RequestParam("employeeId") String employeeId, @RequestParam("year") int year) {
        Employee employee = adjustmentInfoEditService.getEmployeeById(employeeId);
        if (employee == null) {
            throw new RuntimeException("指定された社員が存在しません。");
        }
        String employeeEmail = employee.getMailAdress();
        // 仅获取 fileType 为 "resultType" 的文件
        List<AdjustmentFile> files = adjustmentInfoEditService.getFilesByTypeEmployeeAndYear("resultType", employeeEmail, year);

        Map<String, Object> response = new HashMap<>();
        response.put("files", files);
        return response;
    }

    /**
     * レスポンスメッセージを作成する
     */
    private Map<String, String> createResponse(String message) {
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return response;
    }
}
