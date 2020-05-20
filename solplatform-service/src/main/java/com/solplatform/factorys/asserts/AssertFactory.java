package com.solplatform.factorys.asserts;

import com.solplatform.factorys.asserts.impl.FullTextProcessor;
import com.solplatform.factorys.asserts.impl.JsonPathProcessor;
import com.solplatform.factorys.component.ComponentProcessor;
import com.solplatform.factorys.component.impl.DubboProcessor;
import com.solplatform.factorys.component.impl.HttpProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 断言工厂类
 *
 * @author sol
 * @create 2020-05-19  5:16 下午
 */
@Component
public class AssertFactory {
    @Autowired
    JsonPathProcessor jsonPathProcessor;
    @Autowired
    FullTextProcessor fullTextProcessor;

    public AssertProcessor getAssert(String assertType) {
        if ("jsonPath".equals (assertType)) {
            return jsonPathProcessor;
        } else if ("fullText".equals (assertType)) {
            return fullTextProcessor;
        } else {
            return null;
        }
    }
}
