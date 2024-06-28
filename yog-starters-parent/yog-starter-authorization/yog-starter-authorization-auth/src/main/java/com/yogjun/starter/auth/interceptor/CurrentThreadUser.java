package com.yogjun.starter.auth.interceptor;

import com.yogjun.starter.auth.api.bean.UserInfo;
import java.util.Objects;

/**
 * {@link CurrentThreadUser}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/12
 */
public class CurrentThreadUser {
  private CurrentThreadUser() {}

  /** current user */
  public static final ThreadLocal<UserInfo> CURRENT_USER = new ThreadLocal<>();

  public static UserInfo get() {
    return CURRENT_USER.get();
  }

  /** clear cache */
  public static void unload() {
    CURRENT_USER.remove();
  }

  public static void set(UserInfo bean) {
    if (Objects.nonNull(bean)) {
      CURRENT_USER.set(bean);
    }
  }
}
