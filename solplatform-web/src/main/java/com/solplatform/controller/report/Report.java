package com.solplatform.controller.report;

import com.solplatform.common.CommonResult;
import com.solplatform.entity.builds.BuildTestEntity;
import com.solplatform.service.UserService;
import com.solplatform.service.builds.BuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试报告
 *
 * @author sol
 * @create 2020-06-10  10:51 上午
 */
@RestController
@RequestMapping("/report")
public class Report {

    @Autowired
    BuildService buildService;
    @Autowired
    UserService userService;

    @GetMapping("/lastReport")
    public CommonResult getLastBuildTestResult() {
        String projectId = userService.getLastProjectId ();
        BuildTestEntity buildTestEntity = buildService.getLastBuildTest (projectId);
        return CommonResult.success (buildTestEntity);
    }
}
