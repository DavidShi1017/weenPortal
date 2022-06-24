package com.jingtong.platform.message.dao.impl;

import java.util.List;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.message.dao.IMessageDao;
import com.jingtong.platform.message.pojo.Message;



public class MessageDaoImpl extends BaseDaoImpl implements IMessageDao {

	@Override
	public List<Message> searchMessage(Message g) {
		return getSqlMapClientTemplate().queryForList("message.searchMessage",g);
	}

	@Override
	public long saveMessage(Message g) {
		return (Long) getSqlMapClientTemplate().insert("message.saveMessage", g);
	}

	@Override
	public int updateMessage(Message g) {
		return getSqlMapClientTemplate().update("message.updateMessage", g);
	}
}
