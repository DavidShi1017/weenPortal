package com.jingtong.platform.sap.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.velocity.runtime.directive.Foreach;

import com.jingtong.platform.base.action.BaseAction;

import com.jingtong.platform.order.pojo.StarderOrder;
import com.jingtong.platform.sampleOrder.pojo.SampleOrder;
import com.jingtong.platform.sap.pojo.SampleOrderDetail;
import com.jingtong.platform.sap.pojo.SampleOrderToSap;
import com.jingtong.platform.sap.service.SAPService;
import com.jingtong.platform.sap.service.SampleOrderToSapService;
import com.jingtong.platform.sap.service.impl.SampleOrderToSapServiceImpl;

public class SampleOrderToSapAction extends BaseAction {

	private SampleOrderToSapService sampleOrderToSapService;

	private String salesId;// ¶©µ¥ºÅ
	private long id;
	private SAPService sapService;

	public SampleOrderToSapService getSampleOrderToSapService() {
		return sampleOrderToSapService;
	}

	public void setSampleOrderToSapService(
			SampleOrderToSapService sampleOrderToSapService) {
		this.sampleOrderToSapService = sampleOrderToSapService;
	}

	public String getSalesId() {
		return salesId;
	}

	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}

	public SAPService getSapService() {
		return sapService;
	}

	public void setSapService(SAPService sapService) {
		this.sapService = sapService;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private static final long serialVersionUID = -3599908969412005618L;

	public String getSampleOrderToSAP() {

		this.setSuccessMessage("");
		String message = "";
		SampleOrderToSap sampleOrder = new SampleOrderToSap();
		sampleOrder.setId(id);
		List<SampleOrderToSap> sampleOrderList = sampleOrderToSapService
				.getSampleOrderTotal(sampleOrder);
		sampleOrder = sampleOrderList.get(0);
		List<SampleOrderDetail> sampleOrderDetail = sampleOrderToSapService
				.getSampleOrderDetail(sampleOrder);
		System.out.println(sampleOrder.getId());
		StarderOrder sorder = new StarderOrder();
		sorder.setId(sampleOrder.getId());
		message = message
				+ sampleOrderToSapService.sampleOrderToSap(sampleOrder,
						sampleOrderDetail, sorder);
		this.setSuccessMessage(message);
		return RESULT_MESSAGE;
	}
//
//	 public String getSampleOrderToSapTest() {
//	 String message="";
//	
//	 SampleOrderToSap sampleOrder = new SampleOrderToSap();
//	
//	
//	 List<SampleOrderToSap> soList =
//	 sampleOrderToSapService.getSampleOrderTotal(sampleOrder);
//	
//	 for (SampleOrderToSap sampleOrderToSap : soList) {
//	  sampleOrder.setSalesId("001");
//	  sampleOrder.setSalesOrg(so.getSalesOrg());
//	  sampleOrder.setDistChannel(so.getDistChannel());
//	  sampleOrder.setProductGroup(so.getProductGroup());
//	  sampleOrder.setSalesDocumentType(so.getSalesDocumentType());
//	  sampleOrder.setShipTo(so.getShipTo());
//	  sampleOrder.setBillTo(so.getBillTo());
//	  sampleOrder.setCollectTo(so.getCollectTo());
//	  sampleOrder.setSalesTo(so.getSalesTo());
//	  sampleOrder.setCurrency(so.getCurrency());
//	  sampleOrder.setOrderId(so.getOrderId());
//	  sampleOrder.setHeaderTxt(so.getHeaderTxt());
//	
//	 SampleOrderDetail sampleOrderDetail = new SampleOrderDetail();
//	
//	 List<SampleOrderDetail>
//	 sodList=sampleOrderToSapService.getSampleOrderDetail(sampleOrder);
//	
//	  sampleOrderDetail.setSalesId("001");
//	  sampleOrderDetail.setSalesDocumentItem(sod.getSalesDocumentItem());
//	  sampleOrderDetail.setMaterialNumber(sod.getMaterialNumber());
//	  sampleOrderDetail.setTotalQty(sod.getTotalQty());
//	  sampleOrderDetail.setSalesUnit(sod.getSalesUnit());
//	  sampleOrderDetail.setTxt(sod.getTxt());
//	 List<SampleOrderDetail> items = new ArrayList<SampleOrderDetail>();
//	 for (SampleOrderDetail sod : sodList) {
//	 items.add(sod);
//	
//	 }
//	
//	 SampleOrderToSapServiceImpl sap = new SampleOrderToSapServiceImpl();
//	
//	 message=sap.sampleOrderToSap(sampleOrder, items);
//	
//	 }
//	 //
//	
//	
//	
//	
//	
//	 return message;
	
//	 }

}
