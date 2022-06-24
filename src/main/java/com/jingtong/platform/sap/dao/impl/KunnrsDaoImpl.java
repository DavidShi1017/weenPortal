package com.jingtong.platform.sap.dao.impl;

import java.util.List;
import java.util.Map;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.org.pojo.Borg;
import com.jingtong.platform.role.pojo.Role;
import com.jingtong.platform.sap.dao.KunnrsDao;
import com.jingtong.platform.sap.pojo.Credit;
import com.jingtong.platform.sap.pojo.Knvp;
import com.jingtong.platform.sap.pojo.Kunnr;
import com.jingtong.platform.sap.pojo.KunnrCompany;
import com.jingtong.platform.sap.pojo.KunnrVtweg;
import com.jingtong.platform.sap.pojo.ToolsClass;


@SuppressWarnings("rawtypes")
public class KunnrsDaoImpl extends BaseDaoImpl implements KunnrsDao {

	@Override
	public String createKunnr(Kunnr kunnr) {
		long id = (Long) getSqlMapClientTemplate().insert("sapReport.createKunnrs",kunnr);
		return id+"";
	}

	@Override
	public int updateKunnr(Kunnr kunnr) {

		return getSqlMapClientTemplate().update("sapReport.updateKunnr", kunnr);
	}


	 
	@Override
	public int getKunnrList(Kunnr customerId) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"sapReport.getKunnrCount", customerId);
	}

	@Override
	public String createKunnrCompany(KunnrCompany kunnrCompany) {
		long id = (Long) getSqlMapClientTemplate().insert("sapReport.createKunnrCompany",kunnrCompany);
		return id+"";
	}

	@Override
	public int updateKunnrCompany(KunnrCompany kunnrCompany) {
		return getSqlMapClientTemplate().update("sapReport.updateKunnrCompany", kunnrCompany);
	}

	@Override
	public int getKunnrCompanyList(KunnrCompany kunnrCompany) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"sapReport.getKunnrCompanyCount", kunnrCompany);
	}

	

}
