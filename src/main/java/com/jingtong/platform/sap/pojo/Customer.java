package com.jingtong.platform.sap.pojo;


import java.io.Serializable;
import java.sql.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

public class Customer extends SearchInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long custNumber;    //门店编码kunnr
	private String custName;    //门店名称
	private String custOrg;     //门店组织
	private String pCustNumber; //上级客户
	private String custLevel;   //门店等级
	private String custType; 	//门店类型
	private String isVip; 		//是否重点门店
	private String custState;	//门店状态
	private String custLegal;	//法人
	private String custContacter;	//客户联系人
	private String contacterSex;	//联系人性别	
	private int contacterAge;	//联系人年龄
	private String contacterPhone;	//联系电话
	private String contacterMobile;	//联系手机	
	private String email;		//邮箱 
	private long custChannel;	//门店渠道
	private String custDelivery;	//配送方式
	private String companyKind;	//门店企业性质	
	private String companySystem;	//门店企业系统	
	private String businessLicensen;	//营业执照
	private Date licensenDate; 	//营业执照有效期
	private Date lastAudit;		//上次年审
	private int tradeYears;		//经营年限
	private String tradeType;	//经营方式
	private String mainBrand;	//主营品牌		
	private Date cooperation; 	//开始合作时间		
	private String companyWebSite;	//门店网站网址
	private String companyPhone;	//门店企业电话
	private String companyFax;		//门店企业传真
	private String companyPost;		//邮编
	private String companyArea;		//面积
	private String companyProvince; //公司所在省
	private String companyCity; 	//公司所在市
	private String companyReg;		//公司所在区
	private String companyLocation; //公司所在地
	private String locationDes;		//具体参考位置
	private String assetInfo;		//资产信息
	private String rowInfo;			//排面信息
	private String hasTallyman;		//有无理货员
	private float longItude;		//经度
	private float latItude;			//纬度
	private String remark;			//备注
	private Date createDate;		//创建时间
	private long createUser;		//创建人
	private Date modifyDate;		//最后修改时间
	private long modifyUser;		//修改人
	private String template;		//客户类别(方案编码)
	private String registeredAddress;//公司注册地址
	private float registeredCapital; //注册资金
	private String idnNumber;		//法人身份证
	public Long getCustNumber() {
		return custNumber;
	}
	public void setCustNumber(Long custNumber) {
		this.custNumber = custNumber;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustOrg() {
		return custOrg;
	}
	public void setCustOrg(String custOrg) {
		this.custOrg = custOrg;
	}
	public String getpCustNumber() {
		return pCustNumber;
	}
	public void setpCustNumber(String pCustNumber) {
		this.pCustNumber = pCustNumber;
	}
	public String getCustLevel() {
		return custLevel;
	}
	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public String getIsVip() {
		return isVip;
	}
	public void setIsVip(String isVip) {
		this.isVip = isVip;
	}
	public String getCustState() {
		return custState;
	}
	public void setCustState(String custState) {
		this.custState = custState;
	}
	public String getCustLegal() {
		return custLegal;
	}
	public void setCustLegal(String custLegal) {
		this.custLegal = custLegal;
	}
	public String getCustContacter() {
		return custContacter;
	}
	public void setCustContacter(String custContacter) {
		this.custContacter = custContacter;
	}
	public String getContacterSex() {
		return contacterSex;
	}
	public void setContacterSex(String contacterSex) {
		this.contacterSex = contacterSex;
	}
	public int getContacterAge() {
		return contacterAge;
	}
	public void setContacterAge(int contacterAge) {
		this.contacterAge = contacterAge;
	}
	public String getContacterPhone() {
		return contacterPhone;
	}
	public void setContacterPhone(String contacterPhone) {
		this.contacterPhone = contacterPhone;
	}
	public String getContacterMobile() {
		return contacterMobile;
	}
	public void setContacterMobile(String contacterMobile) {
		this.contacterMobile = contacterMobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getCustChannel() {
		return custChannel;
	}
	public void setCustChannel(long custChannel) {
		this.custChannel = custChannel;
	}
	public String getCustDelivery() {
		return custDelivery;
	}
	public void setCustDelivery(String custDelivery) {
		this.custDelivery = custDelivery;
	}
	public String getCompanyKind() {
		return companyKind;
	}
	public void setCompanyKind(String companyKind) {
		this.companyKind = companyKind;
	}
	public String getCompanySystem() {
		return companySystem;
	}
	public void setCompanySystem(String companySystem) {
		this.companySystem = companySystem;
	}  
	public String getBusinessLicensen() {
		return businessLicensen;
	}
	public void setBusinessLicensen(String businessLicensen) {
		this.businessLicensen = businessLicensen;
	}
	public Date getLicensenDate() {
		return licensenDate;
	}
	public void setLicensenDate(Date licensenDate) {
		this.licensenDate = licensenDate;
	}
	public Date getLastAudit() {
		return lastAudit;
	}
	public void setLastAudit(Date lastAudit) {
		this.lastAudit = lastAudit;
	}
	public int getTradeYears() {
		return tradeYears;
	}
	public void setTradeYears(int tradeYears) {
		this.tradeYears = tradeYears;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getMainBrand() {
		return mainBrand;
	}
	public void setMainBrand(String mainBrand) {
		this.mainBrand = mainBrand;
	}
	public Date getCooperation() {
		return cooperation;
	}
	public void setCooperation(Date cooperation) {
		this.cooperation = cooperation;
	}
	public String getCompanyWebSite() {
		return companyWebSite;
	}
	public void setCompanyWebSite(String companyWebSite) {
		this.companyWebSite = companyWebSite;
	}
	public String getCompanyPhone() {
		return companyPhone;
	}
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	public String getCompanyFax() {
		return companyFax;
	}
	public void setCompanyFax(String companyFax) {
		this.companyFax = companyFax;
	}
	public String getCompanyPost() {
		return companyPost;
	}
	public void setCompanyPost(String companyPost) {
		this.companyPost = companyPost;
	}
	public String getCompanyArea() {
		return companyArea;
	}
	public void setCompanyArea(String companyArea) {
		this.companyArea = companyArea;
	}
	public String getCompanyProvince() {
		return companyProvince;
	}
	public void setCompanyProvince(String companyProvince) {
		this.companyProvince = companyProvince;
	}
	public String getCompanyCity() {
		return companyCity;
	}
	public void setCompanyCity(String companyCity) {
		this.companyCity = companyCity;
	}
	public String getCompanyReg() {
		return companyReg;
	}
	public void setCompanyReg(String companyReg) {
		this.companyReg = companyReg;
	}
	public String getCompanyLocation() {
		return companyLocation;
	}
	public void setCompanyLocation(String companyLocation) {
		this.companyLocation = companyLocation;
	}
	public String getLocationDes() {
		return locationDes;
	}
	public void setLocationDes(String locationDes) {
		this.locationDes = locationDes;
	}
	public String getAssetInfo() {
		return assetInfo;
	}
	public void setAssetInfo(String assetInfo) {
		this.assetInfo = assetInfo;
	}
	public String getRowInfo() {
		return rowInfo;
	}
	public void setRowInfo(String rowInfo) {
		this.rowInfo = rowInfo;
	}
	public String getHasTallyman() {
		return hasTallyman;
	}
	public void setHasTallyman(String hasTallyman) {
		this.hasTallyman = hasTallyman;
	}
	public float getLongItude() {
		return longItude;
	}
	public void setLongItude(float longItude) {
		this.longItude = longItude;
	}
	public float getLatItude() {
		return latItude;
	}
	public void setLatItude(float latItude) {
		this.latItude = latItude;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public long getCreateUser() {
		return createUser;
	}
	public void setCreateUser(long createUser) {
		this.createUser = createUser;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public long getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(long modifyUser) {
		this.modifyUser = modifyUser;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getRegisteredAddress() {
		return registeredAddress;
	}
	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}
	public float getRegisteredCapital() {
		return registeredCapital;
	}
	public void setRegisteredCapital(float registeredCapital) {
		this.registeredCapital = registeredCapital;
	}
	public String getIdnNumber() {
		return idnNumber;
	}
	public void setIdnNumber(String idnNumber) {
		this.idnNumber = idnNumber;
	}
	 
	 
}
