package com.springboot.framework.vo;

import com.springboot.framework.dao.entity.Area;
import com.springboot.framework.dao.entity.Park;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ParkVO {
    private Integer parkId;
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
        this.longitude = park.getLongitude();
        this.latitude = park.getLatitude();
        this.introduction = park.getIntroduction();
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
}
