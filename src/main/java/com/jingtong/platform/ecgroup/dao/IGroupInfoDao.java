package com.jingtong.platform.ecgroup.dao;

import java.util.List;

import com.jingtong.platform.ecgroup.pojo.GroupInfo;


/** 
 * @author cl 
 * @createDate 2016-5-25
 * 
 */

public interface IGroupInfoDao {
	/**
	 * 查询EC集团信息
	 * @return
	 */
	public List<GroupInfo> searchGroupInfo(GroupInfo g);
	
	/**
	 * 添加新的信息
	 * @param g
	 */
	public long saveGroupInfo(GroupInfo g);
	
	/**
	 * 修改信息
	 * @param g
	 */
	public int updateGroupInfo(GroupInfo g);
	
	
	
	/**
	 * 删除信息
	 * @param g
	 */
	public int deleteGroupInfoById(GroupInfo g);

	public GroupInfo getGroupInfoById(GroupInfo g);
	
	
	public int getGroupInfoListCount(GroupInfo g);
	public int getCountByEcgroupId(GroupInfo g);
	
	public int auditGroup(GroupInfo g);

	public int getGroupListCount(GroupInfo g);

	List<GroupInfo> getGroupList(GroupInfo g);

	public int setECGroupCode(GroupInfo g);
}
