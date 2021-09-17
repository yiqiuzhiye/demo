package com.demo.xyz.common.core.constant;

/**
 * uces模块
 * 
 * @author admin
 *
 */

public class UcesApiRetCodeEnum extends ApiRetCodeEnum {

	public final static int CODE_INVALID_TOKEN = 201001;
	public final static String MESSAGE_INVALID_TOKEN = "uces.user.invalid-token";

	public final static int CODE_EMPTY_TOKEN = 201002;
	public final static String MESSAGE_EMPTY_TOKEN = "uces.user.empty-token";
	public final static int CODE_NOTMATCH_ORIGNAL_PASSWORD = 201003;
	public final static String MESSAGE_NOTMATCH_ORIGNAL_PASSWORD = "uces.user.notmatch-original-password";
	public final static int CODE_EMPTY_PASSWORD = 201004;
	public final static String MESSAGE_EMPTY_PASSWORD = "uces.user.empty-password";
	public final static int CODE_EMPTY_LOGIN_USERTYPE = 201005;
	public final static String MESSAGE_EMPTY_LOGIN_USERTYPE = "uces.user.empty-login-usertype";
	public final static int CODE_UNMATCH_LOGIN_USERTYPE = 201006;
	public final static String MESSAGE_UNMATCH_LOGIN_USERTYPE = "uces.user.unmatch-login-usertype";

	public final static int CODE_UNMATCH_USERNAME_PASSWORD = 201007;
	public final static String MESSAGE_UNMATCH_USERNAME_PASSWORD = "uces.user.unmatch-username-password";

	public final static int CODE_ORDER_PAY_FAIL = 201008;
	public final static String MESSAGE_ORDER_PAY_FAIL = "uces.order.pay-fail";

	public final static int CODE_CV_RECOGNIZE_GOODS_FAIL = 201009;
	public final static String MESSAGE_CV_RECOGNIZE_GOODS_FAIL = "uces.cv.recognize-goods-fail";

	public final static int CODE_ORDER_CREATE_FAIL = 201010;
	public final static String MESSAGE_ORDER_CREATE_FAIL = "uces.order.create-order-fail";

	public final static int CODE_ORDER_NOT_BUY_GOODS = 201011;

	public final static String MESSAGE_ORDER_NOT_BUY_GOODS = "uces.order.not-buy-goods";

	public final static int CODE_OPEN_DOOR_TIMEOUT = 201012;

	public final static String MESSAGE_OPEN_DOOR_TIMEOUT = "uces.vending.open-door-timeout";

}
