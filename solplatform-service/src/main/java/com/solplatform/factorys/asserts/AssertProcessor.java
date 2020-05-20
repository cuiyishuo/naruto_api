package com.solplatform.factorys.asserts;

import com.solplatform.vo.BuildContent;
import org.springframework.stereotype.Component;

/**
 * 断言处理器
 *
 * @author sol
 * @create 2020-05-19  5:19 下午
 */
@Component
public interface AssertProcessor {
    // 断言构建接口中的用例,返回断言是否通过
    public void assertTest(BuildContent buildContent);
}
