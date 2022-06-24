package com.jingtong.platform.sap.dao.impl;

import java.util.List;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.sap.dao.ExcludeDao;
import com.jingtong.platform.sap.pojo.Exclude;

public class ExcludeDaoImpl extends BaseDaoImpl implements ExcludeDao{

	@Override
	public long saveExclude(Exclude exc) {
		// TODO Auto-generated method stub
		return (Long) this.getSqlMapClientTemplate().insert("exc.saveExclude", exc);
	}

	@Override
	public int updateExclude(Exclude exc) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().update("exc.updateExclude", exc);
		
	}

	@Override
	public int getExcludeCount() {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().delete("exc.getExcludeCount");
	}

	@Override
	public int getExcludeTotal(Exclude exc) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject("exc.getExcludeTotal", exc);
	}

	@Override
	public List<Exclude> getExcludeList(Exclude exc) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("exc.getExcludeList", exc);
	}

}
