package com.demo.xyz.common.core.env;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

/**
 * @author Charles Ouyang
 * @date 2021/04/15 14:35
 */
@Component
@Slf4j
public class CrossEnvironmentPostProcessor implements EnvironmentPostProcessor {

  @Override
  public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

    String nacosGroup = environment.getProperty("NACOS_GROUP");
    String appEnv = null;
    if (StringUtils.isNotBlank(nacosGroup)) {
      String[] arr = nacosGroup.split("\\.");
      if (arr.length == 2) {
        appEnv = arr[1];
        System.getenv().put("APP_ENV", appEnv);
      }
    }
    log.info(">>>>>>postProcessEnvironment nacosGroup:{},appEnv:{}", nacosGroup, appEnv);

  }
}
