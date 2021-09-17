package com.demo.xyz.common.core.constant;

/**
 * cves模块
 * 
 * @author: Jack
 * @create: 2021-4-22
 **/
public class CvesApiRetCodeEnum extends ApiRetCodeEnum {

    /**
     * 非法token
     */
    public final static int CODE_INVALID_TOKEN = 203001;
    public final static String MESSAGE_INVALID_TOKEN = "cves.user.invalid-token";

    /**
     * 空的token
     */
    public final static int CODE_EMPTY_TOKEN = 203002;
    public final static String MESSAGE_EMPTY_TOKEN = "cves.user.empty-token";

    /**
     *该用户被商家禁止购物，请和商家联系
     */
    public final static int CODE_BAN_SHOP = 203003;
    public final static String MESSAGE_BAN_SHOP = "cves.user.ban-shop";

    /**
     * 货柜不存在
     */
    public final static int CODE_VENDING_NOT_EXIST = 203004;
    public final static String MESSAGE_VENDING_NOT_EXIST = "cves.vending.vending-not-exist";

    /**
     * 货柜已停用
     */
    public final static int CODE_VENDING_DEACTIVATE = 203005;
    public final static String MESSAGE_VENDING_DEACTIVATE = "cves.vending.vending-deactivate";

    /**
     * 货柜没有可用库存
     */
    public final static int CODE_VENDING_NOT_SKU = 203006;
    public final static String MESSAGE_VENDING_NOT_SKU = "cves.vending.vending-not-sku";

    /**
     * 货柜使用中
     */
    public final static int CODE_VENDING_IS_USING = 203007;
    public final static String MESSAGE_VENDING_IS_USING = "cves.vending.vending-is-using";

    /**
     * 货柜使用中-01（购买中）
     */
    public final static int CODE_USER_IS_SHOPPING = 20300701;
    public final static String MESSAGE_USER_IS_SHOPPING = "cves.user.user-is-shopping";

    /**
     * 货柜使用中-02(购物次数达到上限5次)
     */
    public final static int CODE_VENDING_OVER_SHOPPING_COUNT = 20300702;
    public final static String MESSAGE_VENDING_OVER_SHOPPING_COUNT = "cves.vending.vending-over-shoppping-count";

    /**
     * 1.rcu上传重力或视频失败（针对1代和3代）
     * 2.美智失败
     */
    public final static int CODE_GOODS_IDENTIFY_FAIL = 20300703;
    public final static String MESSAGE_GOODS_IDENTIFY_FAIL = "cves.vending.goods-identify-fail";

    /**
     *存在未完成购物行为，请稍候再试
     */
    public final static int CODE_EXIST_NOT_COMPLETE_SHOPPING = 203008;
    public final static String MESSAGE_EXIST_NOT_COMPLETE_SHOPING = "cves.user.exist-not-complete-shopping";

    /**
     * 存在未完成购物行为，请稍候再试-01(用户购买中)
     */
    public final static int CODE_USER_SHOPPING = 20300801;
    public final static String MESSAGE_USER_SHOPPING = "cves.user.user-shopping";

    /**
     * 存在未完成购物行为，请稍候再试-02(存在关门事件)
     */
    public final static int CODE_USER_EXIST_CLOSEDOOR_EVENT = 20300802;
    public final static String MESSAGE_USER_EXIST_CLOSEDOOR_EVENT_ = "cves.user.user-exist-closedoor-event";

    /**
     * 微信未开通免密支付
     */
    public final static int CODE_WX_NOT_OPEN_NO_SECRET = 203009;
    public final static String MESSAGE_WX_NOT_OPEN_NO_SECRET = "cves.user.wx-not-open-no-secret";

    /**
     * 支付宝未开通免密支付
     */
    public final static int CODE_ALIPY_NOT_OPEN_NO_SECRET = 203010;
    public final static String MESSAGE_ALIPY_NOT_OPEN_NO_SECRET = "cves.user.alipy-not-open-no-secret";

    /**
     * 设备不在线
     */
    public final static int CODE_VENDING_NOT_ONLINE = 203011;
    public final static String MESSAGE_VENDING_NOT_ONLINE = "cves.vending.vending-not-online";

    /**
     * 用户存在未支付订单(欠费)
     */
    public final static int CODE_USER_EXIST_UNPAY_ORDER=203012;
    public final static String MESSAGE_USER_EXIST_UNPAY_ORDER = "cves.user.user-exist-unpay-order";
}
