package com.yogjun.commont.kits;

import com.yogjun.commont.kits.utils.BeanUtils;

/**
 * {@link BeanUtil}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/12
 */
public class BeanUtil<S, T> {

  /** source */
  private final S source;

  /** target */
  private final T target;

  private BeanUtil(S source, T target) {
    this.source = source;
    this.target = target;
  }

  public static <S, T> BeanUtil<S, T> build(S source, Class<? extends T> targetClass) {
    try {
      T t = targetClass.newInstance();
      return new BeanUtil<>(source, t);
    } catch (Exception e) {
      throw new IllegalArgumentException(" targetClass must contain public noarg constructor", e);
    }
  }

  public static <S, T> T createAndCopy(S source, Class<? extends T> targetClass) {
    return BeanUtil.build(source, targetClass).copy().getTarget();
  }

  public BeanUtil<S, T> copy() {
    BeanUtils.copyProperties(source, target);
    return this;
  }

  public S getSource() {
    return source;
  }

  public T getTarget() {
    return target;
  }
}
