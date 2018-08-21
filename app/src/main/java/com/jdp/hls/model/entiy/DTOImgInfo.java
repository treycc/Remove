package com.jdp.hls.model.entiy;

import java.io.Serializable;

/**
 * Description:TODO
 * Create Time:2018/8/21 0021 下午 4:36
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DTOImgInfo implements Serializable {
    private String url;
    private String uriStr;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUriStr() {
        return uriStr;
    }

    public void setUriStr(String uriStr) {
        this.uriStr = uriStr;
    }
}
