package com.jingtong.platform.designReg.dao;

import java.util.List;

import com.jingtong.platform.customer.pojo.CustomerUser;
import com.jingtong.platform.customer.pojo.Disti_branch;
import com.jingtong.platform.designReg.pojo.DesignReg;
import com.jingtong.platform.designReg.pojo.DesignRegDetail;
import com.jingtong.platform.designReg.pojo.DesignRegDetailLog;
import com.jingtong.platform.designReg.pojo.DesignWinAudit;
import com.jingtong.platform.designReg.pojo.Dict;
import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.endCustomer.pojo.EndCustomer;
import com.jingtong.platform.pos.pojo.Pos;
import com.jingtong.platform.product.pojo.Product;

public interface IDesignRegDao {
    /**
     * 获取设计注册列表数
     * 
     * @param c
     * @return
     */
    public int getDesignRegListCount(DesignReg dr);

    /**
     * 获取设计注册信息列表
     * 
     * @param c
     * @return
     */
    public List<DesignReg> getDesignRegList(DesignReg dr);

    public List<DesignRegDetail> checkDesignReg(DesignRegDetail drd);

    /**
     * 根据ID获取设计注册信息
     * 
     * @param c
     * @return
     */
    public DesignReg getDesignRegById(DesignReg dr);

    /**
     * 设计注册信息新增
     * 
     * @param p
     * @return
     */
    public long createDesignReg(DesignReg dr);

    /**
     * 修改设计注册信息
     * 
     * @param o
     * @return
     */
    public int updateDesignReg(DesignReg dr);

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
    public long createDesignRegDetail(DesignRegDetail drd, DesignReg dr);

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

    public int setCheck(DesignRegDetail drd);

    public int setDRNum(DesignReg dr);

    public List<DesignRegDetail> outPutDR(DesignRegDetail drd);

    void updateDRState();

    public List<Dict> getDictOfWeen(Dict m);

    public String getDRECCountryOrg(DesignReg dr);

    public List<DesignRegDetail> getDRDbyIds(DesignRegDetail drd);

    public List<CustomerUser> getDRAuditSale(DesignReg dr);

    long createDesignWinAudit(DesignWinAudit designWinAudit);

    public List<DesignRegDetail> getRoleUserByMenuId(DesignRegDetail drd);

    public DesignRegDetail getDRDbyId(DesignRegDetail drd);

    public int checkDRExpireByDrNum(DesignRegDetail drd);

    public List<DesignRegDetailLog> getDrLogList(DesignRegDetail drd);

    public int getDrLogListCount(DesignRegDetail drd);

    public Long createDesignRegDetailLog(DesignRegDetailLog log);

    public List<Pos> getCustomerPos(Pos pos);

    public Product getProductByMaterialIdOrName(Product p);
    
    public DesignRegDetail getDRTypeByItmeValue(DesignRegDetail drd);
    
    public int updateDesignRegDetailDrType(DesignRegDetail drd);
    
    public DesignReg getDesignRegByDesignRegDetail(DesignRegDetail drd);
    
    public EndCustomer getEndCustomerByCode(EndCustomer ec);

    public List<Disti_branch> getDistiBranchList(Disti_branch db);
    
    public int updateDesignRegDetailRemark(DesignRegDetail drd);

    public CmsTbDict getCmsTbDict(CmsTbDict direct);
    
    public List<Disti_branch> getDistiAliasList(Disti_branch db);
    
    public List<Disti_branch> getDistiBranchAliasList(Disti_branch db);
}
