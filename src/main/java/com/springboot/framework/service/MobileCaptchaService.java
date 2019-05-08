package com.springboot.framework.service;


import com.springboot.framework.constant.SmsConstants.SmsCaptchaType;
import com.springboot.framework.controller.request.CaptchaRequestBean;
import com.springboot.framework.controller.response.CaptchaResponseBean;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title: MobileCaptchaService.java
 * @Package cc.uworks.library.service
 * @author liyuchang
 * @Description: 短信验证码发送、验证
 * @date 2017年1月23日
 * @version V1.0
 */
public interface MobileCaptchaService {

  /**
   * 发送验证码
   * @return
   */
  CaptchaResponseBean send(CaptchaRequestBean bean, HttpServletRequest request);

  /**
   * 验证码验证
   * @param mobile 手机号
   * @param captcha 验证码
   * @param type 类型:1注册,2修改密码,3找回密码
   * @return 验证结果 true通过 false不通过
   */
  boolean verify(String mobile, String captcha, SmsCaptchaType type);
}
