package com.jingtong.platform.file.service.impl;

import com.jingtong.platform.file.dao.IFileDao;
import com.jingtong.platform.file.pojo.BudgetFileTmp;
import com.jingtong.platform.file.service.IFileService;

public class FileServiceImpl implements IFileService{

	private IFileDao fileDao;
	
	 
	public BudgetFileTmp getFileByFileId(Long fileId) {
		return fileDao.getFileByFileId(fileId);
	}

	public IFileDao getFileDao() {
		return fileDao;
	}

	public void setFileDao(IFileDao fileDao) {
		this.fileDao = fileDao;
	}

}
