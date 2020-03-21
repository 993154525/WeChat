package com.st.springboot.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author shaotian
 * @create 2020-03-12 14:46
 */
public class JSONUtil {

    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }

}
