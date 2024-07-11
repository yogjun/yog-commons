package com.yogjun.starter.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:tangtongda@gmail.com">Tino.Tang</a>
 * @version ${project.version} - 2021/1/15
 */
@ConfigurationProperties(prefix = "yog.mail")
@Configuration
@Data
public class MailConfig {

  /** send account */
  private String account;

  /** personal */
  private String name;
}
