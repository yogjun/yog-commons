package com.yogjun.starter.auth.database;

import com.yogjun.starter.auth.api.bean.UserInfo;
import lombok.*;

/**
 * {@link UserDTO}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserDTO extends UserInfo {
  private String sessionId;
  private String password;
}
