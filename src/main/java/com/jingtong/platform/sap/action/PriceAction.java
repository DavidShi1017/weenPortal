package com.jingtong.platform.sap.action;

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.sap.service.PriceService;

public class PriceAction extends BaseAction {


	private static final long serialVersionUID = 8702448542151199224L;
	
	private PriceService priceSerivce;
	private String customer_code;
	private String start_dateStr;
	private String end_dateStr;
	private String price_type;
	private String org_id;
	private String material_number;
	
	
	

	public String getMaterial_number() {
		return material_number;
	}

	public void setMaterial_number(String material_number) {
		this.material_number = material_number;
	}

	public String getCustomer_code() {
		return customer_code;
	}

	public void setCustomer_code(String customer_code) {
		this.customer_code = customer_code;
	}

	public String getStart_dateStr() {
		return start_dateStr;
	}

	public void setStart_dateStr(String start_dateStr) {
		this.start_dateStr = start_dateStr;
	}

	public String getEnd_dateStr() {
		return end_dateStr;
	}

	public void setEnd_dateStr(String end_dateStr) {
		this.end_dateStr = end_dateStr;
	}

	public String getPrice_type() {
		return price_type;
	}

	public void setPrice_type(String price_type) {
		this.price_type = price_type;
	}

	public PriceService getPriceSerivce() {
		return priceSerivce;
	}

	public void setPriceSerivce(PriceService priceSerivce) {
		this.priceSerivce = priceSerivce;
	}
	
	
	
public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

public String getPriceListFromSAP(){
	String dd=price_type;
	String ff=material_number;
	System.out.println(dd+""+ff);
	this.priceSerivce.getPriceListFromSAP(dd,ff);
	this.setSuccessMessage("SUCCESS"); 
		return RESULT_MESSAGE;
	}
 
	
}
