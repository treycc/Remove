package com.jdp.hls.model.entiy;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * Description:TODO
 * Create Time:2018/8/21 0021 下午 4:36
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DTOImgInfo implements Serializable {
    private String Id;
    private String url;
    private String smallUrl;
    private String uriStr;
    private boolean checked;

    public String getSmallUrl() {
        return smallUrl;
    }

    public void setSmallUrl(String smallUrl) {
        this.smallUrl = smallUrl;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

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

    @Override
    public int hashCode() {
        if (!TextUtils.isEmpty(uriStr)) {
            return uriStr.hashCode();
        }else{
            return url.hashCode();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DTOImgInfo)) {
            return false;
        }
        DTOImgInfo imgInfo = (DTOImgInfo) obj;
        if (!TextUtils.isEmpty(uriStr)) {
            return uriStr.equals(imgInfo.getUriStr());
        }else{
            return url.equals(imgInfo.getUrl());
        }
    }
}
