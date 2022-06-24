package com.jingtong.platform.sap.service;

import java.util.Date;
import java.util.Map;

public interface ShopDeliveryService {
 

	public boolean getDeliveryFromSap();
	
	public boolean autoGetCusDownFromSap();
	
	public boolean getCusDownFromSap(Date begda,Date endda,String kunnr);

	public void updateinfoPro(Map promap);
}
