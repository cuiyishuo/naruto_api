package com.solplatform.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.solplatform.entity.ProjectEntity;
import com.solplatform.entity.ProjectMemberEntity;
import com.solplatform.entity.UserEntity;
import com.solplatform.mapper.ProjectMapper;
import com.solplatform.mapper.ProjectMemberMapper;
import com.solplatform.util.SessionUtil;
import com.solplatform.vo.TablePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 新增项目
     *
     * @param projectEntity
     */
    public void createProject(ProjectEntity projectEntity) {
        try {
            projectMapper.addProject (projectEntity);

            // 新建项目成功后将创建项目id加入到项目成员中
            ProjectMemberEntity projectMemberEntity = new ProjectMemberEntity ();
            projectMemberEntity.setProjectId (projectEntity.getId ());
            // 获取userId
            String userId = SessionUtil.getSession ("userId");
            System.err.println ("获取到session中的userId为：" + userId);
            // 新建项目成功后将创建人加入到项目成员中
            projectMemberEntity.setUserId (userId);
            projectMemberMapper.addProjectMember (projectMemberEntity);

        } catch (DuplicateKeyException e) {
            throw new DuplicateKeyException ("项目名称已存在");
        }
    }

    /**
     * 修改项目
     *
     * @param projectEntity
     */
    public void modifyProject(ProjectEntity projectEntity) {
        try {
            projectMapper.modifyProject (projectEntity);
        } catch (DuplicateKeyException e) {
            throw new DuplicateKeyException ("项目名称已存在");
        }
    }

    /**
     * 获得项目列表
     *
     * @param pageNo   页码
     * @param pageSize 每页显示条数
     * @return
     */
    public TablePage getProjectList(Integer pageNo, Integer pageSize) {

        // 获取userId
        String userId = SessionUtil.getSession ("userId");
        // 分页查询
        Page page = PageHelper.startPage (pageNo, pageSize, true);
        List<ProjectEntity> projectList = projectMapper.getProjectList (userId);
        // 获得分页总数据
        Long total = page.getTotal ();
        TablePage tablePage = new TablePage ();
        tablePage.setTotal (total);
        tablePage.setCurrentPageData (projectList);
        return tablePage;

    }

    /**
     * 新增项目成员
     *
     * @param projectId
     */
    public void addProjectMember(String projectId, String userId) {

        ProjectMemberEntity projectMemberEntity = new ProjectMemberEntity ();
        projectMemberEntity.setProjectId (projectId);
        projectMemberEntity.setUserId (userId);
        projectMemberMapper.addProjectMember (projectMemberEntity);

    }

    /**
     * 获得项目成员列表
     *
     * @param pageNo
     * @param pageSize
     * @param projectId 项目id
     * @return
     */
    public TablePage getProjectMember(Integer pageNo, Integer pageSize, String projectId) {

        // 分页查询
        Page page = PageHelper.startPage (pageNo, pageSize, true);
        List<UserEntity> userList = projectMapper.getProjectMemberList (projectId);
        // 获得分页总数据
        Long total = page.getTotal ();
        TablePage tablePage = new TablePage ();
        tablePage.setTotal (total);
        tablePage.setCurrentPageData (userList);
        return tablePage;

    }
}
