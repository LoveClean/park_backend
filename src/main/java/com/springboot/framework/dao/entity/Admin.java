package com.springboot.framework.dao.entity;

import com.springboot.framework.dto.AdminDTO;

import java.io.Serializable;
import java.util.Date;

public class Admin implements Serializable {
    /*登录Token*/
    private String accessToken;

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

    public Admin() {
    }

    public Admin(AdminDTO adminDTO) {
        this.id = adminDTO.getId();
        this.account = adminDTO.getAccount();
        this.password = adminDTO.getPassword();
        this.phone = adminDTO.getPhone();
        this.name = adminDTO.getName();
        this.createBy = adminDTO.getCreateBy();
        this.updateBy = adminDTO.getUpdateBy();
        this.status = adminDTO.getStatus();
        this.parkId = adminDTO.getParkId();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }
}