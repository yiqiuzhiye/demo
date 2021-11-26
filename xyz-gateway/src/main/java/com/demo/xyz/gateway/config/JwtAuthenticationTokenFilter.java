package com.demo.xyz.gateway.config;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.ObjectUtil;
import com.demo.xyz.common.constant.RedisKey;
import com.demo.xyz.common.core.CommonUser;
import com.netflix.zuul.context.RequestContext;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final RedisTemplate redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = request.getHeader(jwtTokenUtil.getHeader());//获取token
        if (!jwtTokenUtil.isTokenExpired(token)) {
            String username = jwtTokenUtil.getUserFromToken(token);//取出token的用户信息
            CommonUser commonUser =(CommonUser) redisTemplate.opsForValue().get(RedisKey.LOGIN_USER_CACHE_KEY + username);
            //SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_user");
            // 将用户信息存入 authentication，校验
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(commonUser, null, null);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // 设置 authentication
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // 把用户信息存入zuul请求头，方便其他服务取用户信息
            RequestContext ctx = RequestContext.getCurrentContext();
            String base64UserInfo = Base64.encode(ObjectUtil.serialize(commonUser));
            ctx.addZuulRequestHeader("x-user-info", base64UserInfo);
        }
        chain.doFilter(request, response);
    }
}