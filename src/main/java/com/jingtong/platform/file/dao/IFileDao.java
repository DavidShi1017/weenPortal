package com.jingtong.platform.file.dao;

import com.jingtong.platform.file.pojo.BudgetFileTmp;

public interface IFileDao {

	public BudgetFileTmp getFileByFileId(Long fileId);
}
