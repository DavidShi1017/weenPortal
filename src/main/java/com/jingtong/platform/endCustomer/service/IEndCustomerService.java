package com.jingtong.platform.endCustomer.service;

import java.util.List;

import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.endCustomer.pojo.ECAlias;
import com.jingtong.platform.endCustomer.pojo.EndCustomer;

/** 2018/11/06 Add Change alias to EC */
public interface IEndCustomerService {
    public int deleteEndCustomer(EndCustomer ec);

    public List<ECAlias> searchECAlias(EndCustomer c);

    /**
     * 获取终端页面查询列表数
     * 
     * @param c
     * @return
     */
    public int searchEndCustomerListCount(EndCustomer ec);

    /**
     * 获取终端客户页面查询列表
     * 
     * @param c
     * @return
     */
    public List<EndCustomer> searchEndCustomerList(EndCustomer ec);

    /**
     * 获取终端combobox列表数
     * 
     * @param c
     * @return
     */
    public int getEndCustomerListCount(EndCustomer ec);

    /**
     * 获取终端客户combobox列表
     * 
     * @param c
     * @return
     */
    public List<EndCustomer> getEndCustomerList(EndCustomer ec);

    public List<EndCustomer> checkEndCustomer(EndCustomer ec);

    /**
     * 根据ID获取终端客户信息
     * 
     * @param c
     * @return
     */
    public EndCustomer getEndCustomerById(EndCustomer ec);

    public long getECIdPrc();

    /**
     * 设置编码
     * 
     * @param ec
     * @return
     */
    public int setECCode(EndCustomer ec);

    /**
     * 标记为已check
     * 
     * @param ec
     * @return
     */
    public int checkEC(EndCustomer ec);

    /**
     * 终端客户信息注册（新增）
     * 
     * @param ec
     * @return
     */
    public long createEndCustomer(EndCustomer ec);

    /**
     * 终端客户信息修改
     * 
     * @param ec
     * @return
     */
    public int updateEndCustomer(EndCustomer ec);

    /**
     * 终端客户审核
     * 
     * @param ec
     * @return
     */
    public int auditEndCustomer(EndCustomer ec);

    public int setCheck(EndCustomer ec);

    // **************************************别名模块***************************************//
    public List<ECAlias> getECAliasList(ECAlias ea);

    public int getECAliasListCount(ECAlias ea);

    public int getCountByAliasName(ECAlias ea);

    public long createECAlias(ECAlias ea);

    public int updateECAlias(ECAlias ea);

    public int deleteECAliasById(ECAlias ea);

    public ECAlias getECAliasById(ECAlias ea);

    public EndCustomer getEndCustomerByCode(EndCustomer c);

    public int updateQuoteECid(ECAlias ea);

    public int updateQuotePCid(ECAlias ea);

    public int updateDRECid(ECAlias ea);

    public BooleanResult changeEC(ECAlias ea, EndCustomer ec);

    int changeEC1(ECAlias ea);

    public List<EndCustomer> pendingData(EndCustomer c);

    // 2018/11/06 Add Start
    /**
     * Change Alias to EC
     * 
     * @param ea
     * @param ec
     * @param ec
     * @return
     */
    public BooleanResult changeAlias(ECAlias ea);
    // 2018/11/06 Add End

    public List<CmsTbDict> getNewHierarchyList(CmsTbDict nh);

}
