package com.xy.debug.helper.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * TODO
 *
 * @author moxiaonan
 * @since 2021/1/26
 */
@Configuration
public class DebugWebMvcConfigure implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new DefaultUserResolver());
    }
}
