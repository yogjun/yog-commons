package com.yogjun.starter.auth.cache;

/**
 * {@link CacheDao}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/13
 */
public interface CacheDao {

  <V> void put(String key, V value);

  <V> V get(String key);
}
