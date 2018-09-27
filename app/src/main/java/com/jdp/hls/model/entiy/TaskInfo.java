package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/27 0027 上午 10:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TaskInfo {
    private List<Business>MyTaskList;
    private Auth Auth;

    public List<Business> getMyTaskList() {
        return MyTaskList;
    }

    public void setMyTaskList(List<Business> myTaskList) {
        MyTaskList = myTaskList;
    }

    public com.jdp.hls.model.entiy.Auth getAuth() {
        return Auth;
    }

    public void setAuth(com.jdp.hls.model.entiy.Auth auth) {
        Auth = auth;
    }
}
