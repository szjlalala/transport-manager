package com.tms.util;

public enum RedisKeyUtils {
	
	渠道商品缓存KEY("CHANNEL_PRODUCT", "渠道商品缓存KEY"),
	票点场次缓存KEY("TICKET_SITE_PRODUCT_SESSION", "票点场次缓存KEY"),
	票点商品缓存KEY("TICKET_SITE_PRODUCT", "票点商品缓存KEY");
	
    private String code; // 错误编码

    private String message; // 提示信息

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private RedisKeyUtils(String code, String message) {
		this.code = code;
		this.message = message;
	}



}
