package com.yogjun.api.exception;

import static org.springframework.http.HttpStatus.OK;

import com.yogjun.api.exception.common.ErrorCode;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

/**
 * {@link CompatibleYogException}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/9
 */
public class CompatibleYogException extends YogException {

  /**
   * Biz Process Result return With {@link HttpStatus}
   *
   * @see org.springframework.http.HttpStatus
   */
  private HttpStatus httpStatus = OK;

  /**
   * 业务处理错误识别代码，默认：-1
   *
   * <p>
   */
  private int code = -1;

  /**
   * 业务异常，携带的数据体
   *
   * <p>
   *
   * @since 1.0.5.RC5
   */
  @Nullable private Object data;

  public HttpStatus httpStatus() {
    return this.httpStatus;
  }

  public int httpStatusCode() {
    return this.httpStatus.value();
  }

  public int code() {
    return this.code;
  }

  public @Nullable Object data() {
    return this.data;
  }

  // Constructors Defined.

  /**
   * Constructs a new exception with the specified detail message. The cause is not initialized, and
   * may subsequently be initialized by a call to {@link #initCause}.
   *
   * @param httpStatus Http Request & Response Status Code
   * @param code biz error code
   * @param message the detail message. The detail message is saved for later retrieval by the
   *     {@link #getMessage()} method.
   */
  public CompatibleYogException(HttpStatus httpStatus, int code, String message) {
    super(message);
    this.httpStatus = httpStatus;
    this.code = code;
  }

  /**
   * Constructs a new exception with the specified detail message. The cause is not initialized, and
   * may subsequently be initialized by a call to {@link #initCause}.
   *
   * @param httpStatus Http Request & Response Status Code
   * @param code biz error code
   * @param data exception extensional data object
   * @param message the detail message. The detail message is saved for later retrieval by the
   *     {@link #getMessage()} method.
   */
  public <T> CompatibleYogException(
      HttpStatus httpStatus, int code, @Nullable T data, String message) {
    super(message);
    this.httpStatus = httpStatus;
    this.code = code;
    this.data = data;
  }

  /**
   * Constructs a new exception with the specified detail message and cause.
   *
   * <p>Note that the detail message associated with {@code cause} is <i>not</i> automatically
   * incorporated in this exception's detail message.
   *
   * @param httpStatus Http Request & Response Status Code
   * @param code biz error code
   * @param message the detail message (which is saved for later retrieval by the {@link
   *     #getMessage()} method).
   * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method).
   *     (A <tt>null</tt> value is permitted, and indicates that the cause is nonexistent or
   *     unknown.)
   * @since 1.4
   */
  @Builder
  public CompatibleYogException(HttpStatus httpStatus, int code, String message, Throwable cause) {
    super(message, cause);
    this.httpStatus = httpStatus;
    this.code = code;

    // check
    if (httpStatus == null) {
      this.httpStatus = OK;
    }
  }

  /**
   * Constructs a new exception with the specified detail message and cause.
   *
   * <p>Note that the detail message associated with {@code cause} is <i>not</i> automatically
   * incorporated in this exception's detail message.
   *
   * @param httpStatus Http Request & Response Status Code
   * @param code biz error code
   * @param data exception extensional data object
   * @param message the detail message (which is saved for later retrieval by the {@link
   *     #getMessage()} method).
   * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method).
   *     (A <tt>null</tt> value is permitted, and indicates that the cause is nonexistent or
   *     unknown.)
   * @since 1.0.5.RC5
   */
  public <T> CompatibleYogException(
      HttpStatus httpStatus, int code, @Nullable T data, String message, Throwable cause) {
    super(message, cause);
    this.httpStatus = httpStatus;
    this.code = code;
    this.data = data;

    // check
    if (httpStatus == null) {
      this.httpStatus = OK;
    }
  }

  /**
   * Constructs a new runtime exception with the specified cause and a detail message of
   * <tt>(cause==null ? null : cause.toString())</tt> (which typically contains the class and detail
   * message of <tt>cause</tt>). This constructor is useful for runtime exceptions that are little
   * more than wrappers for other throwables.
   *
   * @param httpStatus Http Request & Response Status Code
   * @param code biz error code
   * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method).
   *     (A <tt>null</tt> value is permitted, and indicates that the cause is nonexistent or
   *     unknown.)
   * @since 1.4
   */
  public CompatibleYogException(HttpStatus httpStatus, int code, Throwable cause) {
    super(cause);
    this.httpStatus = httpStatus;
    this.code = code;
  }

  /**
   * Constructs a new runtime exception with the specified cause and a detail message of
   * <tt>(cause==null ? null : cause.toString())</tt> (which typically contains the class and detail
   * message of <tt>cause</tt>). This constructor is useful for runtime exceptions that are little
   * more than wrappers for other throwables.
   *
   * @param httpStatus Http Request & Response Status Code
   * @param code biz error code
   * @param data exception extensional data object
   * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method).
   *     (A <tt>null</tt> value is permitted, and indicates that the cause is nonexistent or
   *     unknown.)
   * @since 1.4
   */
  public <T> CompatibleYogException(
      HttpStatus httpStatus, int code, @Nullable T data, Throwable cause) {
    super(cause);
    this.httpStatus = httpStatus;
    this.code = code;
    this.data = data;
  }

  /**
   * Constructs a new exception with the specified detail message. The cause is not initialized, and
   * may subsequently be initialized by a call to {@link #initCause}.
   *
   * @param code biz error code
   * @see ErrorCode error code instance
   */
  public CompatibleYogException(ErrorCode code) {
    super(code.errorMessage());
    this.httpStatus = code.httpStatus();
    this.code = code.code();
  }

  /**
   * Constructs a new exception with the specified detail message. The cause is not initialized, and
   * may subsequently be initialized by a call to {@link #initCause}.
   *
   * @param code biz error code
   * @param data exception extensional data object
   * @see ErrorCode error code instance
   * @since 1.0.5.RC5
   */
  public <T> CompatibleYogException(ErrorCode code, @Nullable T data) {
    super(code.errorMessage());
    this.httpStatus = code.httpStatus();
    this.code = code.code();
    this.data = data;
  }

  /**
   * Constructs a new exception with the specified detail message. The cause is not initialized, and
   * may subsequently be initialized by a call to {@link #initCause}.
   *
   * @param code biz error code
   * @see ErrorCode error code instance
   * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method).
   *     (A <tt>null</tt> value is permitted, and indicates that the cause is nonexistent or
   *     unknown.)
   */
  public CompatibleYogException(ErrorCode code, Throwable cause) {
    super(code.errorMessage(), cause);
    this.httpStatus = code.httpStatus();
    this.code = code.code();
  }

  /**
   * Constructs a new exception with the specified detail message. The cause is not initialized, and
   * may subsequently be initialized by a call to {@link #initCause}.
   *
   * @param code biz error code
   * @param data exception extensional data object
   * @see ErrorCode error code instance
   * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method).
   *     (A <tt>null</tt> value is permitted, and indicates that the cause is nonexistent or
   *     unknown.)
   * @since 1.0.5.RC5
   */
  public <T> CompatibleYogException(ErrorCode code, @Nullable T data, Throwable cause) {
    super(code.errorMessage(), cause);
    this.httpStatus = code.httpStatus();
    this.code = code.code();
    this.data = data;
  }
}
