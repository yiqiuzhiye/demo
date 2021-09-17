package com.demo.xyz.common.core.config;

import com.demo.xyz.common.core.util.ThreadLocalUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
//                .getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        Enumeration<String> headerNames = request.getHeaderNames();
//        if (headerNames != null) {
//            while (headerNames.hasMoreElements()) {
//                String name = headerNames.nextElement();
//                String values = request.getHeader(name);
//                requestTemplate.header(name, values);
//            }
//        }
        String s=ThreadLocalUtils.getValue();
        if(StringUtils.isNotBlank(s)){
            requestTemplate.header("guid", s);
        }
    }
}