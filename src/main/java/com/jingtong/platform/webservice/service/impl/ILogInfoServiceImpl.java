package com.jingtong.platform.webservice.service.impl;

import java.util.List;

import com.jingtong.platform.webservice.dao.ILogInfoDao;
import com.jingtong.platform.webservice.pojo.ExceptionLog;
import com.jingtong.platform.webservice.service.ILogInfoService;

public class ILogInfoServiceImpl implements ILogInfoService {

	private ILogInfoDao iLogInfoDao;

	public void setiLogInfoDao(ILogInfoDao iLogInfoDao) {
		this.iLogInfoDao = iLogInfoDao;
	}

	@Override
	public void addLogInfo(ExceptionLog elog) throws Exception{
		this.iLogInfoDao.addLogInfo(elog);
	}

	@Override
	public List<ExceptionLog> searchLogList(ExceptionLog elog) throws Exception {
		return this.iLogInfoDao.searchLogList(elog);
	}

	@Override
	public int searchLogListCount(ExceptionLog elog) throws Exception {
		return this.iLogInfoDao.searchLogListCount(elog);
	}
	
}
