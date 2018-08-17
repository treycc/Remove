package com.jdp.hls.model.entiy;

import android.net.Uri;

/**
 * Description:TODO
 * Create Time:2018/8/3 0003 上午 9:21
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ImgInfo {
    private String Id;
    private String FileUrl;
    private String SmallImgUrl;
    private Uri uri;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getSmallImgUrl() {
        return SmallImgUrl;
    }

    public void setSmallImgUrl(String smallImgUrl) {
        SmallImgUrl = smallImgUrl;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getFileUrl() {
        return FileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.FileUrl = fileUrl;
    }

    @Override
    public int hashCode() {
        if (uri != null) {
            return uri.hashCode();
        }else{
            return FileUrl.hashCode();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ImgInfo)) {
            return false;
        }
        ImgInfo imgInfo = (ImgInfo) obj;
        return uri.equals(imgInfo.uri);
    }

}
