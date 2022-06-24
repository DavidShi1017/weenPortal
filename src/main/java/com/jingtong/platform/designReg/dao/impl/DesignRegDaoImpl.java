package com.jingtong.platform.designReg.dao.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.customer.pojo.CustomerUser;
import com.jingtong.platform.customer.pojo.Disti_branch;
import com.jingtong.platform.designReg.dao.IDesignRegDao;
import com.jingtong.platform.designReg.pojo.DesignReg;
import com.jingtong.platform.designReg.pojo.DesignRegDetail;
import com.jingtong.platform.designReg.pojo.DesignRegDetailLog;
import com.jingtong.platform.designReg.pojo.DesignWinAudit;
import com.jingtong.platform.designReg.pojo.Dict;
import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.endCustomer.pojo.EndCustomer;
import com.jingtong.platform.pos.pojo.Pos;
import com.jingtong.platform.product.pojo.Product;

public class DesignRegDaoImpl extends BaseDaoImpl<Object> implements IDesignRegDao {

    @Override
    public int getDesignRegListCount(DesignReg dr) {
        return (Integer) getSqlMapClientTemplate().queryForObject("designReg.getDesignRegListCount", dr);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DesignReg> getDesignRegList(DesignReg dr) {
        return (List<DesignReg>) getSqlMapClientTemplate().queryForList("designReg.getDesignRegList", dr);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DesignRegDetail> checkDesignReg(DesignRegDetail drd) {
        return (List<DesignRegDetail>) getSqlMapClientTemplate().queryForList("designReg.checkDesignReg", drd);
    }

    @Override
    public DesignReg getDesignRegById(DesignReg dr) {
        return (DesignReg) getSqlMapClientTemplate().queryForObject("designReg.getDesignRegById", dr);
    }

    @Override
    public long createDesignReg(DesignReg dr) {
        return (Long) getSqlMapClientTemplate().insert("designReg.createDesignReg", dr);
    }

    @Override
    public int updateDesignReg(DesignReg dr) {
        return (Integer) getSqlMapClientTemplate().update("designReg.updateDesignReg", dr);
    }

    @Override
    public int setDRNum(DesignReg dr) {
        return (Integer) getSqlMapClientTemplate().update("designReg.setDRNum", dr);
    }

    @Override
    public int deleteDesignReg(DesignReg dr) {
        return (Integer) getSqlMapClientTemplate().delete("designReg.deleteDesignReg", dr);
    }

    @Override
    public long createDesignRegDetail(DesignRegDetail drd, DesignReg dr) {
        Long dr_detail_id = (Long) getSqlMapClientTemplate().insert("designReg.createDesignRegDetail", drd);
        try {
            DesignRegDetailLog log = new DesignRegDetailLog(drd);
            log.setType("0");
            log.setDr_detail_id(dr_detail_id.toString());
            log.setUsage_amount(drd.getUsage_amount());
            log.setMp_schedule(dr.getMp_schedule());

            getSqlMapClientTemplate().insert("designReg.createDesignRegDetailLog", log);
        } catch (Exception e) {
            logger.error(e);
        }
        return dr_detail_id;
    }

    @Override
    public int updateDesignRegDetail(DesignRegDetail drd) {
        return (Integer) getSqlMapClientTemplate().update("designReg.updateDesignRegDetail", drd);
    }

    @Override
    public int updateDesignRegDetailDesignStatus(DesignRegDetail drd) {

        DesignWinAudit designWindAudit = new DesignWinAudit();
        designWindAudit.setDetail_id(drd.getId());
        designWindAudit.setUser_id(drd.getCreate_userId());
        designWindAudit.setUser_account(drd.getCreate_userName());
        if (drd.getSaler_design_status() == 1L) {
            designWindAudit.setAudit_info("commit");
        } else if (drd.getSaler_design_status() == 2L || drd.getSaler_design_status() == 4L
                || drd.getSaler_design_status() == 6L) {
            designWindAudit.setAudit_info("approve");
        } else {
            designWindAudit.setAudit_info("reject");
            getSqlMapClientTemplate().update("designReg.deleteDesignWinAudit", designWindAudit);
        }
        designWindAudit.setAudit_node(drd.getSaler_design_status().toString());
        getSqlMapClientTemplate().insert("designReg.createDesignWinAudit", designWindAudit);

        drd.setEnd_date(null);

        DesignRegDetail checkData = getDRDbyId(drd);
        DesignReg dr = new DesignReg();
        dr.setId(checkData.getMain_id());
        dr = getDesignRegById(dr);

        // Turn Win Expire Date + 2 Year
        if (drd.getSaler_design_status() == 4L) {
            Calendar rightNow = Calendar.getInstance();
            rightNow.add(Calendar.YEAR, 2);
            dr.setEnd_date(rightNow.getTime());
            dr.setLatest_userId(drd.getCreate_userId());
            getSqlMapClientTemplate().update("designReg.updateDesignRegEndDate", dr);

            drd.setEnd_date(rightNow.getTime());
        }
        int count = getSqlMapClientTemplate().update("designReg.updateDesignRegDetailDesignStatus", drd);
        try {
            checkData = getDRDbyId(drd);
            DesignRegDetailLog log = new DesignRegDetailLog(checkData);
            if (drd.getSaler_design_status() == 1L) {
                log.setType("11");
            } else if (drd.getSaler_design_status() == 2L) {
                log.setType("13");
            } else if (drd.getSaler_design_status() == 4L) {
                log.setType("15");
            } else if (drd.getSaler_design_status() == 9L) {
                log.setType("12");
            } else if (drd.getSaler_design_status() == 3L) {
                log.setType("14");
            } else if (drd.getSaler_design_status() == 5L) {
                log.setType("16");
            }
            log.setProject_state(drd.getProject_state());
            log.setDr_detail_id(Long.toString(checkData.getId()));
            log.setCreate_userId(drd.getCreate_userId());
            log.setUsage_amount(checkData.getUsage_amount());
            log.setMp_schedule(dr.getMp_schedule());
            log.setWeencomments(drd.getWeencomments());
            getSqlMapClientTemplate().insert("designReg.createDesignRegDetailLog", log);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public long createDesignWinAudit(DesignWinAudit designWinAudit) {
        return (Long) getSqlMapClientTemplate().insert("designReg.createDesignWinAudit", designWinAudit);
    }

    @Override
    public int deleteDesignRegDetail(DesignRegDetail drd) {
        return (Integer) getSqlMapClientTemplate().delete("designReg.deleteDesignRegDetail", drd);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DesignRegDetail> getDesignRegDetailList(DesignRegDetail drd) {
        return (List<DesignRegDetail>) getSqlMapClientTemplate().queryForList("designReg.getDesignRegDetailList", drd);
    }

    /**
     * 8y>]SAP;qH!AwK.ID
     */
    @Override
    public String getSystemIdPrc() {
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("incount", 6 + "");
        parameter.put("intype", 4 + "");
        parameter.put("RESULTCODE", "");

        this.getSqlMapClientTemplate().queryForList("designReg.getSystemIdPrc", parameter);
        String message = (String) parameter.get("RESULTCODE");
        return message;
    }

    /**
     * J5J18|PB1mM7W4L,
     */
    @Override
    public void updateDRState() {
        this.getSqlMapClientTemplate().update("designReg.updateDRState");
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DesignRegDetail> checkDesignRegDetailList(DesignRegDetail drd) {
        return (List<DesignRegDetail>) getSqlMapClientTemplate().queryForList("designReg.checkDesignRegDetailList",
                drd);
    }

    @Override
    public int checkDesignRegDetailCount(DesignRegDetail drd) {
        return (Integer) getSqlMapClientTemplate().queryForObject("designReg.checkDesignRegDetailListCount", drd);
    }

    @Override
    public int auditDRD(DesignRegDetail drd) {
        return (Integer) getSqlMapClientTemplate().update("designReg.auditDRD", drd);

    }

    @Override
    public int setCheck(DesignRegDetail drd) {
        return (Integer) getSqlMapClientTemplate().update("designReg.setCheck", drd);

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DesignRegDetail> outPutDR(DesignRegDetail drd) {
        return (List<DesignRegDetail>) getSqlMapClientTemplate().queryForList("designReg.getDRDListNoPage", drd);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Dict> getDictOfWeen(Dict m) {
        return (List<Dict>) getSqlMapClientTemplate().queryForList("designReg.getDictOfWeen", m);
    }

    @Override
    public String getDRECCountryOrg(DesignReg dr) {
        return (String) getSqlMapClientTemplate().queryForObject("designReg.getDRECCountryOrg", dr);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DesignRegDetail> getDRDbyIds(DesignRegDetail drd) {
        return (List<DesignRegDetail>) getSqlMapClientTemplate().queryForList("designReg.getDRDbyIds", drd);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CustomerUser> getDRAuditSale(DesignReg dr) {
        return (List<CustomerUser>) getSqlMapClientTemplate().queryForList("designReg.getDRAuditSale", dr);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DesignRegDetail> getRoleUserByMenuId(DesignRegDetail drd) {
        return (List<DesignRegDetail>) getSqlMapClientTemplate().queryForList("designReg.getRoleUserByMenuId", drd);
    }

    @Override
    public DesignRegDetail getDRDbyId(DesignRegDetail drd) {
        return (DesignRegDetail) getSqlMapClientTemplate().queryForObject("designReg.getDRDbyId", drd);
    }

    @Override
    public int checkDRExpireByDrNum(DesignRegDetail drd) {
        return (Integer) getSqlMapClientTemplate().queryForObject("designReg.checkDRExpireByDrNum", drd);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DesignRegDetailLog> getDrLogList(DesignRegDetail drd) {
        return (List<DesignRegDetailLog>) getSqlMapClientTemplate().queryForList("designReg.getDesignRegDetailLogList",
                drd);
    }

    @Override
    public int getDrLogListCount(DesignRegDetail drd) {
        return (Integer) getSqlMapClientTemplate().queryForObject("designReg.getDesignRegDetailLogListCount", drd);
    }

    @Override
    public Long createDesignRegDetailLog(DesignRegDetailLog log) {
        return (Long) getSqlMapClientTemplate().insert("designReg.createDesignRegDetailLog", log);
    }

    @Override
    public List<Pos> getCustomerPos(Pos pos) {
        return (List<Pos>) getSqlMapClientTemplate().queryForList("pos.getCustomerPos", pos);
    }

    @Override
    public Product getProductByMaterialIdOrName(Product p) {
        return (Product) getSqlMapClientTemplate().queryForObject("product.getProductByMaterialIdOrName", p);
    }
    
    @Override
    public DesignRegDetail getDRTypeByItmeValue(DesignRegDetail drd) {
        return (DesignRegDetail) getSqlMapClientTemplate().queryForObject("designReg.getDRTypeByItmeValue", drd);
    }
    
    @Override
    public int updateDesignRegDetailDrType(DesignRegDetail drd) {
        return getSqlMapClientTemplate().update("designReg.updateDesignRegDetailDrType", drd);
    }
    
    @Override
    public DesignReg getDesignRegByDesignRegDetail(DesignRegDetail drd) {
        return (DesignReg) getSqlMapClientTemplate().queryForObject("designReg.getDesignRegByDesignRegDetail", drd);
    }
    
    @Override
    public EndCustomer getEndCustomerByCode(EndCustomer ec) {
        return (EndCustomer) getSqlMapClientTemplate().queryForObject("endCustomer.getEndCustomerByCode",ec);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Disti_branch> getDistiBranchList(Disti_branch db) {
        return getSqlMapClientTemplate().queryForList("customer.getDistiBranchList", db);
    }
    
    @Override
    public int updateDesignRegDetailRemark(DesignRegDetail drd) {
        return getSqlMapClientTemplate().update("designReg.updateDesignRegDetailRemark", drd);
    }
    
    @Override
    public CmsTbDict getCmsTbDict(CmsTbDict direct) {
        return (CmsTbDict) getSqlMapClientTemplate().queryForObject("dict.getCmsTbDict", direct);
    }
    
    @Override
    public List<Disti_branch> getDistiAliasList(Disti_branch db) {
        return getSqlMapClientTemplate().queryForList("customer.getDistiAliasByName", db);
    }

    @Override
    public List<Disti_branch> getDistiBranchAliasList(Disti_branch db) {
        return getSqlMapClientTemplate().queryForList("customer.getDistiBranchAliasByName", db);
    }
}
