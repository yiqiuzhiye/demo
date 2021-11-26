package com.demo.xyz.gateway;

import com.demo.xyz.common.filter.UserContextInitFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableZuulProxy
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class},scanBasePackages = {"com.demo.xyz.gateway","com.demo.xyz.common"})
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.demo.xyz.client")
public class XyzGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(XyzGatewayApplication.class, args);
    }
}
