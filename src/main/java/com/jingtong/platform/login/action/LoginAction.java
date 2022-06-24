package com.jingtong.platform.login.action;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.jingtong.platform.account.service.IAccountService;
import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.allUser.service.IAllUserService;
import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.framework.content.listener.OnlineCounter;
import com.jingtong.platform.framework.util.EncryptUtil;
import com.jingtong.platform.framework.util.PropertiesUtil;
import com.jingtong.platform.login.pojo.ValidateResult;
import com.jingtong.platform.login.service.ICAService;
import com.jingtong.platform.login.service.ILDAPService;
import com.opensymphony.xwork2.ActionContext;

public class LoginAction extends BaseAction {

    private Log logger = LogFactory.getLog(LoginAction.class);

    private String param;// 单点登录使用的参数
    private String ack;

    public String getAck() {
        return ack;
    }

    public void setAck(String ack) {
        this.ack = ack;
    }
    private ILDAPService LDAPService;

    /**
     * 
     */
    private static final long serialVersionUID = -1872868236628398675L;

    /**
     * 登录账号
     */
    private String passport;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 用户名字
     */
    private String name;
    /**
     * 是否AD验证
     */
    private boolean validate;

    /**
     * 域名
     */
    private String domain;

    private ICAService caService;    
    
    private IAccountService accountService;
    
    private IAllUserService allUserService;

    private String jdbcUrl;

    private String jdbcSid;

    private String jdbcUsername;

    private String jdbcPassword;

    private String ldapHost;

    private String ldapDomain;

    private String ldapUser;

    private String ldapPassword;

    private long onLine;

    private AllUsers loginUser;

    /**
     * 首页
     * 
     * @return
     */
    @PermissionSearch
    public String index() {
        // 过期访问权限控制
        HttpSession session = this.getSession();
        session.setAttribute("ACEGI_SECURITY_ACCESS", "access");
        ack = "a47b7b4fab0be92acbcc4e3f6e9bd96c";
        session.setAttribute("ack", ack);
        String driver = PropertiesUtil.readValue(
                this.getServletRequest().getSession().getServletContext().getRealPath("WEB-INF/env.properties"),
                "jdbc.url");
        if (!"".equals(driver)) {
            this.setFailMessage("");
            HttpServletRequest request = getServletRequest();
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length != 0) {
                for (int i = 0; i < cookies.length; i++) {
                    Cookie cookie = cookies[i];
                    if ("PS".equals(cookie.getName())) {
                        passport = cookie.getValue();
                        break;
                    }
                }
            }
            return "index";
        } else {
            return "initCon";
        }
    }

    @PermissionSearch
    @SuppressWarnings("deprecation")
    public String initConnect() throws ClassNotFoundException, SQLException {
        setFailMessage("");
        String env = this.getServletRequest().getRealPath("WEB-INF/env.properties");
        String jdbc = "jdbc:oracle:thin:" + jdbcUsername + "/" + jdbcPassword + "@" + jdbcUrl + ":1521:" + jdbcSid;
        Connection con = null;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection(jdbc, jdbcUsername, jdbcPassword);
        if (con != null) {
            PropertiesUtil.writeProperties(env, "jdbc.url", jdbc);
            PropertiesUtil.writeProperties(env, "jdbc.username", jdbcUsername);
            PropertiesUtil.writeProperties(env, "jdbc.password", jdbcPassword);
            if (!"".equals(ldapHost)) {
                String bs[] = ldapDomain.split("\\.");
                String base = "DC=" + bs[0] + ",DC=" + bs[1];
                String dn = "CN=" + ldapUser + ",CN=Users," + base;
                PropertiesUtil.writeProperties(env, "ldap.ldapHost", "ladp://" + ldapHost + ":389");
                PropertiesUtil.writeProperties(env, "ldap.ldapHost2", "ladp://" + ldapHost + ":636");
                PropertiesUtil.writeProperties(env, "ldap.domain", "@" + ldapDomain);
                PropertiesUtil.writeProperties(env, "ldap.base", base);
                PropertiesUtil.writeProperties(env, "ldap.userDn", dn);
                PropertiesUtil.writeProperties(env, "ldap.password", ldapPassword);
                PropertiesUtil.writeProperties(env, "ldap.validate", "true");
            } else {
                PropertiesUtil.writeProperties(env, "ldap.validate", "false");
            }
            con.close();
            // 重载bean
            XmlWebApplicationContext context = (XmlWebApplicationContext) WebApplicationContextUtils
                    .getWebApplicationContext(this.getServletRequest().getSession().getServletContext());
            context.refresh();
        }
        return "index";
    }

    /**
     * 登录页
     * 
     * @return
     */
    @PermissionSearch
    public String login() {

        ValidateResult result = null;

        // true:需要域验证 false:不需要域验证
        if (validate) {
            result = caService.validateUserByLDAP(passport.trim().toUpperCase(), password);
        } else if (passport != null) {
            if ("admin".equals(passport))
                result = caService.validateUser(passport.trim(), password);
            else
                result = caService.validateUser(passport.trim().toUpperCase(), password);
        }
        HttpSession session = this.getSession();
        int logintimes=0;
        if(passport != null) {
        	logintimes = accountService.searchLoginTimes(passport.trim());
        }
        
        // 验证失败
        if (result == null) {
        	logintimes = logintimes +1;
            if(logintimes>5) {
            	//锁定
            	this.setFailMessage("User account has been locked, please contact your salesperson!");
            	if(passport != null)
            	{
            		accountService.addLoginTimes(passport.trim());
            		AllUsers allUsers = allUserService.getAllUserByPassport(passport);
            		if(allUsers!=null && allUsers.getUserId()!=null && allUsers.getUserId()!="") {
                		allUsers.setUserState("N");                		
                	//	LDAPService.disableUser2Ad(allUsers);
                	//	allUserService.deleteUserByEmpId(allUsers);
                		allUserService.forbidden(allUsers);
            		}            		
        		}
            	return LOGFAIL;
            }
            else {
            	this.setFailMessage("User name or password entered is incorrect！!");
            	if(passport != null)
            	{
            		accountService.addLoginTimes(passport.trim());
            	}
            	return LOGFAIL;
            }
        }

        if (ICAService.RESULT_FAILED.equals(result.getResultCode())
                || ICAService.RESULT_ERROR.equals(result.getResultCode())) 
        {
        	logintimes = logintimes +1;
        	if(logintimes>5) {
            	//锁定
        		this.setFailMessage("User account has been locked, please contact your salesperson!");
        		if(passport != null)
            	{
        			accountService.addLoginTimes(passport.trim());
        			AllUsers allUsers = allUserService.getAllUserByPassport(passport);
            		if(allUsers!=null && allUsers.getUserId()!=null && allUsers.getUserId()!="") {
                		allUsers.setUserState("N");                		
                		//LDAPService.disableUser2Ad(allUsers);
                		//allUserService.deleteUserByEmpId(allUsers);
                		allUserService.forbidden(allUsers);                		
            		}    
            	}
            	return LOGFAIL;
            }
        	else {
        		this.setFailMessage(result.getMessage());   
        		if(passport != null)
            	{
        			accountService.addLoginTimes(passport.trim());        			
            	}
                return LOGFAIL;
        	}
            
        }
        loginUser = result.getAllUser();
        name = loginUser.getUserName();

        
        session.setAttribute("ACEGI_SECURITY_LAST_LOGINID", loginUser.getLoginId());
        session.setAttribute("ACEGI_SECURITY_LAST_LOGINUSER", loginUser);
        session.setAttribute("ACEGI_SECURITY_LAST_VCODE", "3380169ff852b607d84d62c075c1fec4");
        HttpServletResponse response = getServletResponse();
        if (response != null) {
            Cookie ps = new Cookie("PS", loginUser.getLoginId());
            ps.setPath("/");
            ps.setDomain(domain);
            ps.setMaxAge(28800);
            response.addCookie(ps);
            try {
                password = EncryptUtil.md5Encry(password);
            } catch (Exception e) {
                password = null;
            }
            //Cookie pw = new Cookie("PW", password);
            //pw.setPath("/");
            //pw.setDomain(domain);
            //pw.setMaxAge(28800);
            //response.addCookie(pw);

            // 论坛SSO登录 从cooike取
            accountService.deleteLoginTimes(loginUser.getLoginId());
            try {
                String nameTemp = URLEncoder.encode(name, "gbk");
                // admin登录只存loginid
                String cookieV = "admin".equals(loginUser.getLoginId()) ? loginUser.getLoginId()
                        : nameTemp + "(" + loginUser.getLoginId() + ")";
                Cookie jforumName = new Cookie("jforumName", cookieV);
                jforumName.setPath("/");
                jforumName.setDomain(domain);
                jforumName.setMaxAge(28800);
                response.addCookie(jforumName);
            } catch (UnsupportedEncodingException e) {
                logger.error(e);
            }
        }
        
        
        
        onLine = OnlineCounter.getOnline();
        if ("0".equals(loginUser.getIsFirst())) {
            return "firstLogin";
        } else {
            return SUCCESS;
        }
    }
    
    public static void main(String[] args) {
    	try {
			String ssString = EncryptUtil.md5Encry("Happy2021@@");
			System.out.println(ssString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public String singleIndex() {

        // 过期访问权限控制
        HttpSession session = this.getSession();
        session.setAttribute("ACEGI_SECURITY_ACCESS", "access");

        String driver = PropertiesUtil.readValue(
                this.getServletRequest().getSession().getServletContext().getRealPath("WEB-INF/env.properties"),
                "jdbc.url");
        if (!"".equals(driver)) {
            this.setFailMessage("");
            HttpServletRequest request = getServletRequest();
            Cookie[] cookies = request.getCookies();
            session.setAttribute("param", param);
            if (cookies != null && cookies.length != 0) {
                for (int i = 0; i < cookies.length; i++) {
                    Cookie cookie = cookies[i];
                    if ("PS".equals(cookie.getName())) {
                        passport = cookie.getValue();
                        break;
                    }
                }
            }
            return "singleIndex";
        } else {
            return "singleInitCon";
        }

    }

    /**
     * 单点登录
     * 
     * @return
     */
    public String singleLogin() {
        Map session = ActionContext.getContext().getSession();//
        session.put("ACEGI_SECURITY_ACCESS", "access");

        String driver = PropertiesUtil.readValue(
                this.getServletRequest().getSession().getServletContext().getRealPath("WEB-INF/env.properties"),
                "jdbc.url");
        if (!"".equals(driver)) {
            this.setFailMessage("");
            HttpServletRequest request = getServletRequest();
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length != 0) {
                for (int i = 0; i < cookies.length; i++) {
                    Cookie cookie = cookies[i];
                    if ("PS".equals(cookie.getName())) {
                        passport = cookie.getValue();
                        break;
                    }
                }
            }
        }

        // 判断过期访问权限是否已经销毁`
        String access = (String) session.get("ACEGI_SECURITY_ACCESS");

        if (("access").equals(access)) {

            ValidateResult result = null;
            String loginStr;
            try {
                loginStr = new String(new Base64().decode(this.param), "UTF-8");
                String userName = loginStr.split("&")[0];
                String vCode = loginStr.split("&")[1];
                System.out.println(vCode.split("=")[1]);
                if (vCode.split("=")[1] == null || !vCode.split("=")[1].trim().equals("bGFuanV3ZWJzZXJ2aWNl")) {
                    return "logintimeout";
                }
                AllUsers userTemp = new AllUsers();
                userTemp.setLoginId(userName.split("=")[1]);
                AllUsers user = this.caService.queryAllUser(userTemp);
                this.passport = user.getLoginId();
                this.password = user.getPassWd();

            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            // true:需要域验证 false:不需要域验证
            if (validate) {
                result = caService.singleValidateUserByLDAP(passport, password);
            } else {
                result = caService.singleValidateUser(passport, password);
            }

            // 验证失败
            if (ICAService.RESULT_FAILED.equals(result.getResultCode())
                    || ICAService.RESULT_ERROR.equals(result.getResultCode())) {
                this.setFailMessage(result.getMessage());
                return LOGFAIL;
            }

            loginUser = result.getAllUser();
            name = loginUser.getUserName();

            session.put("ACEGI_SECURITY_LAST_LOGINID", loginUser.getLoginId());
            session.put("ACEGI_SECURITY_LAST_LOGINUSER", loginUser);
            HttpServletResponse response = getServletResponse();
            if (response != null) {
                Cookie ps = new Cookie("PS", loginUser.getLoginId());
                ps.setPath("/");
                ps.setDomain(domain);
                ps.setMaxAge(7200);
                response.addCookie(ps);

                //Cookie pw = new Cookie("PW", password);
                //pw.setPath("/");
                //pw.setDomain(domain);
                //pw.setMaxAge(7200);
                //response.addCookie(pw);

                // 论坛SSO登录 从cooike取
                try {
                    String nameTemp = URLEncoder.encode(name, "gbk");
                    // admin登录只存loginid
                    String cookieV = "admin".equals(loginUser.getLoginId()) ? loginUser.getLoginId()
                            : nameTemp + "(" + loginUser.getLoginId() + ")";
                    Cookie jforumName = new Cookie("jforumName", cookieV);
                    jforumName.setPath("/");
                    jforumName.setDomain(domain);
                    jforumName.setMaxAge(7200);
                    response.addCookie(jforumName);
                } catch (UnsupportedEncodingException e) {
                    logger.error(e);
                }
            }
            onLine = OnlineCounter.getOnline();
            if ("0".equals(loginUser.getIsFirst())) {
                return "firstLogin";
            } else {
                return SUCCESS;
            }
        } else {
            return "logintimeout";
        }
    }

    /**
     * OA点击分货流程代办任务时的单点登录
     * 
     * @return
     */
    public String oaWorkflowSingleLogin() {
        Map session = (Map<String, Serializable>) ActionContext.getContext().getSession();//
        session.put("ACEGI_SECURITY_ACCESS", "access");

        String driver = PropertiesUtil.readValue(
                this.getServletRequest().getSession().getServletContext().getRealPath("WEB-INF/env.properties"),
                "jdbc.url");
        if (!"".equals(driver)) {
            this.setFailMessage("");
            HttpServletRequest request = getServletRequest();
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length != 0) {
                for (int i = 0; i < cookies.length; i++) {
                    Cookie cookie = cookies[i];
                    if ("PS".equals(cookie.getName())) {
                        passport = cookie.getValue();
                        break;
                    }
                }
            }
        }

        // 判断过期访问权限是否已经销毁`
        String access = (String) session.get("ACEGI_SECURITY_ACCESS");

        if (("access").equals(access)) {

            ValidateResult result = null;
            String loginStr;
            try {
                loginStr = new String(new Base64().decode(this.param), "UTF-8");
                String userName = loginStr.split("&")[0];
                String vCode = loginStr.split("&")[1];
                System.out.println(vCode.split("=")[1]);
                if (vCode.split("=")[1] == null || !vCode.split("=")[1].trim().equals("bGFuanV3ZWJzZXJ2aWNl")) {
                    return "logintimeout";
                }
                System.out.println("UserName:" + userName.split("=")[1] + "vCode:" + vCode);

                AllUsers userTemp = new AllUsers();
                userTemp.setLoginId(userName.split("=")[1]);
                AllUsers user = this.caService.queryAllUser(userTemp);
                this.passport = user.getLoginId();
                this.password = user.getPassWd();

            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            // true:需要域验证 false:不需要域验证
            if (validate) {
                result = caService.singleValidateUserByLDAP(passport, password);
            } else {
                result = caService.singleValidateUser(passport, password);
            }

            // 验证失败
            if (ICAService.RESULT_FAILED.equals(result.getResultCode())
                    || ICAService.RESULT_ERROR.equals(result.getResultCode())) {
                this.setFailMessage(result.getMessage());
                return LOGFAIL;
            }

            loginUser = result.getAllUser();
            name = loginUser.getUserName();

            session.put("ACEGI_SECURITY_LAST_LOGINID", loginUser.getLoginId());
            session.put("ACEGI_SECURITY_LAST_LOGINUSER", loginUser);
            HttpServletResponse response = getServletResponse();
            if (response != null) {
                Cookie ps = new Cookie("PS", loginUser.getLoginId());
                ps.setPath("/");
                ps.setDomain(domain);
                ps.setMaxAge(7200);
                response.addCookie(ps);
//				try {
//					password = password;
//				} catch (Exception e) {
//					password = null;
//				}
                //Cookie pw = new Cookie("PW", password);
                //pw.setPath("/");
                //pw.setDomain(domain);
                //pw.setMaxAge(7200);
                //response.addCookie(pw);

                // 论坛SSO登录 从cooike取
                try {
                    String nameTemp = URLEncoder.encode(name, "gbk");
                    // admin登录只存loginid
                    String cookieV = "admin".equals(loginUser.getLoginId()) ? loginUser.getLoginId()
                            : nameTemp + "(" + loginUser.getLoginId() + ")";
                    Cookie jforumName = new Cookie("jforumName", cookieV);
                    jforumName.setPath("/");
                    jforumName.setDomain(domain);
                    jforumName.setMaxAge(7200);
                    response.addCookie(jforumName);
                } catch (UnsupportedEncodingException e) {
                    logger.error(e);
                }
            }
            onLine = OnlineCounter.getOnline();
            if ("0".equals(loginUser.getIsFirst())) {
                return "firstLogin";
            } else {
                return "oaWorkflow";
            }
        } else {
            return "logintimeout";
        }
    }
//   http://jingtong.com:8080/crmPlatform/demandAction!demandPre.jspa

    @PermissionSearch
    public String logout() {
        HttpSession session = this.getSession();
        HttpServletResponse response = getServletResponse();
        try {
            session.removeAttribute("ACEGI_SECURITY_LAST_LOGINID");
            session.removeAttribute("ACEGI_SECURITY_LAST_LOGINUSER");
            session.removeAttribute("ACEGI_SECURITY_ACCESS");
            session.invalidate();

            // 删除论坛SSO
            Cookie cookie = new Cookie("jforumSSOCookieNameUser", "");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            if ("logout".equals(this.getServletRequest().getParameter("action"))) {
                this.setFailMessage("您已成功退出exp系统!");
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return LOGOUT;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public ICAService getCaService() {
        return caService;
    }

    public void setCaService(ICAService caService) {
        this.caService = caService;
    }
    
    public IAccountService getAccountService() {
    	return accountService;
    }
    
    public void setAccountService(IAccountService accountService) {
    	this.accountService =  accountService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcUsername() {
        return jdbcUsername;
    }

    public void setJdbcUsername(String jdbcUsername) {
        this.jdbcUsername = jdbcUsername;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    public String getLdapHost() {
        return ldapHost;
    }

    public void setLdapHost(String ldapHost) {
        this.ldapHost = ldapHost;
    }

    public String getLdapUser() {
        return ldapUser;
    }

    public void setLdapUser(String ldapUser) {
        this.ldapUser = ldapUser;
    }

    public String getLdapPassword() {
        return ldapPassword;
    }

    public void setLdapPassword(String ldapPassword) {
        this.ldapPassword = ldapPassword;
    }

    public String getLdapDomain() {
        return ldapDomain;
    }

    public void setLdapDomain(String ldapDomain) {
        this.ldapDomain = ldapDomain;
    }

    public String getJdbcSid() {
        return jdbcSid;
    }

    public void setJdbcSid(String jdbcSid) {
        this.jdbcSid = jdbcSid;
    }

    public long getOnLine() {
        return onLine;
    }

    public void setOnLine(long onLine) {
        this.onLine = onLine;
    }

    public AllUsers getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(AllUsers loginUser) {
        this.loginUser = loginUser;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

	public IAllUserService getAllUserService() {
		return allUserService;
	}

	public void setAllUserService(IAllUserService allUserService) {
		this.allUserService = allUserService;
	}

	public ILDAPService getLDAPService() {
		return LDAPService;
	}

	public void setLDAPService(ILDAPService lDAPService) {
		LDAPService = lDAPService;
	}

//	public static void main(String[] args) {
//		String ss = "userName=010177&vCode="+new BASE64Encoder().encode("lanjuwebservice".getBytes());
//		System.out.println(new BASE64Encoder().encode(ss.getBytes()));
//	}
}
