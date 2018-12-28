package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/12/12 0012 上午 9:17
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProjectFacade {
    private String ProjectId;
    private String OldVRUrl;
    private String NewVRUrl;
    private String HouseIchnographyVRUrl;
    private List<ImgInfo> OldFiles;
    private List<ImgInfo> NewFiles;
    private List<ImgInfo> HouseIchnographyFiles;

    public String getHouseIchnographyVRUrl() {
        return null == HouseIchnographyVRUrl ? "" : HouseIchnographyVRUrl;
    }

    public void setHouseIchnographyVRUrl(String houseIchnographyVRUrl) {
        HouseIchnographyVRUrl = houseIchnographyVRUrl;
    }

    public List<ImgInfo> getHouseIchnographyFiles() {
        return HouseIchnographyFiles;
    }

    public void setHouseIchnographyFiles(List<ImgInfo> houseIchnographyFiles) {
        HouseIchnographyFiles = houseIchnographyFiles;
    }

    public String getProjectId() {
        return null == ProjectId ? "" : ProjectId;
    }

    public void setProjectId(String projectId) {
        ProjectId = projectId;
    }

    public String getOldVRUrl() {
        return null == OldVRUrl ? "" : OldVRUrl;
    }

    public void setOldVRUrl(String oldVRUrl) {
        OldVRUrl = oldVRUrl;
    }

    public String getNewVRUrl() {
        return null == NewVRUrl ? "" : NewVRUrl;
    }

    public void setNewVRUrl(String newVRUrl) {
        NewVRUrl = newVRUrl;
    }

    public List<ImgInfo> getOldFiles() {
        return OldFiles;
    }

    public void setOldFiles(List<ImgInfo> oldFiles) {
        OldFiles = oldFiles;
    }

    public List<ImgInfo> getNewFiles() {
        return NewFiles;
    }

    public void setNewFiles(List<ImgInfo> newFiles) {
        NewFiles = newFiles;
    }
}
