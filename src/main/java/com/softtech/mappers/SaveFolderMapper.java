package com.softtech.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.entity.SaveFolder;

@Mapper
public interface SaveFolderMapper {

	//List<SaveFolder> getAllSaveFolder();

	SaveFolder findFileTypeCode(String findFileTypeCode);
	SaveFolder findFileTypeName(String findFileTypeName);


	//void update(SaveFolder SaveFolder);
}
