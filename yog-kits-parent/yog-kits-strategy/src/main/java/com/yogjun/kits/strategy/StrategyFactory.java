package com.yogjun.kits.strategy;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * {@link StrategyFactory}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/9/13
 */
public abstract class StrategyFactory {
  private final Map<String, Strategy> strategyMap;

  public StrategyFactory(List<Strategy> services) {
    this.strategyMap =
        services.stream()
            .filter(doh -> Objects.nonNull(doh.getType()))
            .collect(Collectors.toMap(Strategy::getType, Function.identity()));
  }

  public Strategy getStrategyMap(String type) throws IllegalAccessException {
    if (type == null) {
      throw new IllegalAccessException("type should not be empty.");
    }
    if (strategyMap.get(type) == null) {
      throw new IllegalAccessException("type cannot find strategy");
    }
    return strategyMap.get(type);
  }
}
