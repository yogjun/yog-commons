package com.yogjun.api.exception;

/**
 * {@link YogException}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/9
 */
public class YogException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public YogException(String message) {super(message);}
    public YogException(String message, Throwable cause) {super(message, cause);}
    public YogException(Throwable cause) {super(cause);}
}
