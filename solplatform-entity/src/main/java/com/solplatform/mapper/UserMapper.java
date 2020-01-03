package com.solplatform.mapper;

import com.solplatform.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    // 新增用户
    public int addUser(UserEntity userEntity);
}
