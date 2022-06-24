package com.jingtong.platform.account.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

public class ExpensesDetail extends SearchInfo {

	private static final long serialVersionUID = -7217743280414889116L;

	
	private Long transaction_id;
	private Long detail_id;
	private Long expenses_total_id;
	
	private String cost_type;			//费用类型
	private String cost_type_content;	//费用类型内容
	
	private Date cost_date;				//费用日期
	private String cost_purpose;		//开支用途
	private Long invoice_num;			//发票张数
	private BigDecimal invoice_amount;	//发票金额
	private BigDecimal audit_money;// 实际金额
	private String cost_memo;// 备注
	private String payee;// 收款人
	private String project;// 项目
	private String project_manager;// 项目经理
	private Date financial_operate_date;// 财务处理时间
	private String status;// 事务状态
	private Date start_date;
	private Date end_date;
	private Long org_id; //成本中心ID
	private String org_name; //成本中心名称
	private String budget_number; //预算编号
	private Long bndetail_id; //预算总表ID
	private Long event_id; //申请ID
	private Long write_event_id; //核销ID
	private  Date businessStartDate; //业务开始时间
	private  Date businessEndDate; //业务结束时间
	private BigDecimal communication_amount;//通讯费用
	private BigDecimal transportation_amount;//交通费用
	private BigDecimal hospitality_amount;	//招待费用
	private BigDecimal travel_amount;	//差旅费用
	private BigDecimal accommodation_amount;//住宿费
	private BigDecimal allowance_amount;	//出差补贴
	private BigDecimal office_amount;	//办公用品
	private BigDecimal express_amount;	//快递费用
    private BigDecimal other_amount; //其他
	
	public Long getDetail_id() {
		return detail_id;
	}
	public void setDetail_id(Long detail_id) {
		this.detail_id = detail_id;
	}
	public Long getExpenses_total_id() {
		return expenses_total_id;
	}
	public void setExpenses_total_id(Long expenses_total_id) {
		this.expenses_total_id = expenses_total_id;
	}
	public String getCost_type() {
		return cost_type;
	}
	public void setCost_type(String cost_type) {
		this.cost_type = cost_type;
	}
	public Date getCost_date() {
		return cost_date;
	}
	public void setCost_date(Date cost_date) {
		this.cost_date = cost_date;
	}
	public String getCost_purpose() {
		return cost_purpose;
	}
	public void setCost_purpose(String cost_purpose) {
		this.cost_purpose = cost_purpose;
	}
	public Long getInvoice_num() {
		return invoice_num;
	}
	public void setInvoice_num(Long invoice_num) {
		this.invoice_num = invoice_num;
	}
	public BigDecimal getInvoice_amount() {
		return invoice_amount;
	}
	public void setInvoice_amount(BigDecimal invoice_amount) {
		this.invoice_amount = invoice_amount;
	}
	public String getCost_memo() {
		return cost_memo;
	}
	public void setCost_memo(String cost_memo) {
		this.cost_memo = cost_memo;
	}
	public String getCost_type_content() {
		return cost_type_content;
	}
	public void setCost_type_content(String cost_type_content) {
		this.cost_type_content = cost_type_content;
	}
	public BigDecimal getAudit_money() {
		return audit_money;
	}
	public void setAudit_money(BigDecimal audit_money) {
		this.audit_money = audit_money;
	}
	public Long getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(Long transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getPayee() {
		return payee;
	}
	public void setPayee(String payee) {
		this.payee = payee;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getProject_manager() {
		return project_manager;
	}
	public void setProject_manager(String project_manager) {
		this.project_manager = project_manager;
	}
	public Date getFinancial_operate_date() {
		return financial_operate_date;
	}
	public void setFinancial_operate_date(Date financial_operate_date) {
		this.financial_operate_date = financial_operate_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public Long getOrg_id() {
		return org_id;
	}
	public void setOrg_id(Long org_id) {
		this.org_id = org_id;
	}
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	public String getBudget_number() {
		return budget_number;
	}
	public void setBudget_number(String budget_number) {
		this.budget_number = budget_number;
	}
	public Long getBndetail_id() {
		return bndetail_id;
	}
	public void setBndetail_id(Long bndetail_id) {
		this.bndetail_id = bndetail_id;
	}
	public Long getEvent_id() {
		return event_id;
	}
	public void setEvent_id(Long event_id) {
		this.event_id = event_id;
	}
	public Long getWrite_event_id() {
		return write_event_id;
	}
	public void setWrite_event_id(Long write_event_id) {
		this.write_event_id = write_event_id;
	}
	public Date getBusinessStartDate() {
		return businessStartDate;
	}
	public void setBusinessStartDate(Date businessStartDate) {
		this.businessStartDate = businessStartDate;
	}
	public Date getBusinessEndDate() {
		return businessEndDate;
	}
	public void setBusinessEndDate(Date businessEndDate) {
		this.businessEndDate = businessEndDate;
	}
	public BigDecimal getCommunication_amount() {
		return communication_amount;
	}
	public void setCommunication_amount(BigDecimal communication_amount) {
		this.communication_amount = communication_amount;
	}
	public BigDecimal getTransportation_amount() {
		return transportation_amount;
	}
	public void setTransportation_amount(BigDecimal transportation_amount) {
		this.transportation_amount = transportation_amount;
	}
	public BigDecimal getHospitality_amount() {
		return hospitality_amount;
	}
	public void setHospitality_amount(BigDecimal hospitality_amount) {
		this.hospitality_amount = hospitality_amount;
	}
	public BigDecimal getTravel_amount() {
		return travel_amount;
	}
	public void setTravel_amount(BigDecimal travel_amount) {
		this.travel_amount = travel_amount;
	}
	public BigDecimal getAccommodation_amount() {
		return accommodation_amount;
	}
	public void setAccommodation_amount(BigDecimal accommodation_amount) {
		this.accommodation_amount = accommodation_amount;
	}
	public BigDecimal getAllowance_amount() {
		return allowance_amount;
	}
	public void setAllowance_amount(BigDecimal allowance_amount) {
		this.allowance_amount = allowance_amount;
	}
	public BigDecimal getOffice_amount() {
		return office_amount;
	}
	public void setOffice_amount(BigDecimal office_amount) {
		this.office_amount = office_amount;
	}
	public BigDecimal getExpress_amount() {
		return express_amount;
	}
	public void setExpress_amount(BigDecimal express_amount) {
		this.express_amount = express_amount;
	}
	public BigDecimal getOther_amount() {
		return other_amount;
	}
	public void setOther_amount(BigDecimal other_amount) {
		this.other_amount = other_amount;
	}
}
