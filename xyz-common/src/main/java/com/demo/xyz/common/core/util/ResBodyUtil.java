package com.demo.xyz.common.core.util;

import com.demo.xyz.common.core.constant.CommonConstants;

/**
 * @author alex.xu
 * 响应对象工具类
 *2019/08/14
 */
public class ResBodyUtil {
	
	public static R buildSuccessResBody() {
		return new R();
	}
	
	public static R buildSuccessResBody(Object data) {
		return new R(data);
	}
	
	public static R buildSuccessResBody(Object data, String msg) {
		R resp = new R(data);
		resp.setMessage(msg);
		return resp;
	}

	public static R buildFailResBody(String message) {
		R resp = new R();
		resp.setCode(CommonConstants.FAIL);
		resp.setMessage(message);
		return resp;
	}
	
	public static R buildFailResBody(String message, Object data) {
		R resp = new R();
		resp.setCode(CommonConstants.FAIL);
		resp.setMessage(message);
		resp.setData(data);
		return resp;
	}
	



}
