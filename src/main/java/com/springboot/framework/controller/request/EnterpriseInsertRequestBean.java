package com.springboot.framework.controller.request;

import lombok.Data;

import java.util.Date;

/**
 * @Author SWF
 * @Date 2019/5/5 21:37
 **/
@Data
public class EnterpriseInsertRequestBean {

    private Integer parkId;// 园区id

    private String name;

    private String legalPerson;//法人

    private String contact;

    private String businessLicense;

    private String location;

    private String beginDate;

    private Integer duration;

}
