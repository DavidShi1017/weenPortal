package com.jingtong.platform.logisticsScheduling.action;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.logisticsScheduling.pojo.LoadCapacity;
import com.jingtong.platform.logisticsScheduling.pojo.ScheduleList;
import com.jingtong.platform.logisticsScheduling.pojo.ScheduleSmtor;
import com.jingtong.platform.logisticsScheduling.service.ScheduleListService;

public class ScheduleListAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1367518859009395228L;

	private ScheduleListService scheduleListService;
	private int total;
	private Long ids;//装运能力ID
	private String strids;
	private List<ScheduleSmtor> ssList = new ArrayList<ScheduleSmtor>();
	private List<ScheduleList> slList = new ArrayList<ScheduleList>();
	private ScheduleList  scheduleList;
	/**查询条件 ***/
	private String vstel;
	private String bolnr;
	
	
	
	
	public ScheduleList getScheduleList() {
		return scheduleList;
	}
	public void setScheduleList(ScheduleList scheduleList) {
		this.scheduleList = scheduleList;
	}
	public ScheduleListService getScheduleListService() {
		return scheduleListService;
	}
	public void setScheduleListService(ScheduleListService scheduleListService) {
		this.scheduleListService = scheduleListService;
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
	public String getVstel() {
		return vstel;
	}
	public void setVstel(String vstel) {
		this.vstel = vstel;
	}
	
	
	public String getBolnr() {
		return bolnr;
	}
	public void setBolnr(String bolnr) {
		this.bolnr = bolnr;
	}
	 /****************************装运单页面***************/
	public String toScheduleSmtor(){
		return "toScheduleSmtor";
	}
	@PermissionSearch
	@JsonResult(field = "ssList", include = { "id", "bolnr","vstel","brgew", "bolnr_level","location_level","states","is_lock"}, total = "total")
	public String getScheduleSmtor() throws UnsupportedEncodingException{
		ScheduleSmtor ss = new ScheduleSmtor();
		ss.setStart(getStart());
		ss.setEnd(getEnd()); 
		ss.setVstel(java.net.URLDecoder.decode(this.vstel, "UTF-8"));
		ss.setBolnr(java.net.URLDecoder.decode(this.bolnr, "UTF-8"));
		total = this.scheduleListService.getScheduleSmtorTotal(ss);
		if(total>0){
			ssList = this.scheduleListService.getScheduleSmtor(ss);
		}
		System.out.println(JSON.toString());
		return JSON;
	}
	
	//同步装运单
	public void synchroSmtor(){
			int id =0;
			try {
				
				if(id>=0){
					this.responseToAjax("1");
				}
			} catch (Exception e) {
				this.responseToAjax("0");
			}
	}	
   //排程
	public void shcedulelist(){
		int id =0;
		Map map=new HashMap();
        map.put("userid", this.getUser().getUserId());
		try {
			//查询是否有锁定的单据
			ScheduleList shecduleLocok=new ScheduleList();
			int lockcount = this.scheduleListService.viewshcedulelistLock(shecduleLocok);
			if(lockcount>0){
				this.responseToAjax("运单存在锁定不能排程");
			}else{
				this.scheduleListService.querySchedule(map);
					this.responseToAjax("1");
			}
		
		} catch (Exception e) {
			this.responseToAjax("0");
		}
	}
	
	//运单优先级向上
	public void uplevel(){
		int id =0;
		try {
			if(this.strids.length()>0&&this.strids!=null){
				String[] lcIds = strids.split(",");
				for(int i=0;i<lcIds.length;i++){
					this.scheduleListService.uplevel(Long.valueOf(lcIds[i]));
				}
				
			}
			if(id>=0){
				this.responseToAjax("1");
			}
		} catch (Exception e) {
			this.responseToAjax("0");
		}
	}
	
	//运单优先级向上
		public void downlevel(){
			int id =0;
			try {
				if(this.strids.length()>0&&this.strids!=null){
					String[] lcIds = strids.split(",");
					for(int i=0;i<lcIds.length;i++){
						this.scheduleListService.downlevel(Long.valueOf(lcIds[i]));
					}
					
				}
				if(id>=0){
					this.responseToAjax("1");
				}
			} catch (Exception e) {
				this.responseToAjax("0");
			}
		}
	
		//锁定
    public void islock(){
    	int id =0;
		try {
			if(this.strids.length()>0&&this.strids!=null){
					ScheduleSmtor ss=new ScheduleSmtor();
					ss.setSmtorIds(strids);
					this.scheduleListService.islock(ss);
			}
			if(id>=0){
				this.responseToAjax("1");
			}
		} catch (Exception e) {
			this.responseToAjax("0");
		}
    }
    
	//解锁
    public void unlock(){
    	int id =0;
		try {
			if(this.strids.length()>0&&this.strids!=null){
					ScheduleSmtor ss=new ScheduleSmtor();
					ss.setSmtorIds(strids);
					this.scheduleListService.unlock(ss);
				
			}
			if(id>=0){
				this.responseToAjax("1");
			}
		} catch (Exception e) {
			this.responseToAjax("0");
		}
    }
    
    
    /****************************装卸清单计算结果页面***************/
	public String toviewshcedulelist(){
		return "toviewshcedulelist";
	}
	
	@PermissionSearch
	@JsonResult(field = "slList", include = { "id", "bolnr","vstel","brgew", "bolnr_level","workstart_date","workend_date","team"}, total = "total")
	public String viewshcedulelist() throws UnsupportedEncodingException{
		ScheduleList sl = new ScheduleList();
		sl.setStart(getStart());
		sl.setEnd(getEnd()); 
		sl.setVstel(java.net.URLDecoder.decode(this.vstel, "UTF-8"));
		sl.setBolnr(java.net.URLDecoder.decode(this.bolnr, "UTF-8"));
		total = this.scheduleListService.viewshcedulelistTotal(sl);
		if(total>0){
			slList = this.scheduleListService.viewshcedulelist(sl);
		}
		return JSON;
	}
	
	public String toeditShceduleList(){
		scheduleList = this.scheduleListService.getShceduleListByid(ids);
		return "toeditShceduleList";
	}
	public String editShceduleList(){
		this.setSuccessMessage("");
		if(this.scheduleList!=null){
			if(scheduleList.getWorkend_date() !=null && scheduleList.getWorkstart_date()!=null){
			ScheduleList	newscheduleList = this.scheduleListService.getShceduleListByid(scheduleList.getId());
			newscheduleList.setWorkstart_date(scheduleList.getWorkstart_date());
			newscheduleList.setWorkend_date(scheduleList.getWorkend_date());
					this.scheduleListService.updateShceduleList(newscheduleList);
					this.setSuccessMessage("操作成功");
					
			}else{
				this.setFailMessage("数据不能为空!");
				}
		}
 
	return RESULT_MESSAGE;
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
