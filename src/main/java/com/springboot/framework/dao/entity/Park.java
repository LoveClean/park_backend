package com.springboot.framework.dao.entity;

import com.springboot.framework.dto.ParkDTO;

import java.math.BigDecimal;
import java.util.Date;

public class Park {
    private Integer id;

    private String name;

    private String logo;

    private String location;

    private String address;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private String introduction;

    private Byte sort;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private Byte status;

    public Park() {
    }

    public Park(ParkDTO parkDTO) {
        this.id = parkDTO.getId();
        this.name = parkDTO.getName();
        this.logo = parkDTO.getLogo();
        this.location = parkDTO.getLocation();
        this.address = parkDTO.getAddress();
        this.longitude = parkDTO.getLongitude();
        this.latitude = parkDTO.getLatitude();
        this.introduction = parkDTO.getIntroduction();
        this.sort = parkDTO.getSort();
        this.createBy = parkDTO.getCreateBy();
        this.createDate = parkDTO.getCreateDate();
        this.updateBy = parkDTO.getUpdateBy();
        this.updateDate = parkDTO.getUpdateDate();
        this.status = parkDTO.getStatus();
    }

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
        this.name = name == null ? null : name.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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
        this.introduction = introduction == null ? null : introduction.trim();
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
        this.createBy = createBy == null ? null : createBy.trim();
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
        this.updateBy = updateBy == null ? null : updateBy.trim();
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
}