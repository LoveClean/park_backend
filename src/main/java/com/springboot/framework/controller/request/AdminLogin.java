package com.springboot.framework.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

@ApiModel
public class AdminLogin {
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名，必填", required = true)
    private String phone;

    /**
     * 密码
     */
    @Length(min = 3, max = 16, message = "请输入3-16位密码")
    @ApiModelProperty(value = "密码，必填", required = true)
    private String loginPwd;

    @ApiModelProperty(value = "验证码，必填", required = true)
    private String verifyCode;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
