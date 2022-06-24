package com.jingtong.platform.wfe.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.jingtong.platform.framework.util.LogUtil;
import com.jingtong.platform.wfe.dao.IModelDao;
import com.jingtong.platform.wfe.pojo.ActProcdef;
import com.jingtong.platform.wfe.service.IModelService;


public class ModelServiceImpl implements IModelService {

	private Logger logger = Logger.getLogger(ModelServiceImpl.class);
	
	private IModelDao modelDao;

	/**
	 * 获取流程模板
	 */
	
	public List<ActProcdef> getModelByRole(ActProcdef actProcdef) {
		try{
			return modelDao.invokeUserModel(actProcdef);
		}catch(Exception e){
			logger.error(LogUtil.parserBean(this.getClass()), e);
			return null;
		}
	}
	
	public IModelDao getModelDao() {
		return modelDao;
	}

	public void setModelDao(IModelDao modelDao) {
		this.modelDao = modelDao;
	}

	
	
}
