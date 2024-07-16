package com.yogjun.starter.pay.endpoint;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@link PayPalEndpoint}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/7/11
 */
@RequestMapping("/yog/pay/paypal")
@RestController
@ConditionalOnWebApplication
public class PayPalEndpoint {
  @RequestMapping("/test")
  public String test() {
    return "Hello, PayPal!";
  }
}
