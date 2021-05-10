package com.softtech.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.entity.Ofcfunction;

@Mapper
public interface MenuMapper {

	List<Ofcfunction> queryOfcfunction(String string);
}
