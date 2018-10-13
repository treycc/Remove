package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/9/11 0011 上午 10:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Dict {
    private int RowNum;
    private int ConfigType;
    private int TypeId;
    private String TypeName;
    private String ConfigTypeDesc;
    private int ParentId;
    private double ClassValue;

    public int getParentId() {
        return ParentId;
    }

    public void setParentId(int parentId) {
        ParentId = parentId;
    }

    public double getClassValue() {
        return ClassValue;
    }

    public void setClassValue(double classValue) {
        ClassValue = classValue;
    }

    public int getRowNum() {
        return RowNum;
    }

    public void setRowNum(int rowNum) {
        RowNum = rowNum;
    }

    public int getConfigType() {
        return ConfigType;
    }

    public void setConfigType(int configType) {
        ConfigType = configType;
    }

    public int getTypeId() {
        return TypeId;
    }

    public void setTypeId(int typeId) {
        TypeId = typeId;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public String getConfigTypeDesc() {
        return ConfigTypeDesc;
    }

    public void setConfigTypeDesc(String configTypeDesc) {
        ConfigTypeDesc = configTypeDesc;
    }
}
