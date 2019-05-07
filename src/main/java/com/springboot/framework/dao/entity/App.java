package com.springboot.framework.dao.entity;

import com.springboot.framework.dto.AppDTO;

import java.util.Date;

public class App {
    private Integer id;

    private Integer parkId;

    private String name;

    private String icon;

    private Byte sort;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private Byte status;

    public App() {
    }

    public App(AppDTO appDTO) {
        this.id = appDTO.getId();
        this.parkId = appDTO.getParkId();
        this.name = appDTO.getName();
        this.icon = appDTO.getIcon();
        this.sort = appDTO.getSort();
        this.createBy = appDTO.getCreateBy();
        this.createDate = appDTO.getCreateDate();
        this.updateBy = appDTO.getUpdateBy();
        this.updateDate = appDTO.getUpdateDate();
        this.status = appDTO.getStatus();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
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