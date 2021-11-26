package com.demo.xyz.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private RestAccessDeniedHandler restAccessDeniedHandler;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/v1/login");
//    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //使用自己的前置拦截器
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // 定制我们自己的 session 策略：调整为让 Spring Security 不创建和使用 session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 请求进行拦截 验证 accessToken
        http.authorizeRequests().antMatchers("/v1/login").permitAll().anyRequest().authenticated().and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).accessDeniedHandler(restAccessDeniedHandler)
                .and()
                .cors()
                // 关闭csrf防护
                .and()
                .csrf()
                .disable();
    }



    //SpringBoot2.x后需要使用BCrypt强哈希方法来加密密码，如果不加的话登录不上并且控制台会有警告Encoded password does not look like BCrypt
//    @Bean
//    public BCryptPasswordEncoder PasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Autowired
//    public void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(myUserService);
//    }

//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(myUserService);
//    }

//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
}
