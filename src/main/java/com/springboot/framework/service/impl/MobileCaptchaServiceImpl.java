package com.springboot.framework.service.impl;

import com.alibaba.fastjson.JSON;

import com.springboot.framework.cache.MobileCaptchaCache;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.constant.SmsConstants;
import com.springboot.framework.controller.request.CaptchaRequestBean;
import com.springboot.framework.controller.response.CaptchaResponseBean;
import com.springboot.framework.service.AdminService;
import com.springboot.framework.service.MobileCaptchaService;
import com.springboot.framework.service.SmsService;
import com.springboot.framework.util.ExceptionUtil;
import com.springboot.framework.util.RedisUtil;
import com.springboot.framework.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Title: MobileCaptchaServiceImpl.java
 * @Package cc.uworks.library.service.impl
 * @author shiwenfeng
 * @Description: 短信验证码发送、验证
 * @date 2019年4月12日
 * @version V1.0
 */
@Service
public class MobileCaptchaServiceImpl implements MobileCaptchaService {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Resource
  private SmsService smsService;
  @Resource
  private RedisUtil redisUtil;
  @Resource
  private AdminService userService;



  /**
   * 发送验证码
   * 
   *  手机号
   *  类型:1注册,2修改密码,3登录,4注册+登陆(加入)
   *        code=1手机号有误;code=2未超过发送冷却时间，coolSeconds=剩余发送冷却时间(单位秒)；code=3送失败请稍后再试
   * @return
   */
  @Override
  public CaptchaResponseBean send(CaptchaRequestBean bean , HttpServletRequest request) {
    // 发送前验证request
    sendVidate(bean);
    // 随机生成6位验证码数字
    String captcha = StringUtil.random(6);
    // 放入缓存
    MobileCaptchaCache mobileCaptcha = new MobileCaptchaCache();
    mobileCaptcha.setCaptcha(captcha);
    mobileCaptcha.setSentTime(new Date());
    redisUtil.set(captchaKey(bean.getType(), bean.getMobile()),JSON.toJSONString(mobileCaptcha) , SmsConstants.SMS_VALID_TIME);
    // 验证码发送,是否存库待定
    if (!this.smsService.send(bean.getMobile(),captcha)) {
          logger.error("发送验证码失败,{}");
          ExceptionUtil.throwException(3, "发送失败请稍后再试");
        }

    // 封装返回数据 冷却时间 有效时间
    CaptchaResponseBean responseBean = new CaptchaResponseBean();
    responseBean.setValidSeconds(SmsConstants.SMS_VALID_TIME);
    responseBean.setCoolSeconds(SmsConstants.SMS_COOL_TIME);
    return responseBean;
  }


  /**
   * 验证码验证
   * 
   * @param mobile 手机号
   * @param captcha 验证码
   * @param type 类型:1注册,2修改密码,3找回密码
   */
  @Override
  public boolean verify(String mobile, String captcha, SmsConstants.SmsCaptchaType type) {
    String captchaKey = captchaKey(type.code, mobile);
    String mobileCaptchaJson = (String) redisUtil.get(captchaKey);
    if (StringUtil.isBlank(mobileCaptchaJson)) {
      ExceptionUtil.throwException(Errors.CAPTCHA_IS_NULL.code, "未发送验证码");
    }
    MobileCaptchaCache mobileCaptcha = JSON.parseObject(mobileCaptchaJson, MobileCaptchaCache.class);
    if (!mobileCaptcha.getCaptcha().equals(captcha)) {
      ExceptionUtil.throwException(Errors.CAPTCHA_ERROR.code, "验证码有误");
    }
    redisUtil.del(captchaKey);
    return true;
  }

  /**
   * 验证码缓存key
   *
   * @param type
   * @param mobile
   * @return groupId_SMS_CAPTCHA_type_mobile
   */
  private String captchaKey(Byte type, String mobile) {
    return String.format(SmsConstants.CAPTCHA_KEY, type, mobile);
  }

  /**
   * 发送前验证,手机号是否注册、是否发送频繁等
   *
   * @param bean
   */
  private void sendVidate(CaptchaRequestBean bean) {
    // 手机号验证在controller层验证

//    // 注册 验证该手机号是否已存在
//    if (bean.getType() == SmsConstants.SmsCaptchaType.REGIST.code) {
//      if (!this.userService.checkPhone(bean.getMobile())) {
//        ExceptionUtil.throwException(Errors.USER_MOBILE_EXISTS.code, "手机号已注册");
//      }
//    }
//    // 修改密码
//    else if (bean.getType() == SmsConstants.SmsCaptchaType.CHANGE_PWD.code) {
//      if (this.userService.checkPhone(bean.getMobile())) {
//        ExceptionUtil.throwException(Errors.USER_MOBILE_NOT_REGISTER.code, "手机号未注册");
//      }
//    }
    // 是否频繁发送
    String mobileCaptchaJson = (String) redisUtil.get(captchaKey(bean.getType(), bean.getMobile()));
    if (StringUtil.isBlank(mobileCaptchaJson)) {
      return;
    }
    MobileCaptchaCache mobileCaptcha = JSON.parseObject(mobileCaptchaJson, MobileCaptchaCache.class);
    // 是否未超过冷却时间
    long sendTime = mobileCaptcha.getSentTime().getTime();
    int seconds = (int) (System.currentTimeMillis() - sendTime) / 1000;
    if (seconds < SmsConstants.SMS_COOL_TIME) {
      ExceptionUtil.throwException(Errors.SYSTEM_CUSTOM_ERROR.code, "请求太过频繁，请稍后再试");
    }
  }

}
