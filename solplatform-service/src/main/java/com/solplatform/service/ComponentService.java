package com.solplatform.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.solplatform.entity.HttpEntity;
import com.solplatform.entity.UserEntity;
import com.solplatform.exception.BusinessException;
import com.solplatform.mapper.ComponentMapper;
import com.solplatform.mapper.UserMapper;
import com.solplatform.util.SessionUtil;
import com.solplatform.vo.TablePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 组件业务
 *
 * @author sol
 * @create 2020-04-03  11:27
 */
@Service
public class ComponentService {
    @Autowired
    ComponentMapper componentMapper;
    @Autowired
    UserMapper userMapper;

    /**
     * 新增组件
     *
     * @param httpEntity
     */
    public void createComponent(HttpEntity httpEntity) {
        try {
            // 获取projectId
            String userId = SessionUtil.getSession ("userId");
            UserEntity resultUser = userMapper.selectLastProjectId (userId);
            String projectId = resultUser.getLastProjectId ();
            httpEntity.setProjectId (projectId);
            componentMapper.addComponent (httpEntity);
        } catch (Exception e) {
            e.printStackTrace ();
            System.err.println (e.getMessage ());
            throw new BusinessException ("新增组件异常");
        }
    }

    /**
     * 查询组件
     *
     * @param pageNo
     * @param pageSize
     * @param httpEntity
     * @return
     */
    public TablePage getComponetList(Integer pageNo, Integer pageSize, HttpEntity httpEntity) {
        try {
            // 分页查询
            Page page = PageHelper.startPage (pageNo, pageSize, true);
            List<HttpEntity> componentList = componentMapper.findComponentBy (httpEntity);
            // 获得分页总数据
            Long total = page.getTotal ();
            TablePage tablePage = new TablePage ();
            tablePage.setTotal (total);
            tablePage.setCurrentPageData (componentList);
            return tablePage;
        } catch (Exception e) {
            throw new BusinessException ("查询组件异常");
        }
    }

    /**
     * 更新组件
     *
     * @param httpEntity
     */
    public void updateCOmponent(HttpEntity httpEntity) {
        try {
            componentMapper.updateComponent (httpEntity);
        } catch (Exception e) {
            throw new BusinessException ("更新组件异常");
        }
    }
}
