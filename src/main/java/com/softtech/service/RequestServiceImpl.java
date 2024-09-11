package com.softtech.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.RequestFromBean;
import com.softtech.entity.ClaimInfo;
import com.softtech.entity.Employee;
import com.softtech.entity.RequestEntity;
import com.softtech.entity.RequestToDownloadEntity;
import com.softtech.mappers.RequestMapper;
import com.softtech.util.DataUtil;
import com.softtech.util.DateUtil;
import com.softtech.util.ExportUtils;

@Service
public class RequestServiceImpl {

	private static final DecimalFormat df_MONEY = new DecimalFormat("#,##0");
    private static final DecimalFormat df_TIME = new DecimalFormat("#0.00");
	@Autowired
	RequestMapper mapper;

	//DBToFormBean用
	public List<RequestFromBean> transferEntityToFormBean(List<RequestEntity> ret1,String claimMonth) {
		if(ret1 == null ) return new ArrayList<RequestFromBean>();

		List<RequestFromBean> rfc = new ArrayList<RequestFromBean>();
		for(RequestEntity re:ret1) {
			RequestFromBean requestDetail = new RequestFromBean();
			//社員氏名
			requestDetail.setEmployeeName(re.getEmployeeName());
			//会社名
			requestDetail.setCompanyName(re.getCompanyName());
			requestDetail.setContractID(re.getContractID());
			//契約時間最小
			requestDetail.setContractLowerTime(re.getContractLowerTime());
			int contractLowerTime = Integer.parseInt(requestDetail.getContractLowerTime());
			//契約時間最大
			requestDetail.setContractUpperTime(re.getContractUpperTime());
			int contractUpperTime = Integer.parseInt(requestDetail.getContractUpperTime());
			//契約時間
			requestDetail.setContractTime(requestDetail.getContractLowerTime()+"~"+requestDetail.getContractUpperTime());
//			requestDetail.setContractTime(Integer.parseInt(re.getContractLowerTime())+"~"+Integer.parseInt(re.getContractUpperTime()));
			//標準単価
			int price =re.getPrice();
			requestDetail.setPrice(String.format("%,d",price));
			//稼働時間
			float workTime= re.getWorkTime();
			requestDetail.setWorkTime(String.format("%.2f",workTime));
			//控除単価
			int lowerPrice = re.getLowerPrice();
			requestDetail.setLowerPrice(String.format("%,d",lowerPrice));
			//残業単価

			int upperPrice = re.getUpperPrice();
			requestDetail.setUpperPrice(String.format("%,d",upperPrice));
			//過/不足時間
			int upperTime=0;//初期化
            int lowerTime=0;//初期化

//			if(contractLowerTime!=0&&contractUpperTime!=0) {
//				lowerPrice = price / contractLowerTime;
//				upperPrice = price / contractUpperTime;
//	            requestDetail.setLowerPrice(df_MONEY.format(lowerPrice));
//	            requestDetail.setUpperPrice(df_MONEY.format(upperPrice));

				if(workTime > contractUpperTime) {
					upperTime =(int)(workTime-contractUpperTime);
					requestDetail.setUpperTime(df_TIME.format(upperTime));
					requestDetail.setLowerTime("0");
				}else if(workTime < contractLowerTime) {
					lowerTime =(int)(contractLowerTime-workTime);
					requestDetail.setLowerTime(df_TIME.format(lowerTime));
					requestDetail.setUpperTime("0");
				}else {

					requestDetail.setUpperTime("0");
					requestDetail.setLowerTime("0");
				}
//			}

			//残業額
			int lowerToatal = lowerTime*lowerPrice;
            requestDetail.setLowerTotal(df_MONEY.format(lowerToatal));
            //控除額
            int upperToatal = upperTime*upperPrice;
			requestDetail.setUpperTotal(df_MONEY.format(upperToatal));
			//税抜総額
			int claimPrice = price+upperToatal-lowerToatal;
			requestDetail.setClaimPrice(df_MONEY.format(claimPrice));
			//交通費
			int transport =0;
			//出張旅費
			int businessTrip= 0;
			//特別請求
			int specialClaim =0;
			//請求税込額

			transport = re.getTransport();
		    businessTrip = re.getBusinessTrip();
		    specialClaim = re.getSpecialClaim();

			// 设置属性值
			requestDetail.setTransport(String.format("%,d", transport));
			requestDetail.setBusinessTrip(String.format("%,d", businessTrip));
			requestDetail.setSpecialClaim(String.format("%,d", specialClaim));

			int sum=(int)(claimPrice*0.1)+claimPrice+transport+businessTrip+specialClaim;
			requestDetail.setSum(String.format("%,d",sum));
//			requestDetail.setSum("0");
			requestDetail.setClaimMonth(claimMonth);

			rfc.add(requestDetail);
		}
		return rfc;
	}


	//DB用
	public List<RequestEntity> getRequestData(String monthP){
		List<RequestEntity>elist=mapper.getRequestData(monthP);
		return elist;
	}

	public boolean isDataEmpty(String month) {
	    int totalEmployees = getCountByMonthAndType(month, "allEmployees");
	    int workInfoCount = getCountByMonthAndType(month, "workTime");
	    int transportCount = getCountByMonthAndType(month, "transport");


	    return workInfoCount < totalEmployees || transportCount < totalEmployees;
	}

    public boolean isClaimEmpty(String month) {
        int claimCount = getCountByMonthAndType(month, "claimMonth");
        return claimCount == 0;
    }

    private int getCountByMonthAndType(String month, String type) {
        Map<String, Object> params = new HashMap<>();
        params.put("month", month);
        params.put("type", type);
        return mapper.countByMonthAndType(params);
    }

    public List<Employee> getEmployeeNamesByMonth(String month) {
        return mapper.findEmployeeNamesByMonth(month);
    }



	/**
	 * @param param
	 * @return
	 */
	public String getNextYearMonth(String param) {
		// TODO 自動生成されたメソッド・スタブ
		return ExportUtils.getNextMonth(param);
	}

	/**
	 * @return
	 */
	public String queryLatestYearMonth() {
		String month = mapper.getLatestYearMonth();
		return month;
	}


	/**
	 * @param requestDetailList1
	 * @return
	 */
	public List<ClaimInfo> transforFormBeanToEntity(List<RequestFromBean> requestDetailList1) {
		if (requestDetailList1 == null || requestDetailList1.isEmpty()) {
	        throw new IllegalArgumentException("Request detail list cannot be null or empty");
	    }

		List<ClaimInfo> claimInfoList = new ArrayList<>();
		// 从数据库中获取当前最大 ID
	    String maxID = mapper.getMaxClaimID();
	    String currentID = getNextID(maxID, 2);

    	for (RequestFromBean requestFromBean : requestDetailList1) {
    		ClaimInfo claimInfo = new ClaimInfo();

        	String nextID = getNextID(currentID, 2);
        	currentID = nextID; // 更新当前 ID 为最新生成的 ID
    		claimInfo.setClaimID(nextID);

			claimInfo.setContractID(requestFromBean.getContractID());

			// 检查处理后的月份
		    String month = DateUtil.chgMonthToYM(requestFromBean.getClaimMonth());
		    System.out.println("Original Claim Month: " + requestFromBean.getClaimMonth());
		    System.out.println("Processed Claim Month: " + month);
		    claimInfo.setClaimMonth(month);

		    // 检查处理后的金额
		    String sum = DataUtil.deleteComma(requestFromBean.getSum());
		    System.out.println("Original Sum: " + requestFromBean.getSum());
		    System.out.println("Processed Sum: " + sum);
		    claimInfo.setSum(sum);

			claimInfo.setBusinessTrip(requestFromBean.getBusinessTrip());
			claimInfo.setClaimStatus("2");

			claimInfoList.add(claimInfo);
    	}
		return claimInfoList;
	}


	/*
	 * 機能：採番する
	 *
	 * @param nowID 現在ID
	 * @param headerIndex ヘッダー桁数
	 * @return 新ID
	 * @exception なし
	 * @author 孫@ソフトテク
	 */

	public boolean processAndInsertClaims(String month) {
	    try {
	        // Fetch data from the database
	        List<RequestEntity> claimDatas = mapper.getDataForMonth(month);

	        // Process data
	        for (RequestEntity claimInfo : claimDatas) {
	            // Perform calculations
	            float workTime = claimInfo.getWorkTime();
	            int upperTime = claimInfo.getUpperTime();
	            int lowerTime = claimInfo.getLowerTime();
	            int price = claimInfo.getPrice();
	            int upperPrice = claimInfo.getUpperPrice();
	            int lowerPrice = claimInfo.getLowerPrice();

	            // Calculate exceTime, addpayOff, deficiTime, minusPayOff
	            int exceTime = (int) ((workTime > upperTime) ? workTime - upperTime : 0);
	            int addpayOff = (workTime > upperTime) ? exceTime * upperPrice : 0;
	            int deficiTime = (int) ((workTime < lowerTime) ? lowerTime - workTime : 0);
	            int minusPayOff = (workTime < lowerTime) ? deficiTime * lowerPrice : 0;

	            // Calculate sum
	            int sum = (int) ((price + addpayOff - minusPayOff) * 1.1 + claimInfo.getTransport() + claimInfo.getBusinessTrip());

	            // Set calculated values
	            claimInfo.setExceTime(exceTime);
	            claimInfo.setAddpayOff(addpayOff);
	            claimInfo.setDeficiTime(deficiTime);
	            claimInfo.setMinusPayOff(minusPayOff);
	            claimInfo.setSum(sum);
	            claimInfo.setTaxRate("10");
	            claimInfo.setConsumpTax((price + addpayOff - minusPayOff)* 0.1);
	            claimInfo.setClaimStatus("2");
	            claimInfo.setClaimID(getNextID(mapper.getMaxClaimID(),8));
	            System.out.print(claimInfo);
	            // Insert into database
	            mapper.insertClaimInfo(claimInfo);
	        }
	        return true; // Success
	    } catch (Exception e) {
	        e.printStackTrace(); // Log the exception
	        return false; // Failure
	    }
	}



	public String getNextID(String currentID, int length) {
	    if (currentID == null || currentID.isEmpty()) {
	        return generateInitialID(length);
	    }


	    int numberPart = Integer.parseInt(currentID.substring(2));
	    numberPart++;


	    return String.format("CM%0" + length + "d", numberPart);
	}

	private String generateInitialID(int length) {

	    return String.format("CM%0" + length + "d", 1);
	}


	/**
	 * @param claimInfo
	 * @return
	 */



	/**
	 * @param requestDetailList
	 * @return
	 */
	public List<RequestToDownloadEntity> transforFormBeanToDownloadEntity(List<RequestFromBean> requestDetailList) {
		// TODO 自動生成されたメソッド・スタブ
		if (requestDetailList == null || requestDetailList.isEmpty()) {
	        throw new IllegalArgumentException("Request detail list cannot be null or empty");
	    }

		List<RequestToDownloadEntity> downloadList = new ArrayList<>();

    	for (RequestFromBean requestFromBean : requestDetailList) {
    		RequestToDownloadEntity claimDownload = new RequestToDownloadEntity();



		    String month = DateUtil.chgMonthToYM(requestFromBean.getClaimMonth());
		    System.out.println("Original Claim Month: " + requestFromBean.getClaimMonth());
		    System.out.println("Processed Claim Month: " + month);
		    claimDownload.setClaimMonth(month);

		    claimDownload.setCompanyName(requestFromBean.getCompanyName());
		    claimDownload.setEmployeeName(requestFromBean.getEmployeeName());
			claimDownload.setContractLowerTime(requestFromBean.getContractLowerTime());
			claimDownload.setContractUpperTime(requestFromBean.getContractUpperTime());
			claimDownload.setWorkTime(requestFromBean.getWorkTime());
			claimDownload.setPrice(requestFromBean.getPrice());
			claimDownload.setSum(requestFromBean.getSum());
			double workTime = Double.parseDouble(requestFromBean.getWorkTime());
			claimDownload.setWorkTime(String.valueOf((int) workTime));
			//控除単価
			int lowerPrice = Integer.parseInt(requestFromBean.getLowerPrice().replace(",", ""));
			claimDownload.setLowerPrice(String.valueOf(lowerPrice));
			//残業単価

			int upperPrice = Integer.parseInt(requestFromBean.getUpperPrice().replace(",", ""));
			claimDownload.setUpperPrice(String.valueOf( upperPrice));
			//過/不足時間
			int upperTime=0;//初期化
            int lowerTime=0;//初期化



            if(workTime > Integer.parseInt(requestFromBean.getContractUpperTime())) {
				upperTime =(int)(workTime-Integer.parseInt(requestFromBean.getContractUpperTime()));
				claimDownload.setUpperTime(df_TIME.format(upperTime));
				claimDownload.setLowerTime("0");
			}else if(workTime < Integer.parseInt(requestFromBean.getContractLowerTime())) {
				lowerTime =(int)(Integer.parseInt(requestFromBean.getContractLowerTime())-workTime);
				claimDownload.setLowerTime(df_TIME.format(lowerTime));
				claimDownload.setUpperTime("0");
			}else {

				claimDownload.setUpperTime("0");
				claimDownload.setLowerTime("0");
			}

			//残業額
			int lowerToatal = lowerTime*lowerPrice;
			claimDownload.setLowerTotal(df_MONEY.format(lowerToatal));
            //控除額
            int upperToatal = upperTime*upperPrice;
            claimDownload.setUpperTotal(df_MONEY.format(upperToatal));

			downloadList.add(claimDownload);
    	}
		return downloadList;

	}




}
