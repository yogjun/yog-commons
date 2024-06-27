package com.yogjun.starter.cache.local;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

/**
 * {@link LocalCache}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/13
 */
@Getter
@Setter
@ToString
@FieldNameConstants
public class LocalCache<K, V> {
  private K key;
  private V value;
  private LocalDateTime createTime;
  private LocalDateTime expireTime;
}
