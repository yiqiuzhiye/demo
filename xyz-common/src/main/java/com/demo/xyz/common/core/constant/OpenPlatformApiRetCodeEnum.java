package com.demo.xyz.common.core.constant;

/**
 * upms模块
 *
 * @author admin
 */

public class OpenPlatformApiRetCodeEnum extends ApiRetCodeEnum {

	/**
	 * 对外开放接口错误码区间[100001,100199]
	 **/
	
	/**
	 * 参数method为空
	 */
	public final static int CODE_EMPTY_METHOD = 100001;
	public final static String MESSAGE_EMPTY_METHOD = "op.empty-method";

	/**
	 * 参数appId为空
	 */
	public final static int CODE_EMPTY_APPID = 100002;
	public final static String MESSAGE_EMPTY_APPID = "op.empty-appid";
	/**
	 * 参数timestamp为空
	 */
	public final static int CODE_NULL_TIMESTAMP = 100003;
	public final static String MESSAGE_NULL_TIMESTAMP = "op.null-timestamp";
	/**
	 * 参数sign为空
	 */
	public final static int CODE_EMPTY_SIGN = 100004;
	public final static String MESSAGE_EMPTY_SIGN = "op.empty-sign";
	/**
	 * 无效的appId
	 */
	public final static int CODE_INVALID_APPID = 100005;
	public final static String MESSAGE_INVALID_APPID = "op.invalid-appid";
	/**
	 * 签名不匹配
	 */
	public final static int CODE_UNMATCH_SIGN = 100006;
	public final static String MESSAGE_UNMATCH_SIGN = "op.unmatch-sign";
	/**
	 * 无效的method
	 */
	public final static int CODE_INVALID_METHOD = 100007;
	public final static String MESSAGE_INVALID_METHOD = "op.invalid-method";
	/**
	 * 接口配置错误
	 */
	public final static int CODE_ERRCONFIG_IF = 100008;
	public final static String MESSAGE_ERRCONFIG_IF = "op.errconfig-interface";

	/**
	 * 远程服务调用异常
	 */
	public final static int CODE_EXCEPTION_REMOTECALL = 100009;
	public final static String MESSAGE_EXCEPTION_REMOTECALL = "op.exception-remotecall";

	/**
	 * 开放平台内部管理错误码区间[100200,]
	 */


}
