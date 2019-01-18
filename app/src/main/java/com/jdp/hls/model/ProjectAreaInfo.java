package com.jdp.hls.model;

import com.jdp.hls.model.entiy.AreaSelectorItem;
import com.jdp.hls.model.entiy.Project;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2019/1/17 0017 上午 10:54
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProjectAreaInfo {
    private boolean IsAvailable;
    private List<AreaSelectorItem> Authoritys;
    private List<Project> ProjectList;

    public boolean isAvailable() {
        return IsAvailable;
    }

    public void setAvailable(boolean available) {
        IsAvailable = available;
    }

    public List<AreaSelectorItem> getAuthoritys() {
        return Authoritys;
    }

    public void setAuthoritys(List<AreaSelectorItem> authoritys) {
        Authoritys = authoritys;
    }

    public List<Project> getProjectList() {
        return ProjectList;
    }

    public void setProjectList(List<Project> projectList) {
        ProjectList = projectList;
    }
}
