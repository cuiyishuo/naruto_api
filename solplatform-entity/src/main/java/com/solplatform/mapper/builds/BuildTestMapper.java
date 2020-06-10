package com.solplatform.mapper.builds;

import com.solplatform.entity.builds.BuildTestEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 构建测试
 *
 * @author sol
 * @create 2020-06-10  10:07 上午
 */
@Mapper
public interface BuildTestMapper {
    // 新增构建任务
    void addBuildTest(BuildTestEntity buildTestEntity);

    // 根据id查询构建任务
    BuildTestEntity findBuildTestById(String id);

    // 更新构建的任务
    void updateBuildTest(BuildTestEntity buildTestEntity);

    // 根据projectId查询最后一次构建的测试任务
    BuildTestEntity findLastBuildTestByProjectId(String projectId);
}
