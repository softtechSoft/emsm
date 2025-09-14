
package com.softtech.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.util.MimeTypeUtils;

import com.softtech.actionForm.WelfareBean;
import com.softtech.actionForm.WorkDetailBean;
import com.softtech.entity.ClaimInfo;
import com.softtech.entity.SalaryInfoEntity;

/**
 * 概要：ファイル処理機能
 *
 * 作成者：@ソフトテク
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
	public boolean salaryDownload(HttpServletResponse  response,List<SalaryInfoEntity> sl2){
		//エンコーディング設定
		response.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE + ";charset=Shift-JIS");
		//ダウンロードファイル名設定
		response.setHeader("Content-Disposition", "attachment; filename=\"salarylist.csv\"");

		try {
			PrintWriter pw = response.getWriter();
			String outputString1 ="社員ID,"
									+"社員氏名,"
									+"対象年月,"
									+"支払日,"
									+"基本給(円),"
									+"残業時間(H),"
									+"不足時間(H),"
									+"残業加算(円),"
									+"稼働不足減(円),"
									+"交通費(円),"
									+"手当加算(円),"
									+"手当減算(円),"
									+"手当理由,"
									+"厚生年金控除個人(円),"
									+"厚生健康控除個人(円),"
									+"厚生年金控除会社(円),"
									+"厚生健康控除会社(円),"
									+"厚生控除子育(会社)(円),"
									+"雇用保険個人負担(円),"
									+"雇用保険会社負担(円),"
									+"雇用保拠出金（会社)(円),"
									+"労災保険（会社負担のみ）(円),"
									+"源泉控除(円),"
									+"住民税控除(円),"
									+"社宅家賃控除(円),"
									+"社宅共益費控除(円),"
									+"総額(円),"
									+"総費用(円),"
									+"備考"
									+ "\r\n";
			pw.print(outputString1);
            for(SalaryInfoEntity wl:sl2) {
		    	String employeeID = wl.getEmployeeID();
		        String employeeName = wl.getEmployeeName();
		        String month = wl.getMonth();
		        String paymentDate = wl.getPaymentDate();
		        String base = wl.getBase();
		        String base1 = base.replace(",", "");
		        String overTime = wl.getOverTime();
		        String shortage = wl.getShortage();
		        String overTimePlus = wl.getOverTimePlus();
		        String overTimePlus1 = overTimePlus.replace(",", "");
		        String shortageReduce = wl.getShortageReduce();
		        String shortageReduce1 = shortageReduce.replace(",", "");
		        String transportExpense = wl.getTransportExpense();
		        String transportExpense1 = transportExpense.replace(",", "");
		        String allowancePlus = wl.getAllowancePlus();
		        String allowancePlus1 = allowancePlus.replace(",", "");
		        String allowanceReduce = wl.getAllowanceReduce();
		        String allowanceReduce1 = allowanceReduce.replace(",", "");
		        String allowanceReason = wl.getAllowanceReason();
		        String welfarePensionSelf = wl.getWelfarePensionSelf();
		        String welfarePensionSelf1 = welfarePensionSelf.replace(",", "");
		        String welfareHealthSelf = wl.getWelfareHealthSelf();
		        String welfareHealthSelf1 = welfareHealthSelf.replace(",", "");
		        String welfarePensionComp = wl.getWelfarePensionComp();
		        String welfarePensionComp1 = welfarePensionComp.replace(",", "");
		        String welfareHealthComp = wl.getWelfareHealthComp();
		        String welfareHealthComp1 = welfareHealthComp.replace(",", "");
		        String welfareBaby = wl.getWelfareBaby();
		        String welfareBaby1 = welfareBaby.replace(",", "");
		        String eplyInsSelf = wl.getEplyInsSelf();
		        String eplyInsSelf1 = eplyInsSelf.replace(",", "");
		        String eplyInsComp = wl.getEplyInsComp();
		        String eplyInsComp1 = eplyInsComp.replace(",", "");
		        String eplyInsWithdraw = wl.getEplyInsWithdraw();
		        String eplyInsWithdraw1 = eplyInsWithdraw.replace(",", "");
		        String wkAcccpsIns = wl.getWkAcccpsIns();
		        String wkAcccpsIns1 = wkAcccpsIns.replace(",", "");
		        String withholdingTax = wl.getWithholdingTax();
		        String withholdingTax1 = withholdingTax.replace(",", "");
		        String municipalTax = wl.getMunicipalTax();
		        String municipalTax1 = municipalTax.replace(",", "");
		        String rental = wl.getRental();
		        String rental1 = rental.replace(",", "");
		        String rentalMgmtFee = wl.getRentalMgmtFee();
		        String rentalMgmtFee1 = rentalMgmtFee.replace(",", "");
		        String totalFee = wl.getTotalFee();
		        String totalFee1 = totalFee.replace(",", "");
		        String remark = wl.getRemark();
		        String sum = wl.getSum();
		        String sum1 = sum.replace(",", "");
		        String outputString = employeeID + ","
		        					+ employeeName + ","
		        					+ month + ","
		        					+ paymentDate + ","
		        					+ base1 + ","
		        					+ overTime+ ","
		        					+shortage+ ","
		        					+overTimePlus1+ ","
		        					+shortageReduce1+ ","
		        					+transportExpense1+ ","
		        					+allowancePlus1+ ","
		        					+allowanceReduce1+ ","
		        					+allowanceReason+ ","
		        					+welfarePensionSelf1+ ","
		        					+welfareHealthSelf1+ ","
		        					+welfarePensionComp1+ ","
		        					+welfareHealthComp1+ ","
		        					+welfareBaby1+ ","
		        					+eplyInsSelf1+ ","
		        					+eplyInsComp1+ ","
		        					+eplyInsWithdraw1+ ","
		        					+wkAcccpsIns1+ ","
		        					+withholdingTax1+ ","
		        					+municipalTax1+ ","
		        					+rental1+ ","
		        					+rentalMgmtFee1+ ","
		        					+sum1+ ","
		        					+totalFee1+ ","
		        					+remark+ ","
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
	public boolean workSheetDownload(HttpServletResponse  response,List<WorkDetailBean> workDetailList){
		//エンコーディング設定
		response.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE + ";charset=Shift-JIS");
		//ダウンロードファイル名設定
		response.setHeader("Content-Disposition", "attachment; filename=\"worksheet.csv\"");

		try {
			PrintWriter pw = response.getWriter();
			String outputString1 ="社員id,"+"社員氏名," + "対象年月," + "勤怠時間(H)," + "定期券額(円)," +"その他交通費(円）," + "\r\n";
			pw.print(outputString1);
			for(WorkDetailBean wl:workDetailList) {
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
	/**
	 * 機能：請求リストダウンロード
	 *
	 * @param response レスポンス
	 * @param ClaimList 対象データ
	 * @return TRUE:成功、FALSE失敗
	 *
	 * @exception なし
	 * @author @ソフトテク
	 */
	public boolean claimDownload(HttpServletResponse  response, List<ClaimInfo> cl2){
		//エンコーディング設定
		response.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE + ";charset=Shift-JIS");
		//ダウンロードファイル名設定
		response.setHeader("Content-Disposition", "attachment; filename=\"claimList.csv\"");

		try {
			PrintWriter pw = response.getWriter();
			String outputString1 ="請求ID,"+"契約ID,"+"取引先名称," + "請求月," + "社員氏名," + "稼働時間," +"単価," + "過稼働時間,"+"加算額," + "不足稼働時間,"+ "減算額（円),"+ "交通費（円）,"+ "出張旅費(円）,"+ "消費税率,"+ "消費税,"
					+ "合計,"+ "特別請求,"+ "請求ステータス,"+"\r\n";
			pw.print(outputString1);
			for(ClaimInfo cl:cl2) {
				String claimID=cl.getClaimID();
				String contractID=cl.getContractID();
				String companyName=cl.getCompanyName();
				String claimMonth=cl.getClaimMonth();
				String employeeName=cl.getEmployeeName();
				String workTime=cl.getWorkTime();
				String price=cl.getPrice();
				String price1 = price.replace(",", "");
				String exceTime=cl.getExceTime();
				String addpayOff=cl.getAddpayOff();
				String addpayOff1 = addpayOff.replace(",", "");
				String deficiTime=cl.getDeficiTime();
				String minusPayOff=cl.getMinusPayOff();
				String minusPayOff1 = minusPayOff.replace(",", "");
				String transport=cl.getTransport();
				String transport1 = transport.replace(",", "");
				String businessTrip=cl.getBusinessTrip();
				String businessTrip1 = businessTrip.replace(",", "");
				String taxRate=cl.getTaxRate();
				String consumpTax=cl.getConsumpTax();
				String consumpTax1 = consumpTax.replace(",", "");
				String sum=cl.getSum();
				String sum1 = sum.replace(",", "");
				String specialClaim=cl.getSpecialClaim();
				String claimStatus=cl.getClaimStatus() ;


                String outputString = claimID + "," + contractID + ","  + companyName + ","+ claimMonth + "," + employeeName + "," + workTime + "," + price1 + "," +
                		exceTime+ "," +addpayOff1+ "," +deficiTime+ "," +minusPayOff1+ "," +transport1+ "," + businessTrip1+ "," +taxRate+ "," +consumpTax1+ "," +
                		sum1+ "," +specialClaim+ "," +claimStatus


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
	/**
	 * 機能：福祉控除リストダウンロード
	 *
	 * @param response レスポンス
	 * @param workDetailList 対象データ
	 * @return TRUE:成功、FALSE失敗
	 *
	 * @exception なし
	 * @author @ソフトテク
	 */
	public boolean welfareDownload(HttpServletResponse  response,List<WelfareBean> welfareBeanList){
		//エンコーディング設定
		response.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE + ";charset=Shift-JIS");
		//ダウンロードファイル名設定
		response.setHeader("Content-Disposition", "attachment; filename=\"welfareList.csv\"");

		try {
			PrintWriter pw = response.getWriter();
			String outputString1 ="社員id,"+"社員氏名," + "控除開始日,"  + "厚生年金控除個人,"
					+ "厚生年金控除会社," + "厚生健康控除会社," + "厚生健康控除個人," + "厚生控除子育(会社),"
					+ "雇用保険個人負担," + "雇用保険会社負担,"+ "雇用保拠出金（会社),"+ "労災保険（会社負担のみ）,"
					+"源泉控除,"+"住民税控除,"+"社宅家賃控除,"+"社宅管理費控除,"
					+"控除ステータス,"+"作成日,"+"作成者,"+"更新日,"+"更新者,"+ "\r\n";
			pw.print(outputString1);
			for(WelfareBean wl:welfareBeanList) {
            	String employeeID = wl.getEmployeeID();
                String employeeName = wl.getEmployeeName();
                String startDate = wl.getStartDate();
                String welfarePensionSelf = wl.getWelfarePensionSelf();
                String welfarePensionComp = wl.getWelfarePensionComp();
                String welfareHealthComp = wl.getWelfareHealthComp();
                String welfareHealthSelf = wl.getWelfareHealthSelf();
                String welfareBaby = wl.getWelfareBaby();
                String eplyInsSelf = wl.getEplyInsSelf();
                String eplyInsComp = wl.getEplyInsComp();
                String eplyInsWithdraw = wl.getEplyInsWithdraw();
                String wkAcccpsIns = wl.getWkAcccpsIns();
                String withholdingTax = wl.getWithholdingTax();
                String municipalTax = wl.getMunicipalTax1();
                String rental = wl.getRental();
                String rentalMgmtFee = wl.getRentalMgmtFee();
                String status = wl.getStatus();
                String insertDate = wl.getInsertDate();
                String insertEmployee = wl.getInsertEmployee();
                String updateDate = wl.getUpdateDate();
                String updateEmployee = wl.getUpdateEmployee();



                String outputString = employeeID + "," + employeeName + "," + startDate + "," + welfarePensionSelf
                		+ "," + welfarePensionComp + "," + welfareHealthComp + "," + welfareHealthSelf + "," + welfareBaby + "," + eplyInsSelf
                		+ "," + eplyInsComp + "," + eplyInsWithdraw + "," + wkAcccpsIns
                		+ "," + withholdingTax + "," + municipalTax + "," + rental
                + "," + rentalMgmtFee + "," + status + "," + insertDate + ","+ insertEmployee + "," + updateDate+ "," +updateEmployee
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
