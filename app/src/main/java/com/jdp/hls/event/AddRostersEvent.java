package com.jdp.hls.event;

import com.jdp.hls.model.entiy.Roster;

/**
 * Description:TODO
 * Create Time:2018/8/13 0013 上午 11:59
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AddRostersEvent {
    private Roster roster;

    public AddRostersEvent(Roster roster) {
        this.roster=roster;
    }

    public Roster getRoster() {
        return roster;
    }

    public void setRoster(Roster roster) {
        this.roster = roster;
    }
}
