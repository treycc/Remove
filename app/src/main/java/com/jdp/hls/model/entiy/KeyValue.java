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
    private String ValueDesc;
    private int Type;
    private int InterfaceId;
    private int UseItemId;
    private int ItemId;
    private boolean HasDetail;

    public int getItemId() {
        return ItemId;
    }

    public void setItemId(int itemId) {
        ItemId = itemId;
    }

    public String getValueDesc() {
        return null == ValueDesc ? "" : ValueDesc;
    }

    public void setValueDesc(String valueDesc) {
        ValueDesc = valueDesc;
    }

    public int getUseItemId() {
        return UseItemId;
    }

    public void setUseItemId(int useItemId) {
        UseItemId = useItemId;
    }

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
