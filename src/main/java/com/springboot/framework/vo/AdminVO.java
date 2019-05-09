package com.springboot.framework.vo;

import com.springboot.framework.dao.entity.Admin;
import lombok.Data;

import java.util.Date;

@Data
public class AdminVO {
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
    private String parkName;

    public AdminVO() {

    }

    public AdminVO(Admin admin, String parkName) {
        this.id = admin.getId();
        this.account = admin.getAccount();
        this.password = admin.getPassword();
        this.phone = admin.getPhone();
        this.name = admin.getName();
        this.createBy = admin.getCreateBy();
        this.createDate = admin.getCreateDate();
        this.updateBy = admin.getUpdateBy();
        this.updateDate = admin.getUpdateDate();
        this.status = admin.getStatus();
        this.parkId = admin.getParkId();
        this.parkName = parkName;
    }
}
