package com.xy.debug.helper.controller;

import com.xy.debug.helper.entity.UserInfo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author moxiaonan
 * @since 2021/1/26
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping
    public void user(@AuthenticationPrincipal UserInfo userInfo){
        System.out.println(userInfo);
    }
}
