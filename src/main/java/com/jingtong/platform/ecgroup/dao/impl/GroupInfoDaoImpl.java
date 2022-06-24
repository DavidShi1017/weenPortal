package com.jingtong.platform.ecgroup.dao.impl;

import java.util.List;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.ecgroup.dao.IGroupInfoDao;
import com.jingtong.platform.ecgroup.pojo.GroupInfo;

/** 
 * @author cl 
 * @createDate 2016-5-25
 * 
 */
public class GroupInfoDaoImpl extends BaseDaoImpl implements IGroupInfoDao {

	@Override
	public List<GroupInfo> searchGroupInfo(GroupInfo g) {
		return getSqlMapClientTemplate().queryForList("ecGroup.searchGroupInfo",g);
	}
	@Override
	public List<GroupInfo> getGroupList(GroupInfo g) {
		return getSqlMapClientTemplate().queryForList("ecGroup.getGroupList",g);
	}
	@Override
	public long saveGroupInfo(GroupInfo g) {
		return (Long) getSqlMapClientTemplate().insert("ecGroup.saveGroupInfo", g);
	}

	@Override
	public int updateGroupInfo(GroupInfo g) {
		return getSqlMapClientTemplate().update("ecGroup.updateGroupInfo", g);
	}

	@Override
	public int deleteGroupInfoById(GroupInfo g) {
		return getSqlMapClientTemplate().delete("ecGroup.deleteGroupInfoById", g);
	}

	@Override
	public GroupInfo getGroupInfoById(GroupInfo g) {
		return (GroupInfo)getSqlMapClientTemplate().queryForObject("ecGroup.getGroupInfoById", g);
	}

	@Override
	public int getGroupInfoListCount(GroupInfo g) {
		return (Integer)getSqlMapClientTemplate().queryForObject("ecGroup.getGroupInfoListCount", g);
	}
	@Override
	public int getGroupListCount(GroupInfo g) {
		return (Integer)getSqlMapClientTemplate().queryForObject("ecGroup.getGroupListCount", g);
	}
	@Override
	public int getCountByEcgroupId(GroupInfo g) {
		int n= (Integer) getSqlMapClientTemplate().queryForObject("ecGroup.getCountByEcgroupId", g);
		return n;
	}

	@Override
	public int auditGroup(GroupInfo g) {
		return getSqlMapClientTemplate().update("ecGroup.auditGroup", g);
	}
	@Override
	public int setECGroupCode(GroupInfo g) {
		return getSqlMapClientTemplate().update("ecGroup.setECGroupCode", g);
	}

}
