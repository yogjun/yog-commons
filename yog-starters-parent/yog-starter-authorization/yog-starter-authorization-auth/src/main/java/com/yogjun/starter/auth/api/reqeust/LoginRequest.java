package com.yogjun.starter.auth.api.reqeust;

import lombok.Data;

/**
 * {@link LoginRequest}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/12
 */
@Data
public class LoginRequest {
  private String username;
  private String password;
}
