package com.yogjun.starter.auth.api.bean;

import java.time.LocalDateTime;
import lombok.*;

/**
 * {@link UserInfo} 用户信息
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class UserInfo {
  /** 唯一标识 */
  private Long id;

  /** 用户名 */
  private String username;

  /** 手机区号 */
  private String countryCode;

  /** 手机号 */
  private String phone;

  /** 邮箱 */
  private String email;

  private LocalDateTime createTime;
  private LocalDateTime updateTime;
}
