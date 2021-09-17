package com.demo.xyz.common.core.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    private static JsonUtils jacksonUtil = new JsonUtils();

    private static ObjectMapper mapper;

    private JsonUtils() {
        mapper = new ObjectMapper();
        // 反序列化
        // 禁止遇到空原始类型时抛出异常，用默认值代替。
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        // 禁止遇到未知（新）属性时报错，支持兼容扩展
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
    }

    public static ObjectMapper getInstance() {
        return jacksonUtil.mapper;
    }


    /**
     * 功能描述:  json转化为java bean
     * Author: xuyanxiong
     * Date:   2017年3月15日 下午6:15:32
     * return  T
     */
    public static <T> T jsonToBean(String json, Class<T> valueType) {
        if (!StringUtils.isBlank(json)) {
            try {
                return getInstance().readValue(json, valueType);
            } catch (JsonParseException e) {
                logger.error(e.getMessage(), e);
            } catch (JsonMappingException e) {
                logger.error(e.getMessage(), e);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return null;
    }


    /**
     * 功能描述:  java bean转化为json
     * Author: xuyanxiong
     * Date:   2017年3月15日 下午6:15:16
     * return  String
     */
    public static String beanToJson(Object bean) {
        try {
            return getInstance().writeValueAsString(bean);
        } catch (JsonGenerationException e) {
            logger.error(e.getMessage(), e);
        } catch (JsonMappingException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }


    /**
     * 功能描述:  json转list
     * Author: xuyanxiong
     * Date:   2017年3月15日 下午6:10:54
     * return  List
     */
    public static <T> List<T> jsonToList(String json, Class<T> clazz) {
        List<T> list = null;
        if (StringUtils.isNotBlank(json)) {
            try {
                list = getInstance().readValue(json, new TypeReference<List<T>>() {
                });
            } catch (JsonGenerationException e) {
                logger.error(e.getMessage(), e);
            } catch (JsonMappingException e) {
                logger.error(e.getMessage(), e);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return list;
    }

    public static String beanToJsonAndFmDate(Object channelList) {
        return beanToJson(channelList);
    }

    /**
     * 集合转json
     *
     * @param list
     * @return author lusongjiong
     */
    public static String ListToJson(List list) {
        String str = null;
        try {
            str = getInstance().writeValueAsString(list);
        } catch (JsonGenerationException e) {
            logger.error(e.getMessage(), e);
        } catch (JsonMappingException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return str;
    }


    /**
     * 解决从mysql的json类型List,导致List无法遍历问题
     *
     * @param list
     * @return
     */
    public static <T> T[] typeConverse(List<T> list, Class<T[]> valueType) {
        String str = ListToJson(list);
        T[] arr = null;
        try {
            arr = mapper.readValue(str, valueType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }

    /**
     * json 转hashMap
     * @param json
     * @param valueType
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> jsonToMap(String json, Class<V> valueType) {
        Map<K, V> result = null;
        JavaType javaType2 = mapper.getTypeFactory().constructParametricType(HashMap.class, String.class, valueType);
        try {
            result = mapper.readValue(json, javaType2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Map<String, Object> objectToMap(Object obj, DateFormat fmt)  {
        Map<String, Object> map = new HashMap<String,Object>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = null;
            try {
                if(field.getGenericType().toString().equals( "class java.util.Date")){
                    value = field.get(obj);
                    if(value!=null){
                        value=fmt.format(field.get(obj));
                    }
                }else {
                    value = field.get(obj);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            map.put(fieldName, value);
        }
        return map;
    }
}
