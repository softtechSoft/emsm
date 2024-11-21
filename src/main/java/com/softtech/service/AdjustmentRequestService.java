package com.softtech.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    // ファイルを保存するメソッド
    public void storeFile(MultipartFile file, String employeeID, String employeeEmail, int fileYear) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("アップロードされたファイルが空です。");
        }

        // ディレクトリが存在しない場合、作成する
        if (!Files.exists(rootLocation)) {
            Files.createDirectories(rootLocation);
        }

        Path destinationFile = this.rootLocation.resolve(Paths.get(file.getOriginalFilename())).normalize();
        // ファイルを保存（既存ファイルを上書きする）
        Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

        // データベース用のファイル情報を設定
        AdjustmentRequestFiles adjustmentFile = new AdjustmentRequestFiles();
        adjustmentFile.setEmployeeID(employeeID);
        adjustmentFile.setEmployeeEmail(employeeEmail);
        adjustmentFile.setFileName(destinationFile.getFileName().toString());
        adjustmentFile.setFileYear(fileYear); // 現在の年を設定
        adjustmentFile.setFileULStatus("1"); // アップロード済み
        adjustmentFile.setFileInsertDate(new java.util.Date());
        adjustmentFile.setFileUpdateDate(new java.util.Date());

        // データベースに挿入
        adjustmentRequestFilesMapper.insert(adjustmentFile);
    }

    // アップロード済みファイルを取得するメソッド
    public List<AdjustmentRequestFiles> getAllFiles() {
        return adjustmentRequestFilesMapper.selectByStatus("1");
    }

    // すべての従業員データを取得するメソッド
    public List<Employee> getAllEmployees() {
        return employeeMapper.findAll();
    }

    // 指定された従業員IDの詳細を取得するメソッド
    public Employee getEmployeeDetails(String employeeID) {
        return employeeMapper.findById(employeeID);
    }

    // 従業員IDに基づく調整状況を取得するメソッド
    public String getAdjustmentStatusByEmployeeId(String employeeId) {
        return adjustmentDetailMapper.findByEmployeeId(employeeId).getAdjustmentStatus();
    }

    // ファイル削除メソッド
    public void deleteFile(String fileName) throws IOException {
        Path file = this.rootLocation.resolve(fileName).normalize();
        Files.deleteIfExists(file);
        adjustmentRequestFilesMapper.deleteByFileName(fileName);
    }
}
