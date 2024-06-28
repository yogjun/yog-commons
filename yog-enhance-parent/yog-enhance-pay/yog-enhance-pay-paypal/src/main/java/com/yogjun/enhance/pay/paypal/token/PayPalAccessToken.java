package com.yogjun.enhance.pay.paypal.token;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.yogjun.commont.kits.RetryUtils;
import java.io.Serializable;
import java.util.Map;

/**
 * {@link PayPalAccessToken}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/27
 */
public class PayPalAccessToken implements Serializable, RetryUtils.ResultCheck {
  private static final long serialVersionUID = 150495825818051646L;
  private String access_token;
  private String token_type;
  private String app_id;
  private Integer expires_in;
  private Long expiredTime;
  private String json;

  /** http 请求状态码 */
  private Integer status;

  public PayPalAccessToken(String json, int httpCode) {
    this.json = json;
    this.status = httpCode;
    try {
      JSONObject jsonObject = JSONUtil.parseObj(json);
      this.access_token = jsonObject.getStr("access_token");
      this.expires_in = jsonObject.getInt("expires_in");
      this.app_id = jsonObject.getStr("app_id");
      this.token_type = jsonObject.getStr("token_type");
      if (expires_in != null) {
        this.expiredTime = System.currentTimeMillis() + ((expires_in - 9) * 1000);
      }
      if (jsonObject.containsKey("expiredTime")) {
        this.expiredTime = jsonObject.getLong("expiredTime");
      }
      if (jsonObject.containsKey("status")) {
        this.status = jsonObject.getInt("status");
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public boolean isAvailable() {
    if (status != 200) {
      return false;
    }
    if (expiredTime == null) {
      return false;
    }
    if (expiredTime < System.currentTimeMillis()) {
      return false;
    }
    return StrUtil.isNotEmpty(access_token);
  }

  public String getCacheJson() {
    Map<String, Object> temp = JSONUtil.toBean(json, Map.class);
    temp.put("expiredTime", expiredTime);
    temp.remove("expires_in");
    temp.remove("scope");
    temp.remove("nonce");
    return JSONUtil.toJsonStr(temp);
  }

  public String getAccessToken() {
    return access_token;
  }

  public void setAccessToken(String accessToken) {
    this.access_token = accessToken;
  }

  public String getTokenType() {
    return token_type;
  }

  public void setTokenType(String tokenType) {
    this.token_type = tokenType;
  }

  public String getAppId() {
    return app_id;
  }

  public void setAppId(String appId) {
    this.app_id = appId;
  }

  public Integer getExpiresIn() {
    return expires_in;
  }

  public void setExpiresIn(Integer expiresIn) {
    this.expires_in = expiresIn;
  }

  public Long getExpiredTime() {
    return expiredTime;
  }

  public void setExpiredTime(Long expiredTime) {
    this.expiredTime = expiredTime;
  }

  @Override
  public boolean matching() {
    return isAvailable();
  }

  public String getJson() {
    return json;
  }

  public void setJson(String json) {
    this.json = json;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}
