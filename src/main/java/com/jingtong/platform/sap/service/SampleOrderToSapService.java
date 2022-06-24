package com.jingtong.platform.sap.service;

import java.util.List;

import com.jingtong.platform.order.pojo.StarderOrder;
import com.jingtong.platform.sap.pojo.SampleOrderDetail;
import com.jingtong.platform.sap.pojo.SampleOrderToSap;



public interface SampleOrderToSapService {
	
	
	public String sampleOrderToSap(SampleOrderToSap sampleOrder,List<SampleOrderDetail> sampleOrderDetail,StarderOrder sorder);
	
	
 	public List<SampleOrderToSap> getSampleOrderTotal(SampleOrderToSap sampleOrder);
 	public List<SampleOrderDetail> getSampleOrderDetail(SampleOrderToSap sampleOrder);

}
