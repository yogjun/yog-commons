package com.yogjun.starter.auth.cache.mongo;

import com.yogjun.starter.auth.cache.CacheDao;
import java.time.LocalDateTime;
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
public class MongoCacheDaoImpl implements CacheDao {

  @Autowired private MongoTemplate mongoTemplate;

  @Override
  public <V> void put(String key, V value) {
    MongoCache<V> mongoCache = new MongoCache<V>();
    mongoCache.setId(key);
    mongoCache.setValue(value);
    mongoCache.setCreateTime(LocalDateTime.now());
    mongoTemplate.save(mongoCache);
  }

  @Override
  public <V> V get(String key) {

    Criteria criteria = new Criteria();
    criteria.and("id").is(key);
    Query query = new Query(criteria);
    MongoCache mongoCache = mongoTemplate.findOne(query, MongoCache.class);
    if (mongoCache == null) {
      return null;
    }
    return (V) mongoCache.getValue();
  }
}
