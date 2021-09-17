package com.demo.xyz.common.core.filter;


import cn.hutool.core.util.ObjectUtil;
import com.demo.xyz.common.core.util.Base64;
import com.demo.xyz.common.security.service.CrssUserInfo;
import com.demo.xyz.common.security.util.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Charles Ouyang
 * @date 2021/08/11 17:47
 */
@Slf4j
@javax.servlet.annotation.WebFilter(filterName = "userContextInitFilter", urlPatterns = "/*")
public class UserContextInitFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    String authorization = httpRequest.getHeader("Authorization");
    String username = httpRequest.getHeader("x-username");
    String base64UserInfo = httpRequest.getHeader("x-user-info");
    log.info(">>>>authorization:{},username:{},userInfo:{}", authorization, username, base64UserInfo);
    if (StringUtils.isNotBlank(username)) {
      CrssUserInfo crssUserInfo = ObjectUtil.unserialize(Base64.decode(base64UserInfo));
      log.info("Init UserContextHolder....");
      UserContextHolder.setContext(crssUserInfo);
    }
    chain.doFilter(request, response);
    log.info("Clear UserContextHolder....");
    UserContextHolder.clearContext();


  }
}
