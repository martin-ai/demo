package com.example.demo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringApplicationContext implements ApplicationContextAware {

    private static ApplicationContext ctx;

    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        ctx = applicationContext;
    }

    public static <T> T getBean(String beanName) {
        return (T) ctx.getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class<T> type) {
        return (T) ctx.getBean(beanName, type);
    }

    public static <T> T getBean(Class<T> type) {
        return (T) ctx.getBean(type);
    }

    public static boolean containsBean(Class clazz) {
        return ctx.containsBean(StringUtils.uncapitalize(clazz.getSimpleName()));
    }

    public static ApplicationContext getApplicationContext() {
        return ctx;
    }

}
