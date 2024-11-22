package com.softtech.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
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
    private static final Logger logger = LoggerFactory.getLogger(AdjustmentRequestController.class);

    @Autowired
    private AdjustmentRequestService adjustmentRequestService;

    private final Path rootLocation = Paths.get("/Users/yangxiwen/Documents/work/templates");

    @GetMapping("/adjustmentList")
    public ModelAndView loadAdjustmentList() {
        ModelAndView mav = new ModelAndView("adjustmentList");
        int currentYear = java.time.LocalDate.now().getYear();  
        mav.addObject("currentYear", currentYear);
        mav.addObject("files", adjustmentRequestService.getCurrentYearFilesWithStatusOne(currentYear));
        mav.addObject("employees", adjustmentRequestService.getAllEmployeesWithAdjustmentDetail(currentYear)); 
        return mav;
    }


    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(@RequestParam("files") MultipartFile[] files, HttpSession session) {
        String employeeEmail = (String) session.getAttribute("userMailAdress");
        String employeeID = (String) session.getAttribute("loginUserID");
        if (employeeEmail == null || employeeID == null) {
            logger.warn("Authentication information not found");
            Map<String, String> response = new HashMap<>();
            response.put("message", "ログイン情報が見つかりません。再ログインしてください。");
            return ResponseEntity.badRequest().body(response);
        }

        if (files.length == 0) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "ファイルが選択されていません。");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            int currentYear = java.time.LocalDate.now().getYear();
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    String fileName = file.getOriginalFilename();
                    Date currentDate = new Date();

                    AdjustmentRequestFiles fileRecord = new AdjustmentRequestFiles();
                    fileRecord.setEmployeeID(employeeID);
                    fileRecord.setEmployeeEmail(employeeEmail);
                    fileRecord.setFileName(fileName);
                    fileRecord.setFileYear(currentYear);
                    fileRecord.setFileULStatus("1");
                    fileRecord.setFileInsertDate(currentDate);
                    fileRecord.setFileUpdateDate(currentDate);

                    Path destinationFile = rootLocation.resolve(Paths.get(fileName)).normalize().toAbsolutePath();
                    file.transferTo(destinationFile);
                    adjustmentRequestService.storeFile(file, employeeID, employeeEmail, currentYear);
                }
            }
            Map<String, String> response = new HashMap<>();
            response.put("message", "ファイルのアップロードに成功しました！");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("ファイルのアップロードに失敗しました: {}", e.getMessage());
            Map<String, String> response = new HashMap<>();
            response.put("message", "ファイルのアップロードに失敗しました: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }


    @GetMapping("/getAdjustmentData")
    @ResponseBody
    public Map<String, Object> getAdjustmentData() {
        int currentYear = java.time.LocalDate.now().getYear();
        Map<String, Object> data = new HashMap<>();
        data.put("requestFiles", adjustmentRequestService.getCurrentYearFilesWithStatusOne(currentYear));
        data.put("employees", adjustmentRequestService.getAllEmployeesWithAdjustmentDetail(currentYear));
        return data;
    }

    @GetMapping("/download/{fileName:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        logger.info("Downloading file: {}", fileName);
        Resource resource = adjustmentRequestService.loadFileAsResource(fileName);
        if (resource.exists() && resource.isReadable()) {
            logger.debug("File downloaded successfully");
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } else {
            logger.warn("File not found: {}", fileName);
            return ResponseEntity.notFound().build();
        }
    }
}
