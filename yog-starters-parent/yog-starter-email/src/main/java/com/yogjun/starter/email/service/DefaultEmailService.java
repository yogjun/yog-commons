package com.yogjun.starter.email.service;

import cn.hutool.core.util.StrUtil;
import com.yogjun.starter.email.model.YogEmail;
import java.util.Date;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * {@link DefaultEmailService}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/9
 */
@Service
public class DefaultEmailService implements EmailService {

  @Autowired private JavaMailSender javaMailSender;

  @Override
  public MimeMessage send(YogEmail email) throws MessagingException {
    email.setSentAt(new Date());
    final MimeMessage mimeMessage = toMimeMessage(email);
    switch (email.getContentType()) {
      case HTML:
        final MimeMultipart content = new MimeMultipart("mixed");
        // Set the HTML text part
        final MimeBodyPart textPart = new MimeBodyPart();
        textPart.setText(email.getBody(), email.getEncoding(), "html");
        content.addBodyPart(textPart);
        mimeMessage.setContent(content);
        mimeMessage.saveChanges();
        break;
      case TEXT_PLAIN:
        break;
    }
    javaMailSender.send(mimeMessage);
    return mimeMessage;
  }

  private MimeMessage toMimeMessage(YogEmail email) throws MessagingException {
    final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper messageHelper =
        new MimeMessageHelper(mimeMessage, false, email.getEncoding());
    messageHelper.setFrom(email.getFrom());
    for (final InternetAddress address : email.getTo()) {
      messageHelper.addTo(address);
    }
    messageHelper.setSubject(StrUtil.emptyIfNull(email.getSubject()));
    messageHelper.setText(StrUtil.emptyIfNull(email.getBody()));
    messageHelper.setSentDate(email.getSentAt());
    mimeMessage.saveChanges();
    return mimeMessage;
  }
}
