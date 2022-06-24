package com.jingtong.platform.country.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.country.dao.ICountryDao;
import com.jingtong.platform.country.pojo.BranchMapping;
import com.jingtong.platform.country.pojo.Country;
import com.jingtong.platform.country.pojo.SaleCountry;
import com.jingtong.platform.country.service.ICountryService;

/**
 * @author cl
 * @createDate 2016-5-25
 * 
 */
public class CountryServiceImpl implements ICountryService {
    private static final Log logger = LogFactory.getLog(CountryServiceImpl.class);
    private ICountryDao countryDao;
    private TransactionTemplate transactionTemplate;

    public static Log getLogger() {
        return logger;
    }

    public ICountryDao getCountryDao() {
        return countryDao;
    }

    public void setCountryDao(ICountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public List<Country> searchCountry(Country g) {
        try {
            return countryDao.searchCountry(g);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long saveCountry(Country country) {
        try {
            Country org = countryDao.getOrgCodeByOrgId(country);
            if (org != null) {
                country.setOrg_code(org.getOrg_code());
            }
            return countryDao.saveCountry(country);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateCountry(Country country) {
        try {
            Country org = countryDao.getOrgCodeByOrgId(country);
            if (org != null) {
                country.setOrg_code(org.getOrg_code());
            }
            return countryDao.updateCountry(country);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int deleteCountryById(Country g) {
        try {
            return countryDao.deleteCountryById(g);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Country getCountryById(Country g) {
        try {
            return countryDao.getCountryById(g);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getCountryListCount(Country g) {
        try {
            return countryDao.getCountryListCount(g);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getCountByCode(Country g) {
        try {
            return countryDao.getCountByCode(g);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getSaleCountryListCount(SaleCountry c) {
        try {
            return countryDao.getSaleCountryListCount(c);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<SaleCountry> searchSaleCountry(SaleCountry c) {
        try {
            return countryDao.searchSaleCountry(c);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Country> searchCountryProvince(Country c) {
        try {
            return countryDao.searchCountryProvince(c);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将国家省份分配到销售
     */
    @Override
    public BooleanResult addSaleCountry(final List<SaleCountry> scList) {
        BooleanResult booleanResult = new BooleanResult();
        booleanResult = (BooleanResult) transactionTemplate.execute(new TransactionCallback() {
            public Object doInTransaction(TransactionStatus ts) {
                BooleanResult booleanResult = new BooleanResult();

                long m = 0;
                booleanResult.setResult(true);
                try {
                    booleanResult.setResult(false);
                    for (SaleCountry sc : scList) {
                        if ("undefined".equals(sc.getProvince_code())) {
                            sc.setProvince_code(null);
                        }
                        if ("undefined".equals(sc.getProvince_name())) {
                            sc.setProvince_name(null);
                        }
                        m = countryDao.addSaleCountry(sc);
                        if (m <= 0) {
                            booleanResult.setResult(false);
                            booleanResult.setCode("操作失败！");
                            ts.setRollbackOnly();
                            return booleanResult;
                        } else {
                            long n = 0;
                            sc.setIsAssign("Y");
                            if (sc.getProvince_code() == null || "".equals(sc.getProvince_code())) {
                                n = countryDao.assignCountry(sc);
                            } else {
                                n = countryDao.assignProvince(sc);
                            }
                            if (n <= 0) {
                                booleanResult.setResult(false);
                                booleanResult.setCode("操作失败！");
                                ts.setRollbackOnly();
                                return booleanResult;
                            }
                        }
                    }
                    booleanResult.setCode("Success");
                    booleanResult.setResult(true);

                } catch (Exception e) {
                    e.printStackTrace();
                    booleanResult.setResult(false);
                    booleanResult.setCode("Error");
                    ts.setRollbackOnly();
                    return booleanResult;
                }
                return booleanResult;
            }
        });
        return booleanResult;
    }

    /**
     * 解除分配
     */
    @Override
    public BooleanResult delSaleCountry(final List<SaleCountry> scList) {
        BooleanResult booleanResult = new BooleanResult();
        booleanResult = (BooleanResult) transactionTemplate.execute(new TransactionCallback() {
            public Object doInTransaction(TransactionStatus ts) {
                BooleanResult booleanResult = new BooleanResult();

                long m = 0;
                booleanResult.setResult(true);
                try {
                    booleanResult.setResult(false);
                    for (SaleCountry sc : scList) {
                        if ("undefined".equals(sc.getProvince_code())) {
                            sc.setProvince_code(null);
                        }
                        if ("undefined".equals(sc.getProvince_name())) {
                            sc.setProvince_name(null);
                        }
                        m = countryDao.delSaleCountry(sc);
                        if (m <= 0) {
                            booleanResult.setResult(false);
                            booleanResult.setCode("操作失败！");
                            ts.setRollbackOnly();
                            return booleanResult;
                        } else {
                            long n = 0;
                            sc.setIsAssign("N");
                            if (sc.getProvince_code() == null || "".equals(sc.getProvince_code())) {
                                n = countryDao.assignCountry(sc); // 修改分配标记
                            } else {
                                n = countryDao.assignProvince(sc);// 修改分配标记
                            }
                            if (n <= 0) {
                                booleanResult.setResult(false);
                                booleanResult.setCode("操作失败！");
                                ts.setRollbackOnly();
                                return booleanResult;
                            }
                        }
                    }
                    booleanResult.setCode("Success");
                    booleanResult.setResult(true);

                } catch (Exception e) {
                    e.printStackTrace();
                    booleanResult.setResult(false);
                    booleanResult.setCode("Error");
                    ts.setRollbackOnly();
                    return booleanResult;
                }
                return booleanResult;
            }
        });
        return booleanResult;
    }

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    // branch mapping table xcfeng 20180615

    @Override
    public int getBranchMappingListCount(BranchMapping pr) {
        try {
            return countryDao.getBranchMappingListCount(pr);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<BranchMapping> getBranchMappingList(BranchMapping pr) {
        try {
            return countryDao.getBranchMappingList(pr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long createBranchMapping(BranchMapping pr) {
        try {
            return countryDao.createBranchMapping(pr);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateBranchMapping(BranchMapping pr) {
        try {
            return countryDao.updateBranchMapping(pr);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int deleteBranchMapping(BranchMapping pr) {
        try {
            return countryDao.deleteBranchMapping(pr);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
