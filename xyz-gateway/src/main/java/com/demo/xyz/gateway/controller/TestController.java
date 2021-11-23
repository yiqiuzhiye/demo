package com.demo.xyz.gateway.controller;


import com.demo.xyz.client.dto.auth.LoginDto;
import com.demo.xyz.client.dto.auth.StaffDto;
import com.demo.xyz.client.service.AuthRemoteService;
import com.demo.xyz.common.core.R;
import com.demo.xyz.common.core.ServiceException;
import com.demo.xyz.gateway.config.JwtTokenUtil;
import com.demo.xyz.gateway.config.MyUserService;
import com.demo.xyz.gateway.config.SecurityUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/v1")
public class TestController {

    private final MyUserService myUserService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthRemoteService authRemoteService;
    private final RedisTemplate redisTemplate;
    private final String loginUserKey = "xyz:user:login:";

    @GetMapping("/login")
    public String login(@RequestParam("username")String username,@RequestParam("password")String password) {
        LoginDto dto = new LoginDto();
        // Object o =(StaffDto) redisTemplate.opsForValue().get(loginUserKey + username);
        dto.setUsername(username);
        dto.setPassword(password);
        StaffDto staffDto = R.getSuccessData(authRemoteService.login(dto));
        if (staffDto == null){
            throw new ServiceException(400,"用户名或密码错误");
        }
        SecurityUser securityUser = new SecurityUser();
        BeanUtils.copyProperties(staffDto,securityUser);
        return jwtTokenUtil.generateToken(securityUser);
    }

    @GetMapping("/test")
    public String test(){
        System.out.println();
        return "访问成功";
    }

}
