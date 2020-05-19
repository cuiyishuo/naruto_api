package com.solplatform.controller.testbuild;

import com.solplatform.common.CommonResult;
import com.solplatform.entity.builds.BuildTestEntity;
import com.solplatform.service.builds.RunTestService;
import com.solplatform.vo.BuildContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 执行测试接口
 *
 * @author sol
 * @create 2020-05-06  5:15 下午
 */
@RestController
@RequestMapping("/run")
public class RunTestController {
    @Autowired
    RunTestService runTestService;

    /**
     * 运行用例级别的测试
     *
     * @param buildTestId
     * @return
     */
    @GetMapping("/build/{buildTestId}")
    public CommonResult runTestByCase(@PathVariable String buildTestId) {
        BuildContent buildContent = new BuildContent ();
        BuildTestEntity buildTestEntity = new BuildTestEntity ();
        buildTestEntity.setId (buildTestId);
        buildContent.setBuildTestEntity (buildTestEntity);
        buildTestEntity = runTestService.runTest (buildContent);
        return CommonResult.success (buildTestEntity);
    }
}
