package com.jingtong.platform.wfe.dao.impl;

import java.util.List;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.wfe.dao.IModelDao;
import com.jingtong.platform.wfe.pojo.ActProcdef;

@SuppressWarnings("rawtypes")
public class ModelDaoImpl extends BaseDaoImpl implements IModelDao {

	 
	@SuppressWarnings("unchecked")
	public List<ActProcdef> invokeUserModel(ActProcdef actProcdef) {
		return getSqlMapClientTemplate().queryForList(
					"wfe.getUserModel", actProcdef);

	}

}
