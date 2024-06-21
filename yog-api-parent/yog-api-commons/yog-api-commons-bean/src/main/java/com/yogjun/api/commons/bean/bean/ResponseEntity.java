package com.yogjun.api.commons.bean.bean;

import com.yogjun.api.commons.bean.base.BaseBean;
import java.time.LocalDateTime;
import lombok.*;

/**
 * {@link ResponseEntity}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/21
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntity<T> extends BaseBean {
  @Builder.Default private int code = -1;
  @Builder.Default private String message = "";
  private T data;
  private LocalDateTime timestamp = LocalDateTime.now();
}
