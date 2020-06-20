package com.solplatform.factorys.asserts.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import com.solplatform.constants.BuildStatus;
import com.solplatform.entity.builds.BuildCaseEntity;
import com.solplatform.exception.BusinessException;
import com.solplatform.factorys.asserts.AssertFactory;
import com.solplatform.factorys.asserts.AssertProcessor;
import com.solplatform.service.AssertExpressionService;
import com.solplatform.util.LogInfoUtil;
import com.solplatform.util.ReportLogUtil;
import com.solplatform.vo.BuildContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 通过jsonpath断言
 *
 * @author sol
 * @create 2020-05-19  5:30 下午
 */
@Slf4j
@Component
public class JsonPathProcessor implements AssertProcessor {

    @Autowired
    AssertExpressionService assertExpressionService;

    @Override
    public void assertTest(BuildContent buildContent) {
        log.info ("进入方法【{}】", LogInfoUtil.getCurrentMethod ());
        ReportLogUtil reportLogUtil = buildContent.getReportLogUtil ();
        BuildCaseEntity buildCaseEntity = buildContent.getBuildCaseEntity ();
        List actualBody = buildCaseEntity.getResponseBody ();
        Map<String, String> actualHeader = buildCaseEntity.getResponseHeader ();
        List expectBodyExpression = buildCaseEntity.getAssertResbodyList ();
        List expectHeaderExpression = buildCaseEntity.getAssertHeaderList ();
        buildCaseEntity.setStatus (BuildStatus.PASS.name ());
        String actual;
        String expect;
        Iterator iterator = expectBodyExpression.iterator ();
        while (iterator.hasNext ()) {
            Object expressionObj = iterator.next ();
            log.info ("解析获取实际值的jsonpath表达式");
            String expression = JSONPath.eval (expressionObj, "$.expression").toString ();
            actual = JSONPath.eval (actualBody, expression).toString ();
            log.info ("解析出来的实际值是:{}", actual);
            expect = JSONPath.eval (expressionObj, "$.expectValue").toString ();
            log.info ("解析出来的期望值是:{}", expect);
            try {
                Assert.isTrue (actual.equalsIgnoreCase (expect), "结果与预期不符");
            } catch (IllegalArgumentException e) {
                buildCaseEntity.setStatus (BuildStatus.FAILED.name ());
                log.error ("测试用例[" + buildCaseEntity.getCaseName () + "]断言校验未通过");
                log.error (e.getMessage ());
                break;
            }
        }
    }
}
