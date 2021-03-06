package com.solplatform.mapper;

import com.solplatform.entity.builds.BuildCaseEntity;
import com.solplatform.entity.builds.BuildInterfaceEntity;
import com.solplatform.entity.builds.BuildTestEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BuildMapper {

    // 批量新增构建的cases
    void addBuildCases(@Param ("buildCaseEntitys") List<BuildCaseEntity> buildCaseEntitys);

    // 批量新增构建的interfaces
    void addBuildInterfaces(@Param("buildInterfaceEntitys") List<BuildInterfaceEntity> buildInterfaceEntities);

    // 根据构建id查询构建接口
    List<BuildInterfaceEntity> findBuildInterfaceByBuildTestId(String buildTestId);

    // 根据接口id查询构建用例
    List<BuildCaseEntity> findBuildCaseByInterfaceId(String InterfaceId);

    // 更新构建的测试用例
    void updateBuildCase(BuildCaseEntity buildCaseEntity);

    // 更新构建的接口
    void updateBuildInterface(BuildInterfaceEntity buildInterfaceEntity);
}
