package com.jingtong.platform.sap.dao.impl;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.sap.dao.ChannelPriceDao;
import com.jingtong.platform.sap.pojo.ChannelPrice;
import com.jingtong.platform.sap.pojo.Material;

public class ChannelPriceDaoImpl extends BaseDaoImpl  implements ChannelPriceDao{

	@Override
	public long creatChannelPrice(ChannelPrice channel) {
		return (Long) getSqlMapClientTemplate().insert("channelPrice.insertChannelPrice", channel);
	}

	@Override
	public int updateChannelPrice(ChannelPrice channel) {
		 
		return getSqlMapClientTemplate().update("channelPrice.updateChannelPrice", channel);
	}

	@Override
	public int getChannelPriceCount(ChannelPrice channel) {
		return (Integer) getSqlMapClientTemplate().queryForObject("channelPrice.getChannelPriceCount", channel);
	}

	@Override
	public Material getMaterial(String materNumber) {
		// TODO Auto-generated method stub
		return (Material) this.getSqlMapClientTemplate().queryForObject("channelPrice.getMaterial", materNumber);
	}
 
}
