package com.yogjun.starter.email.service;

import com.yogjun.starter.email.model.YogEmail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * {@link EmailService}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/9
 */
public interface EmailService {
    MimeMessage send(YogEmail mimeEmail) throws MessagingException;
}
