
package com.demo.xyz.common.core.config;

import cn.hutool.core.date.DatePattern;
import com.demo.xyz.common.core.jackson.CrssJavaLongModule;
import com.demo.xyz.common.core.jackson.CrssJavaTimeModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

/**
 * JacksonConfig
 * 
 * @author admin
 *
 */
@Configuration
@ConditionalOnClass(ObjectMapper.class)
@AutoConfigureBefore(JacksonAutoConfiguration.class)
public class JacksonConfig {
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer customizer() {
		return builder -> {
			builder.locale(Locale.CHINA);
			builder.timeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
			builder.simpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
			builder.modules(new CrssJavaTimeModule(), new CrssJavaLongModule());

		};
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * Date date = new Date();
	 * 
	 * System.out.println("date:" + DatePattern.UTC_FORMAT.format(date));
	 * 
	 * }
	 */
}
