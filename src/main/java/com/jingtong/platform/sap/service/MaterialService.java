package com.jingtong.platform.sap.service;

public interface MaterialService {

	/**
	 * @remark 从SAP获取产品主数据
	 */
	public String getMaterialListFromSAP();
	public void getMaterialListFromSAP(String material_id);
	

}
