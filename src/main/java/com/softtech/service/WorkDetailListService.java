package com.softtech.service;

import java.util.List;

import com.softtech.actionForm.WorkDetailBean;

public interface WorkDetailListService {

	List<WorkDetailBean> queryWorkDetail(String month);

}
