package com.jingtong.platform.webservice.service;

import java.util.List;
import com.jingtong.platform.webservice.pojo.SendMessage;

/**
 * webservice调用客户端
 * @author XIA_QX
 *
 */
public interface IClient {

	public void executeClientEmailExpireDR();

	void executeClientSendEmail();

	void executeClientEmailExpireQuote();

//	/**
//	 * 定时截止询价单
//	 */
//	public void executeClientXunjia();
	
 }
