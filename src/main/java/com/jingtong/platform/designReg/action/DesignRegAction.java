package com.jingtong.platform.designReg.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.allUser.service.IAllUserService;
import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.common.Escape;
import com.jingtong.platform.customer.pojo.Customer;
import com.jingtong.platform.customer.pojo.CustomerUser;
import com.jingtong.platform.customer.pojo.Disti_branch;
import com.jingtong.platform.customer.service.ICustomerService;
import com.jingtong.platform.designReg.pojo.DesignReg;
import com.jingtong.platform.designReg.pojo.DesignRegDetail;
import com.jingtong.platform.designReg.pojo.DesignRegDetailLog;
import com.jingtong.platform.designReg.pojo.Dict;
import com.jingtong.platform.designReg.service.IDesignRegService;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.framework.util.DateUtil;
import com.jingtong.platform.framework.util.JsonUtil;
import com.jingtong.platform.framework.util.XssUtils;
import com.jingtong.platform.message.pojo.Message;
import com.jingtong.platform.message.service.IMessageService;
import com.jingtong.platform.product.pojo.Product;
import com.jingtong.platform.quote.pojo.Quote;
import com.jingtong.platform.quote.service.IQuoteService;
import com.jingtong.platform.role.pojo.Role;
import com.jingtong.platform.role.service.IRoleService;

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

public class DesignRegAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IDesignRegService designRegService;
	private IMessageService messageService;
	private IQuoteService quoteService;
	private DesignReg dr;
	private List<DesignReg> drList;
	private DesignRegDetail drd;
	private List<DesignRegDetail> drdList;
	private List<DesignRegDetailLog> drLogList;
	private String designRegDetailJson;
	private String id;
	private String delDesignRegDetail;
	private String drNum;
	private String project_name;
	private String endCus_groupId;
	private String start_date;
	private String end_date;
	private String endCus_id;
	private String material_id;
	private String material_name;
	private String equip_type;
	private String states;
	private String state;
	private int total;
	private String ids;
	private String quoteDetail;
	private String cus_groupId;// Dr
	private String cusGroup_id; // Quote
	private String customer_id;
	private String customer_name;
	private String ecGroup_id;
	private String ecGroup_name;
	private String latest_expire;
	private String endCustomer_id;
	private CustomerUser cusUser;
	private Customer customer;
	private List<CustomerUser> cusUserList;
	private ICustomerService customerService;
	private String main_id;
	private Quote q;
	private String endCustomer_name;
	private String endCustomer_name_city;
	private String disti_branch;
	private Disti_branch db;
	private List<Disti_branch> dbList;
	private List<Role> roleList;
	private IRoleService roleService;
	private String loginRole;
	private String myself;
	private IAllUserService allUserService;
	private String smtpServer;// =env.getProperty("allUser.smtpServer");
	private String from;// =env.getProperty("allUser.from");
	private String displayName;// =env.getProperty("allUser.displayName");
	private String emailaddress;// =env.getProperty("allUser.emailaddress");
	private String emailpassword;// =env.getProperty("allUser.emailpassword");
	private long dictTypeId;
	private long itemId;
	private String forWho;
	private List<Dict> cmsTbDictList;
	private String create_userName;
	private String start_dateStr;
	private String end_dateStr;
	private String remarks;
	private Long dr_detail_id;
	private String isTurnWin;
	private String drType;
	private String dr_types;
	private String isUpdate;
	private String remark;
	private String weencomments;

	private String weencomment;

	private String rowIndex;

	private String saler_design_statusStr;

	public String getSaler_design_statusStr() {
		return saler_design_statusStr;
	}

	public void setSaler_design_statusStr(String saler_design_statusStr) {
		this.saler_design_statusStr = saler_design_statusStr;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setWeencomments(String _weencomments) {
		this.weencomments = _weencomments;
	}

	public String getWeencomments() {
		return weencomments;
	}

	public void setWeencomment(String _weencomment) {
		this.weencomment = _weencomment;
	}

	public String getWeencomment() {
		return weencomment;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public long getDictTypeId() {
		return dictTypeId;
	}

	public void setDictTypeId(long dictTypeId) {
		this.dictTypeId = dictTypeId;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String toSearchDesignReg() {
		dr = new DesignReg();

		if (isDisti()) {
			dr.setCus_groupId(this.getUser().getUserName());
			forWho = "Disti";
		}
		return "toSearchDesignReg";
	}

	public String importExcel() {
		return "importExcel";
	}

	public String toViewDesignReg() {
		dr = new DesignReg();
		dr.setId(Long.valueOf(id));
		dr = designRegService.getDesignRegById(dr);
		if (dr.getEc_city() != null) {
			endCustomer_name_city = dr.getEndCus_name() + "-" + dr.getEc_city();
		} else {
			endCustomer_name_city = dr.getEndCus_name();
		}
		String orgString = this.getUser().getOrgId();
		if (isDisti()) {
			forWho = "Disti";
		} else if (orgString == null || "".equals(orgString)) {
			forWho = "internalUser";
		} else {
			forWho = "";
		}
		return "toViewDesignReg";
	}

	public String toViewApproveDR() {
		dr = new DesignReg();
		
		dr.setId(Long.valueOf(id));
		dr = designRegService.getDesignRegById(dr);
		String orgString = this.getUser().getOrgId();
		if (dr.getEc_city() != null) {
			dr.setEndCus_name(dr.getEndCus_name() + "-" + dr.getEc_city());
		}
		if (isDisti()) {
			forWho = "Disti";
		} else if (orgString == null || "".equals(orgString)) {
			forWho = "internalUser";
		} else {
			forWho = "";
		}
		return "toViewApproveDR";
	}
	
	public String toCreateDesignReg() {
		dr = new DesignReg();
		String orgString = this.getUser().getOrgId();
		if (isDisti()) {
			forWho = "Disti";
			dr.setCus_groupId(this.getUser().getUserName());
		} else if (orgString == null || "".equals(orgString)) {
			forWho = "internalUser";
			dr.setCus_groupId(this.getUser().getUserName());
		} else {
			forWho = "";
			dr.setCus_groupId(this.getUser().getUserName());
		}
		dr.setState(0);
		Date date = new Date();
		dr.setStart_dateStr(DateUtil.getNowDateStr());
		dr.setEnd_dateStr(DateUtil.getDateTime(DateUtil.addMonths(date, 3), "yyyy-MM-dd"));
		return "toCreateDesignReg";
	}

	public String toCopyCreate() {
		String orgString = this.getUser().getOrgId();

		dr = new DesignReg();
		dr.setId(Long.valueOf(id));
		dr = designRegService.getDesignRegById(dr);

		if (isDisti()) {
			forWho = "Disti";
			dr.setCus_groupId(this.getUser().getUserName());
		} else if (orgString == null || "".equals(orgString)) {
			forWho = "internalUser";
		} else {
			forWho = "";
		}

		Date date = new Date();
		dr.setCreate_time(new Date());
		String userString = this.getUser().getLoginId();
		int n = userString.indexOf("@");
		if (n > -1) {
			dr.setCreate_userId(userString.substring(0, userString.indexOf("@")));
		} else {
			dr.setCreate_userId(userString);
		}
		dr.setStart_dateStr(DateUtil.getNowDateStr());
		dr.setEnd_dateStr(DateUtil.getDateTime(DateUtil.addMonths(date, 3), "yyyy-MM-dd"));
		dr.setMp_scheduleStr("");
		dr.setUsage_amount("");

		return "toCopyCreate";
	}

	public String toUpdateDesignReg() {
		dr = new DesignReg();
		dr.setId(Long.valueOf(id));
		dr = designRegService.getDesignRegById(dr);

		String orgString = this.getUser().getOrgId();
		if (orgString == null || "".equals(orgString)) {
			dr.setCus_groupId(this.getUser().getUserName());
		}

		String pay_code = dr.getCustomer_id();
		String ssa = pay_code.substring(0, 1);
		if (!"7".equals(ssa)) {
			forWho = "";
		} else {
			forWho = "Disti";
		}
		if (this.getUser().getRoleIds() != null) {
			String[] ss = this.getUser().getRoleIds().split(",");
			for (String role : ss) {
				if ("VAR".equals(role)) {
					forWho = "";
					break;
				}
			}
		}
		dr.setForWho(forWho);
		// 2019/04/03 START
		DesignRegDetail drItem = new DesignRegDetail();
		drItem.setDrNum(dr.getDrNum());
		drItem.setMain_id(Long.valueOf(dr.getId()));
		List<DesignRegDetail> drItemList = designRegService.getDesignRegDetailList(drItem);
		boolean isDW = true;

		for (DesignRegDetail item : drItemList) {
			if (item.getProject_state() != 2) {
				isDW = false;
				break;
			}
		}
		drItemList.clear();
		if (isDW) {
			dr.setIsAllDW(1);
		} else {
			dr.setIsAllDW(0);
		}
		// 2019/04/03 END
		return "toUpdateDesignReg";
	}

	public String toCheckDesignReg() {
		return "toCheckDesignReg";
	}

	public String toDesignwinlist() {
		String orgString = this.getUser().getOrgId();
		dr = new DesignReg();
		if (isDisti()) {
			forWho = "Disti";
			dr.setCus_groupId(this.getUser().getUserName());
		} else if (orgString == null || "".equals(orgString)) {
			forWho = "internalUser";
			dr.setCus_groupId(this.getUser().getUserName());
		} else {
			forWho = "";
		}
		return "toDesignwinlist";
	}

	public String toOutport() {
		dr = new DesignReg();
		String orgString = this.getUser().getOrgId();
		if (isDisti()) {
			dr.setCus_groupId(this.getUser().getUserName());
			forWho = "Disti";
		} else if (orgString == null || "".equals(orgString)) {
			dr.setCus_groupId(this.getUser().getUserName());
			forWho = "internalUser";
		} else {
			forWho = "";
		}
		return "toOutport";
	}

	public String toCheck() {
		drd = new DesignRegDetail();
		drd.setId(Long.valueOf(id));
		drd.setDrNum(drNum);
		drd.setEndCus_id(endCus_id);
		drd.setEndCus_groupId(endCus_groupId);
		drd.setStart_dateStr(start_date);
		drd.setEnd_dateStr(end_date);
		drd.setMaterial_id(material_id);
		drd.setCustomer_id(customer_id);
		designRegService.setCheck(drd);
		return "toCheck";
	}

	public String designwinSalerApprove() {
		dr = new DesignReg();
		String orgString = this.getUser().getOrgId();
		if (isDisti()) {
			dr.setCus_groupId(this.getUser().getUserName());
			forWho = "Disti";
		} else if (orgString == null || "".equals(orgString)) {
			dr.setCus_groupId(this.getUser().getUserName());
			forWho = "internalUser";
		} else {
			forWho = "";
		}
		return "designwinSalerApprove";
	}

	public String designwinLeaderApprove() {
		dr = new DesignReg();
		String orgString = this.getUser().getOrgId();
		if (isDisti()) {
			dr.setCus_groupId(this.getUser().getUserName());
			forWho = "Disti";
		} else if (orgString == null || "".equals(orgString)) {
			dr.setCus_groupId(this.getUser().getUserName());
			forWho = "internalUser";
		} else {
			forWho = "";
		}
		return "designwinLeaderApprove";
	}

	public String designwinBusinessApprove() {
		dr = new DesignReg();
		String orgString = this.getUser().getOrgId();
		if (isDisti()) {
			dr.setCus_groupId(this.getUser().getUserName());
			forWho = "Disti";
		} else if (orgString == null || "".equals(orgString)) {
			dr.setCus_groupId(this.getUser().getUserName());
			forWho = "internalUser";
		} else {
			forWho = "";
		}
		return "designwinBusinessApprove";
	}

	public String designwinFinanceApprove() {
		dr = new DesignReg();
		String orgString = this.getUser().getOrgId();
		if (isDisti()) {
			dr.setCus_groupId(this.getUser().getUserName());
			forWho = "Disti";
		} else if (orgString == null || "".equals(orgString)) {
			dr.setCus_groupId(this.getUser().getUserName());
			forWho = "internalUser";
		} else {
			forWho = "";
		}
		return "designwinFinanceApprove";
	}

	public String toQuote() {
		q = new Quote();
		q.setType_id("Quote");
		q.setCusGroup_id(cusGroup_id);
		q.setEcGroup_id(ecGroup_id);
		q.setCustomer_id(customer_id);
		q.setEndCustomer_id(endCustomer_id);
		q.setEndCustomer_name(endCustomer_name);
		q.setStart_dateStr(start_date);
		q.setLatest_expireStr(latest_expire);
		q.setProject_name(project_name);
		q.setDisti_branch(disti_branch);
		q.setEcGroup_name(ecGroup_name);
		q.setState(0);
		db = new Disti_branch();
		db.setDisti_branch(disti_branch);
		db.setStart(0);
		db.setEnd(20);
		dbList = customerService.getDistiBranchList(db);
		q.setCurrency_code(dbList.get(0).getCurrency());
		q.setSalesOrg(dbList.get(0).getPricing_region());
		q.setCustomer_id(dbList.get(0).getPayer_to());
		q.setCreate_timeStr(DateUtil.getNowDateStr());
		q.setCreate_userId(this.getUser().getUserName());
		return "toQuote";
	}

	@PermissionSearch
	@JsonResult(field = "drList", include = { "id", "drNum", "cus_groupId", "customer_id", "endCus_groupId",
			"endCus_id", "ec_contact", "customer_name", "endCus_groupName", "endCus_name", "disti_branch", "start_date",
			"start_dateStr", "mp_schedule", "mp_scheduleStr", "end_date", "end_dateStr", "state", "remark",
			"project_name", "project_state", "usage_amount", "tel", "equip_type", "create_time", "create_userId",
			"primaryProState", "latest_time", "latest_userId", "latest_deptId", "create_userName",
			"sale_office" }, total = "total")
	public String getDesignRegList() {

		dr = new DesignReg();
		dr.setStart(getStart());
		dr.setEnd(getEnd());
		dr.setSort("aa.id");
		dr.setDir("desc");
		if (drNum != null && (!("".equals(drNum)))) {
			dr.setDrNum(drNum.toUpperCase());
		}
		if (StringUtils.isNotEmpty(cus_groupId) && StringUtils.isNotEmpty(cus_groupId.trim())) {
			try {
				cus_groupId = java.net.URLDecoder.decode(cus_groupId, "UTF-8");
				dr.setCus_groupId(cus_groupId.toUpperCase());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.isNotEmpty(disti_branch) && StringUtils.isNotEmpty(disti_branch.trim())) {
			try {
				disti_branch = java.net.URLDecoder.decode(disti_branch, "UTF-8");
				dr.setDisti_branch(disti_branch.toUpperCase());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.isNotEmpty(endCustomer_name) && StringUtils.isNotEmpty(endCustomer_name.trim())) {
			try {
				endCustomer_name = java.net.URLDecoder.decode(endCustomer_name, "UTF-8");
				dr.setEndCus_name(endCustomer_name.toUpperCase());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.isNotEmpty(project_name) && StringUtils.isNotEmpty(project_name.trim())) {
			try {
				project_name = java.net.URLDecoder.decode(project_name, "UTF-8");
				dr.setProject_name(project_name.toUpperCase());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (create_userName != null && (!("".equals(create_userName)))) {
			dr.setCreate_userName(create_userName.toUpperCase());
		}

		dr.setCustomer_id(customer_id);
		String userId = this.getUser().getUserId();
		String userName = this.getUser().getUserName();
		Role r = new Role();
		r.setStart(0);
		r.setEnd(100);
		r.setEmp_code(userId);
		roleList = roleService.getSelectedRole4StationList(r);
		for (Role rl : roleList) {
			if ("HK10_H_Sale_Mgmt".equals(rl.getRoleId())) {
				dr.setAuditorId(userId);
			}
		}
		String orgString = this.getUser().getOrgId();
		if (orgString == null || "".equals(orgString)) {
			dr.setCus_groupId(userName.toUpperCase());
			Disti_branch disti = new Disti_branch();

			disti.setDisti_name(userName.toUpperCase());
			Disti_branch alias = designRegService.getDistAlias(disti);

			if (alias != null) {
				dr.setDisti_alias(alias.getDisti_alias());
			}

			if (StringUtils.isNotEmpty(disti_branch) && StringUtils.isNotEmpty(disti_branch.trim())) {
				Disti_branch db = new Disti_branch();
				db.setDisti_branch(disti_branch);
				Disti_branch dbAlias = designRegService.getDistBranchAlias(db);

				if (dbAlias != null) {
					dr.setDisti_branch_alias(dbAlias.getAlias());
				}
			}
		}
		if (myself != null && (!("".equals(myself)))) {
			dr.setCreate_userId(userId);
		}
		dr.setEndCus_id(endCus_id);
		total = designRegService.getDesignRegListCount(dr);
		if (total > 0) {
			drList = designRegService.getDesignRegList(dr);
		}
		return JSON;
	}

	@PermissionSearch
	@JsonResult(field = "drdList", include = { "id", "row_no", "drNum", "material_id", "price", "equip_usage",
			"project_state", "state", "drState", "shipPrice", "start_date", "start_dateStr", "equip_type", "value",
			"disti_branch", "end_date", "end_dateStr", "material_name", "primaryProState", "cost", "moq", "pbMpp",
			"project_name", "cus_remark", "remark", "main_id", "create_userId", "create_time", "latest_userId",
			"latest_time", "latest_deptId", "usage_amount", "dr_type", "weencomments", "drtype_def", "dw_cal",
			"dr_typeStr" })
	public String getDesignRegDetailList() {
		drd = new DesignRegDetail();
		drd.setDrNum(drNum);
		drd.setMain_id(Long.valueOf(main_id));
		drdList = designRegService.getDesignRegDetailList(drd);
		if ("copy".equals(forWho)) {
			for (DesignRegDetail designRegDetail : drdList) {
				designRegDetail.setProject_state(0);
				designRegDetail.setPrimaryProState(0);
			}
		}
		return JSON;
	}

	@SuppressWarnings("unchecked")
	public String createDesignReg() throws UnsupportedEncodingException, ParseException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		this.setSuccessMessage("");
		this.setFailMessage("");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		designRegDetailJson = designRegDetailJson.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
		designRegDetailJson = designRegDetailJson.replaceAll("\\+", "%2B");

		designRegDetailJson = java.net.URLDecoder.decode(this.designRegDetailJson, "utf-8");
		drdList = JsonUtil.getDTOList(designRegDetailJson, DesignRegDetail.class);
		dr.setCreate_userId(this.getUser().getUserId());
		dr.setLatest_userId(this.getUser().getUserId());
		if ((dr.getMp_scheduleStr() != null) && (!dr.getMp_scheduleStr().isEmpty())) {
			dr.setMp_schedule(sdf.parse(dr.getMp_scheduleStr()));
		}
		Date date = new Date();
		Calendar now = Calendar.getInstance();
		dr.setStart_date(date);
		dr.setEnd_date(DateUtil.addMonths(date, 3));

		XssUtils.getXssSaftBean(dr.getClass(), dr);

		if (isDisti()) {
			dr.setCreate_time(now.getTime());
			Boolean isNewCustomer = designRegService.checkNewCustomer(dr);

			// Check DR_Type
			for (DesignRegDetail drd : drdList) {

				XssUtils.getXssSaftBean(drd.getClass(), drd);

				Product product = designRegService.getProduct(drd);
				if (product.getRfs_date() == null) {
					// 如果RFS date为空
					this.setFailMessage("The RFS date of " + drd.getMaterial_name()
							+ " is missing, please contact CMD to maintain.");
					return RESULT_MESSAGE;
				}
				// Check New Product
				Boolean isNewProduct = designRegService.checkNewProduct(product, now.getTime());

				if (isNewCustomer && isNewProduct) {
					// New product*New customer
					drd.setDr_type("1");

					DesignRegDetail drType = designRegService.getDRTypeByItmeValue(drd);
					drd.setDrtype_def(drType.getDrtype_def());
					drd.setDw_cal(drType.getDw_cal());
				} else if (!isNewCustomer && isNewProduct) {
					// New product*Existing customer
					drd.setDr_type("2");

					DesignRegDetail drType = designRegService.getDRTypeByItmeValue(drd);
					drd.setDrtype_def(drType.getDrtype_def());
					drd.setDw_cal(drType.getDw_cal());
				} else if (isNewCustomer && !isNewProduct) {
					// Existing product*New customer
					drd.setDr_type("3");

					DesignRegDetail drType = designRegService.getDRTypeByItmeValue(drd);
					drd.setDrtype_def(drType.getDrtype_def());
					drd.setDw_cal(drType.getDw_cal());
				} else if (!isNewCustomer && !isNewProduct) {
					// Existing product*Existing customer
					drd.setDr_type("4");

					DesignRegDetail drType = designRegService.getDRTypeByItmeValue(drd);
					drd.setDrtype_def(drType.getDrtype_def());
					drd.setDw_cal(drType.getDw_cal());
				}
			}
		} else {
			for (DesignRegDetail drd : drdList) {
				if (drd.getDr_type() == null || "".equals(drd.getDr_type().trim())) {
					drd.setDr_type("0");
					continue;
				}
				Product product = designRegService.getProduct(drd);
				if (product.getRfs_date() == null) {
					// 如果RFS date为空
					this.setFailMessage("The RFS date of " + drd.getMaterial_name()
							+ " is missing, please contact CMD to maintain.");
					return RESULT_MESSAGE;
				}
				// Check New Product
				Boolean isNewProduct = designRegService.checkNewProduct(product, now.getTime());
				if ("1".equals(drd.getDr_type()) || "2".equals(drd.getDr_type())) {
					if (!isNewProduct) {
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
						this.setFailMessage(drd.getMaterial_name() + " is not new product.RFS date is "
								+ simpleDateFormat.format(product.getRfs_date())
								+ ".Please change DR type and submit DR then.");
						return RESULT_MESSAGE;
					}
				} else if ("3".equals(drd.getDr_type()) || "4".equals(drd.getDr_type())) {
					if (isNewProduct) {
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
						this.setFailMessage(drd.getMaterial_name() + " is new product.RFS date is "
								+ simpleDateFormat.format(product.getRfs_date())
								+ ".Please change DR type and submit DR then.");
						return RESULT_MESSAGE;
					}
				}

				DesignRegDetail drType = designRegService.getDRTypeByItmeValue(drd);
				drd.setDrtype_def(drType.getDrtype_def());
				drd.setDw_cal(drType.getDw_cal());
			}
		}
		for (DesignRegDetail drd : drdList) {
			if (drd.getRemark() != null) {
				drd.setRemark(Escape.unescape(drd.getRemark()));
				drd.setRemark(StringUtils.replace(drd.getRemark(), "undefined", ""));
				drd.setRemark(drd.getRemark().trim());
			}

			if (drd.getCus_remark() != null) {
				drd.setCus_remark(Escape.unescape(drd.getCus_remark()));
				drd.setCus_remark(StringUtils.replace(drd.getCus_remark(), "undefined", ""));
				drd.setCus_remark(drd.getCus_remark().trim());
			}
		}
		BooleanResult bool = designRegService.createDesignReg(dr, drdList);
		if (bool.getResult()) {
			this.setSuccessMessage("Success !");
			//// 邮件
			String content = "<br>&nbsp;&nbsp;Disti " + dr.getCus_groupId() + " submit a DR,coded as " + dr.getDrNum()
					+ ", please login platform for approval";
			CustomerUser cusUser = new CustomerUser();
			cusUser.setRoleIds("HK10_H_Sale_Mgmt");
			dr.setAuditorId("HK10_H_Sale_Mgmt");
			List<CustomerUser> userList = designRegService.getDRAuditSale(dr);
			if (userList != null && userList.size() != 0) {
				for (CustomerUser user : userList) {
					if (user.getEmail() != null) {
						String contents = "Hi " + user.getUserName() + content + "<br>";
						sendMailByAddree(user.getEmail(), contents, "DR Create");
					}
				}
			}
		} else {
			this.setFailMessage(bool.getCode());
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 修改订单信息
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws ParseException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public String updateDesignReg() throws UnsupportedEncodingException, ParseException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		this.setSuccessMessage("");
		this.setFailMessage("");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		designRegDetailJson = designRegDetailJson.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
		designRegDetailJson = designRegDetailJson.replaceAll("\\+", "%2B");

		designRegDetailJson = java.net.URLDecoder.decode(this.designRegDetailJson, "utf-8");
		drdList = JsonUtil.getDTOList(designRegDetailJson, DesignRegDetail.class);
		dr.setLatest_userId(this.getUser().getUserId());
		dr.setCreate_userId(this.getUser().getUserId());

		XssUtils.getXssSaftBean(dr.getClass(), dr);

		if ((dr.getMp_scheduleStr() != null) && (!dr.getMp_scheduleStr().isEmpty())) {
			dr.setMp_schedule(sdf.parse(dr.getMp_scheduleStr()));
		}
		if ((dr.getStart_dateStr() != null) && (!dr.getStart_dateStr().isEmpty())) {
			dr.setStart_date(sdf.parse(dr.getStart_dateStr()));
		}
		if ((dr.getEnd_dateStr() != null) && (!dr.getEnd_dateStr().isEmpty())) {
			dr.setEnd_date(sdf.parse(dr.getEnd_dateStr()));
		}
		DesignReg mainDr = designRegService.getDesignRegById(dr);
		if (!isDisti()) {
			for (DesignRegDetail drd : drdList) {

				XssUtils.getXssSaftBean(drd.getClass(), drd);

				DesignRegDetail checkData = designRegService.getDRDbyId(drd);
				// if the record is DW, skip
				if (checkData.getProject_state() == 2) {
					continue;
				}
				// if the record is Expired, skip
				else if (checkData.getState() == 3) {
					continue;
				}
				if (drd.getDr_type() == null || "".equals(drd.getDr_type().trim())) {
					continue;
				}
				Product product = designRegService.getProduct(drd);

				if (product.getRfs_date() == null) {
					this.setFailMessage("The RFS date of " + product.getMaterial_name()
							+ " is missing, please contact CMD to maintain.");
					return RESULT_MESSAGE;
				}

				// Check New Product
				Boolean isNewProduct = designRegService.checkNewProduct(product, mainDr.getCreate_time());
				if ("1".equals(drd.getDr_type()) || "2".equals(drd.getDr_type())) {
					if (!isNewProduct) {
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
						this.setFailMessage(drd.getMaterial_name() + " is not new product.RFS date is "
								+ simpleDateFormat.format(product.getRfs_date())
								+ ".Please change DR type and submit DR then.");
						return RESULT_MESSAGE;
					}
				} else if ("3".equals(drd.getDr_type()) || "4".equals(drd.getDr_type())) {
					if (isNewProduct) {
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
						this.setFailMessage(drd.getMaterial_name() + " is new product.RFS date is "
								+ simpleDateFormat.format(product.getRfs_date())
								+ ".Please change DR type and submit DR then.");
						return RESULT_MESSAGE;
					}
				}
				DesignRegDetail drType = designRegService.getDRTypeByItmeValue(drd);
				drd.setDrtype_def(drType.getDrtype_def());
				drd.setDw_cal(drType.getDw_cal());
			}
		} else {
			for (DesignRegDetail drd : drdList) {
				drd.setDr_type("");
			}
		}
		for (DesignRegDetail drd : drdList) {

			if ("1".equals(isUpdate)) {
				if (drd.getCus_remark() != null) {
					drd.setCus_remark(Escape.unescape(drd.getCus_remark()));
					drd.setCus_remark(StringUtils.replace(drd.getCus_remark(), "undefined", ""));
					drd.setCus_remark(drd.getCus_remark().trim());
				}
			} else {
				if (drd.getRemark() != null) {
					drd.setRemark(Escape.unescape(drd.getRemark()));
					drd.setRemark(StringUtils.replace(drd.getRemark(), "undefined", ""));
					drd.setRemark(drd.getRemark().trim());
				}

				if (drd.getWeencomments() != null) {
					drd.setWeencomments(Escape.unescape(drd.getWeencomments()));
					drd.setWeencomments(StringUtils.replace(drd.getWeencomments(), "undefined", ""));
					drd.setWeencomments(drd.getWeencomments().trim());
				}

			}
		}
		BooleanResult bool = designRegService.updateDesignReg(dr, drdList, mainDr.getState(), isUpdate);
		if (bool.getResult()) {
			this.setSuccessMessage("Success !");
			for (DesignRegDetail drd : drdList) {
				if (drd.getState() == 1) {
					// 邮件
					drd.setIds("(" + drd.getId() + ")");
					List<DesignRegDetail> dtList = designRegService.getDRDbyIds(drd);
					if (dtList != null && dtList.size() > 0) {
						for (DesignRegDetail d : dtList) {
							String content = "<br>&nbsp;&nbsp;The material " + d.getMaterial_name()
									+ " you submit in DR " + d.getDrNum()
									+ " has been checked, please login platform to view";
							cusUser = new CustomerUser();
							cusUser = customerService.getUsersByUserId(d.getCreate_userId());
							if (cusUser != null) {
								String contents = "Dear user" + content + "<br>";
								sendMailByAddree(cusUser.getEmail(), contents, "DR Approve");
							}
						}
					}
					// 发邮件给Region sales head
					if (drd.getProject_state() == 2
							&& (drd.getSaler_design_status() == null || drd.getSaler_design_status() == 0L)) {
						drd.setIsCheck("0");
						List<DesignRegDetail> ll = designRegService.getDesignRegDetailList(drd);
						String branch = ll.get(0).getDisti_branch();
						String drnum = ll.get(0).getDrNum();
						drdList = designRegService.getRoleUserByMenuId(drd);
						if (drdList != null && drdList.size() > 0) {
							for (DesignRegDetail d : drdList) {
								String content = "<br>&nbsp;&nbsp; BD/Var " + branch + "'s DR:" + drnum
										+ " has turned to design win, please check and needs your approval in portal.";
								String contents = "" + content + "<br>";
								sendMailByAddree(d.getProject_name(), contents, "DW");
							}
						}
					}

				}
			}
		} else {
			this.setFailMessage(bool.getCode());
		}
		return RESULT_MESSAGE;
	}

	public String deleteDesignReg() {
		this.setFailMessage("");
		this.setSuccessMessage("");
		dr = new DesignReg();
		dr.setId(Long.valueOf(id));
		int i = designRegService.deleteDesignReg(dr);
		if (i > 0) {
			this.setSuccessMessage("Success !");
		} else {
			this.setFailMessage("failed !");
		}
		return RESULT_MESSAGE;
	}

	public String auditDRD() {
		this.setFailMessage("");
		this.setSuccessMessage("");

		String[] id = ids.split(",");
		String[] remark = remarks.split(",");

		String[] weencomments_list = weencomments.split(",");
		int i = 0;

		for (int ii = 0; ii < id.length; ii++) {
			drd = new DesignRegDetail();

			drd.setIds("(" + id[ii] + ")");

			drd.setRemark(Escape.unescape(remark[ii]));
			drd.setRemark(StringUtils.replace(drd.getRemark(), "undefined", ""));
			drd.setRemark(drd.getRemark().trim());

			drd.setWeencomments(Escape.unescape(weencomments_list[ii]));
			drd.setWeencomments(StringUtils.replace(drd.getWeencomments(), "undefined", ""));
			drd.setWeencomments(drd.getWeencomments().trim());

			drd.setState(Integer.valueOf(state));
			drd.setLatest_userId(this.getUser().getUserId());
			i = designRegService.auditDRD(drd);

			if (i > 0) {
				drdList = designRegService.getDRDbyIds(drd);
				if (drdList != null && drdList.size() > 0) {
					for (DesignRegDetail d : drdList) {
						// create DR log START
						DesignReg dr = new DesignReg();
						dr.setId(d.getMain_id());
						DesignReg mainDr = designRegService.getDesignRegById(dr);
						try {
							DesignRegDetailLog log = new DesignRegDetailLog(d);
							if (drd.getState() == 1) {
								log.setType("2");
							} else {
								log.setType("3");
							}
							log.setDr_detail_id(Long.toString(d.getId()));
							log.setCreate_userId(this.getUser().getUserId());
							log.setUsage_amount(mainDr.getUsage_amount());
							log.setMp_schedule(mainDr.getMp_schedule());
							log.setWeencomments(drd.getWeencomments());
							designRegService.createDrLog(log);
						} catch (Exception e) {
							e.printStackTrace();
						}
						// create DR log END
						String content = "<br>&nbsp;&nbsp;The material " + d.getMaterial_name() + " you submit in DR "
								+ d.getDrNum() + " has been checked, please login platform to view";
						cusUser = new CustomerUser();
						cusUser = customerService.getUsersByUserId(d.getCreate_userId());
						if (cusUser != null) {
							String contents = "Dear user" + content + "<br>";
							sendMailByAddree(cusUser.getEmail(), contents, "DR Approve");
						}
					}
				}
			}
		}
		if (i > 0) {
			this.setSuccessMessage("Success !");
		} else {
			this.setFailMessage("failed !");
		}
		return RESULT_MESSAGE;
	}

	@PermissionSearch
	@JsonResult(field = "drdList", total = "total")
	public String checkDesignRegDetailList() {

		drd = new DesignRegDetail();
		drd.setStart(getStart());
		drd.setEnd(getEnd());
		drd.setSort("aa.create_time");
		drd.setDir("desc");
		if (drNum != null && (!("".equals(drNum)))) {
			drd.setDrNum(drNum.toUpperCase());
		}
		drd.setStates(states);
		drd.setSaler_design_statusStr(saler_design_statusStr);
		if (StringUtils.isNotEmpty(cus_groupId) && StringUtils.isNotEmpty(cus_groupId.trim())) {
			try {
				cus_groupId = java.net.URLDecoder.decode(cus_groupId, "UTF-8");
				drd.setCus_groupId(cus_groupId.toUpperCase());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.isNotEmpty(disti_branch) && StringUtils.isNotEmpty(disti_branch.trim())) {
			try {
				disti_branch = java.net.URLDecoder.decode(disti_branch, "UTF-8");
				drd.setDisti_branch(disti_branch.toUpperCase());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.isNotEmpty(endCustomer_name) && StringUtils.isNotEmpty(endCustomer_name.trim())) {
			try {
				endCustomer_name = java.net.URLDecoder.decode(endCustomer_name, "UTF-8");
				drd.setEndCus_name(endCustomer_name.toUpperCase());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.isNotEmpty(project_name) && StringUtils.isNotEmpty(project_name.trim())) {
			try {
				project_name = java.net.URLDecoder.decode(project_name, "UTF-8");
				drd.setProject_name(project_name.toUpperCase());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (create_userName != null && (!("".equals(create_userName)))) {
			drd.setCreate_userName(create_userName.toUpperCase());

		}
		if (material_name != null && (!("".equals(material_name)))) {
			drd.setMaterial_name(material_name.toUpperCase());
		}
		if (equip_type != null && (!("".equals(equip_type)))) {
			drd.setEquip_type(equip_type.toUpperCase());
		}
		drd.setStart_dateStr(start_dateStr);
		drd.setEnd_dateStr(end_dateStr);
		AllUsers allUsers = this.getUser();
		String loginId = allUsers.getLoginId();
		if (!"admin".equals(loginId)) {
			String org = allUsers.getOrgId();
			drd.setSalesOrg(org);
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
				drd.setAuditorId(userId);
			}
		}

		if ("saler_approve".equals(myself) || "leader_approve".equals(myself) || "finance_approve".equals(myself)
				|| "business_approve".equals(myself)) {
			drd.setProject_state(2); // (userId);
			for (Role r2 : roleList) {
				if ("HK10_H_DW_SalesAppr".equals(r2.getRoleId())) {
					drd.setAuditorId(userId);
				}
			}
		}
		if ("leader_approve".equals(myself) || "business_approve".equals(myself)) {
			drd.setAuditorId(null);
		}
		String orgString = this.getUser().getOrgId();
		if (orgString == null || "".equals(orgString)) {
			drd.setCus_groupId(userName.toUpperCase());
			Disti_branch disti = new Disti_branch();

			disti.setDisti_name(userName.toUpperCase());
			Disti_branch alias = designRegService.getDistAlias(disti);

			if (alias != null) {
				drd.setDisti_alias(alias.getDisti_alias());
			}

			if (StringUtils.isNotEmpty(disti_branch) && StringUtils.isNotEmpty(disti_branch.trim())) {
				Disti_branch db = new Disti_branch();
				db.setDisti_branch(disti_branch);
				Disti_branch dbAlias = designRegService.getDistBranchAlias(db);

				if (dbAlias != null) {
					drd.setDisti_branch_alias(dbAlias.getAlias());
				}
			}
		}

		drd.setCustomer_id(customer_id);
		
		total = designRegService.checkDesignRegDetailCount(drd);
		if (total > 0) {
			drdList = designRegService.checkDesignRegDetailList(drd);
			for (DesignRegDetail detail : drdList) {
				if (detail.getDw_cal() == null || "".equals(detail.getDw_cal().trim())) {
					continue;
				}

				try {
					double dwValue = new Double(detail.getDw_cal());
					detail.setDw_value(detail.getValue() * dwValue);
				} catch (NumberFormatException e) {
					continue;
				}
			}
		}
		return JSON;
	}

	@PermissionSearch
	@JsonResult(field = "drdList", include = { "id", "row_no", "drNum", "material_id", "price", "equip_usage",
			"project_state", "state", "drState", "start_date", "start_dateStr", "equip_type", "disti_branch",
			"end_date", "end_dateStr", "material_name", "project_name", "cus_remark", "remark", "main_id",
			"create_userId", "create_time", "latest_userId", "latest_time", "latest_deptId", "cus_groupId",
			"customer_id", "endCus_groupId", "endCus_id", "customer_name", "endCus_groupName", "endCus_name",
			"ec_contact", "usage_amount", "tel", "dr_type", "drtype_def", "dw_cal", "dr_typeStr" }, total = "total")
	public String checkDesignReg() {
		drd = new DesignRegDetail();
		drd.setDrNum(drNum);
		drd.setEndCus_id(endCus_id);
		if (StringUtils.isNotEmpty(endCus_groupId) && StringUtils.isNotEmpty(endCus_groupId.trim())) {
			try {
				endCus_groupId = java.net.URLDecoder.decode(endCus_groupId, "UTF-8");
				endCus_groupId = endCus_groupId.toUpperCase();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		drd.setEndCus_groupId(endCus_groupId);
		drd.setId(Long.valueOf(id));
		drd.setMaterial_id(material_id);
		drdList = designRegService.checkDesignReg(drd);
		return JSON;
	}

	public String updateSalerDesignWinStatus() {
		String userId = this.getUser().getUserId();
		String userName = this.getUser().getUserName();
		String[] s = ids.split(",");
		String[] remarkList = remarks.split(",");
		String[] weencommentlist = weencomments.split(",");

		String[] drTypeList = {};
		if (dr_types != null) {
			drTypeList = dr_types.split(",");
		}
		int i = 0;
		for (int index = 0; index < s.length; index++) {
			String s1 = s[index];
			drd = new DesignRegDetail();
			drd.setId(Long.valueOf(s1));
			drd.setSaler_design_status(Long.valueOf(state));
			drd.setCreate_userId(userId);
			drd.setCreate_userName(userName);
			drd.setDesign_win(new Date());
			if (remarkList.length > index && remarkList[index] != null) {
				drd.setRemark(Escape.unescape(remarkList[index]));
				drd.setRemark(StringUtils.replace(drd.getRemark(), "undefined", ""));
				drd.setRemark(drd.getRemark().trim());
			}

			if (weencommentlist.length > index && weencommentlist[index] != null) {
				drd.setWeencomments(Escape.unescape(weencommentlist[index]));
				drd.setWeencomments(StringUtils.replace(drd.getWeencomments(), "undefined", ""));
				drd.setWeencomments(drd.getWeencomments().trim());
			}

			if ("3".equals(state) || "9".equals(state)) {
				// 拒绝设置Design in
				drd.setProject_state(1);
			} else if ("5".equals(state)) {
				// is Disti
				DesignReg drh = designRegService.getDesignRegByDesignRegDetail(drd);
				String pay_code = drh.getCustomer_id();
				String ssa = pay_code.substring(0, 1);
				if ("7".equals(ssa)) {
					// DW_Reject
					drd.setProject_state(9);
				} else {
					drd.setProject_state(1);
				}
			} else {
				drd.setProject_state(2); // 同意设置Design win
			}
			if (drTypeList.length > index && !"".equals(drTypeList[index])) {
				drd.setDr_type(drTypeList[index]);
				DesignRegDetail drType = designRegService.getDRTypeByItmeValue(drd);

				drd.setDrtype_def(drType.getDrtype_def());
				drd.setDw_cal(drType.getDw_cal());
			}
			i = designRegService.updateDesignRegDetailDesignStatus(drd);
			if (i > 0) {
				drd.setIsCheck(state);
				drd.setMain_id(-1L);
				List<DesignRegDetail> ll = designRegService.getDesignRegDetailList(drd);
				String branch = ll.get(0).getDisti_branch();
				String drnum = ll.get(0).getDrNum();

				drd.setDrNum(drnum);
				drdList = designRegService.getRoleUserByMenuId(drd);

				if (drdList != null && drdList.size() > 0) {
					if ("1".equals(state) || "2".equals(state)) {
						for (DesignRegDetail d : drdList) {
							String content = "<br>&nbsp;&nbsp; BD/Var " + branch + "'s DR:" + drnum
									+ " has turned to design win, please check and needs your approval in portal.";
							String contents = "" + content + "<br>";
							sendMailByAddree(d.getProject_name(), contents, "DW");
						}
					} else if ("4".equals(state)) {
						for (DesignRegDetail d : drdList) {
							String content = "<br>&nbsp;&nbsp; BD/Var " + branch + "'s DR:" + drnum
									+ " got final approved, please check in portal.";
							String contents = "" + content + "<br>";
							sendMailByAddree(d.getProject_name(), contents, "DW");
						}

					} else if ("3".equals(state) || "5".equals(state) || "9".equals(state)) {
						for (DesignRegDetail d : drdList) {
							String content = "<br>&nbsp;&nbsp; BD/Var " + branch + "'s DR:" + drnum
									+ " has been rejected, please check in portal.";
							String contents = "" + content + "<br>";
							sendMailByAddree(d.getProject_name(), contents, "DW");
						}
					}
				}
			}
		}

		this.setSuccessMessage("Success!");
		return RESULT_MESSAGE;
	}

	public void downloadExcelData() {
		try {

			List<String> list = new ArrayList<String>();
			list.add("Status");
			list.add("DRNum");
			list.add("Disti ");
			list.add("Disti Branch");
			list.add("Region");
			list.add("EC Group");
			list.add("End Customer ");
			list.add("EC Contact ");
			list.add("EC Tel Number");
			list.add("12NC");
			list.add("BookPart");
			list.add("DR Type");
			list.add("DR Type Definition");

			list.add("Price ");
			list.add("Annual Runrate");
			list.add("Pcs/Set ");
			list.add("Value ");
			list.add("Application");
			list.add("Registration Date ");
			list.add("Latest Expire");
			list.add("Project");
			list.add("Project Status ");
			list.add("Remarks");
			list.add("Creator Name");
			list.add("Create Date");
			list.add("Design Win");
			list.add("Estimated Share(%)");
			list.add(" MP Schedule");

			list.add("Ween Comments");
			// 导出文件增加三列 20210928 by sst
			list.add("Customer Type");
			list.add("Segment");
			list.add("Segment Application");

			File source = new File("DesignReg.xls");
			WritableWorkbook wwb = Workbook.createWorkbook(source);
			WritableSheet sheet = wwb.createSheet("DesignReg", 0);
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
			jxl.write.Number label11 = null;
			Label label12 = null;
			Label label13 = null;
			jxl.write.Number label14 = null;
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
			Label labelDRType = null;
			Label labelMPSchedule = null;
			Label labelDRTypeDef = null;
			Label labelRegion = null;

			Label lblweencomments = null;
			
			Label lblCustomerType = null;
			Label lblSegment = null;
			Label lblSegmentApplication = null;

			WritableFont font1 = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false,
					UnderlineStyle.NO_UNDERLINE, Colour.RED);

			WritableCellFormat cellFormat1 = new WritableCellFormat(font1);
			cellFormat1.setBackground(Colour.YELLOW);
			cellFormat1.setBorder(Border.ALL, BorderLineStyle.HAIR);
			for (int i = 0; i < list.size(); i++) {
				label = new Label(i, 0, list.get(i).toString(), cellFormat1);
				sheet.addCell(label);
			}

			drd = new DesignRegDetail();
			drd.setStates(states);
			drd.setCustomer_id(customer_id);
			if (StringUtils.isNotEmpty(cus_groupId) && StringUtils.isNotEmpty(cus_groupId.trim())) {
				try {
					cus_groupId = java.net.URLDecoder.decode(cus_groupId, "UTF-8");
					drd.setCus_groupId(cus_groupId.toUpperCase());
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			if (StringUtils.isNotEmpty(disti_branch) && StringUtils.isNotEmpty(disti_branch.trim())) {
				try {
					disti_branch = java.net.URLDecoder.decode(disti_branch, "UTF-8");
					drd.setDisti_branch(disti_branch.toUpperCase());
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			if (StringUtils.isNotEmpty(endCustomer_name) && StringUtils.isNotEmpty(endCustomer_name.trim())) {
				try {
					endCustomer_name = java.net.URLDecoder.decode(endCustomer_name, "UTF-8");
					drd.setEndCus_name(endCustomer_name.toUpperCase());
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			if (StringUtils.isNotEmpty(project_name) && StringUtils.isNotEmpty(project_name.trim())) {
				try {
					project_name = java.net.URLDecoder.decode(project_name, "UTF-8");
					drd.setProject_name(project_name.toUpperCase());
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			drd.setMaterial_id(material_id);
			drd.setStart_dateStr(start_date);
			drd.setEnd_dateStr(end_date);
			if (drNum != null && (!("".equals(drNum)))) {
				drd.setDrNum(drNum.toUpperCase());
			}
			if (customer_name != null && (!("".equals(customer_name)))) {
				drd.setCustomer_name(customer_name.toUpperCase());
			}
			if (material_name != null && (!("".equals(material_name)))) {
				drd.setMaterial_name(material_name.toUpperCase());
			}
			if (equip_type != null && (!("".equals(equip_type)))) {
				drd.setEquip_type(equip_type.toUpperCase());
			}
			drd.setStart_dateStr(start_dateStr);
			drd.setEnd_dateStr(end_dateStr);

			if (create_userName != null && (!("".equals(create_userName)))) {
				drd.setCreate_userName(create_userName.toUpperCase());
			}
			String userId = this.getUser().getUserId();
			Role r = new Role();
			r.setStart(0);
			r.setEnd(100);
			r.setEmp_code(userId);
			roleList = roleService.getSelectedRole4StationList(r);
			for (Role rl : roleList) {
				if ("HK10_H_Sale_Mgmt".equals(rl.getRoleId())) {
					drd.setAuditorId(userId);
				}
			}
			String userName = this.getUser().getUserName();
			String orgString = this.getUser().getOrgId();
			if (orgString == null || "".equals(orgString)) {
				drd.setCus_groupId(userName.toUpperCase());
			}
			if (myself != null && (!("".equals(myself)))) {
				drd.setCreate_userId(userId);
			}
			drdList = designRegService.outPutDR(drd);

			jxl.write.NumberFormat nfPrice = new jxl.write.NumberFormat("0.0000");
			jxl.write.WritableCellFormat cellPrice = new jxl.write.WritableCellFormat(nfPrice);

			jxl.write.NumberFormat nfValue = new jxl.write.NumberFormat("#,###");
			jxl.write.WritableCellFormat cellValue = new jxl.write.WritableCellFormat(nfValue);

			for (int i = 0; i < drdList.size(); i++) {
				DesignRegDetail drdt = drdList.get(i);

				label = new Label(0, i + 1, transState(drdt.getState()));
				label2 = new Label(1, i + 1, drdt.getDrNum());
				label3 = new Label(2, i + 1, drdt.getCus_groupId());
				label4 = new Label(3, i + 1, drdt.getDisti_branch());
				labelRegion = new Label(4, i + 1, transNullString(drdt.getSale_office()));
				label5 = new Label(5, i + 1, transNullString(drdt.getEndCus_groupName()));
				label6 = new Label(6, i + 1, transNullString(drdt.getEndCus_name()));
				label7 = new Label(7, i + 1, transNullString(drdt.getEc_contact()));
				label8 = new Label(8, i + 1, transNullString(drdt.getTel()));
				label9 = new Label(9, i + 1, drdt.getMaterial_id());
				label10 = new Label(10, i + 1, drdt.getMaterial_name());
				labelDRType = new Label(11, i + 1, transNullString(drdt.getDr_typeStr()));
				labelDRTypeDef = new Label(12, i + 1, transNullString(drdt.getDrtype_def()));

				label11 = new jxl.write.Number(13, i + 1, drdt.getPrice(), cellPrice);
				label12 = new Label(14, i + 1, String.valueOf(drdt.getUsage_amount()));
				label13 = new Label(15, i + 1, String.valueOf(drdt.getEquip_usage()));
				label14 = new jxl.write.Number(16, i + 1, drdt.getValue(), cellValue);
				label15 = new Label(17, i + 1, transNullString(drdt.getEquip_type()));//
				label16 = new Label(18, i + 1, transDate(drdt.getStart_date()));//
				label17 = new Label(19, i + 1, transDate(drdt.getEnd_date()));//
				label18 = new Label(20, i + 1, transNullString(drdt.getProject_name()));
				label19 = new Label(21, i + 1, transProjectState(drdt.getProject_state()));
				label20 = new Label(22, i + 1, transNullString(drdt.getCus_remark()));
				label21 = new Label(23, i + 1, transNullString(drdt.getCreate_userName()));
				label22 = new Label(24, i + 1, transNullString(DateUtil.date(drdt.getCreate_time(), "yyyy-MM-dd")));
				label23 = new Label(25, i + 1, transNullString(drdt.getDesign_winStr()));
				label24 = new Label(26, i + 1, transNullString(drdt.getEstimated_share()));

				labelMPSchedule = new Label(27, i + 1, transNullString(drdt.getMp_scheduleStr()));

				lblweencomments = new Label(28, i + 1, transNullString(drdt.getWeencomments()));
				
				lblCustomerType = new Label(29, i + 1, transNullString(drdt.getCustomerTypeName()));
				lblSegment = new Label(30, i + 1, transNullString(drdt.getSegmentName()));
				lblSegmentApplication = new Label(31, i + 1, transNullString(drdt.getApplicationName()));

				sheet.addCell(label);
				sheet.setColumnView(0, 15);
				sheet.addCell(label2);
				sheet.setColumnView(1, 15);
				sheet.addCell(label3);
				sheet.setColumnView(2, 30);
				sheet.addCell(label4);
				sheet.setColumnView(3, 50);
				sheet.addCell(labelRegion);
				sheet.setColumnView(4, 15);
				sheet.addCell(label5);
				sheet.setColumnView(5, 40);
				sheet.addCell(label6);
				sheet.setColumnView(6, 15);
				sheet.addCell(label7);
				sheet.setColumnView(7, 20);
				sheet.addCell(label8);
				sheet.setColumnView(8, 20);
				sheet.addCell(label9);
				sheet.setColumnView(9, 40);
				sheet.addCell(label10);
				sheet.setColumnView(10, 15);
				sheet.addCell(labelDRType);
				sheet.setColumnView(11, 15);
				sheet.addCell(labelDRTypeDef);
				sheet.setColumnView(12, 50);

				sheet.addCell(label11);
				sheet.setColumnView(13, 15);
				sheet.addCell(label12);
				sheet.setColumnView(14, 15);
				sheet.addCell(label13);
				sheet.setColumnView(15, 15);
				sheet.addCell(label14);
				sheet.setColumnView(16, 15);
				sheet.addCell(label15);
				sheet.setColumnView(17, 15);
				sheet.addCell(label16);
				sheet.setColumnView(18, 15);
				sheet.addCell(label17);
				sheet.setColumnView(19, 15);
				sheet.addCell(label18);
				sheet.setColumnView(20, 15);
				sheet.addCell(label19);
				sheet.setColumnView(21, 30);
				sheet.addCell(label20);
				sheet.setColumnView(22, 30);
				sheet.addCell(label21);
				sheet.setColumnView(23, 15);
				sheet.addCell(label22);
				sheet.setColumnView(24, 15);
				sheet.addCell(label23);
				sheet.setColumnView(25, 15);
				sheet.addCell(label24);
				sheet.setColumnView(26, 15);

				sheet.addCell(labelMPSchedule);
				sheet.setColumnView(27, 15);

				sheet.addCell(lblweencomments);
				sheet.setColumnView(28, 15);
				
				sheet.addCell(lblCustomerType);
				sheet.setColumnView(29, 15);
				sheet.addCell(lblSegment);
				sheet.setColumnView(30, 15);
				sheet.addCell(lblSegmentApplication);
				sheet.setColumnView(31, 15);

			}
			wwb.write();
			wwb.close();

			display(source, "DesignReg.xls", ServletActionContext.getResponse());

		} catch (Exception e) {
			e.printStackTrace();
			this.setFailMessage("Failed:");
		}
	}

	public void downloadExcelDataForDw() throws Exception {
		try {

			List<String> list = new ArrayList<String>();
			list.add("Dw Status");
			list.add("DRNum");
			list.add("Disti ");
			list.add("Disti Branch");
			list.add("Region");
			list.add("EC Group");
			list.add("End Customer ");
			list.add("EC Contact ");
			list.add("EC Tel Number");
			list.add("12NC");
			list.add("BookPart");
			list.add("Price ");
			list.add("Annual runrate");
			list.add("Pcs/Set ");
			list.add("DR Value ");

			list.add("DW Value ");
			list.add("DR Type");
			list.add("DR Type Definition");

			list.add("Application");
			list.add("Registration Date ");
			list.add("Latest Expire");
			list.add("Project");
			list.add("Project Status ");
			list.add("Cus Remarks");
			list.add("Creator Name");
			list.add("Create Date");
			list.add("Design win");
			list.add("DR Submitter");
			list.add("DR Approver");
			list.add("DR Approved Date");
			list.add("DW Approved-1(Regional sales)");
			list.add("DW Approved-1(Regional sales) date");
			list.add("DW Approved-2(Sales head)");
			list.add("DW Approved-2(Sales head) date");
			list.add("DW Approved-3(MKT)");
			list.add("DW Approved-3(MKT) date");

			list.add("Ween Comments");

			File source = new File("DesignReg.xls");
			WritableWorkbook wwb = Workbook.createWorkbook(source);
			WritableSheet sheet = wwb.createSheet("DesignReg", 0);
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

			Label lblweencomments = null;

			jxl.write.Number labelDwValue = null;
			Label labelDrType = null;
			Label labelDrTypeDef = null;
			jxl.write.NumberFormat nfDwValue = new jxl.write.NumberFormat("0.0");
			jxl.write.WritableCellFormat cellDwValue = new jxl.write.WritableCellFormat(nfDwValue);

			WritableFont font1 = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false,
					UnderlineStyle.NO_UNDERLINE, Colour.RED);

			WritableCellFormat cellFormat1 = new WritableCellFormat(font1);
			cellFormat1.setBackground(Colour.YELLOW);
			cellFormat1.setBorder(Border.ALL, BorderLineStyle.HAIR);
			for (int i = 0; i < list.size(); i++) {
				label = new Label(i, 0, list.get(i).toString(), cellFormat1);
				sheet.addCell(label);
			}

			drd = new DesignRegDetail();
			drd.setStates(states);
			drd.setCustomer_id(customer_id);
			if (StringUtils.isNotEmpty(cus_groupId) && StringUtils.isNotEmpty(cus_groupId.trim())) {
				try {
					cus_groupId = java.net.URLDecoder.decode(cus_groupId, "UTF-8");
					drd.setCus_groupId(cus_groupId.toUpperCase());
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			if (StringUtils.isNotEmpty(disti_branch) && StringUtils.isNotEmpty(disti_branch.trim())) {
				try {
					disti_branch = java.net.URLDecoder.decode(disti_branch, "UTF-8");
					drd.setDisti_branch(disti_branch.toUpperCase());
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			if (StringUtils.isNotEmpty(endCustomer_name) && StringUtils.isNotEmpty(endCustomer_name.trim())) {
				try {
					endCustomer_name = java.net.URLDecoder.decode(endCustomer_name, "UTF-8");
					drd.setEndCus_name(endCustomer_name.toUpperCase());
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			if (StringUtils.isNotEmpty(project_name) && StringUtils.isNotEmpty(project_name.trim())) {
				try {
					project_name = java.net.URLDecoder.decode(project_name, "UTF-8");
					drd.setProject_name(project_name.toUpperCase());
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			drd.setMaterial_id(material_id);
			drd.setStart_dateStr(start_date);
			drd.setEnd_dateStr(end_date);
			if (drNum != null && (!("".equals(drNum)))) {
				drd.setDrNum(drNum.toUpperCase());
			}
			if (customer_name != null && (!("".equals(customer_name)))) {
				drd.setCustomer_name(customer_name.toUpperCase());

			}
			if (material_name != null && (!("".equals(material_name)))) {
				drd.setMaterial_name(material_name.toUpperCase());
			}
			if (equip_type != null && (!("".equals(equip_type)))) {
				drd.setEquip_type(equip_type.toUpperCase());
			}
			drd.setStart_dateStr(start_dateStr);
			drd.setEnd_dateStr(end_dateStr);

			if (create_userName != null && (!("".equals(create_userName)))) {
				drd.setCreate_userName(create_userName.toUpperCase());
			}
			String userId = this.getUser().getUserId();
			Role r = new Role();
			r.setStart(0);
			r.setEnd(100);
			r.setEmp_code(userId);
			roleList = roleService.getSelectedRole4StationList(r);
			for (Role rl : roleList) {
				if ("HK10_H_Sale_Mgmt".equals(rl.getRoleId())) {
					drd.setAuditorId(userId);
				}
			}
			String userName = this.getUser().getUserName();
			String orgString = this.getUser().getOrgId();
			if (orgString == null || "".equals(orgString)) {
				drd.setCus_groupId(userName.toUpperCase());
			}
			drd.setProject_state(2);
			drdList = designRegService.outPutDR(drd);

			for (DesignRegDetail detail : drdList) {
				if (detail.getDw_cal() == null || "".equals(detail.getDw_cal().trim())) {
					continue;
				}

				try {
					double dwValue = new Double(detail.getDw_cal());
					detail.setDw_value(detail.getValue() * dwValue);
				} catch (NumberFormatException e) {
					continue;
				}
			}

			for (int i = 0; i < drdList.size(); i++) {
				DesignRegDetail drdt = drdList.get(i);
				label = new Label(0, i + 1, transStateDw(Integer.valueOf(drdt.getSaler_design_status().toString())));
				label2 = new Label(1, i + 1, drdt.getDrNum());
				label3 = new Label(2, i + 1, drdt.getCus_groupId());
				label4 = new Label(3, i + 1, drdt.getDisti_branch());
				label24 = new Label(4, i + 1, transNullString(drdt.getSale_office()));
				label5 = new Label(5, i + 1, transNullString(drdt.getEndCus_groupName()));
				label6 = new Label(6, i + 1, transNullString(drdt.getEndCus_name()));
				label7 = new Label(7, i + 1, transNullString(drdt.getEc_contact()));
				label8 = new Label(8, i + 1, transNullString(drdt.getTel()));
				label9 = new Label(9, i + 1, drdt.getMaterial_id());
				label10 = new Label(10, i + 1, drdt.getMaterial_name());
				label11 = new Label(11, i + 1, String.valueOf(drdt.getPrice()));
				label12 = new Label(12, i + 1, String.valueOf(drdt.getUsage_amount()));
				label13 = new Label(13, i + 1, String.valueOf(drdt.getEquip_usage()));
				label14 = new Label(14, i + 1, String.valueOf(drdt.getValue()));

				labelDwValue = new jxl.write.Number(15, i + 1, drdt.getDw_value(), cellDwValue);
				labelDrType = new Label(16, i + 1, transNullString(drdt.getDr_typeStr()));
				labelDrTypeDef = new Label(17, i + 1, transNullString(drdt.getDrtype_def()));

				label15 = new Label(18, i + 1, transNullString(drdt.getEquip_type()));//
				label16 = new Label(19, i + 1, transDate(drdt.getStart_date()));//
				label17 = new Label(20, i + 1, transDate(drdt.getEnd_date()));//
				label18 = new Label(21, i + 1, transNullString(drdt.getProject_name()));
				label19 = new Label(22, i + 1, transProjectState(drdt.getProject_state()));
				label20 = new Label(23, i + 1, transNullString(drdt.getCus_remark()));
				label21 = new Label(24, i + 1, transNullString(drdt.getCreate_userName()));
				label22 = new Label(25, i + 1, transNullString(DateUtil.date(drdt.getCreate_time(), "yyyy-MM-dd")));
				label23 = new Label(26, i + 1, transNullString(DateUtil.date(drdt.getDesign_win(), "yyyy-MM-dd")));
				label25 = new Label(27, i + 1, transNullString(drdt.getCreate_userName())); // DR submitter
				label26 = new Label(28, i + 1, transNullString(drdt.getAuditorId())); // 26 DR approver

				// 20190403 START
				// DR approved date
				// label27 = new Label(26, i + 1,
				// transNullString(DateUtil.date(drdt.getCreate_time(), "yyyy-MM-dd")));
				DesignRegDetail drdLog = new DesignRegDetail();
				drdLog.setId(drdt.getId());
				List<DesignRegDetailLog> LogList = designRegService.getDrLogList(drdLog);

				if (LogList != null && LogList.size() > 0) {
					Date drApproveDate = drdt.getCreate_time();
					for (DesignRegDetailLog log : LogList) {
						if ("2".equals(log.getType())) {
							drApproveDate = log.getCreate_time();
						}
					}
					label27 = new Label(29, i + 1, transNullString(DateUtil.date(drApproveDate, "yyyy-MM-dd")));
					LogList.clear();
				} else {
					label27 = new Label(29, i + 1, transNullString(DateUtil.date(drdt.getCreate_time(), "yyyy-MM-dd")));
				}
				// 20190403 END

				label28 = new Label(30, i + 1, transNullString(drdt.getAudit_person())); //
				label29 = new Label(31, i + 1, transNullString(drdt.getAudit_time()));
				label30 = new Label(32, i + 1, transNullString(drdt.getAudit_person2()));
				label31 = new Label(33, i + 1, transNullString(drdt.getAudit_time2()));
				label32 = new Label(34, i + 1, transNullString(drdt.getAudit_person3()));
				label33 = new Label(35, i + 1, transNullString(drdt.getAudit_time3()));

				lblweencomments = new Label(36, i + 1, transNullString(drdt.getWeencomments()));

				sheet.addCell(label);
				sheet.setColumnView(0, 15);
				sheet.addCell(label2);
				sheet.setColumnView(1, 15);
				sheet.addCell(label3);
				sheet.setColumnView(2, 30);
				sheet.addCell(label4);
				sheet.setColumnView(3, 50);
				sheet.addCell(label24);
				sheet.setColumnView(4, 15);
				sheet.addCell(label5);
				sheet.setColumnView(5, 40);
				sheet.addCell(label6);
				sheet.setColumnView(6, 15);
				sheet.addCell(label7);
				sheet.setColumnView(7, 20);
				sheet.addCell(label8);
				sheet.setColumnView(8, 20);
				sheet.addCell(label9);
				sheet.setColumnView(9, 40);
				sheet.addCell(label10);
				sheet.setColumnView(10, 15);
				sheet.addCell(label11);
				sheet.setColumnView(11, 15);
				sheet.addCell(label12);
				sheet.setColumnView(12, 15);
				sheet.addCell(label13);
				sheet.setColumnView(13, 15);
				sheet.addCell(label14);
				sheet.setColumnView(14, 15);

				sheet.addCell(labelDwValue);
				sheet.setColumnView(15, 15);
				sheet.addCell(labelDrType);
				sheet.setColumnView(16, 15);
				sheet.addCell(labelDrTypeDef);
				sheet.setColumnView(17, 50);

				sheet.addCell(label15);
				sheet.setColumnView(18, 15);
				sheet.addCell(label16);
				sheet.setColumnView(19, 15);
				sheet.addCell(label17);
				sheet.setColumnView(20, 15);
				sheet.addCell(label18);
				sheet.setColumnView(21, 15);
				sheet.addCell(label19);
				sheet.setColumnView(22, 30);
				sheet.addCell(label20);
				sheet.setColumnView(23, 30);
				sheet.addCell(label21);
				sheet.setColumnView(24, 15);
				sheet.addCell(label22);
				sheet.setColumnView(25, 15);
				sheet.addCell(label23);
				sheet.setColumnView(26, 15);
				sheet.addCell(label25);
				sheet.setColumnView(27, 15);
				sheet.addCell(label26);
				sheet.setColumnView(28, 15);
				sheet.addCell(label27);
				sheet.setColumnView(29, 15);
				sheet.addCell(label28);
				sheet.setColumnView(30, 15);
				sheet.addCell(label29);
				sheet.setColumnView(31, 15);
				sheet.addCell(label30);
				sheet.setColumnView(32, 15);
				sheet.addCell(label31);
				sheet.setColumnView(33, 15);
				sheet.addCell(label32);
				sheet.setColumnView(34, 15);
				sheet.addCell(label33);
				sheet.setColumnView(35, 15);

				sheet.addCell(lblweencomments);
				sheet.setColumnView(36, 15);
			}
			wwb.write();
			wwb.close();

			display(source, "DesignReg.xls", ServletActionContext.getResponse());

		} catch (Exception e) {
			e.printStackTrace();
			this.setFailMessage("Failed !");
		}
	}

	public void downloadExcelDataForSales() throws Exception {
		try {

			List<String> list = new ArrayList<String>();
			list.add("Dw Status");
			list.add("DRNum");
			list.add("Disti ");
			list.add("Disti Branch");
			list.add("Region");
			list.add("EC Group");
			list.add("End Customer ");
			list.add("EC Contact ");
			list.add("EC Tel Number");
			list.add("12NC");
			list.add("BookPart");
			list.add("Price ");
			list.add("Annual runrate");
			list.add("Pcs/Set ");
			list.add("Value ");
			list.add("Application");
			list.add("Registration Date ");
			list.add("Latest Expire");
			list.add("Project");
			list.add("Project Status ");
			list.add("Cus Remarks");
			list.add("Creator Name");
			list.add("Create Date");
			list.add("Design Win");
			list.add("DR Submitter");
			list.add("DR Approver");
			list.add("DR Approved Date");
			list.add("DW Approved-1(Regional sales)");
			list.add("DW Approved-1(Regional sales) date");
			list.add("DW Approved-2(Sales head)	");
			list.add("DW Approved-2(Sales head) date");
			list.add("DW Approved-3(MKT)");
			list.add("DW Approved-3(MKT) date");
			list.add("DR Type");
			list.add("DR Type Definition");

			File source = new File("DesignReg.xls");
			WritableWorkbook wwb = Workbook.createWorkbook(source);
			WritableSheet sheet = wwb.createSheet("DesignReg", 0);
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

			WritableFont font1 = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false,
					UnderlineStyle.NO_UNDERLINE, Colour.RED);

			WritableCellFormat cellFormat1 = new WritableCellFormat(font1);
			cellFormat1.setBackground(Colour.YELLOW);
			cellFormat1.setBorder(Border.ALL, BorderLineStyle.HAIR);
			for (int i = 0; i < list.size(); i++) {
				label = new Label(i, 0, list.get(i).toString(), cellFormat1);
				sheet.addCell(label);
			}

			drd = new DesignRegDetail();
			drd.setStates(states);
			drd.setCustomer_id(customer_id);
			if (StringUtils.isNotEmpty(cus_groupId) && StringUtils.isNotEmpty(cus_groupId.trim())) {
				try {
					cus_groupId = java.net.URLDecoder.decode(cus_groupId, "UTF-8");
					drd.setCus_groupId(cus_groupId.toUpperCase());
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			if (StringUtils.isNotEmpty(disti_branch) && StringUtils.isNotEmpty(disti_branch.trim())) {
				try {
					disti_branch = java.net.URLDecoder.decode(disti_branch, "UTF-8");
					drd.setDisti_branch(disti_branch.toUpperCase());
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			if (StringUtils.isNotEmpty(endCustomer_name) && StringUtils.isNotEmpty(endCustomer_name.trim())) {
				try {
					endCustomer_name = java.net.URLDecoder.decode(endCustomer_name, "UTF-8");
					drd.setEndCus_name(endCustomer_name.toUpperCase());
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			if (StringUtils.isNotEmpty(project_name) && StringUtils.isNotEmpty(project_name.trim())) {
				try {
					project_name = java.net.URLDecoder.decode(project_name, "UTF-8");
					drd.setProject_name(project_name.toUpperCase());
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			drd.setMaterial_id(material_id);
			drd.setStart_dateStr(start_date);
			drd.setEnd_dateStr(end_date);
			if (drNum != null && (!("".equals(drNum)))) {
				drd.setDrNum(drNum.toUpperCase());
			}
			if (customer_name != null && (!("".equals(customer_name)))) {
				drd.setCustomer_name(customer_name.toUpperCase());

			}
			if (material_name != null && (!("".equals(material_name)))) {
				drd.setMaterial_name(material_name.toUpperCase());
			}
			if (equip_type != null && (!("".equals(equip_type)))) {
				drd.setEquip_type(equip_type.toUpperCase());
			}
			drd.setStart_dateStr(start_dateStr);
			drd.setEnd_dateStr(end_dateStr);

			if (create_userName != null && (!("".equals(create_userName)))) {
				drd.setCreate_userName(create_userName.toUpperCase());
			}
			String userId = this.getUser().getUserId();
			Role r = new Role();
			r.setStart(0);
			r.setEnd(100);
			r.setEmp_code(userId);
			roleList = roleService.getSelectedRole4StationList(r);
			for (Role rl : roleList) {
				if ("HK10_H_Sale_Mgmt".equals(rl.getRoleId())) {
					drd.setAuditorId(userId);
				}
				if ("DR".equals(rl.getRoleId())) {
					drd.setCreate_userId(userId);
				}
			}
			drd.setProject_state(2); // (userId);
			String userName = this.getUser().getUserName();
			String orgString = this.getUser().getOrgId();
			if (orgString == null || "".equals(orgString)) {
				drd.setCus_groupId(userName.toUpperCase());
			}
			drdList = designRegService.outPutDR(drd);

			for (int i = 0; i < drdList.size(); i++) {
				DesignRegDetail drdt = drdList.get(i);
				label = new Label(0, i + 1, transStateDw(Integer.valueOf(drdt.getSaler_design_status().toString())));
				label2 = new Label(1, i + 1, drdt.getDrNum());
				label3 = new Label(2, i + 1, drdt.getCus_groupId());
				label4 = new Label(3, i + 1, drdt.getDisti_branch());
				label24 = new Label(4, i + 1, transNullString(drdt.getSale_office())); // Region 24
				label5 = new Label(5, i + 1, transNullString(drdt.getEndCus_groupName()));
				label6 = new Label(6, i + 1, transNullString(drdt.getEndCus_name()));
				label7 = new Label(7, i + 1, transNullString(drdt.getEc_contact()));
				label8 = new Label(8, i + 1, transNullString(drdt.getTel()));
				label9 = new Label(9, i + 1, drdt.getMaterial_id());
				label10 = new Label(10, i + 1, drdt.getMaterial_name());
				label11 = new Label(11, i + 1, String.valueOf(drdt.getPrice()));
				label12 = new Label(12, i + 1, String.valueOf(drdt.getUsage_amount()));
				label13 = new Label(13, i + 1, String.valueOf(drdt.getEquip_usage()));
				label14 = new Label(14, i + 1, String.valueOf(drdt.getValue()));
				label15 = new Label(15, i + 1, transNullString(drdt.getEquip_type()));//
				label16 = new Label(16, i + 1, transDate(drdt.getStart_date()));//
				label17 = new Label(17, i + 1, transDate(drdt.getEnd_date()));//
				label18 = new Label(18, i + 1, transNullString(drdt.getProject_name()));
				label19 = new Label(19, i + 1, transProjectState(drdt.getProject_state()));
				label20 = new Label(20, i + 1, transNullString(drdt.getCus_remark()));
				label21 = new Label(21, i + 1, transNullString(drdt.getCreate_userName()));
				label22 = new Label(22, i + 1, transNullString(DateUtil.date(drdt.getCreate_time(), "yyyy-MM-dd")));
				label23 = new Label(23, i + 1, transNullString(DateUtil.date(drdt.getDesign_win(), "yyyy-MM-dd")));
				label25 = new Label(24, i + 1, transNullString(drdt.getCreate_userName())); // DR submitter
				label26 = new Label(25, i + 1, transNullString(drdt.getAuditorId())); // 26 DR approver
				// 20190403 START
				// DR approved date
				// label27 = new Label(26, i + 1,
				// transNullString(DateUtil.date(drdt.getCreate_time(), "yyyy-MM-dd")));
				DesignRegDetail drdLog = new DesignRegDetail();
				drdLog.setId(drdt.getId());
				List<DesignRegDetailLog> LogList = designRegService.getDrLogList(drdLog);

				if (LogList != null && LogList.size() > 0) {
					Date drApproveDate = drdt.getCreate_time();
					for (DesignRegDetailLog log : LogList) {
						if ("2".equals(log.getType())) {
							drApproveDate = log.getCreate_time();
						}
					}
					label27 = new Label(26, i + 1, transNullString(DateUtil.date(drApproveDate, "yyyy-MM-dd")));
					LogList.clear();
				} else {
					label27 = new Label(26, i + 1, transNullString(DateUtil.date(drdt.getCreate_time(), "yyyy-MM-dd")));
				}
				// 20190403 END
				label28 = new Label(27, i + 1, transNullString(drdt.getAudit_person())); //
				label29 = new Label(28, i + 1, transNullString(drdt.getAudit_time()));
				label30 = new Label(29, i + 1, transNullString(drdt.getAudit_person2()));
				label31 = new Label(30, i + 1, transNullString(drdt.getAudit_time2()));
				label32 = new Label(31, i + 1, transNullString(drdt.getAudit_person3()));
				label33 = new Label(32, i + 1, transNullString(drdt.getAudit_time3()));

				label34 = new Label(33, i + 1, transNullString(drdt.getDr_typeStr()));
				label35 = new Label(34, i + 1, transNullString(drdt.getDrtype_def()));

				sheet.addCell(label);
				sheet.setColumnView(0, 15);
				sheet.addCell(label2);
				sheet.setColumnView(1, 15);
				sheet.addCell(label3);
				sheet.setColumnView(2, 30);
				sheet.addCell(label4);
				sheet.setColumnView(3, 50);
				sheet.addCell(label24);
				sheet.setColumnView(4, 15);
				sheet.addCell(label5);
				sheet.setColumnView(5, 40);
				sheet.addCell(label6);
				sheet.setColumnView(6, 15);
				sheet.addCell(label7);
				sheet.setColumnView(7, 20);
				sheet.addCell(label8);
				sheet.setColumnView(8, 20);
				sheet.addCell(label9);
				sheet.setColumnView(9, 40);
				sheet.addCell(label10);
				sheet.setColumnView(10, 15);
				sheet.addCell(label11);
				sheet.setColumnView(11, 15);
				sheet.addCell(label12);
				sheet.setColumnView(12, 15);
				sheet.addCell(label13);
				sheet.setColumnView(13, 15);
				sheet.addCell(label14);
				sheet.setColumnView(14, 15);
				sheet.addCell(label15);
				sheet.setColumnView(15, 15);
				sheet.addCell(label16);
				sheet.setColumnView(16, 15);
				sheet.addCell(label17);
				sheet.setColumnView(17, 15);
				sheet.addCell(label18);
				sheet.setColumnView(18, 15);
				sheet.addCell(label19);
				sheet.setColumnView(19, 30);
				sheet.addCell(label20);
				sheet.setColumnView(20, 30);
				sheet.addCell(label21);
				sheet.setColumnView(21, 15);
				sheet.addCell(label22);
				sheet.setColumnView(22, 15);
				sheet.addCell(label23);
				sheet.setColumnView(23, 15);
				sheet.addCell(label25);
				sheet.setColumnView(24, 15);
				sheet.addCell(label26);
				sheet.setColumnView(25, 15);
				sheet.addCell(label27);
				sheet.setColumnView(26, 15);
				sheet.addCell(label28);
				sheet.setColumnView(27, 15);
				sheet.addCell(label29);
				sheet.setColumnView(28, 15);
				sheet.addCell(label30);
				sheet.setColumnView(29, 15);
				sheet.addCell(label31);
				sheet.setColumnView(30, 15);
				sheet.addCell(label32);
				sheet.setColumnView(31, 15);
				sheet.addCell(label33);
				sheet.setColumnView(32, 15);
				sheet.addCell(label34);
				sheet.setColumnView(33, 15);
				sheet.addCell(label35);
				sheet.setColumnView(34, 15);
			}
			wwb.write();
			wwb.close();

			display(source, "DesignReg.xls", ServletActionContext.getResponse());
		} catch (Exception e) {
			e.printStackTrace();
			this.setFailMessage("Failed !");
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

	public String transStateDw(int flag) {
		if (flag == 0) {
			return "pending";
		} else if (flag == 1) {
			return "Regional Sales approved";
		} else if (flag == 2) {
			return "Sales head approved";
		} else if (flag == 3) {
			return "Sales head reject";
		} else if (flag == 4) {
			return "Final approved";
		} else if (flag == 5) {
			return "Final reject";
		} else if (flag == 6) {
			return "Finance approved";
		} else if (flag == 7) {
			return "Finance reject";
		} else if (flag == 9) {
			return "Regional Sales reject";
		} else {
			return "";
		}
	}

	public String transState(int flag) {
		if (flag == 9) {
			return "Deleted";
		} else if (flag == 0) {
			return "Pending";
		} else if (flag == 1) {
			return "Approve";
		} else if (flag == 2) {
			return "Reject";
		} else if (flag == 3) {
			return "Expired";
		} else {
			return String.valueOf(flag);
		}
	}

    public String transProjectState(int flag) {
        if (flag == 0) {
            return "Opportunity";
        } else if (flag == 1) {
            return "Design_in";
        } else if (flag == 2) {
            return "Design_win";
        } else if (flag == 9) {
            return "DW_Reject";
        } 
        else {
            return String.valueOf(flag);
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
		if (s == null || "undefined".equals(s) || "".equals(s)) {
			return "";
		} else {
			return s;
		}
	}

	public String transPersent(String s) {
		if (s == null || "undefined".equals(s) || "".equals(s)) {
			return "";
		} else {
			return s + "%";
		}
	}

	@PermissionSearch
	@JsonResult(field = "cmsTbDictList", include = { "itemId", "itemValue", "itemName", "itemState", "remark" })
	public String getDictOfWeen() {
		Dict m = new Dict();
		m.setDictTypeId(dictTypeId);
		m.setItemId(Long.valueOf(itemId));
		m.setSort("e.item_value");
		m.setDir("ASC");
		cmsTbDictList = designRegService.getDictOfWeen(m);
		return JSON;
	}

	// 上传文件
	public String findOrderExcel() {
		return "";
	}

	public String sendMailByAddree(String emailAddress, String content, String type) {
		Message m = new Message();
		m.setContent(content);
		m.setType(type);
		m.setSendNumber(emailAddress);
		messageService.saveMessage(m);
		return "saveSuccess";
	}

	public String toSearchDrLog() {
		drd = new DesignRegDetail();
		drd.setId(Long.valueOf(id));
		return "toSearchDrLog";
	}

	@PermissionSearch
	@JsonResult(field = "drLogList", include = { "type", "price", "usage_amount", "mp_schedule", "equip_usage",
			"equip_type", "project_state", "start_dateStr", "state", "primaryProState", "end_dateStr",
			"product_dateStr", "value", "project_name", "cus_remark", "remark", "create_userName", "create_time",
			"createTime", "dr_type", "drtype_def", "dw_cal", "dr_typeStr", "weencomments" })
	public String getDesignRegDetailLogList() {
		drd = new DesignRegDetail();
		drd.setId(Long.valueOf(id));
		drLogList = designRegService.getDrLogList(drd);
		return JSON;
	}

	public IDesignRegService getDesignRegService() {
		return designRegService;
	}

	public void setDesignRegService(IDesignRegService designRegService) {
		this.designRegService = designRegService;
	}

	public List<DesignReg> getdrList() {
		return drList;
	}

	public void setdrList(List<DesignReg> drList) {
		this.drList = drList;
	}

	public List<DesignRegDetail> getEdrList() {
		return drdList;
	}

	public void setEdrList(List<DesignRegDetail> drdList) {
		this.drdList = drdList;
	}

	public String getDesignRegDetailJson() {
		return designRegDetailJson;
	}

	public void setDesignRegDetailJson(String designRegDetailJson) {
		this.designRegDetailJson = designRegDetailJson;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDelDesignRegDetail() {
		return delDesignRegDetail;
	}

	public void setDelDesignRegDetail(String delDesignRegDetail) {
		this.delDesignRegDetail = delDesignRegDetail;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public DesignReg getDr() {
		return dr;
	}

	public void setDr(DesignReg dr) {
		this.dr = dr;
	}

	public List<DesignReg> getDrList() {
		return drList;
	}

	public void setDrList(List<DesignReg> drList) {
		this.drList = drList;
	}

	public DesignRegDetail getDrd() {
		return drd;
	}

	public void setDrd(DesignRegDetail drd) {
		this.drd = drd;
	}

	public List<DesignRegDetail> getDrdList() {
		return drdList;
	}

	public void setDrdList(List<DesignRegDetail> drdList) {
		this.drdList = drdList;
	}

	public String getDrNum() {
		return drNum;
	}

	public void setDrNum(String drNum) {
		this.drNum = drNum;
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

	public String getEndCus_groupId() {
		return endCus_groupId;
	}

	public void setEndCus_groupId(String endCus_groupId) {
		this.endCus_groupId = endCus_groupId;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getEndCus_id() {
		return endCus_id;
	}

	public void setEndCus_id(String endCus_id) {
		this.endCus_id = endCus_id;
	}

	public String getMaterial_id() {
		return material_id;
	}

	public void setMaterial_id(String material_id) {
		this.material_id = material_id;
	}

	public String getQuoteDetail() {
		return quoteDetail;
	}

	public void setQuoteDetail(String quoteDetail) {
		this.quoteDetail = quoteDetail;
	}

	public Quote getQ() {
		return q;
	}

	public void setQ(Quote q) {
		this.q = q;
	}

	public String getEndCustomer_id() {
		return endCustomer_id;
	}

	public void setEndCustomer_id(String endCustomer_id) {
		this.endCustomer_id = endCustomer_id;
	}

	public String getCusGroup_id() {
		return cusGroup_id;
	}

	public void setCusGroup_id(String cusGroup_id) {
		this.cusGroup_id = cusGroup_id;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getEcGroup_id() {
		return ecGroup_id;
	}

	public void setEcGroup_id(String ecGroup_id) {
		this.ecGroup_id = ecGroup_id;
	}

	public String getLatest_expire() {
		return latest_expire;
	}

	public void setLatest_expire(String latest_expire) {
		this.latest_expire = latest_expire;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getMain_id() {
		return main_id;
	}

	public void setMain_id(String main_id) {
		this.main_id = main_id;
	}

	public String getEndCustomer_name() {
		return endCustomer_name;
	}

	public void setEndCustomer_name(String endCustomer_name) {
		this.endCustomer_name = endCustomer_name;
	}

	public String getMaterial_name() {
		return material_name;
	}

	public void setMaterial_name(String material_name) {
		this.material_name = material_name;
	}

	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}

	public String getDisti_branch() {
		return disti_branch;
	}

	public void setDisti_branch(String disti_branch) {
		this.disti_branch = disti_branch;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
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

	public String getCus_groupId() {
		return cus_groupId;
	}

	public String getForWho() {
		return forWho;
	}

	public void setForWho(String forWho) {
		this.forWho = forWho;
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

	public void setCus_groupId(String cus_groupId) {
		this.cus_groupId = cus_groupId;
	}

	public String getMyself() {
		return myself;
	}

	public void setMyself(String myself) {
		this.myself = myself;
	}

	public String getEndCustomer_name_city() {
		return endCustomer_name_city;
	}

	public void setEndCustomer_name_city(String endCustomer_name_city) {
		this.endCustomer_name_city = endCustomer_name_city;
	}

	public List<Dict> getCmsTbDictList() {
		return cmsTbDictList;
	}

	public void setCmsTbDictList(List<Dict> cmsTbDictList) {
		this.cmsTbDictList = cmsTbDictList;
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

	public IQuoteService getQuoteService() {
		return quoteService;
	}

	public void setQuoteService(IQuoteService quoteService) {
		this.quoteService = quoteService;
	}

	public String getCreate_userName() {
		return create_userName;
	}

	public void setCreate_userName(String create_userName) {
		this.create_userName = create_userName;
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
	}

	public IMessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(IMessageService messageService) {
		this.messageService = messageService;
	}

	public String getEcGroup_name() {
		return ecGroup_name;
	}

	public void setEcGroup_name(String ecGroup_name) {
		this.ecGroup_name = ecGroup_name;
	}

	public String getEquip_type() {
		return equip_type;
	}

	public void setEquip_type(String equip_type) {
		this.equip_type = equip_type;
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

	public Long getDr_detail_id() {
		return dr_detail_id;
	}

	public void setDr_detail_id(Long dr_detail_id) {
		this.dr_detail_id = dr_detail_id;
	}

	public void setDrLogList(List<DesignRegDetailLog> drLogList) {
		this.drLogList = drLogList;
	}

	public List<DesignRegDetailLog> getDrLogList() {
		return drLogList;
	}

	public String getIsTurnWin() {
		return isTurnWin;
	}

	public void setIsTurnWin(String isTurnWin) {
		this.isTurnWin = isTurnWin;
	}

	public String getDrType() {
		return drType;
	}

	public void setDrType(String drType) {
		this.drType = drType;
	}

	public String getDr_types() {
		return dr_types;
	}

	public void setDr_types(String dr_types) {
		this.dr_types = dr_types;
	}

	public String getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
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

	public String updateDrType() {

		this.setSuccessMessage("");
		this.setFailMessage("");

		DesignRegDetail detail = new DesignRegDetail();

		detail.setId(Long.valueOf(ids));
		detail.setDr_type(drType);

		detail.setLatest_userId(this.getUser().getUserId());
		DesignRegDetail drType = designRegService.getDRTypeByItmeValue(detail);
		detail.setDrtype_def(drType.getDrtype_def());
		detail.setDw_cal(drType.getDw_cal());
		int i = designRegService.updateDesignRegDetailDrType(detail);

		if (i > 0) {
			this.setSuccessMessage("Success !");
		} else {
			this.setFailMessage("Failure !");
		}
		return RESULT_MESSAGE;
	}

	private boolean isDisti() {

		boolean isDisti = false;
		Disti_branch db = new Disti_branch();

		db.setDisti_name(this.getUser().getUserName());

		List<Disti_branch> distiList = designRegService.getDistiBranchList(db);

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

		return isDisti;
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
		dr = new DesignReg();
		String userString = this.getUser().getLoginId();
		int n = userString.indexOf("@");
		if (n > -1) {
			dr.setCreate_userId(userString.substring(0, userString.indexOf("@")));
		} else {
			dr.setCreate_userId(userString);
		}
		return "toRemarkTxt";
	}

	public String updateRemark() {
		this.setFailMessage("");
		this.setSuccessMessage("");
		drd = new DesignRegDetail();
		if (StringUtils.isNotEmpty(remark) && StringUtils.isNotEmpty(remark.trim())) {
			try {
				drd.setRemark(Escape.unescape(remark));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		drd.setId(Long.valueOf(id));
		drd.setLatest_userId(this.getUser().getUserId());
		int i = designRegService.updateDrdRemark(drd);
		if (i > 0) {
			this.setSuccessMessage("Success !");
		} else {
			this.setFailMessage("failed !");
		}
		return RESULT_MESSAGE;
	}
}
