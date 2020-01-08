package com.solplatform.service;

import com.solplatform.entity.UserEntity;
import com.solplatform.mapper.UserMapper;
import com.solplatform.vo.ResponseMessage;
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
    public ResponseMessage registertUser(UserEntity userEntity) {
        try {
            int i = userMapper.addUser (userEntity);
            // insert成功会返回成功条数，用来判断是否注册成功
            if (i == 1) {
                return ResponseMessage.successResponse ();
            } else {
                return ResponseMessage.errorResponse ("注册异常", 400);
            }
        } catch (DuplicateKeyException e) {
            log.error ("输入的用户名已被注册");
            return ResponseMessage.errorResponse ("用户名已被注册", 400);
        } catch (Exception e) {
            log.error ("注册失败", e);
            return ResponseMessage.errorResponse ("注册异常", 400);
        }
    }
}
