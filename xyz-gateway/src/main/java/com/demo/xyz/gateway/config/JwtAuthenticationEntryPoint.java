package com.demo.xyz.gateway.config;

import com.demo.xyz.common.core.R;
import com.demo.xyz.common.util.MyJsonUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        //按照系统自定义结构返回授权失败
        response.getWriter().write(MyJsonUtils.beanToJson(new R<>(40002,"登陆信息无效，请重新登陆再访问")));
    }
}