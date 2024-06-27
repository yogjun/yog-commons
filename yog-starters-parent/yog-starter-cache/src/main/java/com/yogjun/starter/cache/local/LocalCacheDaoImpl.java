package com.yogjun.starter.cache.local;

import com.yogjun.enhance.cache.core.YogCache;
import com.yogjun.enhance.cache.core.bean.CacheGetResult;
import com.yogjun.enhance.cache.core.bean.CacheResult;
import com.yogjun.starter.cache.YogCacheSourceType;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

/**
 * {@link LocalCacheDaoImpl}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/13
 */
@Repository(YogCacheSourceType.localCache)
public class LocalCacheDaoImpl<K, V> implements YogCache<K, V> {

  private final Map<K, LocalCache<K, V>> cache = new ConcurrentHashMap<>();

  @Override
  public CacheGetResult<V> GET(K key) {
    LocalCache LocalCache = getByKey(key);
    if (LocalCache == null) {
      return CacheGetResult.NOT_EXISTS_WITHOUT_MSG;
    }
    LocalDateTime now = LocalDateTime.now();
    if (LocalCache.getExpireTime() == null || now.isAfter(LocalCache.getExpireTime())) {
      REMOVE(key);
      return CacheGetResult.NOT_EXISTS_WITHOUT_MSG;
    }
    return new CacheGetResult<>(
        CacheGetResult.SUCCESS_WITHOUT_MSG.getResultCode(), (V) LocalCache.getValue());
  }

  @Override
  public CacheResult PUT(K key, V value, long expireAfterWrite, ChronoUnit timeUnit) {
    LocalCache LocalCache = new LocalCache<K, V>();
    LocalCache.setKey(key);
    LocalCache.setValue(value);
    LocalDateTime now = LocalDateTime.now();
    LocalCache.setCreateTime(now);
    LocalCache.setExpireTime(now.plus(expireAfterWrite, timeUnit));
    cache.put(key, LocalCache);
    return CacheResult.SUCCESS_WITHOUT_MSG;
  }

  private LocalCache getByKey(K key) {
    LocalCache LocalCache = cache.get(key);
    return LocalCache;
  }

  @Override
  public CacheResult REMOVE(K key) {
    cache.remove(key);
    return CacheResult.SUCCESS_WITHOUT_MSG;
  }
}
