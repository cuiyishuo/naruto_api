package com.solplatform.mapper;

import com.solplatform.entity.build.BuildCaseEntity;
import com.solplatform.entity.build.BuildTest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BuildMapper {
    // 新增构建任务
    void addBuildTest(BuildTest buildTest);

    // 根据id查询构建任务
    BuildTest findBuildTestById(String buildTestId);

    // 新增构建的case
    void addBuildCases(@Param ("buildCaseEntitys") List<BuildCaseEntity> buildCaseEntitys);
}
