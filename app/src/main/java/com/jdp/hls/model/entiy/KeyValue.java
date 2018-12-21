package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/11/30 0030 下午 4:08
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class KeyValue {
    private String Name;
    private String Value;
    private int Type;
    private int InterfaceId;
    private boolean HasDetail;

    public int getInterfaceId() {
        return InterfaceId;
    }

    public void setInterfaceId(int interfaceId) {
        InterfaceId = interfaceId;
    }

    public boolean isHasDetail() {
        return HasDetail;
    }

    public void setHasDetail(boolean hasDetail) {
        HasDetail = hasDetail;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setValue(String value) {
        Value = value;
    }

    public void setType(int buildingType) {
        Type = buildingType;
    }

    public String getName() {
        return null == Name ? "" : Name;
    }

    public String getValue() {
        return null == Value ? "" : Value;
    }

    public int getType() {
        return Type;
    }
}
