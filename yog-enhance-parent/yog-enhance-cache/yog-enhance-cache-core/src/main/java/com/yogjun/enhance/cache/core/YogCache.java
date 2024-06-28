package com.yogjun.enhance.cache.core;

import com.yogjun.enhance.cache.core.bean.CacheGetResult;
import com.yogjun.enhance.cache.core.bean.CacheResult;
import com.yogjun.enhance.cache.core.exception.CacheInvokeException;
import java.time.temporal.ChronoUnit;

/**
 * {@link YogCache}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/27
 */
public interface YogCache<K, V> {

  default void put(K key, V value) {
    PUT(key, value, 1, ChronoUnit.DAYS);
  }

  default void put(K key, V value, long expireAfterWrite, ChronoUnit timeUnit) {
    PUT(key, value, expireAfterWrite, timeUnit);
  }

  default V get(K key) throws CacheInvokeException {
    CacheGetResult<V> result = GET(key);
    if (result.isSuccess()) {
      return result.getValue();
    } else {
      return null;
    }
  }

  default boolean remove(K key) {
    return REMOVE(key).isSuccess();
  }

  CacheGetResult<V> GET(K key);

  CacheResult PUT(K key, V value, long expireAfterWrite, ChronoUnit timeUnit);

  CacheResult REMOVE(K key);
}
