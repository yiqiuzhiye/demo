package com.demo.xyz.common.core.config;

import com.demo.xyz.common.core.mdc.MDCScopeManager;
import io.opentracing.contrib.java.spring.jaeger.starter.TracerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JaegerConfig {

  @Bean
  public TracerBuilderCustomizer mdcBuilderCustomizer() {
    return builder -> builder.withScopeManager(new MDCScopeManager());
  }

}
