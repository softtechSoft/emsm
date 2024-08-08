package com.softtech.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.entity.ClaimInfo;
import com.softtech.entity.Employee;
import com.softtech.entity.RequestEntity;

@Mapper
public interface RequestMapper {
	
	List<RequestEntity> getRequestData(String month);

	/**
	 * @param month
	 * @return
	 */
	List<Employee> findEmployeeNamesByMonth(String month);

	/**
	 * @param params
	 * @return
	 */
	int countByMonthAndType(Map<String, Object> params);

	/**
	 * @return
	 */
	String getLatestYearMonth();
	
	/**
	 * @param claimInfo
	 * @return
	 */
	int insertClaim(List<ClaimInfo> claimInfo);

	/**
	 * @return
	 */
	String getMaxClaimID();
	
}
