package com.jingtong.platform.logisticsScheduling.dao.impl;

import java.util.List;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.logisticsScheduling.dao.LoadCapacityDao;
import com.jingtong.platform.logisticsScheduling.pojo.LoadCapacity;
import com.jingtong.platform.monitor.pojo.HessianDetail;

public class LoadCapacityDaoImpl extends BaseDaoImpl implements LoadCapacityDao {

	@Override
	public int getLoadCapacityListTotal(LoadCapacity lc) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject("loadCapacity.getLoadCapacityListTotal",lc);
	}

	@Override
	public List<LoadCapacity> getLoadCapacityList(LoadCapacity lc) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("loadCapacity.getLoadCapacityList", lc);
	}

	@Override
	public int updateLoadCapacity(LoadCapacity loadCapacity) {
		// TODO Auto-generated method stub
				return this.getSqlMapClientTemplate().update("loadCapacity.updateLoadCapacity", loadCapacity);
	}

	@Override
	public int saveLoadCapacity(LoadCapacity loadCapacity) {
		String id=this.getSqlMapClientTemplate().insert("loadCapacity.insetLoadCapacity", loadCapacity)+"";
		return Integer.parseInt(id);
	}

	@Override
	public LoadCapacity getLoadCapacity(Long ids) {
		// TODO Auto-generated method stub
		return (LoadCapacity) this.getSqlMapClientTemplate().queryForObject("loadCapacity.getLoadCapacity", ids);
	}

	@Override
	public int deleteLoadCapacity(Long id) {
		return this.getSqlMapClientTemplate().delete("loadCapacity.delLoadCapacity", id);
	}


}
