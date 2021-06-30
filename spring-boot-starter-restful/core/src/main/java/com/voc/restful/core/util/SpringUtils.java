package com.voc.restful.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/24 19:44
 */
public class SpringUtils implements ApplicationContextAware, EnvironmentAware {
    private static ApplicationContext applicationContext = null;
    private static Environment environment = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtils.applicationContext == null) {
            SpringUtils.applicationContext = applicationContext;
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        if (SpringUtils.environment == null) {
            SpringUtils.environment = environment;
        }
    }

    /**
     * 获取 applicationContext
     *
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean.
     *
     * @param name 名称
     * @return Object
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean
     *
     * @param clazz type the bean must match; can be an interface or superclass
     * @param <T>   instances of generic
     * @return an instance of the single bean matching the required type
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name  the name of the bean to retrieve
     * @param clazz type the bean must match; can be an interface or superclass
     * @param <T>   instances of generic
     * @return an instance of the single bean matching the required type
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * 如果只有一个该类型的实列，则返回该实列，否则为空。
     * @param type Class<T>
     * @param <T> 泛型
     * @return a bean of the requested class if there's just a single registered component, null otherwise.
     */
    public static <T> T getBeanOrNull(Class<T> type) {
        String[] beanNames = applicationContext.getBeanNamesForType(type);
        if (beanNames.length != 1) {
            return null;
        }

        return applicationContext.getBean(beanNames[0], type);
    }

    /**
     * Return the property value associated with the given key,
     * or {@code null} if the key cannot be resolved.
     *
     * @param key the property name to resolve
     */
    public static String getProperty(String key) {
        return environment.getProperty(key);
    }

    /**
     * Return the property value associated with the given key,
     * or {@code null} if the key cannot be resolved.
     *
     * @param key        the property name to resolve
     * @param targetType the expected type of the property value
     */
    public static <T> T getProperty(String key, Class<T> targetType) {
        return environment.getProperty(key, targetType);
    }

}
