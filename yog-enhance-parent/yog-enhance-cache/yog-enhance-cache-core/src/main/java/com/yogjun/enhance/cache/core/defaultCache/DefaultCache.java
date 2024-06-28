package com.yogjun.enhance.cache.core.defaultCache;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

/**
 * {@link DefaultCache}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/13
 */
@Getter
@Setter
@ToString
@FieldNameConstants
public class DefaultCache<K, V> {
  private K key;
  private V value;
  private LocalDateTime createTime;
  private LocalDateTime expireTime;
}
