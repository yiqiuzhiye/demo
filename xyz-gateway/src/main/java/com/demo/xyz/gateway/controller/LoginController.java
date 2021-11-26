package com.demo.xyz.gateway.controller;


import com.demo.xyz.client.dto.auth.LoginDto;
import com.demo.xyz.client.service.AuthRemoteService;
import com.demo.xyz.common.constant.RedisKey;
import com.demo.xyz.common.core.CommonUser;
import com.demo.xyz.common.core.R;
import com.demo.xyz.common.core.ServiceException;
import com.demo.xyz.gateway.config.JwtTokenUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/v1")
public class LoginController {

    private final JwtTokenUtil jwtTokenUtil;
    private final AuthRemoteService authRemoteService;
    private final RedisTemplate redisTemplate;


    @GetMapping("/login")
    public String login(@RequestParam("username")String username,@RequestParam("password")String password) {
        LoginDto dto = new LoginDto();
        CommonUser commonUser =(CommonUser) redisTemplate.opsForValue().get(RedisKey.LOGIN_USER_CACHE_KEY + username);
        if(commonUser == null){
            dto.setUsername(username);
            commonUser = R.getSuccessData(authRemoteService.login(dto));
            redisTemplate.opsForValue().set(RedisKey.LOGIN_USER_CACHE_KEY + username,commonUser);
        }
        if (commonUser == null){
            throw new ServiceException(400,"用户名或密码错误");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(! encoder.matches(password,commonUser.getPassword())){
            throw new ServiceException(400,"用户名或密码错误");
        }
        return jwtTokenUtil.generateToken(commonUser.getUsername());
    }

    @GetMapping("/test")
    public String test(){
        System.out.println();
        return "访问成功";
    }

}
