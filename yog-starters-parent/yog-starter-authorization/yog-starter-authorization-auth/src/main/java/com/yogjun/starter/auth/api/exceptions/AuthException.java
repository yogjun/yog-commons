package com.yogjun.starter.auth.api.exceptions;

import com.yogjun.api.exception.YogException;
import com.yogjun.api.exception.common.ErrorCode;

/**
 * {@link AuthException}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/12
 */
public class AuthException extends YogException {
  public AuthException(String message) {
    super(message);
  }

  public AuthException(ErrorCode errorCode) {
    super(errorCode.errorMessage());
  }

  public enum Code implements ErrorCode {
    NOT_LOGIN(1001, "用户未登录"),
    ;

    private Integer code;
    private String message;

    Code(Integer code, String message) {
      this.code = code;
      this.message = message;
    }

    @Override
    public int code() {
      return code;
    }

    @Override
    public String errorKey() {
      return "";
    }

    @Override
    public String errorMessage() {
      return message;
    }
  }
}
