package com.jingtong.platform.account.action;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.jingtong.platform.account.pojo.BudgetNumber;
import com.jingtong.platform.account.pojo.TravelDetail;
import com.jingtong.platform.account.pojo.TravelTotal;
import com.jingtong.platform.account.service.IAccountService;
import com.jingtong.platform.account.service.ITravelAppService;
import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.framework.util.DateUtil;
import com.jingtong.platform.framework.util.JavaBeanXMLUtil;
import com.jingtong.platform.framework.util.XMLInfo;
import com.jingtong.platform.org.pojo.Borg;
import com.jingtong.platform.org.service.IOrgService;
import com.jingtong.platform.webservice.pojo.UserUtil;
import com.jingtong.platform.webservice.resps.JsonUtil;
import com.jingtong.platform.webservice.service.IWebService;
import com.jingtong.platform.wfe.pojo.ProEventDetail;
import com.jingtong.platform.wfe.pojo.ProEventTotal;
import com.jingtong.platform.wfe.service.IEventService;

public class TravelAppAction  extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(AccountAction.class);

	private ITravelAppService travelAppService;
	private IWebService webService;
	private IEventService eventService;
	private IOrgService iOrgService;
	private IAccountService accountService;



	private String userId;
	private String userName;
	private String orgId;
	private String orgName;
	private IOrgService orgService;
	
	
	private String totalMoney;
	private String type;//区分是核销还是申请
	private String flag;//区分是职能差旅还是销售差旅
	private String appUrl;//流程中用到的参数
	private String role;//流程发起人角色
	
	private String eventId;//事务Id
	private String  nextUserId;//下一个处理人
	private String  title;//事务标题

	private File[] upload;
	private String[] uploadFileName;
	private String xmlFilePath; // Xml文件路径
	

	private String detailJsonStr; // 明细Json字符串
	private UserUtil userUtil; // 下个处理人列表
	private TravelTotal  travelTotal;
	
	
	private List<TravelTotal>  travelTotalList; 
	private String status;
	private String payee;
	private String costType;
	private Date startDate;
	private Date endDate;
	private int total;
	private String  transactionId;
	private String travelId;
	private String auditMoney;
	private String bhxjFlag;
	private String subFolders;
	private String writeEventId;
	private String writeEvent;
	private String writeStatus;
	private String searchUserRole;
	private ProEventTotal proEventTotal;
	private List<ProEventDetail>  eventDetailList;
	
	private BooleanResult executeResult;// 事务处理结束返回信息

	private String curStaId;
	private String budgetNumberNum;
	private String bnumberId;
	private BudgetNumber budgetNumber;
	private String costOrgId;
	
	private String year;
	private String month;
	private static String key = "fix_znfysq";
	
	/**
	 * 进入差旅申请页面
	 * @return
	 */
	
	public String travelApp(){
		userId = getUser().getUserId();
		userName = getUser().getUserName();
		Borg org = orgService.getOrgByOrgId(getUser().getOrgId());
		orgId = org.getOrgId().toString();
		orgName = org.getOrgName();
		return "travelApp";
	}
	
	
	@PermissionSearch
	@JsonResult(field = "userUtil", include = { "processInstanceId", "result" })
	public String selectNexUser() {
		Double money = 0D;
		if (StringUtils.isNotEmpty(totalMoney)) {
			money = new Double(totalMoney);
		}
		Object[] res = new Object[4];
		res[0] = key;
		res[1] = this.getUser().getUserId();
		res[2] = "role,money";
		res[3] =  "start"+","+money;// 事务最后一步同意时业务操作
		userUtil = webService.startWorkflowFix(res);
		return JSON;
	}
	
	
	/**
	 * 获取下个处理人并处理
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String startWorkflowFix() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		
		List<TravelDetail> detailList = JsonUtil.getDTOList(detailJsonStr,TravelDetail.class);
		Object[] res = new Object[7];
		res[0] = eventId;
		res[1] = this.getUser().getUserId();
		res[2] = nextUserId;
		res[3] = travelTotal.getTitle();
		res[4] = appUrl + "/travelApp/travelAppAction!readTravelAppTr.jspa";
		res[5] = key;
		res[6] = "";
		
		if ("update".equals(type)) { // 核销的时候
			String result = webService.processWorkflowFix(res);
			if ("success".equals(result)) {
				// 保存附件
				if (upload != null && upload.length > 0) {
					eventService.processAttachments(upload, uploadFileName,Long.valueOf(eventId),String.valueOf(new Date().getTime()), key);
				}
				travelTotal.setWriteEventId(eventId);
				if("1".equals(travelTotal.getPayType())){
					travelTotal.setPaycash(travelTotal.getAuditMoney());
					travelTotal.setDkMoney(new BigDecimal(0));
				}else {
					travelTotal.setPaycash(new BigDecimal(0));
					travelTotal.setDkMoney(travelTotal.getAuditMoney());
				}
				travelTotal.setChouqian(new BigDecimal(0));
				travelTotal.setTravelDetailList(detailList);
				travelTotal.setCreateUserId(this.getUser().getUserId());
				BooleanResult strResult = travelAppService.updateTravel(travelTotal);
				
				if (strResult.getResult()) { // 数据库修改成功
					if (!JavaBeanXMLUtil.JavaBean2XML(xmlFilePath + "/" + eventId+ ".xml", travelTotal, getUser().getUserId(), getUser().getUserName(), null)) {
						this.setFailMessage("写入XML出错");
						webService.cancelEvent(eventId);
						return RESULT_MESSAGE;
					}
					this.setSuccessMessage("事务启动成功,事务号为：" + eventId);
					// 写XML文件
				} else {
					webService.cancelEvent(eventId);
					this.setFailMessage(strResult.getCode());
				}
				
			} else {
				this.setFailMessage("启动失败");
			}
		} else {
			String result = webService.processWorkflowFix(res);
			if ("success".equals(result)) {
				// 保存附件
				if (upload != null && upload.length > 0) {
					eventService.processAttachments(upload, uploadFileName,Long.valueOf(eventId),String.valueOf(new Date().getTime()), key);
				}

				// 职能费用提报
				travelTotal.setTravelDetailList(detailList);
				travelTotal.setTransactionId(eventId);
				travelTotal.setCreateUserName(this.getUser().getUserName());
				travelTotal.setCreateUserId(this.getUser().getUserId());
				travelTotal.setMonth(DateUtil.getMonth()+"");
				travelTotal.setYear(DateUtil.getYear()+"");
				StringResult strResult = travelAppService.saveTravelTotal(travelTotal); // 保存报销单进表
			// 写XML文件
				if (!JavaBeanXMLUtil.JavaBean2XML(xmlFilePath + "/" + eventId+ ".xml", travelTotal, getUser().getUserId(), getUser().getUserName(), null)) {
					this.setFailMessage("写入XML出错");
					webService.cancelEvent(eventId);
					return RESULT_MESSAGE;
				}
				if ("success".equals(strResult.getCode())) {
					this.setSuccessMessage("事务启动成功,事务号为：" + eventId);
				} else {
					webService.cancelEvent(eventId);
					this.setFailMessage(strResult.getResult());
				}
			} else {
				this.setFailMessage("启动失败");
			}	
		}
		return RESULT_MESSAGE;
	}

	public String searchTravel(){
		return "searchTravel";
	}
	
	
	public String searchAllTravel(){
		return "searchAllTravel";
	}
	
	

	@SuppressWarnings("deprecation")
	@PermissionSearch
	@JsonResult(field = "travelTotalList", include = { "travelId","transactionId", "title", "payee", "costOrgName", "totalMoney", "auditMoney", "payType",
			"createDate", "status", "writeEventId", "writeStatus","writeEvent" }, total = "total")
	public String searchTravelJson() {
		 travelTotal = new TravelTotal();
		 travelTotal.setStart(getStart());
		 travelTotal.setEnd(getEnd());
		 	
		try {
			if("all".equals(searchUserRole)){
				if (StringUtils.isNotEmpty(writeEventId)&& StringUtils.isNotEmpty(writeEventId.trim())) {
					travelTotal.setWriteEventId(writeEventId.trim());
				}
				if (StringUtils.isNotEmpty(writeEvent)
						&& StringUtils.isNotEmpty(writeEvent.trim())) {
					travelTotal.setWriteEvent(writeEvent.trim());
				}
				if (StringUtils.isNotEmpty(writeStatus)
						&& StringUtils.isNotEmpty(writeStatus.trim())) {
					travelTotal.setWriteStatus(writeStatus.trim());
				}
				if (StringUtils.isNotEmpty(orgId)) {
					if ("C".equals(bhxjFlag)) {
						String orgids = iOrgService.getFnAllChildStrOrg(orgId);
						if (StringUtils.isNotEmpty(orgids)) {
							travelTotal.setOrgIds(orgids.split(","));
						}
					} else {
						travelTotal.setOrgId(orgId);
					}
				} 
					travelTotal.setStatus("2");
			}else{
				 if (StringUtils.isNotEmpty(status)&& StringUtils.isNotEmpty(status.trim())) {
						travelTotal.setStatus(status.trim());
				 }
//				 Borg org = orgService.getOrgByOrgId(getUser().getOrgId());
//					orgName = org.getOrgName();
//				if(orgName.contains("办事处")&&"1".equals(flag)){
				 AllUsers roleUsers=this.getUser();
				 if(accountService.getUserRoles(roleUsers, "banshichu")){
						travelTotal.setOrgId(this.getUser().getOrgId());
				 }else{
					 travelTotal.setCreateUserId(this.getUser().getUserId());
				 }
			}
			
			if (StringUtils.isNotEmpty(eventId)
					&& StringUtils.isNotEmpty(eventId.trim())) {
				travelTotal.setEventId(eventId.trim());
			}
			if (StringUtils.isNotEmpty(title)
					&& StringUtils.isNotEmpty(title.trim())) {
				travelTotal.setTitle(title.trim());
			}
			
			if (StringUtils.isNotEmpty(payee)
					&& StringUtils.isNotEmpty(payee.trim())) {
				travelTotal.setPayee(payee.trim());
			}
			if (startDate != null) {
				travelTotal.setStartDate(startDate);
			}
			if (endDate != null) {
				travelTotal.setEndDate(endDate);
			}
			total = travelAppService.searchTravelCount(travelTotal);
			if (total != 0) {
				travelTotalList = travelAppService.searchTravel(travelTotal);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		
		return JSON;
	}
	
	//差旅费用申请执行完毕执行
		@JsonResult(field = "executeResult", responseFormat = "jsonp")
	  public String executeTravelWtr(){
			BooleanResult result = new BooleanResult();
			travelTotal = new TravelTotal();
			if (StringUtils.isNotEmpty(eventId)&& StringUtils.isNotEmpty(subFolders)) {
				String pathFile = xmlFilePath + File.separator + subFolders
						+ File.separator + eventId + ".xml";
				XMLInfo info = JavaBeanXMLUtil.XML2JavaBean(pathFile, travelTotal);
				if (info != null) {
					travelTotal = (TravelTotal) info.getObject();
				}
				try {
					result=travelAppService.exitTravelTotal(travelTotal);
				} catch (Exception e) {
					logger.error(e);
					result.setResult(false);
					result.setCode(result.getCode() + "\n" + "数据保存数据库失败.请联系系统管理员");
				}
			setExecuteResult(result);
		}
			return JSON;
	   }

	public String travelWta(){
		travelTotal = new TravelTotal();
		userId = getUser().getUserId();
		userName = getUser().getUserName();
		Borg org = orgService.getOrgByOrgId(getUser().getOrgId());
		orgId = org.getOrgId().toString();
		orgName = org.getOrgName();
		travelTotal = travelAppService.searchTraveltotalByTravelId(travelId, transactionId);
		return "travelWta";
	}
	
	// 事物拒绝的时候调用
	@JsonResult(field = "executeResult", responseFormat = "jsonp")
	public String refuseTravelApp() {
		BooleanResult result = null;
		travelTotal = new TravelTotal();
		if (StringUtils.isNotEmpty(eventId)) {
			try {
				travelTotal.setEventId(eventId);
				result = travelAppService.refuseTravelTr(travelTotal);
			} catch (Exception e) {
				logger.error(e);
				result.setResult(false);
				result.setCode(result.getCode() + "\n" + "数据保存数据库失败.请联系系统管理员");
			}
		}
		setExecuteResult(result);
		return JSON;
	}
	
	
	public String readTravelAppTr() {
		travelTotal = new TravelTotal();
		if (StringUtils.isNotEmpty(eventId)&& StringUtils.isNotEmpty(subFolders)) {
			String pathFile = xmlFilePath + File.separator + subFolders
					+ File.separator + eventId + ".xml";
			XMLInfo info = JavaBeanXMLUtil.XML2JavaBean(pathFile, travelTotal);
			if (info != null) {
				travelTotal = (TravelTotal) info.getObject();
			}
			/*if("start".equals(curStaId)){
				try {
					budgetNumber=getbNumber(travelTotal);
					travelTotal.setBudgetNumberBalance(budgetNumber.getBudget_money().add(travelTotal.getTotalMoney()));
					travelTotal.setOldTotalMoney(travelTotal.getTotalMoney());
				} catch (Exception e) {
					logger.error(TravelAppAction.class,e);
					travelTotal.setBudgetNumberBalance(travelTotal.getTotalMoney());
				}
			}*/
		}
		return "readTravelAppTr";
	}
	
	public String readTravelWtrTr() {
		travelTotal = new TravelTotal();
		if (StringUtils.isNotEmpty(eventId)&& StringUtils.isNotEmpty(subFolders)) {
			String pathFile = xmlFilePath + File.separator + subFolders
					+ File.separator + eventId + ".xml";
			XMLInfo info = JavaBeanXMLUtil.XML2JavaBean(pathFile, travelTotal);
			if (info != null) {
				travelTotal = (TravelTotal) info.getObject();
				
			}
		}
		return "readTravelWtrTr";
	}
	
	/**
	 * 查看明细
	 * @return
	 */
	public String travelDetail() {
		travelTotal = travelAppService.searchTraveltotalByTravelId(travelId, eventId);
		return "travelDetail";
	}
	
	/**
	 * 打印
	 * @return
	 */
	public String print() {
		travelTotal = travelAppService.searchTraveltotalByTravelId(travelId, eventId);
		proEventTotal = accountService.getEventTotalById(Long.valueOf(eventId));
		if (proEventTotal == null) {
			return ERROR;
		}
		eventDetailList = accountService.getEventDetailListAndSort(Long.valueOf(eventId));

		return "printTravel";
	}
	
	
	/**
	 * 修改核销信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String modifyTravelTrForm() {
		List<TravelDetail> detailList = JsonUtil.getDTOList(detailJsonStr,
				TravelDetail.class);
		if (detailList!=null&&detailList.size()!=0) {
			// 初始化
			travelTotal.setTravelDetailList(detailList);
			travelTotal.setOperatorId(this.getUser().getUserId());
			BooleanResult strResult = travelAppService.updateTravel(travelTotal);
			travelTotal = travelAppService.searchTraveltotalByTravelId(travelTotal.getTravelId().toString(), null);
			if (strResult.getResult()) {
				// 写XML文件
				if (!JavaBeanXMLUtil.JavaBean2XML(xmlFilePath + "/"+ travelTotal.getWriteEventId() + ".xml", travelTotal,
						getUser().getUserId(), getUser().getUserName(), subFolders)) {
					this.setFailMessage("写入XML出错");
					return RESULT_MESSAGE;
				}
			}
			this.setSuccessMessage("操作成功!");
		} else {
			this.setFailMessage("操作失败!");
		}

		return RESULT_MESSAGE;
	}
	/**
	 * 修改申请信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String modifyTravelForm() {
		List<TravelDetail> detailList = JsonUtil.getDTOList(detailJsonStr,
				TravelDetail.class);
		if (detailList!=null&&detailList.size()!=0) {
			// 初始化
			travelTotal.setYear(year);
			travelTotal.setMonth(month);
			travelTotal.setCostType(costType);
			budgetNumber=getbNumber(travelTotal);
			BigDecimal bumoney=budgetNumber.getBudget_money().add(travelTotal.getOldTotalMoney());
			if(bumoney.compareTo(travelTotal.getTotalMoney())==-1){
				this.setFailMessage("操作失败!申请金额大于预算余额，现在您可用的预算为"+bumoney+";");
				return RESULT_MESSAGE;
			}
			travelTotal.setTravelDetailList(detailList);
			travelTotal.setOperatorId(this.getUser().getUserId());
			BooleanResult strResult = travelAppService.updateTravelApp(travelTotal);
			travelTotal = travelAppService.searchTraveltotalByTravelId(travelTotal.getTravelId().toString(), null);
			travelTotal.setYear(year);
			travelTotal.setMonth(month);
			travelTotal.setCostType(costType);
			if (strResult.getResult()) {
				// 写XML文件
				if (!JavaBeanXMLUtil.JavaBean2XML(xmlFilePath + "/"+ travelTotal.getTransactionId() + ".xml", travelTotal,
						getUser().getUserId(), getUser().getUserName(), subFolders)) {
					this.setFailMessage("写入XML出错");
					return RESULT_MESSAGE;
				}
				this.setSuccessMessage("操作成功!");
			}else{
				this.setFailMessage("操作失败!"+strResult.getCode());
			}
		} else {
			this.setFailMessage("操作失败!");
		}

		return RESULT_MESSAGE;
	}
	
	public BudgetNumber getbNumber(TravelTotal travelTotal) {
		budgetNumber = new BudgetNumber();
		// 日常职能费用
		budgetNumber.setBudget_type("02");
		budgetNumber.setCostCentId(travelTotal.getCostOrgId());
		budgetNumber.setSubjectId(travelTotal.getCostType());
		budgetNumber.setBudget_number(travelTotal.getBudgetNumber());
		budgetNumber.setbId(travelTotal.getBndetailId());
		budgetNumber.setTheYear(travelTotal.getYear());
		budgetNumber.setTheMonth(travelTotal.getMonth());
		budgetNumber.setStart(0);
		budgetNumber.setEnd(1);
		budgetNumber = travelAppService.getBudgetNumber(budgetNumber);
		
		return budgetNumber;
	}
	
	public ITravelAppService getTravelAppService() {
		return travelAppService;
	}

	public void setTravelAppService(ITravelAppService travelAppService) {
		this.travelAppService = travelAppService;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public IOrgService getOrgService() {
		return orgService;
	}

	public void setOrgService(IOrgService orgService) {
		this.orgService = orgService;
	}


	public IWebService getWebService() {
		return webService;
	}


	public void setWebService(IWebService webService) {
		this.webService = webService;
	}


	public String getTotalMoney() {
		return totalMoney;
	}


	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getFlag() {
		return flag;
	}


	public void setFlag(String flag) {
		this.flag = flag;
	}


	public String getAppUrl() {
		return appUrl;
	}


	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public UserUtil getUserUtil() {
		return userUtil;
	}


	public void setUserUtil(UserUtil userUtil) {
		this.userUtil = userUtil;
	}


	public IEventService getEventService() {
		return eventService;
	}


	public void setEventService(IEventService eventService) {
		this.eventService = eventService;
	}


	public String getEventId() {
		return eventId;
	}


	public void setEventId(String eventId) {
		this.eventId = eventId;
	}


	public String getNextUserId() {
		return nextUserId;
	}


	public void setNextUserId(String nextUserId) {
		this.nextUserId = nextUserId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public File[] getUpload() {
		return upload;
	}


	public void setUpload(File[] upload) {
		this.upload = upload;
	}


	public String[] getUploadFileName() {
		return uploadFileName;
	}


	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}


	public String getXmlFilePath() {
		return xmlFilePath;
	}


	public void setXmlFilePath(String xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
	}


	public String getDetailJsonStr() {
		return detailJsonStr;
	}


	public void setDetailJsonStr(String detailJsonStr) {
		this.detailJsonStr = detailJsonStr;
	}


	public TravelTotal getTravelTotal() {
		return travelTotal;
	}


	public void setTravelTotal(TravelTotal travelTotal) {
		this.travelTotal = travelTotal;
	}


	public List<TravelTotal> getTravelTotalList() {
		return travelTotalList;
	}


	public void setTravelTotalList(List<TravelTotal> travelTotalList) {
		this.travelTotalList = travelTotalList;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getPayee() {
		return payee;
	}


	public void setPayee(String payee) {
		this.payee = payee;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


	public String getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}


	public String getTravelId() {
		return travelId;
	}


	public void setTravelId(String travelId) {
		this.travelId = travelId;
	}


	public String getAuditMoney() {
		return auditMoney;
	}


	public void setAuditMoney(String auditMoney) {
		this.auditMoney = auditMoney;
	}


	public IOrgService getiOrgService() {
		return iOrgService;
	}


	public void setiOrgService(IOrgService iOrgService) {
		this.iOrgService = iOrgService;
	}


	public String getBhxjFlag() {
		return bhxjFlag;
	}


	public void setBhxjFlag(String bhxjFlag) {
		this.bhxjFlag = bhxjFlag;
	}


	public String getSubFolders() {
		return subFolders;
	}


	public void setSubFolders(String subFolders) {
		this.subFolders = subFolders;
	}


	public String getWriteEventId() {
		return writeEventId;
	}


	public void setWriteEventId(String writeEventId) {
		this.writeEventId = writeEventId;
	}


	public String getWriteEvent() {
		return writeEvent;
	}


	public void setWriteEvent(String writeEvent) {
		this.writeEvent = writeEvent;
	}


	public String getWriteStatus() {
		return writeStatus;
	}


	public void setWriteStatus(String writeStatus) {
		this.writeStatus = writeStatus;
	}

	public String getSearchUserRole() {
		return searchUserRole;
	}

	public void setSearchUserRole(String searchUserRole) {
		this.searchUserRole = searchUserRole;
	}


	public IAccountService getAccountService() {
		return accountService;
	}


	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}


	public ProEventTotal getProEventTotal() {
		return proEventTotal;
	}


	public void setProEventTotal(ProEventTotal proEventTotal) {
		this.proEventTotal = proEventTotal;
	}


	public List<ProEventDetail> getEventDetailList() {
		return eventDetailList;
	}


	public void setEventDetailList(List<ProEventDetail> eventDetailList) {
		this.eventDetailList = eventDetailList;
	}


	public BooleanResult getExecuteResult() {
		return executeResult;
	}


	public void setExecuteResult(BooleanResult executeResult) {
		this.executeResult = executeResult;
	}


	public String getCurStaId() {
		return curStaId;
	}


	public void setCurStaId(String curStaId) {
		this.curStaId = curStaId;
	}


	public String getCostType() {
		return costType;
	}


	public void setCostType(String costType) {
		this.costType = costType;
	}


	public String getBudgetNumberNum() {
		return budgetNumberNum;
	}


	public void setBudgetNumberNum(String budgetNumberNum) {
		this.budgetNumberNum = budgetNumberNum;
	}


	public String getBnumberId() {
		return bnumberId;
	}


	public void setBnumberId(String bnumberId) {
		this.bnumberId = bnumberId;
	}


	public BudgetNumber getBudgetNumber() {
		return budgetNumber;
	}


	public void setBudgetNumber(BudgetNumber budgetNumber) {
		this.budgetNumber = budgetNumber;
	}


	public String getCostOrgId() {
		return costOrgId;
	}

	public void setCostOrgId(String costOrgId) {
		this.costOrgId = costOrgId;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public String getMonth() {
		return month;
	}


	public void setMonth(String month) {
		this.month = month;
	}

}
