package com.springboot.framework.vo;

import com.springboot.framework.dao.entity.App;
import com.springboot.framework.dao.entity.AppDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
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
}
