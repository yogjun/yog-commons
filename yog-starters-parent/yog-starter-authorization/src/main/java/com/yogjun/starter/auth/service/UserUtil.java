package com.yogjun.starter.auth.service;

import cn.hutool.core.util.IdUtil;
import com.yogjun.starter.auth.cache.CacheDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * {@link UserUtil}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/13
 */
@Component
public class UserUtil {

  public static final String CACHE_PREFIX = "yogtoken:";

  @Autowired private CacheDao cacheDao;

  public String generateSession(Long userId) {
    String sessionId = IdUtil.fastSimpleUUID();
    cacheDao.put(CACHE_PREFIX + "_" + sessionId, userId);
    return sessionId;
  }
}
