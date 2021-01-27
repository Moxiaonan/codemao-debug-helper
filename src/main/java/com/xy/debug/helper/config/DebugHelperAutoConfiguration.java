package com.xy.debug.helper.config;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
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
@AutoConfigureBefore({SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class, SecurityFilterAutoConfiguration.class})
public class DebugHelperAutoConfiguration {
}
