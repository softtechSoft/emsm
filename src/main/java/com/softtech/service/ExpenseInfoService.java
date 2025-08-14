package com.softtech.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.softtech.entity.ExpenseListEntity;
import com.softtech.entity.SaveFolder;

/**
 * 複数の経費エンティティを一括でデータベースに挿入するサービス
 */
@Service
public class ExpenseInfoService {

    @Autowired
    private ExpenseListService expenseListService;
    @Autowired
	private SaveFolderService saveFolderService;

    @Value("${file.receipt.location}")
    private String configuredReceiptLocation;

    private Path receiptFolderPath;


    @PostConstruct
    public void init() {
    	SaveFolder saveFolder = saveFolderService.findFileTypeCode("01");
    	String baseDir = saveFolder.getSaveFolder();


        Path path = Paths.get(baseDir).normalize();
        if (!path.isAbsolute()) {
            String userDir = System.getProperty("user.dir");
            path = Paths.get(userDir).resolve(path).normalize();
        }
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new RuntimeException("ファイル保存用ディレクトリを作成できません: " + path, e);
        }
        this.receiptFolderPath = path;

        System.out.println("=== Receipt Folder Absolute Path ===");
        System.out.println("receiptFolderPath = " + this.receiptFolderPath);
    }

    /**
     * 経費情報とレシート画像を同時に保存する
     *
     * @param expense     ExpenseListEntity
     * @param receiptFile 上传的图片文件 (可选)
     */
    @Transactional
    public void addExpenseWithReceipt(ExpenseListEntity expense, MultipartFile receiptFile) throws IOException {
        if (receiptFile != null && !receiptFile.isEmpty()) {

            String originalName = receiptFile.getOriginalFilename();
            String maxId = expenseListService.getMaxExpensesID();
            String newFileName = maxId + "_" + originalName;


            SaveFolder saveFolder = saveFolderService.findFileTypeCode("01");
            String baseDir = saveFolder.getSaveFolder();
            LocalDate acDate = expense.getAccrualDate();
            String acc = acDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));


            String dateFolderPath = baseDir + File.separator + acc;
            File dateFolder = new File(dateFolderPath);


            if (!dateFolder.exists()) {
                if (dateFolder.mkdirs()) {
                    System.out.println("新規フォルダ成功：" + dateFolderPath);
                } else {
                    System.out.println("新規フォルダ成失敗：" + dateFolderPath);
                    throw new IOException("新規フォルダできない：" + dateFolderPath);
                }
            } else {
                System.out.println("日期文件夹已存在：" + dateFolderPath);
            }


            String fullFilePath = dateFolderPath + File.separator + newFileName;


            Path destPath = Paths.get(fullFilePath);

            try {

                receiptFile.transferTo(destPath.toFile());


                expense.setReceiptPath(fullFilePath);
                expense.setExpensesID(maxId);

            } catch (IOException e) {
                throw new IOException("ファイル保存失敗: " + e.getMessage(), e);
            }
        }
        expenseListService.insertExpense(expense);
    }



}
