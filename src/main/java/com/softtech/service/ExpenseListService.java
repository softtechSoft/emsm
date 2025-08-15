package com.softtech.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.softtech.entity.ExpenseListEntity;
import com.softtech.entity.SaveFolder;
import com.softtech.mappers.ExpenseListMapper;
import com.softtech.util.DataUtil;

/**
 * 経費リストを管理するサービスクラス。
 * 経費の検索、取得、削除、更新、挿入の機能を提供する。
 */
@Service
public class ExpenseListService {

    @Autowired
    private ExpenseListMapper expenseListMapper;
    @Autowired
	private SaveFolderService saveFolderService;

    @Value("${file.receipt.location}")
    private String configuredReceiptLocation;

    private Path receiptFolderPath;

    public Path getReceiptFolderPath() {
        return this.receiptFolderPath;
    }


    /**
    * アプリケーション起動時に領収書保存用ディレクトリの初期設定を行う
    *
    * @throws RuntimeException ディレクトリ作成に失敗した場合
    * @details
    * - 設定ファイルから保存先パスを読み込み
    * - 相対パスの場合は絶対パスに変換
    * - 必要なディレクトリ構造を作成
    */
    @PostConstruct
    public void init() {
       try {

    	    SaveFolder saveFolder = saveFolderService.findFileTypeCode("01");
    	    String baseDir = saveFolder.getSaveFolder();

           // 設定ファイルのパスを正規化
           Path path = Paths.get(baseDir).normalize();

           // 相対パスの場合は実行ディレクトリを基準に解決
           if (!path.isAbsolute()) {
               String userDir = System.getProperty("user.dir");
               path = Paths.get(userDir).resolve(path).normalize();
           }

           // 保存用ディレクトリの作成
           Files.createDirectories(path);

           // 解決されたパスをフィールドに保持
           this.receiptFolderPath = path;

       } catch (IOException e) {
           throw new RuntimeException("ファイル保存用ディレクトリ作成失敗: " + e.getMessage(), e);
       }
    }

    /**
     * 指定された年と月に基づいて経費リストを取得する。
     *
     * @param year  年度
     * @param month 月度
     * @return 経費リスト
     */
    public List<ExpenseListEntity> findExpensesByYearMonth(int year, int month) {
//        return expenseListMapper.findByYearMonth(year, month);
    	List<ExpenseListEntity> expList = expenseListMapper.findByYearMonth(year, month);;
    	for(ExpenseListEntity exp : expList) {
            // 如果需要提取文件名，处理 receiptPath
            if(exp.getReceiptPath() != null && !exp.getReceiptPath().isEmpty()) {
                String fileName = extractFileName(exp.getReceiptPath());
                exp.setReceiptPath(fileName);
            }
        }

        return expList;
    }
    /**
     * 从完整路径中提取文件名
     */
    private String extractFileName(String fullPath) {
        return Paths.get(fullPath).getFileName().toString();
    }

    /**
     * 指定された経費IDに基づいて経費を取得する。
     *
     * @param expensesID 経費ID
     * @return 経費エンティティ
     */
    public ExpenseListEntity findById(String expensesID) {
        return expenseListMapper.findById(expensesID);
    }

    /**
     * 指定された経費IDに基づいて経費を論理削除する。
     *
     * @param expensesID 削除対象の経費ID
     */
    @Transactional
    public void deleteExpense(String expensesID) {
        expenseListMapper.deleteById(expensesID);
    }

    /**
     * 指定された経費エンティティを更新する。
     *
     * @param expense 更新する経費エンティティ
     */
    @Transactional
    public void updateExpense(ExpenseListEntity expense) {
        expenseListMapper.update(expense);
    }

    /**
     * 新しい経費エンティティを挿入する。
     *
     * @param expense 挿入する経費エンティティ
     */
    @Transactional
    public void insertExpense(ExpenseListEntity expense) {
        expenseListMapper.insert(expense);
    }

    /**
    * 領収書ファイルの保存と経費データの更新を行う
    *
    * @param expensesID 経費ID
    * @param file アップロードされたファイル
    * @throws RuntimeException ファイル保存に失敗した場合
    * @details
    * - ファイル名はUUIDで一意に生成
    * - ファイルは設定された保存ディレクトリに保存
    * - データベースには相対パス(ems_files/receipt/xxx)で保存
    */
    @Transactional
    public void saveReceiptFile(String expensesID, MultipartFile file) {
       if (file == null || file.isEmpty()) {
           return;
       }

       // 対象の経費データを取得
       ExpenseListEntity expense = expenseListMapper.findById(expensesID);
       if (expense == null) {
           return;
       }

       // 保存用ファイル名の生成
       String originalName = file.getOriginalFilename();
       String ext = "";
       if (originalName != null && originalName.contains(".")) {
           ext = originalName.substring(originalName.lastIndexOf("."));
       }
       String newFileName = UUID.randomUUID().toString() + ext;

       // 保存ディレクトリの確認
       File folder = receiptFolderPath.toFile();
       if (!folder.exists()) {
           folder.mkdirs();
       }

       // ファイルの保存処理
       File dest = new File(folder, newFileName);
       try {
           file.transferTo(dest);
       } catch (IOException e) {
           e.printStackTrace();
           throw new RuntimeException("ファイルの保存に失敗しました。", e);
       }

       // 相対パスをデータベースに保存
       expense.setReceiptPath("ems_files/receipt/" + newFileName);
       expenseListMapper.update(expense);
    }

    /**
    * 領収書画像を保存し、相対パスを返却する
    *
    * @param file アップロードされたファイル
    * @return 保存された画像の相対パス（ems_files/receipt/ファイル名）、ファイルが無い場合はnull
    * @throws IOException ファイル保存処理で例外が発生した場合
    * @details
    * - ファイル名はUUIDで一意に生成
    * - 拡張子は元のファイル名から取得
    * - 戻り値は常に相対パス形式
    */
    public String saveAndReturnReceiptPath(MultipartFile file) throws IOException {
       // 入力チェック
       if (file == null || file.isEmpty()) {
           return null;
       }

       // 保存用ファイル名の生成
       String originalName = file.getOriginalFilename();
       String ext = "";
       if (originalName != null && originalName.lastIndexOf('.') != -1) {
           ext = originalName.substring(originalName.lastIndexOf('.'));
       }
       String newFileName = UUID.randomUUID().toString() + ext;

       // ファイルの保存
       File dest = new File(receiptFolderPath.toFile(), newFileName);
       file.transferTo(dest);

       // 相対パスを返却
       return "ems_files/receipt/" + newFileName;
    }

    /**
     * 最大値
     *
     * @param
     */
    public String getMaxExpensesID() {

		String maxExpensesID = expenseListMapper.getMaxExpensesID();
//      return nextEmployeeID;
		String nextExpensesID = "EX0001";
        if (maxExpensesID != null) {
        	maxExpensesID = maxExpensesID.toUpperCase();
        	nextExpensesID = DataUtil.getNextID(maxExpensesID, 2);
        }

        return nextExpensesID != null ? nextExpensesID.toUpperCase() : null;


	}

}
