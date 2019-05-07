package com.springboot.framework.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ParkDTO {
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

    private Integer[] appIds;
    private Integer[] appIds2;
    private Integer[] appIds3;

    public ParkDTO() {
    }

    //删除
    public ParkDTO(Integer id, String updateBy) {
        this.id = id;
        this.updateBy = updateBy;
    }

    //新增
    public ParkDTO(String name, String logo, String location, String address, BigDecimal longitude, BigDecimal latitude, String introduction, Byte sort, String createBy, Integer[] appIds) {
        this.name = name;
        this.logo = logo;
        this.location = location;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.introduction = introduction;
        this.sort = sort;
        this.createBy = createBy;
        this.appIds = appIds;
    }

    //更新应用
    public ParkDTO(Integer id, String name, String logo, String location, String address, BigDecimal longitude, BigDecimal latitude, String introduction, Byte sort, String updateBy, Integer[] appIds2, Integer[] appIds3) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.location = location;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.introduction = introduction;
        this.sort = sort;
        this.updateBy = updateBy;
        this.appIds2 = appIds2;
        this.appIds3 = appIds3;
    }

    //更新状态
    public ParkDTO(Integer id, String updateBy, Byte status) {
        this.id = id;
        this.updateBy = updateBy;
        this.status = status;
    }
}
