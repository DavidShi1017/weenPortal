package com.jingtong.platform.ediDisti.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.country.pojo.Country;
import com.jingtong.platform.cusInventroy.pojo.CusInventroy;
import com.jingtong.platform.ediDisti.pojo.EdiDisti;
import com.jingtong.platform.ediDisti.service.IEdiDistiService;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;

public class EdiDistiAction extends BaseAction {
	
	private IEdiDistiService ediDistiService;
	private EdiDisti ed;
	private List<EdiDisti> edList; 
	private String sold_to;
	private String disti_name;
	private int total;
	
	
	/**
	 * 跳转到查询页面
	 * 
	 * @return
	 */
	public String toSearchEdiDisti(){
		return "toSearchEdiDisti";
	}
	
	/**
	 * 跳转到新增页面
	 * 
	 * @return
	 */
	public String toCreateEdiDisti(){
		ed = new EdiDisti();
		return "toCreateEdiDisti";
	}
	
	/**
	 * 查询列表
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "edList", include = {  "sold_to", "disti_name"}, total = "total")
	public String getEdiDistiList() {
		ed = new EdiDisti();
		ed.setStart(getStart());
		ed.setEnd(getEnd());
//		ed.setSort("file_id");
//		ed.setDir("desc");
		ed.setSold_to(sold_to);	
		if (StringUtils.isNotEmpty(disti_name)){
			ed.setDisti_name(disti_name.toUpperCase());			
		}
		
		total = ediDistiService.getEdiDistiListCount(ed);
		if (total > 0) {
			edList = ediDistiService.getEdiDistiList(ed);
		}
		return JSON;
	}
	
	/**
	 * 添加信息
	 * 
	 * @return
	 */
	public String createEdiDisti() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		EdiDisti e = new EdiDisti();
		e.setSold_to(ed.getSold_to());
		e.setDisti_name(ed.getDisti_name());
		Boolean flag = checkEdiDisti(e);
		try {
		if (flag) {
			long result = ediDistiService.createEdiDisti(e);
				this.setSuccessMessage("Success!");
		} else {
			this.setFailMessage("Code already exist!");
		}
		} catch (Exception e2) {
			this.setFailMessage("Fail!");
		}
		return RESULT_MESSAGE;
	}
	
	/**
	 * 检查是否重复
	 * 
	 * @param g
	 * @return
	 */
	private Boolean checkEdiDisti(EdiDisti ed) {
		Boolean b = true;
		int n = ediDistiService.getEdiDistiListCount(ed);
		if (n >= 1) {
			b = false;
		}
		return b;

	}
	

	/**
	 * 删除信息
	 * 
	 * @return
	 */
	public String deleteEdiDisti() {

		this.setFailMessage("");
		this.setSuccessMessage("");
		ed = new EdiDisti();
		ed.setSold_to(sold_to);
		ed.setDisti_name(disti_name);
		long i = ediDistiService.deleteEdiDisti(ed);
		if (i > 0) {
			this.setSuccessMessage("Success!");
		} else {
			this.setFailMessage("Failed!");
		}
		return RESULT_MESSAGE;
	}

	public IEdiDistiService getEdiDistiService() {
		return ediDistiService;
	}

	public void setEdiDistiService(IEdiDistiService ediDistiService) {
		this.ediDistiService = ediDistiService;
	}

	public EdiDisti getEd() {
		return ed;
	}

	public void setEd(EdiDisti ed) {
		this.ed = ed;
	}

	public List<EdiDisti> getEdList() {
		return edList;
	}

	public void setEdList(List<EdiDisti> edList) {
		this.edList = edList;
	}

	public String getSold_to() {
		return sold_to;
	}

	public void setSold_to(String sold_to) {
		this.sold_to = sold_to;
	}

	public String getDisti_name() {
		return disti_name;
	}

	public void setDisti_name(String disti_name) {
		this.disti_name = disti_name;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
	
}
