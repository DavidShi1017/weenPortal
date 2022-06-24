package com.jingtong.platform.kunnr.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.framework.util.DateUtil;
import com.jingtong.platform.framework.util.FileUtil;
import com.jingtong.platform.kunnr.dao.IKunnrDao;
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
import com.jingtong.platform.kunnr.service.IKunnrService;
import com.sap.mw.jco.JCO;
import com.sap.mw.jco.JCO.Function;
import com.sap.mw.jco.JCO.ParameterList;
import com.sap.mw.jco.JCO.Structure;
import com.sap.mw.jco.JCO.Table;

public class KunnrServiceImpl implements IKunnrService {

	private static Log logger = LogFactory.getLog(KunnrServiceImpl.class);
	private String uploadFilePath;
	private String licenseFilePath;
	private IKunnrDao kunnrDao;
 
	@Override
	public String getRanKunnrCode() {
		return kunnrDao.getRanKunnrCode();
	}

	@Override
	public void saveAttachments(KunnrBusiness business, File[] upload,
			String[] uploadFileName, String key) {
		for (int i = 0; i < upload.length; i++) {
			if (uploadFileName[i] != null && uploadFileName[i].length() > 0) {
				String newFileName = UUID.randomUUID()
						+ FileUtil.getFileExtention(uploadFileName[i]);

				// 文件目录
				String folderPath = uploadFilePath + "/" + key + "/"
						+ DateUtil.datetime("yyyyMM");

				File savedir = new File(folderPath);
				// 如果目录不存在，则新建
				if (!savedir.exists()) {
					savedir.mkdirs();
				}
				String path = folderPath + "/" + newFileName;
				File file = new File(path);
				FileUtil.saveAsFile(upload[i], file);
				if (i == 0) {
					business.setAccessFile(uploadFileName[i]);
					business.setAccessFilePath(path);
				} else {
					business.setCustomerFile(uploadFileName[i]);
					business.setCustomerFilePath(path);
				}
			}
		}
	}

	@Override
	public void saveLicenses(List<KunnrLicense> kunnrLicenseList,
			String[] licenseName, File[] license, String[] licenseFileName,
			Date[] licenseValid) {
		for (int i = 0; i < license.length; i++) {
			if (licenseFileName[i] != null && licenseFileName[i].length() > 0) {
				String newFileName = UUID.randomUUID()
						+ FileUtil.getFileExtention(licenseFileName[i]);

				// 文件目录
				String folderPath = licenseFilePath;

				File savedir = new File(folderPath);
				// 如果目录不存在，则新建
				if (!savedir.exists()) {
					savedir.mkdirs();
				}
				String path = folderPath + "/" + newFileName;
				File file = new File(path);
				FileUtil.saveAsFile(license[i], file);
				KunnrLicense kunnrLicense = new KunnrLicense();
				kunnrLicense.setLicenseName(licenseName[i]);
				kunnrLicense.setLicenseFile(licenseFileName[i]);
				kunnrLicense.setLicensePath(path);
				kunnrLicense.setLicenseValid(licenseValid[i]);
				kunnrLicenseList.add(kunnrLicense);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public BooleanResult kunnrOpen(Kunnr kunnr) {
		BooleanResult result = new BooleanResult();
		// RFC调用 经销商开户

		result.setResult(true);
		result.setCode(IKunnrService.SAP_SUCCESS);
		// 创建kunnr
		long kunnrId = kunnrDao.createKunnr(kunnr);
		KunnrBusiness business = kunnr.getKunnrbusinessList().get(0);
		// 创建kunnr详细信息
		business.setKunnr(kunnr.getKunnr());
		kunnrDao.createKunnrBusiness(business);

		// 创建证照
		List<KunnrLicense> kunnrLicenseList = kunnr.getKunnrLicenseList();
		if (kunnrLicenseList != null)
			kunnrDao.createKunnrLicense(kunnrLicenseList, kunnr.getKunnr());
		// 创建 银行账户信息
		List<KunnrBanks> kunnrBanklist = kunnr.getKunnrBanklist();
		if (kunnrBanklist != null) {
			kunnrDao.createKunnrBanks(kunnrBanklist, kunnr.getKunnr());
		}

		List<KunnrAddress> kunnrAddressList = kunnr.getKunnrAddressList();
		if (kunnrAddressList != null) { // 创建送货地址
			kunnrDao.createKunnrAddress(kunnrAddressList, kunnr.getKunnr());
		}

		// 创建地址送达方
		/*
		 * List<KunnrAddress> kunnrAddressList = kunnr.getKunnrAddressList(); if
		 * (kunnrAddressList != null)
		 * kunnrDao.createKunnrAddress(kunnrAddressList, kunnr.getKunnr()); //
		 * 创建品牌 // List<KunnrBrand> kunnrBrandListt = kunnr.getKunnrBrandList();
		 * // kunnrDao.createKunnrBrand(kunnrBrandListt, kunnr.getKunnr());
		 * List<KunnrSalesArea> kunnrSalesAreaList =
		 * kunnr.getKunnrSalesAreaList(); if (kunnrSalesAreaList != null)
		 * kunnrDao.createSaleArea(kunnrSalesAreaList, kunnr.getKunnr()); //
		 * 创建折扣 List<KunnrAcount> kunnrAcountList = kunnr.getKunnrAcountList();
		 * if (kunnrAcountList != null)
		 * kunnrDao.createKunnrAcount(kunnrAcountList, kunnr.getKunnr());
		 * List<BCustomerTarget> bCustomerTargetList = kunnr.getTargetList();
		 * List<BCustomerTarget> targetList = new ArrayList<BCustomerTarget>();
		 * if (bCustomerTargetList != null) for (BCustomerTarget target :
		 * bCustomerTargetList) { target.setCustNumber(kunnr.getKunnr());
		 * target.setOrgId(kunnr.getOrgId());
		 * target.setCustId(kunnr.getKunnr()); target.setCtState("0");
		 * target.setTrFlag("T"); target.setMark("Y"); //
		 * target.setKunnrGoalType("B");
		 * target.setOpId(String.valueOf(kunnr.getCreateUserId()));
		 * target.setCheckOpId(kunnr.getCheckOpId()); //
		 * target.setTheMonth(StringUtils.leftPad(target.getTheMonth(), 2,"0"));
		 * targetList.add(target); } kunnrDao.createKunnrGoal(targetList);
		 */
		// 创建经销商销售区域
		List<KunnrSalesArea> kunnrSalesAreaList = kunnr.getKunnrSalesAreaList();
		if (kunnrSalesAreaList != null) {
			kunnrDao.createSaleArea(kunnrSalesAreaList, kunnr.getKunnr());
		}
		// 创建经价格政策
		List<KunnrAcount> kunnrAcountList = kunnr.getKunnrAcountList();
		if (kunnrAcountList != null) {
			kunnrDao.createKunnrAcount(kunnrAcountList, kunnr.getKunnr());
		}
		// 创建经销商政策
		List<KunnrPolicy> kunnrPlicyList = kunnr.getKunnrPolicyList();
		if (kunnrPlicyList != null) {
			kunnrDao.createKunnrPolicy(kunnrPlicyList, kunnr.getKunnr(), "1");
		}

		if (kunnrId > 0) {
			result.setCode(result.getCode() + "'\n" + "+'"
					+ IKunnrService.DB_SUCCESS);
		} else {
			result.setResult(false);
			result.setCode(result.getCode() + "'\n" + "+'"
					+ IKunnrService.DB_FAIL);
		}
		//
		// String rfcResult = kunnrOpenCallRFC(kunnr);
		// // String rfcResult ="success";
		// if (IKunnrService.SUCCESS.equals(rfcResult)) {} else {
		// result.setResult(false);
		// result.setCode(IKunnrService.SAP_FAIL);
		// }
		return result;
	}

	@Override
	public BooleanResult kunnrFreeze(Kunnr kunnr) {
		BooleanResult result = new BooleanResult();
		if (kunnrDao.kunnrFreeze(kunnr)) {
			result.setCode(IKunnrService.DB_SUCCESS);
		} else {
			result.setResult(false);
			result.setCode(IKunnrService.DB_FAIL);
		}
		// String rfcResult = kunnrFreezeCallRFC(kunnr, "C");
		// try {
		// if (IKunnrService.SUCCESS.equals(rfcResult)) {
		// result.setResult(true);
		// result.setCode(IKunnrService.SAP_SUCCESS);
		//
		// } else {
		// result.setResult(false);
		// result.setCode(IKunnrService.SAP_FAIL);
		// }
		// } catch (Exception e) {
		// logger.error(e);
		// result.setResult(false);
		// result.setCode(result.getCode() + "'\n" + "+'" +
		// IKunnrService.DB_FAIL);
		// }
		return result;

	}

	@Override
	public BooleanResult kunnrClose(Kunnr kunnr) {
		BooleanResult result = new BooleanResult();
		try {
			result.setResult(kunnrDao.kunnrClose(kunnr));
			result.setCode(IKunnrService.DB_SUCCESS);
		} catch (Exception e) {
			logger.error(e);
			result.setResult(false);
			result.setCode(IKunnrService.DB_FAIL);
		}
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public BooleanResult kunnrUpdate(Kunnr kunnr) {
		BooleanResult result = new BooleanResult();
		// String rfcResult = kunnrModifyCallRFC(kunnr);
		/*
		 * String rfcDeleteAreaResult = ""; List<KunnrSalesArea> kills =
		 * kunnr.getKillSalesArea();
		 */
		// if (kills.size() > 0) {
		// rfcDeleteAreaResult = kunnrDelSalesAreaCallRFC(kunnr);
		// }

		result.setResult(true);
		result.setCode(IKunnrService.SAP_SUCCESS);
		// 修改kunnr
		long kunnrId = kunnrDao.updateKunnr(kunnr);
		KunnrBusiness business = kunnr.getKunnrbusinessList().get(0);
		// 修改kunnr详细信息
		business.setKunnr(kunnr.getKunnr());
		kunnrDao.updateKunnrBusiness(business);
		
		List<KunnrAddress> kunnrAddressList = kunnr.getKunnrAddressList();
		if (kunnrAddressList != null) {
			kunnrDao.updateAndCreateKunnrAddress(kunnrAddressList,
					kunnr.getKunnr());
		}

		/*
		 * // 修改创建地址送达方 List<KunnrAddress> kunnrAddressList =
		 * kunnr.getKunnrAddressList(); if (kunnrAddressList != null)
		 * kunnrDao.updateAndCreateKunnrAddress(kunnrAddressList,
		 * kunnr.getKunnr()); // 修改创建品牌 List<KunnrBrand> kunnrBrandListt =
		 * kunnr.getKunnrBrandList(); if (kunnrBrandListt != null)
		 * kunnrDao.updateAndCreateKunnrBrand(kunnrBrandListt,
		 * kunnr.getKunnr()); // 修改创建折扣 List<KunnrAcount> kunnrAcountList =
		 * kunnr.getKunnrAcountList(); if (kunnrAcountList != null)
		 * kunnrDao.updateAndCreateKunnrAcount(kunnrAcountList,
		 * kunnr.getKunnr());
		 */
		// 修改折扣
		List<KunnrAcount> kunnrAcountList = kunnr.getKunnrAcountList();
		if (kunnrAcountList != null) {
			kunnrDao.updateAndCreateKunnrAcount(kunnrAcountList,
					kunnr.getKunnr());
		}

		// 修改证照
		List<KunnrLicense> kunnrLicenseList = kunnr.getKunnrLicenseList();
		if (kunnrLicenseList != null)
			kunnrDao.createKunnrLicense(kunnrLicenseList, kunnr.getKunnr());
		// 销售渠道
		List<KunnrSalesArea> salesAreaList = kunnr.getKunnrSalesAreaList();
		if (salesAreaList != null) {
			kunnrDao.updateAndCreateSalesArea(salesAreaList, kunnr.getKunnr());
		}
		// 经销商政策修改
		List<KunnrPolicy> kunnrPolicyList = kunnr.getKunnrPolicyList();
		if (kunnrPolicyList != null) {
			kunnrDao.createKunnrPolicy(kunnrPolicyList, kunnr.getKunnr(), "2");
		}
		/*
		 * List<BCustomerTarget> targetList=kunnr.getTargetList();
		 * if(targetList!=null)
		 * kunnrDao.updateAndCreateBCustomerTarget(targetList, kunnr);
		 */
		// 删除品牌及折扣
		/*
		 * String killBrand = kunnr.getKillBrand(); if
		 * (StringUtils.isNotEmpty(killBrand)) {
		 * kunnrDao.removeBrand(killBrand); } String killAcount =
		 * kunnr.getKillAcount(); if (StringUtils.isNotEmpty(killAcount)) {
		 * kunnrDao.removeAcount(killAcount); } List<KunnrSalesArea>
		 * killSalesArea = kunnr.getKillSalesArea(); if (null != killSalesArea)
		 * { kunnrDao.removeSalesArea(killSalesArea); }
		 */
		if (kunnrId > 0 /* && kunnrBusinessId > 0 */) {
			result.setResult(true);
			result.setCode(result.getCode() + "'\n" + "+'"
					+ IKunnrService.DB_SUCCESS);
		} else {
			result.setResult(false);
			result.setCode(result.getCode() + "'\n" + "+'"
					+ IKunnrService.DB_FAIL);
		}
		//
		// // String rfcResult="success";
		// if ((IKunnrService.SUCCESS.equals(rfcResult) &&
		// IKunnrService.SUCCESS.equals(rfcDeleteAreaResult))
		// || (IKunnrService.SUCCESS.equals(rfcResult) && 0 == kills.size())) {}
		// else {
		// result.setResult(false);
		// result.setCode(IKunnrService.SAP_FAIL);
		// }
		return result;
	}

	@Override
	public int kunnrSearchCount(Kunnr kunnr) {
		try {
			return kunnrDao.kunnrSearchCount(kunnr);
		} catch (Exception e) {
			logger.error(e);
			return 0;

		}
	}

	@Override
	public List<Kunnr> kunnrSearch(Kunnr kunnr) {
		try {
			return kunnrDao.kunnrSearch(kunnr);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public Kunnr getKunnrEntity(Kunnr kunnr) {
		try {
			return kunnrDao.getKunnrEntity(kunnr);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public KunnrBusiness getKunnrBusinessEntity(Kunnr kunnr) {
		try {
			return kunnrDao.getKunnrBusinessEntity(kunnr);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public KunnrPolicy getKunnrPolicyEntity(Kunnr kunnr) {
		try {
			return kunnrDao.getKunnrPolicyEntity(kunnr);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<KunnrAddress> getKunnrAddressList(Kunnr kunnr) {
		try {
			return kunnrDao.getKunnrAddressList(kunnr);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<KunnrBrand> getKunnrBrandList(Kunnr kunnr) {
		try {
			return kunnrDao.getKunnrBrandList(kunnr);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<KunnrAcount> getKunnrAcountList(Kunnr kunnr) {
		try {
			return kunnrDao.getKunnrAcountList(kunnr);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<KunnrLicense> getKunnrLicenseList(Kunnr kunnr) {
		try {
			return kunnrDao.getKunnrLicenseList(kunnr);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	 

	@Override
	public List<KunnrSalesArea> getKunnrSalesAreaList(Kunnr kunnr) {
		try {
			return kunnrDao.getKunnrSalesAreaList(kunnr);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	 
 

	 

	public String getUploadFilePath() {
		return uploadFilePath;
	}

	public void setUploadFilePath(String uploadFilePath) {
		this.uploadFilePath = uploadFilePath;
	}

	public IKunnrDao getKunnrDao() {
		return kunnrDao;
	}

	public void setKunnrDao(IKunnrDao kunnrDao) {
		this.kunnrDao = kunnrDao;
	}

	 
	public String getLicenseFilePath() {
		return licenseFilePath;
	}

	public void setLicenseFilePath(String licenseFilePath) {
		this.licenseFilePath = licenseFilePath;
	}

	@Override
	public List<KunnrLogisticsArea> getKunnrLogisticsArea(
			KunnrLogisticsArea area) {
		return kunnrDao.getKunnrLogisticsArea(area);
	}

	@Override
	public int getKunnrLogisticsAreaCount(KunnrLogisticsArea area) {
		return kunnrDao.getKunnrLogisticsAreaCount(area);
	}

	@Override
	public BooleanResult updateLogisticArea(List<KunnrLogisticsArea> areaList) {

		BooleanResult result = new BooleanResult();
		// RFC调用 物流修改rfc
		// String rfcResult = "";
		// if (areaList.size() > 0)
		// for (int i = 0; i < areaList.size(); i++) {
		// String rfc = kunnrModifyCallRFC(areaList.get(i).getKunnrObject());
		// if (IKunnrService.ERROR.equals(rfc)) {
		// rfcResult = IKunnrService.ERROR;
		// }
		// }
		//
		// if ("".equals(rfcResult)) {
		// rfcResult = "success";
		// }
		// // kunnrLogisticsAreaModifyCallRFC(areaList);
		// // String rfcResult = "success";
		// if (IKunnrService.SUCCESS.equals(rfcResult)) {
		// result.setResult(true);
		// result.setCode(IKunnrService.SAP_SUCCESS);
		// // 修改物流区域
		// if (areaList != null)
		//
		// } else {
		// result.setResult(false);
		// result.setCode(IKunnrService.SAP_FAIL);
		// }
		try {
			kunnrDao.updateLogisticArea(areaList);
			result.setCode(result.getCode() + "'\n" + "+'"
					+ IKunnrService.DB_SUCCESS);
		} catch (Exception e) {
			logger.error(e);
			result.setCode(result.getCode() + "\n" + "+'"
					+ IKunnrService.DB_FAIL);
		}
		return result;

	}

	 
	@Override
	public BooleanResult updateKunnrAcount(List<KunnrAcount> kunnrAcountList,
			String kunnr) {
		BooleanResult result = new BooleanResult();
		result.setResult(true);
		// 修改物流区域
		if (null != kunnrAcountList)
			try {
				kunnrDao.updateAndCreateKunnrAcount(kunnrAcountList, kunnr);
				result.setCode(IKunnrService.DB_SUCCESS);
			} catch (Exception e) {
				logger.error(e);
				result.setResult(false);
				result.setCode(IKunnrService.DB_FAIL);
			}
		return result;
	}

	@Override
	public List<KunnrBanks> getKunnrBanksList(Kunnr kunnr) {
		try {
			return kunnrDao.getKunnrBankList(kunnr);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Long savekunnrPunish(KunnrPunish kunnr) {
		try {
			kunnrDao.savekunnrPunish(kunnr);
			return 1L;
		} catch (Exception e) {
			return 0L;
		}
	}

	/**
	 * 
	 * 经销商处罚记录count
	 * */

	@Override
	public int kunnrPunishSearchCount(KunnrPunish kunnrPunish) {
		try {
			return kunnrDao.kunnrPunishSearchCount(kunnrPunish);
		} catch (Exception e) {
			logger.error(e);
			return 0;

		}
	}

	/**
	 * 经销商处罚记录
	 * 
	 * */

	@Override
	public List<KunnrPunish> kunnrPunishSearch(KunnrPunish kunnrPunish) {
		try {
			return kunnrDao.kunnrPunishSearch(kunnrPunish);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public int updateKunnrAutoDemand(Kunnr kunnr) {
		// TODO Auto-generated method stub
		try {
			return kunnrDao.updateKunnrAutoDemand(kunnr);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	@Override
	public List<Knvp> getknvpList(Knvp knvp) {
		try {
			return kunnrDao.getknvpList(knvp);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<Kunnr> queryKunnrCompany(Kunnr kunnr) {
		// TODO Auto-generated method stub
		return this.kunnrDao.queryKunnrCompany(kunnr);
	}

	@Override
	public List<Knvp> getAllknvpList(Knvp knvp) {
		try {
			return kunnrDao.getAllknvpList(knvp);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	@Override
	public List<Knvp> getAllknvpList1(Knvp knvp) {
		try {
			return kunnrDao.getAllknvpList1(knvp);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public int getknvpListCount(Knvp knvp) {
		try {
			return kunnrDao.getknvpListCount(knvp);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	@Override
	public List<AllUsers> getAllUserByCode(AllUsers user) {
		try {
			return kunnrDao.getAllUserByCode(user);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	@Override
	public List<Kunnr> getKunnrByKunnr(Kunnr kunnr){
		try {
			return kunnrDao.getKunnrByKunnr(kunnr);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	@Override
	public Kunnr getPhonesByKunnr(Kunnr kunnr) {
		try {
			return kunnrDao.getPhonesByKunnr(kunnr);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public void saveCustNameFlie(Kunnr kunnr, File[] upload,
			String[] uploadFileName, String key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveAttachments(Kunnr kunnr, File[] upload,
			String[] uploadFileName, String key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getRoleOnEventByUser(String userId, String roleId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BooleanResult kunnrFreezeXview(Kunnr kunnr) {
		// TODO Auto-generated method stub
		return null;
	}

}
