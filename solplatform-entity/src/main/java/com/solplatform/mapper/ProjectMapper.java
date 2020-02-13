package com.solplatform.mapper;

import com.solplatform.entity.ProjectEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {
    // 新增项目
    int addProject(ProjectEntity projectEntity);

    // 更新项目
    int modifyProject(ProjectEntity projectEntity);

    List<ProjectEntity> getProjectList(String userId);
}
