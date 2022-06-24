package com.jingtong.platform.priceRule.action;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.CellType;
import jxl.DateCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.crystaldecisions.enterprise.ocaframework.idl.OCA.OCAs.interface_num;
import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.framework.util.DateUtil;
import com.jingtong.platform.framework.util.ExcelUtil;
import com.jingtong.platform.framework.util.StockUtil;
import com.jingtong.platform.order.pojo.OrderDetail;
import com.jingtong.platform.org.pojo.Borg;
import com.jingtong.platform.priceRule.pojo.PriceRule;
import com.jingtong.platform.priceRule.service.IPriceRuleService;
import com.jingtong.platform.priceRule.pojo.PriceRule;
import com.jingtong.platform.product.pojo.Product;

public class PriceRuleAction  extends BaseAction{
	private PriceRule pr;
	private List<PriceRule> prList;
	private IPriceRuleService priceRuleService;
	private String material_id;
	private String material_name;
	private String state;
	private int total;
	private String id;
	private String uploadFile;
	private String price_type;
	private String customer_name;
	private List<Borg> orgList;
	private String path;
	private String latest_time;
	
	public String getLatest_time() {
		return latest_time;
	}
	public void setLatest_time(String latest_time) {
		this.latest_time = latest_time;
	}
	public String toSearchPriceRule(){
		return "toSearchPriceRule";
	}
	public String toAuditPriceRule(){
		return "toAuditPriceRule";
	}
	public String toControlPriceRule(){
		return "toControlPriceRule";
	}
	
	public String toViewPriceRule(){
		pr = new PriceRule();
		pr.setId(Long.valueOf(id));
		pr = priceRuleService.getPriceRuleById(pr);
		return "toViewPriceRule";
	}
	public String toSyncPrice(){
		return "toSyncPrice";
	}
	
	public String toCreatePriceRule(){
		return "toCreatePriceRule";
	}
	
	public String toUpdatePriceRule(){
		pr = new PriceRule();
		pr.setId(Long.valueOf(id));
		pr = priceRuleService.getPriceRuleById(pr);
		return "toCreatePriceRule";
	}
	
	
	
	/**
	 * 查询所有信息
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "prList", include = { "id","org_id","office_id","material_id","material_name","materal_type",
			"basic_type","material_desc","currency_code","price_type","customer_code",
			"sale_price","start_date","end_date","start_dateStr","end_dateStr",
			"state","remark","isDeleted","create_time","create_userId",
		    "latest_time","latest_userId","org_code","perUnit","customer_name"},
			    total = "total")
	public String getPriceRuleList(){
		try {			
			pr = new PriceRule();
			pr.setStart(getStart());
			pr.setEnd(getEnd());
			pr.setSort("create_time");
			pr.setDir("desc");
			pr.setMaterial_id(material_id);
			pr.setPrice_type(price_type);
			if (StringUtils.isNotEmpty(customer_name)
					&& StringUtils.isNotEmpty(customer_name.trim())) {
				try {
					customer_name=java.net.URLDecoder.decode(customer_name,"UTF-8");
					customer_name = customer_name.toUpperCase();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			pr.setCustomer_name(customer_name);
	 		if (StringUtils.isNotEmpty(material_name)
					&& StringUtils.isNotEmpty(material_name.trim())) {
				try {
					material_name=java.net.URLDecoder.decode(material_name,"UTF-8");
					material_name = material_name.toUpperCase();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			pr.setMaterial_name(material_name);
			//pr.setState(Integer.valueOf(state));
			total = priceRuleService.getPriceRuleListCount(pr);
			if(total > 0){
				prList = priceRuleService.getPriceRuleList(pr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON;
	}

	
	/**
	 * 添加信息
	 * @return
	 */
	public String createPriceRule() {		
    	this.setSuccessMessage("");
    	this.setFailMessage("");
    	pr.setCreate_userId(this.getUser().getUserId());
    	pr.setStart(0);
    	pr.setEnd(100);
    	prList = priceRuleService.getControlPriceRuleList(pr);
    	if(prList==null || prList.size()<=0){
	    	if("".equals(pr.getPerUnit())){
	    		pr.setPerUnit("1");
	    	}
			long result = priceRuleService.createPriceRule(pr);
			if(result>0){
				this.setSuccessMessage("Success!");
			}else{
				this.setFailMessage("Failed!");
			}
		}else {
			this.setFailMessage("Aready Exist!<br>"+prList.get(0).getStart_dateStr()+"--"+prList.get(0).getEnd_dateStr()+"<br>perunit:"+prList.get(0).getPerUnit()+"   Price:"+prList.get(0).getSale_price());
		}
 		return RESULT_MESSAGE;
	}
	
	
	
	/**
	 * 验证该编码名是否被占用
	 * @param a
	 * @return
	 */
	private Boolean checkPriceRule(PriceRule aa) {
		Boolean b = true;
		int n = priceRuleService.getPriceRuleListCount(aa);
		if(n>=1){
			b = false;
		}
		return b;
	}
	
	/**
	 * 添加信息
	 * @return
	 */
	public String updatePriceRule() {		
    	this.setSuccessMessage("");
    	this.setFailMessage("");
    	pr.setLatest_userId(this.getUser().getUserId());
//    	PriceRule aa = new PriceRule(); 
//    	aa.setEnd_customer_name(end_customer_name);
 //   	Boolean flag = chprkEnd_customer_name(aa);
//    	if(flag){
			int result = priceRuleService.updatePriceRule(pr);
			if(result>0){
				this.setSuccessMessage("Success!");
			}else{
				this.setFailMessage("Failed!");
			}
//		}else {
//			this.setFailMessage("已经存在该编码,请重新输入编码");
//		}
 		return RESULT_MESSAGE;
	}
	
	
	/**
	 * 审核信息
	 * @return
	 */
	public String auditPriceRule(){
		this.setFailMessage("");
		this.setSuccessMessage("");
		int i=priceRuleService.auditPriceRule(pr);
    	if(i>0){
    		this.setSuccessMessage("Success !");
    	}else{
    		this.setFailMessage("failed !");
    	}
    	return RESULT_MESSAGE;
	}
	
	
	public String deletePriceRule(){
		this.setFailMessage("");
		this.setSuccessMessage("");
		pr=new PriceRule();
		pr.setId(Long.valueOf(id));
		int i=priceRuleService.deletePriceRule(pr);
    	if(i>0){
    		this.setSuccessMessage("Success !");
    	}else{
    		this.setFailMessage("failed !");
    	}
    	return RESULT_MESSAGE;
	}
	
	
	
	public String importExcel(){
		return "importExcel";
	}
	
	/**
	 * 导入excel数据
	 * @return
	 * @throws ParseException 
	 */
	public String importData() throws ParseException{
			// 导入数据
 			FileInputStream fileIn = null;
			Workbook rwb = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
 			List<PriceRule> pList = new ArrayList<PriceRule>();
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
							contentResult.append("物料编码 is not completed yet!");
							break;
						}
						if ("".equals(rs.getCell(1, i).getContents().trim())) {
							if (contentResult.length() > 0) {
								contentResult.append("</br>");
							}
							contentResult.append("销售大区 is not completed yet!");
							break;
						}
						if ("".equals(rs.getCell(2, i).getContents().trim())) {
							if (contentResult.length() > 0) {
								contentResult.append("</br>");
							}
							contentResult.append("销售办公室 is not completed yet!");
							break;
						}
						if ("".equals(rs.getCell(3, i).getContents().trim())) {
							if (contentResult.length() > 0) {
								contentResult.append("</br>");
							}
							contentResult.append("货币 is not completed yet!");
							break;
						}
						
						if ("".equals(rs.getCell(4, i).getContents().trim())) {
							if (contentResult.length() > 0) {
								contentResult.append("</br>");
							}
							contentResult.append("价格类型 is not completed yet!");
							break;
						}
						if ("".equals(rs.getCell(5, i).getContents().trim())) {
							if (contentResult.length() > 0) {
								contentResult.append("</br>");
							}
							contentResult.append("价格 is not completed yet!");
							break;
						}
						if ("".equals(rs.getCell(6, i).getContents().trim())) {
							if (contentResult.length() > 0) {
								contentResult.append("</br>");
							}
							contentResult.append("开始日期 is not completed yet!");
							break;
						}
						if ("".equals(rs.getCell(7, i).getContents().trim())) {
							if (contentResult.length() > 0) {
								contentResult.append("</br>");
							}
							contentResult.append("截止日期 is not completed yet!");
							break;
						}
					}						 					
					
					if (!"".equals(contentResult.toString())) {
						this.setFailMessage(contentResult.toString());
						return RESULT_MESSAGE;
					}
					PriceRule prRule;
					for (int i = 1; i < actualRows; i++) {
						prRule = new PriceRule();
						prRule.setMaterial_id(rs.getCell(0, i).getContents().trim());
						prRule.setOrg_id(rs.getCell(1, i).getContents().trim());
						prRule.setOffice_id(rs.getCell(2, i).getContents().trim());
						prRule.setCurrency_code(rs.getCell(3, i).getContents().trim());
						prRule.setPrice_type(rs.getCell(4, i).getContents().trim());
						prRule.setSale_price(Double.valueOf(rs.getCell(5, i).getContents().trim()));
						prRule.setPerUnit("1");
						//System.out.println(rs.getCell(5, i).getContents().trim());
						if (rs.getCell(6, i).getType() == CellType.DATE) {
					        DateCell dateCell = (DateCell) rs.getCell(6, i);
					        Date date = dateCell.getDate();
					        System.out.println(date);
					        prRule.setStart_date(date);
					        String year = new SimpleDateFormat("yyyy-MM-dd").format(date);
					        System.out.println(year);
						} 
						if (rs.getCell(7, i).getType() == CellType.DATE) {
					        DateCell dateCell = (DateCell) rs.getCell(7, i);
					        Date date1 = dateCell.getDate();
					        prRule.setEnd_date(date1);
						} 
						//prRule.setStart_date(sdf.parse(rs.getCell(5, i).getContents().trim()));
						//System.out.println(prRule.getStart_date());
						//prRule.setEnd_date(sdf.parse(rs.getCell(6, i).getContents().trim()));
						pList.add(prRule);
					}
				}
				long result = 1;
				for (PriceRule priceRule : pList) {
					int n = priceRuleService.getPriceRuleListCountCmRm(priceRule);
					if (n>=1) {
						int a = priceRuleService.updatePriceRule(priceRule);
					}else {
						result = priceRuleService.createPriceRule(priceRule);						
					}
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
	
	
	/**
	 * 查询所有信息
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "prList", include = { "id","org_id","office_id","material_id","material_name","materal_type",
			"basic_type","material_desc","currency_code","price_type","customer_code",
			"sale_price","start_date","end_date","start_dateStr","end_dateStr",
			"state","remark","isDeleted","create_time","create_userId",
		    "latest_time","latest_userId","org_code","perUnit","customer_name"},
			    total = "total")
	public String getControlPriceRuleList(){
		try {			
			pr = new PriceRule();
			pr.setStart(getStart());
			pr.setEnd(getEnd());
			pr.setSort("create_time");
			pr.setDir("desc");
			pr.setMaterial_id(material_id);
			pr.setPrice_type(price_type);
			pr.setLatest_time(DateUtil.getDateTime(latest_time));
	 		if (StringUtils.isNotEmpty(material_name)
					&& StringUtils.isNotEmpty(material_name.trim())) {
				try {
					material_name=java.net.URLDecoder.decode(material_name,"UTF-8");
					material_name = material_name.toUpperCase();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			pr.setMaterial_name(material_name);
			//pr.setState(Integer.valueOf(state));
			total = priceRuleService.getControlPriceRuleListCount(pr);
			if(total > 0){
				prList = priceRuleService.getControlPriceRuleList(pr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON;
	}

	
	
	@PermissionSearch
	@JsonResult(field = "orgList", include = {"orgId", "sapOrgId", "orgName" })
	public String getOrgLists() {
		Borg borg = new Borg();
//		borg.setStart(0);
//		borg.setEnd(100);
		borg.setSort("g.org_id");
		borg.setDir("asc");
		borg.setOrgLevel(Long.valueOf(state));
//		total = priceRuleService.getOrgListCount(borg);
//		if (total != 0) {
			orgList=priceRuleService.getOrgList(borg);
//		}
		return JSON;
	}
	
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		System.out.println(sdf.parse("2016/12/31"));
	}
	
	public PriceRule getEc() {
		return pr;
	}
	public void setEc(PriceRule pr) {
		this.pr = pr;
	}
	public List<PriceRule> getEcList() {
		return prList;
	}
	public void setEcList(List<PriceRule> prList) {
		this.prList = prList;
	}
	public IPriceRuleService getPriceRuleService() {
		return priceRuleService;
	}
	public void setPriceRuleService(IPriceRuleService priceRuleService) {
		this.priceRuleService = priceRuleService;
	}

	public PriceRule getPr() {
		return pr;
	}
	public void setPr(PriceRule pr) {
		this.pr = pr;
	}
	public List<PriceRule> getPrList() {
		return prList;
	}
	public void setPrList(List<PriceRule> prList) {
		this.prList = prList;
	}
	public String getMaterial_id() {
		return material_id;
	}
	public void setMaterial_id(String material_id) {
		this.material_id = material_id;
	}
	public String getMaterial_name() {
		return material_name;
	}
	public void setMaterial_name(String material_name) {
		this.material_name = material_name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getPrice_type() {
		return price_type;
	}
	public void setPrice_type(String price_type) {
		this.price_type = price_type;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public List<Borg> getOrgList() {
		return orgList;
	}
	public void setOrgList(List<Borg> orgList) {
		this.orgList = orgList;
	}
	

public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

private String findOrderExcelXlsx(String path) {
	StringBuilder contentResult = new StringBuilder();
	List<PriceRule> pList = new ArrayList<PriceRule>();
	PriceRule prRule;
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
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			if(row==null){//空行跳过
				continue;
			}
			prRule = new PriceRule();
			XSSFCell cell0 = row.getCell(0);
				if(cell0!=null){	
					if(cell0.getCellType()==cell0.CELL_TYPE_NUMERIC){	
						//数字格式无法解析
						cell0.setCellType(cell0.CELL_TYPE_STRING);
							
						prRule.setMaterial_id(cell0.getStringCellValue().trim());
					}else{
						prRule.setMaterial_id(cell0.getStringCellValue().trim());
					}
				}else {
					contentResult.append("Row "+i+": 12NC is not completed yet!");
					break;
				}
			XSSFCell cell1 = row.getCell(1);
			if(cell1!=null){	
				if(cell1.getCellType()==cell1.CELL_TYPE_NUMERIC){	
					//数字格式无法解析
					cell1.setCellType(cell1.CELL_TYPE_STRING);
					prRule.setOrg_id(cell1.getStringCellValue().trim());

				}else{
					prRule.setOrg_id(cell1.getStringCellValue().trim());
				}
			}
			
			XSSFCell cell2 = row.getCell(2);
			if(cell2!=null && !"".equals(cell2.getStringCellValue().trim())){				
				prRule.setOffice_id(cell2.getStringCellValue().trim());
			}else {
				contentResult.append("Row "+i+": SalesOffice is not completed yet!");
				break;
			}
			XSSFCell cell3 = row.getCell(3);
			if(cell3!=null && !"".equals(cell3.getStringCellValue().trim())){
				prRule.setCurrency_code(cell3.getStringCellValue().trim());
			}else {
				contentResult.append("Row "+i+": Currency is not completed yet!");
				break;
			}
			XSSFCell cell4 = row.getCell(4);
			if(cell4!=null && !"".equals(cell4.getStringCellValue().trim())){				
				prRule.setPrice_type(cell4.getStringCellValue().trim());
			}else {
				contentResult.append("Row "+i+": Price Type is not completed yet!");
				break;
			}
			XSSFCell cell5 = row.getCell(5);
			if(cell5!=null){
				if(cell5.getCellType()==cell5.CELL_TYPE_NUMERIC){					
					prRule.setSale_price(cell5.getNumericCellValue());
				}else {
					contentResult.append("Row"+i+": Sale Price format error!");
					break;
				}
			}else {
				contentResult.append("Row "+i+": Sale Price is not completed yet!");
				break;
			}
			XSSFCell cell6 = row.getCell(6);
			if(cell6!=null){
				try {					
					prRule.setStart_date(cell6.getDateCellValue());
				} catch (Exception e) {
					contentResult.append("Row"+i+": Start Date format error!");
					break;
				}
			}else {
				contentResult.append("Row "+i+": Start Date is not completed yet!");
				break;
			}
			XSSFCell cell7 = row.getCell(7);
			if(cell7!=null){	
				try {					
					prRule.setEnd_date(cell7.getDateCellValue());
				} catch (Exception e) {
					contentResult.append("Row"+i+": End Date format error!");
					break;
				}
			}else {
				contentResult.append("Row "+i+": End Date is not completed yet!");
				break;
			}
			prRule.setPerUnit("1");
			prRule.setCreate_userId(this.getUser().getUserId());
			pList.add(prRule);
	}
	if (!"".equals(contentResult.toString())) {
		return contentResult.toString();
	}
	long result = 1;
	for (PriceRule priceRule : pList) {
		int n = priceRuleService.getPriceRuleListCountCmRm(priceRule);
		priceRule.setLatest_time(new Date());
		if (n>=1) {
			int a = priceRuleService.updatePriceRule(priceRule);
		}else {
			result = priceRuleService.createPriceRule(priceRule);						
		}
		if (result==0) {
			break;
		}
	}
	file.close();
	if (result>0){
		return "Success";
	} else {
		return "Failed";
	}		
	} catch (Exception e) {
		System.out.println(e.getMessage());
		return "failed (导入内容格式有误)！";
	}
}


	/**
	* 
	* 2003
	* @param path
	* @return
	*/
	private  String findOrderExcelXls(String path) {

			// 导入数据
 			FileInputStream fileIn = null;
			Workbook rwb = null;
			SimpleDateFormat in = new SimpleDateFormat("dd/MM/yyyy");
 			List<PriceRule> pList = new ArrayList<PriceRule>();
				StringBuilder contentResult = new StringBuilder();
			try {
				fileIn = new FileInputStream(uploadFile);
				rwb = Workbook.getWorkbook(fileIn);
				Sheet rs = rwb.getSheet(0);
				int column = 0;
				column = rs.getColumns();
				int actualRows = 0;
				actualRows = StockUtil.delEmptyRow(rs);
				/** 去除空行得到真实行数 **/
				if (actualRows == 0 && column == 0) {
					return "导入的Excel为空！";
				} else {
					System.out.println(actualRows);
					for (int i = 1; i < actualRows; i++) {
						if ("".equals(rs.getCell(0, i).getContents().trim())) {
							if (contentResult.length() > 0) {
								contentResult.append("</br>");
							}
							contentResult.append("Row "+i+": 12NC is not completed yet!");
							break;
						}
						if ("".equals(rs.getCell(1, i).getContents().trim())) {
							if (contentResult.length() > 0) {
								contentResult.append("</br>");
							}
							contentResult.append("Row "+i+": SalesOrg is not completed yet!");
							break;
						}
						if ("".equals(rs.getCell(2, i).getContents().trim())) {
							if (contentResult.length() > 0) {
								contentResult.append("</br>");
							}
							contentResult.append("Row "+i+": Sales Office is not completed yet!");
							break;
						}
						if ("".equals(rs.getCell(3, i).getContents().trim())) {
							if (contentResult.length() > 0) {
								contentResult.append("</br>");
							}
							contentResult.append("Row "+i+": Currency is not completed yet!");
							break;
						}
						
						if ("".equals(rs.getCell(4, i).getContents().trim())) {
							if (contentResult.length() > 0) {
								contentResult.append("</br>");
							}
							contentResult.append("Row "+i+": Price Type is not completed yet!");
							break;
						}

						if ("".equals(rs.getCell(5, i).getContents().trim())) {
							if (contentResult.length() > 0) {
								contentResult.append("</br>");
							}
							contentResult.append("Row "+i+": Sale Price is not completed yet!");
							break;
						}
						if ("".equals(rs.getCell(6, i).getContents().trim())) {
							if (contentResult.length() > 0) {
								contentResult.append("</br>");
							}
							contentResult.append("Row "+i+": StartDate is not completed yet!");
							break;
						}
						if ("".equals(rs.getCell(7, i).getContents().trim())) {
							if (contentResult.length() > 0) {
								contentResult.append("</br>");
							}
							contentResult.append("Row "+i+": EndDate is not completed yet!");
							break;
						}
					}						 					
					
					if (!"".equals(contentResult.toString())) {
						return contentResult.toString();
					}
					PriceRule prRule;
					for (int i = 1; i < actualRows; i++) {
						prRule = new PriceRule();
						prRule.setMaterial_id(rs.getCell(0, i).getContents().trim());
						prRule.setOrg_id(rs.getCell(1, i).getContents().trim());
						prRule.setOffice_id(rs.getCell(2, i).getContents().trim());
						prRule.setCurrency_code(rs.getCell(3, i).getContents().trim());
						prRule.setPrice_type(rs.getCell(4, i).getContents().trim());
						if(rs.getCell(5, i).getType() == CellType.NUMBER){
							NumberCell numberCell = (NumberCell) rs.getCell(5, i);
							double price =  numberCell.getValue();
							prRule.setSale_price(price);
						}else{
							contentResult.append("Row "+i+": Price format error!");
							break;
						}

						if (rs.getCell(6, i).getType() == CellType.DATE) {
					        DateCell dateCell = (DateCell) rs.getCell(6, i);
					        Date date = dateCell.getDate();
					        System.out.println(date);
					        prRule.setStart_date(date);
					        String year = new SimpleDateFormat("yyyy-MM-dd").format(date);
					        System.out.println(year);
						} else{
							contentResult.append("Row "+i+": StartDate format error!");
							break;
						} 
						if (rs.getCell(7, i).getType() == CellType.DATE) {
					        DateCell dateCell = (DateCell) rs.getCell(7, i);
					        Date date1 = dateCell.getDate();
					        prRule.setEnd_date(date1);
						} else{
							contentResult.append("Row "+i+": EndDate format error!");
							break;
						} 
						prRule.setPerUnit("1");
						prRule.setCreate_userId(this.getUser().getUserId());
						pList.add(prRule);
					}
				}
				if (!"".equals(contentResult.toString())) {
					return contentResult.toString();
				}
				long result = 1;
				for (PriceRule priceRule : pList) {
					int n = priceRuleService.getPriceRuleListCountCmRm(priceRule);
					priceRule.setLatest_time(new Date());
					if (n>=1) {
						List<PriceRule> listPriceRule  = priceRuleService.getPriceRuleListCmRmQm(priceRule);
						if (listPriceRule.size() > 0) {
							priceRule.setId(listPriceRule.get(0).getId());
						}
						int a = priceRuleService.updatePriceRule(priceRule);
					}else {
						result = priceRuleService.createPriceRule(priceRule);						
					}
					if (result==0) {
						break;
					}
				}
				if (result>0){
					return "Success";
				} else {
					return "Failed";
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				 
				return "Excel模板错误！";
			} catch (Exception e) {
				 
				return "failed (导入内容格式有误)！";
			}
	}

	public  String findOrderExcel() {
		String result;
		if (ExcelUtil.getExcelStyle(path).intValue() == 1) {
		  result = findOrderExcelXls(uploadFile);
		  if ("Success".equals(result)) {
			  this.setSuccessMessage("Success!");
		  }else{
			  this.setFailMessage(result);
		  }
		  return RESULT_MESSAGE;
		} else if (ExcelUtil.getExcelStyle(path).intValue() == 2){
	 	  result = findOrderExcelXlsx(uploadFile);
		  if ("Success".equals(result)) {
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

}
