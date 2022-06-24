package com.jingtong.platform.ecgroup.Action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.ecgroup.pojo.GroupInfo;
import com.jingtong.platform.ecgroup.service.IGroupInfoService;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.framework.util.DataUtil;

/**
 * @author cl
 * @createDate 2016-5-25
 * 
 */
public class GroupInfoAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private IGroupInfoService groupInfoService;
	private GroupInfo g;
	private List<GroupInfo> gList;
	private String id;
	private int total;
	private String ids;
	private String states;
	private String ecGroup_id;
	private String ecGroup_name;
	private String state;

	/**
	 * 跳转到查询页面
	 * 
	 * @return
	 */
	public String tosearchGroupInfo() {
		return "tosearchGroupInfo";
	}

	/**
	 * 跳转到查看页面
	 * 
	 * @return
	 */
	public String toViewGroup() {
		g = new GroupInfo();
		g.setId(Long.valueOf(id));
		g = groupInfoService.getGroupInfoById(g);
		return "toViewGroup";
	}

	/**
	 * 跳转到新增页面
	 * 
	 * @return
	 */
	public String toCreateGroup() {
		g = new GroupInfo();
		g.setState(0);
		return "toCreateGroup";
	}

	// 跳转到修改页面
	public String toUpdateGroup() {
		g = new GroupInfo();
		g.setId(Long.valueOf(id));
		g = groupInfoService.getGroupInfoById(g);
		return "toCreateGroup";
	}

	/**
	 * 查询订单信息
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "gList", include = { "id", "state", "ecGroup_id",
			"ecGroup_name", "create_time", "create_timestr", "create_userId",
			"org_code", "modify_time", "modify_timestr", "modify_userId" }, total = "total")
	public String getGroupInfoList() {

		g = new GroupInfo();
		g.setStart(getStart());
		g.setEnd(getEnd());
		g.setSort("id");
		g.setDir("desc");
		g.setStates(states);
		String userId = this.getUser().getUserId();
		//!"admin".equals(this.getUser().getLoginId())
		if (this.getUser().getOrgId()==null || "".equals(this.getUser().getOrgId())) {
			g.setCreate_userId(userId);		
		}
		if (StringUtils.isNotEmpty(ecGroup_id)
				&& StringUtils.isNotEmpty(ecGroup_id.trim())) {
			ecGroup_id=ecGroup_id.toUpperCase();
		}
		g.setEcGroup_id(ecGroup_id);
 		if (StringUtils.isNotEmpty(ecGroup_name)
				&& StringUtils.isNotEmpty(ecGroup_name.trim())) {
				ecGroup_name=ecGroup_name.toUpperCase();
		}
		g.setEcGroup_name(ecGroup_name);
		total = groupInfoService.getGroupInfoListCount(g);
		//if (total > 0) {
			gList = groupInfoService.searchGroupInfo(g);
		//}
		return JSON;
	}
	/**
	 * 查询订单信息
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "gList", include = { "id", "state", "ecGroup_id",
			"ecGroup_name", "create_time", "create_timestr", "create_userId",
			"org_code", "modify_time", "modify_timestr", "modify_userId" }, total = "total")
	public String getGroupList() {
		g = new GroupInfo();
		g.setStart(getStart());
		g.setEnd(getEnd());
		g.setSort("create_time");
		g.setDir("desc");
		g.setStates(states);
		String userId = this.getUser().getUserId();
		if (this.getUser().getOrgId()==null || "".equals(this.getUser().getOrgId())) {
			g.setCreate_userId(userId);		
		}
		if (StringUtils.isNotEmpty(ecGroup_id)
				&& StringUtils.isNotEmpty(ecGroup_id.trim())) {
			ecGroup_id=ecGroup_id.toUpperCase();
		}
		g.setEcGroup_id(ecGroup_id);
 		if (StringUtils.isNotEmpty(ecGroup_name)
				&& StringUtils.isNotEmpty(ecGroup_name.trim())) {
				ecGroup_name=ecGroup_name.toUpperCase();
		}
		g.setEcGroup_name(ecGroup_name);

		total = groupInfoService.getGroupListCount(g);
		if (total > 0) {
			gList = groupInfoService.getGroupList(g);
		}
		return JSON;
	}
	/**
	 * 添加信息
	 * 
	 * @return
	 */
	public String addCodeType() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		g.setCreate_userId(this.getUser().getUserId());
		GroupInfo aa = new GroupInfo();
		aa.setEcGroup_name(g.getEcGroup_name().toUpperCase());
		Boolean flag = checkGroupInfoId(aa);
		if (flag) {
			g.setEcGroup_name(g.getEcGroup_name().toUpperCase());
			long result = groupInfoService.saveGroupInfo(g);
			if (result > 0) {
				String ecGroupId=DataUtil.frontCompWithZore("G",result, 7);
				g.setEcGroup_id(ecGroupId);
				g.setId(result);
				int n = groupInfoService.setECGroupCode(g);
				this.setSuccessMessage("Success!");
			} else {
				this.setFailMessage("Failed!");
			}
		} else {
			this.setFailMessage("Name aready exist!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 修改信息
	 */
	public String updateCodeType() {

		this.setSuccessMessage("");
		this.setFailMessage("");
		g.setModify_userId(this.getUser().getUserId());
		GroupInfo aa = new GroupInfo();
		aa.setEcGroup_name(g.getEcGroup_name());		
		if (aa.getEcGroup_name().equals(g.getOldName())) {
			g.setEcGroup_name(g.getEcGroup_name().toUpperCase());
			long result = groupInfoService.updateGroupInfo(g);
			if (result > 0) {
				this.setSuccessMessage("Success!");
			} else {
				this.setFailMessage("Failed!");
			}
		} else {
			aa.setEcGroup_name(aa.getEcGroup_name().toUpperCase());
			Boolean flag = checkGroupInfoId(aa);
			if (flag) {
				g.setEcGroup_name(g.getEcGroup_name().toUpperCase());
				long result = groupInfoService.updateGroupInfo(g);
				if (result > 0) {
					this.setSuccessMessage("Success！");
				} else {
					this.setFailMessage("Failed！");
				}
			} else {
				this.setFailMessage("Name aready exist!");
			}
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 删除信息
	 * 
	 * @return
	 */
	public String delCodeType() {

		this.setFailMessage("");
		this.setSuccessMessage("");
		g = new GroupInfo();
		g.setId(Long.valueOf(id));
		int i = groupInfoService.deleteGroupInfoById(g);
		if (i > 0) {
			this.setSuccessMessage("Success!");
		} else {
			this.setFailMessage("Failed!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 验证该编码名是否被占用
	 * 
	 * @param g
	 * @return
	 */
	private Boolean checkGroupInfoId(GroupInfo g) {
		Boolean b = true;
		int n = groupInfoService.getCountByEcgroupId(g);
		if (n >= 1) {
			b = false;
		}
		return b;

	}

	/**
	 * 审核
	 * 
	 * @return
	 */
	public String auditGroup() {
		this.setFailMessage("");
		this.setSuccessMessage("");
		g = new GroupInfo();
		g.setId(Long.valueOf(id));
		g.setState(Integer.valueOf(state));
		int i = groupInfoService.auditGroup(g);
		if (i > 0) {
			this.setSuccessMessage("Success!");
		} else {
			this.setFailMessage("Failed!");
		}
		return RESULT_MESSAGE;
	}

	public IGroupInfoService getGroupInfoService() {
		return groupInfoService;
	}

	public void setGroupInfoService(IGroupInfoService groupInfoService) {
		this.groupInfoService = groupInfoService;
	}

	public GroupInfo getG() {
		return g;
	}

	public void setG(GroupInfo g) {
		this.g = g;
	}

	public List<GroupInfo> getgList() {
		return gList;
	}

	public void setgList(List<GroupInfo> gList) {
		this.gList = gList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getIds() {
		return ids;
	}

	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getEcGroup_id() {
		return ecGroup_id;
	}

	public void setEcGroup_id(String ecGroup_id) {
		this.ecGroup_id = ecGroup_id;
	}

	public String getEcGroup_name() {
		return ecGroup_name;
	}

	public void setEcGroup_name(String ecGroup_name) {
		this.ecGroup_name = ecGroup_name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
