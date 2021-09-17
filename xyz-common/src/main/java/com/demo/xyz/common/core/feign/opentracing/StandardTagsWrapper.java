package com.demo.xyz.common.core.feign.opentracing;

import com.demo.xyz.common.core.util.ReflectionUtils;
import feign.Request;
import feign.Request.Options;
import feign.Response;
import feign.Util;
import feign.opentracing.FeignSpanDecorator;
import io.opentracing.Span;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * 扩展Jaeger Feign上报的tag
 *
 * @author charles.ouyang
 * @date 2020/12/10
 */
@Slf4j
public class StandardTagsWrapper implements FeignSpanDecorator {


  private StandardTags standardTags;

  public StandardTagsWrapper(StandardTags standardTags) {

    this.standardTags = standardTags;

  }

  @Override
  public void onRequest(Request request, Options options, Span span) {
    
    this.standardTags.onRequest(request, options, span);

  }

  @Override
  public void onResponse(Response response, Options options, Span span) {
    try {

      Response responseWrapper = null;
      log.info(">>>response.isRepeatable:{}", response.body().isRepeatable());
      String url = response.request().url();
      byte[] body = response.request().body();
      String reqBody = null;
      if (body != null) {
        reqBody = new String(response.request().body());
      }
      String respBody = null;
      // 不可重复读的response，需要转换为可重复读
      if (!response.body().isRepeatable()) {
        byte[] bodyData = Util.toByteArray(response.body().asInputStream());
        respBody = new String(bodyData, "UTF-8");
        // 构造可重复读的response
        responseWrapper = response.toBuilder().body(bodyData).build();
        // 只能通过反射方式将原有的response的body修改为可重复读取的
        ReflectionUtils.setFieldValue(response, "body", responseWrapper.body());
      }
      log.info(">>>>>feign onResponse,url:{},req:{},resp:{}", url, reqBody, respBody);
      if (span != null) {
        if (StringUtils.isNotBlank(reqBody)) {
          span.setTag("cross.req_data", reqBody);
        }
        if (StringUtils.isNotBlank(respBody)) {
            span.setTag("cross.resp_data", respBody);
        }
      }

    } catch (Exception ex) {
      log.error("onResponse exception:", ex);
    }

    this.standardTags.onResponse(response, options, span);
  }

  @Override
  public void onError(Exception exception, Request request, Span span) {

    this.standardTags.onError(exception, request, span);
  }
}