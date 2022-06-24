package com.jingtong.platform.cusInventroy.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import jxl.CellType;
import jxl.Sheet;
import jxl.NumberCell;
import jxl.Workbook;
import jxl.biff.ContinueRecord;
import jxl.read.biff.BiffException;
import jxl.read.biff.CellValue;
import jxl.read.biff.SharedNumberFormulaRecord;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.crystaldecisions.celib.parser.ParseException;
import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.cusInventroy.dao.ICusInventroyDao;
import com.jingtong.platform.cusInventroy.pojo.CusInventroy;
import com.jingtong.platform.cusInventroy.service.ICusInventroyService;
import com.jingtong.platform.customer.pojo.CustomerUser;
import com.jingtong.platform.customer.service.ICustomerService;
import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.dict.service.IDictService;
import com.jingtong.platform.ecgroup.pojo.GroupInfo;
import com.jingtong.platform.ecgroup.service.IGroupInfoService;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.framework.util.DateUtil;
import com.jingtong.platform.framework.util.ExcelUtil;
import com.jingtong.platform.framework.util.StockUtil;
import com.jingtong.platform.pos.pojo.Pos;
import com.jingtong.platform.product.dao.IProductDao;
import com.jingtong.platform.product.dao.impl.ProductDaoImpl;
import com.jingtong.platform.product.pojo.Product;
import com.jingtong.platform.product.service.IProductService;
import com.jingtong.platform.product.service.impl.ProductServiceImpl;
import com.jingtong.platform.sap.pojo.QuoteDetail;

/**
 * @author cl
 * @createDate 2016-6-16
 * 
 */
public class CusInventroyAction extends BaseAction {
	private ICusInventroyService cusInventroyService;
	private CusInventroy c;
	private List<CusInventroy> cList;
	private String id;
	private int total;
	private String st_party;
	private String part_name;
	private String state;
	private String uploadFile;
	private Date start_time;
	private Date end_time;
	private String start_timeString;
	private String end_timeString;
	private String cus_name;
	private String status;
	private String path;
	private String file_url;
	private String file_id_str;;
	private long file_id;
	private CustomerUser cusUser;
	private List<CustomerUser> cusUserList;
	 private List<Integer> idList;
	private ICustomerService customerService;
	private IProductService productService;
	
	public String getStart_timeString() {
		return start_timeString;
	}

	public void setStart_timeString(String start_timeString) {
		this.start_timeString = start_timeString;
	}

	public String getEnd_timeString() {
		return end_timeString;
	}

	public void setEnd_timeString(String end_timeString) {
		this.end_timeString = end_timeString;
	}

	private CmsTbDict cmsTbDict;
	private IDictService dictService;
	private List<CustomerUser> userList;
	private String frequencyMark;
	
	


	public String getFrequencyMark() {
		return frequencyMark;
	}

	public void setFrequencyMark(String frequencyMark) {
		this.frequencyMark = frequencyMark;
	}

	/**
	 * 跳转到查询页面
	 * 
	 * @return
	 */
	public String toSearchCusInventroy() {
		return "toSearchCusInventroy";
	}
	
	public String toSearchCusInventroyDetail() {
		c = new CusInventroy();
		c.setFile_id(file_id);
		c.setFile_url(file_url);
		return "toSearchCusInventroyDetail";
	}


	/**
	 * 跳转到查看页面
	 * 
	 * @return
	 */
	public String toViewCusInventroy() {
		c = new CusInventroy();
		c.setId(Long.valueOf(id));
		c = cusInventroyService.getCusInventroyById(c);
		return "toViewCusInventroy";
	}

	public String importExcel(){
		return "importExcel";
	}
	
	/**
	 * 跳转到报表页面
	 * 
	 * @return
	 */
	public String bBCusInventroy() {
		return "bBCusInventroy";
	}
	
	/**
	 * 查询订单信息
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "cList", include = {  "status", "message_number",
			"doc_id", "doc_code", "doc_identifier", "function_code",
			"sender_id", "recipient_id", "sender_date", "by_party", "st_party",
			"line_item", "part_name", "item_type", "inventroy_method",
			"quantity_type", "quantity", "unit_code", "currency", "price_code",
			"price", "price_type", "price_basis", "synchronization_time",
			"synchronization_timestr", "synchronization_user", "update_time",
			"update_timestr", "update_user", "data_from" ,"cus_name","sales_org","file_id_str",
			"monthly_closing_date","tips","file_id","file_url","status_num","frequency","qty","amount"}, total = "total")
	public String getCusInventroyList() {
		c = new CusInventroy();
		c.setStart(getStart());
		c.setEnd(getEnd());
		c.setSort("file_id");
		c.setDir("desc");
		
		if (StringUtils.isNotEmpty(cus_name)
				&& StringUtils.isNotEmpty(cus_name.trim())) {
			try {
				cus_name = java.net.URLDecoder.decode(cus_name,"UTF-8");
				cus_name = cus_name.toUpperCase();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		c.setCus_name(cus_name);
		c.setFile_url(file_url);
		c.setStatus(status);
		if (null != start_timeString && !"".equals(start_timeString))
			c.setStart_time(DateUtil.getDateFromStr(start_timeString));
		if (null != end_timeString && !"".equals(end_timeString))
			c.setEnd_time(DateUtil.getDateFromStr(end_timeString));
		
		c.setFile_id(file_id);
		c.setFile_id_str(file_id_str);
		String loginId =  this.getUser().getUserId();
		String roleIds = this.getUser().getRoleIds();
//		if(!"85131".equals(loginId) && !roleIds.contains("")){			
//			c.setSynchronization_user(loginId);
//			
//		}
		total = cusInventroyService.getCusInventroyListCount(c);
		if (total > 0) {
			cList = cusInventroyService.searchCusInventroyList(c);
			System.out.println(System.currentTimeMillis());
			for ( CusInventroy p: cList){
				Long  file_id = p.getFile_id();
				CusInventroy pp = new CusInventroy();
				pp.setFile_id(file_id);
				
				CusInventroy ppp = cusInventroyService.getInvByfileId(pp);
				 p.setStatus_num(ppp.getStatus_num());
				 p.setFrequency(ppp.getFrequency());
				 p.setQty(ppp.getQty());
				 p.setAmount(ppp.getAmount());
				 
			}
		}
		return JSON;
	}
	
	
	/**
	 * 查询明细信息
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "cList", include = {  "id","status", "message_number",
			"doc_id", "doc_code", "doc_identifier", "function_code",
			"sender_id", "recipient_id", "sender_date", "by_party", "st_party",
			"line_item", "part_name", "item_type", "inventroy_method",
			"quantity_type", "quantity", "unit_code", "currency", "price_code",
			"price", "price_type", "price_basis", "synchronization_time",
			"synchronization_timestr", "synchronization_user","update_time","file_id_str",
			"update_timestr", "update_user", "data_from" ,"cus_name","sales_org",
			"monthly_closing_date","tips","file_id","file_url","status_num","frequency"}, total = "total")
	public String getCusInventroyListById() {
		c = new CusInventroy();
		c.setStart(getStart());
		c.setEnd(getEnd());
		c.setSort("synchronization_time");
		c.setDir("desc");
		if (StringUtils.isNotEmpty(part_name)
				&& StringUtils.isNotEmpty(part_name.trim())) {
			try {
				part_name = java.net.URLDecoder.decode(part_name,"UTF-8");
				part_name = part_name.toUpperCase();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		c.setPart_name(part_name);
		
		c.setFile_id(file_id);
		if (StringUtils.isNotEmpty(cus_name)
				&& StringUtils.isNotEmpty(cus_name.trim())) {
			try {
				cus_name = java.net.URLDecoder.decode(cus_name,"UTF-8");
				cus_name = cus_name.toUpperCase();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		c.setCus_name(cus_name);
		c.setStatus(status);
		if (null != start_timeString && !"".equals(start_timeString))
			c.setStart_time(DateUtil.getDateFromStr(start_timeString));
		if (null != end_timeString && !"".equals(end_timeString))
			c.setEnd_time(DateUtil.getDateFromStr(end_timeString));
		
		total = cusInventroyService.getCusInventroyListCountById(c);
		if (total > 0) {
			cList = cusInventroyService.searchCusInventroyListById(c);
		}
		return JSON;
	}
	
	
	
	
	
	/**
	 * 查询明细信息
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "cList", include = {  "status", "message_number",
			"doc_id", "doc_code", "doc_identifier", "function_code",
			"sender_id", "recipient_id", "sender_date", "by_party", "st_party",
			"line_item", "part_name", "item_type", "inventroy_method",
			"quantity_type", "quantity", "unit_code", "currency", "price_code",
			"price","price1","price2", "price_type", "price_basis", "synchronization_time","unit",
			"synchronization_timestr", "synchronization_user","update_time","file_id_str",
			"update_timestr", "update_user", "data_from" ,"cus_name","sales_org","material_id",
			"monthly_closing_date","tips","file_id","file_url","status_num","price_USD","price_total",
			"price_USD_total","price1_total","price2_total"}, total = "total")
	public String getCusInventroyListForB() {
		c = new CusInventroy();
		c.setStart(getStart());
		c.setEnd(getEnd());
		c.setSort("synchronization_time");
		c.setDir("desc");
		if (StringUtils.isNotEmpty(part_name)
				&& StringUtils.isNotEmpty(part_name.trim())) {
			try {
				part_name = java.net.URLDecoder.decode(part_name,"UTF-8");
				part_name = part_name.toUpperCase();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		c.setPart_name(part_name);
		if (StringUtils.isNotEmpty(cus_name)
				&& StringUtils.isNotEmpty(cus_name.trim())) {
			try {
				cus_name = java.net.URLDecoder.decode(cus_name,"UTF-8");
				cus_name = cus_name.toUpperCase();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		c.setCus_name(cus_name);
		c.setStatus(status);
		if (null != start_timeString && !"".equals(start_timeString))
			c.setStart_time(DateUtil.getDateFromStr(start_timeString));
		if (null != end_timeString && !"".equals(end_timeString))
			c.setEnd_time(DateUtil.getDateFromStr(end_timeString));
		
		CmsTbDict searchCmsTbDict = new CmsTbDict();
		searchCmsTbDict.setItemId(Long.valueOf("14213"));
		cmsTbDict = dictService.getCmsTbDict(searchCmsTbDict);
		double rate = Double.valueOf(cmsTbDict.getItemValue());
		total = cusInventroyService.getCusInventroyListCountForB(c);
		if (total > 0) {
			
			
			cList = cusInventroyService.searchCusInventroyListForB(c);
			System.out.println(cList.size());
			for (CusInventroy c : cList) {
				c.setUnit("PC");

				//保留4位小数
				BigDecimal b=new BigDecimal(Double.valueOf(c.getPrice())/Double.valueOf(c.getPrice_basis()));  
				double f1 = b.setScale(4,   BigDecimal.ROUND_HALF_UP).doubleValue();  
				c.setPrice(String.valueOf(f1));
				
				
			if(c.getCurrency().equals("EUR")){
				//保留4位小数
				BigDecimal b2=new BigDecimal((Double.valueOf(c.getPrice())/rate));  
				double f2 = b.setScale(4,   BigDecimal.ROUND_HALF_UP).doubleValue();  
				c.setPrice_USD(String.valueOf(f2));
//				c.setPrice_USD(String.valueOf((Double.valueOf(c.getPrice())/rate)));
			}else{
				c.setPrice_USD(c.getPrice());
			}
			c.setPrice1(c.getPrice());
			c.setPrice2(c.getPrice());
			  
			//大数字防止科学计数法
			java.text.NumberFormat nf = java.text.NumberFormat.getInstance();   
			nf.setGroupingUsed(false);
			Double d = (Double.valueOf(c.getPrice()))*(Double.valueOf(c.getQuantity()));
			c.setPrice_total(String.valueOf(nf.format(d)));
			System.out.println("d:="+nf.format(d));   
			
			Double e = (Double.valueOf(c.getPrice_USD()))*(Double.valueOf(c.getQuantity()));
			c.setPrice_USD_total(String.valueOf(nf.format(d)));
			System.out.println("e:="+nf.format(e)); 
			
			Double f = (Double.valueOf(c.getPrice1()))*(Double.valueOf(c.getQuantity()));
			c.setPrice1_total(String.valueOf(nf.format(f)));
			System.out.println("f:="+nf.format(f)); 
			
			Double g = (Double.valueOf(c.getPrice2()))*(Double.valueOf(c.getQuantity()));
			c.setPrice2_total(String.valueOf(nf.format(g)));
			System.out.println("g:="+nf.format(g)); 
			
//			c.setPrice_total(String.valueOf((Double.valueOf(c.getPrice()))*(Double.valueOf(c.getQuantity()))));
//			c.setPrice_USD_total(String.valueOf((Double.valueOf(c.getPrice_USD()))*(Double.valueOf(c.getQuantity()))));
//			c.setPrice1_total(String.valueOf((Double.valueOf(c.getPrice1()))*(Double.valueOf(c.getQuantity()))));
//			c.setPrice2_total(String.valueOf((Double.valueOf(c.getPrice2()))*(Double.valueOf(c.getQuantity()))));
			
		  } 
		}
		return JSON;
	}
	
	
	
	/**
	 * 导入excel数据
	 * @return
	 * @throws ParseException 
	 */
	public String importData() throws ParseException{
			// 导入数据
			String resultMessage = "";
 			FileInputStream fileIn = null;
			Workbook rwb = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
 			List<CusInventroy> cList = new ArrayList<CusInventroy>();
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
						if ("".equals(rs.getCell(0, i).getContents().trim())) {
							if (contentResult.length() > 0) {
								contentResult.append("</br>");
							}
							contentResult.append("**SO (Sales Org.)列不能为空!请检查源数据并处理");
							break;
						}
						if ("".equals(rs.getCell(1, i).getContents().trim())) {
							if (contentResult.length() > 0) {
								contentResult.append("</br>");
							}
							contentResult.append("Sold to HQ列不能为空!请检查源数据并处理");
							break;
						}
						if ("".equals(rs.getCell(2, i).getContents().trim())) {
							if (contentResult.length() > 0) {
								contentResult.append("</br>");
							}
							contentResult.append("Dist. Sold to (Distributor num)列不能为空!请检查源数据并处理");
							break;
						}
						
						if ("".equals(rs.getCell(3, i).getContents().trim())) {
							if (contentResult.length() > 0) {
								contentResult.append("</br>");
							}
							contentResult.append("Monthly closing date不能为空!请检查源数据并处理");
							break;
						}
//						if ("".equals(rs.getCell(4, i).getContents().trim())) {
//							if (contentResult.length() > 0) {
//								contentResult.append("</br>");
//							}
//							contentResult.append("价格不能为空!请检查源数据并处理");
//							break;
//						}
//						if ("".equals(rs.getCell(5, i).getContents().trim())) {
//							if (contentResult.length() > 0) {
//								contentResult.append("</br>");
//							}
//							contentResult.append("开始日期不能为空!请检查源数据并处理");
//							break;
//						}
						if ("".equals(rs.getCell(6, i).getContents().trim())) {
							if (contentResult.length() > 0) {
								contentResult.append("</br>");
							}
							contentResult.append("BOOK PART不能为空!请检查源数据并处理");
							break;
						}
						if ("".equals(rs.getCell(7, i).getContents().trim())) {
							if (contentResult.length() > 0) {
								contentResult.append("</br>");
							}
							contentResult.append("Inventory Qty不能为空!请检查源数据并处理");
							break;
						}
						if ("".equals(rs.getCell(8, i).getContents().trim())) {
							if (contentResult.length() > 0) {
								contentResult.append("</br>");
							}
							contentResult.append("PriUnit不能为空!请检查源数据并处理");
							break;
						}
						if ("".equals(rs.getCell(9, i).getContents().trim())) {
							if (contentResult.length() > 0) {
								contentResult.append("</br>");
							}
							contentResult.append("Currency不能为空!请检查源数据并处理");
							break;
						}
						if ("".equals(rs.getCell(10, i).getContents().trim())) {
							if (contentResult.length() > 0) {
								contentResult.append("</br>");
							}
							contentResult.append("Disti Unit Cost不能为空!请检查源数据并处理");
							break;
						}
					}						 					
					
					if (!"".equals(contentResult.toString())) {
						this.setFailMessage(contentResult.toString());
						return RESULT_MESSAGE;
					}
					CusInventroy cusInventroy;
					for (int i = 1; i < actualRows; i++) {
						cusInventroy = new CusInventroy();
						cusInventroy.setSales_org(rs.getCell(0, i).getContents().trim());
						cusInventroy.setSt_party(rs.getCell(1, i).getContents().trim());
						cusInventroy.setCus_name(rs.getCell(2, i).getContents().trim());
						cusInventroy.setSender_date(rs.getCell(3, i).getContents().trim());
						
						// 3 Bad ship date
						String stri = rs.getCell(3, i).getContents().trim();
						 Format f = new SimpleDateFormat("yyyyMMdd");
						Date d = (Date) f.parseObject(stri);

						String tmp = f.format(d);
						System.out.println(stri + ":" + tmp.equals(stri));
						boolean flag = tmp.equals(stri);
						if (flag = false){
							resultMessage ="Bad ship date!";
						}
						// 3 Invalid ship date
						//获取当前日期
						Date date = new Date();
						SimpleDateFormat sdf1= new SimpleDateFormat("yyyyMMdd");
						String DateStr1 = sdf1.format(date);
						String DateStr2 = rs.getCell(3, i).getContents().trim();   
				        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");  
				        Date dateTime1 = dateFormat.parse(DateStr1);  
				        Date dateTime2 = dateFormat.parse(DateStr2);  
				        int b = dateTime1.compareTo(dateTime2);  
				        if(b<0){
				        	resultMessage ="Invalid ship date!";
						}
						
						cusInventroy.setDoc_identifier(rs.getCell(4, i).getContents().trim());
						cusInventroy.setDoc_code(rs.getCell(5, i).getContents().trim());
						cusInventroy.setPart_name(rs.getCell(6, i).getContents().trim());
						
						//6 Bad Book part
							
							
						total =cusInventroyService.searchProductCount(cusInventroy);
						if(total<=0){
							resultMessage ="Bad Book part!";
							}
							
						
						
						cusInventroy.setQuantity(rs.getCell(7, i).getContents().trim());
						
						//7 Bad quantity
						
						if(Long.valueOf(rs.getCell(7, i).getContents().trim())<0){
							resultMessage ="Bad quantity!";
						}
						
						cusInventroy.setPrice_basis(rs.getCell(8, i).getContents().trim());
						cusInventroy.setCurrency(rs.getCell(9, i).getContents().trim());
						
						// 9 Invalid currency
						
						if(!"USD4".equals(rs.getCell(9, i).getContents().trim())&!"EUR4".equals(rs.getCell(9, i).getContents().trim())){
							resultMessage ="Invalid currency!";
						}
						
						cusInventroy.setPrice(rs.getCell(10, i).getContents().trim());
						
						if(resultMessage !=""){
							cusInventroy.setSynchronization_user(this.getUser().getUserId());
							cusInventroy.setTips(resultMessage);
							cusInventroy.setStatus("0");
						}else{
							cusInventroy.setSynchronization_user(this.getUser().getUserId());
							cusInventroy.setTips("Success!");
							cusInventroy.setStatus("1");
						}
						
						cList.add(cusInventroy);
					}
				}
				long result = 1;
				for (CusInventroy cusInventroy : cList) {
					int n = cusInventroyService.getCusInventroyListCount(cusInventroy);
					if (n>=1) {
//						int a = cusInventroyService.updateCusInventroy(cusInventroy);
						result = cusInventroyService.createCusInventroy(cusInventroy);
					}else {
						result = cusInventroyService.createCusInventroy(cusInventroy);						
					}
//					Product product = new Product();
//					product.setMaterial_name(cusInventroy.getPart_name());
//					System.out.println(product.getMaterial_name());
//					int i =productService.getProductListCountByName(product);
//					if(i==1){
//						product = productService.getProductByName(product);
//						cusInventroy.setMaterial_id(product.getCustomer_id());
//						cusInventroyService.updateCusInventroy(cusInventroy);
//					}
					if (result==0) {
						break;
					}
				}
				if (result>0){
					this.setSuccessMessage("Success !");
				} else {
					this.setFailMessage("failed !");
				}
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
	
	
	
	public void downloadExcelModel(){
		try{
	   		
	   		List<String> list=new ArrayList<String>();
			list.add("Sales_org");
			list.add("St_party");
			list.add("Cus_name");
			list.add("Sender_date");
			list.add("Doc_identifier");
			list.add("Doc_code");
			list.add("Part_name");
			list.add("Quantity");
			list.add("Price_basis");
			list.add("Currency");
			list.add("Price");
			
	   	    
			File source = new File("inventory.xls");
			WritableWorkbook wwb = Workbook.createWorkbook(source);
			WritableSheet sheet = wwb.createSheet("Customer_inventroy", 0);
			Label label = null;
			Label label2=null;
			Label label3=null;
			Label label4 = null;
			Label label5=null;
			Label label6=null;
			Label label7=null;
			Label label8=null;
			Label label9=null;
			Label label10=null;
			Label label11=null;
			for (int i = 0; i < list.size(); i++) {
				// Label(x,y,z)其中x代表单元格的第x+1列，第y+1行, 单元格的内容是y
				// 在Label对象的子对象中指明单元格的位置和内容
				label = new Label(i, 0, list.get(i).toString());
				// 将定义好的单元格添加到工作表中
				sheet.addCell(label);
			}
			
			List<CusInventroy> cList =new ArrayList<CusInventroy>();
			c = new CusInventroy();
//			c.setIsDownLoad("Y");
			cList =cusInventroyService.searchCusInventroyListNoPage(c);
            
			
			for (int i = 0; i < cList.size(); i++) {
				// Label(x,y,z)其中x代表单元格的第x+1列，第y+1行, 单元格的内容是y
				// 在Label对象的子对象中指明单元格的位置和内容
				label = new Label(0, i+1, cList.get(i).getSales_org());
				label2 = new Label(1, i+1, cList.get(i).getSt_party());
				label3 =new Label(2,i+1,cList.get(i).getCus_name());
				label4 = new Label(3, i+1, cList.get(i).getSender_date());
				label5 = new Label(4, i+1, cList.get(i).getDoc_identifier());
				label6 =new Label(5,i+1, cList.get(i).getDoc_id());
				label7 =new Label(6,i+1, cList.get(i).getPart_name());
				label8 = new Label(7, i+1, String.valueOf(cList.get(i).getQuantity()));
				label9 = new Label(8, i+1, cList.get(i).getPrice_basis());
				label10 = new Label(9, i+1, cList.get(i).getMaterial_id());
				label11 = new Label(10, i+1, String.valueOf(cList.get(i).getPrice()));
				// 将定义好的单元格添加到工作表中
				sheet.addCell(label);
				sheet.addCell(label2);
 				sheet.addCell(label3);
 				sheet.addCell(label4);
				sheet.addCell(label5);
				sheet.addCell(label6);
				sheet.addCell(label7);
				sheet.addCell(label8);
				sheet.addCell(label9);
				sheet.addCell(label10);
				sheet.addCell(label11);
			}
			wwb.write(); // 写入数据
			wwb.close(); // 关闭文件
			
			display(source,"inventory.xls",ServletActionContext.getResponse());
			
		} catch (Exception e) {
			this.setFailMessage("Customer_inventroy模版导出出错！");
		}			
	}
	 
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
	
	
	

	public long getFile_id() {
		return file_id;
	}

	public void setFile_id(long file_id) {
		this.file_id = file_id;
	}

	public String getFile_url() {
		return file_url;
	}

	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}

	public ICusInventroyService getCusInventroyService() {
		return cusInventroyService;
	}

	public void setCusInventroyService(ICusInventroyService cusInventroyService) {
		this.cusInventroyService = cusInventroyService;
	}

	public CusInventroy getC() {
		return c;
	}

	public void setC(CusInventroy c) {
		this.c = c;
	}

	public List<CusInventroy> getcList() {
		return cList;
	}

	public void setcList(List<CusInventroy> cList) {
		this.cList = cList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}



	public List<Integer> getIdList() {
		return idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}

	public String getFile_id_str() {
		return file_id_str;
	}

	public void setFile_id_str(String file_id_str) {
		this.file_id_str = file_id_str;
	}

	public String getSt_party() {
		return st_party;
	}

	public CmsTbDict getCmsTbDict() {
		return cmsTbDict;
	}

	public void setCmsTbDict(CmsTbDict cmsTbDict) {
		this.cmsTbDict = cmsTbDict;
	}

	public IDictService getDictService() {
		return dictService;
	}

	public void setDictService(IDictService dictService) {
		this.dictService = dictService;
	}

	public List<CustomerUser> getUserList() {
		return userList;
	}

	public void setUserList(List<CustomerUser> userList) {
		this.userList = userList;
	}

	public void setSt_party(String st_party) {
		this.st_party = st_party;
	}

	public String getPart_name() {
		return part_name;
	}

	public void setPart_name(String part_name) {
		this.part_name = part_name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}

	public IProductService getProductService() {
		return productService;
	}

	public void setProductService(IProductService productService) {
		this.productService = productService;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public String getCus_name() {
		return cus_name;
	}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}

	
	
	public CustomerUser getCusUser() {
		return cusUser;
	}

	public void setCusUser(CustomerUser cusUser) {
		this.cusUser = cusUser;
	}

	public List<CustomerUser> getCusUserList() {
		return cusUserList;
	}

	public void setCusUserList(List<CustomerUser> cusUserList) {
		this.cusUserList = cusUserList;
	}

	public ICustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomerService customerService) {
		this.customerService = customerService;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	
	//2007
private String findOrderExcelXlsx(String path) {
	String reMessage = "";
	StringBuilder contentResult = new StringBuilder();
	List<CusInventroy> cList = new ArrayList<CusInventroy>();
	try {
		
		FileInputStream file = new FileInputStream(path);
	 	XSSFWorkbook xwb = new XSSFWorkbook(file);
	 	XSSFSheet sheet = xwb.getSheetAt(0);
		// 定义 row、cell
		XSSFRow row;
		// 循环输出表格中的内容
		if(sheet.getPhysicalNumberOfRows()<=1){
			return  "导入的Excel为空！";
		}
		System.out.println("表格总行数"+sheet.getPhysicalNumberOfRows());
		System.out.println("第一行行号"+sheet.getFirstRowNum());
		System.out.println("最后一行行号"+sheet.getLastRowNum());
		
		long fileId= cusInventroyService.getFileId();//file_id
		
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			if(row==null){//空行跳过
				continue;
			}
			
			
			
			CusInventroy cusInventroy = new CusInventroy();
			
			XSSFCell cell0 = row.getCell(0);
			if(cell0!=null){	
				cell0.setCellType(Cell.CELL_TYPE_STRING);
				cusInventroy.setSales_org(cell0.getStringCellValue().trim());
			}
			
			
			XSSFCell cell1 = row.getCell(1);
			if(cell1!=null){
				cell1.setCellType(Cell.CELL_TYPE_STRING);
				cusInventroy.setSt_party(cell1.getStringCellValue());
			}
			
			XSSFCell cell2 = row.getCell(2);
			if(cell2!=null){		
				cell2.setCellType(Cell.CELL_TYPE_STRING);
				cusInventroy.setCus_name(cell2.getStringCellValue().trim());
			}
			
			XSSFCell cell3 = row.getCell(3);
			if(cell3!=null){
				
				try {
					Date ss1 = cell3.getDateCellValue();
					cell3.setCellType(Cell.CELL_TYPE_STRING);
					String ss = cell3.getStringCellValue().trim();
					if (ss.length() <=5){
						cusInventroy.setSender_date(DateUtil.getDateTime(ss1, "yyyyMMdd"));
					}else{
						Date dd = DateUtil.getDateFromStr(DateUtil.FormatDate(ss));
							
						System.out.println(DateUtil.getDateTime(dd, "yyyyMMdd"));
						cusInventroy.setSender_date(DateUtil.getDateTime(dd, "yyyyMMdd"));
					}
				} catch (Exception e) {
					cell3.setCellType(Cell.CELL_TYPE_STRING);
					String ss = cell3.getStringCellValue().trim();
					cusInventroy.setSender_date(ss);
				}
				
//				cell3.setCellType(Cell.CELL_TYPE_STRING);
//				cusInventroy.setSender_date(cell3.getStringCellValue().trim());
			}
				
			
			XSSFCell cell4 = row.getCell(4);
			if(cell4!=null){
				cell4.setCellType(Cell.CELL_TYPE_STRING);
				cusInventroy.setDoc_identifier(cell4.getStringCellValue().trim());
			}
			
			XSSFCell cell5 = row.getCell(5);
			if(cell5!=null){
				cell5.setCellType(Cell.CELL_TYPE_STRING);
				cusInventroy.setDoc_code(cell5.getStringCellValue().trim());
			}

			XSSFCell cell6 = row.getCell(6);
			if(cell6!=null){
				cell6.setCellType(Cell.CELL_TYPE_STRING);
				//从物料表查询一下物料信息
				cusInventroy.setPart_name(cell6.getStringCellValue().trim());
				com.jingtong.platform.product.pojo.Product  pro = cusInventroyService.getProductInfo(cusInventroy);
				if (pro==null)
					cusInventroy.setPart_name(cell6.getStringCellValue().trim());
				else
					cusInventroy.setPart_name(pro.getMaterial_name());
			}
				
			XSSFCell cell7 = row.getCell(7);
			if(cell7!=null){	
				cell7.setCellType(Cell.CELL_TYPE_STRING);
				cusInventroy.setQuantity(cell7.getStringCellValue().trim());
			}
			
			XSSFCell cell8 = row.getCell(8);
			if(cell8!=null){		
				cell8.setCellType(Cell.CELL_TYPE_STRING);
				cusInventroy.setPrice_basis(cell8.getStringCellValue().trim());
			}
			
			XSSFCell cell9 = row.getCell(9);
			if(cell9!=null){
				cell9.setCellType(Cell.CELL_TYPE_STRING);
				cusInventroy.setCurrency(cell9.getStringCellValue().trim());
			}
			
			XSSFCell cell10 = row.getCell(10);
			if(cell10!=null){
				cell10.setCellType(Cell.CELL_TYPE_STRING);
				try {
					cell10.setCellType(Cell.CELL_TYPE_STRING);
					cusInventroy.setPrice(String.valueOf(Double.valueOf(cell10.getStringCellValue().trim())));
				} catch (Exception e) {
					cusInventroy.setPrice(cell10.getStringCellValue().trim());
				}
			
			
				cusInventroy.setFile_id(fileId);
				cusInventroy.setSynchronization_user(this.getUser().getUserId());
				cusInventroy.setStatus("9");
				cusInventroy.setFile_url(file_url);
				
			}
			
			cList.add(cusInventroy);
			
		}
		
										
		
		long result = 1;
		for (CusInventroy cusInventroy : cList) {
				result = cusInventroyService.createCusInventroy(cusInventroy);
		}
		cusInventroyService.checkEDI();
		if (result>0){
			 reMessage ="Success !";
		} else {
			 reMessage ="failed !";
		}
		
	} catch (Exception e) {
		System.out.println(e.getMessage());
		return reMessage = "failed (导入内容格式有误)！";
	}

	return reMessage;
}


	/**
	* 
	* 
	* @param path
	* @return
	*/
	//2003
	private  String findOrderExcelXls(String path) {

			// 导入数据
			String Message;
 			FileInputStream fileIn = null;
			Workbook rwb = null;
			SimpleDateFormat in = new SimpleDateFormat("dd/MM/yyyy");
 			List<CusInventroy> cList = new ArrayList<CusInventroy>();
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
					return "导入的Excel为空！";
				} else {
					long fileId= cusInventroyService.getFileId();
					for (int i = 1; i < actualRows; i++) {
						
						
						
						
						CusInventroy cusInventroy;
						cusInventroy = new CusInventroy();
						
						cusInventroy.setSales_org(rs.getCell(0, i).getContents().trim());
						cusInventroy.setSt_party(rs.getCell(1, i).getContents().trim());
						cusInventroy.setCus_name(rs.getCell(2, i).getContents().trim());
						cusInventroy.setSender_date(rs.getCell(3, i).getContents().trim());
						cusInventroy.setDoc_identifier(rs.getCell(4, i).getContents().trim());
						cusInventroy.setDoc_code(rs.getCell(5, i).getContents().trim());
						
						//从物料表查询一下物料信息
						cusInventroy.setPart_name(rs.getCell(6, i).getContents().trim());
						com.jingtong.platform.product.pojo.Product  pro = cusInventroyService.getProductInfo(cusInventroy);
						if (pro==null)
							cusInventroy.setPart_name(rs.getCell(6, i).getContents().trim());
						else
							cusInventroy.setPart_name(pro.getMaterial_name());
						cusInventroy.setQuantity(rs.getCell(7, i).getContents().trim());
						cusInventroy.setPrice_basis(rs.getCell(8, i).getContents().trim());	
						cusInventroy.setCurrency(rs.getCell(9, i).getContents().trim());
						jxl.Cell cell = rs.getCell(10,i);
						if(cell.getType() == CellType.NUMBER){ 
							cusInventroy.setPrice(String.valueOf(((NumberCell)cell).getValue()));
	                    } else if(cell.getType() == CellType.LABEL){ 
	                    	cusInventroy.setPrice(rs.getCell(10, i).getContents().trim());
	                    }
						
							
						
							cusInventroy.setFile_id(fileId);
							cusInventroy.setSynchronization_user(this.getUser().getUserId());
							cusInventroy.setStatus("9");
							cusInventroy.setFile_url(file_url);
						
						
						cList.add(cusInventroy);
					}
				
				long result = 0;
				for (CusInventroy cusInventroy : cList) {
					result = cusInventroyService.createCusInventroy(cusInventroy);
				}
				cusInventroyService.checkEDI();
				if (result>0){
					Message = "Success !";
				} else {
					Message = "failed !";
				}
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				 
				return "Excel模板错误！";
			} catch (Exception e) {
				 
				return "failed (导入内容格式有误)！";
			}

			return Message;
		}
	

	// 判断03 or 07
	public  String findOrderExcel() {
		String result;
		this.setSuccessMessage("");
		this.setFailMessage("");
		if (ExcelUtil.getExcelStyle(path).intValue() == 1) {
		  result = findOrderExcelXls(uploadFile);
		  if ("Success !".equals(result)) {
			  this.setSuccessMessage("Success!");
		  }else{
			  this.setFailMessage(result);
		  }
		  return RESULT_MESSAGE;
		} else if (ExcelUtil.getExcelStyle(path).intValue() == 2){
	 	  result = findOrderExcelXlsx(uploadFile);
	 	  if ("Success !".equals(result)) {
			  this.setSuccessMessage("Success!");
		  }else{
			  this.setFailMessage(result);
		  }
		  return RESULT_MESSAGE;
		}else{
			this.setFailMessage("failed (导入内容格式有误)！");
			return RESULT_MESSAGE;		
		} 
	}

//  EDI check
	public String  checkEDI() throws Exception{
		String Message = cusInventroyService.checkEDI();
		//1未检查 0失败 3成功
		this.setSuccessMessage("Success!");
		return RESULT_MESSAGE;
	}
	
	//报表数据导出
		public void downloadExcelModelForBb(){
			try{
		   		List<String> list=new ArrayList<String>();
		   		list.add("BOOK PART");
		   		list.add("MATERIAL");
		   		list.add("INVENTORY DATE");
				list.add("INVENTORY QUANTITY");
				list.add("INV VALUE PB CURR");
				list.add("INV VALUE PB CURR TOTAL");
				list.add("INV VALUE PB_USD");
				list.add("INV VALUE PB_USD TOTAL");
				list.add("UNIT COST PB CURR");
				list.add("UNIT COST PB CURR TOTAL");
				list.add("UNIT COST SENT");
				list.add("UNIT COST SENT TOTAL");
				list.add("UNIT OF MEASURE");
				list.add("PRICEBOOK CURRENCY");
				list.add("Price Unit_SENT");
				list.add("Consolidated Account Description");
				list.add("SOLD_TO");
				
				
				
				
				
		   	    
				File source = new File("Inventroy.xls");
				WritableWorkbook wwb = Workbook.createWorkbook(source);
				WritableSheet sheet = wwb.createSheet("Inventroy", 0);
				Label label0 = null;
				Label label1 = null;
				Label label2=null;
				Label label3=null;
				Label label4 = null;
				Label label5=null;
				Label label6=null;
				Label label7=null;
				Label label8=null;
				Label label9=null;
				Label label10=null;
				Label label11=null;
				Label label12 = null;
				Label label13 = null;
				Label label14 = null;
				Label label15 = null;
				Label label16 = null;
				

				
				for (int i = 0; i < list.size(); i++) {
					// Label(x,y,z)其中x代表单元格的第x+1列，第y+1行, 单元格的内容是y
					// 在Label对象的子对象中指明单元格的位置和内容
					label0 = new Label(i, 0, list.get(i).toString());
					// 将定义好的单元格添加到工作表中
					sheet.addCell(label0);
				}
				List<CusInventroy> cList =new ArrayList<CusInventroy>();
				c = new CusInventroy();
				c.setStart(getStart());
				c.setEnd(getEnd());
				c.setSort("synchronization_time");
				c.setDir("desc");
				if (StringUtils.isNotEmpty(part_name)
						&& StringUtils.isNotEmpty(part_name.trim())) {
					try {
						part_name = java.net.URLDecoder.decode(part_name,"UTF-8");
						part_name = part_name.toUpperCase();
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
				c.setPart_name(part_name);
				if (StringUtils.isNotEmpty(cus_name)
						&& StringUtils.isNotEmpty(cus_name.trim())) {
					try {
						cus_name = java.net.URLDecoder.decode(cus_name,"UTF-8");
						cus_name = cus_name.toUpperCase();
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
				c.setCus_name(cus_name);
				c.setStatus(status);
				if (null != start_timeString && !"".equals(start_timeString))
					c.setStart_time(DateUtil.getDateFromStr(start_timeString));
				if (null != end_timeString && !"".equals(end_timeString))
					c.setEnd_time(DateUtil.getDateFromStr(end_timeString));
				
				CmsTbDict searchCmsTbDict = new CmsTbDict();
				searchCmsTbDict.setItemId(Long.valueOf("14213"));
				cmsTbDict = dictService.getCmsTbDict(searchCmsTbDict);
				double rate = Double.valueOf(cmsTbDict.getItemValue());
				
					cList = cusInventroyService.searchCusInventroyListForBAll(c);
					System.out.println(cList.size());
					for (CusInventroy c : cList) {
						c.setUnit("PC");

					//保留4位小数
					BigDecimal b=new BigDecimal(Double.valueOf(c.getPrice())/Double.valueOf(c.getPrice_basis()));  
					double f1 = b.setScale(4,   BigDecimal.ROUND_HALF_UP).doubleValue();  
					c.setPrice(String.valueOf(f1));
						
						
					if(c.getCurrency().equals("EUR")){
						
						//保留4位小数
						BigDecimal b2=new BigDecimal((Double.valueOf(c.getPrice())/rate));  
						double f2 = b.setScale(4,   BigDecimal.ROUND_HALF_UP).doubleValue();  
						c.setPrice_USD(String.valueOf(f2));
//						c.setPrice_USD(String.valueOf((Double.valueOf(c.getPrice())/rate)));
					}else{
						c.setPrice_USD(c.getPrice());
					}
					c.setPrice1(c.getPrice());
					c.setPrice2(c.getPrice());
					  
					//大数字防止科学计数法
					java.text.NumberFormat nf = java.text.NumberFormat.getInstance();   
					nf.setGroupingUsed(false);
					Double d = (Double.valueOf(c.getPrice()))*(Double.valueOf(c.getQuantity()));
					c.setPrice_total(String.valueOf(nf.format(d)));
					System.out.println("d:="+nf.format(d));   
					
					Double e = (Double.valueOf(c.getPrice_USD()))*(Double.valueOf(c.getQuantity()));
					c.setPrice_USD_total(String.valueOf(nf.format(d)));
					System.out.println("e:="+nf.format(e)); 
					
					Double f = (Double.valueOf(c.getPrice1()))*(Double.valueOf(c.getQuantity()));
					c.setPrice1_total(String.valueOf(nf.format(f)));
					System.out.println("f:="+nf.format(f)); 
					
					Double g = (Double.valueOf(c.getPrice2()))*(Double.valueOf(c.getQuantity()));
					c.setPrice2_total(String.valueOf(nf.format(g)));
					System.out.println("g:="+nf.format(g)); 
					
//					c.setPrice_total(String.valueOf((Double.valueOf(c.getPrice()))*(Double.valueOf(c.getQuantity()))));
//					c.setPrice_USD_total(String.valueOf((Double.valueOf(c.getPrice_USD()))*(Double.valueOf(c.getQuantity()))));
//					c.setPrice1_total(String.valueOf((Double.valueOf(c.getPrice1()))*(Double.valueOf(c.getQuantity()))));
//					c.setPrice2_total(String.valueOf((Double.valueOf(c.getPrice2()))*(Double.valueOf(c.getQuantity()))));
				  } 
				for (int i = 0; i < cList.size(); i++) {
					// Label(x,y,z)其中x代表单元格的第x+1列，第y+1行, 单元格的内容是y
					// 在Label对象的子对象中指明单元格的位置和内容
					
					label0 = new Label(0, i+1, cList.get(i).getPart_name());
					label1 = new Label(1, i+1, cList.get(i).getMaterial_id());
					label2 = new Label(2, i+1, cList.get(i).getSender_date());
					label3 = new Label(3, i+1, cList.get(i).getQuantity());
					label4 = new Label(4, i+1, cList.get(i).getPrice());
					label5 = new Label(5, i+1, cList.get(i).getPrice_total());
					label6 = new Label(6, i+1, cList.get(i).getPrice_USD());
					label7 = new Label(7, i+1, cList.get(i).getPrice_USD_total());
					label8 = new Label(8, i+1, cList.get(i).getPrice1());
					label9 = new Label(9, i+1, cList.get(i).getPrice1_total());
					label10 =new Label(10,i+1, cList.get(i).getPrice2());
					label11 = new Label(11, i+1,cList.get(i).getPrice2_total());
					label12 = new Label(12, i+1, cList.get(i).getUnit());
					label13 = new Label(13, i+1, cList.get(i).getCurrency());
					label14 =new Label(14, i+1, cList.get(i).getPrice_basis());
					label15 = new Label(15, i+1,cList.get(i).getCus_name());
					label16 = new Label(16, i+1, cList.get(i).getSt_party());
					
					
					// 将定义好的单元格添加到工作表中
					sheet.addCell(label0);
					sheet.addCell(label1);
					sheet.addCell(label2);
	 				sheet.addCell(label3);
	 				sheet.addCell(label4);
					sheet.addCell(label5);
					sheet.addCell(label6);
					sheet.addCell(label7);
					sheet.addCell(label8);
					sheet.addCell(label9);
					sheet.addCell(label10);
					sheet.addCell(label11);
					sheet.addCell(label12);
					sheet.addCell(label13);
					sheet.addCell(label14);
					sheet.addCell(label15);
					sheet.addCell(label16);
					
				}
				wwb.write(); // 写入数据
				wwb.close(); // 关闭文件
				
				display(source,"Inventroy.xls",ServletActionContext.getResponse());	
			} catch (Exception e) {
				this.setFailMessage("Inventroy报表导出出错！");
			}			
		}
	
		
		
		
		//单条数据导出
				public void downloadExcelModelForOne(){
					try{
				   		List list=new ArrayList();
				   		list.add("Status");
				   		list.add("ERROR CODES");
				   		list.add("**SO (Sales Org.)");
				   		list.add("Sold to HQ");
				   		list.add("Dist. Sold to (Distributor num)");
						list.add("Monthly closing date");
						list.add("Idoc num");
						list.add("Seg num");
						list.add("Book Part");
						list.add("Inventory Qty");
						
						list.add("PriUnit");
						list.add("Currency");
						list.add("Disti Unit Cost");//12

						
				   	    
						File source = new File("Inventroy.xls");
						WritableWorkbook wwb = Workbook.createWorkbook(source);
						WritableSheet sheet = wwb.createSheet("Inventroy", 0);
						Label label0 = null;
						Label label1 = null;
						Label label2=null;
						Label label3=null;
						Label label4 = null;
						Label label5=null;
						Label label6=null;
						Label label7=null;
						Label label8=null;
						Label label9=null;
						Label label10=null;
						Label label11=null;
						Label label12=null;
						
						
						

						
						for (int i = 0; i < list.size(); i++) {
							// Label(x,y,z)其中x代表单元格的第x+1列，第y+1行, 单元格的内容是y
							// 在Label对象的子对象中指明单元格的位置和内容
							label0 = new Label(i, 0, list.get(i).toString());
							// 将定义好的单元格添加到工作表中
							sheet.addCell(label0);
						}
						List<CusInventroy> cList =new ArrayList<CusInventroy>();
						
						c = new CusInventroy();
						c.setStart(getStart());
						c.setEnd(getEnd());
						c.setSort("synchronization_time");
						c.setDir("desc");
						if (StringUtils.isNotEmpty(part_name)
								&& StringUtils.isNotEmpty(part_name.trim())) {
							try {
								part_name = java.net.URLDecoder.decode(part_name,"UTF-8");
								part_name = part_name.toUpperCase();
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}
						}
						c.setPart_name(part_name);
						c.setFile_id(file_id);
						if (StringUtils.isNotEmpty(cus_name)
								&& StringUtils.isNotEmpty(cus_name.trim())) {
							try {
								cus_name = java.net.URLDecoder.decode(cus_name,"UTF-8");
								cus_name = cus_name.toUpperCase();
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}
						}
						c.setCus_name(cus_name);
						c.setStatus(status);
						if (null != start_timeString && !"".equals(start_timeString))
							c.setStart_time(DateUtil.getDateFromStr(start_timeString));
						if (null != end_timeString && !"".equals(end_timeString))
							c.setEnd_time(DateUtil.getDateFromStr(end_timeString));
						
//						total = cusInventroyService.getCusInventroyListCountById(c);
//						if (total > 0) {
							cList = cusInventroyService.searchCusInventroyListByIdForOne(c);
//						}
						
						for (int i = 0; i < cList.size(); i++) {
							// Label(x,y,z)其中x代表单元格的第x+1列，第y+1行, 单元格的内容是y
							// 在Label对象的子对象中指明单元格的位置和内容
							
							
							
							label0 = new Label(0, i+1, cList.get(i).getStatus());
							label1 = new Label(1, i+1, cList.get(i).getTips());
							label2 = new Label(2, i+1, cList.get(i).getSales_org());
							label3 = new Label(3, i+1, cList.get(i).getSt_party());
							label4 = new Label(4, i+1, cList.get(i).getCus_name());
							label5 = new Label(5, i+1, cList.get(i).getSender_date());
							label6 = new Label(6, i+1, cList.get(i).getDoc_identifier());
							label7 = new Label(7, i+1, cList.get(i).getDoc_code());
							label8 = new Label(8, i+1, cList.get(i).getPart_name());
							label9 = new Label(9, i+1, cList.get(i).getQuantity());
							label10 = new Label(10, i+1, cList.get(i).getPrice_basis());
							label11 = new Label(11, i+1, cList.get(i).getCurrency());
							label12 = new Label(12, i+1, cList.get(i).getPrice());
							
							
							
							
							
							// 将定义好的单元格添加到工作表中
							sheet.addCell(label0);
							sheet.addCell(label1);
							sheet.addCell(label2);
			 				sheet.addCell(label3);
			 				sheet.addCell(label4);
							sheet.addCell(label5);
							sheet.addCell(label6);
							sheet.addCell(label7);
							sheet.addCell(label8);
							sheet.addCell(label9);
							sheet.addCell(label10);
							sheet.addCell(label11);
							sheet.addCell(label12);
							
							
						}
						wwb.write(); // 写入数据
						wwb.close(); // 关闭文件
						
						display(source,"Inventroy.xls",ServletActionContext.getResponse());	
					} catch (Exception e) {
						this.setFailMessage("Inventroy报表导出出错！");
					}			
				}
				
				//重置status=9
				public String resetCusInventroy() {
					c = new CusInventroy();
					c.setId(Long.valueOf(id));
					cusInventroyService.resetCusInventroy(c);
					this.setSuccessMessage("Success!");
					return RESULT_MESSAGE;
				}
				
				//重置status=3
				public String approvedCusInventroy() {
					c = new CusInventroy();
					String [] s = id.split(",");
					for(String s1:s){
						c.setId(Long.valueOf(s1));
						c.setFile_id(file_id);
						cusInventroyService.approvedCusInventroy(c);
					}
					this.setSuccessMessage("Success!");
					return RESULT_MESSAGE;
				}
				
				//重置status=0
				public String rejectCusInventroy() {
					c = new CusInventroy();
					String [] s = id.split(",");
					for(String s1:s){
						c.setId(Long.valueOf(s1));
						c.setFile_id(file_id);
						cusInventroyService.rejectCusInventroy(c);
					}
					this.setSuccessMessage("Success!");
					return RESULT_MESSAGE;
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
		  
		  /*
		   * weeklyMonthly xcfeng 20161227
		   */
	  public String weeklyMonthly() throws Exception {
			c = new CusInventroy();
			String [] s = file_id_str.split(",");
			for(String s1:s){
				c.setFile_id(Long.valueOf(s1));
				c.setFrequency(frequencyMark);
				cusInventroyService.updateFrequencyMarkCusInventroy(c);
			}
			this.setSuccessMessage("Success!");
			return RESULT_MESSAGE;
	  }
}



