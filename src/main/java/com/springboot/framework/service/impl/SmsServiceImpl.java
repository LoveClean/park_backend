package com.springboot.framework.service.impl;


import com.springboot.framework.contant.Errors;
import com.springboot.framework.service.SmsService;
import com.springboot.framework.util.ExceptionUtil;
import com.springboot.framework.util.HwSendSmsUtil;
import com.springboot.framework.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title: SmsServiceImpl.java
 * @Package cc.uworks.library.service.impl
 * @author liyuchang
 * @date 2017年1月23日
 * @version V1.0
 */
@Service()
public class SmsServiceImpl implements SmsService {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private HwSendSmsUtil hwSendSmsUtil;

  /**
   * 短信单发
   * 
   * @param mobile
   * @param content
   * @return true 发送成功，false 发送失败
   */
  @Override
  public boolean send(String mobile, String content) {
    try {
      logger.info("发送短信, mobile = {}, content = {}", mobile, content);
      if (StringUtil.isBlank(mobile)) {
        ExceptionUtil.throwException(Errors.SYSTEM_CUSTOM_ERROR.code, "手机号不能为空");
      }
      if (StringUtil.isBlank(content)) {
        ExceptionUtil.throwException(Errors.SYSTEM_CUSTOM_ERROR.code, "短信内容不能为空");
      }
//      JSONObject params = new JSONObject();
//      params.put("code", content);
//      aliyunSms.sendMsg(mobile, params.toJSONString());
      hwSendSmsUtil.sendMsg(mobile,content);
      return true;
    } catch (Exception e) {
      logger.error("发送短信失败， mobile = {}, content = {}, error={}", mobile, content, e.getMessage(), e);
      return false;
    }
  }

  /**
   * 短信群发
   * 
   * @param mobiles
   * @param content
   */
  @Override
  public void send(List<String> mobiles, String content) {
   /* StringBuilder mobilesStr = new StringBuilder();
    mobiles.forEach(mobile -> {
      if (StringUtils.isNotBlank(mobile)) {
        mobilesStr.append(mobile).append(",");
      }
      if (mobiles.toString().split(",").length >= SmsConstants.MAX_COUNT) {
        this.send(mobiles.toString().replaceAll(",$", ""), content);
        mobilesStr.setLength(0);
      }
    });
    if (mobilesStr.length() > 0) {
      this.send(mobiles.toString().replaceAll(",$", ""), content);
    }*/
  }
  
 


}
