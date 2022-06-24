package com.jingtong.platform.sap.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;
/**
 * 产品区域限制
 * @author Administrator
 *
 */
public class Exclude  extends SearchInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8092301909884488590L;

	private long id;
	private String pCrdat=""; 	//当前日期，格式：20150420
	private String sMatnr="";//Table	物料编码

	private String kappl;//应用程序
	private String kschl;//条件类型
	private String kunnr;//客户/客户组/县代码/城市代码
	private String matnr;//物料编码
	private Date datab;//有效开始日期
	private Date datbi;//有效结束日期
	private String dtype;//类型：01客户；02客户组；03县代码；04城市代码
	private String createUser;
	private Date createDate;
	private String modifyUser;
	private Date modifyDate;
	
	private String datab1;
	private String datbi1;
	public String getpCrdat() {
		return pCrdat;
	}
	public void setpCrdat(String pCrdat) {
		this.pCrdat = pCrdat;
	}
	public String getsMatnr() {
		return sMatnr;
	}
	public void setsMatnr(String sMatnr) {
		this.sMatnr = sMatnr;
	}
	public String getKappl() {
		return kappl;
	}
	public void setKappl(String kappl) {
		this.kappl = kappl;
	}
	public String getKschl() {
		return kschl;
	}
	public void setKschl(String kschl) {
		this.kschl = kschl;
	}
	public String getKunnr() {
		return kunnr;
	}
	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}
	public String getMatnr() {
		return matnr;
	}
	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}
	public Date getDatab() {
		return datab;
	}
	public void setDatab(Date datab) {
		this.datab = datab;
	}
	public Date getDatbi() {
		return datbi;
	}
	public void setDatbi(Date datbi) {
		this.datbi = datbi;
	}
	public String getDtype() {
		return dtype;
	}
	public void setDtype(String dtype) {
		this.dtype = dtype;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDatab1() {
		return datab1;
	}
	public void setDatab1(String datab1) {
		this.datab1 = datab1;
	}
	public String getDatbi1() {
		return datbi1;
	}
	public void setDatbi1(String datbi1) {
		this.datbi1 = datbi1;
	}

}
