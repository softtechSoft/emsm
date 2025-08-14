package com.softtech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.entity.SaveFolder;
import com.softtech.mappers.SaveFolderMapper;

@Service
public class SaveFolderService {

	@Autowired
	private SaveFolderMapper saveFolderMapper;

	public SaveFolder findFileTypeCode(String findFileTypeCode) {
		SaveFolder saveFolder = saveFolderMapper.findFileTypeCode(findFileTypeCode);
		if (saveFolder == null) {
            throw new RuntimeException("保存路径配置未找到: " + findFileTypeCode);
        }
		return saveFolder;
	}


}
