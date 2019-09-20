package com.taotao.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Map;

/**
 * @Title fastJson工具类
 * @Description: fastJson工具类
 * @Author caoqiang
 * @Date 2019/9/20 10:37
 * @Version 1.0
 */
public class FastJsonUtils {

    /**
     * json转bean
     * @param jsonData
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getJsonToBean(String jsonData,Class<T> clazz){
        return JSON.parseObject(jsonData,clazz);
    }

    /**
     * bean转json
     * @param object
     * @return
     */
    public static String getBeanToJson(Object object){
        return JSON.toJSONString(object);
    }

    /**
     * json转换为List
     * @param jsonData
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> getJsonToList(String jsonData,Class<T> clazz){
        return JSON.parseArray(jsonData,clazz);
    }

    /**
     * 把JSON数据转换成较为复杂的List<Map<String, Object>>
     * @param jsonData
     * @return
     */
    public static List<Map<String,Object>> getJsonToMap(String jsonData){
        return JSON.parseObject(jsonData,new TypeReference<List<Map<String,Object>>>(){});
    }
}
