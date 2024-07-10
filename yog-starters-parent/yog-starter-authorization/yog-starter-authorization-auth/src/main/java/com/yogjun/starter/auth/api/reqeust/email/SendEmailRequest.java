package com.yogjun.starter.auth.api.reqeust.email;

import lombok.Data;

/**
 * {@link SendEmailRequest}
 *
 * <p>发送邮件验证码请求
 */
@Data
public class SendEmailRequest {

  private String email;
}
