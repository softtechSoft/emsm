package com.softtech.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
     * 調整リストを表示する
     */
    @GetMapping("/adjustmentList")
    public String loadAdjustmentList(Model model) {
        int currentYear = java.time.LocalDate.now().getYear();
        model.addAttribute("currentYear", currentYear);

        // 全ての社員と調整状態を取得
        List<Map<String, Object>> employees = adjustmentRequestService.getAllEmployeesWithAdjustmentStatuses(currentYear);
        model.addAttribute("employees", employees);

        // 現在の年度のファイルを取得
        List<AdjustmentRequestFiles> files = adjustmentRequestService.getCurrentYearFilesWithStatusOne(currentYear);
        model.addAttribute("files", files);

        return "adjustmentList";
    }

    /**
     * ファイルをアップロードする
     */
    @PostMapping("/uploadFile")
    @ResponseBody
    public ResponseEntity<?> uploadFile(@RequestParam("files") MultipartFile[] files, HttpSession session) {
        String employeeEmail = (String) session.getAttribute("userMailAdress");
        String employeeID = (String) session.getAttribute("loginUserID");
        if (employeeEmail == null || employeeID == null) {
            return ResponseEntity.badRequest().body(createResponse("ログイン情報が見つかりません。再ログインしてください。"));
        }

        try {
            adjustmentRequestService.uploadFiles(files, employeeID, employeeEmail);
            return ResponseEntity.ok(createResponse("ファイルのアップロードに成功しました！"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createResponse("ファイルのアップロードに失敗しました: " + e.getMessage()));
        }
    }

    /**
     * ファイルをダウンロードする
     */
    @GetMapping("/download/{fileName:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        try {
            Resource resource = adjustmentRequestService.loadFileAsResource(fileName);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * レスポンスメッセージを作成する
     */
    private Map<String, String> createResponse(String message) {
        Map<String, String> response = new java.util.HashMap<>();
        response.put("message", message);
        return response;
    }
}
