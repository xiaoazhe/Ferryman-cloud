package com.ferry.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * 描述：json 和 bean 相互转换工具类
 */
public class JsonUtils {

    /**
     * Bean对象转JSON
     *
     * @param object
     * @param dataFormatString
     * @return
     */
    public static String beanToJson(Object object, String dataFormatString) {
        if (object != null) {
            if (StringUtils.isEmpty(dataFormatString)) {
                return JSONObject.toJSONString(object);
            }
            return JSON.toJSONStringWithDateFormat(object, dataFormatString);
        } else {
            return null;
        }
    }

    /**
     * Bean对象转JSON
     *
     * @param object
     * @return
     */
    public static String beanToJson(Object object) {
        if (object != null) {
            return JSON.toJSONString(object);
        } else {
            return null;
        }
    }

    /**
     * String转JSON字符串
     *
     * @param key
     * @param value
     * @return
     */
    public static String stringToJsonByFastjson(String key, String value) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return null;
        }
        Map<String, String> map = new HashMap<String, String>(16);
        map.put(key, value);
        return beanToJson(map, null);
    }

    /**
     * 将json字符串转换成对象
     *
     * @param json
     * @param clazz
     * @return
     */
    public static Object jsonToBean(String json, Object clazz) {
        if (StringUtils.isEmpty(json) || clazz == null) {
            return null;
        }
        return JSON.parseObject(json, clazz.getClass());
    }

    /**
     * json字符串转map
     *
     * @param json
     * @return
     */
    public static Map jsonToMap(String json) {
        if (StringUtils.isEmpty(json)) {
            return Maps.newHashMap();
        }
        return JSON.parseObject(json, Map.class);
    }

    /**
     * 定义返回封装 JSONObject 对象
     *
     * @param object 对象
     * @return JSONObject
     */
    public static JSONObject setResult(Object object) {
        JSONObject jsonObject = new JSONObject(1);
        jsonObject.put("object", object);
        return jsonObject;
    }

    /**
     * 从 JSONObject 获取指定key的值
     *
     * @param jsonObject 对象
     * @return JSONObject
     */
    public static Object getResult(JSONObject jsonObject) {
        if (jsonObject != null) {
            return jsonObject.get("object");
        }
        return null;
    }
}
