package com.softtech.controller;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.softtech.entity.AdjustmentRequestFiles;
import com.softtech.service.AdjustmentRequestService;

@Controller
public class AdjustmentRequestController {
    @Autowired
    private AdjustmentRequestService adjustmentRequestService;

    private final Path rootLocation = Paths.get("/Users/yangxiwen/Documents/work/templates");

    @GetMapping("/adjustmentList")
    public ModelAndView loadAdjustmentList() {
        ModelAndView mav = new ModelAndView("adjustmentList");
        mav.addObject("currentYear", java.time.LocalDate.now().getYear());
        mav.addObject("files", adjustmentRequestService.getAllFiles());
        mav.addObject("employees", adjustmentRequestService.getAllEmployees());
        return mav;
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(@RequestParam("files") MultipartFile[] files, HttpSession session) {
        String employeeEmail = (String) session.getAttribute("userMailAdress"); 
        String employeeID = (String) session.getAttribute("loginUserID");
        if (employeeEmail == null || employeeID == null) {
            return ResponseEntity.badRequest().body("ログイン情報が見つかりません。再ログインしてください。");
        }

        if (files.length == 0) {
            return ResponseEntity.badRequest().body("ファイルが選択されていません。");
        }

        try {
            int currentYear = java.time.LocalDate.now().getYear(); // 現在の年を取得
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    String fileName = file.getOriginalFilename();
                    Date currentDate = new Date();

                    AdjustmentRequestFiles fileRecord = new AdjustmentRequestFiles();
                    fileRecord.setEmployeeID(employeeID);
                    fileRecord.setEmployeeEmail(employeeEmail);
                    fileRecord.setFileName(fileName);
                    fileRecord.setFileYear(currentYear); // 現在の年を設定
                    fileRecord.setFileULStatus("1");
                    fileRecord.setFileInsertDate(currentDate);
                    fileRecord.setFileUpdateDate(currentDate);

                    // サーバーにファイルを保存
                    Path destinationFile = rootLocation.resolve(Paths.get(fileName)).normalize().toAbsolutePath();
                    file.transferTo(destinationFile);

                    // データベースにファイル情報を挿入
                    adjustmentRequestService.storeFile(file, employeeID, employeeEmail, currentYear);
                }
            }
            return ResponseEntity.ok("ファイルのアップロードに成功しました！");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ファイルのアップロードに失敗しました: " + e.getMessage());
        }
    }

    @GetMapping("/download/{fileName:.+}")
    @ResponseBody
    public ResponseEntity<?> serveFile(@PathVariable String fileName) {
        try {
            Path file = rootLocation.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().body("URL形式が正しくありません。");
        }
    }
}
