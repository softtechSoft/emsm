package com.softtech.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.softtech.entity.AdjustmentRequestFiles;
import com.softtech.mappers.AdjustmentRequestFilesMapper;
import com.softtech.mappers.EmployeeMapper;

/**
 * 調整申請サービス
 */
@Service
public class AdjustmentRequestService {

    @Autowired
    private AdjustmentRequestFilesMapper adjustmentRequestFilesMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Value("${file.storage.location}")
    private String rootLocation;

    private Path getRootLocation() {
        return Paths.get(rootLocation);
    }

    /**
     * ファイルをアップロードする
     */
    public void uploadFiles(MultipartFile[] files, String employeeID, String employeeEmail) throws IOException {
        if (files == null || files.length == 0) {
            throw new IllegalArgumentException("ファイルが選択されていません。");
        }
        int currentYear = java.time.LocalDate.now().getYear();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                storeFile(file, employeeID, employeeEmail, currentYear);
            }
        }
    }

    /**
     * ファイルを保存する
     */
    private void storeFile(MultipartFile file, String employeeID, String employeeEmail, int fileYear)
            throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("アップロードされたファイルが空です。");
        }

        Path destinationFile = this.getRootLocation().resolve(Paths.get(file.getOriginalFilename())).normalize();
        Files.createDirectories(destinationFile.getParent());
        Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

        String fileName = destinationFile.getFileName().toString();

        Date currentDate = new Date();

        // 既存のレコードをチェックする
        List<AdjustmentRequestFiles> existingFiles = adjustmentRequestFilesMapper
                .selectByFileNameAndEmployeeIDAndYear(fileName, employeeID, fileYear);

        if (existingFiles != null && !existingFiles.isEmpty()) {
            // 既存のレコードを更新する
            AdjustmentRequestFiles existingFile = existingFiles.get(0);
            existingFile.setEmployeeEmail(employeeEmail);
            existingFile.setFileULStatus("1");
            existingFile.setFileUpdateDate(currentDate);
            adjustmentRequestFilesMapper.updateByEmployeeIDAndFileYearAndFileName(existingFile);

            // 重複するレコードを削除する（もしあれば）
            for (int i = 1; i < existingFiles.size(); i++) {
                adjustmentRequestFilesMapper.deleteByFileID(existingFiles.get(i).getFileID());
            }
        } else {
            // 新しいレコードを挿入する
            AdjustmentRequestFiles adjustmentFile = new AdjustmentRequestFiles();
            adjustmentFile.setEmployeeID(employeeID);
            adjustmentFile.setEmployeeEmail(employeeEmail);
            adjustmentFile.setFileName(fileName);
            adjustmentFile.setFileYear(fileYear);
            adjustmentFile.setFileULStatus("1");
            adjustmentFile.setFileInsertDate(currentDate);
            adjustmentFile.setFileUpdateDate(currentDate);

            adjustmentRequestFilesMapper.insert(adjustmentFile);
        }
    }

    /**
     * 現在の年度のファイルを取得する（ステータスが1のもの）
     */
    public List<AdjustmentRequestFiles> getCurrentYearFilesWithStatusOne(int currentYear) {
        return adjustmentRequestFilesMapper.findByYearAndStatus(currentYear, "1");
    }

    /**
     * 全ての社員と調整状態を取得する
     */
    public List<java.util.Map<String, Object>> getAllEmployeesWithAdjustmentStatuses(int currentYear) {
        return employeeMapper.queryAllEmployeesWithAdjustmentStatuses(currentYear);
    }

    /**
     * ファイルをリソースとしてロードする
     */
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.getRootLocation().resolve(fileName).normalize();
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
}
