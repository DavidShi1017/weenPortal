package com.jingtong.platform.sap.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.endCustomer.pojo.EndCustomer;
import com.jingtong.platform.quote.pojo.Quote;
import com.jingtong.platform.sap.pojo.CrmOrderItem;
import com.jingtong.platform.sap.pojo.CrmOrderToSap;
import com.jingtong.platform.sap.pojo.EndCustomerToSap;
import com.jingtong.platform.sap.pojo.ProductStock;
import com.jingtong.platform.sap.pojo.QuoteDetail;
import com.jingtong.platform.sap.pojo.QuoteToSap;
import com.jingtong.platform.sap.service.EndCustomerToSapService;
import com.jingtong.platform.sap.service.MaterialService;
import com.jingtong.platform.sap.service.SAPService;

public class EndCustomerToSapAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3599908969412005618L;

	private EndCustomerToSapService endCustomerToSapSerivce;
	private String disti_customer_id;//¿Í»§±àºÅ
 	private String ids; 
 	private SAPService sapService;
 	private long id; 
	
	 



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EndCustomerToSapService getEndCustomerToSapSerivce() {
		return endCustomerToSapSerivce;
	}

	public void setEndCustomerToSapSerivce(
			EndCustomerToSapService endCustomerToSapSerivce) {
		this.endCustomerToSapSerivce = endCustomerToSapSerivce;
	}



	public String getDisti_customer_id() {
		return disti_customer_id;
	}

	public void setDisti_customer_id(String disti_customer_id) {
		this.disti_customer_id = disti_customer_id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public SAPService getSapService() {
		return sapService;
	}

	public void setSapService(SAPService sapService) {
		this.sapService = sapService;
	}

	public void setEndCustomerSerivce(EndCustomerToSapService endCustomerSerivce) {
		this.endCustomerToSapSerivce = endCustomerSerivce;
	}

	public String getEndCustomerToSAP(){
		
		this.setSuccessMessage("");
		String message = "";
		EndCustomerToSap endCustomer = new EndCustomerToSap();
		endCustomer.setId(id);
		List<EndCustomerToSap> endCustomerList = endCustomerToSapSerivce
				.getEndCustomerTotal(endCustomer);
		endCustomer = endCustomerList.get(0);
		System.out.println(endCustomer.getId());
		EndCustomer ec = new EndCustomer();
		ec.setId(endCustomer.getId());
		message = message
				+ endCustomerToSapSerivce.endCustomerToSap(endCustomer, ec);
		this.setSuccessMessage(message);
		return RESULT_MESSAGE;
	}
	}
	
	
	
	
	
//public String getEndCustomerToSAPTest(){
		

//	EndCustomerToSap endCustomer = new EndCustomerToSap();
//	
//	endCustomer.setId("0001");
//	endCustomer.setCustomerId("0001");
//	endCustomer.setCustomerName("WeEn");
//	endCustomer.setEndCustomerGroupId("0001");
//	endCustomer.setEndCustomerGroupName("WeEn");
//	endCustomer.setEndCustomerId("0001");
//	endCustomer.setEndCustomerName("WeEn");
//	endCustomer.setEndCustomerType("ZDKH");
//	endCustomer.setEndCustomerProperty("1");
//	endCustomer.setCurrencyCode("00");
//	endCustomer.setCountry("00");
//	endCustomer.setProvince("00");
//	endCustomer.setAddress("00");
//	endCustomer.setContactName("WeEn");
//	endCustomer.setTel("12345");
//	endCustomer.setCreateUserId("1");
//	endCustomer.setCreateTime("20160101");
//	endCustomer.setState("Y");

	
//	
//		
//	String message=endCustomerToSapSerivce.endCustomerToSap(endCustomer);
//	
////	return message;
//	
//}



