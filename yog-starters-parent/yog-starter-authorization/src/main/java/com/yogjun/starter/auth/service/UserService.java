package com.yogjun.starter.auth.service;

import com.yogjun.starter.auth.database.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@link UserService}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/12
 */
@Service
public class UserService {
  @Autowired private UserDao userDao;
}
