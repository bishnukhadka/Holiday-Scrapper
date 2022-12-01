package com.acem.demo.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtil {
    public static ObjectMapper objectMapper;

    public static String toJson(Object object) {
        String str =null;
        try {
            str =  objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return str;
    }
}
