package com.yogjun.starter.auth.config;

import com.yogjun.starter.auth.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * {@link InterceptorConfigure}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/21
 */
@Configuration
public class InterceptorConfigure implements WebMvcConfigurer {

  private UserInterceptor userInterceptor;

  public InterceptorConfigure(UserInterceptor userInterceptor) {
    this.userInterceptor = userInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    if (userInterceptor != null) {
      registry.addInterceptor(userInterceptor).addPathPatterns("/**");
    }
  }
}
