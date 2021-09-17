package com.demo.xyz.common.core.config;

import com.demo.xyz.common.core.aspect.pointcut.MethodLogPointcut;
import com.demo.xyz.common.core.exception.GlobalExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Cross基础配置类
 *
 * @author Charles Ouyang
 * @date 2021/08/11 18:44
 */
@Configuration
@Import({MethodLogPointcut.class, DateConfig.class, FeignConfiguration.class, FilterIgnorePropertiesConfig.class,
    JacksonConfig.class,
    JaegerConfig.class, RestTemplateConfig.class, CommonFilterConfig.class, BossPropertyConfig.class,
    RedisConfig.class})
@ComponentScan(basePackageClasses = {GlobalExceptionHandler.class})
public class CrssBaseConfig {


}
