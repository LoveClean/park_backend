package com.springboot.framework.controller.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Title: CaptchaRequestBean.java
 * @Package cc.uworks.library.controller.request
 * @author liyuchang
 * @Description: 短信验证码参数
 * @date 2017年1月23日
 * @version V1.0
 */
public class CaptchaRequestBean {

  @NotEmpty(message = "手机号必填")
  @Pattern(regexp = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$",message = "请输入正确的手机号")
  private String mobile;

  @ApiModelProperty(value = "类型：1注册,2修改密码,3登录，4注册+登陆", required = true)
  @NotNull(message = "验证码类型不能为空")
  private Byte type;

  public String getMobile() {
    return mobile.replaceAll(" ", "");
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public Byte getType() {
    return type;
  }

  public void setType(Byte type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "CaptchaRequestBean [mobile=" + mobile + ", type=" + type + "]";
  }
  
}
