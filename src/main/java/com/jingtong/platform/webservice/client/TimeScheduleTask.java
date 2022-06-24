package com.jingtong.platform.webservice.client;

import java.util.Date;

import org.apache.log4j.Logger;

import com.jingtong.platform.webservice.service.IClient;

/**
 * 定时任务调度类
 * @author XIA_QX
 *
 */
public class TimeScheduleTask {
    private IClient iClient;
	Logger log = Logger.getLogger(TimeScheduleTask.class);
	
	
	public void executeClientEmailExpireDR() throws Exception{
		System.out.println("定时任务(DR失效提醒)开始"+new Date());
		//log.fatal("DR失效提醒:"+new Date());
		iClient.executeClientEmailExpireDR();
	}
	
	
	public void executeClientEmailExpireQuote() throws Exception{
		System.out.println("定时任务(Quote失效提醒)开始"+new Date());
		//log.fatal("DR失效提醒:"+new Date());
		iClient.executeClientEmailExpireQuote();
	}
	
	public void executeClientSendEmail() throws Exception{
		System.out.println("定时任务(邮件表逐个发送)开始"+new Date());
		//log.fatal("DR失效提醒:"+new Date());
		iClient.executeClientSendEmail();
	}

	public IClient getiClient() {
		return iClient;
	}


	public void setiClient(IClient iClient) {
		this.iClient = iClient;
	}


	public Logger getLog() {
		return log;
	}


	public void setLog(Logger log) {
		this.log = log;
	}
	 

	
	
	
}
