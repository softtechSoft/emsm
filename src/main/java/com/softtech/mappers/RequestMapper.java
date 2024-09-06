package com.softtech.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

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


	/**
	 * @return
	 */
	String getMaxClaimID();

	 int insertClaimInfo(RequestEntity claimInfo);

	    /**
	     * Retrieve data for a specific month and map it to ClaimInfo objects
	     * @param month the month for which data is retrieved
	     * @return a list of ClaimInfo objects
	     */
	    List<RequestEntity> getDataForMonth(String month);
	    int countByMonthAndEmployee(Map<String, Object> params);
}
