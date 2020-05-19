package com.solplatform.factorys.component.impl;

import com.solplatform.factorys.component.ComponentProcessor;
import com.solplatform.vo.BuildContent;
import com.solplatform.vo.ResponseData;
import org.springframework.stereotype.Component;

/**
 * @author sol
 * @create 2020-05-08  10:33 上午
 */
@Component
public class DubboProcessor implements ComponentProcessor {
    @Override
    public ResponseData runTest(BuildContent buildContent) {
        System.out.println ("dubbo 模式");
        return null;
    }
}
