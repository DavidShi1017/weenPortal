package com.jingtong.platform.sap.dao;

 import java.util.List;

import com.jingtong.platform.sap.pojo.Accrual;
 import java.util.Map;

 import com.jingtong.platform.sap.pojo.ShopDelivery;

 
 

public interface ShopDeliveryDao {
 
	
	public int getDeliverys(ShopDelivery del);
	
	public int updateDelivery(ShopDelivery del);
	
	public long insertDelivery(ShopDelivery del);
 	
	public String updateOrderPrc(ShopDelivery del);
	
	public List<Accrual> getCreditByOrder();
	
	public long insetAccrual(Accrual acc);
	
	public long checkExistAccrual(Accrual acc);
	
	public List<Accrual> getOnlineCredit();
 
	public void updateinfoPro(Map promap);
	
	public int updateAccrual();
 }
