package com.softtech.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.entity.TransportEntity;
import com.softtech.entity.WorkInfo;

@Mapper
public interface WorkDetailListMapper {

	List<WorkInfo> getWorkInfoDetail(String month);
	List<TransportEntity> geTransportDetail(String month);
	List<TransportEntity> getWorkTransport(String month);
}
