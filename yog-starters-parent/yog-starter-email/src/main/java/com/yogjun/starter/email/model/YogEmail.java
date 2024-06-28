package com.yogjun.starter.email.model;

import lombok.Data;

import javax.mail.internet.InternetAddress;
import java.util.Date;
import java.util.List;

/**
 * {@link YogEmail}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/9
 */
@Data
public class YogEmail {
    private InternetAddress from;
    private List<InternetAddress> to;
    private String subject;
    private ContentType contentType;
    private String body;
    private String encoding;
    private Date sentAt;
}
