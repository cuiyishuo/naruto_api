package com.solplatform.mapper;

import com.solplatform.entity.CaseEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CaseMapper {
    // 新增测试用例
    public void addCase(CaseEntity caseEntity);
}
