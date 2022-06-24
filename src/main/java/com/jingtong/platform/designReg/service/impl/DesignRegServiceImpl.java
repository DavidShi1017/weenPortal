package com.jingtong.platform.designReg.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.jingtong.platform.framework.util.DateUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.common.Escape;
import com.jingtong.platform.customer.pojo.CustomerUser;
import com.jingtong.platform.customer.pojo.Disti_branch;
import com.jingtong.platform.designReg.dao.IDesignRegDao;
import com.jingtong.platform.designReg.pojo.DesignReg;
import com.jingtong.platform.designReg.pojo.DesignRegDetail;
import com.jingtong.platform.designReg.pojo.DesignRegDetailLog;
import com.jingtong.platform.designReg.pojo.Dict;
import com.jingtong.platform.designReg.service.IDesignRegService;
import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.framework.util.DataUtil;
import com.jingtong.platform.pos.pojo.Pos;
import com.jingtong.platform.product.pojo.Product;

public class DesignRegServiceImpl implements IDesignRegService {
    private IDesignRegDao designRegDao;
    private TransactionTemplate transactionTemplate;

    @Override
    public int getDesignRegListCount(DesignReg dr) {
        try {
            return designRegDao.getDesignRegListCount(dr);
        } catch (Exception e1) {
            e1.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<DesignReg> getDesignRegList(DesignReg dr) {
        try {
            return designRegDao.getDesignRegList(dr);
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DesignRegDetail> checkDesignReg(DesignRegDetail drd) {
        try {
            return designRegDao.checkDesignReg(drd);
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    @Override
    public DesignReg getDesignRegById(DesignReg dr) {
        try {
            return designRegDao.getDesignRegById(dr);
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    @Override
    public BooleanResult createDesignReg(final DesignReg dr, final List<DesignRegDetail> drdList) {
        BooleanResult booleanResult = new BooleanResult();
        booleanResult = (BooleanResult) transactionTemplate.execute(new TransactionCallback() {
            public Object doInTransaction(TransactionStatus ts) {
                BooleanResult booleanResult = new BooleanResult();
                long n;
                long m;
                booleanResult.setResult(true);
                try {
                    booleanResult.setResult(false);
                    n = designRegDao.createDesignReg(dr);
                    if (n <= 0) {
                        booleanResult.setResult(false);
                        booleanResult.setCode("操作失败！");
                        ts.setRollbackOnly();
                        return booleanResult;
                    } else {
                        String drNum = DataUtil.frontCompWithZore("R", n, 6);
                        dr.setDrNum(drNum);
                        dr.setId(n);
                        designRegDao.setDRNum(dr);
                        for (DesignRegDetail drd : drdList) {
                            drd.setDrNum(drNum);
                            drd.setMain_id(n);
                            drd.setCreate_userId(dr.getCreate_userId());
                            drd.setLatest_userId(dr.getCreate_userId());
                            drd.setUsage_amount(dr.getUsage_amount());
                            m = designRegDao.createDesignRegDetail(drd, dr);
                            if (m <= 0) {
                                booleanResult.setResult(false);
                                booleanResult.setCode("操作失败！");
                                ts.setRollbackOnly();
                                return booleanResult;
                            }
                        }
                        booleanResult.setResult(true);
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                    booleanResult.setResult(false);
                    booleanResult.setCode("操作失败！");
                    ts.setRollbackOnly();
                    return booleanResult;
                }
                return booleanResult;
            }
        });
        return booleanResult;
    }

    @Override
    public BooleanResult updateDesignReg(final DesignReg dr, final List<DesignRegDetail> drdList, final int state,
            final String isUpdate) {
        BooleanResult booleanResult = new BooleanResult();
        booleanResult = (BooleanResult) transactionTemplate.execute(new TransactionCallback() {
            public Object doInTransaction(TransactionStatus ts) {
                BooleanResult booleanResult = new BooleanResult();
                long n, m;
                booleanResult.setResult(true);
                try {
                    booleanResult.setResult(false);
                    // 在DR approve后任何时间这个DR owner修改了DR上可编辑字段，
                    // expire date再按照当天往后顺延三个月
                    if (state == 1 && "1".equals(isUpdate)) {
                        Date date = new Date();
                        dr.setEnd_date(DateUtil.addMonths(date, 3));     
                    }
                    n = designRegDao.updateDesignReg(dr);
                    if (n <= 0) {
                        booleanResult.setResult(false);
                        booleanResult.setCode("操作失败！");
                        ts.setRollbackOnly();
                        return booleanResult;
                    } else {
                        for (DesignRegDetail drd : drdList) {

                            DesignRegDetail checkData = designRegDao.getDRDbyId(drd);
                            // if the record is DW, skip
                            if (checkData.getProject_state() == 2) {
                                drd.setSaler_design_status(checkData.getSaler_design_status());
                                continue;
                            }
                            // if the record is Expired, skip
                            else if (checkData.getState() == 3) {
                                drd.setSaler_design_status(checkData.getSaler_design_status());
                                continue;
                            }

                            drd.setRemark(Escape.unescape(drd.getRemark()));
                            drd.setWeencomments(Escape.unescape(drd.getWeencomments()));
                            
                            if (drd.getRemark() != null) {
                                drd.setRemark(StringUtils.replace(drd.getRemark(), "undefined", ""));
                            }
                            if (drd.getWeencomments() != null) {
                                drd.setWeencomments(StringUtils.replace(drd.getWeencomments(), "undefined", ""));
                            }
                            
                            drd.setUsage_amount(dr.getUsage_amount());

                            if (drd.getProject_state() == 2) {
                                drd.setSaler_design_status(0L);
                            }

                            drd.setLatest_userId(dr.getLatest_userId());
                            if ("1".equals(isUpdate)) {
                                drd.setRemark(checkData.getRemark());
                                drd.setWeencomments(drd.getWeencomments());
                            } else {
                                drd.setCus_remark(checkData.getCus_remark());
                            }
                            
                            // 在DR approve后任何时间这个DR owner修改了DR上可编辑字段，
                            // expire date再按照当天往后顺延三个月
                            if (checkData.getState() == 1 && "1".equals(isUpdate)) {
                                Date date = new Date();
                                drd.setEnd_date(DateUtil.addMonths(date, 3));     
                            }
                            else if ("0".equals(isUpdate)) {
                                Date date = new Date();
                                drd.setEnd_date(DateUtil.addMonths(date, 6));     
                            }
                            m = designRegDao.updateDesignRegDetail(drd);
                            if (m <= 0) {
                                booleanResult.setResult(false);
                                booleanResult.setCode("Update Error!");
                                ts.setRollbackOnly();
                                return booleanResult;
                            }
                            DesignReg drInfo = designRegDao.getDesignRegById(dr);
                            DesignRegDetail nowData = designRegDao.getDRDbyId(drd);
                            DesignRegDetailLog log = new DesignRegDetailLog(nowData);
                            log.setId(null);
                            if (drd.getProject_state() == 2) {
                                log.setType("10");
                            } else if ("1".equals(state)) {
                                log.setType("2");
                            } else {
                                log.setType("1");
                            }
                            log.setDr_detail_id(Long.toString(drd.getId()));
                            log.setUsage_amount(drd.getUsage_amount());
                            log.setMp_schedule(drInfo.getMp_schedule());
                            log.setCreate_userId(dr.getLatest_userId());
                            log.setWeencomments(drd.getWeencomments());
                            designRegDao.createDesignRegDetailLog(log);
                        }
                        booleanResult.setResult(true);
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                    booleanResult.setResult(false);
                    booleanResult.setCode("操作失败！");
                    ts.setRollbackOnly();
                    return booleanResult;
                }
                return booleanResult;
            }
        });
        return booleanResult;
    }

    @Override
    public int deleteDesignReg(DesignReg dr) {
        try {
            return designRegDao.deleteDesignReg(dr);
        } catch (Exception e1) {
            e1.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<DesignRegDetail> getDesignRegDetailList(DesignRegDetail drd) {
        try {
            return designRegDao.getDesignRegDetailList(drd);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getSystemIdPrc() {
        try {
            return designRegDao.getSystemIdPrc();
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateDRState() {
        try {
            designRegDao.updateDRState();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return;
    }

    @Override
    public long createDesignRegDetail(DesignRegDetail drd) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int updateDesignRegDetail(DesignRegDetail drd) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int updateDesignRegDetailDesignStatus(DesignRegDetail drd) {
        // TODO Auto-generated method stub
        return designRegDao.updateDesignRegDetailDesignStatus(drd);
    }

    @Override
    public int deleteDesignRegDetail(DesignRegDetail drd) {
        // TODO Auto-generated method stub
        return 0;
    }

    public IDesignRegDao getDesignRegDao() {
        return designRegDao;
    }

    public void setDesignRegDao(IDesignRegDao designRegDao) {
        this.designRegDao = designRegDao;
    }

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public List<DesignRegDetail> checkDesignRegDetailList(DesignRegDetail drd) {
        try {
            return designRegDao.checkDesignRegDetailList(drd);
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    @Override
    public int checkDesignRegDetailCount(DesignRegDetail drd) {
        try {
            return designRegDao.checkDesignRegDetailCount(drd);
        } catch (Exception e1) {
            e1.printStackTrace();
            return 0;
        }
    }

    @Override
    public int auditDRD(DesignRegDetail drd) {
        try {
            return designRegDao.auditDRD(drd);
        } catch (Exception e1) {
            e1.printStackTrace();
            return 0;
        }
    }

    @Override
    public int setCheck(DesignRegDetail drd) {
        try {
            return designRegDao.setCheck(drd);
        } catch (Exception e1) {
            e1.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<DesignRegDetail> outPutDR(DesignRegDetail drd) {
        try {
            return designRegDao.outPutDR(drd);
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Dict> getDictOfWeen(Dict m) {
        try {
            return designRegDao.getDictOfWeen(m);
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    @Override
    public String getDRECCountryOrg(DesignReg dr) {
        try {
            return designRegDao.getDRECCountryOrg(dr);
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DesignRegDetail> getDRDbyIds(DesignRegDetail drd) {
        try {
            return designRegDao.getDRDbyIds(drd);
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CustomerUser> getDRAuditSale(DesignReg dr) {
        try {
            return designRegDao.getDRAuditSale(dr);
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DesignRegDetail> getRoleUserByMenuId(DesignRegDetail drd) {
        try {
            return designRegDao.getRoleUserByMenuId(drd);
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    @Override
    public int checkDRExpireByDrNum(DesignRegDetail drd) {
        try {
            return designRegDao.checkDRExpireByDrNum(drd);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<DesignRegDetailLog> getDrLogList(DesignRegDetail drd) {
        try {
            int count = designRegDao.getDrLogListCount(drd);
            if (count == 0) {
                return null;
            }
            return designRegDao.getDrLogList(drd);
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    @Override
    public Long createDrLog(DesignRegDetailLog log) {
        try {
            return designRegDao.createDesignRegDetailLog(log);
        } catch (Exception e1) {
            e1.printStackTrace();
            return 0L;
        }
    }

    @Override
    public boolean checkNewCustomer(DesignReg dr) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dr.getCreate_time());

        int month = calendar.get(Calendar.MONTH) + 1;
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        if (month <= 3) {
            calendar.set(Calendar.MONTH, 0);
        } else if (month <= 6) {
            calendar.set(Calendar.MONTH, 3);
        } else if (month <= 9) {
            calendar.set(Calendar.MONTH, 6);
        } else {
            calendar.set(Calendar.MONTH, 9);
        }

        Pos pos = new Pos();
        pos.setCreated_time_end(calendar.getTime());

        // DR 创建季度的前8个季度（排除DR创建日期所在季度）
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 2);
        pos.setCreated_time_start(calendar.getTime());
        pos.setEndcust_id(dr.getEndCus_id());

        List<Pos> ecPosList = designRegDao.getCustomerPos(pos);

        if (ecPosList == null || ecPosList.size() == 0) {
            return true;
        }
        CmsTbDict searchCmsTbDict = new CmsTbDict();
        searchCmsTbDict.setItemId(Long.valueOf("14213"));
        CmsTbDict cmsTbDict = designRegDao.getCmsTbDict(searchCmsTbDict);
        double rate = Double.valueOf(cmsTbDict.getItemValue());

        double total = 0.00;

        for (Pos ecPos : ecPosList) {
            // 汇率处理
            if (ecPos.getDisti_resale_currency() != null && "EUR".equals(ecPos.getDisti_resale_currency())) {
                Double shipQty = Double.valueOf(ecPos.getShip_qty()) / rate;
                total = total + shipQty;
            } else {
                total = total + Double.valueOf(ecPos.getShip_qty());
            }

            if (total > 1000.0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkNewProduct(Product product, Date drTime) {

        Calendar now = Calendar.getInstance();
        Calendar rfsDate = Calendar.getInstance();
        rfsDate.setTime(product.getRfs_date());
        now.setTime(drTime);

        int nowYear = now.get(Calendar.YEAR);
        int rfsYear = rfsDate.get(Calendar.YEAR);

        if (nowYear - rfsYear > 3) {
            return false;
        }
        return true;
    }

    @Override
    public Product getProduct(DesignRegDetail drd) {

        Product prod = new Product();

        prod.setMaterial_id(drd.getMaterial_id());

        return designRegDao.getProductByMaterialIdOrName(prod);
    }

    @Override
    public DesignRegDetail getDRTypeByItmeValue(DesignRegDetail drd) {
        return designRegDao.getDRTypeByItmeValue(drd);
    }

    @Override
    public int updateDesignRegDetailDrType(final DesignRegDetail drd) {
        DesignReg drInfo = designRegDao.getDesignRegByDesignRegDetail(drd);
        DesignRegDetailLog log;
        try {
            DesignRegDetail checkData = designRegDao.getDRDbyId(drd);
            checkData.setDr_type(drd.getDr_type());
            checkData.setLatest_userId(drd.getLatest_userId());
            checkData.setDrtype_def(drd.getDrtype_def());
            checkData.setDw_cal(drd.getDw_cal());

            log = new DesignRegDetailLog(checkData);
            log.setId(null);
            log.setType("1");
            log.setDr_detail_id(Long.toString(drd.getId()));
            log.setMp_schedule(drInfo.getMp_schedule());
            log.setCreate_userId(drd.getLatest_userId());
            designRegDao.createDesignRegDetailLog(log);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return designRegDao.updateDesignRegDetailDrType(drd);
    }

    @Override
    public DesignReg getDesignRegByDesignRegDetail(final DesignRegDetail drd) {
        return designRegDao.getDesignRegByDesignRegDetail(drd);
    }

    @Override
    public List<Disti_branch> getDistiBranchList(Disti_branch db) {
        try {
            db.setStart(0);
            db.setEnd(20);
            db.setSort("disti_branch");
            return designRegDao.getDistiBranchList(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public DesignRegDetail getDRDbyId(DesignRegDetail drd) {
        return designRegDao.getDRDbyId(drd);
    }

    @Override
    public int updateDrdRemark(DesignRegDetail drd) {
        return designRegDao.updateDesignRegDetailRemark(drd);
    }
    
    @Override
    public Disti_branch getDistAlias(Disti_branch disti) {
        List<Disti_branch> list = designRegDao.getDistiAliasList(disti);
        
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        
        return null;
    }
    
    @Override
    public Disti_branch getDistBranchAlias(Disti_branch distibranch) {
        List<Disti_branch> list = designRegDao.getDistiBranchAliasList(distibranch);
        
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        
        return null;
    }
}
