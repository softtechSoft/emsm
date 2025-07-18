package com.softtech.service;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import com.softtech.entity.BankAccount;
import com.softtech.mappers.BankAccountMapper;

@Service
@Transactional
public class BankAccountService {

    @Autowired
    private BankAccountMapper mapper;

    @Value("${file.bankAccount.location:./ems_files/bankAccountFiles}")
    private String bankAccountDir;

    public Map<String, Object> searchAccounts(LocalDate startDate, LocalDate endDate) {
        List<BankAccount> accounts = mapper.findByDateRange(startDate, endDate);
        BigDecimal totalWithdrawal = mapper.sumWithdrawalByDateRange(startDate, endDate);
        BigDecimal totalDeposit    = mapper.sumDepositByDateRange(startDate, endDate);
        BigDecimal finalBalance    = mapper.findLatestBalance(startDate, endDate);

        List<Map<String, Object>> accountList = new ArrayList<>();
        for (BankAccount account : accounts) {
            Map<String, Object> item = new HashMap<>();
            item.put("id", account.getId());
            item.put("transactionDate", account.getTransactionDate());
            item.put("transactionType", account.getTransactionType());
            item.put("description", account.getDescription());
            item.put("withdrawal", account.getWithdrawal());
            item.put("deposit", account.getDeposit());
            item.put("balance", account.getBalance());
            item.put("remarks", account.getRemarks());
            accountList.add(item);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("accountList", accountList);
        result.put("totalWithdrawal", totalWithdrawal != null ? totalWithdrawal : BigDecimal.ZERO);
        result.put("totalDeposit",    totalDeposit    != null ? totalDeposit    : BigDecimal.ZERO);
        result.put("finalBalance",    finalBalance    != null ? finalBalance    : BigDecimal.ZERO);
        return result;
    }

    public void updateRemarks(Long id, String remarks) {
        BankAccount account = mapper.findById(id);
        if (account == null) {
            throw new RuntimeException("Account not found");
        }
        mapper.updateRemarks(id, remarks);
    }

    /**
     * CSVアップロード（重複・エラー抽出）
     */
    public Map<String, Object> uploadCsvFile(MultipartFile file) throws IOException {

        // 保存ディレクトリ
        Path dirPath = Paths.get(bankAccountDir).toAbsolutePath();
        Files.createDirectories(dirPath);

        // オリジナルファイル名からベース名を作成（拡張子除去・危険文字置換）
        String original = file.getOriginalFilename();
        String base     = buildSafeBaseName(original);

        // タイムスタンプ
        String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        String savedName = base + "_" + ts + ".csv";
        Path   savedPath = dirPath.resolve(savedName);
        file.transferTo(savedPath);

        // 解析用リスト
        List<BankAccount> insertList = new ArrayList<>();
        List<String[]>    dupLines   = new ArrayList<>();
        List<String[]>    errorLines = new ArrayList<>();

        // CSV 読み込み
        try (Reader r = Files.newBufferedReader(savedPath, StandardCharsets.UTF_8);
             CSVReader csv = new CSVReader(r)) {

            String[] row;
            int lineNo = 0;
            while ((row = readNextSafe(csv)) != null) {
                lineNo++;

                if (lineNo == 1 && isHeaderRow(row)) {
                    continue;
                }

                try {
                    BankAccount ba = convertRow(row, savedPath.toString());
                    if (isDuplicate(ba)) {
                        dupLines.add(addLineNo(row, lineNo));
                        continue;
                    }
                    insertList.add(ba);
                } catch (RuntimeException ex) {
                    errorLines.add(addLineNo(row, lineNo, ex.getMessage()));
                }
            }
        }

        if (!insertList.isEmpty()) {
            mapper.insertBatch(insertList);
        }

        String dupFile = writeCsv(dirPath, "duplicate_" + base + "_" + ts + ".csv", dupLines,
                                  new String[]{"行","日付","取引区分","摘要","出金額","入金額","残高"});
        String errFile = writeCsv(dirPath, "error_"     + base + "_" + ts + ".csv", errorLines,
                                  new String[]{"行","日付","取引区分","摘要","出金額","入金額","残高","エラー理由"});

        Map<String, Object> res = new HashMap<>();
        res.put("inserted", insertList.size());
        res.put("duplicates", dupLines.size());
        res.put("errors", errorLines.size());
        res.put("duplicateFile", dupLines.isEmpty() ? null : dupFile);
        res.put("errorFile",     errorLines.isEmpty() ? null : errFile);
        res.put("uploadedFile",  savedName);
        res.put("rawFile",       savedName);

        return res;
    }
    
    private String buildSafeBaseName(String originalFilename) {
        if (originalFilename == null || originalFilename.trim().isEmpty()) {
            return "upload";
        }
        String justName = Paths.get(originalFilename).getFileName().toString();
        int dot = justName.lastIndexOf('.');
        if (dot > 0 && dot < justName.length() - 1) {
            String ext = justName.substring(dot + 1);
            if (ext.equalsIgnoreCase("csv")) {
                justName = justName.substring(0, dot);
            }
        }
        justName = justName.replaceAll("[\\\\/:*?\"<>|\\p{Cntrl}]", "_").trim();
        return justName.isEmpty() ? "upload" : justName;
    }



    private String[] readNextSafe(CSVReader csv) throws IOException {
        try {
            return csv.readNext();
        } catch (CsvValidationException e) {
            throw new IOException("CSV読み込みエラー: " + e.getMessage(), e);
        }
    }


    private boolean isDuplicate(BankAccount ba) {
        BigDecimal w = ba.getWithdrawal() != null ? ba.getWithdrawal() : BigDecimal.ZERO;
        BigDecimal d = ba.getDeposit()    != null ? ba.getDeposit()    : BigDecimal.ZERO;
        return mapper.existsByUniqueKeys(
                ba.getTransactionDate(),
                ba.getTransactionType(),
                w, d,
                ba.getDescription()
        ) > 0;
    }

    private boolean isHeaderRow(String[] row) {
        if (row.length == 0) return false;
        String c0 = row[0] == null ? "" : row[0].trim();
        return c0.contains("日付") || c0.equalsIgnoreCase("date");
    }

    private BankAccount convertRow(String[] row, String filePath) {
        if (row.length < 6) {
            throw new RuntimeException("列不足: " + row.length);
        }
        BankAccount ba = new BankAccount();
        ba.setTransactionDate(parseDate(row[0]));
        ba.setTransactionType(safeTrim(row[1]));
        ba.setDescription(safeTrim(row[2]));
        ba.setWithdrawal(parseAmount(row[3]));
        ba.setDeposit(parseAmount(row[4]));
        ba.setBalance(parseAmount(row[5]));
        ba.setRemarks(null);
        ba.setPath(filePath);
        return ba;
    }

    private String[] addLineNo(String[] row, int lineNo) {
        String[] out = new String[row.length + 1];
        out[0] = String.valueOf(lineNo);
        System.arraycopy(row, 0, out, 1, row.length);
        return out;
    }

    private String[] addLineNo(String[] row, int lineNo, String errorMsg) {
        String[] out = new String[row.length + 2];
        out[0] = String.valueOf(lineNo);
        System.arraycopy(row, 0, out, 1, row.length);
        out[out.length - 1] = errorMsg;
        return out;
    }

    private String writeCsv(Path dir, String name, List<String[]> rows, String[] header) throws IOException {
        if (rows.isEmpty()) return null;
        Path path = dir.resolve(name);

        try (java.io.OutputStream os = Files.newOutputStream(path)) {
            os.write(new byte[]{(byte)0xEF, (byte)0xBB, (byte)0xBF});
            try (java.io.OutputStreamWriter osw = new java.io.OutputStreamWriter(os, StandardCharsets.UTF_8);
                 CSVWriter w = new CSVWriter(osw)) {
                if (header != null) w.writeNext(header);
                for (String[] r : rows) {
                    w.writeNext(r);
                }
            }
        }
        return path.getFileName().toString();
    }


    private LocalDate parseDate(String raw) {
        if (raw == null || raw.trim().isEmpty()) {
            throw new RuntimeException("日付が空です");
        }
        String normalized = raw.trim()
                               .replace('.', '-')
                               .replace('/', '-');
        DateTimeFormatter fmt = new DateTimeFormatterBuilder()
                .appendValue(ChronoField.YEAR, 4)
                .appendLiteral('-')
                .appendValue(ChronoField.MONTH_OF_YEAR)
                .appendLiteral('-')
                .appendValue(ChronoField.DAY_OF_MONTH)
                .toFormatter();
        try {
            return LocalDate.parse(normalized, fmt);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("無効な日付形式: " + raw);
        }
    }

    private BigDecimal parseAmount(String amountStr) {
        if (amountStr == null || amountStr.trim().isEmpty()) {
            return BigDecimal.ZERO;
        }
        String clean = amountStr.trim()
            .replaceAll(",", "")
            .replaceAll("\\s", "")
            .replaceAll("円", "")
            .replaceAll("¥", "");
        try {
            return new BigDecimal(clean);
        } catch (NumberFormatException e) {
            throw new RuntimeException("金額不正: " + amountStr);
        }
    }

    private String safeTrim(String s) {
        return s == null ? "" : s.trim();
    }
}
