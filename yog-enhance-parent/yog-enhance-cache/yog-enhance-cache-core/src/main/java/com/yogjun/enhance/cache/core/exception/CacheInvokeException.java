package com.yogjun.enhance.cache.core.exception;

public class CacheInvokeException extends CacheException {

  public CacheInvokeException(String message, Throwable cause) {
    super(message, cause);
  }

  public CacheInvokeException(Throwable cause) {
    super(cause);
  }
}
