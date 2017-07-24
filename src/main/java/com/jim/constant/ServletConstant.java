package com.jim.constant;

/**
 * Created by jim on 2017/7/24.
 * This class is ...
 */
public enum ServletConstant {
	HAS_LOGIN(true), NOT_LOGIN(false), FORWARD_URL("forwardUrl");
	private Boolean isLogin;
	private String forwardUrl;

	ServletConstant(Boolean isLogin) {
		this.isLogin = isLogin;
	}

	ServletConstant(String url) {
		this.forwardUrl = url;
	}
}
