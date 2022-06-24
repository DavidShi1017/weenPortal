package com.jingtong.platform.sap.service;

import java.util.List;

import com.jingtong.platform.sap.pojo.CrmOrderItem;
import com.jingtong.platform.sap.pojo.CrmOrderToSap;

 
public interface CrmOrderToSAPService {
	
	public String crmOderToSap(CrmOrderToSap order,List<CrmOrderItem> item);
	
	public List<CrmOrderToSap> getCrmOrderTotal(CrmOrderToSap order);
 	public List<CrmOrderItem> getCrmOrderItems(CrmOrderToSap order);
 	public String crmOrderSoDelete(CrmOrderItem item);
 	public int updateOrderStatus(CrmOrderToSap order);
 	
 	public List<CrmOrderItem> getDeleteCrmOrder(CrmOrderToSap order);
 	
}
