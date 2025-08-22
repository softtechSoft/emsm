package com.softtech.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.softtech.actionForm.BpInvoiceFormBean;
import com.softtech.entity.BpInvoice;
import com.softtech.service.BpInvoiceService;

@Controller
public class BpInvoiceController {

    private static final Logger logger = LoggerFactory.getLogger(BpInvoiceController.class);

    @Autowired
    private BpInvoiceService bpInvoiceService;

    @Value("${file.bpPayment.location}")
    private String bpPaymentLocation;



    // 請求書ファイルをアップロード
    @RequestMapping(value = "/uploadBpInvoice", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, String>> uploadBpInvoice(@RequestParam("paymentId") String paymentId,
                                                               @RequestParam("file") MultipartFile file) {
        Map<String, String> response = new HashMap<>();
        try {
            // 入力パラメータを検証
            if (paymentId == null || paymentId.trim().isEmpty()) {
                logger.warn("請求書のアップロード時に支払IDが選択されていません");
                response.put("error", "支払IDを選択してください");
                return ResponseEntity.badRequest().body(response);
            }

            if (file == null || file.isEmpty()) {
                logger.warn("請求書のアップロード時にファイルが選択されていません");
                response.put("error", "アップロードするファイルを選択してください");
                return ResponseEntity.badRequest().body(response);
            }

            // ファイル保存用ディレクトリを作成
            Path uploadPath = Paths.get(bpPaymentLocation).toAbsolutePath().normalize();
            Files.createDirectories(uploadPath);

            // サーバーにファイルを保存
            String fileName = file.getOriginalFilename();
            if (fileName == null || fileName.isEmpty()) {
                logger.warn("請求書のアップロード時にファイル名が無効です");
                response.put("error", "ファイル名が無効です");
                return ResponseEntity.badRequest().body(response);
            }

            Path destinationFile = uploadPath.resolve(fileName).normalize();
            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

            // データベース保存用の相対パスを構築
            String relativeFilePath = "ems_files/bp_invoice/" + fileName;

            // 既にこのpaymentIdの請求書記録が存在するか確認
            List<BpInvoice> existingInvoices = bpInvoiceService.getAllInvoices(paymentId);

            if (existingInvoices != null && !existingInvoices.isEmpty()) {
                // 既存の記録を更新
                BpInvoice existingInvoice = existingInvoices.get(0);
                existingInvoice.setFileName(fileName);
                existingInvoice.setFilePath(relativeFilePath);
                // 月を現在の年月に設定
                String month = YearMonth.now().toString().replace("-", "");
                existingInvoice.setMonth(month);
                bpInvoiceService.updateInvoice(existingInvoice);
            } else {
                // 記録が存在しない場合は新規作成
                BpInvoiceFormBean formBean = new BpInvoiceFormBean();
                // 請求書IDを生成
                String invoiceId = bpInvoiceService.getMaxBpInvoiceId();
                formBean.setInvoiceId(invoiceId);
                formBean.setPaymentId(paymentId);
                formBean.setFileName(fileName);
                formBean.setFilePath(relativeFilePath);
                formBean.setFileSize(file.getSize());
                // 月を現在の年月に設定
                String month = YearMonth.now().toString().replace("-", "");
                formBean.setMonth(month);
                // 請求書番号を生成
                String invoiceNumber = "INV-" + paymentId + "-" + System.currentTimeMillis();
                formBean.setInvoiceNumber(invoiceNumber);
                bpInvoiceService.insertInvoice(formBean);
            }

            logger.info("ファイルのアップロードが成功しました: {}", fileName);
            response.put("message", "ファイルのアップロードが成功しました！");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // エーログを記録
            logger.error("ファイルのアップロードに失敗しました", e);
            response.put("error", "アップロードに失敗しました: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }


    // 請求書ファイルをダウンロード
    @RequestMapping(value = "/downloadBpInvoice", method = RequestMethod.GET)
    public void downloadBpInvoice(@RequestParam("no") String invoiceId, HttpServletResponse response) {
        try {
            // パラメータ検証
            if (invoiceId == null || invoiceId.trim().isEmpty()) {
                logger.warn("請求書のダウンロード時に請求書IDが空です");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            BpInvoice invoice = bpInvoiceService.getInvoiceById(invoiceId);
            if (invoice == null || invoice.getFilePath() == null || invoice.getFileName() == null) {
                logger.warn("請求書記録が存在しないか、またはファイルパスが空です: {}", invoiceId);
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            // 完全なファイルパス
            String fullPath = bpPaymentLocation + File.separator + invoice.getFileName();
            File file = new File(fullPath);
            if (!file.exists()) {
                logger.warn("請求書ファイルが存在しません: {}", fullPath);
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + invoice.getFileName() + "\"");
            try (InputStream in = new FileInputStream(file); OutputStream out = response.getOutputStream()) {
                byte[] buffer = new byte[4096];
                int len;
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                logger.info("請求書ファイルのダウンロードが成功しました: {}", invoice.getFileName());
            }
        } catch (Exception e) {
            // エーログを記録
            logger.error("請求書ファイルのダウンロードに失敗しました", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    // 請求書レコードを削除
    @RequestMapping(value = "/deleteBpInvoice", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteBpInvoice(@RequestParam("no") String invoiceId, Model model) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (invoiceId != null && !invoiceId.trim().isEmpty()) {
                // 請求書情報を取得
                BpInvoice invoice = bpInvoiceService.getInvoiceById(invoiceId);

                if (invoice != null && invoice.getFilePath() != null) {
                    // 物理ファイルを削除
                    File file = new File(invoice.getFilePath());
                    if (file.exists()) {
                        if (file.delete()) {
                            logger.info("請求書ファイルの削除が成功しました: {}", invoice.getFilePath());
                        } else {
                            logger.warn("請求書ファイルの削除に失敗しました: {}", invoice.getFilePath());
                        }
                    } else {
                        logger.warn("請求書ファイルが存在しません: {}", invoice.getFilePath());
                    }
                }

                // データベースのファイル情報をクリア
                bpInvoiceService.clearInvoice(invoiceId);
                logger.info("請求書レコードの削除が成功しました: {}", invoiceId);

                response.put("success", true);
            } else {
                logger.warn("請求書削除時に請求書IDが空です");
                response.put("error", "無効な請求書ID");
            }
        } catch (Exception e) {
            // エーログを記録
            logger.error("請求書レコードの削除に失敗しました", e);
            response.put("error", "削除に失敗しました: " + e.getMessage());
        }
        return response;
    }
}