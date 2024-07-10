package com.yogjun.starter.auth.service.user;

import com.yogjun.starter.auth.api.bean.UserInfo;

/**
 * {@link UserService}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/12
 */
public interface UserService {
  UserInfo getUserInfoBySessionId(String sessionId);
}
