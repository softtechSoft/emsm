package com.softtech.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.softtech.service.BankAccountService;

@RestController
@RequestMapping("/bankAccount")
@CrossOrigin(origins = "http://localhost:3000")
public class BankAccountController {
    
    @Autowired
    private BankAccountService service;

    @GetMapping("/search")
    public ResponseEntity<?> searchAccounts(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        
        Map<String, Object> result = service.searchAccounts(startDate, endDate);
        return ResponseEntity.ok(result);
    }
    
    @PostMapping("/updateRemarks")
    public ResponseEntity<?> updateRemarks(@RequestBody Map<String, Object> request) {
        Long id = Long.parseLong(request.get("id").toString());
        String remarks = request.get("remarks").toString();
        
        service.updateRemarks(id, remarks);
        
        Map<String, Boolean> response = new HashMap<>();
        response.put("success", true);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "ファイルが選択されていません"));
            }
            String filename = file.getOriginalFilename();
            if (filename == null || !filename.toLowerCase().endsWith(".csv")) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "CSVファイルのみアップロード可能です"));
            }

            Map<String, Object> res = service.uploadCsvFile(file);
            res.put("success", true);
            res.put("message", "ファイルのアップロードが完了しました");
            return ResponseEntity.ok(res);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "ファイルのアップロードに失敗しました: " + e.getMessage()));
        }
    }
}