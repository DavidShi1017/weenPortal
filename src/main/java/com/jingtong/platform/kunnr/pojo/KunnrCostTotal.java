package com.jingtong.platform.kunnr.pojo;

import java.util.Date;
import java.util.List;

import com.jingtong.platform.base.pojo.SearchInfo;
/***
 * 经销商费用总表
 * @author hfwu
 *
 */
public class KunnrCostTotal extends SearchInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long kunnr_cost_total_id; // 主键ID crm_sq_kunnr_cost_total
	
	private String apply_event_id; // 事务编号
	
	private String org_id;// 申请组织

	private Date apply_date;// 申请日期

	private String kunnr;// 经销商编号

	private String kunnr_name;// 经销商名称

	private String payment;// 付款方式

	private String total_amount;// 总金额

	private String creator;// 创建人

	private Date create_date;// 创建时间

	private String modifier;// 修改人

	private Date modify_date;// 修改时间

	private String status;// Y:正常 N：删除
	
	private List<KunnrCostDetail> kunnrCostDetailList;
	
	private Date startDate;// 起始日期
	
	private Date endDate;// 结束日期
	
	private String org_name; //组织名称
	
	private String creator_name;//创建人
	
	private String flag;  //事务标志

	public Long getKunnr_cost_total_id() {
		return kunnr_cost_total_id;
	}

	public void setKunnr_cost_total_id(Long kunnr_cost_total_id) {
		this.kunnr_cost_total_id = kunnr_cost_total_id;
	}

	public String getApply_event_id() {
		return apply_event_id;
	}

	public void setApply_event_id(String apply_event_id) {
		this.apply_event_id = apply_event_id;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public Date getApply_date() {
		return apply_date;
	}

	public void setApply_date(Date apply_date) {
		this.apply_date = apply_date;
	}

	public String getKunnr() {
		return kunnr;
	}

	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}

	public String getKunnr_name() {
		return kunnr_name;
	}

	public void setKunnr_name(String kunnr_name) {
		this.kunnr_name = kunnr_name;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<KunnrCostDetail> getKunnrCostDetailList() {
		return kunnrCostDetailList;
	}

	public void setKunnrCostDetailList(List<KunnrCostDetail> kunnrCostDetailList) {
		this.kunnrCostDetailList = kunnrCostDetailList;
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

	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public String getCreator_name() {
		return creator_name;
	}

	public void setCreator_name(String creator_name) {
		this.creator_name = creator_name;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
