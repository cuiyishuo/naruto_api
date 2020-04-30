package com.solplatform.controller.testbuild;

import com.solplatform.common.CommonResult;
import com.solplatform.service.BuildService;
import com.solplatform.vo.component.BuildArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 构建测试
 *
 * @author sol
 * @create 2020-04-27  18:58
 */
@RestController
@RequestMapping("/build")
public class BuildController {
    @Autowired
    BuildService buildService;

    @PostMapping("/cases")
    public CommonResult buildCase(@RequestBody BuildArray buildArray) {
        List<String> caseIds = new ArrayList<String> (Arrays.asList (buildArray.getCaseIds ()));
        buildService.addBuildTestForCase (caseIds);
        return CommonResult.success (caseIds);
    }
}
