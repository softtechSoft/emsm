package com.softtech.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
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

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.softtech.actionForm.RequestFromBean;
import com.softtech.entity.ClaimInfo;
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
	 
	    if (claimMonth == null || claimMonth.trim().isEmpty()) {
            response.put("error", "Invalid claimMonth parameter.");
            return response;
        }
	    try {
	    	
	        // 日志输出
	    	logger.info("Checking database for month: " + claimMonth);
	        //
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
	 *    
	 * @return  response
	 */
	@RequestMapping(value = "/searchDatabase", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> searchDatabase(@RequestParam("claimMonth") String claimMonth) {
        Map<String, Object> response = new HashMap<>();
        if (claimMonth == null || claimMonth.trim().isEmpty()) {
            response.put("error", "Invalid claimMonth parameter.");
            return response;
        }
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
		    if (claimMonth == null || claimMonth.trim().isEmpty()) {
	            response.put("error", "Invalid claimMonth parameter.");
	            return response;
	        }
        
        try {
        	logger.info("Checking database for month: " + claimMonth);
            // 获取请求数据
            List<RequestEntity> requestData = rqService.getRequestData(claimMonth);
            
            // 将实体数据转换为表单数据
            List<RequestFromBean> requestDetailList = rqService.transferEntityToFormBean(requestData, claimMonth);
            
            if (requestDetailList.isEmpty()) {
                response.put("updateMsg", "No related data found.");
            } else {
                // 转换表单数据到实体
                List<ClaimInfo> claimInfoList = rqService.transforFormBeanToEntity(requestDetailList);
                logger.info("Checking database: " + claimInfoList);
                // 数据库
                boolean updateResult = rqService.insertClaim(claimInfoList);

                // 处理更新结果
                if (updateResult) {
                    response.put("updateMsg", "請求情報を更新しました。");
                } else {
                    response.put("error", "Failed to update claim information.");
                }
            }

        } catch (Exception e) {
            response.put("error", "Database error: " + e.getMessage());
            logger.error("Error in claimDatabase:", e);
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
	@RequestMapping(value = "/requestSeikyu", method = RequestMethod.POST)
	public void requestSubmit(HttpServletResponse response) {
		String excelFilePath = "E:\\請求書_202401.xlsx";
		String pdfFilePath = "E:\\output.pdf";

		try (FileInputStream inputStream = new FileInputStream(excelFilePath);
				XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
				FileOutputStream outputStream = new FileOutputStream(pdfFilePath)) {

			Document document = new Document();
			PdfWriter.getInstance(document, outputStream);
			document.open();

			XSSFSheet sheet = workbook.getSheetAt(0);
			PdfPTable table = new PdfPTable(sheet.getRow(0).getPhysicalNumberOfCells());
			addTableData(workbook, table, sheet);
			document.add(table);

			document.close();
			logger.info("PDF generated successfully.");

		} catch (Exception e) {
			logger.error("Error processing requestSubmit", e);
		}
	}

	public static void addTableData(XSSFWorkbook workbook, PdfPTable table, XSSFSheet sheet) {
		if (workbook == null || table == null || sheet == null) {
			logger.error("Workbook, table, or sheet is null");
			return;
		}

		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			if (row == null) {
				continue; // Skip null rows
			}

			for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
				Cell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
				String cellValue = getCellValueAsString(cell);

				PdfPCell pdfCell = new PdfPCell(new Phrase(cellValue));
				setCellAlignment(cell, pdfCell);
				table.addCell(pdfCell);
			}
		}
	}

	public static String getCellValueAsString(Cell cell) {
		if (cell == null) {
			return "";
		}

		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				return cell.getDateCellValue().toString();
			} else {
				return String.valueOf(cell.getNumericCellValue());
			}
		case BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());
		case FORMULA:
			return cell.getCellFormula();
		case BLANK:
		default:
			return "";
		}
	}

	public static void setCellAlignment(Cell cell, PdfPCell pdfCell) {
		if (cell == null || pdfCell == null) {
			return;
		}

		CellStyle cellStyle = cell.getCellStyle();
		if (cellStyle == null) {
			return;
		}

		HorizontalAlignment horizontalAlignment = cellStyle.getAlignment();
		switch (horizontalAlignment) {
		case LEFT:
			pdfCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			break;
		case CENTER:
			pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			break;
		case RIGHT:
			pdfCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			break;
		default:
			pdfCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			break;
		}
	}




	    

	

    //downloadInvoice
    @PostMapping("/downloadInvoice")
    @ResponseBody
    public Map<String, Object> downloadInvoice(@RequestBody Map<String, String> params) {
        String claimMonth = params.get("claimMonth");
        Map<String, Object> response = new HashMap<>();
        List<RequestEntity> requestData = rqService.getRequestData(claimMonth);
        List<RequestToDownloadEntity> requestDownloadList = rqService.transforFormBeanToDownloadEntity(
                rqService.transferEntityToFormBean(requestData, claimMonth));

        if (requestDownloadList.isEmpty()) {
            response.put("error", "No related data found.");
            return response;
        }

//        String inputFilePath = "C:\\Users\\admin\\Desktop\\YYYYMM請求書_ソフトテク2.xlsx";
//        String outputFilePath = "E:\\請求書_" + claimMonth + ".xlsx";
        String inputFilePath = "C:\\Users\\admin\\Desktop\\YYYYMM請求書_ソフトテク2_VBA.xlsm";
        String outputFilePath = "E:\\請求書_" + claimMonth + ".xlsm";
        String templateSheetName = "sample"; // 模板工作表名

        try (FileInputStream fis = new FileInputStream(inputFilePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            Map<String, Integer> sheetRowMap = new HashMap<>();

            for (RequestToDownloadEntity rqDlEntity : requestDownloadList) {
                String companyName = rqDlEntity.getCompanyName();
                String sheetName = companyName;

                Sheet sheet = workbook.getSheet(sheetName);
                if (sheet == null) {
                    int templateSheetIndex = workbook.getSheetIndex(templateSheetName);
                    sheet = workbook.cloneSheet(templateSheetIndex);
                    int newSheetIndex = workbook.getSheetIndex(sheet);
                    workbook.setSheetName(newSheetIndex, sheetName);
                    sheetRowMap.put(sheetName, 20);

                    // 在新工作表中写入请求月和公司名
                    writeCellData(sheet, 3, 0, claimMonth);
                    writeCellData(sheet, 5, 0, companyName);
                }

                int startRow = sheetRowMap.get(sheetName);

                String employeeName = rqDlEntity.getEmployeeName();
                String ctLowerTime = rqDlEntity.getContractLowerTime();
                String ctUpperTime = rqDlEntity.getContractUpperTime();
                String price = rqDlEntity.getPrice();
                int priceInt = Integer.parseInt(price.replace(",", "")); 
                String workTime = rqDlEntity.getWorkTime();

                writeCellData(sheet, startRow, 1, employeeName);
                writeCellData(sheet, startRow, 4, ctLowerTime);
                writeCellData(sheet, startRow, 7, ctUpperTime);
                writeCellData(sheet, startRow, 17, priceInt);
                writeCellData(sheet, startRow, 9, workTime);

                // 更新下一行的起始位置
                sheetRowMap.put(sheetName, startRow + 1);
            }
            // 全ての数式を再計算
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
            try (FileOutputStream fos = new FileOutputStream(outputFilePath)) {
                workbook.write(fos);
            }

            response.put("updateMsg", "Invoice downloaded successfully for month: " + claimMonth);

        } catch (IOException e) {
            response.put("error", "An error occurred while downloading the invoice: " + e.getMessage());
        }

        return response;
    }

    private String getCellValue(Sheet sheet, int row, int col) {
        Row r = sheet.getRow(row);
        if (r == null) return "";
        Cell c = r.getCell(col);
        if (c == null) return "";
        return c.toString();
    }

    //String書き込み
    private void writeCellData(Sheet sheet, int row, int col, String value) {
        Row r = sheet.getRow(row);
        if (r == null) r = sheet.createRow(row);
        Cell c = r.getCell(col);
        if (c == null) c = r.createCell(col);
        c.setCellValue(value);
    }
    

    //int書き込み
    public void writeCellData(Sheet sheet, int row, int col, Integer value) {  
        Row r = sheet.getRow(row);  
        if (r == null) r = sheet.createRow(row);  
        Cell c = r.getCell(col);  
        if (c == null) c = r.createCell(col);  
        c.setCellValue(value);  
    }
	
}
