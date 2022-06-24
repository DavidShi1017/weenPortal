package com.jingtong.platform.sap.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.allUser.service.IAllUserService;
import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.framework.mail.MailService;
import com.jingtong.platform.order.pojo.StarderOrder;
import com.jingtong.platform.quote.pojo.Quote;
import com.jingtong.platform.sap.dao.impl.QuoteToSapDaoImpl;
import com.jingtong.platform.sap.pojo.QuoteDetail;
import com.jingtong.platform.sap.pojo.QuoteToSap;
import com.jingtong.platform.sap.pojo.SampleOrderDetail;
import com.jingtong.platform.sap.pojo.SampleOrderToSap;
import com.jingtong.platform.sap.service.QuoteToSapService;
import com.jingtong.platform.sap.service.SAPService;
import com.jingtong.platform.sap.service.impl.QuoteToSapServiceImpl;

public class QuoteToSapAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3599908969412005618L;

	private QuoteToSapService quoteToSapService;
	
 	private String quoteId;//订单号
 	private long id;
 	private SAPService sapService;
 	
	private IAllUserService allUserService;
	private String smtpServer;//=env.getProperty("allUser.smtpServer");
	private String  from;//=env.getProperty("allUser.from");
	private String  displayName;//=env.getProperty("allUser.displayName");
	private String  emailaddress;//=env.getProperty("allUser.emailaddress");
	private String 	emailpassword;//=env.getProperty("allUser.emailpassword");

 	



	public IAllUserService getAllUserService() {
		return allUserService;
	}

	public void setAllUserService(IAllUserService allUserService) {
		this.allUserService = allUserService;
	}

	public String getSmtpServer() {
		return smtpServer;
	}

	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getEmailpassword() {
		return emailpassword;
	}

	public void setEmailpassword(String emailpassword) {
		this.emailpassword = emailpassword;
	}

	public QuoteToSapService getQuoteToSapService() {
		return quoteToSapService;
	}

	public void setQuoteToSapService(QuoteToSapService quoteToSapService) {
		this.quoteToSapService = quoteToSapService;
	}

	public String getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public SAPService getSapService() {
		return sapService;
	}

	public void setSapService(SAPService sapService) {
		this.sapService = sapService;
	}

	public String getQuoteToSAP(){
		
		this.setSuccessMessage("");
		String message = "";
		QuoteToSap quote = new QuoteToSap();
//		quote.setId(id);
		List<QuoteToSap> quoteList = quoteToSapService
				.getQuoteTotal(quote);
//		quote = quoteList.get(0);
		 for(int i=0;i<quoteList.size();i++)    {   
		       quote = quoteList.get(i);
		       List<QuoteDetail> quoteDetail = quoteToSapService
						.getQuoteDetail(quote);
				System.out.println(quote.getId());      
				Quote q = new Quote();
				q.setId(quote.getId());
				message = quoteToSapService.quoteToSap(quote,
							quoteDetail, q);
		}
		
		Quote q = new Quote();
		q.setId(quote.getId());
				
		this.setSuccessMessage(message);
		return RESULT_MESSAGE;
	}
	
	
	
	
	public String getQuoteList(){
		System.out.println("------------");
		QuoteToSap quote = new QuoteToSap();
//		this.quoteToSapService.getQuoteTotal(quote);
		
		List<QuoteToSap> list = quoteToSapService.getQuoteTotal(quote);;
		if(null !=list && list.size()>0){
			smtpServer=env.getProperty("allUser.smtpServer");
			from=env.getProperty("allUser.from");
			displayName=env.getProperty("allUser.displayName");
			emailaddress=env.getProperty("allUser.emailaddress");
	 		emailpassword=env.getProperty("allUser.emailpassword");
			HashMap<String, String> map = new HashMap<String, String>();
			AllUsers allUser = new AllUsers();
			if (StringUtils.isNotEmpty("013404")
					&& StringUtils.isNotEmpty("013404")) {// 邮件收件人不为空loginId
				allUser.setLoginId("013404");
				List<AllUsers> userList = allUserService.getEmpList4Code(allUser);
				if (userList.size() != 0) {
					for (AllUsers allUser1 : userList) {
						String userNumber = "<br>&nbsp;&nbsp;您的姓名为："
								+ allUser1.getUserName()
								+ "&nbsp;&nbsp;<br>&nbsp;&nbsp;" + "您的登陆账号为："
								+ allUser1.getLoginId(); 
						String contents = "尊敬的用户" + userNumber + "<br>";
						if (null != allUser1.getEmail()) {
							MailService mail = new MailService(smtpServer, from,
									displayName, emailaddress, emailpassword,
									allUser1.getEmail(), "系统提醒", contents);// 邮件做成
							map = mail.send(); // 邮件发送
						} else {
							this.setFailMessage("邮件发送失败,没有找到该账号的邮箱地址，请确认账号是否正确！<br>如账号是准确的，请联系管理员，确认邮箱信息是否维护！");
							return RESULT_MESSAGE;
						}
						if ("success".equals(map.get("state"))) {
							this.setSuccessMessage(/* map.get("message") */"尊敬的用户"
									+ allUser1.getUserName()
									+ "，您好：<br>邮件已发送至您邮箱<font size=2 color=blue>"
									+ allUser1.getEmail() + "</font>，注意查收！");
						} else {
							this.setFailMessage( "邮件发送失败,请联系系统管理员！");
						}
					}
				} else {
					this.setFailMessage("系统内无该用户,请确认账号是否正确！");
				}
			}
			
		}
		
 		return null;
	}
	
 

//public String getQuoteToSapTest() {
//		
//		QuoteToSap quote = new QuoteToSap();
//	
////		quote.setId("1");
//		quote.setQuoteId("1");
//		quote.setQuoteType("1");
//		quote.setCustomerGroup("1");
//		quote.setCustomer("1");
//		quote.setEcGroup("1");
//		quote.setEndCustomer("1");
//		quote.setTotalAmountv("1");
//		quote.setCurrency("1");
//		quote.setProject("1");
//		quote.setAssembly("1");
//		quote.setStartTime("1");
//		quote.setEndTime("1");
//		quote.setState("1");
//		quote.setEnquiryMainId("1");
//		quote.setSyncState("1"); 
//		quote.setSyncTime("1");
//		quote.setSyncUserId("1");
//		quote.setCreateTime("1");
//		quote.setCreateTime("1");
//		quote.setCreateUserId("1");
//		quote.setLatestTime("1");
//		quote.setLatestUserId("1");
//		quote.setOrgCode("1");
//		
//		QuoteDetail quoteDetail = new QuoteDetail();
//		
//		quoteDetail.setId("1");
//		quoteDetail.setQuoteId("1");
//		quoteDetail.setRowNo("1");
//		quoteDetail.setMaterialId("1"); 
//		quoteDetail.setDrId("1");
//		quoteDetail.setQty("1");
//		quoteDetail.setTargetResale("1");
//		quoteDetail.setTargetCost("1"); 
//		
//		quoteDetail.setMfrMargin("1"); 
//		quoteDetail.setAmount("1"); 
//		quoteDetail.setSuggestResale("1"); 
//		quoteDetail.setSuggestCost("1"); 
//		quoteDetail.setCusProfitsPercent("1"); 
//		quoteDetail.setJustification("1"); 
//		quoteDetail.setCompetitor("1"); 
//		quoteDetail.setStartOfProduction("1");
//		quoteDetail.setCusRemarks("1");
//		quoteDetail.setWeenRemarks("1");
//		quoteDetail.setStatus("1");
//		quoteDetail.setMainId("1");
//		quoteDetail.setEnquiryDetailId("1");
//		quoteDetail.setLatestTime("1");
//		quoteDetail.setLatestUserId("1");
//		
//		List<QuoteDetail> items = new ArrayList<QuoteDetail>();
//		items.add(quoteDetail);
//		
//		QuoteToSapServiceImpl sap = new QuoteToSapServiceImpl();
//		
//		String message=sap.quoteToSap(quote, items);
//		
//		return message;
//		
//		
//		
//	}
}
