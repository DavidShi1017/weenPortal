package com.jingtong.platform.allUser.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jingtong.platform.account.service.IAccountService;
import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.allUser.service.IAllUserService;
import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.conpoint.service.IConpointService;
import com.jingtong.platform.customer.pojo.CustomerUser;
import com.jingtong.platform.customer.service.ICustomerService;
import com.jingtong.platform.framework.annotations.Decode;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.framework.mail.MailService;
import com.jingtong.platform.framework.util.EncryptUtil;
import com.jingtong.platform.framework.util.ParamsEncryptUtil;
import com.jingtong.platform.login.service.ILDAPService;
import com.jingtong.platform.org.pojo.Borg;
import com.jingtong.platform.org.service.IOrgService;
import com.jingtong.platform.post.pojo.EmpPost;
import com.jingtong.platform.post.service.IPostService;
import com.jingtong.platform.station.pojo.Station;
import com.jingtong.platform.station.service.IStationService;

/** 2018/11/15 Modify CMD Data to forward list */
public class AllUserAction extends BaseAction {

	/**
	 * 
	 */
	private static final Log logger = LogFactory.getLog(AllUserAction.class);
	private static final long serialVersionUID = -4645806566226562765L;
	@Decode
	private String orgId;
	private String bhxjFlag;
	private String loginId;
	@Decode
	private String userName;
	private String userId;
	private String phone;
	private String mobile;
	private int total;
	private List<AllUsers> userList;
	private IAllUserService allUserService;
	private String ids;
	private List<Station> stationList;
	private String stationId;
	private String stationName;
	private IStationService stationService;
	private String loginId4Check;
	private AllUsers allUsers;
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
	private String flag;// 标记 标记组织是否有重选过
	private String orgId4Update;// 页面有重复 orgId 所以用这个来传。。
	private String loginId4AD;
	/**
	 * 错误信息
	 */
	private String failMessage;

	/**
	 * 成功信息
	 */
	private String successMessage;

	private String stationNames;

	private IOrgService iOrgService;

	private String repassWd;

	private String orgStr;

	private ILDAPService LDAPService;
	private String logins;
	private boolean validate;

	private List<EmpPost> empPostList;

	private String orgId4Post;

	private IPostService postService;
	private StringResult stringResult;
	private String passport;
	private String name;
	private String customer_code;
	private ICustomerService customerService;
	private IAccountService accountService;
	
	 public IAccountService getAccountService() {
	    	return accountService;
	    }
	    
	    public void setAccountService(IAccountService accountService) {
	    	this.accountService =  accountService;
	    }

	/**
	 * 通讯录类似RTX
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchRtxPre() {
		return "rtx";
	}

	/**
	 * 通讯录人员信息
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "allUsers", include = { "userName", "sex", "orgName", "stationNames", "busMobilephone", "phone",
			"address", "email", "workFax" })
	public String userInfoInRtx() {
		allUsers = allUserService.getAllUsersByUserId(userId);
		Station station = new Station();
		station.setUserId(userId);
		List<Station> stationList = new ArrayList<Station>();
		stationList = stationService.searchStationUserMore(station);
		stationNames = "";
		if (stationList.size() != 0) {
			for (Station station2 : stationList) {
				stationNames += station2.getStationName() + ",";
			}
			stationNames = stationNames.substring(0, stationNames.length() - 1);
		}
		allUsers.setStationNames(stationNames);
		return JSON;
	}

	@PermissionSearch
	public String searchAddressBookPre() {
		AllUsers allUsers = this.getUser();
		orgId = allUsers.getOrgId();
		orgName = iOrgService.getorgname(Long.valueOf(orgId));
		return "searchAddressBookPre";
	}

	/**
	 * 老的版本 暂不用
	 * 
	 * @return
	 */
	@JsonResult(field = "userList", include = { "userId", "loginId", "userName", "userShowName", "userState",
			"haveMail", "posName", "positionTypeName", "orgId", "phone", "mobile", "email",
			"address" }, total = "total")
	public String searchAddressBook() {
		AllUsers allUser = new AllUsers();
		if (StringUtils.isNotEmpty(orgId)) {
			if ("C".equals(bhxjFlag)) {
				// String orgids = orgService.getFnAllChildStrOrg(orgId);
				// if (StringUtils.isNotEmpty(orgids)) {
				// allUser.setOrgIds(orgids.split(","));
			}
		} else {
			allUser.setOrgId(orgId);
		}
		if (StringUtils.isNotEmpty(loginId)) {
			allUser.setLoginId(loginId);
		}
		if (StringUtils.isNotEmpty(userName)) {
			allUser.setUserName(userName);
		}
		if (StringUtils.isNotEmpty(phone)) {
			allUser.setPhone(phone);
		}
		if (StringUtils.isNotEmpty(mobile)) {
			allUser.setMobile(mobile);
		}
		total = allUserService.searchAllUsersCount(allUser);
		if (total != 0) {
			userList = allUserService.searchAllUsers(allUser);
		}
		return JSON;
	}

	@PermissionSearch
	public String userManage() {
		AllUsers allUsers = this.getUser();
		orgId = allUsers.getOrgId();
		orgName = iOrgService.getorgname(Long.valueOf(orgId));
		return "userManage";
	}

	/**
	 * 人员管理页面数据 查询
	 * 
	 * @return
	 */
	@JsonResult(field = "userList", include = { "userId", "loginId", "userName", "userState", "orgId", "orgName",
			"mobile", "email", "address", "phone", "sex" }, total = "total")
	@PermissionSearch
	public String getUserInfoListForQuoteForward() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		userList = null;
		AllUsers allUser = new AllUsers();
		allUser.setStart(getStart());
		allUser.setEnd(getEnd());
		if (StringUtils.isNotEmpty(loginId)) {
			allUser.setLoginId(loginId);
		}
		if (StringUtils.isNotEmpty(userName)) {
			allUser.setUserName(userName);
		}
        // 2018/11/15 Modify Start
		total = customerService.searchAllUsersForForwardCount(allUser);
		if (total > 0) {
			userList = customerService.searchAllUsersForForward(allUser);
		}
	    // 2018/11/15 Modify End

		return JSON;
	}

	/**
	 * 人员管理页面数据 查询
	 * 
	 * @return
	 */
	@JsonResult(field = "userList", include = { "userId", "loginId", "userName", "userState", "orgId", "orgName",
			"mobile", "email", "address", "phone", "sex" }, total = "total")
	@PermissionSearch
	public String getUserInfoList() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		userList = null;
		AllUsers allUser = new AllUsers();
		allUser.setStart(getStart());
		allUser.setEnd(getEnd());
		if (StringUtils.isNotEmpty(orgId)) {
			if ("C".equals(bhxjFlag)) {
				allUser.setBhxjFlag(bhxjFlag);
			} else {
				allUser.setBhxjFlag("N");
			}
			allUser.setOrgId(orgId);
		} else {
			allUser.setOrgId(this.getUser().getOrgId());
		}
		if (StringUtils.isNotEmpty(loginId)) {
			allUser.setLoginId(loginId);
		}
		if (StringUtils.isNotEmpty(userName)) {
			allUser.setUserName(userName);
		}
//		if (StringUtils.isNotEmpty(customer_code)) {
//			allUser.setUserName(customer_code);
//		}
		total = allUserService.searchAllUsersCount(allUser);
		if (total != 0) {
			userList = allUserService.searchAllUsers(allUser);
		}
		return JSON;
	}

	/**
	 * 禁用人员账号 根据人员 EMP_ID
	 * 
	 * @return
	 */
	public String forbidden() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		boolean flag = true;
		AllUsers allUsers = new AllUsers();
		allUsers.setUserId(userId);
		allUsers.setUserState("N");
		allUsers.setLoginId(loginId4AD);
		if (validate) {
			flag = LDAPService.disableUser2Ad(allUsers);
		}
		if (flag) {
			StringResult result = allUserService.forbidden(allUsers);
			if (SUCCESS.equals(result.getCode())) {
				this.setSuccessMessage("Success!");
			} else {
				this.setFailMessage(result.getResult());
			}
		} else {
			this.setSuccessMessage("Failed!");

		}
		return RESULT_MESSAGE;
	}

	/**
	 * 启用人员账号
	 * 
	 * @return
	 */
	public String startup() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		boolean flag = true;
		AllUsers allUsers = new AllUsers();
		allUsers.setUserId(userId);
		allUsers.setUserState("Y");
		allUsers.setLoginId(loginId4AD);
		if (validate) {
			flag = LDAPService.enableUser2Ad(allUsers);
		}
		if (flag) {
			StringResult result = allUserService.forbidden(allUsers);
			if (SUCCESS.equals(result.getCode())) {
				accountService.deleteLoginTimes(allUsers.getLoginId());
				this.setSuccessMessage("Success!");
			} else {
				this.setFailMessage(result.getResult());
			}
		} else {
			this.setSuccessMessage("Failed!");
		}
		return RESULT_MESSAGE;
	}

	@PermissionSearch
	public String toCreateUser() {
		return "toCreateUser";
	}

	/**
	 * 人员删除，先判断是否分配了岗位
	 * 
	 * @return
	 */
	public String deleteUserByEmpId() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		AllUsers allUsers = new AllUsers();
		String[] id = ids.split(",");
		String[] login = logins.split(",");
		int count = 0;
		for (int i = 0; i < id.length; i++) {
			boolean flag = true;
			allUsers.setUserId(id[i]);
			allUsers.setLoginId(login[i]);
			String haveStation = stationService.getStationCountByEmpId(allUsers);
			if ("exist".equals(haveStation)) {
				this.setFailMessage("员工    " + allUserService.getEmpNameByUserId(id[i]) + "  还处于岗位上，请先解除岗位再做删除");
				return RESULT_MESSAGE;
			}
			if (validate) {
				flag = LDAPService.deleteUser2Ad(allUsers);
			}
			if (flag) {
				StringResult result = allUserService.deleteUserByEmpId(allUsers);
				if (IConpointService.ERROR.equals(result.getCode())) {
					this.setFailMessage(result.getResult());
				} else {
					count++;
				}
			}
		}
		this.setSuccessMessage("Success!");
		return RESULT_MESSAGE;
	}

	/**
	 * 根据人员组织，找到该组织下所有有编制可用的岗位
	 * 
	 * @return
	 */
	@JsonResult(field = "stationList", include = { "id", "stationId", "stationName", "orgName", "userName",
			"isMainStation" }, total = "total")
	@PermissionSearch
	public String getSelectedStationsJSON() {
		Station station = new Station();
		station = getSearchInfo(station);
		station.setStart(getStart());
		station.setEnd(getEnd());
		if (StringUtils.isNotEmpty(stationId) && StringUtils.isNotEmpty(stationId.trim())) {
			try {
				stationId = new String(this.getStationId().getBytes("ISO8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			station.setStationId(stationId.trim());
			station.setStationName(stationId.trim());
		}
		if (StringUtils.isNotEmpty(orgId) && StringUtils.isNotEmpty(orgId.trim())) {
			station.setOrgId(Long.valueOf(orgId));
		}
		total = stationService.getStationJsonListCountForSelect(station);
		if (total != 0) {
			stationList = stationService.getStationJsonListForSelect(station);
		} else {
			stationList = null;
		}
		return JSON;
	}

	/**
	 * 判断登录名（loginId）是否存在
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "stringResult", include = { "result", "code" })
	public String isLoginIdExist() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		stringResult = new StringResult();
		String message = "";
		if (StringUtils.isNotEmpty(loginId4Check)) {
			message = allUserService.getAllUserByLoginId(loginId4Check.toUpperCase());
		}
		stringResult.setCode(message);

		if ("exist".equals(message)) {
			// stringResult.setResult(loginId4Check + "aleady existed！");
			stringResult.setResult("Duplicated LoginId!");
		} else if ("unexist".equals(message)) {
			stringResult.setResult("This is a valid LoginId");
		}
		return JSON;
	}

	/**
	 * 重置密码(重置密码写配置文件里去）
	 * 
	 * @return
	 */
	public String resetPWd() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		boolean flag = true;
		AllUsers users = new AllUsers();
		if (StringUtils.isNotEmpty(userId)) {
			users.setUserId(userId);
			// users.setLoginId(allUsers.getLoginId());
		} else {
			this.setFailMessage("用户为空，请重新登录再修改！");
			return RESULT_MESSAGE;
		}
		try {
			users.setPassWd(EncryptUtil.md5Encry("123456"));
			users.setExpressly("123456");
			if (validate) {
				flag = LDAPService.modifyPassword2Ad(users);
			}
			if (flag) {
				StringResult result = allUserService.updatePwd(users);
				if (IConpointService.ERROR.equals(result.getCode())) {
					this.setFailMessage(result.getCode());
				} else {
					this.setSuccessMessage("Password has been reset to 123456");
				}
			} else {
				this.setFailMessage("Failed!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 重置密码(重置密码写配置文件里去）
	 * 
	 * @return
	 */
	public String resetPWdToKunnr() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		boolean flag = true;
		AllUsers users = new AllUsers();
		if (StringUtils.isNotEmpty(userId)) {
			users.setUserId(userId);
			users.setLoginId(userId);
		} else {
			this.setFailMessage("用户为空，请重新登录再修改！");
			return RESULT_MESSAGE;
		}
		try {
			users.setPassWd(EncryptUtil.md5Encry("123456"));
			users.setExpressly("123456");
			if (validate) {
				flag = LDAPService.modifyPassword2Ad(users);
			}
			if (flag) {
				StringResult result = allUserService.updatePwdToKunnr(users);
				if (IConpointService.ERROR.equals(result.getCode())) {
					this.setFailMessage(result.getCode());
				} else {
					this.setSuccessMessage("密码已经重置为" + repassWd);
				}
			} else {
				this.setFailMessage("密码重置失败.AD域账号密码重置失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 查找人员所分配的岗位
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchStationUser() {
		if (StringUtils.isNotEmpty(orgName) && StringUtils.isNotEmpty(orgName.trim())) {
			try {
				this.orgName = new String(orgName.trim().getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return "searchStationUser";
	}

	/**
	 * 查找人员所分配的岗位
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchNStationUser() {
		if (StringUtils.isNotEmpty(orgName) && StringUtils.isNotEmpty(orgName.trim())) {
			try {
				this.orgName = java.net.URLDecoder.decode(orgName, "UTF-8");
				/*
				 * this.orgName = new String(orgName.trim().getBytes("ISO8859-1"), "UTF-8");
				 */
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return "searchNStationUser";
	}

	/**
	 * 通讯录的岗位查看页面 只能查看，不能做修改的。
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchStationUser4Book() {
		if (StringUtils.isNotEmpty(orgName) && StringUtils.isNotEmpty(orgName.trim())) {
			try {
				this.orgName = new String(orgName.trim().getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return "searchStationUser4Book";
	}

	/**
	 * 添加人员次岗位，不变更人员组织
	 */
	public String addUserNStationById() {
		this.setFailMessage("");
		this.setSuccessMessage("");
		AllUsers allUsers = new AllUsers();
		allUsers.setUserId(userId);
		if (!StringUtils.isEmpty(ids) && !StringUtils.isEmpty(ids.trim())) {
			String[] stationIds = ids.split(",");
			for (int i = 0; i <= stationIds.length - 1; i++) {
				allUsers.setRoleIds(stationIds[i]);
				allUsers.setIsMainStation("N");
				BooleanResult booleanResultForStation = stationService.updateStationUser(allUsers);
				if (!booleanResultForStation.getResult()) {
					this.setFailMessage(booleanResultForStation.getCode());
				}
			}
		}
		this.setSuccessMessage("Success!");

		return RESULT_MESSAGE;
	}

	/**
	 * 为人员配置岗位 先判断组织有无变更 然后根据岗位对应的的station_user里的ID 去把人员的emp_id(userId)添加进去
	 * 
	 * @return
	 */
	public String addUserStationById() {
		this.setFailMessage("");
		this.setSuccessMessage("");
		boolean flag1 = true;
		AllUsers allUsers = new AllUsers();
		allUsers.setUserId(userId);
		if (!StringUtils.isEmpty(flag) && !StringUtils.isEmpty(flag.trim())) {
			allUsers.setOrgId(orgId4Update);
			if (validate) {
				Borg oldOrg = iOrgService.getOrgByUserId(userId);
				flag1 = LDAPService.changeGroup(allUsers, oldOrg.getOrgId().toString());
			}
			if (flag1) {
				int n = allUserService.updateAllUserOrg(allUsers);
				if (n != 1) {
					this.setFailMessage("组织变更失败");
				}
			} else {
				this.setFailMessage("组织变更失败.AD域账号组织变更失败");
				return RESULT_MESSAGE;
			}
		}
		if (!StringUtils.isEmpty(ids) && !StringUtils.isEmpty(ids.trim())) {
			String[] stationIds = ids.split(",");
			for (int i = 0; i <= stationIds.length - 1; i++) {
				allUsers.setIds(stationIds[i]);
				BooleanResult booleanResultForStation = stationService.updateStationUser(allUsers);
				if (!booleanResultForStation.getResult()) {
					this.setFailMessage(booleanResultForStation.getCode());
				}
			}
		}
		this.setSuccessMessage("Success!");
		return RESULT_MESSAGE;
	}

	/**
	 * 删除人员岗位 可批量删除
	 * 
	 * @return
	 */
	public String deleteUserStationById() {
		this.setFailMessage("");
		this.setSuccessMessage("");
		Station station = new Station();
		station.setUserId(userId);
		int count = 0;
		if (!StringUtils.isEmpty(ids) && !StringUtils.isEmpty(ids.trim())) {
			String[] stationIds = ids.split(",");
			for (int i = 0; i <= stationIds.length - 1; i++) {
				station.setId(Long.valueOf(stationIds[i]));
				int n = stationService.deleteStationUserById(station);
				if (n == 1) {
					count++;
				} else {
					this.setFailMessage("Failed!");
				}
			}
		}
		this.setSuccessMessage("Success!");
		return RESULT_MESSAGE;
	}

	@PermissionSearch
	public String toUpdatePassPre() throws Exception {
		this.email = ParamsEncryptUtil.Encrypt(this.email);
		this.successMessage = null;
		this.failMessage = null;
		return "toUpdatePassPre";
	}

	public String updatePass() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		boolean flag = true;
		AllUsers user = new AllUsers();
		user.setEmail(email);
		try {
			user.setPassWd(EncryptUtil.md5Encry(password));
		} catch (Exception e) {
			e.printStackTrace();
			user.setPassWd("");
		}
		List<AllUsers> u = allUserService.searchAllUsers(user);
		if (u != null) {
			if (validate) {
				u.get(0).setExpressly(password);
				flag = LDAPService.modifyPassword2Ad(u.get(0));
			}
			if (flag) {
				BooleanResult result = allUserService.updateAllUser(user, "0");
				if (IConpointService.ERROR.equals(result.getCode())) {
					this.setFailMessage("Failed！");
				} else {
					this.setSuccessMessage("Success！");
				}
			} else {
				this.setFailMessage("Failed");
			}
		}
		return RESULT_MESSAGE;

	}

	/****
	 * 首次登陆修改密码
	 * 
	 * @return
	 */
	public String updateFristPass() {
		boolean flag = true;
		AllUsers user = new AllUsers();
		user.setLoginId(loginId);
		user.setIsFirst("1");
		user.setStart(0);
		user.setEnd(10);
		try {
			user.setPassWd(EncryptUtil.md5Encry(password));
		} catch (Exception e) {
			e.printStackTrace();
			user.setPassWd("");
		}
		List<AllUsers> u = allUserService.searchAllUsers(user);

		passport = u.get(0).getLoginId();
		name = u.get(0).getUserName();

		if (u != null) {
			if (validate) {
				u.get(0).setExpressly(password);
				flag = LDAPService.modifyPassword2Ad(u.get(0));
			}
			if (flag) {
				BooleanResult result = allUserService.updateAllUser(user, "1");

				if (IConpointService.ERROR.equals(result.getCode())) {
					this.setFailMessage("Failed!");
				} else {
					this.setSuccessMessage("Success!");
				}
			} else {
				this.setFailMessage("Failed!");
			}
		}
		return "updateFristPass";
	}

	@PermissionSearch
	public String sendMailPre() {
		if (StringUtils.isNotEmpty(loginId) && StringUtils.isNotEmpty(loginId.trim())) {
			try {
				loginId = new String(loginId.trim().getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(loginId, e);
			}
		}
		this.successMessage = null;
		this.failMessage = null;
		this.email = null;
		return "sendMailPre";
	}

	@JsonResult(field = "userList", include = { "loginId", "userName", "email" }, total = "total")
	@PermissionSearch
	public String getEmailJsonList() {
		AllUsers allUser = new AllUsers();
		allUser.setStart(getStart());
		allUser.setEnd(getEnd());
		if (StringUtils.isNotEmpty(loginId) && StringUtils.isNotEmpty(loginId.trim())) {
			allUser.setLoginId(loginId.trim());
		}
		total = allUserService.getEmpCount(allUser);
		if (total != 0) {
			userList = allUserService.getEmpList(allUser);
		} else {
			userList = null;
		}
		return JSON;
	}

	public String creatUser() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		boolean flag = true;
		String message = customerService.getAllUserByLoginId(allUsers.getLoginId().toUpperCase());
		if ("exist".equals(message)) {
			this.setFailMessage("User" + allUsers.getLoginId() + "already exist!");
			return RESULT_MESSAGE;
		}
		try {
			allUsers.setUserState("Y");
			allUsers.setExpressly(allUsers.getPassWd());
			allUsers.setPassWd(EncryptUtil.md5Encry(allUsers.getPassWd()));
			allUsers.setExpressly(this.getUser().getUserId());
			if (validate) {
				flag = LDAPService.addUser2Ad(allUsers);
			}
			if (flag) {
				BooleanResult booleanResult = allUserService.createUser(allUsers);
				if (!booleanResult.getResult()) {
					this.setFailMessage(booleanResult.getCode());
				} else {
					if (!StringUtils.isEmpty(roleIds) && !StringUtils.isEmpty(roleIds.trim())) {
						String[] ids = roleIds.split(",");
						for (int i = 0; i <= ids.length - 1; i++) {
							allUsers.setUserId(booleanResult.getCode());
							allUsers.setIds(ids[i]);
							allUsers.setIsMainStation("Y");
							BooleanResult booleanResultForStation = stationService.updateStationUser(allUsers);
							if (!booleanResultForStation.getResult()) {
								this.setFailMessage(booleanResultForStation.getCode());
							}
						}
					}
					this.setSuccessMessage("Success, whether to close the current page？");
					CustomerUser cusUser = new CustomerUser();
					cusUser.setLoginId(allUsers.getLoginId());
					cusUser.setCreate_user(this.getUser().getUserId());
					customerService.setCreateUserForUser(cusUser);
					// 若是客户则更改客户编码
					if (customer_code != null && (!("".equals(customer_code)))) {
						cusUser.setCustomer_code(customer_code);
						customerService.setCusomerSignForUser(cusUser);
						customerService.setCreateUserForUser(cusUser);
						customerService.bindDistiToThisUser(cusUser);
					}
				}
			} else {
				this.setSuccessMessage("Failed!");
			}
		} catch (Exception e) {
			logger.error(e);
			this.setFailMessage("Failed!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 跳转到用户信息修改页面
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toUpdateUserInfo() {
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ids = ids;
		allUsers = allUserService.getAllUsersByUserId(ids);
//		this.userId = ids;
//		Station station = new Station();
//		station.setUserId(ids);
//		station.setIsMainStation("Y");
//		List<Station> stationList = new ArrayList<Station>();
//		stationList = stationService.searchStationUserMore(station);
//		roleIds = "";
//		stationNames = "";
//		if (stationList.size() != 0) {
//			for (Station station2 : stationList) {
//				roleIds += station2.getId() + ",";
//				stationNames += station2.getStationName() + ",";
//			}
//			roleIds = roleIds.substring(0, roleIds.length() - 1);
//			stationNames = stationNames.substring(0, stationNames.length() - 1);
//		}
		return "updateUserInfo";
	}

	public String updateUserInfo() {
		boolean flag = true;
		this.setSuccessMessage("");
		this.setFailMessage("");
		allUsers.setUserId(userId);
		allUsers.setExpressly(this.getUser().getUserId());
		Borg oldOrg = iOrgService.getOrgByUserId(userId);
		if (allUsers.getOrgId() != null && oldOrg.getOrgId() != null
				&& !allUsers.getOrgId().equals(oldOrg.getOrgId().toString())) {
			if (validate) {
				flag = LDAPService.updateUser2Ad(allUsers, oldOrg.getOrgId().toString());
			}
		} else {
			if (validate) {
				flag = LDAPService.updateUser2Ad(allUsers, "");
			}
		}
		if (flag) {
			BooleanResult booleanResult = allUserService.updateUserInfo(allUsers);
			if (!booleanResult.getResult()) {
				this.setFailMessage("Failed!");
			}
			this.setSuccessMessage("Success!");
			CustomerUser cusUser = new CustomerUser();
			cusUser.setLoginId(allUsers.getLoginId());
			cusUser.setModify_user(this.getUser().getUserId());
			int n = customerService.setModifyUserForUser(cusUser);
			/*
			 * if (!StringUtils.isEmpty(roleIds) && !StringUtils.isEmpty(roleIds.trim())) {
			 * String[] ids = roleIds.split(","); for (int i = 0; i <= ids.length - 1; i++)
			 * { allUsers.setUserId(userId); allUsers.setIsMainStation("Y");
			 * allUsers.setIds(ids[i]); BooleanResult booleanResultForStation =
			 * stationService .updateStationUser(allUsers); if
			 * (!booleanResultForStation.getResult()) {
			 * this.setFailMessage(booleanResultForStation.getCode()); } } }
			 */
		} else {
			this.setFailMessage("Failed!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	public String updatePwd() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		boolean flag = true;
		try {
			AllUsers user = new AllUsers();
			if (StringUtils.isNotEmpty(userId)) {
				user.setUserId(userId);
				user.setLoginId(allUsers.getLoginId());
			} else {
				this.setFailMessage("用户为空，请重新登录再修改！");
				return RESULT_MESSAGE;
			}
			user.setOrgId(allUsers.getOrgId());
			user.setPassWd(EncryptUtil.md5Encry(repassWd));
			user.setExpressly(repassWd);
			if (validate) {
				flag = LDAPService.modifyPassword2Ad(user);
			}
			if (flag) {
				StringResult stringResult = allUserService.updatePwd(user);
				if (IConpointService.ERROR.equals(stringResult.getCode())) {
					this.setFailMessage("Failed!");
				} else {
					this.setSuccessMessage("Success!");
				}
			} else {
				this.setFailMessage("Failed!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RESULT_MESSAGE;
	}

	@PermissionSearch
	public String sendTenderMail() throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		AllUsers allUser = new AllUsers();
		if (StringUtils.isNotEmpty(loginId) && StringUtils.isNotEmpty(loginId.trim())) {// 邮件收件人不为空loginId
			allUser.setLoginId(loginId.trim());
			userList = allUserService.getEmpList4Code(allUser);
			if (userList.size() != 0) {
				for (AllUsers allUser1 : userList) {
					String userNumber = "<br>&nbsp;&nbsp;您的姓名为：" + allUser1.getUserName()
							+ "&nbsp;&nbsp;<br>&nbsp;&nbsp;" + "您的登陆账号为：" + allUser1.getLoginId()
							+ "&nbsp;&nbsp;<br>&nbsp;&nbsp;请点击修改:" + "<a href='" + env.getProperty("appUrl")
							+ "/allUserAction1/allUserAction1!toUpdatePassPre." + "jspa?email="
							+ ParamsEncryptUtil.Encrypt(allUser1.getEmail()) + "'>点击此链接修改</a>"; // 供应商登陆信息
					String contents = "尊敬的用户" + userNumber + "<br>";
					if (null != allUser1.getEmail()) {
						MailService mail = new MailService(smtpServer, from, displayName, emailaddress, emailpassword,
								allUser1.getEmail(), "密码修改确认函", contents);// 邮件做成
						map = mail.send(); // 邮件发送
					} else {
						this.failMessage = "邮件发送失败,没有找到该账号的邮箱地址，请确认账号是否正确！<br>如账号是准确的，请联系管理员，确认邮箱信息是否维护！";
						return "warnMegs";
					}
					if ("success".equals(map.get("state"))) {
						this.successMessage = /* map.get("message") */"尊敬的用户" + allUser1.getUserName()
								+ "，您好：<br>邮件已发送至您邮箱<font size=2 color=blue>" + allUser1.getEmail() + "</font>，注意查收！";
					} else {
						this.failMessage = "邮件发送失败,请联系系统管理员！";
					}
				}
			} else {
				this.failMessage = "系统内无该用户,请确认账号是否正确！";
			}
		}
		return "warnMegs";
	}

	/**
	 * 查看人员详细信息
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchAllUserInfo() {
		allUsers = allUserService.getAllUser(userId);

		if (allUsers != null) {

			initOrgStr(Long.parseLong(allUsers.getOrgId()));

			// Bposition bposition = new Bposition();
			// bposition.setEmpId(Long.valueOf(userId));
			// bposition.setUseState("B");
			//
			// List<Bposition> pList = positionService.getPosition(bposition);
			//
			// if (pList != null && pList.size() > 0) {
			// StringBuffer str = new StringBuffer();
			// for (Bposition p : pList) {
			// if (str.length() != 0) {
			// str.append("*");
			// }
			// str.append(p.getPosId()).append(",").append(p.getPosName());
			// }
			// imgPosStr = str.toString();
			// }
		}

		return "searchAllUserInfo";
	}

	/**
	 * 查询向上组织结构
	 * 
	 * @param orgId
	 */
	@PermissionSearch
	private void initOrgStr(Long orgId) {
		StringBuffer o = new StringBuffer();

		List<Borg> orgs = iOrgService.getAllParentOrgs(orgId);
		if (orgs != null && orgs.size() > 0) {
			for (Borg org : orgs) {
				if (o.length() != 0) {
					o.append("\\");
				}
				o.append(org.getOrgName());
			}

			orgStr = o.toString();
		}
	}

	/**
	 * 根据组织树选择人员(跳转)
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toShowUserByOrgId() {
		return "toShowUserByOrgIdMain";
	}

	/**
	 * 根据组织ID查找人员信息
	 * 
	 * @return
	 */
	@JsonResult(field = "userList", include = { "userId", "posName", "userName", "orgId" })
	@PermissionSearch
	public String getEmpListByOrgId() {
		if (orgId != null && !"".equals(orgId)) {
			userList = allUserService.getEmpListByOrgId(orgId);
		}
		return JSON;
	}

	/**
	 * 查看个人信息非管理员权限
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toViewOfUserInfo() {
		AllUsers aUsers = this.getUser();
		userId = aUsers.getUserId();
		allUsers = allUserService.getAllUsersByUserId(userId);
		Station station = new Station();
		station.setUserId(userId);
		List<Station> stationList = new ArrayList<Station>();
		stationList = stationService.searchStationUserMore(station);
		roleIds = "";
		stationNames = "";
		if (stationList.size() != 0) {
			for (Station station2 : stationList) {
				roleIds += station2.getId() + ",";
				stationNames += station2.getStationName() + ",";
			}
			roleIds = roleIds.substring(0, roleIds.length() - 1);
			stationNames = stationNames.substring(0, stationNames.length() - 1);
		}
		return "toViewOfUserInfo";
	}

	/**
	 * 获取某一组织下未被分配的职务
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "empPostList", include = { "empPostId", "empPostName", "orgId", "orgName" }, total = "total")
	public String getEmpPostListJSON() {
		if (orgId4Post != null && !"".equals(orgId4Post)) {
			total = postService.getEmpPostCount(orgId4Post);
			if (total != 0) {
				empPostList = postService.getEmpPostList(orgId4Post);
			}
		} else {
			empPostList = null;
		}
		return JSON;
	}

	/**
	 * 查看经销商个人信息非管理员权限
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toViewOfKunnrUserInfo() {
		AllUsers aUsers = this.getUser();
		userId = aUsers.getUserId();
		allUsers = allUserService.getKunnrUsersByUserId(userId);

		return "toViewOfKunnrUserInfo";
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

	public List<AllUsers> getUserList() {
		return userList;
	}

	public void setUserList(List<AllUsers> userList) {
		this.userList = userList;
	}

	public IAllUserService getAllUserService() {
		return allUserService;
	}

	public void setAllUserService(IAllUserService allUserService) {
		this.allUserService = allUserService;
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

	public AllUsers getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(AllUsers allUsers) {
		this.allUsers = allUsers;
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

	@Override
	public String getFailMessage() {
		return failMessage;
	}

	@Override
	public void setFailMessage(String failMessage) {
		this.failMessage = failMessage;
	}

	@Override
	public String getSuccessMessage() {
		return successMessage;
	}

	@Override
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	@Override
	public Properties getEnv() {
		return env;
	}

	@Override
	public void setEnv(Properties env) {
		this.env = env;
	}

	public String getStationNames() {
		return stationNames;
	}

	public void setStationNames(String stationNames) {
		this.stationNames = stationNames;
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

	public IOrgService getiOrgService() {
		return iOrgService;
	}

	public void setiOrgService(IOrgService iOrgService) {
		this.iOrgService = iOrgService;
	}

	public String getOrgStr() {
		return orgStr;
	}

	public void setOrgStr(String orgStr) {
		this.orgStr = orgStr;
	}

	public String getRepassWd() {
		return repassWd;
	}

	public void setRepassWd(String repassWd) {
		this.repassWd = repassWd;
	}

	public ILDAPService getLDAPService() {
		return LDAPService;
	}

	public void setLDAPService(ILDAPService lDAPService) {
		LDAPService = lDAPService;
	}

	public String getLogins() {
		return logins;
	}

	public void setLogins(String logins) {
		this.logins = logins;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public List<EmpPost> getEmpPostList() {
		return empPostList;
	}

	public void setEmpPostList(List<EmpPost> empPostList) {
		this.empPostList = empPostList;
	}

	public String getOrgId4Post() {
		return orgId4Post;
	}

	public void setOrgId4Post(String orgId4Post) {
		this.orgId4Post = orgId4Post;
	}

	public IPostService getPostService() {
		return postService;
	}

	public void setPostService(IPostService postService) {
		this.postService = postService;
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

	public StringResult getStringResult() {
		return stringResult;
	}

	public void setStringResult(StringResult stringResult) {
		this.stringResult = stringResult;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCustomer_code() {
		return customer_code;
	}

	public void setCustomer_code(String customer_code) {
		this.customer_code = customer_code;
	}

	public ICustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomerService customerService) {
		this.customerService = customerService;
	}
}
