package com.jingtong.platform.pos.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.common.CommonUtil;
import com.jingtong.platform.endCustomer.pojo.ECAlias;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.framework.util.DateUtil;
import com.jingtong.platform.framework.util.ExcelUtil;
import com.jingtong.platform.framework.util.StockUtil;
import com.jingtong.platform.framework.util.XssUtils;
import com.jingtong.platform.pos.pojo.Pos;
import com.jingtong.platform.pos.service.IPosService;
import com.jingtong.platform.quote.pojo.QuoteDetail;

import jxl.CellType;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/***
 * 
 * @author mk
 * @createDate 2016-5-24
 * 
 */

public class ClaimAction extends BaseAction {

    private static final long serialVersionUID = 8326843659323817600L;
    private String uploadFile;
    private List<Pos> poList;
    private List<QuoteDetail> quList;
    private List<ECAlias> ecList;
    private List<Integer> idList;
    private int total;
    private String branchId;
    private String currency_name;
    private String path;
    private Pos pos;
    private long file_id;
    private String file_url;
    private String status;
    private Date start_time;
    private Date end_time;
    private String start_timeString;
    private String end_timeString;
    private String upload_start_timeString;
    private String upload_end_timeString;

    private String order_start_timeString;
    private String order_end_timeString;
    private String data_from;

    private String pc_ec_name;
    private String error_code;
    private String invoice_number;

    public String getData_from() {
        return data_from;
    }

    public void setData_from(String data_from) {
        this.data_from = data_from;
    }

    public String getPc_ec_name() {
        return pc_ec_name;
    }

    public void setPc_ec_name(String pc_ec_name) {
        this.pc_ec_name = pc_ec_name;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(String invoice_number) {
        this.invoice_number = invoice_number;
    }

    public String getOrder_start_timeString() {
        return order_start_timeString;
    }

    public void setOrder_start_timeString(String order_start_timeString) {
        this.order_start_timeString = order_start_timeString;
    }

    public String getOrder_end_timeString() {
        return order_end_timeString;
    }

    public void setOrder_end_timeString(String order_end_timeString) {
        this.order_end_timeString = order_end_timeString;
    }

    private String disti_invoice_nbr;
    private String ids;

    public String getUpload_start_timeString() {
        return upload_start_timeString;
    }

    public void setUpload_start_timeString(String upload_start_timeString) {
        this.upload_start_timeString = upload_start_timeString;
    }

    public String getUpload_end_timeString() {
        return upload_end_timeString;
    }

    public void setUpload_end_timeString(String upload_end_timeString) {
        this.upload_end_timeString = upload_end_timeString;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getDisti_invoice_nbr() {
        return disti_invoice_nbr;
    }

    public void setDisti_invoice_nbr(String disti_invoice_nbr) {
        this.disti_invoice_nbr = disti_invoice_nbr;
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

    private String material_name;
    private String disti_name;
    private String disti_branch;
    private String debit_number;
    private String book_part;
    private String purchasing_customer_name;
    private String endcust_name;
    private String sapClaimNbr;

    private IPosService posService;

    public IPosService getPosService() {
        return posService;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public void setPosService(IPosService posService) {
        this.posService = posService;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public String getSapClaimNbr() {
        return sapClaimNbr;
    }

    public void setSapClaimNbr(String sapClaimNbr) {
        this.sapClaimNbr = sapClaimNbr;
    }

    public String getDebit_number() {
        return debit_number;
    }

    public void setDebit_number(String debit_number) {
        this.debit_number = debit_number;
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

    public String getDisti_branch() {
        return disti_branch;
    }

    public void setDisti_branch(String disti_branch) {
        this.disti_branch = disti_branch;
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

    public Pos getPos() {
        return pos;
    }

    public void setPos(Pos pos) {
        this.pos = pos;
    }

    public List<ECAlias> getEcList() {
        return ecList;
    }

    public void setEcList(List<ECAlias> ecList) {
        this.ecList = ecList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public long getFile_id() {
        return file_id;
    }

    public void setFile_id(long file_id) {
        this.file_id = file_id;
    }

    public String getCurrency_name() {
        return currency_name;
    }

    public void setCurrency_name(String currency_name) {
        this.currency_name = currency_name;
    }

    public List<QuoteDetail> getQuList() {
        return quList;
    }

    public void setQuList(List<QuoteDetail> quList) {
        this.quList = quList;
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
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

    public String toPosPre() {
        return "toPosPre";
    }

    public String searchClaimBb() {
        return "searchClaimBb";
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

    @PermissionSearch
    @JsonResult(field = "poList", include = { "id", "row_no", "sapClaimNbr", "transaction_code", "disti_name",
            "disti_branch", "disti_city", "book_part", "ship_qty", "ship_date", "debit_number", "oppreg_nbr",
            "disti_invoice_nbr", "disti_invoice_item_number", "disti_cost_currency", "disti_resale_denom",
            "disti_resale_currency", "purchasing_customer_name", "purchasing_cust_city", "purchasing_cust_zip",
            "disti_cost_exchangeRate", "purchasing_cust_state", "purchasing_cust_country", "endcust_name",
            "endcust_city", "endcust_loc", "endcust_zip", "endcust_state", "endcust_country", "status", "file_id",
            "file_url", "status_num", "created_user", "created_time", "disti_accounting_nbr",
            "data_from" }, total = "total")
    public String getPosList() {

        Pos pos = new Pos();
        if (StringUtils.isNotEmpty(branchId) && StringUtils.isNotEmpty(branchId.trim())) {
            try {
                branchId = java.net.URLDecoder.decode(branchId, "UTF-8");
                branchId = branchId.toUpperCase();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        pos.setDisti_name(branchId);
        pos.setDisti_cost_currency(currency_name);
        pos.setStart(this.getStart());
        pos.setEnd(this.getEnd());
        pos.setStatus(status);
        pos.setFile_id(file_id);
        if (upload_start_timeString != null && !"".equals(upload_start_timeString))
            pos.setCreated_time_start(DateUtil.getDateFromStr(upload_start_timeString));
        if (upload_end_timeString != null && !"".equals(upload_end_timeString))
            pos.setCreated_time_end(DateUtil.getDateFromStr(upload_end_timeString));

        if (start_timeString != null && !"".equals(start_timeString))
            pos.setShip_dateStart(start_timeString.replaceAll("-", ""));
        if (end_timeString != null && !"".equals(end_timeString))
            pos.setShip_dateEnd(end_timeString.replaceAll("-", ""));

        if (null != start_timeString && !"".equals(start_timeString))
            pos.setStart_time(DateUtil.getDateFromStr(start_timeString));
        if (null != end_timeString && !"".equals(end_timeString))
            pos.setEnd_time(DateUtil.getDateFromStr(end_timeString));

        if (StringUtils.isNotEmpty(file_url) && StringUtils.isNotEmpty(file_url.trim())) {
            try {
                file_url = java.net.URLDecoder.decode(file_url, "UTF-8");
                file_url = file_url.toUpperCase();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        pos.setFile_url(file_url);
        pos.setType("2");
        if (file_id > 0) {
            pos.setFile_id_str(file_id);
        }
        total = posService.searchPosListCount(pos);
        if (total != 0) {
            poList = posService.searchPosList(pos);
            for (Pos p : poList) {
                Long file_id = p.getFile_id();
                Pos pp = new Pos();
                pp.setFile_id(file_id);
                pp.setType("2");
                Pos ppp = posService.getPosByfileId(pp);
                p.setStatus_num(ppp.getStatus_num());
                p.setQty(ppp.getQty());
                p.setAmount(ppp.getAmount());

            }
        }

        return JSON;
    }

    @PermissionSearch
    @JsonResult(field = "poList", include = { "id", "row_no", "sapClaimNbr", "transaction_code", "disti_name",
            "disti_branch", "disti_city", "book_part", "ship_qty", "ship_date", "debit_number", "disti_claimnbr",
            "oppreg_nbr", "disti_invoice_nbr", "disti_invoice_item_number", "disti_cost", "cost_denom",
            "disti_cost_currency", "disti_resale_denom", "disti_bookcost", "dbc_denom", "dbc_currency_code",
            "dbc_exchange_rate", "disti_resale", "disti_resale_currency", "purchasing_customer_name",
            "purchasing_cust_city", "purchasing_cust_zip", "disti_cost_exchangeRate", "purchasing_cust_state",
            "purchasing_cust_country", "endcust_name", "endcust_city", "endcust_loc", "endcust_zip", "endcust_state",
            "endcust_country", "status", "tips", "rejecttoapprove_remark", "file_id", "file_url", "m_12nc",
            "statue_num", "type", "disti_accounting_nbr" }, total = "total")
    public String getPosListById() {

        pos = new Pos();
        if (StringUtils.isNotEmpty(branchId) && StringUtils.isNotEmpty(branchId.trim())) {
            try {
                branchId = java.net.URLDecoder.decode(branchId, "UTF-8");
                branchId = branchId.toUpperCase();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        pos.setDisti_name(branchId);
        if (StringUtils.isNotEmpty(material_name) && StringUtils.isNotEmpty(material_name.trim())) {
            try {
                material_name = java.net.URLDecoder.decode(material_name, "UTF-8");
                material_name = material_name.toUpperCase();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        pos.setBook_part(material_name);
        pos.setDisti_cost_currency(currency_name);
        pos.setStatus(status);
        pos.setFile_id(file_id);
        if (null != start_timeString && !"".equals(start_timeString))
            pos.setStart_time(DateUtil.getDateFromStr(start_timeString));
        if (null != end_timeString && !"".equals(end_timeString))
            pos.setEnd_time(DateUtil.getDateFromStr(end_timeString));
        pos.setType("2");

        pos.setStart(this.getStart());
        pos.setEnd(this.getEnd());
        if (null != pc_ec_name && !"".equals(pc_ec_name))
            pos.setPc_ec_name(pc_ec_name.toUpperCase());
        if (null != error_code && !"".equals(error_code))
            pos.setError_code(error_code.toUpperCase());
        if (null != debit_number && !"".equals(debit_number))
            pos.setDebit_number(debit_number.toUpperCase());
        if (null != invoice_number && !"".equals(invoice_number))
            pos.setInvoice_number(invoice_number.toUpperCase());

        total = posService.searchPosDetailListCountById(pos);
        if (total != 0) {
            poList = posService.searchPosDetailListById(pos);
        }

        return JSON;
    }

    @PermissionSearch
    @JsonResult(field = "poList", include = { "id", "row_no", "sapClaimNbr", "transaction_code", "disti_name",
            "disti_branch", "disti_city", "book_part", "ship_qty", "ship_date", "debit_number", "oppreg_nbr",
            "disti_invoice_nbr", "disti_invoice_item_number", "disti_cost_currency", "disti_resale_denom",
            "disti_resale_currency", "disti_bookcost", "purchasing_customer_name", "purchasing_cust_city",
            "purchasing_cust_zip", "disti_cost", "remainQty", "rebatePrice", "rebateTotal", "purchasing_cust_state",
            "purchasing_cust_country", "endcust_name", "endcust_city", "endcust_loc", "endcust_zip", "endcust_state",
            "endcust_country", "status", "disti_accounting_nbr" }, total = "total")
    public String getReceivePosList() {
        Pos pos = new Pos();
        pos.setDisti_name(branchId);
        pos.setDisti_cost_currency(currency_name);
        pos.setStatus("3");
        pos.setFlag("1");
        pos.setStart(this.getStart());
        pos.setEnd(this.getEnd());
        total = posService.searchPosListCount(pos);
        if (total != 0) {
            poList = new ArrayList<Pos>();
            List<Pos> lisPos = posService.getPosDebitNumber(pos);
            if (null != lisPos && lisPos.size() > 0) {
                String debitNum = null;
                String bookPart = null;
                double posQty = 0;
                for (int i = 0; i < lisPos.size(); i++) {
                    debitNum = lisPos.get(i).getDebit_number();
                    bookPart = lisPos.get(i).getBook_part();
                    pos.setDebit_number(debitNum);
                    pos.setBook_part(bookPart);
                    Pos debitpos = posService.getquoListBydebitNumBook(pos);
                    double quoteTotalqty = debitpos.getQuote_totalqty();
                    double distiCost = Double.valueOf(debitpos.getDisti_cost());
                    List<Pos> posList = posService.searchPosList(pos);
                    for (int j = 0; j < posList.size(); j++) {
                        pos = posList.get(j);
                        posQty = Double.valueOf(pos.getRebate_qty());
                        if (quoteTotalqty >= 0) {

                            if (quoteTotalqty <= posQty) {
                                pos.setRemainQty(quoteTotalqty);
                                pos.setDisti_cost(String.valueOf(distiCost));

                                double rebatePrice = distiCost - Double.valueOf(pos.getDisti_bookcost());
                                pos.setRebatePrice(rebatePrice);
                                pos.setRebateTotal(quoteTotalqty * rebatePrice);

                                poList.add(pos);
                                break;
                            } else {
                                pos.setRemainQty(posQty);
                                pos.setDisti_cost(String.valueOf(distiCost));
                                double rebatePrice = distiCost - Double.valueOf(pos.getDisti_bookcost());
                                pos.setRebatePrice(rebatePrice);
                                pos.setRebateTotal(posQty * rebatePrice);
                                poList.add(pos);
                            }
                            quoteTotalqty = quoteTotalqty - pos.getRemainQty();
                        }

                    }

                }

            }

        }

        return JSON;
    }

    public String findOrderExcelXls1(String path) {
        FileInputStream fileIn = null;
        String remsg = "";
        Workbook rwb = null;
        List<Pos> pList = new ArrayList<Pos>();
        StringBuilder contentResult = new StringBuilder();

        try {
            fileIn = new FileInputStream(uploadFile);
            rwb = Workbook.getWorkbook(fileIn);
            Sheet rs = rwb.getSheet(0);
            int column = 0;
            column = rs.getColumns();
            int actualRows = 0;
            actualRows = StockUtil.delEmptyRow(rs);
            if (actualRows == 0 && column == 0) {
                this.setFailMessage("Import Excel is EMPTY !");
                return RESULT_MESSAGE;
            } else {
                long fileId = posService.getClaimFileId();
                Pos pos;
                for (int i = 1; i < actualRows; i++) {

                    pos = new Pos();

                    String M1J = "";
                    String M2A = "";
                    String M2B = "";
                    String M2C = "";
                    String M2D = "";
                    String M2E = "";
                    String M2F = "";
                    String M2G = "";
                    String M2H = "";
                    String M2I = "";
                    String M2J = "";
                    String M2K = "";
                    String M2L = "";
                    String M2M = "";
                    String M2N = "";
                    String M2O = "";
                    String M2P = "";
                    String M2Q = "";
                    String M2R = "";
                    String M2S = "";
                    String M2T = "";
                    String M2U = "";
                    String M2V = "";
                    String M2W = "";
                    String M2X = "";

                    String C3A = "";
                    String C3C = "";
                    String C3G = "";
                    String C3K = "";
                    String C3M = "";
                    String C3O = "";
                    String C4B = "";
                    String C4C = "";
                    String C4E = "";
                    String C4Q = "";
                    String C4V = "";
                    String C4T = "";
                    String C5E = "";
                    String C5F = "";
                    String C5G = "";
                    String C5J = "";
                    String C5O = "";
                    String C5Q = "";
                    String C5V = "";
                    String C5T = "";
                    String C6E = "";
                    String C6F = "";
                    String C6G1 = "";
                    String C6G2 = "";
                    String C6H = "";
                    String C6K = "";
                    String C6L = "";
                    String C6M = "";
                    String C6Q = "";
                    String C6V = "";
                    String C6T = "";

                    if ("".equals(rs.getCell(0, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        M2A = "2A;";
                    } else {
                        pos.setTransaction_code(rs.getCell(0, i).getContents().trim());
                    }

                    if ("".equals(rs.getCell(1, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        M2B = "2B;";
                    } else {
                        pos.setDisti_name(rs.getCell(1, i).getContents().trim());
                    }

                    if ("".equals(rs.getCell(2, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        M2C = "2C;";
                    } else {

                        pos.setDisti_branch((rs.getCell(2, i).getContents().trim()));
                    }

                    if ("".equals(rs.getCell(3, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        M2D = "2D;";
                    } else {
                        pos.setDisti_city(rs.getCell(3, i).getContents().trim());
                    }

                    if ("".equals(rs.getCell(4, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        M2E = "2E;";
                    } else {
                        pos.setBook_part(rs.getCell(4, i).getContents().trim());
                    }

                    if ("".equals(rs.getCell(5, i).getContents().trim())
                            || "0".equals(rs.getCell(5, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        M2F = "2F;";
                        pos.setShip_qty("0");
                    } else {
                        pos.setShip_qty(rs.getCell(5, i).getContents().trim());
                    }

                    if ("".equals(rs.getCell(6, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        M2G = "2G;";

                    } else {
                        pos.setShip_date(rs.getCell(6, i).getContents().trim());
                        String stri = rs.getCell(6, i).getContents().trim();
                        Format f = new SimpleDateFormat("yyyyMMdd");
                        try {
                            Date d = (Date) f.parseObject(stri);

                            String tmp = f.format(d);
                            System.out.println(stri + ":" + tmp.equals(stri));
                            boolean flag = tmp.equals(stri);
                            if (flag == false) {
                                M2G = "2G;";
                            }
                        } catch (Exception e) {
                            M2G = "2G;";
                        }
                    }

                    if ("".equals(rs.getCell(7, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        M2H = "2H;";
                    } else {
                        pos.setDebit_number(rs.getCell(7, i).getContents().trim());
                    }

                    if ("".equals(rs.getCell(8, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        M2I = "2I;";
                    } else {
                        pos.setDisti_claimnbr(rs.getCell(8, i).getContents().trim());
                    }

                    pos.setOppreg_nbr(rs.getCell(9, i).getContents().trim());
                    pos.setCpn(rs.getCell(10, i).getContents().trim());

                    if ("".equals(rs.getCell(11, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        M2J = "2J;";
                    } else {
                        pos.setDisti_invoice_nbr(rs.getCell(11, i).getContents().trim());
                    }

                    pos.setDisti_invoice_item_number(rs.getCell(12, i).getContents().trim());

                    pos.setDisti_cost(rs.getCell(13, i).getContents().trim());

                    if ("".equals(rs.getCell(14, i).getContents().trim())
                            || "0".equals(rs.getCell(14, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        pos.setCost_denom(rs.getCell(14, i).getContents().trim());
                        M2K = "2K;";
                    } else {
                        pos.setCost_denom(rs.getCell(14, i).getContents().trim());
                        System.out.println(pos.getCost_denom());
                    }

                    if ("".equals(rs.getCell(15, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        M2L = "2L;";
                    } else {
                        pos.setDisti_cost_currency(rs.getCell(15, i).getContents().trim());
                    }

                    if ("".equals(rs.getCell(16, i).getContents().trim())) {
                        pos.setDisti_cost_exchangeRate("1");
                    } else {
                        pos.setDisti_cost_exchangeRate(rs.getCell(16, i).getContents().trim());

                    }

                    pos.setDisti_bookcost(rs.getCell(17, i).getContents().trim());

                    if ("".equals(rs.getCell(18, i).getContents().trim())
                            || "0".equals(rs.getCell(18, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        M2M = "2M;";
                    } else {
                        pos.setDbc_denom(rs.getCell(18, i).getContents().trim());
                    }

                    if ("".equals(rs.getCell(19, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        M2N = "2N;";
                        pos.setDbc_currency_code("0");
                    } else {
                        pos.setDbc_currency_code(rs.getCell(19, i).getContents().trim());
                    }

                    pos.setDbc_exchange_rate(rs.getCell(20, i).getContents().trim());

                    pos.setDisti_resale(rs.getCell(21, i).getContents().trim());

                    if ("".equals(rs.getCell(22, i).getContents().trim())
                            || "0".equals(rs.getCell(22, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        M2O = "2O;";
                    } else {
                        pos.setDisti_resale_denom((rs.getCell(22, i).getContents().trim()));
                    }
                    System.out.println(rs.getCell(22, i).getContents().trim());
                    System.out.println(pos.getDisti_resale_denom());

                    if ("".equals(rs.getCell(23, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        M2P = "2P;";
                    } else {
                        pos.setDisti_resale_currency(rs.getCell(23, i).getContents().trim());
                    }

                    if ("".equals(rs.getCell(24, i).getContents().trim())) {
                        pos.setDisti_resale_exchange_rate("1");
                    } else {
                        pos.setDisti_resale_exchange_rate(rs.getCell(24, i).getContents().trim());
                    }

                    if ("".equals(rs.getCell(25, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        M2Q = "2Q;";
                    } else {
                        pos.setPurchasing_customer_name(rs.getCell(25, i).getContents().trim());
                    }

                    if ("".equals(rs.getCell(26, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        M2R = "2R;";
                    } else {
                        pos.setPurchasing_cust_city(rs.getCell(26, i).getContents().trim());
                    }

                    if ("".equals(rs.getCell(27, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        M2R = "2R;";
                    } else {
                        pos.setPurchasing_cust_zip(rs.getCell(27, i).getContents().trim());
                    }

                    if ("".equals(rs.getCell(28, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        M2R = "2R;";
                    } else {
                        pos.setPurchasing_cust_state(rs.getCell(28, i).getContents().trim());
                    }

                    if ("".equals(rs.getCell(29, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        M2S = "2S;";
                    } else {
                        pos.setPurchasing_cust_country(rs.getCell(29, i).getContents().trim());
                    }

                    if ("".equals(rs.getCell(30, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        M2T = "2T;";
                    } else {
                        pos.setEndcust_name(rs.getCell(30, i).getContents().trim());
                    }

                    if ("".equals(rs.getCell(31, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        M2U = "2U;";
                    } else {
                        pos.setEndcust_city(rs.getCell(31, i).getContents().trim());
                    }

                    if ("".equals(rs.getCell(32, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        M2V = "2V;";
                    } else {
                        pos.setEndcust_loc(rs.getCell(32, i).getContents().trim());
                    }

                    if ("".equals(rs.getCell(33, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        M2V = "2V;";
                    } else {
                        pos.setEndcust_zip(rs.getCell(33, i).getContents().trim());
                    }

                    if ("".equals(rs.getCell(34, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        M2V = "2V;";
                    } else {
                        pos.setEndcust_state(rs.getCell(34, i).getContents().trim());
                    }

                    if ("".equals(rs.getCell(35, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        M2W = "2W;";
                    } else {
                        pos.setEndcust_country(rs.getCell(35, i).getContents().trim());
                    }

                    pos.setEndcust_country(rs.getCell(35, i).getContents().trim());
                    pos.setDisti_num(rs.getCell(36, i).getContents().trim());

                    if ("".equals(rs.getCell(37, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        M2X = "2X;";
                    } else {
                        pos.setDisti_accounting_nbr(rs.getCell(37, i).getContents().trim());
                    }

                    Pos qpos = new Pos();

                    qpos.setMark(pos.getDisti_invoice_nbr() + pos.getDisti_invoice_item_number());
                    qpos.setType("2");
                    qpos.setStatus("('3','4')");
                    total = posService.searchPosListCountForAll(qpos);
                    if (total > 0) {
                        M1J = "1J;";
                    }

                    // 1 BAD_TRANS_CODE
                    if ("".equals(M2A)) {
                        if (!"C".equals(pos.getTransaction_code()) && !"c".equals(pos.getTransaction_code())) {
                            C3A = "3A;";
                        }
                    }

                    // 2 BAD_CR_N_DAYS_AFTER_SHIP
                    if ("".equals(M2G)) {
                        String str1 = pos.getShip_date();
                        DateFormat fmtDateTime = new SimpleDateFormat("yyyyMMdd");
                        Date date1 = fmtDateTime.parse(str1.toString());
                        Calendar calendar1 = Calendar.getInstance();
                        calendar1.setTime(date1);
                        Calendar today = Calendar.getInstance();
                        double days = (today.getTimeInMillis() - calendar1.getTimeInMillis()) / (1000 * 60 * 60 * 24);
                        System.out.println(days);
                        if (days > 90) {
                            C3G = "3G;";
                        }
                    }

                    if ("".equals(M2K)) {
                        try {
                            Double e = Double.valueOf(pos.getCost_denom());
                            System.out.println((e + "").length() - (e + "").indexOf(".") - 1);
                            int num1 = (e + "").length() - (e + "").indexOf(".") - 1;
                            if (num1 > 4) {
                                C3K = "3K;";
                            }

                        } catch (Exception e) {
                            C3K = "3K;";
                        }
                    }
                    if ("".equals(M2M)) {
                        try {
                            Double g = Double.valueOf(pos.getDbc_denom());
                            System.out.println((g + "").length() - (g + "").indexOf(".") - 1);
                            int num3 = (g + "").length() - (g + "").indexOf(".") - 1;
                            if (num3 > 4) {
                                C3M = "3M;";
                            }

                        } catch (Exception e) {
                            C3M = "3M;";
                        }
                    }

                    if ("".equals(M2O)) {
                        try {
                            Double h = Double.valueOf(pos.getDisti_resale_denom());
                            System.out.println((h + "").length() - (h + "").indexOf(".") - 1);
                            int num4 = (h + "").length() - (h + "").indexOf(".") - 1;
                            if (num4 > 4) {
                                C3O = "3O;";
                            }
                        } catch (Exception e) {
                            C3O = "3O;";
                        }
                    }

                    // 4 BAD_DISTI_ACCOUNT
                    if ("".equals(M2C)) {
                        String aa = rs.getCell(37, i).getContents().trim();
                        String bb = pos.getDisti_branch();
                        if (!aa.equals(bb)) {
                            C3C = "3C;";
                        }
                    }

                    // 6 - 11
                    if ("".equals(M2J)) {
                        qpos = new Pos();
                        qpos.setMark(pos.getDisti_invoice_nbr() + pos.getDisti_invoice_item_number());
                        qpos.setType("1");
                        qpos.setStatus("3");

                        poList = posService.searchPosListByPos(qpos);
                        if (null != poList && poList.size() > 0) { 
                            // 7
                            // SHIP_DATE_NOT_MATCH
                            if ("".equals(M2F) && "".equals(M2G)) {
                                System.out.println(pos.getShip_date());
                                System.out.println(poList.get(0).getShip_date());
                                if (!pos.getShip_date().equals(poList.get(0).getShip_date())) {
                                    C5G = "5G;";
                                }
                                if ("".equals(M2F)) {
                                    System.out.println(pos.getShip_qty());
                                    System.out.println(poList.get(0).getShip_qty());
                                    double aaa = Double.valueOf(pos.getShip_qty()) * 10 / 10;
                                    double bbb = Double.valueOf(poList.get(0).getShip_qty()) * 10 / 10;

                                    if (aaa != bbb) {
                                        // 8
                                        // SHIP_QUANTITY_NOT_MATCH
                                        C5F = "5F;";
                                    }
                                }
                            }

                            if ("".equals(M2Q)) {
                                try {
                                    System.out.println(pos.getPurchasing_customer_name());
                                    System.out.println(poList.get(0).getPurchasing_customer_name());
                                    if (!pos.getPurchasing_customer_name()
                                            .equals(poList.get(0).getPurchasing_customer_name())) {
                                        C5Q = "5Q;";
                                    }
                                } catch (Exception e) {
                                    C5Q = "5Q;";
                                }
                            }

                            if ("".equals(M2O)) {
                                try {
                                    double aa = Double.valueOf(pos.getDisti_resale_denom()) * 1;
                                    double bb = Double.valueOf(poList.get(0).getDisti_resale_denom()) * 1;
                                    if (aa != bb) { // RESALE_PRICE_NOT_MATCH
                                        C5O = "5O;";
                                    }
                                } catch (Exception e) {
                                    C5O = "5O;";
                                }
                            }

                            if ("".equals(M2T)) {
                                try {
                                    System.out.println(pos.getEndcust_name());
                                    System.out.println(poList.get(0).getEndcust_name());
                                    if (!pos.getEndcust_name().equals(poList.get(0).getEndcust_name())) {
                                        C5T = "5T;";
                                    }
                                } catch (Exception e) {
                                    C5T = "5T;";
                                }

                            }

                            if ("".equals(M2E)) {
                                System.out.println(pos.getBook_part());
                                System.out.println(poList.get(0).getBook_part());
                                if (!pos.getBook_part().equals(poList.get(0).getBook_part())) {
                                    C5E = "5E;";
                                }
                            }

                        } else {
                            // 6 BAD_CR_WITHOUT_POS
                            C5J = "5J;";
                        }
                    }

                    // 12 - 16
                    // 12 BAD_DISTI_CUST toUpperCase
                    if ("".equals(M2B)) {
                        qpos = new Pos();
                        qpos.setDisti_name(pos.getDisti_name().toUpperCase());
                        total = posService.searchCustomerCount(qpos);
                        if (total <= 0) {
                            C4B = "4B;";
                        }
                    }

                    // 13 BAD_DISTRIBUTOR_BRANCH
                    if ("".equals(M2C) && "".equals(M2B)) {
                        qpos = new Pos();
                        qpos.setDisti_branch(pos.getDisti_branch());
                        qpos.setDisti_name(pos.getDisti_name());
                        total = posService.searchRelationshipCount(qpos);
                        if (total <= 0) {
                            C4C = "4C;";
                        }
                    }

                    // 14 BAD_CUSTOMER toUpperCase
                    if ("".equals(M2Q)) {
                        qpos = new Pos();
                        qpos.setPurchasing_customer_name(pos.getPurchasing_customer_name().toUpperCase());
                        total = posService.searchEndCustomerCount(qpos);
                        if (total <= 0) {
                            C4Q = "4Q;";
                        }
                    }

                    // 15 BAD_END_CUSTOMER toUpperCase
                    if ("".equals(M2T)) {
                        qpos = new Pos();
                        qpos.setEndcust_name(pos.getEndcust_name().toUpperCase());
                        total = posService.searchEndCustomerCount(qpos);
                        if (total <= 0) {
                            C4T = "4T;";
                        }
                    }

                    // 16 BAD_PART
                    if ("".equals(M2E)) {
                        qpos = new Pos();
                        qpos.setBook_part(pos.getBook_part());
                        total = posService.searchProductCount(qpos);
                        if (total <= 0) {
                            C4E = "4E;";
                        }
                    }

                    // 17 - 27
                    // 17 BAD_DEBIT_NUM
                    if ("".equals(M2B) && "".equals(M2H)) {
                        qpos = new Pos();
                        qpos.setDebit_number(pos.getDebit_number());
                        qpos.setDisti_name(pos.getDisti_name());
                        total = posService.getQuoteDetailCount(qpos);
                        if (total <= 0) {
                            C6H = "6H;";
                        }
                    }

                    // 18 Bad_Debit_Channel
                    if ("".equals(M2B) && "".equals(M2E) && "".equals(M2H)) {
                        qpos = new Pos();
                        qpos.setDebit_number(pos.getDebit_number());
                        qpos.setDisti_name(pos.getDisti_name());
                        qpos.setBook_part(pos.getBook_part());
                        quList = posService.searchQuoteList(qpos);
                        if (null != quList && quList.size() > 0) {
                            System.out.println(quList.get(0).getIsAgree());
                            if (quList.get(0).getIsAgree() != 1) {
                                C6E = "6E;";
                            } else {

                                if ("".equals(M2L)) {
                                    System.out.println((quList.get(0).getCurrency_code()).toUpperCase());
                                    System.out.println(pos.getDisti_cost_currency().toUpperCase());
                                    if (!pos.getDisti_cost_currency().toUpperCase()
                                            .equals((quList.get(0).getCurrency_code()).toUpperCase())) {
                                        C6L = "6L;";
                                    }
                                }

                                if ("".equals(M2Q)) {
                                    try {
                                        System.out.println(quList.get(0).getPurchaseCustomer_name());
                                        System.out.println(pos.getPurchasing_customer_name());

                                        Pos qposs = new Pos();
                                        qposs.setPurchasing_customer_name(
                                                (pos.getPurchasing_customer_name()).toUpperCase());
                                        ecList = new ArrayList<ECAlias>();
                                        List<ECAlias> ecList = posService.searchEcList(pos);
                                        if (null != ecList && ecList.size() > 0) {
                                            for (ECAlias ea : ecList) {
                                                if (!((quList.get(0).getEndCustomer_name()).toUpperCase())
                                                        .equals(ea.getEc_name().toUpperCase())) {
                                                    C6T = "6T;";
                                                }
                                            }
                                        }
                                        C6Q = "6Q;";

                                    } catch (Exception e) {
                                        C6Q = "6Q;";
                                    }
                                }
                                if ("".equals(M2T)) {
                                    try {
                                        Pos qposs = new Pos();
                                        qposs.setEndcust_name((pos.getEndcust_name()).toUpperCase());
                                        ecList = new ArrayList<ECAlias>();
                                        List<ECAlias> ecList = posService.searchEcList(pos);
                                        if (null != ecList && ecList.size() > 0) {
                                            for (ECAlias ea : ecList) {
                                                if (!((quList.get(0).getEndCustomer_name()).toUpperCase())
                                                        .equals(ea.getEc_name().toUpperCase())) {
                                                    C6T = "6T;";
                                                }
                                            }
                                        }
                                    } catch (Exception e) {
                                        C6T = "6T;";
                                    }
                                }
                                if ("".equals(M2G)) {
                                    DateFormat fmt = new SimpleDateFormat("yyyyMMdd");
                                    Date date = fmt.parse(pos.getShip_date());
                                    boolean flag = quList.get(0).getStart_date().after(date);
                                    if (flag == true && !date.equals(quList.get(0).getStart_date())) {
                                        C6G1 = "6G1;";
                                    }
                                    System.out.println(pos.getShip_date());
                                    System.out.println(quList.get(0).getStart_date());

                                    boolean flag1 = quList.get(0).getEnd_date().after(date);
                                    if (flag1 == false && !date.equals(quList.get(0).getEnd_date())) {
                                        C6G2 = "6G2;";
                                    }
                                }

                                if ("".equals(M2K)) {
                                    System.out.println(quList.get(0).getSuggest_cost());
                                    System.out.println(pos.getCost_denom());
                                    if (Double.valueOf(pos.getCost_denom()) < quList.get(0).getSuggest_cost()) {
                                        C6K = "6K;";
                                    }
                                }
                            }
                        } else {
                            C6E = "6E;";
                        }

                        // 25 BAD_DISTI_BOOK_COST
                        if ("".equals(M2C) && "".equals(M2E) && "".equals(M2M) && "".equals(M2N)) {
                            qpos = new Pos();
                            qpos.setDbc_denom(pos.getDbc_denom());
                            qpos.setDisti_branch(pos.getDisti_branch());
                            qpos.setBook_part(pos.getBook_part());

                            Date date = new Date();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                            String DateStr1 = sdf.format(date);
                            qpos.setShip_date(DateStr1);

                            qpos.setDbc_currency_code(pos.getDbc_currency_code().toUpperCase());
                            System.out.println(pos.getDisti_branch());
                            System.out.println(pos.getBook_part());
                            System.out.println(pos.getShip_date());
                            System.out.println(pos.getDisti_resale_currency().toUpperCase());
                            try {
                                double minPrice = posService.getMinPrice(qpos);
                                System.out.println(qpos.getDbc_denom());
                                if (Double.valueOf(qpos.getDbc_denom()) > minPrice) {
                                    C6M = "6M;";
                                }
                            } catch (Exception e2) {
                                C6M = "6M0;";
                            }
                        }

                        // 26 BAD_QUANTITY
                        if ("".equals(M2F)) {
                            try {
                                qpos = new Pos();

                                pos.setShip_qty(String.valueOf((Double.valueOf(pos.getShip_qty())) * 10 / 10));
                                double ccc = Double.valueOf(pos.getShip_qty()) * 10 / 10;
                                double passQty = posService.getPassedQty(pos);
                                if (ccc > (quList.get(0).getQty() - passQty)) {
                                    C6F = "6F;";
                                }
                            } catch (Exception e1) {
                                C6F = "6F0;";
                            }
                        }
                    }

                    String resultMessage = M1J + M2A + M2B + M2C + M2D + M2E + M2F + M2G + M2H + M2I + M2J + M2K + M2L
                            + M2M + M2N + M2O + M2P + M2Q + M2R + M2S + M2T + M2U + M2V + M2W + M2X + C3A + C3C + C3G
                            + C3K + C3M + C3O + C4B + C4C + C4E + C4Q + C4V + C4T + C5E + C5F + C5G + C5J + C5O + C5Q
                            + C5V + C5T + C6E + C6F + C6G1 + C6G2 + C6H + C6K + C6L + C6M + C6Q + C6V + C6T;

                    if (!"".equals(resultMessage)) {
                        resultMessage = resultMessage.substring(0, resultMessage.length() - 1);
                    }

                    if ("".equals(rs.getCell(7, i).getContents().trim())) {
                        pos.setStatus("0");
                        pos.setTips(resultMessage);
                        pos.setFile_id(fileId);
                        pos.setFile_url(file_url);
                        pos.setType("2");
                        pos.setData_from("1");
                        pos.setCreated_user(this.getUser().getUserId());
                    } else {
                        pos.setDebit_number(rs.getCell(7, i).getContents().trim());

                        if ("".equals(resultMessage)) {
                            pos.setStatus("3");
                            pos.setFile_id(fileId);
                            pos.setFile_url(file_url);
                            pos.setType("2");
                            pos.setTips("Success!");
                            pos.setData_from("1");
                            pos.setCreated_user(this.getUser().getUserId());
                            pos.setFile_id(fileId);
                            pos.setType("2");
                        } else {
                            pos.setStatus("0");
                            pos.setTips(resultMessage);
                            pos.setFile_id(fileId);
                            pos.setFile_url(file_url);
                            pos.setType("2");
                            pos.setData_from("1");
                            pos.setCreated_user(this.getUser().getUserId());
                        }
                    }
                    pList.add(pos);
                }
            }
            long result = 0;
            for (Pos pos : pList) {
                result = posService.createPosInfo(pos);
                int aa = posService.searchPosDetailListCount(pos);
                int bb = posService.searchPosDetailListCountForError(pos);
                pos.setStatus_num(bb + "/" + aa);
                posService.updatePos(pos);
                if (result == 0) {
                    break;
                }
            }
            if (result != 0) {
                remsg = "Success!";
            } else {
                this.setFailMessage("Fail!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {

            remsg = "Excel Format Error !";
        } catch (BiffException e) {

            remsg = "03以上版本Excel导入暂不支持";
        } catch (Exception e) {

            remsg = "Fatal Error !";
        }
        return remsg;

    }

    // Excel 2003
    public String findOrderExcelXls(String path) {

        FileInputStream fileIn = null;
        String remsg = "";
        Workbook rwb = null;
        List<Pos> pList = new ArrayList<Pos>();
        try {
            fileIn = new FileInputStream(uploadFile);
            rwb = Workbook.getWorkbook(fileIn);
            Sheet rs = rwb.getSheet(0);
            int column = 0;
            column = rs.getColumns();
            int actualRows = 0;
            actualRows = StockUtil.delEmptyRow(rs);
            if (actualRows == 0 && column == 0) {
                this.setFailMessage("Import Excel is EMPTY !");
                return RESULT_MESSAGE;
            } else {
                long fileId = posService.getClaimFileId();
                Pos pos;
                for (int i = 1; i < actualRows; i++) {

                    pos = new Pos();

                    pos.setTransaction_code(rs.getCell(0, i).getContents().trim());
                    pos.setDisti_name(rs.getCell(1, i).getContents().trim());
                    pos.setDisti_branch((rs.getCell(2, i).getContents().trim()));
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

                    jxl.Cell cell14 = rs.getCell(14, i);
                    if (cell14.getType() == CellType.NUMBER) {
                        pos.setCost_denom(String.valueOf(((NumberCell) cell14).getValue()));
                    } else if (cell14.getType() == CellType.LABEL) {
                        pos.setCost_denom(rs.getCell(14, i).getContents().trim());
                    }

                    pos.setDisti_cost_currency(rs.getCell(15, i).getContents().trim());
                    pos.setDisti_cost_exchangeRate(rs.getCell(16, i).getContents().trim());

                    jxl.Cell cell17 = rs.getCell(17, i);
                    if (cell17.getType() == CellType.NUMBER) {
                        pos.setDisti_bookcost(String.valueOf(((NumberCell) cell17).getValue()));
                        System.out.print(((NumberCell) cell17).getValue() + "  ");
                    } else if (cell17.getType() == CellType.LABEL) {
                        pos.setDisti_bookcost(rs.getCell(17, i).getContents().trim());
                        System.out.print(cell17.getContents() + "  ");
                    }

                    jxl.Cell cell18 = rs.getCell(18, i);
                    if (cell18.getType() == CellType.NUMBER) {
                        pos.setDbc_denom(String.valueOf(((NumberCell) cell18).getValue()));
                    } else if (cell18.getType() == CellType.LABEL) {
                        pos.setDbc_denom(rs.getCell(18, i).getContents().trim());
                    }

                    pos.setDbc_currency_code(rs.getCell(19, i).getContents().trim());
                    pos.setDbc_exchange_rate(rs.getCell(20, i).getContents().trim());

                    jxl.Cell cell21 = rs.getCell(21, i);
                    if (cell21.getType() == CellType.NUMBER) {
                        pos.setDisti_resale(String.valueOf(((NumberCell) cell21).getValue()));
                    } else if (cell21.getType() == CellType.LABEL) {
                        pos.setDisti_resale(rs.getCell(21, i).getContents().trim());
                    }

                    jxl.Cell cell22 = rs.getCell(22, i);
                    if (cell22.getType() == CellType.NUMBER) {
                        pos.setDisti_resale_denom(String.valueOf(((NumberCell) cell22).getValue()));
                    } else if (cell22.getType() == CellType.LABEL) {
                        pos.setDisti_resale_denom(rs.getCell(22, i).getContents().trim());
                    }

                    pos.setDisti_resale_currency(rs.getCell(23, i).getContents().trim());
                    pos.setDisti_resale_exchange_rate(rs.getCell(24, i).getContents().trim());
                    pos.setPurchasing_customer_name(rs.getCell(25, i).getContents().trim());
                    pos.setPurchasing_cust_city(rs.getCell(26, i).getContents().trim());
                    pos.setPurchasing_cust_zip(rs.getCell(27, i).getContents().trim());
                    pos.setPurchasing_cust_state(rs.getCell(28, i).getContents().trim());
                    pos.setPurchasing_cust_country(rs.getCell(29, i).getContents().trim());
                    if ("".equals(rs.getCell(30, i).getContents().trim())) {

                    } else {

                        pos.setEndcust_name(rs.getCell(30, i).getContents().trim());
                        pos.setEndcust_city(rs.getCell(31, i).getContents().trim());
                        pos.setEndcust_loc(rs.getCell(32, i).getContents().trim());
                        pos.setEndcust_zip(rs.getCell(33, i).getContents().trim());
                        pos.setEndcust_state(rs.getCell(34, i).getContents().trim());
                        pos.setEndcust_country(rs.getCell(35, i).getContents().trim());
                    }

                    pos.setDisti_num(rs.getCell(36, i).getContents().trim());
                    pos.setDisti_accounting_nbr(rs.getCell(37, i).getContents().trim());

                    pos.setStatus("9");
                    pos.setFile_id(fileId);

                    if (StringUtils.isNotEmpty(file_url) && StringUtils.isNotEmpty(file_url.trim())) {
                        try {
                            file_url = java.net.URLDecoder.decode(file_url, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    pos.setFile_url(file_url);
                    pos.setType("2");
                    pos.setData_from("1");
                    pos.setCreated_user(this.getUser().getUserId());
                    
                    XssUtils.getXssSaftBean(pos.getClass(), pos);
                    
                    pList.add(pos);
                }
                long result = 0;
                for (Pos pos1 : pList) {
                    result = posService.createPosInfo(pos1);
                }
                checkEDI();

                if (result != 0) {
                    remsg = "Success!";
                } else {
                    this.setFailMessage("Fail!");
                }
            }
        } catch (Exception e) {
            remsg = "Fatal Error !";
        }
        return remsg;
    }

    public String toPosUnpre() {
        return "toPosUnpre";
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

    // eXcel 2007+
    private String findOrderExcelXlsx(String path) {
        String reMessage = "Success!";
        List<Pos> pList = new ArrayList<Pos>();
        try {

            FileInputStream file = new FileInputStream(path);
            XSSFWorkbook xwb = new XSSFWorkbook(file);
            XSSFSheet sheet = xwb.getSheetAt(0);
            XSSFRow row;
            if (sheet.getPhysicalNumberOfRows() <= 1) {
                return "Import Excel is empty!";
            }

            Pos pos;
            long fileId = posService.getClaimFileId();
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                row = sheet.getRow(i);
                if (row == null) {// 空行跳过
                    continue;
                }

                pos = new Pos();
                XSSFCell cell0 = row.getCell(0);
                if (cell0 != null) {
                    cell0.setCellType(Cell.CELL_TYPE_STRING);
                    if ("".equals(cell0.getStringCellValue().trim())) {
                        continue;
                    }
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
                    cell5.setCellType(Cell.CELL_TYPE_NUMERIC);
                    pos.setShip_qty(String.valueOf(Math.round(cell5.getNumericCellValue())));
                }

                XSSFCell cell6 = row.getCell(6);
                if (cell6 != null) {
                    try {
                        Date ss1 = cell6.getDateCellValue();
                        cell6.setCellType(Cell.CELL_TYPE_STRING);
                        String ss = cell6.getStringCellValue().trim();
                        if (ss.length() <= 5) {
                            pos.setShip_date(DateUtil.getDateTime(ss1, "yyyyMMdd"));
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
                    pos.setDisti_invoice_item_number(cell12.getStringCellValue().trim());
                }

                XSSFCell cell13 = row.getCell(13);
                if (cell13 != null) {
                    pos.setDisti_cost(String.valueOf(getCellValue(cell13)));
                }

                XSSFCell cell14 = row.getCell(14);
                if (cell14 != null) {
                    pos.setCost_denom(String.valueOf(getCellValue(cell14)));
                }

                XSSFCell cell15 = row.getCell(15);
                if (cell15 != null) {
                    cell15.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setDisti_cost_currency(cell15.getStringCellValue().trim());
                }

                XSSFCell cell16 = row.getCell(16);
                if (cell16 == null) {
                    pos.setDisti_cost_exchangeRate("1");
                } else {
                    cell16.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setDisti_cost_exchangeRate(cell16.getStringCellValue().trim());
                }

                XSSFCell cell17 = row.getCell(17);
                if (cell17 != null) {
                    pos.setDisti_bookcost(String.valueOf(getCellValue(cell17)));
                }

                XSSFCell cell18 = row.getCell(18);
                if (cell18 != null) {
                    pos.setDbc_denom(String.valueOf(getCellValue(cell18)));
                }

                XSSFCell cell19 = row.getCell(19);
                if (cell19 != null) {
                    cell19.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setDbc_currency_code(cell19.getStringCellValue().trim());
                }

                XSSFCell cell20 = row.getCell(20);
                if (cell20 != null) {
                    cell20.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setDbc_exchange_rate(cell20.getStringCellValue().trim());
                }

                XSSFCell cell21 = row.getCell(21);
                if (cell21 != null) {
                    pos.setDisti_resale(String.valueOf(getCellValue(cell21)));
                }

                XSSFCell cell22 = row.getCell(22);
                if (cell22 != null) {
                    pos.setDisti_resale_denom(String.valueOf(getCellValue(cell22)));
                }

                XSSFCell cell23 = row.getCell(23);
                if (cell23 != null) {
                    cell23.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setDisti_resale_currency(cell23.getStringCellValue().trim());
                }

                XSSFCell cell24 = row.getCell(24);
                if (cell24 != null) { // DISTIRESALEEXCHANGERATE
                    cell24.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setDisti_resale_exchange_rate(cell24.getStringCellValue().trim());
                }
                XSSFCell cell25 = row.getCell(25);
                if (cell25 != null) {
                    cell25.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setPurchasing_customer_name(cell25.getStringCellValue().trim());
                }

                XSSFCell cell26 = row.getCell(26);
                if (cell26 != null) {
                    cell26.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setPurchasing_cust_city(cell26.getStringCellValue().trim());
                }

                XSSFCell cell27 = row.getCell(27);
                if (cell27 != null) {
                    cell27.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setPurchasing_cust_zip(cell27.getStringCellValue().trim());
                }

                XSSFCell cell28 = row.getCell(28);
                if (cell28 != null) {
                    cell28.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setPurchasing_cust_state(cell28.getStringCellValue().trim());
                }

                XSSFCell cell29 = row.getCell(29);
                if (cell29 != null) {
                    cell29.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setPurchasing_cust_country(cell29.getStringCellValue().trim());
                }

                XSSFCell cell30 = row.getCell(30);
                if (cell30 != null && !"".equals(cell30.getStringCellValue().trim())) {
                    cell30.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setEndcust_name(cell30.getStringCellValue().trim());

                    XSSFCell cell31 = row.getCell(31);
                    if (cell31 != null) {
                        cell31.setCellType(Cell.CELL_TYPE_STRING);
                        pos.setEndcust_city(cell31.getStringCellValue().trim());
                    }

                    XSSFCell cell32 = row.getCell(32);
                    if (cell32 != null) {
                        cell32.setCellType(Cell.CELL_TYPE_STRING);
                        pos.setEndcust_loc(cell32.getStringCellValue().trim());
                    }

                    XSSFCell cell33 = row.getCell(33);
                    if (cell33 != null) {
                        cell33.setCellType(Cell.CELL_TYPE_STRING);
                        pos.setEndcust_zip(String.valueOf(getCellValue(cell33)));
                    }

                    XSSFCell cell34 = row.getCell(34);
                    if (cell34 != null) {
                        cell34.setCellType(Cell.CELL_TYPE_STRING);
                        pos.setEndcust_state(cell34.getStringCellValue().trim());
                    }

                    XSSFCell cell35 = row.getCell(35);
                    if (cell35 != null) {
                        cell35.setCellType(Cell.CELL_TYPE_STRING);
                        pos.setEndcust_country(cell35.getStringCellValue().trim());
                    }
                }

                XSSFCell cell36 = row.getCell(36);
                if (cell36 != null) {
                    cell36.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setDisti_num(cell36.getStringCellValue().trim());

                }

                XSSFCell cell37 = row.getCell(37);
                if (cell37 != null) {
                    cell37.setCellType(Cell.CELL_TYPE_STRING);
                    pos.setDisti_accounting_nbr(cell37.getStringCellValue().trim());
                }

                pos.setStatus("9");
                pos.setFile_id(fileId);
                pos.setFile_url(file_url);
                pos.setType("2");
                pos.setData_from("1");
                pos.setCreated_user(this.getUser().getUserId());
                
                XssUtils.getXssSaftBean(pos.getClass(), pos);

                pList.add(pos);
            }
            long result = 0;
            for (Pos pos1 : pList) {
                result = posService.createPosInfo(pos1);
            }
            checkEDI();
            if (result != 0) {
                this.setSuccessMessage("Success!");
            } else {
                this.setFailMessage("Fail! please confirm content");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return reMessage = "failed (Excel Format) !";
        }

        return reMessage;
    }

    private String getCellValue(XSSFCell cell) {
        String cellValue = "";
        switch (cell.getCellType()) {
        case XSSFCell.CELL_TYPE_STRING:
            cellValue = cell.getRichStringCellValue().getString().trim();
            break;
        case XSSFCell.CELL_TYPE_NUMERIC:
            Double dd = cell.getNumericCellValue();
            System.out.println(dd);

            String ss = String.valueOf(dd);
            if (ss.length() > 15) {
                ss = ss.substring(0, ss.length() - 1);
                System.out.println(ss);
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
    }

    // 03 or 07
    public String findOrderExcel() {
        String result;
        this.setSuccessMessage("");
        this.setFailMessage("");
        System.out.println(path);
        path = path.replace("Â ", " ");
        file_url = file_url.replace("Â ", " ");
        if (ExcelUtil.getExcelStyle(path).intValue() == 1) {
            result = findOrderExcelXls(uploadFile);
            if ("Success!".equals(result)) {
                this.setSuccessMessage("Success!");
            } else {
                this.setFailMessage(result);
            }
            return RESULT_MESSAGE;
        } else if (ExcelUtil.getExcelStyle(path).intValue() == 2) {
            result = findOrderExcelXlsx(uploadFile);
            if ("Success!".equals(result)) {
                this.setSuccessMessage("Success!");
            } else {
                this.setFailMessage(result);
            }
            return RESULT_MESSAGE;
        } else {
            this.setFailMessage("failed (Excel Format) !");
            return RESULT_MESSAGE;
        }
    }

    // EDI check
    public String checkEDIs() throws Exception {
        try {
            pos = new Pos();
            String[] s = ids.split(",");
            for (String s1 : s) {
                // pos.setId(Long.valueOf(s1));
                Pos p = new Pos();
                p.setId(Long.valueOf(s1));
                p.setTransaction_code("C");
                Pos pos = posService.searchPosByIdForOneCheck(p);
                posService.claimCheckEDI(pos);
            }

        } catch (Exception e) {
            this.setFailMessage("Data error please contact the system administrator!");
            return RESULT_MESSAGE;
        }

        this.setSuccessMessage("Success!");

        return RESULT_MESSAGE;
    }

    public String checkEDI() {
        try {
            posService.claimCheckEDI();
        } catch (Exception e) {
            this.setFailMessage("Data error please contact the system administrator !");
            return RESULT_MESSAGE;
        }

        this.setSuccessMessage("Success!");

        return RESULT_MESSAGE;
    }

    public void downloadExcelModel() {
        try {
            List<String> list = new ArrayList<String>();
            list.add("STATUS");
            list.add("ERROR CODES");
            list.add("SAPCLAIMNBR ");
            list.add("TRANSACTION CODE");
            list.add("DISTI");
            list.add("DISTIBRANCH(Payer Code)");
            list.add("DISTI ACCT#");
            list.add("BOOKPART");
            list.add("SHIP QTY");
            list.add("SHIP DATE(YYYYMMDD)");

            list.add("DEBITNUMBER");
            list.add("DISTICLAIMNBR(Country+DIS+YYMM+Serial NO#)");
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
            list.add("ENDCUST ZIP ");
            list.add("ENDCUST STATE");
            list.add("ENDCUSTCOUNTRY");
            list.add("VALIDATIONERRORS");

            list.add("DISTIACCOUNTINGNBR(same with C)");

            File source = new File("claim.xls");
            WritableWorkbook wwb = Workbook.createWorkbook(source);
            WritableSheet sheet = wwb.createSheet("claim", 0);
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
            Label label38 = null;
            Label label39 = null;
            Label label40 = null;
            for (int i = 0; i < list.size(); i++) {
                label0 = new Label(i, 0, list.get(i).toString());
                sheet.addCell(label0);
            }
            List<Pos> poList = new ArrayList<Pos>();
            pos = new Pos();
            pos.setStatus(status);
            pos.setType("2");
            if (StringUtils.isNotEmpty(branchId) && StringUtils.isNotEmpty(branchId.trim())) {
                try {
                    branchId = java.net.URLDecoder.decode(branchId, "UTF-8");
                    branchId = branchId.toUpperCase();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            pos.setDisti_name(branchId);
            pos.setDisti_cost_currency(currency_name);
            pos.setStart(this.getStart());
            pos.setEnd(this.getEnd());
            pos.setStatus(status);
            pos.setFile_id(file_id);
            if (null != start_timeString && !"".equals(start_timeString))
                pos.setStart_time(DateUtil.getDateFromStr(start_timeString));
            if (null != end_timeString && !"".equals(end_timeString))
                pos.setEnd_time(DateUtil.getDateFromStr(end_timeString));
            if (StringUtils.isNotEmpty(file_url) && StringUtils.isNotEmpty(file_url.trim())) {
                try {
                    file_url = java.net.URLDecoder.decode(file_url, "UTF-8");
                    file_url = file_url.toUpperCase();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            pos.setFile_url(file_url);

            poList = posService.searchPosListForAll(pos);
            System.out.println(poList.size());
            for (int i = 0; i < poList.size(); i++) {
                label0 = new Label(0, i + 1, poList.get(i).getStatus());
                label1 = new Label(1, i + 1, poList.get(i).getTips());
                label2 = new Label(2, i + 1, poList.get(i).getSapClaimNbr());
                label3 = new Label(3, i + 1, poList.get(i).getTransaction_code());
                label4 = new Label(4, i + 1, poList.get(i).getDisti_name());
                label5 = new Label(5, i + 1, poList.get(i).getDisti_branch());
                label6 = new Label(6, i + 1, poList.get(i).getDisti_city());
                label7 = new Label(7, i + 1, poList.get(i).getBook_part());
                label8 = new Label(8, i + 1, poList.get(i).getShip_qty());
                label9 = new Label(9, i + 1, poList.get(i).getShip_date());
                label10 = new Label(10, i + 1, poList.get(i).getDebit_number());
                label11 = new Label(11, i + 1, poList.get(i).getDisti_claimnbr());
                label12 = new Label(12, i + 1, poList.get(i).getOppreg_nbr());
                label13 = new Label(13, i + 1, poList.get(i).getCpn());
                label14 = new Label(14, i + 1, poList.get(i).getDisti_invoice_nbr());
                label15 = new Label(15, i + 1, poList.get(i).getDisti_invoice_item_number());
                label16 = new Label(16, i + 1, poList.get(i).getDisti_cost());
                label17 = new Label(17, i + 1, poList.get(i).getCost_denom());
                label18 = new Label(18, i + 1, poList.get(i).getDisti_cost_currency());
                label19 = new Label(19, i + 1, poList.get(i).getDisti_cost_exchangeRate());
                label20 = new Label(20, i + 1, poList.get(i).getDisti_bookcost());
                label21 = new Label(21, i + 1, poList.get(i).getDbc_denom());
                label22 = new Label(22, i + 1, poList.get(i).getDbc_currency_code());
                label23 = new Label(23, i + 1, poList.get(i).getDbc_exchange_rate());
                label24 = new Label(24, i + 1, poList.get(i).getDisti_resale());
                label25 = new Label(25, i + 1, poList.get(i).getDisti_resale_denom());
                label26 = new Label(26, i + 1, poList.get(i).getDisti_resale_currency());
                label27 = new Label(27, i + 1, poList.get(i).getDisti_resale_exchange_rate());
                label28 = new Label(28, i + 1, poList.get(i).getPurchasing_customer_name());
                label29 = new Label(29, i + 1, poList.get(i).getPurchasing_cust_city());
                label30 = new Label(30, i + 1, poList.get(i).getPurchasing_cust_zip());
                label31 = new Label(31, i + 1, poList.get(i).getPurchasing_cust_state());
                label32 = new Label(32, i + 1, poList.get(i).getPurchasing_cust_country());
                label33 = new Label(33, i + 1, poList.get(i).getEndcust_name());
                label34 = new Label(34, i + 1, poList.get(i).getEndcust_city());
                label35 = new Label(35, i + 1, poList.get(i).getEndcust_loc());
                label36 = new Label(36, i + 1, poList.get(i).getEndcust_zip());
                label37 = new Label(37, i + 1, poList.get(i).getEndcust_state());
                label38 = new Label(38, i + 1, poList.get(i).getEndcust_country());
                label39 = new Label(39, i + 1, poList.get(i).getDisti_num());
                label40 = new Label(40, i + 1, poList.get(i).getDisti_accounting_nbr());
                
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
                sheet.addCell(label38);
                sheet.addCell(label39);
                sheet.addCell(label40);
            }
            wwb.write();
            wwb.close();

            display(source, "Claim.xls", ServletActionContext.getResponse());
        } catch (Exception e) {
            this.setFailMessage("Claim Format Error !");
        }
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

    @PermissionSearch
    @JsonResult(field = "poList", include = { "id", "row_no", "sapClaimNbr", "transaction_code", "disti_name",
            "disti_name1", "disti_branch", "disti_city", "book_part", "book_part1", "book_part2", "ship_qty",
            "ship_date", "debit_number", "oppreg_nbr", "disti_invoice_nbr", "disti_invoice_item_number", "cost_denom",
            "dbc_denom", "disti_cost_currency", "disti_resale_denom", "disti_bookcost", "disti_cost",
            "disti_resale_currency", "purchasing_customer_name", "purchasing_cust_city", "purchasing_cust_zip",
            "dbc_currency_code", "disti_cost_exchangeRate", "purchasing_cust_state", "purchasing_cust_country",
            "endcust_name", "endcust_name1", "endcust_name2", "endcust_city", "endcust_loc", "endcust_zip",
            "endcust_state", "endcust_country", "status", "file_id", "file_url", "status_num", "created_user",
            "created_time", "disti_accounting_nbr", "disti_claimnbr", "oppreg_nbr", "disti_resale_denom_total",
            "cost_denom_total", "dbc_denom_total", "update_time" }, total = "total")
    public String getPosListForB() {

        Pos pos = new Pos();
        if (StringUtils.isNotEmpty(branchId) && StringUtils.isNotEmpty(branchId.trim())) {
            try {
                branchId = java.net.URLDecoder.decode(branchId, "UTF-8");
                branchId = branchId.toUpperCase();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        pos.setStart(this.getStart());
        pos.setEnd(this.getEnd());
        pos.setStatus(status);

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

        // 查询update_time
        if (null != order_start_timeString && !"".equals(order_start_timeString))
            pos.setCreated_time_start(DateUtil.getDateFromStr(order_start_timeString));
        if (null != order_end_timeString && !"".equals(order_end_timeString))
            pos.setCreated_time_end(DateUtil.getDateFromStr(order_end_timeString));

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
        if (StringUtils.isNotEmpty(endcust_name) && StringUtils.isNotEmpty(endcust_name.trim())) {
            try {
                endcust_name = java.net.URLDecoder.decode(endcust_name, "UTF-8");
                endcust_name = endcust_name.toUpperCase();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        pos.setEndcust_name(endcust_name);
        pos.setSapClaimNbr(sapClaimNbr);

        pos.setDisti_invoice_nbr(disti_invoice_nbr);

        if (data_from != null && !"0".equals(data_from))
            pos.setData_from(data_from);

        pos.setType("2");
        total = posService.searchClaimListCountForBb(pos);
        if (total != 0) {
            poList = posService.searchClaimListForBb(pos);
            for (Pos p : poList) {
                p.setDisti_name1(p.getDisti_name());
                p.setBook_part1(p.getBook_part());
                p.setBook_part2(p.getBook_part());
                p.setEndcust_name1(p.getEndcust_name());
                p.setEndcust_name2(p.getEndcust_name());

                java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
                nf.setGroupingUsed(false);

                if (p.getDisti_resale_denom() == null || p.getShip_qty() == null) {
                    p.setDisti_resale_denom_total("0");
                } else {
                    Double d = (Double.valueOf(p.getDisti_resale_denom())) * (Double.valueOf(p.getShip_qty()));
                    p.setDisti_resale_denom_total(String.valueOf(nf.format(d)));
                    System.out.println("d:=" + nf.format(d));
                }

                if (p.getCost_denom() == null || p.getShip_qty() == null) {
                    p.setCost_denom_total("0");
                } else {
                    Double e = (Double.valueOf(p.getCost_denom())) * (Double.valueOf(p.getShip_qty()));
                    p.setCost_denom_total(String.valueOf(nf.format(e)));
                    System.out.println("e:=" + nf.format(e));
                }

                if (p.getDbc_denom() == null || p.getShip_qty() == null) {
                    p.setDbc_denom_total("0");
                } else {
                    Double f = (Double.valueOf(p.getDbc_denom())) * (Double.valueOf(p.getShip_qty()));
                    p.setDbc_denom_total(String.valueOf(nf.format(f)));
                    System.out.println("d:=" + nf.format(f));
                }
            }
        }

        return JSON;
    }

    public void downloadExcelModelForBb() {
        try {
            List<String> list = new ArrayList<String>();
            list.add("Status");
            list.add("Fiscal Year/Period");
            list.add("Global Account");
            list.add("Sold-to Party");
            list.add("Debit Distributor");
            list.add("Debit Number");
            list.add("Order Date");
            list.add("Sap Claim Nbr");
            list.add("Book Part Number");
            list.add("POS Book Part Number");
            list.add("Debit Book Part");

            list.add("Debit Customer");
            list.add("Debit End Customer");
            list.add("End Cust Name");
            list.add("Ship Date");
            list.add("Currency Code");
            list.add("Invoice Number");
            list.add("Invoice Item Number");
            list.add("Ship Qty");
            list.add("Disti Resale");
            list.add("Disti Resale Total");

            list.add("Disti Resale Currency");
            list.add("Disti Cost");
            list.add("Disti Cost Total");
            list.add("Disti Cost Currency");
            list.add("Dbc Denom");
            list.add("Dbc Denom Total");
            list.add("Dbc Currency Code");
            list.add("Disti Claimnbr");
            list.add("Oppreg Nbr");

            list.add("approve remark");

            File source = new File("claim.xls");
            WritableWorkbook wwb = Workbook.createWorkbook(source);
            WritableSheet sheet = wwb.createSheet("claim", 0);
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

            for (int i = 0; i < list.size(); i++) {
                label0 = new Label(i, 0, list.get(i).toString());
                sheet.addCell(label0);
            }
            List<Pos> poList = new ArrayList<Pos>();
            Pos pos = new Pos();
            if (StringUtils.isNotEmpty(branchId) && StringUtils.isNotEmpty(branchId.trim())) {
                try {
                    branchId = java.net.URLDecoder.decode(branchId, "UTF-8");
                    branchId = branchId.toUpperCase();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            pos.setStart(this.getStart());
            pos.setEnd(this.getEnd());
            pos.setStatus(status);

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
            if (StringUtils.isNotEmpty(endcust_name) && StringUtils.isNotEmpty(endcust_name.trim())) {
                try {
                    endcust_name = java.net.URLDecoder.decode(endcust_name, "UTF-8");
                    endcust_name = endcust_name.toUpperCase();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            pos.setEndcust_name(endcust_name);
            pos.setSapClaimNbr(sapClaimNbr);

            if (null != order_start_timeString && !"".equals(order_start_timeString))
                pos.setCreated_time_start(DateUtil.getDateFromStr(order_start_timeString));
            if (null != order_end_timeString && !"".equals(order_end_timeString))
                pos.setCreated_time_end(DateUtil.getDateFromStr(order_end_timeString));

            pos.setDisti_invoice_nbr(disti_invoice_nbr);
            if (data_from != null && !"0".equals(data_from))
                pos.setData_from(data_from);

            pos.setType("2");
            poList = posService.searchClaimListForBbAll(pos);
            for (Pos p : poList) {
            	try {
            		p.setDisti_name1(p.getDisti_name());
                    p.setBook_part1(p.getBook_part());
                    p.setBook_part2(p.getBook_part());
                    p.setEndcust_name1(p.getEndcust_name());
                    p.setEndcust_name2(p.getEndcust_name());

                    java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
                    nf.setGroupingUsed(false);

                    if (p.getDisti_resale_denom() == null || p.getShip_qty() == null) {
                        p.setDisti_resale_denom_total("0");
                    } else {
                        Double d = (Double.valueOf(p.getDisti_resale_denom())) * (Double.valueOf(p.getShip_qty()));
                        p.setDisti_resale_denom_total(String.valueOf(nf.format(d)));
                        System.out.println("d:=" + nf.format(d));
                    }

                    if (p.getCost_denom() == null || p.getShip_qty() == null) {
                        p.setCost_denom_total("0");
                    } else {
                        Double e = (Double.valueOf(p.getCost_denom())) * (Double.valueOf(p.getShip_qty()));
                        p.setCost_denom_total(String.valueOf(nf.format(e)));
                        System.out.println("e:=" + nf.format(e));
                    }

                    if (p.getDbc_denom() == null || p.getShip_qty() == null) {
                        p.setDbc_denom_total("0");
                    } else {
                        Double f = (Double.valueOf(p.getDbc_denom())) * (Double.valueOf(p.getShip_qty()));
                        p.setDbc_denom_total(String.valueOf(nf.format(f)));
                        System.out.println("d:=" + nf.format(f));
                    }
            	}
            	catch (Exception e) {
					System.out.println(e.getMessage());
				}
            }

            for (int i = 0; i < poList.size(); i++) {
            	try {
            		label0 = new Label(0, i + 1, poList.get(i).getStatus());
                    label1 = new Label(1, i + 1, DateUtil.getDateTime(poList.get(i).getCreated_time(), "yyyy-MM-dd"));
                    label2 = new Label(2, i + 1, poList.get(i).getDisti_name());
                    label3 = new Label(3, i + 1, poList.get(i).getDisti_branch());
                    label4 = new Label(4, i + 1, poList.get(i).getDisti_name1());
                    label5 = new Label(5, i + 1, poList.get(i).getDebit_number());
                    label6 = new Label(6, i + 1, DateUtil.getDateTime(poList.get(i).getUpdate_time(), "yyyy-MM-dd"));
                    label7 = new Label(7, i + 1, poList.get(i).getSapClaimNbr());
                    label8 = new Label(8, i + 1, poList.get(i).getBook_part());
                    label9 = new Label(9, i + 1, poList.get(i).getBook_part());
                    label10 = new Label(10, i + 1, poList.get(i).getBook_part());
                    label11 = new Label(11, i + 1, poList.get(i).getEndcust_name());
                    label12 = new Label(12, i + 1, poList.get(i).getEndcust_name());
                    label13 = new Label(13, i + 1, poList.get(i).getEndcust_name());
                    label14 = new Label(14, i + 1, poList.get(i).getShip_date());
                    label15 = new Label(15, i + 1, poList.get(i).getDisti_cost_currency());
                    label16 = new Label(16, i + 1, poList.get(i).getDisti_invoice_nbr());
                    label17 = new Label(17, i + 1, poList.get(i).getDisti_invoice_item_number());
                    label18 = new Label(18, i + 1, poList.get(i).getShip_qty());
                    label19 = new Label(19, i + 1, poList.get(i).getDisti_resale_denom());
                    label20 = new Label(20, i + 1, poList.get(i).getDisti_resale_denom_total());
                    label21 = new Label(21, i + 1, poList.get(i).getDisti_resale_currency());
                    label22 = new Label(22, i + 1, poList.get(i).getCost_denom());
                    label23 = new Label(23, i + 1, poList.get(i).getCost_denom_total());
                    label24 = new Label(24, i + 1, poList.get(i).getDisti_cost_currency());
                    label25 = new Label(25, i + 1, poList.get(i).getDbc_denom());
                    label26 = new Label(26, i + 1, poList.get(i).getDbc_denom_total());
                    label27 = new Label(27, i + 1, poList.get(i).getDbc_currency_code());
                    label28 = new Label(28, i + 1, poList.get(i).getDisti_claimnbr());
                    label29 = new Label(29, i + 1, poList.get(i).getOppreg_nbr());
                    label30 = new Label(30, i + 1, poList.get(i).getRejecttoapprove_remark());

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
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
            }
            wwb.write();
            wwb.close();

            display(source, "Claim.xls", ServletActionContext.getResponse());
        } catch (Exception e) {
            this.setFailMessage("Claim Format Error !");
        }
    }

    // 单条导出
    public void downloadExcelModelForOne() {
        try {
            List<String> list = new ArrayList<String>();
            list.add("STATUS");
            list.add("ERROR CODES");
            list.add("SAPCLAIMNBR");
            list.add("TRANSACTION CODE");
            list.add("DISTI");
            list.add("DISTIBRANCH(Payer Code)");
            list.add("DISTI ACCT#");
            list.add("BOOKPART");
            list.add("SHIP QTY");
            list.add("SHIP DATE(YYYYMMDD)");

            list.add("DEBITNUMBER");
            list.add("DISTICLAIMNBR(Country+DIS+YYMM+Serial NO#)");
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
            list.add("ENDCUST ZIP ");
            list.add("ENDCUST STATE");
            list.add("ENDCUSTCOUNTRY");
            list.add("VALIDATIONERRORS");

            list.add("DISTIACCOUNTINGNBR(same with C)");// 41

            list.add("APPROVE REMARK");

            File source = new File("claim.xls");
            WritableWorkbook wwb = Workbook.createWorkbook(source);
            WritableSheet sheet = wwb.createSheet("claim", 0);
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
            Label label38 = null;
            Label label39 = null;
            Label label40 = null;
            Label label41 = null;
            for (int i = 0; i < list.size(); i++) {
                label0 = new Label(i, 0, list.get(i).toString());
                sheet.addCell(label0);
            }
            List<Pos> poList = new ArrayList<Pos>();
            pos = new Pos();
            pos.setDisti_name(branchId);
            if (StringUtils.isNotEmpty(branchId) && StringUtils.isNotEmpty(branchId.trim())) {
                try {
                    branchId = java.net.URLDecoder.decode(branchId, "UTF-8");
                    branchId = branchId.toUpperCase();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            pos.setDisti_cost_currency(currency_name);
            pos.setStatus(status);
            pos.setFile_id(file_id);
            if (null != start_timeString && !"".equals(start_timeString))
                pos.setStart_time(DateUtil.getDateFromStr(start_timeString));
            if (null != end_timeString && !"".equals(end_timeString))
                pos.setEnd_time(DateUtil.getDateFromStr(end_timeString));
            pos.setType("2");

            pos.setStart(this.getStart());
            pos.setEnd(this.getEnd());

            poList = posService.searchPosDetailListByIdForOne(pos);

            for (int i = 0; i < poList.size(); i++) {
                label0 = new Label(0, i + 1, poList.get(i).getStatus());
                label1 = new Label(1, i + 1, poList.get(i).getTips());
                label2 = new Label(2, i + 1, poList.get(i).getSapClaimNbr());
                label3 = new Label(3, i + 1, poList.get(i).getTransaction_code());
                label4 = new Label(4, i + 1, poList.get(i).getDisti_name());
                label5 = new Label(5, i + 1, poList.get(i).getDisti_branch());
                label6 = new Label(6, i + 1, poList.get(i).getDisti_city());
                label7 = new Label(7, i + 1, poList.get(i).getBook_part());
                label8 = new Label(8, i + 1, poList.get(i).getShip_qty());
                label9 = new Label(9, i + 1, poList.get(i).getShip_date());
                label10 = new Label(10, i + 1, poList.get(i).getDebit_number());
                label11 = new Label(11, i + 1, poList.get(i).getDisti_claimnbr());
                label12 = new Label(12, i + 1, poList.get(i).getOppreg_nbr());
                label13 = new Label(13, i + 1, poList.get(i).getCpn());
                label14 = new Label(14, i + 1, poList.get(i).getDisti_invoice_nbr());
                label15 = new Label(15, i + 1, poList.get(i).getDisti_invoice_item_number());
                label16 = new Label(16, i + 1, poList.get(i).getDisti_cost());
                label17 = new Label(17, i + 1, poList.get(i).getCost_denom());
                label18 = new Label(18, i + 1, poList.get(i).getDisti_cost_currency());
                label19 = new Label(19, i + 1, poList.get(i).getDisti_cost_exchangeRate());
                label20 = new Label(20, i + 1, poList.get(i).getDisti_bookcost());
                label21 = new Label(21, i + 1, poList.get(i).getDbc_denom());
                label22 = new Label(22, i + 1, poList.get(i).getDbc_currency_code());
                label23 = new Label(23, i + 1, poList.get(i).getDbc_exchange_rate());
                label24 = new Label(24, i + 1, poList.get(i).getDisti_resale());
                label25 = new Label(25, i + 1, poList.get(i).getDisti_resale_denom());
                label26 = new Label(26, i + 1, poList.get(i).getDisti_resale_currency());
                label27 = new Label(27, i + 1, poList.get(i).getDisti_resale_exchange_rate());
                label28 = new Label(28, i + 1, poList.get(i).getPurchasing_customer_name());
                label29 = new Label(29, i + 1, poList.get(i).getPurchasing_cust_city());
                label30 = new Label(30, i + 1, poList.get(i).getPurchasing_cust_zip());
                label31 = new Label(31, i + 1, poList.get(i).getPurchasing_cust_state());
                label32 = new Label(32, i + 1, poList.get(i).getPurchasing_cust_country());
                label33 = new Label(33, i + 1, poList.get(i).getEndcust_name());
                label34 = new Label(34, i + 1, poList.get(i).getEndcust_city());
                label35 = new Label(35, i + 1, poList.get(i).getEndcust_loc());
                label36 = new Label(36, i + 1, poList.get(i).getEndcust_zip());
                label37 = new Label(37, i + 1, poList.get(i).getEndcust_state());
                label38 = new Label(38, i + 1, poList.get(i).getEndcust_country());
                label39 = new Label(39, i + 1, poList.get(i).getDisti_num());
                label40 = new Label(40, i + 1, poList.get(i).getDisti_accounting_nbr());
                label41 = new Label(41, i + 1, poList.get(i).getRejecttoapprove_remark());
                
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
                sheet.addCell(label38);
                sheet.addCell(label39);
                sheet.addCell(label40);
                sheet.addCell(label41);
            }
            wwb.write();
            wwb.close();

            display(source, "Claim.xls", ServletActionContext.getResponse());
        } catch (Exception e) {
            this.setFailMessage("Claim Format Error !");
        }
    }

    public static void main(String[] args) {
        Double dd = 0.17200000000000001d;
        String ss = String.valueOf(dd);
        if (ss.length() > 10) {
            ss = ss.substring(0, ss.length() - 1);
            System.out.println(ss);
            Double ss1 = Double.valueOf(ss);
            BigDecimal bd1 = new BigDecimal(ss1.toString());

            System.out.println(bd1);
        }
        System.out.println("");
    }

    public String rejectToApproveRemark() {
        return "rejectToApproveRemark";
    }

    @PermissionSearch
    @JsonResult(field = "poList", include = { "id", "row_no", "sapClaimNbr", "transaction_code", "disti_name",
            "disti_branch", "disti_city", "book_part", "ship_qty", "ship_date", "debit_number", "disti_claimnbr",
            "oppreg_nbr", "disti_invoice_nbr", "disti_invoice_item_number", "disti_cost", "cost_denom",
            "disti_cost_currency", "disti_resale_denom", "disti_bookcost", "dbc_denom", "dbc_currency_code",
            "dbc_exchange_rate", "disti_resale", "disti_resale_currency", "purchasing_customer_name",
            "purchasing_cust_city", "purchasing_cust_zip", "disti_cost_exchangeRate", "purchasing_cust_state",
            "purchasing_cust_country", "endcust_name", "endcust_city", "endcust_loc", "endcust_zip", "endcust_state",
            "endcust_country", "status", "tips", "rejecttoapprove_remark", "file_id", "file_url", "m_12nc",
            "statue_num", "type", "disti_accounting_nbr", "cmd_pc_name", "cmd_pc_city", "cmd_pc_country",
            "quote_pc_name", "quote_pc_city", "quote_pc_country", "cmd_ec_name", "cmd_ec_city", "cmd_ec_country",
            "quote_ec_name", "quote_ec_city", "quote_ec_country" }, total = "total")
    public String getPosList6q6t() {

        pos = new Pos();
        if (StringUtils.isNotEmpty(branchId) && StringUtils.isNotEmpty(branchId.trim())) {
            try {
                branchId = java.net.URLDecoder.decode(branchId, "UTF-8");
                branchId = branchId.toUpperCase();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        pos.setDisti_name(branchId);
        if (StringUtils.isNotEmpty(material_name) && StringUtils.isNotEmpty(material_name.trim())) {
            try {
                material_name = java.net.URLDecoder.decode(material_name, "UTF-8");
                material_name = material_name.toUpperCase();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        pos.setBook_part(material_name);
        pos.setDisti_cost_currency(currency_name);
        pos.setStatus(status);
        pos.setFile_id(file_id);
        if (null != start_timeString && !"".equals(start_timeString))
            pos.setStart_time(DateUtil.getDateFromStr(start_timeString));
        if (null != end_timeString && !"".equals(end_timeString))
            pos.setEnd_time(DateUtil.getDateFromStr(end_timeString));
        pos.setType("2");

        pos.setStart(this.getStart());
        pos.setEnd(this.getEnd());
        if (null != pc_ec_name && !"".equals(pc_ec_name))
            pos.setPc_ec_name(pc_ec_name.toUpperCase());
        if (null != error_code && !"".equals(error_code))
            pos.setError_code(error_code.toUpperCase());
        if (null != debit_number && !"".equals(debit_number))
            pos.setDebit_number(debit_number.toUpperCase());
        if (null != invoice_number && !"".equals(invoice_number))
            pos.setInvoice_number(invoice_number.toUpperCase());

        total = posService.searchPosDetailListCount6q6t(pos);
        if (total != 0) {
            poList = posService.searchPosDetailList6q6t(pos);
            for (Pos p : poList) {
                List<Pos> pp = posService.getClaimErrorCodeDetail(p);
                for (Pos dd : pp) {
                    if ("Claim(CMD)".equals(dd.getDisti_name())) {
                        p.setCmd_pc_name(dd.getPurchasing_customer_name());
                        p.setCmd_pc_city(dd.getPurchasing_cust_city());
                        p.setCmd_pc_country(dd.getPurchasing_cust_country());
                        p.setCmd_ec_city(dd.getEndcust_city());
                        p.setCmd_ec_country(dd.getEndcust_country());
                        p.setCmd_ec_name(dd.getEndcust_name());
                    }

                    if ("Quote(CMD)".equals(dd.getDisti_name())) {
                        p.setQuote_pc_name(dd.getPurchasing_customer_name());
                        p.setQuote_pc_city(dd.getPurchasing_cust_city());
                        p.setQuote_pc_country(dd.getPurchasing_cust_country());
                        p.setQuote_ec_city(dd.getEndcust_city());
                        p.setQuote_ec_country(dd.getEndcust_country());
                        p.setQuote_ec_name(dd.getEndcust_name());
                    }
                }

            }
        }

        return JSON;
    }

    public void downloadExcelForClaim6q6t() throws Exception {
        try {
            this.setPage(1);
            this.setRows(1000000);
            getPosList6q6t();

            Date aDate = new Date();
            SimpleDateFormat aDateFormat = new SimpleDateFormat("yyyyMMdd");
            String dateString = aDateFormat.format(aDate);
            String fileName = "Pos" + dateString;

            CommonUtil.setExcelResponseInfo(getServletRequest(), getServletResponse(), fileName);
            String[] titles = { "TRANSACTION CODE", "Error Codes", "File Id", "ID", "DISTI", "DISTIBRANCH", "DISTIACCT",
                    "BOOK PART", "SHIP QTY", "SHIP DATE", "DEBIT NUMBER", "DISTI CLAIM NBR", "DISTI INVOICENBR",
                    "DISTI INVOICE ITEM NUMBER", "Disti Cost", "Cost Denom", "DISTI COST CURRENCY",
                    "Disti Cost ExchangeRate", "Disti Bookcost", "Dbc Denom", "Dbc Currency Code",
                    "Dbc Exchange Rate 	", "Disti Resale", "DISTI RESALE DENOM", "DISTI RESALE CURRENCY",
                    "Disti Resale Exchange Rate", "PURCHASING CUSTOMER NAME 	", "PURCHASING CUST CITY",
                    "PURCHASING CUST ZIP", "PURCHASING CUST STATE", "PURCHASING CUST COUNTRY", "ENDCUST NAME",
                    "ENDCUST CITY", "ENDCUST LOC", "ENDCUST ZIP", "ENDCUST STATE 	", "ENDCUST COUNTRY 	",
                    "DISTI ACCOUNTING NBR", "	(CMD)PC NAME", "	(CMD)PC CITY ", "(CMD)PC COUNTRY", "(quote)PC NAME",
                    "(quote)PC CITY", "(quote)PC COUNTRY", "(CMD)EC NAME ", "(CMD)EC CITY 	", "(CMD)EC COUNTRY",
                    "(quote)EC NAME", "(quote)EC CITY", "(quote)EC COUNTRY" };
            String[] keys = { "transaction_code", "tips", "file_id", "ID", "disti_name", "disti_branch", "disti_city",
                    "book_part", "ship_qty", "ship_date", "debit_number", "disti_claimnbr", "disti_invoice_nbr",
                    "disti_invoice_item_number", "disti_cost", "cost_denom", "disti_cost_currency",
                    "disti_cost_exchangeRate", "disti_bookcost", "dbc_denom", "dbc_currency_code", "dbc_exchange_rate",
                    "disti_resale", "disti_resale_denom", "disti_resale_currency", "Disti_resale_exchange_rate",
                    "purchasing_customer_name", "purchasing_cust_city", "purchasing_cust_zip", "purchasing_cust_state",
                    "purchasing_cust_country", "endcust_name", "endcust_city", "endcust_loc", "endcust_zip",
                    "endcust_state", "endcust_country", "disti_accounting_nbr", "cmd_pc_name", "cmd_pc_city",
                    "cmd_pc_country", "quote_pc_name", "quote_pc_city", "quote_pc_country", "cmd_ec_name",
                    "cmd_ec_city", "cmd_ec_country", "quote_ec_name", "quote_ec_city", "quote_ec_country" };
            List<Map<String, String>> li = new ArrayList<Map<String, String>>();
            for (Pos p : poList) {
                li.add(CommonUtil.transBean2Map(p));
            }
            CommonUtil.exportExcel(li, titles, keys, getServletResponse().getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
