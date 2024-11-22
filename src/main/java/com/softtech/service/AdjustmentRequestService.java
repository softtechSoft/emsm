package com.softtech.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.softtech.entity.AdjustmentRequestFiles;
import com.softtech.entity.Employee;
import com.softtech.mappers.AdjustmentDetailMapper;
import com.softtech.mappers.AdjustmentRequestFilesMapper;
import com.softtech.mappers.EmployeeMapper;

@Service
public class AdjustmentRequestService {
	
    @Autowired
    private AdjustmentRequestFilesMapper adjustmentRequestFilesMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private AdjustmentDetailMapper adjustmentDetailMapper;

    private final Path rootLocation = Paths.get("/Users/yangxiwen/Documents/work/templates");

    public void storeFile(MultipartFile file, String employeeID, String employeeEmail, int fileYear) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("アップロードされたファイルが空です。");
        }

        Path destinationFile = this.rootLocation.resolve(Paths.get(file.getOriginalFilename())).normalize();
        Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

        String fileName = destinationFile.getFileName().toString();

        Date currentDate = new Date();

        // 检查是否存在相同的记录
        List<AdjustmentRequestFiles> existingFiles = adjustmentRequestFilesMapper.selectByFileNameAndEmployeeIDAndYear(fileName, employeeID, fileYear);

        if (existingFiles != null && !existingFiles.isEmpty()) {
            // 更新已有记录
            AdjustmentRequestFiles existingFile = existingFiles.get(0);
            existingFile.setEmployeeEmail(employeeEmail);
            existingFile.setFileULStatus("1");
            existingFile.setFileUpdateDate(currentDate);
            adjustmentRequestFilesMapper.updateByEmployeeIDAndFileYearAndFileName(existingFile);

            // 删除其他重复的记录（如果有）
            for (int i = 1; i < existingFiles.size(); i++) {
                adjustmentRequestFilesMapper.deleteByFileID(existingFiles.get(i).getFileID());
            }
        } else {
            // 插入新记录
            try {
                AdjustmentRequestFiles adjustmentFile = new AdjustmentRequestFiles();
                adjustmentFile.setEmployeeID(employeeID);
                adjustmentFile.setEmployeeEmail(employeeEmail);
                adjustmentFile.setFileName(fileName);
                adjustmentFile.setFileYear(fileYear);
                adjustmentFile.setFileULStatus("1");
                adjustmentFile.setFileInsertDate(currentDate);
                adjustmentFile.setFileUpdateDate(currentDate);

                adjustmentRequestFilesMapper.insert(adjustmentFile);
            } catch (DuplicateKeyException e) {
                // 如果违反唯一约束，更新已有的记录
                AdjustmentRequestFiles existingFile = adjustmentRequestFilesMapper.selectByFileNameAndEmployeeIDAndYearSingle(fileName, employeeID, fileYear);
                if (existingFile != null) {
                    existingFile.setEmployeeEmail(employeeEmail);
                    existingFile.setFileULStatus("1");
                    existingFile.setFileUpdateDate(currentDate);
                    adjustmentRequestFilesMapper.updateByEmployeeIDAndFileYearAndFileName(existingFile);
                } else {
                    throw new RuntimeException("既存のファイルを更新できませんでした。");
                }
            }
        }
    }


    public List<AdjustmentRequestFiles> getCurrentYearFilesWithStatusOne(int currentYear) {
        return adjustmentRequestFilesMapper.findByYearAndStatus(currentYear, "1");
    }

    public List<Map<String, Object>> getAllEmployeesWithAdjustmentDetail(int currentYear) {
        return employeeMapper.queryEmployeeAndAdjustmentStatuses(currentYear);
    }

    public List<Employee> getAllEmployees() {
        return employeeMapper.findAll();
    }

    public String getAdjustmentStatusByEmployeeId(String employeeId) {
        return adjustmentDetailMapper.findByEmployeeId(employeeId).getAdjustmentStatus();
    }

    public void deleteFile(String fileName) throws IOException {
        Path file = this.rootLocation.resolve(fileName).normalize();
        Files.deleteIfExists(file);
        adjustmentRequestFilesMapper.deleteByFileName(fileName);
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.rootLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("ファイルが見つかりません: " + fileName);
            }
        } catch (Exception e) {
            throw new RuntimeException("ファイルのロードに失敗しました: " + fileName, e);
        }
    }

    public List<Map<String, Object>> getUploadedFiles() {
        return adjustmentRequestFilesMapper.selectByStatusMapped("1");
    }


}
