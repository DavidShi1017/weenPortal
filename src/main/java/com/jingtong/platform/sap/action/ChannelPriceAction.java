package com.jingtong.platform.sap.action;
 

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.sap.service.ChannelPriceService;
import com.jingtong.platform.sap.service.KunnrsService;
 

public class ChannelPriceAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8702448542151199224L;
 
	private ChannelPriceService channelPriceSerivce;
	
	public ChannelPriceService getChannelPriceSerivce() {
		return channelPriceSerivce;
	}

	public void setChannelPriceSerivce(ChannelPriceService channelPriceSerivce) {
		this.channelPriceSerivce = channelPriceSerivce;
	}

	public String getKunnrListFromSAP(){ 
		System.out.println("---getChannelPriceFromSAP--------");
		this.channelPriceSerivce.getChannelPriceFromSAP();
		return null;
	}
 
}
