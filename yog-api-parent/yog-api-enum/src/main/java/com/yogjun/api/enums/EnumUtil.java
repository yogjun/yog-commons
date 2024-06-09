package com.yogjun.api.enums;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * {@link EnumUtil}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/9
 */
public class EnumUtil {

    public static Map<String, List<EnumVO>> getEnumDictMap(Set<Class> classes) {
        return classes.stream().collect(Collectors.toMap(Class::getSimpleName, EnumUtil::getEnumDict));
    }

    public static <T extends Enum<T>> List<EnumVO> getEnumDict(Class<T> clazz) {
        List<EnumVO> list = new ArrayList<>();
        if (clazz == null) {
            return list;
        }
        try {
            // 没传递code和desc的类型的默认使用getCode和getDesc方法
            final Method getDesc = clazz.getMethod("getMessage", new Class[]{});
            for (T stateEnum : clazz.getEnumConstants()) {
                EnumVO vo = new EnumVO();
                vo.setName(getDesc.invoke(stateEnum).toString());
                vo.setCode(stateEnum.name());
                list.add(vo);
            }
        } catch (Exception e) {

        }
        return list;
    }
}
