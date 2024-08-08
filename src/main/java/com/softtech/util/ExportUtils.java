package com.softtech.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.softtech.actionForm.RequestFromBean;

public class ExportUtils {
	/*  
     * 機能： 年月を採番する
     * 
     * @param param 入力のStringフォーマットはyyyyMM  
     * @return 年月+1後のStringフォーマットはyyyyMM  
     * @throws IllegalArgumentException もし入力が間違い場合、エラーが起こり
     * @author 孫@ソフトテク 
     */  
	
	public static String getNextMonth(String dateString) {  
	    // 桁数チェック
	    if (dateString == null || dateString.length() != 6) {  
	    	return null;
//	        throw new IllegalArgumentException("Invalid date string format: " + dateString);  
	    }else {
	    	//年月を区切り
		    int year = Integer.parseInt(dateString.substring(0, 4));  
		    int month = Integer.parseInt(dateString.substring(4, 6));  
		  
		    // 月数の範囲チェック  
		    if (month < 1 || month > 12) {  
		        throw new IllegalArgumentException("Invalid month: " + month);  
		    }  
		  
		    // 次の月を取得  
		    int nextMonth = month + 1;  
		    if (nextMonth > 12) {  
		        year = year + 1;  
		        nextMonth = 1; // 来年の一月  
		    }  
		  
		    //フォーマットして、Stringになる    
		    return String.format("%d%02d", year, nextMonth); // 使用%02d来确保月份是两位数  
		}  
	    
	}
	/**
	 * 機能：請求リストフォーマットダウンロード
	 *
	 * @param response レスポンス
	 * @param ClaimList 対象データ
	 * @return TRUE:成功、FALSE失敗
	 *
	 * @exception なし
	 * @author @ソフトテク
	 */

	public boolean exportToExcelXLSX01(List<RequestFromBean> rf, String templateFilePath, String exportFilePath, HttpServletResponse response) {
        System.out.println("開始：Excelファイルの生成...");

        try (FileInputStream templateStream = new FileInputStream(templateFilePath)) {
            Workbook workbook = new XSSFWorkbook(templateStream);

            Sheet templateSheet = workbook.getSheetAt(0); // 假设模板表单在第一个位置
            // 用于存储每个公司名对应的Sheet对象
            Map<String, Sheet> sheetMap = new HashMap<>();

            // 初始化行号
            int companyNameRow = 5;
            int dataStartRow = 21;

            // 计算行偏移量，即当前Sheet已有的数据行数
            int rowOffset = 0;
            int companyCounter = 0;

            for (int i = 0; i < rf.size(); i++) {
                RequestFromBean requestFromBean = rf.get(i);
                String companyName = sanitizeSheetName(requestFromBean.getCompanyName());

                // 如果当前公司名对应的Sheet不存在，则创建新的Sheet，并复制模板表单内容和样式
                Sheet sheet = sheetMap.computeIfAbsent(companyName, k -> {
                    Sheet newSheet = workbook.createSheet(companyName);
                    copySheet(templateSheet, newSheet);
                    return newSheet;
                });

                // 复制样式和内容
                copyRowWithStyles(workbook, sheet, companyNameRow, companyNameRow + i);
                setCellValueAndMerge(sheet, companyNameRow, 0, 3, requestFromBean.getCompanyName());
                
                // 设置数据行内容
                int currentDataRow = dataStartRow + rowOffset;
                setCellValueAndMerge(sheet, currentDataRow, 0, 1, String.valueOf(companyCounter + 1));
                setCellValueAndMerge(sheet, currentDataRow, 1, 3, requestFromBean.getEmployeeName());
                setCellValueAndMerge(sheet, currentDataRow, 4, 7, "160-200");
                setCellValueAndMerge(sheet, currentDataRow, 8, 11, requestFromBean.getWorkTime());
                setCellValueAndMerge(sheet, currentDataRow, 12, 15, requestFromBean.getPrice());
                setCellValueAndMerge(sheet, currentDataRow, 16, 18, requestFromBean.getWorkTime());
                setCellValueAndMerge(sheet, currentDataRow, 19, 21, requestFromBean.getLowerTotal());
                setCellValueAndMerge(sheet, currentDataRow, 22, 25, requestFromBean.getClaimPrice());
//                rowOffset++;
            }

            if (response != null && !response.isCommitted()) {
                // 设置HTTP响应头
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                response.setCharacterEncoding("UTF-8");

                // 设置Excel文件名
                String excelFileName = URLEncoder.encode("請求書" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".xlsx", "UTF-8");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + excelFileName + "\"");

                try (OutputStream out = response.getOutputStream()) {
                    workbook.write(out);
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false; // 失败返回
                }
            } else {
                // 保存Excel文件到本地文件系统
                try (FileOutputStream fileOut = new FileOutputStream(exportFilePath)) {
                    workbook.write(fileOut);
                    System.out.println("Excelファイルが正常に生成されました：" + exportFilePath);
                } catch (IOException e) {
                    e.printStackTrace();
                    return false; // 失败返回
                }
            }

            System.out.println("Excelファイルの生成が完了しました。");
            return true; // 成功返回

        } catch (IOException e) {
            e.printStackTrace();
            return false; // 失败返回
        }
    }

    // 复制表单的内容和样式
    private void copySheet(Sheet sourceSheet, Sheet targetSheet) {
        targetSheet.setDefaultColumnWidth(sourceSheet.getDefaultColumnWidth());
        targetSheet.setDefaultRowHeight(sourceSheet.getDefaultRowHeight());
        targetSheet.setDisplayGridlines(sourceSheet.isDisplayGridlines());
        targetSheet.setDisplayGuts(sourceSheet.getDisplayGuts());

        // 复制所有行和单元格
        for (int rowNum = sourceSheet.getFirstRowNum(); rowNum <= sourceSheet.getLastRowNum(); rowNum++) {
            Row sourceRow = sourceSheet.getRow(rowNum);
            Row targetRow = targetSheet.createRow(rowNum);

            if (sourceRow != null) {
                // 设置行高度
                targetRow.setHeight(sourceRow.getHeight());

                // 复制所有单元格
                for (int colNum = sourceRow.getFirstCellNum(); colNum < sourceRow.getLastCellNum(); colNum++) {
                    Cell sourceCell = sourceRow.getCell(colNum);
                    Cell targetCell = targetRow.createCell(colNum);

                    if (sourceCell != null) {
                        // 复制单元格样式和内容
                        copyCell(sourceCell, targetCell);
                    }
                }
            }
        }

        // 复制合并单元格
        for (int i = 0; i < sourceSheet.getNumMergedRegions(); i++) {
            CellRangeAddress mergedRegion = sourceSheet.getMergedRegion(i);
            targetSheet.addMergedRegion(mergedRegion);
        }
    }
 // 复制行的内容和样式
    private void copyRowWithStyles(Workbook workbook, Sheet sheet, int sourceRowNum, int targetRowNum) {
        Row sourceRow = sheet.getRow(sourceRowNum);
        Row targetRow = sheet.getRow(targetRowNum);

        if (sourceRow == null || targetRow == null) {
            return; // 如果源行或目标行为空，直接返回
        }

        targetRow.setHeight(sourceRow.getHeight()); // 设置目标行高度

        for (int colNum = sourceRow.getFirstCellNum(); colNum < sourceRow.getLastCellNum(); colNum++) {
            Cell sourceCell = sourceRow.getCell(colNum);
            Cell targetCell = targetRow.createCell(colNum);

            if (sourceCell != null) {
                // 复制单元格样式和内容
                copyCell(workbook, sourceCell, targetCell);
            }
        }
    }

    // 复制单元格内容和样式
    private void copyCell(Workbook workbook, Cell sourceCell, Cell targetCell) {
        targetCell.setCellStyle(sourceCell.getCellStyle()); // 设置目标单元格样式

        switch (sourceCell.getCellType()) {
            case STRING:
                targetCell.setCellValue(sourceCell.getRichStringCellValue());
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(sourceCell)) {
                    targetCell.setCellValue(sourceCell.getDateCellValue());
                } else {
                    targetCell.setCellValue(sourceCell.getNumericCellValue());
                }
                break;
            case BOOLEAN:
                targetCell.setCellValue(sourceCell.getBooleanCellValue());
                break;
            case FORMULA:
                targetCell.setCellFormula(sourceCell.getCellFormula());
                break;
            case BLANK:
                targetCell.setBlank();
                break;
            default:
                break;
        }
    }


    // 复制单元格内容和样式
    private void copyCell(Cell sourceCell, Cell targetCell) {
        targetCell.setCellStyle(sourceCell.getCellStyle()); // 设置目标单元格样式

        switch (sourceCell.getCellType()) {
            case STRING:
                targetCell.setCellValue(sourceCell.getRichStringCellValue());
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(sourceCell)) {
                    targetCell.setCellValue(sourceCell.getDateCellValue());
                } else {
                    targetCell.setCellValue(sourceCell.getNumericCellValue());
                }
                break;
            case BOOLEAN:
                targetCell.setCellValue(sourceCell.getBooleanCellValue());
                break;
            case FORMULA:
                targetCell.setCellFormula(sourceCell.getCellFormula());
                break;
            case BLANK:
                targetCell.setBlank();
                break;
            default:
                break;
        }
    }

    // 设置单元格内容并合并单元格
    private void setCellValueAndMerge(Sheet sheet, int rowNum, int firstCol, int lastCol, String value) {
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum);
        }

        Cell cell = row.createCell(firstCol);
        cell.setCellValue(value);

        // 合并单元格处理
        CellRangeAddress newRegion = new CellRangeAddress(rowNum, rowNum, firstCol, lastCol);
        addMergedCellWithOverlapCheck(sheet, newRegion, value);
    }

    // 添加带重叠检查的合并单元格
    private void addMergedCellWithOverlapCheck(Sheet sheet, CellRangeAddress newRegion, String value) {
        List<CellRangeAddress> existingMergedRegions = sheet.getMergedRegions();

        // 检查并删除重叠的合并单元格
        for (CellRangeAddress existingRegion : existingMergedRegions) {
            if (existingRegion.intersects(newRegion)) {
                System.out.println("删除重叠的合并单元格: " + existingRegion.formatAsString());
                sheet.removeMergedRegion(existingMergedRegions.indexOf(existingRegion));
                break; // 找到重叠的合并单元格后结束循环
            }
        }

        // 添加新的合并单元格
        sheet.addMergedRegion(newRegion);
    }

    // 清理公司名中的特殊字符，用于Sheet的命名
    private String sanitizeSheetName(String companyName) {
        return companyName.replaceAll("[\\/*\\[\\]:?]", "_");
    }
    
    
    
    
    
    
    public boolean exportToExcelXLSX02(List<RequestFromBean> rf, String templateFilePath, String exportFilePath, HttpServletResponse response) {
        System.out.println("開始：Excelファイルの生成...");

        try (FileInputStream templateStream = new FileInputStream(templateFilePath)) {
            Workbook workbook = new XSSFWorkbook(templateStream);

            Sheet templateSheet = workbook.getSheetAt(1); // 假设模板表单在第一个位置
            // 用于存储每个公司名对应的Sheet对象
            Map<String, Sheet> sheetMap = new HashMap<>();

            // 初始化行号
            int companyNameRow = 5;
            int dataStartRow = 21;

            
            int companyCounter = 0;

            for (int i = 0; i < rf.size(); i++) {
                RequestFromBean requestFromBean = rf.get(i);
                String companyName = sanitizeSheetName(requestFromBean.getCompanyName());

                // 如果当前公司名对应的Sheet不存在，则创建新的Sheet，并复制模板表单内容和样式
                Sheet sheet = sheetMap.computeIfAbsent(companyName, k -> {
                    Sheet newSheet = workbook.createSheet(companyName);
                    copySheet(templateSheet, newSheet);
                    return newSheet;
                });

                // 复制样式和内容
                copyRowWithStyles(workbook, sheet, companyNameRow, companyNameRow + i);
                setCellValueAndMerge(sheet, companyNameRow, 0, 3, requestFromBean.getCompanyName());
                
                // 设置数据行内容
                int currentDataRow = dataStartRow ;
                setCellValueAndMerge(sheet, currentDataRow, 0, 1, String.valueOf(companyCounter + 1));
                setCellValueAndMerge(sheet, currentDataRow, 1, 3, requestFromBean.getEmployeeName());
                setCellValueAndMerge(sheet, currentDataRow, 4, 7, "160-200");
                setCellValueAndMerge(sheet, currentDataRow, 8, 11, requestFromBean.getWorkTime());
                setCellValueAndMerge(sheet, currentDataRow, 12, 15, requestFromBean.getPrice());
                setCellValueAndMerge(sheet, currentDataRow, 16, 18, requestFromBean.getWorkTime());
                setCellValueAndMerge(sheet, currentDataRow, 19, 21, requestFromBean.getLowerTotal());
                setCellValueAndMerge(sheet, currentDataRow, 22, 25, requestFromBean.getClaimPrice());
//                
            }

            if (response != null && !response.isCommitted()) {
                // 设置HTTP响应头
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                response.setCharacterEncoding("UTF-8");

                // 设置Excel文件名
                String excelFileName = URLEncoder.encode("請求書" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".xlsx", "UTF-8");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + excelFileName + "\"");

                try (OutputStream out = response.getOutputStream()) {
                    workbook.write(out);
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false; // 失败返回
                }
            } else {
                // 保存Excel文件到本地文件系统
                try (FileOutputStream fileOut = new FileOutputStream(exportFilePath)) {
                    workbook.write(fileOut);
                    System.out.println("Excelファイルが正常に生成されました：" + exportFilePath);
                } catch (IOException e) {
                    e.printStackTrace();
                    return false; // 失败返回
                }
            }

            System.out.println("Excelファイルの生成が完了しました。");
            return true; // 成功返回

        } catch (IOException e) {
            e.printStackTrace();
            return false; // 失败返回
        }
    }

    
    
    
    
    
    
    
    
    
    /**
     * 合并单元格を作成してデータを設定するメソッド
     * @param sheet シートオブジェクト
     * @param firstRow 開始行
     * @param firstCol 開始列
     * @param lastRow 終了行
     * @param lastCol 終了列
     * @param value 設定する値
     */
    private void addMergedCell(Sheet sheet, int firstRow, int firstCol, int lastRow, int lastCol, Object value) {
        CellRangeAddress cellRangeAddress = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
        sheet.addMergedRegion(cellRangeAddress);

        Row row = sheet.getRow(firstRow);
        if (row == null) {
            row = sheet.createRow(firstRow);
        }
        Cell cell = row.createCell(firstCol);

        if (value instanceof String) {
            cell.setCellValue((String) value);
        } else if (value instanceof Number) {
            cell.setCellValue(((Number) value).doubleValue());
        } else {
            cell.setCellValue(value.toString());
        }

        // セルのスタイルを設定（任意でフォーマットを変更）
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cell.setCellStyle(cellStyle);
    }

    /**
     * 通常のセルにデータを設定するメソッド
     * @param row 行オブジェクト
     * @param columnIndex 列番号
     * @param value 設定する値
     */
    private void addCell(Row row, int columnIndex, Object value) {
        Cell cell = row.createCell(columnIndex);

        if (value instanceof String) {
            cell.setCellValue((String) value);
        } else if (value instanceof Number) {
            cell.setCellValue(((Number) value).doubleValue());
        } else {
            cell.setCellValue(value.toString());
        }

        // セルのスタイルを設定（任意でフォーマットを変更）
        CellStyle cellStyle = row.getSheet().getWorkbook().createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cell.setCellStyle(cellStyle);
    }


	//そのままに
	 public boolean exportToXLSX(List<RequestFromBean> rf, String templateFilePath, String exportFilePath, HttpServletResponse response){
		 try (FileInputStream fis = new FileInputStream(templateFilePath);  
		         Workbook workbook = WorkbookFactory.create(fis)) {  
		  
		        Sheet sheet = workbook.getSheetAt(0); // 获取第一个工作表  
		        
		        for(RequestFromBean requestFromBean : rf) {
		        	String companyName = requestFromBean.getCompanyName();
		        } 
		        int startRowNum = 1; // 假设从第二行开始写入数据（第一行可能是标题）  
		        int bodyRowNum = 20; // 假设要写入数据的行数是第22行
		        int headRowNum = 5;
		        Row rowbody = sheet.getRow(bodyRowNum);
		        Row rowhead = sheet.getRow(headRowNum);
		        if ((rowbody == null)&(rowhead == null)) {
		        	rowbody = sheet.createRow(20); // 如果第22行不存在，创建新的行
		        	rowhead = sheet.createRow(5);
		        }else {
		        	rowbody = sheet.createRow(bodyRowNum++); // 如果第22行不存在，创建新的行
		        	rowhead = sheet.createRow(headRowNum++);
		        }
		        
		       
		        // 填充数据到模板中
		        fillDataToSheet(sheet, rf);
		        
		          
		  
		        // 如果传入了 HttpServletResponse，则将生成的文件作为响应返回  
		        if (response != null) {  
		            // 设置响应头  
		            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");  
		            response.setCharacterEncoding("UTF-8");  
		            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");  
		            String fileName = URLEncoder.encode("請求書" + dateFormat.format(new Date()) + ".xlsx", "UTF-8");  
		            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");  
		  
		            // 将Workbook内容写入到响应流中  
		            try (OutputStream out = response.getOutputStream()) {  
		                workbook.write(out);  
		                out.flush();  
		                // 注意：这里不需要返回false，因为异常会被下面的catch块捕获  
		            } catch (IOException e) {  
		                e.printStackTrace();  
		                return false; // 如果写入响应失败，返回false  
		            }  
		            // 写入HTTP响应后，不需要再执行文件保存，直接返回true  
		            return true;  
		        } else {  
		            // 如果没有传入 HttpServletResponse，则保存到指定路径  
		            try (FileOutputStream fileOut = new FileOutputStream(exportFilePath)) {  
		                workbook.write(fileOut);  
		                // 成功保存到文件，返回true  
		                return true;  
		            } catch (IOException e) {  
		                e.printStackTrace();  
		                return false; // 如果保存到文件失败，返回false  
		            }  
		        }
		    } catch (IOException e) {  
		        e.printStackTrace();  
		        // 发生异常，返回false  
		        return false;  
		    } 
	 }
	 
	 private void fillDataToSheet(Sheet sheet, List<RequestFromBean> rf) {
        // 填充数据到指定的 Sheet 中
    	if (sheet == null) {
            return;
        }

        // 将 RequestFromBean 中的数据填充到 Map 中
        Map<String, Object> dataMap = extractDataToMap(rf);

        // 将数据填充到指定位置的单元格
        writeDataToCells(sheet, dataMap);
    }
	        
	 private Map<String, Object> extractDataToMap(List<RequestFromBean> rf) {
	    Map<String, Object> dataMap = new HashMap<>();

	    for (RequestFromBean bean : rf) {
	        dataMap.put("companyName", bean.getCompanyName());
	        dataMap.put("employeeName", bean.getEmployeeName());
	        dataMap.put("workTime", bean.getWorkTime());
	        try {
	            // 去掉逗号和 ".00" 后转换为 double
	            double upperTm = parseStringToDouble(bean.getUpperTime());
	            double lowerTm = parseStringToDouble(bean.getLowerTime());
	            double upperPrs = parseStringToDouble(bean.getUpperPrice());
	            double lowerPrs = parseStringToDouble(bean.getLowerPrice());
	            double price = parseStringToDouble(bean.getPrice());
	            double claimPrice = parseStringToDouble(bean.getClaimPrice());

	            if (upperTm > 0 && lowerTm == 0) {
	                dataMap.put("contractOutTime", "+" + upperTm);
	                dataMap.put("outTotal", "+" + formatDecimal(upperTm * upperPrs));
	            } else if (upperTm == 0 && lowerTm > 0) {
	                dataMap.put("contractOutTime", "-" + lowerTm);
	                dataMap.put("outTotal", "-" + formatDecimal(lowerTm * lowerPrs));
	            } else {
	                dataMap.put("contractOutTime", "0.00");
	                dataMap.put("outTotal", "0");
	            }
	            dataMap.put("price", formatDecimalWithSeparator(price));
		        dataMap.put("claimPrice", formatDecimalWithSeparator(claimPrice));
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	            // 可以根据需要在这里添加进一步的异常处理或日志记录
	        } 
	    }
	    return dataMap;
	}

	/**
	 * 将字符串转换为双精度浮点数，去掉千位分隔符和小数部分。
	 * @param str 待转换的字符串
	 * @return 转换后的双精度浮点数
	 * @throws NumberFormatException 如果字符串无法转换为数字
	 */
	private double parseStringToDouble(String str) throws NumberFormatException {
	    if (str == null || str.isEmpty()) {
	        return 0;
	    }
	    // 去掉逗号
	    String sanitizedStr = str.replaceAll(",", "");
	    return Double.parseDouble(sanitizedStr);
	}

	/**
	 * 将双精度浮点数格式化为字符串，保留两位小数。
	 * @param value 待格式化的值
	 * @return 格式化后的字符串
	 */
	private String formatDecimal(double value) {
	    return String.format("%.2f", value);
	}
	
	/**
	 * 将双精度浮点数格式化为字符串，保留千位分隔符。
	 * @param value 待格式化的值
	 * @return 格式化后的字符串
	 */
	public static String formatDecimalWithSeparator(double value) {  
        // 保留千位分隔符  
        return String.format("#,##0", value);  
    }  


	 private void writeDataToCells(Sheet sheet, Map<String, Object> dataMap) {
        // 写入数据到指定位置的单元格
        if (sheet == null || dataMap == null) {
            return;
        }

        Row row = sheet.getRow(21); // 假设要写入数据的行数是第22行
        Row row0 = sheet.getRow(5);
        
        if ((row == null)&(row0 == null)) {
            row = sheet.createRow(21); // 如果第22行不存在，创建新的行
            row0 = sheet.createRow(5);
        }

        // 写入数据到指定的列
        writeCellValue(row0, 0, dataMap.get("companyName"));
        writeCellValue(row, 1, dataMap.get("employeeName"));
        writeCellValue(row, 8, dataMap.get("workTime"));
        writeCellValue(row, 12, dataMap.get("contractOutTime"));
        writeCellValue(row, 16, dataMap.get("price"));
        writeCellValue(row, 19, dataMap.get("outTotal"));
        writeCellValue(row, 22, dataMap.get("claimPrice"));
    }

    private void writeCellValue(Row row, int column, Object value) {
        // 写入单元格数据
        Cell cell = row.createCell(column);
        if (value instanceof String) {
            cell.setCellValue((String) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        }
        // 可以根据需要扩展其他类型的数据写入
    }

    private static String readCellContent(Sheet sheet, int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);
        if (row != null) {
            Cell cell = row.getCell(colNum);
            if (cell != null) {
                // 根据单元格类型读取内容
                switch (cell.getCellType()) {
                    case STRING:
                        return cell.getStringCellValue().trim();
                    case NUMERIC:
                        return String.valueOf(cell.getNumericCellValue());
                    default:
                        return "";
                }
            }
        }
        return "";
    }
    
	//データを読取    
    public void readExcelCellType(String filePath) throws Exception {
    	
    	FileInputStream inputStream = new FileInputStream(filePath);
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheetAt(0);
		// Read title row
		Row title = sheet.getRow(0);
		if(title != null) {
			// 获取最后一列列号
//	        int lastCellNum = title.getLastCellNum();
//	        System.out.println("Last cell number: " + lastCellNum);
			int cellNum = title.getPhysicalNumberOfCells();
			System.out.println("Number of cells in title row: "+ cellNum);
			for(int i =0;i<cellNum;i++) {
				Cell cell = title.getCell(i);
				if(cell!=null) {
					System.out.println("Cell " + i + ":");
					String value=cell.getStringCellValue();
					System.out.println("Title cell value: " + value);
				}
			}
		}
		// 获取最后一行行号
//        int lastRowNum = sheet.getLastRowNum();
//        System.out.println("Last row number: " + lastRowNum);
		// Read data rows
		int rowNum = sheet.getPhysicalNumberOfRows();
		System.out.println("Number of rows: " + rowNum);
		for(int i = 1; i< rowNum; i++) {
			Row row = sheet.getRow(i);
			if(row!=null) {
				System.out.println("Row " + i + ":");
				int cellNum=row.getPhysicalNumberOfCells();
				for(int j=0;j<cellNum ;j++) {
					Cell cell= row.getCell(j);
					if(cell!=null) {
						/*
						 * 
						 */
						System.out.println("Cell " + j + ":");
						CellType cellType = cell.getCellType() ;
						String cellVal = "";
						
						switch (cellType) {
					    case STRING:
					        cellVal = cell.getStringCellValue();
					        System.out.println("String: " + cellVal);
					        break;
					    case NUMERIC:
					        if (DateUtil.isCellDateFormatted(cell)) {
					            System.out.println("Date");
					            Date date = cell.getDateCellValue();
					            cellVal = new SimpleDateFormat("yyyy-MM-dd").format(date);
					            System.out.println("Formatted Date: " + cellVal);
					        } else {
					            double numericVal = cell.getNumericCellValue();
					            cellVal = String.valueOf(numericVal);
					            System.out.println("Numeric: " + cellVal);
					        }
					        break;
					    case BLANK:
					    	cellVal = "";
					    	System.out.println("Blank");
					    	break;
					    case BOOLEAN:
					    	cellVal=String.valueOf(cell.getBooleanCellValue());
					    	System.out.println("Boolean"+ cellVal);
					    	break;
					    case ERROR:
					    	cellVal = "Error in cell";
					        System.out.println("Error");
					        break;
					    default:
					    	
                            System.out.println("Un cell type.");
                            break;
					}
					System.out.println("Cell value: " + cellVal);
					}
				}
			}
		}
		inputStream.close();
	}
    
    public void readPDF(String filePath) {
        try (PDDocument document = PDDocument.load(new File(filePath))) {
            if (!document.isEncrypted()) {
                PDFTextStripper stripper = new PDFTextStripper();
                String pdfText = stripper.getText(document);
                System.out.println("PDF内容:");
                System.out.println(pdfText);

                // 这里可以根据实际情况解析 pdfText，提取需要的数据到表格或其他结构中
                // 例如，可以使用正则表达式或字符串处理方法来解析文本

            } else {
                System.err.println("加密的PDF文件，无法读取。");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
