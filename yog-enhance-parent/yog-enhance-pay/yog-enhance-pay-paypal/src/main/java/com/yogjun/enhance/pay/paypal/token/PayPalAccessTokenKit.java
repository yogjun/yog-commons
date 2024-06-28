package com.yogjun.enhance.pay.paypal.token;

import cn.hutool.core.util.StrUtil;
import com.yogjun.commont.kits.RetryUtils;
import com.yogjun.commont.kits.http.YogHttpResponse;
import com.yogjun.enhance.cache.core.defaultCache.DefaultCacheHolder;
import com.yogjun.enhance.cache.core.YogCache;
import com.yogjun.enhance.pay.paypal.PayPalApi;
import com.yogjun.enhance.pay.paypal.config.PayPalApiConfig;
import com.yogjun.enhance.pay.paypal.config.PayPalApiConfigKit;
import java.util.concurrent.Callable;

/**
 * {@link PayPalAccessTokenKit}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/27
 */
public class PayPalAccessTokenKit {

  private static final YogCache<String, String> cache = new DefaultCacheHolder<String, String>();

  /**
   * 获取当前线程中的 AccessToken
   *
   * @return {@link PayPalAccessToken}
   */
  public static PayPalAccessToken get() {
    return get(PayPalApiConfigKit.getApiConfig().getClientId(), false);
  }

  /**
   * 获取当前线程中的 AccessToken
   *
   * @param forceRefresh 是否强制刷新
   * @return {@link PayPalAccessToken}
   */
  public static PayPalAccessToken get(boolean forceRefresh) {
    return get(PayPalApiConfigKit.getApiConfig().getClientId(), forceRefresh);
  }

  /**
   * 通过 clientId 来获取 AccessToken
   *
   * @param clientId 应用编号
   * @return {@link PayPalAccessToken}
   */
  public static PayPalAccessToken get(String clientId) {
    return get(clientId, false);
  }

  /**
   * 通过 clientId 来获取 AccessToken
   *
   * @param clientId 应用编号
   * @param forceRefresh 是否强制刷新
   * @return {@link PayPalAccessToken}
   */
  public static PayPalAccessToken get(String clientId, boolean forceRefresh) {
    YogCache<String, String> accessTokenCache = cache;
    // 从缓存中获取 AccessToken
    if (!forceRefresh) {
      String json = accessTokenCache.get(clientId);
      if (StrUtil.isNotEmpty(json)) {
        PayPalAccessToken accessToken = new PayPalAccessToken(json, 200);
        if (accessToken.isAvailable()) {
          return accessToken;
        }
      }
    }

    PayPalApiConfig apiConfig = PayPalApiConfigKit.getApiConfig(clientId);

    PayPalAccessToken result =
        RetryUtils.retryOnException(
            3,
            new Callable<PayPalAccessToken>() {
              @Override
              public PayPalAccessToken call() {
                YogHttpResponse response = PayPalApi.getToken(apiConfig);
                return new PayPalAccessToken(response.getBody(), response.getStatus());
              }
            });

    // 三次请求如果仍然返回了不可用的 PayPalAccessToken 仍然 put 进去，便于上层通过 PayPalAccessToken 中的属性判断底层的情况
    if (null != result) {
      // 利用 clientId 与 PayPalAccessToken 建立关联，支持多账户
      cache.put(clientId, result.getCacheJson());
    }
    return result;
  }
}
