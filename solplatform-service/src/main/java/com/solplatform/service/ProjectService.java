package com.solplatform.service;

import com.solplatform.entity.ProjectEntity;
import com.solplatform.entity.ProjectMemberEntity;
import com.solplatform.mapper.ProjectMapper;
import com.solplatform.mapper.ProjectMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 项目管理业务
 *
 * @author sol
 * @create 2020-02-03  00:35
 */
@Service
public class ProjectService {

    @Autowired
    ProjectMapper projectMapper;
    @Autowired
    ProjectMemberMapper projectMemberMapper;

    public void createProject(ProjectEntity projectEntity) {
        try {
            projectMapper.addProject (projectEntity);

            // 新建项目成功后将创建项目id加入到项目成员中
            ProjectMemberEntity projectMemberEntity = new ProjectMemberEntity ();
            projectMemberEntity.setProjectId (projectEntity.getId ());
            // 获取userId
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes ();
            HttpServletRequest request = requestAttributes.getRequest ();
            String userId = (String) request.getSession ().getAttribute ("userId");
            System.err.println ("获取到session中的userId为：" + userId);
            // 新建项目成功后将创建人加入到项目成员中
            projectMemberEntity.setUserId (userId);
            projectMemberMapper.addProjectMember (projectMemberEntity);

        } catch (DuplicateKeyException e) {
            throw new DuplicateKeyException ("项目名称已存在");
        }
    }

    public void modifyProject(ProjectEntity projectEntity) {
        try {
            projectMapper.modifyProject (projectEntity);
        } catch (DuplicateKeyException e) {
            throw new DuplicateKeyException ("项目名称已存在");
        }
    }
}
