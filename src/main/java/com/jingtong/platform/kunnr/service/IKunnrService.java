package com.jingtong.platform.kunnr.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.base.pojo.BooleanResult;
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

/**
 * 经销商
 * 
 * @author mengkun
 * 
 */
public interface IKunnrService {

	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	static final String DB_SUCCESS="数据保存数据库成功.";
	static final String DB_FAIL="数据保存数据库失败.请联系系统管理员!";
	static final String SAP_SUCCESS="数据传输成功.";
	static final String SAP_FAIL="数据传输失败,请重试或联系系统管理员!";



	/**
	 * 
	 * 经销处罚记录
	 * 
	 * @return
	 */
	public List<KunnrPunish> kunnrPunishSearch(KunnrPunish kunnr);

	public List<Knvp> getknvpList(Knvp knvp);
	/**
	 * 
	 * 经销处罚记录保存
	 * 
	 * @return
	 */
	public Long savekunnrPunish(KunnrPunish kunnr);
 	
	

	/**
	 * 经销商编码序列
	 * 
	 * @return
	 */
	public String getRanKunnrCode();

	/**
	 * 经销商开户附件保存
	 * 
	 * @param business
	 * @param upload
	 * @param uploadFileName
	 * @param key
	 *            文件夾
	 */
	public void saveAttachments(KunnrBusiness business, File[] upload,
			String[] uploadFileName, String key);

	/**
	 * * 经销商开户证照保存
	 * 
	 * @param kunnrLicenseList
	 * @param licenseName
	 * @param license
	 * @param licenseFileName
	 * @param licenseValid
	 */
	public void saveLicenses(List<KunnrLicense> kunnrLicenseList,
			String[] licenseName, File[] license, String[] licenseFileName,
			Date[] licenseValid);
	

	/**
	 * * 名称变更证明
	 * 
	 * @param kunnrLicenseList
	 * @param licenseName
	 * @param license
	 * @param licenseFileName
	 * @param licenseValid
	 */
	public void saveCustNameFlie(Kunnr kunnr, File[] upload,
			String[] uploadFileName, String key);

	/**
	 * 经销商冻结关户附件保存
	 * 
	 * @param kunnr
	 * @param upload
	 * @param uploadFileName
	 * @param key
	 *            文件夾
	 */
	public void saveAttachments(Kunnr kunnr, File[] upload,
			String[] uploadFileName, String key);
	/**
	 * 經銷商開戶 入庫
	 * 
	 * @param kunnr
	 * @return
	 */
	public BooleanResult kunnrOpen(Kunnr kunnr);

	/**
	 * 经销商冻结
	 * 
	 * @param kunnr
	 * @return
	 */
	public BooleanResult kunnrFreeze(Kunnr kunnr);

	/**
	 * 经销商关乎
	 * 
	 * @param kunnr
	 * @return
	 */
	public BooleanResult kunnrClose(Kunnr kunnr);

	/**
	 * 经销商修改
	 * 
	 * @param kunnr
	 * @return
	 */
	public BooleanResult kunnrUpdate(Kunnr kunnr);

	/**
	 * 
	 * 经销商查询COUNT
	 * 
	 * @return
	 */
	public int kunnrSearchCount(Kunnr kunnr);

	/**
	 * 
	 * 经销列表商查询
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
	 * 经销商政策
	 * 
	 * @param kunnr
	 * @return
	 */
	public KunnrPolicy getKunnrPolicyEntity(Kunnr kunnr);
	/**
	 * 经销商地址信息列表 即送达方
	 * 
	 * @param kunnr
	 * @return
	 */
	public List<KunnrAddress> getKunnrAddressList(Kunnr kunnr);
	/****
	 * 
	 * 经销商银行账户信息
	 * @param kunnr
	 * @return
	 */
	public List<KunnrBanks> getKunnrBanksList(Kunnr kunnr);


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
	 * 经销商销售范围
	 * 
	 * @param kunnr
	 * @return
	 */
	public List<KunnrSalesArea> getKunnrSalesAreaList(Kunnr kunnr);

	/**
	 * 物流区域查询
	 * @return
	 */
	public List<KunnrLogisticsArea> getKunnrLogisticsArea(KunnrLogisticsArea area);
	public int getKunnrLogisticsAreaCount(KunnrLogisticsArea area);
	
	
	public BooleanResult updateLogisticArea(List<KunnrLogisticsArea> areaList);
	
	/**
	 * 验证是否是此角色用户
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public int getRoleOnEventByUser(String userId,String roleId);
	
	/**
	 * 修改经销商折扣
	 * @param areaList
	 * @return
	 */
	public BooleanResult updateKunnrAcount(List<KunnrAcount> kunnrAcountList,
			String kunnr);
	/**
	 * 冻结销售视图
	 * @param kunnr
	 * @return
	 */
	public BooleanResult kunnrFreezeXview(Kunnr kunnr);
	
	/**
	 * 
	 * 经销商违约记录查询COUNT
	 * 
	 * @return
	 */
	public int kunnrPunishSearchCount(KunnrPunish kunnrPunish);

	/**
	 * 修改是否分配
	 * @param kunnr
	 * @return
	 */
	public int updateKunnrAutoDemand(Kunnr kunnr);
	
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
