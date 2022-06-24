package com.jingtong.platform.endCustomer.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;
//import com.sun.tools.doclets.formats.html.resources.standard;

/**
 * EC集团
 * @author cl
 * @createDate 2016-5-25
 */
public class ECAlias extends SearchInfo{
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String ec_id;
	private String ec_name;
	private String ec_group;
	private String ec_groupName;
	private String ec_city;
	private String ec_alias_name;
	private String oldEc_alias_name;
	private String alias_city;
	
	
	private Date create_time;	
	private String create_userId;
	private String create_timestr;
	private String modify_timestr;
	private Date modify_time;
	private String modify_userId;
	private String country;
	private String oldEc_id;
	private String disti_groupId;//客户（经销商集团）编码-
	private String quote_id;
	public String getEc_groupName() {
		return ec_groupName;
	}
	public void setEc_groupName(String ec_groupName) {
		this.ec_groupName = ec_groupName;
	}
	public String getDisti_groupId() {
		return disti_groupId;
	}
	public void setDisti_groupId(String disti_groupId) {
		this.disti_groupId = disti_groupId;
	}
	public String getOldEc_id() {
		return oldEc_id;
	}
	public void setOldEc_id(String oldEc_id) {
		this.oldEc_id = oldEc_id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEc_id() {
		return ec_id;
	}
	public void setEc_id(String ec_id) {
		this.ec_id = ec_id;
	}
	public String getEc_name() {
		return ec_name;
	}
	public void setEc_name(String ec_name) {
		this.ec_name = ec_name;
	}
	public String getEc_alias_name() {
		return ec_alias_name;
	}
	public void setEc_alias_name(String ec_alias_name) {
		this.ec_alias_name = ec_alias_name;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getCreate_userId() {
		return create_userId;
	}
	public void setCreate_userId(String create_userId) {
		this.create_userId = create_userId;
	}
	public String getCreate_timestr() {
		return create_timestr;
	}
	public void setCreate_timestr(String create_timestr) {
		this.create_timestr = create_timestr;
	}
	public String getModify_timestr() {
		return modify_timestr;
	}
	public void setModify_timestr(String modify_timestr) {
		this.modify_timestr = modify_timestr;
	}
	public Date getModify_time() {
		return modify_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	public String getModify_userId() {
		return modify_userId;
	}
	public void setModify_userId(String modify_userId) {
		this.modify_userId = modify_userId;
	}
	public String getEc_group() {
		return ec_group;
	}
	public void setEc_group(String ec_group) {
		this.ec_group = ec_group;
	}
	public String getEc_city() {
		return ec_city;
	}
	public void setEc_city(String ec_city) {
		this.ec_city = ec_city;
	}
	public String getAlias_city() {
		return alias_city;
	}
	public void setAlias_city(String alias_city) {
		this.alias_city = alias_city;
	}
	public String getOldEc_alias_name() {
		return oldEc_alias_name;
	}
	public void setOldEc_alias_name(String oldEc_alias_name) {
		this.oldEc_alias_name = oldEc_alias_name;
	}
	public String getQuote_id() {
		return quote_id;
	}
	public void setQuote_id(String quote_id) {
		this.quote_id = quote_id;
	}

	

}
