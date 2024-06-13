package com.yogjun.starter.auth.interceptor;

import cn.hutool.core.util.StrUtil;
import com.yogjun.commont.kits.utils.StringUtils;
import com.yogjun.starter.auth.api.bean.UserInfo;
import com.yogjun.starter.auth.api.constants.Constants;
import com.yogjun.starter.auth.api.exceptions.AuthException;
import com.yogjun.starter.auth.service.UserService;
import java.util.Objects;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * {@link UserInterceptor}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/12
 */
@Slf4j
public class UserInterceptor implements HandlerInterceptor {

  public static final String SESSION_ID = Constants.AUTH_TOKEN_KEY;

  private UserService userService;

  public UserInterceptor(UserService userService) {
    this.userService = userService;
  }

  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {
    String sessionId = this.getSessionId(request);
    loginHandle(sessionId);
    return true;
  }

  private void loginHandle(String sessionId) {
    UserInfo userInfo = CurrentThreadUser.get();
    if (StrUtil.isNotBlank(sessionId)) {
      userInfo = userService.getUserInfoBySessionId(sessionId);
    }
    if (Objects.isNull(userInfo)) {
      // not login
      throw new AuthException(AuthException.Code.NOT_LOGIN);
    }
    CurrentThreadUser.set(userInfo);
  }

  public String getSessionId(HttpServletRequest request) {
    String value = request.getHeader(SESSION_ID);
    if (!StringUtils.isBlank(value)) {
      return value;
    }

    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (Objects.equals(SESSION_ID, cookie.getName())) {
          return cookie.getValue();
        }
      }
    }

    return null;
  }
}
