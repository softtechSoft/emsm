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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.softtech.entity.AdjustmentRequestFiles;
import com.softtech.service.AdjustmentRequestService;

/**
 * 年末調整コントローラー
 * 
 * このコントローラーは、年末調整のリスト表示、ファイルのアップロードおよびダウンロード
 * を担当します。
 */
@Controller
public class AdjustmentRequestController {

    @Autowired
    private AdjustmentRequestService adjustmentRequestService; // 年末調整リクエストサービスの注入

    /**
     * 年末調整リストを表示するメソッド
     * 
     * @param model ビューにデータを渡すためのモデルオブジェクト
     * @return 年末調整リストページのビュー名
     */
    @GetMapping("/adjustmentList")
    public String loadAdjustmentList(Model model) {
        int currentYear = java.time.LocalDate.now().getYear(); // 現在の年度を取得
        model.addAttribute("currentYear", currentYear); // 現在の年度をモデルに追加

        // 全ての社員と年末調整状態を取得し、モデルに追加
        List<Map<String, Object>> employees = adjustmentRequestService.getAllEmployeesWithAdjustmentStatuses(currentYear);
        model.addAttribute("employees", employees);

        // 現在の年度のファイルを取得し、モデルに追加
        List<AdjustmentRequestFiles> files = adjustmentRequestService.getCurrentYearFilesWithStatusOne(currentYear);
        model.addAttribute("files", files);

        return "adjustmentList"; // 年末調整リストページのビュー名を返す
    }

    /**
     * 年末調整ファイルをアップロードするメソッド
     * 
     * @param files アップロードされたファイルの配列
     * @return アップロード結果を含むレスポンスエンティティ
     */
    @PostMapping("/uploadFile")
    @ResponseBody
    public ResponseEntity<?> uploadFile(@RequestParam("files") MultipartFile[] files) {
        try {
            int currentYear = java.time.LocalDate.now().getYear(); // 現在の年度を取得
            adjustmentRequestService.uploadFiles(files, currentYear); // ファイルのアップロード処理を実行
            return ResponseEntity.ok(createResponse("ファイルのアップロードに成功しました！")); // 成功メッセージを返す
        } catch (Exception e) {
            // エラーが発生した場合、エラーメッセージを返す
            return ResponseEntity.badRequest().body(createResponse("ファイルのアップロードに失敗しました: " + e.getMessage()));
        }
    }

    /**
     * 年末調整ファイルをダウンロードするメソッド
     * 
     * @param fileName ダウンロードするファイルの名前
     * @return ダウンロードファイルを含むレスポンスエンティティ
     */
    @GetMapping("/download/{fileName:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        try {
            // 指定されたファイルをリソースとして取得
            Resource resource = adjustmentRequestService.loadFileAsResource(fileName);
            
            ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                    .filename(fileName, StandardCharsets.UTF_8)
                    .build();
            
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (Exception e) {
            // ファイルが見つからない場合は404レスポンスを返す
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * レスポンスメッセージを作成するメソッド
     * 
     * @param message メッセージ内容
     * @return メッセージを含むマップ
     */
    private Map<String, String> createResponse(String message) {
        Map<String, String> response = new java.util.HashMap<>();
        response.put("message", message);
        return response;
    }
}
