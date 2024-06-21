package com.yogjun.starter.web.config;

import cn.hutool.core.collection.CollUtil;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.InitializingBean;

@Data
public class ResponseConfig implements Serializable, InitializingBean {

  private int defaultSuccessResponseCode = 0;

  @Getter
  private static final List<String> DEFAULT_URIS =
      CollUtil.newArrayList(
          "/actuator/prometheus",
          "/v2/api-docs",
          "/swagger-resources",
          "/swagger-ui.html",
          "/webjars");

  private List<String> ignoreWrapUris = CollUtil.newArrayList();

  @Override
  public void afterPropertiesSet() throws Exception {
    ignoreWrapUris.addAll(DEFAULT_URIS);
  }
}
