package com.jingtong.platform.ediDisti.dao.impl;

import java.util.List;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.customer.pojo.Customer;
import com.jingtong.platform.ediDisti.dao.IEdiDistiDao;
import com.jingtong.platform.ediDisti.pojo.EdiDisti;

public class EdiDistiDaoImpl extends BaseDaoImpl implements IEdiDistiDao{

	@Override
	public List<EdiDisti> getEdiDistiList(EdiDisti ed) {
		return (List<EdiDisti>) getSqlMapClientTemplate().queryForList("ediDisti.getEdiDistiList",ed);
	}

	public int getEdiDistiListCount(EdiDisti ed) {
		return (Integer) getSqlMapClientTemplate().queryForObject("ediDisti.getEdiDistiListCount",ed);
	}
	

	@Override
	public long createEdiDisti(EdiDisti ed) {
		return (Long)getSqlMapClientTemplate().insert("ediDisti.createEdiDisti", ed);
	}

	@Override
	public long deleteEdiDisti(EdiDisti ed) {
		return (Integer)getSqlMapClientTemplate().update("ediDisti.deleteEdiDisti", ed);
	}

}
