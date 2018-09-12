package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/9/6 0006 下午 5:09
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Task {
    private String TaskTypeName;
    private int TaskType;
    private int Count;

    public String getTaskTypeName() {
        return TaskTypeName;
    }

    public void setTaskTypeName(String taskTypeName) {
        TaskTypeName = taskTypeName;
    }

    public int getTaskType() {
        return TaskType;
    }

    public void setTaskType(int taskType) {
        TaskType = taskType;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }
}
