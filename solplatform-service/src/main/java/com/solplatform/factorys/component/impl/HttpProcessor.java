package com.solplatform.factorys.component.impl;

import com.solplatform.entity.HttpEntity;
import com.solplatform.factorys.component.ComponentProcessor;
import com.solplatform.service.UtilService;
import com.solplatform.util.DateUtil;
import com.solplatform.util.LogInfoUtil;
import com.solplatform.vo.BuildContent;
import com.solplatform.vo.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.LocalDateTime;
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
    public void runTest(BuildContent buildContent) {
        LocalDateTime startAt = null;
        LocalDateTime endAt = null;
        Duration duration = null;
        ResponseData responseData = new ResponseData ();
        try {
            log.info ("进入方法【{}】，执行http接口测试", LogInfoUtil.getCurrentMethod ());
            HttpEntity httpEntity = buildContent.getBuildCaseEntity ().getHttpEntity ();
            String method = httpEntity.getMethods ();
            if ("get".equals (method)) {
                log.info ("执行get请求");
                log.info ("计算case执行时长");
                startAt = DateUtil.getCurrentDate ();
                responseData = utilService.invokeGet (httpEntity);
                endAt = DateUtil.getCurrentDate ();
                duration = Duration.between (startAt, endAt);
            } else if ("post".equals (method)) {
                log.info ("执行post请求");
                log.info ("计算case执行时长");
                responseData = utilService.invokePost (httpEntity);
            }
            log.info ("提取响应信息");
            Map headerMap = responseData.getHeaders ();
            List responseBody = responseData.getBody ();
            buildContent.getBuildCaseEntity ().setResponseHeader (headerMap);
            buildContent.getBuildCaseEntity ().setResponseBody (responseBody);
            buildContent.getBuildCaseEntity ().setStartAt (startAt.toString ());
            buildContent.getBuildCaseEntity ().setEndAt (endAt.toString ());
            buildContent.getBuildCaseEntity ().setDuration (String.valueOf (duration.toMillis ()));
        } catch (Exception e) {
            log.error ("执行http接口异常", e);
        }
    }
}
