package com.softtech.controller;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.softtech.entity.AdjustmentDetail;
import com.softtech.entity.AdjustmentFile;
import com.softtech.entity.Employee;
import com.softtech.service.AdjustmentInfoEditService;

/**
 * 年末調整情報編集コントローラー
 * 
 * このコントローラーは、従業員の調整情報の表示、ファイルのアップロードおよびダウンロード、
 * 過去のファイルの取得、および調整の確定処理を担当します。
 */
@Controller
@RequestMapping("/adjustmentInfoEdit")
public class AdjustmentInfoEditController {

    @Autowired
    private AdjustmentInfoEditService adjustmentInfoEditService; // 調整情報編集サービスの注入
   

    /**
     * 年末調整情報編集ページを表示するメソッド
     * 
     * @param employeeId 編集対象の従業員ID
     * @param model      ビューにデータを渡すためのモデルオブジェクト
     * @return 年末調整情報編集ページのビュー名
     */
    @GetMapping
    public String showAdjustmentInfoEdit(@RequestParam("employeeId") String employeeId, Model model) {
        int currentYear = java.time.LocalDate.now().getYear(); // 現在の年度を取得
        Employee employee = adjustmentInfoEditService.getEmployeeById(employeeId); // 従業員情報を取得

        // 指定された従業員が存在しない場合、エラーページを表示
        if (employee == null) {
            model.addAttribute("errorMessage", "指定された社員が存在しません。");
            return "errorPage";
        }

        // モデルに必要なデータを追加
        model.addAttribute("currentYear", currentYear); // 現在の年度
        model.addAttribute("employeeName", employee.getEmployeeName()); // 従業員名
        model.addAttribute("employeeId", employeeId); // 従業員ID
        model.addAttribute("employeeEmail", employee.getMailAdress()); // 従業員のメールアドレス

        // detailTypeのファイルを取得してモデルに追加
        List<AdjustmentFile> detailFiles = adjustmentInfoEditService.getFilesByTypeEmployeeAndYear("detailType",
                employee.getEmployeeID(), currentYear);
        model.addAttribute("detailFiles", detailFiles);

        // resultTypeのファイルを取得してモデルに追加
        List<AdjustmentFile> resultFiles = adjustmentInfoEditService.getFilesByTypeEmployeeAndYear("resultType",
                employee.getEmployeeID(), currentYear);
        model.addAttribute("resultFiles", resultFiles);

        // 現在の年度および過去10年間の年度リストを生成してモデルに追加
        List<Integer> yearList = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            yearList.add(currentYear - i);
        }
        model.addAttribute("yearList", yearList);
        
     // AdjustmentDetailを取得し、adjustmentStatus、uploadStatusを設定
        AdjustmentDetail detail = adjustmentInfoEditService
                .getAdjustmentDetailByEmployeeIdAndYear(employee.getEmployeeID(), String.valueOf(currentYear));

        String adjustmentStatus = (detail == null || detail.getAdjustmentStatus() == null) 
                                  ? "0" 
                                  : detail.getAdjustmentStatus();
        String uploadStatus = (detail == null || detail.getUploadStatus() == null) 
                              ? "0" 
                              : detail.getUploadStatus();

        model.addAttribute("adjustmentStatus", adjustmentStatus);
        model.addAttribute("uploadStatus", uploadStatus);

        return "adjustmentInfoEdit"; // 年末調整情報編集ページのビュー名を返す
    }

    /**
     * 結果ファイルをアップロードするメソッド
     * 
     * @param files      アップロードされたファイルの配列
     * @param employeeId アップロード対象の従業員ID
     * @return アップロード結果を含むレスポンスエンティティ
     */
    @PostMapping("/uploadResultFiles")
    @ResponseBody
    public ResponseEntity<?> uploadResultFiles(@RequestParam("files") MultipartFile[] files,
            @RequestParam("employeeId") String employeeId) {
        try {
            // 結果ファイルのアップロード処理を実行
            adjustmentInfoEditService.uploadResultFiles(files, employeeId);
            return ResponseEntity.ok(createResponse("ファイルのアップロードに成功しました！"));
        } catch (Exception e) {
            // エラーが発生した場合、エラーメッセージを返す
            return ResponseEntity.badRequest().body(createResponse("ファイルのアップロードに失敗しました: " + e.getMessage()));
        }
    }

    /**
     * ファイルをダウンロードするメソッド
     * 
     * @param fileType   ダウンロードするファイルの種類（例: "resultType", "detailType"）
     * @param fileYear   ダウンロードするファイルの年度
     * @param employeeID ダウンロード対象の従業員ID
     * @param filename   ダウンロードするファイル名
     * @return ダウンロードファイルを含むレスポンスエンティティ
     */
    @GetMapping("/download/{fileType}/{fileYear}/{employeeID}/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileType") String fileType,
            @PathVariable("fileYear") int fileYear,
            @PathVariable("employeeID") String employeeID,
            @PathVariable("filename") String filename) {
        try {
            // 指定されたファイルをリソースとして取得
            Resource resource = adjustmentInfoEditService.loadFileAsResource(fileType, fileYear, employeeID, filename);
            
            
            ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                    .filename(filename, StandardCharsets.UTF_8)
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
     * 指定した年度の過去ファイルを取得するメソッド
     * 
     * @param employeeId 取得対象の従業員ID
     * @param year       取得対象の年度
     * @return 指定された年度のファイル情報を含むマップ
     */
    @GetMapping("/getPastFiles")
    @ResponseBody
    public Map<String, Object> getPastFiles(@RequestParam("employeeId") String employeeId, @RequestParam("year") int year) {
        // 指定された従業員IDで従業員情報を取得
        Employee employee = adjustmentInfoEditService.getEmployeeById(employeeId);
        if (employee == null) {
            throw new RuntimeException("指定された社員が存在しません。");
        }

        // 指定された年度とタイプでファイルを取得
        List<AdjustmentFile> files = adjustmentInfoEditService.getFilesByTypeEmployeeAndYear("resultType", employee.getEmployeeID(), year);

        // レスポンスマップを作成してファイル情報を追加
        Map<String, Object> response = new HashMap<>();
        response.put("files", files);
        return response;
    }

    /**
     * 年末調整を確定ボタンメソッド
     * 
     * @param employeeId 確定対象の従業員ID
     * @return 確定処理結果を含むレスポンスエンティティ
     */
    @PostMapping("/finalizeAdjustment")
    @ResponseBody
    public ResponseEntity<?> finalizeAdjustment(@RequestParam("employeeId") String employeeId) {
        try {
            // 先にuploadStatusを確認する
            Employee employee = adjustmentInfoEditService.getEmployeeById(employeeId); 
            if (employee == null) {
                return ResponseEntity.badRequest().body(createResponse("指定された社員が存在しません。"));
            }
            String employeeID = employee.getEmployeeID();
            int currentYear = java.time.LocalDate.now().getYear();

            // 現在の年度でAdjustmentDetailを検索
            AdjustmentDetail detail = adjustmentInfoEditService.getAdjustmentDetailByEmployeeIdAndYear(employeeID, String.valueOf(currentYear));
            // detailが存在しないか、uploadStatusが1でない場合はエラー
            if (detail == null || !"1".equals(detail.getUploadStatus())) {
                return ResponseEntity.badRequest().body(createResponse("該当社員はファイルがまだアップロードされていません。調整完了できません。"));
            }

            // 問題がなければ調整の確定処理を実行
            adjustmentInfoEditService.finalizeAdjustment(employeeId);
            return ResponseEntity.ok(createResponse("調整完了しました！"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createResponse("調整完了に失敗しました: " + e.getMessage()));
        }
    }

//    /**
//     * ファイルを直接ダウンロードするメソッド
//     * 
//     * @param filePath ダウンロードするファイルのパス
//     * @return ダウンロードファイルを含むレスポンスエンティティ
//     */
//    @GetMapping("/downloadFileDirect")
//    @ResponseBody
//    public ResponseEntity<Resource> downloadFileDirect(@RequestParam("filePath") String filePath) {
//        try {
//            Path path = Paths.get(filePath); // ファイルパスを取得
//            if (!Files.exists(path)) {
//                return ResponseEntity.notFound().build(); // ファイルが存在しない場合、404レスポンスを返す
//            }
//            // ファイルをリソースとして取得
//            Resource resource = new UrlResource(path.toUri());
//            // ファイルをダウンロード可能なレスポンスを返す
//            return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
//        } catch (Exception e) {
//            // エラーが発生した場合、404レスポンスを返す
//            return ResponseEntity.notFound().build();
//        }
//    }

    
    /**
     * 指定されたファイルを削除するメソッド
     */
    @PostMapping("/deleteFile")
    @ResponseBody
    public ResponseEntity<?> deleteResultFile(@RequestBody Map<String, Object> payload) {
        try {
            // 1) 从payload获取参数
            String employeeID = (String) payload.get("employeeID");
            String fileName   = (String) payload.get("fileName");
            String fileType   = (String) payload.get("fileType");

            // fileYear 要从 int 或字符串转换
            Object yearObj = payload.get("fileYear");
            Integer fileYear = null;
            if (yearObj instanceof Number) {
                fileYear = ((Number) yearObj).intValue();
            } else if (yearObj instanceof String) {
                fileYear = Integer.valueOf((String) yearObj);
            }

            if (employeeID == null || fileName == null || fileType == null || fileYear == null) {
                return ResponseEntity.badRequest().body(createResponse("パラメータが無効です。"));
            }

            // 2) Serviceメソッド呼び出し
            adjustmentInfoEditService.deleteFile(employeeID, fileYear, fileName, fileType);

            // 3) 成功メッセージを返す
            return ResponseEntity.ok(createResponse("削除が成功しました。"));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(createResponse("ファイルの削除に失敗しました: " + e.getMessage()));
        }
    }

    
    /**
     * レスポンスメッセージを作成するメソッド
     * 
     * @param message メッセージ内容
     * @return メッセージを含むマップ
     */
    private Map<String, String> createResponse(String message) {
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return response;
    }
}
