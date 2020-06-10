package com.solplatform.controller.testbuild;

import com.solplatform.common.CommonResult;
import com.solplatform.entity.builds.BuildTestEntity;
import com.solplatform.service.builds.RunTestService;
import com.solplatform.thread.RunTestThread;
import com.solplatform.vo.BuildContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    @Resource(name="runTestThreadPool")
    ThreadPoolTaskExecutor runTestExecutor;

    /**
     * 运行用例级别的测试
     *
     * @param buildTestId
     * @return
     */
    @GetMapping("/build/{buildTestId}")
    public CommonResult runTestByCase(@PathVariable String buildTestId) {
        // 因为是new出来的对象，所以无法在期内部使用autowired，所以通过构造函数传入
        RunTestThread runTestThread = new RunTestThread (buildTestId,runTestService);
        runTestExecutor.execute (runTestThread);
        return CommonResult.success ();
    }
}
