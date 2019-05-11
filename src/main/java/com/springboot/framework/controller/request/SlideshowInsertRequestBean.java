package com.springboot.framework.controller.request;

import lombok.Data;

import java.util.Date;

/**
 * @Author SWF
 * @Date 2019/5/9 17:41
 **/
@Data
public class SlideshowInsertRequestBean {

    private Integer parkId;

    private Integer informationId;

    private String name;

    private String picture;

}
