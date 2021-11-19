package com.demo.xyz.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserService implements UserDetailsService {

 
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SecurityUser securityUser = new SecurityUser();
        if("106200@qq.com".equals(s)){
            securityUser.setEmail("106200@qq.com");
            List<SecurityRole> roleList = new ArrayList<>();
            SecurityRole role = new SecurityRole();
            role.setName("user");
            roleList.add(role);
            return securityUser;
        }else {
            return null;
        }
    }
}