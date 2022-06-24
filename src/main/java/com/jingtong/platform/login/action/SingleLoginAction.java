package com.jingtong.platform.login.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.framework.content.listener.OnlineCounter;
import com.jingtong.platform.framework.util.PropertiesUtil;
import com.jingtong.platform.login.pojo.ValidateResult;
import com.jingtong.platform.login.service.ICAService;
import com.opensymphony.xwork2.ActionContext;

/**
 * OA代办任务单点登录
 * 
 * @author
 *
 */
public class SingleLoginAction extends BaseAction {

    private Log logger = LogFactory.getLog(SingleLoginAction.class);

    private String param;// 单点登录使用的参数
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

    // 分货跳转路径
    private String distributionUrl;

    /**
     * 单点登录
     * 
     * @return
     */
    public String singleLogin() {
        Map session = ActionContext.getContext().getSession();//
        session.put("ACEGI_SECURITY_ACCESS", "access");

        String driver = PropertiesUtil.readValue(this.getServletRequest().getRealPath("WEB-INF/env.properties"),
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
//	   

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
                ps.setMaxAge(3600);
                response.addCookie(ps);
                //Cookie pw = new Cookie("PW", password);
                //pw.setPath("/");
                //pw.setDomain(domain);
                //pw.setMaxAge(3600);
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
                    jforumName.setMaxAge(3600);
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
//		HttpSession session =  this.getServletRequest().getSession();
        // this.distributionUrl =
        // "http://jingtong.com:8080/crmPlatform/demandAction!demandPre.jspa";
        String oaWorkflowSingleLoginUrl = PropertiesUtil
                .readValue(this.getServletRequest().getRealPath("WEB-INF/env.properties"), "oaWorkflowSingleLoginUrl");
        this.distributionUrl = oaWorkflowSingleLoginUrl; // "http://192.168.0.183:8080/crmPlatform/demandAction!demandPre.jspa";

        Map session = ActionContext.getContext().getSession();//
        session.put("ACEGI_SECURITY_ACCESS", "access");

        String driver = PropertiesUtil.readValue(this.getServletRequest().getRealPath("WEB-INF/env.properties"),
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
//	   

        // 判断过期访问权限是否已经销毁`
        String access = (String) session.get("ACEGI_SECURITY_ACCESS");

        if (("access").equals(access)) {

            ValidateResult result = null;
            String loginStr;
            try {
//           	this.param = (String) session.getAttribute("param");
//           	System.out.println("param:"+this.param);
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
                ps.setMaxAge(3600);
                response.addCookie(ps);
//				try {
//					password = password;
//				} catch (Exception e) {
//					password = null;
//				}
                //Cookie pw = new Cookie("PW", password);
                //pw.setPath("/");
                //pw.setDomain(domain);
                //pw.setMaxAge(3600);
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
                    jforumName.setMaxAge(3600);
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

    /**
     * OA点击分货流程代办任务时的单点登录
     * 
     * @return
     */
    public String oaWorkflowCirculatSingleLogin() {
//			HttpSession session =  this.getServletRequest().getSession();
        String oaWorkflowCirculatSingleLoginUrl = PropertiesUtil.readValue(
                this.getServletRequest().getRealPath("WEB-INF/env.properties"), "oaWorkflowCirculatSingleLoginUrl");

        this.distributionUrl = oaWorkflowCirculatSingleLoginUrl;// "http://192.168.0.183:8080/crmPlatform/demandCirculatAction!toCreateDemandCirculat.jspa";
        Map session = ActionContext.getContext().getSession();//
        session.put("ACEGI_SECURITY_ACCESS", "access");

        String driver = PropertiesUtil.readValue(this.getServletRequest().getRealPath("WEB-INF/env.properties"),
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
//		   

        // 判断过期访问权限是否已经销毁`
        String access = (String) session.get("ACEGI_SECURITY_ACCESS");

        if (("access").equals(access)) {

            ValidateResult result = null;
            String loginStr;
            try {
//	           	this.param = (String) session.getAttribute("param");
//	           	System.out.println("param:"+this.param);
                loginStr = new String(new Base64().decode(this.param), "UTF-8");
                String userName = loginStr.split("&")[0];
                String vCode = loginStr.split("&")[1];
                String promotionId = loginStr.split("&")[2];
                String demandMonth = loginStr.split("&")[3];
                distributionUrl = distributionUrl + "?promotionId=" + promotionId.split("=")[1] + "&demandMonth="
                        + demandMonth.split("=")[1];
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
                ps.setMaxAge(3600);
                response.addCookie(ps);

                //Cookie pw = new Cookie("PW", password);
                //pw.setPath("/");
                //pw.setDomain(domain);
                //pw.setMaxAge(3600);
                //response.addCookie(pw);

                try {
                    String nameTemp = URLEncoder.encode(name, "gbk");
                    String cookieV = "admin".equals(loginUser.getLoginId()) ? loginUser.getLoginId()
                            : nameTemp + "(" + loginUser.getLoginId() + ")";
                    Cookie jforumName = new Cookie("jforumName", cookieV);
                    jforumName.setPath("/");
                    jforumName.setDomain(domain);
                    jforumName.setMaxAge(3600);
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

    public String oaWorkflowMarketSingleLogin() {
//			HttpSession session =  this.getServletRequest().getSession();
        String oaWorkflowMarketSingleLoginUrl = PropertiesUtil.readValue(
                this.getServletRequest().getRealPath("WEB-INF/env.properties"), "oaWorkflowMarketSingleLoginUrl");

        this.distributionUrl = oaWorkflowMarketSingleLoginUrl;// "http://192.168.0.183:8080/crmPlatform/demandCirculatAction!searchMarket.jspa";
        Map session = ActionContext.getContext().getSession();//
        session.put("ACEGI_SECURITY_ACCESS", "access");

        String driver = PropertiesUtil.readValue(this.getServletRequest().getRealPath("WEB-INF/env.properties"),
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
//		   

        // 判断过期访问权限是否已经销毁`
        String access = (String) session.get("ACEGI_SECURITY_ACCESS");

        if (("access").equals(access)) {

            ValidateResult result = null;
            String loginStr;
            try {
//	           	this.param = (String) session.getAttribute("param");
//	           	System.out.println("param:"+this.param);
                loginStr = new String(new Base64().decode(this.param), "UTF-8");
                String userName = loginStr.split("&")[0];
                String vCode = loginStr.split("&")[1];
                String promotionId = loginStr.split("&")[2];
                String demandMonth = loginStr.split("&")[3];
                distributionUrl = distributionUrl + "?promotionId=" + promotionId.split("=")[1] + "&demandMonth="
                        + demandMonth.split("=")[1];
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
                ps.setMaxAge(3600);
                response.addCookie(ps);
//					try {
//						password = password;
//					} catch (Exception e) {
//						password = null;
//					}
                //Cookie pw = new Cookie("PW", password);
                //pw.setPath("/");
                //pw.setDomain(domain);
                //pw.setMaxAge(3600);
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
                    jforumName.setMaxAge(3600);
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

    public String oaWorkflowRegionSingleLogin() {
//			HttpSession session =  this.getServletRequest().getSession();

        String oaWorkflowRegionSingleLoginUrl = PropertiesUtil.readValue(
                this.getServletRequest().getRealPath("WEB-INF/env.properties"), "oaWorkflowRegionSingleLoginUrl");

        this.distributionUrl = oaWorkflowRegionSingleLoginUrl;// "http://192.168.0.183:8080/crmPlatform/demandCirculatAction!searchLargeArea.jspa";
        Map session = ActionContext.getContext().getSession();//
        session.put("ACEGI_SECURITY_ACCESS", "access");

        String driver = PropertiesUtil.readValue(this.getServletRequest().getRealPath("WEB-INF/env.properties"),
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
//		   

        // 判断过期访问权限是否已经销毁`
        String access = (String) session.get("ACEGI_SECURITY_ACCESS");

        if (("access").equals(access)) {

            ValidateResult result = null;
            String loginStr;
            try {
//	           	this.param = (String) session.getAttribute("param");
//	           	System.out.println("param:"+this.param);
                loginStr = new String(new Base64().decode(this.param), "UTF-8");
                String userName = loginStr.split("&")[0];
                String vCode = loginStr.split("&")[1];
                String promotionId = loginStr.split("&")[2];
                String demandMonth = loginStr.split("&")[3];
                distributionUrl = distributionUrl + "?promotionId=" + promotionId.split("=")[1] + "&demandMonth="
                        + demandMonth.split("=")[1];
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
                ps.setMaxAge(3600);
                response.addCookie(ps);

                //Cookie pw = new Cookie("PW", password);
                //pw.setPath("/");
                //pw.setDomain(domain);
                //pw.setMaxAge(3600);
                //response.addCookie(pw);

                try {
                    String nameTemp = URLEncoder.encode(name, "gbk");
                    String cookieV = "admin".equals(loginUser.getLoginId()) ? loginUser.getLoginId()
                            : nameTemp + "(" + loginUser.getLoginId() + ")";
                    Cookie jforumName = new Cookie("jforumName", cookieV);
                    jforumName.setPath("/");
                    jforumName.setDomain(domain);
                    jforumName.setMaxAge(3600);
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

    public String oaWorkflowReportSingleLogin() {
        String oaWorkflowSingleLoginUrl = PropertiesUtil.readValue(
                this.getServletRequest().getRealPath("WEB-INF/env.properties"), "oaWorkflowReportSingleLoginUrl");
        this.distributionUrl = oaWorkflowSingleLoginUrl; // "http://192.168.0.183:8080/crmPlatform/demandAction!demandPre.jspa";
        Map session = ActionContext.getContext().getSession();//
        session.put("ACEGI_SECURITY_ACCESS", "access");

        String driver = PropertiesUtil.readValue(this.getServletRequest().getRealPath("WEB-INF/env.properties"),
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
//		   

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
                String planId = loginStr.split("&")[2];
                distributionUrl = distributionUrl + "?planId=" + planId.split("=")[1];
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
                ps.setMaxAge(3600);
                response.addCookie(ps);

                //Cookie pw = new Cookie("PW", password);
                //pw.setPath("/");
                //pw.setDomain(domain);
                //pw.setMaxAge(3600);
                //response.addCookie(pw);

                try {
                    String nameTemp = URLEncoder.encode(name, "gbk");
                    String cookieV = "admin".equals(loginUser.getLoginId()) ? loginUser.getLoginId()
                            : nameTemp + "(" + loginUser.getLoginId() + ")";
                    Cookie jforumName = new Cookie("jforumName", cookieV);
                    jforumName.setPath("/");
                    jforumName.setDomain(domain);
                    jforumName.setMaxAge(3600);
                    response.addCookie(jforumName);
                } catch (UnsupportedEncodingException e) {
                    logger.error(e);
                }
            }
            onLine = OnlineCounter.getOnline();
            return "oaWorkflow";
        } else {
            return "logintimeout";
        }
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

    public String getDistributionUrl() {
        return distributionUrl;
    }

    public void setDistributionUrl(String distributionUrl) {
        this.distributionUrl = distributionUrl;
    }

//	public static void main(String[] args) {
//		String ss = "userName=010177&vCode="+new BASE64Encoder().encode("lanjuwebservice".getBytes());
//		System.out.println(new BASE64Encoder().encode(ss.getBytes()));
//	}
}
