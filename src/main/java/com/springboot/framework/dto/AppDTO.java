package com.springboot.framework.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AppDTO {
    private Integer id;
    private Integer parkId;
    private String name;
    private String icon;
    private Byte sort;
    private String createBy;
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    private Byte status;

    public AppDTO() {
    }

    //删除
    public AppDTO(Integer id, String updateBy) {
        this.id = id;
        this.updateBy = updateBy;
    }
}
