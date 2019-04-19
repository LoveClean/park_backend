package com.springboot.framework.controller.request;

public class ParkInsertSelective {
    private String name;
    private String logo;
    private String location;
    private String address;
    private Byte sort;
    private Integer[] appIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Byte getSort() {
        return sort;
    }

    public void setSort(Byte sort) {
        this.sort = sort;
    }

    public Integer[] getAppIds() {
        return appIds;
    }

    public void setAppIds(Integer[] appIds) {
        this.appIds = appIds;
    }
}
