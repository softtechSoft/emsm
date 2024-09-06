package com.softtech.controller;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

		//本年度を取得
//        String searchParam = DateUtil.getNowMonth();
        String Param = null;
        Param = rqService.queryLatestYearMonth();
        String searchParam=rqService.getNextYearMonth(Param);
        model.addAttribute("month",searchParam);

        return "requestList";
    }
	/**
	 *    状況確認ボタン処理
	 *    画面に確認の結果を表示。
	 *
	 * @param  請求月
	 *
	 *
	 * @return  response
	 */

	@RequestMapping(value = "/checkDatabase", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkDatabase(@RequestParam("claimMonth") String claimMonth) {
	    Map<String, Object> response = new HashMap<>();


	    try {

	        // 日志输出
	    	logger.info("Checking database for month: " + claimMonth);
        	boolean isDataEmpty = rqService.isDataEmpty(claimMonth);
	        response.put("isDataEmpty", isDataEmpty);
	        System.out.println("isDataEmpty: " + isDataEmpty);



	        if (!isDataEmpty) {
	        	boolean isClaimEmpty = rqService.isClaimEmpty(claimMonth);
	            response.put("isClaimEmpty", isClaimEmpty);
	            response.put("searchParam", claimMonth);
	            logger.info("isClaimEmpty: " + isClaimEmpty);
		        //
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
	 *
	 * @return  response
	 */
	@RequestMapping(value = "/searchDatabase", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> searchDatabase(@RequestParam("claimMonth") String claimMonth) {
        Map<String, Object> response = new HashMap<>();

        try {
        	// 日志输出
	    	logger.info("Checking database for month: " + claimMonth);
            List<RequestEntity> rqdt = rqService.getRequestData(claimMonth);
            List<RequestFromBean> requestDetailList1 = rqService.transferEntityToFormBean(rqdt,"");

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
	 *
	 * @return  response
	 */
	@RequestMapping(value = "/claimDatabase", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> claimDatabase(@RequestParam("claimMonth") String claimMonth) {
		 Map<String, Object> response = new HashMap<>();

		 try {
	            boolean result = rqService.processAndInsertClaims(claimMonth);

	            if (result) {
	                response.put("updateMsg", "請求情報を更新しました。");
	            } else {
	                response.put("error", "Failed to update claim information.");
	            }
	        } catch (Exception e) {
	            response.put("error", "Database error: " + e.getMessage());
	        }
		 List<RequestEntity> rqdt = rqService.getRequestData(claimMonth);
         List<RequestFromBean> requestDetailList1 = rqService.transferEntityToFormBean(rqdt,"");
         if (requestDetailList1.isEmpty()) {
             response.put("updateMsg", "No related data found.");
         } else {
             response.put("list", requestDetailList1);
         }

	        return response;
	    }
	/**
	 *    請求書ダウンロードボタン処理
	 *    画面に確認の結果を表示。
	 *
	 * @param  請求月
	 *
	 *
	 * @return  response
	 */


	@PostMapping("/downloadInvoice")
    @ResponseBody
    public Map<String, Object> downloadInvoice(@RequestBody Map<String, String> params) {
        String claimMonth = params.get("claimMonth");
        System.out.print(claimMonth);
        Map<String, Object> response = new HashMap<>();
        List<RequestEntity> requestData = rqService.getRequestData(claimMonth);
        List<RequestToDownloadEntity> requestDownloadList = rqService.transforFormBeanToDownloadEntity(
                rqService.transferEntityToFormBean(requestData, claimMonth));

        if (requestDownloadList.isEmpty()) {
            response.put("error", "No related data found.");
            return response;
        }

        String inputFilePath = "C:\\Users\\81809\\OneDrive\\Desktop\\YYYYMM請求書_ソフトテク.xlsx";
        String excelOutputFilePath = "C:\\Users\\81809\\請求書_" + claimMonth + ".xlsx";
        String pdfOutputFilePath = "C:\\Users\\81809\\ "+ claimMonth +"_請求書 .pdf";
        String templateSheetName = "sample";

        try (FileInputStream fis = new FileInputStream(inputFilePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            Map<String, Integer> sheetRowMap = new HashMap<>();
            int templateSheetIndex = workbook.getSheetIndex(templateSheetName);


            String formattedDate = convertToEndOfMonth(claimMonth);

            for (RequestToDownloadEntity rqDlEntity : requestDownloadList) {
                String companyName = rqDlEntity.getCompanyName();
                String sheetName = companyName;
                String companyNameWithSuffix = companyName + "  御中";

                Sheet sheet = workbook.getSheet(sheetName);
                if (sheet == null) {
                    // 既存のシートがない場合は、テンプレートシートをクローンする
                    sheet = workbook.cloneSheet(templateSheetIndex);
                    int newSheetIndex = workbook.getSheetIndex(sheet);
                    workbook.setSheetName(newSheetIndex, sheetName);
                    sheetRowMap.put(sheetName, 20);

                    // 新しいシートにデータを書き込む
                    writeCellData(sheet, 3, 0, formattedDate);
                    writeCellData(sheet, 5, 0, companyNameWithSuffix);
                }

                int startRow = sheetRowMap.get(sheetName);

                String employeeName = rqDlEntity.getEmployeeName();
                String price = rqDlEntity.getPrice();
                int priceInt = Integer.parseInt(price.replace(",", ""));
                String workTime = rqDlEntity.getWorkTime();
                String sum = rqDlEntity.getSum();
                int workTimeOut = 0;
                int sumInt = Integer.parseInt(sum.replace(",", ""));
                int upperTime = (int) Float.parseFloat(rqDlEntity.getUpperTime());
                int lowerTime = (int) Float.parseFloat(rqDlEntity.getLowerTime());

                if (upperTime != 0) {
                	workTimeOut = upperTime;
                } else if (lowerTime != 0) {
                	workTimeOut = lowerTime;
                }

             	String upperTotal = rqDlEntity.getUpperTotal();
             	String lowerTotal = rqDlEntity.getLowerTotal();
             	String specialPrice;
             	if (!upperTotal.equals("0")) {
             	    specialPrice = upperTotal;
             	} else if (!lowerTotal.equals("0")) {
             	    specialPrice = lowerTotal;
             	} else {
             	    specialPrice = "0";
             	}

             	writeCellData(sheet, startRow, 19, specialPrice);
                writeCellData(sheet, startRow, 1, employeeName);
                writeCellData(sheet, startRow, 22, sumInt);
                writeCellData(sheet, startRow, 16, priceInt);
                writeCellData(sheet, startRow, 8, workTime);
                writeCellData(sheet, startRow, 12, workTimeOut);

                // 次の行の開始位置を更新
                sheetRowMap.put(sheetName, startRow + 1);
            }

            // すべての数式を再計算
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

            try (FileOutputStream fos = new FileOutputStream(excelOutputFilePath)) {
                workbook.write(fos);
            }
            convertExcelToPdf(excelOutputFilePath, pdfOutputFilePath);

            response.put("updateMsg", "請求書をダウンロード完了： " + claimMonth);

        } catch (IOException e) {
            response.put("error", "An error occurred while downloading the invoice: " + e.getMessage());
        }

        return response;
    }
	private void convertExcelToPdf(String excelFilePath, String pdfFilePath) {
	    try {

	        Workbook workbook = new Workbook(excelFilePath);


	        PdfSaveOptions pdfSaveOptions = new PdfSaveOptions();
	        pdfSaveOptions.setOnePagePerSheet(true);


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
        if (r == null) r = sheet.createRow(row);
        Cell c = r.getCell(col);
        if (c == null) c = r.createCell(col);
        c.setCellValue(value);
    }

    private void writeCellData(Sheet sheet, int row, int col, Integer value) {
        Row r = sheet.getRow(row);
        if (r == null) r = sheet.createRow(row);
        Cell c = r.getCell(col);
        if (c == null) c = r.createCell(col);
        c.setCellValue(value);
    }
}