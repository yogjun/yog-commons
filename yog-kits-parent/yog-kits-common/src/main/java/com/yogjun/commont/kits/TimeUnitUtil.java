package com.yogjun.commont.kits;

import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

/**
 * {@link TimeUnitUtil}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/27
 */
public class TimeUnitUtil {
  public static ChronoUnit toChronoUnit(TimeUnit timeUnit) {
    switch (timeUnit) {
      case NANOSECONDS:
        return ChronoUnit.NANOS;
      case MICROSECONDS:
        return ChronoUnit.MICROS;
      case MILLISECONDS:
        return ChronoUnit.MILLIS;
      case SECONDS:
        return ChronoUnit.SECONDS;
      case MINUTES:
        return ChronoUnit.MINUTES;
      case HOURS:
        return ChronoUnit.HOURS;
      case DAYS:
        return ChronoUnit.DAYS;
      default:
        throw new AssertionError();
    }
  }
}
