package com.jingtong.platform.sap.dao.impl;

import java.util.List;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.sap.dao.WarehouseDao;
import com.jingtong.platform.sap.pojo.LoadPoint;
import com.jingtong.platform.sap.pojo.Warehouses;

public class WarehouseDaoImpl extends BaseDaoImpl  implements WarehouseDao {

	@Override
	public List<Warehouses> queryWarehouseList(Warehouses warehouse) {
		// TODO Auto-generated method stub
		List<Warehouses> warehouseList = this.getSqlMapClientTemplate().queryForList("warehouses.queryWarehouseList", warehouse);
		return warehouseList;
	}

	@Override
	public List<LoadPoint> queryLoadPointList(LoadPoint loadPoint) {
		return this.getSqlMapClientTemplate().queryForList("warehouses.queryLoadPointList", loadPoint);
	}

	@Override
	public void addWarehouse(Warehouses warehouse) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("warehouses.addWarehouse", warehouse);
	}

	@Override
	public void updateWarehouse(Warehouses warehouse) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update("warehouses.updateWarehouse", warehouse);
	}

	@Override
	public void addLoadPoint(LoadPoint loadPoint) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("warehouses.addLoadPoint", loadPoint);
	}

	@Override
	public void updateLoadPoint(LoadPoint loadPoint) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update("warehouses.updateLoadPoint", loadPoint);
	}

}
