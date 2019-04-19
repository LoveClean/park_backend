package com.springboot.framework.vo;

import com.springboot.framework.dao.entity.Area;
import com.springboot.framework.dao.entity.Park;

import java.util.Date;

public class ParkVO {
    private Integer parkId;
    private String name;
    private String logo;
    private String location;
    private String address;
    private Byte sort;
    private String createBy;
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    private Byte status;

    private Integer areaId;
    private String areaCode;
    private String areaName;
    private String cityCode;

    public ParkVO() {
    }

    public ParkVO(Park park, Area area) {
        this.parkId = park.getId();
        this.name = park.getName();
        this.logo = park.getLogo();
        this.location = park.getLocation();
        this.address = park.getAddress();
        this.sort = park.getSort();
        this.createBy = park.getCreateBy();
        this.createDate = park.getCreateDate();
        this.updateBy = park.getUpdateBy();
        this.updateDate = park.getUpdateDate();
        this.status = park.getStatus();

        this.areaId = area.getId();
        this.areaCode = area.getAreaCode();
        this.areaName = area.getAreaName();
        this.cityCode = area.getCityCode();
    }

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
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

    public Byte getSort() {
        return sort;
    }

    public void setSort(Byte sort) {
        this.sort = sort;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
