package com.springboot.framework.controller.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author SWF
 * @Date 2019/5/5 21:37
 **/
@Data
public class EnterpriseInsertRequestBean {

    @NotNull(message = "参数错误")
    private Integer parkId;// 园区id

    @NotEmpty(message = "参数错误")
    private String name;

    @NotEmpty(message = "参数错误")
    private String legalPerson;//法人

    @NotEmpty(message = "参数错误")
    private String contact;

    private String businessLicense;

    @NotEmpty(message = "参数错误")
    private String location;

    @NotEmpty(message = "参数错误")
    private String beginDate;

    @NotNull(message = "参数错误")
    private Integer duration;

}
