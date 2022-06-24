package com.jingtong.platform.sap.dao;

import com.jingtong.platform.sap.pojo.Material;

public interface MaterialDao {

	public String creatMaterial(Material material);
	
	public int updateMaterial(Material material);
	
	public int getMaterialCount(Material material);
	

}
