package com.ferry.admin.annotation;

import com.ferry.admin.enums.PlatformEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: huangchongzhe006
 * @Date: 2021/11/15
 * @Description 日志记录、自定义注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BussinessLog {
    /**
     * 业务的名称
     */
    String value() default "";

    /**
     * 平台，默认为后台管理
     */
    PlatformEnum platform() default PlatformEnum.ADMIN;

}
