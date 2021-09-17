package com.demo.xyz.common.core.config;

import com.demo.xyz.common.core.util.ThreadLocalUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class WebFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String guid = httpServletRequest.getHeader("guid");
        if (StringUtils.isNotBlank(guid)) {
            ThreadLocalUtils.setValue(guid);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
