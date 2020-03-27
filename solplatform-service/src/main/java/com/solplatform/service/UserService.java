package com.solplatform.service;

import com.solplatform.entity.ProjectEntity;
import com.solplatform.entity.ProjectMemberEntity;
import com.solplatform.entity.UserEntity;
import com.solplatform.exception.BusinessException;
import com.solplatform.mapper.ProjectMemberMapper;
import com.solplatform.mapper.UserMapper;
import com.solplatform.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * 用户相关业务
 *
 * @author sol
 * @create 2020-01-04  23:38
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 注册用户
     *
     * @param userEntity
     */
    public void registertUser(UserEntity userEntity) {
        try {
            userMapper.addUser (userEntity);
        } catch (DuplicateKeyException e) {
            throw new DuplicateKeyException ("该用户名已被注册");
        }
    }

    /**
     * 检查用户是否存在
     *
     * @param userEntity
     * @return
     */
    public UserEntity checkUser(UserEntity userEntity) {
        UserEntity userEntityDB = userMapper.checkUser (userEntity);
        // 判断是否可以查到用户信息,查到了则返回用户id
        if (userEntityDB == null) {
            return null;
        } else {
            return userEntityDB;
        }
    }

    /**
     * 修改用户最后登录项目
     *
     * @param userEntity
     */
    public void modifyUser(UserEntity userEntity) {
        try {
            // 获取userId
            String userId = SessionUtil.getSession ("userId");
            userEntity.setId (userId);
            userMapper.modifyLastProjectId (userEntity);
        } catch (BusinessException e) {
            throw new BusinessException ("修改用户lastProjectId异常");
        }
    }

    /**
     * 获取lastProjectId
     *
     * @return
     */
    public String getLastProjectId() {
        try {
            // 获取userId
            String userId = SessionUtil.getSession ("userId");
            UserEntity resultUser = userMapper.selectLastProjectId (userId);
            return resultUser.getLastProjectId ();
        } catch (BusinessException e) {
            throw new BusinessException ("获取lastProjectId异常");
        }
    }
}