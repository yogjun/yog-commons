package com.yogjun.starter.auth.api.response;

import com.yogjun.starter.auth.database.UserDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * {@link LoginResponse}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LoginResponse extends UserDTO {}
