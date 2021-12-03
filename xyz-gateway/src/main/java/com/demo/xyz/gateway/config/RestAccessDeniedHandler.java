//package com.demo.xyz.gateway.config;
//
//import com.demo.xyz.common.core.R;
//import com.demo.xyz.common.util.MyJsonUtils;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class RestAccessDeniedHandler implements AccessDeniedHandler {
//    @Override
//    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
//
//        httpServletResponse.setContentType("application/json;charset=UTF-8");
//        //按照系统自定义结构返回授权失败
//        httpServletResponse.getWriter().write(MyJsonUtils.beanToJson(new R<>(40001,"登陆信息已失效，请重新登陆")));
//    }
//}