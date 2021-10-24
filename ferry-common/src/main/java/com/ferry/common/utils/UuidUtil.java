package com.ferry.common.utils;


import java.util.UUID;

/**
 * 描述：UUID 生成工具类
 */
public class UuidUtil {
    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
