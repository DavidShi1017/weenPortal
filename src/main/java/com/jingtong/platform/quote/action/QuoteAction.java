package com.jingtong.platform.quote.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.allUser.service.IAllUserService;
import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.common.CommonUtil;
import com.jingtong.platform.common.Escape;
import com.jingtong.platform.country.pojo.SaleCountry;
import com.jingtong.platform.customer.pojo.CustomerUser;
import com.jingtong.platform.customer.pojo.Disti_branch;
import com.jingtong.platform.customer.service.ICustomerService;
import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.dict.service.IDictService;
import com.jingtong.platform.endCustomer.pojo.EndCustomer;
import com.jingtong.platform.endCustomer.service.IEndCustomerService;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.framework.util.DateUtil;
import com.jingtong.platform.framework.util.ExcelUtil;
import com.jingtong.platform.framework.util.JsonUtil;
import com.jingtong.platform.framework.util.StockUtil;
import com.jingtong.platform.framework.util.XssUtils;
import com.jingtong.platform.message.pojo.Message;
import com.jingtong.platform.message.service.IMessageService;
import com.jingtong.platform.pos.pojo.Pos;
import com.jingtong.platform.pos.service.IPosService;
import com.jingtong.platform.product.pojo.Product;
import com.jingtong.platform.product.service.IProductService;
import com.jingtong.platform.quote.pojo.Quote;
import com.jingtong.platform.quote.pojo.QuoteDetail;
import com.jingtong.platform.quote.service.IQuoteService;
import com.jingtong.platform.role.pojo.Role;
import com.jingtong.platform.role.service.IRoleService;

import jxl.CellType;
import jxl.DateCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class QuoteAction extends BaseAction {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private IQuoteService quoteService;
    private Quote q;
    private List<Quote> qList;
    private QuoteDetail qd;
    private Quote q1;
    private List<Quote> qList1;
    private QuoteDetail qd1;
    private List<QuoteDetail> qdList1;
    private List<QuoteDetail> qdList;
    private String quoteDetailJson;
    private String id;
    private String delQuoteDetail;
    private String quote_id;
    private String qdState;
    private String project_name;
    private String price_region;
    private String customer_id;
    private String cusGroup_id;
    private String pcGroup_id;
    private String ecGroup_id;
    private String disti_branch;
    private String endCustomer_id;
    private String purchaseCustomer_id;
    private String endCustomer_name;
    private String purchaseCustomer_name;
    private String state;
    private String states;
    private String isAgrees;
    private int total;
    private String ids;
    private CustomerUser cusUser;
    private List<CustomerUser> cusUserList;
    private ICustomerService customerService;
    private List<Role> roleList;
    private IRoleService roleService;
    private String loginRole;
    private String material_id;
    private String material_name;
    private String approver;
    private String start_dateStr;
    private String end_dateStr;
    private String uploadFile;
    private Product p;
    private List<Product> pList;
    private IProductService productService;
    private String path;
    private String file_name;
    private String file_path;
    private IPosService posService;
    private Pos pos;
    private IAllUserService allUserService;
    private String smtpServer;// =env.getProperty("allUser.smtpServer");
    private String from;// =env.getProperty("allUser.from");
    private String displayName;// =env.getProperty("allUser.displayName");
    private String emailaddress;// =env.getProperty("allUser.emailaddress");
    private String emailpassword;// =env.getProperty("allUser.emailpassword");
    private String remark;
    private String cus_remark;
    private String rowIndex;
    private String forWho;
    private String useFor;
    private CmsTbDict cmsTbDict;
    private IDictService dictService;
    private List<CustomerUser> userList;
    private String currency_code;
    private String office_id;
    private String myself;
    private String create_userName;
    private EndCustomer ec;
    private List<EndCustomer> ecList;
    private IEndCustomerService endCustomerService;
    private String noPCEC;
    private IMessageService messageService;
    private String debit_num;
    private String fromWho;
    private String pending_approver;
    private String loginid;
    private String pc_region;
    private String suggestCosts;
    private String suggestResales;
    private String cusProfitsPercents;
    private String profitsPercents;
    private String amounts;
    private String roleControl;
    private String webFrom;

    public String getPc_region() {
        return pc_region;
    }

    public void setPc_region(String pc_region) {
        this.pc_region = pc_region;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getCusProfitsPercents() {
        return cusProfitsPercents;
    }

    public void setCusProfitsPercents(String cusProfitsPercents) {
        this.cusProfitsPercents = cusProfitsPercents;
    }

    public String getProfitsPercents() {
        return profitsPercents;
    }

    public void setProfitsPercents(String profitsPercents) {
        this.profitsPercents = profitsPercents;
    }

    public String getPending_approver() {
        return pending_approver;
    }

    public void setPending_approver(String pending_approver) {
        this.pending_approver = pending_approver;
    }

    public String toSearchQuote() {
        q = new Quote();
        String userId = this.getUser().getUserId();
        String orgString = this.getUser().getOrgId();
        if (orgString == null || "".equals(orgString)) {
            q.setCusGroup_id(this.getUser().getUserName());
            forWho = "Disti";
        }
        Role r = new Role();
        r.setStart(0);
        r.setEnd(100);
        r.setEmp_code(userId);
        roleList = roleService.getSelectedRole4StationList(r);
        loginRole = "";
        for (Role rl : roleList) {
            loginRole += rl.getRoleId() + "*";
            if ("JXS".equals(rl.getRoleId()) || orgString == null || "".equals(orgString)) {
                q.setRoleId("JXS");
            } else if ("HK10_H_Sale_Mgmt".equals(rl.getRoleId())) {
                q.setRoleId("HK10_H_Sale_Mgmt");
            }
            //
        }
        if (orgString == null || "".equals(orgString)) {
            loginRole += "JXS";
        }
        return "toSearchQuote";
    }

    public String toRemarkTxt() {
        if (StringUtils.isNotEmpty(remark) && StringUtils.isNotEmpty(remark.trim())) {
            remark = remark.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
            try {
                remark = java.net.URLDecoder.decode(remark, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        q = new Quote();
        String userString = this.getUser().getLoginId();
        int n = userString.indexOf("@");
        if (n > -1) {

            q.setCreate_userId(userString.substring(0, userString.indexOf("@")));
        } else {
            q.setCreate_userId(userString);
        }
        return "toRemarkTxt";
    }

    public String toViewQuote() {
        q = new Quote();
        q.setId(Long.valueOf(id));
        q = quoteService.getQuoteById(q);
        String str = q.getFile_path();
        if (str != null && str.lastIndexOf("webapp") != -1) {
            str = str.substring(str.lastIndexOf("webapp") - 1, str.length());
            q.setFile_path(str);
        }
        return "toViewQuote";
    }

    public String toSalerViewQuote() {
        q = new Quote();
        q.setId(Long.valueOf(id));
        q = quoteService.getQuoteById(q);
        String str = q.getFile_path();
        if (str != null && str.lastIndexOf("webapp") != -1) {
            str = str.substring(str.lastIndexOf("webapp") - 1, str.length());
            System.out.println(str);
            q.setFile_path(str);
        }
        q.setApprover(approver);

        // 获得国家
        EndCustomer ec = new EndCustomer();
        ec.setEnd_customer_id(q.getEndCustomer_id());
        EndCustomer ec1 = endCustomerService.getEndCustomerByCode(ec);

        ec.setEnd_customer_id(q.getPurchaseCustomer_id());
        EndCustomer ec2 = endCustomerService.getEndCustomerByCode(ec);
        if (ec1 != null)
            q.setEdi_ec_country(ec1.getCountry()); // EC国家

        if (ec2 != null)
            q.setEdi_pc_country(ec2.getCountry()); // PC国家

        if (q.getEc_city() != null) {
            q.setEndCustomer_name(q.getEndCustomer_name() + "-" + q.getEc_city());
        }
        if (q.getPc_city() != null) {
            q.setPurchaseCustomer_name(q.getPurchaseCustomer_name() + "-" + q.getPc_city());
        }
        String userString = this.getUser().getLoginId();
        int n = userString.indexOf("@");
        if (n > -1) {

            q.setSysnc_exception(userString.substring(0, userString.indexOf("@")));
        } else {
            q.setSysnc_exception(userString);
        }
        q.setAuditorId(String.valueOf(this.getUser().getUserId()));
        return "toSalerViewQuote";
    }

    public String toCreateQuote() {
        q = new Quote();
        q.setCusGroup_id(this.getUser().getUserName());
        q.setCreate_timeStr(DateUtil.getNowDateStr());
        String userString = this.getUser().getLoginId();
        int n = userString.indexOf("@");
        if (n > -1) {
            q.setCreate_userId(userString.substring(0, userString.indexOf("@")));
        } else {
            q.setCreate_userId(userString);
        }
        return "toCreateQuote";
    }

    public String toUpdateQuote() {
        q = new Quote();
        q.setId(Long.valueOf(id));
        q = quoteService.getQuoteById(q);
        return "toCreateQuote";
    }

    public String toSearchQuotePrice() {
        return "toSearchQuotePrice";
    }

    public String toQuoteCenter() {
        return "toQuoteCenter";
    }

    public String toBusinessQuote() {
        return "toBusinessQuote";
    }

    public String toSelectUserForQuoteForward() {
        return "toSelectUserForQuoteForward";
    }

    public String updateUserForQuoteForward() {
        this.setSuccessMessage("");
        this.setFailMessage("");
        try {
            String detail_id[] = ids.split(",");
            String detail_suggestCost[] = suggestCosts.split(",");
            String detail_suggestResale[] = suggestResales.split(",");
            String detail_cus_profits_percent[] = cusProfitsPercents.split(",");
            String detail_profits_percent[] = profitsPercents.split(",");
            String detail_amount[] = amounts.split(",");

            int ii = 0;
            Double suggest_cost = 0.0;
            Double suggest_resale = 0.0;
            Double amount = 0.0;

            for (int i = 0; i < detail_id.length; i++) {
                String quotedetailid = detail_id[i];
                QuoteDetail qd = new QuoteDetail();
                qd.setForward_id(Long.valueOf(loginid));
                qd.setId(Long.valueOf(quotedetailid));
                qd.setLatest_userId(this.getUser().getUserId());

                try {
                    suggest_cost = Double.valueOf(detail_suggestCost[i]);
                    qd.setSuggest_cost(suggest_cost);
                } catch (Exception e) {
                    qd.setSuggest_cost(0.0000);
                }

                try {
                    suggest_resale = Double.valueOf(detail_suggestResale[i]);
                    qd.setSuggest_resale(suggest_resale);
                } catch (Exception e) {
                    qd.setSuggest_resale(0.0000);
                }

                try {
                    qd.setCus_profits_percent(java.net.URLDecoder.decode(detail_cus_profits_percent[i], "UTF-8"));
                } catch (Exception e) {
                }

                try {
                    qd.setProfits_percent(java.net.URLDecoder.decode(detail_profits_percent[i], "UTF-8"));
                } catch (Exception e) {
                }

                try {
                    amount = Double.valueOf(detail_amount[i]);
                    qd.setAmount(amount);
                } catch (Exception e) {
                    qd.setAmount(0.0000);
                }
                ii = quoteService.updateUserForQuoteForward(qd);
            }
            if (ii == 1) {
                Quote quote = new Quote();
                quote.setId(Long.valueOf(quote_id));
                Quote qt = quoteService.getQuoteById(quote);
                CustomerUser cusUser = new CustomerUser();
                cusUser.setEmpUserId(String.valueOf(loginid));
                List<CustomerUser> userList = quoteService.getAuditors(cusUser);
                if (userList != null && userList.size() != 0) {
                    for (CustomerUser user : userList) {
                        String userNumber = "<br>&nbsp;&nbsp;Disti " + qt.getCusGroup_id() + " submit a Quote,coded as "
                                + qt.getQuote_id() + ", please login platform for approval";
                        String contents = "Hi " + user.getUserName() + userNumber + "<br>";
                        Message m = new Message();
                        m.setContent(contents);
                        m.setType("Quote Create");
                        m.setSendNumber(user.getEmail());
                        messageService.saveMessage(m);
                    }
                }
                QuoteDetail quoteDetail = new QuoteDetail();
                quoteDetail.setQuote_id(qt.getQuote_id());
                quoteDetail.setForward_id(Long.valueOf(loginid));
                qdList = quoteService.getQuoteDetailList(quoteDetail);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                for (QuoteDetail qd : qdList) {
                    if (qd.getProduct_dateStr() != null && !"".equals(qd.getProduct_dateStr())
                            && !"undefined".equals(qd.getProduct_dateStr())) {
                        try {

                            qd.setProduct_date(sdf.parse(qd.getProduct_dateStr()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    qd.setCus_remark(Escape.unescape(qd.getCus_remark()));
                    qd.setRemark(Escape.unescape(qd.getRemark()));
                    qd.setCusGroup_id(Escape.unescape(qd.getCusGroup_id()));
                    qd.setCus_profits_percent(Escape.unescape(qd.getCus_profits_percent()));
                    qd.setProfits_percent(Escape.unescape(qd.getProfits_percent()));
                    qd.setType("Quotecenter");
                    qd.setLatest_userId(this.getUser().getUserId());
                    quoteService.createQuoteLog(qd);
                }
            } else {
                this.setFailMessage("error");
            }
        } catch (Exception e) {
            this.setFailMessage(e.getMessage());
        }
        return RESULT_MESSAGE;

    }
    // end

    public String toMarketQuote() {
        return "toMarketQuote";
    }

    public String toOutPort() {
        q = new Quote();
        String orgString = this.getUser().getOrgId();
        if (orgString == null || "".equals(orgString)) {
            q.setCusGroup_id(this.getUser().getUserName());
            forWho = "Disti";
        }
        return "toOutPort";
    }

    public String toSalerQuote() {
        return "toSalerQuote";
    }

    public String toEDIQuote() {
        AllUsers a = this.getUser();
        System.out.println("a:" + a.getEmail());
        System.out.println("a:" + a.getEmpPostId());
        return "toEDIQuote";
    }

    public String toCheck() {
        qd = new QuoteDetail();
        qd.setId(Long.valueOf(id));
        qd.setMaterial_name(material_name);
        qd.setPurchaseCustomer_name(purchaseCustomer_name);
        qd.setEndCustomer_name(endCustomer_name);
        qd.setPcGroup_id(pcGroup_id);
        qd.setEcGroup_id(ecGroup_id);
        qd.setStart_dateStr(start_dateStr);
        qd.setRemark(remark);
        return "toCheck";
    }

    public String toOutPortQuote() {
        q = new Quote();
        String userId = this.getUser().getUserId();
        String orgString = this.getUser().getOrgId();
        if (orgString == null || "".equals(orgString)) {
            q.setCusGroup_id(this.getUser().getUserName());
            forWho = "Disti";
        }

        roleControl = "0";

        Role r = new Role();
        r.setStart(0);
        r.setEnd(100);
        r.setEmp_code(userId);
        roleList = roleService.getSelectedRole4StationList(r);
        for (Role rl : roleList) {
            if ("JXS".equals(rl.getRoleId()) || orgString == null || "".equals(orgString)) {
                q.setRoleId("JXS");
            } else if ("HK10_H_Sale_Mgmt".equals(rl.getRoleId())) {
                q.setRoleId("HK10_H_Sale_Mgmt");
            } else if ("sys_admin".equals(rl.getRoleId())) {
                q.setRoleId("admin");
            } else if ("HK10_H_Marketing_Mgmt".equals(rl.getRoleId())) {
                q.setRoleId("HK10_H_Marketing_Mgmt");
                roleControl = "1";
            } else if ("HK10_H_BL_Mgmt".equals(rl.getRoleId())) {
                roleControl = "1";
            } else if ("Quote_Center".equals(rl.getRoleId())) {
                roleControl = "1";
            }
        }
        return "toOutPortQuote";
    }

    public String toQuotePcCountry() {
        q = new Quote();
        String userId = this.getUser().getUserId();
        String orgString = this.getUser().getOrgId();
        if (orgString == null || "".equals(orgString)) {
            q.setCusGroup_id(this.getUser().getUserName());
            forWho = "Disti";
        }
        Role r = new Role();
        r.setStart(0);
        r.setEnd(100);
        r.setEmp_code(userId);
        roleList = roleService.getSelectedRole4StationList(r);
        for (Role rl : roleList) {
            if ("JXS".equals(rl.getRoleId()) || orgString == null || "".equals(orgString)) {
                q.setRoleId("JXS");
            } else if ("HK10_H_Sale_Mgmt".equals(rl.getRoleId())) {
                q.setRoleId("HK10_H_Sale_Mgmt");
            } else if ("sys_admin".equals(rl.getRoleId())) {
                q.setRoleId("admin");
            } else if ("HK10_H_Marketing_Mgmt".equals(rl.getRoleId())) {
                q.setRoleId("HK10_H_Marketing_Mgmt");
            }
        }
        return "toQuotePcCountry";
    }

    public String toSearchQuoteLog() {
        qd = new QuoteDetail();
        qd.setQuote_id(quote_id);
        qd.setMaterial_id(material_id);
        String orgString = this.getUser().getOrgId();
        if (orgString == null || "".equals(orgString)) {
            forWho = "JXS";
        }
        return "toSearchQuoteLog";
    }

    @PermissionSearch
    @JsonResult(field = "qList", include = { "id", "type_id", "disti_branch", "customer_id", "quote_id", "cusGroup_id",
            "ecGroup_id", "isDelivery", "currency_code", "ship_to", "payer_to", "billing_to", "saler", "sale_company",
            "endCustomer_id", "pcGroup_id", "customer_name", "endCustomer_name", "ecGroup_name", "pcGroup_name",
            "purchaseCustomer_id", "purchaseCustomer_name", "project_name", "contact_info", "state", "remark",
            "enquiry_id", "start_date", "latest_expire", "create_time", "create_userId", "start_dateStr",
            "latest_expireStr", "isRepresent", "latest_time", "latest_userId", "org_code",
            "sync_state" }, total = "total")
    public String getQuoteList() {

        q = new Quote();
        q.setStart(getStart());
        q.setEnd(getEnd());
        q.setSort("aa.id");
        q.setDir("desc");
        if (cusGroup_id != null && (!("".equals(cusGroup_id)))) {
            q.setCusGroup_id(cusGroup_id.toUpperCase());
        }
        if (disti_branch != null && (!("".equals(disti_branch)))) {
            q.setDisti_branch(disti_branch.toUpperCase());
        }
        if (quote_id != null && (!("".equals(quote_id)))) {
            q.setQuote_id(quote_id.toUpperCase());
        }
        if (project_name != null && (!("".equals(project_name)))) {
            q.setProject_name(project_name.toUpperCase());
        }
        if (material_name != null && (!("".equals(material_name)))) {
            q.setMaterial_name(material_name.toUpperCase());

        }
        if (create_userName != null && (!("".equals(create_userName)))) {
            q.setCreate_userName(create_userName.toUpperCase());

        }
        String userId = this.getUser().getUserId();
        String userName = this.getUser().getUserName();
        Role r = new Role();
        r.setStart(0);
        r.setEnd(100);
        r.setEmp_code(userId);
        roleList = roleService.getSelectedRole4StationList(r);
        for (Role rl : roleList) {
            if ("HK10_H_Sale_Mgmt".equals(rl.getRoleId())) {
                q.setAuditorId(userId);
            }
        }
        String orgString = this.getUser().getOrgId();
        if (orgString == null || "".equals(orgString)) {
            q.setCusGroup_id(userName.toUpperCase());
        }
        q.setEndCustomer_id(endCustomer_id);
        q.setPurchaseCustomer_id(purchaseCustomer_id);
        total = quoteService.getQuoteListCount(q);
        if (total > 0) {
            qList = quoteService.getQuoteList(q);
        }
        return JSON;
    }

    @PermissionSearch
    @JsonResult(field = "qdList", include = {
            // 主表
            "customer_id", "disti_branch", "cusGroup_id", "ecGroup_id", "isDelivery", "currency_code",
            "purchaseCustomer_id", "purchaseCustomer_name", "endCustomer_id", "project_name", "customer_name",
            "endCustomer_name", "ecGroup_name", "pcGroup_name", "latest_userId", "latest_time",
            // 从表
            "id", "main_id", "quote_id", "row_no", "material_id", "drNum", "qty", "isAgree", "isRepresent",
            "target_resale", "target_cost", "target_amount", "amount", "reason", "competitor", "res_qty",
            "product_date", "product_dateStr", "start_date", "start_dateStr", "end_date", "end_dateStr", "cus_remark",
            "suggest_resale", "suggest_cost", "material_name", "create_userId", "cus_profits_percent",
            "profits_percent", "remark", "state", "target_margin", "data_from", "debit_start", "debit_end", "debit_num",
            "sale_price", "stop_price", "pbMpp", "cost", "price_region", "disti_region", "pc_region", "ec_region",
            "forwarder", "forward_id", "qm_price", "isDRItem" }, total = "total")
    public String getAuditQuoteList() {

        qd = new QuoteDetail();
        qd.setStart(getStart());
        qd.setEnd(getEnd());
        qd.setSort("aa.id");
        qd.setDir("desc");
        qd.setStates(states);
        if (cusGroup_id != null && (!("".equals(cusGroup_id)))) {
            qd.setCusGroup_id(cusGroup_id.toUpperCase());
        }
        String userId = this.getUser().getUserId();
        String userName = this.getUser().getUserName();
        Role r = new Role();
        r.setStart(0);
        r.setEnd(100);
        r.setEmp_code(userId);
        roleList = roleService.getSelectedRole4StationList(r);
        boolean isSales = false;
        boolean isQuoteCenter = false;
        boolean isMarketing = false;
        boolean isBL = false;
        boolean isOthers = false;
        if ("0".equals(webFrom)) {
            for (Role rl : roleList) {
                if (isOthers) {
                    break;
                }
                if ("Quote_Center".equals(rl.getRoleId())) {
                    isQuoteCenter = true;
                } else if ("sys_admin".equals(rl.getRoleId()) || "CMD".equals(rl.getRoleId())) {
                    isOthers = true;
                    isBL = false;
                    isSales = false;
                    isQuoteCenter = false;
                }
            }
        } else {
            for (Role rl : roleList) {
                if (isOthers) {
                    break;
                }
                if ("Quote_Center".equals(rl.getRoleId())) {
                    if (!isBL && !isSales && !isMarketing) {
                        isQuoteCenter = true;
                    }
                } else if ("HK10_H_Sale_Mgmt".equals(rl.getRoleId())) {
                    if (!isBL && !isMarketing) {
                        isSales = true;
                    }
                    isQuoteCenter = false;
                } else if ("HK10_H_Marketing_Mgmt".equals(rl.getRoleId())) {
                    if (!isBL) {
                        isMarketing = true;
                    }
                    isSales = false;
                    isQuoteCenter = false;
                } else if ("HK10_H_BL_Mgmt".equals(rl.getRoleId())) {
                    isBL = true;
                    isSales = false;
                    isMarketing = false;
                    isQuoteCenter = false;
                } else if ("sys_admin".equals(rl.getRoleId()) || "CMD".equals(rl.getRoleId())) {
                    isOthers = true;
                    isBL = false;
                    isSales = false;
                    isQuoteCenter = false;
                }
            }
        }
        
        if ("(2)".equals(states) && isBL) {
            qd.setForward_id(Long.valueOf(userId));
        } else if (isSales) {
            if ("(0)".equals(states)) {
                qd.setForward_id(Long.valueOf(userId));
            } else {
                states = "(1,2,3,4,5,6,7,8)";
                qd.setIsDelivery("3");
                qd.setStates(states);
                qd.setForward_id(Long.valueOf(userId));
            }
        } else if (isQuoteCenter) {
            if ("(0)".equals(states)) {
                qd.setForward_id(Long.valueOf(userId));
                qd.setForwarder("1");
            } else {
                states = "(0,1,2,3,4,5,6,7,8)";
                qd.setIsDelivery("2");
                qd.setStates(states);
            }
        }
        String orgString = this.getUser().getOrgId();
        if (orgString == null || "".equals(orgString)) {
            qd.setCusGroup_id(userName.toUpperCase());
        }
        if (disti_branch != null && (!("".equals(disti_branch)))) {
            qd.setDisti_branch(disti_branch.toUpperCase());
        }
        if (quote_id != null && (!("".equals(quote_id)))) {
            qd.setQuote_id(quote_id.toUpperCase());
        }
        if (debit_num != null && (!("".equals(debit_num)))) {
            qd.setDebit_num(debit_num.toUpperCase());
        }
        if (project_name != null && (!("".equals(project_name)))) {
            qd.setProject_name(project_name.toUpperCase());
        }
        if (material_name != null && (!("".equals(material_name)))) {
            qd.setMaterial_name(material_name.toUpperCase());
        }

        qd.setCustomer_id(customer_id);
        qd.setEndCustomer_id(endCustomer_id);
        qd.setPurchaseCustomer_id(purchaseCustomer_id);
        qd.setMaterial_id(material_id);
        qd.setStart_dateStr(start_dateStr);
        qd.setEnd_dateStr(end_dateStr);
        AllUsers allUsers = this.getUser();

        if (isOthers) {
            String org = allUsers.getOrgId();
            qd.setSalesOrg(org);
        }
        qd.setPc_region(pc_region);
        if ("(2)".equals(states) && isBL) {
            total = quoteService.getAuditQuoteListCountForBL(qd);
            if (total > 0) {
                qdList = quoteService.getAuditQuoteListForBL(qd);
                for (QuoteDetail qdt : qdList) {
                    double rm = quoteService.getReginalMin(qdt);
                    double cm = quoteService.getCMM(qdt);
                    qdt.setSale_price(String.valueOf(rm));
                    qdt.setStop_price(String.valueOf(cm));
                    pos = new Pos();
                    pos.setDebit_number(qdt.getQuote_id());
                    pos.setBook_part(qdt.getMaterial_name());
                    double passQty = posService.getPassedQty(pos);
                    qdt.setRes_qty(qdt.getQty() - passQty);

                    double resale = qdt.getTarget_resale();
                    double cost = qdt.getTarget_cost();
                    double mCost = Double.valueOf(qdt.getCost());
                    double num = qdt.getQty();
                    qdt.setTarget_amount(Math.round((cost * num) * 10000) / 10000.0000);
                    if ("EUR".equals(qdt.getCurrency_code())) {
                        mCost = Math.round((mCost * qdt.getRate()) * 10000) / 10000.0000;
                    }
                    qdt.setCost(String.valueOf(mCost));
                    if (resale == 0 || (resale - cost) == 0) {
                        qdt.setTarget_margin("0.00");
                    } else {
                        DecimalFormat formater = new DecimalFormat("#0.##");
                        String targetMargin = formater.format(((resale - cost) / resale) * 10000 / 100.0000);
                        qdt.setTarget_margin(targetMargin);
                    }
                }
            }
        } else {
            total = quoteService.getAuditQuoteListCount(qd);
            if (total > 0) {
                qdList = quoteService.getAuditQuoteList(qd);
                for (QuoteDetail qdt : qdList) {
                    double rm = quoteService.getReginalMin(qdt);
                    double cm = quoteService.getCMM(qdt);
                    qdt.setSale_price(String.valueOf(rm));
                    qdt.setStop_price(String.valueOf(cm));
                    pos = new Pos();
                    pos.setDebit_number(qdt.getQuote_id());
                    pos.setBook_part(qdt.getMaterial_name());
                    double passQty = posService.getPassedQty(pos);
                    qdt.setRes_qty(qdt.getQty() - passQty);
                    double resale = qdt.getTarget_resale();
                    double cost = qdt.getTarget_cost();
                    double mCost = Double.valueOf(qdt.getCost());
                    double num = qdt.getQty();
                    qdt.setTarget_amount(Math.round((cost * num) * 10000) / 10000.0000);
                    if ("EUR".equals(qdt.getCurrency_code())) {
                        mCost = Math.round((mCost * qdt.getRate()) * 10000) / 10000.0000;
                    }
                    qdt.setCost(String.valueOf(mCost));
                    if (resale == 0 || (resale - cost) == 0) {
                        qdt.setTarget_margin("0.00");
                    } else {
                        DecimalFormat formater = new DecimalFormat("#0.##");
                        String targetMargin = formater.format(((resale - cost) / resale) * 10000 / 100.0000);
                        qdt.setTarget_margin(targetMargin);
                    }
                }
            }
        }

        return JSON;
    }

    @PermissionSearch
    @JsonResult(field = "qList", include = { "id", "type_id", "disti_branch", "customer_id", "quote_id", "cusGroup_id",
            "ecGroup_id", "isDelivery", "currency_code", "ship_to", "payer_to", "billing_to", "saler", "sale_company",
            "endCustomer_id", "pcGroup_id", "customer_name", "endCustomer_name", "ecGroup_name", "pcGroup_name",
            "purchaseCustomer_id", "purchaseCustomer_name", "project_name", "contact_info", "state", "remark",
            "enquiry_id", "start_date", "latest_expire", "create_time", "create_userId", "start_dateStr",
            "latest_expireStr", "latest_time", "latest_userId", "org_code", "sync_state", "noPCEC", "edi_ec_country",
            "edi_ec_province", "edi_ec_city", "edi_ec_zip", "edi_pc_country", "edi_pc_province", "edi_pc_city",
            "edi_pc_zip" }, total = "total")
    public String getEDIQuote() {

        q = new Quote();
        q.setStart(getStart());
        q.setEnd(getEnd());
        q.setSort("aa.id");
        q.setDir("desc");
        q.setNoPCEC("noPCEC");
        total = quoteService.getEDIQuoteCount(q);
        if (total > 0) {
            qList = quoteService.getEDIQuote(q);
            for (Quote qt : qList) {
                boolean pc = (qt.getPurchaseCustomer_name() != null && (!"".equals(qt.getPurchaseCustomer_name())));
                boolean pcid = (qt.getPurchaseCustomer_id() == null || ("".equals(qt.getPurchaseCustomer_id())));
                boolean ec = (qt.getEndCustomer_name() != null && (!"".equals(qt.getEndCustomer_name())));
                boolean ecid = (qt.getEndCustomer_id() == null || ("".equals(qt.getEndCustomer_id())));
                if ((pc && pcid) && (ec && ecid)) {
                    qt.setNoPCEC("PCEC");
                } else if ((pc && pcid) && (!(ec && ecid))) {
                    qt.setNoPCEC("PC");
                } else if ((!(pc && pcid)) && (ec && ecid)) {
                    qt.setNoPCEC("EC");
                }
            }
        }
        return JSON;
    }

    @PermissionSearch
    @JsonResult(field = "qdList", include = { "id", "quote_id", "row_no", "material_id", "drNum", "qty", "isRepresent",
            "target_resale", "target_cost", "target_amount", "amount", "reason", "competitor", "target_margin",
            "res_qty", "product_date", "product_dateStr", "start_date", "start_dateStr", "end_date", "end_dateStr",
            "latest_time", "cus_remark", "suggest_resale", "suggest_cost", "state", "latest_userId", "isAgree",
            "create_userId", "cus_profits_percent", "profits_percent", "remark", "material_name", "cost", "moq",
            "pbMpp", "data_from", "debit_start", "debit_end", "debit_num", "isDRItem" })
    public String getQuoteDetailList() {
        qd = new QuoteDetail();
        qd.setQuote_id(quote_id);
        qdList = quoteService.getQuoteDetailList(qd);
        for (QuoteDetail qdt : qdList) {
            pos = new Pos();
            pos.setDebit_number(qdt.getQuote_id());
            pos.setBook_part(qdt.getMaterial_name());
            double passQty = posService.getPassedQty(pos);
            qdt.setRes_qty(qdt.getQty() - passQty);
            double resale = qdt.getTarget_resale();
            double cost = qdt.getTarget_cost();
            double num = qdt.getQty();
            qdt.setTarget_amount(Math.round((cost * num) * 10000) / 10000.0000);
            if (resale == 0 || (resale - cost) == 0) {
                qdt.setTarget_margin("0.00");
            } else {
                DecimalFormat formater = new DecimalFormat("#0.##");
                String targetMargin = formater.format(((resale - cost) / resale) * 10000 / 100.0000);
                qdt.setTarget_margin(targetMargin);
            }
        }
        return JSON;
    }

    @PermissionSearch
    @JsonResult(field = "qdList", include = { "id", "quote_id", "row_no", "res_qty", "isRepresent", "material_id",
            "qty", "target_resale", "target_cost", "target_amount", "latest_time", "type", "create_userId",
            "suggest_resale", "suggest_cost", "state", "latest_userId", "remark", "cus_remark", "material_name",
            "data_from" })
    public String getQuoteLogList() {
        qd = new QuoteDetail();
        qd.setQuote_id(quote_id);
        qd.setMaterial_id(material_id);
        qdList = quoteService.getQuoteLogList(qd);
        return JSON;
    }

    @SuppressWarnings("unchecked")
    public String createQuote() {
        this.setSuccessMessage("");
        this.setFailMessage("");
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            qdList = JsonUtil.getDTOList(quoteDetailJson, QuoteDetail.class);
            q.setCreate_userId(this.getUser().getUserId());            
            XssUtils.getXssSaftBean(q.getClass(),q);           
            
            for (QuoteDetail qd : qdList) {            	
            	XssUtils.getXssSaftBean(qd.getClass(),qd);
            	
                if (qd.getProduct_dateStr() != null && !"".equals(qd.getProduct_dateStr())
                        && !"undefined".equals(qd.getProduct_dateStr())) {
                    qd.setProduct_date(sdf.parse(qd.getProduct_dateStr()));
                }
                qd.setCus_remark(Escape.unescape(qd.getCus_remark()));
                qd.setRemark(Escape.unescape(qd.getRemark()));
                qd.setCus_profits_percent(Escape.unescape(qd.getCus_profits_percent()));
                qd.setProfits_percent(Escape.unescape(qd.getProfits_percent()));
                qd.setLatest_userId(this.getUser().getUserId());
                qd.setType("DistiCreate");
                Product p1 = new Product();
                p1.setMaterial_id(qd.getMaterial_id());
                pList = productService.getDRQuoteProductListNoPage(p1);
                if (pList.size() > 0) {
                    qd.setCost(pList.get(0).getCost());
                }
            }
            BooleanResult bool = quoteService.createQuote(q, qdList);
            if (bool.getResult()) {
                this.setSuccessMessage("Success !");
                // 需求变更V6.9 202008 Quote生成后不在发给相关销售邮件通知， START
                /*
                List<CustomerUser> userList = quoteService.getQuoteAuditSale(q);
                if (userList != null && userList.size() != 0) {
                    for (CustomerUser user : userList) {

                        String userNumber = "<br>&nbsp;&nbsp;Disti " + q.getCusGroup_id() + " submit a Quote,coded as "
                                + q.getQuote_id() + ", please login platform for approval";
                        String contents = "Hi " + user.getUserName() + userNumber + "<br>";
                        Message m = new Message();
                        m.setContent(contents);
                        m.setType("Quote Create");
                        m.setSendNumber(user.getEmail());
                        // messageService.saveMessage(m);
                        System.out.println("AddEmail(Quote Create) : " + user.getEmail());

                    }
                }
                */
                // 需求变更V6.9 202008 Quote生成后不在发给相关销售邮件通知， END                
            } else {
                this.setFailMessage("Error !");
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.setFailMessage(e.getMessage());
        }
        return RESULT_MESSAGE;
    }

    @SuppressWarnings("unchecked")
    public String updateQuote() throws UnsupportedEncodingException {
        this.setSuccessMessage("");
        this.setFailMessage("");
        try {
            quoteDetailJson = java.net.URLDecoder.decode(this.quoteDetailJson, "utf-8");
            qdList = JsonUtil.getDTOList(quoteDetailJson, QuoteDetail.class);
            qd = new QuoteDetail();
            qd.setIds(delQuoteDetail);
            q.setLatest_userId(this.getUser().getUserId());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            XssUtils.getXssSaftBean(q.getClass(),q);   
            
            for (QuoteDetail qd : qdList) {
            	XssUtils.getXssSaftBean(qd.getClass(),qd);
            	
                if (qd.getProduct_dateStr() != null && !"".equals(qd.getProduct_dateStr())
                        && !"undefined".equals(qd.getProduct_dateStr())) {
                    qd.setProduct_date(sdf.parse(qd.getProduct_dateStr()));
                }
                qd.setMain_id(q.getId());
                qd.setQuote_id(q.getQuote_id());
                qd.setLatest_userId(this.getUser().getUserId());
            }
            BooleanResult bool = quoteService.updateQuote(q, qdList, qd);
            if (bool.getResult()) {
                this.setSuccessMessage("Success !");
            } else {
                this.setFailMessage(bool.getCode());
            }

        } catch (Exception e) {
            this.setFailMessage(e.getMessage());
        }
        return RESULT_MESSAGE;
    }

    public String auditQuote() throws UnsupportedEncodingException {
        this.setFailMessage("");
        this.setSuccessMessage("");
        q = new Quote();
        q.setId(Long.valueOf(id));
        q.setState(Integer.valueOf(state));
        int i = quoteService.auditQuote(q);
        if (i > 0) {
            this.setSuccessMessage("Success !");
        } else {
            this.setFailMessage("failed !");
        }
        return RESULT_MESSAGE;
    }

    @SuppressWarnings("unchecked")
    public String auditQuoteDetail() throws UnsupportedEncodingException {
        this.setSuccessMessage("");
        this.setFailMessage("");
        String role = "";
        qdList = JsonUtil.getDTOList(quoteDetailJson, QuoteDetail.class);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (QuoteDetail qd : qdList) {
            if (qd.getProduct_dateStr() != null && !"".equals(qd.getProduct_dateStr())
                    && !"undefined".equals(qd.getProduct_dateStr())) {
                try {
                    qd.setProduct_date(sdf.parse(qd.getProduct_dateStr()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            qd.setRemark(Escape.unescape(qd.getRemark()));
            qd.setCusGroup_id(java.net.URLDecoder.decode(qd.getCusGroup_id(), "UTF-8"));
            qd.setCus_remark(Escape.unescape(qd.getCus_remark()));
            qd.setCus_profits_percent(java.net.URLDecoder.decode(qd.getCus_profits_percent(), "UTF-8"));
            qd.setProfits_percent(java.net.URLDecoder.decode(qd.getProfits_percent(), "UTF-8"));
            if (qd.getState() == 3 && "9".equals(qd.getIsDelivery())) {
                qd.setType("QuoteCenterApprove");
            } else if (qd.getState() == 3) {
                qd.setType("SalesApprove");
            } else if (qd.getState() == 4) {
                qd.setType("BusinessApprove");
            } else if (qd.getState() == 5) {
                qd.setType("DirectorApprove");
            } else if (qd.getState() == 6) {
                qd.setType("SalesReject");
            } else if (qd.getState() == 7) {
                qd.setType("BusinessReject");
            } else if (qd.getState() == 8) {
                qd.setType("DirectorReject");
            } else if (qd.getState() == 1) {
                qd.setType("SalesEscalation");
            } else if (qd.getState() == 2) {
                qd.setType("BusinessEscalation");
            } else if (qd.getState() == 0) {
                qd.setType("DistiRepresent");
            }
            qd.setLatest_userId(this.getUser().getUserId());
        }
        BooleanResult bool = quoteService.auditQuoteDetail(q, qdList);
        if (bool.getResult()) {
            this.setSuccessMessage("Success !");
            List<String> areadySend = new ArrayList<String>();
            for (QuoteDetail qDetail : qdList) {
                boolean needSend = true;
                for (String quoteNum : areadySend) {
                    if (qDetail.getQuote_id().equals(quoteNum)) {
                        needSend = false;
                        break;
                    }
                }
                if (needSend == true) {
                    if (qDetail.getState() == 1) {
                        String content = "<br>&nbsp;&nbsp;Disti " + qDetail.getCusGroup_id()
                                + " submit a Quote,coded as " + qDetail.getQuote_id()
                                + ", please login platform for approval";
                        CustomerUser cusUser = new CustomerUser();
                        role = "HK10_H_Marketing_Mgmt";
                        cusUser.setRoleIds(role);
                        List<CustomerUser> userList = quoteService.getAuditors(cusUser);
                        if (userList != null && userList.size() != 0) {
                            for (CustomerUser user : userList) {
                                if (user.getEmail() != null) {
                                    String contents = "Hi " + user.getUserName() + content + "<br>";
                                    sendMailByAddree(user.getEmail(), contents, "Quote ESC2Business");
                                }
                            }
                        }
                    } else if (qDetail.getState() == 2) {
                        String content = "<br>&nbsp;&nbsp;Disti " + qDetail.getCusGroup_id()
                                + " submit a Quote,coded as " + qDetail.getQuote_id()
                                + ", please login platform for approval";
                        CustomerUser cusUser = new CustomerUser();
                        role = "HK10_H_BL_Mgmt";
                        cusUser.setRoleIds(role);
                        List<CustomerUser> userList = quoteService.getAuditors(cusUser);
                        if (userList != null && userList.size() != 0) {
                            for (CustomerUser user : userList) {
                                if (user.getEmail() != null) {
                                    String contents = "Hi " + user.getUserName() + content + "<br>";
                                    sendMailByAddree(user.getEmail(), contents, "Quote ESC2Market");
                                }
                            }
                        }
                    } else if (qDetail.getState() == 0) {
                        String content = "<br>&nbsp;&nbsp;Disti " + qDetail.getCusGroup_id()
                                + " submit a Quote,coded as " + qDetail.getQuote_id()
                                + ", please login platform for approval";
                        q = new Quote();
                        q.setQuote_id(qDetail.getQuote_id());
                        List<CustomerUser> userList = quoteService.getQuoteAuditSale(q);
                        if (userList != null && userList.size() != 0) {
                            for (CustomerUser user : userList) {
                                String contents = "Hi " + user.getUserName() + content + "<br>";
                                Message m = new Message();
                                m.setContent(contents);
                                m.setType("Quote Create");
                                m.setSendNumber(user.getEmail());
                                messageService.saveMessage(m);
                                System.out.println("AddEmail(Quote Create) : " + user.getEmail());

                            }
                        }
                        if (userList.size() != 0) {
                            for (CustomerUser user : userList) {
                                String contents = "Hi " + user.getUserName() + content + "<br>";
                                sendMailByAddree(user.getEmail(), contents, "Quote Represent");
                            }
                        }
                    } else if (qDetail.getState() == 3 || qDetail.getState() == 4 || qDetail.getState() == 5
                            || qDetail.getState() == 6 || qDetail.getState() == 7 || qDetail.getState() == 8) {// 审批发给Disti
                        String content = "<br>&nbsp;&nbsp;The material " + qDetail.getMaterial_name()
                                + " you submit in Quote " + qDetail.getQuote_id()
                                + " has been checked, please login platform to view";
                        cusUser = new CustomerUser();
                        cusUser = customerService.getUsersByUserId(qDetail.getCreate_userId());
                        if (cusUser != null) {
                            String contents = "Dear user" + content + "<br>";
                            sendMailByAddree(cusUser.getEmail(), contents, "Quote Approve");
                        }
                    }
                    areadySend.add(qDetail.getQuote_id());
                }
            }
        } else {
            this.setFailMessage(bool.getCode());
        }
        return RESULT_MESSAGE;
    }

    public String sendMailByAddree(String emailAddress, String content, String type) {

        Message m = new Message();
        m.setContent(content);
        m.setType(type);
        m.setSendNumber(emailAddress);
        messageService.saveMessage(m);
        return "saveSuccess";
    }

    @SuppressWarnings("unchecked")
    public String resetAudit() throws UnsupportedEncodingException {
        this.setFailMessage("");
        this.setSuccessMessage("");
        quoteDetailJson = java.net.URLDecoder.decode(this.quoteDetailJson, "utf-8");
        qdList = JsonUtil.getDTOList(quoteDetailJson, QuoteDetail.class);
        int i = 0;
        for (QuoteDetail qd : qdList) {
            if (qd.getState() == 3 || qd.getState() == 6 || qd.getState() == 1) {
                qd.setState(0);
            } else if (qd.getState() == 4 || qd.getState() == 7 || qd.getState() == 2) {
                qd.setState(1);
            } else if (qd.getState() == 5 || qd.getState() == 8) {
                qd.setState(2);
            }
            if ("9".equals(approver)) {
                qd.setIsAgree(9);
                qd.setForward_id(null);
            }
            qd.setLatest_userId(this.getUser().getUserId());
            qd.setType("Reset");
            i = quoteService.changeQuoteDetailState(qd);
            if (i > 0) {
                this.setSuccessMessage("Success !");
            } else {
                this.setFailMessage("failed !");
                break;
            }
        }
        return RESULT_MESSAGE;
    }

    public String deleteQuote() {
        this.setFailMessage("");
        this.setSuccessMessage("");
        q = new Quote();
        q.setId(Long.valueOf(id));
        int i = quoteService.deleteQuote(q);
        qd = new QuoteDetail();
        qd.setMain_id(q.getId());
        if (i > 0) {
            quoteService.deleteQuoteDetail(qd);
            this.setSuccessMessage("Success !");
        } else {
            this.setFailMessage("failed !");
        }
        return RESULT_MESSAGE;
    }

    // Disti Agree
    @SuppressWarnings("unchecked")
    public String agreeQuoteDetail() throws UnsupportedEncodingException {
        this.setFailMessage("");
        this.setSuccessMessage("");
        qdList = JsonUtil.getDTOList(quoteDetailJson, QuoteDetail.class);
        int n = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (QuoteDetail qd : qdList) {
            if (qd.getProduct_dateStr() != null && !"".equals(qd.getProduct_dateStr())
                    && !"undefined".equals(qd.getProduct_dateStr())) {
                try {

                    qd.setProduct_date(sdf.parse(qd.getProduct_dateStr()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            qd.setCus_remark(Escape.unescape(qd.getCus_remark()));
            qd.setRemark(Escape.unescape(qd.getRemark()));
            qd.setCusGroup_id(Escape.unescape(qd.getCusGroup_id()));
            qd.setCus_profits_percent(Escape.unescape(qd.getCus_profits_percent()));
            qd.setProfits_percent(Escape.unescape(qd.getProfits_percent()));
            n = 0;
            n = quoteService.agreeQuoteDetail(qd);
            qd.setType("DistiAgree");
            qd.setLatest_userId(this.getUser().getUserId());
            quoteService.createQuoteLog(qd);
        }
        if (n > 0) {
            this.setSuccessMessage("Success !");
        } else {
            this.setFailMessage("failed !");
        }
        return RESULT_MESSAGE;
    }

    // Update debit
    @SuppressWarnings("unchecked")
    public String updateDebitDate() throws UnsupportedEncodingException {
        this.setFailMessage("");
        this.setSuccessMessage("");
        quoteDetailJson = java.net.URLDecoder.decode(this.quoteDetailJson, "utf-8");
        qdList = JsonUtil.getDTOList(quoteDetailJson, QuoteDetail.class);
        int n = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (QuoteDetail qd : qdList) {
            if (qd.getDebit_startStr() != null && !"".equals(qd.getDebit_startStr())
                    && !"undefined".equals(qd.getDebit_startStr())) {
                try {
                    qd.setDebit_start(sdf.parse(qd.getDebit_startStr()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (qd.getDebit_endStr() != null && !"".equals(qd.getDebit_endStr())
                    && !"undefined".equals(qd.getDebit_endStr())) {
                try {
                    qd.setDebit_end(sdf.parse(qd.getDebit_endStr()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            n = quoteService.updateDebitDate(qd);
        }
        if (n > 0) {
            this.setSuccessMessage("Success !");
        } else {
            this.setFailMessage("failed !");
        }
        return RESULT_MESSAGE;
    }

    @PermissionSearch
    @JsonResult(field = "qdList", include = { "customer_id", "disti_branch", "cusGroup_id", "ecGroup_id", "isDelivery",
            "currency_code", "purchaseCustomer_id", "purchaseCustomer_name", "endCustomer_id", "project_name",
            "customer_name", "endCustomer_name", "ecGroup_name", "pcGroup_name", "latest_userId", "latest_time", "id",
            "main_id", "quote_id", "row_no", "material_id", "drNum", "qty", "res_qty", "isRepresent", "target_resale",
            "target_cost", "target_amount", "amount", "reason", "competitor", "isAgree", "create_userId",
            "product_date", "product_dateStr", "start_date", "start_dateStr", "end_date", "end_dateStr", "cus_remark",
            "suggest_resale", "suggest_cost", "material_name", "create_time", "cus_profits_percent", "profits_percent",
            "remark", "state", "target_margin", "data_from", "debit_start", "debit_end", "debit_num", "debit_startStr",
            "debit_endStr", "sale_price", "stop_price", "pbMpp", "cost", "price_region", "disti_region", "pc_region",
            "ec_region", "ec_city", "pc_city", "ec_country", "ec_state", "pc_country", "pc_state", "create_user",
            "isDRItem", "ec_zip_code", "pc_zip_code",
            "customertypename","segmentname","applicationname","customertypenamepur","segmentnamepur","applicationnamepur" }, total = "total")
    public String getOutPortQuoteList() {
        qd = new QuoteDetail();
        qd.setStart(getStart());
        qd.setEnd(getEnd());
        qd.setStates(states);
        qd.setIsAgrees(isAgrees);
        if (cusGroup_id != null && (!("".equals(cusGroup_id)))) {
            qd.setCusGroup_id(cusGroup_id.toUpperCase());

        }
        String userId = this.getUser().getUserId();
        String userName = this.getUser().getUserName();
        Role r = new Role();
        r.setStart(0);
        r.setEnd(100);
        r.setEmp_code(userId);
        roleList = roleService.getSelectedRole4StationList(r);
        boolean isSalers = false;
        boolean isQuoteList = false;

        for (Role rl : roleList) {
            if ("HK10_H_Sale_Mgmt".equals(rl.getRoleId())) {
                isSalers = true;
                break;
                // qd.setForward_id(Long.valueOf(userId));
            } else if ("HK10_H_Quote_List".equals(rl.getRoleId())) {
                isQuoteList = true;
            }
        }

        if (isQuoteList || isSalers) {
            List<SaleCountry> countrys = quoteService.searchSaleCountry(userId);
            if (countrys != null && countrys.size() > 0) {
                StringBuilder countries = new StringBuilder();
                for (SaleCountry country : countrys) {
                    if (countries.length() == 0) {
                        countries.append("(");
                        countries.append("'");
                        countries.append(country.getCountry_code());
                        countries.append("'");
                    } else {
                        countries.append(", ");
                        countries.append("'");
                        countries.append(country.getCountry_code());
                        countries.append("'");
                    }
                }
                countries.append(")");
                qd.setPc_regions(countries.toString());
            }
        }
        String orgString = this.getUser().getOrgId();
        if (orgString == null || "".equals(orgString)) {
            qd.setCusGroup_id(userName.toUpperCase());
            
            Disti_branch disti = new Disti_branch();
            
            disti.setDisti_name(userName.toUpperCase());
            Disti_branch alias = quoteService.getDistAlias(disti);
            
            if (alias != null) {
                qd.setDisti_alias(alias.getDisti_alias()); 
            }
            
            if (StringUtils.isNotEmpty(disti_branch) && StringUtils.isNotEmpty(disti_branch.trim())) {
                Disti_branch db = new Disti_branch();
                db.setDisti_branch(disti_branch);
                Disti_branch dbAlias = quoteService.getDistBranchAlias(db);

                if (dbAlias != null) {
                    qd.setDisti_branch_alias(dbAlias.getAlias()); 
                }
            }
        }
        if (disti_branch != null && (!("".equals(disti_branch)))) {
            qd.setDisti_branch(disti_branch.toUpperCase());
        }
        if (quote_id != null && (!("".equals(quote_id)))) {
            qd.setQuote_id(quote_id.toUpperCase());
        }
        if (debit_num != null && (!("".equals(debit_num)))) {
            qd.setDebit_num(debit_num.toUpperCase());
        }
        if (project_name != null && (!("".equals(project_name)))) {
            qd.setProject_name(project_name.toUpperCase());
        }
        if (endCustomer_name != null && (!("".equals(endCustomer_name)))) {
            qd.setEndCustomer_name(endCustomer_name.toUpperCase());
        }
        if (purchaseCustomer_name != null && (!("".equals(purchaseCustomer_name)))) {
            qd.setPurchaseCustomer_name(purchaseCustomer_name.toUpperCase());
        }
        if (material_name != null && (!("".equals(material_name)))) {
            qd.setMaterial_name(material_name.toUpperCase());
        }
        if (material_id != null && (!("".equals(material_id)))) {
            qd.setMaterial_id(material_id.toUpperCase());
        }
        if (customer_id != null && (!("".equals(customer_id)))) {
            qd.setCustomer_id(customer_id.toUpperCase());
        }
        if (create_userName != null && (!("".equals(create_userName)))) {
            qd.setCreate_userName(create_userName.toUpperCase());
        }
        qd.setStart_dateStr(start_dateStr);
        qd.setEnd_dateStr(end_dateStr);
        AllUsers allUsers = this.getUser();
        String loginId = allUsers.getLoginId();
        if (!"admin".equals(loginId)) {
            String org = allUsers.getOrgId();
            qd.setSalesOrg(org);
        }
        total = quoteService.getOutPortQuoteListCount(qd);
        if (total > 0) {
            qdList = quoteService.getOutPortQuoteList(qd);
            List<QuoteDetail> rmList = quoteService.getReginalMins();
            if (rmList == null) {
                rmList = new ArrayList<QuoteDetail>();
            }

            List<QuoteDetail> cmList = quoteService.getCMMs();
            if (cmList == null) {
                cmList = new ArrayList<QuoteDetail>();
            }

            List<Pos> posList = posService.getPassedQtys();
            if (posList == null) {
                posList = new ArrayList<Pos>();
            }

            for (QuoteDetail qdt : qdList) {
                // double rm = quoteService.getReginalMin(qdt);
                // double cm = quoteService.getCMM(qdt);
                for (QuoteDetail rm : rmList) {
                    if (isEquals(rm, qdt)) {
                        qdt.setSale_price(rm.getSale_price());
                        break;
                    }
                }
                for (QuoteDetail cm : cmList) {
                    if (isEquals(cm, qdt)) {
                        qdt.setStop_price(cm.getStop_price());
                        break;
                    }
                }
                // qdt.setSale_price(String.valueOf(rm));
                // qdt.setStop_price(String.valueOf(cm));
                pos = new Pos();
                pos.setDebit_number(qdt.getQuote_id());
                pos.setBook_part(qdt.getMaterial_name());
                double passQty = 0.00;
                for (Pos pos : posList) {
                    if (pos.getDebit_number().equals(qdt.getQuote_id())
                            && pos.getBook_part().equals(qdt.getMaterial_name())) {
                        passQty = pos.getSumShipQty();
                        break;
                    }
                }
                // double passQty = posService.getPassedQty(pos);
                qdt.setRes_qty(qdt.getQty() - passQty);
                double resale = qdt.getTarget_resale();
                double cost = qdt.getTarget_cost();
                double mCost = Double.valueOf(qdt.getCost());
                double num = qdt.getQty();

                BigDecimal bd = new BigDecimal(num);
                pos.setQty(Long.valueOf(bd.toPlainString()));

                qdt.setTarget_amount(Math.round((cost * num) * 10000) / 10000.0000);
                if ("EUR".equals(qdt.getCurrency_code())) {
                    mCost = Math.round((mCost * qdt.getRate()) * 10000) / 10000.0000;
                }
                qdt.setCost(String.valueOf(mCost));
                if (resale == 0 || (resale - cost) == 0) {
                    qdt.setTarget_margin("0.00");
                } else {
                    DecimalFormat formater = new DecimalFormat("#0.##");
                    String targetMargin = formater.format(((resale - cost) / resale) * 10000 / 100.0000);
                    qdt.setTarget_margin(targetMargin);
                }
            }
        }
        return JSON;
    }

    private boolean isEquals(QuoteDetail a, QuoteDetail b) {
        if (a.getPrice_region().equals(b.getPrice_region()) && a.getCurrency_code().equals(b.getCurrency_code())
                && a.getStrMaterialId().equals(b.getStrMaterialId())) {
            return true;
        }
        return false;
    }

    public void downloadExcelModel() {
        try {
            List<String> list = new ArrayList<String>();
            list.add("Status");
            list.add("IsAgree");
            list.add("QuoteNum");
            list.add("12NC");
            list.add("BookPart");
            list.add("DRNum");
            list.add("Project");
            list.add("Disti");
            list.add("Disti Branch");
            list.add("Currency");
            list.add("QTY");
            list.add("Target Cost");
            list.add("Target Resale");
            list.add("PB/MPP");
            list.add("RegionalMin");
            list.add("CMM");
            list.add("Suggest Cost");
            list.add("Suggest Resale");
            list.add("MfrMargin");
            list.add("DistiMargin");
            list.add("Value");
            list.add("Justification");
            list.add("Competitor");
            list.add("StartDate");
            list.add("EndDate");
            list.add("Start of Production");
            list.add("PC Group");
            list.add("PC");
            list.add("PC Location");
            list.add("EC Group");
            list.add("EC");
            list.add("EC Location");
            list.add("Price Region");
            list.add("Disti Region");
            list.add("PC Region");
            list.add("EC Region");
            list.add("CusRemark");
            list.add("Remark");
            list.add("Create Time");
            list.add("Updater");
            list.add("Update Time");
            File source = new File("Quote.xls");
            WritableWorkbook wwb = Workbook.createWorkbook(source);
            WritableSheet sheet = wwb.createSheet("Quote", 0);
            Label label = null;
            Label label2 = null;
            Label label3 = null;
            Label label4 = null;
            Label label5 = null;
            Label label6 = null;
            Label label7 = null;
            Label label8 = null;
            Label label9 = null;
            Label label10 = null;
            Label label11 = null;
            Label label12 = null;
            Label label13 = null;
            Label label14 = null;
            Label label15 = null;
            Label label16 = null;
            Label label17 = null;
            Label label18 = null;
            Label label19 = null;
            Label label20 = null;
            Label label21 = null;
            Label label22 = null;
            Label label23 = null;
            Label label24 = null;
            Label label25 = null;
            Label label26 = null;
            Label label27 = null;
            Label label28 = null;
            Label label29 = null;
            Label label30 = null;
            Label label31 = null;
            Label label32 = null;
            Label label33 = null;
            Label label34 = null;
            Label label35 = null;
            Label label36 = null;
            Label label37 = null;
            Label label38 = null;
            Label label39 = null;
            Label label40 = null;
            Label label41 = null;

            WritableFont font1 = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false,
                    UnderlineStyle.NO_UNDERLINE, Colour.RED);

            WritableCellFormat cellFormat1 = new WritableCellFormat(font1);
            cellFormat1.setBackground(Colour.YELLOW);
            cellFormat1.setBorder(Border.ALL, BorderLineStyle.HAIR);
            for (int i = 0; i < list.size(); i++) {
                label = new Label(i, 0, list.get(i).toString(), cellFormat1);
                sheet.addCell(label);
            }
            List<QuoteDetail> qdList = new ArrayList<QuoteDetail>();
            qd = new QuoteDetail();
            qd.setStates(states);
            qd.setIsAgrees(isAgrees);
            qd.setCustomer_id(customer_id);
            qd.setMaterial_id(material_id);
            qd.setStart_dateStr(start_dateStr);
            qd.setEnd_dateStr(end_dateStr);
            String userId = this.getUser().getUserId();
            String userName = this.getUser().getUserName();
            Role r = new Role();
            r.setStart(0);
            r.setEnd(100);
            r.setEmp_code(userId);
            roleList = roleService.getSelectedRole4StationList(r);
            for (Role rl : roleList) {
                if ("HK10_H_Sale_Mgmt".equals(rl.getRoleId())) {
                    qd.setAuditorId(userId);
                }
            }
            if (cusGroup_id != null && (!("".equals(cusGroup_id)))) {
                qd.setCusGroup_id(cusGroup_id.toUpperCase());

            }
            String orgString = this.getUser().getOrgId();
            if (orgString == null || "".equals(orgString)) {
                qd.setCusGroup_id(userName.toUpperCase());
            }
            if (disti_branch != null && (!("".equals(disti_branch)))) {
                qd.setDisti_branch(disti_branch.toUpperCase());
            }
            if (quote_id != null && (!("".equals(quote_id)))) {
                qd.setQuote_id(quote_id.toUpperCase());
            }
            if (project_name != null && (!("".equals(project_name)))) {
                qd.setProject_name(project_name.toUpperCase());
            }
            if (endCustomer_name != null && (!("".equals(endCustomer_name)))) {
                qd.setEndCustomer_name(endCustomer_name.toUpperCase());

            }
            if (purchaseCustomer_name != null && (!("".equals(purchaseCustomer_name)))) {
                qd.setPurchaseCustomer_name(purchaseCustomer_name.toUpperCase());

            }
            if (material_name != null && (!("".equals(material_name)))) {
                qd.setMaterial_name(material_name.toUpperCase());

            }
            if (create_userName != null && (!("".equals(create_userName)))) {
                qd.setCreate_userName(create_userName.toUpperCase());
            }
            qdList = quoteService.outPutQuote(qd);
            for (QuoteDetail qdt : qdList) {
                qdt.setQty(qdt.getQty() * 1);
                pos = new Pos();
                pos.setDebit_number(qdt.getQuote_id());
                pos.setBook_part(qdt.getMaterial_name());
                double passQty = posService.getPassedQty(pos);
                qdt.setRes_qty(qdt.getQty() - passQty);
            }

            for (int i = 0; i < qdList.size(); i++) {
                QuoteDetail qd = qdList.get(i);
                label = new Label(0, i + 1, transState(qd.getState()));
                label2 = new Label(1, i + 1, transIsAgree(qd.getIsAgree()));
                label3 = new Label(2, i + 1, qd.getQuote_id());
                label4 = new Label(3, i + 1, qd.getMaterial_id());
                label5 = new Label(4, i + 1, qd.getMaterial_name());
                label6 = new Label(5, i + 1, transNullString(qd.getDrNum()));
                label7 = new Label(6, i + 1, qd.getProject_name());
                label8 = new Label(7, i + 1, qd.getCusGroup_id());
                label9 = new Label(8, i + 1, qd.getDisti_branch());
                label10 = new Label(9, i + 1, qd.getCurrency_code());
                label11 = new Label(10, i + 1, String.valueOf(qd.getQty()));
                label12 = new Label(11, i + 1, String.valueOf(qd.getTarget_cost()));
                label13 = new Label(12, i + 1, String.valueOf(qd.getTarget_resale()));
                label14 = new Label(13, i + 1, transNullString(qd.getPbMpp()));//
                label15 = new Label(14, i + 1, transNullString(qd.getSale_price()));//
                label16 = new Label(15, i + 1, transNullString(qd.getStop_price()));//
                label17 = new Label(16, i + 1, String.valueOf(qd.getSuggest_cost()));
                label18 = new Label(17, i + 1, String.valueOf(qd.getSuggest_resale()));
                label19 = new Label(18, i + 1, transPersent(qd.getCus_profits_percent()));
                label20 = new Label(19, i + 1, transPersent(qd.getProfits_percent()));
                label21 = new Label(20, i + 1, String.valueOf(qd.getAmount()));
                label22 = new Label(21, i + 1, transNullString(qd.getReason()));
                label23 = new Label(22, i + 1, transNullString(qd.getCompetitor()));
                label24 = new Label(23, i + 1, qd.getStart_dateStr());
                label25 = new Label(24, i + 1, qd.getEnd_dateStr());
                label26 = new Label(25, i + 1, qd.getProduct_dateStr());
                label27 = new Label(26, i + 1, transNullString(qd.getPcGroup_name()));
                label28 = new Label(27, i + 1, qd.getPurchaseCustomer_name());
                label29 = new Label(28, i + 1, qd.getPc_city());
                label30 = new Label(29, i + 1, transNullString(qd.getEcGroup_name()));
                label31 = new Label(30, i + 1, qd.getEndCustomer_name());
                label32 = new Label(31, i + 1, qd.getEc_city());
                label33 = new Label(32, i + 1, transNullString(qd.getPrice_region()));
                label34 = new Label(33, i + 1, transNullString(qd.getDisti_region()));
                label35 = new Label(34, i + 1, transNullString(qd.getPc_region()));
                label36 = new Label(35, i + 1, transNullString(qd.getEc_region()));
                label37 = new Label(36, i + 1, transNullString(qd.getCus_remark()));
                label38 = new Label(37, i + 1, transNullString(qd.getRemark()));
                label39 = new Label(38, i + 1, transDate(qd.getCreate_time()));
                label40 = new Label(39, i + 1, transNullString(qd.getLatest_userId()));
                label41 = new Label(40, i + 1, transDate(qd.getLatest_time()));
                sheet.addCell(label);
                sheet.setColumnView(0, 30);
                sheet.addCell(label2);
                sheet.setColumnView(1, 15);
                sheet.addCell(label3);
                sheet.setColumnView(2, 15);
                sheet.addCell(label4);
                sheet.setColumnView(3, 30);
                sheet.addCell(label5);
                sheet.setColumnView(4, 60);
                sheet.addCell(label6);
                sheet.setColumnView(5, 15);
                sheet.addCell(label7);
                sheet.setColumnView(6, 15);
                sheet.addCell(label8);
                sheet.setColumnView(7, 30);
                sheet.addCell(label9);
                sheet.setColumnView(8, 60);
                sheet.addCell(label10);
                sheet.setColumnView(9, 15);
                sheet.addCell(label11);
                sheet.setColumnView(10, 15);
                sheet.addCell(label12);
                sheet.setColumnView(11, 15);
                sheet.addCell(label13);
                sheet.setColumnView(12, 15);
                sheet.addCell(label14);
                sheet.setColumnView(13, 15);
                sheet.addCell(label15);
                sheet.setColumnView(14, 15);
                sheet.addCell(label16);
                sheet.setColumnView(15, 15);
                sheet.addCell(label17);
                sheet.setColumnView(16, 15);
                sheet.addCell(label18);
                sheet.setColumnView(17, 15);
                sheet.addCell(label19);
                sheet.setColumnView(18, 15);
                sheet.addCell(label20);
                sheet.setColumnView(19, 15);
                sheet.addCell(label21);
                sheet.setColumnView(20, 30);
                sheet.addCell(label22);
                sheet.setColumnView(21, 30);
                sheet.addCell(label23);
                sheet.setColumnView(22, 30);
                sheet.addCell(label24);
                sheet.setColumnView(23, 30);
                sheet.addCell(label25);
                sheet.setColumnView(24, 30);
                sheet.addCell(label26);
                sheet.setColumnView(25, 30);
                sheet.addCell(label27);
                sheet.setColumnView(26, 30);
                sheet.addCell(label28);
                sheet.setColumnView(27, 30);
                sheet.addCell(label29);
                sheet.setColumnView(28, 30);
                sheet.addCell(label30);
                sheet.setColumnView(29, 30);
                sheet.addCell(label31);
                sheet.setColumnView(30, 30);
                sheet.addCell(label32);
                sheet.setColumnView(31, 30);
                sheet.addCell(label33);
                sheet.setColumnView(32, 30);
                sheet.addCell(label34);
                sheet.setColumnView(33, 30);
                sheet.addCell(label35);
                sheet.setColumnView(34, 30);
                sheet.addCell(label36);
                sheet.setColumnView(35, 30);
                sheet.addCell(label37);
                sheet.setColumnView(36, 30);
                sheet.addCell(label38);
                sheet.setColumnView(37, 30);
                sheet.addCell(label39);
                sheet.setColumnView(38, 30);
                sheet.addCell(label40);
                sheet.setColumnView(39, 30);
                sheet.addCell(label41);
                sheet.setColumnView(40, 30);
            }
            wwb.write();
            wwb.close();

            display(source, "Quote.xls", ServletActionContext.getResponse());
        } catch (Exception e) {
            e.printStackTrace();
            this.setFailMessage("Quote数据导出出错 !");
        }
    }

    public void distiDownloadExcelModel() {
        try {

            List<String> list = new ArrayList<String>();
            list.add("Status");
            list.add("IsAgree");
            list.add("QuoteNum");
            list.add("12NC");
            list.add("BookPart");
            list.add("DRNum");
            list.add("Project");
            list.add("Disti");
            list.add("Disti Branch");
            list.add("Currency");
            list.add("QTY");
            list.add("Target Cost");
            list.add("Target Resale");
            list.add("Suggest Cost");
            list.add("Suggest Resale");
            list.add("DistiMargin");
            list.add("Value");
            list.add("Justification");
            list.add("Competitor");
            list.add("StartDate");
            list.add("EndDate");
            list.add("Start of Production");
            list.add("PC Group");
            list.add("PC");
            list.add("PC Location");
            list.add("EC Group");
            list.add("EC");
            list.add("EC Location");
            list.add("CusRemark");
            list.add("Create Time");
            list.add("Updater");
            list.add("Update Time");
            File source = new File("Quote.xls");
            WritableWorkbook wwb = Workbook.createWorkbook(source);
            WritableSheet sheet = wwb.createSheet("Quote", 0);
            Label label = null;
            Label label2 = null;
            Label label3 = null;
            Label label4 = null;
            Label label5 = null;
            Label label6 = null;
            Label label7 = null;
            Label label8 = null;
            Label label9 = null;
            Label label10 = null;
            Label label11 = null;
            Label label12 = null;
            Label label13 = null;
            Label label14 = null;
            Label label15 = null;
            Label label16 = null;
            Label label17 = null;
            Label label18 = null;
            Label label19 = null;
            Label label20 = null;
            Label label21 = null;
            Label label22 = null;
            Label label23 = null;
            Label label24 = null;
            Label label25 = null;
            Label label26 = null;
            Label label27 = null;
            Label label28 = null;
            Label label29 = null;
            Label label30 = null;
            Label label31 = null;
            Label label32 = null;
            WritableFont font1 = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false,
                    UnderlineStyle.NO_UNDERLINE, Colour.RED);

            WritableCellFormat cellFormat1 = new WritableCellFormat(font1);
            cellFormat1.setBackground(Colour.YELLOW);
            cellFormat1.setBorder(Border.ALL, BorderLineStyle.HAIR);
            for (int i = 0; i < list.size(); i++) {
                label = new Label(i, 0, list.get(i).toString(), cellFormat1);
                sheet.addCell(label);
            }
            List<QuoteDetail> qdList = new ArrayList<QuoteDetail>();
            qd = new QuoteDetail();
            qd.setStates(states);
            qd.setIsAgrees(isAgrees);
            qd.setCustomer_id(customer_id);
            qd.setMaterial_id(material_id);
            qd.setStart_dateStr(start_dateStr);
            qd.setEnd_dateStr(end_dateStr);
            if (cusGroup_id != null && (!("".equals(cusGroup_id)))) {
                qd.setCusGroup_id(cusGroup_id.toUpperCase());
            }
            String userId = this.getUser().getUserId();
            String userName = this.getUser().getUserName();
            Role r = new Role();
            r.setStart(0);
            r.setEnd(100);
            r.setEmp_code(userId);
            roleList = roleService.getSelectedRole4StationList(r);
            for (Role rl : roleList) {
                if ("HK10_H_Sale_Mgmt".equals(rl.getRoleId())) {
                    qd.setAuditorId(userId);
                }
            }
            String orgString = this.getUser().getOrgId();
            if (orgString == null || "".equals(orgString)) {
                qd.setCusGroup_id(userName.toUpperCase());
            }
            if (myself != null && (!("".equals(myself)))) {
                qd.setCreate_userId(userId);
            }
            if (disti_branch != null && (!("".equals(disti_branch)))) {
                qd.setDisti_branch(disti_branch.toUpperCase());
            }
            if (quote_id != null && (!("".equals(quote_id)))) {
                qd.setQuote_id(quote_id.toUpperCase());
            }
            if (project_name != null && (!("".equals(project_name)))) {
                qd.setProject_name(project_name.toUpperCase());
            }
            if (endCustomer_name != null && (!("".equals(endCustomer_name)))) {
                qd.setEndCustomer_name(endCustomer_name.toUpperCase());

            }
            if (purchaseCustomer_name != null && (!("".equals(purchaseCustomer_name)))) {
                qd.setPurchaseCustomer_name(purchaseCustomer_name.toUpperCase());

            }
            if (material_name != null && (!("".equals(material_name)))) {
                qd.setMaterial_name(material_name.toUpperCase());

            }

            qdList = quoteService.outPutQuote(qd);
            for (QuoteDetail qdt : qdList) {
                double rm = quoteService.getReginalMin(qdt);
                double cm = quoteService.getCMM(qdt);
                qdt.setSale_price(String.valueOf(rm));
                qdt.setStop_price(String.valueOf(cm));
                pos = new Pos();
                pos.setDebit_number(qdt.getQuote_id());
                pos.setBook_part(qdt.getMaterial_name());
                double passQty = posService.getPassedQty(pos);
                qdt.setRes_qty(qdt.getQty() - passQty);
            }

            for (int i = 0; i < qdList.size(); i++) {
                QuoteDetail qd = qdList.get(i);
                label = new Label(0, i + 1, transState(qd.getState()));
                label2 = new Label(1, i + 1, transIsAgree(qd.getIsAgree()));
                label3 = new Label(2, i + 1, qd.getQuote_id());
                label4 = new Label(3, i + 1, qd.getMaterial_id());
                label5 = new Label(4, i + 1, qd.getMaterial_name());
                label6 = new Label(5, i + 1, transNullString(qd.getDrNum()));
                label7 = new Label(6, i + 1, qd.getProject_name());
                label8 = new Label(7, i + 1, qd.getCusGroup_id());
                label9 = new Label(8, i + 1, qd.getDisti_branch());
                label10 = new Label(9, i + 1, qd.getCurrency_code());
                String ss = "";
                DecimalFormat d1 = new DecimalFormat("##,###,###,###.0");
                ss = String.valueOf(d1.format(qd.getQty()));

                label11 = new Label(10, i + 1, ss);
                label12 = new Label(11, i + 1, String.valueOf(qd.getTarget_cost()));
                label13 = new Label(12, i + 1, String.valueOf(qd.getTarget_resale()));
                label14 = new Label(13, i + 1, String.valueOf(qd.getSuggest_cost()));
                label15 = new Label(14, i + 1, String.valueOf(qd.getSuggest_resale()));
                label16 = new Label(15, i + 1, transPersent(qd.getCus_profits_percent()));
                String ss1 = "";
                ss1 = String.valueOf(d1.format(qd.getAmount()));
                label17 = new Label(16, i + 1, ss1);
                label18 = new Label(17, i + 1, transNullString(qd.getReason()));
                label19 = new Label(18, i + 1, transNullString(qd.getCompetitor()));
                label20 = new Label(19, i + 1, qd.getStart_dateStr());
                label21 = new Label(20, i + 1, qd.getEnd_dateStr());
                label22 = new Label(21, i + 1, qd.getProduct_dateStr());
                label23 = new Label(22, i + 1, transNullString(qd.getPcGroup_id()));
                label24 = new Label(23, i + 1, qd.getPurchaseCustomer_name());
                label25 = new Label(24, i + 1, transNullString(qd.getPc_city()));
                label26 = new Label(25, i + 1, transNullString(qd.getEcGroup_id()));
                label27 = new Label(26, i + 1, qd.getEndCustomer_name());
                label28 = new Label(27, i + 1, transNullString(qd.getEc_city()));
                label29 = new Label(28, i + 1, transNullString(qd.getCus_remark()));
                label30 = new Label(29, i + 1, transDate(qd.getCreate_time()));
                label31 = new Label(30, i + 1, transNullString(qd.getLatest_userId()));
                label32 = new Label(31, i + 1, transDate(qd.getLatest_time()));

                sheet.addCell(label);
                sheet.setColumnView(0, 30);
                sheet.addCell(label2);
                sheet.setColumnView(1, 15);
                sheet.addCell(label3);
                sheet.setColumnView(2, 15);
                sheet.addCell(label4);
                sheet.setColumnView(3, 30);
                sheet.addCell(label5);
                sheet.setColumnView(4, 60);
                sheet.addCell(label6);
                sheet.setColumnView(5, 15);
                sheet.addCell(label7);
                sheet.setColumnView(6, 15);
                sheet.addCell(label8);
                sheet.setColumnView(7, 30);
                sheet.addCell(label9);
                sheet.setColumnView(8, 60);
                sheet.addCell(label10);
                sheet.setColumnView(9, 15);
                sheet.addCell(label11);
                sheet.setColumnView(10, 15);
                sheet.addCell(label12);
                sheet.setColumnView(11, 15);
                sheet.addCell(label13);
                sheet.setColumnView(12, 15);
                sheet.addCell(label14);
                sheet.setColumnView(13, 15);
                sheet.addCell(label15);
                sheet.setColumnView(14, 15);
                sheet.addCell(label16);
                sheet.setColumnView(15, 15);
                sheet.addCell(label17);
                sheet.setColumnView(16, 15);
                sheet.addCell(label18);
                sheet.setColumnView(17, 15);
                sheet.addCell(label19);
                sheet.setColumnView(18, 15);
                sheet.addCell(label20);
                sheet.setColumnView(19, 15);
                sheet.addCell(label21);
                sheet.setColumnView(20, 30);
                sheet.addCell(label22);
                sheet.setColumnView(21, 30);
                sheet.addCell(label23);
                sheet.setColumnView(22, 30);
                sheet.addCell(label24);
                sheet.setColumnView(23, 30);
                sheet.addCell(label25);
                sheet.setColumnView(24, 30);
                sheet.addCell(label26);
                sheet.setColumnView(25, 30);
                sheet.addCell(label27);
                sheet.setColumnView(26, 30);
                sheet.addCell(label28);
                sheet.setColumnView(27, 30);
                sheet.addCell(label29);
                sheet.setColumnView(28, 30);
                sheet.addCell(label30);
                sheet.setColumnView(29, 30);
                sheet.addCell(label31);
                sheet.setColumnView(30, 30);
                sheet.addCell(label32);
                sheet.setColumnView(31, 30);
            }
            wwb.write();
            wwb.close();

            display(source, "Quote.xls", ServletActionContext.getResponse());

        } catch (Exception e) {
            e.printStackTrace();
            this.setFailMessage("Quote Data outport Error !");
        }
    }

    private boolean display(File file, String fileName, HttpServletResponse response) {
        System.out.println(file);
        FileInputStream in = null;
        OutputStream out = null;
        try {
            fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment;filename=\"" + fileName);
            in = new FileInputStream(file);
            out = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            response.flushBuffer();
        } catch (Exception ex) {
            return false;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (final Exception e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (final Exception e) {
                }
            }
        }
        return true;
    }

    public void downloadQuoteList() throws Exception {
        try {
            super.getSession().setAttribute("exportedFlag", "false");
            this.setPage(1);
            this.setRows(1000000);

            getOutPortQuoteList();

            Date aDate = new Date();
            SimpleDateFormat aDateFormat = new SimpleDateFormat("yyyyMMdd");
            String dateString = aDateFormat.format(aDate);
            String fileName = "Quote" + dateString;

            CommonUtil.setExcelResponseInfo(getServletRequest(), getServletResponse(), fileName);
            String orgString = this.getUser().getOrgId();
            String roleid = this.getUser().getRoleIds();
            String[] titles;
            String[] keys;

            if (orgString == null || "".equals(orgString)) {
                titles = new String[] { "Agreement", "Quote status", "Quote Num", "Debit Num", "12NC", "BookPart",
                        "DR Number", "QTY", "Remain QTY", "Target Cost ", "Target Resale", "Target Margin", "Disti",
                        "Disti Branch", "Purchasing Customer", "PC LOCATION", "End Customer", "EC LOCATION", "Project",
                        "Currency", "Final Quoted Cost", "Final Quoted Resale", "Final Quoted Disti Margin",
                        "Final Quoted Amount", "Create Date", "Effective Date", "Expire Date", "Debit Start",
                        "Debit End", " Comments", "Represent", "Competitor", "Justification", "Start of Production",
                        "Customer Type","Segment","Application","Purchase Customer Type","Purchase Customer Segment","Purchase Customer Application"};
                
                keys = new String[] { "isAgreeStr", "stateStr", "quote_id", "debit_num", "material_id", "material_name",
                        "drNum", "qty", "res_qty", "target_cost", "target_resale", "target_margin", "cusGroup_id",
                        "disti_branch", "purchaseCustomer_name", "pc_city", "endCustomer_name", "ec_city",
                        "project_name", "currency_code", "suggest_cost", "suggest_resale", "cus_profits_percent",
                        "amount", "create_timeStr", "start_dateStr", "end_dateStr", "debit_startStr", "debit_endStr",
                        "cus_remark", "isRepresent", "competitor", "reason", "product_dateStr",
                        "customertypename","segmentname","applicationname","customertypenamepur","segmentnamepur","applicationnamepur" };

            } else {
                // excel表头HK10_H_Sale_Mgmt
                if ("HK10_H_Sale_Mgmt".equals(roleid)) {

                    titles = new String[] { "Agreement", "Quote status", "Quote Num", "Debit Num", "12NC", "BookPart",
                            "DR Number", "QTY", "Remain QTY", "Target Cost ", "Target Resale", "Target Margin",
                            "Regional Min", "Price Region", "Disti", "Disti Branch", "Disti Region",
                            "Purchasing Customer", "PC Region", "PC LOCATION", "End Customer", "EC Region",
                            "EC LOCATION", "Project", "Currency", "PB/MPP", "Final Quoted Cost", "Final Quoted Resale",
                            "Final Quoted Disti Margin", "Final Quoted Amount", "Create Date", "Effective Date",
                            "Expire Date", "Internal Comments", "Debit Start", "Debit End", " Comments", "Represent",
                            "Competitor", "Justification", "Start of Production",
                            "Customer Type","Segment","Application","Purchase Customer Type","Purchase Customer Segment","Purchase Customer Application" };

                    keys = new String[] { "isAgreeStr", "stateStr", "quote_id", "debit_num", "material_id",
                            "material_name", "drNum", "qty", "res_qty", "target_cost", "target_resale", "target_margin",
                            "sale_price", "price_region", "cusGroup_id", "disti_branch", "disti_region",
                            "purchaseCustomer_name", "pc_region", "pc_city", "endCustomer_name", "ec_region", "ec_city",
                            "project_name", "currency_code", "pbMpp", "suggest_cost", "suggest_resale",
                            "cus_profits_percent", "amount", "create_timeStr", "start_dateStr", "end_dateStr", "remark",
                            "debit_startStr", "debit_endStr", "cus_remark", "isRepresent", "competitor", "reason","product_dateStr",
                            "customertypename","segmentname","applicationname","customertypenamepur","segmentnamepur","applicationnamepur" };

                } else {

                    if ("1".equals(pending_approver)) {
                        titles = new String[] { "Agreement", "Quote status", "Quote Num", "Debit Num", "12NC",
                                "BookPart", "DR Number", "QTY", "Remain QTY", "Target Cost ", "Target Resale",
                                "Target Margin", "Regional Min ", "CMM", "Price Region", "Disti", "Disti Branch",
                                "Disti Region", "Purchasing Customer", "PC Country", "PC State", "PC Region",
                                "PC LOCATION", "End Customer", "EC Region", "EC LOCATION", "Project", "Currency",
                                "PB/MPP", "Final Quoted Cost", "Final Quoted Resale", "Final Quoted Disti Margin",
                                "Mfr Margin", "Final Quoted Amount", "Create Date", "Effective Date", "Expire Date",
                                "Internal Comments", "Debit Start", "Debit End", " Comments", "Represent", "Competitor",
                                "Justification", "Start of Production",
                                "Customer Type","Segment","Application","Purchase Customer Type","Purchase Customer Segment","Purchase Customer Application" };

                        keys = new String[] { "isAgreeStr", "stateStr", "quote_id", "debit_num", "material_id",
                                "material_name", "drNum", "qty", "res_qty", "target_cost", "target_resale",
                                "target_margin", "sale_price", "stop_price", "price_region", "cusGroup_id",
                                "disti_branch", "disti_region", "purchaseCustomer_name", "pc_country", "pc_state",
                                "pc_region", "pc_city", "endCustomer_name", "ec_region", "ec_city", "project_name",
                                "currency_code", "pbMpp", "suggest_cost", "suggest_resale", "cus_profits_percent",
                                "profits_percent", "amount", "create_timeStr", "start_dateStr", "end_dateStr", "remark",
                                "debit_startStr", "debit_endStr", "cus_remark", "isRepresent", "competitor", "reason","product_dateStr",
                                "customertypename","segmentname","applicationname","customertypenamepur","segmentnamepur","applicationnamepur" };
                    } else {
                        roleControl = "0";

                        Role r = new Role();
                        r.setStart(0);
                        r.setEnd(100);
                        r.setEmp_code(this.getUser().getUserId());
                        roleList = roleService.getSelectedRole4StationList(r);
                        for (Role rl : roleList) {
                            if ("HK10_H_Marketing_Mgmt".equals(rl.getRoleId())) {
                                roleControl = "1";
                                break;
                            } else if ("HK10_H_BL_Mgmt".equals(rl.getRoleId())) {
                                roleControl = "1";
                                break;
                            } else if ("Quote_Center".equals(rl.getRoleId())) {
                                roleControl = "1";
                                break;
                            }
                        }
                        if ("0".equals(roleControl)) {
                            titles = new String[] { "Agreement", "Quote status", "Quote Num", "Debit Num", "12NC",
                                    "BookPart", "DR Indicator", "DR Number", "QTY", "Remain QTY", "Target Cost ",
                                    "Target Resale", "Target Margin", "Regional Min ", "Price Region", "Disti",
                                    "Disti Branch", "Disti Region", "Purchasing Customer", "PC Region", "PC LOCATION",
                                    "PC Zip Code", "PC State", "End Customer", "EC Region", "EC LOCATION",
                                    "EC Zip Code", "EC State", "Project", "Currency", "PB/MPP", "Final Quoted Cost",
                                    "Final Quoted Resale", "Final Quoted Disti Margin", "Final Quoted Amount",
                                    "Create Date", "Effective Date", "Expire Date", "Internal Comments", "Debit Start",
                                    "Debit End", " Comments", "Represent", "Competitor", "Justification",
                                    "Start of Production",
                                    "Customer Type","Segment","Application","Purchase Customer Type","Purchase Customer Segment","Purchase Customer Application" };

                            keys = new String[] { "isAgreeStr", "stateStr", "quote_id", "debit_num", "material_id",
                                    "material_name", "isDRItem", "drNum", "qty", "res_qty", "target_cost",
                                    "target_resale", "target_margin", "sale_price", "price_region", "cusGroup_id",
                                    "disti_branch", "disti_region", "purchaseCustomer_name", "pc_region", "pc_city",
                                    "pc_zip_code", "pc_state", "endCustomer_name", "ec_region", "ec_city",
                                    "ec_zip_code", "ec_state", "project_name", "currency_code", "pbMpp", "suggest_cost",
                                    "suggest_resale", "cus_profits_percent", "amount", "create_timeStr",
                                    "start_dateStr", "end_dateStr", "remark", "debit_startStr", "debit_endStr",
                                    "cus_remark", "isRepresent", "competitor", "reason", "product_dateStr",
                                    "customertypename","segmentname","applicationname","customertypenamepur","segmentnamepur","applicationnamepur" };

                        } else {
                            titles = new String[] { "Agreement", "Quote status", "Quote Num", "Debit Num", "12NC",
                                    "BookPart", "DR Indicator", "DR Number", "QTY", "Remain QTY", "Target Cost ",
                                    "Target Resale", "Target Margin", "Regional Min ", "CMM", "Price Region", "Disti",
                                    "Disti Branch", "Disti Region", "Purchasing Customer", "PC Region", "PC LOCATION",
                                    "PC Zip Code", "PC State", "End Customer", "EC Region", "EC LOCATION",
                                    "EC Zip Code", "EC State", "Project", "Currency", "PB/MPP", "Final Quoted Cost",
                                    "Final Quoted Resale", "Final Quoted Disti Margin", "Mfr Margin",
                                    "Final Quoted Amount", "Create Date", "Effective Date", "Expire Date",
                                    "Internal Comments", "Debit Start", "Debit End", " Comments", "Represent",
                                    "Competitor", "Justification", "Start of Production",
                                    "Customer Type","Segment","Application","Purchase Customer Type","Purchase Customer Segment","Purchase Customer Application" };

                            keys = new String[] { "isAgreeStr", "stateStr", "quote_id", "debit_num", "material_id",
                                    "material_name", "isDRItem", "drNum", "qty", "res_qty", "target_cost",
                                    "target_resale", "target_margin", "sale_price", "stop_price", "price_region",
                                    "cusGroup_id", "disti_branch", "disti_region", "purchaseCustomer_name", "pc_region",
                                    "pc_city", "pc_zip_code", "pc_state", "endCustomer_name", "ec_region", "ec_city",
                                    "ec_zip_code", "ec_state", "project_name", "currency_code", "pbMpp", "suggest_cost",
                                    "suggest_resale", "cus_profits_percent", "profits_percent", "amount",
                                    "create_timeStr", "start_dateStr", "end_dateStr", "remark", "debit_startStr",
                                    "debit_endStr", "cus_remark", "isRepresent", "competitor", "reason","product_dateStr",
                                    "customertypename","segmentname","applicationname","customertypenamepur","segmentnamepur","applicationnamepur" };

                        }
                    }
                }
            }

            List<Map<String, String>> li = new ArrayList<Map<String, String>>();

            for (QuoteDetail qd : qdList) {
                qd.setStateStr(transState(qd.getState()));
                qd.setIsAgreeStr(transIsAgree(qd.getIsAgree()));
                qd.setPbMpp(transNullString(qd.getPbMpp()));
                qd.setIsRepresent(transIsRepresent(qd.getIsRepresent()));
                qd.setTarget_margin(transIsFinalMargin(qd.getTarget_margin()));
                qd.setCus_profits_percent(transIsFinalMargin(qd.getCus_profits_percent()));
                qd.setProfits_percent(transIsFinalMargin(qd.getProfits_percent()));
                li.add(CommonUtil.transBean2Map(qd));
            }
            // System.out.println(System.currentTimeMillis());
            CommonUtil.exportExcel(li, titles, keys, getServletResponse().getOutputStream());
            // System.out.println(System.currentTimeMillis());
            super.getSession().removeAttribute("exportedFlag");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void isExport() {
        Object exportedFlag = super.getSession().getAttribute("exportedFlag");
        if (exportedFlag == null) {
            responseToAjax("true");
        } else {
            responseToAjax("false");
        }
    }

    public void responseToAjax(String jsonStr) {
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String transState(int flag) {
        if (flag == 9) {
            return "Deleted";
        } else if (flag == 0 || flag == 1 || flag == 2) {
            return "Pending";
        } else if (flag == 3 || flag == 4 || flag == 5) {
            return "Approved";
        } else if (flag == 6 || flag == 7 || flag == 8) {
            return "Reject";
        } else {
            return String.valueOf(flag);
        }
    }

    public String transIsAgree(int flag) {
        if (flag == 2) {
            return "Expired";
        } else if (flag == 1) {
            return "Agree";
        } else {
            return "";
        }
    }

    // Final Quoted Disti Margin
    public String transIsFinalMargin(String flag) {
        if (flag == null)
            return "";

        if ("undefined".equals(flag)) {
            return "";
        } else {
            return flag + "%";
        }
    }

    public String transIsRepresent(String flag) {
        if (flag == null)
            return "";

        if ("Represent".equals(flag) || "represent".equals(flag))
            return "Y";
        else if ("undefined".equals(flag)) {
            return "";
        } else {
            return flag;
        }

    }

    public String transDate(Date d) {
        if (d != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(d);
        } else {
            return "";
        }
    }

    public String transNullString(String s) {
        String str = "";
        if (s == null || "undefined".equals(s) || "".equals(s)) {
            str = "";
        } else {
            if (s.indexOf(".") != -1 && s.indexOf(".") == 0) {
                str = "0" + s;
            } else {
                str = s;
            }
        }
        if (str.indexOf("<br>") != -1) {
            str = str.replaceAll("<br>", "  ");
        }
        return str;
    }

    public String transPersent(String s) {
        if (s == null || "undefined".equals(s) || "".equals(s)) {
            return "";
        } else {
            return s + "%";
        }
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

    public List<Quote> getqList() {
        return qList;
    }

    public void setqList(List<Quote> qList) {
        this.qList = qList;
    }

    public QuoteDetail getQd() {
        return qd;
    }

    public void setQd(QuoteDetail qd) {
        this.qd = qd;
    }

    public List<QuoteDetail> getQdList() {
        return qdList;
    }

    public void setQdList(List<QuoteDetail> qdList) {
        this.qdList = qdList;
    }

    public List<QuoteDetail> getEdList() {
        return qdList;
    }

    public void setEdList(List<QuoteDetail> qdList) {
        this.qdList = qdList;
    }

    public String getQuoteDetailJson() {
        return quoteDetailJson;
    }

    public void setQuoteDetailJson(String quoteDetailJson) {
        this.quoteDetailJson = quoteDetailJson;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDelQuoteDetail() {
        return delQuoteDetail;
    }

    public void setDelQuoteDetail(String delQuoteDetail) {
        this.delQuoteDetail = delQuoteDetail;
    }

    public String getQuote_id() {
        return quote_id;
    }

    public void setQuote_id(String quote_id) {
        this.quote_id = quote_id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getQdState() {
        return qdState;
    }

    public void setQdState(String qdState) {
        this.qdState = qdState;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
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

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getEndCustomer_id() {
        return endCustomer_id;
    }

    public void setEndCustomer_id(String endCustomer_id) {
        this.endCustomer_id = endCustomer_id;
    }

    public String getPurchaseCustomer_id() {
        return purchaseCustomer_id;
    }

    public void setPurchaseCustomer_id(String purchaseCustomer_id) {
        this.purchaseCustomer_id = purchaseCustomer_id;
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

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public IAllUserService getAllUserService() {
        return allUserService;
    }

    public void setAllUserService(IAllUserService allUserService) {
        this.allUserService = allUserService;
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

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getStart_dateStr() {
        return start_dateStr;
    }

    public void setStart_dateStr(String start_dateStr) {
        this.start_dateStr = start_dateStr;
    }

    public String getEnd_dateStr() {
        return end_dateStr;
    }

    public void setEnd_dateStr(String end_dateStr) {
        this.end_dateStr = end_dateStr;
    }

    public String getIsAgrees() {
        return isAgrees;
    }

    public void setIsAgrees(String isAgrees) {
        this.isAgrees = isAgrees;
    }

    @PermissionSearch
    @JsonResult(field = "qdList", include = { "customer_id", "disti_branch", "cusGroup_id", "ecGroup_id", "isDelivery",
            "currency_code", "purchaseCustomer_id", "purchaseCustomer_name", "endCustomer_id", "project_name",
            "customer_name", "endCustomer_name", "ecGroup_name", "pcGroup_name", "latest_userId", "latest_time", "id",
            "main_id", "quote_id", "row_no", "material_id", "drNum", "qty", "isAgree", "isRepresent", "target_resale",
            "target_cost", "target_amount", "amount", "reason", "competitor", "res_qty", "create_userId",
            "product_date", "product_dateStr", "start_date", "start_dateStr", "end_date", "end_dateStr", "cus_remark",
            "suggest_resale", "suggest_cost", "material_name", "cus_profits_percent", "profits_percent", "remark",
            "state", "target_margin", "data_from", "debit_start", "debit_end", "debit_num", "sale_price", "stop_price",
            "pbMpp", "cost", "price_region", "disti_region", "pc_region", "ec_region", "qm_price", "forward_id",
            "isDRItem" })
    public String getAuditQuoteListNoPage() {
        qd = new QuoteDetail();
        qd.setSort("aa.id");
        qd.setDir("desc");
        qd.setQuote_id(quote_id);
        if (debit_num != null && (!("".equals(debit_num)))) {
            qd.setDebit_num(debit_num.toUpperCase());
        }
        qd.setStates(states);
        qd.setProject_name(project_name);
        qd.setCustomer_id(customer_id);
        qd.setEndCustomer_id(endCustomer_id);
        qd.setPurchaseCustomer_id(purchaseCustomer_id);
        if (cusGroup_id != null && (!("".equals(cusGroup_id)))) {
            qd.setCusGroup_id(cusGroup_id.toUpperCase());

        }
        String userId = this.getUser().getUserId();
        String userName = this.getUser().getUserName();
        Role r = new Role();
        r.setStart(0);
        r.setEnd(100);
        r.setEmp_code(userId);
        roleList = roleService.getSelectedRole4StationList(r);
        for (Role rl : roleList) {
            if ("HK10_H_Sale_Mgmt".equals(rl.getRoleId())) {
                qd.setAuditorId(userId);
            }
        }
        String orgString = this.getUser().getOrgId();
        if (orgString == null || "".equals(orgString)) {
            qd.setCusGroup_id(userName.toUpperCase());
        }
        if (disti_branch != null && (!("".equals(disti_branch)))) {
            qd.setDisti_branch(disti_branch.toUpperCase());
        }
        qd.setMaterial_id(material_id);
        qd.setMaterial_name(material_name);
        qd.setStart_dateStr(start_dateStr);
        qd.setEnd_dateStr(end_dateStr);
        AllUsers allUsers = this.getUser();
        String loginId = allUsers.getLoginId();
        if (!"admin".equals(loginId)) {
            String org = allUsers.getOrgId();
            qd.setSalesOrg(org);
        }
        qdList = quoteService.getAuditQuoteListNoPage(qd);
        for (QuoteDetail qdt : qdList) {
            double rm = quoteService.getReginalMin(qdt);
            double cm = quoteService.getCMM(qdt);
            qdt.setSale_price(String.valueOf(rm));
            qdt.setStop_price(String.valueOf(cm));

            pos = new Pos();
            pos.setDebit_number(qdt.getQuote_id());
            pos.setBook_part(qdt.getMaterial_name());
            double passQty = posService.getPassedQty(pos);
            qdt.setRes_qty(qdt.getQty() - passQty);

            double resale = qdt.getTarget_resale();
            double cost = qdt.getTarget_cost();
            double mCost = Double.valueOf(qdt.getCost());
            double num = qdt.getQty();
            qdt.setTarget_amount(Math.round((cost * num) * 10000) / 10000.0000);
            if ("EUR".equals(qdt.getCurrency_code())) {
                mCost = Math.round((mCost * qdt.getRate()) * 10000) / 10000.0000;
            }
            qdt.setCost(String.valueOf(mCost));
            if (resale == 0 || (resale - cost) == 0) {
                qdt.setTarget_margin("0.00");
            } else {
                DecimalFormat formater = new DecimalFormat("#0.##");
                String targetMargin = formater.format(((resale - cost) / resale) * 10000 / 100.0000);
                qdt.setTarget_margin(targetMargin);
            }
        }
        return JSON;
    }

    public String importExcel() {
        return "importExcel";
    }

    /**
     * 2007 + Excel
     */
    private String findOrderExcelXlsx(String path, Product pr) {
        StringBuilder contentResult = new StringBuilder();
        List<QuoteDetail> qdList = new ArrayList<QuoteDetail>();
        try {

            FileInputStream file = new FileInputStream(path);
            XSSFWorkbook xwb = new XSSFWorkbook(file);
            XSSFSheet sheet = xwb.getSheetAt(0);
            XSSFRow row;
            if (sheet.getPhysicalNumberOfRows() <= 1) {
                return "The Excel  is not completed yet !";
            }
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                if (row == null) {// 空行跳过
                    continue;
                }
                qd = new QuoteDetail();
                XSSFCell cell0 = row.getCell(0);
                if (cell0 != null) {
                    if ("".equals(cell0.getStringCellValue())) {
                        continue;
                    }
                    qd.setMaterial_name(cell0.getStringCellValue().trim());
                } else {
                    contentResult.append("Row" + i + ": BookPart  is not completed yet!");
                    break;
                }
                XSSFCell cell1 = row.getCell(1);
                if (cell1 != null) {
                    if (cell1.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        cell1.setCellType(Cell.CELL_TYPE_STRING);
                    }
                    qd.setMaterial_id(cell1.getStringCellValue().trim());
                }
                XSSFCell cell2 = row.getCell(2);
                if (cell2 != null) {
                    if (cell2.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        qd.setQty((int) cell2.getNumericCellValue());
                    } else {
                        contentResult.append("Row" + i + ": QTY format error!");
                        break;
                    }
                } else {
                    contentResult.append("Row" + i + ": QTY  is not completed yet!");
                    break;
                }
                XSSFCell cell3 = row.getCell(3);
                if (cell3 != null) {
                    if (cell3.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        qd.setTarget_cost(cell3.getNumericCellValue());
                    } else {
                        contentResult.append("Row " + i + ":  Target Cost format error!");
                        break;
                    }
                } else {
                    contentResult.append("Row " + i + ": Target Cost  is not completed yet!");
                    break;
                }
                XSSFCell cell4 = row.getCell(4);
                if (cell4 != null) {
                    if (cell4.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        qd.setTarget_resale(cell4.getNumericCellValue());
                    } else {
                        contentResult.append("Row " + i + ":  Target Resale format error!");
                        break;
                    }
                } else {
                    contentResult.append("Row" + i + ": Target Resale  is not completed yet!");
                    break;
                }
                double resale = qd.getTarget_resale();
                double cost = qd.getTarget_cost();
                double num = qd.getQty();
                qd.setTarget_amount(Math.round((cost * num) * 10000) / 10000.0000);
                if (resale == 0 || (resale - cost) == 0) {
                    qd.setTarget_margin("0.00");
                } else {
                    DecimalFormat formater = new DecimalFormat("#0.##");
                    String targetMargin = formater.format(((resale - cost) / resale) * 10000 / 100.0000);
                    qd.setTarget_margin(targetMargin);
                }
                XSSFCell cell5 = row.getCell(5);
                if (cell5 != null) {
                    try {
                        qd.setStart_dateStr(new SimpleDateFormat("yyyy-MM-dd").format(cell5.getDateCellValue()));
                    } catch (Exception e) {
                        contentResult.append("Row" + i + ": Start Date format error!");
                        break;
                    }
                } else {
                    contentResult.append("Row" + i + ": Start Date  is not completed yet!");
                    break;
                }
                XSSFCell cell6 = row.getCell(6);
                if (cell6 != null) {
                    try {
                        qd.setEnd_dateStr(new SimpleDateFormat("yyyy-MM-dd").format(cell6.getDateCellValue()));
                    } catch (Exception e) {
                        contentResult.append("Row" + i + ": Expire Date format error!");
                        break;
                    }
                } else {
                    contentResult.append("Row" + i + ": Expire Date  is not completed yet!");
                    break;
                }
                XSSFCell cell7 = row.getCell(7);
                if (cell7 != null) {
                    try {
                        if (cell7.getCellType() != Cell.CELL_TYPE_BLANK) {
                            qd.setProduct_dateStr(new SimpleDateFormat("yyyy-MM-dd").format(cell7.getDateCellValue()));

                        }
                    } catch (Exception e) {
                        contentResult.append("Row" + i + ": Start Production format error!");
                        break;
                    }
                }
                XSSFCell cell8 = row.getCell(8);
                if (cell8 != null) {
                    qd.setReason(cell8.getStringCellValue().trim());
                }
                XSSFCell cell9 = row.getCell(9);
                if (cell9 != null) {
                    qd.setCompetitor(cell9.getStringCellValue().trim());
                }
                XSSFCell cell10 = row.getCell(10);
                if (cell10 != null) {
                    qd.setCus_remark(cell10.getStringCellValue().trim());
                }
                p = new Product();
                if (qd.getMaterial_name() != null && !"".equals(qd.getMaterial_name())) {
                    p.setMaterial_name(qd.getMaterial_name());
                } else {
                    p.setMaterial_id(qd.getMaterial_id());
                }
                p.setCustomer_id(pr.getCustomer_id());
                p.setCurrency_code(pr.getCurrency_code());
                p.setOffice_id(pr.getOffice_id());
                p.setIsQuoteItem("Y");
                pList = productService.getDRQuoteProductListNoPage(p);
                if (pList != null && pList.size() != 0) {
                    p = pList.get(0);
                    qd.setMaterial_id(p.getMaterial_id());
                    qd.setMaterial_name(p.getMaterial_name());
                    qd.setMoq(p.getMoq());
                    qd.setCost(p.getCost());
                    qd.setPbMpp(String.valueOf(p.getPbPrice()));

                } else {
                    contentResult.append(
                            "Row" + i + ":" + qd.getMaterial_id() + " " + qd.getMaterial_name() + "do not exist!");
                    break;
                }
                
                
                XssUtils.getXssSaftBean(qd.getClass(), qd);
                qdList.add(qd);
            }

            if (!"".equals(contentResult.toString())) {
                return contentResult.toString();
            }

            file.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "failed (Importing content format error) !";
        }
        String qdJson = JsonUtil.list2json(qdList);
        return "success!" + qdJson;
    }

    /**
     * 
     * 2003 Excel
     * 
     * @param path
     * @return
     */
    private String findOrderExcelXls(String path, Product pr) {
        FileInputStream fileIn = null;
        Workbook rwb = null;
        List<QuoteDetail> qdList = new ArrayList<QuoteDetail>();
        StringBuilder contentResult = new StringBuilder();
        try {
            fileIn = new FileInputStream(path);
            rwb = Workbook.getWorkbook(fileIn);
            Sheet rs = rwb.getSheet(0);
            int column = 0;
            column = rs.getColumns();
            int actualRows = 0;
            actualRows = StockUtil.delEmptyRow(rs);
            if (actualRows == 0 && column == 0) {
                return "The Excel  is not completed yet !";
            } else {

                for (int i = 1; i < actualRows; i++) {
                    if ("".equals(rs.getCell(0, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        contentResult.append("Row" + i + ": BookPart  is not completed yet!");
                        break;
                    }
                    if ("".equals(rs.getCell(2, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        contentResult.append("Row" + i + ": QTY  is not completed yet!");
                        break;
                    }
                    if ("".equals(rs.getCell(3, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        contentResult.append("Row" + i + ": Target Cost  is not completed yet!");
                        break;
                    }
                    if ("".equals(rs.getCell(4, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        contentResult.append("Row" + i + ": Target Resale  is not completed yet!");
                        break;
                    }
                    if ("".equals(rs.getCell(5, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        contentResult.append("Row" + i + ": Start Date  is not completed yet!");
                        break;
                    }
                    if ("".equals(rs.getCell(6, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        contentResult.append("Row" + i + ": Expire Date  is not completed yet!");
                        break;
                    }
                }

                if (!"".equals(contentResult.toString())) {
                    return contentResult.toString();
                }
                for (int i = 1; i < actualRows; i++) {
                    qd = new QuoteDetail();
                    qd.setMaterial_name(rs.getCell(0, i).getContents().trim());
                    qd.setMaterial_id(rs.getCell(1, i).getContents().trim());
                    // QTY
                    if (rs.getCell(2, i).getType() == CellType.NUMBER) {
                        NumberCell numberCell = (NumberCell) rs.getCell(2, i);
                        int qty = (int) numberCell.getValue();
                        qd.setQty(qty);
                    } else {
                        contentResult.append("Row " + i + ": QTY format error!");
                        break;
                    }
                    // Target_cost
                    if (rs.getCell(3, i).getType() == CellType.NUMBER) {
                        NumberCell numberCell = (NumberCell) rs.getCell(3, i);
                        double cost = numberCell.getValue();
                        qd.setTarget_cost(cost);
                    } else {
                        contentResult.append("Row " + i + ": Target Cost format error!");
                        break;
                    }
                    // Target_resale
                    if (rs.getCell(4, i).getType() == CellType.NUMBER) {
                        NumberCell numberCell = (NumberCell) rs.getCell(4, i);
                        double resale = numberCell.getValue();
                        qd.setTarget_resale(resale);
                    } else {
                        contentResult.append("Row " + i + ": Target Resale format error!");
                        break;
                    }
                    double resale = qd.getTarget_resale();
                    double cost = qd.getTarget_cost();
                    double num = qd.getQty();
                    qd.setTarget_amount(Math.round((cost * num) * 10000) / 10000.0000);
                    if (resale == 0 || (resale - cost) == 0) {
                        qd.setTarget_margin("0.00");
                    } else {
                        DecimalFormat formater = new DecimalFormat("#0.##");
                        String targetMargin = formater.format(((resale - cost) / resale) * 10000 / 100.0000);
                        qd.setTarget_margin(targetMargin);
                    }
                    // Start Date
                    try {
                        if (rs.getCell(5, i).getType() == CellType.DATE) {
                            DateCell dateCell = (DateCell) rs.getCell(5, i);
                            Date date = dateCell.getDate();
                            String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
                            qd.setStart_dateStr(dateStr);
                        } else {
                            contentResult.append("Row" + i + ": Start Date format error!");
                            break;
                        }
                    } catch (Exception e) {
                        contentResult.append("Row" + i + ": Start Date format error!");
                        break;
                    }
                    // Expire Date
                    try {
                        if (rs.getCell(6, i).getType() == CellType.DATE) {
                            DateCell dateCell = (DateCell) rs.getCell(6, i);
                            Date date = dateCell.getDate();
                            String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
                            qd.setEnd_dateStr(dateStr);
                        } else {
                            contentResult.append("Row" + i + ": Expire Date format error!");
                            break;
                        }
                    } catch (Exception e) {
                        contentResult.append("Row" + i + ": Expire Date format error!");
                        break;
                    }
                    // Start Production
                    try {
                        if (rs.getCell(7, i).getType() == CellType.DATE) {
                            DateCell dateCell = (DateCell) rs.getCell(7, i);
                            Date date = dateCell.getDate();
                            String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
                            qd.setProduct_dateStr(dateStr);
                        }
                    } catch (Exception e) {
                        contentResult.append("Row" + i + ": Start Production format error!");
                        break;
                    }

                    qd.setReason(rs.getCell(8, i).getContents().trim());
                    qd.setCompetitor(rs.getCell(9, i).getContents().trim());
                    qd.setCus_remark(rs.getCell(10, i).getContents().trim());
                    p = new Product();
                    if (qd.getMaterial_name() != null && !("".equals(qd.getMaterial_name()))) {
                        p.setMaterial_name(qd.getMaterial_name());
                    } else {
                        p.setMaterial_id(qd.getMaterial_id());
                    }
                    p.setCustomer_id(pr.getCustomer_id());
                    p.setCurrency_code(pr.getCurrency_code());
                    p.setOffice_id(pr.getOffice_id());
                    p.setIsQuoteItem("Y");
                    pList = productService.getDRQuoteProductListNoPage(p);
                    if (pList != null && pList.size() != 0) {
                        p = pList.get(0);
                        qd.setMaterial_id(p.getMaterial_id());
                        qd.setMaterial_name(p.getMaterial_name());
                        qd.setMoq(p.getMoq());
                        qd.setCost(p.getCost());
                        qd.setPbMpp(String.valueOf(p.getPbPrice()));
                    } else {
                        contentResult.append(
                                "Row" + i + ":" + qd.getMaterial_id() + " " + qd.getMaterial_name() + "do not exist!");
                        break;
                    }
                    
                    XssUtils.getXssSaftBean(qd.getClass(), qd);
                    
                    qdList.add(qd);
                }

                if (!"".equals(contentResult.toString())) {
                    return contentResult.toString();
                }
            }
            String qdJson = JsonUtil.list2json(qdList);
            return "success!" + qdJson;
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Excel Template error !";
        } catch (Exception e) {
            return "failed (Importing content format error) !";
        }
    }

    public String findOrderExcel() {
        String result;
        Product pr = new Product();
        pr.setCustomer_id(customer_id);
        pr.setOffice_id(office_id);
        pr.setCurrency_code(currency_code);
        if (ExcelUtil.getExcelStyle(path).intValue() == 1) {
            result = findOrderExcelXls(uploadFile, pr);
            if (result.indexOf("success!") > -1) {
                this.setSuccessMessage(result.split("!")[1]);
            } else {
                this.setFailMessage(result);
            }
            return RESULT_MESSAGE;
        } else if (ExcelUtil.getExcelStyle(path).intValue() == 2) {
            result = findOrderExcelXlsx(uploadFile, pr);
            if (result.indexOf("success!") > -1) {
                this.setSuccessMessage(result.split("!")[1]);
            } else {
                this.setFailMessage(result);
            }
            return RESULT_MESSAGE;
        } else {
            this.setFailMessage("failed (Importing content format error) !");
            return RESULT_MESSAGE;
        }
    }

    @PermissionSearch
    @JsonResult(field = "qdList", include = { "customer_id", "disti_branch", "cusGroup_id", "ecGroup_id", "isDelivery",
            "currency_code", "purchaseCustomer_id", "purchaseCustomer_name", "endCustomer_id", "project_name",
            "customer_name", "endCustomer_name", "ecGroup_name", "pcGroup_name", "latest_userId", "latest_time", "id",
            "main_id", "quote_id", "row_no", "material_id", "drNum", "qty", "isAgree", "isRepresent", "target_resale",
            "target_cost", "target_amount", "amount", "reason", "competitor", "res_qty", "create_userId",
            "product_date", "product_dateStr", "start_date", "start_dateStr", "end_date", "end_dateStr", "cus_remark",
            "suggest_resale", "suggest_cost", "material_name", "data_from", "cus_profits_percent", "profits_percent",
            "remark", "state", "target_margin", "debit_start", "debit_end", "debit_num" })
    public String checkQuote() throws ParseException {
        qd = new QuoteDetail();
        qd.setId(Long.valueOf(id));
        if (disti_branch != null && (!("".equals(disti_branch)))) {
            qd.setDisti_branch(disti_branch.toUpperCase());
        }
        if (project_name != null && (!("".equals(project_name)))) {
            qd.setProject_name(project_name.toUpperCase());
        }
        if (StringUtils.isNotEmpty(material_name) && StringUtils.isNotEmpty(material_name.trim())) {
            try {
                material_name = java.net.URLDecoder.decode(material_name, "UTF-8");
                qd.setMaterial_name(material_name.toUpperCase());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (StringUtils.isNotEmpty(cusGroup_id) && StringUtils.isNotEmpty(cusGroup_id.trim())) {
            try {
                cusGroup_id = java.net.URLDecoder.decode(cusGroup_id, "UTF-8");
                qd.setCusGroup_id(cusGroup_id.toUpperCase());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (StringUtils.isNotEmpty(pcGroup_id) && StringUtils.isNotEmpty(pcGroup_id.trim())) {
            try {
                pcGroup_id = java.net.URLDecoder.decode(pcGroup_id, "UTF-8");
                qd.setPcGroup_id(pcGroup_id.toUpperCase());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (StringUtils.isNotEmpty(ecGroup_id) && StringUtils.isNotEmpty(ecGroup_id.trim())) {
            try {
                ecGroup_id = java.net.URLDecoder.decode(ecGroup_id, "UTF-8");
                qd.setEcGroup_id(ecGroup_id.toUpperCase());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        qd.setCreate_time(sdf.parse(start_dateStr));
        if (StringUtils.isNotEmpty(purchaseCustomer_name) && StringUtils.isNotEmpty(purchaseCustomer_name.trim())) {
            try {
                purchaseCustomer_name = java.net.URLDecoder.decode(purchaseCustomer_name, "UTF-8");
                qd.setPurchaseCustomer_name(purchaseCustomer_name.toUpperCase());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (StringUtils.isNotEmpty(endCustomer_name) && StringUtils.isNotEmpty(endCustomer_name.trim())) {
            try {
                endCustomer_name = java.net.URLDecoder.decode(endCustomer_name, "UTF-8");
                qd.setEndCustomer_name(endCustomer_name.toUpperCase());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        qdList = quoteService.checkQuote(qd);
        return JSON;
    }

    public String updateRemark() {
        this.setFailMessage("");
        this.setSuccessMessage("");
        qd = new QuoteDetail();
        if (StringUtils.isNotEmpty(remark) && StringUtils.isNotEmpty(remark.trim())) {
            try {
                qd.setRemark(Escape.unescape(remark));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (StringUtils.isNotEmpty(cus_remark) && StringUtils.isNotEmpty(cus_remark.trim())) {
            try {
                qd.setCus_remark(Escape.unescape(cus_remark));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        qd.setId(Long.valueOf(id));
        int i = quoteService.updateRemark(qd);
        if (i > 0) {
            this.setSuccessMessage("Success !");
        } else {
            this.setFailMessage("failed !");
        }
        return RESULT_MESSAGE;
    }

    public String toCopyCreate() {
        q = new Quote();
        q.setId(Long.valueOf(id));
        q = quoteService.getQuoteById(q);
        ec = new EndCustomer();
        ec.setEnd_customer_id(q.getPurchaseCustomer_id());
        ec = endCustomerService.getEndCustomerByCode(ec);
        if (ec != null) {
            q.setPcGroup_id(ec.getEnd_customer_groupId());
        }

        if (q.getEndCustomer_id() != null && !"".equals(q.getEndCustomer_id())) {// 若EC不为空，则查询对应ECGroup
            ec = new EndCustomer();
            ec.setEnd_customer_id(q.getEndCustomer_id());
            ec = endCustomerService.getEndCustomerByCode(ec);
            if (ec != null) {
                q.setEcGroup_id(ec.getEnd_customer_groupId());
            }
        }

        q.setCreate_timeStr(DateUtil.getNowDateStr());
        String userString = this.getUser().getLoginId();
        int n = userString.indexOf("@");
        if (n > -1) {
            q.setCreate_userId(userString.substring(0, userString.indexOf("@")));
        } else {
            q.setCreate_userId(userString);
        }
        q.setFile_name(null);
        q.setFile_path(null);
        String orgString = this.getUser().getOrgId();
        if (orgString == null || "".equals(orgString)) {
            q.setCusGroup_id(this.getUser().getUserName());
        }
        return "toCopyCreate";
    }

    @PermissionSearch
    @JsonResult(field = "qdList1", include = { "id", "quote_id", "row_no", "material_id", "drNum", "qty", "isRepresent",
            "target_resale", "target_cost", "target_amount", "amount", "reason", "competitor", "target_margin",
            "res_qty", "product_date", "product_dateStr", "start_date", "start_dateStr", "end_date", "end_dateStr",
            "latest_time", "cus_remark", "suggest_resale", "suggest_cost", "state", "latest_userId", "isAgree",
            "create_userId", "cus_profits_percent", "profits_percent", "remark", "material_name", "cost", "moq",
            "pbMpp", "debit_start", "debit_end", "debit_num", })
    public String getCopyQDList() {
        q = new Quote();
        q.setId(Long.valueOf(id));
        q = quoteService.getQuoteById(q);
        qd = new QuoteDetail();
        qd.setQuote_id(quote_id);
        qdList = quoteService.getQuoteDetailList(qd);
        qdList1 = new ArrayList<QuoteDetail>();
        for (QuoteDetail qdt : qdList) {
            qd1 = new QuoteDetail();
            p = new Product();
            p.setMaterial_id(qdt.getMaterial_id());
            p.setMaterial_name(qdt.getMaterial_name());
            p.setCustomer_id(q.getPayer_to());
            p.setCurrency_code(q.getCurrency_code());
            p.setOffice_id(q.getPricing_region());
            p.setIsQuoteItem("Y");
            //
            if (office_id != null && (!("".equals(office_id)))) {
                p.setOffice_id(office_id);
            }
            if (customer_id != null && (!("".equals(customer_id)))) {
                p.setCustomer_id(customer_id);
            }
            if (currency_code != null && (!("".equals(currency_code)))) {
                p.setCurrency_code(currency_code);
            }
            //
            pList = productService.getDRQuoteProductListNoPage(p);
            if (pList != null && pList.size() != 0) {
                p = pList.get(0);
                qd1.setMaterial_id(p.getMaterial_id());
                qd1.setMaterial_name(p.getMaterial_name());
                qd1.setMoq(p.getMoq());
                qd1.setCost(p.getCost());
                qd1.setPbMpp(String.valueOf(p.getPbPrice()));
            } else {
                continue;
            }
            double resale = qdt.getTarget_resale();
            double cost = qdt.getTarget_cost();
            double num = qdt.getQty();
            qd1.setTarget_amount(Math.round((cost * num) * 10000) / 10000.0000);
            qd1.setTarget_cost(cost);
            qd1.setTarget_resale(resale);
            qd1.setQty(qdt.getQty());
            if (resale == 0 || (resale - cost) == 0) {
                qd1.setTarget_margin("0.00");
            } else {
                DecimalFormat formater = new DecimalFormat("#0.##");
                String targetMargin = formater.format(((resale - cost) / resale) * 10000 / 100.0000);
                qd1.setTarget_margin(targetMargin);
            }

            qd1.setStart_dateStr(qdt.getStart_dateStr());
            qd1.setEnd_dateStr(qdt.getEnd_dateStr());
            qd1.setProduct_dateStr(null);
            qd1.setReason(qdt.getReason());
            qd1.setCompetitor(qdt.getCompetitor());

            qdList1.add(qd1);
        }
        return JSON;
    }

    /**
     * 查询Quote历史价格导出报表
     * 
     * @return
     */
    @PermissionSearch
    @JsonResult(field = "qdList", include = {
            // 主表
            "disti_branch", "cusGroup_id", "currency_code",
            // 从表
            "id", "main_id", "quote_id", "material_id", "isAgree", "rate", "suggest_resale", "suggest_cost",
            "suggest_cost_usd", "material_name", "debit_start", "debit_end", "debit_startStr", "debit_endStr",
            "debit_num", "price_region", "disti_region" }, total = "total")
    public String getOutQuotePrice() {
        qd = new QuoteDetail();
        qd.setStart(getStart());
        qd.setEnd(getEnd());
        qd.setSort("aa.id");
        qd.setDir("desc");
        qd.setStates(states);
        if (cusGroup_id != null && (!("".equals(cusGroup_id)))) {
            qd.setCusGroup_id(cusGroup_id.toUpperCase());
        }
        if (price_region != null && (!("".equals(price_region)))) {
            qd.setPrice_region(price_region);
        }
        if (currency_code != null && (!("".equals(currency_code)))) {
            qd.setCurrency_code(currency_code);
        }
        if (material_name != null && (!("".equals(material_name)))) {
            qd.setMaterial_name(material_name.toUpperCase());
        }
        qd.setMaterial_id(material_id);
        if (quote_id != null && (!("".equals(quote_id)))) {
            qd.setQuote_id(quote_id.toUpperCase());
        }
        total = quoteService.getOutQuotePriceCount(qd);
        if (total > 0) {
            qdList = quoteService.getOutQuotePrice(qd);
            for (QuoteDetail qdt : qdList) {
                double cost = qdt.getSuggest_cost();
                double USDCost = qdt.getSuggest_cost();
                if ("EUR".equals(qdt.getCurrency_code())) {
                    USDCost = Math.round((cost * qdt.getRate()) * 10000) / 10000.0000;
                }
                qdt.setSuggest_cost_usd(USDCost);
            }
        }
        return JSON;
    }

    /**
     * 导出历史价格报表
     * 
     * @throws Exception
     */
    public void downloadQuotePrice() throws Exception {
        try {
            this.setPage(1);
            this.setRows(100000);
            getOutQuotePrice();
            String fileName = "QuotePrice";
            CommonUtil.setExcelResponseInfo(getServletRequest(), getServletResponse(), fileName);
            // excel
            String[] titles = { "Disti Name", "Region", "12NC", "Currency", "Quote Price", "USD Price",
                    "Debit Expire" };
            String[] keys = { "cusGroup_id", "price_region", "material_id", "currency_code", "suggest_cost",
                    "suggest_cost_usd", "debit_endStr" };
            List<Map<String, String>> li = new ArrayList<Map<String, String>>();
            for (QuoteDetail qd : qdList) {
                li.add(CommonUtil.transBean2Map(qd));
            }
            CommonUtil.exportExcel(li, titles, keys, getServletResponse().getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getEndCustomer_name() {
        return endCustomer_name;
    }

    public void setEndCustomer_name(String endCustomer_name) {
        this.endCustomer_name = endCustomer_name;
    }

    public String getPurchaseCustomer_name() {
        return purchaseCustomer_name;
    }

    public void setPurchaseCustomer_name(String purchaseCustomer_name) {
        this.purchaseCustomer_name = purchaseCustomer_name;
    }

    public String getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(String uploadFile) {
        this.uploadFile = uploadFile;
    }

    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getDisti_branch() {
        return disti_branch;
    }

    public void setDisti_branch(String disti_branch) {
        this.disti_branch = disti_branch;
    }

    public List<Product> getpList() {
        return pList;
    }

    public void setpList(List<Product> pList) {
        this.pList = pList;
    }

    public IProductService getProductService() {
        return productService;
    }

    public void setProductService(IProductService productService) {
        this.productService = productService;
    }

    public IPosService getPosService() {
        return posService;
    }

    public void setPosService(IPosService posService) {
        this.posService = posService;
    }

    public Pos getPos() {
        return pos;
    }

    public void setPos(Pos pos) {
        this.pos = pos;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCusGroup_id() {
        return cusGroup_id;
    }

    public void setCusGroup_id(String cusGroup_id) {
        this.cusGroup_id = cusGroup_id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(String rowIndex) {
        this.rowIndex = rowIndex;
    }

    public CmsTbDict getCmsTbDict() {
        return cmsTbDict;
    }

    public void setCmsTbDict(CmsTbDict cmsTbDict) {
        this.cmsTbDict = cmsTbDict;
    }

    public IDictService getDictService() {
        return dictService;
    }

    public void setDictService(IDictService dictService) {
        this.dictService = dictService;
    }

    public List<CustomerUser> getUserList() {
        return userList;
    }

    public void setUserList(List<CustomerUser> userList) {
        this.userList = userList;
    }

    public String getForWho() {
        return forWho;
    }

    public void setForWho(String forWho) {
        this.forWho = forWho;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getOffice_id() {
        return office_id;
    }

    public void setOffice_id(String office_id) {
        this.office_id = office_id;
    }

    public String getCus_remark() {
        return cus_remark;
    }

    public void setCus_remark(String cus_remark) {
        this.cus_remark = cus_remark;
    }

    public String getMyself() {
        return myself;
    }

    public void setMyself(String myself) {
        this.myself = myself;
    }

    public Quote getQ1() {
        return q1;
    }

    public void setQ1(Quote q1) {
        this.q1 = q1;
    }

    public List<Quote> getqList1() {
        return qList1;
    }

    public void setqList1(List<Quote> qList1) {
        this.qList1 = qList1;
    }

    public QuoteDetail getQd1() {
        return qd1;
    }

    public void setQd1(QuoteDetail qd1) {
        this.qd1 = qd1;
    }

    public List<QuoteDetail> getQdList1() {
        return qdList1;
    }

    public void setQdList1(List<QuoteDetail> qdList1) {
        this.qdList1 = qdList1;
    }

    public String getCreate_userName() {
        return create_userName;
    }

    public void setCreate_userName(String create_userName) {
        this.create_userName = create_userName;
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

    public String getPrice_region() {
        return price_region;
    }

    public void setPrice_region(String price_region) {
        this.price_region = price_region;
    }

    public IEndCustomerService getEndCustomerService() {
        return endCustomerService;
    }

    public void setEndCustomerService(IEndCustomerService endCustomerService) {
        this.endCustomerService = endCustomerService;
    }

    public String getPcGroup_id() {
        return pcGroup_id;
    }

    public void setPcGroup_id(String pcGroup_id) {
        this.pcGroup_id = pcGroup_id;
    }

    public String getEcGroup_id() {
        return ecGroup_id;
    }

    public void setEcGroup_id(String ecGroup_id) {
        this.ecGroup_id = ecGroup_id;
    }

    public String getNoPCEC() {
        return noPCEC;
    }

    public void setNoPCEC(String noPCEC) {
        this.noPCEC = noPCEC;
    }

    public void addActionError(String anErrorMessage) {
        String s = anErrorMessage;
        System.out.println(s);
    }

    public void addActionMessage(String aMessage) {
        String s = aMessage;
        System.out.println(s);

    }

    public void addFieldError(String fieldName, String errorMessage) {
        String s = errorMessage;
        String f = fieldName;
        System.out.println(s);
        System.out.println(f);

    }

    public IMessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(IMessageService messageService) {
        this.messageService = messageService;
    }

    public String getUseFor() {
        return useFor;
    }

    public void setUseFor(String useFor) {
        this.useFor = useFor;
    }

    public String getDebit_num() {
        return debit_num;
    }

    public void setDebit_num(String debit_num) {
        this.debit_num = debit_num;
    }

    public String getFromWho() {
        return fromWho;
    }

    public void setFromWho(String fromWho) {
        this.fromWho = fromWho;
    }

    public static void main(String[] args) {
    }

    public String getSuggestCosts() {
        return suggestCosts;
    }

    public void setSuggestCosts(String suggestCosts) {
        this.suggestCosts = suggestCosts;
    }

    public String getSuggestResales() {
        return suggestResales;
    }

    public void setSuggestResales(String suggestResales) {
        this.suggestResales = suggestResales;
    }

    public String getAmounts() {
        return amounts;
    }

    public void setAmounts(String amounts) {
        this.amounts = amounts;
    }

    public String getRoleControl() {
        return roleControl;
    }

    public void setRoleControl(String roleControl) {
        this.roleControl = roleControl;
    }

    public String getWebFrom() {
        return webFrom;
    }

    public void setWebFrom(String webFrom) {
        this.webFrom = webFrom;
    }

}
