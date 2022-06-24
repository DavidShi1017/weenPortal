package com.jingtong.platform.logisticsScheduling.service.impl;


import java.util.List;

import com.jingtong.platform.logisticsScheduling.dao.LoadCapacityDao;
import com.jingtong.platform.logisticsScheduling.pojo.LoadCapacity;
import com.jingtong.platform.logisticsScheduling.service.LoadCapacityService;


public class LoadCapacityServiceImpl implements LoadCapacityService {

	private LoadCapacityDao loadCapacityDao;

	public LoadCapacityDao getLoadCapacityDao() {
		return loadCapacityDao;
	}

	public void setLoadCapacityDao(LoadCapacityDao loadCapacityDao) {
		this.loadCapacityDao = loadCapacityDao;
	}

	@Override
	public int getLoadCapacityListTotal(LoadCapacity lc) {
		// TODO Auto-generated method stub
		return this.loadCapacityDao.getLoadCapacityListTotal(lc);
	}

	@Override
	public List<LoadCapacity> getLoadCapacityList(LoadCapacity lc) {
		// TODO Auto-generated method stub
		return this.loadCapacityDao.getLoadCapacityList(lc);
	}

	@Override
	public int updateLoadCapacity(LoadCapacity loadCapacity) {
		// TODO Auto-generated method stub
		return this.loadCapacityDao.updateLoadCapacity(loadCapacity);
	}

	@Override
	public int saveLoadCapacity(LoadCapacity loadCapacity) {
		// TODO Auto-generated method stub
		return this.loadCapacityDao.saveLoadCapacity(loadCapacity);
	}

	@Override
	public LoadCapacity getLoadCapacity(Long ids) {
		// TODO Auto-generated method stub
		return this.loadCapacityDao.getLoadCapacity(ids);
	}

	@Override
	public int deleteLoadCapacity(Long id) {
		return this.loadCapacityDao.deleteLoadCapacity(id);
		
	}

	
}
