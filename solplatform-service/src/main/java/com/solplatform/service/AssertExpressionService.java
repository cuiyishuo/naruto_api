package com.solplatform.service;

import com.alibaba.fastjson.JSONPath;
import com.solplatform.exception.BusinessException;
import org.springframework.stereotype.Component;

/**
 * 断言业务
 *
 * @author sol
 * @create 2020-04-24  14:37
 */
@Component
public class AssertExpressionService {
    /**
     * 测试jsonpath表达式
     *
     * @param exprsstionObj
     * @return
     */
    public String checkJsonPath(Object exprsstionObj, Object responseData) {
        String result = "";
        try {
            // 将列表数据[{}],通过jsonpath表达式取出
            String expression = JSONPath.eval (exprsstionObj, "$.expression").toString ();
            System.out.println ("解析出来的value：" + expression);
            // 通过断言表达式解析响应题内的信息
            result = JSONPath.eval (responseData, expression).toString ();
            System.out.println (result);
            if ("[]".equalsIgnoreCase (result)) {
                throw new BusinessException ("表达式格式不正确，请检查表达式");
            }
        } catch (NullPointerException e) {
            e.printStackTrace ();
            System.err.println (e.getMessage ());
            throw new BusinessException ("未找到指定元素，请检查表达式");
        }
        return result;
    }
}
