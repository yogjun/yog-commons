package com.yogjun.starter.cache.mongo;

import cn.hutool.core.util.IdUtil;
import com.yogjun.enhance.cache.core.YogCache;
import com.yogjun.enhance.cache.core.bean.CacheGetResult;
import com.yogjun.enhance.cache.core.bean.CacheResult;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * {@link MongoCacheDaoImpl}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/13
 */
@Repository
public class MongoCacheDaoImpl<K, V> implements YogCache<K, V> {

  @Autowired private MongoTemplate mongoTemplate;

  @Override
  public CacheGetResult<V> GET(K key) {
    Criteria criteria = new Criteria();
    criteria.and("key").is(key);
    Query query = new Query(criteria);
    MongoCache mongoCache = mongoTemplate.findOne(query, MongoCache.class);
    if (mongoCache == null) {
      return CacheGetResult.NOT_EXISTS_WITHOUT_MSG;
    }
    LocalDateTime now = LocalDateTime.now();
    if (now.isAfter(mongoCache.getExpireTime())) {
      REMOVE(key);
      return CacheGetResult.NOT_EXISTS_WITHOUT_MSG;
    }
    return new CacheGetResult<>(
        CacheGetResult.SUCCESS_WITHOUT_MSG.getResultCode(), (V) mongoCache.getValue());
  }

  @Override
  public CacheResult PUT(K key, V value, long expireAfterWrite, TimeUnit timeUnit) {
    MongoCache<K, V> mongoCache = new MongoCache<K, V>();
    mongoCache.setId(IdUtil.getSnowflakeNextIdStr());
    mongoCache.setKey(key);
    mongoCache.setValue(value);
    LocalDateTime now = LocalDateTime.now();
    mongoCache.setCreateTime(now);
    mongoCache.setExpireTime(now.plus(expireAfterWrite, toChronoUnit(timeUnit)));
    mongoTemplate.save(mongoCache);
    return CacheResult.SUCCESS_WITHOUT_MSG;
  }

  @Override
  public CacheResult REMOVE(K key) {
    Criteria criteria = Criteria.where("key").is(key);
    mongoTemplate.findAndRemove(Query.query(criteria), MongoCache.class);
    return CacheResult.SUCCESS_WITHOUT_MSG;
  }

  private ChronoUnit toChronoUnit(TimeUnit timeUnit) {
    switch (timeUnit) {
      case NANOSECONDS:
        return ChronoUnit.NANOS;
      case MICROSECONDS:
        return ChronoUnit.MICROS;
      case MILLISECONDS:
        return ChronoUnit.MILLIS;
      case SECONDS:
        return ChronoUnit.SECONDS;
      case MINUTES:
        return ChronoUnit.MINUTES;
      case HOURS:
        return ChronoUnit.HOURS;
      case DAYS:
        return ChronoUnit.DAYS;
      default:
        throw new AssertionError();
    }
  }
}
