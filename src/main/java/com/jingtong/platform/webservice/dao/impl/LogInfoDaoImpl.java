package com.jingtong.platform.webservice.dao.impl;

import java.util.List;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.webservice.dao.ILogInfoDao;
import com.jingtong.platform.webservice.pojo.ExceptionLog;

@SuppressWarnings("rawtypes")
public class LogInfoDaoImpl extends BaseDaoImpl implements ILogInfoDao {

	@Override
	public void addLogInfo(ExceptionLog elog) throws Exception {
		this.getSqlMapClientTemplate().insert("elog.insertLogInfo",elog);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExceptionLog> searchLogList(ExceptionLog elog) throws Exception {
		return this.getSqlMapClientTemplate().queryForList("elog.searchLogInfo",elog);
	}

	@Override
	public Integer searchLogListCount(ExceptionLog elog) throws Exception {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("elog.searchLogInfoCount", elog);
	}

}
