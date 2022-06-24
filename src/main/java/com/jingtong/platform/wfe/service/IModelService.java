package com.jingtong.platform.wfe.service;

import java.util.List;

import com.jingtong.platform.wfe.pojo.ActProcdef;


/**
 * 
 * 流程模板
 * 
 */
public interface IModelService {

	/**
	 * 获取流程模板
	 * @return
	 */
	public List<ActProcdef> getModelByRole(ActProcdef actProcdef);
}
