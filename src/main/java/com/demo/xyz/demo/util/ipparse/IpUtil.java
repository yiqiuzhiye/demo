package com.demo.xyz.demo.util.ipparse;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

@Slf4j
public class IpUtil {
	
	
	public static String getIpAddr(HttpServletRequest request) {
		
		  /* Enumeration<String> headerNames = request.getHeaderNames();
		    while (headerNames.hasMoreElements()) {
		        String s = headerNames.nextElement();
		        String header = request.getHeader(s);
		        System.err.print(s+"=" + header);
		        log.info(s+"=" + header);
		    }*/
		
		String ipAddress = request.getHeader("x-forwarded-for");
	    if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
	        ipAddress = request.getHeader("Proxy-Client-IP");
	    }
	    if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
	        //X-Real-IP：nginx服务代理
	        ipAddress = request.getHeader("X-Real-IP");
	    }
	    if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
	        ipAddress = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {

	        ipAddress = request.getHeader("HTTP_CLIENT_IP");
	    }
	    if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {

	        ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
	    }
	    if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
	        ipAddress = request.getRemoteAddr();
	        if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
	            // 根据网卡取本机配置的IP
	            InetAddress inet = null;
	            try {
	                inet = InetAddress.getLocalHost();
		            ipAddress = inet.getHostAddress();
	            } catch (Exception e) {
	    			log.error("getIpAddr() got Exception:{}", e.getMessage(), e);
	            }

	        }

	    }

	    // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
	    if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
	        // = 15
	        if (ipAddress.indexOf(",") > 0) {
	            ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
	        }
	    }
	    return ipAddress;

	}
	
	public static String getUserAgent(HttpServletRequest request) { 
		
		String userAgent = request.getHeader("User-Agent");
		
		return userAgent == null?"":userAgent;
	}
	
	

}
