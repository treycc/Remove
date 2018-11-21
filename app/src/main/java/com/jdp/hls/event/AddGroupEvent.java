package com.jdp.hls.event;

import com.jdp.hls.model.entiy.Group;

/**
 * Description:TODO
 * Create Time:2018/11/21 0021 下午 9:11
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AddGroupEvent {
    private Group group;

    public AddGroupEvent(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
