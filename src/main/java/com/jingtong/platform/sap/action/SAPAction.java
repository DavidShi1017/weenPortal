package com.jingtong.platform.sap.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.sap.pojo.CuspayAccdoc;
import com.jingtong.platform.sap.pojo.NoClearMoney;
import com.jingtong.platform.sap.pojo.ProductStock;
import com.jingtong.platform.sap.pojo.ReceivePay;
import com.jingtong.platform.sap.service.SAPService;



public class SAPAction  extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2708360048296344402L;

	private SAPService sapService;
	
	private ReceivePay receivePay;
	

	public SAPService getSapService() {
		return sapService;
	}

	public void setSapService(SAPService sapService) {
		this.sapService = sapService;
	}
	private String matnr;//物料
	private String werks;//工厂
	private String lgort;//库位
 	
	public String getMatnr() {
		return matnr;
	}

	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}

	public String getWerks() {
		return werks;
	}

	public void setWerks(String werks) {
		this.werks = werks;
	}

	public String getLgort() {
		return lgort;
	}

	public void setLgort(String lgort) {
		this.lgort = lgort;
	}

	/**
	 * 产品库存可用量
	 * @param punit String	统计单位：ST或CAR(必填)
	 * @param pmatnr String	物料编码(必填)
	 * @param pwerks String	工厂编码(必填)
	 * @param plgort String	库位编码
	 * @throws IOException 
	 * @remark 参数顺序 getMaterialInventoryFromSap(String pmatnr,String pwerks,String plgort)
	 */
	public void getMaterialInventoryFromSap() throws IOException{
	   HttpServletResponse response = ServletActionContext.getResponse();
	   response.setContentType("text/html; charset=GBK");
  	   ProductStock ps = this.sapService.getMaterialInventoryFromSap(matnr,werks,lgort);
  	   PrintWriter pw = response.getWriter();
  	   if(ps==null || ps.getStocknum()==0){
  		 pw.write("000");
  	   }else{
		   pw.write(ps.getStocknum()+","+ps.getBigmg());
	//	   pw.write("10"+","+"10");
 	   }
	   pw.close();
		
	}
	
	 
	/**
	 * 同步sap 产品库存量接口
	 */
	public void getProductStockFromSap(){
		try {
			this.sapService.getMaterialInventory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getCusPlusFromSap(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.sapService.getCusPlusFromSap(sdf.parse("2015-09-20"), sdf.parse("2015-09-30"), null);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		if(this.sapService.getCusPlusFromSap(null, null, null)==true){
//			return "1";
//		}else{
//			return "0";
//		}
		return "0";
	}
	/**
	 * 自动创建客户回款会计凭证函数
	 * @return
	 */
	public void getCuspayAccdocFromSAP(){
	  /*P_BUKRS	String	公司代码
		P_KUNNR	String	客户编码
		P_DMBTR	double	金额
		P_BELNR_HK	String	现金凭证号码
		P_GJAHR_HK	String	现金凭证会计年度
		P_BUKRS_HK	String	现金凭证公司代码
		P_USNAM	String	用户名
		P_XREF1	String	回款类型*/
		HttpServletResponse response = ServletActionContext.getResponse();
		// 编制响应的格式
		response.setContentType("text/html;charset=UTF-8");
		try{
			CuspayAccdoc doc = new CuspayAccdoc();
			doc.setP_bukrs(this.receivePay.getSaleCompany());
			doc.setP_kunnr(this.receivePay.getKunnr());
			doc.setP_dmbtr(this.receivePay.getReceiveMoney());
			doc.setP_belnr_hk(this.receivePay.getSkpz());
			doc.setP_gjahr_hk(this.receivePay.getAccountYear());
			doc.setP_bukrs_hk(this.receivePay.getSaleCompany());
			doc.setP_usnam("crm测试用户");
			doc.setP_xref1(this.receivePay.getSgType());
			doc = this.sapService.getCuspayAccdocFromSAP(doc);
			if(doc != null && doc.getL_belnr() != null && !doc.getL_belnr().equals("")){
				this.receivePay.setRemark(java.net.URLDecoder.decode(this.receivePay.getRemark(),"UTF-8"));
				this.receivePay.setReceiveId(doc.getL_belnr());//回款凭证号
				this.receivePay.setStatus("0");
				this.receivePay.setSyMoney(this.receivePay.getReceiveMoney());
				this.receivePay.setCreateUser(this.getUser().getUserId());
				this.receivePay.setCreateDate(new Date());
				this.receivePay.setModifyUser(this.getUser().getUserId());
				this.receivePay.setModifyDate(new Date());
				this.receivePay.setBuzei("002");
//				this.receivePay.setReceiveDate(new SimpleDateFormat("yyyy-mm-dd").format(this.receivePay.getReceiveDate()));
				long i=this.sapService.addReceivePay(this.receivePay);//创建回款
				if(i>0){
					sapService.updateCreditByReceive(receivePay.getKunnr());
				}
				response.getWriter().print("1");
			}else{
				throw new Exception("请求SAP异常"+doc.getRetmsg());
			}
			System.out.println(doc.toString());
		}catch(Exception e){
			try {
				response.getWriter().print(e.getMessage());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * 获取未清凭证 
	 * @return
	 */
	public void getWeiqingMoneyFromSAP(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String p_bukrs = request.getParameter("p_bukrs");
		String p_gjahr = new SimpleDateFormat("yyyy").format(new Date());//暂时去当前年度，SAP有传的话去sap值"2015";
		List<NoClearMoney> noClearMoneyList  = this.sapService.getweiqingMoneyFromSAP(p_bukrs,p_gjahr);
		List<String> clearMoneyList = this.sapService.getClearMoneyList();
		int record = 1;
		String jsonArray = "[";
		for(NoClearMoney noClearMoney:noClearMoneyList){
			int flag = 1;
			for(String s : clearMoneyList){
				if(s!=null&&s.trim().equals(noClearMoney.getBelnr().trim())){
					flag = 0;
					break;
				}
			}
			if(flag==1){
					jsonArray += "{\"bukrs\":"+noClearMoney.getBukrs()+",\"belnr\":\""+noClearMoney.getBelnr()+"\",\"remark\":\""+noClearMoney.getRemark()+"\",\"dmbtr\":\""+noClearMoney.getDmbtr()+"\",\"buzei\":\""+noClearMoney.getBuzei()+"\"},";
				record++;
			}
		}
		if(jsonArray.length()>0){
			jsonArray = jsonArray.substring(0, jsonArray.length()-1);
		}
		jsonArray +="]";
		HttpServletResponse response = ServletActionContext.getResponse();
		// 编制响应的格式
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().write(jsonArray);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取回款凭证
	 * @throws Exception 
	 */
	public void getReceiveCode() throws Exception{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String belnr = request.getParameter("belnr");
		System.out.println(belnr);
		
		
		NoClearMoney noClearMoney = this.sapService.getReceiveCode(belnr);
		HttpServletResponse response = ServletActionContext.getResponse();
		// 编制响应的格式
		response.setContentType("text/html;charset=UTF-8");
		String json = "";
		if(noClearMoney != null){
			json = "{\"id\":"+noClearMoney.getId()+",\"bukrs\":\""+noClearMoney.getBukrs()+"\",\"gjahr\":\""+noClearMoney.getGjahr()+"\",\"belnr\":\""+noClearMoney.getBelnr()+"\",\"remark\":\""+noClearMoney.getRemark()+"\",\"hkont\":\""+noClearMoney.getHkont()+"\",\"budat\":\""+new SimpleDateFormat("yyyy-MM-dd").format(noClearMoney.getBudat())+"\",\"zuonr\":\""+noClearMoney.getZuonr()+"\",\"dmbtr\":"+noClearMoney.getDmbtr()+"}";
		}
		response.getWriter().write(json);
	}
	
	public void getReceivePayFromSap(){
		this.sapService.getReceivePayFromSap();
	}
	//CRM 同步信用初始化值
	public void getInitialCredit(){
		this.sapService.getInitialCredit();
	}
	//SAP凭证同步接口
	public void getZSVoucher(){
		this.sapService.getZSVoucher();
	}
	//SAP凭证同步接口
		public void getFSVoucher(){
			this.sapService.getFSVoucher();
		}
	public ReceivePay getReceivePay() {
		return receivePay;
	}

	public void setReceivePay(ReceivePay receivePay) {
		this.receivePay = receivePay;
	}
}
