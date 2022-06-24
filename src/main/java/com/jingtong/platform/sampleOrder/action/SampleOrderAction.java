package com.jingtong.platform.sampleOrder.action;

import java.io.File;
import java.io.FileInputStream;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.allUser.service.IAllUserService;
import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.common.CommonUtil;
import com.jingtong.platform.customer.pojo.CustomerUser;
import com.jingtong.platform.customer.service.ICustomerService;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.framework.util.ExcelUtil;
import com.jingtong.platform.framework.util.FileUtil;
import com.jingtong.platform.framework.util.JsonUtil;
import com.jingtong.platform.framework.util.StockUtil;
import com.jingtong.platform.framework.util.XssUtils;
import com.jingtong.platform.message.pojo.Message;
import com.jingtong.platform.message.service.IMessageService;
import com.jingtong.platform.org.pojo.Borg;
import com.jingtong.platform.product.pojo.Product;
import com.jingtong.platform.product.service.IProductService;
import com.jingtong.platform.role.pojo.Role;
import com.jingtong.platform.role.service.IRoleService;
import com.jingtong.platform.sampleOrder.pojo.AccountManager;
import com.jingtong.platform.sampleOrder.pojo.SampleOrder;
import com.jingtong.platform.sampleOrder.pojo.SampleOrderAndDetail;
import com.jingtong.platform.sampleOrder.pojo.SampleOrderDetail;
import com.jingtong.platform.sampleOrder.service.ISampleOrderService;
import com.jingtong.platform.sap.pojo.OrderToSap;
import com.jingtong.platform.sap.service.OrderToSapService;
import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.dict.pojo.CmsTbDictType;
import com.jingtong.platform.dict.service.*;

import jxl.CellType;
import jxl.DateCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;

public class SampleOrderAction extends BaseAction {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private IDictService dictService;
    private ISampleOrderService sampleOrderService;
    private OrderToSapService orderToSapService;
    private SampleOrder so;
    private List<SampleOrder> soList;
    private SampleOrderDetail sod;
    private List<SampleOrderDetail> sodList;
    private List<SampleOrderAndDetail> soanddetailList;
    private List<Borg> orgList;
    private List<AccountManager> accountManageList;
    private String orderDetailJson;
    private String id;
    private String managerId;
    private String delOrderDetail;
    private String order_id;
    private String sap_order_id;
    private String project_name;
    private int total;
    private String main_id;
    private String branch_id;
    private String ids;
    private CustomerUser cusUser;
    private List<CustomerUser> cusUserList;
    private ICustomerService customerService;
    private IMessageService messageService;
    private IAllUserService allUserService;
    private List<Role> roleList;// 登陆人角色列表
    private IRoleService roleService;
    private String loginRole;// 标记登陆人的角色
    private String start_dateStr;
    private String end_dateStr;
    private String states;
    private String uploadFile;
    private Product p;
    private List<Product> pList;
    private IProductService productService;
    private String path;
    private String currency_code;
    private String office_id;
    private String customer_id;
    private String company;
    private String contact_name;
    private String wfeDownloadPath;
    private String imgUploadPath;
    private String imgUploadPathUrl;
    private String fileId;
    private String file_name;
    private String file_path;
    private File upload;
    private String uploadFileName;
    private String shipToRegion;
    private String forWho;
    private String userId;
    private String remark;
    private String orderStatus;
    private String type;
    
    public List<SampleOrderAndDetail> getSoanddetailList() {
        return soanddetailList;
    }

    public void setSoanddetailList(List<SampleOrderAndDetail> soanddetailList) {
        this.soanddetailList = soanddetailList;
    }

    public IAllUserService getAllUserService() {
        return allUserService;
    }

    public void setAllUserService(IAllUserService allUserService) {
        this.allUserService = allUserService;
    }
    public SampleOrder getSo() {
        return so;
    }

    public void setSo(SampleOrder so) {
        this.so = so;
    }
    
    public String getShipToRegion() {
        return shipToRegion;
    }

    public void setShipToRegion(String shipToRegion) {
        this.shipToRegion = shipToRegion;
    }
    
    public List<SampleOrder> getSoList() {
        return soList;
    }

    public void setSoList(List<SampleOrder> soList) {
        this.soList = soList;
    }

    public SampleOrderDetail getSod() {
        return sod;
    }

    public void setSod(SampleOrderDetail sod) {
        this.sod = sod;
    }

    public List<SampleOrderDetail> getSodList() {
        return sodList;
    }

    public void setSodList(List<SampleOrderDetail> sodList) {
        this.sodList = sodList;
    }

    public String getOrderDetailJson() {
        return orderDetailJson;
    }

    public void setOrderDetailJson(String orderDetailJson) {
        this.orderDetailJson = orderDetailJson;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDelOrderDetail() {
        return delOrderDetail;
    }

    public void setDelOrderDetail(String delOrderDetail) {
        this.delOrderDetail = delOrderDetail;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ISampleOrderService getSampleOrderService() {
        return sampleOrderService;
    }

    public void setSampleOrderService(ISampleOrderService sampleOrderService) {
        this.sampleOrderService = sampleOrderService;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
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

    public String getMain_id() {
        return main_id;
    }

    public IMessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(IMessageService messageService) {
        this.messageService = messageService;
    }

    public IDictService getDictService() {
		return dictService;
	}

	public void setDictService(IDictService dictService) {
		this.dictService = dictService;
	}

	public void setMain_id(String main_id) {
        this.main_id = main_id;
    }

    public String getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(String branch_id) {
        this.branch_id = branch_id;
    }

    public String getSap_order_id() {
        return sap_order_id;
    }

    public void setSap_order_id(String sap_order_id) {
        this.sap_order_id = sap_order_id;
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

    public String getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(String uploadFile) {
        this.uploadFile = uploadFile;
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

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String manager_id) {
        this.managerId = manager_id;
    }

    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public OrderToSapService getOrderToSapService() {
        return orderToSapService;
    }

    public void setOrderToSapService(OrderToSapService orderToSapService) {
        this.orderToSapService = orderToSapService;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getWfeDownloadPath() {
        return wfeDownloadPath;
    }

    public void setWfeDownloadPath(String wfeDownloadPath) {
        this.wfeDownloadPath = wfeDownloadPath;
    }

    public String getImgUploadPath() {
        return imgUploadPath;
    }

    public void setImgUploadPath(String imgUploadPath) {
        this.imgUploadPath = imgUploadPath;
    }

    public String getImgUploadPathUrl() {
        return imgUploadPathUrl;
    }

    public void setImgUploadPathUrl(String imgUploadPathUrl) {
        this.imgUploadPathUrl = imgUploadPathUrl;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
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

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public List<AccountManager> getAccountManageList() {
        return accountManageList;
    }

    public void setAccountManageList(List<AccountManager> accountManageList) {
        this.accountManageList = accountManageList;
    }
    
    public String getIds() {
        return ids;
    }

    public String getForWho() {
        return forWho;
    }

    public void setForWho(String forWho) {
        this.forWho = forWho;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String toImportPage() {
        return "toImportPage";
    }
    
    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public List<Borg> getOrgList() {
        return orgList;
    }

    public void setOrgList(List<Borg> orgList) {
        this.orgList = orgList;
    }

    /**
     * 跳转到excel导入页面
     * 
     * @return
     */
    public String importExcel() {

        return "importExcel";
    }

    public String toSearchSampleOrder() {
        String loginId = this.getUser().getLoginId();
        so = new SampleOrder();

        String orgString = this.getUser().getOrgId();
        if (orgString == null || "".equals(orgString)) {
            so.setLoginId(loginId);
        }
        if ("admin".equals(loginId)) {
            so.setLoginId(loginId);
        }

        return "toSearchSampleOrder";
    }

    public String toApproveSampleOrder() {
        String loginId = this.getUser().getLoginId();
        so = new SampleOrder();

        String orgString = this.getUser().getOrgId();
        if (orgString == null || "".equals(orgString)) {
            so.setLoginId(loginId);
        }
        if ("admin".equals(loginId)) {
            so.setLoginId(loginId);
        }
        userId = loginId;
        return "toApproveSampleOrder";
    }

    public String toViewSampleOrder() {
        so = new SampleOrder();
        so.setId(Long.valueOf(id));
        so = sampleOrderService.getSampleOrderById(so);
        return "toViewSampleOrder";
    }

    public String toCreateSampleOrder() {
        
        forWho = "";
        if (isDisti()) {
            forWho = "Disti";
        }
        
        so = new SampleOrder();
        so.setType_id("ZFD1");

        String orgidString = this.getUser().getOrgId();
        cusUser = new CustomerUser();
        cusUser.setUserId(this.getUser().getUserId());
        cusUserList = customerService.getLoginCusUser(cusUser);
        if (cusUserList != null && cusUserList.size() > 0) {
            so.setBranch_id(cusUserList.get(0).getTrafficExpense());
        }
        if (orgidString == null || "".equals(orgidString)) {

        } else {
            office_id = orgidString;
        }

        return "toCreateSampleOrder";
    }

    public String toUpdateSampleOrder() {
        so = new SampleOrder();
        so.setId(Long.valueOf(id));
        so = sampleOrderService.getSampleOrderById(so);
        return "toCreateSampleOrder";
    }

    /**
     * 查询订单信息
     * 
     * @return
     */
    @PermissionSearch
    @JsonResult(field = "soList", include = { "id", "order_id", "type_id", "branch_id", "currency_code", "ship_to",
            "payer_to", "billing_to", "saler", "sale_company", "end_customer", "city", "project_name", "contact_info",
            "state", "remark", "company", "sale_to", "country", "street", "create_time", "create_userId", "sync_state",
            "sap_order_id", "zip", "shipToAddress", "latest_time", "latest_userId", "org_code", "contact_name",
            "contact_tel", "ship_to_region", "applicant_name", "applicant_company", "account_manager", "comments", "manager_id"}, total = "total")
    public String getSampleOrderList() {

        so = new SampleOrder();
        so.setStart(getStart());
        so.setEnd(getEnd());
        so.setSort("aa.create_time");
        so.setDir("desc");
        so.setOrder_id(order_id);
        so.setProject_name(project_name);
        so.setSap_order_id(sap_order_id);
        so.setStart_dateStr(start_dateStr);
        so.setEnd_dateStr(end_dateStr);
        so.setStates(states);
        
        if (StringUtils.isNotEmpty(company) && StringUtils.isNotEmpty(company.trim())) {
            try {
                company = java.net.URLDecoder.decode(company, "UTF-8");
                company = company.toUpperCase();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        so.setCompany(company);
        if (StringUtils.isNotEmpty(contact_name) && StringUtils.isNotEmpty(contact_name.trim())) {
            try {
                contact_name = java.net.URLDecoder.decode(contact_name, "UTF-8");
                contact_name = contact_name.toUpperCase();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        so.setContact_name(contact_name);
        String loginId = this.getUser().getLoginId();

        String orgString = this.getUser().getOrgId();
        so.setLoginId(loginId);
        // admin 可以看到所有的Sample Order
        if(!"admin".equals(loginId)) {
            if (orgString == null || "".equals(orgString)) {
                // 外部人员只能看到自己创建的Sample Order
                so.setCreate_userId(this.getUser().getUserId());
                so.setRoleType("Disti");
            }
            else {
                so.setLoginId(loginId);
                String userId = this.getUser().getUserId();
                List<Role> roles = sampleOrderService.getRoleForSampleOrder(userId);
                
                boolean isYPDDManager = false;
                boolean isRegionSaler = false;
                boolean isYPDD = false;
                String orgName = "";
                
                for (Role soRole : roles) {
                    if ("YPDD_MANAGER".equals(soRole.getRoleId())) {
                        isYPDDManager = true;
                        break;
                    }
                    else if ("HK10_H_Sale_Mgmt".equals(soRole.getRoleId())) {
                        isRegionSaler = true;
                        orgName = soRole.getOrgName();
                    }
                    else if ("YPDD".equals(soRole.getRoleId())) {
                        isYPDD = true;
                    }
                }
                so.setRoleType("");
                if (isYPDDManager) {
                    // YPDD_MANAMER可以查看所有Sample Order
                }
                else if (isRegionSaler && isYPDD) {
                    // 有HK10_H_Sale_Mgmt权限的用户可以看到对应区域的Sample Order
                    // YPDD能看到自己创建的Sample Order
                    so.setRoleType("RY");
                    so.setShipToRegions(getOrgNames(orgName));
                    so.setCreate_userId(this.getUser().getUserId());
                }
                else if (isRegionSaler) {
                    // 有HK10_H_Sale_Mgmt权限的用户可以看到对应区域的Sample Order
                    so.setRoleType("R");
                    so.setShipToRegions(getOrgNames(orgName));
                }
                else if (isYPDD) {
                    // YPDD只能看到自己创建的Sample Order
                    so.setRoleType("Y");
                    so.setCreate_userId(this.getUser().getUserId());
                }
                else {
                    total = 0;
                    soList = new ArrayList<SampleOrder>();
                    return JSON;
                }
            }
        }

        total = sampleOrderService.getSampleOrderListCount(so);
        if (total > 0) {
            soList = sampleOrderService.getSampleOrderList(so);
        }
        return JSON;
    }

    /**
     * 查询订单明细信息
     * 
     * @return
     */
    @PermissionSearch
    @JsonResult(field = "sodList", include = { "id", "order_id", "row_no", "material_id", "material_name",
            "material_typeId", "material_groupId", "sale_unit", "limited_QTY", "order_QTY", "delivery_dateStr",
            "confirm_dateStr", "lead_time", "delivery_date", "confirm_date", "remark", "ween_remark", "pq", "po_item",
            "pbMpp", "stock_status", "expressage_info", "ship_date", "invoice"})
    public String getSampleOrderDetailList() {
        sod = new SampleOrderDetail();
        sod.setOrder_id(order_id);
        sod.setMain_id(Long.valueOf(main_id));
        sodList = sampleOrderService.getSampleOrderDetailList(sod);
        return JSON;
    }
    
    /**
     * 获取数据字典配置的订单相关plant值
     */
    private String GetOrderPlantValue(String value)
    {
    	String result = "";
        CmsTbDict searchCmsTbDict = new CmsTbDict();
		searchCmsTbDict.setItemId(Long.valueOf(value));
		CmsTbDict cmsTbDict = dictService.getCmsTbDict(searchCmsTbDict);
        result = cmsTbDict.getItemValue();
    	return result;
    }
    
    /**
     * 创建订单信息
     * 
     * @return
     * @throws UnsupportedEncodingException
     * @throws ParseException
     */
    @SuppressWarnings("unchecked")
    public String createSampleOrder() throws UnsupportedEncodingException, ParseException, Exception {

        boolean isDisti = isDisti();
        
        this.setSuccessMessage("");
        this.setFailMessage("");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        orderDetailJson = java.net.URLDecoder.decode(this.orderDetailJson, "utf-8");
        sodList = JsonUtil.getDTOList(orderDetailJson, SampleOrderDetail.class);
        so.setCreate_userId(this.getUser().getUserId());
        // 从数据字典获取地区（cn、hk等）20210915 by sst
        String salegroup = this.GetOrderPlantValue("15001");
        so.setSale_group(salegroup);
        
        XssUtils.getXssSaftBean(so.getClass(), so);
        for (SampleOrderDetail sod : sodList) {
            sod.setOrder_id(so.getOrder_id());
            if (sod.getDelivery_dateStr() != null && !"".equals(sod.getDelivery_dateStr())
                    && !"undefined".equals(sod.getDelivery_dateStr())) {
                sod.setDelivery_date(sdf.parse(sod.getDelivery_dateStr()));
            }
            
            XssUtils.getXssSaftBean(sod.getClass(), sod);
        }

        if (!isDisti) {
            so.setState(0);
            
            if (!StringUtils.isEmpty(so.getAccount_manager()) && !"0".equals(so.getAccount_manager())) {
                so.setManager_id(so.getAccount_manager());

                AllUsers user = allUserService.getAllUsersByUserId(so.getAccount_manager());
                String[] strs = user.getEmail().split("@");
                so.setAccount_manager(strs[0]);               
            }
            else {
                so.setManager_id("0");
                so.setAccount_manager(StringUtils.EMPTY);               
            }
            
            BooleanResult bool = sampleOrderService.createSampleOrder(so, sodList);

            if (bool.getResult()) {
                this.setSuccessMessage("Success!");
                // 创建成功，同步至sap
                try {
                    // 获取表头
                    OrderToSap order = new OrderToSap();
                    order.setId(Long.valueOf(bool.getCode()));
                    List<OrderToSap> orderList = orderToSapService.getOrderTotal(order);
                    order = orderList.get(0);
                    // 获取明细
                    List<com.jingtong.platform.sap.pojo.OrderDetail> orderDetail = orderToSapService.getOrderDetail(order);
                    System.out.println(order.getId());

                    SampleOrder sorder = new SampleOrder();
                    sorder.setId(order.getId());
                    // 同步
                    orderToSapService.orderToSap(order, orderDetail, sorder);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                }

            } else {
                this.setFailMessage("Failed !");
            }
        }
        else {
            so.setManager_id(so.getAccount_manager());

            AllUsers user = allUserService.getAllUsersByUserId(so.getAccount_manager());
            String[] strs = user.getEmail().split("@");
            so.setAccount_manager(strs[0]);
            so.setState(1);
                        
            
            BooleanResult bool = sampleOrderService.createSampleOrder(so, sodList);

            if (bool.getResult()) {
                this.setSuccessMessage("Success!");
                
                // Send Mail
                String content = "<br>&nbsp;&nbsp;Disti " + this.getUser().getUserName() + " submit a Sample Order. Please login platform for approval";
                if (user.getEmail() != null) {
                    String contents = "Hi, " + user.getUserName() + content + "<br>";
                    sendMailByAddree(user.getEmail(), contents, "Sample Order Create");
                }
            }
        }
        return RESULT_MESSAGE;
    }

    /**
     * 修改订单信息
     * 
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    @SuppressWarnings("unchecked")
    public String updateSampleOrder() throws UnsupportedEncodingException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        this.setSuccessMessage("");
        this.setFailMessage("");

        orderDetailJson = java.net.URLDecoder.decode(this.orderDetailJson, "utf-8");
        sodList = JsonUtil.getDTOList(orderDetailJson, SampleOrderDetail.class);
        sod = new SampleOrderDetail();
        sod.setIds(delOrderDetail);
        so.setLatest_userId(this.getUser().getUserId());
        
        XssUtils.getXssSaftBean(so.getClass(), so);

        for (SampleOrderDetail sod : sodList) {
            sod.setOrder_id(so.getOrder_id());
            sod.setMain_id(so.getId());            
            
            XssUtils.getXssSaftBean(sod.getClass(), sod);
        }
        BooleanResult bool = sampleOrderService.updateSampleOrder(so, sodList, sod);
        if (bool.getResult()) {
            this.setSuccessMessage("Success!");
        } else {
            this.setFailMessage("Failed !");
        }
        return RESULT_MESSAGE;
    }

    public String deleteSampleOrder() {
        this.setFailMessage("");
        this.setSuccessMessage("");
        so = new SampleOrder();
        so.setId(Long.valueOf(id));
        int i = sampleOrderService.deleteSampleOrder(so);
        if (i > 0) {
            sod = new SampleOrderDetail();
            sod.setMain_id(so.getId());
            sampleOrderService.deleteSODofMain(sod);
            this.setSuccessMessage("Success !");
        } else {
            this.setFailMessage("failed !");
        }
        return RESULT_MESSAGE;
    }

    /**
     * 2007
     * 
     * @param path
     * @return
     */
    private String findOrderExcelXlsx(String path, Product pr) {
        StringBuilder contentResult = new StringBuilder();
        List<SampleOrderDetail> sodList = new ArrayList<SampleOrderDetail>();
        try {

            FileInputStream file = new FileInputStream(path);
            XSSFWorkbook xwb = new XSSFWorkbook(file);
            XSSFSheet sheet = xwb.getSheetAt(0);
            // 定义 row、cell
            XSSFRow row;
            // 循环输出表格中的内容
            if (sheet.getPhysicalNumberOfRows() <= 1) {
                return "The Excel is empty！";
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                if (row == null) {// 空行跳过
                    continue;
                }
                sod = new SampleOrderDetail();
                XSSFCell cell0 = row.getCell(0);
                if (cell0 != null) {
                    if (cell0.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        sod.setPo_item(String.valueOf((int) cell0.getNumericCellValue()));
                    } else {
                        sod.setPo_item(cell0.getStringCellValue().trim());
                    }
                }
                XSSFCell cell1 = row.getCell(1);
                if (cell1 != null) {
                    if (cell1.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        // 数字格式无法解析
                        cell1.setCellType(Cell.CELL_TYPE_STRING);
                    }
                    sod.setMaterial_id(cell1.getStringCellValue().trim());
                }

                XSSFCell cell2 = row.getCell(2);
                if (cell2 != null) {
                    if ("".equals(cell2.getStringCellValue())) {
                        continue;
                    }
                    sod.setMaterial_name(cell2.getStringCellValue().trim());
                } else {
                    contentResult.append("Row" + i + ": BookPart is empty!");
                    break;
                }
                XSSFCell cell3 = row.getCell(3);
                if (cell3 != null) {
                    if (cell3.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        sod.setOrder_QTY((int) cell3.getNumericCellValue());
                    } else {
                        contentResult.append("Row " + i + ":  Order QTY format error!");
                        break;
                    }
                } else {
                    contentResult.append("Row" + i + ": OrderQTY is empty!");
                    break;
                }
                XSSFCell cell4 = row.getCell(4);
                if (cell4 != null) {
                    try {
                        sod.setDelivery_dateStr(new SimpleDateFormat("yyyy-MM-dd").format(cell4.getDateCellValue()));
                    } catch (Exception e) {
                        contentResult.append("Row" + i + ": RequestDate format error!");
                        break;
                    }
                } else {
                    contentResult.append("Row" + i + ": RequestDate is empty!");
                    break;
                }
                p = new Product();
                if (sod.getMaterial_name() != null && "".equals(sod.getMaterial_name())) {
                    p.setMaterial_name(sod.getMaterial_name());
                } else {
                    p.setMaterial_id(sod.getMaterial_id());
                }

                p.setIsOrderItem("Y");
                p.setUseFor("SimpleOrder");
                pList = productService.getProductListNoPage(p);
                if (pList != null && pList.size() != 0) {
                    p = pList.get(0);
                    sod.setMaterial_id(p.getMaterial_id());
                    sod.setMaterial_name(p.getMaterial_name());
                    sod.setMaterial_typeId(p.getMaterial_type());
                    sod.setMaterial_groupId(p.getMaterial_groupId());
                    sod.setSale_unit(p.getBase_unit());
                    sod.setLimited_QTY(p.getLimited_qty());
                    sod.setPq(Integer.valueOf(p.getNumerator()));
                    sod.setLead_time(p.getLead_time());
                } else {
                    contentResult.append("Row" + i + ": material " + sod.getMaterial_id() + " " + sod.getMaterial_name()
                            + "do not exist in the ProductList!");
                    break;
                }
                sodList.add(sod);
            }

            if (!"".equals(contentResult.toString())) {
                return contentResult.toString();
            }

            file.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return "failed (Importing content format error)！";
        }
        String sodJson = JsonUtil.list2json(sodList);
        return "success!;" + sodJson;
    }

    /**
     * 
     * 2003
     * 
     * @param path
     * @return
     */
    private String findOrderExcelXls(String path, Product pr) {
        // 导入数据
        FileInputStream fileIn = null;
        Workbook rwb = null;
        List<SampleOrderDetail> sodList = new ArrayList<SampleOrderDetail>();
        StringBuilder contentResult = new StringBuilder();
        try {
            fileIn = new FileInputStream(path);
            rwb = Workbook.getWorkbook(fileIn);
            Sheet rs = rwb.getSheet(0);
            int column = 0;
            column = rs.getColumns();
            int actualRows = 0;
            /** 去除空行得到真实行数 **/
            actualRows = StockUtil.delEmptyRow(rs);
            if (actualRows == 0 && column == 0) {
                return "The Excel is empty！";
            } else {

                for (int i = 1; i < actualRows; i++) {
                    if ("".equals(rs.getCell(2, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        contentResult.append("Row" + i + ": BookPart is empty!");
                        break;
                    }
                    if ("".equals(rs.getCell(3, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        contentResult.append("Row" + i + ": OrderQTY is empty!");
                        break;
                    }
                    if ("".equals(rs.getCell(4, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        contentResult.append("Row" + i + ": RequestDate is empty!");
                        break;
                    }
                }

                if (!"".equals(contentResult.toString())) {
                    return contentResult.toString();
                }
                for (int i = 1; i < actualRows; i++) {
                    sod = new SampleOrderDetail();
                    sod.setPo_item(rs.getCell(0, i).getContents().trim());
                    sod.setMaterial_id(rs.getCell(1, i).getContents().trim());
                    sod.setMaterial_name(rs.getCell(2, i).getContents().trim());
                    if (rs.getCell(3, i).getType() == CellType.NUMBER) {
                        NumberCell numberCell = (NumberCell) rs.getCell(3, i);
                        int qty = (int) numberCell.getValue();
                        sod.setOrder_QTY(qty);
                    } else {
                        contentResult.append("Row " + i + ": Order QTY format error!");
                        break;
                    }

                    if (rs.getCell(4, i).getType() == CellType.DATE) {
                        DateCell dateCell = (DateCell) rs.getCell(4, i);
                        Date date = dateCell.getDate();
                        String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
                        sod.setDelivery_dateStr(dateStr);
                    } else {
                        contentResult.append("Row " + i + ": RequestDate format error!");
                        break;
                    }
                    p = new Product();
                    if (sod.getMaterial_name() != null && "".equals(sod.getMaterial_name())) {
                        p.setMaterial_name(sod.getMaterial_name());
                    } else {
                        p.setMaterial_id(sod.getMaterial_id());
                    }

                    p.setIsOrderItem("Y");
                    p.setUseFor("SimpleOrder");
                    pList = productService.getProductListNoPage(p);
                    if (pList != null && pList.size() != 0) {
                        p = pList.get(0);
                        sod.setMaterial_id(p.getMaterial_id());
                        sod.setMaterial_name(p.getMaterial_name());
                        sod.setMaterial_typeId(p.getMaterial_type());
                        sod.setMaterial_groupId(p.getMaterial_groupId());
                        sod.setSale_unit(p.getBase_unit());
                        sod.setLimited_QTY(p.getLimited_qty());
                        sod.setPq(Integer.valueOf(p.getNumerator()));
                        sod.setLead_time(p.getLead_time());
                    } else {
                        contentResult.append("Row" + i + ": material " + sod.getMaterial_id() + " "
                                + sod.getMaterial_name() + "do not exist in the ProductList!");
                        break;
                    }
                    sodList.add(sod);
                }

                if (!"".equals(contentResult.toString())) {
                    return contentResult.toString();
                }
            }
            String sodJson = JsonUtil.list2json(sodList);
            return "success!;" + sodJson;
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Excel Template error！";// 模板错误
        } catch (Exception e) {
            return "failed (Importing content format error)！";
        }
    }

    /**
     * 导入excel数据
     * 
     * @return
     */
    public String findOrderExcel() {
        String result;
        Product pr = new Product();

        if (ExcelUtil.getExcelStyle(path).intValue() == 1) {
            result = findOrderExcelXls(uploadFile, pr);
            if (result.indexOf(";") == -1) {
                this.setFailMessage(result);
            } else {
                this.setSuccessMessage(result.split(";")[1]);
            }
            return RESULT_MESSAGE;
        } else if (ExcelUtil.getExcelStyle(path).intValue() == 2) {
            result = findOrderExcelXlsx(uploadFile, pr);
            if (result.indexOf(";") == -1) {
                this.setFailMessage(result);
            } else {
                this.setSuccessMessage(result.split(";")[1]);
            }
            return RESULT_MESSAGE;
        } else {
            this.setFailMessage("failed (Importing content format error)！");// 倒入内容格式错误
            return RESULT_MESSAGE;
        }
    }

/////////////////////////////////////////////上传EXCEL更新///////////////////////////////////////////////////////////////////////////////////////////////////////////	
    /**
     * 上传Excel文件处理（ Portal按照附件，取A列(Order
     * Num）+B列（12NC），然后与Portal中的SAP单号进行更新L列（Expressage
     * Info）、M列（Ship-Date）、O列（Invoice no））；
     * 
     * @return
     */
    public String importPageHandler() {

        String newFileName = null;
        boolean saveAsFile = false;
        if (uploadFileName != null && uploadFileName.length() > 0) {
            // 正式机
            String savedirPath = "/opt/tomcat/webapps/webapp/file_sampleOrder";
            File savedir = new File(savedirPath);
            // 如果目录不存在，则新建
            if (!savedir.exists()) {
                savedir.mkdirs();
            }
            newFileName = String.valueOf(new Date().getTime()) + FileUtil.getFileExtention(uploadFileName);
            File targetFile = new File(savedirPath + "/" + newFileName);
            try {
                saveAsFile = FileUtil.saveAsFile(upload, targetFile);
            } catch (Exception e) {
                e.printStackTrace();
                this.setFailMessage(e.getMessage());
                return RESULT_MESSAGE;
            }

            if (!saveAsFile) {
                this.setFailMessage("File upload failed");
                return RESULT_MESSAGE;
            }
        }

        String result;
        if (ExcelUtil.getExcelStyle(path).intValue() == 1) {
            result = importPageXls(path.toString());
            if ("Success".equals(result)) {
                this.setSuccessMessage("Success!");
            } else {
                this.setFailMessage(result);
            }
            return RESULT_MESSAGE;
        } else if (ExcelUtil.getExcelStyle(path).intValue() == 2) {
            result = importPageXlsx(upload.toString());
            if ("Success".equals(result)) {
                this.setSuccessMessage("Success!");
            } else {
                this.setFailMessage(result);
            }
            return RESULT_MESSAGE;
        } else {
            this.setFailMessage("failed (Data Format error)！");
            return RESULT_MESSAGE;
        }
    }

    private String importPageXlsx(String path) {
        StringBuilder contentResult = new StringBuilder();
        try {
            FileInputStream file = new FileInputStream(path);
            XSSFWorkbook xwb = new XSSFWorkbook(file);
            XSSFSheet sheet = xwb.getSheetAt(0);
            // 定义 row、cell
            XSSFRow row;
            // 循环输出表格中的内容
            if (sheet.getPhysicalNumberOfRows() <= 1) {
                return "The Excel is empty！";
            }
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                if (row == null) {// 空行跳过
                    continue;
                }
                sod = new SampleOrderDetail();
                XSSFCell cell0 = row.getCell(0);
                if (cell0 != null) {
                    if (cell0 != null) {
                        if (cell0.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                            // 数字格式无法解析
                            cell0.setCellType(Cell.CELL_TYPE_STRING);
                        }
                        if ("".equals(cell0.getStringCellValue().trim())) {
                            contentResult.append("Row " + i + ": OrderId is empty!");
                            continue;
                        } else {
                            sod.setSap_order_id(cell0.getStringCellValue().trim());
                        }
                    }
                } else {
                    contentResult.append("Row" + i + ": OrderId is empty!");
                    continue;
                }
                XSSFCell cell1 = row.getCell(1);
                if (cell1 != null) {
                    if (cell1.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        // 数字格式无法解析
                        cell1.setCellType(Cell.CELL_TYPE_STRING);
                    }
                    if ("".equals(cell1.getStringCellValue().trim())) {
                        contentResult.append("Row" + i + ": 12NC is empty!");
                        continue;
                    } else {
                        sod.setMaterial_id(cell1.getStringCellValue().trim());
                    }
                } else {
                    contentResult.append("Row" + i + ": 12NC is empty!");
                    continue;
                }
                XSSFCell cell11 = row.getCell(11);
                if (cell11 != null) {
                    if (cell11.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        // 数字格式无法解析
                        cell11.setCellType(Cell.CELL_TYPE_STRING);
                    }
                    sod.setExpressage_info(cell11.getStringCellValue().trim());
                }

                XSSFCell cell12 = row.getCell(12);
                if (cell12 != null) {
                    try {
                        Date d = cell12.getDateCellValue();
                        if (d != null) {
                            sod.setShip_date(new SimpleDateFormat("yyyy-MM-dd").format(d));
                        }
                    } catch (Exception e) {
                        contentResult.append("Row" + i + ": ShipDate format error!");
                        e.printStackTrace();
                        continue;
                    }
                }

                XSSFCell cell13 = row.getCell(13);
                if (cell13 != null) {
                    if (cell13.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        // 数字格式无法解析
                        cell13.setCellType(Cell.CELL_TYPE_STRING);
                    }
                    sod.setWeen_remark(cell13.getStringCellValue().trim());
                }
                XSSFCell cell14 = row.getCell(14);
                if (cell14 != null) {
                    if (cell14.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        // 数字格式无法解析
                        cell14.setCellType(Cell.CELL_TYPE_STRING);
                    }
                    sod.setInvoice(cell14.getStringCellValue().trim());
                }
                
                
                XssUtils.getXssSaftBean(sod.getClass(), sod);
                
                int n = sampleOrderService.importUpdateCount(sod);
                if (n >= 1) {
                    int a = sampleOrderService.importUpdate(sod);
                    if (a <= 0) {
                        contentResult.append("Row" + i + ": Data update error!");
                        break;
                    }
                } else {
                    continue;
                }
            }

            if (!"".equals(contentResult.toString())) {
                return contentResult.toString();
            }

            file.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return "failed (Importing content format error)！";
        }
        return "Success";
    }

    private String importPageXls(String path) {
        // 导入数据
        FileInputStream fileIn = null;
        Workbook rwb = null;
        StringBuilder contentResult = new StringBuilder();
        try {
            fileIn = new FileInputStream(path);
            rwb = Workbook.getWorkbook(fileIn);
            Sheet rs = rwb.getSheet(0);
            int column = 0;
            column = rs.getColumns();
            int actualRows = 0;
            /** 去除空行得到真实行数 **/
            actualRows = StockUtil.delEmptyRow(rs);
            if (actualRows == 0 && column == 0) {
                return "The Excel is empty！";
            } else {

                for (int i = 1; i < actualRows; i++) {
                    if ("".equals(rs.getCell(0, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        contentResult.append("Row" + i + ": OrderId is empty!");
                        break;
                    }
                    if ("".equals(rs.getCell(1, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        contentResult.append("Row" + i + ": 12NC is empty!");
                        break;
                    }

                    if ("".equals(rs.getCell(12, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        contentResult.append("Row" + i + ": ShipDate is empty!");
                        break;
                    }
                    if ("".equals(rs.getCell(14, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        contentResult.append("Row" + i + ": Invoice is empty!");
                        break;
                    }
                }

                if (!"".equals(contentResult.toString())) {
                    return contentResult.toString();
                }
                for (int i = 1; i < actualRows; i++) {
                    sod = new SampleOrderDetail();
                    sod.setSap_order_id(rs.getCell(0, i).getContents().trim());
                    sod.setMaterial_id(rs.getCell(1, i).getContents().trim());
                    sod.setExpressage_info(rs.getCell(11, i).getContents().trim());
                    if (rs.getCell(12, i).getType() == CellType.DATE) {
                        DateCell dateCell = (DateCell) rs.getCell(12, i);
                        Date date = dateCell.getDate();
                        String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
                        sod.setShip_date(dateStr);
                    } else {
                        contentResult.append("Row " + i + ": ShipDate format error!");
                        break;
                    }
                    sod.setWeen_remark(rs.getCell(13, i).getContents().trim());
                    sod.setInvoice(rs.getCell(14, i).getContents().trim());
                    
                    
                    XssUtils.getXssSaftBean(sod.getClass(), sod);
                    
                    int n = sampleOrderService.importUpdateCount(sod);
                    if (n >= 1) {
                        int a = sampleOrderService.importUpdate(sod);
                        if (a <= 0) {
                            contentResult.append("Row" + i + ": Data update error!");
                            break;
                        }
                    } else {
                        continue;
                    }
                }

                if (!"".equals(contentResult.toString())) {
                    return contentResult.toString();
                }
            }
            return "Success";
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Excel Template error！";// 模板错误
        } catch (Exception e) {
            return "failed (Importing content format error)！";
        }
    }

    /**
     * 查询订单很明显信息 xcfeng
     * 
     * @return
     */
    @PermissionSearch
    @JsonResult(field = "soList", include = { "id", "order_id", "type_id", "branch_id", "currency_code", "ship_to",
            "payer_to", "billing_to", "saler", "sale_company", "end_customer", "city", "project_name", "contact_info",
            "state", "remark", "company",
            "ship_to_region", "applicant_name", "applicant_company", "account_manager", "comments",
            "sale_to", "country", "street", "create_time", "create_timeString",
            "create_userId", "sync_state", "sap_order_id", "zip", "shipToAddress", "latest_time", "latest_userId",
            "org_code", "contact_name", "contact_tel", "application_desc", "detail_id", "row_no", "material_id",
            "material_name", "material_typeId", "material_groupId", "sale_unit", "limited_QTY", "order_QTY",
            "delivery_dateStr", "confirm_dateStr", "lead_time", "delivery_date", "confirm_date", "remark", "pq",
            "po_item", "pbMpp", "stock_status", "expressage_info", "ship_date", "invoice", "detail_remark",
            "ween_remark" }, total = "total")
    public String getSampleOrderAndDetailList() {

        so = new SampleOrder();
        so.setStart(getStart());
        so.setEnd(getEnd());
        so.setSort("aa.create_time");
        so.setDir("desc");
        so.setOrder_id(order_id);
        so.setProject_name(project_name);
        so.setSap_order_id(sap_order_id);
        so.setStart_dateStr(start_dateStr);
        so.setEnd_dateStr(end_dateStr);
        if (StringUtils.isNotEmpty(company) && StringUtils.isNotEmpty(company.trim())) {
            try {
                company = java.net.URLDecoder.decode(company, "UTF-8");
                company = company.toUpperCase();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        so.setCompany(company);
        if (StringUtils.isNotEmpty(contact_name) && StringUtils.isNotEmpty(contact_name.trim())) {
            try {
                contact_name = java.net.URLDecoder.decode(contact_name, "UTF-8");
                contact_name = contact_name.toUpperCase();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        so.setContact_name(contact_name);
        String loginId = this.getUser().getLoginId();
        String orgString = this.getUser().getOrgId();
        if ("1".equals(type)) {
            // admin 可以看到所有的Sample Order
            if(!"admin".equals(loginId)) {
                if (orgString == null || "".equals(orgString)) {
                    // 外部人员只能看到自己创建的Sample Order
                    so.setCreate_userId(this.getUser().getUserId());
                    so.setRoleType("Disti");
                }
                else {
                    so.setLoginId(loginId);
                    String userId = this.getUser().getUserId();
                    List<Role> roles = sampleOrderService.getRoleForSampleOrder(userId);
                    
                    boolean isYPDDManager = false;
                    boolean isRegionSaler = false;
                    boolean isYPDD = false;
                    String orgName = "";
                    
                    for (Role soRole : roles) {
                        if ("YPDD_MANAGER".equals(soRole.getRoleId())) {
                            isYPDDManager = true;
                            break;
                        }
                        else if ("HK10_H_Sale_Mgmt".equals(soRole.getRoleId())) {
                            isRegionSaler = true;
                            orgName = soRole.getOrgName();
                        }
                        else if ("YPDD".equals(soRole.getRoleId())) {
                            isYPDD = true;
                        }
                    }
                    so.setRoleType("");
                    if (isYPDDManager) {
                        // YPDD_MANAMER可以查看所有Sample Order
                    }
                    else if (isRegionSaler && isYPDD) {
                        // 有HK10_H_Sale_Mgmt权限的用户可以看到对应区域的Sample Order
                        so.setRoleType("RY");
                        so.setShipToRegions(getOrgNames(orgName));
                        so.setCreate_userId(this.getUser().getUserId());
                    }
                    else if (isRegionSaler) {
                        // 有HK10_H_Sale_Mgmt权限的用户可以看到对应区域的Sample Order
                        so.setRoleType("R");
                        so.setShipToRegions(getOrgNames(orgName));
                    }
                    else if (isYPDD) {
                        // YPDD只能看到自己创建的Sample Order
                        so.setRoleType("Y");
                        so.setCreate_userId(this.getUser().getUserId());
                    }
                    else {
                        total = 0;
                        soanddetailList = new ArrayList<SampleOrderAndDetail>();
                        return JSON;
                    }
                }
            }
        }
        else {
            // admin 可以看到所有的Sample Order
            if(!"admin".equals(loginId)) {
                so.setManager_id(this.getUser().getUserId());
            }
        }
        total = sampleOrderService.getSampleOrderAndDetailListCount(so);
        if (total > 0) {
            soanddetailList = (List<SampleOrderAndDetail>) sampleOrderService.getSampleOrderAndDetailList(so);
        }
        return JSON;
    }

    // 新版导出excel xcfeng 导出Excel
    public void downloadSampleOrderDetail() throws Exception {
        try {
            this.setPage(1);
            this.setRows(1000000);
            // 查询数据
            getSampleOrderAndDetailList();

            Date aDate = new Date();
            SimpleDateFormat aDateFormat = new SimpleDateFormat("yyyyMMdd");
            String dateString = aDateFormat.format(aDate);
            String fileName = "Simple_Order_Detail" + dateString;

            CommonUtil.setExcelResponseInfo(getServletRequest(), getServletResponse(), fileName);
            // excel表头
            String[] titles = { "Order Status", "Order Num", "Create Date", "Ship-To Company", "Ship-To Consignee", "Ship-To Phone No",
                    "Ship-To Country", "Ship-To City", "Ship-To Zip", "Ship-To Street", "End Customer",
                    "Application Desc", "Customer Project", "Shipment Remark", "Status",
                    "Ship-To_Region", "Applicant Name", "Applicant Company", "Account Manager", 
                    "Item ID", "BookPart", "12NC",
                    "Sale Unit", "Limited Qty", "Order QTY", "Expressage Info ", "Ship Date", "Invoice", "Cust Remark",
                    "Ween Remark" };

            // 表头对应list中Map对应属性名称
            String[] keys = { "orderState", "sap_order_id", "create_timeString", "company", "contact_name", "contact_tel", "country",
                    "city", "zip", "street", "end_customer", "application_desc", "project_name", "remark",
                    "detail_state",
                    "ship_to_region", "applicant_name", "applicant_company", "account_manager", 
                    "row_no", "material_name", "material_id", "sale_unit", "limited_QTY", "order_QTY",
                    "expressage_info", "ship_date", "invoice", "detail_remark", "ween_remark" };

            List<Map<String, String>> li = new ArrayList<Map<String, String>>();
            for (SampleOrderAndDetail p : soanddetailList) {
                li.add(CommonUtil.transBean2Map(p));
            }
            CommonUtil.exportExcel(li, titles, keys, getServletResponse().getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isDisti() {

        boolean isDisti = false;
        
        String orgString = this.getUser().getOrgId();
        if (orgString == null || "".equals(orgString)) {
            isDisti = true;
        }

        /*
        Disti_branch db = new Disti_branch();

        db.setDisti_name(this.getUser().getUserName());

        List<Disti_branch> distiList = sampleOrderService.getDistiBranchList(db);

        if (distiList != null) {
            for (Disti_branch distiBranch : distiList) {
                String pay_code = distiBranch.getPayer_to();
                String ssa = pay_code.substring(0, 1);
                if ("7".equals(ssa)) {
                    isDisti = true;
                    break;
                }
            }
        }
*/
        return isDisti;
    }

    @PermissionSearch
    @JsonResult(field = "orgList", include = {"orgId", "sapOrgId", "orgName" })
    public String getOrgLists() {
        Borg borg = new Borg();
        borg.setSort("g.org_id");
        borg.setDir("asc");
        borg.setOrgLevel(Long.valueOf(2));
        
        orgList = sampleOrderService.getOrgList(borg);

        return JSON;
    }
    
    @PermissionSearch
    @JsonResult(field = "accountManageList", include = {"userId", "userName"})
    public String getAccountManagerList() {
        
        AccountManager accountManager = new AccountManager();
        
        if (StringUtils.isEmpty(shipToRegion)) {
            accountManageList = new ArrayList<AccountManager>();
            return JSON;
        }
        
        accountManager.setShipToRegion(getOrgNames(shipToRegion));

        accountManageList = sampleOrderService.getAccountManageList(accountManager);
        
        for (AccountManager accManager : accountManageList) {
            if (!StringUtils.isEmpty(accManager.getUserName())) {
                String[] strNames = accManager.getUserName().split("@");
                accManager.setUserName(strNames[0]);
            }
        }
        
        return JSON;
    }

    private String getOrgNames(String shipToRegion) {
        
        String shipToRegions = StringUtils.EMPTY;
        
        if ("GC".equals(shipToRegion)) {
            shipToRegions = "('GC', 'WeEn HK')";
        }
        else if ("JP".equals(shipToRegion)) {
            shipToRegions = "('JP', 'AP', 'WeEn HK')";
        } 
        else if ("KR".equals(shipToRegion)) {
            shipToRegions = "('KR', 'AP', 'WeEn HK')";
        }
        else if ("SAP".equals(shipToRegion)) {
            shipToRegions = "('SAP', 'AP', 'WeEn HK')";
        }
        else if ("AM".equals(shipToRegion)) {
            shipToRegions = "('AM', 'West', 'WeEn HK')";
        } 
        else if ("EU".equals(shipToRegion)) {
            shipToRegions = "('EU', 'West', 'WeEn HK')";
        }
        else if ("AP".equals(shipToRegion)) {
            shipToRegions = "('JP', 'KR', 'SAP', 'AP')";
        }
        else if ("West".equals(shipToRegion)) {
            shipToRegions = "('AM', 'EU', 'West')";
        }
        else if ("WeEn HK".equals(shipToRegion)) {
            shipToRegions = "('GC', 'JP', 'KR', 'SAP', 'AP', 'AM', 'EU', 'West', 'WeEn HK')";
        }
        else {
            shipToRegions = "('NO ROLES')";
        }
        
        return shipToRegions;
    }
    
    public String sendMailByAddree(String emailAddress, String content, String type) {
        Message m = new Message();
        m.setContent(content);
        m.setType(type);
        m.setSendNumber(emailAddress);
        messageService.saveMessage(m);
        return "saveSuccess";
    }
    
    /**
     * Approve
     * 
     * @return
     * @throws UnsupportedEncodingException
     */
    @SuppressWarnings("unchecked")
    public String approveSampleOrder() throws UnsupportedEncodingException {
        this.setSuccessMessage("");
        this.setFailMessage("");
        SampleOrder simpleOrder = new SampleOrder(); 
        simpleOrder.setId(Long.valueOf(id));
        simpleOrder.setState(0);
        orderDetailJson = java.net.URLDecoder.decode(this.orderDetailJson, "utf-8");
        sodList = JsonUtil.getDTOList(orderDetailJson, SampleOrderDetail.class);

        BooleanResult bool = sampleOrderService.auditSampleOrder(simpleOrder, sodList);
        if (bool.getResult()) {
            this.setSuccessMessage("Success!");
            // 同步至sap
            try {
                // 获取表头
                OrderToSap order = new OrderToSap();
                order.setId(Long.valueOf(id));
                List<OrderToSap> orderList = orderToSapService.getOrderTotal(order);
                order = orderList.get(0);
                // 获取明细
                List<com.jingtong.platform.sap.pojo.OrderDetail> orderDetail = orderToSapService.getOrderDetail(order);

                SampleOrder sorder = new SampleOrder();
                sorder.setId(order.getId());
                // 同步
                orderToSapService.orderToSap(order, orderDetail, sorder);
                
                AllUsers user = allUserService.getAllUsersByUserId(so.getCreate_userId());

                // Send Mail
                String content = "<br>&nbsp;&nbsp;" + this.getUser().getUserName() + " approve a Sample Order. Please check in Portal.";
                if (user.getEmail() != null) {
                    String contents = "Hi, " + user.getUserName() + content + "<br>";
                    sendMailByAddree(user.getEmail(), contents, "Sample Order Approve");
                }
            } catch (Exception e) {
                e.printStackTrace();
                this.setFailMessage("SAP Failed !");;
            }
        }
        else {
            this.setFailMessage("Failed !");
        }
        return RESULT_MESSAGE;
    }
    
    /**
     * Reject
     * 
     * @return
     * @throws UnsupportedEncodingException
     */
    @SuppressWarnings("unchecked")
    public String rejectSampleOrder() throws UnsupportedEncodingException {
        this.setSuccessMessage("");
        this.setFailMessage("");
        SampleOrder simpleOrder = new SampleOrder(); 
        simpleOrder.setId(Long.parseLong(id));
        simpleOrder.setState(2);
        orderDetailJson = java.net.URLDecoder.decode(this.orderDetailJson, "utf-8");
        sodList = JsonUtil.getDTOList(orderDetailJson, SampleOrderDetail.class);

        BooleanResult bool = sampleOrderService.auditSampleOrder(simpleOrder, sodList);
        if (bool.getResult()) {
            AllUsers user = allUserService.getAllUsersByUserId(so.getCreate_userId());

            // Send Mail
            String content = "<br>&nbsp;&nbsp;" + this.getUser().getUserName() + " reject a Sample Order. Please check in Portal.";
            if (user.getEmail() != null) {
                String contents = "Hi, " + user.getUserName() + content + "<br>";
                sendMailByAddree(user.getEmail(), contents, "Sample Order Reject");
            }
            this.setSuccessMessage("Success!");
        } else {
            this.setFailMessage("Failed !");
        }
        return RESULT_MESSAGE;
    }
    
    public String toAuditSampleOrder() {
        so = new SampleOrder();
        so.setId(Long.valueOf(id));
        so = sampleOrderService.getSampleOrderById(so);
        String loginId = this.getUser().getUserId();
        forWho = StringUtils.EMPTY;
        if (StringUtils.equals(loginId, so.getManager_id())) {
            forWho = "Manager";
        }
        if (so.getState() == 0) {
            orderStatus = "Approved";
        }
        else if (so.getState() == 1) {
            orderStatus = "Pending";
        } 
        else if (so.getState() == 2) {
            orderStatus = "Rejected";
        } 
        else {
            orderStatus = StringUtils.EMPTY;
        }
        return "toAuditSampleOrder";
    }
    
    @PermissionSearch
    @JsonResult(field = "soList", include = { "id", "order_id", "type_id", "branch_id", "currency_code", "ship_to",
            "payer_to", "billing_to", "saler", "sale_company", "end_customer", "city", "project_name", "contact_info",
            "state", "remark", "company", "sale_to", "country", "street", "create_time", "create_userId", "sync_state",
            "sap_order_id", "zip", "shipToAddress", "latest_time", "latest_userId", "org_code", "contact_name",
            "contact_tel", "ship_to_region", "applicant_name", "applicant_company", "account_manager", "comments", "manager_id"}, total = "total")
    public String getPendingSampleOrderList() {

        so = new SampleOrder();
        so.setStart(getStart());
        so.setEnd(getEnd());
        so.setSort("aa.create_time");
        so.setDir("desc");
        so.setOrder_id(order_id);
        so.setProject_name(project_name);
        so.setSap_order_id(sap_order_id);
        so.setStart_dateStr(start_dateStr);
        so.setEnd_dateStr(end_dateStr);
        
        if (StringUtils.isEmpty(states)) {
            so.setStates("('1')");
        }
        else {
            so.setStates(states);
        }
        if (StringUtils.isNotEmpty(company) && StringUtils.isNotEmpty(company.trim())) {
            try {
                company = java.net.URLDecoder.decode(company, "UTF-8");
                company = company.toUpperCase();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        so.setCompany(company);
        if (StringUtils.isNotEmpty(contact_name) && StringUtils.isNotEmpty(contact_name.trim())) {
            try {
                contact_name = java.net.URLDecoder.decode(contact_name, "UTF-8");
                contact_name = contact_name.toUpperCase();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        so.setContact_name(contact_name);
        String loginId = this.getUser().getLoginId();
        
        // admin 可以看到所有的Sample Order
        if(!"admin".equals(loginId)) {
           so.setManager_id(this.getUser().getUserId());
        }
        total = sampleOrderService.getSampleOrderListCount(so);
        if (total > 0) {
            soList = sampleOrderService.getSampleOrderList(so);
        }
        return JSON;
    }

}
