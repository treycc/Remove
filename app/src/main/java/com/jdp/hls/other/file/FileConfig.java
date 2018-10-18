package com.jdp.hls.other.file;

import java.io.Serializable;

/**
 * Description:TODO
 * Create Time:2018/10/18 0018 上午 9:27
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class FileConfig implements Serializable {
    private String fileType;
    private String buildingId;
    private String buildingType;

    public FileConfig(int fileType, String buildingId, String buildingType) {
        this(String.valueOf(fileType),  buildingId,  buildingType);
    }
    public FileConfig(String fileType, String buildingId, String buildingType) {
        this.fileType =String.valueOf(fileType) ;
        this.buildingId = buildingId;
        this.buildingType = buildingType;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
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