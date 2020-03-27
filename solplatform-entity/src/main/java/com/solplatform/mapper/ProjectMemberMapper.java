package com.solplatform.mapper;

import com.solplatform.entity.ProjectMemberEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectMemberMapper {
    // 新增项目成员
    int addProjectMember(ProjectMemberEntity projectMemberEntity);

}
