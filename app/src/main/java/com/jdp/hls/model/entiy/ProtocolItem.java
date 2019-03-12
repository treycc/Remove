package com.jdp.hls.model.entiy;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Description:TODO
 * Create Time:2019/3/12 0012 下午 3:00
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProtocolItem implements Serializable, Comparable<ProtocolItem> {
    private String Title;
    private int PayType;
    private String Value;
    private boolean IsAllowEdit;
    private int Sort;
    private String ParamName;

    public String getTitle() {
        return null == Title ? "" : Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getPayType() {
        return PayType;
    }

    public void setPayType(int payType) {
        PayType = payType;
    }

    public String getValue() {
        return null == Value ? "" : Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public boolean isAllowEdit() {
        return IsAllowEdit;
    }

    public void setAllowEdit(boolean allowEdit) {
        IsAllowEdit = allowEdit;
    }

    public int getSort() {
        return Sort;
    }

    public void setSort(int sort) {
        Sort = sort;
    }

    public String getParamName() {
        return null == ParamName ? "" : ParamName;
    }

    public void setParamName(String paramName) {
        ParamName = paramName;
    }

    @Override
    public int compareTo(@NonNull ProtocolItem o) {
        return this.getSort() - o.getSort();
    }
}
