package com.demo.xyz.common.filter;


import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.ObjectUtil;
import com.demo.xyz.common.core.CommonUser;
import com.demo.xyz.common.core.CommonUserContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Jiahong.Li
 * @date 2021-11-24 18:54:23
 */
@Slf4j
public class UserContextInitFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String base64UserInfo = httpRequest.getHeader("x-user-info");
        if (StringUtils.isNotBlank(base64UserInfo)) {
            CommonUser commonUser = ObjectUtil.unserialize(Base64.decode(base64UserInfo));
            CommonUserContext.setContext(commonUser);
        }
        chain.doFilter(request, response);
    }
}
