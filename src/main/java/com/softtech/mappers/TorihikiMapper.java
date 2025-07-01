package com.softtech.mappers;

import com.softtech.entity.Torihiki;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface TorihikiMapper {
    List<Torihiki> selectAll();
    
    Torihiki selectByPrimaryKey(String companyID);
    
    List<Torihiki> search(String keyword);
    
    int insert(Torihiki record);
    
    int updateByPrimaryKey(Torihiki record);
    
    int deleteByPrimaryKey(String companyID);
    
    @Select("SELECT MAX(companyID) FROM company")
    String getMaxCompanyId();
} 