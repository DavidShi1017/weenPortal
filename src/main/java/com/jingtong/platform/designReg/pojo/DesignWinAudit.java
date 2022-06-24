package com.jingtong.platform.designReg.pojo;

import java.util.Date;

public class DesignWinAudit {
	private Long id;
	private Long detail_id;
	private String user_id;
	private String user_account;
	private String audit_info;
	private Date create_date;
	private String audit_node;
	
	
	public String getAudit_node() {
		return audit_node;
	}
	public void setAudit_node(String audit_node) {
		this.audit_node = audit_node;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDetail_id() {
		return detail_id;
	}
	public void setDetail_id(Long detail_id) {
		this.detail_id = detail_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_account() {
		return user_account;
	}
	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}
	public String getAudit_info() {
		return audit_info;
	}
	public void setAudit_info(String audit_info) {
		this.audit_info = audit_info;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
}
