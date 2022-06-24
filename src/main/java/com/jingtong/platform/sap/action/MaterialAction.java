package com.jingtong.platform.sap.action;

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.sap.service.MaterialService;

public class MaterialAction  extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3599908969412005618L;

	private MaterialService materialsSerivce;
	private String material_id;
	
	
	public String getMaterial_id() {
		return material_id;
	}

	public void setMaterial_id(String material_id) {
		this.material_id = material_id;
	}

	public MaterialService getMaterialsSerivce() {
		return materialsSerivce;
	}

	public void setMaterialsSerivce(MaterialService materialsSerivce) {
		this.materialsSerivce = materialsSerivce;
	}

	public String getMaterialFromSAP(){
		System.out.println("---------");
		
	    this.setSuccessMessage("");
		this.materialsSerivce.getMaterialListFromSAP(material_id);
	 	this.setSuccessMessage("SUCCESS");
		return RESULT_MESSAGE;
	}
	
	
}
