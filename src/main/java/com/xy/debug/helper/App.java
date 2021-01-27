package com.xy.debug.helper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * TODO
 *
 * @author moxiaonan
 * @since 2021/1/26
 */
@SpringBootApplication
//@EnableWebSecurity
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
