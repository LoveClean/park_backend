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

    private Integer[] parkIds;
    private Integer[] parkIds3;

    public AppDTO() {
    }

    //删除
    public AppDTO(Integer id, String updateBy) {
        this.id = id;
        this.updateBy = updateBy;
    }

    //新增
    public AppDTO(String name, String icon, Byte sort, String createBy, Integer[] parkIds) {
        this.name = name;
        this.icon = icon;
        this.sort = sort;
        this.createBy = createBy;
        this.parkIds = parkIds;
    }

    //更新
    public AppDTO(Integer id, String name, String icon, Byte sort, String updateBy, Integer[] parkIds, Integer[] parkIds3) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.sort = sort;
        this.updateBy = updateBy;
        this.parkIds = parkIds;
        this.parkIds3 = parkIds3;
    }

    //更新状态
    public AppDTO(Integer id, String updateBy, Byte status) {
        this.id = id;
        this.updateBy = updateBy;
        this.status = status;
    }
}
