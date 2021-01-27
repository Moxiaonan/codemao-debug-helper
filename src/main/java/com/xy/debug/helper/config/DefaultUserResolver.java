package com.xy.debug.helper.config;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

/**
 * TODO
 *
 * @author moxiaonan
 * @since 2021/1/26
 */
public class DefaultUserResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return findMethodAnnotation(AuthenticationPrincipal.class, parameter) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Class<?> clazz = parameter.getParameterType();
        Constructor<?> constructor = clazz.getConstructor(long.class, String.class);
        long defaultUserId = 1L;
        Object instance = constructor.newInstance(defaultUserId, "系统");
        return instance;
    }

    /**
     * Obtains the specified {@link Annotation} on the specified {@link MethodParameter}.
     *
     * @param annotationClass the class of the {@link Annotation} to find on the
     * {@link MethodParameter}
     * @param parameter the {@link MethodParameter} to search for an {@link Annotation}
     * @return the {@link Annotation} that was found or null.
     */
    private <T extends Annotation> T findMethodAnnotation(Class<T> annotationClass,
                                                          MethodParameter parameter) {
        T annotation = parameter.getParameterAnnotation(annotationClass);
        if (annotation != null) {
            return annotation;
        }
        Annotation[] annotationsToSearch = parameter.getParameterAnnotations();
        for (Annotation toSearch : annotationsToSearch) {
            annotation = AnnotationUtils.findAnnotation(toSearch.annotationType(),
                    annotationClass);
            if (annotation != null) {
                return annotation;
            }
        }
        return null;
    }
}
