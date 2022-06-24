package com.jingtong.platform.account.pojo;

import java.io.Serializable;
public class CostType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long COST_TYPE_ID;
	private String COST_TYPE_NAME;
	private Long COST_LEVEL;
	private String STATUS;
	private Long PARENT_ID;
	private Long TEMPLATE_ID;
	private String TEMPLATE_NAME;
	private String COST_SORT;
	private Long CREATED_ID;
	private Long OPERATOR_ID;
	private String type;
	
	private String id;
	private String text;
	private String state;
	
	private String parent_cost_name;
	private String parent_cost_id;
	
	
	
	public String getTEMPLATE_NAME() {
		return TEMPLATE_NAME;
	}
	public void setTEMPLATE_NAME(String tEMPLATE_NAME) {
		TEMPLATE_NAME = tEMPLATE_NAME;
	}
	public String getParent_cost_name() {
		return parent_cost_name;
	}
	public void setParent_cost_name(String parent_cost_name) {
		this.parent_cost_name = parent_cost_name;
	}
	public String getParent_cost_id() {
		return parent_cost_id;
	}
	public void setParent_cost_id(String parent_cost_id) {
		this.parent_cost_id = parent_cost_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getCOST_TYPE_ID() {
		return COST_TYPE_ID;
	}
	public void setCOST_TYPE_ID(Long cOST_TYPE_ID) {
		COST_TYPE_ID = cOST_TYPE_ID;
	}
	public String getCOST_TYPE_NAME() {
		return COST_TYPE_NAME;
	}
	public void setCOST_TYPE_NAME(String cOST_TYPE_NAME) {
		COST_TYPE_NAME = cOST_TYPE_NAME;
	}
	public Long getCOST_LEVEL() {
		return COST_LEVEL;
	}
	public void setCOST_LEVEL(Long cOST_LEVEL) {
		COST_LEVEL = cOST_LEVEL;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public Long getPARENT_ID() {
		return PARENT_ID;
	}
	public void setPARENT_ID(Long pARENT_ID) {
		PARENT_ID = pARENT_ID;
	}
	public Long getTEMPLATE_ID() {
		return TEMPLATE_ID;
	}
	public void setTEMPLATE_ID(Long tEMPLATE_ID) {
		TEMPLATE_ID = tEMPLATE_ID;
	}
	
	
	public String getCOST_SORT() {
		return COST_SORT;
	}
	public void setCOST_SORT(String cOST_SORT) {
		COST_SORT = cOST_SORT;
	}
	public Long getCREATED_ID() {
		return CREATED_ID;
	}
	public void setCREATED_ID(Long cREATED_ID) {
		CREATED_ID = cREATED_ID;
	}
	public Long getOPERATOR_ID() {
		return OPERATOR_ID;
	}
	public void setOPERATOR_ID(Long oPERATOR_ID) {
		OPERATOR_ID = oPERATOR_ID;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
