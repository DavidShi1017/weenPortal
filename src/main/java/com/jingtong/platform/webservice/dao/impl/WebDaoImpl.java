package com.jingtong.platform.webservice.dao.impl;

import java.util.List;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.customer.pojo.CustomerUser;
import com.jingtong.platform.designReg.pojo.DesignRegDetail;
import com.jingtong.platform.message.pojo.Message;
import com.jingtong.platform.quote.pojo.QuoteDetail;
import com.jingtong.platform.webservice.dao.IWebDao;
import com.jingtong.platform.sap.pojo.ExceptionLog;

@SuppressWarnings("rawtypes")
public class WebDaoImpl extends BaseDaoImpl implements IWebDao {
    @Override
    public CustomerUser getUsersByUserId(String ids) {
        return (CustomerUser) getSqlMapClientTemplate().queryForObject("customer.getUserByUserId", ids);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DesignRegDetail> getEmailExpireDR() {
        return (List<DesignRegDetail>) getSqlMapClientTemplate().queryForList("designReg.getEmailExpireDR");
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<QuoteDetail> getEmailExpireQuote() {
        return (List<QuoteDetail>) getSqlMapClientTemplate().queryForList("quote.getEmailExpireQuote");
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Message> searchMessage(Message g) {
        return getSqlMapClientTemplate().queryForList("message.searchMessage", g);
    }

    @Override
    public long saveMessage(Message g) {
        return (Long) getSqlMapClientTemplate().insert("message.saveMessage", g);
    }

    @Override
    public int updateMessage(Message g) {
        return getSqlMapClientTemplate().update("message.updateMessage", g);
    }

    @Override
    public long insertJobLog(ExceptionLog log) {
        return (Long) getSqlMapClientTemplate().insert("exlog.insertJobLog", log);
    }
}
