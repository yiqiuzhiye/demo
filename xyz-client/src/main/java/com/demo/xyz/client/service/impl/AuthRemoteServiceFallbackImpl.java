package com.demo.xyz.client.service.impl;

import com.demo.xyz.client.dto.auth.LoginDto;
import com.demo.xyz.client.dto.auth.StaffDto;
import com.demo.xyz.client.service.AuthRemoteService;
import com.demo.xyz.common.core.R;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthRemoteServiceFallbackImpl implements AuthRemoteService {
    @Setter
    private Throwable cause;
    @Override
    public R<StaffDto> login(LoginDto dto) {
        log.error("login:{}, error:{}", cause.getMessage(), cause);
        return null;
    }
}
