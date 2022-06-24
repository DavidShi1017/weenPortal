package com.jingtong.platform.file.dao.impl;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.file.dao.IFileDao;
import com.jingtong.platform.file.pojo.BudgetFileTmp;

public class FileDaoImpl extends BaseDaoImpl implements IFileDao{

	 
	public BudgetFileTmp getFileByFileId(Long fileId) {
		return (BudgetFileTmp) getSqlMapClientTemplate().queryForObject("files.queryFileTmpByKey", fileId);
	}

}
