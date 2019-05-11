package com.springboot.framework.controller.request;

import lombok.Data;

import java.util.Date;

/**
 * @Author SWF
 * @Date 2019/5/10 11:07
 **/
@Data
public class InformationInsertRequestBean {

    private Integer parkId;

    private Integer categoryId;

    private String title;

    private String introduction;

    private String cover;

    private Byte sort;

    private String content;

}
