package com.yogjun.starter.auth.api.bean;

import java.io.Serializable;
import lombok.Data;

@Data
public class EmailVerifyBean implements Serializable {

  /** 邮箱 */
  private String email;

  /** 邮箱验证码 */
  private String emailCode;
}
