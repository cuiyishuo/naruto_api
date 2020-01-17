package com.solplatform.service;

import com.solplatform.entity.UserEntity;
import com.solplatform.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

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
            throw new DuplicateKeyException ("主键重复异常");
        }
    }

    /**
     * 检查用户是否存在
     *
     * @param userEntity
     * @return
     */
    public boolean checkUser(UserEntity userEntity) {
        boolean isLogin;
        UserEntity userEntityDB = userMapper.checkUser (userEntity);
        // 判断是否可以查到用户信息
        if (userEntityDB == null) {
            isLogin = false;
        } else {
            isLogin = true;
        }
        return isLogin;
    }
}