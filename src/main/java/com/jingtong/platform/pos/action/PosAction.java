package com.jingtong.platform.pos.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.common.CommonUtil;
import com.jingtong.platform.common.DoubleCalculateUtil;
import com.jingtong.platform.common.Escape;
import com.jingtong.platform.customer.pojo.CustomerUser;
import com.jingtong.platform.designReg.service.IDesignRegService;
import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.dict.service.IDictService;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.framework.util.DateUtil;
import com.jingtong.platform.framework.util.ExcelUtil;
import com.jingtong.platform.framework.util.StockUtil;
import com.jingtong.platform.pos.pojo.Pos;
import com.jingtong.platform.pos.service.IPosService;
import com.jingtong.platform.role.pojo.Role;
import com.jingtong.platform.role.service.IRoleService;

import jxl.CellType;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/***
 * 
 * @author mk
 * @createDate 2016-5-24
 * 
 */

public class PosAction extends BaseAction {

    private static final long serialVersionUID = 8326843659323817600L;
    private Pos pos;
    private String uploadFile;
    private List<Pos> poList;
    private List<Integer> idList;
    private int total;
    private String branchId;
    private String currency_name;
    private String status;
    private Date update_time;
    private String disti_name;
    private Date start_time;

    private Date end_time;
    private String start_timeString;
    private String end_timeString;
    private String path;
    private String file_url;
    private String file_name;
    private long file_id;
    private String disti_accounting_nbr;
    private String book_part;
    private CustomerUser cusUser;
    private IPosService posService;
    private String rebateOver;
    private String p_name;
    private String p_city;
    private String p_zip;
    private String e_name;
    private String e_city;
    private String e_zip;
    private String ship_to;
    private long id;
    private String pcec;
    private CmsTbDict cmsTbDict;
    private IDictService dictService;
    // 报表查询
    private String disti_branch;
    private String debit_number;
    private String purchasing_customer_name;
    private String endcust_name;
    private String debitsOrRedits;
    private String file_ids;
    private String type;
    private String data_from;
    private String quote_id;

    private String ids;
    private String remark;
    private IDesignRegService designRegService;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getP_zip() {
        return p_zip;
    }

    public void setP_zip(String p_zip) {
        this.p_zip = p_zip;
    }

    public String getE_zip() {
        return e_zip;
    }

    public void setE_zip(String e_zip) {
        this.e_zip = e_zip;
    }

    public String getStart_timeString() {
        return start_timeString;
    }

    public void setStart_timeString(String start_timeString) {
        this.start_timeString = start_timeString;
    }

    public String getEnd_timeString() {
        return end_timeString;
    }

    public void setEnd_timeString(String end_timeString) {
        this.end_timeString = end_timeString;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    // 判断角色
    private List<Role> roleList;
    private IRoleService roleService;
    // 标记登陆人的角色
    private String loginRole;

    private String disti_invoice_nbr;

    public String getDisti_invoice_nbr() {
        return disti_invoice_nbr;
    }

    public void setDisti_invoice_nbr(String disti_invoice_nbr) {
        this.disti_invoice_nbr = disti_invoice_nbr;
    }

    public IRoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getLoginRole() {
        return loginRole;
    }

    public void setLoginRole(String loginRole) {
        this.loginRole = loginRole;
    }

    public String getDebitsOrRedits() {
        return debitsOrRedits;
    }

    public void setDebitsOrRedits(String debitsOrRedits) {
        this.debitsOrRedits = debitsOrRedits;
    }

    public String getFile_ids() {
        return file_ids;
    }

    public void setFile_ids(String file_ids) {
        this.file_ids = file_ids;
    }

    public String getType() {
        return type;
    }

    public String getData_from() {
        return data_from;
    }

    public void setData_from(String data_from) {
        this.data_from = data_from;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CmsTbDict getCmsTbDict() {
        return cmsTbDict;
    }

    public void setCmsTbDict(CmsTbDict cmsTbDict) {
        this.cmsTbDict = cmsTbDict;
    }

    public IPosService getPosService() {
        return posService;
    }

    public void setPosService(IPosService posService) {
        this.posService = posService;
    }

    public String getDisti_branch() {
        return disti_branch;
    }

    public void setDisti_branch(String disti_branch) {
        this.disti_branch = disti_branch;
    }

    public String getDebit_number() {
        return debit_number;
    }

    public void setDebit_number(String debit_number) {
        this.debit_number = debit_number;
    }

    public String getPurchasing_customer_name() {
        return purchasing_customer_name;
    }

    public void setPurchasing_customer_name(String purchasing_customer_name) {
        this.purchasing_customer_name = purchasing_customer_name;
    }

    public String getEndcust_name() {
        return endcust_name;
    }

    public void setEndcust_name(String endcust_name) {
        this.endcust_name = endcust_name;
    }

    public IDictService getDictService() {
        return dictService;
    }

    public void setDictService(IDictService dictService) {
        this.dictService = dictService;
    }

    public String getCurrency_name() {
        return currency_name;
    }

    public void setCurrency_name(String currency_name) {
        this.currency_name = currency_name;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getShip_to() {
        return ship_to;
    }

    public void setShip_to(String ship_to) {
        this.ship_to = ship_to;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Pos> getPoList() {
        return poList;
    }

    public void setPoList(List<Pos> poList) {
        this.poList = poList;
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

    public String getBook_part() {
        return book_part;
    }

    public void setBook_part(String book_part) {
        this.book_part = book_part;
    }

    public String getDisti_name() {
        return disti_name;
    }

    public void setDisti_name(String disti_name) {
        this.disti_name = disti_name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_city() {
        return p_city;
    }

    public void setP_city(String p_city) {
        this.p_city = p_city;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public String getE_city() {
        return e_city;
    }

    public void setE_city(String e_city) {
        this.e_city = e_city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getDisti_accounting_nbr() {
        return disti_accounting_nbr;
    }

    public void setDisti_accounting_nbr(String disti_accounting_nbr) {
        this.disti_accounting_nbr = disti_accounting_nbr;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public long getFile_id() {
        return file_id;
    }

    public void setFile_id(long file_id) {
        this.file_id = file_id;
    }

    public CustomerUser getCusUser() {
        return cusUser;
    }

    public void setCusUser(CustomerUser cusUser) {
        this.cusUser = cusUser;
    }

    public String getPcec() {
        return pcec;
    }

    public void setPcec(String pcec) {
        this.pcec = pcec;
    }

    public String getRebateOver() {
        return rebateOver;
    }

    public void setRebateOver(String rebateOver) {
        this.rebateOver = rebateOver;
    }

    public Pos getPos() {
        return pos;
    }

    public void setPos(Pos pos) {
        this.pos = pos;
    }

    public String toPosPre() {
        return "toPosPre";
    }

    public String toPosTrackingDetail() {
        return "toPosTrackingDetail";
    }

    public String toClaimTrackingDetail() {
        return "toClaimTrackingDetail";
    }

    public String toPosPreDetail() {
        pos = new Pos();
        pos.setFile_id(file_id);
        pos.setFile_url(file_url);
        return "toPosPreDetail";
    }

    public String importExcel() {
        return "importExcel";
    }

    public String toCreateECAlias() {
        pos = new Pos();
        pos.setP_city(p_city);
        pos.setP_name(p_name);
        pos.setPurchasing_cust_zip(p_zip);
        pos.setE_city(e_city);
        pos.setE_name(e_name);
        pos.setEndcust_zip(e_zip);

        return "toCreateECAlias";
    }

    public String searchBb() {
        return "searchBb";
    }

    @PermissionSearch
    @JsonResult(field = "poList", include = { "id", "row_no", "sapClaimNbr", "transaction_code", "disti_name",
            "disti_branch", "disti_city", "book_part", "ship_qty", "ship_date", "debit_number", "oppreg_nbr",
            "disti_invoice_nbr", "disti_invoice_item_number", "disti_cost_currency", "disti_resale_denom",
            "disti_resale_currency", "purchasing_customer_name", "purchasing_cust_city", "purchasing_cust_zip",
            "created_user", "created_time", "purchasing_cust_state", "purchasing_cust_country", "endcust_name",
            "endcust_city", "endcust_loc", "endcust_zip", "endcust_state", "endcust_country", "status", "tips",
            "file_id", "file_url", "status_num", "type", "disti_accounting_nbr", "data_from", "qty",
            "amount" }, total = "total")
    public String getPosList() {

        Pos pos = new Pos();
        if (StringUtils.isNotEmpty(disti_name) && StringUtils.isNotEmpty(disti_name.trim())) {
            try {
                disti_name = java.net.URLDecoder.decode(disti_name, "UTF-8");
                disti_name = disti_name.toUpperCase();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        pos.setDisti_name(disti_name);
        pos.setDisti_cost_currency(currency_name);
        pos.setStatus(status);
        if (file_id > 0)
            pos.setFile_id_str(file_id);

        pos.setType("1");
        if (null != start_timeString && !"".equals(start_timeString))
            pos.setStart_time(DateUtil.getDateFromStr(start_timeString));
        if (null != end_timeString && !"".equals(end_timeString))
            pos.setEnd_time(DateUtil.getDateFromStr(end_timeString));

        if (start_timeString != null && !"".equals(start_timeString))
            pos.setShip_dateStart(start_timeString.replace("-", ""));
        if (start_timeString != null && !"".equals(start_timeString))
            pos.setShip_dateEnd(end_timeString.replace("-", ""));

        if (StringUtils.isNotEmpty(file_url) && StringUtils.isNotEmpty(file_url.trim())) {
            try {
                file_url = java.net.URLDecoder.decode(file_url, "UTF-8");
                file_url = file_url.toUpperCase();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        pos.setFile_url(file_url);

        pos.setStart(this.getStart());
        pos.setEnd(this.getEnd());
        total = posService.searchPosListCount(pos);
        if (total != 0) {
            poList = posService.searchPosList(pos);
            System.out.println(System.currentTimeMillis());

            StringBuilder fileIds = new StringBuilder();
            StringBuilder fileIdsFromSearch = new StringBuilder();
            for (Pos p : poList) {
                Long file_id = p.getFile_id();
                if (fileIds.length() > 0) {
                    fileIds.append(",");
                }
                fileIds.append(file_id);
            }
            fileIdsFromSearch.append("(");
            fileIdsFromSearch.append(fileIds);
            fileIdsFromSearch.append(")");
            Pos pp = new Pos();
            pp.setType("1");
            pp.setFileIds(fileIdsFromSearch.toString());
            List<Pos> sumPosList = posService.getPosByfileIds(pp);

            for (Pos p : poList) {
                Integer s1 = 0;
                Integer s2 = 0;
                Integer s3 = 0;
                Long sumQty = 0L;
                Double sumAmount = 0.0;
                for (Pos sumPos : sumPosList) {
                    if (sumPos.getFile_id() == p.getFile_id()) {
                        if ("1".equals(sumPos.getStatus())) {
                            s1 = s1 + 1;
                        } else if ("0".equals(sumPos.getStatus())) {
                            s2 = s2 + 1;
                        } else if ("3".equals(sumPos.getStatus())) {
                            Long shipQty = 0L;
                            try {
                                shipQty = Long.valueOf(sumPos.getShip_qty());
                            } catch (NumberFormatException e) {
                                shipQty = 0L;
                            }
                            sumQty = sumQty + shipQty;
                            sumAmount = sumAmount + shipQty * Double.valueOf(sumPos.getDisti_resale_denom());
                        }
                        s3 = s3 + 1;
                    }
                }
                StringBuilder statusNum = new StringBuilder();
                statusNum.append(s1.toString());
                statusNum.append("/");
                statusNum.append(s2.toString());
                statusNum.append("/");
                statusNum.append(s3.toString());
                p.setStatus_num(statusNum.toString());
                p.setQty(sumQty);
                BigDecimal b = new BigDecimal(sumAmount);
                p.setAmount(b.setScale(4, BigDecimal.ROUND_HALF_UP).toString());
            }
        }
        System.out.println(System.currentTimeMillis());
        return JSON;
    }

    @PermissionSearch
    @JsonResult(field = "poList", include = { "id", "row_no", "sapClaimNbr", "transaction_code", "disti_name",
            "disti_branch", "disti_city", "book_part", "ship_qty", "ship_date", "debit_number", "oppreg_nbr",
            "disti_invoice_nbr", "m_12nc", "disti_invoice_item_number", "disti_cost_currency", "disti_resale_denom",
            "disti_resale_currency", "purchasing_customer_name", "purchasing_cust_city", "purchasing_cust_zip",
            "purchasing_cust_state", "purchasing_cust_country", "endcust_name", "endcust_city", "endcust_loc",
            "endcust_zip", "endcust_state", "endcust_country", "status", "tips", "file_id", "file_url", "statue_num",
            "type", "disti_accounting_nbr" }, total = "total")
    public String getPosListById() {

        pos = new Pos();

        if (StringUtils.isNotEmpty(disti_name) && StringUtils.isNotEmpty(disti_name.trim())) {
            try {
                disti_name = java.net.URLDecoder.decode(disti_name, "UTF-8");
                disti_name = disti_name.toUpperCase();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        pos.setStatus(status);
        if (StringUtils.isNotEmpty(book_part) && StringUtils.isNotEmpty(book_part.trim())) {
            try {
                book_part = java.net.URLDecoder.decode(book_part, "UTF-8");
                book_part = book_part.toUpperCase();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        pos.setBook_part(book_part);
        pos.setFile_id(file_id);
        if (null != start_timeString && !"".equals(start_timeString))
            pos.setStart_time(DateUtil.getDateFromStr(start_timeString));
        if (null != end_timeString && !"".equals(end_timeString))
            pos.setEnd_time(DateUtil.getDateFromStr(end_timeString));
        pos.setType("1");

        pos.setStart(this.getStart());
        pos.setEnd(this.getEnd());
        total = posService.searchPosDetailListCountById(pos);
        if (total != 0) {
            poList = posService.searchPosDetailListById(pos);
        }

        return JSON;
    }

    public String toPosUnpre() {
        return "toPosUnpre";
    }

    public String toPosUnpreA() {
        String userId = this.getUser().getUserId();
        Role r = new Role();
        r.setStart(0);
        r.setEnd(100);
        r.setEmp_code(userId);
        roleList = roleService.getSelectedRole4StationList(r);
        loginRole = "";
        for (Role rl : roleList) {
            loginRole += rl.getRoleId() + ",";
        }
        return "toPosUnpreA";
    }

    public String toPosUnpreB() {
        return "toPosUnpreB";
    }

    @PermissionSearch
    @JsonResult(field = "poList", include = { "id", "row_no", "sapClaimNbr", "transaction_code", "disti_name",
            "disti_branch", "disti_city", "quote_totalqty", "book_part", "ship_qty", "ship_date", "debit_number",
            "oppreg_nbr", "disti_invoice_nbr", "m_12nc", "rebateOver", "ship_to", "payer_to", "billing_to",
            "disti_invoice_item_number", "disti_cost_currency", "disti_resale_denom", "disti_resale_currency",
            "disti_bookcost", "purchasing_customer_name", "purchasing_cust_city", "purchasing_cust_zip", "disti_cost",
            "remainQty", "rebatePrice", "rebateTotal", "cost_denom", "dbc_denom", "purchasing_cust_state",
            "purchasing_cust_country", "endcust_name", "endcust_city", "endcust_loc", "endcust_zip", "endcust_state",
            "endcust_country", "status", "disti_accounting_nbr" })
    public String getReceivePosListA() {
        Pos pos = new Pos();
        pos.setDisti_name(branchId);
        pos.setDisti_cost_currency(currency_name);
        pos.setStatus("3");
        pos.setFlag("1");
        pos.setType("2");
        pos.setShip_to(ship_to);
        poList = posService.searchPosListForAll(pos);
        for (Pos p : poList) {
            DecimalFormat df = new DecimalFormat("0.0000");
            String rebatePriceStr = df.format(Double.valueOf(p.getDbc_denom()) - Double.valueOf(p.getCost_denom()));
            double rebatePrice = Double.valueOf(rebatePriceStr);
            p.setRebatePrice(rebatePrice);
            BigDecimal bd1 = new BigDecimal(Double.toString(rebatePrice));
            BigDecimal bd2 = new BigDecimal(p.getShip_qty());
            String rebateTotalStr = df.format(bd1.multiply(bd2).doubleValue());
            p.setRebateTotal(Double.valueOf(rebateTotalStr));
        }
        return JSON;
    }

    @PermissionSearch
    @JsonResult(field = "poList", include = { "id", "row_no", "sapClaimNbr", "disti_claimnbr", "transaction_code",
            "disti_name", "disti_branch", "disti_city", "quote_totalqty", "book_part", "ship_qty", "ship_date",
            "debit_number", "oppreg_nbr", "disti_invoice_nbr", "m_12nc", "rebateOver", "sold_to", "ship_to", "payer_to",
            "billing_to", "disti_invoice_item_number", "disti_cost_currency", "disti_resale_denom",
            "disti_resale_currency", "disti_bookcost", "purchasing_customer_name", "purchasing_cust_city",
            "purchasing_cust_zip", "disti_cost", "remainQty", "rebatePrice", "rebateTotal", "cost_denom", "dbc_denom",
            "purchasing_cust_state", "purchasing_cust_country", "endcust_name", "endcust_city", "endcust_loc",
            "endcust_zip", "endcust_state", "endcust_country", "status", "disti_accounting_nbr",
            "rejecttoapprove_remark" })
    public String getReceivePosListB() {
        Pos pos = new Pos();
        pos.setDisti_name(branchId);
        pos.setDisti_cost_currency(currency_name);
        pos.setStatus("3");
        pos.setFlag("1");
        pos.setType("2");
        pos.setShip_to(ship_to);
        pos.setDebitsOrRedits(debitsOrRedits);
        List<Pos> resultList = posService.searchPosListForAll(pos);
        poList = new ArrayList<Pos>();
        if (resultList != null) {
            for (Pos p : resultList) {
                if (!isInteger(p.getShip_qty()) && !isDouble(p.getShip_qty())) {
                    continue;
                }
                Double shipQty = new Double(p.getShip_qty());
                if ("Debits".equals(debitsOrRedits)) {
                    if (shipQty <= 0) {
                        continue;
                    }
                } else if ("Redits".equals(debitsOrRedits)) {
                    if (shipQty >= 0) {
                        continue;
                    }
                }
                DecimalFormat df = new DecimalFormat("0.0000");
                String rebatePriceStr = df.format(Double.valueOf(p.getDbc_denom()) - Double.valueOf(p.getCost_denom()));
                double rebatePrice = Double.valueOf(rebatePriceStr);
                p.setRebatePrice(rebatePrice);
                BigDecimal bd1 = new BigDecimal(Double.toString(rebatePrice));
                BigDecimal bd2 = new BigDecimal(p.getShip_qty());
                String rebateTotalStr = df.format(bd1.multiply(bd2).doubleValue());
                p.setRebateTotal(Double.valueOf(rebateTotalStr));

                poList.add(p);
            }
        }
        return JSON;
    }

    private static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    private static boolean isDouble(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(str).matches();
    }

    @PermissionSearch
    @JsonResult(field = "poList", include = { "id", "row_no", "sapClaimNbr", "transaction_code", "disti_name",
            "disti_branch", "disti_city", "quote_totalqty", "book_part", "ship_qty", "ship_date", "debit_number",
            "oppreg_nbr", "disti_invoice_nbr", "m_12nc", "rebateOver", "ship_to", "payer_to", "billing_to",
            "disti_invoice_item_number", "disti_cost_currency", "disti_resale_denom", "disti_resale_currency",
            "disti_bookcost", "purchasing_customer_name", "purchasing_cust_city", "purchasing_cust_zip", "disti_cost",
            "remainQty", "rebatePrice", "rebateTotal", "cost_denom", "dbc_denom", "purchasing_cust_state",
            "purchasing_cust_country", "endcust_name", "endcust_city", "endcust_loc", "endcust_zip", "endcust_state",
            "endcust_country", "status", "disti_accounting_nbr" })
    public String getReceivePosList() {
        Pos pos = new Pos();
        pos.setDisti_name(branchId);// Disti编码
        pos.setDisti_cost_currency(currency_name);
        pos.setStatus("3");
        pos.setFlag("1");
        pos.setType("2");
        pos.setShip_to(ship_to);
        poList = posService.searchPosListForAll(pos);
        for (Pos p : poList) {
            double rebatePrice = Double.valueOf(p.getDbc_denom()) - Double.valueOf(p.getCost_denom());
            p.setRebatePrice(rebatePrice);
            BigDecimal bd1 = new BigDecimal(Double.toString(rebatePrice));
            BigDecimal bd2 = new BigDecimal(p.getShip_qty());
            p.setRebateTotal(bd1.multiply(bd2).doubleValue());
        }
        return JSON;
    }

    public String getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(String uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    // 2007以上版本
    private String findOrderExcelXlsx(String path) {
        String resultMessage = "Success!";
        List<Pos> pList = new ArrayList<Pos>();
        try {
            FileInputStream file = new FileInputStream(path);
            XSSFWorkbook xwb = new XSSFWorkbook(file);
            XSSFSheet sheet = xwb.getSheetAt(0);
            XSSFRow row;
            if (sheet.getPhysicalNumberOfRows() <= 1) {
                return "Excel is null!";
            }

            long fileId = posService.getFileId();
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                Pos pos = new Pos();
                XSSFCell cell0 = row.getCell(0);
                if (cell0 != null) {
                    if ("".equals(cell0.getStringCellValue().trim())) {
                        continue;
                    }
                    cell0.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setTransaction_code(cell0.getStringCellValue().trim());
                } else {
                    continue;
                }

                XSSFCell cell1 = row.getCell(1);
                if (cell1 != null) {
                    cell1.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setDisti_name(cell1.getStringCellValue().trim());
                }

                XSSFCell cell2 = row.getCell(2);
                if (cell2 != null) {
                    cell2.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setDisti_branch(cell2.getStringCellValue().trim());
                }

                XSSFCell cell3 = row.getCell(3);
                if (cell3 != null) {
                    cell3.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setDisti_city(cell3.getStringCellValue().trim());
                }

                XSSFCell cell4 = row.getCell(4);
                if (cell4 != null) {
                    cell4.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setBook_part(cell4.getStringCellValue().trim());
                }

                XSSFCell cell5 = row.getCell(5);
                if (cell5 != null) {
                    cell5.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setShip_qty(cell5.getStringCellValue());
                }

                XSSFCell cell6 = row.getCell(6);
                if (cell6 != null) {
                    try {
                        Date ss1 = cell6.getDateCellValue();
                        cell6.setCellType(Cell.CELL_TYPE_STRING);
                        String ss = cell6.getStringCellValue().trim();
                        if (ss.length() <= 5) {
                            pos.setShip_date(DateUtil.getDateTime(ss1, "yyyyMMdd"));
                        } else if (ss.length() > 8) {
                            pos.setShip_date(ss);
                        } else {
                            Date dd = DateUtil.getDateFromStr(DateUtil.FormatDate(ss));

                            System.out.println(DateUtil.getDateTime(dd, "yyyyMMdd"));
                            pos.setShip_date(DateUtil.getDateTime(dd, "yyyyMMdd"));
                        }
                    } catch (Exception e) {
                        cell6.setCellType(Cell.CELL_TYPE_STRING);
                        String ss = cell6.getStringCellValue().trim();
                        pos.setShip_date(ss);
                    }
                }

                XSSFCell cell7 = row.getCell(7);
                if (cell7 != null) {
                    cell7.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setDebit_number(cell7.getStringCellValue().trim());
                }

                XSSFCell cell8 = row.getCell(8);
                if (cell8 != null) {
                    cell8.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setDisti_claimnbr(cell8.getStringCellValue().trim());
                }

                XSSFCell cell9 = row.getCell(9);
                if (cell9 != null) {
                    cell9.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setOppreg_nbr(cell9.getStringCellValue().trim());
                }

                XSSFCell cell10 = row.getCell(10);
                if (cell10 != null) {
                    cell10.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setCpn(cell10.getStringCellValue().trim());
                }

                XSSFCell cell11 = row.getCell(11);
                if (cell11 != null) {
                    cell11.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setDisti_invoice_nbr(cell11.getStringCellValue().trim());
                }

                XSSFCell cell12 = row.getCell(12);
                if (cell12 != null) {
                    cell12.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setDisti_invoice_item_number(cell12.getStringCellValue());
                }

                XSSFCell cell13 = row.getCell(13);
                if (cell13 != null) {
                    pos.setDisti_cost(String.valueOf(getCellValue(cell13)));
                } else {
                    pos.setDisti_cost("0");
                }

                XSSFCell cell15 = row.getCell(15);
                if (cell15 != null) {
                    cell15.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setDisti_cost_currency(cell15.getStringCellValue().trim());
                }

                XSSFCell cell22 = row.getCell(22);
                if (cell22 != null) {
                    try {
                        String resale = String.valueOf(getCellValue(cell22));
                        String resale_demon = String.valueOf(DoubleCalculateUtil.div(Double.valueOf(resale), 1d, 4));
                        pos.setDisti_resale_denom(resale_demon);
                    } catch (Exception e) {
                        e.printStackTrace();
                        pos.setDisti_resale_denom(cell22.getStringCellValue().trim());
                    }
                }

                XSSFCell cell23 = row.getCell(23);
                if (cell23 != null) {
                    cell23.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setDisti_resale_currency(cell23.getStringCellValue().trim());
                }

                XSSFCell cell25 = row.getCell(25);
                if (cell25 != null) {
                    cell25.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setPurchasing_customer_name(getCellValue(cell25));
                }

                XSSFCell cell26 = row.getCell(26);
                if (cell26 != null) {
                    cell26.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setPurchasing_cust_city(getCellValue(cell26));
                }

                XSSFCell cell27 = row.getCell(27);
                if (cell27 != null) {
                    cell27.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setPurchasing_cust_zip(getCellValue(cell27));
                }

                XSSFCell cell28 = row.getCell(28);
                if (cell28 != null) {
                    cell28.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setPurchasing_cust_state(getCellValue(cell28));
                }

                XSSFCell cell29 = row.getCell(29);
                if (cell29 != null) {
                    cell29.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setPurchasing_cust_country(getCellValue(cell29));
                }

                XSSFCell cell30 = row.getCell(30);
                if (cell30 != null) {
                    cell30.setCellType(Cell.CELL_TYPE_STRING);
                    if (null == cell30.getStringCellValue() || "".equals(cell30.getStringCellValue().trim())) { // 如果是空字符
                        pos.setEndcust_name(pos.getPurchasing_customer_name());
                        pos.setEndcust_city(pos.getPurchasing_cust_city());
                        pos.setEndcust_loc(pos.getPurchasing_cust_city());
                        pos.setEndcust_zip(pos.getPurchasing_cust_zip());
                        pos.setEndcust_state(pos.getPurchasing_cust_state());
                        pos.setEndcust_country(pos.getPurchasing_cust_country());
                    } else {
                        pos.setEndcust_name(cell30.getStringCellValue().trim());

                        XSSFCell cell31 = row.getCell(31);
                        if (cell31 != null) {
                            cell31.setCellType(Cell.CELL_TYPE_STRING);
                            pos.setEndcust_city(getCellValue(cell31));
                        }

                        XSSFCell cell32 = row.getCell(32);
                        if (cell32 != null) {
                            cell32.setCellType(Cell.CELL_TYPE_STRING);
                            pos.setEndcust_loc(getCellValue(cell32));
                        }

                        XSSFCell cell33 = row.getCell(33);
                        if (cell33 != null) {
                            cell33.setCellType(Cell.CELL_TYPE_STRING);
                            pos.setEndcust_zip(getCellValue(cell33));
                        }

                        XSSFCell cell34 = row.getCell(34);
                        if (cell34 != null) {
                            cell34.setCellType(Cell.CELL_TYPE_STRING);
                            pos.setEndcust_state(getCellValue(cell34));
                        }

                        XSSFCell cell35 = row.getCell(35);
                        if (cell35 != null) {
                            cell35.setCellType(Cell.CELL_TYPE_STRING);
                            pos.setEndcust_country(getCellValue(cell35));
                        }
                    }

                } else {
                    pos.setEndcust_name(pos.getPurchasing_customer_name());
                    pos.setEndcust_city(pos.getPurchasing_cust_city());
                    pos.setEndcust_loc(pos.getPurchasing_cust_city());
                    pos.setEndcust_zip(pos.getPurchasing_cust_zip());
                    pos.setEndcust_state(pos.getPurchasing_cust_state());
                    pos.setEndcust_country(pos.getPurchasing_cust_country());
                }
                pos.setStatus("9");
                pos.setCreated_user(this.getUser().getUserId());
                pos.setData_from("1");
                pos.setUpdate_user(this.getUser().getUserId());
                if (StringUtils.isNotEmpty(file_url) && StringUtils.isNotEmpty(file_url.trim())) {
                    try {
                        file_url = java.net.URLDecoder.decode(file_url, "UTF-8");
                        file_url = file_url.toUpperCase();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                pos.setFile_url(file_url);
                pos.setFile_id(fileId);
                pos.setType("1");
                pList.add(pos);
            }
            long result = 0;
            for (Pos pos : pList) {
                result = result + posService.createPosInfo(pos);
            }
            checkEDIForAll();

            if (result != 0) {
                resultMessage = "Success !";
            } else {
                resultMessage = "failed !";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resultMessage = "failed (Data format error)!";
        }

        return resultMessage;
    }

    /**
     * 
     * 
     * @param path
     * @return
     */

    // 2003版本
    private String findOrderExcelXls(String path) {
        String resultMessage = "";
        FileInputStream fileIn = null;
        Workbook rwb = null;
        List<Pos> pList = new ArrayList<Pos>();
        try {
            fileIn = new FileInputStream(uploadFile);
            rwb = Workbook.getWorkbook(fileIn);
            Sheet rs = rwb.getSheet(0);
            int column = 0;
            column = rs.getColumns();
            int actualRows = 0;
            /** 去除空行得到真实行数 **/
            actualRows = StockUtil.delEmptyRow(rs);
            if (actualRows == 0 && column == 0) {
                return "Excel is null!";
            } else {
                long fileId = posService.getFileId();
                for (int i = 1; i < actualRows; i++) {
                    Pos pos = new Pos();
                    pos.setTransaction_code(rs.getCell(0, i).getContents().trim());
                    pos.setDisti_name(rs.getCell(1, i).getContents().trim());
                    pos.setDisti_branch(rs.getCell(2, i).getContents().trim());
                    pos.setDisti_city(rs.getCell(3, i).getContents().trim());
                    pos.setBook_part(rs.getCell(4, i).getContents().trim());
                    pos.setShip_qty(rs.getCell(5, i).getContents().trim());
                    pos.setShip_date(rs.getCell(6, i).getContents().trim());
                    pos.setDebit_number(rs.getCell(7, i).getContents().trim());
                    pos.setDisti_claimnbr(rs.getCell(8, i).getContents().trim());
                    pos.setOppreg_nbr(rs.getCell(9, i).getContents().trim());
                    pos.setCpn(rs.getCell(10, i).getContents().trim());
                    pos.setDisti_invoice_nbr(rs.getCell(11, i).getContents().trim());
                    pos.setDisti_invoice_item_number(rs.getCell(12, i).getContents().trim());
                    jxl.Cell cell13 = rs.getCell(13, i);
                    if (cell13.getType() == CellType.NUMBER) {
                        pos.setDisti_cost(String.valueOf(((NumberCell) cell13).getValue()));
                    } else if (cell13.getType() == CellType.LABEL) {
                        pos.setDisti_cost(rs.getCell(13, i).getContents().trim());
                    }
                    pos.setDisti_cost_currency(rs.getCell(15, i).getContents().trim());
                    jxl.Cell cell22 = rs.getCell(22, i);
                    if (cell22.getType() == CellType.NUMBER) {
                        String resale = String.valueOf(((NumberCell) cell22).getValue());
                        String resale_demon = String.valueOf(DoubleCalculateUtil.div(Double.valueOf(resale), 1d, 4));
                        pos.setDisti_resale_denom(resale_demon);
                    } else if (cell22.getType() == CellType.LABEL) {
                        try {
                            String resale = rs.getCell(22, i).getContents().trim();
                            String resale_demon = String
                                    .valueOf(DoubleCalculateUtil.div(Double.valueOf(resale), 1d, 4));
                            pos.setDisti_resale_denom(resale_demon);
                        } catch (Exception e) {
                            pos.setDisti_resale_denom(rs.getCell(22, i).getContents().trim());
                        }
                    }
                    pos.setDisti_resale_currency(rs.getCell(23, i).getContents().trim());
                    pos.setPurchasing_customer_name(rs.getCell(25, i).getContents().trim());
                    pos.setPurchasing_cust_city(rs.getCell(26, i).getContents().trim());
                    pos.setPurchasing_cust_zip(rs.getCell(27, i).getContents().trim());
                    pos.setPurchasing_cust_state(rs.getCell(28, i).getContents().trim());
                    pos.setPurchasing_cust_country(rs.getCell(29, i).getContents().trim());
                    if ("".equals(rs.getCell(30, i).getContents().trim())) {
                        pos.setEndcust_name(pos.getPurchasing_customer_name());
                        pos.setEndcust_city(pos.getPurchasing_cust_city());
                        pos.setEndcust_loc(pos.getPurchasing_cust_city());
                        pos.setEndcust_zip(pos.getPurchasing_cust_zip());
                        pos.setEndcust_state(pos.getPurchasing_cust_state());
                        pos.setEndcust_country(pos.getPurchasing_cust_country());
                    } else {
                        pos.setEndcust_name(rs.getCell(30, i).getContents().trim());
                        pos.setEndcust_city(rs.getCell(31, i).getContents().trim());
                        pos.setEndcust_loc(rs.getCell(32, i).getContents().trim());
                        pos.setEndcust_zip(rs.getCell(33, i).getContents().trim());
                        pos.setEndcust_state(rs.getCell(34, i).getContents().trim());
                        pos.setEndcust_country(rs.getCell(35, i).getContents().trim());
                    }

                    pos.setStatus("9");
                    pos.setCreated_user(this.getUser().getUserId());
                    pos.setData_from("1");
                    if (StringUtils.isNotEmpty(file_url) && StringUtils.isNotEmpty(file_url.trim())) {
                        try {
                            file_url = java.net.URLDecoder.decode(file_url, "UTF-8");
                            file_url = file_url.toUpperCase();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                    pos.setFile_url(file_url);
                    pos.setFile_id(fileId);
                    pos.setType("1");
                    pList.add(pos);
                }

                long result = 0;
                for (Pos pos : pList) {
                    result = result + posService.createPosInfo(pos);
                }
                checkEDIForAll();

                if (result != 0) {
                    resultMessage = "Success !";
                } else {
                    resultMessage = "Fail!";
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            resultMessage = "Excel format error!";
        } catch (Exception e) {
            e.printStackTrace();
            resultMessage = "Format error!";
            return resultMessage;
        }
        return resultMessage;
    }

    // 判断03 or 07
    public String findOrderExcel() {
        String result;
        this.setSuccessMessage("");
        this.setFailMessage("");
        path = path.replace("Â ", " ");
        file_url = file_url.replace("Â ", " ");
        if (ExcelUtil.getExcelStyle(path).intValue() == 1) {
            result = findOrderExcelXls(uploadFile);
            if ("Success !".equals(result)) {
                this.setSuccessMessage("Success!");
            } else {
                this.setFailMessage(result);
            }
            return RESULT_MESSAGE;
        } else if (ExcelUtil.getExcelStyle(path).intValue() == 2) {
            result = findOrderExcelXlsx(uploadFile);
            if ("Success !".equals(result)) {
                this.setSuccessMessage("Success!");
            } else {
                this.setFailMessage(result);
            }
            return RESULT_MESSAGE;
        } else {
            this.setFailMessage("failed (Formqat error)!");
            return RESULT_MESSAGE;
        }
    }

    // EDI check 单条
    public String checkEDI() throws Exception {
        try {
            String[] s = ids.split(",");

            for (String s1 : s) {
                System.out.print(s1);
                posService.checkEDI(Long.valueOf(s1));
            }

        } catch (Exception e) {
            this.setFailMessage("Data error please contact the system administrator!");
            return RESULT_MESSAGE;
        }

        this.setSuccessMessage("Success!");
        return RESULT_MESSAGE;
    }

    // EDI check 多条
    public String checkEDIForAll() throws Exception {
        pos = new Pos();
        pos.setType("1");
        pos.setId(id);
        int num = posService.searchPosFileIdCount(pos);
        if (num >= 1) {
            idList = posService.searchPosFileId(pos);
            for (Integer fileId : idList) {
                pos.setFile_id(fileId);
                poList = posService.searchPosListForEDI(pos);
                for (Pos pos : poList) { // 单条更新逻辑
                    posService.checkEDI(Long.valueOf(pos.getId()));
                }
                pos.setFile_id(pos.getFile_id());
                pos.setType("1");
                int aa = posService.searchPosDetailListCount(pos);
                int bb = posService.searchPosDetailListCountForError(pos);
                pos.setStatus_num(bb + "/" + aa);
                posService.updatePos(pos);
            }
        }
        this.setSuccessMessage("Success!");
        return RESULT_MESSAGE;
    }

// POS Report页面查询
    @PermissionSearch
    @JsonResult(field = "poList", include = { "id", "transaction_code", "ship_date", "ship_date1", "ship_date2",
            "ship_date3", "book_part", "material_id", "material_exp", "cost_denom_USD", "cost_denom_EUR",
            "cost_denom_USD_total", "cost_denom_EUR_total", "material_state", "org_name_pc", "org_name_ec", "ship_qty",
            "debit_number", "disti_cost_currency", "cost_denom", "disti_name", "office_id", "disti_branch",
            "purchasing_customer_name", "purchasing_cust_city", "purchasing_cust_country", "purchasing_cust_zip",
            "purchasing_cust_state", "purchasing_cust_zip", "endcust_city", "endcust_loc", "endcust_zip",
            "endcust_state", "endcust_country", "endcust_name", "disti_resale_currency", "disti_resale_denom", "status",
            "disti_invoice_nbr", "disti_invoice_item_number" }, total = "total")
    public String getPosListForBb() {
        try {
            poList = getPosListForBb(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON;
    }

    private List<Pos> getPosListForBb(int from) throws IOException {

        Pos pos = new Pos();
        if (from == 1) {
            this.setPage(1);
            this.setRows(100001);
        }
        pos.setStart(this.getStart());
        pos.setEnd(this.getEnd());

        if (StringUtils.isNotEmpty(disti_name) && StringUtils.isNotEmpty(disti_name.trim())) {
            try {
                disti_name = java.net.URLDecoder.decode(disti_name, "UTF-8");
                disti_name = disti_name.toUpperCase();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (null != start_timeString && !"".equals(start_timeString))
            pos.setStart_time(DateUtil.getDateFromStr(start_timeString));

        if (null != end_timeString && !"".equals(end_timeString))
            pos.setEnd_time(DateUtil.getDateFromStr(end_timeString));

        pos.setDisti_name(disti_name);
        pos.setDisti_branch(disti_branch);
        pos.setDebit_number(debit_number);
        if (StringUtils.isNotEmpty(book_part) && StringUtils.isNotEmpty(book_part.trim())) {
            try {
                book_part = java.net.URLDecoder.decode(book_part, "UTF-8");
                book_part = book_part.toUpperCase();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        pos.setBook_part(book_part);
        if (StringUtils.isNotEmpty(purchasing_customer_name)
                && StringUtils.isNotEmpty(purchasing_customer_name.trim())) {
            try {
                purchasing_customer_name = java.net.URLDecoder.decode(purchasing_customer_name, "UTF-8");
                purchasing_customer_name = purchasing_customer_name.toUpperCase();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        pos.setPurchasing_customer_name(purchasing_customer_name);
        pos.setBook_part(book_part);
        if (StringUtils.isNotEmpty(endcust_name) && StringUtils.isNotEmpty(endcust_name.trim())) {
            try {
                endcust_name = java.net.URLDecoder.decode(endcust_name, "UTF-8");
                endcust_name = endcust_name.toUpperCase();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        pos.setEndcust_name(endcust_name);

        CmsTbDict searchCmsTbDict = new CmsTbDict();
        searchCmsTbDict.setItemId(Long.valueOf("14213"));
        double rate = 1.0;
        if (dictService != null) {
            cmsTbDict = dictService.getCmsTbDict(searchCmsTbDict);
            rate = Double.valueOf(cmsTbDict.getItemValue());
        }
        List<Pos> posDataList = new ArrayList<Pos>();
        total = posService.searchPosListCountForBb(pos);
        if (total > 0) {
            if (from == 1) {
                if (total > 100000) {
                    this.setFailMessage(
                            "Download exceeds the limit! Please limit the filter conditions or contact the POS manager to get data.");
                    throw new IOException();
                }
            }
            posDataList = posService.searchPosListForBb(pos);
            for (Pos p : posDataList) {

                // 日期处理
                try {
                    String str0 = p.getShip_date();
                    Date d1 = new SimpleDateFormat("yyyyMMdd").parse(str0);
                    SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy");
                    SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
                    String str1 = sdf0.format(d1);
                    String str2 = sdf1.format(d1);
                    String str3 = "";
                    if (Long.valueOf(str2) <= 3)
                        str3 = "Q1";
                    else if (Long.valueOf(str2) <= 6 && Long.valueOf(str2) > 3)
                        str3 = "Q2";
                    else if (Long.valueOf(str2) <= 9 && Long.valueOf(str2) > 6)
                        str3 = "Q3";
                    else if (Long.valueOf(str2) <= 12 && Long.valueOf(str2) > 9)
                        str3 = "Q4";
                    p.setShip_date1(str1 + str2);
                    p.setShip_date2(str1 + str3);
                    p.setShip_date3(str1);
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }

                // 汇率处理
                if (p.getDisti_resale_currency() != null) {
                    if (p.getDisti_resale_currency().equals("EUR")) {
                        p.setCost_denom_EUR(p.getDisti_resale_denom());
                        if (isNumber(p.getDisti_resale_denom())) {
                            DecimalFormat df = new DecimalFormat("0.0000");
                            p.setCost_denom_USD(
                                    String.valueOf(df.format((Double.valueOf(p.getDisti_resale_denom()) / rate))));
                        }
                    } else if (p.getDisti_resale_currency().equals("USD")) {
                        if (isNumber(p.getDisti_resale_denom())) {
                            DecimalFormat df = new DecimalFormat("0.0000");
                            p.setCost_denom_EUR(
                                    String.valueOf(df.format((Double.valueOf(p.getDisti_resale_denom()) * rate))));
                        }
                        p.setCost_denom_USD(p.getDisti_resale_denom());
                    }
                    java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
                    nf.setGroupingUsed(false);
                    if (null == p.getCost_denom_EUR() || !isNumber(p.getCost_denom_EUR())) {
                        p.setCost_denom_EUR("0");
                    }
                    if (null == p.getShip_qty() || !isNumber(p.getShip_qty())) {
                        p.setShip_qty("0");
                    }
                    Double d = (Double.valueOf(p.getCost_denom_EUR())) * (Double.valueOf(p.getShip_qty()));
                    p.setCost_denom_EUR_total(String.valueOf(nf.format(d)));

                    if (null == p.getCost_denom_USD() || !isNumber(p.getCost_denom_USD())) {
                        p.setCost_denom_USD("0");
                    }
                    Double e = (Double.valueOf(p.getCost_denom_USD())) * (Double.valueOf(p.getShip_qty()));
                    p.setCost_denom_USD_total(String.valueOf(nf.format(e)));
                }
            }
        }

        return posDataList;
    }

    // 旧版报表数据导出
    public void downloadExcelModelOld() throws Exception {
        try {
            List<String> list = new ArrayList<String>();
            list.add("STATUS");
            list.add("TRANSACTION CODE");
            list.add("SHIP_DATE");
            list.add("FISCPER [YYYYMMM] ");
            list.add("FISCQTR");
            list.add("FISCYR");
            list.add("12NC");
            list.add("Commercial Type");
            list.add("BOOK PART");
            list.add("SHIP_QTY");
            list.add("DEBIT_NUM");
            list.add("DISTI_INVOICE_NBR");
            list.add("DISTI_INVOICE_ITEM_NUMBER");
            list.add("DISTI NAME");
            list.add("DISTI HQ SOLDTO");
            list.add("DISTI BRANCH SOLDTO");
            list.add("PC_NAME");
            list.add("PC_LOCATION");
            list.add("PC_FREGION");
            list.add("PC_COUNTRY");
            list.add("PC_STATE");
            list.add("PC_ZIPCODE");
            list.add("EC_COUNTRY");
            list.add("EC_FREGION");
            list.add("EC_NAME");
            list.add("EC_LOCATION");
            list.add("EC_STATE");
            list.add("EC_ZIPCODE");
            list.add("DISTI_RESALE_CURRENCY_CODE");
            list.add("DISTI_RESALE_USD");
            list.add("DISTI_RESALE_USD_TOTAL");
            list.add("DISTI_RESALE_EUR");
            list.add("DISTI_RESALE_EUR_TOTAL");

            File source = new File("pos.xls");
            WritableWorkbook wwb = Workbook.createWorkbook(source);
            WritableSheet sheet = wwb.createSheet("pos", 0);
            Label label0 = null;
            Label label1 = null;
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

            for (int i = 0; i < list.size(); i++) {
                label0 = new Label(i, 0, list.get(i).toString());
                sheet.addCell(label0);
            }
            List<Pos> poList = new ArrayList<Pos>();
            Pos pos = new Pos();
            pos.setStart(this.getStart());
            pos.setEnd(this.getEnd());

            if (StringUtils.isNotEmpty(disti_name) && StringUtils.isNotEmpty(disti_name.trim())) {
                try {
                    disti_name = java.net.URLDecoder.decode(disti_name, "UTF-8");
                    disti_name = disti_name.toUpperCase();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            if (null != start_timeString && !"".equals(start_timeString))
                pos.setStart_time(DateUtil.getDateFromStr(start_timeString));
            if (null != end_timeString && !"".equals(end_timeString))
                pos.setEnd_time(DateUtil.getDateFromStr(end_timeString));
            pos.setDisti_name(disti_name);
            pos.setDisti_branch(disti_branch);
            pos.setDebit_number(debit_number);
            if (StringUtils.isNotEmpty(book_part) && StringUtils.isNotEmpty(book_part.trim())) {
                try {
                    book_part = java.net.URLDecoder.decode(book_part, "UTF-8");
                    book_part = book_part.toUpperCase();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            pos.setBook_part(book_part);
            if (StringUtils.isNotEmpty(purchasing_customer_name)
                    && StringUtils.isNotEmpty(purchasing_customer_name.trim())) {
                try {
                    purchasing_customer_name = java.net.URLDecoder.decode(purchasing_customer_name, "UTF-8");
                    purchasing_customer_name = purchasing_customer_name.toUpperCase();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            pos.setPurchasing_customer_name(purchasing_customer_name);
            pos.setBook_part(book_part);
            if (StringUtils.isNotEmpty(endcust_name) && StringUtils.isNotEmpty(endcust_name.trim())) {
                try {
                    endcust_name = java.net.URLDecoder.decode(endcust_name, "UTF-8");
                    endcust_name = endcust_name.toUpperCase();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            pos.setEndcust_name(endcust_name);

            CmsTbDict searchCmsTbDict = new CmsTbDict();
            searchCmsTbDict.setItemId(Long.valueOf("14213"));
            cmsTbDict = dictService.getCmsTbDict(searchCmsTbDict);
            double rate = Double.valueOf(cmsTbDict.getItemValue());

            poList = posService.searchPosListForBbAll(pos);
            for (Pos p : poList) {
                // 日期处理
                try {
                    String str0 = p.getShip_date();
                    Date d1 = new SimpleDateFormat("yyyyMMdd").parse(str0);
                    SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy");
                    SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
                    String str1 = sdf0.format(d1);
                    String str2 = sdf1.format(d1);
                    p.setShip_date1(str1 + str2);
                    p.setShip_date2(str1 + str2);
                    p.setShip_date3(str1);
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }

                // 汇率处理
                if (p.getDisti_resale_currency() != null) {
                    if (p.getDisti_resale_currency().equals("EUR")) {
                        p.setCost_denom_EUR(p.getDisti_resale_denom());
                        DecimalFormat df = new DecimalFormat("0.0000");
                        p.setCost_denom_USD(
                                String.valueOf(df.format((Double.valueOf(p.getDisti_resale_denom()) / rate))));
                    } else if (p.getDisti_resale_currency().equals("USD")) {
                        DecimalFormat df = new DecimalFormat("0.0000");
                        p.setCost_denom_EUR(
                                String.valueOf(df.format((Double.valueOf(p.getDisti_resale_denom()) * rate))));
                        p.setCost_denom_USD(p.getDisti_resale_denom());
                    }
                    // 大数字防止科学计数法
                    java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
                    nf.setGroupingUsed(false);
                    Double d = (Double.valueOf(p.getDisti_resale_denom())) * (Double.valueOf(p.getShip_qty()));
                    p.setCost_denom_EUR_total(String.valueOf(nf.format(d)));
                    Double e = 0.0;
                    if (null == p.getCost_denom_USD() || null == p.getShip_qty()) {
                    } else
                        e = (Double.valueOf(p.getCost_denom_USD())) * (Double.valueOf(p.getShip_qty()));
                    p.setCost_denom_USD_total(String.valueOf(nf.format(e)));
                }
            }

            for (int i = 0; i < poList.size(); i++) {
                label0 = new Label(0, i + 1, poList.get(i).getStatus());
                label1 = new Label(1, i + 1, poList.get(i).getTransaction_code());
                label2 = new Label(2, i + 1, poList.get(i).getShip_date());
                label3 = new Label(3, i + 1, poList.get(i).getShip_date1());
                label4 = new Label(4, i + 1, poList.get(i).getShip_date2());
                label5 = new Label(5, i + 1, poList.get(i).getShip_date3());
                label6 = new Label(6, i + 1, poList.get(i).getMaterial_id());
                label7 = new Label(7, i + 1, poList.get(i).getMaterial_exp());
                label8 = new Label(8, i + 1, poList.get(i).getBook_part());
                label9 = new Label(9, i + 1, poList.get(i).getShip_qty());
                label10 = new Label(10, i + 1, poList.get(i).getDebit_number());
                label11 = new Label(11, i + 1, poList.get(i).getDisti_invoice_nbr());
                label12 = new Label(12, i + 1, poList.get(i).getDisti_invoice_item_number());
                label13 = new Label(13, i + 1, poList.get(i).getDisti_name());
                label14 = new Label(14, i + 1, poList.get(i).getDisti_branch());
                label15 = new Label(15, i + 1, poList.get(i).getDisti_branch());
                label16 = new Label(16, i + 1, poList.get(i).getPurchasing_customer_name());
                label17 = new Label(17, i + 1, poList.get(i).getPurchasing_cust_city());
                label18 = new Label(18, i + 1, poList.get(i).getOrg_name_pc());
                label19 = new Label(19, i + 1, poList.get(i).getPurchasing_cust_country());
                label20 = new Label(20, i + 1, poList.get(i).getPurchasing_cust_state());
                label21 = new Label(21, i + 1, poList.get(i).getPurchasing_cust_zip());
                label22 = new Label(22, i + 1, poList.get(i).getEndcust_country());
                label23 = new Label(23, i + 1, poList.get(i).getOrg_name_ec());
                label24 = new Label(24, i + 1, poList.get(i).getEndcust_name());
                label25 = new Label(25, i + 1, poList.get(i).getEndcust_loc());
                label26 = new Label(26, i + 1, poList.get(i).getEndcust_state());
                label27 = new Label(27, i + 1, poList.get(i).getEndcust_zip());
                label28 = new Label(28, i + 1, poList.get(i).getDisti_resale_currency());
                label29 = new Label(29, i + 1, poList.get(i).getCost_denom_USD());
                label30 = new Label(30, i + 1, poList.get(i).getCost_denom_USD_total());
                label31 = new Label(31, i + 1, poList.get(i).getCost_denom_EUR());
                label32 = new Label(32, i + 1, poList.get(i).getCost_denom_EUR_total());

                sheet.addCell(label0);
                sheet.addCell(label1);
                sheet.addCell(label2);
                sheet.addCell(label3);
                sheet.addCell(label4);
                sheet.addCell(label5);
                sheet.addCell(label6);
                sheet.addCell(label7);
                sheet.addCell(label8);
                sheet.addCell(label9);
                sheet.addCell(label10);
                sheet.addCell(label11);
                sheet.addCell(label12);
                sheet.addCell(label13);
                sheet.addCell(label14);
                sheet.addCell(label15);
                sheet.addCell(label16);
                sheet.addCell(label17);
                sheet.addCell(label18);
                sheet.addCell(label19);
                sheet.addCell(label20);
                sheet.addCell(label21);
                sheet.addCell(label22);
                sheet.addCell(label23);
                sheet.addCell(label24);
                sheet.addCell(label25);
                sheet.addCell(label26);
                sheet.addCell(label27);
                sheet.addCell(label28);
                sheet.addCell(label29);
                sheet.addCell(label30);
                sheet.addCell(label31);
                sheet.addCell(label32);

            }
            wwb.write();
            wwb.close();

            display(source, "Pos.xls", ServletActionContext.getResponse());
        } catch (Exception e) {
            e.printStackTrace();
            this.setFailMessage("Pos report error!");
        }
    }

    // 新版报表数据导出
    public String downloadExcelModel() throws Exception {
        List<Pos> posDataLiat = new ArrayList<Pos>();
        try {
            posDataLiat = getPosListForBb(1);
        } catch (IOException e) {
            return RESULT_MESSAGE;
        }

        try {
            Date aDate = new Date();
            SimpleDateFormat aDateFormat = new SimpleDateFormat("yyyyMMdd");
            String dateString = aDateFormat.format(aDate);
            String fileName = "Pos" + dateString;

            CommonUtil.setExcelResponseInfo(getServletRequest(), getServletResponse(), fileName);
            // excel表头
            String[] titles = { "STATUS", "TRANSACTION CODE", "SHIP DATE", "FISCPER [YYYYMMM]", "FISCQTR", "FISCYR",
                    "12NC", "Commercial Type", "BOOK PART", "SHIP_QTY", "DEBIT_NUM", "DISTI_INVOICE_NBR",
                    "DISTI_INVOICE_ITEM_NUMBER", "DISTI NAME", "DISTI HQ SOLDTO", "DISTI BRANCH SOLDTO", "PC_NAME",
                    "PC_LOCATION", "PC_FREGION", "PC_COUNTRY", "PC_STATE", "PC_ZIPCODE", "EC_COUNTRY", "EC_FREGION",
                    "EC_NAME", "EC_LOCATION", "EC_STATE", "EC_ZIPCODE", "DISTI_RESALE_CURRENCY_CODE",
                    "DISTI_RESALE_USD", "DISTI_RESALE_USD_TOTAL", "DISTI_RESALE_EUR", "DISTI_RESALE_EUR_TOTAL" };
            String[] keys = { "status", "transaction_code", "ship_date", "ship_date1", "ship_date2", "ship_date3",
                    "material_id", "material_exp", "book_part", "ship_qty", "debit_number", "disti_invoice_nbr",
                    "disti_invoice_item_number", "disti_name", "disti_branch", "disti_branch",
                    "purchasing_customer_name", "purchasing_cust_city", "org_name_pc", "purchasing_cust_country",
                    "purchasing_cust_state", "purchasing_cust_zip", "endcust_country", "org_name_ec", "endcust_name",
                    "endcust_loc", "endcust_state", "endcust_zip", "disti_resale_currency", "cost_denom_USD",
                    "cost_denom_USD_total", "cost_denom_EUR", "cost_denom_EUR_total" };
            List<Map<String, String>> li = new ArrayList<Map<String, String>>();
            for (Pos p : posDataLiat) {
                if (p.getStatus().equals("1")) {
                    p.setStatus("Pending");
                } else if (p.getStatus().equals("0")) {
                    p.setStatus("Reject");
                } else if (p.getStatus().equals("3")) {
                    p.setStatus("Approved");
                } else if (p.getStatus().equals("9")) {
                    p.setStatus("Transfer");
                }
                li.add(CommonUtil.transBean2Map(p));
            }
            CommonUtil.exportExcel(li, titles, keys, getServletResponse().getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean display(File file, String fileName, HttpServletResponse response) {
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

    // 旧版数据下载(单条)
    public void downloadExcelModelForOneOld() {
        try {
            List<String> list = new ArrayList<String>();
            list.add("STATUS");
            list.add("ERROR CODES");
            list.add("TRANSACTION CODE");
            list.add("DISTI");
            list.add("DISTIBRANCH");
            list.add("DISTI ACCT#");
            list.add("BOOKPART");
            list.add("SHIP QTY");
            list.add("SHIP DATE");
            list.add("DEBITNUMBER");
            list.add("DISTICLAIMNBR");
            list.add("OPPREGNBR");
            list.add("CPN");
            list.add("DISTIINVOICENBR");
            list.add("DISTIINVOICEITEMNUMBER");
            list.add("DISTICOST");
            list.add("DISTICOSTDENOM");
            list.add("DISTICOSTCURRENCY");
            list.add("DISTI COST EXCHANGERATE");
            list.add("DISTIBOOKCOST");
            list.add("DBCDENOM");
            list.add("DBCCURRENCYCODE");
            list.add("DBCEXCHANGERATE");
            list.add("DISTIRESALE");
            list.add("DISTIRESALEDENOM");
            list.add("DISTI RESALE CURRENCY");
            list.add("DISTIRESALEEXCHANGERATE");
            list.add("PURCHASINGCUSTOMER NAME");
            list.add("PURCHASING CUST CITY");
            list.add("PURCHASING CUST ZIP");
            list.add("PURCHASING CUST STATE");
            list.add("PURCHASING CUST COUNTRY");
            list.add("ENDCUST NAME");
            list.add("ENDCUST CITY");
            list.add("ENDCUSTLOC");
            list.add("ENDCUST ZIP");
            list.add("ENDCUST STATE");
            list.add("ENDCUSTCOUNTRY");

            File source = new File("pos.xls");
            WritableWorkbook wwb = Workbook.createWorkbook(source);
            WritableSheet sheet = wwb.createSheet("pos", 0);
            Label label0 = null;
            Label label1 = null;
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

            for (int i = 0; i < list.size(); i++) {
                label0 = new Label(i, 0, list.get(i).toString());
                sheet.addCell(label0);
            }
            List<Pos> poList = new ArrayList<Pos>();
            pos = new Pos();

            if (StringUtils.isNotEmpty(disti_name) && StringUtils.isNotEmpty(disti_name.trim())) {
                try {
                    disti_name = java.net.URLDecoder.decode(disti_name, "UTF-8");
                    disti_name = disti_name.toUpperCase();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            // pos.setDisti_name(disti_name);
            // pos.setDisti_cost_currency(currency_name);
            pos.setStatus(status);
            if (StringUtils.isNotEmpty(book_part) && StringUtils.isNotEmpty(book_part.trim())) {
                try {
                    book_part = java.net.URLDecoder.decode(book_part, "UTF-8");
                    book_part = book_part.toUpperCase();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            pos.setBook_part(book_part);
            pos.setFile_id(file_id);
            if (null != start_timeString && !"".equals(start_timeString))
                pos.setStart_time(DateUtil.getDateFromStr(start_timeString));
            if (null != end_timeString && !"".equals(end_timeString))
                pos.setEnd_time(DateUtil.getDateFromStr(end_timeString));
            pos.setType("1");

            pos.setStart(this.getStart());
            pos.setEnd(this.getEnd());

            poList = posService.searchPosDetailListByIdForOne(pos);

            for (int i = 0; i < poList.size(); i++) {
                label0 = new Label(0, i + 1, poList.get(i).getStatus());
                label1 = new Label(1, i + 1, poList.get(i).getTips());
                label2 = new Label(2, i + 1, poList.get(i).getTransaction_code());
                label3 = new Label(3, i + 1, poList.get(i).getDisti_name());
                label4 = new Label(4, i + 1, poList.get(i).getDisti_branch());
                label5 = new Label(5, i + 1, poList.get(i).getDisti_city());
                label6 = new Label(6, i + 1, poList.get(i).getBook_part());
                label7 = new Label(7, i + 1, poList.get(i).getShip_qty());
                label8 = new Label(8, i + 1, poList.get(i).getShip_date());
                label9 = new Label(9, i + 1, poList.get(i).getDebit_number());
                label10 = new Label(10, i + 1, poList.get(i).getDisti_claimnbr());
                label11 = new Label(11, i + 1, poList.get(i).getOppreg_nbr());
                label12 = new Label(12, i + 1, poList.get(i).getCpn());
                label13 = new Label(13, i + 1, poList.get(i).getDisti_invoice_nbr());
                label14 = new Label(14, i + 1, poList.get(i).getDisti_invoice_item_number());
                label15 = new Label(15, i + 1, poList.get(i).getDisti_cost());
                label16 = new Label(16, i + 1, poList.get(i).getCost_denom());// DISTICOSTDENON
                label17 = new Label(17, i + 1, poList.get(i).getDisti_cost_currency());
                // label16 = new Label(16, i+1,
                // poList.get(i).getPurchasing_cust_country());
                // label17 = new Label(17, i+1,
                // poList.get(i).getPurchasing_cust_state());
                // label18 = new Label(18, i+1,
                // poList.get(i).getPurchasing_cust_zip());
                // label19 = new Label(19, i+1,
                // poList.get(i).getEndcust_country());
                // label20 = new Label(20, i+1, poList.get(i).getOrg_name_ec());
                // label21 = new Label(21, i+1,
                // poList.get(i).getEndcust_name());
                label18 = new Label(18, i + 1, poList.get(i).getDisti_cost_exchangeRate());
                // label16 = new Label(16, i+1,
                // poList.get(i).getDisti_resale_denom());
                // label17 = new Label(17, i+1,
                // poList.get(i).getDisti_resale_currency());
                label19 = new Label(19, i + 1, poList.get(i).getDisti_bookcost());
                label20 = new Label(20, i + 1, poList.get(i).getDbc_denom());
                label21 = new Label(21, i + 1, poList.get(i).getDbc_currency_code());
                label22 = new Label(22, i + 1, poList.get(i).getDbc_exchange_rate());
                label23 = new Label(23, i + 1, poList.get(i).getDisti_resale());
                label24 = new Label(24, i + 1, poList.get(i).getDisti_resale_denom());
                label25 = new Label(25, i + 1, poList.get(i).getDisti_resale_currency());
                label26 = new Label(26, i + 1, poList.get(i).getDisti_resale_exchange_rate());
                label27 = new Label(27, i + 1, poList.get(i).getPurchasing_customer_name());
                label28 = new Label(28, i + 1, poList.get(i).getPurchasing_cust_city());
                label29 = new Label(29, i + 1, poList.get(i).getPurchasing_cust_zip());
                label30 = new Label(30, i + 1, poList.get(i).getPurchasing_cust_state());
                label31 = new Label(31, i + 1, poList.get(i).getPurchasing_cust_country());
                label32 = new Label(32, i + 1, poList.get(i).getEndcust_name());
                label33 = new Label(33, i + 1, poList.get(i).getEndcust_city());
                label34 = new Label(34, i + 1, poList.get(i).getEndcust_loc());
                label35 = new Label(35, i + 1, poList.get(i).getEndcust_zip());
                label36 = new Label(36, i + 1, poList.get(i).getEndcust_state());
                label37 = new Label(37, i + 1, poList.get(i).getEndcust_country());

                sheet.addCell(label0);
                sheet.addCell(label1);
                sheet.addCell(label2);
                sheet.addCell(label3);
                sheet.addCell(label4);
                sheet.addCell(label5);
                sheet.addCell(label6);
                sheet.addCell(label7);
                sheet.addCell(label8);
                sheet.addCell(label9);
                sheet.addCell(label10);
                sheet.addCell(label11);
                sheet.addCell(label12);
                sheet.addCell(label13);
                sheet.addCell(label14);
                sheet.addCell(label15);
                sheet.addCell(label16);
                sheet.addCell(label17);
                sheet.addCell(label18);
                sheet.addCell(label19);
                sheet.addCell(label20);
                sheet.addCell(label21);
                sheet.addCell(label22);
                sheet.addCell(label23);
                sheet.addCell(label24);
                sheet.addCell(label25);
                sheet.addCell(label26);
                sheet.addCell(label27);
                sheet.addCell(label28);
                sheet.addCell(label29);
                sheet.addCell(label30);
                sheet.addCell(label31);
                sheet.addCell(label32);
                sheet.addCell(label33);
                sheet.addCell(label34);
                sheet.addCell(label35);
                sheet.addCell(label36);
                sheet.addCell(label37);
            }
            wwb.write();
            wwb.close();

            display(source, "Pos.xls", ServletActionContext.getResponse());
        } catch (Exception e) {
            this.setFailMessage("Pos output error!");
        }
    }

    // 新版数据下载(单条)
    public void downloadExcelModelForOne() throws Exception {
        try {
            this.setPage(1);
            this.setRows(1000000);
            // 查询数据
            getPosListById();

            Date aDate = new Date();
            SimpleDateFormat aDateFormat = new SimpleDateFormat("yyyyMMdd");
            String dateString = aDateFormat.format(aDate);
            String fileName = "Pos" + dateString;

            CommonUtil.setExcelResponseInfo(getServletRequest(), getServletResponse(), fileName);
            // excel表头
            String[] titles = { "STATUS", "ERROR CODES", "TRANSACTION CODE", "DISTI", "DISTI BRANCH", "DISTI ACCT#",
                    "BOOK PART", "SHIP QTY", "SHIP DATE", "DEBIT NUMBER", "OPPREG NBR", "DISTI INVOICE NBR",
                    "DISTI INVOICE ITEM NUMBER", "DISTI COST CURRENCY", "DISTI RESALE DENOM", "DISTI RESALE CURRENCY",
                    "PURCHASINGCUSTOMER NAME", "PURCHASING CUST CITY", "PURCHASING CUST ZIP", "PURCHASING CUST STATE",
                    "PURCHASING CUST COUNTRY", "ENDCUST NAME", "ENDCUST CITY", "ENDCUST LOC", "ENDCUST ZIP",
                    "ENDCUST STATE", "ENDCUST COUNTRY" };
            String[] keys = { "status", "tips", "transaction_code", "disti_name", "disti_branch", "disti_city",
                    "book_part", "ship_qty", "ship_date", "debit_number", "oppreg_nbr", "disti_invoice_nbr",
                    "disti_invoice_item_number", "disti_cost_currency", "disti_resale_denom", "disti_resale_currency",
                    "purchasing_customer_name", "purchasing_cust_city", "purchasing_cust_zip", "purchasing_cust_state",
                    "purchasing_cust_country", "endcust_name", "endcust_city", "endcust_loc", "endcust_zip",
                    "endcust_state", "endcust_country" };
            List<Map<String, String>> li = new ArrayList<Map<String, String>>();
            for (Pos p : poList) {
                if (p.getStatus().equals("1")) {
                    p.setStatus("Pending");
                } else if (p.getStatus().equals("0")) {
                    p.setStatus("Reject");
                } else if (p.getStatus().equals("3")) {
                    p.setStatus("Approved");
                } else if (p.getStatus().equals("9")) {
                    p.setStatus("Transfer");
                }
                li.add(CommonUtil.transBean2Map(p));
            }
            CommonUtil.exportExcel(li, titles, keys, getServletResponse().getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PermissionSearch
    @JsonResult(field = "poList", include = { "id", "type", "file_id", "file_url", "status", "statue_num", "row_no",
            "m_12nc", "sapClaimNbr", "transaction_code", "disti_name", "disti_branch", "disti_city", "book_part",
            "ship_qty", "ship_date", "debit_number", "disti_claimnbr", "oppreg_nbr", "cpn", "disti_invoice_nbr",
            "disti_invoice_item_number", "disti_cost", "cost_denom", "disti_cost_currency", "disti_cost_exchangeRate",
            "disti_bookcost", "dbc_denom", "dbc_currency_code", "dbc_exchange_rate", "disti_resale",
            "disti_resale_denom", "disti_resale_currency", "getDisti_resale_exchange_rate", "purchasing_customer_name",
            "purchasing_cust_city", "purchasing_cust_zip", "purchasing_cust_country", "purchasing_cust_state",
            "endcust_name", "endcust_city", "endcust_loc", "endcust_zip", "endcust_state", "endcust_country", "tips",
            "disti_accounting_nbr", "data_from" }, total = "total")
    public String searchPosTrackingDetail() {
        try {
            poList = searchPosTrackingDetailList(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON;
    }

    public String getQuote_id() {
        return quote_id;
    }

    public void setQuote_id(String quote_id) {
        this.quote_id = quote_id;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String resetPos() {
        pos = new Pos();
        pos.setId(Long.valueOf(id));
        posService.resetPos(pos);
        this.setSuccessMessage("Success!");
        return RESULT_MESSAGE;
    }

    public String resetClaim() {
        String ids1[] = ids.split(",");
        for (String id : ids1) {
            pos = new Pos();
            pos.setId(Long.valueOf(id));
            posService.resetPos(pos);
        }
        this.setSuccessMessage("Success!");
        return RESULT_MESSAGE;
    }

    public String approveClaim() {
        String ids1[] = ids.split(",");
        for (String id : ids1) {
            pos = new Pos();
            pos.setId(Long.valueOf(id));
            pos.setStatus("3");
            pos.setTips(Escape.unescape(remark));
            posService.approvePos(pos);
        }
        this.setSuccessMessage("Success!");
        return RESULT_MESSAGE;
    }

    private List<Pos> searchPosTrackingDetailList(int from) throws IOException {

        pos = new Pos();

        if (StringUtils.isNotEmpty(disti_name) && StringUtils.isNotEmpty(disti_name.trim())) {
            try {
                disti_name = java.net.URLDecoder.decode(disti_name, "UTF-8");
                pos.setDisti_name(disti_name.toUpperCase());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (StringUtils.isNotEmpty(purchasing_customer_name)
                && StringUtils.isNotEmpty(purchasing_customer_name.trim())) {
            try {
                purchasing_customer_name = java.net.URLDecoder.decode(purchasing_customer_name, "UTF-8");
                pos.setPurchasing_customer_name(purchasing_customer_name.toUpperCase());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (StringUtils.isNotEmpty(endcust_name) && StringUtils.isNotEmpty(endcust_name.trim())) {
            try {
                endcust_name = java.net.URLDecoder.decode(endcust_name, "UTF-8");
                pos.setEndcust_name(endcust_name.toUpperCase());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (StringUtils.isNotEmpty(book_part) && StringUtils.isNotEmpty(book_part.trim())) {
            try {
                book_part = java.net.URLDecoder.decode(book_part, "UTF-8");
                pos.setBook_part(book_part.toUpperCase());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (StringUtils.isNotEmpty(file_ids) && StringUtils.isNotEmpty(file_ids.trim())) {
            pos.setFile_ids("(" + file_ids + ")");
        }

        if (StringUtils.isNotEmpty(disti_invoice_nbr) && StringUtils.isNotEmpty(disti_invoice_nbr.trim())) {
            try {
                disti_invoice_nbr = java.net.URLDecoder.decode(disti_invoice_nbr, "UTF-8");
                pos.setDisti_invoice_nbr(disti_invoice_nbr.toUpperCase());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        if (null != start_timeString && !"".equals(start_timeString))
            pos.setStart_time(DateUtil.getDateFromStr(start_timeString));
        if (null != end_timeString && !"".equals(end_timeString))
            pos.setEnd_time(DateUtil.getDateFromStr(end_timeString));

        if (status == null)
            status = "(0, 1, 3, 9)";
        pos.setStatus(status);
        if (data_from == null)
            data_from = "(1, 2)";
        pos.setData_from(data_from);
        pos.setType(type);
        if (from == 1) {
            this.setPage(1);
            this.setRows(100001);
        }
        pos.setStart(this.getStart());
        pos.setEnd(this.getEnd());
        if (start_timeString != null && !"".equals(start_timeString))
            pos.setShip_dateStart(start_timeString.replace("-", ""));
        if (start_timeString != null && !"".equals(start_timeString))
            pos.setShip_dateEnd(end_timeString.replace("-", ""));
        total = posService.searchPosTrackingDetailCount(pos);
        List<Pos> posList = new ArrayList<Pos>();
        if (total > 0) {
            if (from == 1) {
                if (total > 100000) {
                    this.setFailMessage(
                            "Download exceeds the limit! Please limit the filter conditions or contact the POS manager to get data.");
                    throw new IOException();
                }
            }
            posList = posService.searchPosTrackingDetail(pos);
        }

        return posList;
    }

    public String downloadPosTrackingDetail() throws Exception {
        List<Pos> posList = new ArrayList<Pos>();
        try {
            posList = searchPosTrackingDetailList(1);
        } catch (IOException e) {
            return RESULT_MESSAGE;
        }

        try {
            Date aDate = new Date();
            SimpleDateFormat aDateFormat = new SimpleDateFormat("yyyyMMdd");
            String dateString = aDateFormat.format(aDate);
            String fileName = "POS_Tracking_Detail" + dateString;

            CommonUtil.setExcelResponseInfo(getServletRequest(), getServletResponse(), fileName);
            // excel表头
            String[] titles = { "STATUS", "TRANSACTION CODE", "DISTI", "DISTIBRANCH", "DISTI ACCT#", "BOOKPART",
                    "SHIP QTY", "SHIP DATE", "DEBITNUMBER", "DISTICLAIMNBR", "OPPREGNBR", "CPN", "DISTIINVOICENBR",
                    "DISTIINVOICEITEMNUMBER", "DISTICOST", "DISTICOSTDENOM", "DISTICOSTCURRENCY",
                    "DISTI COST EXCHANGERATE", "DISTIBOOKCOST", "DBCDENOM", "DBCCURRENCYCODE", "DBCEXCHANGERATE",
                    "DISTIRESALE", "DISTIRESALEDENOM", "DISTI RESALE CURRENCY", "DISTIRESALEEXCHANGERATE",
                    "PURCHASINGCUSTOMER NAME", "PURCHASING CUST CITY", "PURCHASING CUST ZIP", "PURCHASING CUST STATE",
                    "PURCHASING CUST COUNTRY", "ENDCUST NAME", "ENDCUST CITY", "ENDCUSTLOC", "ENDCUST ZIP",
                    "ENDCUST STATE", "ENDCUSTCOUNTRY", "VALIDATIONERRORS", "DISTIACCOUNTINGNBR", "DATAFROM" };
            String[] keys = { "statusInfo", "transaction_code", "disti_name", "disti_branch", "disti_city", "book_part",
                    "ship_qty", "ship_date", "debit_number", "disti_claimnbr", "oppreg_nbr", "cpn", "disti_invoice_nbr",
                    "disti_invoice_item_number", "disti_cost", "cost_denom", "disti_cost_currency",
                    "disti_cost_exchangeRate", "disti_bookcost", "dbc_denom", "dbc_currency_code", "dbc_exchange_rate",
                    "disti_resale", "disti_resale_denom", "disti_resale_currency", "getDisti_resale_exchange_rate",
                    "purchasing_customer_name", "purchasing_cust_city", "purchasing_cust_zip", "purchasing_cust_state",
                    "purchasing_cust_country", "endcust_name", "endcust_city", "endcust_loc", "endcust_zip",
                    "endcust_state", "endcust_country", "tips", "disti_accounting_nbr", "data_from" };

            List<Map<String, String>> li = new ArrayList<Map<String, String>>();
            for (Pos p : posList) {
                li.add(CommonUtil.transBean2Map(p));
            }
            CommonUtil.exportExcel(li, titles, keys, getServletResponse().getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        posList.clear();
        return null;
    }

    // 跳转编辑PCEC页面
    public String toUpdatePCEC() {
        pos = new Pos();
        pos.setId(Long.valueOf(id));
        pos = posService.getPosById(pos);
        return "toUpdatePCEC";
    }

    // PC/EC编辑
    public String updatePCEC() {

        this.setSuccessMessage("");
        this.setFailMessage("");
        Pos p = new Pos();
        p.setId(pos.getId());
        p.setPurchasing_cust_zip(pos.getPurchasing_cust_zip());
        p.setPurchasing_cust_state(pos.getPurchasing_cust_state());
        p.setPurchasing_cust_country(pos.getPurchasing_cust_country());
        p.setEndcust_zip(pos.getEndcust_zip());
        p.setEndcust_state(pos.getEndcust_state());
        p.setEndcust_country(pos.getEndcust_country());
        long result = posService.updatePCEC(p);
        if (result > 0) {
            this.setSuccessMessage("Success!");
        } else {
            this.setFailMessage("Failed!");
        }

        return RESULT_MESSAGE;
    }

    // 新版导出rebate order xcfeng
    public void downloadExcelForRebateOrder() throws Exception {
        try {
            this.setPage(1);
            this.setRows(1000000);
            // 查询数据
            getReceivePosListB();

            Date aDate = new Date();
            SimpleDateFormat aDateFormat = new SimpleDateFormat("yyyyMMdd");
            String dateString = aDateFormat.format(aDate);
            String fileName = "Pos" + dateString;

            CommonUtil.setExcelResponseInfo(getServletRequest(), getServletResponse(), fileName);
            // excel表头
            String[] titles = { "Disti Name", "Paycode", "Book Part", "Ship Date", "SHIP QTY", "QUOTE NUM", "INVOICE",
                    "INVOICE item number", "Cost", "DBC", "DEBIT PRICE", "CURRENCY", "PRICE TOTAL", "PC Name",
                    "EC Name", "APPROVE REMARK" };
            String[] keys = { "disti_name", "disti_branch", "book_part", "ship_date", "ship_qty", "debit_number",
                    "disti_invoice_nbr", "disti_invoice_item_number", "cost_denom", "dbc_denom", "rebatePrice",
                    "disti_cost_currency", "rebateTotal", "purchasing_customer_name", "endcust_name",
                    "rejecttoapprove_remark" };
            List<Map<String, String>> li = new ArrayList<Map<String, String>>();
            for (Pos p : poList) {
                li.add(CommonUtil.transBean2Map(p));
            }
            CommonUtil.exportExcel(li, titles, keys, getServletResponse().getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String status1;

    public String getStatus1() {
        return status1;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }

    public String approvePos() {
        pos = new Pos();
        String[] s = ids.split(",");
        for (String s1 : s) {
            pos.setId(Long.valueOf(s1));
            pos.setStatus(status1);
            posService.approvePos(pos);
        }
        this.setSuccessMessage("Success!");
        return RESULT_MESSAGE;
    }

    private String getCellValue(XSSFCell cell) {
        try {
            String cellValue = "";
            switch (cell.getCellType()) {
            case XSSFCell.CELL_TYPE_STRING:
                cellValue = cell.getRichStringCellValue().getString().trim();
                break;
            case XSSFCell.CELL_TYPE_NUMERIC:
                Double dd = cell.getNumericCellValue();

                String ss = String.valueOf(dd);
                if (ss.length() > 15) {
                    ss = ss.substring(0, ss.length() - 1);
                    dd = Double.valueOf(ss);
                    DecimalFormat dfff = new DecimalFormat("#.0000");
                    dd = Double.valueOf(dfff.format(dd));
                }

                BigDecimal b1 = new BigDecimal(Double.toString(dd));
                cellValue = String.valueOf(b1);
                break;
            case XSSFCell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue()).trim();
                break;
            case XSSFCell.CELL_TYPE_FORMULA:
                cellValue = cell.getCellFormula();
                break;
            default:
                cellValue = "";
            }
            return cellValue;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String rejectToApproveRemark() {
        return "rejectToApproveRemark";
    }

    public String searchClaimErrorCode() {
        return "searchClaimErrorCode";
    }

    @PermissionSearch
    @JsonResult(field = "poList", include = { "disti_name", "purchasing_customer_name", "purchasing_cust_city",
            "purchasing_cust_country", "endcust_name", "endcust_city", "endcust_country" }, total = "total")
    public String getClaimErrorCodeDetail() {
        Pos pos = new Pos();
        pos.setId(id);
        poList = posService.getClaimErrorCodeDetail(pos);

        return JSON;
    }

    private boolean isNumber(String input) {
        String reg = "-?[0-9]*(\\.?)[0-9]*";
        if (input == null || "".equals(input) || "".equals(input.trim())) {
            return false;
        }

        return Pattern.matches(reg, input);
    }

    public IDesignRegService getDesignRegService() {
        return designRegService;
    }

    public void setDesignRegService(IDesignRegService designRegService) {
        this.designRegService = designRegService;
    }
}
