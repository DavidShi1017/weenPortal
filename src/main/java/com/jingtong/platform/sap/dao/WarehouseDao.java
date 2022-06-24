package com.jingtong.platform.sap.dao;

import java.util.List;

import com.jingtong.platform.sap.pojo.LoadPoint;
import com.jingtong.platform.sap.pojo.Warehouses;

public interface WarehouseDao {

	public List<Warehouses> queryWarehouseList(Warehouses warehouse);
	
	public List<LoadPoint> queryLoadPointList(LoadPoint loadPoint);
	
	public void addWarehouse(Warehouses warehouse) throws Exception;
	
	public void updateWarehouse(Warehouses warehouse) throws Exception; 
	
	public void addLoadPoint(LoadPoint loadPoint) throws Exception;
	
	public void updateLoadPoint(LoadPoint loadPoint) throws Exception;
}
