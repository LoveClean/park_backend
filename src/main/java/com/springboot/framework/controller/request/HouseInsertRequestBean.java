package com.springboot.framework.controller.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author SWF
 * @Date 2019/4/25 10:56
 **/
@Data
public class HouseInsertRequestBean {
    private String cover;

    private String contact;

    private String location;

    private Integer acreage;

    private Integer floor;

    private Byte orientation;

    private Byte finish;

    private Byte elevator;

    private Byte tenancyTerm;

    private Byte registeredCompany;

    private BigDecimal usageRate;

    private BigDecimal unitPrice;

    private BigDecimal price;

    private BigDecimal serviceCharge;

    private BigDecimal cashPledge;

    private BigDecimal propertyFee;

//    private String label;

    private String introduction;

    private List<String> imgs;
    // 用作户型
    private String remark;

}
