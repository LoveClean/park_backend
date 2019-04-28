package com.springboot.framework.controller.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author SWF
 * @Date 2019/4/26 14:29
 **/
@Data
public class HouseUpdateRequestBean {
    private Integer id;

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

    private String remark;

}
