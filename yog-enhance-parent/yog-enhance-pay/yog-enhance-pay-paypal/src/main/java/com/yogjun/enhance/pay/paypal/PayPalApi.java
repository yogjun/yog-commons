package com.yogjun.enhance.pay.paypal;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import com.yogjun.commont.kits.http.HttpKit;
import com.yogjun.commont.kits.http.YogHttpResponse;
import com.yogjun.enhance.pay.paypal.config.PayPalApiConfig;
import com.yogjun.enhance.pay.paypal.enums.PayPalApiUrl;
import com.yogjun.enhance.pay.paypal.token.PayPalAccessToken;
import com.yogjun.enhance.pay.paypal.token.PayPalAccessTokenKit;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class PayPalApi {
  /**
   * 获取接口请求的 URL
   *
   * @param payPalApiUrl {@link PayPalApiUrl} 支付 API 接口枚举
   * @param isSandBox 是否是沙箱环境
   * @return {@link String} 返回完整的接口请求URL
   */
  public static String getReqUrl(PayPalApiUrl payPalApiUrl, boolean isSandBox) {
    return (isSandBox ? PayPalApiUrl.SANDBOX_GATEWAY.getUrl() : PayPalApiUrl.LIVE_GATEWAY.getUrl())
        .concat(payPalApiUrl.getUrl());
  }

  /**
   * 获取 AccessToken
   *
   * @param config {@link PayPalApiConfig} 支付配置
   */
  public static YogHttpResponse getToken(PayPalApiConfig config) {
    Map<String, String> headers = new HashMap<>(3);
    headers.put("Accept", ContentType.JSON.toString());
    headers.put("Content-Type", ContentType.FORM_URLENCODED.toString());
    headers.put(
        "Authorization",
        "Basic "
            .concat(
                Base64.encode(
                    (config.getClientId().concat(":").concat(config.getSecret()))
                        .getBytes(StandardCharsets.UTF_8))));
    Map<String, Object> params = new HashMap<>(1);
    params.put("grant_type", "client_credentials");
    return post(getReqUrl(PayPalApiUrl.GET_TOKEN, config.isSandBox()), params, headers);
  }

  /**
   * 创建订单
   *
   * @param config {@link PayPalApiConfig} 支付配置
   * @param data 请求参数
   */
  public static YogHttpResponse createOrder(PayPalApiConfig config, String data) {
    PayPalAccessToken accessToken = PayPalAccessTokenKit.get(config.getClientId());
    return post(
        getReqUrl(PayPalApiUrl.CHECKOUT_ORDERS, config.isSandBox()),
        data,
        getBaseHeaders(accessToken));
  }

  /**
   * 更新订单
   *
   * @param config {@link PayPalApiConfig} 支付配置
   * @param id 订单号
   * @param data 请求参数
   */
  public static YogHttpResponse updateOrder(PayPalApiConfig config, String id, String data) {
    PayPalAccessToken accessToken = PayPalAccessTokenKit.get(config.getClientId());
    String url = getReqUrl(PayPalApiUrl.CHECKOUT_ORDERS, config.isSandBox()).concat("/").concat(id);
    return patch(url, data, getBaseHeaders(accessToken));
  }

  /**
   * 查询订单
   *
   * @param config {@link PayPalApiConfig} 支付配置
   * @param orderId 订单号
   * @return {@link YogHttpResponse} 请求返回的结果
   */
  public static YogHttpResponse queryOrder(PayPalApiConfig config, String orderId) {
    PayPalAccessToken accessToken = PayPalAccessTokenKit.get(config.getClientId());
    String url =
        getReqUrl(PayPalApiUrl.CHECKOUT_ORDERS, config.isSandBox()).concat("/").concat(orderId);
    return get(url, null, getBaseHeaders(accessToken));
  }

  /**
   * 确认订单
   *
   * @param config {@link PayPalApiConfig} 支付配置
   * @param id 订单号
   * @param data 请求参数
   * @return {@link YogHttpResponse} 请求返回的结果
   */
  public static YogHttpResponse captureOrder(PayPalApiConfig config, String id, String data) {
    PayPalAccessToken accessToken = PayPalAccessTokenKit.get(config.getClientId());
    String url = String.format(getReqUrl(PayPalApiUrl.CAPTURE_ORDER, config.isSandBox()), id);
    return post(url, data, getBaseHeaders(accessToken));
  }

  /**
   * 查询确认的订单
   *
   * @param config {@link PayPalApiConfig} 支付配置
   * @param captureId 订单号
   * @return {@link YogHttpResponse} 请求返回的结果
   */
  public static YogHttpResponse captureQuery(PayPalApiConfig config, String captureId) {
    PayPalAccessToken accessToken = PayPalAccessTokenKit.get(config.getClientId());
    String url =
        String.format(getReqUrl(PayPalApiUrl.CAPTURE_QUERY, config.isSandBox()), captureId);
    return get(url, null, getBaseHeaders(accessToken));
  }

  /**
   * 退款
   *
   * @param config {@link PayPalApiConfig} 支付配置
   * @param captureId 订单号
   * @param data 请求参数
   * @return {@link YogHttpResponse} 请求返回的结果
   */
  public static YogHttpResponse refund(PayPalApiConfig config, String captureId, String data) {
    PayPalAccessToken accessToken = PayPalAccessTokenKit.get(config.getClientId());
    String url = String.format(getReqUrl(PayPalApiUrl.REFUND, config.isSandBox()), captureId);
    return post(url, data, getBaseHeaders(accessToken));
  }

  /**
   * 查询退款
   *
   * @param config {@link PayPalApiConfig} 支付配置
   * @param id 订单号
   * @return {@link YogHttpResponse} 请求返回的结果
   */
  public static YogHttpResponse refundQuery(PayPalApiConfig config, String id) {
    PayPalAccessToken accessToken = PayPalAccessTokenKit.get(config.getClientId());
    String url = String.format(getReqUrl(PayPalApiUrl.REFUND_QUERY, config.isSandBox()), id);
    return get(url, null, getBaseHeaders(accessToken));
  }

  /**
   * post 请求
   *
   * @param url 请求 url
   * @param params {@link Map} 请求参数
   * @param headers {@link Map} 请求头
   * @return {@link YogHttpResponse} 请求返回的结果
   */
  public static YogHttpResponse post(
      String url, Map<String, Object> params, Map<String, String> headers) {
    return HttpKit.getDelegate().post(url, params, headers);
  }

  /**
   * get 请求
   *
   * @param url 请求 url
   * @param params {@link Map} 请求参数
   * @param headers {@link Map} 请求头
   * @return {@link YogHttpResponse} 请求返回的结果
   */
  public static YogHttpResponse get(
      String url, Map<String, Object> params, Map<String, String> headers) {
    return HttpKit.getDelegate().get(url, params, headers);
  }

  /**
   * post 请求
   *
   * @param url 请求 url
   * @param data {@link String} 请求参数
   * @param headers {@link Map} 请求头
   * @return {@link YogHttpResponse} 请求返回的结果
   */
  public static YogHttpResponse post(String url, String data, Map<String, String> headers) {
    return HttpKit.getDelegate().post(url, data, headers);
  }

  /**
   * patch 请求
   *
   * @param url 请求 url
   * @param data {@link String} 请求参数
   * @param headers {@link Map} 请求头
   * @return {@link YogHttpResponse} 请求返回的结果
   */
  public static YogHttpResponse patch(String url, String data, Map<String, String> headers) {
    return HttpKit.getDelegate().patch(url, data, headers);
  }

  public static Map<String, String> getBaseHeaders(PayPalAccessToken accessToken) {
    return getBaseHeaders(accessToken, PayKit.generateStr(), null, null);
  }

  public static Map<String, String> getBaseHeaders(
      PayPalAccessToken accessToken,
      String payPalRequestId,
      String payPalPartnerAttributionId,
      String prefer) {
    if (accessToken == null
        || StrUtil.isEmpty(accessToken.getTokenType())
        || StrUtil.isEmpty(accessToken.getAccessToken())) {
      throw new RuntimeException("PayPalAccessToken is null");
    }
    Map<String, String> headers = new HashMap<>(3);
    headers.put("Content-Type", ContentType.JSON.toString());
    headers.put(
        "Authorization",
        accessToken.getTokenType().concat(" ").concat(accessToken.getAccessToken()));
    if (StrUtil.isNotEmpty(payPalRequestId)) {
      headers.put("PayPal-Request-Id", payPalRequestId);
    }
    if (StrUtil.isNotEmpty(payPalPartnerAttributionId)) {
      headers.put("PayPal-Partner-Attribution-Id", payPalPartnerAttributionId);
    }
    if (StrUtil.isNotEmpty(prefer)) {
      headers.put("Prefer", prefer);
    }
    return headers;
  }
}
