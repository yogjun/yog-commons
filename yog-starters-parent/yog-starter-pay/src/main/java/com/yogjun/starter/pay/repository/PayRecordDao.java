package com.yogjun.starter.pay.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 * {@link PayRecordDao}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/7/11
 */
@Repository
public class PayRecordDao {
  @Autowired private MongoTemplate mongoTemplate;

  public void save(PayRecord payRecord) {
    mongoTemplate.save(payRecord);
  }
}
