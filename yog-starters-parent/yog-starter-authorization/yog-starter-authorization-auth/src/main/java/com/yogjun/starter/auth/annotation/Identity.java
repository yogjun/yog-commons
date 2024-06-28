package com.yogjun.starter.auth.annotation;

import java.lang.annotation.*;

/**
 * {@link Identity}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/13
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Identity {}
