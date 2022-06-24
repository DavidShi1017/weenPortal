package com.jingtong.platform.account.pojo;

import java.io.Serializable;
import java.util.Date;

public class UserLocked implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String USERNAME;
	private Date LOGINTIME;

	public String getUSERNAME() {
		return USERNAME;
	}

	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}

	public Date getLOGINTIME() {
		return LOGINTIME;
	}

	public void setLOGINTIME(Date lOGINTIME) {
		LOGINTIME = lOGINTIME;
	}
	
	
}
