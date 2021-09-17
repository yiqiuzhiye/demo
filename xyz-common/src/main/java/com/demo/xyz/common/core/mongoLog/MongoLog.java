package com.demo.xyz.common.core.mongoLog;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author alex.zhao
 * @date 2021-04-21
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MongoLog {

    //机器人id
    public String robotCode();

    //发送端  "1"===="cross请求";"2"===="hari上报";"3"===="roc上报";
    public int type();

    //消息类型
    public String messageType();
}
