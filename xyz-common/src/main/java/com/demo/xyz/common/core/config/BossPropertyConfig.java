package com.demo.xyz.common.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "boss")
@Data
@RefreshScope
public class BossPropertyConfig {
	// 环境信息
	private String env;
	// boss api入口
	private String api;

}
