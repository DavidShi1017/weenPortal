package com.jingtong.platform.wfe.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.framework.annotations.Decode;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.framework.util.DateUtil;
import com.jingtong.platform.framework.util.FileUtil;
import com.jingtong.platform.framework.util.JavaBeanXMLUtil;
import com.jingtong.platform.framework.util.XMLInfo;
import com.jingtong.platform.framework.util.XmlUtil;
import com.jingtong.platform.org.pojo.Borg;
import com.jingtong.platform.org.service.IOrgService;
import com.jingtong.platform.role.service.IRoleService;
import com.jingtong.platform.webservice.pojo.Page;
import com.jingtong.platform.webservice.pojo.ProcessEventDetail;
import com.jingtong.platform.webservice.pojo.ProcessEventTotal;
import com.jingtong.platform.webservice.pojo.UserUtil;
import com.jingtong.platform.webservice.service.IWebService;
import com.jingtong.platform.wfe.pojo.BusinessTripApply;
import com.jingtong.platform.wfe.pojo.Cc;
import com.jingtong.platform.wfe.pojo.LinkMan;
import com.jingtong.platform.wfe.pojo.OaXmlBean;
import com.jingtong.platform.wfe.pojo.PlanAttributeDetail;
import com.jingtong.platform.wfe.pojo.ProEventDetail;
import com.jingtong.platform.wfe.pojo.ProEventTotal;
import com.jingtong.platform.wfe.pojo.TripWay;
import com.jingtong.platform.wfe.service.IEventService;
import com.jingtong.platform.wfe.service.IModelAttributeService;

/**
 * 事务流程
 * 
 */
public class EventAction extends BaseAction {

	private static final long serialVersionUID = -8438833522696652280L;

	private static final Logger logger = Logger.getLogger(EventAction.class);

	private long total = 0;
	private IEventService eventService;
	private IWebService webService;
	private IModelAttributeService modelAttributeService;
	private IOrgService orgService;

	private List<ProcessEventTotal> proEventTotalList;
	private List<ProcessEventDetail> proEventDetailList;

	private String userId;
	@Decode
	private String userName;
	private String orgId;
	private String orgName;
	private String key; // 流程模板
	private String title; // 事务标题
	@Decode
	private String memo; // 审批意见

	private String eventId;
	private String eventIds;
	private String toDoDetail;
	private String modelId;
	private String modelIdType;
	private String type;
	private String curStaId;
	@Decode
	private String modelName;
	@Decode
	private String initator;
	@Decode
	private String eventTitle;
	private String planTypeName;// 事物类别
	private String userList; // 半自动化流程用户
	private String operation;

	/**
	 * 下一处理人信息
	 */
	private String nextUserId;
	private String nextUserName;
	private String nextOrgId;

	private ProcessEventTotal eventTotal;
	private String actionId;
	private ProEventTotal proEventTotal;
	private List<ProEventDetail> eventDetailList;
	private List<TripWay> tripWayList;

	private String planAttId; // 模板属性ID
	private String flag; // 判断是否需要初始话解析XML文件
	private List<OaXmlBean> oaXmlBeanList;

	private String eventContent;
	private String xmlFilePath; // Xml文件路径
	private String xmlTemp_FileName; // XML临时文件名
	private String subFolders;

	private String createDate; // 事务启动流程
	private String curStaIdBack;
	private String text;
	private BusinessTripApply businessTripApply;

	/**
	 * 附件信息
	 */
	private File[] upload;
	private String[] uploadFileName;

	private List<LinkMan> linkManList;

	private String chooseFlag;

	private List<StringResult> stringResultList;
	private String modelKey; // 流程扭转参数
	private String modelValues; // 流程扭转参数值

	private UserUtil userUtil; // 下个处理人列表
	private String appUrl;
	private String event_title; // 下个处理人
	private String keys;
	private String wfeType;
	private String modifyFlag;
	private String shortcutFlag;
	private String event_id;
	private String operationType;
	private String projectId;
	private String successResult;
	private List<BusinessTripApply> tripApplyList; // 出差申请列表
	private List<ProEventTotal> proEventTotals;
	private List<Cc> ccs;
	@Decode
	private String creator_name;
	private String ccFlag;
	private StringResult result = new StringResult(); 
	private String cc_id;
	/**
	 * 创建事务（初始化）
	 * 
	 * @return
	 */
	@PermissionSearch
	public String createEventPrepare() {

		// 提交者
		userId = getUser().getUserId();
		userName = getUser().getUserName();
		return CREATE_PREPARE;
	}

	/**
	 * 创建保存事务
	 * 
	 * @return
	 */
	public String createEvent() {
		this.setFailMessage("");
		this.setSuccessMessage("");
		StringResult result = eventService.createEvent(key, getUser()
				.getUserId(), title, nextUserId, eventContent, userList, memo);
		if (IRoleService.SUCCESS.equals(result.getCode())) {
			String detailId = eventService.getProEventDetail(
					result.getResult(), getUser().getUserId());
			// 保存附件
			if (upload != null && upload.length > 0) {
				eventService.processAttachments(upload, uploadFileName,
						Long.valueOf(detailId),
						String.valueOf(new Date().getTime()), key);
			}
			// 修改事务内容XML文件名
			xmlFilePath = xmlFilePath + "/"
					+ DateUtil.getDateTime(new Date(), "yyyyMM");
			if (!FileUtil.modifyFileNameTo(new File(xmlFilePath + "/"
					+ xmlTemp_FileName + ".xml"),
					xmlFilePath + "/" + result.getResult() + ".xml")) {
				this.setFailMessage("XML文件读取有错!");
			} else {
				this.setSuccessMessage("事务ID:" + result.getResult() + "已经启动!");
			}
			if ("any".equals(key.substring(0, 3))) { // 如果是任意流程，增添联系人
				LinkMan linkMan = new LinkMan();
				linkMan.setEmpId(Long.parseLong(getUser().getUserId()));
				linkMan.setLinkManId(Long.parseLong(nextUserId));
				linkMan.setLinkManName(nextUserName);
				linkMan.setOrgId(Long.valueOf(nextOrgId));
				linkMan.setUseCount(BigDecimal.ONE);
				// 修改联系人
				eventService.saveOrUpdateLinkMan(linkMan);
			}
		} else {
			this.setFailMessage(result.getResult());
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 维护事务内容(跳转)
	 * 
	 * @return
	 */
	@PermissionSearch
	public String updateEventContentPrepare() {
		if ("0".equals(planAttId)) {
			xmlTemp_FileName = "temp_" + eventService.getEvent_XmlTempId(); // 创建XML临时文件名
		}
		return "updateEventContentPrepare";
	}

	/**
	 * 维护事务内容
	 * 
	 * @return
	 */
	public String updateEventContent() {
		this.setFailMessage("");
		this.setSuccessMessage("");
		xmlFilePath = xmlFilePath + "/"
				+ DateUtil.getDateTime(new Date(), "yyyyMM");
		File savedir = new File(xmlFilePath);
		// 如果目录不存在，则新建
		if (!savedir.exists()) {
			savedir.mkdirs();
		}
		// 写XML文件
		if (!FileUtil.saveFile(xmlFilePath + "/" + xmlTemp_FileName + ".xml",
				eventContent, false)) {
			this.setFailMessage("写入XML文件出错!");
		} else {
			this.setSuccessMessage("写入XML文件成功!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 填写具体事务内容
	 * 
	 * @return
	 */
	public String updateEventAttributeContentPrepare() {
		if (StringUtils.isNotEmpty(planAttId)) {
			List<PlanAttributeDetail> detailList = modelAttributeService
					.getPlanAttContentByPlanAttId(Long.parseLong(planAttId));
			xmlFilePath = xmlFilePath + "/"
					+ DateUtil.getDateTime(new Date(), "yyyyMM");
			List<OaXmlBean> list = null;
			// 已保存过XML文件，需要初始化解析
			if ("Y".equals(flag)) {
				OaXmlBean o = new OaXmlBean();
				o.setParameter("new");
				o.setParameter3("text");
				list = XmlUtil.readXml(xmlFilePath + "/" + xmlTemp_FileName
						+ ".xml", o, null);
			}
			oaXmlBeanList = new ArrayList<OaXmlBean>();
			if (detailList != null && detailList.size() != 0) {
				for (int i = 0; i < detailList.size(); i++) {
					OaXmlBean oaXmlBean = new OaXmlBean();
					oaXmlBean.setParameter1(String.valueOf(i + 1));
					oaXmlBean.setParameter2(detailList.get(i)
							.getPlanAttContent());
					if ("Y".equals(flag)) {
						oaXmlBean.setParameter3(list.get(i).getParameter3());
					}
					oaXmlBean.setParameter4(detailList.get(i).getPlanAttKey());
					oaXmlBean.setParameter5(detailList.get(i)
							.getPlanAttDataType());
					oaXmlBean.setParameter6(detailList.get(i)
							.getPlanAttIsNull());
					oaXmlBeanList.add(oaXmlBean);
				}
			}
		}
		return "updateEventAttributeContentPrepare";
	}

	/**
	 * 获取常用联系人
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchLinkMan() {
		return "searchLinkMan";
	}

	/**
	 * 获取常用联系人
	 * 
	 * @return
	 */
	@JsonResult(field = "linkManList", include = { "linkManId", "linkManName",
			"orgId", "orgName" })
	@PermissionSearch
	public String getLinkManJsonList() {
		LinkMan linkMan = new LinkMan();
		linkMan.setEmpId(Long.parseLong(getUser().getUserId()));
		linkManList = eventService.getLinkManList(linkMan);
		return JSON;
	}

	/**
	 * 事务审批过程中查看内容
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchEventContent() {
		if (StringUtils.isNotEmpty(eventId)) {
			String pathFile = xmlFilePath + File.separator + subFolders
					+ File.separator + eventId + ".xml";
			OaXmlBean oaXmlBean = new OaXmlBean();
			oaXmlBean.setParameter("new");
			oaXmlBean.setParameter1("id");
			oaXmlBean.setParameter2("name");
			oaXmlBean.setParameter3("text");
			oaXmlBeanList = XmlUtil.readXml(pathFile, oaXmlBean, null);
			List<OaXmlBean> list = XmlUtil.readXml(pathFile, oaXmlBean, "name");
			if (list != null && list.size() > 0) {
				planTypeName = list.get(0).getParameter();
			}
		}
		return "searchEventContent";
	}

	/**
	 * 页面跳转（待办事宜）
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toSearchProcessEvent() {
		return "toSearchProcessEvent";
	}

	/**
	 * 页面跳转（已办事宜）
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toSearchProcessedEvent() {
		return "toSearchProcessedEvent";
	}

	/**
	 * 页面跳转（事务查询）
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toSearchEvent() {
		return "toSearchEvent";
	}

	/**
	 * 页面跳转（授权事务查询）
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toSearchAuthorizeEvent() {
		return "toSearchAuthorizeEvent";
	}

	/**
	 * 查询待办事宜
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "proEventTotalList", include = { "eventId",
			"currentDetailid", "eventTitle", "status", "curStaId", "initator",
			"empName", "modelId", "modelName", "creatdate", "keys" }, total = "total")
	public String getProcessEventJsonList() {
		proEventTotalList = new ArrayList<ProcessEventTotal>();
		Object[] res = new Object[9];
		res[0] = getUser().getUserId();
		res[1] = eventId;
		res[2] = initator;
		res[3] = eventTitle;
		res[4] = modelName;
		res[5] = "";
		res[6] = "";
		res[7] = getStart();
		res[8] = getEnd();
		Page<ProcessEventTotal> page = webService.findTodoTasks(res);
		proEventTotalList = page.getResult();
		total = page.getTotalCount();

		return JSON;
	}

	/**
	 * 查询已办事宜
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "proEventTotalList", include = { "eventId",
			"currentDetailid", "eventTitle", "status", "curUserName",
			"initator", "empName", "modelName", "creatdate"}, total = "total")
	public String getProcessedEventJsonList() {
		Object[] res = new Object[9];
		res[0] = getUser().getUserId();
		res[1] = eventId;
		res[2] = initator;
		res[3] = eventTitle;
		res[4] = modelName;
		res[5] = "";
		res[6] = "";
		res[7] = getStart();
		res[8] = getEnd();
		Page<ProcessEventTotal> page = webService.findProcessedTasks(res);
		proEventTotalList = page.getResult();
		total = page.getTotalCount();

		return JSON;
	}

	/**
	 * 事务查询
	 * 
	 * @return
	 */
	@JsonResult(field = "proEventTotalList", include = { "eventId",
			"currentDetailid", "eventTitle", "status", "curUserName",
			"initator", "empName", "modelId", "modelName", "creatdate",
			"backStatus", "curStaId" , "subFolders"}, total = "total")
	@PermissionSearch
	public String getEventJsonList() {
		Object[] res = new Object[9];
		res[0] = getUser().getUserId();
		res[1] = eventId;
		res[2] = initator;
		res[3] = eventTitle;
		res[4] = modelName;
		res[5] = "";
		res[6] = "";
		res[7] = getStart();
		res[8] = getEnd();
		Page<ProcessEventTotal> page = webService.findTasksByUser(res);
		proEventTotalList = page.getResult();
		total = page.getTotalCount();

		return JSON;
	}

	/**
	 * 事务取消
	 * 
	 * @return
	 */
	public String cancelEvent() {
		this.setFailMessage("");
		this.setSuccessMessage("");
		String result = webService.cancelEvent(eventIds);
		if ("success".equals(result)) {
			this.setSuccessMessage("取消成功！");
		} else {
			this.setFailMessage("操作失败！");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 页面跳转（事务处理）
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toProcessEvent() {
		if(StringUtils.isNotEmpty(cc_id)){
			proEventTotal = eventService.getEventTotalById(Long.valueOf(event_id),Long.valueOf(cc_id));
		}else{
			proEventTotal = eventService.getEventTotalById(Long.valueOf(event_id));
		}
		if (proEventTotal == null) {
			return ERROR;
		}
		modelIdType = proEventTotal.getModelId().substring(0, 3);
		eventDetailList = eventService.getEventDetailListAndSort(Long
				.valueOf(event_id));

		if ("process".equals(operationType)) {
			modelIdType = modelId.substring(0, 3);
			if ("any".equals(modelIdType)) {
				shortcutFlag = "Y";
			}
		}
		return "toProcessEvent";
	}

	/**
	 * 事务处理
	 * 
	 * @return
	 */
	public String processEvent() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		Object[] res = new Object[6];
		res[0] = toDoDetail;
		res[1] = eventId;
		res[2] = operation;
		res[3] = modelId;
		res[4] = nextUserId;
		res[5] = memo;
		String result = webService.complete(res);
		if ("success".equals(result)) {
			if (upload != null && upload.length > 0) {
				eventService.processAttachments(upload, uploadFileName,
						Long.valueOf(toDoDetail),
						String.valueOf(new Date().getTime()), modelId);
			}
			if ("any".equals(modelId.substring(0, 3))
					&& !"E".equals(chooseFlag) && !"N".equals(operation)) { // 任意流程并且处理尚未完成，添加常用联系人
				LinkMan linkMan = new LinkMan();
				linkMan.setEmpId(Long.parseLong(getUser().getUserId()));
				linkMan.setLinkManId(Long.parseLong(nextUserId));
				linkMan.setLinkManName(nextUserName);
				linkMan.setOrgId(Long.valueOf(nextOrgId));
				linkMan.setUseCount(BigDecimal.ONE);
				eventService.saveOrUpdateLinkMan(linkMan);
			}
			if ("any_testCases".equals(modelId) && "E".equals(chooseFlag)
					&& "Y".equals(operation)) {
				eventService.updateBuglogFile(Long.valueOf(eventId), "S");
			}
			if ("any_testCases".equals(modelId) && "N".equals(operation)) {
				eventService.updateBuglogFile(Long.valueOf(eventId), "D");
			}
			this.setSuccessMessage("处理成功！");
		} else {
			this.setFailMessage("操作失败！");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 判断半自动流程是不是最后一个节点
	 * 
	 * @return
	 */
	@JsonResult(field = "userUtil", include = { "processInstanceId", "result",
			"executeAction" })
	public String selectSemUser() {
		userUtil = new UserUtil();
		Object[] res = new Object[1];
		res[0] = eventId;
		userUtil = webService.selectSemUser(res);
		return JSON;
	}
	
	/**
	 * 判断半自动流程是不是最后一个节点
	 * 
	 * @return
	 */
	@JsonResult(field = "userUtil", include = { "processInstanceId", "result",
			"executeAction" })
	public String selectAnyExecuteAction() {
		userUtil = new UserUtil();
		Object[] res = new Object[2];
		res[0] = eventId;
		res[1] = operation;
		
		userUtil = webService.selectAnyExecuteAction(res);
		return JSON;
	}
	

	/**
	 * 页面跳转半自动化
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toSemiAutomatic() {
		return "toSemiAutomatic";
	}

	/**
	 * 事务明细查看
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchEventDetail() {
		Long id = null;
		try {
			id = Long.parseLong(eventId);
		} catch (Exception e) {
			return ERROR;
		}
		proEventTotal = eventService.getEventTotalById(id);

		modelIdType = proEventTotal.getModelId().substring(0, 3);
		if (proEventTotal == null) {
			return ERROR;
		}
		eventDetailList = eventService.getEventDetailListAndSort(id);

		if (eventDetailList != null && eventDetailList.size() > 1) {
			return "searchEventDetail";
		}

		return ERROR;

	}

	/**
	 * 待办事宜数 tip
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "result")
	public String getProcessedCount() {
		long eventCount = 0l;
		Object[] res = new Object[] { getUser().getUserId(), "", "", "", "","","", 0,
				0 };
		eventCount = webService.findTodoTasks(res).getTotalCount();
		
		long ccCount = 0L;
		Cc cc = new Cc();
		cc.setCc_user_id(this.getUser().getUserId());
		cc.setFlag("1");
		ccCount = eventService.getCcListCount(cc);
		
		long caCount = 0l;
		ProcessEventTotal eventTotal = new ProcessEventTotal();
		eventTotal.setStatus("3");
		eventTotal.setInitator(this.getUser().getUserId());
		caCount = eventService.getCancelEventCount(eventTotal);
		
		result.setCode(String.valueOf(eventCount));
		result.setResult(String.valueOf(ccCount));
		result.setText(String.valueOf(caCount));
		
		return JSON;

	}

	/**
	 * 获取流程资源
	 * 
	 * @return
	 */
	@JsonResult(field = "stringResultList", include = { "result", "code",
			"text" })
	public String traceProcess() {
		eventDetailList = eventService.getEventDetailListAndSort(Long
				.valueOf(eventId));
		stringResultList = new ArrayList<StringResult>();
		for (ProEventDetail proEventDetail : eventDetailList) {
			StringResult stringResult = new StringResult();
			stringResult.setCode(proEventDetail.getOperation());
			stringResult.setResult(proEventDetail.getUserName());
			if (null != proEventDetail.getRoleName()) {
				stringResult.setText(proEventDetail.getRoleName());
			} else {
				stringResult.setText("任意审批人");
			}
			stringResultList.add(stringResult);
		}
		return JSON;
	}

	/**
	 * 启动选择下个处理人
	 * 
	 * @return
	 */
	@JsonResult(field = "userUtil", include = { "processInstanceId", "result" })
	public String selectNexUser() {
		Object[] res = new Object[4];
		res[0] = key;
		res[1] = userId;
		res[2] = modelKey;
		res[3] = modelValues;
	
		userUtil = webService.startWorkflowFix(res);
		return JSON;
	}

	/**
	 * 处理中选择下个处理人
	 * 
	 * @return
	 */
	@JsonResult(field = "userUtil", include = { "processInstanceId",
			"executeAction", "result" })
	public String selectProcessNexUser() {
		Object[] res = new Object[8];
		res[0] = toDoDetail;
		res[1] = eventId;
		res[2] = "Pass";
		res[3] = operation;
		res[4] = modelId;
		res[5] = keys;
		res[6] = curStaId;
		res[7] = memo;
		userUtil = webService.startProcessNexUser(res);
		if (userUtil != null
				&& "success".equals(userUtil.getProcessInstanceId())) {
			if (upload != null && upload.length > 0) { // 上传附件
				eventService.processAttachments(upload, uploadFileName,
						Long.valueOf(toDoDetail),
						String.valueOf(new Date().getTime()), modelId);
			}
		}
		return JSON;
	}

	/**
	 * 处理中处理事务
	 * 
	 * @return
	 */
	public String processCommit() {
		this.setSuccessMessage("");
		this.setFailMessage("");

		Object[] res = new Object[5];
		res[0] = eventId;
		res[1] = nextUserId;
		res[2] = toDoDetail;
		res[3] = "Y";
		res[4] = memo;
		String result = webService.processCommit(res);
		if ("success".equals(result)) {
			if (upload != null && upload.length > 0) { // 上传附件
				eventService.processAttachments(upload, uploadFileName,
						Long.valueOf(toDoDetail),
						String.valueOf(new Date().getTime()), modelId);
			}
			this.setSuccessMessage("事务处理成功！");
		} else {
			this.setFailMessage("事务处理失败！");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 取消下个处理人
	 * 
	 * @return
	 */
	public String cancelNextUser() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		try {
			webService.cancelEvent(eventId);
		} catch (Exception e) {
			logger.error(e);
		}
		this.setSuccessMessage("ok");
		return RESULT_MESSAGE;
	}

	/**
	 * 获取下个处理人并处理
	 * 
	 * @return
	 */
	public String processWorkflowFix() {
		this.setSuccessMessage("");
		this.setFailMessage("");

		Object[] res = new Object[7];
		res[0] = eventId;
		res[1] = userId;
		res[2] = nextUserId;
		res[3] = title;
		res[4] = appUrl + "/wfe/eventAction!searchEventContent.jspa";
		res[5] = key;
		res[6] = memo;
		String result = webService.processWorkflowFix(res);
		if ("success".endsWith(result)) {
			String detailId = eventService.getProEventDetail(eventId, getUser()
					.getUserId());
			// 保存附件
			if (upload != null && upload.length > 0) {
				eventService.processAttachments(upload, uploadFileName,
						Long.valueOf(detailId),
						String.valueOf(new Date().getTime()), key);
			}
			// 修改事务内容XML文件名
			xmlFilePath = xmlFilePath + "/"
					+ DateUtil.getDateTime(new Date(), "yyyyMM");
			if (!FileUtil.modifyFileNameTo(new File(xmlFilePath + "/"
					+ xmlTemp_FileName + ".xml"), xmlFilePath + "/" + eventId
					+ ".xml")) {
				this.setFailMessage("XML文件读取有错!");
			}
			this.setSuccessMessage("事务启动成功,事务号为：" + eventId);
		} else {
			this.setFailMessage("启动失败");

		}
		return RESULT_MESSAGE;
	}

	/**
	 * 查看回退人员信息列表
	 * 
	 * @return
	 */
	@JsonResult(field = "eventDetailList", include = { "curUserId", "curStaId",
			"curUserName", "roleName" })
	@PermissionSearch
	public String getStationListByEventId() {
		eventDetailList = eventService.getStationListByEventId(Long
				.parseLong(eventId));
		return JSON;
	}

	/**
	 * 回退人员页面跳转
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toSearchStationId() {
		return "toSearchStationId";
	}

	/**
	 * 事务回退
	 * 
	 * @return
	 */
	public String backEvent() {
		Object[] res = new Object[8];
		res[0] = toDoDetail;
		res[1] = eventId;
		res[2] = "Pass";
		res[3] = "H";
		res[4] = modelId;
		res[5] = keys;
		res[6] = curStaIdBack;
		res[7] = memo;
		userUtil = webService.startProcessNexUser(res);
		if (userUtil != null
				&& "success".equals(userUtil.getProcessInstanceId())) {
			if (upload != null && upload.length > 0) {
				eventService.processAttachments(upload, uploadFileName,
						Long.valueOf(toDoDetail),
						String.valueOf(new Date().getTime()), modelId);
			}
			this.setSuccessMessage("回退成功!");
		} else {
			this.setFailMessage("操作失败!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 处理最后节点事务，直接完成
	 * 
	 * @return
	 */
	public String completeEndTask() {
		Object[] res = new Object[6];
		res[0] = toDoDetail;
		res[1] = eventId;
		res[2] = "Pass";
		res[3] = operation;
		res[4] = modelId;
		res[5] = memo;
		userUtil = webService.completeEndTask(res);
		if (userUtil != null
				&& "success".equals(userUtil.getProcessInstanceId())) {
			if (upload != null && upload.length > 0) {
				eventService.processAttachments(upload, uploadFileName,
						Long.valueOf(toDoDetail),
						String.valueOf(new Date().getTime()), modelId);
			}
			try {
				successResult = new String(getServletRequest().getParameter(
						"successResult").getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e);
			}
			this.setSuccessMessage((!(StringUtils.isNotEmpty(successResult) && StringUtils
					.isNotEmpty(successResult.trim())) || "undefined"
					.equals(successResult)) ? "操作成功!" : "操作成功!</br>"
					+ successResult);
		} else {
			this.setFailMessage("操作失败!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 处理最后节点事务，直接完成
	 * 
	 * @return
	 */
	public String uploadAttachments() {
		if (upload != null && upload.length > 0) {
			eventService.processAttachments(upload, uploadFileName,
					Long.valueOf(toDoDetail),
					String.valueOf(new Date().getTime()), modelId);
		}
		this.setSuccessMessage("处理成功!");

		return RESULT_MESSAGE;
	}

	/**
	 * 页面跳转(出差申请)
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toBusinessTripApply() {
		Borg org = orgService.getOrgByOrgId(getUser().getOrgId());
		orgId = org.getOrgId().toString();
		orgName = org.getOrgName();
		businessTripApply = new BusinessTripApply();
		/*if ("0".equals(planAttId)) {
			xmlTemp_FileName = "temp_" + eventService.getEvent_XmlTempId(); // 创建XML临时文件名
		} else {
			String pathFile = xmlFilePath + File.separator
					+ DateUtil.getDateTime(new Date(), "yyyyMM")
					+ File.separator + xmlTemp_FileName + ".xml";
			XMLInfo info = JavaBeanXMLUtil.XML2JavaBean(pathFile,
					businessTripApply);
			if (info != null) {
				businessTripApply = (BusinessTripApply) info.getObject();
			}
		}*/
		/*** 申请日期 ***/
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		createDate = sf.format(new Date());
		
		userId = this.getUser().getUserId();
		userName = this.getUser().getUserName();

		return "toBusinessTripApply";
	}

	/**
	 * 查看出差申请内容
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchBusinessTripApply() {
		businessTripApply = new BusinessTripApply();
		String pathFile = xmlFilePath + File.separator + subFolders
				+ File.separator + eventId + ".xml";
		XMLInfo info = JavaBeanXMLUtil
				.XML2JavaBean(pathFile, businessTripApply);
		if (info != null) {
			businessTripApply = (BusinessTripApply) info.getObject();
		}
		return "searchBusinessTripApply";
	}

	/**
	 * 查询出行方式
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "tripWayList", include = { "value", "name" }, total = "total")
	public String getTripWayJsonList() {
		tripWayList = new ArrayList<TripWay>();
		TripWay tripWay = new TripWay();
		if (StringUtils.isNotEmpty(text)) {
			try {
				text = new String(getServletRequest().getParameter("text")
						.getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e);
			}
			tripWay.setText(text);
		}
		tripWay.setStart(getStart());
		tripWay.setEnd(getEnd());
		total = eventService.getTripWayCount(tripWay);
		if (total != 0)
			tripWayList = eventService.getTripWayList(tripWay);
		return JSON;
	}

	/**
	 * 维护出差申请事务内容
	 * 
	 * @return
	 */
	public String updateBusinessTripApplyContent() {
		// 写XML文件
		if (!JavaBeanXMLUtil.JavaBean2XML(xmlFilePath + "/" + xmlTemp_FileName
				+ ".xml", businessTripApply, getUser().getUserId(), getUser()
				.getUserName(), null)) {
			this.setFailMessage("写入XML文件出错!");
		} else {
			this.setSuccessMessage("写入XML文件成功!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 处理最后节点事务，直接完成
	 * 
	 * @return
	 */
	public String backProEvent() {
		Object[] res = new Object[4];
		res[0] = eventIds;
		res[1] = modelId;
		res[2] = toDoDetail;
		res[3] = this.getUser().getUserId();
		userUtil = webService.backProEvent(res);
		if (userUtil != null
				&& "success".equals(userUtil.getProcessInstanceId())) {
			this.setSuccessMessage("操作成功！");
		} else {
			this.setFailMessage("操作失败!");
		}
		return RESULT_MESSAGE;
	}
	
	/**
	 * 出差申请调用流程
	 * 
	 * @return
	 */
	public String processTripApplyWorkflowFix() {
		this.setSuccessMessage("");
		this.setFailMessage("");

		Object[] res = new Object[7];
		res[0] = eventId;
		res[1] = userId;
		res[2] = nextUserId;
		res[3] = title;
		res[4] = appUrl + "/wfe/eventAction!searchBusinessTripApply.jspa";
		res[5] = key;
		res[6] = memo;
		String result = webService.processWorkflowFix(res);
		if ("success".endsWith(result)) {
			businessTripApply.setUserId(userId);
			businessTripApply.setUserName(userName);
			businessTripApply.setEventId(eventId);
			businessTripApply.setEventTitle(title);
			businessTripApply.setOrgId(orgId);
			businessTripApply.setOrgName(orgName);
			// 写XML文件
			if (!JavaBeanXMLUtil.JavaBean2XML(xmlFilePath + "/" + eventId
					+ ".xml", businessTripApply, getUser().getUserId(), getUser()
					.getUserName(), null)) {
				this.setFailMessage("写入XML文件出错!");
			} else {
				this.setSuccessMessage("事务启动成功,事务号为：" + eventId);
			}
		} else {
			webService.cancelEvent(eventId);
			this.setFailMessage("启动失败");

		}
		return RESULT_MESSAGE;
	}

	/***
	 * 跳转到出差申请查询列表页面
	 * 
	 * @return
	 */
	public String toTripApply() {
		return "toTripApply";
	}

	/**
	 * 出差申请查询
	 * 
	 * @return
	 */
	@JsonResult(field = "tripApplyList", include = { "eventId", "eventTitle",
			"status", "orgName", "userName", "place", "tripWay", "tripWayName",
			"beginDate", "endDate", "reason","departure","place" ,"stop","amount","createDate"}, total = "total")
	@PermissionSearch
	public String getTripApplyJsonList() {
		// 初始化
		proEventTotal = new ProEventTotal();
		proEventTotals = new ArrayList<ProEventTotal>();
		tripApplyList = new ArrayList<BusinessTripApply>();

		proEventTotal.setStart(this.getStart());
		proEventTotal.setEnd(this.getEnd());

		if (StringUtils.isNotEmpty(eventId)) { // 事务ID
			proEventTotal.setEventId(Long.valueOf(eventId));
		}
		if (StringUtils.isNotEmpty(userName)) { // 提出者
			proEventTotal.setUserName(userName);
		}
		if (StringUtils.isNotEmpty(eventTitle)) { // 事务标题
			proEventTotal.setEventTitle(eventTitle);
		}
		total = eventService.getTripApplyListCount(proEventTotal);
		if (total > 0) {
			proEventTotals = eventService.getTripApplyList(proEventTotal);
			for (ProEventTotal eventTotal : proEventTotals) { // 出XML文件获取出差字段
				businessTripApply = new BusinessTripApply();
				String pathFile = xmlFilePath + File.separator
						+ eventTotal.getSubFolders() + File.separator
						+ eventTotal.getEventId() + ".xml";
				XMLInfo info = JavaBeanXMLUtil.XML2JavaBean(pathFile,
						businessTripApply);
				if (info != null) {
					businessTripApply = (BusinessTripApply) info.getObject();
					businessTripApply.setEventId(String.valueOf(eventTotal
							.getEventId()));
					businessTripApply.setStatus(eventTotal.getStatus());
					businessTripApply.setEventTitle(eventTotal.getEventTitle());
					businessTripApply.setUserName(eventTotal.getUserName());
					tripApplyList.add(businessTripApply);
				}
			}
		}

		return JSON;
	}

	/**
	 * Excel 导出
	 * 
	 * @return
	 */
	public String downloadExecel() {
		// 初始化
		proEventTotal = new ProEventTotal();
		proEventTotals = new ArrayList<ProEventTotal>();
		tripApplyList = new ArrayList<BusinessTripApply>();

		proEventTotal.setStart(this.getStart());
		proEventTotal.setEnd(100000000);
		proEventTotals = eventService.getTripApplyList(proEventTotal);
		if (proEventTotals != null && proEventTotals.size() > 0) {
			for (ProEventTotal eventTotal : proEventTotals) { // 出XML文件获取出差字段
				businessTripApply = new BusinessTripApply();
				String pathFile = xmlFilePath + File.separator
						+ eventTotal.getSubFolders() + File.separator
						+ eventTotal.getEventId() + ".xml";
				XMLInfo info = JavaBeanXMLUtil.XML2JavaBean(pathFile,
						businessTripApply);
				if (info != null) {
					businessTripApply = (BusinessTripApply) info.getObject();
					businessTripApply.setEventId(String.valueOf(eventTotal
							.getEventId()));
					businessTripApply.setStatus(eventTotal.getStatus());
					businessTripApply.setEventTitle(eventTotal.getEventTitle());
					businessTripApply.setUserName(eventTotal.getUserName());
					tripApplyList.add(businessTripApply);
				}
			}
		}
		try {
			if (tripApplyList != null && tripApplyList.size() > 0) {
				File file = eventService
						.exportBusinessTripApplyList(tripApplyList);
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
				if (file != null) {
					display(file, "出差明细单_" + df.format(new Date()) + ".xls",
							ServletActionContext.getResponse());
					file.delete();
					ServletActionContext.getRequest().getSession()
							.setAttribute("DownLoad", "Over");
				} else {
					this.setFailMessage("Excel数据导出出错");
				}
			}
		} catch (Exception e) {
			this.setFailMessage("Excel数据导出出错");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 文件下载
	 * 
	 * @param file
	 * @param fileName
	 * @param response
	 * @return6
	 */
	@SuppressWarnings("finally")
	@PermissionSearch
	private boolean display(File file, String fileName,
			HttpServletResponse response) {
		FileInputStream in = null;
		OutputStream out = null;
		try {
			fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename=\""
					+ fileName);
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
			return true;
		}
	}

	/***
	 * 创建抄送人
	 * 
	 * @return
	 */
	public String creatCc() {
		if(StringUtils.isNotEmpty(userList)){
			List<Cc> ccList = new ArrayList<Cc>();
			String [] users = userList.split(",");
			for (String userId : users) {
				Cc cc = new Cc();
				cc.setEvent_id(Long.valueOf(eventId));
				cc.setEvent_detail_id(Long.valueOf(toDoDetail));
				cc.setCc_user_id(userId);
				cc.setCreator(this.getUser().getUserId());
				ccList.add(cc);
			}
			
			if(ccList != null && ccList.size() > 0){
				eventService.createCc(ccList);
			}
			this.setSuccessMessage("抄送成功!");
			
		}else{
			this.setFailMessage("请选择抄送人！");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 抄送信息数 tip
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "total")
	public String getCcCount() {
		Cc cc = new Cc();
		cc.setCc_user_id(this.getUser().getUserId());
		cc.setFlag("1");
		total = eventService.getCcListCount(cc);
		return JSON;
	}

	/**
	 * 跳转到抄送信息列表页面
	 * 
	 * @return
	 */
	public String toCcSearchInit() {

		return "toCcSearchInit";
	}

	/**
	 * 抄送信息数 tip
	 * 
	 * @return
	 */
	@JsonResult(field = "ccs", include = { "cc_id", "event_id",
			"event_detail_id", "creator_name", "create_date", "event_title" }, total = "total")
	public String getCcJsonList() {
		ccs = new ArrayList<Cc>();
		Cc cc = new Cc();
		cc.setStart(getStart());
		cc.setEnd(getEnd());

		cc.setCc_user_id(this.getUser().getUserId());
		if(StringUtils.isNotEmpty(eventTitle)){
			cc.setEvent_title(eventTitle);
		}
		if (StringUtils.isNotEmpty(ccFlag)) {
			cc.setFlag(ccFlag);
		}
		if (StringUtils.isNotEmpty(creator_name)) {
			cc.setCreator_name(creator_name);
		}

		total = eventService.getCcListCount(cc);
		if (total > 0) {
			ccs = eventService.getCcList(cc);
		}

		return JSON;
	}
	
	
	/**
	 * 跳转到拒绝事务页面
	 * 
	 * @return
	 */
	public String toCaSearchInit() {
		return "toCaSearchInit";
	}
	
	/**
	 * 查询拒绝事务
	 * @return
	 */
	@JsonResult(field = "proEventTotalList", include = { "eventId",
			"eventTitle", "status", "curUserName", "initator", 
			"empName", "modelId", "modelName", "creatdate"}, 
			total = "total")
	public String getCaJsonList() {
		ProcessEventTotal eventTotal = new ProcessEventTotal();
		eventTotal.setStatus("3");
		eventTotal.setStart(this.getStart());
		eventTotal.setEnd(this.getEnd());
		eventTotal.setInitator(this.getUser().getUserId());

		if(StringUtils.isNotEmpty(eventId)){
			eventTotal.setEventId(Long.parseLong(eventId));
		}
		if (StringUtils.isNotEmpty(eventTitle)) {
			eventTotal.setEventTitle(eventTitle);
		}

		total = eventService.getCancelEventCount(eventTotal);
		if (total > 0) {
			proEventTotalList = eventService.getCancelEventList(eventTotal);
		}
		return JSON;
	}
	
	/***
	 * 创建抄送人提交意见
	 * 
	 * @return
	 */
	public String updateCc() {
		if(StringUtils.isNotEmpty(cc_id)){
			Cc cc = new Cc();
			cc.setCc_id(Long.valueOf(cc_id));
			cc.setReplay_memo(memo);
			eventService.updateCc(cc);
			
			this.setSuccessMessage("处理成功!");
			
		}else{
			this.setFailMessage("处理失败,请重试！");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 获取处理结果列表
	 * @return
	 */
	public String toRepalyMemo(){
		Cc cc = new Cc();
		
		cc.setEvent_id(Long.valueOf(eventId));
		cc.setEvent_detail_id(Long.valueOf(toDoDetail));
		
		ccs = eventService.getCcListByEventId(cc);
		return "toRepalyMemo";
	}

	public List<Cc> getCcs() {
		return ccs;
	}

	public void setCcs(List<Cc> ccs) {
		this.ccs = ccs;
	}

	public IEventService getEventService() {
		return eventService;
	}

	public void setEventService(IEventService eventService) {
		this.eventService = eventService;
	}

	public IModelAttributeService getModelAttributeService() {
		return modelAttributeService;
	}

	public void setModelAttributeService(
			IModelAttributeService modelAttributeService) {
		this.modelAttributeService = modelAttributeService;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
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

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getPlanTypeName() {
		return planTypeName;
	}

	public void setPlanTypeName(String planTypeName) {
		this.planTypeName = planTypeName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getToDoDetail() {
		return toDoDetail;
	}

	public void setToDoDetail(String toDoDetail) {
		this.toDoDetail = toDoDetail;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getNextUserId() {
		return nextUserId;
	}

	public IWebService getWebService() {
		return webService;
	}

	public void setWebService(IWebService webService) {
		this.webService = webService;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public List<ProcessEventTotal> getProEventTotalList() {
		return proEventTotalList;
	}

	public void setProEventTotalList(List<ProcessEventTotal> proEventTotalList) {
		this.proEventTotalList = proEventTotalList;
	}

	public List<ProcessEventDetail> getProEventDetailList() {
		return proEventDetailList;
	}

	public void setProEventDetailList(
			List<ProcessEventDetail> proEventDetailList) {
		this.proEventDetailList = proEventDetailList;
	}

	public ProcessEventTotal getEventTotal() {
		return eventTotal;
	}

	public void setEventTotal(ProcessEventTotal eventTotal) {
		this.eventTotal = eventTotal;
	}

	public void setNextUserId(String nextUserId) {
		this.nextUserId = nextUserId;
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

	public String getPlanAttId() {
		return planAttId;
	}

	public void setPlanAttId(String planAttId) {
		this.planAttId = planAttId;
	}

	public List<OaXmlBean> getOaXmlBeanList() {
		return oaXmlBeanList;
	}

	public void setOaXmlBeanList(List<OaXmlBean> oaXmlBeanList) {
		this.oaXmlBeanList = oaXmlBeanList;
	}

	public String getUserList() {
		return userList;
	}

	public void setUserList(String userList) {
		this.userList = userList;
	}

	public String getEventContent() {
		return eventContent;
	}

	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
	}

	public String getXmlFilePath() {
		return xmlFilePath;
	}

	public void setXmlFilePath(String xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getInitator() {
		return initator;
	}

	public void setInitator(String initator) {
		this.initator = initator;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public String getXmlTemp_FileName() {
		return xmlTemp_FileName;
	}

	public void setXmlTemp_FileName(String xmlTemp_FileName) {
		this.xmlTemp_FileName = xmlTemp_FileName;
	}

	public String getSubFolders() {
		return subFolders;
	}

	public void setSubFolders(String subFolders) {
		this.subFolders = subFolders;
	}

	public List<LinkMan> getLinkManList() {
		return linkManList;
	}

	public void setLinkManList(List<LinkMan> linkManList) {
		this.linkManList = linkManList;
	}

	public List<StringResult> getStringResultList() {
		return stringResultList;
	}

	public void setStringResultList(List<StringResult> stringResultList) {
		this.stringResultList = stringResultList;
	}

	public String getNextUserName() {
		return nextUserName;
	}

	public void setNextUserName(String nextUserName) {
		this.nextUserName = nextUserName;
	}

	public String getNextOrgId() {
		return nextOrgId;
	}

	public void setNextOrgId(String nextOrgId) {
		this.nextOrgId = nextOrgId;
	}

	public String getChooseFlag() {
		return chooseFlag;
	}

	public void setChooseFlag(String chooseFlag) {
		this.chooseFlag = chooseFlag;
	}

	public String getEventIds() {
		return eventIds;
	}

	public void setEventIds(String eventIds) {
		this.eventIds = eventIds;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getModelKey() {
		return modelKey;
	}

	public void setModelKey(String modelKey) {
		this.modelKey = modelKey;
	}

	public UserUtil getUserUtil() {
		return userUtil;
	}

	public void setUserUtil(UserUtil userUtil) {
		this.userUtil = userUtil;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public String getEvent_title() {
		return event_title;
	}

	public void setEvent_title(String event_title) {
		this.event_title = event_title;
	}

	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}

	public String getWfeType() {
		return wfeType;
	}

	public void setWfeType(String wfeType) {
		this.wfeType = wfeType;
	}

	public String getModelIdType() {
		return modelIdType;
	}

	public void setModelIdType(String modelIdType) {
		this.modelIdType = modelIdType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCurStaId() {
		return curStaId;
	}

	public void setCurStaId(String curStaId) {
		this.curStaId = curStaId;
	}

	public String getModelValues() {
		return modelValues;
	}

	public void setModelValues(String modelValues) {
		this.modelValues = modelValues;
	}

	public String getModifyFlag() {
		return modifyFlag;
	}

	public void setModifyFlag(String modifyFlag) {
		this.modifyFlag = modifyFlag;
	}

	public String getShortcutFlag() {
		return shortcutFlag;
	}

	public void setShortcutFlag(String shortcutFlag) {
		this.shortcutFlag = shortcutFlag;
	}

	public String getCurStaIdBack() {
		return curStaIdBack;
	}

	public void setCurStaIdBack(String curStaIdBack) {
		this.curStaIdBack = curStaIdBack;
	}

	public String getEvent_id() {
		return event_id;
	}

	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}

	public IOrgService getOrgService() {
		return orgService;
	}

	public void setOrgService(IOrgService orgService) {
		this.orgService = orgService;
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

	public List<TripWay> getTripWayList() {
		return tripWayList;
	}

	public void setTripWayList(List<TripWay> tripWayList) {
		this.tripWayList = tripWayList;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public BusinessTripApply getBusinessTripApply() {
		return businessTripApply;
	}

	public void setBusinessTripApply(BusinessTripApply businessTripApply) {
		this.businessTripApply = businessTripApply;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getSuccessResult() {
		return successResult;
	}

	public void setSuccessResult(String successResult) {
		this.successResult = successResult;
	}

	public List<BusinessTripApply> getTripApplyList() {
		return tripApplyList;
	}

	public void setTripApplyList(List<BusinessTripApply> tripApplyList) {
		this.tripApplyList = tripApplyList;
	}

	public List<ProEventTotal> getProEventTotals() {
		return proEventTotals;
	}

	public void setProEventTotals(List<ProEventTotal> proEventTotals) {
		this.proEventTotals = proEventTotals;
	}

	public String getCreator_name() {
		return creator_name;
	}

	public void setCreator_name(String creator_name) {
		this.creator_name = creator_name;
	}
	public String getCcFlag() {
		return ccFlag;
	}
	public void setCcFlag(String ccFlag) {
		this.ccFlag = ccFlag;
	}
	public StringResult getResult() {
		return result;
	}
	public void setResult(StringResult result) {
		this.result = result;
	}
	public String getCc_id() {
		return cc_id;
	}
	public void setCc_id(String cc_id) {
		this.cc_id = cc_id;
	}
	
}
