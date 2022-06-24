package com.jingtong.platform.sap.action;
 

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.sap.service.CustomerPriceService;
 

public class CustomerPriceAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8702448542151199224L;
 
	private CustomerPriceService customerPriceSerivce;

	public CustomerPriceService getCustomerPriceSerivce() {
		return customerPriceSerivce;
	}

	public void setCustomerPriceSerivce(CustomerPriceService customerPriceSerivce) {
		this.customerPriceSerivce = customerPriceSerivce;
	}

	public String getCustomerPriceListFromSAP(){ 
		System.out.println("---getCustomerPriceListFromSAP---");
		this.customerPriceSerivce.getCustomerPriceFromSAP();
		return null;
	}
 
}
