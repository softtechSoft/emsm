package com.softtech.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
 * 年末調整申請サービス
 * 
 * このサービスクラスは、年末調整申請に関連するファイルのアップロード、保存、
 * ファイルの取得、および従業員の年末調整状態の管理を担当します。
 */
@Service
public class AdjustmentRequestService {

    @Autowired
    private AdjustmentRequestFilesMapper adjustmentRequestFilesMapper; // 年末調整申請ファイル情報を操作するマッパー

    @Autowired
    private EmployeeMapper employeeMapper; // 従業員情報を操作するマッパー

    @Value("${file.storage.location}")
    private String rootLocation; // ファイル保存のルートディレクトリパス

    @Value("${file.request.location}")
    private String requestFilesLocation; // リクエストファイル保存のディレクトリパス

    /**
     * テンプレートファイル格納先ディレクトリのパスを取得するメソッド
     * 
     * @return テンプレートファイル格納先ディレクトリのPathオブジェクト
     */
    // テンプレートファイル格納先ディレクトリを取得
    private Path getTemplatesLocation() {
        return Paths.get(rootLocation).toAbsolutePath().normalize().resolve(requestFilesLocation);
    }

    /**
     * ファイルをアップロードするメソッド
     * 
     * @param files    アップロードされたファイルの配列
     * @param fileYear 年度（現在年度等）
     * @throws IOException ファイルの保存中に発生する例外
     */
    /**
     * 年末調整申請ファイルをアップロードするメソッド
     * 
     * @param files    アップロードされたファイルの配列
     * @param fileYear 年度（現在年度等）
     * @throws IOException ファイルの保存中に発生する例外
     */
    public void uploadFiles(MultipartFile[] files, int fileYear) throws IOException {
        if (files == null || files.length == 0) {
            throw new IllegalArgumentException("ファイルが選択されていません。"); // ファイルが選択されていない場合の例外
        }

        Files.createDirectories(getTemplatesLocation()); // テンプレートファイル保存ディレクトリを作成

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                storeFile(file, fileYear); // 各ファイルを保存
            }
        }
    }

    /**
     * ファイルを保存し、AdjustmentRequestFilesテーブルに登録または更新するメソッド
     * 
     * @param file     保存するファイル
     * @param fileYear ファイルの年度
     * @throws IOException ファイルの保存中に発生する例外
     */
    /**
     * 年末調整申請ファイルを保存し、AdjustmentRequestFilesテーブルに登録または更新するメソッド
     * 
     * @param file     保存するファイル
     * @param fileYear ファイルの年度
     * @throws IOException ファイルの保存中に発生する例外
     */
    private void storeFile(MultipartFile file, int fileYear) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("アップロードされたファイルが空です。"); // ファイルが空の場合の例外
        }

        Path templatesLocation = getTemplatesLocation(); // テンプレートファイル保存ディレクトリのパスを取得
        Path destinationFile = templatesLocation.resolve(file.getOriginalFilename()).normalize(); // 保存先のファイルパスを設定
        Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING); // ファイルをコピー

        String fileName = destinationFile.getFileName().toString(); // ファイル名を取得

        // fileULStatusを'1'としてアップロード完了扱い
        List<AdjustmentRequestFiles> existingFiles = adjustmentRequestFilesMapper.selectByFileNameAndYear(fileName, fileYear);
        if (existingFiles != null && !existingFiles.isEmpty()) {
            // 既存ファイルの更新
            AdjustmentRequestFiles existingFile = existingFiles.get(0);
            existingFile.setFileULStatus("1"); // アップロードステータスを設定
            existingFile.setFilePath(destinationFile.toString()); // ファイルパスを更新
            adjustmentRequestFilesMapper.updateFileYearAndFileName(existingFile); // データベースを更新
        } else {
            // 新規ファイルの挿入
            AdjustmentRequestFiles newFile = new AdjustmentRequestFiles();
            newFile.setFileName(fileName); // ファイル名を設定
            newFile.setFileYear(fileYear); // ファイル年度を設定
            newFile.setFileULStatus("1"); // アップロードステータスを設定
            newFile.setFilePath(destinationFile.toString()); // ファイルパスを設定
            adjustmentRequestFilesMapper.insert(newFile); // データベースに挿入
        }
    }

    /**
     * 現在の年度のファイルを取得するメソッド（ステータスが1のもの）
     * 
     * @param currentYear 現在の年度
     * @return 現在の年度に対応する年末調整申請ファイルのリスト
     */
    /**
     * 現在の年度の年末調整申請ファイルを取得するメソッド（ステータスが1のもの）
     * 
     * @param currentYear 現在の年度
     * @return 現在の年度に対応する年末調整申請ファイルのリスト
     */
    public List<AdjustmentRequestFiles> getCurrentYearFilesWithStatusOne(int currentYear) {
        return adjustmentRequestFilesMapper.findByYearAndStatus(currentYear, "1"); // ステータスが"1"のファイルを取得
    }

    /**
     * 全ての社員と年末調整状態を取得するメソッド
     * 
     * @param currentYear 現在の年度
     * @return 全ての社員と年末調整状態を含むマップのリスト
     */
    /**
     * 全ての社員と年末調整状態を取得するメソッド
     * 
     * @param currentYear 現在の年度
     * @return 全ての社員と年末調整状態を含むマップのリスト
     */
    public List<java.util.Map<String, Object>> getAllEmployeesWithAdjustmentStatuses(int currentYear) {
        return employeeMapper.queryAllEmployeesWithAdjustmentStatuses(currentYear); // 全ての社員と年末調整状態を取得
    }

    /**
     * ファイルをリソースとしてロードするメソッド
     * 
     * @param fileName ダウンロードするファイルの名前
     * @return ダウンロード可能なリソース
     */
    /**
     * 年末調整申請ファイルをリソースとしてロードするメソッド
     * 
     * @param fileName ダウンロードするファイルの名前
     * @return ダウンロード可能なリソース
     */
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = getTemplatesLocation().resolve(fileName).normalize(); // ファイルパスを構築
            if (!Files.exists(filePath)) {
                throw new RuntimeException("ファイルが見つかりません: " + fileName); // ファイルが存在しない場合の例外
            }
            return new UrlResource(filePath.toUri()); // リソースとしてロード
        } catch (Exception e) {
            throw new RuntimeException("ファイルのロードに失敗しました: " + fileName, e); // ロード失敗時の例外
        }
    }
}
