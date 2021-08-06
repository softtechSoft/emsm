package com.softtech.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.WorkDetailBean;
import com.softtech.entity.TransportEntity;
import com.softtech.mappers.WorkDetailListMapper;
import com.softtech.util.DateUtil;
/**
 * 概要：勤怠リストのservice
 *
 * 作成者：馬＠ソフトテク
 * 作成日：2021/04/10
 */
@Service
public class WorkDetailListServiceImpl implements WorkDetailListService{
	@Autowired
	WorkDetailListMapper workDetailListMapper;
	@Override
	public List<WorkDetailBean> queryWorkDetail(String month) {

        // YYYY/MM→yyyymmに変換
		String monthP = DateUtil.chgMonthToYM(month);
		// 勤怠リストを取得する
		List<TransportEntity> transportLst = workDetailListMapper.getWorkTransport(monthP);

		// 勤怠情報へ変更する。
		List<WorkDetailBean> rtn  = transfter(transportLst);
		return  rtn;
	}

	/**
	 * 機能：DBから取得したデータを勤怠情報へ変換する。
	 *
	 * @param lst DBから取得したデータ
	 * @return 勤怠情報リスト
	 *
	 * @author 馬@ソフトテク
	 */
	private List<WorkDetailBean> transfter(List<TransportEntity> lst){
		if(lst == null ) return new ArrayList<WorkDetailBean>();

		List<WorkDetailBean> rtn  =new ArrayList<WorkDetailBean>();
		for(TransportEntity tt : lst) {
			WorkDetailBean workDetail = new WorkDetailBean();
			//社員ID
			workDetail.setEmployeeID(tt.getEmployeeID());
			//社員氏名
			workDetail.setEmployeeName(tt.getEmployeeName());
			//対象月
			workDetail.setWorkMonth(tt.getWorkMonth());
			//勤怠時間（H)
			workDetail.setWorkTime(tt.getWorkTime());
			//定期券額（円）
			workDetail.setTransportExpense(DateUtil.formatTosepara(tt.getTransportExpense1()));
			//交通費（定期券以外）(円）
			workDetail.setTransport(DateUtil.formatTosepara(tt.getTransport()));
			//出張費
			workDetail.setBusinessTrip((tt.getBusinessTrip()));

			rtn.add(workDetail);
		}

		return rtn;
	}

}
