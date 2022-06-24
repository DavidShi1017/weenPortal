package com.jingtong.platform.sap.action;
 

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.allUser.service.IAllUserService;
import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.framework.mail.MailService;
import com.jingtong.platform.framework.util.ParamsEncryptUtil;
import com.jingtong.platform.sap.pojo.Kunnr;
import com.jingtong.platform.sap.service.KunnrsService;
 

public class KunnrsAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8702448542151199224L;
 
	private KunnrsService kunnrsSerivce;
	private IAllUserService allUserService;
	private String customer_code;
	private String sales_org;
 	
	public IAllUserService getAllUserService() {
		return allUserService;
	}


	public void setAllUserService(IAllUserService allUserService) {
		this.allUserService = allUserService;
	}


	public String getCustomer_code() {
		return customer_code;
	}


	public void setCustomer_code(String customer_code) {
		this.customer_code = customer_code;
	}


	public String getSales_org() {
		return sales_org;
	}


	public void setSales_org(String sales_org) {
		this.sales_org = sales_org;
	}


	public KunnrsService getKunnrsSerivce() {
		return kunnrsSerivce;
	}


	public void setKunnrsSerivce(KunnrsService kunnrsSerivce) {
		this.kunnrsSerivce = kunnrsSerivce;
	}





	public String getKunnrListFromSAP(){
		String aa=customer_code;
		String bb=sales_org;
		System.out.println(aa+" "+bb);
		this.kunnrsSerivce.getKunnrListFromSAP(aa,bb);
		this.setSuccessMessage("SUCCESS"); 
 		return RESULT_MESSAGE;
	}
 
}
