package com.demo.xyz.common.core.feign;

import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import feign.optionals.OptionalDecoder;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 增加feign调用链路追踪配置，将feign调用的请求和响应数据写入span的Tag标签
 * <p>使用说明：在@FeignClient中引入配置，同时配置logback.xml为debug，并将LogLevel设置为full
 *
 * @author charles.ouyang
 */
@Slf4j
public class CrssFeignTraceConfig {

  @Autowired
  private ObjectFactory<HttpMessageConverters> messageConverters;

  @Bean
  public Decoder feignDecoder() {
    return new OptionalDecoder(
        new ResponseEntityDecoderWrapper(new ResponseEntityDecoder(new SpringDecoder(this.messageConverters))));
  }


  public static class ResponseEntityDecoderWrapper extends ResponseEntityDecoder {


    public ResponseEntityDecoderWrapper(Decoder decoder) {
      super(decoder);
    }

    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {

      if (response.body().isRepeatable()) {
        try {
          String url = response.request().url();
          String reqBody = new String(response.request().body());
          String respBody = IOUtils.toString(response.body().asInputStream(), "UTF-8");
          log.info(">>>>>feign execute,url:{},body:{},resp:{}", url, reqBody, respBody);
          Tracer tracer = GlobalTracer.get();
          if (tracer != null &&
              tracer.activeSpan() != null) {

            tracer.activeSpan().setTag("cross.req_data", respBody).setTag("cross.req_url", url)
                .setTag("cross.resp_data", respBody);
          }
        } catch (Exception ex) {
          log.error("CrssFeignTraceConfig exception:", ex);
        }
      }
      return super.decode(response, type);
    }
  }

}
