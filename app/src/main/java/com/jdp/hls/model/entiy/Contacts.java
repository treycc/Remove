package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2019/1/28 0028 下午 3:40
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Contacts {
    private String name;
    private String mobile;
    private boolean isMain;

    public String getName() {
        return null == name ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return null == mobile ? "" : mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean isMain() {
        return isMain;
    }

    public void setMain(boolean main) {
        isMain = main;
    }
}
