package com.jingtong.platform.endCustomer.pojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

/**
 * 终端客户
 * 
 * @author yw
 *
 */
public class EndCustomer extends SearchInfo {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private long id;
    private String disti_groupId;// 客户（经销商集团）编码-
    private String disti_customer_id;// 客户（经销商）编码-
    private String disti_customer_name;// 客户（经销商）名称-
    private String end_customer_groupId;// 终端客户组集团编码-
    private String end_customer_groupName;// 终端客户组集团-
    private String end_customer_id;// 终端客户编码-
    private String end_customer_name;// 终端客户名称-
    private String customer_type;// 终端客户类型-
    private String customer_typeName;// 终端客户类型-
    private String customer_property;// 终端客户属性-
    private String customer_propertyName;// 终端客户属性-
    private String currency_code;// 货币代码-
    private String currency_name;// 货币代码-
    private String country;// 国家代码-
    private String country_name;// 国家代码-
    private String province;// 省、洲State-
    private String address;// 地址-
    private String contact_name;// 联系人-
    private String tel;// 电话-
    private int state;// 状态status 0新建，1审核通过，2审核未过，3冻结，4解冻-
    private String audit_opinion;// 审核意见
    private String remark;// 备注
    private int sync_state;// 同步状态
    private String sync_userId;// 同步时间
    private String sysnc_exception;// 同步异常
    private String org_code;// 所属组织(对应Disti的District，防止串货)
    private String create_userId;// 创建人-
    private Date create_time;// 创建时间-
    private String modify_userId;//
    private Date modify_time;//
    private String isCheck;//
    private String states;//
    private String ecGroupState;//
    private String city;// 城市
    private String salesOrg;
    private String zip;// 邮编
    private String file_name;
    private String file_path;
    private String aliasId;
    private String ec_alias_name;
    private String oldCity;
    private String alias_city;
    private String noPCEC;
    private String pcec;
    private String quote_id;
    private String e_name;
    private String p_name;
    private String e_country;
    private String p_country;
    private String e_province;
    private String p_province;
    private String e_city;
    private String p_city;
    private String e_zip;
    private String p_zip;
    private String editGroupMark;
    private String new_hierarchy;
    private String tier;
    private String newHierarchyString;
    private String tierString;
    private Date noTranBegin;
    private Date noTranEnd;
    private String segment;
    public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}
	private String application;
    
    public String getEditGroupMark() {
        return editGroupMark;
    }

    public void setEditGroupMark(String editGroupMark) {
        this.editGroupMark = editGroupMark;
    }

    public String getE_country() {
        return e_country;
    }

    public void setE_country(String e_country) {
        this.e_country = e_country;
    }

    public String getP_country() {
        return p_country;
    }

    public void setP_country(String p_country) {
        this.p_country = p_country;
    }

    public String getE_province() {
        return e_province;
    }

    public void setE_province(String e_province) {
        this.e_province = e_province;
    }

    public String getP_province() {
        return p_province;
    }

    public void setP_province(String p_province) {
        this.p_province = p_province;
    }

    public String getE_city() {
        return e_city;
    }

    public void setE_city(String e_city) {
        this.e_city = e_city;
    }

    public String getP_city() {
        return p_city;
    }

    public void setP_city(String p_city) {
        this.p_city = p_city;
    }

    public String getE_zip() {
        return e_zip;
    }

    public void setE_zip(String e_zip) {
        this.e_zip = e_zip;
    }

    public String getP_zip() {
        return p_zip;
    }

    public void setP_zip(String p_zip) {
        this.p_zip = p_zip;
    }

    public String getPcec() {
        return pcec;
    }

    public void setPcec(String pcec) {
        this.pcec = pcec;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getQuote_id() {
        return quote_id;
    }

    public void setQuote_id(String quote_id) {
        this.quote_id = quote_id;
    }

    public String getNoPCEC() {
        return noPCEC;
    }

    public void setNoPCEC(String noPCEC) {
        this.noPCEC = noPCEC;
    }

    public String getOldCity() {
        return oldCity;
    }

    public void setOldCity(String oldCity) {
        this.oldCity = oldCity;
    }

    public String getAlias_city() {
        return alias_city;
    }

    public void setAlias_city(String alias_city) {
        this.alias_city = alias_city;
    }

    public String getAliasId() {
        return aliasId;
    }

    public void setAliasId(String aliasId) {
        this.aliasId = aliasId;
    }

    public String getEc_alias_name() {
        return ec_alias_name;
    }

    public void setEc_alias_name(String ec_alias_name) {
        this.ec_alias_name = ec_alias_name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDisti_groupId() {
        return disti_groupId;
    }

    public String getEnd_customer_id() {
        return end_customer_id;
    }

    public void setEnd_customer_id(String end_customer_id) {
        this.end_customer_id = end_customer_id;
    }

    public void setDisti_groupId(String disti_groupId) {
        this.disti_groupId = disti_groupId;
    }

    public String getDisti_customer_id() {
        return disti_customer_id;
    }

    public void setDisti_customer_id(String disti_customer_id) {
        this.disti_customer_id = disti_customer_id;
    }

    public String getSalesOrg() {
        return salesOrg;
    }

    public void setSalesOrg(String salesOrg) {
        this.salesOrg = salesOrg;
    }

    public String getEnd_customer_groupId() {
        return end_customer_groupId;
    }

    public void setEnd_customer_groupId(String end_customer_groupId) {
        this.end_customer_groupId = end_customer_groupId;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getCurrency_name() {
        return currency_name;
    }

    public void setCurrency_name(String currency_name) {
        this.currency_name = currency_name;
    }

    public String getDisti_customer_name() {
        return disti_customer_name;
    }

    public void setDisti_customer_name(String disti_customer_name) {
        this.disti_customer_name = disti_customer_name;
    }

    public String getEnd_customer_name() {
        return end_customer_name;
    }

    public void setEnd_customer_name(String end_customer_name) {
        this.end_customer_name = end_customer_name;
    }

    public String getCustomer_type() {
        return customer_type;
    }

    public void setCustomer_type(String customer_type) {
        this.customer_type = customer_type;
    }

    public String getCustomer_property() {
        return customer_property;
    }

    public void setCustomer_property(String customer_property) {
        this.customer_property = customer_property;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getAudit_opinion() {
        return audit_opinion;
    }

    public void setAudit_opinion(String audit_opinion) {
        this.audit_opinion = audit_opinion;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getSync_state() {
        return sync_state;
    }

    public void setSync_state(int sync_state) {
        this.sync_state = sync_state;
    }

    public String getSync_userId() {
        return sync_userId;
    }

    public void setSync_userId(String sync_userId) {
        this.sync_userId = sync_userId;
    }

    public String getSysnc_exception() {
        return sysnc_exception;
    }

    public void setSysnc_exception(String sysnc_exception) {
        this.sysnc_exception = sysnc_exception;
    }

    public String getOrg_code() {
        return org_code;
    }

    public void setOrg_code(String org_code) {
        this.org_code = org_code;
    }

    public String getCreate_userId() {
        return create_userId;
    }

    public void setCreate_userId(String create_userId) {
        this.create_userId = create_userId;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getCustomer_typeName() {
        return customer_typeName;
    }

    public void setCustomer_typeName(String customer_typeName) {
        this.customer_typeName = customer_typeName;
    }

    public String getCustomer_propertyName() {
        return customer_propertyName;
    }

    public void setCustomer_propertyName(String customer_propertyName) {
        this.customer_propertyName = customer_propertyName;
    }

    public String getModify_userId() {
        return modify_userId;
    }

    public void setModify_userId(String modify_userId) {
        this.modify_userId = modify_userId;
    }

    public Date getModify_time() {
        return modify_time;
    }

    public void setModify_time(Date modify_time) {
        this.modify_time = modify_time;
    }

    public String getEnd_customer_groupName() {
        return end_customer_groupName;
    }

    public void setEnd_customer_groupName(String end_customer_groupName) {
        this.end_customer_groupName = end_customer_groupName;
    }

    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }

    public String getEcGroupState() {
        return ecGroupState;
    }

    public void setEcGroupState(String ecGroupState) {
        this.ecGroupState = ecGroupState;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getNew_hierarchy() {
        return new_hierarchy;
    }

    public void setNew_hierarchy(String new_hierarchy) {
        this.new_hierarchy = new_hierarchy;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getNewHierarchyString() {
        return newHierarchyString;
    }

    public void setNewHierarchyString(String newHierarchyString) {
        this.newHierarchyString = newHierarchyString;
    }

    public String getTierString() {
        return tierString;
    }

    public void setTierString(String tierString) {
        this.tierString = tierString;
    }
    
    public void setNoTranBegin(String noTranBegin) throws ParseException {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
    	this.noTranBegin = simpleDateFormat.parse(noTranBegin);
    }
    public Date getNoTranBegin() {
    	return this.noTranBegin;
    }

    public void setNoTranEnd(String noTranEnd) throws ParseException {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
    	this.noTranEnd = simpleDateFormat.parse(noTranEnd);
    }
    public Date getNoTranEnd() {
    	return this.noTranEnd;
    }
}
