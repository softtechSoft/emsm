package com.softtech.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.softtech.entity.AdjustmentRequestFiles;
import com.softtech.service.AdjustmentRequestService;

/**
 * 年末調整コントローラー
 */
@Controller
public class AdjustmentRequestController {

    @Autowired
    private AdjustmentRequestService adjustmentRequestService;

    /**
     * 年末調整リストを表示するメソッド
     */
    @GetMapping("/adjustmentList")
    public String loadAdjustmentList(Model model) {
        int currentYear = java.time.LocalDate.now().getYear();
        model.addAttribute("currentYear", currentYear);

        adjustmentRequestService.initFolder();

        // 全社員と年末調整状態を取得
        List<Map<String, Object>> employees = adjustmentRequestService.getAllEmployeesWithAdjustmentStatuses(currentYear);
        model.addAttribute("employees", employees);

        // 現在の年度のファイルを取得
        List<AdjustmentRequestFiles> files = adjustmentRequestService.getCurrentYearFilesAll(currentYear);
        model.addAttribute("files", files);

        //fileULStatusを取得
        boolean isFinalized = adjustmentRequestService.checkIfAnyFileIsFinalized(currentYear);
        model.addAttribute("isFinalized", isFinalized);

        return "adjustmentList";
    }

    /**
     * 年末調整ファイルをアップロードするメソッド
     */
    @PostMapping("/uploadFile")
    @ResponseBody
    public ResponseEntity<?> uploadFile(@RequestParam("files") MultipartFile[] files) {
    	adjustmentRequestService.initFolder();
        try {
            int currentYear = java.time.LocalDate.now().getYear();
            adjustmentRequestService.uploadFiles(files, currentYear);
            return ResponseEntity.ok(createResponse("ファイルのアップロードに成功しました！"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createResponse("ファイルのアップロードに失敗しました: " + e.getMessage()));
        }
    }

    /**
     * 年末調整ファイルをダウンロードするメソッド
     */
    @GetMapping("/download/{fileName:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
    	adjustmentRequestService.initFolder();
        try {
            Resource resource = adjustmentRequestService.loadFileAsResource(fileName);
            ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                    .filename(fileName, StandardCharsets.UTF_8)
                    .build();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 年末調整ファイルを削除するメソッド
     */
    @PostMapping("/deleteFile")
    @ResponseBody
    public ResponseEntity<?> deleteFile(@RequestBody Map<String, String> payload) {
    	adjustmentRequestService.initFolder();

        String fileName = payload.get("fileName");
        if (fileName == null || fileName.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(createResponse("ファイル名が無効です。"));
        }
        try {
            adjustmentRequestService.deleteFile(fileName);
            return ResponseEntity.ok(createResponse("削除が成功しました。"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createResponse("ファイルの削除に失敗しました: " + e.getMessage()));
        }
    }

    /**
     * 確定処理を行うメソッド
     */
    @PostMapping("/finalizeAdjustment")
    @ResponseBody
    public ResponseEntity<?> finalizeAdjustment(@RequestBody Map<String, Object> payload) {

    	adjustmentRequestService.initFolder();

    	try {
            Object yearObj = payload.get("fileYear");
            int fileYear;
            if (yearObj instanceof Number) {
                fileYear = ((Number) yearObj).intValue();
            } else if (yearObj instanceof String) {
                fileYear = Integer.parseInt((String) yearObj);
            } else {
                return ResponseEntity.badRequest().body(createResponse("年度が無効です。"));
            }

            adjustmentRequestService.finalizeAdjustment(fileYear);

            return ResponseEntity.ok(createResponse("確定が成功しました。"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createResponse("確定に失敗しました: " + e.getMessage()));
        }
    }

    /**
     * レスポンスメッセージ作成用ユーティリティ
     */
    private Map<String, String> createResponse(String message) {
        Map<String, String> response = new java.util.HashMap<>();
        response.put("message", message);
        return response;
    }
}
