package com.jingtong.platform.sampleOrder.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.customer.pojo.Disti_branch;
import com.jingtong.platform.org.pojo.Borg;
import com.jingtong.platform.role.pojo.Role;
import com.jingtong.platform.sampleOrder.dao.ISampleOrderDao;
import com.jingtong.platform.sampleOrder.pojo.AccountManager;
import com.jingtong.platform.sampleOrder.pojo.SampleOrder;
import com.jingtong.platform.sampleOrder.pojo.SampleOrderAndDetail;
import com.jingtong.platform.sampleOrder.pojo.SampleOrderDetail;
import com.jingtong.platform.sampleOrder.service.ISampleOrderService;

public class SampleOrderServiceImpl implements ISampleOrderService {
    private ISampleOrderDao sampleOrderDao;
    private TransactionTemplate transactionTemplate;

    @Override
    public int getSampleOrderListCount(SampleOrder o) {
        try {
            return sampleOrderDao.getSampleOrderListCount(o);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<SampleOrder> getSampleOrderList(SampleOrder o) {
        try {
            return sampleOrderDao.getSampleOrderList(o);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public SampleOrder getSampleOrderById(SampleOrder o) {
        try {
            return sampleOrderDao.getSampleOrderById(o);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public BooleanResult createSampleOrder(final SampleOrder o, final List<SampleOrderDetail> odList) {
        BooleanResult booleanResult = new BooleanResult();
        booleanResult = (BooleanResult) transactionTemplate.execute(new TransactionCallback() {
            public Object doInTransaction(TransactionStatus ts) {
                BooleanResult booleanResult = new BooleanResult();
                long n = 0;
                long m = 0;
                booleanResult.setResult(true);
                try {
                    booleanResult.setResult(false);
                    n = sampleOrderDao.createSampleOrder(o);
                    if (n <= 0) {
                        booleanResult.setResult(false);
                        booleanResult.setCode("²Ù×÷Ê§°Ü£¡");
                        ts.setRollbackOnly();
                        return booleanResult;
                    } else {
                        for (SampleOrderDetail od : odList) {
                            od.setMain_id(n);
                            m = sampleOrderDao.createSampleOrderDetail(od);
                            if (m <= 0) {
                                booleanResult.setResult(false);
                                booleanResult.setCode("²Ù×÷Ê§°Ü£¡");
                                ts.setRollbackOnly();
                                return booleanResult;
                            }
                        }
                        booleanResult.setCode(String.valueOf(n));
                        booleanResult.setResult(true);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    booleanResult.setResult(false);
                    booleanResult.setCode("²Ù×÷Ê§°Ü£¡");
                    ts.setRollbackOnly();
                    return booleanResult;
                }
                return booleanResult;
            }
        });
        return booleanResult;
    }

    @Override
    public BooleanResult updateSampleOrder(final SampleOrder o, final List<SampleOrderDetail> odList,
            final SampleOrderDetail od) {
        BooleanResult booleanResult = new BooleanResult();
        booleanResult = (BooleanResult) transactionTemplate.execute(new TransactionCallback() {
            public Object doInTransaction(TransactionStatus ts) {
                BooleanResult booleanResult = new BooleanResult();
                long n;
                long m;
                booleanResult.setResult(true);
                try {
                    booleanResult.setResult(false);
                    n = sampleOrderDao.updateSampleOrder(o);
                    if (n <= 0) {
                        booleanResult.setResult(false);
                        booleanResult.setCode("²Ù×÷Ê§°Ü£¡");
                        ts.setRollbackOnly();
                        return booleanResult;
                    } else {
                        sampleOrderDao.deleteSampleOrderDetail(od);
                        for (SampleOrderDetail od : odList) {
                            if (od.getId() == 0) {
                                m = sampleOrderDao.createSampleOrderDetail(od);
                            } else {
                                m = sampleOrderDao.updateSampleOrderDetail(od);
                            }
                            if (m <= 0) {
                                booleanResult.setResult(false);
                                booleanResult.setCode("²Ù×÷Ê§°Ü£¡");
                                ts.setRollbackOnly();
                                return booleanResult;
                            }
                        }
                        booleanResult.setResult(true);
                        booleanResult.setCode(String.valueOf(n));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    booleanResult.setResult(false);
                    booleanResult.setCode("²Ù×÷Ê§°Ü£¡");
                    ts.setRollbackOnly();
                    return booleanResult;
                }
                return booleanResult;
            }
        });
        return booleanResult;
    }

    @Override
    public int deleteSampleOrder(SampleOrder o) {
        try {
            return sampleOrderDao.deleteSampleOrder(o);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<SampleOrderDetail> getSampleOrderDetailList(SampleOrderDetail od) {
        try {
            return sampleOrderDao.getSampleOrderDetailList(od);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getSystemIdPrc() {
        try {
            return sampleOrderDao.getSystemIdPrc();
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    @Override
    public long createSampleOrderDetail(SampleOrderDetail od) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int updateSampleOrderDetail(SampleOrderDetail od) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int deleteSampleOrderDetail(SampleOrderDetail od) {
        // TODO Auto-generated method stub
        return 0;
    }

    public ISampleOrderDao getSampleOrderDao() {
        return sampleOrderDao;
    }

    public void setSampleOrderDao(ISampleOrderDao sampleOrderDao) {
        this.sampleOrderDao = sampleOrderDao;
    }

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public int deleteSODofMain(SampleOrderDetail sod) {
        try {
            return sampleOrderDao.deleteSODofMain(sod);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public int importUpdateCount(SampleOrderDetail o) {
        try {
            return sampleOrderDao.importUpdateCount(o);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int importUpdate(SampleOrderDetail o) {
        try {
            return sampleOrderDao.importUpdate(o);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getSampleOrderAndDetailListCount(SampleOrder o) {
        try {
            return sampleOrderDao.getSampleOrderAndDetailListCount(o);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<SampleOrderAndDetail> getSampleOrderAndDetailList(SampleOrder o) {
        try {
            return sampleOrderDao.getSampleOrderAndDetailList(o);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getRoleByLoginId(SampleOrder o) {
        // TODO Auto-generated method stu public int getRoleByLoginId(SampleOrder o);
        try {
            return sampleOrderDao.getRoleByLoginId(o);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Disti_branch> getDistiBranchList(Disti_branch db) {
        try {
            db.setStart(0);
            db.setEnd(100);
            db.setSort("disti_branch");
            return sampleOrderDao.getDistiBranchList(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Borg> getOrgList(Borg borg) {
        try {
            return sampleOrderDao.getOrgList(borg);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<AccountManager> getAccountManageList(AccountManager manager) {
        try {
            return sampleOrderDao.getAccountManageList(manager);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Role> getRoleForSampleOrder(String userId) {
        try {
            return sampleOrderDao.getRoleForSampleOrder(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Role>();
        }
    }

    @Override
    public BooleanResult auditSampleOrder(final SampleOrder o, final List<SampleOrderDetail> odList) {
        BooleanResult booleanResult = new BooleanResult();
        booleanResult = (BooleanResult) transactionTemplate.execute(new TransactionCallback() {
            public Object doInTransaction(TransactionStatus ts) {
                BooleanResult booleanResult = new BooleanResult();
                long n = 0;
                long m = 0;
                try {
                    booleanResult.setResult(false);
                    n = sampleOrderDao.auditSampleOrder(o);
                    if (n <= 0) {
                        booleanResult.setResult(false);
                        booleanResult.setCode("²Ù×÷Ê§°Ü£¡");
                        ts.setRollbackOnly();
                        return booleanResult;
                    }
                    for (SampleOrderDetail od : odList) {
                        od.setMain_id(n);
                        m = sampleOrderDao.auditSampleOrderDetail(od);
                        if (m <= 0) {
                            booleanResult.setResult(false);
                            booleanResult.setCode("²Ù×÷Ê§°Ü£¡");
                            ts.setRollbackOnly();
                            return booleanResult;
                        }
                    }
                    booleanResult.setResult(true);

                } catch (Exception e) {
                    e.printStackTrace();
                    booleanResult.setResult(false);
                    booleanResult.setCode("²Ù×÷Ê§°Ü£¡");
                    ts.setRollbackOnly();
                    return booleanResult;
                }
                return booleanResult;
            }
        });
        return booleanResult;
    }
}
