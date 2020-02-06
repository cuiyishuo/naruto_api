package com.solplatform.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 配置拦截器
 *
 * @author sol
 * @create 2020-02-06  22:02
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    public void addInterceptors(InterceptorRegistry registry) {
        AuthInterceptor authInterceptor = new AuthInterceptor ();
        // 配置需要拦截的路径
        registry.addInterceptor (authInterceptor).addPathPatterns ("/**")
                .excludePathPatterns ("/main/login", "/main/signup");
        super.addInterceptors (registry);
    }
}
