package com.jingtong.platform.customer.action;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.allUser.service.IAllUserService;
import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.customer.pojo.CusCompany;
import com.jingtong.platform.customer.pojo.Customer;
import com.jingtong.platform.customer.pojo.CustomerUser;
import com.jingtong.platform.customer.pojo.Disti_branch;
import com.jingtong.platform.customer.service.ICustomerService;
import com.jingtong.platform.framework.annotations.Decode;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.framework.util.EncryptUtil;
import com.jingtong.platform.role.pojo.Role;
import com.jingtong.platform.station.pojo.Station;
import com.jingtong.platform.station.service.IStationService;

public class CustomerAction extends BaseAction{

    private static final long serialVersionUID = 1L;
    private ICustomerService customerService;
    private Customer c;
    private List<Customer> cList;
    private CusCompany cc;
    private List<CusCompany> ccList;
    private String id;
    private String customer_name;
    private String customer_code;
    private String customerSign;
    private String global_account;
    private int total;
    private String partnerType;
    private String states;
    private String org_code;
    private List<CustomerUser> cusUserList;
    private String search;
    
    @Decode
    private String orgId;
    private String bhxjFlag;
    private String loginId;
    @Decode
    private String userName;
    private String userId;
    private String phone;
    private String mobile;
    private List<CustomerUser> userList;
    private String ids;
    private List<Station> stationList;
    private String stationId;
    private String stationName;
    private IStationService stationService;
    private String loginId4Check;
    private CustomerUser cusUser;
    private String roleIds;
    private String email;
    private String password;
    private String content;
    private String emailaddress;
    private String emailpassword;
    private String smtpServer;
    private String from;
    private String displayName;
    private Properties env;
    @Decode
    private String orgName;
    private String flag;
    private String orgId4Update;
    private String loginId4AD;
    private IAllUserService allUserService;
    private AllUsers allUsers;
    private String multiDisti;
    private Disti_branch db;
    private List<Disti_branch> dbList;
    private String disti_name;
    private String disti_branch;
    private String branch_code;
    private String payer_to;
    private StringResult stringResult;
    private String roleId;
    private String roleType;
    private Role r;
    private List<Role> roleLists;
    
    public String toSearchCustomer(){
        return "toSearchCustomer";
    }
    
    public String toSearchCusUser(){
        return "toSearchCusUser";
    }
    
    public String toViewCustomer(){
        c=new Customer();
        c.setId(Long.valueOf(id));
        c= customerService.getCustomerById(c);
        return "toViewCustomer";
    }
    
    public String toCreateCustomerUser(){
        cusUser = new CustomerUser();
        cusUser.setCustomer_code(customer_code);
        return "toCreateCustomerUser";
    }
    
    public String toUpdateCustomerUserInfo(){
         allUsers = allUserService.getAllUsersByUserId(ids);
        return "toCreateCustomerUser";
    }
    public String toUpdateUserInfo(){
        cusUser = customerService.getUsersByUserId(ids);
        return "toUpdateUserInfo";
    }
    
    public String toSearchCustomerUser(){
        return "toSearchCustomerUser";
    }
    
    public String toSyncCustomer(){
        return "toSyncCustomer";
    }
    
    @PermissionSearch
    @JsonResult(field = "cList", include = {  "id","customer_code","customer_name","global_account","sales_org","sale_office","district",
              "country","ship_method","customer_type","design_customer","segment","currency_code","address",
              "contact_name","isAssigned","state","audit_opinion","remark","create_time","create_userId",
              "modify_time","modify_userId","org_code","tel","sales",
              "sales_orgName","sale_officeName","districtName","countryName"          
    },
                total = "total")
    public String getCustomerList(){
        try {            
            c = new Customer();
            c.setStart(getStart());
            c.setEnd(getEnd());
            c.setSort("customer_code desc ,customer_name ");
            c.setDir("asc");
             if (StringUtils.isNotEmpty(customer_name)
                    && StringUtils.isNotEmpty(customer_name.trim())) {
                try {
                    customer_name=java.net.URLDecoder.decode(customer_name,"UTF-8");
                    customer_name=customer_name.toUpperCase();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            if (StringUtils.isNotEmpty(search)&& StringUtils.isNotEmpty(search.trim())){        
                search=java.net.URLDecoder.decode(search,"UTF-8");
                c.setSearch(search.toUpperCase());
            }
            c.setCustomer_name(customer_name);
            c.setCustomer_code(customer_code);
            c.setGlobal_account(global_account);
            c.setOrg_code(org_code);
            c.setStates(states);
            c.setLoginId(loginId);
            String loginId =  this.getUser().getLoginId();
            if(!"admin".equals(loginId)){    
                if (multiDisti !=null && !"".equals(multiDisti)) {
                    c.setLoginId(loginId);
                    c.setCustomer_code(customer_code);
                }
                else {
                    cusUser = new CustomerUser();
                    cusUser.setLoginId(loginId);
                    cusUserList = customerService.getLoginCusUser(cusUser);    
                    if(cusUserList!=null){
                        String cusCode =cusUserList.get(0).getCustomer_code();
                        c.setCustomer_code(cusCode);
                    }                    
                }
            }
            else {                
                c.setCustomer_code(customer_code);
            }
            total = customerService.getCustomerListCount(c);
            if(total > 0){
                cList = customerService.getCustomerList(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON;
    }
    
     @PermissionSearch
    @JsonResult(field = "ccList", include = { "id","customer_code","partnerType","partnerName","address","nameAddress","contact","partnerId"})
    public String getCusCompanyList(){
        
         cc =new CusCompany(); 
        String loginId =  this.getUser().getLoginId();
        if (customer_code!=null && !"".equals(customer_code)) {
            cc.setCustomer_code(customer_code);
        }
        else {
            cusUser=new CustomerUser();
            cusUser.setLoginId(loginId);
            cusUserList = customerService.getLoginCusUser(cusUser);    
            if (cusUserList != null) {
                cc.setCustomer_code(cusUserList.get(0).getCustomer_code());
            }            
        }
        cc.setPartnerType(partnerType);
        ccList=customerService.getCusCompanyList(cc);
        return JSON;
    }

     @JsonResult(field = "userList", include = { "userId", "loginId",
            "userName", "userState", "orgId", "orgName", "mobile", "email",
            "address", "phone", "sex","modify_date","modify_user","create_date","create_user" }, total = "total")
    @PermissionSearch
    public String getUserInfoList() {
        this.setSuccessMessage("");
        this.setFailMessage("");
        userList = null;
        CustomerUser cusUser = new CustomerUser();
        cusUser.setStart(getStart());
        cusUser.setEnd(getEnd());
        if (StringUtils.isNotEmpty(email)) {
            cusUser.setEmail(email.toUpperCase());
        }
        if (StringUtils.isNotEmpty(loginId)) {
            cusUser.setLoginId(loginId.toUpperCase());
        }
         if (StringUtils.isNotEmpty(customer_name)
                && StringUtils.isNotEmpty(customer_name.trim())) {
            try {
                customer_name=java.net.URLDecoder.decode(customer_name,"UTF-8");
                customer_name=customer_name.toUpperCase();
                cusUser.setCustomer_name(customer_name);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
         if (StringUtils.isNotEmpty(userName)
                && StringUtils.isNotEmpty(userName.trim())) {
            try {
                userName=java.net.URLDecoder.decode(userName,"UTF-8");
                userName=userName.toUpperCase();
                cusUser.setUserName(userName);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (StringUtils.isNotEmpty(customer_code)) {
            cusUser.setCustomer_code(customer_code);
        }
        if (StringUtils.isNotEmpty(customerSign)) {
            cusUser.setCustomerSign(customerSign);
        }
        
        total = customerService.searchCustomerUserCount(cusUser);
        if (total != 0) {
            userList = customerService.searchCustomerUser(cusUser);
        } 
        return JSON;
    }

     @JsonResult(field = "userList", include = { "userId", "loginId",
            "userName", "userState", "orgId", "orgName", "mobile", "email",
            "address", "phone", "sex","createDate" }, total = "total")
    @PermissionSearch
    public String getCustomerUserInfoList() {
        this.setSuccessMessage("");
        this.setFailMessage("");
        userList = null;
        CustomerUser cusUser = new CustomerUser();
        cusUser.setStart(getStart());
        cusUser.setEnd(getEnd());
         if (StringUtils.isNotEmpty(customer_code)) {
            cusUser.setCustomer_code(customer_code);
        }
         
        total = customerService.searchCustomerUserCount(cusUser);
        if (total != 0) {
            userList = customerService.searchCustomerUser(cusUser);
        } 
        return JSON;
    }
    
    public String creatUser() {
        this.setSuccessMessage("");
        this.setFailMessage("");
        String message = allUserService.getAllUserByLoginId(cusUser
                .getLoginId());
        if ("exist".equals(message)) {
            
            this.setFailMessage("User name " + cusUser.getLoginId()
                    + " aleady existed");
            return RESULT_MESSAGE;
        }
        try {
            cusUser.setUserState("Y");
            cusUser.setExpressly(cusUser.getPassWd());
            cusUser.setCreate_user(this.getUser().getUserId());
            cusUser.setPassWd(EncryptUtil.md5Encry(cusUser.getPassWd()));
            BooleanResult booleanResult = customerService.createUser(cusUser);
                if (!booleanResult.getResult()) {
                    this.setFailMessage(booleanResult.getCode());
                } else {
                    this.setSuccessMessage("Success!");
                    CustomerUser cu = new CustomerUser();
                    cu.setModify_user(this.getUser().getUserId());
                    cu.setLoginId(cusUser.getLoginId());
                    customerService.setCreateUserForUser(cu);
                    if(customer_code!=null&&(!("".equals(customer_code)))){
                        CustomerUser cusUser = new CustomerUser();
                        cusUser.setCustomer_code(customer_code);
                        cusUser.setLoginId(cusUser.getLoginId());
                        customerService.setCusomerSignForUser(cusUser);
                    }
                }
        } catch (Exception e) {
            this.setFailMessage("Failed!");
        }
        return RESULT_MESSAGE;
    }

    public String updateCustomerUser() {
        this.setSuccessMessage("");
        this.setFailMessage("");
        cusUser.setUserId(userId);        
        cusUser.setModify_user(this.getUser().getUserId());
        BooleanResult booleanResult = customerService.updateCustomerUser(cusUser);
        if (!booleanResult.getResult()) {
            this.setFailMessage("Error!");
        }else {
            this.setSuccessMessage("Success!");
            CustomerUser cu = new CustomerUser();
            cu.setModify_user(this.getUser().getUserId());
            cu.setLoginId(cusUser.getLoginId());
            customerService.setModifyUserForUser(cu);
        }
        return RESULT_MESSAGE;
    }
    
    public String toSearchBindUser(){
        return "toSearchBindUser";
    }
    
    public String toBindUser(){
        return "toBindUser";
    }
    
    @PermissionSearch
    @JsonResult(field = "cusUserList", include = {  "id","customer_code","customer_name","loginId","userId","userName"          
    })
    public String getDistiListOfThisUser(){
        try {            
            cusUser = new CustomerUser();
            cusUser.setUserId(userId);
            cusUserList = customerService.getDistiListOfThisUser(cusUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON;
    }
    
    public String bindDistiToThisUser() {
        this.setSuccessMessage("");
        this.setFailMessage("");
        CustomerUser cu = new CustomerUser(); 
        cu.setUserId(cusUser.getUserId());
        cu.setCustomer_code(cusUser.getCustomer_code());
        Boolean flag = checkDoubleBind(cu);
        if (flag) {
            long result = customerService.bindDistiToThisUser(cusUser);
            if (result > 0) {
                this.setSuccessMessage("Success!");
            } else {
                this.setFailMessage("Failed!");
            }
        } else {
            this.setFailMessage("Name aready exist!");
        }
        return RESULT_MESSAGE;
    }
    
    private Boolean checkDoubleBind(CustomerUser cu) {
        Boolean b = true;
        int n = customerService.getCountByCusUser(cu);
        if (n >= 1) {
            b = false;
        }
        return b;
    }

    public String unBind() {
        this.setSuccessMessage("");
        this.setFailMessage("");
        cusUser = new CustomerUser();
        cusUser.setId(Long.valueOf(id));
        int n = customerService.unBind(cusUser);
        if (n>0) {
            this.setSuccessMessage("Success!");
        }else {
            this.setFailMessage("Failed!");            
        }
        return RESULT_MESSAGE;
    }

     @PermissionSearch
    @JsonResult(field = "dbList", include = { "id","disti_name","disti_branch","branch_code","pricing_region","payer_to","currency","creater","create_date","sold_to","ship_to","bill_to"}, total = "total")
    public String getDistiBranchList(){
        
         db =new Disti_branch(); 
        if (StringUtils.isNotEmpty(disti_name)
                && StringUtils.isNotEmpty(disti_name.trim())) {
            try {
                disti_name=java.net.URLDecoder.decode(disti_name,"UTF-8");
                disti_name=disti_name.toUpperCase();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        db.setDisti_name(disti_name);
        db.setStart(getStart());
        db.setEnd(getEnd());
        db.setSort("disti_branch");
        if (StringUtils.isNotEmpty(disti_branch)
                && StringUtils.isNotEmpty(disti_branch.trim())) {
            try {
                disti_branch=java.net.URLDecoder.decode(disti_branch,"UTF-8");
                disti_branch=disti_branch.toUpperCase();
                db.setDisti_branch(disti_branch);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (StringUtils.isNotEmpty(payer_to)&& StringUtils.isNotEmpty(payer_to.trim())) {
                db.setPayer_to(payer_to);
        }
        total = customerService.getDistiBranchCount(db);
        if (total>0) {            
            dbList=customerService.getDistiBranchList(db);
        }
        return JSON;
    }
    
     @PermissionSearch
    @JsonResult(field = "dbList", include = { "disti_name","payer_to"}, total = "total")
    public String getDistiNameList(){        
         db =new Disti_branch(); 
        if (StringUtils.isNotEmpty(disti_name)
                && StringUtils.isNotEmpty(disti_name.trim())) {
            try {
                disti_name=java.net.URLDecoder.decode(disti_name,"UTF-8");
                disti_name=disti_name.toUpperCase();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        db.setDisti_name(disti_name);
        db.setStart(getStart());
        db.setEnd(getEnd());
        db.setSort("disti_name");        
        total = customerService.getDistiNameCount(db);
        if (total>0) {            
            dbList=customerService.getDistiNameList(db);
        }
        return JSON;
    }
    
    public String tosearchDistiBranch() {
        return "tosearchDistiBranch";
    }

    public String toViewDistiBranch() {
        db = new Disti_branch();
        db.setId(Long.valueOf(id));
        db = customerService.getDistiBranchById(db);
        return "toViewDistiBranch";
    }

    public String toCreateDistiBranch() {
        db = new Disti_branch();
        return "toCreateDistiBranch";
    }

    public String toUpdateDistiBranch() {
        db = new Disti_branch();
        db.setId(Long.valueOf(id));
        db = customerService.getDistiBranchById(db);
        return "toUpdateDistiBranch";
    }

    public String createDistiBranch() {
        this.setSuccessMessage("");
        this.setFailMessage("");
        Disti_branch aa = new Disti_branch();
        db.setDisti_name(db.getDisti_name().toUpperCase());
        db.setDisti_branch(db.getDisti_branch().toUpperCase());
        aa.setDisti_branch(db.getDisti_branch().toUpperCase());
        db.setCreater(this.getUser().getUserId());
        db.setSold_to(db.getSold_to());
        db.setShip_to(db.getShip_to());
        db.setBill_to(db.getBill_to());
        Boolean flag = checkDistiBranch(aa);
        if (flag) {
            long result = customerService.createDistiBranch(db);
            if (result > 0) {
                this.setSuccessMessage("Success!");
            } else {
                this.setFailMessage("Failed!");
            }
        } else {
            this.setFailMessage("DistiBranch aready exist!");
        }
        return RESULT_MESSAGE;
    }

    public String updateDistiBranch() {

        this.setSuccessMessage("");
        this.setFailMessage("");
        db.setDisti_name(db.getDisti_name().toUpperCase());
        db.setDisti_branch(db.getDisti_branch().toUpperCase());
        Disti_branch aa = new Disti_branch();
        aa.setDisti_branch(db.getDisti_branch());    
        db.setSold_to(db.getSold_to());
        db.setShip_to(db.getShip_to());
        db.setBill_to(db.getBill_to());
        if (aa.getDisti_branch().equals(db.getOldDisti_branch().toUpperCase())) {
            long result = customerService.updateDistiBranch(db);
            if (result > 0) {
                this.setSuccessMessage("Success!");
            } else {
                this.setFailMessage("Failed!");
            }
        } else {
            Boolean flag = checkDistiBranch(aa);
            if (flag) {
                long result = customerService.updateDistiBranch(db);
                if (result > 0) {
                    this.setSuccessMessage("Success!");
                } else {
                    this.setFailMessage("Failed!");
                }
            } else {
                this.setFailMessage("DistiBranch aready exist!");
            }
        }

        return RESULT_MESSAGE;
    }

    public String deleteDistiBranch() {

        this.setFailMessage("");
        this.setSuccessMessage("");
        db = new Disti_branch();
        db.setId(Long.valueOf(id));
        int i = customerService.deleteDistiBranch(db);
        if (i > 0) {
            this.setSuccessMessage("Success!");
        } else {
            this.setFailMessage("Failed!");
        }
        return RESULT_MESSAGE;
    }

    private Boolean checkDistiBranch(Disti_branch d) {
        Boolean b = true;
        int n = customerService.getDBCountByPayer_to(d);
        if (n >= 1) {
            b = false;
        }
        return b;

    }

    @PermissionSearch
    @JsonResult(field = "stringResult", include = { "result", "code"})
    public String isLoginIdExist() {
        this.setSuccessMessage("");
        this.setFailMessage("");
        stringResult = new StringResult();
        String message = "";
        if (StringUtils.isNotEmpty(loginId4Check)) {
            message = customerService.getAllUserByLoginId(loginId4Check.toUpperCase());
        } 
        stringResult.setCode(message);
        
        if ("exist".equals(message)) {
            stringResult.setResult(loginId4Check + " aleady existed!");
        } else if ("unexist".equals(message)) {
            stringResult.setResult("This is a valid LoginId");
        }
        return JSON;
    }

    public Disti_branch getDb() {
        return db;
    }

    public void setDb(Disti_branch db) {
        this.db = db;
    }

    public List<Disti_branch> getDbList() {
        return dbList;
    }

    public void setDbList(List<Disti_branch> dbList) {
        this.dbList = dbList;
    }

    public String getDisti_name() {
        return disti_name;
    }

    public void setDisti_name(String disti_name) {
        this.disti_name = disti_name;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getBhxjFlag() {
        return bhxjFlag;
    }

    public void setBhxjFlag(String bhxjFlag) {
        this.bhxjFlag = bhxjFlag;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<CustomerUser> getUserList() {
        return userList;
    }

    public void setUserList(List<CustomerUser> userList) {
        this.userList = userList;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public List<Station> getStationList() {
        return stationList;
    }

    public void setStationList(List<Station> stationList) {
        this.stationList = stationList;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public IStationService getStationService() {
        return stationService;
    }

    public void setStationService(IStationService stationService) {
        this.stationService = stationService;
    }

    public String getLoginId4Check() {
        return loginId4Check;
    }

    public void setLoginId4Check(String loginId4Check) {
        this.loginId4Check = loginId4Check;
    }

    public CustomerUser getCustomerUser() {
        return cusUser;
    }

    public void setCustomerUser(CustomerUser cusUser) {
        this.cusUser = cusUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    public String getLoginId4AD() {
        return loginId4AD;
    }

    public void setLoginId4AD(String loginId4AD) {
        this.loginId4AD = loginId4AD;
    }

    public String getOrgId4Update() {
        return orgId4Update;
    }

    public void setOrgId4Update(String orgId4Update) {
        this.orgId4Update = orgId4Update;
    }
    public ICustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(ICustomerService customerService) {
        this.customerService = customerService;
    }

    public Customer getC() {
        return c;
    }

    public void setC(Customer c) {
        this.c = c;
    }

    public List<Customer> getcList() {
        return cList;
    }

    public void setcList(List<Customer> cList) {
        this.cList = cList;
    }

    public CusCompany getCc() {
        return cc;
    }

    public void setCc(CusCompany cc) {
        this.cc = cc;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public List<CusCompany> getCcList() {
        return ccList;
    }

    public void setCcList(List<CusCompany> ccList) {
        this.ccList = ccList;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_code() {
        return customer_code;
    }

    public void setCustomer_code(String customer_code) {
        this.customer_code = customer_code;
    }

    public String getGlobal_account() {
        return global_account;
    }

    public void setGlobal_account(String global_account) {
        this.global_account = global_account;
    }
    public String getPartnerType() {
        return partnerType;
    }

    public void setPartnerType(String partnerType) {
        this.partnerType = partnerType;
    }

    public CustomerUser getCusUser() {
        return cusUser;
    }

    public void setCusUser(CustomerUser cusUser) {
        this.cusUser = cusUser;
    }

    public Properties getEnv() {
        return env;
    }

    public void setEnv(Properties env) {
        this.env = env;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<CustomerUser> getCusUserList() {
        return cusUserList;
    }

    public void setCusUserList(List<CustomerUser> cusUserList) {
        this.cusUserList = cusUserList;
    }

    public IAllUserService getAllUserService() {
        return allUserService;
    }

    public void setAllUserService(IAllUserService allUserService) {
        this.allUserService = allUserService;
    }

    public AllUsers getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(AllUsers allUsers) {
        this.allUsers = allUsers;
    }

    public String getOrg_code() {
        return org_code;
    }

    public void setOrg_code(String org_code) {
        this.org_code = org_code;
    }

    public String getSearch() {
        return search;
    }

    public String getMultiDisti() {
        return multiDisti;
    }

    public void setMultiDisti(String multiDisti) {
        this.multiDisti = multiDisti;
    }

    public void setSearch(String search) {
        this.search = search;
    }    
    public String getDisti_branch() {
        return disti_branch;
    }

    public String getCustomerSign() {
        return customerSign;
    }

    public void setCustomerSign(String customerSign) {
        this.customerSign = customerSign;
    }

    public void setDisti_branch(String disti_branch) {
        this.disti_branch = disti_branch;
    }

    public String getBranch_code() {
        return branch_code;
    }

    public void setBranch_code(String branch_code) {
        this.branch_code = branch_code;
    }

    public String getPayer_to() {
        return payer_to;
    }

    public void setPayer_to(String payer_to) {
        this.payer_to = payer_to;
    }

    public StringResult getStringResult() {
        return stringResult;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public Role getR() {
        return r;
    }

    public void setR(Role r) {
        this.r = r;
    }

    public List<Role> getRoleLists() {
        return roleLists;
    }

    public void setRoleLists(List<Role> roleLists) {
        this.roleLists = roleLists;
    }

    public void setStringResult(StringResult stringResult) {
        this.stringResult = stringResult;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    @PermissionSearch
    @JsonResult(field = "roleLists", include = { "id", "roleId", "roleName",
            "descn" }, total = "total")
    public String getRoleListJosn() {
        Role r = new Role();
        r = getSearchInfo(r);
        r.setStart(getStart());
        r.setEnd(getEnd());
        if (StringUtils.isNotEmpty(roleId)
                && StringUtils.isNotEmpty(roleId.trim())) {
            try {
                roleId = new String(this.getRoleId().getBytes("ISO8859-1"),
                        "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            r.setRoleId(roleId.trim());
            r.setRoleName(roleId.trim());
        }
        if (StringUtils.isNotEmpty(roleType)
                && StringUtils.isNotEmpty(roleType.trim())) {
            try {
                roleType = new String(this.getRoleType().getBytes("ISO8859-1"),
                        "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            r.setRoleType(roleType);
        }
        total = customerService.getRole1Count(r);
        if (total != 0) {
            roleLists = customerService.getRole1List(r);
        } else {
            roleLists = null;
        }

        return JSON;
    }
}
