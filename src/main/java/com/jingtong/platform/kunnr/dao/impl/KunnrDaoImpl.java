package com.jingtong.platform.kunnr.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
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

@SuppressWarnings("rawtypes")
public class KunnrDaoImpl extends BaseDaoImpl implements IKunnrDao {

	@Override
	public String getRanKunnrCode() {
		return (String) this.getSqlMapClientTemplate().queryForObject(
				"kunnr.getRanKunnrCode");
	}

	@Override
	public long createKunnr(Kunnr kunnr) {
		return (Long) getSqlMapClientTemplate().insert("kunnr.createKunnr",
				kunnr);
	}

	@Override
	public long createKunnrBusiness(KunnrBusiness business) {
		return (Long) getSqlMapClientTemplate().insert(
				"kunnr.createKunnrBusiness", business);
	}

	@Override
	public void createKunnrAddress(final List<KunnrAddress> kunnrAddressList,
			final String kunnr) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			@Override
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (KunnrAddress address : kunnrAddressList) {
					address.setKunnr(kunnr);
					executor.insert("kunnr.createKunnrAddress", address);
				}
				executor.executeBatch();
				return true;
			}
		});
	}

	@Override
	public void createKunnrBrand(final List<KunnrBrand> kunnrBrandList,
			final String kunnr) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			@Override
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (KunnrBrand brand : kunnrBrandList) {
					brand.setKunnr(kunnr);
					executor.insert("kunnr.createKunnrBrand", brand);
				}
				executor.executeBatch();
				return true;
			}
		});
	}

	@Override
	public void createKunnrAcount(final List<KunnrAcount> kunnrAcountList,
			final String kunnr) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			@Override
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				// 折扣类型行转列
				for (KunnrAcount acount : kunnrAcountList) {
					acount.setKunnr(kunnr);
					executor.insert("kunnr.createKunnrAcount", acount);
					/*
					 * count.setBonus(acount.getBonus());
					 * count.setFlag(acount.getFlag()); if (null !=
					 * acount.getAcountSap()) {
					 * count.setAcountSap(acount.getAcountSap()); } else {
					 * acount.setAcountSap(""); } if (null !=
					 * acount.getStartDate()) {
					 * count.setStartDate(acount.getStartDate()); } else {
					 * count.setStartDate(""); } if (null !=
					 * acount.getEndDate()) {
					 * count.setEndDate(acount.getEndDate()); } else {
					 * count.setEndDate(""); } // Y表示2级返一级 if
					 * ("Y".equals(acount.getFlag())) { if
					 * (StringUtils.isNotEmpty(acount.getType2A())) {
					 * count.setAcountType("A");
					 * count.setAcount(acount.getType2A());
					 * count.setTypeMoney(acount.getType2Money());
					 * 
					 * if (null != acount.getAcountSap()) {
					 * count.setAcountSap(acount.getAcountSap()); }else{
					 * acount.setAcountSap(""); } if (null !=
					 * acount.getStartDate()) {
					 * count.setStartDate(acount.getStartDate()); }else{
					 * count.setStartDate(""); } if (null !=
					 * acount.getEndDate()) {
					 * count.setEndDate(acount.getEndDate()); }else{
					 * count.setEndDate(""); }
					 * 
					 * executor.insert("kunnr.createKunnrAcount", count); } if
					 * (StringUtils.isNotEmpty(acount.getType2B())) { //
					 * count.setKunnr(kunnr); count.setAcountType("B");
					 * count.setAcount(acount.getType2B());
					 * count.setTypeMoney(acount.getType2Money());
					 * 
					 * if (null != acount.getAcountSap()) {
					 * acount.setAcountSap(acount.getAcountSap()); }else{
					 * acount.setAcountSap(""); } if (null !=
					 * acount.getStartDate()) {
					 * acount.setStartDate(acount.getStartDate()); }else{
					 * acount.setStartDate(""); } if (null !=
					 * acount.getEndDate()) {
					 * acount.setEndDate(acount.getEndDate()); }else{
					 * acount.setEndDate(""); }
					 * 
					 * executor.insert("kunnr.createKunnrAcount", count); } if
					 * (StringUtils.isNotEmpty(acount.getType2C())) { //
					 * count.setKunnr(kunnr); count.setAcountType("C");
					 * count.setAcount(acount.getType2C());
					 * count.setTypeMoney(acount.getType2Money());
					 * 
					 * if (null != acount.getAcountSap()) {
					 * acount.setAcountSap(acount.getAcountSap()); }else{
					 * acount.setAcountSap(""); } if (null !=
					 * acount.getStartDate()) {
					 * acount.setStartDate(acount.getStartDate()); }else{
					 * acount.setStartDate(""); } if (null !=
					 * acount.getEndDate()) {
					 * acount.setEndDate(acount.getEndDate()); }else{
					 * acount.setEndDate(""); }
					 * 
					 * executor.insert("kunnr.createKunnrAcount", count); } } if
					 * (StringUtils.isNotEmpty(acount.getTypeA())) { //
					 * count.setKunnr(kunnr); count.setAcountType("A");
					 * count.setAcount(acount.getTypeA());
					 * count.setTypeMoney(acount.getTypeMoney());
					 * count.setFlag("N");
					 * 
					 * if (null != acount.getAcountSap()) {
					 * acount.setAcountSap(acount.getAcountSap()); }else{
					 * acount.setAcountSap(""); } if (null !=
					 * acount.getStartDate()) {
					 * acount.setStartDate(acount.getStartDate()); }else{
					 * acount.setStartDate(""); } if (null !=
					 * acount.getEndDate()) {
					 * acount.setEndDate(acount.getEndDate()); }else{
					 * acount.setEndDate(""); }
					 * 
					 * executor.insert("kunnr.createKunnrAcount", count); } if
					 * (StringUtils.isNotEmpty(acount.getTypeB())) { //
					 * count.setKunnr(kunnr); count.setAcountType("B");
					 * count.setAcount(acount.getTypeB());
					 * count.setTypeMoney(acount.getTypeMoney());
					 * count.setFlag("N");
					 * 
					 * if (null != acount.getAcountSap()) {
					 * acount.setAcountSap(acount.getAcountSap()); }else{
					 * acount.setAcountSap(""); } if (null !=
					 * acount.getStartDate()) {
					 * acount.setStartDate(acount.getStartDate()); }else{
					 * acount.setStartDate(""); } if (null !=
					 * acount.getEndDate()) {
					 * acount.setEndDate(acount.getEndDate()); }else{
					 * acount.setEndDate(""); }
					 * 
					 * executor.insert("kunnr.createKunnrAcount", count); } if
					 * (StringUtils.isNotEmpty(acount.getTypeC())) { //
					 * count.setKunnr(kunnr); count.setAcountType("C");
					 * count.setFlag("N"); count.setAcount(acount.getTypeC());
					 * 
					 * if (null != acount.getAcountSap()) {
					 * acount.setAcountSap(acount.getAcountSap()); }else{
					 * acount.setAcountSap(""); } if (null !=
					 * acount.getStartDate()) {
					 * acount.setStartDate(acount.getStartDate()); }else{
					 * acount.setStartDate(""); } if (null !=
					 * acount.getEndDate()) {
					 * acount.setEndDate(acount.getEndDate()); }else{
					 * acount.setEndDate(""); }
					 * 
					 * executor.insert("kunnr.createKunnrAcount", count); }
					 */
				}
				executor.executeBatch();
				return true;
			}
		});
	}

	@Override
	public void createKunnrLicense(final List<KunnrLicense> kunnrLicenseList,
			final String kunnr) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			@Override
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (KunnrLicense license : kunnrLicenseList) {
					license.setKunnr(kunnr);
					executor.insert("kunnr.createKunnrLicense", license);
				}
				executor.executeBatch();
				return true;
			}
		});
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<KunnrBanks> getKunnrBankList(Kunnr kunnr) {
		return getSqlMapClientTemplate().queryForList(
				"kunnr.getKunnrBankList", kunnr);
	}

	@Override
	public void createKunnrBanks(final List<KunnrBanks> kunnrBanklist,
			final String kunnr) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			@Override
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (KunnrBanks bank : kunnrBanklist) {
					bank.setKunnr(kunnr);
					executor.insert("kunnr.createKunnrBank", bank);
				}
				executor.executeBatch();
				return true;
			}
		});
	}

	@Override
	public boolean kunnrFreeze(Kunnr kunnr) {
		int result = getSqlMapClientTemplate().update("kunnr.kunnrFreeze",
				kunnr);
		return result > 0 ? true : false;
	}

	@Override
	public boolean kunnrClose(Kunnr kunnr) {
		int result = getSqlMapClientTemplate()
				.update("kunnr.kunnrClose", kunnr);
		return result > 0 ? true : false;
	}

	@Override
	public Integer updateKunnr(Kunnr kunnr) {
		return getSqlMapClientTemplate().update("kunnr.updateKunnr",
				kunnr);
	}

	@Override
	public Integer updateKunnrBusiness(KunnrBusiness business) {
		return getSqlMapClientTemplate().update(
				"kunnr.updateKunnrBusiness", business);
	}

	@Override
	public void updateAndCreateKunnrAddress(
			final List<KunnrAddress> kunnrAddressList, final String kunnr) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			@Override
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (KunnrAddress address : kunnrAddressList) {
					address.setKunnr(kunnr);
					if (address.getId() != null) {
						executor.update("kunnr.updateKunnrAddress", address);
					} else {
						executor.insert("kunnr.createKunnrAddress", address);
					}
				}
				executor.executeBatch();
				return true;
			}
		});
	}

	@Override
	public void updateAndCreateKunnrBrand(
			final List<KunnrBrand> kunnrBrandList, final String kunnr) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			@Override
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (KunnrBrand brand : kunnrBrandList) {
					brand.setKunnr(kunnr);
					if (brand.getId() != null) {
						executor.update("kunnr.updateKunnrBrand", brand);
					} else {
						executor.insert("kunnr.createKunnrBrand", brand);
					}
				}
				executor.executeBatch();
				return true;
			}
		});
	}

	@Override
	public void updateAndCreateKunnrAcount(
			final List<KunnrAcount> kunnrAcountList, final String kunnr) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			@Override
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				// 折扣类型行转列
				for (KunnrAcount acount : kunnrAcountList) {
					acount.setKunnr(kunnr);
					if (acount.getId() == 0 || acount.getId() == null) {
						executor.insert("kunnr.createKunnrAcount", acount);
					} else {
						executor.update("kunnr.updateKunnrAcount", acount);
					}
					// Y表示2级返一级
					/*
					 * KunnrAcount count = new KunnrAcount();
					 * count.setFlag(acount.getFlag()); count.setKunnr(kunnr);
					 * count.setBonus(acount.getBonus()); if (null !=
					 * acount.getAcountSap()) {
					 * count.setAcountSap(acount.getAcountSap()); } else {
					 * count.setAcountSap(""); } if (null !=
					 * acount.getStartDate()) {
					 * count.setStartDate(acount.getStartDate()); } else {
					 * count.setStartDate(""); } if (null !=
					 * acount.getEndDate()) {
					 * count.setEndDate(acount.getEndDate()); } else {
					 * count.setEndDate(""); } if ("Y".equals(acount.getFlag()))
					 * { if (StringUtils.isNotEmpty(acount.getType2A())) {
					 * count.setAcountType("A");
					 * count.setAcount(acount.getType2A());
					 * count.setTypeMoney(acount.getType2Money()); if
					 * (acount.getType2AId() != null) {
					 * count.setId(acount.getType2AId());
					 * executor.update("kunnr.updateKunnrAcount", count); } else
					 * { executor.insert("kunnr.createKunnrAcount", count); } }
					 * if (StringUtils.isNotEmpty(acount.getType2B())) {
					 * count.setAcountType("B");
					 * count.setAcount(acount.getType2B());
					 * count.setTypeMoney(acount.getType2Money()); if
					 * (acount.getType2BId() != null) {
					 * count.setId(acount.getType2BId());
					 * executor.update("kunnr.updateKunnrAcount", count); } else
					 * { executor.insert("kunnr.createKunnrAcount", count); } }
					 * if (StringUtils.isNotEmpty(acount.getType2C())) {
					 * count.setAcountType("C");
					 * count.setAcount(acount.getType2C());
					 * count.setTypeMoney(acount.getType2Money()); if
					 * (acount.getType2CId() != null) {
					 * count.setId(acount.getType2CId());
					 * executor.update("kunnr.updateKunnrAcount", count); } else
					 * { executor.insert("kunnr.createKunnrAcount", count); } }
					 * } if (StringUtils.isNotEmpty(acount.getTypeA())) {
					 * count.setAcountType("A");
					 * count.setAcount(acount.getTypeA()); count.setFlag("N");
					 * count.setTypeMoney(acount.getTypeMoney()); if
					 * (acount.getTypeAId() != null) {
					 * count.setId(acount.getTypeAId());
					 * executor.update("kunnr.updateKunnrAcount", count); } else
					 * { executor.insert("kunnr.createKunnrAcount", count); } }
					 * if (StringUtils.isNotEmpty(acount.getTypeB())) {
					 * count.setAcountType("B");
					 * count.setAcount(acount.getTypeB()); count.setFlag("N");
					 * count.setTypeMoney(acount.getTypeMoney()); if
					 * (acount.getTypeBId() != null) {
					 * count.setId(acount.getTypeBId());
					 * executor.update("kunnr.updateKunnrAcount", count); } else
					 * { executor.insert("kunnr.createKunnrAcount", count); } }
					 * if (StringUtils.isNotEmpty(acount.getTypeC())) {
					 * count.setAcountType("C"); count.setFlag("N");
					 * count.setTypeMoney(acount.getTypeMoney());
					 * acount.setAcount(acount.getTypeC()); if
					 * (acount.getTypeCId() != null) {
					 * count.setId(acount.getTypeCId());
					 * executor.update("kunnr.updateKunnrAcount", count); } else
					 * { executor.insert("kunnr.createKunnrAcount", count); } }
					 */
				}
				executor.executeBatch();
				return true;
			}
		});
	}

	@Override
	public void removeBrand(final String killBrand) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			@Override
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				String[] ids = killBrand.split(",");
				for (String brandId : ids) {
					if (StringUtils.isNotEmpty(brandId)) {
						executor.delete("kunnr.removeBrand",
								Long.parseLong(brandId));
					}
				}
				executor.executeBatch();
				return true;
			}
		});
	}

	@Override
	public void removeAcount(final String killAcount) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			@Override
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				String[] ids = killAcount.split(",");
				for (String acountId : ids) {
					if (StringUtils.isNotEmpty(acountId)) {
						executor.delete("kunnr.removeAcount",
								Long.parseLong(acountId));
					}
				}
				executor.executeBatch();
				return true;
			}
		});
	}

	@Override
	public int kunnrSearchCount(Kunnr kunnr) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"kunnr.kunnrSearchCount", kunnr);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Kunnr> kunnrSearch(Kunnr kunnr) {
		return getSqlMapClientTemplate().queryForList(
				"kunnr.kunnrSearch", kunnr);
	}

	@Override
	public Kunnr getKunnrEntity(Kunnr kunnr) {
		return (Kunnr) getSqlMapClientTemplate().queryForObject(
				"kunnr.getKunnrEntity", kunnr);
	}

	@Override
	public KunnrBusiness getKunnrBusinessEntity(Kunnr kunnr) {
		return (KunnrBusiness) getSqlMapClientTemplate().queryForObject(
				"kunnr.getKunnrBusinessEntity", kunnr);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<KunnrAddress> getKunnrAddressList(Kunnr kunnr) {
		return getSqlMapClientTemplate().queryForList(
				"kunnr.getKunnrAddressList", kunnr);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<KunnrBrand> getKunnrBrandList(Kunnr kunnr) {
		return getSqlMapClientTemplate().queryForList(
				"kunnr.getKunnrBrandList", kunnr);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<KunnrLicense> getKunnrLicenseList(Kunnr kunnr) {
		return getSqlMapClientTemplate().queryForList(
				"kunnr.getKunnrLicenseList", kunnr);
	}

 
	@Override
	@SuppressWarnings("unchecked")
	public List<KunnrSalesArea> getKunnrSalesAreaList(Kunnr kunnr) {
		return getSqlMapClientTemplate().queryForList(
				"kunnr.getKunnrSalesAreaList", kunnr);
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public List<KunnrAcount> getKunnrAcountList(Kunnr kunnr) {
		/*
		 * List<KunnrAcount> tempList = (List<KunnrAcount>)
		 * getSqlMapClientTemplate().queryForList("kunnr.getKunnrAcountList",
		 * kunnr); Map<String, KunnrAcount> tempMap = new HashMap<String,
		 * KunnrAcount>(); List<KunnrAcount> kunnrAcountList = new
		 * ArrayList<KunnrAcount>(); // 折扣类型行转列 for (KunnrAcount acount :
		 * tempList) { if (tempMap.containsKey(acount.getBonus())) { if
		 * ("A".equals(acount.getAcountType())) { if
		 * ("Y".equals(acount.getFlag())) {
		 * tempMap.get(acount.getBonus()).setType2A(acount.getAcount());
		 * tempMap.get(acount.getBonus()).setType2AId(acount.getId());
		 * tempMap.get(acount.getBonus()).setType2Money(acount.getType2Money());
		 * tempMap.get(acount.getBonus()).setFlag("Y"); } else {
		 * tempMap.get(acount.getBonus()).setTypeA(acount.getAcount());
		 * tempMap.get(acount.getBonus()).setTypeAId(acount.getId());
		 * tempMap.get(acount.getBonus()).setTypeMoney(acount.getTypeMoney()); }
		 * } if ("B".equals(acount.getAcountType())) if
		 * ("Y".equals(acount.getFlag())) {
		 * tempMap.get(acount.getBonus()).setType2B(acount.getAcount());
		 * tempMap.get(acount.getBonus()).setType2BId(acount.getId());
		 * tempMap.get(acount.getBonus()).setType2Money(acount.getTypeMoney());
		 * } else { tempMap.get(acount.getBonus()).setTypeB(acount.getAcount());
		 * tempMap.get(acount.getBonus()).setTypeBId(acount.getId());
		 * tempMap.get(acount.getBonus()).setTypeMoney(acount.getTypeMoney()); }
		 * if ("C".equals(acount.getAcountType())) if
		 * ("Y".equals(acount.getFlag())) {
		 * tempMap.get(acount.getBonus()).setType2C(acount.getAcount());
		 * tempMap.get(acount.getBonus()).setType2CId(acount.getId());
		 * tempMap.get(acount.getBonus()).setType2Money(acount.getTypeMoney());
		 * } else { tempMap.get(acount.getBonus()).setTypeC(acount.getAcount());
		 * tempMap.get(acount.getBonus()).setTypeCId(acount.getId());
		 * tempMap.get(acount.getBonus()).setTypeMoney(acount.getTypeMoney()); }
		 * } else { if ("A".equals(acount.getAcountType())) if
		 * ("Y".equals(acount.getFlag())) {
		 * acount.setType2A(acount.getAcount());
		 * acount.setType2AId(acount.getId());
		 * acount.setType2Money(acount.getTypeMoney()); } else {
		 * acount.setTypeA(acount.getAcount());
		 * acount.setTypeAId(acount.getId()); } if
		 * ("B".equals(acount.getAcountType())) if
		 * ("Y".equals(acount.getFlag())) {
		 * acount.setType2B(acount.getAcount());
		 * acount.setType2BId(acount.getId());
		 * acount.setType2Money(acount.getTypeMoney()); } else {
		 * acount.setTypeB(acount.getAcount());
		 * acount.setTypeBId(acount.getId()); } if
		 * ("C".equals(acount.getAcountType())) if
		 * ("Y".equals(acount.getFlag())) {
		 * acount.setType2C(acount.getAcount());
		 * acount.setType2CId(acount.getId());
		 * acount.setType2Money(acount.getTypeMoney()); } else {
		 * acount.setTypeC(acount.getAcount());
		 * acount.setTypeCId(acount.getId()); } tempMap.put(acount.getBonus(),
		 * acount); } } Iterator it = tempMap.entrySet().iterator(); while
		 * (it.hasNext()) { Map.Entry entry = (Map.Entry) it.next();
		 * kunnrAcountList.add((KunnrAcount) entry.getValue()); }
		 */
		return getSqlMapClientTemplate().queryForList(
				"kunnr.getKunnrAcountList", kunnr);
	}

	@Override
	public void createSaleArea(final List<KunnrSalesArea> kunnrSalesAreaList,
			final String kunnr) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			@Override
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (KunnrSalesArea area : kunnrSalesAreaList) {
					area.setKunnr(kunnr);
					executor.insert("kunnr.createSaleArea", area);
				}
				executor.executeBatch();
				return true;
			}
		});
	}

	/**
	 * 创建修改经销商政策
	 * 
	 * @param kunnrPolicyList
	 * @param kunnr
	 */
	@Override
	public void createKunnrPolicy(final List<KunnrPolicy> kunnrPolicyList,
			final String kunnr,final String flag) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			@Override
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (KunnrPolicy kunnrPolicy : kunnrPolicyList) {
					kunnrPolicy.setKunnr(kunnr);
					if("2".equals(flag)){
						executor.update("kunnr.updateKunnrPolicy", kunnrPolicy);
					}else{
						executor.insert("kunnr.createKunnrPolicy", kunnrPolicy);
					}
				}
				executor.executeBatch();
				return true;
			}
		});
	}

 

	@Override
	public void updateAndCreateSalesArea(
			final List<KunnrSalesArea> salesAreaList, final String kunnr) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			@Override
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (KunnrSalesArea salesArea : salesAreaList) {
					salesArea.setKunnr(kunnr);
					if (salesArea.getId() != null) {
						executor.update("kunnr.updateKunnrSalesArea", salesArea);
					} else {
						executor.insert("kunnr.createSaleArea", salesArea);
					}
				}
				executor.executeBatch();
				return true;
			}
		});

	}

	@Override
	public void removeSalesArea(final List<KunnrSalesArea> killSalesArea) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			@Override
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (KunnrSalesArea area : killSalesArea) {
					if (null != area) {
						Long salesAreaId = area.getId();
						executor.delete("kunnr.removeSalesArea", salesAreaId);
					}
				}
				executor.executeBatch();
				return true;
			}
		});

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<KunnrLogisticsArea> getKunnrLogisticsArea(
			KunnrLogisticsArea area) {
		return getSqlMapClientTemplate()
				.queryForList("kunnr.getKunnrLogisticsArea", area);
	}

	@Override
	public int getKunnrLogisticsAreaCount(KunnrLogisticsArea area) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"kunnr.getKunnrLogisticsAreaCount", area);
	}

	@Override
	public void updateLogisticArea(final List<KunnrLogisticsArea> areaList) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			@Override
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (KunnrLogisticsArea area : areaList) {
					executor.update("kunnr.updateLogisticArea", area);

				}
				executor.executeBatch();
				return true;
			}
		});
	}

	@Override
	public int getRoleOnEventByUser(String userId, String roleId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("roleId", roleId);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"kunnr.getRoleOnEventByUser", map);
	}

	/**
	 * 经销商政策
	 * 
	 * @param kunnr
	 * @return
	 */
	@Override
	public KunnrPolicy getKunnrPolicyEntity(Kunnr kunnr) {
		return (KunnrPolicy) getSqlMapClientTemplate().queryForObject(
				"kunnr.getKunnrPolicyEntity", kunnr);
	}
	
	@Override
	public Long savekunnrPunish(KunnrPunish kunnrPunish) {
		return (Long) getSqlMapClientTemplate().insert("kunnr.createkunnrPunish",
				kunnrPunish);
		
	}
	/**
	 * 经销商处罚记录Account
	 * 
	 * */
	
	@Override
	public int kunnrPunishSearchCount(KunnrPunish kunnrPunish) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"kunnr.kunnrPunishSearchCount", kunnrPunish);
	}
	/**
	 * 经销商处罚记录查询
	 * */
	
	@Override
	@SuppressWarnings("unchecked")
	public List<KunnrPunish> kunnrPunishSearch(KunnrPunish kunnrPunish) {
		return getSqlMapClientTemplate().queryForList(
				"kunnr.kunnrPunishSearch", kunnrPunish);
	}

	@Override
	public int updateKunnrAutoDemand(Kunnr kunnr) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().update("kunnr.updateKunnrAutoDemand", kunnr);
	}

	@Override
	public List<Knvp> getknvpList(Knvp knvp) {
	    return this.getSqlMapClientTemplate().queryForList("kunnr.getknvpList",knvp);
	}

	@Override
	public List<Kunnr> queryKunnrCompany(Kunnr kunnr) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("kunnr.queryKunnrCompany", kunnr);
	}

	@Override
	public List<Knvp> getAllknvpList(Knvp knvp) {
		 return this.getSqlMapClientTemplate().queryForList("kunnr.getAllknvpList",knvp);
	}
	
	@Override
	public List<Knvp> getAllknvpList1(Knvp knvp) {
		 return this.getSqlMapClientTemplate().queryForList("kunnr.getAllknvpList1",knvp);
	}

	@Override
	public int getknvpListCount(Knvp knvp) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"kunnr.getTvkbzCount", knvp);
	}

	@Override
	public List<AllUsers> getAllUserByCode(AllUsers user) {
		 return this.getSqlMapClientTemplate().queryForList("kunnr.getAllUserByCode",user);
 	}
	
	@Override
	public List<Kunnr> getKunnrByKunnr(Kunnr kunnr){
		 return this.getSqlMapClientTemplate().queryForList("kunnr.getKunnrByKunnr",kunnr);
	}
	
	@Override
	public Kunnr getPhonesByKunnr(Kunnr kunnr) {
		return (Kunnr)this.getSqlMapClientTemplate().queryForObject("kunnr.getPhonesByKunnr",kunnr);
	}

}
