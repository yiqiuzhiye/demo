package com.demo.xyz.gateway.config;

import com.demo.xyz.gateway.filter.TokenAuthenticationFilter;
import com.demo.xyz.gateway.filter.TokenLoginFilter;
import com.demo.xyz.gateway.service.impl.UserDetailServiceImpl;
import com.demo.xyz.gateway.util.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final UserDetailServiceImpl userDetailService;
    private final JwtTokenUtil jwtTokenUtil;
    private final RedisTemplate redisTemplate;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 定制我们自己的 session 策略：调整为让 Spring Security 不创建和使用 session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 请求进行拦截 验证 accessToken
        http.authorizeRequests().anyRequest().authenticated()
                // 设置过滤器
                .and().addFilter(new TokenLoginFilter(authenticationManager(),jwtTokenUtil,redisTemplate)).addFilter(new TokenAuthenticationFilter(authenticationManager(),jwtTokenUtil,redisTemplate)).httpBasic()
                // 设置jwt无效的返回
                .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and().cors()
                // 关闭csrf防护
                .and().csrf().disable();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }

}
