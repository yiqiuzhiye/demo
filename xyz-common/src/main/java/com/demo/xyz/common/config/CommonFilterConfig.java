package com.demo.xyz.common.config;

import com.demo.xyz.common.filter.UserContextInitFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jiahong.Li
 * @date 2021-11-24 18:54:51
 */
@Configuration
public class CommonFilterConfig {

  @Bean
  public FilterRegistrationBean webFilterRegistrationBean() {
    FilterRegistrationBean registrationBean = new FilterRegistrationBean(new UserContextInitFilter());
    //过滤所有路径
    registrationBean.addUrlPatterns("/*");
    registrationBean.setName("guidFilter");
    registrationBean.setOrder(1);
    return registrationBean;
  }

  @Bean
  public FilterRegistrationBean userContextInitFilterRegistrationBean() {
    FilterRegistrationBean registrationBean = new FilterRegistrationBean(new UserContextInitFilter());
    registrationBean.addUrlPatterns("/*");
    registrationBean.setName("userContextInitFilter");
    registrationBean.setOrder(2);
    return registrationBean;
  }


}
