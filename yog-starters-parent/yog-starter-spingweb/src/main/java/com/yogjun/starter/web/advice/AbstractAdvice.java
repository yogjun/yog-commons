package com.yogjun.starter.web.advice;

import cn.hutool.core.util.StrUtil;
import com.yogjun.starter.web.config.YogWebConfigProperties;
import java.util.List;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;

/**
 * {@link AbstractAdvice}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/21
 */
public class AbstractAdvice implements BeanFactoryAware, EnvironmentAware {

  protected BeanFactory beanFactory;

  protected Environment environment;

  protected YogWebConfigProperties configProperties;

  private String contextPath;

  private PathMatcher pathMatcher = new AntPathMatcher();

  protected YogWebConfigProperties getProperties() {
    if (configProperties == null) {
      try {
        this.configProperties = this.beanFactory.getBean(YogWebConfigProperties.class);
      } catch (Exception ignore) {
      }
    }
    return this.configProperties;
  }

  @Override
  public void setBeanFactory(@NonNull BeanFactory beanFactory) throws BeansException {
    this.beanFactory = beanFactory;
  }

  @Override
  public void setEnvironment(@NonNull Environment environment) {
    this.environment = environment;
  }

  private String cleanContextPath(String contextPath) {
    String candidate = StringUtils.trimWhitespace(contextPath);
    if (StringUtils.hasText(candidate) && candidate.endsWith("/")) {
      return candidate.substring(0, candidate.length() - 1);
    }
    return candidate;
  }

  protected boolean checkIgnoreURL(@NonNull String path) {

    if (StrUtil.isNotBlank(path)) {
      if (this.contextPath == null) {
        this.contextPath = environment.getProperty("server.servlet.context-path", "");
        this.contextPath = cleanContextPath(this.contextPath);
      }
      List<String> urls = getProperties().getResponse().getIgnoreWrapUris();
      for (String url : urls) {
        if (StrUtil.startWith(this.contextPath.concat(path), url)
            || pathMatcher.match(url, this.contextPath.concat(path))) {
          return true;
        }
      }
    }

    return false;
  }
}
