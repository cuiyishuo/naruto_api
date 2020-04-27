package com.solplatform.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.solplatform.entity.CaseEntity;
import com.solplatform.entity.HttpEntity;
import com.solplatform.exception.BusinessException;
import com.solplatform.mapper.CaseMapper;
import com.solplatform.mapper.ComponentMapper;
import com.solplatform.vo.TablePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Autowired
    ComponentMapper componentMapper;

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
    public TablePage findCaseByInterfaceId(Integer pageNo, Integer pageSize, String interfaceId) {
        try {
            // 分页查询
            Page page = PageHelper.startPage (pageNo, pageSize, true);
            List<CaseEntity> caseList = caseMapper.findCasebyInterfaceId (interfaceId);
            // 获得分页总数据
            Long total = page.getTotal ();
            TablePage tablePage = new TablePage ();
            // 将数据总是和数据存入分页对象
            tablePage.setTotal (total);
            tablePage.setCurrentPageData (caseList);
            return tablePage;
        } catch (Exception e) {
            e.printStackTrace ();
            System.err.println (e.getMessage ());
            throw new BusinessException ("查询测试用例异常");
        }
    }

    /**
     * 分别更新case和该case中的http
     * @param caseEntity
     * @param httpEntity
     */
    @Transactional
    public void updateCase(CaseEntity caseEntity, HttpEntity httpEntity) {
        try {
            // 更新用例和接口表数据
            caseMapper.updateCase (caseEntity);
            componentMapper.updateComponent (httpEntity);
        } catch (Exception e) {
            e.printStackTrace ();
            System.err.println (e.getMessage ());
            throw new BusinessException ("更新用例异常");
        }
    }

}
