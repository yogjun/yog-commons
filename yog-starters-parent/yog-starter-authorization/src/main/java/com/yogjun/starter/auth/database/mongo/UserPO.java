package com.yogjun.starter.auth.database.mongo;

import com.yogjun.api.commons.repository.BasePO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * {@link UserPO}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/11
 */
@Getter
@Setter
@ToString
@FieldNameConstants
@Document(UserPO.TABLE_NAME)
public class UserPO extends BasePO {
  public static final String TABLE_NAME = "user_info";

  private String username;
  private String password;
  private String countryCode = "86";
  private String phone;
  private String email;
}
