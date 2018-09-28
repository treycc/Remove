package com.jdp.hls.model.entiy;

import android.support.annotation.NonNull;

/**
 * Description:TODO
 * Create Time:2018/9/6 0006 下午 5:09
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Task implements Comparable<Task>{
    private String TaskTypeName;
    private int TaskType;
    private int sortNo;
    private int Count;

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

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

    @Override
    public int compareTo(@NonNull Task o) {
        if(this.sortNo>o.getSortNo()){
            return 1;
        }
        else if(this.sortNo<o.getSortNo()){
            return -1;
        }
        else{
            return 0;
        }
    }
}
