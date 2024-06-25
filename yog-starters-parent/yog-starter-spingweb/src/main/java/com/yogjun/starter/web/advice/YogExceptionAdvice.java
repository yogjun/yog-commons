package com.yogjun.starter.web.advice;

import com.yogjun.api.commons.bean.bean.YogResponseEntity;
import com.yogjun.api.exception.BizException;
import com.yogjun.api.exception.YogException;
import com.yogjun.starter.web.config.YogWebConfigProperties;
import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class YogExceptionAdvice extends AbstractAdvice {

  private YogExceptionHandler handler;

  @Nullable
  private YogExceptionHandler getExceptionHandlerFromBeanFactory() {
    if (beanFactory != null) {
      try {
        return beanFactory.getBean(YogExceptionHandler.class);
      } catch (Exception ignored) {
      }
    }
    return null;
  }

  @Nullable
  private YogExceptionHandler getExceptionHandler() {

    if (this.handler != null) {
      return this.handler;
    }

    YogWebConfigProperties properties = getProperties();
    try {
      Class<?> aClass = properties.getException().getHandlerClass();
      if (aClass != null) {
        try {
          Constructor<?> constructor = aClass.getConstructor();
          Object o = constructor.newInstance();
          this.handler = (YogExceptionHandler) o;
        } catch (Exception e) {
          log.warn("@ExceptionHandler extended class must default constructor .");
        }
      }
    } catch (Exception ignored) {
    }

    if (this.handler == null) {
      this.handler = getExceptionHandlerFromBeanFactory();
    }

    return this.handler;
  }

  private Optional<ResponseEntity<?>> handlerException(Exception e) {
    YogExceptionHandler handler = getExceptionHandler();
    if (handler != null) {
      return Optional.ofNullable(handler.handlerException(e));
    }
    return Optional.empty();
  }

  private void printStackTrace(Exception e) {
    if (e != null) {
      LogLevel logLevel = LogLevel.ERROR;
      Map<Class<? extends Exception>, LogLevel> levels = getProperties().getException().getLevels();
      if (levels.containsKey(e.getClass())) {
        logLevel = levels.get(e.getClass());
      }
      // global stack config
      if (getProperties().getException().isPrintStackTrace()) {
        // check exception has custom config ?
        Map<Class<? extends Exception>, Boolean> exceptions =
            getProperties().getException().getSensitiveStacks();
        if (exceptions.containsKey(e.getClass())) { // true
          Boolean isPrint = exceptions.get(e.getClass());
          if (isPrint) {
            log(logLevel, e.getMessage(), e);
          } else {
            log(logLevel, e.getMessage(), null);
          }
        } else {
          if (e instanceof YogException) {
            if (getProperties().getException().isPrintMixmicroStackTrace()) { // true
              log(logLevel, e.getMessage(), e);
            } else {
              log(logLevel, e.getMessage(), null);
            }
          } else {
            log(logLevel, e.getMessage(), e);
          }
        }
      } else {
        log(logLevel, e.getMessage(), null);
      }
    }
  }

  private void log(LogLevel level, String message, Throwable throwable) {
    switch (level) {
      case TRACE:
        log.trace(message, throwable);
        return;
      case DEBUG:
        log.debug(message, throwable);
        return;
      case INFO:
        log.info(message, throwable);
        return;
      case WARN:
        log.warn(message, throwable);
        return;
      case ERROR:
      case FATAL:
        log.error(message, throwable);
        return;
      case OFF:
      default:
    }
  }

  /**
   * 缺少参数异常拦截
   *
   * @param e MissingServletRequestParameterException
   * @return 缺少返回返回值
   */
  @ExceptionHandler(value = MissingServletRequestParameterException.class)
  public ResponseEntity<?> missingServletRequestParameterException(
      MissingServletRequestParameterException e) {
    printStackTrace(e);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(
            YogResponseEntity.fail(
                HttpStatus.BAD_REQUEST.value(),
                "missing request parameter(s) [" + e.getParameterName() + "]"));
  }

  // === 定义自定义异常全局拦截机制

  /**
   * 全局业务Service处理异常
   *
   * @return 返回值
   */
  @ExceptionHandler(value = BizException.class)
  public ResponseEntity<?> bizServiceException(BizException e) {
    printStackTrace(e);

    handlerException(e);

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
            YogResponseEntity.fail(
                getProperties().getException().getDefaultExceptionResponseCode(), e));
  }
}
