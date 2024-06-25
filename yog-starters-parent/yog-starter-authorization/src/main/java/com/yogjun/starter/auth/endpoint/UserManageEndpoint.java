package com.yogjun.starter.auth.endpoint;

import com.yogjun.commont.kits.BeanUtil;
import com.yogjun.starter.auth.api.reqeust.LoginRequest;
import com.yogjun.starter.auth.api.reqeust.SignupRequest;
import com.yogjun.starter.auth.api.response.LoginResponse;
import com.yogjun.starter.auth.database.UserDTO;
import com.yogjun.starter.auth.service.UserManageService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
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
@ConditionalOnWebApplication
public class UserManageEndpoint {
  private UserManageService userManageService;

  public UserManageEndpoint(UserManageService userManageService) {
    this.userManageService = userManageService;
  }

  @PostMapping("/signup")
  public LoginResponse signup(@RequestBody SignupRequest request) {
    UserDTO userDTO = userManageService.signup(request);
    return BeanUtil.createAndCopy(userDTO, LoginResponse.class);
  }

  @PostMapping("/login")
  public LoginResponse login(@RequestBody LoginRequest request) {
    UserDTO userDTO = userManageService.login(request);
    return BeanUtil.createAndCopy(userDTO, LoginResponse.class);
  }
}
