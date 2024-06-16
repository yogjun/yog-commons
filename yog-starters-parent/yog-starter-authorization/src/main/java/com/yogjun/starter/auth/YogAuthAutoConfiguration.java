package com.yogjun.starter.auth;

import com.yogjun.starter.auth.config.AuthConfiguration;
import com.yogjun.starter.auth.interceptor.UserInterceptor;
import com.yogjun.starter.auth.service.UserService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * {@link YogAuthAutoConfiguration}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/11
 */
@ComponentScan("com.yogjun.starter.auth")
@EnableConfigurationProperties(AuthConfiguration.class)
public class YogAuthAutoConfiguration {

  @Bean
  public UserInterceptor ldapUserInterceptor(UserService userService) {
    return new UserInterceptor(userService);
  }
}
