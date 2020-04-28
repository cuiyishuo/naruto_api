package com.solplatform.service;

import com.solplatform.entity.BaseEntity;
import com.solplatform.entity.CaseEntity;
import com.solplatform.entity.build.BuildCaseEntity;
import com.solplatform.mapper.BuildMapper;
import com.solplatform.mapper.CaseMapper;
import com.solplatform.util.GenerateId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 构建任务业务
 *
 * @author sol
 * @create 2020-04-28  16:08
 */
@Service
public class BuildService {
    @Autowired
    CaseMapper caseMapper;
    @Autowired
    BuildMapper buildMapper;

    public void addBuildTestForCase(List<String> caseIds) {
        // 1
        // 1.1、通过接口id的list查出所有case数据
        List<CaseEntity> caseEntities = caseMapper.findCaseByCaseIds (caseIds);
        // 1.2、将取出的caseEntity属性copy到buildCaseEntity中
        List<BuildCaseEntity> buildCaseEntities = this.copyProperties (caseEntities);
        System.out.println ("OK");
        // 1.3、将
    }

    /**
     * 遍历case复制属性返回buildCase集合，并存入build_case表中
     *
     * @param caseEntities
     * @return
     */
    public List<BuildCaseEntity> copyProperties(List<CaseEntity> caseEntities) {
        List<BuildCaseEntity> buildCaseEntities = new ArrayList<BuildCaseEntity> ();
        Iterator<CaseEntity> it = caseEntities.iterator ();
        while (it.hasNext ()) {
            BuildCaseEntity buildCaseEntity = new BuildCaseEntity ();
            // copy属性 (第三个参数为不复制哪个属性）
            BeanUtils.copyProperties (it.next (), buildCaseEntity, new String[]{"id"});
            String buildCaseId = GenerateId.getUUID32 ();
            buildCaseEntity.setId (buildCaseId);
            buildCaseEntities.add (buildCaseEntity);
        }
        // 批量插入build_case数据
        buildMapper.addBuildCases (buildCaseEntities);
        return buildCaseEntities;
    }
}
