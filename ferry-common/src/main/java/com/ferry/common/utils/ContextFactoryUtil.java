package com.ferry.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 描述：Spring ApplicationContext 获取spring容器中的实例bean的工具类
 * <p>
 * 作者：HuTongFu
 * 时间：2019/7/3 17:13
 */
@Component
public class ContextFactoryUtil implements ApplicationContextAware {

    /**
     * Spring 应用上下文环境
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        ContextFactoryUtil.applicationContext = applicationContext;
    }

     /**
     * 获取对象
     *
     * @return Object bean的实例
     * @throws BeansException
     */
    public static Object getBean(String beanName) throws BeansException {
        if (!ContextFactoryUtil.containsBean(beanName)) {
            return null;
        } else {
            return applicationContext.getBean(beanName);
        }
    }

    /**
     * 获取bean
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 获取对象
     *
     * @return Object bean的实例
     * @throws BeansException
     */
    public static String[] getBeans() throws BeansException {
            return applicationContext.getBeanDefinitionNames();
    }


    /**
     * 如果BeanFactory包含一个与所给名称匹配的bean定义,则返回true
     *
     * @param name
     * @return boolean
     */
    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    /**
     * @param name
     * @return Class 注册对象的类型
     * @throws NoSuchBeanDefinitionException
     */
    @SuppressWarnings("rawtypes")
    public static Class getType(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.getType(name);
    }
}
