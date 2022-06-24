package com.jingtong.platform.sap.dao.impl;

import java.util.List;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.order.pojo.StarderOrder;
import com.jingtong.platform.sap.dao.SampleOrderToSapDao;
import com.jingtong.platform.sap.pojo.SampleOrderDetail;
import com.jingtong.platform.sap.pojo.SampleOrderToSap;

@SuppressWarnings("rawtypes")
public class SampleOrderToSapDaoImpl extends BaseDaoImpl implements SampleOrderToSapDao{

	@SuppressWarnings("unchecked")
    @Override
	public List<SampleOrderToSap> getSampleOrderTotal(
			SampleOrderToSap sampleOrder) {
		return this.getSqlMapClientTemplate().queryForList("sapReport.getSampleOrderTotal",sampleOrder);
	}

	@SuppressWarnings("unchecked")
    @Override
	public List<SampleOrderDetail> getSampleOrderDetail(
			SampleOrderToSap sampleOrder) {
		return this.getSqlMapClientTemplate().queryForList("sapReport.getSampleOrderDetail",sampleOrder);
	}

	@Override
	public int updateSampleOrderDetail(SampleOrderDetail salesId) {
		return this.getSqlMapClientTemplate().update("sapReport.updateSampleOrderDetail",salesId);
	}

	@Override
	public int updateOrderState(StarderOrder sorder) {
		return this.getSqlMapClientTemplate().update("sapReport.updateOrderState",sorder);
	}

}
