package com.jingtong.platform.order.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.order.dao.IOrderDao;
import com.jingtong.platform.order.pojo.OrderDetail;
import com.jingtong.platform.order.pojo.StarderOrder;
import com.jingtong.platform.order.service.IOrderService;

public class OrderServiceImpl implements IOrderService{
	private IOrderDao orderDao;
	private TransactionTemplate transactionTemplate;
	@Override
	public int getOrderListCount(StarderOrder o) {
		try {
			return orderDao.getOrderListCount(o);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<StarderOrder> getOrderList(StarderOrder o) {
		try {
			return orderDao.getOrderList(o);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public StarderOrder getOrderById(StarderOrder o) {
		try {
			return orderDao.getOrderById(o);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public BooleanResult createOrder(final StarderOrder o, final List<OrderDetail> odList) {
		BooleanResult booleanResult = new BooleanResult();
		booleanResult = (BooleanResult) transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus ts) {
				BooleanResult booleanResult = new BooleanResult();
				long n=0;
				long m=0;
				booleanResult.setResult(true);
				try {
					booleanResult.setResult(false);					 
					n=orderDao.createOrder(o);
					if (n <= 0) {
							booleanResult.setResult(false);
							booleanResult.setCode("²Ù×÷Ê§°Ü£¡");
							ts.setRollbackOnly();
							return booleanResult;
					}else{
						for(OrderDetail od:odList){	
							od.setMain_id(n);
							m=orderDao.createOrderDetail(od);
							if (m <= 0) {
								booleanResult.setResult(false);
								booleanResult.setCode("²Ù×÷Ê§°Ü£¡");
								ts.setRollbackOnly();
								return booleanResult;
							}
						}
						booleanResult.setCode(String.valueOf(n));
						booleanResult.setResult(true);
 					}
 					 
				} catch (Exception e) {
					e.printStackTrace();
					booleanResult.setResult(false);
					booleanResult.setCode("²Ù×÷Ê§°Ü£¡");
					ts.setRollbackOnly();
					return booleanResult;
				}
				return booleanResult;
			}
		});
		return booleanResult;
	}

	@Override
	public BooleanResult updateOrder(final StarderOrder o, final List<OrderDetail> odList,
			final OrderDetail od) {
		BooleanResult booleanResult = new BooleanResult();
		booleanResult = (BooleanResult) transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus ts) {
				BooleanResult booleanResult = new BooleanResult();
				long n;
				long m;
				booleanResult.setResult(true);
				try {
					booleanResult.setResult(false);					 
					n=orderDao.updateOrder(o);
					if (n <= 0) {
							booleanResult.setResult(false);
							booleanResult.setCode("²Ù×÷Ê§°Ü£¡");
							ts.setRollbackOnly();
							return booleanResult;
					}else{
						orderDao.deleteOrderDetail(od);
						for(OrderDetail od:odList){
							if (od.getId()==0) {
								m=orderDao.createOrderDetail(od);
							}else {								
								m=orderDao.updateOrderDetail(od);								
							}
							if (m <= 0) {
								booleanResult.setResult(false);
								booleanResult.setCode("²Ù×÷Ê§°Ü£¡");
								ts.setRollbackOnly();
								return booleanResult;
							}
						}
						booleanResult.setResult(true);
 					}
 					 
				} catch (Exception e) {
					e.printStackTrace();
					booleanResult.setResult(false);
					booleanResult.setCode("²Ù×÷Ê§°Ü£¡");
					ts.setRollbackOnly();
					return booleanResult;
				}
				return booleanResult;
			}
		});
		return booleanResult;
	}

	@Override
	public int deleteOrder(StarderOrder o) {
		try {
			return orderDao.deleteOrder(o);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<OrderDetail> getOrderDetailList(OrderDetail od) {
		try {
			return orderDao.getOrderDetailList(od);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	

	
	@Override
	public long createOrderDetail(OrderDetail od) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateOrderDetail(OrderDetail od) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int deleteOrderOfMain(OrderDetail od) {
		try {
			return orderDao.deleteOrderOfMain(od);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int deleteOrderDetail(OrderDetail od) {
		// TODO Auto-generated method stub
		return 0;
	}

	public IOrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(IOrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	@Override
	public String getSystemIdPrc() {
		try {
			return orderDao.getSystemIdPrc();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<OrderDetail> downloadOrderData(OrderDetail od) {
		try {
			return orderDao.downloadOrderData(od);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
