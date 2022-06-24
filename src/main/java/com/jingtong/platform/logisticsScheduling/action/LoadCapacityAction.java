package com.jingtong.platform.logisticsScheduling.action;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.logisticsScheduling.pojo.LoadCapacity;
import com.jingtong.platform.logisticsScheduling.service.LoadCapacityService;

public class LoadCapacityAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1367518859009395228L;

	private LoadCapacityService loadCapacityService;
	private int total;
	private Long ids;//装运能力ID
	private String strids;
	private LoadCapacity loadCapacity;
	private List<LoadCapacity> loadCapacityList = new ArrayList<LoadCapacity>();
	/**查询条件 ***/
	private String vstel;
	private String hand_team;
	
	public LoadCapacityService getLoadCapacityService() {
		return loadCapacityService;
	}
	public void setLoadCapacityService(LoadCapacityService loadCapacityService) {
		this.loadCapacityService = loadCapacityService;
	}
	
	public LoadCapacity getLoadCapacity() {
		return loadCapacity;
	}
	public void setLoadCapacity(LoadCapacity loadCapacity) {
		this.loadCapacity = loadCapacity;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	public String getStrids() {
		return strids;
	}
	public void setStrids(String strids) {
		this.strids = strids;
	}
	public Long getIds() {
		return ids;
	}
	public void setIds(Long ids) {
		this.ids = ids;
	}
	public String toLoadCapacity(){
		return "toLoadCapacity";
	}
	
	public String getVstel() {
		return vstel;
	}
	public void setVstel(String vstel) {
		this.vstel = vstel;
	}
	public String getHand_team() {
		return hand_team;
	}
	public void setHand_team(String hand_team) {
		this.hand_team = hand_team;
	}
	@PermissionSearch
	@JsonResult(field = "loadCapacityList", include = { "id", "vstel","hand_team","start_date", "end_date","scheduling"}, total = "total")
	public String getLoadCapacityList() throws UnsupportedEncodingException{
		LoadCapacity lc = new LoadCapacity();
		lc.setStart(getStart());
		lc.setEnd(getEnd()); 
		lc.setVstel(java.net.URLDecoder.decode(this.vstel, "UTF-8"));
		lc.setHand_team(java.net.URLDecoder.decode(this.hand_team, "UTF-8"));
		total = this.loadCapacityService.getLoadCapacityListTotal(lc);
		if(total>0){
			loadCapacityList = this.loadCapacityService.getLoadCapacityList(lc);
		}
		return JSON;
	}
	
	public String eidtLoadCapacity(){
		if(this.ids>0){
			loadCapacity = this.loadCapacityService.getLoadCapacity(ids);
		}
		return "editLoadCapacity";
	}
	
	public String saveLoadCapacity(){
			this.setFailMessage("");
		this.setSuccessMessage("");
		if(this.loadCapacity!=null){
			if(loadCapacity.getVstel().trim().length()>0&&loadCapacity.getHand_team().trim().length()>0){
				if(loadCapacity.getId() !=null && loadCapacity.getId()>0){
					this.loadCapacityService.updateLoadCapacity(loadCapacity);
					this.setSuccessMessage("操作成功");
					}else{
					this.loadCapacityService.saveLoadCapacity(loadCapacity);
					this.setSuccessMessage("操作成功");
					}
			}else{
				this.setFailMessage("数据不能为空!");
				}
		}
 
	return RESULT_MESSAGE;
}
	public void deleteLoadCapacity(){
		int id =0;
		try {
			if(this.strids.length()>0&&this.strids!=null){
				String[] lcIds = strids.split(",");
				for(int i=0;i<lcIds.length;i++){
					this.loadCapacityService.deleteLoadCapacity(Long.valueOf(lcIds[i]));
				}
				
			}
			if(id>=0){
				this.responseToAjax("1");
			}
		} catch (Exception e) {
			this.responseToAjax("0");
		}
	}
	public void responseToAjax(String jsonStr){
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 编制响应的格式
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
