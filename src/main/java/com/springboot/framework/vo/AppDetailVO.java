package com.springboot.framework.vo;

import com.springboot.framework.dao.entity.App;
import com.springboot.framework.dao.entity.AppDetail;

import java.math.BigDecimal;
import java.util.Date;

public class AppDetailVO {
    private Integer appId;
    private String name;
    private String icon;
    private String createBy;
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    private Byte status;

    private String cover;
    private String address;
    private BigDecimal price;
    private String model;
    private String color;
    private String contact;
    private String description;
    private String introduction;
    private String content;

    public AppDetailVO() {
    }

    public AppDetailVO(App app, AppDetail appDetail) {
        this.appId = app.getId();
        this.name = app.getName();
        this.icon = app.getIcon();
        this.createBy = app.getIcon();
        this.createDate = app.getCreateDate();
        this.updateBy = app.getUpdateBy();
        this.updateDate = app.getUpdateDate();
        this.status = app.getStatus();

        this.cover = appDetail.getCover();
        this.address = appDetail.getAddress();
        this.price = appDetail.getPrice();
        this.model = appDetail.getModel();
        this.color = appDetail.getColor();
        this.contact = appDetail.getContact();
        this.description = appDetail.getDescription();
        this.introduction = appDetail.getIntroduction();
        this.content = appDetail.getContent();
    }

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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
