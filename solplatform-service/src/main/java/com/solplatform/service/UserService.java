package com.solplatform.service;

import com.solplatform.entity.UserEntity;
import com.solplatform.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * 用户相关业务
 *
 * @author sol
 * @create 2020-01-04  23:38
 */

@Slf4j
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 注册用户
     *
     * @param userEntity
     * @return
     */
    public int registertUser(UserEntity userEntity) {
        int i = 1;
        try {
            userMapper.addUser (userEntity);
        } catch (DuplicateKeyException e) {
            log.error ("输入的用户名已被注册");
            return -1;
        } catch (Exception e) {
            log.error ("注册失败",e);
        }
        return i;
    }
}
