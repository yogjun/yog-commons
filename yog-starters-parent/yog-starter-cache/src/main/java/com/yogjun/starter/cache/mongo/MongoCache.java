package com.yogjun.starter.cache.mongo;

import java.time.LocalDateTime;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * {@link MongoCache}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/13
 */
@Getter
@Setter
@ToString
@FieldNameConstants
@Document("yog_cache_mongo")
public class MongoCache<K, V> {
  @Id private String id;
  private K key;
  private V value;
  private LocalDateTime createTime;
  private LocalDateTime expireTime;
}
