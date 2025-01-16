package com.softtech.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aspose.cells.PaperSizeType;
import com.aspose.cells.PdfSaveOptions;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.softtech.actionForm.RequestFromBean;
import com.softtech.entity.Employee;
import com.softtech.entity.RequestEntity;
import com.softtech.entity.RequestToDownloadEntity;
import com.softtech.service.RequestServiceImpl;

@Controller
public class RequestController {

	@Value("${file.requestSeikyu.location}")
	private String requestSeikyuLocation; // ./ems_files/requestSeikyu

	@Value("${file.requestSeikyu.template}")
	private String requestSeikyuTemplate; // ./ems_files/template/請求書_ソフトテク.xlsx

	@PostConstruct
	public void initDirs() {
		File outDir = new File(requestSeikyuLocation);
		if (!outDir.exists()) {
			boolean created = outDir.mkdirs();
			if (!created) {
				System.err.println("Failed to create directory: " + outDir.getAbsolutePath());
			}
		}
	}

	private static final Logger logger = LoggerFactory.getLogger(RequestController.class);
	private final RequestServiceImpl rqService;

	public RequestController(RequestServiceImpl rqService) {
		this.rqService = rqService;
	}

	/**
	 *    画面初期処理
	 *
	 * @param  モデル
	 *
	 * @return 画面
	 */
	@RequestMapping("/initRequest")
	public String showRequestList(Model model) {
		String Param = null;
		Param = rqService.queryLatestYearMonth();
		String searchParam = rqService.getNextYearMonth(Param);
		model.addAttribute("month", searchParam);

		return "requestList";
	}

	/**
	 *    状況確認ボタン処理
	 *    画面に確認の結果を表示。
	 *
	 * @param  請求月
	 *
	 * @return  response
	 */
	@RequestMapping(value = "/checkDatabase", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkDatabase(@RequestParam("claimMonth") String claimMonth) {
		Map<String, Object> response = new HashMap<>();
		try {
			logger.info("Checking database for month: " + claimMonth);
			boolean isDataEmpty = rqService.isDataEmpty(claimMonth);
			response.put("isDataEmpty", isDataEmpty);
			System.out.println("isDataEmpty: " + isDataEmpty);

			if (!isDataEmpty) {
				boolean isClaimEmpty = rqService.isClaimEmpty(claimMonth);
				response.put("isClaimEmpty", isClaimEmpty);
				response.put("searchParam", claimMonth);
				logger.info("isClaimEmpty: " + isClaimEmpty);
			} else {
				List<String> employeeNames = rqService.getEmployeeNamesByMonth(claimMonth)
						.stream()
						.map(Employee::getEmployeeName)
						.collect(Collectors.toList());
				response.put("employeeNames", employeeNames);
				logger.info("employeeNames: " + employeeNames);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("error", "An error occurred: " + e.getMessage());
		}
		return response;
	}

	/**
	 *    検索ボタン処理
	 *    画面に確認の結果を表示。
	 *
	 * @param  請求月
	 *
	 * @return  response
	 */
	@RequestMapping(value = "/searchDatabase", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> searchDatabase(@RequestParam("claimMonth") String claimMonth) {
		Map<String, Object> response = new HashMap<>();
		try {
			logger.info("Checking database for month: " + claimMonth);
			List<RequestEntity> rqdt = rqService.getRequestData(claimMonth);
			List<RequestFromBean> requestDetailList1 = rqService.transferEntityToFormBean(rqdt, "");

			if (requestDetailList1.isEmpty()) {
				response.put("updateMsg", "No related data found.");
			} else {
				response.put("list", requestDetailList1);
			}
			response.put("searchParam", claimMonth);
		} catch (Exception e) {
			response.put("error", "Database error: " + e.getMessage());
		}
		return response;
	}

	/**
	 *    請求書生成ボタン処理
	 *    画面に確認の結果を表示。
	 *
	 * @param  請求月
	 *
	 * @return  response
	 */
//	@RequestMapping(value = "/claimDatabase", method = RequestMethod.POST)
//	@ResponseBody
//	public Map<String, Object> claimDatabase(@RequestParam("claimMonth") String claimMonth) {
//		Map<String, Object> response = new HashMap<>();
//		try {
//			boolean result = rqService.processAndInsertClaims(claimMonth);
//			if (result) {
//				response.put("updateMsg", "請求情報を更新しました。");
//			} else {
//				response.put("error", "Failed to update claim information.");
//			}
//		} catch (Exception e) {
//			response.put("error", "Database error: " + e.getMessage());
//		}
//
//		List<RequestEntity> rqdt = rqService.getRequestData(claimMonth);
//		List<RequestFromBean> requestDetailList1 = rqService.transferEntityToFormBean(rqdt, "");
//		if (requestDetailList1.isEmpty()) {
//			response.put("updateMsg", "No related data found.");
//		} else {
//			response.put("list", requestDetailList1);
//		}
//		return response;
//	}

	/**
	 * 請求書PDFファイル生成用メソッド
	 * このメソッドはサーバー側でExcelとPDFを作成し、
	 * DBのtimeReportPathを更新する。
	 */
	@PostMapping("/generateInvoiceFile")
	@ResponseBody
	public Map<String, Object> generateInvoiceFile(@RequestBody Map<String, String> params) {
		String claimMonth = params.get("claimMonth");
		logger.info("Downloading invoice for claimMonth = {}", claimMonth);

		Map<String, Object> response = new HashMap<>();

		boolean dbResult = rqService.processAndInsertClaims(claimMonth);
		if (!dbResult) {
			response.put("error", "データベース更新に失敗しました。");
			return response;
		}

		// 1. DBから請求用のデータを取得する
		List<RequestEntity> requestData = rqService.getRequestData(claimMonth);

		// 2. Entity -> FormBean -> DownloadEntity に変換
		List<RequestToDownloadEntity> requestDownloadList = rqService.transforFormBeanToDownloadEntity(
				rqService.transferEntityToFormBean(requestData, claimMonth));

		if (requestDownloadList.isEmpty()) {
			response.put("error", "No related data found.");
			return response;
		}

		// 3. テンプレートファイル（Excel）のパス
		Path templatePath = Paths.get(requestSeikyuTemplate);

		// 4. Excel出力先 & PDF出力先のパスを組み立てる
		Path excelOutputPath = Paths.get(requestSeikyuLocation, claimMonth + "_請求書.xlsx");
		Path pdfOutputPath = Paths.get(requestSeikyuLocation, claimMonth + "_請求書.pdf");

		// テンプレートのシート名
		String templateSheetName = "sample";
		int rowNumber = 1;
		String startDate = claimMonth.substring(0, 4) + "/" + claimMonth.substring(4, 6) + "/01";
		String endDate = claimMonth.substring(0, 4) + "/" + claimMonth.substring(4, 6) + "/"
				+ getLastDayOfMonth(
						Integer.parseInt(claimMonth.substring(4, 6)),
						Integer.parseInt(claimMonth.substring(0, 4)));

		// claimMonthをyyyy/MM/dd形式に変換
		String formattedDate = convertToEndOfMonth(claimMonth);

		try (FileInputStream fis = new FileInputStream(templatePath.toFile());
				XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

			// シートごとに書き込み開始行を保存するためのマップ
			Map<String, Integer> sheetRowMap = new HashMap<>();
			int templateSheetIndex = workbook.getSheetIndex(templateSheetName);

			// ===== Excelデータ書き込みロジック =====
			for (RequestToDownloadEntity rqDlEntity : requestDownloadList) {
				String companyName = rqDlEntity.getCompanyName();
				String sheetName = companyName;
				String companyNameWithSuffix = companyName + "  御中";

				// シート存在チェック
				Sheet sheet = workbook.getSheet(sheetName);
				if (sheet == null) {
					// 既存シートがなければテンプレートをクローン
					sheet = workbook.cloneSheet(templateSheetIndex);
					int newSheetIndex = workbook.getSheetIndex(sheet);
					workbook.setSheetName(newSheetIndex, sheetName);
					sheetRowMap.put(sheetName, 20);
					rowNumber = 1;
					// 日付、会社名など上部へ書き込む
					writeCellData(sheet, 3, 0, formattedDate);
					writeCellData(sheet, 5, 0, companyNameWithSuffix);
				}

				int startRow = sheetRowMap.get(sheetName);
				// No.列に行番号
				writeCellData(sheet, startRow, 0, String.valueOf(rowNumber));
				// 期間
				writeCellData(sheet, startRow, 4, startDate + "～" + endDate);

				// データの取り出し
				String employeeName = rqDlEntity.getEmployeeName();
				String price = rqDlEntity.getPrice();
				int priceInt = Integer.parseInt(price.replace(",", ""));
				String workTime = rqDlEntity.getWorkTime();
				String sum = rqDlEntity.getSum();
				int sumInt = Integer.parseInt(sum.replace(",", ""));
				int upperTime = (int) Float.parseFloat(rqDlEntity.getUpperTime());
				int lowerTime = (int) Float.parseFloat(rqDlEntity.getLowerTime());
				int workTimeOut = 0;

				// 超過か不足かを判定
				if (upperTime != 0) {
					workTimeOut = upperTime;
				} else if (lowerTime != 0) {
					workTimeOut = lowerTime;
				}

				// 特別請求分 = (トータル - 標準単価)
				int specialPriceValue = sumInt - priceInt;

				// 実際にセルへ書き込み
				writeCellData(sheet, startRow, 19, specialPriceValue);
				writeCellData(sheet, startRow, 1, employeeName);
				writeCellData(sheet, startRow, 22, sumInt);
				writeCellData(sheet, startRow, 16, priceInt);
				writeCellData(sheet, startRow, 8, workTime);
				writeCellData(sheet, startRow, 12, workTimeOut);

				// 行を1つ進める
				sheetRowMap.put(sheetName, startRow + 1);
				rowNumber++;
			}

			// 数式を再計算
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			for (Sheet sheet : workbook) {
				for (Row row : sheet) {
					for (Cell cell : row) {
						if (cell.getCellType() == CellType.FORMULA) {
							evaluator.evaluateFormulaCell(cell);
						}
					}
				}
			}

			// ===== Excelファイルを書き出し =====
			try (FileOutputStream fos = new FileOutputStream(excelOutputPath.toFile())) {
				workbook.write(fos);
			}

			// ===== PDFへ変換 =====
			convertExcelToPdf(excelOutputPath.toString(), pdfOutputPath.toString());

			// ===== ここから追加: PDFパスをcontract表のtimeReportPathに更新する =====
			// PDFの絶対パスを取得
			String pdfFullPath = pdfOutputPath.toAbsolutePath().toString();

			// 同一contractIDが複数の行で登場する可能性がある場合、必要に応じて去重してください
			// ここでは単純に全行に対して更新する
			for (RequestToDownloadEntity dto : requestDownloadList) {
				// RequestToDownloadEntity に contractID がある前提
				String contractID = dto.getContractID();
				if (contractID != null && !contractID.isEmpty()) {
					rqService.updateTimeReportPath(contractID, pdfFullPath);
				}
			}

			// 成功メッセージ
			response.put("updateMsg", "請求書を生成完了： " + claimMonth);

		} catch (IOException e) {
			logger.error("Error while downloading invoice: ", e);
			response.put("error", "An error occurred while downloading the invoice: " + e.getMessage());
		}

		return response;
	}

	@GetMapping("/downloadInvoiceByMonth")
	public ResponseEntity<?> downloadInvoiceByMonth(@RequestParam("claimMonth") String claimMonth) {
		List<String> contractIDList = rqService.findContractIDsByWorkMonth(claimMonth);
		if (contractIDList == null || contractIDList.isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("対象契約が存在しません。(workMonth=" + claimMonth + ")");
		}
		String contractID = contractIDList.get(0);
		String filePath = rqService.findTimeReportPathByContractID(contractID);
		if (filePath == null || filePath.isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("ファイルパスが存在しません。(contractID=" + contractID + ")");
		}
		File file = new File(filePath);
		if (!file.exists()) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("ファイルが存在しません: " + filePath);
		}
		Resource resource = new FileSystemResource(file);

		String originalName = file.getName();
		String encodedName = "";
		try {
			encodedName = URLEncoder.encode(originalName, StandardCharsets.UTF_8.toString())
					.replaceAll("\\+", "%20");
		} catch (Exception e) {
			e.printStackTrace();
			encodedName = originalName;
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename*=UTF-8''" + encodedName);
		headers.setContentType(MediaType.APPLICATION_PDF);

		return ResponseEntity.ok()
				.headers(headers)
				.contentLength(file.length())
				.body(resource);
	}

	private String getLastDayOfMonth(int month, int year) {
		LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
		LocalDate lastDayOfMonth = firstDayOfMonth.withDayOfMonth(firstDayOfMonth.lengthOfMonth());
		return String.format("%02d", lastDayOfMonth.getDayOfMonth());
	}

	private void convertExcelToPdf(String excelFilePath, String pdfFilePath) {
		try {
			Workbook workbook = new Workbook(excelFilePath);
			PdfSaveOptions pdfSaveOptions = new PdfSaveOptions();
			pdfSaveOptions.setOnePagePerSheet(true);
			if (workbook.getWorksheets().getCount() > 0) {
				Worksheet firstSheet = workbook.getWorksheets().get(0);
				firstSheet.setVisible(false);
			}
			for (int i = 0; i < workbook.getWorksheets().getCount(); i++) {
				Worksheet sheet = workbook.getWorksheets().get(i);
				sheet.getPageSetup().setPaperSize(PaperSizeType.PAPER_A_4);
			}
			workbook.save(pdfFilePath, pdfSaveOptions);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String convertToEndOfMonth(String claimMonth) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
		YearMonth yearMonth = YearMonth.parse(claimMonth, formatter);
		LocalDate endOfMonth = yearMonth.atEndOfMonth();
		return endOfMonth.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
	}

	private void writeCellData(Sheet sheet, int row, int col, String value) {
		Row r = sheet.getRow(row);
		if (r == null)
			r = sheet.createRow(row);
		Cell c = r.getCell(col);
		if (c == null)
			c = r.createCell(col);
		c.setCellValue(value);
	}

	private void writeCellData(Sheet sheet, int row, int col, Integer value) {
		Row r = sheet.getRow(row);
		if (r == null)
			r = sheet.createRow(row);
		Cell c = r.getCell(col);
		if (c == null)
			c = r.createCell(col);
		c.setCellValue(value);
	}
}
