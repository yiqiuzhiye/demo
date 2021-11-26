package com.demo.xyz.common.core;

import lombok.experimental.UtilityClass;

/**
 * 安全工具类
 *
 * @author admin
 */
@UtilityClass
public class CommonUserContext {

	private static final ThreadLocal<CommonUser> context = new ThreadLocal();

	public static void setContext(CommonUser commonUser) {
		context.set(commonUser);
	}

	public static void clearContext() {
		context.remove();
	}


	/**
	 * 获取用户
	 */
	public CommonUser getUser() {

		return context.get();

	}

	public String getStaffUsername() {
		return getUser().getUsername();
	}

	public Integer getStaffId() {
		return getUser().getId();
	}

}
