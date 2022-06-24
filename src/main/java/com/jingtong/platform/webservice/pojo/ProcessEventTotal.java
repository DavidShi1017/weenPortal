package com.jingtong.platform.webservice.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;

public class ProcessEventTotal extends SearchInfo {
	private static final long serialVersionUID = -1L;
	 
	private Long eventId;
	 
	private String initator;
	
	private String modelId;
	
	private String modelName;
	
	private String creatdate;
	
	private String currentDetailid;
	
	private String status;
	
	private String eventTitle;
	
	private String empName;
	
	private String userId;
	
	private String keys;
	
	private String curUserName;
	
	private String curStaId;
	
	private String backStatus;
	
	private String event_id;
	
	private String subFolders;

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getInitator() {
		return initator;
	}

	public void setInitator(String initator) {
		this.initator = initator;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getCreatdate() {
		return creatdate;
	}

	public void setCreatdate(String creatdate) {
		this.creatdate = creatdate;
	}

	public String getCurrentDetailid() {
		return currentDetailid;
	}

	public void setCurrentDetailid(String currentDetailid) {
		this.currentDetailid = currentDetailid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}

	public String getCurUserName() {
		return curUserName;
	}

	public void setCurUserName(String curUserName) {
		this.curUserName = curUserName;
	}

	public String getCurStaId() {
		return curStaId;
	}

	public void setCurStaId(String curStaId) {
		this.curStaId = curStaId;
	}

	public String getBackStatus() {
		return backStatus;
	}

	public void setBackStatus(String backStatus) {
		this.backStatus = backStatus;
	}

	public String getEvent_id() {
		return event_id;
	}

	public void setEvent_id(String eventId) {
		event_id = eventId;
	}

	public String getSubFolders() {
		return subFolders;
	}

	public void setSubFolders(String subFolders) {
		this.subFolders = subFolders;
	}
}
