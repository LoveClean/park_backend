package com.springboot.framework.controller.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ParkApply {
    private String parkName;
    private String logo;
    private String location;
    private String address;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String introduction;
    private Byte sort;

    private String adminPhone;
}
