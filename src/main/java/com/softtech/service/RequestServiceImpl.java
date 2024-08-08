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
			int lowerPrice=0;//初期化
			//残業単価
			int upperPrice=0;//初期化
			//過/不足時間
			int upperTime=0;//初期化
            int lowerTime=0;//初期化
            
			if(contractLowerTime!=0&&contractUpperTime!=0) {
				lowerPrice = price / contractLowerTime;
				upperPrice = price / contractUpperTime;
	            requestDetail.setLowerPrice(df_MONEY.format(lowerPrice));
	            requestDetail.setUpperPrice(df_MONEY.format(upperPrice));
	            
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
			}
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
        int workInfoCount = getCountByMonthAndType(month, "workTime");
//        int transportCount = getCountByMonthAndType(month, "transport");
        return workInfoCount == 0 ;//|| transportCount == 0;
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
			claimInfo.setClaimStatus("0");
			
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
	public static String getNextID(String nowID, int headerIndex) {
		String nextID = "CM000"; // 初始值
		// 如果 nowID 为 null 或者长度小于等于 headerIndex，返回 nowID
	    if (nowID == null || nowID.length() <= headerIndex) return nextID;

	    // 取得 header 部分，假设前缀为 "CM"
	    String header = nowID.substring(0, headerIndex);

	    // 取得数字部分并转化为整数，加 1 生成下一个数字
	    String numberPart = nowID.substring(headerIndex);
	    int nextNumber = Integer.parseInt(numberPart) + 1;

	    // 格式化数字部分，确保为 3 位数，不足补零
	    String formattedNextNumber = String.format("%03d", nextNumber);
	 // 合成新的 ID
        nextID = header + formattedNextNumber;

	    // 返回新的 ID
	    return nextID;
	}


	/**
	 * @param claimInfo
	 * @return
	 */
	public boolean insertClaim(List<ClaimInfo> claimInfo) {
		// TODO 自動生成されたメソッド・スタブ
		int rowsUpdated=mapper.insertClaim(claimInfo);
		return rowsUpdated>0;
	}


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
			
			downloadList.add(claimDownload);
    	}
		return downloadList;
		
	}

	
  

}
