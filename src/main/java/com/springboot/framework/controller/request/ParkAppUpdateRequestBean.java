package com.springboot.framework.controller.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author SWF
 * @Date 2019/4/29 13:46
 **/
@Data
public class ParkAppUpdateRequestBean {
    private Integer id;
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
