package com.jingtong.platform.endCustomer.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.common.CommonUtil;
import com.jingtong.platform.common.Escape;
import com.jingtong.platform.customer.pojo.CustomerUser;
import com.jingtong.platform.customer.service.ICustomerService;
import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.endCustomer.pojo.ECAlias;
import com.jingtong.platform.endCustomer.pojo.EndCustomer;
import com.jingtong.platform.endCustomer.service.IEndCustomerService;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.framework.util.DataUtil;
import com.jingtong.platform.message.pojo.Message;
import com.jingtong.platform.message.service.IMessageService;
import com.jingtong.platform.quote.pojo.Quote;
import com.jingtong.platform.quote.service.IQuoteService;
import com.jingtong.platform.role.pojo.Role;
import com.jingtong.platform.role.service.IRoleService;

/** 2018/11/06 Add Change alias to EC */
public class EndCustomerAction extends BaseAction {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private EndCustomer ec;
    private List<EndCustomer> ecList;
    private IEndCustomerService endCustomerService;
    private String disti_customer_id;
    private String end_customer_name;
    private String pc_name;
    private String end_customer_id;
    private String pc_id;
    private String disti_customer_name;
    private String disti_groupId;
    private String state;
    private String states;
    private int total;
    private String id;
    private String updateState;
    private String end_customer_groupId;
    private String end_customer_groupName;
    private String customer_type;
    private CustomerUser cusUser;
    private List<CustomerUser> cusUserList;
    private ICustomerService customerService;
    private String ecPage;// ec查看页面（disti只能查看自己的，报价订单可以查看所有的）
    private List<Role> roleList;// 登陆人角色列表
    private IRoleService roleService;
    private String loginRole;// 标记登陆人的角色
    private ECAlias ea;
    private List<ECAlias> eaList;
    private String ec_id;
    private String ec_name;
    private String ec_group;
    private String ec_groupName;
    private String ec_city;
    private String ec_alias_name;
    private String country;
    private String e_name;
    private String p_name;
    private String e_country;
    private String p_country;
    private String e_province;
    private String p_province;
    private String e_city;
    private String p_city;
    private String e_zip;
    private String p_zip;
    private String noPCEC;
    private String countryOrg;
    private String province;
    private IQuoteService quoteService;
    private String smtpServer;// =env.getProperty("allUser.smtpServer");
    private String from;// =env.getProperty("allUser.from");
    private String displayName;// =env.getProperty("allUser.displayName");
    private String emailaddress;// =env.getProperty("allUser.emailaddress");
    private String emailpassword;// =env.getProperty("allUser.emailpassword");
    private Quote q;
    private String quote_id;
    private String alias_city;
    private IMessageService messageService;
    private String end_name;
    private String end_city;
    private String end_zip;
    private String editGroupMark;
    private String newHierarchy;
    private List<CmsTbDict> cmsTbDictList = new ArrayList<CmsTbDict>();
    private String noTranBegin;
    private String noTranEnd;
    
    public String getNoTranBegin() {
    	return this.noTranBegin;
    }
    
    public void setNoTranBegin(String noTranBegin )  {
    	
    	this.noTranBegin=noTranBegin;
    }
    
    public String getNoTranEnd() {
    	return this.noTranEnd;
    }
    
    public void setNoTranEnd(String noTranEnd) {
    	this.noTranEnd=noTranEnd;
    }

    public String getEditGroupMark() {
        return editGroupMark;
    }

    public void setEditGroupMark(String editGroupMark) {
        this.editGroupMark = editGroupMark;
    }

    public String getEnd_zip() {
        return end_zip;
    }

    public void setEnd_zip(String end_zip) {
        this.end_zip = end_zip;
    }

    public String getEnd_name() {
        return end_name;
    }

    public void setEnd_name(String end_name) {
        this.end_name = end_name;
    }

    public String getEnd_city() {
        return end_city;
    }

    public void setEnd_city(String end_city) {
        this.end_city = end_city;
    }

    public String getNewHierarchy() {
        return newHierarchy;
    }

    public void setNewHierarchy(String newHierarchy) {
        this.newHierarchy = newHierarchy;
    }

    public List<CmsTbDict> getCmsTbDictList() {
        return cmsTbDictList;
    }

    public void setCmsTbDictList(List<CmsTbDict> cmsTbDictList) {
        this.cmsTbDictList = cmsTbDictList;
    }

    public EndCustomer getEc() {
        return ec;
    }

    public void setEc(EndCustomer ec) {
        this.ec = ec;
    }

    public List<EndCustomer> getEcList() {
        return ecList;
    }

    public void setEcList(List<EndCustomer> ecList) {
        this.ecList = ecList;
    }

    public IEndCustomerService getEndCustomerService() {
        return endCustomerService;
    }

    public void setEndCustomerService(IEndCustomerService endCustomerService) {
        this.endCustomerService = endCustomerService;
    }

    public String getDisti_customer_id() {
        return disti_customer_id;
    }

    public void setDisti_customer_id(String disti_customer_id) {
        this.disti_customer_id = disti_customer_id;
    }

    public String getEnd_customer_name() {
        return end_customer_name;
    }

    public void setEnd_customer_name(String end_customer_name) {
        this.end_customer_name = end_customer_name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnd_customer_id() {
        return end_customer_id;
    }

    public String getUpdateState() {
        return updateState;
    }

    public void setUpdateState(String updateState) {
        this.updateState = updateState;
    }

    public void setEnd_customer_id(String end_customer_id) {
        this.end_customer_id = end_customer_id;
    }

    public String getDisti_customer_name() {
        return disti_customer_name;
    }

    public void setDisti_customer_name(String disti_customer_name) {
        this.disti_customer_name = disti_customer_name;
    }

    public String getEnd_customer_groupId() {
        return end_customer_groupId;
    }

    public void setEnd_customer_groupId(String end_customer_groupId) {
        this.end_customer_groupId = end_customer_groupId;
    }

    public String getCustomer_type() {
        return customer_type;
    }

    public void setCustomer_type(String customer_type) {
        this.customer_type = customer_type;
    }

    public CustomerUser getCusUser() {
        return cusUser;
    }

    public void setCusUser(CustomerUser cusUser) {
        this.cusUser = cusUser;
    }

    public List<CustomerUser> getCusUserList() {
        return cusUserList;
    }

    public void setCusUserList(List<CustomerUser> cusUserList) {
        this.cusUserList = cusUserList;
    }

    public ICustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(ICustomerService customerService) {
        this.customerService = customerService;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public String getEcPage() {
        return ecPage;
    }

    public void setEcPage(String ecPage) {
        this.ecPage = ecPage;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public IRoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }

    public String getLoginRole() {
        return loginRole;
    }

    public void setLoginRole(String loginRole) {
        this.loginRole = loginRole;
    }

    public ECAlias getEa() {
        return ea;
    }

    public void setEa(ECAlias ea) {
        this.ea = ea;
    }

    public List<ECAlias> getEaList() {
        return eaList;
    }

    public void setEaList(List<ECAlias> eaList) {
        this.eaList = eaList;
    }

    public String getEc_id() {
        return ec_id;
    }

    public void setEc_id(String ec_id) {
        this.ec_id = ec_id;
    }

    public String getEc_name() {
        return ec_name;
    }

    public void setEc_name(String ec_name) {
        this.ec_name = ec_name;
    }

    public String getEc_group() {
        return ec_group;
    }

    public void setEc_group(String ec_group) {
        this.ec_group = ec_group;
    }

    public String getEc_city() {
        return ec_city;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getNoPCEC() {
        return noPCEC;
    }

    public void setNoPCEC(String noPCEC) {
        this.noPCEC = noPCEC;
    }

    public IQuoteService getQuoteService() {
        return quoteService;
    }

    public void setQuoteService(IQuoteService quoteService) {
        this.quoteService = quoteService;
    }

    public Quote getQ() {
        return q;
    }

    public void setQ(Quote q) {
        this.q = q;
    }

    public String getQuote_id() {
        return quote_id;
    }

    public void setQuote_id(String quote_id) {
        this.quote_id = quote_id;
    }

    public void setEc_city(String ec_city) {
        this.ec_city = ec_city;
    }

    public String getDisti_groupId() {
        return disti_groupId;
    }

    public void setDisti_groupId(String disti_groupId) {
        this.disti_groupId = disti_groupId;
    }

    public String getEnd_customer_groupName() {
        return end_customer_groupName;
    }

    public void setEnd_customer_groupName(String end_customer_groupName) {
        this.end_customer_groupName = end_customer_groupName;
    }

    public String getEc_alias_name() {
        return ec_alias_name;
    }

    public void setEc_alias_name(String ec_alias_name) {
        this.ec_alias_name = ec_alias_name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getE_country() {
        return e_country;
    }

    public void setE_country(String e_country) {
        this.e_country = e_country;
    }

    public String getP_country() {
        return p_country;
    }

    public void setP_country(String p_country) {
        this.p_country = p_country;
    }

    public String getE_province() {
        return e_province;
    }

    public void setE_province(String e_province) {
        this.e_province = e_province;
    }

    public String getP_province() {
        return p_province;
    }

    public void setP_province(String p_province) {
        this.p_province = p_province;
    }

    public String getE_city() {
        return e_city;
    }

    public void setE_city(String e_city) {
        this.e_city = e_city;
    }

    public String getP_city() {
        return p_city;
    }

    public void setP_city(String p_city) {
        this.p_city = p_city;
    }

    public String getE_zip() {
        return e_zip;
    }

    public void setE_zip(String e_zip) {
        this.e_zip = e_zip;
    }

    public String getP_zip() {
        return p_zip;
    }

    public void setP_zip(String p_zip) {
        this.p_zip = p_zip;
    }

    public String getCountryOrg() {
        return countryOrg;
    }

    public void setCountryOrg(String countryOrg) {
        this.countryOrg = countryOrg;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAlias_city() {
        return alias_city;
    }

    public void setAlias_city(String alias_city) {
        this.alias_city = alias_city;
    }

    public IMessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(IMessageService messageService) {
        this.messageService = messageService;
    }

    public String getEc_groupName() {
        return ec_groupName;
    }

    public void setEc_groupName(String ec_groupName) {
        this.ec_groupName = ec_groupName;
    }

    public String getPc_name() {
        return pc_name;
    }

    public void setPc_name(String pc_name) {
        this.pc_name = pc_name;
    }

    public String getPc_id() {
        return pc_id;
    }

    public void setPc_id(String pc_id) {
        this.pc_id = pc_id;
    }

    public String toSearchEndCustomer() {
        String userId = this.getUser().getUserId();
        Role r = new Role();
        r.setStart(0);
        r.setEnd(100);
        r.setEmp_code(userId);
        loginRole = "";
        roleList = roleService.getSelectedRole4StationList(r);
        for (Role rl : roleList) {
            loginRole += rl.getRoleId() + "*";
            /*
             * if("JXS".equals(rl.getRoleId())){ disti_groupId =
             * this.getUser().getUserName(); }
             */
        }
        String orgString = this.getUser().getOrgId();
        if (orgString == null || "".equals(orgString)) {
            loginRole += "JXS";
            disti_groupId = this.getUser().getUserName();
        }
        return "toSearchEndCustomer";
    }

    public String toPenddingEndCustomer() {
        return "toPenddingEndCustomer";
    }

    public String toAuditEndCustomer() {
        ec = new EndCustomer();
        ec.setState(Integer.valueOf(state));
        return "toAuditEndCustomer";
    }

    public String toChangeEC() {
        ec = new EndCustomer();
        ec.setId(Long.valueOf(id));
        ec = endCustomerService.getEndCustomerById(ec);
        return "toChangeEC";
    }

    public String toViewEndCustomer() {
        ec = new EndCustomer();
        ec.setId(Long.valueOf(id));
        ec = endCustomerService.getEndCustomerById(ec);
        String str = ec.getFile_path();
        if (str != null && str.lastIndexOf("webapp") != -1) {
            str = str.substring(str.lastIndexOf("webapp") - 1, str.length());
            ec.setFile_path(str);
        }
        return "toViewEndCustomer";
    }

    public String toPendingData() {
        ec = new EndCustomer();
        ec.setId(Long.valueOf(id));
        ec = endCustomerService.getEndCustomerById(ec);
        String str = ec.getFile_path();
        if (str != null && str.lastIndexOf("webapp") != -1) {
            str = str.substring(str.lastIndexOf("webapp") - 1, str.length());
            ec.setFile_path(str);
        }
        return "toPendingData";
    }

    public String toCreateEndCustomer() {
        ec = new EndCustomer();
        ec.setState(0);
        String loginId = this.getUser().getLoginId();
        String orgId = this.getUser().getOrgId();
        ec.setOrg_code(orgId);
        if (!"admin".equals(loginId)) {
            ec.setDisti_groupId(this.getUser().getUserName());
        }
        ec.setEnd_customer_name(Escape.unescape(end_name));
        ec.setCity(Escape.unescape(end_city));
        ec.setZip(Escape.unescape(end_zip));
        return "toCreateEndCustomer";
    }

    public String toCreatePCEC() {
        ec = new EndCustomer();
        ec.setState(0);
        ec.setNoPCEC(noPCEC);
        ec.setE_name(e_name);
        ec.setP_name(p_name);
        ec.setE_country(e_country);
        ec.setP_country(p_country);
        ec.setE_province(e_province);
        ec.setP_province(p_province);
        ec.setP_city(p_city);
        ec.setE_city(e_city);
        ec.setE_zip(e_zip);
        ec.setP_zip(p_zip);
        ec.setQuote_id(quote_id);
        String loginId = this.getUser().getLoginId();
        String orgId = this.getUser().getOrgId();
        ec.setOrg_code(orgId);
        if (!"admin".equals(loginId)) {
            ec.setDisti_groupId(this.getUser().getUserName());
        }
        return "toCreatePCEC";
    }

    public String toUpdateEndCustomer() {
        ec = new EndCustomer();
        ec.setId(Long.valueOf(id));
        ec = endCustomerService.getEndCustomerById(ec);
        ec.setEditGroupMark(updateState);

        String orgId = this.getUser().getOrgId();
        ec.setOrg_code(orgId);

        return "toCreateEndCustomer";
    }

    public String toAuditOpinion() {
        ec = new EndCustomer();
        ec.setId(Long.valueOf(id));
        ec.setState(Integer.valueOf(updateState));
        ec.setEnd_customer_id(end_customer_id);
        ec.setState(Integer.valueOf(updateState));
        ec.setEnd_customer_name(end_customer_name);
        ec.setEnd_customer_groupId(end_customer_groupId);
        ec.setCity(ec_city);
        return "toAuditOpinion";
    }

    public String toCheckEndCustomer() {
        ec = new EndCustomer();
        ec.setId(Long.valueOf(id));
        if (StringUtils.isNotEmpty(ec_alias_name) && StringUtils.isNotEmpty(ec_alias_name.trim())) {
            try {
                ec_alias_name = java.net.URLDecoder.decode(ec_alias_name, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (StringUtils.isNotEmpty(country) && StringUtils.isNotEmpty(country.trim())) {
            try {
                country = java.net.URLDecoder.decode(country, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        ec.setEc_alias_name(ec_alias_name);
        ec.setCountry(country);
        endCustomerService.checkEC(ec);
        return "toCheckEndCustomer";
    }

    /**
     * Customer主页面列表查询（无并集）
     * 
     * @return
     */
    @PermissionSearch
    @JsonResult(field = "ecList", include = { "id", "end_customer_id", "disti_customer_name", "disti_groupId",
            "disti_customer_id", "end_customer_groupId", "end_customer_name", "customer_type", "customer_property",
            "end_customer_groupName", "currency_code", "country", "province", "address", "city", "contact_name", "tel",
            "state", "audit_opinion", "remark", "create_time", "create_userId", "zip", "country_name", "modify_time",
            "modify_userId", "org_code", "country_org", "isCheck", "ecGroupState", "sync_state", "new_hierarchy",
            "tier" }, total = "total")
    public String searchEndCustomerList() {
        try {
            ec = new EndCustomer();
            ec.setStart(getStart());
            ec.setEnd(getEnd());
            ec.setSort("id");
            ec.setDir("desc");
            ec.setStates(states);
            ec.setEnd_customer_id(end_customer_id);
            if (StringUtils.isNotEmpty(end_customer_name) && StringUtils.isNotEmpty(end_customer_name.trim())) {
                try {
                    end_customer_name = java.net.URLDecoder.decode(end_customer_name, "UTF-8");
                    end_customer_name = end_customer_name.toUpperCase();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            if (StringUtils.isNotEmpty(end_customer_groupName)
                    && StringUtils.isNotEmpty(end_customer_groupName.trim())) {
                try {
                    end_customer_groupName = java.net.URLDecoder.decode(end_customer_groupName, "UTF-8");
                    end_customer_groupName = end_customer_groupName.toUpperCase();
                    ec.setEnd_customer_groupName(end_customer_groupName);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            if (StringUtils.isNotEmpty(disti_groupId) && StringUtils.isNotEmpty(disti_groupId.trim())) {
                disti_groupId = java.net.URLDecoder.decode(disti_groupId, "UTF-8");
                ec.setDisti_groupId(disti_groupId.toUpperCase());
            }
            if (StringUtils.isNotEmpty(disti_customer_name) && StringUtils.isNotEmpty(disti_customer_name.trim())) {
                try {
                    disti_customer_name = java.net.URLDecoder.decode(disti_customer_name, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            if (StringUtils.isNotEmpty(country) && StringUtils.isNotEmpty(country.trim())) {
                country = java.net.URLDecoder.decode(country, "UTF-8");
                ec.setCountry(country.toUpperCase());
            }
            if (StringUtils.isNotEmpty(province) && StringUtils.isNotEmpty(province.trim())) {
                province = java.net.URLDecoder.decode(province, "UTF-8");
                ec.setProvince(province.toUpperCase());
            }
            
            if (StringUtils.isNotEmpty(noTranBegin) && StringUtils.isNotEmpty(noTranBegin.trim())) {
            	ec.setNoTranBegin(noTranBegin);
            }
            
            if (StringUtils.isNotEmpty(noTranEnd) && StringUtils.isNotEmpty(noTranEnd.trim())) {
            	ec.setNoTranEnd(noTranEnd);
            }
            
            ec.setCustomer_type(customer_type);
            ec.setDisti_customer_name(disti_customer_name);
            ec.setEnd_customer_name(end_customer_name);
            // ec.setState(Integer.valueOf(state));
            String userId = this.getUser().getUserId();
            ec.setCreate_userId(userId);
            String[] roleIds = this.getUser().getRoleIds().split(",");
            boolean isAdmin = false;
            for (String roldId : roleIds) {
                if ("sys_admin".equals(roldId) || "CMD".equals(roldId)) {
                    isAdmin = true;
                }
            }
            if (!isAdmin) {
                String org = this.getUser().getOrgId();
                ec.setSalesOrg(org);
                ec.setDisti_customer_id(disti_customer_id);
            }

            total = endCustomerService.searchEndCustomerListCount(ec);
            // if(total > 0){
            ecList = endCustomerService.searchEndCustomerList(ec);
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON;
    }

    /**
     * combobox列表查询（含并集查基本状态+自己创建的Pending）
     * 
     * @return
     */
    @PermissionSearch
    @JsonResult(field = "ecList", include = { "id", "end_customer_id", "disti_customer_name", "disti_groupId",
            "disti_customer_id", "end_customer_groupId", "end_customer_name", "customer_type", "customer_property",
            "end_customer_groupName", "currency_code", "country", "province", "address", "city", "contact_name", "tel",
            "state", "audit_opinion", "remark", "create_time", "create_userId", "zip", "country_name", "modify_time",
            "modify_userId", "org_code", "country_org", "isCheck", "ecGroupState", "sync_state" }, total = "total")
    public String getEndCustomerList() {
        try {
            ec = new EndCustomer();
            ec.setStart(getStart());
            ec.setEnd(getEnd());
            ec.setSort("end_customer_name");
            ec.setDir("asc");
            ec.setStates(states);
            ec.setEnd_customer_id(end_customer_id);
            if (StringUtils.isNotEmpty(end_customer_name) && StringUtils.isNotEmpty(end_customer_name.trim())) {
                try {
                    end_customer_name = java.net.URLDecoder.decode(end_customer_name, "UTF-8");
                    end_customer_name = end_customer_name.toUpperCase();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            if (StringUtils.isNotEmpty(end_customer_groupName)
                    && StringUtils.isNotEmpty(end_customer_groupName.trim())) {
                try {
                    end_customer_groupName = java.net.URLDecoder.decode(end_customer_groupName, "UTF-8");
                    end_customer_groupName = end_customer_groupName.toUpperCase();
                    ec.setEnd_customer_groupName(end_customer_groupName);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            if (StringUtils.isNotEmpty(disti_groupId) && StringUtils.isNotEmpty(disti_groupId.trim())) {
                disti_groupId = java.net.URLDecoder.decode(disti_groupId, "UTF-8");
                ec.setDisti_groupId(disti_groupId.toUpperCase());
            }
            if (StringUtils.isNotEmpty(disti_customer_name) && StringUtils.isNotEmpty(disti_customer_name.trim())) {
                try {
                    disti_customer_name = java.net.URLDecoder.decode(disti_customer_name, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            if (StringUtils.isNotEmpty(country) && StringUtils.isNotEmpty(country.trim())) {
                country = java.net.URLDecoder.decode(country, "UTF-8");
                ec.setCountry(country.toUpperCase());
            }
            if (StringUtils.isNotEmpty(province) && StringUtils.isNotEmpty(province.trim())) {
                province = java.net.URLDecoder.decode(province, "UTF-8");
                ec.setProvince(province.toUpperCase());
            }
            
            if (StringUtils.isNotEmpty(province) && StringUtils.isNotEmpty(province.trim())) {
                province = java.net.URLDecoder.decode(province, "UTF-8");
                ec.setProvince(province.toUpperCase());
            }
            
         
            ec.setCustomer_type(customer_type);
            ec.setDisti_customer_name(disti_customer_name);
            ec.setEnd_customer_name(end_customer_name);
            String userId = this.getUser().getUserId();
            if (this.getUser().getOrgId() == null && "".equals(this.getUser().getOrgId())) {
                ec.setCreate_userId(userId);
            }
            if (this.getUser().getRoleIds() != null) {
                String[] roleIds = this.getUser().getRoleIds().split(",");
                boolean isAdmin = false;
                for (String roldId : roleIds) {
                    if ("sys_admin".equals(roldId) || "CMD".equals(roldId)) {
                        isAdmin = true;
                    }
                }
                if (!isAdmin) {
                    String org = this.getUser().getOrgId();
                    ec.setSalesOrg(org);
                    ec.setDisti_customer_id(disti_customer_id);
                }
            }
            total = endCustomerService.getEndCustomerListCount(ec);
            // if(total > 0){
            ecList = endCustomerService.getEndCustomerList(ec);
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON;
    }

    @PermissionSearch
    @JsonResult(field = "ecList", include = { "id", "end_customer_id", "disti_customer_name", "disti_groupId",
            "disti_customer_id", "end_customer_groupId", "end_customer_name", "customer_type", "customer_property",
            "end_customer_groupName", "currency_code", "country", "province", "address", "city", "contact_name", "tel",
            "state", "audit_opinion", "remark", "create_time", "create_userId", "zip", "country_name", "modify_time",
            "modify_userId", "org_code", "country_org", "isCheck", "ecGroupState", "sync_state" }, total = "total")
    public String getPCList() {
        try {
            ec = new EndCustomer();
            ec.setStart(getStart());
            ec.setEnd(getEnd());
            ec.setSort("end_customer_name");
            ec.setDir("asc");
            ec.setStates(states);
            ec.setEnd_customer_id(end_customer_id);
            if (StringUtils.isNotEmpty(pc_name) && StringUtils.isNotEmpty(pc_name.trim())) {
                try {
                    pc_name = java.net.URLDecoder.decode(pc_name, "UTF-8");
                    pc_name = pc_name.toUpperCase();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            if (StringUtils.isNotEmpty(end_customer_groupName)
                    && StringUtils.isNotEmpty(end_customer_groupName.trim())) {
                try {
                    end_customer_groupName = java.net.URLDecoder.decode(end_customer_groupName, "UTF-8");
                    end_customer_groupName = end_customer_groupName.toUpperCase();
                    ec.setEnd_customer_groupName(end_customer_groupName);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            if (StringUtils.isNotEmpty(disti_groupId) && StringUtils.isNotEmpty(disti_groupId.trim())) {
                disti_groupId = java.net.URLDecoder.decode(disti_groupId, "UTF-8");
                ec.setDisti_groupId(disti_groupId.toUpperCase());
            }
            if (StringUtils.isNotEmpty(disti_customer_name) && StringUtils.isNotEmpty(disti_customer_name.trim())) {
                try {
                    disti_customer_name = java.net.URLDecoder.decode(disti_customer_name, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            if (StringUtils.isNotEmpty(country) && StringUtils.isNotEmpty(country.trim())) {
                country = java.net.URLDecoder.decode(country, "UTF-8");
                ec.setCountry(country.toUpperCase());
            }
            if (StringUtils.isNotEmpty(province) && StringUtils.isNotEmpty(province.trim())) {
                province = java.net.URLDecoder.decode(province, "UTF-8");
                ec.setProvince(province.toUpperCase());
            }
            ec.setCustomer_type(customer_type);
            ec.setDisti_customer_name(disti_customer_name);
            ec.setEnd_customer_name(pc_name);
            String userId = this.getUser().getUserId();
            if (this.getUser().getOrgId() == null && "".equals(this.getUser().getOrgId())) {
                ec.setCreate_userId(userId);
            }

            if (this.getUser().getRoleIds() != null) {
                String[] roleIds = this.getUser().getRoleIds().split(",");
                boolean isAdmin = false;
                for (String roldId : roleIds) {
                    if ("sys_admin".equals(roldId) || "CMD".equals(roldId)) {
                        isAdmin = true;
                    }
                }
                if (!isAdmin) {
                    String org = this.getUser().getOrgId();
                    ec.setSalesOrg(org);
                    ec.setDisti_customer_id(disti_customer_id);
                }
            }

            ecList = endCustomerService.getEndCustomerList(ec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON;
    }

    @PermissionSearch
    @JsonResult(field = "ecList", include = { "aliasId", "ec_alias_name", "id", "end_customer_id",
            "disti_customer_name", "disti_groupId", "disti_customer_id", "end_customer_groupId", "end_customer_name",
            "customer_type", "customer_property", "currency_code", "country", "province", "address",
            "end_customer_groupName", "quote_id", "create_time", "state", "audit_opinion", "remark", "city",
            "alias_city" }, total = "total")
    public String pendingData() {
        try {
            ec = new EndCustomer();
            // ec.setId(Long.valueOf(id));
            ec.setEnd_customer_id(String.valueOf(end_customer_id));

            ecList = endCustomerService.pendingData(ec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON;
    }

    /**
     * 查询所有信息
     * 
     * @return
     */
    @PermissionSearch
    @JsonResult(field = "ecList", include = { "aliasId", "ec_alias_name", "id", "end_customer_id",
            "disti_customer_name", "disti_groupId", "disti_customer_id", "end_customer_groupId", "end_customer_name",
            "customer_type", "customer_property", "currency_code", "country", "province", "address",
            "end_customer_groupName", "contact_name", "tel", "state", "audit_opinion", "remark", "city",
            "alias_city" }, total = "total")
    public String checkEndCustomer() {
        try {
            ec = new EndCustomer();
            ec.setId(Long.valueOf(id));
            String userId = this.getUser().getUserId();
            ec.setCreate_userId(userId);
            if (StringUtils.isNotEmpty(ec_alias_name) && StringUtils.isNotEmpty(ec_alias_name.trim())) {
                try {
                    ec_alias_name = java.net.URLDecoder.decode(ec_alias_name, "UTF-8");
                    ec_alias_name = ec_alias_name.toUpperCase();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            if (StringUtils.isNotEmpty(country) && StringUtils.isNotEmpty(country.trim())) {
                try {
                    country = java.net.URLDecoder.decode(country, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            ec.setCountry(country);
            ec.setEc_alias_name(ec_alias_name);
            ecList = endCustomerService.checkEndCustomer(ec);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON;
    }

    /**
     * 添加信息
     * 
     * @return
     */
    public String createEndCustomer() {
        this.setSuccessMessage("");
        this.setFailMessage("");
        ec.setCreate_userId(this.getUser().getUserId());
        EndCustomer aa = new EndCustomer();
        aa.setEnd_customer_name(ec.getEnd_customer_name());
        // aa.setCountry(ec.getCountry());
        aa.setCity(ec.getCity());
        Boolean flag = checkEnd_customer_name(aa);
        if (flag) {
            ec.setEnd_customer_name(ec.getEnd_customer_name().toUpperCase());
            long result = endCustomerService.createEndCustomer(ec);
            if (result > 0) {
                String content = "Dear user<br>&nbsp;&nbsp; A new customer " + ec.getEnd_customer_name()
                        + " needs you to approve, please login in Portal to approve.";
                CustomerUser cusUser = new CustomerUser();
                String role = "CMD";// 申请商务经理审批
                cusUser.setRoleIds(role);
                List<CustomerUser> userList = quoteService.getAuditors(cusUser);
                if (userList != null && userList.size() != 0) {
                    for (CustomerUser user : userList) {
                        if (user.getEmail() != null) {
                            Message ms = new Message();
                            ms.setContent(content);
                            ms.setType("ECPC Create");
                            ms.setSendNumber(user.getEmail());
                            messageService.saveMessage(ms);
                            System.out.println("AddEmail(ECPC Create) : " + user.getEmail());
                        }
                    }
                }
                String ec_code = DataUtil.frontCompWithZore("E", result, 7);
                ec.setEnd_customer_id(ec_code);
                ec.setId(result);
                endCustomerService.setECCode(ec);
                ea = new ECAlias();
                ea.setEc_id(ec.getEnd_customer_id());
                ea.setEc_name(ec.getEnd_customer_name());
                ea.setEc_group(ec.getEnd_customer_groupId());
                ea.setEc_city(ec.getCity());
                ea.setEc_alias_name(ec.getEnd_customer_name());
                ea.setAlias_city(ec.getCity());
                endCustomerService.createECAlias(ea);
                this.setSuccessMessage("Success!");
            } else {
                this.setFailMessage("Failed!");
            }
        } else {
            this.setFailMessage("Name aready exist!");
        }
        return RESULT_MESSAGE;
    }

    /**
     * 为EDI Quote 添加PCEC信息
     * 
     * @return
     */
    public String createPCEC() {
        this.setSuccessMessage("");
        this.setFailMessage("");
        ec.setCreate_userId(this.getUser().getUserId());
        EndCustomer aa = new EndCustomer();
        aa.setEnd_customer_name(ec.getEnd_customer_name());
        aa.setCity(ec.getCity());
        q = new Quote();
        q.setQuote_id(ec.getQuote_id());
        q.setPcGroup_id(ec.getEnd_customer_groupId());
        q.setPurchaseCustomer_name(ec.getEnd_customer_name().toUpperCase());
        Boolean flag = checkEnd_customer_name(aa);
        if (flag) {
            ec.setEnd_customer_name(ec.getEnd_customer_name().toUpperCase());
            long result = endCustomerService.createEndCustomer(ec);
            if (result > 0) {
//				String content = "Dear user<br>&nbsp;&nbsp; A new customer "+ec.getEnd_customer_name()+" needs you to approve, please login in Portal to approve."; 
//				CustomerUser cusUser = new CustomerUser();
//				String role="CMD";//申请商务经理审批
//				cusUser.setRoleIds(role);
//				List<CustomerUser> userList = quoteService.getAuditors(cusUser);
//				if (userList!=null && userList.size() != 0) {
//					for (CustomerUser user : userList) {
//						if (user.getEmail()!=null) {
//							Message ms = new Message();
//							ms.setContent(content);
//							ms.setType("ECPC Create");
//							ms.setSendNumber(user.getEmail());
//							messageService.saveMessage(ms);
//							System.out.println("AddEmail(ECPC Create) : "+user.getEmail());
//						}
//					}
//				}
                String ec_code = DataUtil.frontCompWithZore("E", result, 7);
                ec.setEnd_customer_id(ec_code);
                ec.setId(result);
                int n = endCustomerService.setECCode(ec);
                ea = new ECAlias();
                ea.setEc_id(ec.getEnd_customer_id());
                ea.setEc_name(ec.getEnd_customer_name());
                ea.setEc_group(ec.getEnd_customer_groupId());
                ea.setEc_city(ec.getCity());
                ea.setEc_alias_name(ec.getEnd_customer_name());
                ea.setAlias_city(ec.getCity());
                long m = endCustomerService.createECAlias(ea);

                q.setPurchaseCustomer_id(ec_code);
                // q.setEndCustomer_id(ec_code);
                // 更新edi来源的quote中所有name为此的EC或PC的编码和group
                int p = quoteService.updatePCid(q);// 更新PCid/PCgroup
                int e = quoteService.updateECid(q);// 更新eCid/eCgroup
                /*
                 * if (p>0)
                 * {////////////////////////////////////////////////////////////////////////////
                 * ///////////////////////////////////////// List<CustomerUser> userList =
                 * quoteService.getQuoteAuditSale(q); if (userList.size() != 0) { for
                 * (CustomerUser user : userList) { String userNumber =
                 * "<br>&nbsp;&nbsp;Disti "+q.getCusGroup_id()+" submit a Quote,coded as "+q.
                 * getQuote_id()+", please login platform for approval"; String contents =
                 * "Hi "+user.getUserName() + userNumber + "<br>"; if (null != user.getEmail())
                 * { Message message = new Message(); message.setContent(contents);
                 * message.setType("Quote Create"); message.setSendNumber(user.getEmail());
                 * messageService.saveMessage(message);
                 * System.out.println("AddEmail((EDI)Quote Email) : "+user.getEmail()); }
                 * 
                 * } }
                 * /////////////////////////////////////////////////////////////////////////////
                 * //////////////////////////////// }
                 */

                /*
                 * if ("PC".equals(ec.getPcec())) { //更新PCid quoteService.updatePCid(q); }else
                 * if ("EC".equals(ec.getPcec())) { //更新ECid quoteService.updateECid(q); }
                 */
                this.setSuccessMessage("Success!");
            } else {
                this.setFailMessage("Failed!");
            }
        } else {

            this.setFailMessage("Name aready exist!");
        }
        return RESULT_MESSAGE;
    }

    /**
     * 验证该编码名是否被占用
     * 
     * @param a
     * @return
     */
    private Boolean checkEnd_customer_name(EndCustomer aa) {
        Boolean b = true;
        ECAlias eAlias = new ECAlias();
        eAlias.setEc_alias_name(aa.getEnd_customer_name().toUpperCase());
        // eAlias.setCountry(aa.getCountry());
        if (StringUtils.isNotEmpty(aa.getCity()) && StringUtils.isNotEmpty(aa.getCity().trim())) {
            eAlias.setAlias_city(aa.getCity().toUpperCase());
        }
        int n = endCustomerService.getCountByAliasName(eAlias);
        if (n >= 1) {
            b = false;
        }
        return b;
    }

    /**
     * 添加信息
     * 
     * @return
     */
    public String updateEndCustomer() {
        this.setSuccessMessage("");
        this.setFailMessage("");
        ec.setModify_userId(this.getUser().getUserId());
        EndCustomer aa = new EndCustomer();
        aa.setEnd_customer_name(ec.getEnd_customer_name());
        // aa.setCountry(ec.getCountry());
        aa.setCity(ec.getCity());
        // 改后的Name+City是否在Alias表中存在，当不存在可以进行修改，修改后数据做为当前EC的alias新增添加到Alias表中
        // 当存在时，分两种情况，当alias存在这个Master下面时，可修改。当不是这个Master时提示已经存在不可修改
        List<ECAlias> li = endCustomerService.searchECAlias(aa);
        if (li.size() > 0) {
            ECAlias eCAlias = li.get(0);
            // 当alias存在这个Master下面时，可修改
            if (eCAlias.getEc_id().equals(ec.getEnd_customer_id())) {
                this.setSuccessMessage("Success!");
                endCustomerService.updateEndCustomer(ec);
            } else { // 当不是这个Master时提示已经存在不可修改
                this.setFailMessage("Already exists and cannot be modified!");
            }
        } else { // 不存在 当前EC的alias新增添加到Alias表中
            int result = endCustomerService.updateEndCustomer(ec);
            if (result > 0) {
                this.setSuccessMessage("Success!");
                ea = new ECAlias();
                ea.setEc_id(ec.getEnd_customer_id());
                ea.setEc_name(ec.getEnd_customer_name().toUpperCase());
                ea.setEc_group(ec.getEnd_customer_groupId());
                ea.setEc_city(ec.getCity());
                ea.setEc_alias_name(ec.getEnd_customer_name().toUpperCase());
                ea.setAlias_city(ec.getCity()); // 20170620增加
                ea.setOldEc_id(ec.getEnd_customer_id());
                endCustomerService.createECAlias(ea);
            } else {
                this.setFailMessage("master name and city is already exists");
            }
        }

        return RESULT_MESSAGE;
    }

    /**
     * 审核信息
     * 
     * @return
     */
    public String auditEndCustomer() {
        this.setFailMessage("");
        this.setSuccessMessage("");
        ec.setModify_userId(this.getUser().getUserId());
        int i = endCustomerService.auditEndCustomer(ec);
        if (i > 0) {
//    		if (ec.getState()==1) {
//    			ea = new ECAlias();
//    			ea.setEc_id(ec.getEnd_customer_id());
//    			ea.setEc_name(ec.getEnd_customer_name());
//    			ea.setEc_group(ec.getEnd_customer_groupId());
//    			ea.setEc_city(ec.getCity());
//    			ea.setEc_alias_name(ec.getEnd_customer_name());
//    			ea.setAlias_city(ec.getCity());
//    			long m = endCustomerService.createECAlias(ea);
//    		}
            this.setSuccessMessage("Success!");
        } else {
            this.setFailMessage("Failed!");
        }
        return RESULT_MESSAGE;
    }

    public String deleteEndCustomer() {
        this.setFailMessage("");
        this.setSuccessMessage("");
        ec = new EndCustomer();
        ec.setId(Long.valueOf(id));
        ec.setState(Integer.valueOf(updateState));
        int i = endCustomerService.deleteEndCustomer(ec);
        if (i > 0) {
            this.setSuccessMessage("Success!");
        } else {
            this.setFailMessage("Failed!");
        }
        return RESULT_MESSAGE;
    }

//***************************************别名模块********************************************//
    /**
     * 跳转到查询页面
     */
    public String tosearchECAlias() {
        ea = new ECAlias();
        ea.setEc_id(ec_id);
        ea.setEc_name(ec_name);
        ea.setEc_group(ec_group);
        ea.setEc_city(ec_city);
        ea.setEc_groupName(ec_groupName);
        String userId = this.getUser().getUserId();
        Role r = new Role();
        r.setStart(0);
        r.setEnd(100);
        r.setEmp_code(userId);
        loginRole = "";
        roleList = roleService.getSelectedRole4StationList(r);
        for (Role rl : roleList) {
            loginRole += rl.getRoleId() + "*";
        }
        String orgString = this.getUser().getOrgId();
        if (orgString == null || "".equals(orgString)) {
            loginRole += "JXS";
            disti_groupId = this.getUser().getUserName();
        }
        return "tosearchECAlias";
    }

    public String searchAlias() {
        return "searchAlias";
    }

    /**
     * 跳转到查看页面
     * 
     * @return
     */
    public String toViewECAlias() {
        ea = new ECAlias();
        ea.setId(Long.valueOf(id));
        ea = endCustomerService.getECAliasById(ea);
        return "toViewECAlias";
    }

    /**
     * 跳转到新增页面
     * 
     * @return
     */
    public String toCreateECAlias() {
    	String customer_name = "";
    	EndCustomer ec1 = new EndCustomer();
    	ec1.setEnd_customer_id(ec_id);
    	EndCustomer nec = endCustomerService.getEndCustomerByCode(ec1);
    	if(nec!=null) {
    		customer_name = nec.getEnd_customer_name();
    	}
        ea = new ECAlias();
        ea.setEc_id(ec_id);
        ea.setEc_name(customer_name);
        ea.setEc_group(ec_group);
        ea.setEc_city(ec_city);
        return "toCreateECAlias";
    }

    // 跳转到修改页面
    public String toUpdateECAlias() {
        ea = new ECAlias();
        ea.setId(Long.valueOf(id));
        ea = endCustomerService.getECAliasById(ea);
        return "toCreateECAlias";
    }

    /**
     * 查询订单信息
     * 
     * @return
     */
    @PermissionSearch
    @JsonResult(field = "eaList", include = { "id", "ec_id", "ec_name", "ec_group", "ec_groupName", "ec_city",
            "ec_alias_name", "alias_city" }, total = "total")
    public String getECAliasListJson() {

        ea = new ECAlias();
        ea.setStart(getStart());
        ea.setEnd(getEnd());
        ea.setSort("aa.id");
        ea.setDir("asc");
        ea.setEc_id(ec_id);
        String orgString = this.getUser().getOrgId();
        if (orgString == null || "".equals(orgString)) {
            ea.setDisti_groupId(this.getUser().getUserName().toUpperCase());
        }
        if (StringUtils.isNotEmpty(ec_name) && StringUtils.isNotEmpty(ec_name.trim())) {
            try {
                ec_name = java.net.URLDecoder.decode(ec_name, "UTF-8");
                ea.setEc_name(ec_name.toUpperCase());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (StringUtils.isNotEmpty(ec_alias_name) && StringUtils.isNotEmpty(ec_alias_name.trim())) {
            try {
                ec_alias_name = java.net.URLDecoder.decode(ec_alias_name, "UTF-8");
                ea.setEc_alias_name(ec_alias_name.toUpperCase());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (StringUtils.isNotEmpty(alias_city) && StringUtils.isNotEmpty(alias_city.trim())) {
            try {
                alias_city = java.net.URLDecoder.decode(alias_city, "UTF-8");
                ea.setAlias_city(alias_city.toUpperCase());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        total = endCustomerService.getECAliasListCount(ea);
        if (total > 0) {
            eaList = endCustomerService.getECAliasList(ea);
        }
        return JSON;
    }

    /**
     * 添加信息
     * 
     * @return
     */
    public String addECAlias() {
        this.setSuccessMessage("");
        this.setFailMessage("");
        ECAlias aa = new ECAlias();
        aa.setEc_alias_name(ea.getEc_alias_name().toUpperCase());
        // aa.setEc_id(ea.getEc_id());
        if (StringUtils.isNotEmpty(ea.getAlias_city()) && StringUtils.isNotEmpty(ea.getAlias_city().trim())) {
            aa.setAlias_city(ea.getAlias_city().toUpperCase());
        }
        Boolean flag = checkECAliasId(aa);
        if (flag) {
            ea.setEc_alias_name(ea.getEc_alias_name().toUpperCase());
            long result = endCustomerService.createECAlias(ea);
            if (result > 0) {
                this.setSuccessMessage("Success!");
                if (ea.getQuote_id() != null) {
                    q = new Quote();
                    q.setQuote_id(ea.getQuote_id());
                    q.setPcGroup_id(ea.getEc_group());
                    q.setPurchaseCustomer_name(ea.getEc_alias_name().toUpperCase());
                    q.setPurchaseCustomer_id(ea.getEc_id());
                    // q.setEndCustomer_id(ec_code);
                    // 更新edi来源的quote中所有name为此的EC或PC的编码和group
                    int p = quoteService.updatePCid(q);// 更新PCid/PCgroup
                    int e = quoteService.updateECid(q);// 更新eCid/eCgroup
                }
            } else {
                this.setFailMessage("Failed!");
            }
        } else {
            this.setFailMessage("Name aready exist!");
        }
        return RESULT_MESSAGE;
    }

    /**
     * 修改信息
     */
    public String updateECAlias() {

        this.setSuccessMessage("");
        this.setFailMessage("");
        ECAlias aa = new ECAlias();
        aa.setEc_alias_name(ea.getEc_alias_name());
        aa.setEc_id(ea.getEc_id());
        if (aa.getEc_alias_name().equals(ea.getOldEc_alias_name())) {
            ea.setEc_alias_name(ea.getEc_alias_name().toUpperCase());
            long result = endCustomerService.updateECAlias(ea);
            if (result > 0) {
                this.setSuccessMessage("Success!");
            } else {
                this.setFailMessage("Failed!");
            }
        } else {
            Boolean flag = checkECAliasId(aa);
            if (flag) {
                ea.setEc_alias_name(ea.getEc_alias_name().toUpperCase());
                long result = endCustomerService.updateECAlias(ea);
                if (result > 0) {
                    this.setSuccessMessage("Success!");
                } else {
                    this.setFailMessage("Failed!");
                }
            } else {
                this.setFailMessage("Name aready exist!");
            }
        }

        return RESULT_MESSAGE;
    }

    /**
     * 删除信息
     * 
     * @return
     */
    public String delECAlias() {

        this.setFailMessage("");
        this.setSuccessMessage("");
        ea = new ECAlias();
        ea.setId(Long.valueOf(id));
        int i = endCustomerService.deleteECAliasById(ea);
        if (i > 0) {
            this.setSuccessMessage("Success!");
        } else {
            this.setFailMessage("Failed!");
        }
        return RESULT_MESSAGE;
    }

    /**
     * 验证该编码名是否被占用
     * 
     * @param g
     * @return
     */
    private Boolean checkECAliasId(ECAlias eca) {
        Boolean b = true;
        int n = endCustomerService.getCountByAliasName(eca);
        if (n >= 1) {
            b = false;
        }
        return b;
    }

    /**
     * 添加信息
     * 
     * @return
     */
    public String changeEC() {
        this.setSuccessMessage("");
        this.setFailMessage("");
        ec = new EndCustomer();
        ec.setState(9);
        ec.setId(ea.getId());
        ea.setModify_userId(this.getUser().getUserId());
        BooleanResult bool = endCustomerService.changeEC(ea, ec);
        if (bool.getResult()) {
            this.setSuccessMessage("Success !");
        } else {
            this.setFailMessage(bool.getCode());
        }

        return RESULT_MESSAGE;
    }

    // 新版导出excel
    public void downloadAlisa() throws Exception {
        try {
            this.setPage(1);
            this.setRows(100000);
            // 查询数据
            getECAliasListJson();
            String fileName = "Alisa";
            CommonUtil.setExcelResponseInfo(getServletRequest(), getServletResponse(), fileName);
            // excel表头
            String[] titles = { "Customer Name", "Customer City", "Alias Name", "Alias City" };
            // 表头对应list中Map对应属性名称
            String[] keys = { "ec_name", "ec_city", "ec_alias_name", "alias_city" };
            List<Map<String, String>> li = new ArrayList<Map<String, String>>();
            for (ECAlias ea : eaList) {
                li.add(CommonUtil.transBean2Map(ea));
            }
            CommonUtil.exportExcel(li, titles, keys, getServletResponse().getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 2018/11/06 Add Start
    /**
     * Change alias to EC
     * 
     * @return
     */
    public String changeAlias() {

        this.setFailMessage("");
        this.setSuccessMessage("");
        ea = new ECAlias();
        ea.setId(Long.valueOf(id));
        ea.setModify_userId(this.getUser().getUserId());
        BooleanResult result = endCustomerService.changeAlias(ea);
        if (result.getResult()) {
            this.setSuccessMessage("Success !");
        } else {
            this.setFailMessage(result.getCode());
        }
        return RESULT_MESSAGE;
    }
    // 2018/11/06 Add End

    @PermissionSearch
    @JsonResult(field = "cmsTbDictList", include = { "itemId", "itemValue", "itemName", "itemState", "remark" })
    public String getNewHierarchyList() {
        CmsTbDict m = new CmsTbDict();
        if (newHierarchy != null) {
            m.setItemValue(newHierarchy.toUpperCase());
        }
        cmsTbDictList = endCustomerService.getNewHierarchyList(m);
        return JSON;
    }
}
