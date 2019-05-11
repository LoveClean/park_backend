package com.springboot.framework.controller.request;

import lombok.Data;

import java.util.Date;

/**
 * @Author SWF
 * @Date 2019/5/10 11:10
 **/
@Data
public class InformationUpdateRequestBean {
    private Integer id;

    private Integer parkId;

    private Integer categoryId;

    private String title;

    private String introduction;

    private String cover;

    private Byte sort;

    private Byte status;

    private String content;
}
