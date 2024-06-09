package com.yogjun.enhance.email.service;

import com.yogjun.enhance.email.model.YogEmail;

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
