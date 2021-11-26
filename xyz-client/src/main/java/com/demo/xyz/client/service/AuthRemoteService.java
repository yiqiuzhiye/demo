package com.demo.xyz.client.service;


import com.demo.xyz.client.dto.auth.LoginDto;
import com.demo.xyz.client.dto.auth.StaffDto;
import com.demo.xyz.client.factory.AuthRemoteServiceFallbackFactory;
import com.demo.xyz.client.service.impl.AuthRemoteServiceFallbackImpl;
import com.demo.xyz.common.core.CommonUser;
import com.demo.xyz.common.core.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "xyz-auth",fallback = AuthRemoteServiceFallbackImpl.class,fallbackFactory = AuthRemoteServiceFallbackFactory.class)
public interface AuthRemoteService {
    @PostMapping("/remote/login")
    R<CommonUser> login(@RequestBody LoginDto dto);
}
