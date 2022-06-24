package com.jingtong.platform.webservice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.xml.namespace.QName;

import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.transaction.support.TransactionTemplate;

import com.jingtong.platform.allUser.service.IAllUserService;
import com.jingtong.platform.customer.pojo.CustomerUser;
import com.jingtong.platform.designReg.pojo.DesignRegDetail;
import com.jingtong.platform.framework.mail.MailService;
import com.jingtong.platform.message.pojo.Message;
import com.jingtong.platform.quote.pojo.QuoteDetail;
import com.jingtong.platform.webservice.dao.IWebDao;
import com.jingtong.platform.sap.pojo.ExceptionLog;
import com.jingtong.platform.webservice.service.IClient;

/**
 * 调用接口客户端
 * 
 * @author XIA_QX
 *
 */
public class IClientImpl implements IClient {

    public static Properties env;
    private String smtpServer = "smtp.exmail.qq.com";// =env.getProperty("allUser.smtpServer");
    private String from = "wei.yao@jing-tong.com";// =env.getProperty("allUser.from");
    private String displayName = "PortalSystem";// =env.getProperty("allUser.displayName");
    private String emailaddress = "wei.yao@jing-tong.com";// =env.getProperty("allUser.emailaddress");
    private String emailpassword = "jing-tong.com";// =env.getProperty("allUser.emailpassword");

    /*
     * 邮箱发送服务器是mail.yytex.net 账号是：yysrm@yytex.net 密码是：yuyuejiafangsrm123456
     * 
     */

    public IWebDao iWebDao;
    public TransactionTemplate transactionTemplate;
    public IAllUserService allUserService;

    public IWebDao getiWebDao() {
        return iWebDao;
    }

    public void setiWebDao(IWebDao iWebDao) {
        this.iWebDao = iWebDao;
    }

    public IAllUserService getAllUserService() {
        return allUserService;
    }

    public void setAllUserService(IAllUserService allUserService) {
        this.allUserService = allUserService;
    }

    /**
     * 调用webservice服务端
     * 
     * @param wsdlUrl
     * @param packageName
     * @param method
     * @param vCode
     * @return
     * @throws Exception
     */
    private Object[] executeClient(String wsdlUrl, String packageName, String method, String vCode) throws Exception {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        org.apache.cxf.endpoint.Client client = dcf.createClient(wsdlUrl);
        QName name = new QName(packageName, method);
        Object[] objects = client.invoke(name, vCode);

        if ("".equals(objects) || null == objects || objects.length <= 0) {
            // throw new Exception("调用接口返回有无");
            objects[0] = "调用接口返回失败";
        }

        // 调用web Service//输出调用结果
        // System.out.println(objects[0].toString());
        return objects;
    }

    @Override
    public void executeClientEmailExpireDR() {

        ExceptionLog log = new ExceptionLog();
        log.setLogInfo("START : executeClientEmailExpireDR");
        log.setInterfaceName("executeClientEmailExpireDR");
        iWebDao.insertJobLog(log);
        
        // 1.查询，需要邮件通知的DR行项目（离有效期30天的）
        List<DesignRegDetail> drdList = new ArrayList<DesignRegDetail>();
        drdList = iWebDao.getEmailExpireDR();

        // 2.关联DR表头，向创建人发送邮件
        for (DesignRegDetail drd : drdList) {
            // String content = "<br>&nbsp;&nbsp;The material "+drd.getMaterial_name()+" you
            // submit in DR "+drd.getDrNum()+" expires in "+ drd.getEnd_dateStr() +", please
            // login platform to view";
            String content = "<br>&nbsp;&nbsp;Your DR " + drd.getDrNum() + " will be expired on " + drd.getEnd_dateStr()
                    + ". if this project is in progress,please update it accordingly.";
            CustomerUser cusUser = new CustomerUser();
            cusUser = iWebDao.getUsersByUserId(drd.getCreate_userId());
            if (cusUser != null) {
                String contents = "Dear user" + content + "<br>";
                Message m = new Message();
                m.setContent(contents);
                m.setType("Remind DRExpire");
                m.setSendNumber(cusUser.getEmail());
                iWebDao.saveMessage(m);
                // sendMailByAddree(cusUser.getEmail(),contents);
            }
        }
        log.setLogInfo("END : executeClientEmailExpireDR");
        log.setInterfaceName("executeClientEmailExpireDR");
        iWebDao.insertJobLog(log);
    }

    @Override
    public void executeClientEmailExpireQuote() {
        
        ExceptionLog log = new ExceptionLog();
        log.setLogInfo("START : executeClientEmailExpireQuote");
        log.setInterfaceName("executeClientEmailExpireQuote");
        iWebDao.insertJobLog(log);

        // 1.查询，需要邮件通知的DR行项目（离有效期30天的）
        List<QuoteDetail> qdList = new ArrayList<QuoteDetail>();
        qdList = iWebDao.getEmailExpireQuote();

        // 2.关联DR表头，向创建人发送邮件
        for (QuoteDetail qd : qdList) {
            String content = "";
            String expireDate = "";
            if (qd.getIsAgree() == 0) {
                expireDate = qd.getLatest_timeStr();
            } else {
                continue;
            }
            content = "<br>&nbsp;&nbsp;Please be reminded to trigger debit for Part nbr " + qd.getMaterial_name()
                    + " in Quote nbr " + qd.getQuote_id() + " on time.  Otherwise, the approved Quote nbr "
                    + qd.getQuote_id() + " will be expired and closed on " + expireDate;

            CustomerUser cusUser = new CustomerUser();
            cusUser = iWebDao.getUsersByUserId(qd.getCreate_userId());
            if (cusUser != null) {
                String contents = "Dear user" + content + "<br>";
                Message m = new Message();
                m.setContent(contents);
                m.setType("Remind QuoteExpire");
                m.setSendNumber(cusUser.getEmail());
                iWebDao.saveMessage(m);
            }
        }
        log.setLogInfo("END : executeClientEmailExpireQuote");
        log.setInterfaceName("executeClientEmailExpireQuote");
        iWebDao.insertJobLog(log);
    }

    /**
     * 定时发送邮件表里的邮件
     */
    @Override
    public void executeClientSendEmail() {
        ExceptionLog log = new ExceptionLog();
        log.setLogInfo("START : executeClientSendEmail");
        log.setInterfaceName("executeClientSendEmail");
        iWebDao.insertJobLog(log);
        // 1.查询
        List<Message> mList = new ArrayList<Message>();
        Message message = new Message();
        mList = iWebDao.searchMessage(message);

        //
        for (Message m : mList) {
            // 逐条发邮件
            String result = sendMailByAddree(m.getSendNumber(), m.getContent());
            if ("Success".equals(result)) {
                m.setState("1");
                iWebDao.updateMessage(m);
            } else {
                m.setState("0");
                m.setResultMessage("Error!");
                iWebDao.updateMessage(m);
            }
            // 已发送标记
        }
        
        log.setLogInfo("END : executeClientSendEmail");
        log.setInterfaceName("executeClientSendEmail");
        iWebDao.insertJobLog(log);
    }

    public static void main(String[] arg) {
        System.out.println("111111111111");
        IClientImpl a = new IClientImpl();
        a.sendMailByAddree("33787495@qq.com", "11111111111111111诉调");
    }

    /**
     * 根据邮箱发送邮件
     * 
     * @return
     */
    public String sendMailByAddree(String emailAddress, String content) {
        HashMap<String, String> map = new HashMap<String, String>();
        try {
//			MailService mail = new MailService("smtp.office365.com", "service@ween-semi.com",
//					"PortalSystem", "service@ween-semi.com", "Ls^8!vD6Xa",
//					emailAddress, "WeEn Portal System Notifications", content);// 邮件做成
            MailService mail = new MailService("10.200.9.22", "servicetest@ween-semi.com",
                    "WeEn Portal System Notifications", emailAddress, " ", content);// 邮件做成
            map = mail.send(); // 邮件发送
            System.out.println(map);
        } catch (Exception e) {
            System.out.println("*************Exception!**************");
            return "Failed";
        }
        if (!"success".equals(map.get("state"))) {
            return "Failed";
        }
        if ("success".equals(map.get("state"))) {
            return "Success";
        } else {
            return "Failed";
        }
        //////////
    }

    public static Properties getEnv() {
        return env;
    }

    public static void setEnv(Properties env) {
        IClientImpl.env = env;
    }

    public String getSmtpServer() {
        return smtpServer;
    }

    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getEmailpassword() {
        return emailpassword;
    }

    public void setEmailpassword(String emailpassword) {
        this.emailpassword = emailpassword;
    }

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

}
