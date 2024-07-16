package com.yogjun.starter.pay.repository;

import com.yogjun.api.commons.repository.BasePO;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * {@link PayRecord}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/7/11
 */
@Getter
@Setter
@ToString
@FieldNameConstants
@Document(PayRecord.TABLE_NAME)
public class PayRecord extends BasePO {
  public static final String TABLE_NAME = "pay_record";
  private String payId;
  private String payType;
  private BigDecimal amount;
  private String currency;
}
