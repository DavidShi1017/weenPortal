package com.jingtong.platform.sap.service;

import java.util.List;

import com.jingtong.platform.sap.pojo.Knvp;

public interface KnvpService {
/**
 * 获取 开票方、售达方、送达方、付款方主数据
 */
	public void getKnvpFromSAP();
	
	public List<Knvp> getKunnrList(String kunnr);
}
