package com.jingtong.platform.account.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

public class BNumberDetail extends SearchInfo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1144715639681021409L;
	Long BNDETAIL_ID;// 序号
	String budget_number;// 预算编号
	String budget_number2;//调整到的费用编号
	BigDecimal LAST_MONEY;// 操作前的金额
	BigDecimal op_money1;// 此次操作金额
	String op_money_Str;
	String operatorid;// 操作人的id
	String operatorname;// 操作人姓名
	String op_orgid;// 操作人的组织id
	String op_orgname;// 操作人的组织名称
	String reason;// 操作原因
	Date op_date;// 操作时间
	Long bnumber_id;// 预算编号表的id
	Long bnumber_id_to;//调整到的费用编号序号
	String xml_id;
	// modify by xujiakun 2009-11-17
	Long plan_id;// 计划ID
	Long detail_id;// 明细ID
	Long BMAT_ID;// 终端品id
	Long REASON_NUM;// 操作原因编号
	String CHECK_FLAG;// 显示标记(Y---显示； N---不显示，上月计划提报被系统自动终止)
	Date LAST_MODIFY;// 修改时间
	// add by kchen
	private String wrongMsg;
	private String oa_id;
	/**
	 * 受款人/出差人
	 */
	private Long userId;
	/**
	 * 导入行序号 add by pwang
	 */
	private Long line_num;
	/**
	 * 新增品类、分类、项目、科目
	 * add by xxhu 2012-1-6
	 */
	private String trademark;//品牌
	private String category;//分类
	private String items;//项目
	private String subject;//科目
	
	public String getXml_id() {
		return xml_id;
	}

	public void setXml_id(String xml_id) {
		this.xml_id = xml_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getBudget_number2() {
		return budget_number2;
	}

	public void setBudget_number2(String budget_number2) {
		this.budget_number2 = budget_number2;
	}
	public Long getBnumber_id_to() {
		return bnumber_id_to;
	}

	public void setBnumber_id_to(Long bnumber_id_to) {
		this.bnumber_id_to = bnumber_id_to;
	}

	public Long getBNDETAIL_ID() {
		return BNDETAIL_ID;
	}

	public void setBNDETAIL_ID(Long bndetail_id) {
		BNDETAIL_ID = bndetail_id;
	}

	public String getBudget_number() {
		return budget_number;
	}

	public void setBudget_number(String budget_number) {
		this.budget_number = budget_number;
	}

	public BigDecimal getLAST_MONEY() {
		return LAST_MONEY;
	}

	public void setLAST_MONEY(BigDecimal last_money) {
		LAST_MONEY = last_money;
	}

	public BigDecimal getOp_money1() {
		return op_money1;
	}

	public void setOp_money1(BigDecimal op_money1) {
		this.op_money1 = op_money1;
	}

	public String getOp_money_Str() {
		return op_money_Str;
	}

	public void setOp_money_Str(String op_money_Str) {
		this.op_money_Str = op_money_Str;
	}

	public String getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}

	public String getOperatorname() {
		return operatorname;
	}

	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
	}

	public String getOp_orgid() {
		return op_orgid;
	}

	public void setOp_orgid(String op_orgid) {
		this.op_orgid = op_orgid;
	}

	public String getOp_orgname() {
		return op_orgname;
	}

	public void setOp_orgname(String op_orgname) {
		this.op_orgname = op_orgname;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getOp_date() {
		return op_date;
	}

	public void setOp_date(Date op_date) {
		this.op_date = op_date;
	}

	public Long getBnumber_id() {
		return bnumber_id;
	}

	public void setBnumber_id(Long bnumber_id) {
		this.bnumber_id = bnumber_id;
	}

	public Long getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(Long plan_id) {
		this.plan_id = plan_id;
	}

	public Long getDetail_id() {
		return detail_id;
	}

	public void setDetail_id(Long detail_id) {
		this.detail_id = detail_id;
	}

	public Long getBMAT_ID() {
		return BMAT_ID;
	}

	public void setBMAT_ID(Long bmat_id) {
		BMAT_ID = bmat_id;
	}

	public Long getREASON_NUM() {
		return REASON_NUM;
	}

	public void setREASON_NUM(Long reason_num) {
		REASON_NUM = reason_num;
	}

	public String getCHECK_FLAG() {
		return CHECK_FLAG;
	}

	public void setCHECK_FLAG(String check_flag) {
		CHECK_FLAG = check_flag;
	}

	public Date getLAST_MODIFY() {
		return LAST_MODIFY;
	}

	public void setLAST_MODIFY(Date last_modify) {
		LAST_MODIFY = last_modify;
	}

	public String getWrongMsg() {
		return wrongMsg;
	}

	public void setWrongMsg(String wrongMsg) {
		this.wrongMsg = wrongMsg;
	}

	public String getOa_id() {
		return oa_id;
	}

	public void setOa_id(String oaId) {
		oa_id = oaId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getLine_num() {
		return line_num;
	}

	public void setLine_num(Long line_num) {
		this.line_num = line_num;
	}

	public String getTrademark() {
		return trademark;
	}

	public void setTrademark(String trademark) {
		this.trademark = trademark;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	
}
