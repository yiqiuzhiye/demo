package com.demo.xyz.common.core.constant;

/**
 * upms模块
 * 
 * @author admin
 *
 */

public class UpmsApiRetCodeEnum extends ApiRetCodeEnum {

	public final static int CODE_INVALID_USERNAME = 102001;
	public final static String MESSAGE_INVALID_USERNAME = "upms.staff.invalid-username";

	public final static int CODE_INVALID_CODE = 102002;
	public final static String MESSAGE_INVALID_CODE = "upms.staff.invalid-tenantCode";

	public final static int CODE_EXIST_USERNAME = 102002;

	public final static String MESSAGE_EXIST_USERNAME = "upms.staff.exist-username";

	public final static int CODE_EXIST_PHONE = 102003;

	public final static String MESSAGE_EXIST_PHONE = "upms.staff.exist-phone";

}
