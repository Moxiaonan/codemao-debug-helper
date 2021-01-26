package com.xy.debug.helper;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 调试帮助工具自动注入类
 *
 * @author moxiaonan
 * @since 2021/1/26
 */
@Configuration
@ComponentScan(basePackageClasses = DebugHelperAutoConfiguration.class)
public class DebugHelperAutoConfiguration {
}
