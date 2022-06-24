package com.jingtong.platform.designReg.service;

import java.util.Date;
import java.util.List;

import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.customer.pojo.CustomerUser;
import com.jingtong.platform.customer.pojo.Disti_branch;
import com.jingtong.platform.designReg.pojo.DesignReg;
import com.jingtong.platform.designReg.pojo.DesignRegDetail;
import com.jingtong.platform.designReg.pojo.DesignRegDetailLog;
import com.jingtong.platform.designReg.pojo.Dict;
import com.jingtong.platform.product.pojo.Product;

public interface IDesignRegService {
    /**
     * 获取设计注册列表数
     * 
     * @param o
     * @return
     */
    public int getDesignRegListCount(DesignReg dr);

    /**
     * 获取设计注册信息列表
     * 
     * @param o
     * @return
     */
    public List<DesignReg> getDesignRegList(DesignReg dr);

    public List<DesignRegDetail> checkDesignReg(DesignRegDetail drd);

    /**
     * 根据ID获取设计注册信息
     * 
     * @param o
     * @return
     */
    public DesignReg getDesignRegById(DesignReg dr);

    /**
     * 设计注册信息新增
     * 
     * @param o
     * @return
     */
    public BooleanResult createDesignReg(DesignReg o, List<DesignRegDetail> odList);

    /**
     * 修改设计注册信息
     * 
     * @param o
     * @return
     */
    public BooleanResult updateDesignReg(DesignReg o, List<DesignRegDetail> odList, int state, String isUpdate);

    /**
     * 删除设计注册信息(逻辑删除)
     * 
     * @param o
     * @return
     */
    public int deleteDesignReg(DesignReg dr);

    /**
     * 获取设计注册明细信息列表
     * 
     * @param od
     * @return
     */
    public List<DesignRegDetail> getDesignRegDetailList(DesignRegDetail drd);

    /**
     * 设计注册明细信息新增
     * 
     * @param od
     * @return
     */
    public long createDesignRegDetail(DesignRegDetail drd);

    /**
     * 修改设计注册明细信息
     * 
     * @param od
     * @return
     */
    public int updateDesignRegDetail(DesignRegDetail drd);

    public int updateDesignRegDetailDesignStatus(DesignRegDetail drd);

    /**
     * 删除设计注册明细信息(物理删除)
     * 
     * @param od
     * @return
     */
    public int deleteDesignRegDetail(DesignRegDetail drd);

    public List<DesignRegDetail> checkDesignRegDetailList(DesignRegDetail drd);

    public int checkDesignRegDetailCount(DesignRegDetail drd);

    public int auditDRD(DesignRegDetail drd);

    /**
     * 获取自动生成单号
     */
    public String getSystemIdPrc();

    int setCheck(DesignRegDetail drd);

    public List<DesignRegDetail> outPutDR(DesignRegDetail drd);

    void updateDRState();

    public List<Dict> getDictOfWeen(Dict m);

    String getDRECCountryOrg(DesignReg dr);

    List<DesignRegDetail> getDRDbyIds(DesignRegDetail drd);

    public List<CustomerUser> getDRAuditSale(DesignReg q);

    public List<DesignRegDetail> getRoleUserByMenuId(DesignRegDetail drd);
    
    public int checkDRExpireByDrNum(DesignRegDetail drd);
    
    public List<DesignRegDetailLog> getDrLogList(DesignRegDetail drd);
    
    public Long createDrLog(DesignRegDetailLog log);
    
    public Product getProduct(DesignRegDetail drd);
    
    public boolean checkNewCustomer(DesignReg dr);
    
    public boolean checkNewProduct(Product product, Date drTime);
    
    public DesignRegDetail getDRTypeByItmeValue(DesignRegDetail drd);
    
    public int updateDesignRegDetailDrType(final DesignRegDetail drd);
    
    public DesignReg getDesignRegByDesignRegDetail(final DesignRegDetail drd);

    public List<Disti_branch> getDistiBranchList(Disti_branch db);
    
    public DesignRegDetail getDRDbyId(DesignRegDetail drd);

    public int updateDrdRemark(DesignRegDetail drd);
    
    public Disti_branch getDistAlias(Disti_branch disti);
    
    public Disti_branch getDistBranchAlias(Disti_branch distibranch);
}
