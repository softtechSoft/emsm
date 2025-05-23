package com.softtech.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.softtech.entity.ExpenseListEntity;

/**
 * 複数の経費エンティティを一括でデータベースに挿入するサービス
 */
@Service
public class ExpenseInfoService {

    @Autowired
    private ExpenseListService expenseListService;

    @Value("${file.receipt.location}")
    private String configuredReceiptLocation;

    private Path receiptFolderPath;


    @PostConstruct
    public void init() {
        Path path = Paths.get(configuredReceiptLocation).normalize();
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
            // 生成新文件名
            String originalName = receiptFile.getOriginalFilename();
            String ext = "";
            if (originalName != null && originalName.lastIndexOf(".") != -1) {
                ext = originalName.substring(originalName.lastIndexOf("."));
            }
            String newFileName = UUID.randomUUID().toString() + ext;

            Path destPath = this.receiptFolderPath.resolve(newFileName);

            try {
                receiptFile.transferTo(destPath.toFile());

                expense.setReceiptPath("ems_files/receipt/" + newFileName);

            } catch (IOException e) {
                throw new IOException("ファイル保存失敗: " + e.getMessage(), e);
            }
        }
        
        expenseListService.insertExpense(expense);
    }

}
