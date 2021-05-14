
package com.softtech.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.util.MimeTypeUtils;

import com.softtech.actionForm.WorkDetail;
import com.softtech.entity.SalaryInfo;

/**
 * 概要：対象月処理機能
 *
 * 作成者：馬@ソフトテク
 * 作成日：2021/4/10
 */

public class FileUtil {/**
	 * 機能：給料リストダウンロード
	 *
	 * @param response レスポンス
	 * @param workDetailList 対象データ
	 * @return TRUE:成功、FALSE失敗
	 *
	 * @exception なし
	 * @author @ソフトテク
	 */
	public boolean salaryDownload(HttpServletResponse  response,List<SalaryInfo> sl2){
		//エンコーディング設定
		response.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE + ";charset=Shift-JIS");
		//ダウンロードファイル名設定
		response.setHeader("Content-Disposition", "attachment; filename=\"worksheet.csv\"");

		try {
			PrintWriter pw = response.getWriter();
			String outputString1 ="社員ID,"+"社員氏名,"+"対象年月,"+"支払日,"+"基本給(単位:円),"+"残業時間,"+"不足時間,"+"残業加算(単位:円),"+"稼働不足減(単位:円),"+"交通費(単位:円),"+"手当加算(単位:円),"+"手当減算(単位:円),"+"手当理由,"+"厚生年金控除個人(単位:円),"+"厚生健康控除個人(単位:円),"+"厚生年金控除会社(単位:円),"+"厚生健康控除会社(単位:円),"+"厚生控除子育(会社)(単位:円),"+"雇用保険個人負担(単位:円),"+"雇用保険会社負担(単位:円),"+"雇用保拠出金（会社)(単位:円),"+"労災保険（会社負担のみ）(単位:円),"+"源泉控除(単位:円),"+"住民税控除(単位:円),"+"社宅家賃控除(単位:円),"+"社宅共益費控除(単位:円),"+"総額(単位:円),"+"総費用(単位:円),"+"備考,"+ "\r\n";
			pw.print(outputString1);
    for(SalaryInfo wl:sl2) {
    	String employeeID = wl.getEmployeeID();
        String employeeName = wl.getEmployeeName();
        String month = wl.getMonth();
        String paymentDate = wl.getPaymentDate();
        String base = wl.getBase();
        String overTime = wl.getOverTime();
        String shortage = wl.getShortage();
        String overTimePlus = wl.getOverTimePlus();
        String shortageReduce = wl.getShortageReduce();
        String transportExpense = wl.getTransportExpense();
        String allowancePlus = wl.getAllowancePlus();
        String allowanceReduce = wl.getAllowanceReduce();
        String allowanceReason = wl.getAllowanceReason();
        String welfarePensionSelf = wl.getWelfarePensionSelf();
        String welfareHealthSelf = wl.getWelfareHealthSelf();
        String welfarePensionComp = wl.getWelfarePensionComp();
        String welfareHealthComp = wl.getWelfareHealthComp();
        String welfareBaby = wl.getWelfareBaby();
        String eplyInsSelf = wl.getEplyInsSelf();
        String eplyInsComp = wl.getEplyInsComp();
        String eplyInsWithdraw = wl.getEplyInsWithdraw();
        String wkAcccpsIns = wl.getWkAcccpsIns();
        String withholdingTax = wl.getWithholdingTax();
        String municipalTax = wl.getMunicipalTax();
        String rental = wl.getRental();
        String rentalMgmtFee = wl.getRentalMgmtFee();
        String totalFee = wl.getTotalFee();
        String remark = wl.getRemark();
        String sum = wl.getSum();




        String outputString = employeeID + "," + employeeName + "," + month + "," + paymentDate + "," + base + "."+ overTime+ "," +shortage+ "," +overTimePlus+ "," +shortageReduce+ "," +transportExpense+ "," +allowancePlus+ "," +allowanceReduce+ "," +allowanceReason+ "," +welfarePensionSelf+ "," +welfareHealthSelf+ "," +welfarePensionComp+ "," +welfareHealthComp+ "," +welfareBaby+ "," +eplyInsSelf+ "," +eplyInsComp+ "," +eplyInsWithdraw+ "," +wkAcccpsIns+ "," +withholdingTax+ "," +municipalTax+ "," +rental+ "," +rentalMgmtFee+ "," +sum+ "," +totalFee+ "," +remark+ ","
        		+ "," + "\r\n";

               pw.print(outputString);
           }
           pw.close();
       } catch (IOException e) {
           e.printStackTrace();
           return false;
       }

       return true;
	 }
	/**
	 * 機能：勤怠リストダウンロード
	 *
	 * @param response レスポンス
	 * @param workDetailList 対象データ
	 * @return TRUE:成功、FALSE失敗
	 *
	 * @exception なし
	 * @author @ソフトテク
	 */
	public boolean workSheetDownload(HttpServletResponse  response,List<WorkDetail> workDetailList){
		//エンコーディング設定
		response.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE + ";charset=Shift-JIS");
		//ダウンロードファイル名設定
		response.setHeader("Content-Disposition", "attachment; filename=\"worksheet.csv\"");

		try {
			PrintWriter pw = response.getWriter();
			String outputString1 ="社員id,"+"社員氏名," + "対象年月," + "勤怠時間(H)," + "定期券額(円)," +"その他交通費(円）," + "\r\n";
			pw.print(outputString1);
			for(WorkDetail wl:workDetailList) {
            	String employeeID = wl.getEmployeeID();
                String employeeName = wl.getEmployeeName();
                String workMonth = wl.getWorkMonth();
                float workTime = wl.getWorkTime();
                String transportExpense =  wl.getTransportExpense();
                String transportExpense2 = transportExpense.replace(",", "");
                String transport = wl.getTransport();
                String transport2 = transport.replace(",", "");


                String outputString = employeeID + "," + employeeName + "," + workMonth + "," + workTime + "," + transportExpense2 + "," + transport2
                         + "\r\n";
                pw.print(outputString);
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
	 }
	}
