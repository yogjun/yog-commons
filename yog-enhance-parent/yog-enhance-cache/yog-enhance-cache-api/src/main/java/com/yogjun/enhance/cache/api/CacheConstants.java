package com.yogjun.enhance.cache.api;

import java.time.Duration;

/**
 * {@link CacheConstants}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/27
 */
public interface CacheConstants {
  // 默认超时时间
  Duration ASYNC_RESULT_TIMEOUT = Duration.ofMillis(1000);
}
