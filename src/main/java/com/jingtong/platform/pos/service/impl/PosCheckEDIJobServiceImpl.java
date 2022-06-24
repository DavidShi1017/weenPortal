package com.jingtong.platform.pos.service.impl;

import java.util.List;

import com.jingtong.platform.pos.pojo.Pos;
import com.jingtong.platform.pos.service.IPosCheckEDIJobService;
import com.jingtong.platform.pos.service.IPosService;
import com.jingtong.platform.sap.pojo.ExceptionLog;
import com.jingtong.platform.webservice.dao.IWebDao;

public class PosCheckEDIJobServiceImpl implements IPosCheckEDIJobService {
    IPosService posService;

    private IWebDao iWebDao;

    public IPosService getPosService() {
        return posService;
    }

    public void setPosService(IPosService posService) {
        this.posService = posService;
    }

    public IWebDao getiWebDao() {
        return iWebDao;
    }

    public void setiWebDao(IWebDao iWebDao) {
        this.iWebDao = iWebDao;
    }

    @Override
    public void posCheckEDI() throws Exception {

        ExceptionLog log = new ExceptionLog();
        log.setLogInfo("START : posCheckEDI");
        log.setInterfaceName("PosCheckEDIJobServiceImpl");
        iWebDao.insertJobLog(log);

        Pos pos = new Pos();
        pos.setType("1");
        int num = posService.searchPosFileIdCount(pos); // 查询状态为9的记录
        if (num >= 1) {
            List<Integer> idList = posService.searchPosFileId(pos);
            for (Integer fileId : idList) { // 获得这个FileId下所有记录
                pos.setFile_id(fileId);
                List<Pos> poList = posService.searchPosListForEDI(pos);
                for (Pos pos1 : poList) { // 单条更新逻辑
                    posService.checkEDI(Long.valueOf(pos1.getId()));
                }
                pos.setFile_id(pos.getFile_id());
                pos.setType("1");
                int aa = posService.searchPosDetailListCount(pos);
                int bb = posService.searchPosDetailListCountForError(pos);
                pos.setStatus_num(bb + "/" + aa);
                posService.updatePos(pos);
            }
        }
        log.setLogInfo("END : posCheckEDI");
        log.setInterfaceName("PosCheckEDIJobServiceImpl");
        iWebDao.insertJobLog(log);
    }

    @Override
    public void claimCheckEDI() throws Exception {

        ExceptionLog log = new ExceptionLog();
        log.setLogInfo("START : claimCheckEDI");
        log.setInterfaceName("PosCheckEDIJobServiceImpl");
        iWebDao.insertJobLog(log);

        posService.claimCheckEDI();
        log.setLogInfo("END : claimCheckEDI");
        log.setInterfaceName("PosCheckEDIJobServiceImpl");
        iWebDao.insertJobLog(log);
    }

}
