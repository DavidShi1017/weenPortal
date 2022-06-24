package com.jingtong.platform.order.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import jxl.CellType;
import jxl.DateCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.customer.pojo.CustomerUser;
import com.jingtong.platform.customer.service.ICustomerService;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.framework.util.ExcelUtil;
import com.jingtong.platform.framework.util.JsonUtil;
import com.jingtong.platform.framework.util.StockUtil;
import com.jingtong.platform.order.pojo.OrderDetail;
import com.jingtong.platform.order.pojo.StarderOrder;
import com.jingtong.platform.order.service.IOrderService;
import com.jingtong.platform.product.pojo.Product;
import com.jingtong.platform.product.service.IProductService;
import com.jingtong.platform.role.pojo.Role;
import com.jingtong.platform.role.service.IRoleService;
import com.jingtong.platform.sampleOrder.pojo.SampleOrderDetail;
import com.jingtong.platform.sap.pojo.SampleOrderToSap;
import com.jingtong.platform.sap.service.SampleOrderToSapService;


public class OrderAction extends BaseAction{
	private IOrderService orderService;
	private StarderOrder o;
	private List<StarderOrder> oList;
	private OrderDetail od;
	private List<OrderDetail> odList;
	private String orderDetailJson;
	private String id;
	private String delOrderDetail;
	private String sap_order_id;
	private String order_id;
	private String branch_id;
	private String main_id;
	private int total;
	private String ids;	
	private CustomerUser cusUser;
	private List<CustomerUser> cusUserList;
	private ICustomerService customerService;
	private String uploadFile;
	private Product p;
	private List<Product> pList;
	private IProductService productService;
	private String path;
	private String start_dateStr;
	private String end_dateStr;
	private List<Role> roleList;//登陆人角色列表
	private IRoleService roleService;
	private String loginRole;//标记登陆人的角色
	private String currency_code;
	private String office_id;
	private String customer_id;
	private SampleOrderToSapService sampleOrderToSapService;
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	
	public String toSearchOrder(){
		String userId  = this.getUser().getUserId();
		o = new StarderOrder();
/*		Role r = new Role();
		r.setStart(0);
		r.setEnd(100);
		r.setEmp_code(userId);
		roleList = roleService.getSelectedRole4StationList(r);
		for (Role rl : roleList) {
			loginRole = rl.getRoleId();
			if("JXS".equals(loginRole)){			
				String loginId =  this.getUser().getLoginId();
				o.setLoginId(loginId);//创建时将登陆账号带到页面，使得该账号只能选择绑定到其自身的Disti
			}
		}*/
		String orgString = this.getUser().getOrgId();
		if(orgString==null || "".equals(orgString)){
			String loginId =  this.getUser().getLoginId();
			o.setLoginId(loginId);//创建时将登陆账号带到页面，使得该账号只能选择绑定到其自身的Disti
		}
		return "toSearchOrder";
	}
	
	public String toViewOrder(){
		o=new StarderOrder();
		o.setId(Long.valueOf(id));
		o = orderService.getOrderById(o);
		return "toViewOrder";
	}
	
	public String toCreateOrder(){
		o=new StarderOrder();
		o.setType_id("ZOR1");
		String loginId =  this.getUser().getLoginId();
		if(!"admin".equals(loginId)){	
			String userId  = this.getUser().getUserId();
/*			Role r = new Role();
			r.setStart(0);
			r.setEnd(100);
			r.setEmp_code(userId);
			roleList = roleService.getSelectedRole4StationList(r);
			for (Role rl : roleList) {
				loginRole = rl.getRoleId();
				if("JXS".equals(loginRole)){			
					o.setLoginId(loginId);//创建时将登陆账号带到页面，使得该账号只能选择绑定到其自身的Disti
				}
			}*/
			String orgString = this.getUser().getOrgId();
			if(orgString==null || "".equals(orgString)){
				o.setLoginId(loginId);
			}
			o.setBranch_id(branch_id);
/*			cusUser=new CustomerUser();
			cusUser.setLoginId(loginId);
			cusUserList = customerService.getLoginCusUser(cusUser);	
			if(cusUserList!=null){
				o.setCustomer_name(cusUserList.get(0).getCustomer_name());
				o.setBranch_id(cusUserList.get(0).getCustomer_code());
				o.setCurrency_code(cusUserList.get(0).getCurrency_code());
				o.setSaler(cusUserList.get(0).getSales());				
			}*/
		}
		return "toCreateOrder";
	}
	
	public String toUpdateOrder(){
		o=new StarderOrder();
		o.setId(Long.valueOf(id));
		o = orderService.getOrderById(o);
		return "toCreateOrder";
	}
	/**
	 * 跳转到excel导入页面
	 * @return
	 */
	public String importExcel(){
		office_id=office_id;
		customer_id=customer_id;
		currency_code = currency_code;
		return "importExcel";
	}
	

	/**
	 * 查询订单信息
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "oList", include = {"id","order_id","type_id","branch_id","currency_code",
			  "ship_to","payer_to","billing_to","saler","sale_company","end_customer",
			  "project_name","contact_info","state","remark","sap_order_id",
			  "create_time","create_userId","sync_state","shipToAddress",
			  "latest_time","latest_userId","org_code"},
			    total = "total")
	public String getOrderList(){		
 		o = new StarderOrder();
		o.setStart(getStart());
		o.setEnd(getEnd());
		o.setSort("aa.create_time");
		o.setDir("desc");
		o.setOrder_id(order_id);	
		o.setSap_order_id(sap_order_id);
		o.setStart_dateStr(start_dateStr);
		o.setEnd_dateStr(end_dateStr);
		String loginId =  this.getUser().getLoginId();
		String userId  = this.getUser().getUserId();
/*			Role r = new Role();
			r.setStart(0);
			r.setEnd(100);
			r.setEmp_code(userId);
			roleList = roleService.getSelectedRole4StationList(r);
			for (Role rl : roleList) {
				loginRole = rl.getRoleId();
				if("JXS".equals(loginRole)){
					o.setLoginId(loginId);//JXS角色可查看其对应Disti的数据				
				}
			}*/
		String orgString = this.getUser().getOrgId();
		if(orgString==null || "".equals(orgString)){
			o.setLoginId(loginId);
		}
			o.setBranch_id(branch_id);
		total = orderService.getOrderListCount(o);
		if(total > 0){
			oList = orderService.getOrderList(o);
		}
		return JSON;
	}

	
	/**
	 * 查询订单明细信息
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "odList", include = { "id","order_id","po_item","row_no","material_id","material_name","material_typeId",
			  "material_groupId","sale_unit","moq","order_QTY","delivery_dateStr","confirm_dateStr","price",
			  "lead_time","delivery_date","confirm_date","price","remark","pq","pbMpp" }
			   )
	public String getOrderDetailList(){
		od = new OrderDetail();
		od.setOrder_id(order_id);
		od.setMain_id(Long.valueOf(main_id));
		odList = orderService.getOrderDetailList(od);
		return JSON;
	}
	
	
	/**
	 * 创建订单信息
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	public String createOrder() throws UnsupportedEncodingException{
		this.setSuccessMessage("");
		this.setFailMessage("");
		
		orderDetailJson = java.net.URLDecoder.decode(this.orderDetailJson,"utf-8");	
		odList = JsonUtil.getDTOList(orderDetailJson, OrderDetail.class);
		o.setCreate_userId(this.getUser().getUserId());
		
		for (OrderDetail od : odList) {
			od.setOrder_id(o.getOrder_id());
		}
		BooleanResult bool= orderService.createOrder(o, odList);
		if(bool.getResult()){
			this.setSuccessMessage("Order Save Success!");
			//同步
			SampleOrderToSap sampleOrder = new SampleOrderToSap();
			sampleOrder.setId(Long.valueOf(bool.getCode()));
			List<SampleOrderToSap> sampleOrderList = sampleOrderToSapService.getSampleOrderTotal(sampleOrder);
			sampleOrder = sampleOrderList.get(0);
			List<com.jingtong.platform.sap.pojo.SampleOrderDetail> sampleOrderDetail = sampleOrderToSapService.getSampleOrderDetail(sampleOrder);
			System.out.println(sampleOrder.getId());
			StarderOrder sorder = new StarderOrder();
			sorder.setId(sampleOrder.getId());
			String message = sampleOrderToSapService.sampleOrderToSap(sampleOrder,
							sampleOrderDetail, sorder);
			
			
			
		}else{
			this.setFailMessage(bool.getCode());
		}		
 		return RESULT_MESSAGE;
	}

	
	
	/**
	 * 修改订单信息
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	public String updateOrder() throws UnsupportedEncodingException{
		this.setSuccessMessage("");
		this.setFailMessage("");
		
		orderDetailJson = java.net.URLDecoder.decode(this.orderDetailJson,"utf-8");	
		odList = JsonUtil.getDTOList(orderDetailJson, OrderDetail.class);	
		od = new OrderDetail();
		od.setIds(delOrderDetail);
		o.setLatest_userId(this.getUser().getUserId());
		for (OrderDetail od : odList) {
			od.setOrder_id(o.getOrder_id());
			od.setMain_id(o.getId());
		}
		BooleanResult bool= orderService.updateOrder(o, odList, od);
		if(bool.getResult()){
			this.setSuccessMessage("Order Save Success! Sure to Confirm?");
		}else{
			this.setFailMessage("Failed !");
		}		
 		return RESULT_MESSAGE;
	}

	public String deleteOrder(){
		this.setFailMessage("");
		this.setSuccessMessage("");
		o=new StarderOrder();
		o.setId(Long.valueOf(id));
		int i=orderService.deleteOrder(o);
    	if(i>0){
    		od = new OrderDetail();
    		od.setMain_id(o.getId());
    		orderService.deleteOrderOfMain(od);
    		this.setSuccessMessage("Success !");
    	}else{
    		this.setFailMessage("Failed !");
    	}
    	return RESULT_MESSAGE;
	}
		
	
	/**
	 * 明细导入
	 * @return
	 */		
	public String importData(){
 			// 导入数据
	 			FileInputStream fileIn = null;
				Workbook rwb = null;
 	 			List<OrderDetail> odList = new ArrayList<OrderDetail>();
 				StringBuilder contentResult = new StringBuilder();
				try {
					fileIn = new FileInputStream(uploadFile);
					rwb = Workbook.getWorkbook(fileIn);
					Sheet rs = rwb.getSheet(0);
					int column = 0;
					column = rs.getColumns();
					int actualRows = 0;
					/** 去除空行得到真实行数 **/
					actualRows = StockUtil.delEmptyRow(rs);
					if (actualRows == 0 && column == 0) {
						this.setFailMessage("导入的Excel为空！");
						return RESULT_MESSAGE;
					} else {
						
						for (int i = 1; i < actualRows; i++) {
//							if ("".equals(rs.getCell(0, i).getContents().trim())) {
//								if (contentResult.length() > 0) {
//									contentResult.append("</br>");
//								}
//								contentResult.append("Row"+i+": QuoteItem  is not completed yet!");
//								break;
//							}
//							if ("".equals(rs.getCell(1, i).getContents().trim())) {
//								if (contentResult.length() > 0) {
//									contentResult.append("</br>");
//								}
//								contentResult.append("Row"+i+": 12NC  is not completed yet!");
//								break;
//							}
							if ("".equals(rs.getCell(2, i).getContents().trim())) {
								if (contentResult.length() > 0) {
									contentResult.append("</br>");
								}
								contentResult.append("Row"+i+": BookPart  is not completed yet!");
								break;
							}
							if ("".equals(rs.getCell(3, i).getContents().trim())) {
								if (contentResult.length() > 0) {
									contentResult.append("</br>");
								}
								contentResult.append("Row"+i+": OrderQTY  is not completed yet!");
								break;
							}
							if ("".equals(rs.getCell(4, i).getContents().trim())) {
								if (contentResult.length() > 0) {
									contentResult.append("</br>");
								}
								contentResult.append("Row"+i+": RequestDate  is not completed yet!");
								break;
							}
						}						 						
						
						if (!"".equals(contentResult.toString())) {
							this.setFailMessage(contentResult.toString());
							return RESULT_MESSAGE;
						}
						for (int i = 1; i < actualRows; i++) {
							od = new OrderDetail();
							od.setPo_item(rs.getCell(0, i).getContents().trim());
							od.setMaterial_id(rs.getCell(1, i).getContents().trim());
							od.setMaterial_name(rs.getCell(2, i).getContents().trim());	
 							od.setOrder_QTY(Integer.valueOf(rs.getCell(3, i).getContents().trim()));
 							if (rs.getCell(4, i).getType() == CellType.DATE) {
 						        DateCell dateCell = (DateCell) rs.getCell(4, i);
 						        Date date = dateCell.getDate();
 						        String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
 						        od.setDelivery_dateStr(dateStr);
 							} 
 							p=new Product();
 							p.setMaterial_id(od.getMaterial_id());
 							p.setMaterial_name(od.getMaterial_name());
 							p.setIsOrderItem("Y");
 							pList = productService.getProductListNoPage(p);
 							if(pList!=null&&pList.size()!=0){
 								p=pList.get(0);
 								od.setMaterial_typeId(p.getMaterial_type());
 								od.setMaterial_groupId(p.getMaterial_groupId());
 								od.setSale_unit(p.getBase_unit());
 								od.setMoq(p.getMoq());
 								//od.setLimited_QTY(p.getLimited_qty());
 								od.setPq(Integer.valueOf(p.getNumerator()));
 								od.setLead_time(p.getLead_time());
 							}else {
 								contentResult.append("Row"+i+":"+od.getMaterial_id()+" "+od.getMaterial_name()+"do not exist!");
 								break;
							} 														
							//od.setDelivery_date(sdf.parse(rs.getCell(8, i).getContents().trim()));
							odList.add(od);
						}
						
						if (!"".equals(contentResult.toString())) {
							this.setFailMessage(contentResult.toString());
							return RESULT_MESSAGE;
						}
					}
					String odJson = JsonUtil.list2json(odList);
					this.setSuccessMessage(odJson);
					return RESULT_MESSAGE;
//					int result = 1;
//					for (OrderDetail od : odList) {
//						result = productService.updateProduct(product);
//						if (result==0) {
//							break;
//						}
//					}
//					if (result==1){
//						this.setSuccessMessage("Success !");
//					} else {
//						this.setFailMessage("failed !");
//					}
				} catch (ArrayIndexOutOfBoundsException e) {
					 
					this.setFailMessage("Excel模板错误！");
				} catch (BiffException e) {
					 
					this.setFailMessage("03以上版本Excel导入暂不支持");
					return RESULT_MESSAGE;
				} catch (Exception e) {
					 
					this.setFailMessage("failed (导入内容格式有误)！");
					return RESULT_MESSAGE;
				}
				return RESULT_MESSAGE;
	    
	}
	

	
	



	


private String findOrderExcelXlsx(String path,Product pr) {
	StringBuilder contentResult = new StringBuilder();
	List<OrderDetail> odList = new ArrayList<OrderDetail>();
	try {
	 
		FileInputStream file = new FileInputStream(path);
	 	XSSFWorkbook xwb = new XSSFWorkbook(file);
	 	XSSFSheet sheet = xwb.getSheetAt(0);
		// 定义 row、cell
		XSSFRow row;
		// 循环输出表格中的内容
		if(sheet.getPhysicalNumberOfRows()<=1){
			return  "The Excel  is not completed yet！";
		}
		System.out.println("表格总行数"+sheet.getPhysicalNumberOfRows());
		System.out.println("第一行行号"+sheet.getFirstRowNum());
		System.out.println("最后一行行号"+sheet.getLastRowNum());
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			if(row==null){//空行跳过
				continue;
			}
			od = new OrderDetail();
			XSSFCell cell0 = row.getCell(0);
			if(cell0!=null){
				if(cell0.getCellType()==cell0.CELL_TYPE_NUMERIC){			
					od.setPo_item(String.valueOf((int)cell0.getNumericCellValue()));
				}else{
					od.setPo_item(cell0.getStringCellValue().trim());
				}
			}
			XSSFCell cell1 = row.getCell(1);
			if(cell1!=null){
				if(cell1.getCellType()==cell1.CELL_TYPE_NUMERIC){	
					//数字格式无法解析
					cell1.setCellType(cell1.CELL_TYPE_STRING);
				}
				od.setMaterial_id(cell1.getStringCellValue().trim());
			}
//			else {
//				contentResult.append("Row"+i+": 12NC  is not completed yet!");
//				break;
//			}
			XSSFCell cell2 = row.getCell(2);
			if(cell2!=null){
				if ("".equals(cell2.getStringCellValue())) {
					continue;
				}
				od.setMaterial_name(cell2.getStringCellValue().trim());
			}else {
				contentResult.append("Row"+i+": BookPart  is not completed yet!");
				break;
			}
			XSSFCell cell3 = row.getCell(3);
			if(cell3!=null){	
				if(cell3.getCellType()==cell3.CELL_TYPE_NUMERIC){			
					od.setOrder_QTY((int)cell3.getNumericCellValue());
				}else {
					contentResult.append("Row "+i+":  Order QTY format error!");
					break;
				}
			}else {
				contentResult.append("Row"+i+": Order QTY  is not completed yet!");
				break;
			}
			XSSFCell cell4 = row.getCell(4);
			if(cell4!=null){
				try {		
					od.setDelivery_dateStr(new SimpleDateFormat("yyyy-MM-dd").format(cell4.getDateCellValue()));
				} catch (Exception e) {
					contentResult.append("Row"+i+": RequestDate format error!");
					break;
				}
			}else {
				contentResult.append("Row"+i+": RequestDate  is not completed yet!");
				break;
			}
			p=new Product();
			if (od.getMaterial_name()!=null && !"".equals(od.getMaterial_name())) {
				p.setMaterial_name(od.getMaterial_name());						
			}else {
				p.setMaterial_id(od.getMaterial_id());						
			}
			p.setCustomer_id(pr.getCustomer_id());
			p.setCurrency_code(pr.getCurrency_code());
			p.setOffice_id(pr.getOffice_id());
			p.setIsOrderItem("Y");
			pList = productService.getDRQuoteProductListNoPage(p);
			if(pList!=null&&pList.size()!=0){
				p=pList.get(0);
				od.setMaterial_id(p.getMaterial_id());
				od.setMaterial_name(p.getMaterial_name());
				od.setMaterial_typeId(p.getMaterial_type());
				od.setMaterial_groupId(p.getMaterial_groupId());
				od.setSale_unit(p.getBase_unit());
				od.setMoq(p.getMoq());
				od.setPbMpp(String.valueOf(p.getPbPrice()));
				od.setPq(Integer.valueOf(p.getNumerator()));
				od.setLead_time(p.getLead_time());
			}else {
				contentResult.append("Row"+i+":"+od.getMaterial_id()+" "+od.getMaterial_name()+"do not exist!");
				break;
			} 														
			odList.add(od);
		}
		
		if (!"".equals(contentResult.toString())) {
			return contentResult.toString();
		}
		
		file.close();
	} catch (Exception e) {
		System.out.println(e.getMessage());
		return "failed (Importing content format error)！";
	}
	String odJson = JsonUtil.list2json(odList);
	return "success!;"+odJson;
}


	/**
	* 
	* 
	* @param path
	* @return
	*/
	private  String findOrderExcelXls(String path,Product pr) {
		// 导入数据
			FileInputStream fileIn = null;
			Workbook rwb = null;
			List<OrderDetail> odList = new ArrayList<OrderDetail>();
			StringBuilder contentResult = new StringBuilder();
		try {
			fileIn = new FileInputStream(path);
			rwb = Workbook.getWorkbook(fileIn);
			Sheet rs = rwb.getSheet(0);
			int column = 0;
			column = rs.getColumns();
			int actualRows = 0;
			/** 去除空行得到真实行数 **/
			actualRows = StockUtil.delEmptyRow(rs);
			if (actualRows == 0 && column == 0) {
				return  "The Excel  is not completed yet！";
			} else {
				
				for (int i = 1; i < actualRows; i++) {
//					if ("".equals(rs.getCell(1, i).getContents().trim())) {
//						if (contentResult.length() > 0) {
//							contentResult.append("</br>");
//						}
//						contentResult.append("Row"+i+": 12NC  is not completed yet!");
//						break;
//					}
					if ("".equals(rs.getCell(2, i).getContents().trim())) {
						if (contentResult.length() > 0) {
							contentResult.append("</br>");
						}
						contentResult.append("Row"+i+": BookPart  is not completed yet!");
						break;
					}
					if ("".equals(rs.getCell(3, i).getContents().trim())) {
						if (contentResult.length() > 0) {
							contentResult.append("</br>");
						}
						contentResult.append("Row"+i+": OrderQTY  is not completed yet!");
						break;
					}
					if ("".equals(rs.getCell(4, i).getContents().trim())) {
						if (contentResult.length() > 0) {
							contentResult.append("</br>");
						}
						contentResult.append("Row"+i+": RequestDate  is not completed yet!");
						break;
					}
				}						 						
				
				if (!"".equals(contentResult.toString())) {
					return contentResult.toString();
				}
				for (int i = 1; i < actualRows; i++) {
					od = new OrderDetail();
					od.setPo_item(rs.getCell(0, i).getContents().trim());
					od.setMaterial_id(rs.getCell(1, i).getContents().trim());
					od.setMaterial_name(rs.getCell(2, i).getContents().trim());	
					if(rs.getCell(3, i).getType() == CellType.NUMBER){
						NumberCell numberCell = (NumberCell) rs.getCell(3, i);
						int qty = (int) numberCell.getValue();
						od.setOrder_QTY(qty);
					}else{
						contentResult.append("Row "+i+": Order QTY format error!");
						break;
					}
					if (rs.getCell(4, i).getType() == CellType.DATE) {
				        DateCell dateCell = (DateCell) rs.getCell(4, i);
				        Date date = dateCell.getDate();
				        String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
				        od.setDelivery_dateStr(dateStr);
					}else{
						contentResult.append("Row "+i+": RequestDate format error!");
						break;
					} 
					p=new Product();
					if (od.getMaterial_name()!=null && !"".equals(od.getMaterial_name())) {
						p.setMaterial_name(od.getMaterial_name());						
					}else {
						p.setMaterial_id(od.getMaterial_id());						
					}
					p.setCustomer_id(pr.getCustomer_id());
					p.setCurrency_code(pr.getCurrency_code());
					p.setOffice_id(pr.getOffice_id());
					p.setIsOrderItem("Y");
					pList = productService.getDRQuoteProductListNoPage(p);
					if(pList!=null&&pList.size()!=0){
						p=pList.get(0);
						od.setMaterial_id(p.getMaterial_id());
						od.setMaterial_name(p.getMaterial_name());
						od.setMaterial_typeId(p.getMaterial_type());
						od.setMaterial_groupId(p.getMaterial_groupId());
						od.setSale_unit(p.getBase_unit());
						od.setMoq(p.getMoq());
						od.setPbMpp(String.valueOf(p.getPbPrice()));
						od.setPq(Integer.valueOf(p.getNumerator()));
						od.setLead_time(p.getLead_time());
					}else {
						contentResult.append("Row"+i+":"+od.getMaterial_id()+" "+od.getMaterial_name()+"do not exist!");
						break;
					} 														
					//od.setDelivery_date(sdf.parse(rs.getCell(8, i).getContents().trim()));
					odList.add(od);
				}
				
				if (!"".equals(contentResult.toString())) {
					return contentResult.toString();
				}
			}
			String odJson = JsonUtil.list2json(odList);
			return "success!;"+odJson;
		} catch (ArrayIndexOutOfBoundsException e) {			 
			return "Excel Template error！";//模板错误
		} catch (Exception e) {			 
			return "failed (Importing content format error)！";
		}
	}

	public  String findOrderExcel() {
		String result;
		Product pr = new Product();
		pr.setCustomer_id(customer_id);
		pr.setOffice_id(office_id);
		pr.setCurrency_code(currency_code);
		if (ExcelUtil.getExcelStyle(path).intValue() == 1) {
		  result = findOrderExcelXls(uploadFile,pr);
		  if (result.indexOf(";")==-1) {
			  this.setFailMessage(result);
		  }else{
			  this.setSuccessMessage(result.split(";")[1]);
		  }
		  return RESULT_MESSAGE;
		} else if (ExcelUtil.getExcelStyle(path).intValue() == 2){
	 	  result = findOrderExcelXlsx(uploadFile,pr);
		  if (result.indexOf(";")==-1) {
			  this.setFailMessage(result);
		  }else{
			  this.setSuccessMessage(result.split(";")[1]);
		  }
		  return RESULT_MESSAGE;
		}else{
			this.setFailMessage("failed (Importing content format error)！");//倒入内容格式错误
			return RESULT_MESSAGE;		
		} 
	}

	
	
//将当前导入的orderdetail制成excel导出，对比检查
	public void downloadExcelData(){
		try{
	   		
	   		List list=new ArrayList();
	   		list.add("PO Item");
			list.add("12NC");
			list.add("BookPart");
			list.add("Order Qty");
			list.add("Request Date");

	   	    
			File source = new File("OrderDetail.xls");
			WritableWorkbook wwb = Workbook.createWorkbook(source);
			WritableSheet sheet = wwb.createSheet("orderDetail", 0);
			Label label = null;
			Label label2=null;
			Label label3=null;
			Label label4 = null;
			Label label5=null;
		    //设置字体;  
	        WritableFont font1 = new WritableFont(WritableFont.ARIAL,12,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED);  
	  
	        WritableCellFormat cellFormat1 = new WritableCellFormat(font1);  
	        //设置背景颜色;  
	        cellFormat1.setBackground(Colour.YELLOW);
	       //设置边框;  
	        cellFormat1.setBorder(Border.ALL, BorderLineStyle.HAIR);  
			for (int i = 0; i < list.size(); i++) {
				// Label(x,y,z)其中x代表单元格的第x+1列，第y+1行, 单元格的内容是y
				// 在Label对象的子对象中指明单元格的位置和内容
				label = new Label(i, 0, list.get(i).toString(),cellFormat1);
				// 将定义好的单元格添加到工作表中
				sheet.addCell(label);
			}
			orderDetailJson = java.net.URLDecoder.decode(this.orderDetailJson,"utf-8");	
			odList = JsonUtil.getDTOList(orderDetailJson, OrderDetail.class);
//			List<Product> pList =new ArrayList<Product>();
//			p = new Product();
//			p.setIsDownLoad("Y");
//			pList =productService.getProductListNoPage(p);
            
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for (int i = 0; i < odList.size(); i++) {
				// Label(x,y,z)其中x代表单元格的第x+1列，第y+1行, 单元格的内容是y
				// 在Label对象的子对象中指明单元格的位置和内容
				label = new Label(0, i+1, odList.get(i).getMaterial_id());
				label2 = new Label(1, i+1, odList.get(i).getMaterial_id());
				label3 =new Label(2,i+1,odList.get(i).getMaterial_name());
				label4 = new Label(3, i+1, String.valueOf(odList.get(i).getOrder_QTY()));
				label5 = new Label(4, i+1, sdf.format(odList.get(i).getDelivery_date()));
				// 将定义好的单元格添加到工作表中
				sheet.addCell(label);
				sheet.addCell(label2);
 				sheet.addCell(label3);
 				sheet.addCell(label4);
				sheet.addCell(label5);
			}
			wwb.write(); // 写入数据
			wwb.close(); // 关闭文件
			
			display(source,"OrderDetail.xls",ServletActionContext.getResponse());
			
		} catch (Exception e) {
			this.setFailMessage("Excel Error！");
		}			
	}
	 
	//将Order数据（所有字段表头+明细）制成excel导出
		public void downloadOrderData(){
			try{
		   		
		   		List list=new ArrayList();
		   		
		   		list.add("Sap Order Id");
		   		list.add("Purchase Order");
		   		list.add("Customer");
		   		list.add("Currency");
		   		list.add("Ship To");
				list.add("Payer To");
				list.add("Billing To");
				list.add("PO Item");
				list.add("12NC");
				list.add("BookPart");
				list.add("Material Type");
				list.add("Product Group");
				list.add("Sale Unit");
				list.add("MOQ(pc)");
				list.add("PQ");
				list.add("Order Qty");
				list.add("Lead Time(week)");
				list.add("Request Date");
				//list.add("Confirm Delivery Date");
				File source = new File("OrderDetail.xls");
				WritableWorkbook wwb = Workbook.createWorkbook(source);
				WritableSheet sheet = wwb.createSheet("orderDetail", 0);
				Label label = null;
				Label label2=null;
				Label label3=null;
				Label label4 = null;
				Label label5=null;
				Label label6=null;
				Label label7=null;
				Label label8=null;
				Label label9=null;
				Label label10 = null;
				Label label11=null;
				Label label12=null;
				Label label13=null;
				Label label14=null;
				Label label15=null;
				Label label16=null;
				Label label17=null;
				Label label18 = null;
			    //设置字体;  
		        WritableFont font1 = new WritableFont(WritableFont.ARIAL,12,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED);  
		  
		        WritableCellFormat cellFormat1 = new WritableCellFormat(font1);  
		        //设置背景颜色;  
		        cellFormat1.setBackground(Colour.YELLOW);
		       //设置边框;  
		        cellFormat1.setBorder(Border.ALL, BorderLineStyle.HAIR);  
				for (int i = 0; i < list.size(); i++) {
					// Label(x,y,z)其中x代表单元格的第x+1列，第y+1行, 单元格的内容是y
					// 在Label对象的子对象中指明单元格的位置和内容
					label = new Label(i, 0, list.get(i).toString(),cellFormat1);
					// 将定义好的单元格添加到工作表中
					sheet.addCell(label);
				}
				OrderDetail odDetail = new OrderDetail();
				odDetail.setOrder_id(order_id);	
				odDetail.setSap_order_id(sap_order_id);
				odDetail.setStart_dateStr(start_dateStr);
				odDetail.setEnd_dateStr(end_dateStr);
				String loginId =  this.getUser().getLoginId();
				/*if(!"admin".equals(loginId)){	
					String userId  = this.getUser().getUserId();
					Role r = new Role();
					r.setStart(0);
					r.setEnd(100);
					r.setEmp_code(userId);
					roleList = roleService.getSelectedRole4StationList(r);
					for (Role rl : roleList) {
						loginRole = rl.getRoleId();
						if("JXS".equals(loginRole)){			
							odDetail.setLoginId(loginId);//创建时将登陆账号带到页面，使得该账号只能选择绑定到其自身的Disti
						}
					}
				}*/
				String orgString = this.getUser().getOrgId();
				if(orgString==null || "".equals(orgString)){
					odDetail.setLoginId(loginId);
				}
				odDetail.setBranch_id(branch_id);			
				List<OrderDetail> odList = orderService.downloadOrderData(odDetail);
	            
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				for (int i = 0; i < odList.size(); i++) {
					OrderDetail od = odList.get(i);
					// Label(x,y,z)其中x代表单元格的第x+1列，第y+1行, 单元格的内容是y
					// 在Label对象的子对象中指明单元格的位置和内容
					label = new Label(0, i+1, od.getSap_order_id());
					label2 = new Label(1, i+1, od.getOrder_id());
					label3 =new Label(2,i+1,   od.getBranch_id());
					label4 = new Label(3, i+1, transNullString(od.getCurrency_code()));
					label5 = new Label(4, i+1, transNullString(od.getShip_to()));
					label6 = new Label(5, i+1, transNullString(od.getPayer_to()));
					label7 = new Label(6,i+1,  transNullString(od.getBilling_to()));
					label8 = new Label(7, i+1, transNullString(od.getPo_item()));
					label9 = new Label(8, i+1, transNullString(od.getMaterial_id()));
					label10 =new Label(9, i+1, transNullString(od.getMaterial_name()));
					label11 =new Label(10,i+1, transNullString(od.getMaterial_typeId()));
					label12 =new Label(11,i+1, transNullString(od.getProduct_group()));
					label13 =new Label(12,i+1, transNullString(od.getSale_unit()));
					label14 =new Label(13,i+1, String.valueOf(od.getMoq()));//
					label15 =new Label(14,i+1, String.valueOf(od.getPq()));//
					label16 =new Label(15,i+1, String.valueOf(od.getOrder_QTY()));//
					label17 =new Label(16,i+1, String.valueOf(od.getLead_time()));
					label18 =new Label(17,i+1, transNullString(od.getDelivery_dateStr()));
					// 将定义好的单元格添加到工作表中
					sheet.addCell(label);sheet.setColumnView(0, 15);
					sheet.addCell(label2);sheet.setColumnView(1, 15);
					sheet.addCell(label3);sheet.setColumnView(2, 60);
					sheet.addCell(label4);sheet.setColumnView(3, 30);
					sheet.addCell(label5);sheet.setColumnView(4, 40);
					sheet.addCell(label6);sheet.setColumnView(5, 15);
					sheet.addCell(label7);sheet.setColumnView(6, 20);
					sheet.addCell(label8);sheet.setColumnView(7, 20);
					sheet.addCell(label9);sheet.setColumnView(8, 40);
					sheet.addCell(label10);sheet.setColumnView(9, 15);
					sheet.addCell(label11);sheet.setColumnView(10, 15);
					sheet.addCell(label12);sheet.setColumnView(11, 15);
					sheet.addCell(label13);sheet.setColumnView(12, 15);
					sheet.addCell(label14);sheet.setColumnView(13, 15);
					sheet.addCell(label15);sheet.setColumnView(14, 15);
					sheet.addCell(label16);sheet.setColumnView(15, 15);
					sheet.addCell(label17);sheet.setColumnView(16, 15);
					sheet.addCell(label18);sheet.setColumnView(17, 15);
				}
				wwb.write(); // 写入数据
				wwb.close(); // 关闭文件
				
				display(source,"Order.xls",ServletActionContext.getResponse());
				
			} catch (Exception e) {
				this.setFailMessage("Excel Error！");
			}			
		}
//下载	
	private boolean display(File file, String fileName, HttpServletResponse response) {
		FileInputStream in = null;
		OutputStream out = null;
		try {
			fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename=\"" + fileName);
			in = new FileInputStream(file);
			out = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			response.flushBuffer();
		} catch (Exception ex) {
			return false;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (final Exception e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (final Exception e) {
				}
			}
			return true;
		}
	}
	
	
	public String transState(int flag){
		if (flag == 9) {
			return "Deleted";
		}else if (flag == 0) {
			return "Pending";
		}else if (flag == 1) {
			return "Approve";
		}else if (flag == 2) {
			return "Reject";
		}else{
			return String.valueOf(flag);
		}
	}

	public String transProjectState(int flag){
		if (flag == 0) {
			return "Opportunity";
		}else if (flag == 1) {
			return "Design_in";
		}else if (flag == 2) {
			return "Design_win";
		}else{
			return String.valueOf(flag);
		}
	}

	public String transDate(Date d){
		if (d!=null) {
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(d);
		}else {
			return "";
		}
	}

	public String transNullString(String s){
		if (s==null||"undefined".equals(s)||"".equals(s)) {
			return "";
		}else {
			return s;
		}
	}
	public IOrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}

	public StarderOrder getO() {
		return o;
	}

	public void setO(StarderOrder o) {
		this.o = o;
	}

	public List<StarderOrder> getoList() {
		return oList;
	}

	public void setoList(List<StarderOrder> oList) {
		this.oList = oList;
	}

	public List<OrderDetail> getOdList() {
		return odList;
	}

	public void setOdList(List<OrderDetail> odList) {
		this.odList = odList;
	}

	public String getOrderDetailJson() {
		return orderDetailJson;
	}

	public void setOrderDetailJson(String orderDetailJson) {
		this.orderDetailJson = orderDetailJson;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDelOrderDetail() {
		return delOrderDetail;
	}

	public void setDelOrderDetail(String delOrderDetail) {
		this.delOrderDetail = delOrderDetail;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public OrderDetail getOd() {
		return od;
	}

	public String getMain_id() {
		return main_id;
	}
	public void setMain_id(String main_id) {
		this.main_id = main_id;
	}
	public void setOd(OrderDetail od) {
		this.od = od;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public List<CustomerUser> getCusUserList() {
		return cusUserList;
	}
	public void setCusUserList(List<CustomerUser> cusUserList) {
		this.cusUserList = cusUserList;
	}
	public CustomerUser getCusUser() {
		return cusUser;
	}
	public void setCusUser(CustomerUser cusUser) {
		this.cusUser = cusUser;
	}
	public ICustomerService getCustomerService() {
		return customerService;
	}
	public void setCustomerService(ICustomerService customerService) {
		this.customerService = customerService;
	}
	public String getSap_order_id() {
		return sap_order_id;
	}
	public void setSap_order_id(String sap_order_id) {
		this.sap_order_id = sap_order_id;
	}
	public String getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}
	public Product getP() {
		return p;
	}
	public void setP(Product p) {
		this.p = p;
	}
	public List<Product> getpList() {
		return pList;
	}
	public void setpList(List<Product> pList) {
		this.pList = pList;
	}
	public IProductService getProductService() {
		return productService;
	}
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getStart_dateStr() {
		return start_dateStr;
	}
	public void setStart_dateStr(String start_dateStr) {
		this.start_dateStr = start_dateStr;
	}
	public String getEnd_dateStr() {
		return end_dateStr;
	}
	public void setEnd_dateStr(String end_dateStr) {
		this.end_dateStr = end_dateStr;
	}
	
	
	  public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	public IRoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	public String getLoginRole() {
		return loginRole;
	}
	public void setLoginRole(String loginRole) {
		this.loginRole = loginRole;
	}
	public String getCurrency_code() {
		return currency_code;
	}
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}
	public String getOffice_id() {
		return office_id;
	}
	public void setOffice_id(String office_id) {
		this.office_id = office_id;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public SampleOrderToSapService getSampleOrderToSapService() {
		return sampleOrderToSapService;
	}
	public void setSampleOrderToSapService(
			SampleOrderToSapService sampleOrderToSapService) {
		this.sampleOrderToSapService = sampleOrderToSapService;
	}
	public void addActionError(String anErrorMessage){
		   String s=anErrorMessage;
		   System.out.println(s);
		  }
		  public void addActionMessage(String aMessage){
		   String s=aMessage;
		   System.out.println(s);
		  
		  }
		  public void addFieldError(String fieldName, String errorMessage){
		   String s=errorMessage;
		   String f=fieldName;
		   System.out.println(s);
		   System.out.println(f);
		  
		  }
}
