package com.solplatform.service;

import com.alibaba.fastjson.JSONPath;
import com.solplatform.exception.BusinessException;
import com.solplatform.vo.ResponseData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

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
        } catch (NullPointerException e) {
            e.printStackTrace ();
            System.err.println (e.getMessage ());
            throw new BusinessException ("输入的表达式有误，请重新输入");
        }
        return result;
    }
}
