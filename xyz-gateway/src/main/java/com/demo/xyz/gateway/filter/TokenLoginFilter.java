package com.demo.xyz.gateway.filter;

import com.demo.xyz.common.constant.RedisKey;
import com.demo.xyz.common.core.R;
import com.demo.xyz.common.core.ServiceException;
import com.demo.xyz.common.util.MyJsonUtils;
import com.demo.xyz.gateway.util.JwtTokenUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * 登陆请求过滤器
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private RedisTemplate redisTemplate;

    public TokenLoginFilter(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, RedisTemplate redisTemplate) {
        this.authenticationManager = authenticationManager;
        //this.redisTemplate = redisTemplate;
        this.setPostOnly(false);
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/v1/login","GET"));
        this.jwtTokenUtil = jwtTokenUtil;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 从登陆请求中获得用户名和密码
     * @param req
     * @param res
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        String authorization = req.getHeader("Authorization");
        if(StringUtils.isNotBlank(authorization)){
            // 刷新token

        }
        Map<String, String[]> map = req.getParameterMap();
        String username = map.get("username")[0];
        String password = map.get("password")[0];
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>()));
    }

    /**
     * 登录成功，生成token，将token存到redis，并返回token
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        User user = (User) auth.getPrincipal();
        String token = jwtTokenUtil.generateToken(user.getUsername());
        redisTemplate.opsForValue().set(RedisKey.LOGIN_USER_TOKEN_CACHE_KEY + user.getUsername(),token);
        res.addHeader("Authorization",token);
    }

    /**
     * 登录失败
     * @param request
     * @param response
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        R<Object> r = new R<>(40001, e.getMessage());
        if(e instanceof BadCredentialsException){
            r.setCode(40001);
            r.setMessage("账号或密码错误");
        }
        if(e.getCause() instanceof ServiceException){
            r.setCode(((ServiceException) e.getCause()).getCode());
            r.setMessage(e.getMessage());
        }
        response.getWriter().write(MyJsonUtils.beanToJson(r));
    }
}
