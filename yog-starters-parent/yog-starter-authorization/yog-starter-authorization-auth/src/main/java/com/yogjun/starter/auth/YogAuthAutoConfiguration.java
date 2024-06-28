package com.yogjun.starter.auth;

import com.yogjun.starter.auth.config.AuthConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * {@link YogAuthAutoConfiguration}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/11
 */
@Configuration
@ComponentScan("com.yogjun.starter.auth")
@EnableConfigurationProperties(AuthConfiguration.class)
public class YogAuthAutoConfiguration {

  //  @ConditionalOnMissingBean(UserService.class)
  //  @Bean
  //  public UserService serviceUser() {
  //    return new DefaultUserServiceImpl();
  //  }
  //
  //  @ConditionalOnBean(UserService.class)
  //  @Bean
  //  public UserInterceptor userInterceptor(UserService userService) {
  //    return new UserInterceptor(userService);
  //  }
}
