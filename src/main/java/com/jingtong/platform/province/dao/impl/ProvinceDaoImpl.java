package com.jingtong.platform.province.dao.impl;

import java.util.List;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.province.dao.IProvinceDao;
import com.jingtong.platform.province.pojo.Province;


public class ProvinceDaoImpl extends BaseDaoImpl implements IProvinceDao {

	@Override
	public List<Province> searchProvince(Province g) {
		return getSqlMapClientTemplate().queryForList("province.searchProvince",g);
	}

	@Override
	public long saveProvince(Province g) {
		return (Long) getSqlMapClientTemplate().insert("province.saveProvince", g);
	}

	@Override
	public int updateProvince(Province g) {
		return getSqlMapClientTemplate().update("province.updateProvince", g);
	}

	@Override
	public int deleteProvinceById(Province g) {
		return getSqlMapClientTemplate().delete("province.deleteProvinceById", g);
	}

	@Override
	public Province getProvinceById(Province g) {
		return (Province)getSqlMapClientTemplate().queryForObject("province.getProvinceById", g);
	}

	@Override
	public int getProvinceListCount(Province g) {
		return (Integer)getSqlMapClientTemplate().queryForObject("province.getProvinceListCount", g);
	}

	@Override
	public int getCountByCode(Province g) {
		int n= (Integer) getSqlMapClientTemplate().queryForObject("province.getCountByCode", g);
		return n;
	}


}
