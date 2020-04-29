package com.solplatform.mapper;

import com.solplatform.entity.HttpEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ComponentMapper {
    // 新增接口
    int addComponent(HttpEntity httpEntity);

    // 查询接口
    List<HttpEntity> findComponentBy(HttpEntity httpEntity);

    // 更新接口
    int updateComponent(HttpEntity httpEntity);

    // 通过接口id列表批量查询接口信息
    List<HttpEntity> findComponentByInterfaceIds(@Param ("interfaceids") List<String> interfaceIds);
}
