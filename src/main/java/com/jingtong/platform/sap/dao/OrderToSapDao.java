package com.jingtong.platform.sap.dao;

import java.util.List;

import com.jingtong.platform.order.pojo.StarderOrder;
import com.jingtong.platform.sampleOrder.pojo.SampleOrder;
import com.jingtong.platform.sap.pojo.OrderDetail;
import com.jingtong.platform.sap.pojo.OrderToSap;
import com.jingtong.platform.sap.pojo.SampleOrderDetail;
import com.jingtong.platform.sap.pojo.SampleOrderToSap;



public interface OrderToSapDao {
	

	public List<OrderToSap> getOrderTotal(OrderToSap order);
 	public List<OrderDetail> getOrderDetail(OrderToSap order);
 	public int updateOrderDetail(OrderDetail orderDetail);
 	public int updateOrderState(SampleOrder sorder);
}
