package com.jdp.hls.greendaobean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Description:TODO
 * Create Time:2018/9/11 0011 上午 10:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Entity
public class TDict {
    @Id(autoincrement = true)
    private Long id;
    private int RowNum;
    private int ConfigType;
    private int TypeId;
    private String TypeName;
    private String ConfigTypeDesc;
    private int ParentId;
    private double ClassValue;
    @Generated(hash = 306383593)
    public TDict(Long id, int RowNum, int ConfigType, int TypeId, String TypeName,
            String ConfigTypeDesc, int ParentId, double ClassValue) {
        this.id = id;
        this.RowNum = RowNum;
        this.ConfigType = ConfigType;
        this.TypeId = TypeId;
        this.TypeName = TypeName;
        this.ConfigTypeDesc = ConfigTypeDesc;
        this.ParentId = ParentId;
        this.ClassValue = ClassValue;
    }
    @Generated(hash = 1108334303)
    public TDict() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getRowNum() {
        return this.RowNum;
    }
    public void setRowNum(int RowNum) {
        this.RowNum = RowNum;
    }
    public int getConfigType() {
        return this.ConfigType;
    }
    public void setConfigType(int ConfigType) {
        this.ConfigType = ConfigType;
    }
    public int getTypeId() {
        return this.TypeId;
    }
    public void setTypeId(int TypeId) {
        this.TypeId = TypeId;
    }
    public String getTypeName() {
        return this.TypeName;
    }
    public void setTypeName(String TypeName) {
        this.TypeName = TypeName;
    }
    public String getConfigTypeDesc() {
        return this.ConfigTypeDesc;
    }
    public void setConfigTypeDesc(String ConfigTypeDesc) {
        this.ConfigTypeDesc = ConfigTypeDesc;
    }
    public int getParentId() {
        return this.ParentId;
    }
    public void setParentId(int ParentId) {
        this.ParentId = ParentId;
    }
    public double getClassValue() {
        return this.ClassValue;
    }
    public void setClassValue(double ClassValue) {
        this.ClassValue = ClassValue;
    }

}
