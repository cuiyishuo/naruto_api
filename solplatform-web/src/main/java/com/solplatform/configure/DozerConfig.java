package com.solplatform.configure;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * dozer配置
 *
 * @author sol
 * @create 2020-02-18  23:50
 */
@Configuration
public class DozerConfig {
    @Bean
    public DozerBeanMapper mapper() {
        // 这里没有加载"classpath:dozer-mappings/*.xml"
        DozerBeanMapper mapper = new DozerBeanMapper();
        return mapper;
    }
}
