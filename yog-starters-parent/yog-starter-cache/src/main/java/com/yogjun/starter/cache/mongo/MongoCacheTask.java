package com.yogjun.starter.cache.mongo;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import com.yogjun.starter.cache.mongo.po.MongoCache;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * {@link MongoCacheTask} 每小时清理过期数据
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/7/11
 */
@Slf4j
@Component
public class MongoCacheTask {

  @Autowired private MongoTemplate mongoTemplate;

  @Scheduled(cron = "0 0 * * * *") // 每小时
  public void execute() {
    TimeInterval timer = DateUtil.timer();
    log.info("[清理过期数据] 任务开始");
    mongoTemplate.remove(
        Query.query(Criteria.where("expireTime").lt(LocalDateTime.now())), MongoCache.class);
    log.info("[清理过期数据] 任务结束: time:{}", timer.interval());
  }
}
