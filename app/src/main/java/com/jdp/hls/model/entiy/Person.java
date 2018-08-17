package com.jdp.hls.model.entiy;

import java.io.Serializable;

/**
 * Description:TODO
 * Create Time:2018/8/17 0017 下午 4:51
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Person implements Serializable{
    private String name;
    private String moblie;
    private String idcard;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoblie() {
        return moblie;
    }

    public void setMoblie(String moblie) {
        this.moblie = moblie;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }
}
