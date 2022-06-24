package com.jingtong.platform.sap.service;

import java.util.List;

import com.jingtong.platform.order.pojo.StarderOrder;
import com.jingtong.platform.sampleOrder.pojo.SampleOrder;
import com.jingtong.platform.sap.pojo.OrderDetail;
import com.jingtong.platform.sap.pojo.OrderToSap;



public interface OrderToSapService {
	
	
	public String orderToSap(OrderToSap order,List<OrderDetail> orderDetail,SampleOrder sorder);
	
	
 	public List<OrderToSap> getOrderTotal(OrderToSap order);
 	public List<OrderDetail> getOrderDetail(OrderToSap order);

 	public String getSampleStockFromSAP(String material_id);

}
