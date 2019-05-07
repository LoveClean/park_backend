package com.springboot.framework.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AdminDTO {
    private Integer id;
    private String account;
    private String password;
    private String phone;
    private String name;
    private String createBy;
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    private Byte status;
    private Integer parkId;

    private String loginKey;
    private Boolean parkAdmin;

    public AdminDTO() {
    }

    //删除
    public AdminDTO(Integer id, String updateBy) {
        this.id = id;
        this.updateBy = updateBy;
    }

    //新增
    public AdminDTO(String account, String password, String phone, String name, String createBy, Integer parkId) {
        this.account = account;
        this.password = password;
        this.phone = phone;
        this.name = name;
        this.createBy = createBy;
        this.parkId = parkId;
    }

    //登陆
    public AdminDTO(String loginKey, String password, Boolean parkAdmin) {
        this.password = password;
        this.loginKey = loginKey;
        this.parkAdmin = parkAdmin;
    }

    //更新
    public AdminDTO(Integer id, String password, String phone, String name, String updateBy, Byte status) {
        this.id = id;
        this.password = password;
        this.phone = phone;
        this.name = name;
        this.updateBy = updateBy;
        this.status = status;
    }
}
