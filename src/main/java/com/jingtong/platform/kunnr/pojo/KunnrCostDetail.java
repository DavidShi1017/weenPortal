package com.jingtong.platform.kunnr.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

/***
 * 经销商费用明细表
 * 
 * @author hfwu
 * 
 */
public class KunnrCostDetail extends SearchInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long kunnr_cost_detail_id;// 主键ID crm_sq_kunnr_cost_detail

	private Long kunnr_cost_total_id; // 总单ID

	private String cost_account;// 费用科目

	private String cost_account_name;// 费用科目名称

	private String cost_item; // 费用项目（选择投资分析）

	private String cost_item_name; // 费用项目名称
	
	private Long amount;// 金额

	private String remarks;// 备注

	private Long quantity;// 销售量

	private Long creator; // 创建人

	private Date create_date;// 创建时间

	private String modifier;// 修改人

	private Date modify_date;// 修改时间

	private String status;// Y:正常 N：删除

	public Long getKunnr_cost_detail_id() {
		return kunnr_cost_detail_id;
	}

	public void setKunnr_cost_detail_id(Long kunnr_cost_detail_id) {
		this.kunnr_cost_detail_id = kunnr_cost_detail_id;
	}

	public Long getKunnr_cost_total_id() {
		return kunnr_cost_total_id;
	}

	public void setKunnr_cost_total_id(Long kunnr_cost_total_id) {
		this.kunnr_cost_total_id = kunnr_cost_total_id;
	}

	public String getCost_account() {
		return cost_account;
	}

	public void setCost_account(String cost_account) {
		this.cost_account = cost_account;
	}

	public String getCost_account_name() {
		return cost_account_name;
	}

	public void setCost_account_name(String cost_account_name) {
		this.cost_account_name = cost_account_name;
	}

	public String getCost_item() {
		return cost_item;
	}

	public void setCost_item(String cost_item) {
		this.cost_item = cost_item;
	}

	public String getCost_item_name() {
		return cost_item_name;
	}

	public void setCost_item_name(String cost_item_name) {
		this.cost_item_name = cost_item_name;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
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

}
