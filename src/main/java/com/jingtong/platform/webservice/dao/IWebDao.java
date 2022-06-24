package com.jingtong.platform.webservice.dao;

import java.util.List;

import com.jingtong.platform.customer.pojo.CustomerUser;
import com.jingtong.platform.designReg.pojo.DesignRegDetail;
import com.jingtong.platform.message.pojo.Message;
import com.jingtong.platform.quote.pojo.QuoteDetail;
import com.jingtong.platform.sap.pojo.ExceptionLog;

/**
 * 接口同步DAO层
 * 
 * @author XIA_QX
 *
 */
public interface IWebDao {
    /**
     * 根据userid查询用户信息（查询对应id的邮箱）
     * 
     * @param ids
     * @return
     */
    public CustomerUser getUsersByUserId(String ids);

    /**
     * 查询需发送邮件的DR
     * 
     * @return
     */
    public List<DesignRegDetail> getEmailExpireDR();

    /**
     * 查询需发送邮件的Quote
     * 
     * @return
     */
    public List<QuoteDetail> getEmailExpireQuote();

    /**
     * 查询邮件表，获取需要发送的邮件
     * 
     * @param c
     * @return
     */
    public List<Message> searchMessage(Message c);

    /**
     * 修改邮件状态为已发送
     * 
     * @param c
     * @return
     */
    public int updateMessage(Message c);

    /**
     * 添加新邮件
     * 
     * @param g
     * @return
     */
    public long saveMessage(Message g);

    public long insertJobLog(ExceptionLog log);

}
