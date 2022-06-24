package com.jingtong.platform.sap.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.sap.pojo.Warehouses;
import com.jingtong.platform.sap.service.WarehouseService;

public class WarehousesAction extends BaseAction {
	private Warehouses warehouse;
	private WarehouseService warehouseService;
	
	
	public Warehouses getWarehouse() {
		return warehouse;
	}


	public void setWarehouse(Warehouses warehouse) {
		this.warehouse = warehouse;
	}

	public void setWarehouseService(WarehouseService warehouseService) {
		this.warehouseService = warehouseService;
	}
    
	//同步数据
	public void sysnchWarehouseLocation(){
		HttpServletResponse response = ServletActionContext.getResponse();
		// 编制响应的格式
		response.setContentType("text/html;charset=UTF-8");
		
		try {
			this.warehouseService.getSapWarehouseLocation();
			response.getWriter().write("1");
		} catch (Exception e) {
			try {
				e.printStackTrace();
				response.getWriter().write("0");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
