package com.jingtong.platform.sap.dao.impl;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.sap.dao.PriceDao;
import com.jingtong.platform.sap.pojo.Price;


@SuppressWarnings("rawtypes")
public class PriceDaoImpl extends BaseDaoImpl implements PriceDao{

	@Override
	public String createPrice(Price price) {
		long id = (Long) getSqlMapClientTemplate().insert("sapReport.createPrice",price);
		return id+"";
	}

	@Override
	public int updatePrice(Price price) {

		return getSqlMapClientTemplate().update("sapReport.updatePrice", price);
	}

	@Override
	public int getPriceList(Price price) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"sapReport.getPriceCount", price);
	}

	@Override
	public int deletePrice(Price price) {
		return getSqlMapClientTemplate().update("sapReport.deletePrice", price);
	}
	

	@Override
	public int deletePriceInfoForSap(Price price) {
		return getSqlMapClientTemplate().update("sapReport.deletePriceInfoForSap", price);
	}

}
