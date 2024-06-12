package com.yogjun.starter.auth.config;

import com.yogjun.starter.auth.api.enums.DBType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * {@link AuthConfiguration}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/11
 */
@Data
@ConfigurationProperties(prefix = "yogjun.starter.auth")
public class AuthConfiguration {

  /** 存储数据库类型 */
  private String dbType = DBType.MONGODB.name();

  /** 数据库表名 */
  private String userTable = "user_info";
}
