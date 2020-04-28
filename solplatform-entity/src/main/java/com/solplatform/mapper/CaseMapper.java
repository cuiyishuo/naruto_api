package com.solplatform.mapper;

import com.solplatform.entity.CaseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CaseMapper {
    // 新增测试用例
    void addCase(CaseEntity caseEntity);

    // 根据接口id查询用例
    List<CaseEntity> findCasebyInterfaceId(String interfaceId);

    // 更新测试用例
    int updateCase(CaseEntity caseEntity);

    // 根据caseId的list查询用例
    List<CaseEntity> findCaseByCaseIds(@Param("caseids")List<String> caseIds);
}
