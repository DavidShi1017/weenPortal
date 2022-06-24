package com.jingtong.platform.base.dao.impl;


import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.jingtong.platform.base.dao.IBaseDao;

public class BaseDaoImpl<T> extends SqlMapClientDaoSupport implements
		IBaseDao<T> {
     
}
