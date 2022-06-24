package com.jingtong.platform.province.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;
import com.sun.tools.doclets.formats.html.resources.standard;

/**
 * Ê¡·Ý
 * @author lenovo
 *
 */
public class Province extends SearchInfo{
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String country_code;
	private String country_name;
	private String province_code;
	private String province_name;
	private String oldProvince_code;
	private String search;
	public String getCountry_name() {
		return country_name;
	}
	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProvince_code() {
		return province_code;
	}
	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}
	public String getProvince_name() {
		return province_name;
	}
	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}
	public String getOldProvince_code() {
		return oldProvince_code;
	}
	public void setOldProvince_code(String oldProvince_code) {
		this.oldProvince_code = oldProvince_code;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}


}
