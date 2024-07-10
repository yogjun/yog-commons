package com.yogjun.starter.auth.api.reqeust.email;

import lombok.Data;

@Data
public class VerifyEmailRequest {
  private String verifyCode;
  private String secret;
  private String email;
}
