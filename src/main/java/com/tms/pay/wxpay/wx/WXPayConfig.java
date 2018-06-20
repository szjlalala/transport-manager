package com.tms.pay.wxpay.wx;

import java.io.Serializable;

public class WXPayConfig implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String timestamp;  // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
    private String nonceStr;  // 支付签名随机串，不长于 32 位
    private String prepay_id; // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
    private String signType;  // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
    private String paySign; // 支付签名
    private String openId;
    
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getPrepay_id() {
		return prepay_id;
	}
	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getPaySign() {
		return paySign;
	}
	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
