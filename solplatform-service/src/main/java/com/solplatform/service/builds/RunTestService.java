package com.solplatform.service.builds;

import com.solplatform.entity.builds.BuildTestEntity;
import com.solplatform.mapper.BuildMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 执行测试用例业务
 *
 * @author sol
 * @create 2020-05-06  4:51 下午
 */
@Service
public class RunTestService {
    @Autowired
    BuildMapper buildMapper;

    /**
     * 执行构建任务
     *
     * @param buildTestId 构建任务的id
     */
    public BuildTestEntity runTest(String buildTestId) {
        // 1、根据构建id查询出接口和用例等信息
        BuildTestEntity buildTestEntity = buildMapper.findBuildTestById (buildTestId);
        System.out.println (buildTestEntity);
        return buildTestEntity;
    }
}
