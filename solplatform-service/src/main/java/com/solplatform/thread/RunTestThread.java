package com.solplatform.thread;

import com.solplatform.constants.RunMode;
import com.solplatform.entity.builds.BuildTestEntity;
import com.solplatform.service.builds.RunTestService;
import com.solplatform.util.LogInfoUtil;
import com.solplatform.vo.BuildContent;
import lombok.extern.slf4j.Slf4j;

/**
 * 运行测试线程任务
 *
 * @author sol
 * @create 2020-06-04  5:47 下午
 */
@Slf4j
public class RunTestThread implements Runnable {
    private String buildTestId;
    private RunTestService runTestService;

    public RunTestThread(String buildTestId, RunTestService runTestService) {
        this.buildTestId = buildTestId;
        this.runTestService = runTestService;
    }

    @Override
    public void run() {
        BuildContent buildContent = new BuildContent ();
        BuildTestEntity buildTestEntity = new BuildTestEntity ();
        buildTestEntity.setId (buildTestId);
        buildContent.setBuildTestEntity (buildTestEntity);
        runTestService.runTest (buildContent);
    }
}
