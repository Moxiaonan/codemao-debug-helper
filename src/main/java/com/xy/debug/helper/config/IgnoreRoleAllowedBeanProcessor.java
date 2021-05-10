package com.xy.debug.helper.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;

/**
 * 开发环境bean处理器
 *
 * @author moxiaonan
 * @since 2021/1/22
 */
@Component
@Slf4j
public class IgnoreRoleAllowedBeanProcessor implements BeanPostProcessor, EnvironmentAware {
    public static final String SKIP_AUTH_KEY = "SKIP_AUTH";
    private Environment environment;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Boolean skipAuth = Optional.ofNullable(environment.getProperty(SKIP_AUTH_KEY, Boolean.class))
                .orElse(Boolean.TRUE);
        if (Boolean.TRUE.equals(skipAuth)) {
            // 移除 @RolesAllowed 标签
            Method[] declaredMethods = bean.getClass().getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                try {
                    declaredMethod.getDeclaredAnnotations();
                    Field declaredAnnotationsField = declaredMethod.getClass().getSuperclass().getDeclaredField("declaredAnnotations");
                    declaredAnnotationsField.setAccessible(true);
                    Map<Class<? extends Annotation>, Annotation> annotationMap = (Map<Class<? extends Annotation>, Annotation>) declaredAnnotationsField.get(declaredMethod);
                    if (annotationMap != null) {
                        annotationMap.remove(RolesAllowed.class);
                    }
                } catch (NoSuchFieldException e) {
                    log.warn("开发BeanPostProcessor 无字段错误",e);
                } catch (IllegalAccessException e) {
                    log.warn("开发BeanPostProcessor 非法操作错误",e);
                }
            }
        }
        return bean;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
