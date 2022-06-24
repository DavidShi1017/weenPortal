package com.jingtong.platform.ediDisti.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;

public class EdiDisti extends SearchInfo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private long id;
    private String sold_to;
    private String disti_name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSold_to() {
        return sold_to;
    }

    public void setSold_to(String sold_to) {
        this.sold_to = sold_to;
    }

    public String getDisti_name() {
        return disti_name;
    }

    public void setDisti_name(String disti_name) {
        this.disti_name = disti_name;
    }
}
