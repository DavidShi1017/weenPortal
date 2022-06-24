package com.jingtong.platform.logisticsScheduling.dao;

import java.util.List;

import com.jingtong.platform.logisticsScheduling.pojo.LoadCapacity;
import com.jingtong.platform.monitor.pojo.HessianDetail;

public interface LoadCapacityDao {

	int getLoadCapacityListTotal(LoadCapacity lc);

	List<LoadCapacity> getLoadCapacityList(LoadCapacity lc);

	int updateLoadCapacity(LoadCapacity loadCapacity);

	int saveLoadCapacity(LoadCapacity loadCapacity);

	LoadCapacity getLoadCapacity(Long ids);

	int deleteLoadCapacity(Long id);
	
}
