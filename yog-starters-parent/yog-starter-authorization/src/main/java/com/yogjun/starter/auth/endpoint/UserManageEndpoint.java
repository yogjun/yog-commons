package com.yogjun.starter.auth.endpoint;

import com.yogjun.starter.auth.api.reqeust.LoginRequest;
import com.yogjun.starter.auth.api.reqeust.SignupRequest;
import com.yogjun.starter.auth.api.response.LoginResponse;
import com.yogjun.starter.auth.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@link UserManageEndpoint}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/13
 */
@RequestMapping("/user")
@RestController
public class UserManageEndpoint {

  @Autowired private UserManageService userManageService;

  @PostMapping("/signup")
  public LoginResponse signup(@RequestBody SignupRequest request) {
    // todo 防重复提交
    userManageService.signup(request);
    return null;
  }

  @PostMapping("/login")
  public LoginResponse login(@RequestBody LoginRequest request) {
    userManageService.login(request);
    return null;
  }
}
