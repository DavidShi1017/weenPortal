package com.jingtong.platform.cusInventroy.service.impl;

import com.jingtong.platform.cusInventroy.service.ICusInventroyCheckEDIJobService;
import com.jingtong.platform.cusInventroy.service.ICusInventroyService;
import com.jingtong.platform.sap.pojo.ExceptionLog;
import com.jingtong.platform.webservice.dao.IWebDao;

/**
 * @author cl
 * @createDate 2016-6-16
 * 
 */
public class CusInventroyCheckEDIJobServiceImpl implements ICusInventroyCheckEDIJobService {

    private ICusInventroyService inverntoryService;
    private IWebDao iWebDao;

    public ICusInventroyService getInverntoryService() {
        return inverntoryService;
    }

    public void setInverntoryService(ICusInventroyService inverntoryService) {
        this.inverntoryService = inverntoryService;
    }

    public IWebDao getiWebDao() {
        return iWebDao;
    }

    public void setiWebDao(IWebDao iWebDao) {
        this.iWebDao = iWebDao;
    }

    @Override
    public void checkEDI() throws Exception {
        ExceptionLog log = new ExceptionLog();
        log.setLogInfo("START : inventoryCheckEDI");
        log.setInterfaceName("CusInventroyCheckEDIJobServiceImpl");
        iWebDao.insertJobLog(log);
        inverntoryService.checkEDI();
        log.setLogInfo("END : inventoryCheckEDI");
        log.setInterfaceName("CusInventroyCheckEDIJobServiceImpl");
        iWebDao.insertJobLog(log);
    }

}
