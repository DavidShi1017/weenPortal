package com.jingtong.platform.account.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.jingtong.platform.base.pojo.SearchInfo;

public class BudgetNumber extends SearchInfo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2914335280535488995L;
	Long bnumber_id; // 费用编号序列号
	String budget_number;// 费用编号
	Long org_id; // 组织id
	BigDecimal budget_money;// 费用编号金额
	String budget_money_Str;
	Long the_month;// 季度
	Long the_year;// 年
	String org_name;// 组织名称
	String budget_type;// 预算类型
	String number_flag;// 编号状态标识：U-正常 S-终止 I -初始（申请中 ）
	Long tra_id;// 事务id
	String tra_flag;// 事务状态：U-处理中 S-已终止 D-已完成
	Date creatdate;// 创建时间
//	BigDecimal init_money;// 预算初始金额
	String init_money_Str;//
	Date LAST_MODIFY;// 最后一次修改时间
	
	
	String subjectSapNumber;// SAP科目ID
	String[] subjectSapNumbers;
	String sapActiveType;// 活动类型
	String sapKonty;// 分配
	String sapVersion;// 版本
	String creator;// 创建人id
	String modifyer;// 修改人id
	String sapCostCenterId;// sap成本中心id
	
	String sapFlag;// 导入SAP标志
	String errorMessage;// 错误原因
	String channelName;
	private Long channelId;
	private Long transcation_id;
	private String transcation_flag;
	String subjectId;// 科目ID
	String subjectName;//科目名称
	private String title;
	private String createUserId;
	private String createUser;
	private String upload;
	private String costCenterName;
	private String costCentId;
	
	private String theYear;
	private String budnumType;//预算使用类型（3-总部分摊外部费用；2-大区分摊外部费用；1-内部费用）
	private String theMonth;
	private String budgetTpye;
	private String channelType;
	private BigDecimal init_money;
	private String accessFile;// 评估报告
	private String accessFilePath;
	private String customerFile;// 潜在客户
	private String customerFilePath;
	private List<BudgetNumber> detailList;
	private Long item_id;//品类id
	private Long costTypeId;//费用类型id
	private String costTypeName;
	 String item_name;//品类名称
	private String editor1;
	private boolean item_flag = false;
	private boolean channel_flag = false;
	private boolean quarter_flag = false;
	private boolean month_flag = false;
	private boolean costType_flag = false;
	private boolean budgetType_flag = false;
	private boolean bnumberType_flag = false;
	private String bId;
	
	public boolean isQuarter_flag() {
		return quarter_flag;
	}
	public void setQuarter_flag(boolean quarter_flag) {
		this.quarter_flag = quarter_flag;
	}
	public boolean isMonth_flag() {
		return month_flag;
	}
	public void setMonth_flag(boolean month_flag) {
		this.month_flag = month_flag;
	}
	public boolean isCostType_flag() {
		return costType_flag;
	}
	public void setCostType_flag(boolean costType_flag) {
		this.costType_flag = costType_flag;
	}
	public boolean isBudgetType_flag() {
		return budgetType_flag;
	}
	public void setBudgetType_flag(boolean budgetType_flag) {
		this.budgetType_flag = budgetType_flag;
	}
	public boolean isBnumberType_flag() {
		return bnumberType_flag;
	}
	public void setBnumberType_flag(boolean bnumberType_flag) {
		this.bnumberType_flag = bnumberType_flag;
	}
	public boolean isItem_flag() {
		return item_flag;
	}
	public void setItem_flag(boolean item_flag) {
		this.item_flag = item_flag;
	}
	public boolean isChannel_flag() {
		return channel_flag;
	}
	public void setChannel_flag(boolean channel_flag) {
		this.channel_flag = channel_flag;
	}
	public String getCostTypeName() {
		return costTypeName;
	}
	public void setCostTypeName(String costTypeName) {
		this.costTypeName = costTypeName;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getEditor1() {
		return editor1;
	}
	public void setEditor1(String editor1) {
		this.editor1 = editor1;
	}
	public Long getItem_id() {
		return item_id;
	}
	public Long getCostTypeId() {
		return costTypeId;
	}
	public void setCostTypeId(Long costTypeId) {
		this.costTypeId = costTypeId;
	}
	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}
	public Long getBnumber_id() {
		return bnumber_id;
	}
	
	public void setCostTypeId(long costTypeId) {
		this.costTypeId = costTypeId;
	}
	public String getTranscation_flag() {
		return transcation_flag;
	}
	public void setTranscation_flag(String transcation_flag) {
		this.transcation_flag = transcation_flag;
	}
	public Long getTranscation_id() {
		return transcation_id;
	}
	public void setTranscation_id(Long transcation_id) {
		this.transcation_id = transcation_id;
	}
	public BigDecimal getInit_money() {
		return init_money;
	}
	public void setInit_money(BigDecimal init_money) {
		this.init_money = init_money;
	}
	public List<BudgetNumber> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<BudgetNumber> detailList) {
		this.detailList = detailList;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getUpload() {
		return upload;
	}
	public void setUpload(String upload) {
		this.upload = upload;
	}
	public String getCostCenterName() {
		return costCenterName;
	}
	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
	}
	public String getTheYear() {
		return theYear;
	}
	public void setTheYear(String theYear) {
		this.theYear = theYear;
	}
	public String getTheMonth() {
		return theMonth;
	}
	public void setTheMonth(String theMonth) {
		this.theMonth = theMonth;
	}
	public String getBudgetTpye() {
		return budgetTpye;
	}
	public void setBudgetTpye(String budgetTpye) {
		this.budgetTpye = budgetTpye;
	}
	public String getChannelType() {
		return channelType;
	}
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	public String getCustomerFile() {
		return customerFile;
	}
	public void setCustomerFile(String customerFile) {
		this.customerFile = customerFile;
	}
	public String getCustomerFilePath() {
		return customerFilePath;
	}
	public void setCustomerFilePath(String customerFilePath) {
		this.customerFilePath = customerFilePath;
	}
	public String getAccessFile() {
		return accessFile;
	}
	public void setAccessFile(String accessFile) {
		this.accessFile = accessFile;
	}
	public String getAccessFilePath() {
		return accessFilePath;
	}
	public void setAccessFilePath(String accessFilePath) {
		this.accessFilePath = accessFilePath;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public void setBnumber_id(Long bnumberId) {
		bnumber_id = bnumberId;
	}
	public String getBudget_number() {
		return budget_number;
	}
	public void setBudget_number(String budgetNumber) {
		budget_number = budgetNumber;
	}
	public Long getOrg_id() {
		return org_id;
	}
	public void setOrg_id(Long orgId) {
		org_id = orgId;
	}
	public BigDecimal getBudget_money() {
		return budget_money;
	}
	public void setBudget_money(BigDecimal budgetMoney) {
		budget_money = budgetMoney;
	}
	public String getBudget_money_Str() {
		return budget_money_Str;
	}
	public void setBudget_money_Str(String budgetMoneyStr) {
		budget_money_Str = budgetMoneyStr;
	}
	public Long getThe_month() {
		return the_month;
	}
	public void setThe_month(Long theMonth) {
		the_month = theMonth;
	}
	public Long getThe_year() {
		return the_year;
	}
	public void setThe_year(Long theYear) {
		the_year = theYear;
	}
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String orgName) {
		org_name = orgName;
	}
	public String getBudget_type() {
		return budget_type;
	}
	public void setBudget_type(String budgetType) {
		budget_type = budgetType;
	}
	public String getNumber_flag() {
		return number_flag;
	}
	public void setNumber_flag(String numberFlag) {
		number_flag = numberFlag;
	}
	public Long getTra_id() {
		return tra_id;
	}
	public void setTra_id(Long traId) {
		tra_id = traId;
	}
	public String getTra_flag() {
		return tra_flag;
	}
	public void setTra_flag(String traFlag) {
		tra_flag = traFlag;
	}
	public Date getCreatdate() {
		return creatdate;
	}
	public void setCreatdate(Date creatdate) {
		this.creatdate = creatdate;
	}
	public String getInit_money_Str() {
		return init_money_Str;
	}
	public void setInit_money_Str(String initMoneyStr) {
		init_money_Str = initMoneyStr;
	}
	public Date getLAST_MODIFY() {
		return LAST_MODIFY;
	}
	public void setLAST_MODIFY(Date lASTMODIFY) {
		LAST_MODIFY = lASTMODIFY;
	}
	
	
	public String getCostCentId() {
		return costCentId;
	}
	public void setCostCentId(String costCentId) {
		this.costCentId = costCentId;
	}
	public String getSubjectSapNumber() {
		return subjectSapNumber;
	}
	public void setSubjectSapNumber(String subjectSapNumber) {
		this.subjectSapNumber = subjectSapNumber;
	}
	public String getSapActiveType() {
		return sapActiveType;
	}
	public void setSapActiveType(String sapActiveType) {
		this.sapActiveType = sapActiveType;
	}
	public String getSapKonty() {
		return sapKonty;
	}
	public void setSapKonty(String sapKonty) {
		this.sapKonty = sapKonty;
	}
	public String getSapVersion() {
		return sapVersion;
	}
	public void setSapVersion(String sapVersion) {
		this.sapVersion = sapVersion;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getModifyer() {
		return modifyer;
	}
	public void setModifyer(String modifyer) {
		this.modifyer = modifyer;
	}
	public String getSapCostCenterId() {
		return sapCostCenterId;
	}
	public void setSapCostCenterId(String sapCostCenterId) {
		this.sapCostCenterId = sapCostCenterId;
	}
	
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getSapFlag() {
		return sapFlag;
	}
	public void setSapFlag(String sapFlag) {
		this.sapFlag = sapFlag;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getBudnumType() {
		return budnumType;
	}
	public void setBudnumType(String budnumType) {
		this.budnumType = budnumType;
	}
	public String[] getSubjectSapNumbers() {
		return subjectSapNumbers;
	}
	public void setSubjectSapNumbers(String[] subjectSapNumbers) {
		this.subjectSapNumbers = subjectSapNumbers;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public Long getChannelId() {
		return channelId;
	}
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}
	public String getbId() {
		return bId;
	}
	public void setbId(String bId) {
		this.bId = bId;
	}
}
