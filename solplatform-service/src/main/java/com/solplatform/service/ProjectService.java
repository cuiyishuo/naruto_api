package com.solplatform.service;

import com.solplatform.entity.ProjectEntity;
import com.solplatform.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

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

    public void createProject(ProjectEntity projectEntity) {
        try {
            projectMapper.addProject (projectEntity);
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
