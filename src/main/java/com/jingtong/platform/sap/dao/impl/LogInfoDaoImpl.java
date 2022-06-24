package com.jingtong.platform.sap.dao.impl;



import java.util.List;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.message.pojo.Message;
import com.jingtong.platform.sap.dao.ILogInfoDao;
import com.jingtong.platform.sap.pojo.ExceptionLog;


@SuppressWarnings("rawtypes")
public class LogInfoDaoImpl extends BaseDaoImpl implements ILogInfoDao {

	@Override
	public void addLogInfo(ExceptionLog elog) throws Exception {
		this.getSqlMapClientTemplate().insert("exlog.insertLogInfo",elog);
	}

	@Override
	public void saveMessage(Message g) throws Exception {
		this.getSqlMapClientTemplate().insert("exlog.saveMessage", g);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ExceptionLog> searchLogList(ExceptionLog elog) throws Exception {
		return this.getSqlMapClientTemplate().queryForList("exlog.searchLogInfo",elog);
	}

	@Override
	public Integer searchLogListCount(ExceptionLog elog) throws Exception {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("exlog.searchLogInfoCount", elog);
	}

}
