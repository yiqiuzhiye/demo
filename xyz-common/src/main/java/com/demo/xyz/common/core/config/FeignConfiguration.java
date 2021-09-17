package com.demo.xyz.common.core.config;

import com.demo.xyz.common.core.feign.opentracing.StandardTagsWrapper;
import feign.Logger;
import feign.RequestInterceptor;
import feign.opentracing.FeignSpanDecorator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

/**
 * @author charles.ouyang
 */
@Configuration
public class FeignConfiguration {

	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}

	@Bean
	public RequestInterceptor getRequestInterceptor() {
		return new FeignRequestInterceptor();
	}

	/**
	 * feign调用tag写入请求和响应数据
	 *
	 * @return
	 */
	@Bean
	List<FeignSpanDecorator> spanDecorators() {

		return Collections.<FeignSpanDecorator>singletonList(
				new StandardTagsWrapper(new FeignSpanDecorator.StandardTags()));
	}

}