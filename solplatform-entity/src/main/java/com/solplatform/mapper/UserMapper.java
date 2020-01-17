package com.solplatform.mapper;

import com.solplatform.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserMapper {
    // 新增用户
    int addUser(UserEntity userEntity);

    // 查询用户
    List<UserEntity> selectUser();

    UserEntity selectUserBy();

    // 检查用户是否存在
    UserEntity checkUser(UserEntity userEntity);
}
