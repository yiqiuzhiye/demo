package com.demo.xyz.common.core.constant;

/**
 * oauth模块
 * 
 * @author admin
 *
 */

public class OAuthApiRetCodeEnum extends ApiRetCodeEnum {

	public final static int CODE_UNAUTH_USER = 101001;

	public final static String MESSAGE_UNAUTH_USER = "oauth.user.unauth-user";

	public final static int CODE_LOGIN_FAIL = 101002;

	public final static String MESSAGE_LOGIN_FAIL = "oauth.user.login-fail";

	public final static int CODE_ACCESS_DENIED = 101003;

	public final static String MESSAGE_ACCESS_DENIED = "oauth.user.access-denied";

}
