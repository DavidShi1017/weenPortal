package com.jingtong.platform.leveldict.action;

import java.util.ArrayList;
import java.util.List;

import com.crystaldecisions.enterprise.ocaframework.idl.OCA.OCAs.interface_num;
import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.endCustomer.dao.impl.EndCustomerDaoImpl;
import com.jingtong.platform.endCustomer.pojo.EndCustomer;
import com.jingtong.platform.endCustomer.service.impl.EndCustomerServiceImpl;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.leveldict.service.ILevelDictService;
import com.jingtong.platform.leveldict.dao.impl.LevelDictDao;
import com.jingtong.platform.leveldict.pojo.*;

public class LevelDictAction extends BaseAction {

	private static final long serialVersionUID = -26672553859061830L;
	private List<LevelDict> levelDictList;
	private int id;
	private int groupid;
	private int parentid;
	
	
	
	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private ILevelDictService levelDictService;
	
	public ILevelDictService getLevelDictService() {
		return levelDictService;
	}

	public void setLevelDictService(ILevelDictService levelDictService) {
		this.levelDictService = levelDictService;
	}

	private LevelDict levelDict;
	public LevelDict getLevelDict() {
		return levelDict;
	}
	
	public void setLevelDict(LevelDict levelDict) {
		this.levelDict = levelDict;
	}
	
	@PermissionSearch
	@JsonResult(field = "levelDictList")
	public String QueryLevelDict()
	{
		// /levelDictAction!QueryLevelDict.jspa?groupid=1&parentid=0
		LevelDict paraDict = new LevelDict();
		paraDict.setGroup_id(groupid);
		paraDict.setParent_id(parentid);
		this.levelDictList = this.levelDictService.SearchParameters(paraDict);
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "levelDictList")
	public String QueryById()
	{
		// /levelDictAction!QueryById.jspa?id=5
		this.levelDictList = levelDictService.SearchById(id);
		return JSON;
	}
	
	public static void main(String[] args) {
//		LevelDictAction action = new LevelDictAction();
//		action.QueryById(5);
		
		EndCustomer ec = new EndCustomer();
		ec.setId(1099951);
		
		EndCustomerServiceImpl impl = new EndCustomerServiceImpl();
		EndCustomer rr1 =  impl.getEndCustomerById(ec);
		EndCustomerDaoImpl ddCustomerDaoImpl = new EndCustomerDaoImpl();
		
		EndCustomer rr = ddCustomerDaoImpl.getEndCustomerById(ec);
		LevelDictDao dao = new LevelDictDao();
		dao.SearchById(5);
	}
}
