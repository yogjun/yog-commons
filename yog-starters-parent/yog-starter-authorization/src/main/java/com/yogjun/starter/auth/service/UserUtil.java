package com.yogjun.starter.auth.service;

import cn.hutool.core.util.IdUtil;
import com.yogjun.enhance.cache.core.YogCache;
import com.yogjun.starter.cache.YogCacheSourceType;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

  //  @Autowired private CacheDao cacheDao;
  @Autowired
  @Qualifier(YogCacheSourceType.mongoCache)
  private YogCache<String, Long> cacheDao;

  public String generateSession(Long userId) {
    String sessionId = IdUtil.fastSimpleUUID();
    cacheDao.put(CACHE_PREFIX + "_" + sessionId, userId, 3, ChronoUnit.DAYS);
    return sessionId;
  }
}
