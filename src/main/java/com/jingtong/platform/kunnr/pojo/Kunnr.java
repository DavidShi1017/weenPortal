package com.jingtong.platform.kunnr.pojo;

import java.util.Date;
import java.util.List;

import com.jingtong.platform.base.pojo.SearchInfo;

/**
 * 经分销商基礎信息
 * 
 * @author xxping
 * 
 */

public class Kunnr extends SearchInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5575029897477626479L;
	private Long id;
	private String kunnr;
	private String locco;
	private String name1;
	private String name3;
	private String sex;
	private Integer age;
	private String mobNumber;
	private String bukrs;// 公司代码
	private String vkorg;// 销售组织
	private String vtweg;// 分销渠道
	private String spart;// 产品组
	private String ktokd;// 客户科目组
	private Long channelId;// 客户渠道
	private String channelName;// 客户渠道名称 客户分类
	private String konzs;// 上级经销商
	private String konzsTxt;//经销商名称
	
	private String bank;
	private String bankAccount;
	private String healthNumber;
	private String stceg;// 税务登记证
	private String businessLicense;
	private String kverm;// 纳税人类型(账户备注)
	private Date lastAnnual;
	private String province;
	private String city;
	private String area;
	private String town;
	private String street1;// 参考位置
	private String kpPhone;//开票电话
	private String telNumber;// 公司联系电话
	private String street;// 收货地址
	private String name102;// 收货人
	private String name102tel;// 收货人电话
	private String name102mob;// 收货人手机
	private String maximum;      //最大通行车型      add 2013/08/12   VSART
	
	private String maximumTxt;
	private String faxNumber;
	private String zip;
	private Integer businessLife;
	private Date cooperationStart;
	private Date cooperationEnd;
	private String createReason;
	private Long createUserId;
	private String createUser;
	private String createDate;
	private String status;
	private List<KunnrBusiness> kunnrbusinessList;// 详细信息
	private List<KunnrAddress> kunnrAddressList;// 收货地址列表
	private List<KunnrBrand> kunnrBrandList;// 经营品牌列表
	private List<KunnrAcount> kunnrAcountList;// 折扣列表
	private List<KunnrLicense> kunnrLicenseList;//证照列表
	private List<KunnrBanks> kunnrBanklist;//开户银行
	private List<KunnrPolicy> kunnrPolicyList;//经销商政策

	private List<KunnrSalesArea> kunnrSalesAreaList; //销售范围
	private String businessCompetent;
	private String competentMobile;
	private String businessManager;
	private String managerMobile;
	private String werks;// 工厂
	private Double lastyearSales;
	private Double theyearSales;
	private String coverArea;

	private String killBrand;// 修改时被删除的品牌
	private String killAcount;// 修改时被删除的折扣
	private List<KunnrSalesArea> killSalesArea; //修改时被删除的销售范围
	
	private String warehouseArea;            //仓库面积
	private String natureEnterprise;            //企业性质
	private String fpRecipient;       //发票寄件人
	private String fpAddress;      //发票寄送地址
	private String fpContactPhone;     //发票联系人电话
	private String orgName;             //所属组织
	private String zcAddress;      //公司注册地址
	
	private String noticeFile1;// 整改通知书1
	private String noticeFilePath1;
	private String noticeFile2;// 整改通知书2
	private String noticeFilePath2;
	private String noticeFile3;// 整改通知书3
	private String noticeFilePath3;
	
	private String nameUpdateFile;// 名称变更证明
	private String nameUpdatePath;
	
	private String checkOpId;      //经销商目标量审核人 
	
	private String orgId;
	private String bhxjFlag;
	
	private String kunnrLeader;
	private String kunnrPhone;
	
	private String updateType;
	
	private String kunnrErp;  //ERP的客户编号
	
	private String customerType;

	private String kaManager;
	private String  isautodemand;//自动分货
	
	private String billType;//增值税
 
	private String kvgr4;//客户规模
 
	private String isMortgage;//是否抵押
 
	private String[] orgStr;//组织权限
	
	private String tel_number;
	private String emp_code;
	private String emp_name;
	private String emp_phone;
	private String emp_mobile_phone;
	
	
	 
	public String[] getOrgStr() {
		return orgStr;
	}

	public void setOrgStr(String[] orgStr) {
		this.orgStr = orgStr;
	}

	public String getKvgr4() {
		return kvgr4;
	}

	public void setKvgr4(String kvgr4) {
		this.kvgr4 = kvgr4;
	}

 	public String getIsMortgage() {
		return isMortgage;
	}

	public void setIsMortgage(String isMortgage) {
		this.isMortgage = isMortgage;
	}

 	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getIsautodemand() {
		return isautodemand;
	}

	public void setIsautodemand(String isautodemand) {
		this.isautodemand = isautodemand;
	}

	public String getKaManager() {
		return kaManager;
	}

	public void setKaManager(String kaManager) {
		this.kaManager = kaManager;
	}

	public String getKillBrand() {
		return killBrand;
	}

	public void setKillBrand(String killBrand) {
		this.killBrand = killBrand;
	}

	public String getKillAcount() {
		return killAcount;
	}

	public void setKillAcount(String killAcount) {
		this.killAcount = killAcount;
	}

	public String getCompetentMobile() {
		return competentMobile;
	}

	public void setCompetentMobile(String competentMobile) {
		this.competentMobile = competentMobile;
	}

	public String getManagerMobile() {
		return managerMobile;
	}

	public void setManagerMobile(String managerMobile) {
		this.managerMobile = managerMobile;
	}

	public String getBusinessCompetent() {
		return businessCompetent;
	}

	public void setBusinessCompetent(String businessCompetent) {
		this.businessCompetent = businessCompetent;
	}

	public String getBusinessManager() {
		return businessManager;
	}

	public void setBusinessManager(String businessManager) {
		this.businessManager = businessManager;
	}

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

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}


	public String getName3() {
		return name3;
	}

	public void setName3(String name3) {
		this.name3 = name3;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getMobNumber() {
		return mobNumber;
	}

	public void setMobNumber(String mobNumber) {
		this.mobNumber = mobNumber;
	}

	public String getBukrs() {
		return bukrs;
	}

	public void setBukrs(String bukrs) {
		this.bukrs = bukrs;
	}

	public String getVkorg() {
		return vkorg;
	}

	public void setVkorg(String vkorg) {
		this.vkorg = vkorg;
	}

	public String getVtweg() {
		return vtweg;
	}

	public void setVtweg(String vtweg) {
		this.vtweg = vtweg;
	}

	public String getSpart() {
		return spart;
	}

	public void setSpart(String spart) {
		this.spart = spart;
	}

	public String getKtokd() {
		return ktokd;
	}

	public void setKtokd(String ktokd) {
		this.ktokd = ktokd;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public String getKonzs() {
		return konzs;
	}

	public void setKonzs(String konzs) {
		this.konzs = konzs;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getHealthNumber() {
		return healthNumber;
	}

	public void setHealthNumber(String healthNumber) {
		this.healthNumber = healthNumber;
	}

	public String getStceg() {
		return stceg;
	}

	public void setStceg(String stceg) {
		this.stceg = stceg;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getKverm() {
		return kverm;
	}

	public void setKverm(String kverm) {
		this.kverm = kverm;
	}

	public Date getLastAnnual() {
		return lastAnnual;
	}

	public void setLastAnnual(Date lastAnnual) {
		this.lastAnnual = lastAnnual;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Integer getBusinessLife() {
		return businessLife;
	}

	public void setBusinessLife(Integer businessLife) {
		this.businessLife = businessLife;
	}

	public Date getCooperationStart() {
		return cooperationStart;
	}

	public void setCooperationStart(Date cooperationStart) {
		this.cooperationStart = cooperationStart;
	}

	public Date getCooperationEnd() {
		return cooperationEnd;
	}

	public void setCooperationEnd(Date cooperationEnd) {
		this.cooperationEnd = cooperationEnd;
	}

	public String getCreateReason() {
		return createReason;
	}

	public void setCreateReason(String createReason) {
		this.createReason = createReason;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<KunnrBusiness> getKunnrbusinessList() {
		return kunnrbusinessList;
	}

	public void setKunnrbusinessList(List<KunnrBusiness> kunnrbusinessList) {
		this.kunnrbusinessList = kunnrbusinessList;
	}

	public List<KunnrAddress> getKunnrAddressList() {
		return kunnrAddressList;
	}

	public void setKunnrAddressList(List<KunnrAddress> kunnrAddressList) {
		this.kunnrAddressList = kunnrAddressList;
	}

	public List<KunnrBrand> getKunnrBrandList() {
		return kunnrBrandList;
	}

	public void setKunnrBrandList(List<KunnrBrand> kunnrBrandList) {
		this.kunnrBrandList = kunnrBrandList;
	}

	public List<KunnrAcount> getKunnrAcountList() {
		return kunnrAcountList;
	}

	public void setKunnrAcountList(List<KunnrAcount> kunnrAcountList) {
		this.kunnrAcountList = kunnrAcountList;
	}

	public String getName102() {
		return name102;
	}

	public void setName102(String name102) {
		this.name102 = name102;
	}

	public String getName102tel() {
		return name102tel;
	}

	public void setName102tel(String name102tel) {
		this.name102tel = name102tel;
	}

	public String getName102mob() {
		return name102mob;
	}

	public void setName102mob(String name102mob) {
		this.name102mob = name102mob;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getLocco() {
		return locco;
	}

	public void setLocco(String locco) {
		this.locco = locco;
	}

	public String getWerks() {
		return werks;
	}

	public void setWerks(String werks) {
		this.werks = werks;
	}

	public Double getLastyearSales() {
		return lastyearSales;
	}

	public void setLastyearSales(Double lastyearSales) {
		this.lastyearSales = lastyearSales;
	}

	public Double getTheyearSales() {
		return theyearSales;
	}

	public void setTheyearSales(Double theyearSales) {
		this.theyearSales = theyearSales;
	}

	public String getCoverArea() {
		return coverArea;
	}

	public void setCoverArea(String coverArea) {
		this.coverArea = coverArea;
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getKpPhone() {
		return kpPhone;
	}

	public void setKpPhone(String kpPhone) {
		this.kpPhone = kpPhone;
	}


	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public List<KunnrLicense> getKunnrLicenseList() {
		return kunnrLicenseList;
	}

	public void setKunnrLicenseList(List<KunnrLicense> kunnrLicenseList) {
		this.kunnrLicenseList = kunnrLicenseList;
	}

	public String getMaximum() {
		return maximum;
	}

	public void setMaximum(String maximum) {
		this.maximum = maximum;
	}

	public String getWarehouseArea() {
		return warehouseArea;
	}

	public void setWarehouseArea(String warehouseArea) {
		this.warehouseArea = warehouseArea;
	}

	public String getNatureEnterprise() {
		return natureEnterprise;
	}

	public void setNatureEnterprise(String natureEnterprise) {
		this.natureEnterprise = natureEnterprise;
	}

	public String getFpRecipient() {
		return fpRecipient;
	}

	public void setFpRecipient(String fpRecipient) {
		this.fpRecipient = fpRecipient;
	}

	public String getFpAddress() {
		return fpAddress;
	}

	public void setFpAddress(String fpAddress) {
		this.fpAddress = fpAddress;
	}

	public String getFpContactPhone() {
		return fpContactPhone;
	}

	public void setFpContactPhone(String fpContactPhone) {
		this.fpContactPhone = fpContactPhone;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getZcAddress() {
		return zcAddress;
	}

	public void setZcAddress(String zcAddress) {
		this.zcAddress = zcAddress;
	}

	public List<KunnrSalesArea> getKunnrSalesAreaList() {
		return kunnrSalesAreaList;
	}

	public void setKunnrSalesAreaList(List<KunnrSalesArea> kunnrSalesAreaList) {
		this.kunnrSalesAreaList = kunnrSalesAreaList;
	}

	
	

	public String getCheckOpId() {
		return checkOpId;
	}

	public void setCheckOpId(String checkOpId) {
		this.checkOpId = checkOpId;
	}

	

	public List<KunnrSalesArea> getKillSalesArea() {
		return killSalesArea;
	}

	public void setKillSalesArea(List<KunnrSalesArea> killSalesArea) {
		this.killSalesArea = killSalesArea;
	}

	public String getNoticeFile1() {
		return noticeFile1;
	}

	public void setNoticeFile1(String noticeFile1) {
		this.noticeFile1 = noticeFile1;
	}

	public String getNoticeFilePath1() {
		return noticeFilePath1;
	}

	public void setNoticeFilePath1(String noticeFilePath1) {
		this.noticeFilePath1 = noticeFilePath1;
	}

	public String getNoticeFile2() {
		return noticeFile2;
	}

	public void setNoticeFile2(String noticeFile2) {
		this.noticeFile2 = noticeFile2;
	}

	public String getNoticeFilePath2() {
		return noticeFilePath2;
	}

	public void setNoticeFilePath2(String noticeFilePath2) {
		this.noticeFilePath2 = noticeFilePath2;
	}

	public String getNoticeFile3() {
		return noticeFile3;
	}

	public void setNoticeFile3(String noticeFile3) {
		this.noticeFile3 = noticeFile3;
	}

	public String getNoticeFilePath3() {
		return noticeFilePath3;
	}

	public void setNoticeFilePath3(String noticeFilePath3) {
		this.noticeFilePath3 = noticeFilePath3;
	}

	public String getKunnrLeader() {
		return kunnrLeader;
	}

	public void setKunnrLeader(String kunnrLeader) {
		this.kunnrLeader = kunnrLeader;
	}

	public String getKunnrPhone() {
		return kunnrPhone;
	}

	public void setKunnrPhone(String kunnrPhone) {
		this.kunnrPhone = kunnrPhone;
	}

	public String getMaximumTxt() {
		return maximumTxt;
	}

	public void setMaximumTxt(String maximumTxt) {
		this.maximumTxt = maximumTxt;
	}

	public String getBhxjFlag() {
		return bhxjFlag;
	}

	public void setBhxjFlag(String bhxjFlag) {
		this.bhxjFlag = bhxjFlag;
	}

	public String getNameUpdateFile() {
		return nameUpdateFile;
	}

	public void setNameUpdateFile(String nameUpdateFile) {
		this.nameUpdateFile = nameUpdateFile;
	}

	public String getNameUpdatePath() {
		return nameUpdatePath;
	}

	public void setNameUpdatePath(String nameUpdatePath) {
		this.nameUpdatePath = nameUpdatePath;
	}

	public String getUpdateType() {
		return updateType;
	}

	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}

	public String getKonzsTxt() {
		return konzsTxt;
	}

	public void setKonzsTxt(String konzsTxt) {
		this.konzsTxt = konzsTxt;
	}

	public List<KunnrBanks> getKunnrBanklist() {
		return kunnrBanklist;
	}

	public void setKunnrBanklist(List<KunnrBanks> kunnrBanklist) {
		this.kunnrBanklist = kunnrBanklist;
	}

	public List<KunnrPolicy> getKunnrPolicyList() {
		return kunnrPolicyList;
	}

	public void setKunnrPolicyList(List<KunnrPolicy> kunnrPolicyList) {
		this.kunnrPolicyList = kunnrPolicyList;
	}

	public String getKunnrErp() {
		return kunnrErp;
	}

	public void setKunnrErp(String kunnrErp) {
		this.kunnrErp = kunnrErp;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getEmp_code() {
		return emp_code;
	}

	public void setEmp_code(String emp_code) {
		this.emp_code = emp_code;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getEmp_phone() {
		return emp_phone;
	}

	public void setEmp_phone(String emp_phone) {
		this.emp_phone = emp_phone;
	}

	public String getEmp_mobile_phone() {
		return emp_mobile_phone;
	}

	public void setEmp_mobile_phone(String emp_mobile_phone) {
		this.emp_mobile_phone = emp_mobile_phone;
	}

	public String getTel_number() {
		return tel_number;
	}

	public void setTel_number(String tel_number) {
		this.tel_number = tel_number;
	}
	
}
