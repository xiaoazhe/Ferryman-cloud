package com.ferry.admin.constant;

import java.lang.annotation.*;

/**
 * 描述：自定义策略模式注解：用于指定条件
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
@Inherited
public @interface HandleType {

    String value();
}
