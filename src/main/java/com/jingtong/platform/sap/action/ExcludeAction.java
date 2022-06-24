package com.jingtong.platform.sap.action;

 

import java.util.ArrayList;
import java.util.List;

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.sap.pojo.Exclude;
import com.jingtong.platform.sap.service.ExcludeService;

public class ExcludeAction extends BaseAction {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -1895718145904691474L;
	private ExcludeService excludeService;
	private List<Exclude> excludes = new ArrayList<Exclude>();
	private int total;
	private String matnr;
	private String kunnr;
	 
	public String getMatnr() {
		return matnr;
	}

	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}

	public String getKunnr() {
		return kunnr;
	}

	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}

	public List<Exclude> getExcludes() {
		return excludes;
	}

	public void setExcludes(List<Exclude> excludes) {
		this.excludes = excludes;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public ExcludeService getExcludeService() {
		return excludeService;
	}

	public void setExcludeService(ExcludeService excludeService) {
		this.excludeService = excludeService;
	}
	public String getExcludeFromSap(){
//		Exclude exc  =new Exclude();
//		exc.setCreateUser(this.getUser().getLoginId());
//		exc.setModifyUser(this.getUser().getLoginId());
		this.excludeService.getExcludeFromSap();
		return null;
	}
	@PermissionSearch 
	@JsonResult(field = "excludes", include = {"id","kappl","kschl","kunnr","matnr","datab1","datbi1","dtype"}, total = "total")
	public String getExcludeList(){
		Exclude exc  =new Exclude();
		exc.setStart(this.getStart());
		exc.setEnd(this.getEnd());
		if(this.matnr!=null){
			exc.setMatnr(matnr);
		}
		if(this.kunnr!=null){
			exc.setKunnr(kunnr);
		}
		total = this.excludeService.getExcludeTotal(exc);
		if(total>0){
			excludes = this.excludeService.getExcludeList(exc);
		}
		return JSON;
	}
	
	public String toExclude(){
		return "toExclude";
	}
}
