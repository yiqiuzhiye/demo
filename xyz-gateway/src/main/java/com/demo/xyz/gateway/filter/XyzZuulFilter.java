//package com.demo.xyz.gateway.filter;
//
//import cn.hutool.core.codec.Base64;
//import cn.hutool.core.util.ObjectUtil;
//import com.demo.xyz.common.core.CommonUser;
//import com.demo.xyz.gateway.config.JwtTokenUtil;
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Slf4j
//@Component
//@AllArgsConstructor
//public class XyzZuulFilter extends ZuulFilter {
//
//	private final JwtTokenUtil jwtTokenUtil;
//
//	@Override
//	public boolean shouldFilter() {
//
//		return true;
//	}
//
//	/**
//	 * 过滤逻辑
//	 *
//	 * @return 过滤结果
//	 */
//	@Override
//	public Object run() throws ZuulException {
//		RequestContext ctx = RequestContext.getCurrentContext();
//		HttpServletRequest request = ctx.getRequest();
//
//		String token = request.getHeader(jwtTokenUtil.getHeader());//获取token
//		if (StringUtils.isNotBlank(token) && !jwtTokenUtil.isTokenExpired(token)) {
//			CommonUser user = getUser();
//			String base64UserInfo = Base64.encode(ObjectUtil.serialize(user));
//			ctx.addZuulRequestHeader("x-user-info", base64UserInfo);
//		}else {
//			ctx.addZuulRequestHeader("x-user-info",null);
//			ctx.setSendZuulResponse(false);
//			ctx.setResponseStatusCode(401);
//			ctx.setResponseBody("token is invalid");
//		}
//		log.info("ZuulFilter:send {} request to {}", request.getMethod(), request.getRequestURL().toString());
//		return null;
//	}
//
//	/**
//	 * 过滤器的类型 pre表示请求在路由之前被过滤
//	 *
//	 * @return 类型
//	 */
//
//	@Override
//	public String filterType() {
//		return "pre";
//	}
//
//	/**
//	 * 过滤器的执行顺序
//	 *
//	 * @return 顺序 数字越大表示优先级越低，越后执行
//	 */
//	@Override
//	public int filterOrder() {
//		return 0;
//	}
//
//	/**
//	 * 获取用户
//	 */
//	public CommonUser getUser() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (authentication == null) {
//			return null;
//		}
//		Object principal = authentication.getPrincipal();
//		if (principal instanceof CommonUser) {
//			return (CommonUser) principal;
//		}
//		return null;
//	}
//
//}
