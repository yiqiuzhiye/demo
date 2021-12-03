package com.demo.xyz.gateway.service.impl;

import com.demo.xyz.client.dto.auth.LoginDto;
import com.demo.xyz.client.service.AuthRemoteService;
import com.demo.xyz.common.constant.RedisKey;
import com.demo.xyz.common.core.CommonUser;
import com.demo.xyz.common.core.R;
import com.demo.xyz.common.core.ServiceException;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 重写登陆验证逻辑
 */
@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final RedisTemplate redisTemplate;
    private final AuthRemoteService authRemoteService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CommonUser commonUser =(CommonUser) redisTemplate.opsForValue().get(RedisKey.LOGIN_USER_CACHE_KEY + username);
        if(commonUser == null){
            try {
                LoginDto dto = new LoginDto();
                dto.setUsername(username);
                commonUser = R.getSuccessData(authRemoteService.login(dto));
            }catch (Exception e){
                throw new ServiceException(500,"服务异常，请稍后重试");
            }
            if (commonUser == null){
                throw new UsernameNotFoundException("账号或密码错误");
            }
            redisTemplate.opsForValue().set(RedisKey.LOGIN_USER_CACHE_KEY + username,commonUser);
        }
        return new User(commonUser.getUsername(), commonUser.getPassword(), Arrays.asList(new SimpleGrantedAuthority("user")));
    }
}