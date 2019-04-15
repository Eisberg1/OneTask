package com.example.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

/**
 * Author:Sphinx
 * Date:2019/03/26 16:02
 * Description:
 */
@Component
public class JSONUtil {


    public static String object2String(Object o) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(o);
    }
    public static <T> T string2Object(String jsonStr,Class<T> clazz ) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonStr,clazz);
    }

}
