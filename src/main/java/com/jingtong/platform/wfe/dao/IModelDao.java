package com.jingtong.platform.wfe.dao;

import java.util.List;

import com.jingtong.platform.wfe.pojo.ActProcdef;

public interface IModelDao {

	public List<ActProcdef> invokeUserModel(ActProcdef actProcdef);
}
