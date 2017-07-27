package com.jim.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by jim on 2017/7/28.
 * This class is ...
 */
public class AjaxUtils {
	public static Boolean isAjaxRequest(ServletRequest request) {
		Boolean result = false;
		HttpServletRequest httpServletRequest = null;
		if (request instanceof HttpServletRequest) {
			httpServletRequest = (HttpServletRequest) request;
			String ajaxHeader = httpServletRequest.getHeader("X-Requested-With");
			if (StringUtils.isNoneEmpty(ajaxHeader) && httpServletRequest.getHeader("X-Requested-With").equals("XMLHttpRequest")) {
				result = true;
			}
		}

		return result;
	}
}
