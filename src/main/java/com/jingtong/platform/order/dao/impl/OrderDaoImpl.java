package com.jingtong.platform.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.order.dao.IOrderDao;
import com.jingtong.platform.order.pojo.OrderDetail;
import com.jingtong.platform.order.pojo.StarderOrder;


public class OrderDaoImpl  extends BaseDaoImpl implements IOrderDao{

	@Override
	public int getOrderListCount(StarderOrder o) {
		return (Integer) getSqlMapClientTemplate().queryForObject("order.getOrderListCount",o);
	}

	@Override
	public List<StarderOrder> getOrderList(StarderOrder o) {
		return (List<StarderOrder>) getSqlMapClientTemplate().queryForList("order.getOrderList",o);
	}

	@Override
	public StarderOrder getOrderById(StarderOrder o) {
		return (StarderOrder) getSqlMapClientTemplate().queryForObject("order.getOrderById",o);
	}

	@Override
	public long createOrder(StarderOrder o) {
		return (Long) getSqlMapClientTemplate().insert("order.createOrder",o);
	}

	@Override
	public int updateOrder(StarderOrder o) {
		return (Integer) getSqlMapClientTemplate().update("order.updateOrder",o);
	}

	@Override
	public int deleteOrder(StarderOrder o) {
		return (Integer) getSqlMapClientTemplate().delete("order.deleteOrder",o);
	}

	@Override
	public List<OrderDetail> getOrderDetailList(OrderDetail od) {
		return (List<OrderDetail>) getSqlMapClientTemplate().queryForList("order.getOrderDetailList",od);
	}

	@Override
	public long createOrderDetail(OrderDetail od) {
		return (Long) getSqlMapClientTemplate().insert("order.createOrderDetail",od);
	}

	@Override
	public int updateOrderDetail(OrderDetail od) {
		return (Integer) getSqlMapClientTemplate().update("order.updateOrderDetail",od);
	}

	@Override
	public int deleteOrderDetail(OrderDetail od) {
		return (Integer) getSqlMapClientTemplate().delete("order.deleteOrderDetail",od);
	}

	@Override
	public String getSystemIdPrc() {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("incount", 6+"");
  		parameter.put("intype", 1+"");
  		parameter.put("RESULTCODE","");

 		this.getSqlMapClientTemplate().queryForList(
				"order.getSystemIdPrc", parameter);
 		String message=(String) parameter.get("RESULTCODE");
		return message;
	}

	@Override
	public List<OrderDetail> downloadOrderData(OrderDetail od) {
		return (List<OrderDetail>) getSqlMapClientTemplate().queryForList("order.getAllOrderInfoNoPage",od);
	}

	@Override
	public int deleteOrderOfMain(OrderDetail od) {
		return (Integer) getSqlMapClientTemplate().delete("order.deleteOrderOfMain",od);
	}
	
}
