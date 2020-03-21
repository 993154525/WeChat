package com.st.springboot.util;

import java.util.Random;

/**
 * @author shaotian
 * @create 2020-02-20 20:28
 */
public class KeyUtil {

    /**
     * 时间+随机数
     *
     * @return
     */
    public static synchronized String genKeyUtil() {
        Random random = new Random();

        Integer code = random.nextInt(89999) + 10000;

        return System.currentTimeMillis() + String.valueOf(code);
    }
}
