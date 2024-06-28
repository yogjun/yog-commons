package com.yogjun.starter.cache.mongo;

import cn.hutool.core.util.IdUtil;
import com.yogjun.enhance.cache.core.YogCache;
import com.yogjun.enhance.cache.core.bean.CacheGetResult;
import com.yogjun.enhance.cache.core.bean.CacheResult;
import com.yogjun.enhance.cache.api.YogCacheSourceType;
import com.yogjun.starter.cache.mongo.po.MongoCache;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
@Repository(YogCacheSourceType.mongoCache)
public class MongoCacheDaoImpl<K, V> implements YogCache<K, V> {

  @Autowired private MongoTemplate mongoTemplate;

  @Override
  public CacheGetResult<V> GET(K key) {
    MongoCache mongoCache = getByKey(key);
    if (mongoCache == null) {
      return CacheGetResult.NOT_EXISTS_WITHOUT_MSG;
    }
    LocalDateTime now = LocalDateTime.now();
    if (mongoCache.getExpireTime() == null || now.isAfter(mongoCache.getExpireTime())) {
      REMOVE(key);
      return CacheGetResult.NOT_EXISTS_WITHOUT_MSG;
    }
    return new CacheGetResult<>(
        CacheGetResult.SUCCESS_WITHOUT_MSG.getResultCode(), (V) mongoCache.getValue());
  }

  @Override
  public CacheResult PUT(K key, V value, long expireAfterWrite, ChronoUnit timeUnit) {
    MongoCache mongoCache = getByKey(key);
    if (mongoCache == null) {
      mongoCache = new MongoCache<K, V>();
      mongoCache.setId(IdUtil.getSnowflakeNextIdStr());
    }
    mongoCache.setKey(key);
    mongoCache.setValue(value);
    LocalDateTime now = LocalDateTime.now();
    mongoCache.setCreateTime(now);
    mongoCache.setExpireTime(now.plus(expireAfterWrite, timeUnit));
    mongoTemplate.save(mongoCache);
    return CacheResult.SUCCESS_WITHOUT_MSG;
  }

  private MongoCache getByKey(K key) {
    Criteria criteria = new Criteria();
    criteria.and("key").is(key);
    Query query = new Query(criteria);
    MongoCache mongoCache = mongoTemplate.findOne(query, MongoCache.class);
    return mongoCache;
  }

  @Override
  public CacheResult REMOVE(K key) {
    Criteria criteria = Criteria.where("key").is(key);
    mongoTemplate.findAndRemove(Query.query(criteria), MongoCache.class);
    return CacheResult.SUCCESS_WITHOUT_MSG;
  }
}
