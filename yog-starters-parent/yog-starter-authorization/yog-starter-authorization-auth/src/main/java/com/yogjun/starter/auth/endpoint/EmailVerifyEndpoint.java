package com.yogjun.starter.auth.endpoint;

import cn.hutool.core.lang.RegexPool;
import cn.hutool.core.util.ReUtil;
import com.yogjun.api.exception.YogException;
import com.yogjun.starter.auth.api.reqeust.email.SendEmailRequest;
import com.yogjun.starter.auth.api.reqeust.email.VerifyEmailRequest;
import com.yogjun.starter.auth.service.email.EmailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@link EmailVerifyEndpoint}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/7/8
 */
@RequestMapping("/yog/email")
@RestController
@ConditionalOnWebApplication
public class EmailVerifyEndpoint {

  @Autowired private EmailVerificationService emailVerificationService;

  /** 发送邮箱验证码 */
  @PostMapping({"/sendSmsVerify"})
  public String sendSmsVerify(@RequestBody SendEmailRequest request) {
    if (!ReUtil.isMatch(RegexPool.EMAIL, request.getEmail())) {
      throw new YogException("邮箱格式不正确");
    }
    return emailVerificationService.sendEmailRegisterVerification(request.getEmail());
  }

  /** 校验邮箱验证码 */
  @PostMapping({"/verifySmsEmailVerify"})
  public void verifySmsVerify(@RequestBody VerifyEmailRequest request) {
    emailVerificationService.verifyCaptcha(request);
  }
}
