package com.springboot.framework.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author SWF
 * @Date 2019/4/29 14:00
 **/
@Data
public class ParkAppInfoVo {
    private Integer id;

    private Integer parkId;

    private Integer appId;

    private String cover;

    private String address;

    private BigDecimal price;

    private String model;

    private String color;

    private List contact;

    private String description;

    private String introduction;

    private Byte status;

    private String content;
}
