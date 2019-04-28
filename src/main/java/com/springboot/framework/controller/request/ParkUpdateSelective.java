package com.springboot.framework.controller.request;

import java.math.BigDecimal;

public class ParkUpdateSelective {
    private Integer id;
    private String name;
    private String logo;
    private String location;
    private String address;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String introduction;
    private Byte sort;
    private Integer[] appIds2;
    private Integer[] appIds3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Byte getSort() {
        return sort;
    }

    public void setSort(Byte sort) {
        this.sort = sort;
    }

    public Integer[] getAppIds2() {
        return appIds2;
    }

    public void setAppIds2(Integer[] appIds2) {
        this.appIds2 = appIds2;
    }

    public Integer[] getAppIds3() {
        return appIds3;
    }

    public void setAppIds3(Integer[] appIds3) {
        this.appIds3 = appIds3;
    }
}
