package com.jingtong.platform.designReg.pojo;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;

public class DesignRegDetailLog extends DesignRegDetail {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public DesignRegDetailLog() {
    }
  
    public DesignRegDetailLog(DesignRegDetail drd) throws IllegalAccessException, InvocationTargetException {
        BeanUtils.copyProperties(this, drd);
    }
    
    // Designreg detail id
    private String dr_detail_id;
    
    private String type;
    
    private String usage_amount;
    
    private Date mp_schedule;
    
    private String createTime;
    
    private long logId;

    public String getDr_detail_id() {
        return dr_detail_id;
    }

    public void setDr_detail_id(String dr_detail_id) {
        this.dr_detail_id = dr_detail_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsage_amount() {
        return usage_amount;
    }

    public void setUsage_amount(String usage_amount) {
        this.usage_amount = usage_amount;
    }

    public Date getMp_schedule() {
        return mp_schedule;
    }

    public void setMp_schedule(Date mp_schedule) {
        this.mp_schedule = mp_schedule;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public long getLogId() {
        return logId;
    }

    public void setLogId(long logId) {
        this.logId = logId;
    }
}
