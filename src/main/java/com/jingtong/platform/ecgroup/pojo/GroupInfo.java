package com.jingtong.platform.ecgroup.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;
import com.sun.tools.doclets.formats.html.resources.standard;

/**
 * EC¼¯ÍÅ
 * @author cl
 * @createDate 2016-5-25
 */
public class GroupInfo extends SearchInfo{
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String ecGroup_id;
	private String ecGroup_name;
	private int state;
	private String states;
	
	private Date create_time;
	
	private String create_userId;
	
	
	private String org_code;
	private String oldName;
	private String create_timestr;
	private String modify_timestr;

	private Date modify_time;
	private String modify_userId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEcGroup_id() {
		return ecGroup_id;
	}
	public void setEcGroup_id(String ecGroup_id) {
		this.ecGroup_id = ecGroup_id;
	}
	public String getEcGroup_name() {
		return ecGroup_name;
	}
	public void setEcGroup_name(String ecGroup_name) {
		this.ecGroup_name = ecGroup_name;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
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
	public String getOrg_code() {
		return org_code;
	}
	public void setOrg_code(String org_code) {
		this.org_code = org_code;
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

	public String getOldName() {
		return oldName;
	}
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
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
	

}
