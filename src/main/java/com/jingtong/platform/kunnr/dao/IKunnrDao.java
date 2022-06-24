package com.jingtong.platform.kunnr.dao;

import java.util.List;

import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.kunnr.pojo.Knvp;
import com.jingtong.platform.kunnr.pojo.Kunnr;
import com.jingtong.platform.kunnr.pojo.KunnrAcount;
import com.jingtong.platform.kunnr.pojo.KunnrAddress;
import com.jingtong.platform.kunnr.pojo.KunnrBanks;
import com.jingtong.platform.kunnr.pojo.KunnrBrand;
import com.jingtong.platform.kunnr.pojo.KunnrBusiness;
import com.jingtong.platform.kunnr.pojo.KunnrLicense;
import com.jingtong.platform.kunnr.pojo.KunnrLogisticsArea;
import com.jingtong.platform.kunnr.pojo.KunnrPolicy;
import com.jingtong.platform.kunnr.pojo.KunnrPunish;
import com.jingtong.platform.kunnr.pojo.KunnrSalesArea;

public interface IKunnrDao {

	/**
	 * 经销商编码序列
	 * 
	 * @return
	 */
	public String getRanKunnrCode();

	/**
	 * 创建经销商基本信息
	 * 
	 * @param kunnr
	 * @return
	 */
	public long createKunnr(Kunnr kunnr);

	/**
	 * 创建经销商详细信息
	 * 
	 * @param business
	 * @return
	 */
	public long createKunnrBusiness(KunnrBusiness business);

	/**
	 * 创建经销商送达地址
	 * 
	 * @param address
	 * @return
	 */
	public void createKunnrAddress(List<KunnrAddress> kunnrAddressList,
			String kunnr);

	/**
	 * 创建经销商经营品牌
	 * 
	 * @param brand
	 * @return
	 */
	public void createKunnrBrand(List<KunnrBrand> kunnrBrandList, String kunnr);

	/**
	 * 创建经销商折扣
	 * 
	 * @param kunnrAcountList
	 * @param kunnr
	 */
	public void createKunnrAcount(List<KunnrAcount> kunnrAcountList,
			String kunnr);
	/**
	 * 创建经销商政策
	 * 
	 * @param kunnrPolicyList
	 * @param kunnr
	 */
	public void createKunnrPolicy(List<KunnrPolicy> kunnrPolicyList,
			String kunnr,String flag);
	/**
	 * 银行账户信息
	 * 
	 * @param kunnrLicenseList
	 * @param kunnr
	 */
	public void createKunnrBanks(final List<KunnrBanks> kunnrBanklist,
		final String kunnr) ;
	
	/***
	 * 查看经销商 银行账户信息
	 * @param KunnrBanks
	 * @return
	 */
	public List<KunnrBanks> getKunnrBankList(Kunnr kunnr);
	/**
	 * 创建证照信息
	 * 
	 * @param kunnrLicenseList
	 * @param kunnr
	 */
	public void createKunnrLicense(List<KunnrLicense> kunnrLicenseList,
			String kunnr);

	/**
	 * 修改经销商基本信息
	 * 
	 * @param kunnr
	 * @return
	 */
	public Integer updateKunnr(Kunnr kunnr);

	/**
	 * 修改经销商详细信息
	 * 
	 * @param business
	 * @return
	 */
	public Integer updateKunnrBusiness(KunnrBusiness business);

	/**
	 * 修改经销商送达地址
	 * 
	 * @param address
	 * @return
	 */
	public void updateAndCreateKunnrAddress(
			List<KunnrAddress> kunnrAddressList, String kunnr);

	/**
	 * 修改经销商经营品牌
	 * 
	 * @param brand
	 * @return
	 */
	public void updateAndCreateKunnrBrand(List<KunnrBrand> kunnrBrandList,
			String kunnr);

	/**
	 * 修改经销商折扣
	 * 
	 * @param kunnrAcountList
	 * @param kunnr
	 */
	public void updateAndCreateKunnrAcount(List<KunnrAcount> kunnrAcountList,
			String kunnr);

	/**
	 * 删除品牌
	 * 
	 * @param killBrand
	 */
	public void removeBrand(String killBrand);

	/**
	 * 删除折扣
	 * 
	 * @param killAcount
	 */
	public void removeAcount(String killAcount);

	/**
	 * 经销商冻结
	 * 
	 * @param kunnr
	 * @return
	 */
	public boolean kunnrFreeze(Kunnr kunnr);

	/**
	 * 经销商关乎
	 * 
	 * @param kunnr
	 * @return
	 */
	public boolean kunnrClose(Kunnr kunnr);

	/**
	 * 
	 * 经销商查询COUNT
	 * 
	 * @return
	 */
	public int kunnrSearchCount(Kunnr kunnr);

	/**
	 * 
	 * 经销商列表查询
	 * 
	 * @return
	 */
	public List<Kunnr> kunnrSearch(Kunnr kunnr);

	/**
	 * 
	 * 经销商信息
	 * 
	 * @param kunnr
	 * @return
	 */
	public Kunnr getKunnrEntity(Kunnr kunnr);

	/**
	 * 经销商详细信息
	 * 
	 * @param kunnr
	 * @return
	 */
	public KunnrBusiness getKunnrBusinessEntity(Kunnr kunnr);

	/**
	 * 经销商地址信息列表 即送达方
	 * 
	 * @param kunnr
	 * @return
	 */
	public List<KunnrAddress> getKunnrAddressList(Kunnr kunnr);

	/**
	 * 经销商品牌列表
	 * 
	 * @param kunnr
	 * @return
	 */
	public List<KunnrBrand> getKunnrBrandList(Kunnr kunnr);

	/**
	 * 经销商折扣说明列表
	 * 
	 * @param kunnr
	 * @return
	 */
	public List<KunnrAcount> getKunnrAcountList(Kunnr kunnr);

	/**
	 * 经销商证照信息
	 * 
	 * @param kunnr
	 * @return
	 */
	public List<KunnrLicense> getKunnrLicenseList(Kunnr kunnr);
	
	/**
	 * 创建销售范围
	 * 
	 * @param kunnrLicenseList
	 * @param kunnr
	 */
	public void createSaleArea(List<KunnrSalesArea> kunnrSalesAreaList,
			String kunnr);
	
	 
	/**
	 * 经销商销售范围
	 * 
	 * @param kunnr
	 * @return
	 */
	public List<KunnrSalesArea> getKunnrSalesAreaList(Kunnr kunnr);
	
	/**
	 * 修改经销商范围
	 * 
	 * @param kunnrAcountList
	 * @param kunnr
	 */
	public void updateAndCreateSalesArea(List<KunnrSalesArea> salesAreaList,
			String kunnr);
	
	/**
	 * 删除销售范围
	 * 
	 * @param killSalesArea
	 */
	public void removeSalesArea(List<KunnrSalesArea> salesAreaList);
	

	/**
	 * 物流区域查询
	 * @return
	 */
	public List<KunnrLogisticsArea> getKunnrLogisticsArea(KunnrLogisticsArea area);
	public int getKunnrLogisticsAreaCount(KunnrLogisticsArea area);
	
	/**
	 * 修改物流区域
	 * @param areaList
	 * @return
	 */
	public void updateLogisticArea(List<KunnrLogisticsArea> areaList);
	
	/**
	 * 验证是否是此角色用户
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public int getRoleOnEventByUser(String userId,String roleId);
	/**
	 * 经销商政策
	 * 
	 * @param kunnr
	 * @return
	 */
	public KunnrPolicy getKunnrPolicyEntity(Kunnr kunnr);
	

	/**
	 * 经销商处罚记录保存
	 * 
	 * */
	
	public Long savekunnrPunish(KunnrPunish kunnrPunish);
	 

	/**
	 * 
	 * 经销商处罚记录查询COUNT
	 * 
	 * @return
	 */
	public int kunnrPunishSearchCount(KunnrPunish kunnr);

	/**
	 * 
	 * 经销商处罚记录列表查询
	 * 
	 * @return
	 */
	public List<KunnrPunish> kunnrPunishSearch(KunnrPunish kunnr);

	/**
	 * 是否自动分配
	 * @param kunnr
	 * @return
	 */
	public int updateKunnrAutoDemand(Kunnr kunnr);
	
	public List<Knvp> getknvpList(Knvp knvp);
	
	/**
	 * 查询经销商
	 * @param kunnr
	 * @return
	 */
	public List<Kunnr> queryKunnrCompany(Kunnr kunnr);

	public List<Knvp> getAllknvpList(Knvp knvp);
	
	public List<Knvp> getAllknvpList1(Knvp knvp);

	public int getknvpListCount(Knvp knvp);
	
	public List<AllUsers> getAllUserByCode(AllUsers user);
	
	/**
	 * 根据客户编码查出客户
	 * @param kunnr
	 * @return
	 */
	public List<Kunnr> getKunnrByKunnr(Kunnr kunnr);
	
	public Kunnr getPhonesByKunnr(Kunnr kunnr);
}
