package com.yogjun.starter.web.advice;

import com.yogjun.api.commons.bean.bean.YogResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface YogExceptionHandler {

  default ResponseEntity<?> handlerException(Exception exception) {

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(YogResponseEntity.fail(-1, exception.getMessage()));
  }
}
