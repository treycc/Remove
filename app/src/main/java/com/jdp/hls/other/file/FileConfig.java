package com.jdp.hls.other.file;

import java.io.Serializable;

/**
 * Description:TODO
 * Create Time:2018/10/18 0018 上午 9:27
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class FileConfig implements Serializable {
    private int fileType;
    private String buildingId;
    private String buildingType;
    private String masterId;
    private boolean editable=true;

    public String getMasterId() {
        return null == masterId ? "" : masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }
    public FileConfig(int fileType, String buildingId, String buildingType) {
        this(fileType, buildingId, buildingType, "", true);
    }

    public FileConfig(int fileType, String buildingId, String buildingType, boolean editable) {
        this(fileType, buildingId, buildingType, "", editable);
    }

    public FileConfig(int fileType, String buildingId, String buildingType, String masterId) {
        this(fileType, buildingId, buildingType, masterId, true);
    }

    public FileConfig(int fileType, String buildingId, String buildingType, String masterId, boolean editable) {
        this.fileType = fileType;
        this.buildingId = buildingId;
        this.buildingType = buildingType;
        this.masterId = masterId;
        this.editable = editable;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }




    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }
}