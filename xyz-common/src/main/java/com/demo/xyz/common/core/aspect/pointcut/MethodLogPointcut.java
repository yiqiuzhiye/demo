package com.demo.xyz.common.core.aspect.pointcut;

import com.demo.xyz.common.core.advice.MethodLogAdvice;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 	定义记录日志的切入点，并将日志切面类MethodLogAdvice实例注入到Spring容器：
 * 	注意:该类必须要放在子服务的包路径下:com.cloudminds.crss.aspect.pointcut
 * @author alex.xu
 * @version 2019年8月28日
 * @see MethodLogPointcut
 * @since
 */
@Configuration
public class MethodLogPointcut {

  @Pointcut("execution(* com.cloudminds.crss..*.controller.*.*(..))")
	public void pointcutLog() {}
	
    @Bean
    MethodLogAdvice getMethodLogAdvice() {
        return new MethodLogAdvice();
    }
}
