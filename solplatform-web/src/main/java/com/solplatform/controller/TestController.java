package com.solplatform.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 *
 * @author sol
 * @create 2019-12-10  10:50
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String a() {
        return "hello springBoot";
    }

}
