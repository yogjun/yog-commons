package com.yogjun.starter.auth.service;

import com.yogjun.api.exception.YogException;
import com.yogjun.commont.kits.BeanUtil;
import com.yogjun.starter.auth.api.reqeust.LoginRequest;
import com.yogjun.starter.auth.api.reqeust.SignupRequest;
import com.yogjun.starter.auth.database.UserDTO;
import com.yogjun.starter.auth.database.UserDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@link UserManageService}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/13
 */
@Service
public class UserManageService {

  @Autowired private UserDao userDao;
  @Autowired private UserUtil userUtil;

  public UserDTO signup(SignupRequest request) {
    UserDTO userDTO = new UserDTO();
    BeanUtils.copyProperties(request, userDTO);
    userDao.save(userDTO);
    return login(BeanUtil.createAndCopy(userDTO, LoginRequest.class));
  }

  public UserDTO login(LoginRequest request) {
    UserDTO userDTO =
        userDao.getByUserNameAndPassword(request.getUsername(), request.getPassword());
    if (null == userDTO) {
      throw new YogException("账号不存在或密码错误");
    }
    String sesseionId = userUtil.generateSession(userDTO.getId());
    userDTO.setSessionId(sesseionId);
    return userDTO;
  }
}
