package com.solplatform.mapper;

import com.solplatform.entity.CaseEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CaseMapper {
    // 新增测试用例
    public void addCase(CaseEntity caseEntity);

    // 根据接口id查询用例
    List<CaseEntity> findCasebyInterfaceId(String interfaceId);
}
