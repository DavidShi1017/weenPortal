package com.jingtong.platform.wfe.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

public class BusinessTripApply extends SearchInfo {
	private static final long serialVersionUID = 3690598034302973646L;
	
	private String eventId;
	
	private String orgName;
	
	private String orgId;
	
	private String costCenter;
	
	private String costCenterName;
	
	private String place; //目的地
	
	private String tripWay;
	
	private String tripWayName;
	
	private String peopleNames;
	
	private String distance;
	
	private Date beginDate;
	
	private Date endDate;
	
	private String reason;

	private String status;
	
	private String eventTitle;
	
	private String userName;
	
	private String userId;
	
	private String tripDays;
	
	private String stayDays;
	
	private String departure; //出发地
	
	private String stop; //经停地
	
	private String amount; //申请金额 
	
	private String createDate; //创建日期
	
	private Date modifyDate; //修改日期
	
	private String modifyer; //修改人
	
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getCostCenterName() {
		return costCenterName;
	}

	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTripWay() {
		return tripWay;
	}

	public void setTripWay(String tripWay) {
		this.tripWay = tripWay;
	}

	public String getTripWayName() {
		return tripWayName;
	}

	public void setTripWayName(String tripWayName) {
		this.tripWayName = tripWayName;
	}

	public String getPeopleNames() {
		return peopleNames;
	}

	public void setPeopleNames(String peopleNames) {
		this.peopleNames = peopleNames;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
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

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTripDays() {
		return tripDays;
	}

	public void setTripDays(String tripDays) {
		this.tripDays = tripDays;
	}

	public String getStayDays() {
		return stayDays;
	}

	public void setStayDays(String stayDays) {
		this.stayDays = stayDays;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getStop() {
		return stop;
	}

	public void setStop(String stop) {
		this.stop = stop;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModifyer() {
		return modifyer;
	}

	public void setModifyer(String modifyer) {
		this.modifyer = modifyer;
	}
}
