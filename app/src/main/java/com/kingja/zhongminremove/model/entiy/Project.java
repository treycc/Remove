package com.kingja.zhongminremove.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/7/27 0027 上午 10:13
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Project {
    private String name;
    private String address;
    private String year;
    private String person;

    public Project(String name, String address, String year, String person) {
        this.name = name;
        this.address = address;
        this.year = year;
        this.person = person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
