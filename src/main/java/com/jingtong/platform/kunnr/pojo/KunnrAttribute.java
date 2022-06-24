package com.jingtong.platform.kunnr.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

/**
 * 经分销商属性表
 * 
 */

public class KunnrAttribute extends SearchInfo {

	/**
	 * 属性命名kunnr_name 是为了和数据库字段一致
	 */
	private static final long serialVersionUID = -5575029897477626479L;
	private Long id;
	private String kunnr;
	private String kunnr_name;
	private Date  create_date;	//	增加时间
	private String  create_user;	//	增加人
	private Date modify_date;	//	修改时间
	private String  modify_user;	//	修改人
	private String state="1";	//	状态 
	
	private String sql;//用于动态列查询
	 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getCreate_user() {
		return create_user;
	}
	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public String getModify_user() {
		return modify_user;
	}
	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	
}
