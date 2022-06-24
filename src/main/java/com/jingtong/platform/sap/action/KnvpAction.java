package com.jingtong.platform.sap.action;

import java.util.ArrayList;
import java.util.List;

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.sap.pojo.Knvp;
import com.jingtong.platform.sap.service.KnvpService;

public class KnvpAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1756774206858740457L;

	private KnvpService knvpService;
	
	
	private List<Knvp> knvps = new ArrayList<Knvp>();
    
	private List<Knvp> knvpsh = new ArrayList<Knvp>();
    
	private List<Knvp> knvpRe = new ArrayList<Knvp>();
    
	private String sparvw;
	
	public KnvpService getKnvpService() {
		return knvpService;
	}

	public void setKnvpService(KnvpService knvpService) {
		this.knvpService = knvpService;
	}
	 
 
	public List<Knvp> getKnvps() {
		return knvps;
	}

	public void setKnvps(List<Knvp> knvps) {
		this.knvps = knvps;
	}

	public String getSparvw() {
		return sparvw;
	}

	public void setSparvw(String sparvw) {
		this.sparvw = sparvw;
	}

	public List<Knvp> getKnvpsh() {
		return knvpsh;
	}

	public void setKnvpsh(List<Knvp> knvpsh) {
		this.knvpsh = knvpsh;
	}

 	public List<Knvp> getKnvpRe() {
		return knvpRe;
	}

	public void setKnvpRe(List<Knvp> knvpRe) {
		this.knvpRe = knvpRe;
	}

	/**
	 * 根据经销商和客户类型获取数据
	 * 客户类型（PY付款方；SH送达方；SP售达方）
	 * 
	 * 客户类型（RG/PY付款方；SH/WE送达方；SP/AG售达方；RE/BP开票方）
	 */
	@PermissionSearch
 	@JsonResult(field = "knvps", include = {"kunnr","vkorg","vtweg","spart","parvw","parza","kunn2","lifnr","pernr","parnr","knref","defpa","street1","name1"})
	public String getKnvpFromSAP(){
 		try {
			Knvp knvp = new Knvp();
			knvp.setsParvw("");
			knvp.setSkunnr("");
 			knvps=null;
			this.knvpService.getKnvpFromSAP();
 			
 		} catch (Exception e) {
			 e.printStackTrace();
		}
		return JSON;
	}
	
	/**
	 * 根据经销商和客户类型获取数据
	 * 客户类型（PY付款方；SH送达方；SP售达方）
	 * 
	 * 客户类型（RG/PY付款方；SH/WE送达方；SP/AG售达方；RE/BP开票方）
	 */
	@PermissionSearch
 	@JsonResult(field = "knvpsh", include = {"kunnr","vkorg","vtweg","spart","parvw","parza","kunn2","lifnr","pernr","parnr","knref","defpa","street1","name1"})
	public String getKnvpFromSAPSH(){
 		try {
			Knvp knvp = new Knvp();
			knvp.setsParvw(sparvw);
			knvp.setSkunnr(this.getUser().getLoginId());
			knvpsh=null;
		   // knvpsh = this.knvpService.getKnvpFromSAP();
		 
			
 		} catch (Exception e) {
			 e.printStackTrace();
		}
		return JSON;
	}
	
	/**
	 * 根据经销商和客户类型获取数据
	 * 客户类型（PY付款方；SH送达方；SP售达方）
	 * 
	 * 客户类型（RG/PY付款方；SH/WE送达方；SP/AG售达方；RE/BP开票方）
	 */
	@PermissionSearch
 	@JsonResult(field = "knvpRe", include = {"kunnr","vkorg","vtweg","spart","parvw","parza","kunn2","lifnr","pernr","parnr","knref","defpa","street1","name1"})
	public String getKnvpFromSAPRE(){
 		try {
			Knvp knvp = new Knvp();
			knvp.setsParvw(sparvw);
			knvp.setSkunnr(this.getUser().getLoginId());
			knvpRe=null;
		    //knvpRe = this.knvpService.getKnvpFromSAP();
		 
			
 		} catch (Exception e) {
			 e.printStackTrace();
		}
		return JSON;
	}
}
