package com.jingtong.platform.sap.dao.impl;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.sap.dao.MaterialDao;
import com.jingtong.platform.sap.pojo.Material;

public class MaterialDaoImpl extends BaseDaoImpl  implements MaterialDao {

	@Override
	public String creatMaterial(Material material) {
		long id = (Long) getSqlMapClientTemplate().insert("sapReport.insertMaterials",material);
		return id+"";
		
		
	}

	@Override
	public int updateMaterial(Material material) {
		return getSqlMapClientTemplate().update("sapReport.updateMaterials", material);
	}

	@Override
	public int getMaterialCount(Material material) {
		
		return  (Integer) getSqlMapClientTemplate().queryForObject("sapReport.getMaterialsCount", material);
	}



}
