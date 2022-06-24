package com.jingtong.platform.sap.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.sap.service.ShopDeliveryService;

/**
 * 交货单同步
 */

public class ShopDeliveryAction  extends BaseAction {

	
	private static final long serialVersionUID = 2708360048296344402L;

	private ShopDeliveryService shopDeliveryService;

	public ShopDeliveryService getShopDeliveryService() {
		return shopDeliveryService;
	}

	public void setShopDeliveryService(ShopDeliveryService shopDeliveryService) {
		this.shopDeliveryService = shopDeliveryService;
	}
 

	public String getDeliveryFromSap(){
		if(this.shopDeliveryService.getDeliveryFromSap()==true){
			return "1";
		}else{
			return "0";
		}
	}
	
	public String getCusDownFromSap(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.shopDeliveryService.getCusDownFromSap(sdf.parse("2015-09-20"), sdf.parse("2015-09-30"), null);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "0";
//		if(this.shopDeliveryService.getCusDownFromSap(null,null,null)==true){
//			return "1";
//		}else{
//			return "0";
//		}
	/*	Map promap=new HashMap();
		this.shopDeliveryService.updateinfoPro(promap);*/
	}
}
