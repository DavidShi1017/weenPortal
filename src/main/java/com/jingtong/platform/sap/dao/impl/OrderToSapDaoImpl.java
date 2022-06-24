package com.jingtong.platform.sap.dao.impl;

import java.util.List;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.sampleOrder.pojo.SampleOrder;
import com.jingtong.platform.sap.dao.OrderToSapDao;
import com.jingtong.platform.sap.pojo.OrderToSap;
import com.jingtong.platform.sap.pojo.OrderDetail;

@SuppressWarnings("rawtypes")
public class OrderToSapDaoImpl extends BaseDaoImpl implements OrderToSapDao{

	@SuppressWarnings("unchecked")
    @Override
	public List<OrderToSap> getOrderTotal(
			OrderToSap order) {
		return this.getSqlMapClientTemplate().queryForList("sapReport.getOrderTotal",order);
	}

	@SuppressWarnings("unchecked")
    @Override
	public List<OrderDetail> getOrderDetail(
			OrderToSap order) {
		return this.getSqlMapClientTemplate().queryForList("sapReport.getOrderDetail",order);
	}

	@Override
	public int updateOrderDetail(OrderDetail salesId) {
		return this.getSqlMapClientTemplate().update("sapReport.updateOrderDetail",salesId);
	}

	@Override
	public int updateOrderState(SampleOrder sorder) {
		return this.getSqlMapClientTemplate().update("sapReport.updateSampleOrderState",sorder);
	}
}
