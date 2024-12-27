package com.softtech.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

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
 * 年末調整情報編集サービス
 * 
 * このサービスクラスは、従業員の年末調整情報の取得、ファイルのアップロード・保存、
 * ファイルのダウンロード、および年末調整の確定処理を担当します。
 */
@Service
public class AdjustmentInfoEditService {

    @Autowired
    private EmployeeMapper employeeMapper; // 従業員情報を操作するマッパー

    @Autowired
    private AdjustmentFileMapper adjustmentFileMapper; // 年末調整ファイル情報を操作するマッパー

    @Autowired
    private AdjustmentDetailMapper adjustmentDetailMapper; // 年末調整詳細情報を操作するマッパー

    @Value("${file.storage.location}")
    private String rootLocation; // ファイル保存のルートディレクトリパス
    
    @Value("${file.request.location}")
    private String requestFilesLocation; // リクエストファイル保存のディレクトリパス

    /**
     * 初期化メソッド
     * 
     * アプリケーション起動時に呼び出され、必要なディレクトリを作成します。
     */
    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(getRootLocation().resolve(requestFilesLocation)); // リクエストファイル保存ディレクトリの作成
        } catch (IOException e) {
            throw new RuntimeException("ストレージディレクトリの作成に失敗しました！", e);
        }
    }

    /**
     * ルートディレクトリのパスを取得するメソッド
     * 
     * @return ルートディレクトリのPathオブジェクト
     */
    private Path getRootLocation() {
        return Paths.get(rootLocation).toAbsolutePath().normalize(); // ルートディレクトリの絶対パスを取得
    }

    /**
     * 指定した従業員IDで従業員情報を取得するメソッド
     * 
     * @param employeeId 取得対象の従業員ID
     * @return 指定された従業員IDに対応する従業員情報
     */
    public Employee getEmployeeById(String employeeId) {
        return employeeMapper.findById(employeeId); // 従業員情報を取得
    }

    /**
     * 指定したタイプ、従業員、年度のファイルを取得するメソッド
     * 
     * @param fileType   ファイルの種類（例: "resultType", "detailType"）
     * @param employeeID 従業員のID
     * @param year       ファイルの年度
     * @return 指定された条件に一致する年末調整ファイルのリスト
     */
    public List<AdjustmentFile> getFilesByTypeEmployeeAndYear(String fileType, String employeeID, int year) {
        return adjustmentFileMapper.findFilesByTypeAndEmployee(fileType, employeeID, year); // ファイルを取得
    }

    /**
     * 結果ファイルをアップロードするメソッド
     * 
     * @param files      アップロードされたファイルの配列
     * @param employeeId アップロード対象の従業員ID
     * @throws IOException ファイルの保存中に発生する例外
     */
    public void uploadResultFiles(MultipartFile[] files, String employeeId) throws IOException {
        Employee employee = getEmployeeById(employeeId); // 従業員情報を取得
        if (employee == null) {
            throw new IllegalArgumentException("指定された社員が存在しません。"); // 従業員が存在しない場合は例外を投げる
        }
        String employeeEmail = employee.getMailAdress(); // 従業員のメールアドレスを取得
        String employeeID = employee.getEmployeeID(); // 従業員のIDを取得
        int currentYear = LocalDate.now().getYear(); // 現在の年度を取得
        saveFilesAndDetails(files, employeeEmail, employeeID, currentYear, "resultType"); // ファイルと詳細情報を保存
    }

    /**
     * ファイルと詳細情報を保存するメソッド
     * 
     * @param files         保存するファイルの配列
     * @param employeeEmail 従業員のメールアドレス
     * @param employeeID    従業員のID
     * @param fileYear      ファイルの年度
     * @param fileType      ファイルの種類
     * @throws IOException ファイルの保存中に発生する例外
     */
    private void saveFilesAndDetails(MultipartFile[] files, String employeeEmail, String employeeID, int fileYear,
            String fileType) throws IOException {
        // AdjustmentDetailレコードを検索または作成
        AdjustmentDetail detail = adjustmentDetailMapper.findByEmployeeIdAndYear(employeeID, String.valueOf(fileYear));
        if (detail == null) {
            detail = new AdjustmentDetail(); // 新規作成
            detail.setEmployeeID(employeeID);
            detail.setEmployeeEmail(employeeEmail);
            detail.setYear(String.valueOf(fileYear));
            detail.setUploadStatus("1"); // アップロードステータスを設定
            detail.setAdjustmentStatus("0"); // 調整ステータスを設定

            // 現在の日付を取得して設定
            Date currentDate = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            detail.setInsertDate(currentDate);
            detail.setUpdateDate(currentDate);

            adjustmentDetailMapper.insert(detail); // 新規レコードを挿入
        } else {
            detail.setAdjustmentStatus("0"); // 調整ステータスを更新

            // 現在の日付を取得して更新
            Date currentDate = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            detail.setUpdateDate(currentDate);

            adjustmentDetailMapper.update(detail); // 既存レコードを更新
        }

        // 各ファイルと対応するレコードを保存
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                saveFile(file, employeeID, fileYear, fileType); // ファイルを保存
            }
        }
    }

    /**
     * ファイルを保存するメソッド
     * 
     * @param file       保存するファイル
     * @param employeeID 従業員のID
     * @param year       ファイルの年度
     * @param fileType   ファイルの種類
     * @throws IOException ファイルの保存中に発生する例外
     */
    private void saveFile(MultipartFile file, String employeeID, int year, String fileType)
            throws IOException {
        String originalFileName = file.getOriginalFilename(); // 元のファイル名を取得
        Path destinationFile = saveFileToDisk(file, year, employeeID, fileType); // ファイルをディスクに保存

        // 同名のファイルが存在するかチェック
        AdjustmentFile existingFile = adjustmentFileMapper.findByEmployeeIDAndYearAndFileName(employeeID, year,
                originalFileName, fileType);

        if (existingFile != null) {
            // 既存のレコードを更新
            existingFile.setFilePath(destinationFile.toString());
            existingFile.setFileStatus("0"); // ファイルステータスを設定
            adjustmentFileMapper.updateByEmployeeIDAndYearAndFileName(existingFile); // レコードを更新
        } else {
            // 新しいレコードを挿入
            AdjustmentFile adjustmentFile = new AdjustmentFile();
            adjustmentFile.setEmployeeID(employeeID);
            adjustmentFile.setFileName(originalFileName);
            adjustmentFile.setFilePath(destinationFile.toString());
            adjustmentFile.setFileYear(year);
            adjustmentFile.setFileType(fileType);
            adjustmentFile.setFileStatus("0"); // ファイルステータスを設定
            adjustmentFileMapper.insert(adjustmentFile); // レコードを挿入
        }
    }

    /**
     * ファイルをディスクに保存するメソッド
     * 
     * @param file       保存するファイル
     * @param year       ファイルの年度
     * @param employeeID 従業員のID
     * @param fileType   ファイルの種類
     * @return 保存先のPathオブジェクト
     * @throws IOException ファイルの保存中に発生する例外
     */
    private Path saveFileToDisk(MultipartFile file, int year, String employeeID, String fileType) throws IOException {
        // ファイルを保存するディレクトリパスを取得
        Path fileTypeDirectory = getRootLocation().resolve(Paths.get(String.valueOf(year), employeeID, fileType));
        Files.createDirectories(fileTypeDirectory); // 必要なディレクトリを作成
        Path destinationFile = fileTypeDirectory.resolve(file.getOriginalFilename()); // 保存先のファイルパスを設定
        Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING); // ファイルをコピー
        return destinationFile; // 保存先のPathを返す
    }

    /**
     * ファイルをリソースとしてロードするメソッド
     * 
     * @param fileType   ファイルの種類（例: "resultType", "detailType"）
     * @param fileYear   ファイルの年度
     * @param employeeId 従業員のID
     * @param filename   ダウンロードするファイル名
     * @return ダウンロード可能なリソース
     */
    public Resource loadFileAsResource(String fileType, int fileYear, String employeeId, String filename) {
        try {
            // DBからファイル情報を取得
            AdjustmentFile fileRecord = adjustmentFileMapper.findByEmployeeIDAndYearAndFileName(employeeId, fileYear, filename, fileType);
            if (fileRecord == null) {
                throw new RuntimeException("ファイル情報がデータベースに存在しません: " + filename);
            }

            // DBに保存されているfilePathを使用
            Path filePath = Paths.get(fileRecord.getFilePath());
            if (!Files.exists(filePath)) {
                throw new RuntimeException("実ファイルが見つかりません: " + filename + " パス: " + filePath);
            }

            Resource resource = new UrlResource(filePath.toUri());
            return resource;

        } catch (Exception e) {
            throw new RuntimeException("ファイルのロードに失敗しました: " + filename, e);
        }
    }
    
    /**
     * AdjustmentDetailを取得するメソッド
     * 
     * @param employeeId 従業員ID
     * @param year       年度
     * @return AdjustmentDetailオブジェクト
     */
    public AdjustmentDetail getAdjustmentDetailByEmployeeIdAndYear(String employeeId, String year) {
        return adjustmentDetailMapper.findByEmployeeIdAndYear(employeeId, year);
    }

    /**
     * 年末調整を確定するメソッド
     */
    public void finalizeAdjustment(String employeeId) {
        Employee employee = getEmployeeById(employeeId); // 従業員情報を取得
        if (employee == null) {
            throw new RuntimeException("指定された社員が存在しません。"); // 従業員が存在しない場合は例外を投げる
        }
        String employeeID = employee.getEmployeeID(); // 従業員のIDを取得
        int currentYear = java.time.LocalDate.now().getYear(); // 現在の年度を取得

        // 現在の年度でAdjustmentDetailを検索
        AdjustmentDetail detail = adjustmentDetailMapper.findByEmployeeIdAndYear(employeeID,
                String.valueOf(currentYear));
        if (detail == null) {
            // AdjustmentDetailが存在しない場合、新規作成
            detail = new AdjustmentDetail();
            detail.setEmployeeID(employeeID);
            detail.setEmployeeEmail(employee.getMailAdress());
            detail.setYear(String.valueOf(currentYear));
            detail.setUploadStatus("0"); // アップロードステータスを設定（確定後はアップロード不可）
            detail.setAdjustmentStatus("1"); // 調整ステータスを確定
            detail.setInsertDate(new Date()); // 挿入日時を設定
            detail.setUpdateDate(new Date()); // 更新日時を設定
            adjustmentDetailMapper.insert(detail); // 新規レコードを挿入
        } else {
            // AdjustmentDetailが存在する場合、調整ステータスを確定
            detail.setAdjustmentStatus("1"); // 調整ステータスを確定
            detail.setUpdateDate(new Date()); // 更新日時を設定
            adjustmentDetailMapper.update(detail); // 既存レコードを更新
        }

        // 調整確定後、ファイルの削除を無効化するためにファイルステータスを確定済みに更新
        adjustmentFileMapper.updateFileStatusByEmployeeIDAndYear(Map.of(
            "employeeID", employeeID,
            "fileYear", currentYear,
            "fileType", "resultType",
            "newStatus", "1" // "1" は確定済み
        ));
    }

    /**
     * 指定されたファイルを削除するメソッド
     */
    public void deleteFile(String employeeID, int fileYear, String fileName, String fileType) {
        // 1) AdjustmentDetailが確定済みか確認
        AdjustmentDetail detail = adjustmentDetailMapper.findByEmployeeIdAndYear(employeeID, String.valueOf(fileYear));
        if (detail != null && "1".equals(detail.getAdjustmentStatus())) {
            throw new RuntimeException("調整が既に確定されているため、ファイルを削除できません。");
        }

        // 2) DBにファイルがあるかチェック
        AdjustmentFile fileRecord = adjustmentFileMapper.findByEmployeeIDAndYearAndFileName(employeeID, fileYear, fileName, fileType);
        if (fileRecord == null) {
            throw new RuntimeException("削除対象のファイルが見つかりません: " + fileName);
        }

        // 3) DBから削除
        int affected = adjustmentFileMapper.deleteFile(employeeID, fileYear, fileName, fileType);
        if (affected == 0) {
            throw new RuntimeException("ファイルレコードを削除できませんでした: " + fileName);
        }

        // 4) 物理ファイルを削除
        Path filePath = Paths.get(fileRecord.getFilePath());
        try {
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }
        } catch (IOException e) {
            throw new RuntimeException("物理ファイルの削除に失敗しました: " + filePath, e);
        }
    }



}
