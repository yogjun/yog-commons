package com.yogjun.enhance.pay.paypal.config;

import java.io.Serializable;
import lombok.Data;

@Data
public class PayPalApiConfig implements Serializable {

  private static final long serialVersionUID = -6012811778236113584L;

  /** 应用编号 */
  private String clientId;

  /** 应用密钥 */
  private String secret;

  /** 是否是沙箱环境 */
  private boolean sandBox;

  /** 域名 */
  private String domain;
}
