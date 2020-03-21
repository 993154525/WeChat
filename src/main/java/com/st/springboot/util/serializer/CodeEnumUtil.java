package com.st.springboot.util.serializer;

import com.st.springboot.enums.CodeEnum;

/**
 * @author shaotian
 * @create 2020-03-14 13:57
 */
public class CodeEnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }

}
