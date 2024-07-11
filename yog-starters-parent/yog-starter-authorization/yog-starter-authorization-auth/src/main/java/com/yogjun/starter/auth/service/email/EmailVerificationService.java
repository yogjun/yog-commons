package com.yogjun.starter.auth.service.email;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import com.yogjun.api.exception.YogException;
import com.yogjun.starter.auth.config.MailConfig;
import com.yogjun.starter.auth.service.verification.VerificationService;
import com.yogjun.starter.email.model.ContentType;
import com.yogjun.starter.email.model.YogEmail;
import com.yogjun.starter.email.service.EmailService;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@link EmailVerificationService}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/7/8
 */
@Slf4j
@Service
public class EmailVerificationService {

  @Autowired private EmailService emailService;
  @Autowired private VerificationService verificationService;
  @Autowired private MailConfig mailConfig;

  public Boolean verifyCaptcha(String email, String verifyCode, String secret) {
    return verificationService.verifyCode(secret, email, verifyCode);
  }

  /** 发送邮箱注册验证码 */
  public String sendEmailRegisterVerification(String to) {
    Pair<String, String> codeSecret = verificationService.generateVerification(to);
    try {
      boolean sendEmail =
          sendEmail(
              "邮箱注册验证码", "<p>您的验证码是：" + codeSecret.getKey() + "</p><br/><p>请在5分钟内完成验证</p>", to);
    } catch (Exception e) {
      log.error("发送邮箱验证码失败", e);
      throw new YogException("发送邮箱验证码失败");
    }
    return codeSecret.getValue();
  }

  private boolean sendEmail(String subject, String content, String email)
      throws UnsupportedEncodingException, MessagingException {
    YogEmail yogEmail = new YogEmail();
    yogEmail.setFrom(new InternetAddress(mailConfig.getAccount(), mailConfig.getName()));
    yogEmail.setTo(CollUtil.newArrayList(new InternetAddress(email)));
    yogEmail.setSubject(subject);
    yogEmail.setBody(HtmlUtil.unescape(content));
    yogEmail.setEncoding(StandardCharsets.UTF_8.name());
    yogEmail.setContentType(ContentType.HTML);
    MimeMessage mimeMessage = emailService.send(yogEmail);
    return null != mimeMessage && StrUtil.isNotBlank(mimeMessage.getMessageID());
  }
}
