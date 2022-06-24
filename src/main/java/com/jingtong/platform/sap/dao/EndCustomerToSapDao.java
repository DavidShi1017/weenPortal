package com.jingtong.platform.sap.dao;


import java.util.List;


import com.jingtong.platform.endCustomer.pojo.EndCustomer;
import com.jingtong.platform.quote.pojo.Quote;
import com.jingtong.platform.sap.pojo.EndCustomerToSap;



public interface EndCustomerToSapDao {
	
	public List<EndCustomerToSap> getEndCustomerTotal(EndCustomerToSap endCustomer);
	public int updateEndCustomerState(EndCustomer ec);



}
