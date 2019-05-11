package com.springboot.framework.controller.request;

import lombok.Data;

import java.util.Date;

/**
 * @Author SWF
 * @Date 2019/5/10 10:29
 **/
@Data
public class SlideshowUpdateRequestBean {
    private Integer id;

    private Integer parkId;

    private Integer informationId;

    private String name;

    private String picture;

    private Byte sort;

    private Byte status;
}
