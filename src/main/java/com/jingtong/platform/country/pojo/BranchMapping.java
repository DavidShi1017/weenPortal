package com.jingtong.platform.country.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;
/**
 * 价格规则
 * @author yw
 *
 */
public class BranchMapping extends SearchInfo{
	private long id;
	private String disti_hq_soldto ;
	private String region_level2 ; 
	private String  global_account; 
	private String loc_rep_country ;
	private Date create_time ; 
	private Long create_userid;
	private Date update_time ; 
	private Long update_userid;
	
	
	public Long getUpdate_userid() {
		return update_userid;
	}
	public void setUpdate_userid(Long update_userid) {
		this.update_userid = update_userid;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDisti_hq_soldto() {
		return disti_hq_soldto;
	}
	public void setDisti_hq_soldto(String disti_hq_soldto) {
		this.disti_hq_soldto = disti_hq_soldto;
	}
	public String getRegion_level2() {
		return region_level2;
	}
	public void setRegion_level2(String region_level2) {
		this.region_level2 = region_level2;
	}
	public String getGlobal_account() {
		return global_account;
	}
	public void setGlobal_account(String global_account) {
		this.global_account = global_account;
	}
	public String getLoc_rep_country() {
		return loc_rep_country;
	}
	public void setLoc_rep_country(String loc_rep_country) {
		this.loc_rep_country = loc_rep_country;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Long getCreate_userid() {
		return create_userid;
	}
	public void setCreate_userid(Long create_userid) {
		this.create_userid = create_userid;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	
	
}
