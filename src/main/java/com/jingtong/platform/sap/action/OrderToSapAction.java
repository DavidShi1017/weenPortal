package com.jingtong.platform.sap.action;

import java.util.List;

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.sampleOrder.pojo.SampleOrder;
import com.jingtong.platform.sap.pojo.OrderDetail;
import com.jingtong.platform.sap.pojo.OrderToSap;
import com.jingtong.platform.sap.service.OrderToSapService;
import com.jingtong.platform.sap.service.SAPService;

public class OrderToSapAction extends BaseAction {

    private OrderToSapService orderToSapService;

    private String salesId;
    private long id;
    private SAPService sapService;
    private String material_id;
    private StringResult stringResult;

    public OrderToSapService getOrderToSapService() {
        return orderToSapService;
    }

    public void setOrderToSapService(OrderToSapService orderToSapService) {
        this.orderToSapService = orderToSapService;
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

    public String getOrderToSAP() {
        this.setSuccessMessage("");
        String message = "";
        OrderToSap order = new OrderToSap();
        order.setId(id);
        List<OrderToSap> orderList = orderToSapService.getOrderTotal(order);
        order = orderList.get(0);
        List<OrderDetail> orderDetail = orderToSapService.getOrderDetail(order);
        System.out.println(order.getId());
        SampleOrder sorder = new SampleOrder();
        sorder.setId(order.getId());
        message = message + orderToSapService.orderToSap(order, orderDetail, sorder);
        this.setSuccessMessage(message);
        return RESULT_MESSAGE;
    }

    @PermissionSearch
    @JsonResult(field = "stringResult", include = { "result", "code" })
    public String getSampleStockFromSAP() {
        stringResult = new StringResult();
        String stock = orderToSapService.getSampleStockFromSAP(material_id);
        stringResult.setCode("success");
        stringResult.setResult(stock);
        return JSON;
    }

    public String getOrderToSapTest() {
        String message = "";
        return message;

    }

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }

    public StringResult getStringResult() {
        return stringResult;
    }

    public void setStringResult(StringResult stringResult) {
        this.stringResult = stringResult;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
