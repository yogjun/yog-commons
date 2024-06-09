package com.yogjun.api.exception;

/**
 * {@link BizException}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/9
 */
public class BizException extends YogException{
    public BizException(String message) {super(message);}
    public BizException(String message, Throwable cause) {super(message, cause);}
    public BizException(Throwable cause) {super(cause);}
}
