package com.jingtong.platform.org.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.allUser.service.IAllUserService;
import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.base.pojo.StringArrayResult;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.menu.pojo.Tree4Ajax;
import com.jingtong.platform.org.pojo.Borg;
import com.jingtong.platform.org.pojo.SapTOrgUnit;
import com.jingtong.platform.org.service.IOrgService;
import com.jingtong.platform.position.action.PositionTypeAction;

/**
 * OrgTreeAjax
 * 
 * @author xujiakun
 * 
 */
public class OrgTreeAjaxAction extends BaseAction {

	private Logger logger = Logger.getLogger(PositionTypeAction.class);

	private static final long serialVersionUID = 9124918976690173831L;

	private String node;

	private String orgId;

	private String orgName;

	private String sapOrgId;

	private String actionName;

	private List<Tree4Ajax> treeList;

	private IOrgService orgService;

	private StringArrayResult companyInfo = new StringArrayResult();

	private String userId;

	private IAllUserService allUserService;
	private String  flag ;

	@PermissionSearch
	public String getOrgTreeMain() {
		return SUCCESS;
	}

	/**
	 * 组织树 点击组织 查看该组织详细信息
	 * 
	 * @return
	 */
	@PermissionSearch
	public String getOrgTreeInfo() {
		// AllUsers users = this.getUser();
		this.actionName = "orgAction!showOrgDetail.jspa";
		this.node = "1";
		return "orgTreeAjaxInfo";
	}

	@PermissionSearch
	public String getOrgTreeInfo4User() {
		// AllUsers users = this.getUser();
		this.actionName = env.getProperty("appUrl")
				+ "/alluser/allUserAction!showUserByOrg.jspa";
		this.node = "1";
		return "orgTreeAjaxInfo";
	}

	@PermissionSearch
	public String toGetOrgTreeInfo4Position() {
		return "orgTreePosition";
	}

	@PermissionSearch
	public String getOrgTreeInfo4Position() {
		this.actionName = env.getProperty("appUrl")
				+ "/alluser/allUserAction!toGetPositionList.jspa";
		this.node = "1";
		return "orgTreeAjaxInfo";
	}

	/**
	 * 组织树 根据总部标记 判断是否显示整个组织树
	 * 
	 * @return
	 */
	@PermissionSearch
	public String getOrgTree() {
		AllUsers users = this.getUser();
		if ("1".equals(users.getHqSign())) {
			return "orgTreeAjax";
		} else {
			this.node = "-1";
			return "orgTreeAjax";
		}
	}

	/**
	 * 组织树 显示整个组织树
	 * 
	 * @return
	 */
	@PermissionSearch
	public String getOrgAllTree() {
		return "orgTreeAjax";
	}

	@PermissionSearch
	public String getOrgTree4UserChange() {
		return "orgTreeAjax4UserChange";
	}

	@PermissionSearch
	@JsonResult(field = "treeList", include = { "id", "text", "state" })
	public String getOrgTreeListByAjax() {
		treeList = new ArrayList<Tree4Ajax>();
		List<Borg> orgTreeList = new ArrayList<Borg>();
		try {
			if("Y".equals(flag)){
				Borg borg = new Borg();
				borg = orgService.getOrgByUserId(this.getUser().getUserId());
				orgTreeList.add(borg);
			}else{
				orgTreeList = orgService.getOrgTreeListByPorgId(node);
			}
		} catch (Exception e) {
			logger.error(node, e);
		}
		if (orgTreeList == null || orgTreeList.size() == 0) {
			return JSON;
		}
		for (Borg borg : orgTreeList) {
			Tree4Ajax tree = new Tree4Ajax();
			tree.setId(String.valueOf(borg.getOrgId()));
			tree.setText("("+String.valueOf(borg.getOrgCode())+")"+borg.getOrgName());
			tree.setState("closed");
			treeList.add(tree);
		}

		return JSON;
	}
	
	
	/**兼职职能费用核销**/
	@PermissionSearch
	@JsonResult(field = "treeList", include = { "id", "text", "state" })
	public String getOrgTreeListByUserId() {
		treeList = new ArrayList<Tree4Ajax>();
		List<Borg> orgTreeList = new ArrayList<Borg>();
		try {
			if("Y".equals(flag)){
				/*Borg borg = new Borg();
				borg = orgService.getOrgByUserId(node);
				orgTreeList.add(borg);*/
				orgTreeList = orgService.getOrgTreeListByUserId(node);
			}else{
				orgTreeList = orgService.getOrgTreeListByPorgId(node);
			}
		} catch (Exception e) {
			logger.error(node, e);
		}
		if (orgTreeList == null || orgTreeList.size() == 0) {
			return JSON;
		}
		for (Borg borg : orgTreeList) {
			Tree4Ajax tree = new Tree4Ajax();
			tree.setId(String.valueOf(borg.getOrgId()));
			tree.setText(borg.getOrgName());
			tree.setState("closed");
			treeList.add(tree);
		}
		return JSON;
	}

	@PermissionSearch
	@JsonResult(field = "treeList", include = { "id", "text", "orgUnit" })
	public String getSapOrgTreeListByAjax() {
		treeList = new ArrayList<Tree4Ajax>();

		List<SapTOrgUnit> sapOrgTreeList = null;
		try {
			if (StringUtils.isNotEmpty(node)) {
				sapOrgTreeList = orgService.getSapTOrgUnitListByPId(node);
			}
		} catch (Exception e) {
			logger.error(node, e);
		}

		if (sapOrgTreeList == null || sapOrgTreeList.size() == 0) {
			return JSON;
		}

		for (SapTOrgUnit sapTOrgUnit : sapOrgTreeList) {
			Tree4Ajax tree = new Tree4Ajax();
			tree.setId(String.valueOf(sapTOrgUnit.getNodeid()));
			tree.setText(sapTOrgUnit.getTxtsh() + ":"
					+ sapTOrgUnit.getOrgUnit());
			treeList.add(tree);
		}

		return JSON;
	}

	@PermissionSearch
	@JsonResult(field = "companyInfo", include = { "result", "code" })
	public String getCompany() {
		companyInfo.setCode("T");
		String[] result = new String[2];
		if (!StringUtils.isNotEmpty(orgId)) {
			companyInfo.setCode("F");
			result[0] = "未传入参数orgId";
		}
		Borg b = orgService.getOrgByOrgId(orgId);
		if (b.getCompanyName() != null) {
			result[0] = String.valueOf(b.getCompanyId());
			result[1] = String.valueOf(b.getCompanyName());
		} else {
			result[0] = String.valueOf("未查组织ID为" + orgId + "的公司");
		}
		companyInfo.setResult(result);
		return JSON;
	}

	@PermissionSearch
	public String getOrgNameByOrgid() {

		return "orgInfo";
	}

	/**
	 * 组织人员树 通讯录RTX
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "treeList", include = { "id", "text", "state",
			"iconCls" })
	public String getOrgUserTreeListByAjax() {
		treeList = new ArrayList<Tree4Ajax>();
		List<Borg> orgTreeList = null;
		List<AllUsers> userTreeList = null;
		try {
			userTreeList = allUserService.getEmpListByOrgId(node);
			orgTreeList = orgService.getOrgTreeListByPorgId(node);
		} catch (Exception e) {
			logger.error(node, e);
		}
		for (AllUsers user : userTreeList) {
			Tree4Ajax tree = new Tree4Ajax();
			tree.setId(user.getUserId());
			tree.setText(user.getUserName());
			// tree.setState("closed");
			if ("M".equals(user.getSex()))
				tree.setIconCls("icon-male");
			else
				tree.setIconCls("icon-female");
			treeList.add(tree);
		}
		for (Borg borg : orgTreeList) {
			Tree4Ajax tree = new Tree4Ajax();
			tree.setId(String.valueOf(borg.getOrgId()));
			tree.setText(borg.getOrgName());
			tree.setState("closed");
			treeList.add(tree);
		}
		return JSON;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSapOrgId() {
		return sapOrgId;
	}

	public void setSapOrgId(String sapOrgId) {
		this.sapOrgId = sapOrgId;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public List<Tree4Ajax> getTreeList() {
		return treeList;
	}

	public void setTreeList(List<Tree4Ajax> treeList) {
		this.treeList = treeList;
	}

	public IOrgService getOrgService() {
		return orgService;
	}

	public void setOrgService(IOrgService orgService) {
		this.orgService = orgService;
	}

	public StringArrayResult getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(StringArrayResult companyInfo) {
		this.companyInfo = companyInfo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public IAllUserService getAllUserService() {
		return allUserService;
	}

	public void setAllUserService(IAllUserService allUserService) {
		this.allUserService = allUserService;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}
