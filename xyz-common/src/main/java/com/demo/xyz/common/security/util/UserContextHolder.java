package com.demo.xyz.common.security.util;

import com.demo.xyz.common.core.constant.CommonConstants;
import com.demo.xyz.common.security.service.CrssUserInfo;
import lombok.experimental.UtilityClass;

/**
 * 安全工具类
 *
 * @author admin
 */
@UtilityClass
public class UserContextHolder {

	private static final ThreadLocal<CrssUserInfo> context = new ThreadLocal();

	public static void setContext(CrssUserInfo crssUserInfo) {
		context.set(crssUserInfo);
	}

	public static void clearContext() {
		context.remove();
	}


	/**
	 * 获取用户
	 */
	public CrssUserInfo getUser() {

		return context.get();

	}

	public boolean isDataUser() {

		return getUser().getTenantId().intValue() == 1;
	}

	public boolean isTenantUser() {

		return getUser().getTenantId().intValue() > 1;

	}

	public boolean isDataAdmin() {

		return UserContextHolder.getUser().getBossAdminFlag() == 1;

	}

	@Deprecated
	public boolean isDataAdmin(String username) {
		// 超级管理员返回
		if (CommonConstants.BOSS_ADMIN.equals(username)) {

			return true;
		}
		return false;

	}


	@Deprecated
	public boolean isTenantAdmin() {

		return UserContextHolder.getUser().getBizAdminFlag() == 1;

	}

	@Deprecated
	public boolean isTenantAdmin(String username, String tenantCode) {
		final String adminUsername = "admin@" + tenantCode;
		if (username.equals(adminUsername)) {
			return true;
		}

		return false;

	}

	public Integer getTenantId() {

		return getUser().getTenantId();
	}

	public String getTenantName() {

		return getUser().getTenantName();
	}

	public String getTenantCode() {

		return getUser().getTenantCode();
	}

	public String getStaffName() {
		return getUser().getUsername();
	}

	public Integer getStaffId() {
		return getUser().getId();
	}

	/**
	 * 判断用户是否为虚拟运营商用户
	 *
	 * @return
	 */
	public boolean isVopUser() {
		Integer vopId = getVopId();
		if (vopId != null && vopId > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 获取虚拟运营商ID
	 *
	 * @return
	 */
	public Integer getVopId() {
		return getUser().getVopId();
	}


	public static class Authentication {

		public Object getPrincipal() {

			return null;
		}

	}

}
