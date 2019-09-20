package com.taotao.common.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * @Title GSON工具类
 * @Description: GSON工具类
 * @Author caoqiang
 * @Date 2019/9/12 14:16
 * @Version 1.0
 */
public class GsonUtils {
    /**
     * 使用DCL双检查锁机制保证单例模式的线程安全
     */
    private volatile static Gson gson;
    private GsonUtils(){

    }
    public static Gson getObject() {
        if (gson == null){
            synchronized (GsonUtils.class){
                if (gson == null){
                    gson = new Gson();
                }
            }
        }
        return gson;
    }

    /**
     * 对象转json
     * @param object
     * @return
     */
    public static String gsonString(Object object){
        String gsonString = null;
        if (gson != null){
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }

    /**
     * json转对象
     * T表示某一类具体对象，（如果放多种对象会报错） ？标识多种对象
     * @param gsonString
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T gsonToBean(String gsonString,Class<T> cls){
        T t = null;
        if (gson != null){
            t = gson.fromJson(gsonString,cls);
        }
        return t;
    }

    /**
     * json转list
     * @param gsonString
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> gsonToList(String gsonString,Class<T> cls){
        List<T> list = null;
        if (gson != null){
            list = gson.fromJson(gsonString,new TypeToken<List<T>>(){}.getType());
        }
        return list;
    }

    /**
     * json转list中有map
     * @param gsonString
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<Map<String,T>> gsonToListMaps(String gsonString,Class<T> cls){
        List<Map<String,T>> list = null;
        if (gson != null){
            list = gson.fromJson(gsonString,new TypeToken<List<Map<String,T>>>(){}.getType());
        }
        return list;
    }


    public static <T> Map<String,T> gsonToMap(String gsonString){
        Map<String,T> map = null;
        if (gson != null){
            map = gson.fromJson(gsonString,new TypeToken<Map<String,T>>(){}.getType());
        }
        return map;
    }
}
