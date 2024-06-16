package com.yogjun.starter.auth.service.impl;

import com.yogjun.commont.kits.BeanUtil;
import com.yogjun.starter.auth.api.bean.UserInfo;
import com.yogjun.starter.auth.cache.CacheDao;
import com.yogjun.starter.auth.database.UserDTO;
import com.yogjun.starter.auth.database.UserDao;
import com.yogjun.starter.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@link DefaultUserServiceImpl}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/13
 */
@Service
public class DefaultUserServiceImpl implements UserService {

  @Autowired private CacheDao cacheDao;
  @Autowired private UserDao userDao;

  @Override
  public UserInfo getUserInfoBySessionId(String sessionId) {
    Long userId = cacheDao.get(sessionId);
    if (userId == null) {
      return null;
    }
    UserDTO request = new UserDTO();
    request.setId(userId);
    UserDTO result = userDao.getOne(request);
    if (result == null) {
      return null;
    }
    return BeanUtil.createAndCopy(result, UserDTO.class);
  }
}
