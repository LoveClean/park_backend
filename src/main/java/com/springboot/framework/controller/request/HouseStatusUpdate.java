package com.springboot.framework.controller.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Author SWF
 * @Date 2019/6/4 14:10
 **/
@Data
public class HouseStatusUpdate {
    @NotNull(message = "参数错误")
    private Integer id;

    @NotNull(message = "参数错误")
    @Max(value = 2,message = "参数错误")
    @Min(value = 1,message = "参数错误")
    private Byte status;
}
