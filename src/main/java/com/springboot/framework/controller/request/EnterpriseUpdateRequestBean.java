package com.springboot.framework.controller.request;

import lombok.Data;

import java.util.Date;

/**
 * @Author SWF
 * @Date 2019/5/5 21:43
 **/
@Data
public class EnterpriseUpdateRequestBean {

    private Integer id;

    private String name;

    private String legalPerson;

    private String contact;

    private String businessLicense;

    private String location;

    private String beginDate;

    private Integer duration;

}
