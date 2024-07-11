package com.yogjun.starter.auth.service.verification;

import cn.hutool.core.lang.Pair;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.yogjun.enhance.cache.api.YogCacheSourceType;
import com.yogjun.enhance.cache.core.YogCache;
import com.yogjun.starter.auth.api.bean.EmailVerifyBean;
import java.security.SecureRandom;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * {@link VerificationService}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/7/10
 */
@Service
public class VerificationService {

  private static final Random RANDOM = new SecureRandom();
  private static final String SYMBOLS = "0123456789"; // 纯数字

  @Autowired
  @Qualifier(YogCacheSourceType.mongoCache)
  private YogCache<String, EmailVerifyBean> emailVerifyCacheDao;

  private static final String USER_EMAIL_VERIFY_SECRET_PREFIX = "USER_EMAIL_VERIFY_SECRET:";

  /** 生成6位验证码 返回secret */
  public Pair<String, String> generateVerification(String email) {
    EmailVerifyBean emailVerifyBean = new EmailVerifyBean();
    String verifyCode = getRandomNum(6);
    String secret = IdUtil.fastSimpleUUID();
    String key = USER_EMAIL_VERIFY_SECRET_PREFIX + secret;
    emailVerifyBean.setEmailCode(verifyCode);
    emailVerifyBean.setEmail(email);
    emailVerifyCacheDao.put(key, emailVerifyBean, 300, ChronoUnit.SECONDS);
    return Pair.of(verifyCode, secret);
  }

  /** 校验验证码是否正确 */
  public Boolean verifyCode(String secret, String email, String verifyCode) {
    String key = USER_EMAIL_VERIFY_SECRET_PREFIX + secret;
    EmailVerifyBean res = emailVerifyCacheDao.get(key);
    return res != null
        && StrUtil.equals(email, res.getEmail())
        && StrUtil.equals(verifyCode, res.getEmailCode());
  }

  /**
   * 获取长度为 6 的随机数字
   *
   * @return 随机数字
   */
  private static String getRandomNum(int length) {
    // 指定长度
    char[] nonceChars = new char[length];
    for (int index = 0; index < nonceChars.length; ++index) {
      nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
    }
    return new String(nonceChars);
  }
}
