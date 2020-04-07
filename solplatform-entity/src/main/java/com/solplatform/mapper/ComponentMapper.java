package com.solplatform.mapper;

import com.solplatform.entity.HttpEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ComponentMapper {
    // 新增接口
    int addComponent(HttpEntity httpEntity);

    // 查询接口
    List<HttpEntity> findComponentBy(HttpEntity httpEntity);
}
