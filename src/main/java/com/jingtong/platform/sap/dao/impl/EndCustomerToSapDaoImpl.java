package com.jingtong.platform.sap.dao.impl;

import java.util.List;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.endCustomer.pojo.EndCustomer;
import com.jingtong.platform.sap.dao.EndCustomerToSapDao;
import com.jingtong.platform.sap.pojo.EndCustomerToSap;


@SuppressWarnings("rawtypes")
public class EndCustomerToSapDaoImpl extends BaseDaoImpl  implements EndCustomerToSapDao{

	@Override
	public List<EndCustomerToSap> getEndCustomerTotal(EndCustomerToSap endCustomer) {
		return this.getSqlMapClientTemplate().queryForList("sapReport.getEndCustomerTotal",endCustomer);
	
}

	@Override
	public int updateEndCustomerState(EndCustomer ec) {
		return this.getSqlMapClientTemplate().update("sapReport.updateEndCustomerState",ec);
	}
}