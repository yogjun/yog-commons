package com.yogjun.enhance.pay.paypal.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * {@link PayPalApiUrl}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/27
 */
@ToString
@Getter
@AllArgsConstructor
public enum PayPalApiUrl {
  /** 沙箱环境 */
  SANDBOX_GATEWAY("https://api.sandbox.paypal.com"),
  /** 线上环境 */
  LIVE_GATEWAY("https://api.paypal.com"),
  /** 获取 Access Token */
  GET_TOKEN("/v1/oauth2/token"),
  /** 订单 */
  CHECKOUT_ORDERS("/v2/checkout/orders"),
  /** 确认订单 */
  CAPTURE_ORDER("/v2/checkout/orders/%s/capture"),
  /** 查询已确认订单 */
  CAPTURE_QUERY("/v2/payments/captures/%s"),
  /** 退款 */
  REFUND("/v2/payments/captures/%s/refund"),
  /** 退款查询 */
  REFUND_QUERY("/v2/payments/refunds/%s");

  /** 类型 */
  private final String url;
}
