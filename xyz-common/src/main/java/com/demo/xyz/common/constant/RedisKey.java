package com.demo.xyz.common.constant;

public interface RedisKey {

    /**
     * 登陆用户缓存key
     */
    String LOGIN_USER_CACHE_KEY = "xyz:user:login:";
    /**
     * 用户登陆token
     */
    String LOGIN_USER_TOKEN_CACHE_KEY = "xyz:user:token:";
}
