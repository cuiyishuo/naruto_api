package com.solplatform.mapper;

import com.solplatform.entity.ProjectEntity;
import com.solplatform.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ProjectMapper {
    // 新增项目
    int addProject(ProjectEntity projectEntity);

    // 更新项目
    int modifyProject(ProjectEntity projectEntity);

    // 获取项目列表
    List<ProjectEntity> getProjectList(String userId);

    // 获取项目成员
    List<UserEntity> getProjectMemberList(String projectId);

    // 删除项目
    @Update ("update project set yn=-1 where id=#{0}")
    int deleteProject(String projectId);
}
