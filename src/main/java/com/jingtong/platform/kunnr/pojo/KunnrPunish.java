package com.jingtong.platform.kunnr.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

/**
 * 经分销商违规记录
 * 
 * @author mk
 * 
 */

public class KunnrPunish extends SearchInfo {

 	private static final long serialVersionUID = -5575029897477626479L;
	private Long punish_id ;//number(9) not null primary key,经销商处罚记录主键
    private String kunnr;// varchar2(20) not null,经销商编码
    private String violations;// varchar2(50),处罚事件
    private String penalty_amount;// varchar2(20),处罚金额
    private Date bad_time; //date,处罚时间
    private String creator;// varchar2(20), 创建人
    private Date create_date;// date 创建时间
    
    
	public Long getPunish_id() {
		return punish_id;
	}
	public void setPunish_id(Long punish_id) {
		this.punish_id = punish_id;
	}
	public String getKunnr() {
		return kunnr;
	}
	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}
	public String getViolations() {
		return violations;
	}
	public void setViolations(String violations) {
		this.violations = violations;
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
	public String getPenalty_amount() {
		return penalty_amount;
	}
	public void setPenalty_amount(String penalty_amount) {
		this.penalty_amount = penalty_amount;
	}
	public Date getBad_time() {
		return bad_time;
	}
	public void setBad_time(Date bad_time) {
		this.bad_time = bad_time;
	}
	
}
