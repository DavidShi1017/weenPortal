package com.jingtong.platform.country.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;
//import com.sun.tools.doclets.formats.html.resources.standard;

/**
 * ¹ú¼Ò
 * 
 * @author lenovo
 *
 */
public class Country extends SearchInfo {
    private static final long serialVersionUID = 1L;

    private long id;
    private String country_code;
    private String country_name;
    private String oldCountry_code;
    private String search;
    private String org_id;
    private String org_code;
    private String org_name;

    private String province_code;
    private String province_name;

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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getOldCountry_code() {
        return oldCountry_code;
    }

    public void setOldCountry_code(String oldCountry_code) {
        this.oldCountry_code = oldCountry_code;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getOrg_code() {
        return org_code;
    }

    public void setOrg_code(String org_code) {
        this.org_code = org_code;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

}
