package com.solplatform.factorys.component;

import com.solplatform.entity.HttpEntity;
import com.solplatform.vo.BuildContent;
import com.solplatform.vo.ResponseData;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author sol
 * @create 2020-05-08  10:24 上午
 */
@Component
public interface ComponentProcessor {
    // 运行构建返回测试结果
    public void runTest(BuildContent buildContent);
}
