package com.yogjun.starter.auth.database;

/**
 * {@link UserDao}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/11
 */
public interface UserDao {

  /** 保存用户信息 */
  void save(UserDTO userDTO);

  /** 根据用户名密码查询 */
  UserDTO getByUserNameAndPassword(String username, String password);
}
