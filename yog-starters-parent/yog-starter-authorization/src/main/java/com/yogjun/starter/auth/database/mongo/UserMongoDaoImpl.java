package com.yogjun.starter.auth.database.mongo;

import com.yogjun.api.commons.repository.BasePO;
import com.yogjun.commont.kits.BeanUtil;
import com.yogjun.starter.auth.database.UserDTO;
import com.yogjun.starter.auth.database.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * {@link UserMongoDaoImpl}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/12
 */
@Repository
public class UserMongoDaoImpl implements UserDao {

  @Autowired private MongoTemplate mongoTemplate;

  @Override
  public void save(UserDTO userDTO) {
    UserPO po = BeanUtil.createAndCopy(userDTO, UserPO.class);
    if (po.getId() == null) {
      po.preInsert();
    } else {
      po.preUpdate();
    }
    mongoTemplate.save(po);
  }

  @Override
  public UserDTO getByUserNameAndPassword(String username, String password) {
    Criteria criteria = Criteria.where(BasePO.Fields.deleted).ne(true);
    Query query = new Query(criteria);
    query.addCriteria(Criteria.where("username").is(username));
    query.addCriteria(Criteria.where("password").is(password));
    UserPO po = mongoTemplate.findOne(query, UserPO.class);
    return BeanUtil.createAndCopy(po, UserDTO.class);
  }
}
