package com.demo.xyz.common.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;

@Slf4j
public class MyJsonUtils {
    private static final ObjectMapper OBJECT_MAPPER = objectMapper();

    private MyJsonUtils() {
    }

    /**
     * 功能描述:  json转化为java bean
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T jsonToBean(final String json, final Class<T> clazz) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (final IOException e) {
            log.warn("parse json string error --> {}" + json, e);
            return null;
        }
    }

    /**
     * 通用的ObjectMapper，定义好一个标准处理json的方式，所有项目都使用这个来实现
     *
     * @return
     */
    public static ObjectMapper objectMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        // 反序列化
        // 禁止遇到空原始类型时抛出异常，用默认值代替。
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        // 禁止遇到未知（新）属性时报错，支持兼容扩展
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        return mapper;
    }

    /**
     * 功能描述:  java bean转化为json
     * @param bean
     * @return
     */
    public static String beanToJson(final Object bean) {
        try {
            return OBJECT_MAPPER.writeValueAsString(bean);
        } catch (final IOException e) {
            log.warn("write to json string error --> {} " + bean, e);
            return null;
        }
    }
}
