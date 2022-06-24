package com.jingtong.platform.sap.dao;

import com.jingtong.platform.sap.pojo.Price;


public interface PriceDao {

	public String createPrice(Price price);
	
	public int updatePrice(Price price);
	

	
	public int getPriceList(Price price);
	
	public int deletePrice(Price price);
	
	//删除当前时间的前一天的数据
	public int deletePriceInfoForSap(Price price);
	
}
