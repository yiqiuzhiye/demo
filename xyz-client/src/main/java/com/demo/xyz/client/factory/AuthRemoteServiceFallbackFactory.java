package com.demo.xyz.client.factory;

import com.demo.xyz.client.service.AuthRemoteService;
import com.demo.xyz.client.service.impl.AuthRemoteServiceFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 服务熔断
 * 
 * @author: Sven.Fu
 * @create: 2020-01-19 14:47
 **/
@Component
public class AuthRemoteServiceFallbackFactory implements FallbackFactory<AuthRemoteService> {
    @Override
    public AuthRemoteService create(Throwable cause) {
        AuthRemoteServiceFallbackImpl fallbackService = new AuthRemoteServiceFallbackImpl();
        fallbackService.setCause(cause);
        return fallbackService;
    }
}
