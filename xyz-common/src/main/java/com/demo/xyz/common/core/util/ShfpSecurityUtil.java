package com.demo.xyz.common.core.util;

import cn.hutool.core.text.UnicodeUtil;
import cn.hutool.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;

/**
 * 上海扶贫安全工具类
 *
 * @author charles.ouyang
 */
public class ShfpSecurityUtil {

    private final static Logger logger = LoggerFactory.getLogger(ShfpSecurityUtil.class);

    private final static String CHARACTER_ENCODING = "UTF-8";

    public static String sign(Map<String, Object> paramMap, String privateKey) {

        JSONObject paramJson = new JSONObject(true);
        String[] keyArr = paramMap.keySet().toArray(new String[0]);
        Arrays.sort(keyArr);
        for (String key : keyArr) {
            paramJson.put(key, paramMap.get(key));
        }
        // 需要对中文Unicode编码
        String unicodeParamStr = UnicodeUtil.toUnicode(paramJson.toString()).replace("\\\\","\\");
        // String unicodeParamStr = UnicodeUtil.toUnicode(paramJson.toString());
        logger.info("Unicode param:{}", unicodeParamStr);
        String md5 = EncryptUtils.encryptMD5(unicodeParamStr).toUpperCase();
        // String md5 = EncryptUtils.encryptMD5(paramJson.toString()).toUpperCase();

        String sign = RSA2.sign(md5, privateKey, CHARACTER_ENCODING);

        logger.info("Sorted param:{},md5:{},sign:{}", paramJson.toString(), md5, sign);

        return sign;
    }

    public static String decrypt(String encryptData, String privateKey) {

        try {
            String decryptData = RSA2.decrypt(encryptData, privateKey, CHARACTER_ENCODING);
            logger.info("Do decrypt,encryptData:{},decryptData:{}", encryptData, decryptData);
            return decryptData;

        } catch (Exception ex) {

            logger.error("decrypt exception:", ex);
            return null;
        }

    }


    public static String string2Unicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }
        return unicode.toString();
    }
}
