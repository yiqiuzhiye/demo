package com.demo.xyz.common.core.constant;

import java.util.HashMap;
import java.util.Map;


/**
 * @author alex.xu
 * @version 1.0
 * @desc 基础响应状态码，各子业务模块可继承该基类后再扩展各自业务相关的状态码
 * @date 2019年8月13日
 */
public class BaseApiCode {

    public static final Map<Integer, String> zhMsgMap = new HashMap<>(300);

    /**
     * 正常
     **/
    public static final Integer SUCCESS = 200;
    /**
     * 内部服务器错误
     */
    public static final Integer INTERNAL_SERVER_ERROR = 500;

    /**
     * clientID参数无效
     **/
    public static final Integer NOT_EXISTS_CLIENTID = 1000;
    /**
     * 时间戳参数无效
     **/
    public static final Integer NOT_EXISTS_TIMESTAMP = 1001;
    /**
     * 签名参数无效
     **/
    public static final Integer NOT_EXISTS_SIGN = 1002;
    /**
     * 短信服务获取不到模板id对应短信模板记录
     **/
    public static final Integer NOT_EXISTS_SMSTEMPLATE = 1003;
    /**
     * 未发现秘钥
     **/
    public static final Integer NOT_EXISTS_SECRET = 1004;
    /**
     * 失败
     **/
    public static final Integer FAILURE = 1005;
    /**
     * 参数不全
     **/
    public static final Integer NOT_EMPTY = 1006;
    /**
     * 没有有效信息
     **/
    public static final Integer NOT_EXIST = 1007;

    /**
     * 参数不合法
     **/
    public static final Integer PARAM_INVALID = 1008;
    /**
     * 调用图片服务上传图片文件接口发生异常
     */
    public static final Integer UPLOAN_IMG_EXCEPTION = 1009;
    /**
     * 调用图片服务上传图片文件失败
     */
    public static final Integer UPLOAN_IMG_FAILURE = 1010;


    /**
     * token在黑名单列表中
     **/
    public static final Integer TOKEN_IN_BLACKLIST = 1011;


    /**
     * 超过最大上传大小限制
     **/
    public static final Integer MAX_UPLOAD_SIZE_EXCEEDED_EXCEPTION = 1012;

    /**
     * 未授权的访问
     **/
    public static final Integer UNAUTHORIZED_ACCESS = 1013;

    /**
     * 网关校验权限发生异常
     **/
    public static final Integer VERIFY_AUTHORIZATION_EXCEPTION = 1014;

    /**
     * ID不存在
     **/
    public static final Integer ID_NOT_EXISTED = 1015;

    /**
     * 签名验证失败
     **/
    public static final Integer FAIL_SIGN = 3000;
    /**
     * 调用超出五分钟时间范围
     **/
    public static final Integer FAIL_TIMESTAMP = 3001;
    /**
     * 短信服务请勿频繁重复发送短信
     **/
    public static final Integer FAIL_REPEAT = 3002;
    /**
     * 短信服务获取短信模板列表失败
     **/
    public static final Integer FAIL_TEMPLATE = 3003;
    /**
     * 替换内容与替换参数不匹配
     **/
    public static final Integer FAIL_SMSTEMPLATE_PRASE = 3004;
    /**
     * 短信发送失败
     **/
    public static final Integer FAIL_SMS_SEND = 3005;
    /**
     * 短信渠道错误
     **/
    public static final Integer FAIL_SMS_CHANNEL = 3006;
    /**
     * 黑名单验证不通过
     **/
    public static final Integer FAIL_BLACKLIST_VALIDATE = 3007;
    /**
     * 验证码验证不通过
     */
    public static final Integer FAIL_CODE_VALIDATE = 3008;

    /**
     * 验证码验证不通过
     */
    public static final Integer CODE_INVALID = 3009;

    /**
     * 时间戳类型不对
     **/
    public static final Integer NOT_TYPE_TIMESTAMP = 3010;

    /**
     * 微信授权码无效
     */
    public static final Integer WX_CODE_INVALID = 3101;

    /**
     * 时间戳超时验证异常
     **/
    public static final Integer EXCEPTION_TIMESTAMP = 4000;
    /**
     * 签名验证异常
     **/
    public static final Integer EXCEPTION_SIGN = 4001;
    /**
     * token验证异常
     **/
    public static final Integer EXCEPTION_TOKEN = 4002;
    /**
     * 黑名单验证异常
     **/
    public static final Integer EXCEPTION_BLACKLIST = 4003;
    /**
     * 网关层过滤器异常
     **/
    public static final Integer EXCEPTION_GATEWAY = 4004;
    /**
     * 发送短信异常
     **/
    public static final Integer EXCEPTION_SMS_SEND = 4005;
    /**
     * json解析报错
     **/
    public static final Integer EXCEPTION_PRASE_JSON = 4006;
    /**
     * get请求参数解析报错
     **/
    public static final Integer EXCEPTION_PRASE_GET = 4007;
    /**
     * form解析异常
     **/
    public static final Integer EXCEPTION_PRASE_FORM = 4008;
    /**
     * 未知异常
     **/
    public static final Integer EXCEPTION_UNKNOWN = 4009;


    public static String getZhMsg(Integer errorCode) {
        return zhMsgMap.get(errorCode);
    }


    static {
        zhMsgMap.put(SUCCESS, "请求成功");
        zhMsgMap.put(NOT_EXISTS_CLIENTID, "clientID请求参数不存在");
        zhMsgMap.put(NOT_EXISTS_TIMESTAMP, "timestamp请求参数不存在或者非数字");
        zhMsgMap.put(NOT_EXISTS_SIGN, "sign请求参数不存在");
        zhMsgMap.put(ID_NOT_EXISTED, "记录不存在");
        zhMsgMap.put(FAIL_TIMESTAMP, "调用时间戳超出五分钟时间范围");
        zhMsgMap.put(NOT_TYPE_TIMESTAMP, "调用时间戳类型不对,应为String或long类型");
        zhMsgMap.put(FAIL_SIGN, "签名验证失败");
        zhMsgMap.put(NOT_EXISTS_SECRET, "未发现秘钥");
        zhMsgMap.put(FAILURE, "失败");
        zhMsgMap.put(NOT_EMPTY, "参数不能为空");
        zhMsgMap.put(NOT_EXIST, "没有有效信息");
        zhMsgMap.put(PARAM_INVALID, "参数不合法");
        zhMsgMap.put(EXCEPTION_PRASE_JSON, "解析Json失败");
        zhMsgMap.put(EXCEPTION_PRASE_GET, "get请求参数解析失败");
        zhMsgMap.put(FAIL_BLACKLIST_VALIDATE, "黑名单验证不通过");
        zhMsgMap.put(TOKEN_IN_BLACKLIST, "token在黑名单列表中, 已经不再有效!");
        zhMsgMap.put(UNAUTHORIZED_ACCESS, "未授权的访问请求!");
        zhMsgMap.put(FAIL_CODE_VALIDATE, "验证码错误，请重新输入或获取验证码");
        zhMsgMap.put(CODE_INVALID, "短信验证码已失效");
        zhMsgMap.put(WX_CODE_INVALID, "不是有效的微信授权码");
        zhMsgMap.put(EXCEPTION_TIMESTAMP, "时间戳超时验证异常");
        zhMsgMap.put(EXCEPTION_SIGN, "签名验证异常");
        zhMsgMap.put(EXCEPTION_GATEWAY, "网关层过滤器验证异常");
        zhMsgMap.put(EXCEPTION_BLACKLIST, "黑名单验证异常");
        zhMsgMap.put(FAIL_REPEAT, "请勿频繁重复发送短信");
        zhMsgMap.put(FAIL_TEMPLATE, "获取短信模板列表失败");
        zhMsgMap.put(NOT_EXISTS_SMSTEMPLATE, "获取不到模板id对应短信模板记录");
        zhMsgMap.put(FAIL_SMSTEMPLATE_PRASE, "替换内容与替换参数不匹配");
        zhMsgMap.put(FAIL_SMS_SEND, "短信发送失败");
        zhMsgMap.put(FAIL_SMS_CHANNEL, "短信渠道失败");
        zhMsgMap.put(EXCEPTION_SMS_SEND, "发送短信异常");
        zhMsgMap.put(EXCEPTION_PRASE_FORM, "form解析异常");
        zhMsgMap.put(EXCEPTION_UNKNOWN, "未知异常");
        zhMsgMap.put(UPLOAN_IMG_EXCEPTION, "调用图片服务上传图片文件接口发生异常！");
        zhMsgMap.put(UPLOAN_IMG_FAILURE, "调用图片服务上传图片文件失败！");
        zhMsgMap.put(MAX_UPLOAD_SIZE_EXCEEDED_EXCEPTION, "文件上传大小超限！");
        zhMsgMap.put(INTERNAL_SERVER_ERROR, "系统错误!");


    }

}
