package com.jingtong.platform.ecgroup.service;

import java.util.List;

import com.jingtong.platform.ecgroup.pojo.GroupInfo;

/** 
 * @author cl 
 * @createDate 2016-5-25
 * 
 */
public interface IGroupInfoService {
public List<GroupInfo> searchGroupInfo(GroupInfo g);
	
	public long saveGroupInfo(GroupInfo g);
	
	public int updateGroupInfo(GroupInfo g);
	
	
	public int deleteGroupInfoById(GroupInfo g);

	public GroupInfo getGroupInfoById(GroupInfo g);
	
	
	public int getGroupInfoListCount(GroupInfo g);
	public int getCountByEcgroupId(GroupInfo g);
	public int auditGroup(GroupInfo g);

	List<GroupInfo> getGroupList(GroupInfo g);

	int getGroupListCount(GroupInfo g);

	public int setECGroupCode(GroupInfo g);
}
