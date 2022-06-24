package com.jingtong.platform.ecgroup.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jingtong.platform.ecgroup.dao.IGroupInfoDao;
import com.jingtong.platform.ecgroup.pojo.GroupInfo;
import com.jingtong.platform.ecgroup.service.IGroupInfoService;

/** 
 * @author cl 
 * @createDate 2016-5-25
 * 
 */
public class GroupInfoServiceImpl implements IGroupInfoService {
	private static final Log logger = LogFactory.getLog(GroupInfoServiceImpl.class);
	private IGroupInfoDao groupInfoDao;
	
	public static Log getLogger() {
		return logger;
	}

	public IGroupInfoDao getGroupInfoDao() {
		return groupInfoDao;
	}

	public void setGroupInfoDao(IGroupInfoDao groupInfoDao) {
		this.groupInfoDao = groupInfoDao;
	}


	@Override
	public List<GroupInfo> searchGroupInfo(GroupInfo g) {
		try {
			return groupInfoDao.searchGroupInfo(g);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<GroupInfo> getGroupList(GroupInfo g) {
		try {
			return groupInfoDao.getGroupList(g);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public long saveGroupInfo(GroupInfo g) {
		try {
			return groupInfoDao.saveGroupInfo(g);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateGroupInfo(GroupInfo g) {
		try {
			return groupInfoDao.updateGroupInfo(g);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int deleteGroupInfoById(GroupInfo g) {
		try {
			return groupInfoDao.deleteGroupInfoById(g);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public GroupInfo getGroupInfoById(GroupInfo g) {
		try {
			return groupInfoDao.getGroupInfoById(g);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getGroupInfoListCount(GroupInfo g) {
		try {
			return groupInfoDao.getGroupInfoListCount(g);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int getGroupListCount(GroupInfo g) {
		try {
			return groupInfoDao.getGroupListCount(g);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public int getCountByEcgroupId(GroupInfo g) {
		try {
			return groupInfoDao.getCountByEcgroupId(g);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int auditGroup(GroupInfo g) {
		try {
			return groupInfoDao.auditGroup(g);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int setECGroupCode(GroupInfo g) {
		try {
			return groupInfoDao.setECGroupCode(g);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	
}
