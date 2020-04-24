package com.solplatform.service;

import com.solplatform.entity.CaseEntity;
import com.solplatform.exception.BusinessException;
import com.solplatform.mapper.CaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 用例相关业务
 *
 * @author sol
 * @create 2020-04-16  11:43
 */
@Service
public class CaseService {
    @Autowired
    CaseMapper caseMapper;

    /**
     * 新增测试用例
     *
     * @param caseEntity
     */
    public void addCase(CaseEntity caseEntity) {
        try {
            caseMapper.addCase (caseEntity);
        } catch (Exception e) {
            e.printStackTrace ();
            System.err.println (e.getMessage ());
            throw new BusinessException ("新增测试用例异常");
        }
    }

    /**
     * 通过所属接口id查询测试用例
     *
     * @param interfaceId
     */
    public List<CaseEntity> findCaseByInterfaceId(String interfaceId) {
        try {
            List<CaseEntity> caseList = caseMapper.findCasebyInterfaceId (interfaceId);
            return caseList;
        } catch (Exception e) {
            e.printStackTrace ();
            System.err.println (e.getMessage ());
            throw new BusinessException ("查询测试用例异常");
        }
    }

}
