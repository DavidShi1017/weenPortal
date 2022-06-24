package com.jingtong.platform.order.service;

import java.util.List;

import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.order.pojo.OrderDetail;
import com.jingtong.platform.order.pojo.StarderOrder;

public interface IOrderService {
	/**
	 * 获取订单列表数
	 * @param o
	 * @return
	 */
	public int getOrderListCount(StarderOrder o);
	/**
	 * 获取订单信息列表
	 * @param o
	 * @return
	 */
	public List<StarderOrder> getOrderList(StarderOrder o);
	/**
	 * 根据ID获取订单信息
	 * @param o
	 * @return
	 */
	public StarderOrder getOrderById(StarderOrder o);
	/**
	 * 订单信息新增
	 * @param o
	 * @return
	 */
	public BooleanResult createOrder(StarderOrder o,List<OrderDetail> odList);
	/**
	 * 修改订单信息
	 * @param o
	 * @return
	 */
	public BooleanResult updateOrder(StarderOrder o,List<OrderDetail> odList,OrderDetail od);
	/**
	 * 删除订单信息(逻辑删除)
	 * @param o
	 * @return
	 */
	public int deleteOrder(StarderOrder o);
	
	
	

	/**
	 * 获取订单明细信息列表
	 * @param od
	 * @return
	 */
	public List<OrderDetail> getOrderDetailList(OrderDetail od);

	/**
	 * 订单明细信息新增
	 * @param od
	 * @return
	 */
	public long createOrderDetail(OrderDetail od);
	/**
	 * 修改订单明细信息
	 * @param od
	 * @return
	 */
	public int updateOrderDetail(OrderDetail od);
	/**
	 * 删除订单明细信息(物理删除)
	 * @param od
	 * @return
	 */
	public int deleteOrderDetail(OrderDetail od);
	
	
	/**
	 * 获取自动生成单号
	 */
	public String getSystemIdPrc();
	public List<OrderDetail> downloadOrderData(OrderDetail od);
	int deleteOrderOfMain(OrderDetail od);
}
