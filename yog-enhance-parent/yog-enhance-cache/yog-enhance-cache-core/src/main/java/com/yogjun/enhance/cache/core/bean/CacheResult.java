package com.yogjun.enhance.cache.core.bean;

import com.yogjun.enhance.cache.core.enums.CacheResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {@link CacheResult}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/27
 */
@Data
@NoArgsConstructor
public class CacheResult {
  private CacheResultCode resultCode;

  public static final CacheResult SUCCESS_WITHOUT_MSG = new CacheResult(CacheResultCode.SUCCESS);
  public static final CacheResult PART_SUCCESS_WITHOUT_MSG =
      new CacheResult(CacheResultCode.PART_SUCCESS);
  public static final CacheResult FAIL_WITHOUT_MSG = new CacheResult(CacheResultCode.FAIL);
  public static final CacheResult FAIL_ILLEGAL_ARGUMENT = new CacheResult(CacheResultCode.FAIL);
  public static final CacheResult EXISTS_WITHOUT_MSG = new CacheResult(CacheResultCode.EXISTS);

  public CacheResult(CacheResultCode resultCode) {
    this.resultCode = resultCode;
  }

  public boolean isSuccess() {
    return getResultCode() == CacheResultCode.SUCCESS;
  }
}
