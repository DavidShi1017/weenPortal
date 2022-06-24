package com.jingtong.platform.ireport.dao.impl;

import java.util.List;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.ireport.dao.IreportDao;
import com.jingtong.platform.ireport.pojo.IreportType;

public class ReportDaoImpl  extends BaseDaoImpl implements IreportDao{


	 
	public List<IreportType> serachReportModle(IreportType type) {
		return getSqlMapClientTemplate().queryForList(
				"ireport.getreportModleList", type);
	}


	 
	public Long saveReportModle(IreportType type) {
		return (Long) getSqlMapClientTemplate().insert("ireport.saveModle", type);
	}


	 
	public int deleteReportModle(IreportType type) {
		return getSqlMapClientTemplate().delete("ireport.deleteModle", type);
	
	}


	 
	public int modifyReportModle(IreportType type) {
		return getSqlMapClientTemplate().update("ireport.updateModle", type);
	}

}
