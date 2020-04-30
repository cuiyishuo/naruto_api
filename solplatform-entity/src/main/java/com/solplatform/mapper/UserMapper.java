package com.solplatform.mapper;

import com.solplatform.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface UserMapper {
    // 新增用户
    int addUser(UserEntity userEntity);

    // 查找当前用户的lastProjectId
    UserEntity selectLastProjectId(String userId);

    // 检查用户是否存在
    UserEntity checkUser(UserEntity userEntity);

    // 修改用户信息
    int modifyLastProjectId(UserEntity userEntity);

    // 通过userId查找用户
    UserEntity findUserById(@Param("userid") String id);
}
