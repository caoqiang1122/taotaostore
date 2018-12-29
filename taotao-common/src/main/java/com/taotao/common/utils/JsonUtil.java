package com.taotao.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * 自定义响应结构
 */
public class JsonUtil {
    //定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转化为json字符串
     * @param data
     * @return
     */
    public static String objectToJson(Object data){
        try {
            String string = MAPPER.writeValueAsString(data);
            return string;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json对象转化为pojo对象list
     * @param jsonData
     * @param beanType
     * @param <T>
     * @return
     */
    public static <T>List<T> jsonList(String jsonData,Class<T> beanType){
        JavaType javaType = MAPPER.getTypeFactory().constructType(List.class,beanType);
        try {
            List<T> list = MAPPER.readValue(jsonData,javaType);
            return  list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
}