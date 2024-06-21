package com.yogjun.starter.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * {@link YogWebConfigProperties}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/21
 */
@Data
@ConfigurationProperties(prefix = "yog.spring.web")
public class YogWebConfigProperties {

  @NestedConfigurationProperty private ResponseConfig response = new ResponseConfig();
}
