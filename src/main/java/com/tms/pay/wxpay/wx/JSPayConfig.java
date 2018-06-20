package com.tms.pay.wxpay.wx;

import java.io.Serializable;

public class JSPayConfig implements Serializable {
	private static final long serialVersionUID = 1L;
	private String noncestr;
	private long timestamp;
	private String signature;
	private long orderExpireTime;// 订单过期时间
	
	public String getNoncestr() {
		return noncestr;
	}
	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public long getOrderExpireTime() {
		return orderExpireTime;
	}
	public void setOrderExpireTime(long orderExpireTime) {
		this.orderExpireTime = orderExpireTime;
	}
}
