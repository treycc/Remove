package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/9/6 0006 下午 5:09
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Task {
    private String TaskName;
    private String TaskType;
    private int Count;

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public String getTaskType() {
        return TaskType;
    }

    public void setTaskType(String taskType) {
        TaskType = taskType;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }
}
