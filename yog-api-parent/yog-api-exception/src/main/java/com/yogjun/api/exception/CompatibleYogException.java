package com.yogjun.api.exception;

/**
 * {@link CompatibleYogException}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/9
 */
public class CompatibleYogException extends YogException {
  private static final long serialVersionUID = 1L;

  public CompatibleYogException(String message) {
    super(message);
  }

  public CompatibleYogException(String message, Throwable cause) {
    super(message, cause);
  }

  public CompatibleYogException(Throwable cause) {
    super(cause);
  }
}
