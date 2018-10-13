package com.jdp.hls.model.entiy;

import java.io.Serializable;

/**
 * Description:TODO
 * Create Time:2018/10/10 0010 下午 8:04
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class UnRecordBuilding implements Serializable {

    /**
     * Id : 1
     * AirCheckId : 3
     * Position : 上
     * Layer : 0
     * Area : 0
     * Pic84 :
     * Pic94 :
     * Pic2000 : 面积是多少
     */

    private int Id;
    private int AirCheckId;
    private String Position;
    private int Layer;
    private double Area;
    private String Pic84;
    private String Pic94;
    private String Pic2000;
    private boolean edited;

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getAirCheckId() {
        return AirCheckId;
    }

    public void setAirCheckId(int AirCheckId) {
        this.AirCheckId = AirCheckId;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public int getLayer() {
        return Layer;
    }

    public void setLayer(int Layer) {
        this.Layer = Layer;
    }

    public double getArea() {
        return Area;
    }

    public void setArea(double Area) {
        this.Area = Area;
    }

    public String getPic84() {
        return Pic84;
    }

    public void setPic84(String Pic84) {
        this.Pic84 = Pic84;
    }

    public String getPic94() {
        return Pic94;
    }

    public void setPic94(String Pic94) {
        this.Pic94 = Pic94;
    }

    public String getPic2000() {
        return Pic2000;
    }

    public void setPic2000(String Pic2000) {
        this.Pic2000 = Pic2000;
    }
}
