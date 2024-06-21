package com.yogjun.starter.web.advice;

import com.yogjun.api.commons.bean.bean.ResponseEntity;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * {@link YogjunResponseBodyAdvice}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/21
 */
@RestControllerAdvice
public class YogjunResponseBodyAdvice extends AbstractAdvice implements ResponseBodyAdvice<Object> {

  @Override
  public boolean supports(
      MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
    return true;
  }

  @Override
  public Object beforeBodyWrite(
      Object body,
      MethodParameter returnType,
      MediaType selectedContentType,
      Class<? extends HttpMessageConverter<?>> selectedConverterType,
      ServerHttpRequest request,
      ServerHttpResponse response) {
    if (checkIgnoreURL(request.getURI().getPath())) {
      return body;
    }
    return ResponseEntity.builder()
        .code(getProperties().getResponse().getDefaultSuccessResponseCode())
        .data(body)
        .build();
  }
}
