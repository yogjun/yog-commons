package com.yogjun.commont.kits.utils;

public class ClassTypeUtils {

  /**
   * Class[]转String[]
   *
   * @param typeStrs 对象描述[]
   * @return Class[]
   */
  public static Class[] getClasses(String[] typeStrs) throws RuntimeException {
    if (CommonUtils.isEmpty(typeStrs)) {
      return new Class[0];
    } else {
      Class[] classes = new Class[typeStrs.length];
      for (int i = 0; i < typeStrs.length; i++) {
        classes[i] = getClass(typeStrs[i]);
      }
      return classes;
    }
  }

  /**
   * String转Class
   *
   * @param typeStr 对象描述
   * @return Class[]
   */
  public static Class getClass(String typeStr) {
    Class clazz = ReflectCache.getClassCache(typeStr);
    if (clazz == null) {
      if ("void".equals(typeStr)) {
        clazz = void.class;
      } else if ("boolean".equals(typeStr)) {
        clazz = boolean.class;
      } else if ("byte".equals(typeStr)) {
        clazz = byte.class;
      } else if ("char".equals(typeStr)) {
        clazz = char.class;
      } else if ("double".equals(typeStr)) {
        clazz = double.class;
      } else if ("float".equals(typeStr)) {
        clazz = float.class;
      } else if ("int".equals(typeStr)) {
        clazz = int.class;
      } else if ("long".equals(typeStr)) {
        clazz = long.class;
      } else if ("short".equals(typeStr)) {
        clazz = short.class;
      } else {
        String jvmName = canonicalNameToJvmName(typeStr);
        clazz = ClassUtils.forName(jvmName);
      }
      ReflectCache.putClassCache(typeStr, clazz);
    }
    return clazz;
  }

  /**
   * 通用描述转JVM描述
   *
   * @param canonicalName 例如 int[]
   * @return JVM描述 例如 [I;
   */
  public static String canonicalNameToJvmName(String canonicalName) {
    boolean isArray = canonicalName.endsWith("[]");
    if (isArray) {
      String t = ""; // 计数，看上几维数组
      while (isArray) {
        canonicalName = canonicalName.substring(0, canonicalName.length() - 2);
        t += "[";
        isArray = canonicalName.endsWith("[]");
      }
      if ("boolean".equals(canonicalName)) {
        canonicalName = t + "Z";
      } else if ("byte".equals(canonicalName)) {
        canonicalName = t + "B";
      } else if ("char".equals(canonicalName)) {
        canonicalName = t + "C";
      } else if ("double".equals(canonicalName)) {
        canonicalName = t + "D";
      } else if ("float".equals(canonicalName)) {
        canonicalName = t + "F";
      } else if ("int".equals(canonicalName)) {
        canonicalName = t + "I";
      } else if ("long".equals(canonicalName)) {
        canonicalName = t + "J";
      } else if ("short".equals(canonicalName)) {
        canonicalName = t + "S";
      } else {
        canonicalName = t + "L" + canonicalName + ";";
      }
    }
    return canonicalName;
  }

  /**
   * Class[]转String[] <br>
   * 注意，得到的String可能不能直接用于Class.forName，请使用getClasses(String[])反向获取
   *
   * @param types Class[]
   * @return 对象描述
   * @see #getClasses(String[])
   */
  public static String[] getTypeStrs(Class[] types) {
    return getTypeStrs(types, false);
  }

  /**
   * Class[]转String[] <br>
   * 注意，得到的String可能不能直接用于Class.forName，请使用getClasses(String[])反向获取
   *
   * @param types Class[]
   * @param javaStyle JDK自带格式，例如 int[], true的话返回 [I; false的话返回int[]
   * @return 对象描述
   * @see #getClasses(String[])
   */
  public static String[] getTypeStrs(Class[] types, boolean javaStyle) {
    if (CommonUtils.isEmpty(types)) {
      return StringUtils.EMPTY_STRING_ARRAY;
    } else {
      String[] strings = new String[types.length];
      for (int i = 0; i < types.length; i++) {
        strings[i] = javaStyle ? types[i].getName() : getTypeStr(types[i]);
      }
      return strings;
    }
  }

  /**
   * Class转String<br>
   * 注意，得到的String可能不能直接用于Class.forName，请使用getClass(String)反向获取
   *
   * @param clazz Class
   * @return 对象
   * @see #getClass(String)
   */
  public static String getTypeStr(Class clazz) {
    String typeStr = ReflectCache.getTypeStrCache(clazz);
    if (typeStr == null) {
      if (clazz.isArray()) {
        String name = clazz.getName(); // 原始名字：[Ljava.lang.String;
        typeStr = jvmNameToCanonicalName(name); // java.lang.String[]
      } else {
        typeStr = clazz.getName();
      }
      ReflectCache.putTypeStrCache(clazz, typeStr);
    }
    return typeStr;
  }

  /**
   * JVM描述转通用描述
   *
   * @param jvmName 例如 [I;
   * @return 通用描述 例如 int[]
   */
  public static String jvmNameToCanonicalName(String jvmName) {
    boolean isArray = jvmName.charAt(0) == '[';
    if (isArray) {
      String cnName = StringUtils.EMPTY; // 计数，看上几维数组
      int i = 0;
      for (; i < jvmName.length(); i++) {
        if (jvmName.charAt(i) != '[') {
          break;
        }
        cnName += "[]";
      }
      String componentType = jvmName.substring(i, jvmName.length());
      if ("Z".equals(componentType)) {
        cnName = "boolean" + cnName;
      } else if ("B".equals(componentType)) {
        cnName = "byte" + cnName;
      } else if ("C".equals(componentType)) {
        cnName = "char" + cnName;
      } else if ("D".equals(componentType)) {
        cnName = "double" + cnName;
      } else if ("F".equals(componentType)) {
        cnName = "float" + cnName;
      } else if ("I".equals(componentType)) {
        cnName = "int" + cnName;
      } else if ("J".equals(componentType)) {
        cnName = "long" + cnName;
      } else if ("S".equals(componentType)) {
        cnName = "short" + cnName;
      } else {
        cnName = componentType.substring(1, componentType.length() - 1) + cnName; // 对象的 去掉L
      }
      return cnName;
    }
    return jvmName;
  }
}
