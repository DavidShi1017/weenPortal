package com.jingtong.platform.activitiWebEdit.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jingtong.platform.activitiWebEdit.dao.IRoleDao;
import com.jingtong.platform.activitiWebEdit.pojo.WorkFlowRole;
import com.jingtong.platform.activitiWebEdit.service.RoleService;
import com.jingtong.platform.webservice.service.IWebService;

public class RoleServiceImpl implements RoleService{
	private static final Log logger = LogFactory.getLog(RoleServiceImpl.class);
	private IRoleDao allRoleDao;

	private IWebService webService;




	public IWebService getWebService() {
		return webService;
	}


	public void setWebService(IWebService webService) {
		this.webService = webService;
	}


	
	public List<WorkFlowRole> searchAllRoles(WorkFlowRole wfr) {
		try {
			return allRoleDao.searchAllRoles(wfr);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}


	
	public int searchAllRolesCount(WorkFlowRole wfr) {
		try {
			return allRoleDao.searchAllRolesCount(wfr);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}
	public IRoleDao getAllRoleDao() {
		return allRoleDao;
	}
	public void setAllRoleDao(IRoleDao allRoleDao) {
		this.allRoleDao = allRoleDao;
	}
	
	public boolean deployProcessDefinition(String  processXml,String processname){
	 
		String string = webService.deployProcessDefinition(processXml, processname);
		if(string!=null&&string.equals("success")){
			return true;
		}
		return false;
	}
	
}
