package com.solplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot启动类
 *
 * @author sol
 * @create 2019-12-09  18:15
 */
@SpringBootApplication
public class SpringbootApplication {
    /**
     * 固定写法,springboot 是通过main方法去启动的
     * 通过 @SpringBootApplication注解标记当前类是整个工程的启动类
     * 注解被识别的前提是，拥有注解的类必须在SpringbootApplication的同级别或者子包下面，才能被扫描到
     **/
    public static void main(String[] args) {

        SpringApplication.run(SpringbootApplication.class, args);

    }
}
