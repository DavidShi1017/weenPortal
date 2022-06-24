package com.jingtong.platform.message.service;

import java.util.List;

import com.jingtong.platform.message.pojo.Message;



/** 
 * @author cl 
 * @createDate 2016-5-25
 * 
 */
public interface IMessageService {
public List<Message> searchMessage(Message g);
	
	public long saveMessage(Message g);
	
	public int updateMessage(Message g);
	
}
