package com.jingtong.platform.sap.service;

import java.util.List;

import com.jingtong.platform.endCustomer.pojo.EndCustomer;
import com.jingtong.platform.sap.pojo.CrmOrderItem;
import com.jingtong.platform.sap.pojo.CrmOrderToSap;
import com.jingtong.platform.sap.pojo.EndCustomerToSap;
import com.jingtong.platform.sap.pojo.Kunnr;

public interface EndCustomerToSapService {

	public String endCustomerToSap(EndCustomerToSap endCustomer,EndCustomer ec);
	
	public List<EndCustomerToSap> getEndCustomerTotal(EndCustomerToSap endCustomer);
	
	
	
}
