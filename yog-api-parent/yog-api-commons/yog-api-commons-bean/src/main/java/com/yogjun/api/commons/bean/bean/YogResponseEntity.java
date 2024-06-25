package com.yogjun.api.commons.bean.bean;

import com.yogjun.api.commons.bean.base.BaseBean;
import java.time.LocalDateTime;
import lombok.*;

/**
 * {@link YogResponseEntity}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/21
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class YogResponseEntity<T> extends BaseBean {
  @Builder.Default private int code = -1;
  @Builder.Default private String message = "";
  private T data;
  private LocalDateTime timestamp = LocalDateTime.now();

  public static YogResponseEntity<Object> ok(int code, Object body, String message) {
    return YogResponseEntity.builder().code(code).data(body).message(message).build();
  }

  public static YogResponseEntity<Object> fail(int code, String message) {
    return YogResponseEntity.builder().code(code).message(message).build();
  }

  public static YogResponseEntity<Object> fail(int code, Throwable e) {
    return YogResponseEntity.builder().code(code).message(e.getMessage()).build();
  }
}
