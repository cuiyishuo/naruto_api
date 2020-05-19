package com.solplatform.factorys.component.impl;

import com.solplatform.entity.HttpEntity;
import com.solplatform.factorys.component.ComponentProcessor;
import com.solplatform.service.UtilService;
import com.solplatform.vo.BuildContent;
import com.solplatform.vo.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * @author sol
 * @create 2020-05-08  10:26 上午
 */
@Slf4j
@Component
public class HttpProcessor implements ComponentProcessor {

    @Autowired
    UtilService utilService;

    @Override
    public ResponseData runTest(BuildContent buildContent) {
        try {
            log.info ("执行http组件测试：【{}】", this.getClass ().getName ());
            ResponseData responseData = new ResponseData ();
            HttpEntity httpEntity = buildContent.getBuildCaseEntity ().getHttpEntity ();
            String method = httpEntity.getMethods ();
            if ("get".equals (method)) {
                log.info ("执行get请求");
                responseData = utilService.invokeGet (httpEntity);
            } else if ("post".equals (method)) {
                log.info ("执行post请求");
                responseData = utilService.invokePost (httpEntity);
            }
            log.info ("提取响应信息");
            Map<String, String> headerMap = responseData.getHeaders ();
            List responsBody = responseData.getBody ();
            buildContent.getBuildCaseEntity ().setResponseHeader (headerMap);
            buildContent.getBuildCaseEntity ().setResponseBody (responsBody);
            return responseData;
        } catch (Exception e) {
            log.error ("执行http接口异常", e);
        }
        return null;
    }
}
