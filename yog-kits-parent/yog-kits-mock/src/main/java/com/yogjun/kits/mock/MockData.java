package com.yogjun.kits.mock;

import cn.hutool.core.util.RandomUtil;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/** 填充对象 */
public class MockData {

  public static <T> T fillObject(Class<T> clazz)
      throws IllegalAccessException, InstantiationException, ClassNotFoundException {
    Object o = clazz.newInstance();
    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
      field.setAccessible(true);
      String typeName = field.getType().getName();
      Object value = getValue(typeName);
      if (value != null) {
        field.set(o, value);
      } else if (typeName.contains("List") || typeName.contains("Set")) {
        List<Object> list = new ArrayList<>();
        String genericTypeName = field.getGenericType().getTypeName();
        Object value2 = getValue(genericTypeName);
        if (value2 != null) {
          for (int i = 0; i < 10; i++) {
            list.add(getValue(genericTypeName));
          }
        } else {
          String generatorTypeRoute =
              genericTypeName.substring(
                  genericTypeName.indexOf("<") + 1, genericTypeName.indexOf(">"));
          for (int i = 0; i < 10; i++) {
            list.add(fillObject(Class.forName(generatorTypeRoute)));
          }
        }
        field.set(o, list);
      } else {
        field.set(o, fillObject(Class.forName(typeName)));
      }
    }
    return (T) o;
  }

  private static Object getValue(String type) {
    if (type.contains("String")) {
      return GenerateData.getString(10);
    } else if (type.contains("int") || type.contains("Integer")) {
      return GenerateData.getInt(0, 100);
    } else if (type.contains("long") || type.contains("Long")) {
      return GenerateData.getInt(0, 1000000000);
    } else if (type.contains("float") || type.contains("Float")) {
      return GenerateData.getDouble(0, 100);
    } else if (type.contains("double") || type.contains("Double")) {
      return GenerateData.getDouble(0, 100);
    } else if (type.contains("BigDecimal")) {
      return new BigDecimal(GenerateData.getDouble(0, 100));
    } else {
      return null;
    }
  }
}

class GenerateData {

  public static String getString(int length) {
    return RandomUtil.randomString(length);
  }

  public static int getInt(int min, int max) {
    return RandomUtil.randomInt(min, max);
  }

  public static double getDouble(int min, int max) {
    return RandomUtil.randomDouble(min, max);
  }
}
