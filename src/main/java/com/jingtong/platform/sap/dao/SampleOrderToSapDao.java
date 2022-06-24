package com.jingtong.platform.sap.dao;

import java.util.List;

import com.jingtong.platform.order.pojo.StarderOrder;
import com.jingtong.platform.sampleOrder.pojo.SampleOrder;
import com.jingtong.platform.sap.pojo.SampleOrderDetail;
import com.jingtong.platform.sap.pojo.SampleOrderToSap;



public interface SampleOrderToSapDao {
	
	public List<SampleOrderToSap> getSampleOrderTotal(SampleOrderToSap sampleOrder);
 	public List<SampleOrderDetail> getSampleOrderDetail(SampleOrderToSap sampleOrder);
 	public int updateSampleOrderDetail(SampleOrderDetail sampleOrderDetail);
 	public int updateOrderState(StarderOrder sorder);
}
