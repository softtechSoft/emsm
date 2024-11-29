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
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.softtech.entity.AdjustmentDetail;
import com.softtech.entity.AdjustmentFile;
import com.softtech.entity.Employee;
import com.softtech.mappers.AdjustmentDetailMapper;
import com.softtech.mappers.AdjustmentFileMapper;
import com.softtech.mappers.EmployeeMapper;

/**
 * 調整情報編集サービス
 */
@Service
public class AdjustmentInfoEditService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private AdjustmentFileMapper adjustmentFileMapper;

    @Autowired
    private AdjustmentDetailMapper adjustmentDetailMapper;

    @Value("${file.storage.location}")
    private String rootLocation;

    private Path getRootLocation() {
        return Paths.get(rootLocation);
    }

    /**
     * 社員IDで社員情報を取得する
     */
    public Employee getEmployeeById(String employeeId) {
        return employeeMapper.findById(employeeId);
    }

    /**
     * 指定したタイプ、社員、年度のファイルを取得する
     */
    public List<AdjustmentFile> getFilesByTypeEmployeeAndYear(String fileType, String employeeEmail, int year) {
        return adjustmentFileMapper.findFilesByTypeAndEmployee(fileType, employeeEmail, year);
    }

    /**
     * 指定した社員、年度のファイルを取得する
     */
    public List<AdjustmentFile> getFilesByEmployeeEmailAndYear(String employeeEmail, int year) {
        return adjustmentFileMapper.findFilesByEmployeeEmailAndYear(employeeEmail, year);
    }

    /**
     * 指定した社員の有ファイル年度一覧を取得する
     */
    public List<Integer> getYearsWithFiles(String employeeEmail) {
        List<AdjustmentFile> files = adjustmentFileMapper.findFilesByEmployeeEmail(employeeEmail);
        // ファイルの年度を取得し、重複を除外してリスト化
        Set<Integer> yearsSet = files.stream()
                .map(AdjustmentFile::getFileYear)
                .collect(Collectors.toSet());
        List<Integer> yearList = new ArrayList<>(yearsSet);
        // 年度を降順にソート
        Collections.sort(yearList, Collections.reverseOrder());
        return yearList;
    }

    /**
     * 結果ファイルをアップロードする
     */
    public void uploadResultFiles(MultipartFile[] files, String employeeId) throws IOException {
        Employee employee = getEmployeeById(employeeId);
        if (employee == null) {
            throw new IllegalArgumentException("指定された社員が存在しません。");
        }
        String employeeEmail = employee.getMailAdress();
        String employeeID = employee.getEmployeeID();
        int currentYear = LocalDate.now().getYear();
        saveFilesAndDetails(files, employeeEmail, employeeID, currentYear, "resultType");
    }

    /**
     * ファイルと詳細情報を保存する
     */
    private void saveFilesAndDetails(MultipartFile[] files, String employeeEmail, String employeeID, int fileYear,
            String fileType) throws IOException {
        // AdjustmentDetailレコードを検索または作成
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
            detail.setAdjustmentStatus("1:調整済み"); // 調整状態を更新

            Date currentDate = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            detail.setUpdateDate(currentDate);

            adjustmentDetailMapper.update(detail);
        }

        // 各ファイルと対応するレコードを保存
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                saveFile(file, employeeEmail, employeeID, fileYear, fileType);
            }
        }
    }

    /**
     * ファイルを保存する
     */
    private void saveFile(MultipartFile file, String employeeEmail, String employeeID, int year, String fileType)
            throws IOException {
        String originalFileName = file.getOriginalFilename();
        Path destinationFile = saveFileToDisk(file, year, employeeEmail);

        // 同名のファイルが存在するかチェック
        AdjustmentFile existingFile = adjustmentFileMapper.findByEmployeeIDAndYearAndFileName(employeeID, year,
                originalFileName);

        if (existingFile != null) {
            // 既存のレコードを更新
            existingFile.setFilePath(destinationFile.toString());
            existingFile.setFileType(fileType);
            adjustmentFileMapper.updateByEmployeeIDAndYearAndFileName(existingFile);
        } else {
            // 新しいレコードを挿入
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

    /**
     * ファイルをディスクに保存する
     */
    private Path saveFileToDisk(MultipartFile file, int year, String employeeEmail) throws IOException {
        Path yearDirectory = getRootLocation().resolve(Paths.get(String.valueOf(year), employeeEmail));
        Files.createDirectories(yearDirectory);
        Path destinationFile = yearDirectory.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
        return destinationFile;
    }

    /**
     * ファイルをリソースとしてロードする
     */
    public Resource loadFileAsResource(int fileYear, String employeeEmail, String filename) {
        try {
            Path file = getRootLocation().resolve(Paths.get(String.valueOf(fileYear), employeeEmail, filename));
            if (Files.exists(file)) {
                Resource resource = new UrlResource(file.toUri());
                return resource;
            } else {
                throw new RuntimeException("ファイルが見つかりません: " + filename);
            }
        } catch (Exception e) {
            throw new RuntimeException("ファイルのロードに失敗しました: " + filename, e);
        }
    }
}
