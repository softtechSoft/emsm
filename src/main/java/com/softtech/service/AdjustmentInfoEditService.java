package com.softtech.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.softtech.entity.AdjustmentDetail;
import com.softtech.entity.AdjustmentFile;
import com.softtech.entity.Employee;
import com.softtech.mappers.AdjustmentDetailMapper;
import com.softtech.mappers.AdjustmentFileMapper;
import com.softtech.mappers.EmployeeMapper;

@Service
public class AdjustmentInfoEditService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private AdjustmentFileMapper adjustmentFileMapper;

    @Autowired
    private AdjustmentDetailMapper adjustmentDetailMapper;

    public Employee getEmployeeById(String employeeId) {
        return employeeMapper.findById(employeeId);
    }

    public List<AdjustmentFile> getFilesByTypeEmployeeAndYear(String fileType, String employeeEmail, int year) {
        return adjustmentFileMapper.findFilesByTypeAndEmployee(fileType, employeeEmail, year);
    }

    public List<AdjustmentFile> getResultFilesByEmployeeAndYear(String employeeEmail, int year) {
        return adjustmentFileMapper.findFilesByTypeAndEmployee("resultType", employeeEmail, year);
    }

    public List<Integer> getPastYears(int numYears) {
        int currentYear = LocalDate.now().getYear();
        List<Integer> years = new ArrayList<>();
        for (int i = 0; i < numYears; i++) {
            years.add(currentYear - i);
        }
        return years;
    }

    public void saveFilesAndDetails(MultipartFile[] files, String employeeEmail, String employeeID, int fileYear, String fileType) throws IOException {
        // 查找或创建 AdjustmentDetail 记录
        AdjustmentDetail detail = adjustmentDetailMapper.findByEmployeeIdAndYear(employeeID, String.valueOf(fileYear));
        if (detail == null) {
            detail = new AdjustmentDetail();
            detail.setEmployeeID(employeeID);
            detail.setEmployeeEmail(employeeEmail);
            detail.setYear(String.valueOf(fileYear));
            detail.setUploadStatus("1:アップロード完了");
            detail.setAdjustmentStatus("1:調整済み"); 

            Date currentDate = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            detail.setInsertDate(currentDate);
            detail.setUpdateDate(currentDate);

            adjustmentDetailMapper.insert(detail);
        } else {
            detail.setAdjustmentStatus("1:調整済み");  // 更新调整状态为已调整

            Date currentDate = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            detail.setUpdateDate(currentDate);

            adjustmentDetailMapper.update(detail);
        }

        // 保存每个文件及其对应的记录
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                saveFile(file, employeeEmail, employeeID, fileYear, fileType);
            }
        }
    }

    private void saveFile(MultipartFile file, String employeeEmail, String employeeID, int year, String fileType) throws IOException {
        String originalFileName = file.getOriginalFilename();
        Path destinationFile = saveFileToDisk(file, year, employeeEmail);

        // 检查是否存在同名文件
        AdjustmentFile existingFile = adjustmentFileMapper.findByEmployeeIDAndYearAndFileName(employeeID, year, originalFileName);

        if (existingFile != null) {
            // 更新已有的记录
            existingFile.setFilePath(destinationFile.toString());
            existingFile.setFileType(fileType);
            adjustmentFileMapper.updateByEmployeeIDAndYearAndFileName(existingFile);
        } else {
            // 插入新记录
            AdjustmentFile adjustmentFile = new AdjustmentFile();
            adjustmentFile.setEmployeeID(employeeID);
            adjustmentFile.setEmployeeEmail(employeeEmail);
            adjustmentFile.setFileName(originalFileName);
            adjustmentFile.setFilePath(destinationFile.toString());
            adjustmentFile.setFileYear(year);
            adjustmentFile.setFileType(fileType);
            adjustmentFileMapper.insert(adjustmentFile);
        }
    }

    private Path saveFileToDisk(MultipartFile file, int year, String employeeEmail) throws IOException {
        Path yearDirectory = Paths.get("/Users/yangxiwen/Documents/work", String.valueOf(year), employeeEmail);
        Files.createDirectories(yearDirectory);
        Path destinationFile = yearDirectory.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
        return destinationFile;
    }
}
