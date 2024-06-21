package com.yogjun.starter.web;

import com.yogjun.starter.web.config.YogWebConfigProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * {@link YogWebAutoConfiguration}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/21
 */
@Configuration
@EnableConfigurationProperties(YogWebConfigProperties.class)
public class YogWebAutoConfiguration {

}
