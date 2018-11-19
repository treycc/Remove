package com.jdp.hls.event;

import com.jdp.hls.model.entiy.ProjectItem;

/**
 * Description:TODO
 * Create Time:2018/11/19 0019 下午 5:59
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AddProjectEvent {
    private ProjectItem projectItem;

    public ProjectItem getProjectItem() {
        return projectItem;
    }

    public void setProjectItem(ProjectItem projectItem) {
        this.projectItem = projectItem;
    }

    public AddProjectEvent(ProjectItem projectItem) {
        this.projectItem = projectItem;
    }
}
