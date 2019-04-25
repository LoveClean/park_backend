package com.springboot.framework.controller.request;

public class AppUpdateSelective {
    private Integer appId;
    private String name;
    private String icon;
    private Byte sort;
    private Integer[] parkIds2;
    private Integer[] parkIds3;

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Byte getSort() {
        return sort;
    }

    public void setSort(Byte sort) {
        this.sort = sort;
    }

    public Integer[] getParkIds2() {
        return parkIds2;
    }

    public void setParkIds2(Integer[] parkIds2) {
        this.parkIds2 = parkIds2;
    }

    public Integer[] getParkIds3() {
        return parkIds3;
    }

    public void setParkIds3(Integer[] parkIds3) {
        this.parkIds3 = parkIds3;
    }
}
