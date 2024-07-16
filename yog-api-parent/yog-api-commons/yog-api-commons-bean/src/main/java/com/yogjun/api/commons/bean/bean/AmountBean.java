package com.yogjun.api.commons.bean.bean;

import java.math.BigDecimal;
import lombok.Data;

/**
 * {@link AmountBean}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/7/11
 */
@Data
public class AmountBean {
  private BigDecimal amount;
  private String currency;
}
