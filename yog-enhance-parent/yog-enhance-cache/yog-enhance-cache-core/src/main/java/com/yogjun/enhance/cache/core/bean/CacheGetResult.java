package com.yogjun.enhance.cache.core.bean;

import com.yogjun.enhance.cache.core.enums.CacheResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CacheGetResult<V> extends CacheResult {

  public static final CacheGetResult NOT_EXISTS_WITHOUT_MSG =
      new CacheGetResult(CacheResultCode.NOT_EXISTS);
  public static final CacheGetResult EXPIRED_WITHOUT_MSG =
      new CacheGetResult(CacheResultCode.EXPIRED);

  private V value;

  public CacheGetResult(CacheResultCode resultCode, V value) {
    super(resultCode);
    this.value = value;
  }

  public CacheGetResult(CacheResultCode cacheResultCode) {
    super(cacheResultCode);
  }
}
