package com.yogjun.kits.strategy;

/**
 * {@link Strategy}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/9/13
 */
public interface Strategy<Param, Result> {

  Result execute(Param param);

  String getType();
}
