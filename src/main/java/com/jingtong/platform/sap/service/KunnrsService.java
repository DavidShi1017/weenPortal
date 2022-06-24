package com.jingtong.platform.sap.service;

import java.util.List;

import com.jingtong.platform.sap.pojo.Kunnr;

 
public interface KunnrsService {

	/**
	 * @remark 从SAP获取客户主数据
	 */
	public void getKunnrListFromSAP();
	public void getKunnrListFromSAP(String customer_code,String sales_org);
	
}
