package com.base.framework.bean;

import org.springframework.context.ApplicationContext;

/**
 * 可以从applicationContext根据类名或实例名称获取实例，
 * 使用时需要在App启动时初始化，否则无法从上下文环境获得实例
 */
public class AppBeanContext {
    private static ApplicationContext applicationContext;

    private AppBeanContext() {
        super();
    }

    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    /**
     * 根据bean类获取bean
     *
     * @param clazz 类名
     * @return 实例
     */
    public static <T> T getBean(Class<T> clazz) {
        if (applicationContext == null) {
            return null;
        }
        return applicationContext.getBean(clazz);
    }

    /**
     * 根据bean的ID获取bean
     *
     * @param beanId
     * @return 实例
     */
    public static Object getBean(String beanId) {
        return applicationContext.getBean(beanId);
    }

    public static ApplicationContext getContext() {
        return applicationContext;
    }
}
