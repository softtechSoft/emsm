package com.softtech.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
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

@Controller
@RequestMapping("/adjustmentInfoEdit")
public class AdjustmentInfoEditController {

    @Autowired
    private AdjustmentInfoEditService adjustmentInfoEditService;

    @GetMapping
    public String showAdjustmentInfoEdit(@RequestParam("employeeId") String employeeId, Model model) {
        int currentYear = LocalDate.now().getYear();
        Employee employee = adjustmentInfoEditService.getEmployeeById(employeeId);
        model.addAttribute("currentYear", currentYear);
        model.addAttribute("employeeName", employee.getEmployeeName());
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("employeeEmail", employee.getMailAdress());

        // 获取 detailType 的文件
        List<AdjustmentFile> detailFiles = adjustmentInfoEditService.getFilesByTypeEmployeeAndYear("detailType", employee.getMailAdress(), currentYear);
        model.addAttribute("detailFiles", detailFiles);

        // 年份列表
        List<Integer> yearList = adjustmentInfoEditService.getPastYears(10);
        model.addAttribute("yearList", yearList);

        return "adjustmentInfoEdit";
    }

    @GetMapping("/getPastFiles")
    @ResponseBody
    public Map<String, Object> getPastFiles(@RequestParam("employeeId") String employeeId, @RequestParam("year") int year) {
        Employee employee = adjustmentInfoEditService.getEmployeeById(employeeId);
        List<AdjustmentFile> files = adjustmentInfoEditService.getFilesByEmployeeAndYear(employee.getMailAdress(), year);
        Map<String, Object> response = new HashMap<>();
        response.put("files", files);
        return response;
    }

    @PostMapping("/uploadResultFiles")
    @ResponseBody
    public ResponseEntity<?> uploadResultFiles(@RequestParam("files") MultipartFile[] files,
                                               @RequestParam("employeeId") String employeeId) {
        try {
            Employee employee = adjustmentInfoEditService.getEmployeeById(employeeId);
            int currentYear = LocalDate.now().getYear();
            adjustmentInfoEditService.saveFilesAndDetails(files, employee.getMailAdress(), employee.getEmployeeID(), currentYear, "resultType");
            Map<String, String> response = new HashMap<>();
            response.put("message", "ファイルのアップロードに成功しました！");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "ファイルのアップロードに失敗しました: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/download/{fileYear}/{employeeEmail}/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileYear") int fileYear,
                                                 @PathVariable("employeeEmail") String employeeEmail,
                                                 @PathVariable("filename") String filename) {
        try {
            Path file = Paths.get("/Users/yangxiwen/Documents/work", String.valueOf(fileYear), employeeEmail, filename);
            if (Files.exists(file)) {
                Resource resource = new UrlResource(file.toUri());
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
