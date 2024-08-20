package com.softtech.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softtech.actionForm.TblJournalDetailFormBean;
import com.softtech.entity.TblAccount;
import com.softtech.entity.TblJournalDetail;
import com.softtech.mappers.TblJournalDetailMapper;
@Service
public class TblJournalDetailServiceImpl implements TblJournalDetailService {

	 @Autowired
	    private TblJournalDetailMapper journalDetailMapper;

	    // すべての仕訳明細を取得する
	    @Override
	    public List<TblJournalDetail> getAllJournalDetails() {
	        return journalDetailMapper.getAllJournalDetails();
	    }

	    // 仕訳明細を追加する

	    // 仕訳明細を更新する
	    @Override
	    public void updateJournalDetail(TblJournalDetail journalDetail) {
	        journalDetailMapper.updateJournalDetail(journalDetail);
	    }

	    // 行番号による仕訳明細の取得
	    @Override
	    public TblJournalDetail getJournalDetailByLineNumber(int lineNumber) {
	        // 実装が必要
	        return null;
	    }

	    // すべてのアカウントを取得する
	    @Override
	    public List<TblAccount> getAllAccounts() {
	        // 実装が必要
	        return journalDetailMapper.getAllAccounts();
	    }
	    @Override
	    public int getNextLineNumber() {
	        Integer maxLineNumber = journalDetailMapper.getMaxLineNumber();
	        	return (maxLineNumber != null) ? maxLineNumber : 0;
	        }
	    @Override
	    public int getMaxLineNumber() {
	        return journalDetailMapper.getMaxLineNumber();  // 実装
	    }
	    @Transactional
	    public void addJournal(TblJournalDetailFormBean tblJournalDetailFormBean) {
	        // Tạo đối tượng TblJournalDetail cho từng dòng nhật ký mới và lưu vào cơ sở dữ liệu
	        TblJournalDetail journalDetail = new TblJournalDetail();

	        // Thiết lập các giá trị từ form vào đối tượng journalDetail
	        journalDetail.setdebitAccountTitleID(tblJournalDetailFormBean.getdebitAccountTitleID());
	        journalDetail.setcreditAccountTitleID(tblJournalDetailFormBean.getcreditAccountTitleID());
	        journalDetail.setdebitAccountTitleName(tblJournalDetailFormBean.getdebitAccountTitleName());
	        journalDetail.setcreditAccountTitleName(tblJournalDetailFormBean.getcreditAccountTitleName());
	        journalDetail.setdebitcdTaxationKbn(tblJournalDetailFormBean.getdebitcdTaxationKbn());
	        journalDetail.setcreditcdTaxationKbn(tblJournalDetailFormBean.getcreditcdTaxationKbn());
	        journalDetail.setdebitcdCTaxPriceKbn(tblJournalDetailFormBean.getdebitcdCTaxPriceKbn());
	        journalDetail.setcreditcdCTaxPriceKbn(tblJournalDetailFormBean.getcreditcdCTaxPriceKbn());
	        journalDetail.setdebitTransValue(tblJournalDetailFormBean.getdebitTransValue());
	        journalDetail.setcreditTransValue(tblJournalDetailFormBean.getcreditTransValue());
	        journalDetail.setdebitDescription(tblJournalDetailFormBean.getdebitDescription());
	        journalDetail.setcreditDescription(tblJournalDetailFormBean.getcreditDescription());
	        journalDetail.setUid(tblJournalDetailFormBean.getUid());
	        journalDetail.setLineNumber(tblJournalDetailFormBean.getLineNumber());
	        String bookDateStr = tblJournalDetailFormBean.getBookDate(); // Giả sử chuỗi là '2024-08-04 00:00:00.0'
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
	        LocalDateTime localDateTime = LocalDateTime.parse(bookDateStr, formatter);
	        Timestamp bookDateTimestamp = Timestamp.valueOf(localDateTime);

	        journalDetail.setBookDate(bookDateTimestamp);


	        // Lưu đối tượng journalDetail vào cơ sở dữ liệu
	        journalDetailMapper.insertJournalDetail(journalDetail);
	    }


}