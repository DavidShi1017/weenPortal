package com.jingtong.platform.webservice.pojo;


import java.io.Serializable;

/**
 * 翼讯短信发送类
 * @author XIA_QX
 *
 */
public class SendMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8778320889553578490L;

	private String mobileNumber;//手机号码
	private String messageContent;//短信内容
	
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	
	
}
