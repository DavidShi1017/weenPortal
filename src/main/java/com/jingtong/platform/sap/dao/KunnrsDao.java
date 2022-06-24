package com.jingtong.platform.sap.dao;

 

import java.util.List;
import java.util.Map;

import com.jingtong.platform.org.pojo.Borg;
import com.jingtong.platform.role.pojo.Role;
import com.jingtong.platform.sap.pojo.Credit;
import com.jingtong.platform.sap.pojo.Knvp;
import com.jingtong.platform.sap.pojo.Kunnr;
import com.jingtong.platform.sap.pojo.KunnrCompany;
import com.jingtong.platform.sap.pojo.KunnrVtweg;
import com.jingtong.platform.sap.pojo.ToolsClass;

public interface KunnrsDao {

	public String createKunnr(Kunnr kunnr);

	public int updateKunnr(Kunnr kunnr);

	public int getKunnrList(Kunnr customerId);

	public String createKunnrCompany(KunnrCompany kunnrCompany);

	public int updateKunnrCompany(KunnrCompany kunnrCompany);

	public int getKunnrCompanyList(KunnrCompany kunnrCompany);

}
