package com.ferry.common.mode;


import com.ferry.common.utils.ContextFactoryUtil;

import java.util.Map;
import java.util.Objects;

/**
 * 描述：自定义spring上下文处理器缓存
 */
public class HandlerContext {

    private Map<String, Class> handleMap;

    public HandlerContext(Map<String, Class> handleMap) {
        this.handleMap = handleMap;
    }

    public IModeHandleService getInstance(String type) {
        if (Objects.isNull(type)) {
            throw new IllegalArgumentException("parameter type is null");
        }

        Class<?> clazz = handleMap.get(type);
        if (clazz == null) {
            throw new IllegalArgumentException("not found handler for type:" + type);
        }
        return (IModeHandleService) ContextFactoryUtil.getBean(clazz);
    }
}
