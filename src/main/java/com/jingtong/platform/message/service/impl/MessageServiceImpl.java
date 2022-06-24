package com.jingtong.platform.message.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jingtong.platform.message.dao.IMessageDao;
import com.jingtong.platform.message.pojo.Message;
import com.jingtong.platform.message.service.IMessageService;


/** 
 * @author cl 
 * @createDate 2016-5-25
 * 
 */
public class MessageServiceImpl implements IMessageService {
	private static final Log logger = LogFactory.getLog(MessageServiceImpl.class);
	private IMessageDao messageDao;
	
	public static Log getLogger() {
		return logger;
	}


	@Override
	public List<Message> searchMessage(Message g) {
		try {
			return messageDao.searchMessage(g);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public long saveMessage(Message g) {
		try {
			return messageDao.saveMessage(g);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateMessage(Message g) {
		try {
			return messageDao.updateMessage(g);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}


	public IMessageDao getMessageDao() {
		return messageDao;
	}


	public void setMessageDao(IMessageDao messageDao) {
		this.messageDao = messageDao;
	}

	
}
