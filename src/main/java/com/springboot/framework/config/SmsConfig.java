package com.springboot.framework.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "sms")
@Component
@Data
public class SmsConfig {

	/*
	 APP接入地址+接口访问URI
	 */
	private String url;
	private String appKey;
	private String appSecret;
	/*
	 国内短信签名通道号或国际/港澳台短信通道号
	 */
	private String sender;
	/*
	 模板id
	 */
	private String templateId;
	/*
	 当templateId指定的模板类型为通用模板时生效且必填,必须是已审核通过的,与模板类型一致的签名名称
	 */
	private String signature;
	/*
     选填,短信状态报告接收地址,推荐使用域名,为空或者不填表示不接收状态报告
     */
	private String statusCallBack;



}
