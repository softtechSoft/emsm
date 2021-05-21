package com.softtech.service;

import java.util.List;

import com.softtech.entity.ClaimInfo;

public interface ClaimListService {

	List<ClaimInfo> queryClaimList(String month,String companyName);

}
