package com.jingtong.platform.sap.dao;

import com.jingtong.platform.sap.pojo.ChannelPrice;
import com.jingtong.platform.sap.pojo.Material;

public interface ChannelPriceDao {

public long creatChannelPrice(ChannelPrice channel);
	
	public int updateChannelPrice(ChannelPrice channel);
	
	public int getChannelPriceCount(ChannelPrice channel);
	
	public Material getMaterial(String materNumber);
}
