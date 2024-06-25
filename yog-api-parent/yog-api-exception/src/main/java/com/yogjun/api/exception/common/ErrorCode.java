package com.yogjun.api.exception.common;

import org.springframework.http.HttpStatus;

/**
 * {@link ErrorCode}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/7
 */
public interface ErrorCode {
  /**
   * error code
   *
   * @return integer value
   */
  int code();

  /**
   * error key
   *
   * @return internation key
   */
  String errorKey();

  /**
   * error message
   *
   * @return string
   */
  String errorMessage();

  default HttpStatus httpStatus() {
    return HttpStatus.OK;
  }
}
