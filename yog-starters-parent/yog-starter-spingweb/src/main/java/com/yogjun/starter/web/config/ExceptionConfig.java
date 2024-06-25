package com.yogjun.starter.web.config;

import cn.hutool.core.map.MapUtil;
import java.io.Serializable;
import java.util.Map;

import lombok.Data;
import org.springframework.boot.logging.LogLevel;

@Data
public class ExceptionConfig implements Serializable {

  private boolean printStackTrace = false;

  private boolean printMixmicroStackTrace = true;

  private Class<?> handlerClass;

  /**
   * Default Biz Exception Code
   *
   * <p><b>warning:: not http status code</b>
   */
  private int defaultExceptionResponseCode = -1;

  private Map<Class<? extends Exception>, Boolean> sensitiveStacks = MapUtil.newHashMap();

  /** Logging levels */
  private Map<Class<? extends Exception>, LogLevel> levels = MapUtil.newHashMap();
}
