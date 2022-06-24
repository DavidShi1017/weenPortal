package com.jingtong.platform.country.dao.impl;

import java.util.List;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.country.pojo.BranchMapping;
import com.jingtong.platform.country.dao.ICountryDao;
import com.jingtong.platform.country.pojo.Country;
import com.jingtong.platform.country.pojo.SaleCountry;


public class CountryDaoImpl extends BaseDaoImpl implements ICountryDao {

	@Override
	public List<Country> searchCountry(Country g) {
		return getSqlMapClientTemplate().queryForList("country.searchCountry",g);
	}

	@Override
	public long saveCountry(Country g) {
		return (Long) getSqlMapClientTemplate().insert("country.saveCountry", g);
	}

	@Override
	public int updateCountry(Country g) {
		return getSqlMapClientTemplate().update("country.updateCountry", g);
	}

	@Override
	public int deleteCountryById(Country g) {
		return getSqlMapClientTemplate().delete("country.deleteCountryById", g);
	}

	@Override
	public Country getCountryById(Country g) {
		return (Country)getSqlMapClientTemplate().queryForObject("country.getCountryById", g);
	}

	@Override
	public int getCountryListCount(Country g) {
		return (Integer)getSqlMapClientTemplate().queryForObject("country.getCountryListCount", g);
	}

	@Override
	public int getCountByCode(Country g) {
		int n= (Integer) getSqlMapClientTemplate().queryForObject("country.getCountByCode", g);
		return n;
	}

	@Override
	public int getSaleCountryListCount(SaleCountry c) {
		return (Integer) getSqlMapClientTemplate().queryForObject("country.getSaleCountryListCount",c);
	}

	@Override
	public List<SaleCountry> searchSaleCountry(SaleCountry c) {
		return getSqlMapClientTemplate().queryForList("country.searchSaleCountry",c);
	}

	@Override
	public List<Country> searchCountryProvince(Country c) {
		return getSqlMapClientTemplate().queryForList("country.searchCountryProvince",c);
	}

	@Override
	public long addSaleCountry(SaleCountry sc) {
		return (Long) getSqlMapClientTemplate().insert("country.addSaleCountry", sc);
	}

	@Override
	public long assignCountry(SaleCountry sc) {
		return getSqlMapClientTemplate().update("country.assignCountry", sc);
	}

	@Override
	public long assignProvince(SaleCountry sc) {
		return getSqlMapClientTemplate().update("country.assignProvince", sc);
	}

	@Override
	public long delSaleCountry(SaleCountry sc) {
		return getSqlMapClientTemplate().delete("country.delSaleCountry", sc);
	}

	@Override
	public int getBranchMappingListCount(BranchMapping pr){
		return (Integer) getSqlMapClientTemplate().queryForObject("country.getBranchMappingListCount",pr);
	}

	@Override
	public List<BranchMapping> getBranchMappingList(BranchMapping pr) {
		return (List<BranchMapping>) getSqlMapClientTemplate().queryForList("country.getBranchMappingList",pr);
	}

	
	@Override
	public long createBranchMapping(BranchMapping pr) {
		return  (Long) getSqlMapClientTemplate().insert("country.createBranchMapping",pr);
	}

	@Override
	public int updateBranchMapping(BranchMapping pr) {
		return (int) getSqlMapClientTemplate().update("country.updateBranchMapping",pr);
	}

	@Override
	public int deleteBranchMapping(BranchMapping pr) {
		return (int) getSqlMapClientTemplate().delete("country.deleteBranchMapping",pr);
	}

    @Override
    public Country getOrgCodeByOrgId(Country c) {
        return (Country)getSqlMapClientTemplate().queryForObject("country.getOrgCodeByOrgId", c);
    }
}
