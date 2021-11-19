package com.demo.xyz.gateway.controller;

import com.demo.xyz.client.dto.auth.LoginDto;
import com.demo.xyz.client.dto.auth.StaffDto;
import com.demo.xyz.client.service.AuthRemoteService;
import com.demo.xyz.gateway.config.JwtTokenUtil;
import com.demo.xyz.gateway.config.MyUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/v1")
public class TestController {

    private final MyUserService myUserService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthRemoteService authRemoteService;

    @GetMapping("/login")
    public String login() {
        LoginDto dto = new LoginDto();
        dto.setUsername("Jiahong.Li");
        StaffDto login = authRemoteService.login(dto);

        UserDetails userDetails = myUserService.loadUserByUsername("106200@qq.com");
        return jwtTokenUtil.generateToken(userDetails);
    }

    @GetMapping("/test")
    public String test(){
        System.out.println();
        return "访问成功";
    }

}
