package com.jingtong.platform.sap.service;

public interface PriceService {
	
	public void getPriceListFromSAPForPB(); 
	public void getPriceListFromSAPForMPP();
	public void getPriceListFromSAP(String price_type,String material_number);

}
