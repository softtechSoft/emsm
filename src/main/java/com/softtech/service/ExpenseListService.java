package com.softtech.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
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
    * @param expense 経費情報エンティティ（発生日取得用）
    * @return 保存された画像の相対パス（ems_files/receipt/ファイル名）、ファイルが無い場合はnull
    * @throws IOException ファイル保存処理で例外が発生した場合
    * @details
    * - 既存のファイルがある場合は削除
    * - ファイル名は経費IDとオリジナルファイル名を組み合わせて生成
    * - 拡張子は元のファイル名から取得
    * - 戻り値は常に相対パス形式
    */
    public String saveAndReturnReceiptPath(MultipartFile file, ExpenseListEntity expense) throws IOException {
       // 入力チェック
       if (file == null || file.isEmpty()) {
           return null;
       }

       // 既存のファイルがある場合、そのファイルを削除
       if (expense.getReceiptPath() != null && !expense.getReceiptPath().isEmpty()) {
           try {
               String oldPath = expense.getReceiptPath();
               File oldFile = new File(oldPath);

               if (oldFile.exists() && oldFile.isFile()) {
                   oldFile.delete();
               }
           } catch (Exception e) {
               System.err.println("ファイル削除中にエラーが発生しました: " + e.getMessage());
               e.printStackTrace();
           }
       }

       // 保存用ファイル名の生成
       String originalName = file.getOriginalFilename();
    //    String ext = "";
    //    if (originalName != null && originalName.lastIndexOf('.') != -1) {
    //        ext = originalName.substring(originalName.lastIndexOf('.'));
    //    }
       //String newFileName = UUID.randomUUID().toString() + ext;
       String expenseId = expense.getExpensesID();
       String newFileName = expenseId + "_" + originalName;

       SaveFolder saveFolder = saveFolderService.findFileTypeCode("01");
        String baseDir = saveFolder.getSaveFolder();

        LocalDate newAcDate = expense.getAccrualDate();
        String acc = newAcDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        String dateFolderPath = baseDir + File.separator + acc;
        File dateFolder = new File(dateFolderPath);

        if (!dateFolder.exists()) {
	        if (!dateFolder.mkdirs()) {
	            throw new IOException("新規フォルダできない：" + dateFolderPath);
	        }
        }


        String fullFilePath = dateFolderPath + File.separator + newFileName;
        Path destPath = Paths.get(fullFilePath);

       // ファイルの保存
       //File dest = new File(receiptFolderPath.toFile(), newFileName);
       file.transferTo(destPath);

       // 相対パスを返却（保存ルートからの相対：yyyyMMdd/ファイル名）
       // 例: 20250807/EX0001_receipt.png
       String relativePath = acc + File.separator + newFileName;
       return relativePath;
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

    /**
	 * CSVファイルをZIPに追加する
	 */
	public void addCsvToZip(ZipOutputStream zipOut, List<ExpenseListEntity> expenses,
	                        int year, int month) throws IOException {
		String csvFileName = String.format("expenses_%04d%02d.csv", year, month);
		StringBuilder csv = new StringBuilder();
		csv.append("経費種別,経費名称,発生日付,金額(円),用途,担当者,精算日付,精算種別,証跡\n");
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		for (ExpenseListEntity e : expenses) {
			String st = "";
			if ("0".equals(e.getSettlementType())) st = "現金";
			else if ("1".equals(e.getSettlementType())) st = "口座";
			csv.append(String.format("\"%s\",\"%s\",%s,%.0f,\"%s\",\"%s\",%s,\"%s\",\"%s\"\n",
				escapeCSV(e.getExpensesTypeName()),
				escapeCSV(e.getExpenseNameText()),
				e.getAccrualDate() != null ? e.getAccrualDate().format(df) : "",
				e.getCost() != null ? e.getCost().doubleValue() : 0.0,
				escapeCSV(e.getHappenAddress()),
				escapeCSV(e.getTantouName()),
				e.getSettlementDate() != null ? e.getSettlementDate().format(df) : "",
				escapeCSV(st),
				e.getReceiptPath() != null ? getFileNameFromPath(e.getReceiptPath()) : "なし"
			));
		}
		ZipEntry csvEntry = new ZipEntry(csvFileName);
		zipOut.putNextEntry(csvEntry);
		byte[] bom = new byte[]{(byte)0xEF,(byte)0xBB,(byte)0xBF};
		zipOut.write(bom);
		zipOut.write(csv.toString().getBytes("UTF-8"));
		zipOut.closeEntry();
	}

	/**
	 * 証跡ファイルを年月日サブフォルダーでZIPに追加する
	 */
	public void addEvidenceFilesToZip(ZipOutputStream zipOut, List<ExpenseListEntity> expenses)
			throws IOException {
		// 基準フォルダを一度だけ解決（01: 経費管理）
		String baseFolder = null;
		try {
			SaveFolder sf = saveFolderService.findFileTypeCode("01");
			if (sf != null) {
				baseFolder = sf.getSaveFolder();
			}
		} catch (Exception ignore) {}
		if (!StringUtils.hasText(baseFolder)) {
			baseFolder = System.getProperty("user.dir");
		}
		System.out.println("[ZIP] Base folder resolved: " + baseFolder);
		String yearMonthFolder = expenses.stream()
			.filter(e -> e.getAccrualDate() != null)
			.findFirst()
			.map(e -> e.getAccrualDate().format(DateTimeFormatter.ofPattern("yyyyMM")))
			.orElse("");
		if (StringUtils.hasText(yearMonthFolder)) {
			ZipEntry ymEntry = new ZipEntry(String.format("evidence_files/%s/", yearMonthFolder));
			zipOut.putNextEntry(ymEntry);
			zipOut.closeEntry();
		} else {
			ZipEntry root = new ZipEntry("evidence_files/");
			zipOut.putNextEntry(root);
			zipOut.closeEntry();
		}
		Map<String, List<ExpenseListEntity>> expensesByDate = expenses.stream()
			.filter(e -> e.getAccrualDate() != null && StringUtils.hasText(e.getReceiptPath()))
			.collect(Collectors.groupingBy(e -> e.getAccrualDate().format(DateTimeFormatter.ofPattern("yyyyMMdd"))));
		for (Map.Entry<String, List<ExpenseListEntity>> entry : expensesByDate.entrySet()) {
			String dateFolder = entry.getKey();
			List<ExpenseListEntity> daily = entry.getValue();
			String dateDir = StringUtils.hasText(yearMonthFolder)
				? String.format("evidence_files/%s/%s/", yearMonthFolder, dateFolder)
				: String.format("evidence_files/%s/", dateFolder);
			ZipEntry dateEntry = new ZipEntry(dateDir);
			zipOut.putNextEntry(dateEntry);
			zipOut.closeEntry();
			System.out.println("[ZIP] Creating date dir: " + dateDir + " (items: " + daily.size() + ")");
			for (ExpenseListEntity expense : daily) {
				addSingleEvidenceFile(zipOut, expense, yearMonthFolder, dateFolder, baseFolder);
			}
		}
	}

	/**
	 * 単一の証跡ファイルをZIPに追加する
	 */
	private void addSingleEvidenceFile(ZipOutputStream zipOut, ExpenseListEntity expense,
									String yearMonthFolder, String dateFolder, String baseFolder) throws IOException {
		String receiptPath = expense.getReceiptPath();
		if (!StringUtils.hasText(receiptPath)) {
			return;
		}
		try {
			// 正規化：ドライブ無しで先頭がスラッシュの場合は相対扱いにする
			String normalized = receiptPath.trim();
			boolean hasDrive = normalized.matches("^[A-Za-z]:.*");
			if (!hasDrive) {
				while (normalized.startsWith("/") || normalized.startsWith("\\")) {
					normalized = normalized.substring(1);
				}
			}
			File file = new File(normalized);
			if (!file.isAbsolute() && StringUtils.hasText(baseFolder)) {
				file = new File(baseFolder, normalized);
			}
			System.out.println("[ZIP] Expense " + expense.getExpensesID() +
				" originalPath=\"" + receiptPath + "\" normalized=\"" + normalized +
				"\" resolved=\"" + file.getPath() + "\"");
			if (!file.exists() || !file.isFile()) {
				// 二次フォールバック：絶対パスでも見つからない場合、ドライブ表記を外して基準フォルダで再解決
				if (StringUtils.hasText(baseFolder)) {
					String stripped = normalized.replaceFirst("^[A-Za-z]:[\\/]*", "");
					while (stripped.startsWith("/") || stripped.startsWith("\\")) {
						stripped = stripped.substring(1);
					}
					// 候補1：基準フォルダ + 相対パス（推奨）
					File alt1 = new File(baseFolder, stripped);
					System.out.println("[ZIP] Fallback try #1: " + alt1.getPath());
					if (alt1.exists() && alt1.isFile()) {
						file = alt1;
					} else {
						// 候補2：相対パス中に日付フォルダが含まれていない場合のみ、基準フォルダ + dateFolder + ファイル名
						String strippedLower = stripped.replace('\\', '/');
						boolean startsWithDate = strippedLower.startsWith(dateFolder + "/");
						if (!startsWithDate) {
							String onlyName = getFileNameFromPath(stripped);
							File alt2 = new File(new File(baseFolder, dateFolder), onlyName);
							System.out.println("[ZIP] Fallback try #2: " + alt2.getPath());
							if (alt2.exists() && alt2.isFile()) {
								file = alt2;
							} else {
								System.err.println("ファイルが見つかりません: " + alt1.getPath() + " or " + alt2.getPath());
								return;
							}
						} else {
							System.err.println("ファイルが見つかりません: " + alt1.getPath());
							return;
						}
					}
				} else {
					System.err.println("ファイルが見つかりません: " + file.getPath());
					return;
				}
			}
			String originalFileName = getFileNameFromPath(file.getPath());
			String zipPath = StringUtils.hasText(yearMonthFolder)
				? String.format("evidence_files/%s/%s/%s_%s", yearMonthFolder, dateFolder, expense.getExpensesID(), originalFileName)
				: String.format("evidence_files/%s/%s_%s", dateFolder, expense.getExpensesID(), originalFileName);
			ZipEntry fileEntry = new ZipEntry(zipPath);
			zipOut.putNextEntry(fileEntry);
			try (FileInputStream fis = new FileInputStream(file)) {
				byte[] buffer = new byte[8192];
				int length;
				while ((length = fis.read(buffer)) != -1) {
					zipOut.write(buffer, 0, length);
				}
			}
			zipOut.closeEntry();
		} catch (Exception ex) {
			System.err.println("ファイル追加エラー: " + receiptPath + ", エラー: " + ex.getMessage());
		}
	}

	/**
	 * パスからファイル名を抽出する
	 */
	public String getFileNameFromPath(String path) {
		if (!StringUtils.hasText(path)) {
			return "";
		}
		return path.substring(Math.max(path.lastIndexOf('/'), path.lastIndexOf('\\')) + 1);
	}

	/**
	 * CSV用の文字列エスケープ処理
	 */
	private String escapeCSV(String value) {
		if (value == null) {
			return "";
		}
		return value.replace("\"", "\"\"");
	}

}
