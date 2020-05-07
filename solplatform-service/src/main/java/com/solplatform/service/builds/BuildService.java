package com.solplatform.service.builds;

import com.solplatform.constants.BuildStatus;
import com.solplatform.constants.RunMode;
import com.solplatform.entity.CaseEntity;
import com.solplatform.entity.HttpEntity;
import com.solplatform.entity.builds.BuildCaseEntity;
import com.solplatform.entity.builds.BuildInterfaceEntity;
import com.solplatform.entity.builds.BuildTestEntity;
import com.solplatform.mapper.BuildMapper;
import com.solplatform.mapper.CaseMapper;
import com.solplatform.mapper.ComponentMapper;
import com.solplatform.mapper.UserMapper;
import com.solplatform.util.DateUtil;
import com.solplatform.util.GenerateId;
import com.solplatform.util.SessionUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    ComponentMapper componentMapper;
    @Autowired
    BuildMapper buildMapper;
    @Autowired
    UserMapper userMapper;

    /**
     * 通过某一个接口下的用例，创建测试构建任务，并返回构建任务id
     *
     * @param caseIds 某个接口下的用例id集合
     */
    @Transactional
    public String addBuildTestForCase(List<String> caseIds) {
        // 1、处理用例
        // 1.1、通过接口id的list查出所有case数据
        List<CaseEntity> caseEntities = caseMapper.findCaseByCaseIds (caseIds);
        // 1.2、将取出的caseEntity属性copy到buildCaseEntity中
        List<BuildCaseEntity> buildCaseEntities = this.copyPropertiesForCase (caseEntities);
        // 2、处理接口
        // 2.1、查询出该批用例所属的接口interfaceId，然后查询接口信息
        String interfaceId;
        interfaceId = buildCaseEntities.get (0).getInterfaceId ();
        List<String> interfaceIds = new ArrayList<String> ();
        interfaceIds.add (interfaceId);
        List<HttpEntity> httpEntities = componentMapper.findComponentByInterfaceIds (interfaceIds);
        // 2.2、将取出的interfaceEntity属性copy到buildInterfaceEntity中
        List<BuildInterfaceEntity> buildInterfaceEntities = this.copyPropertiesForInterface (httpEntities);
        String buildInterfaceId = buildInterfaceEntities.get (0).getId ();
        // 3、再处理用例，将buildCase中的interfaceId，替换为新的buildInterfaceId（也就是本次构建接的口下的cases）
        buildCaseEntities.forEach (buildCase -> buildCase.setInterfaceId (buildInterfaceId));
        // 4、批量插入用例数据
        buildMapper.addBuildCases (buildCaseEntities);
        // 5、新建构建任务，并取出构建id
        BuildTestEntity buildTestEntity = new BuildTestEntity ();
        buildTestEntity.setTestPlanName ("用例任务-"+ DateUtil.getCurrentDate ());
        buildTestEntity.setStatus (BuildStatus.WAITFOREXCUTE.name ());
        buildTestEntity.setMode (RunMode.CASE.name ());
        buildTestEntity.setCaseSize (buildCaseEntities.size ());
        buildTestEntity.setProjectId (SessionUtil.getSession("lastProjectId"));
        String userName = userMapper.findUserById (SessionUtil.getSession ("userId")).getUserName ();
        buildTestEntity.setExcutionUser (userName);
        buildMapper.addBuildTest (buildTestEntity);
        String buildTestId = buildTestEntity.getId ();
        // 6、在处理接口，赋值给buildInterface中的buildTestId
        buildInterfaceEntities.forEach (buildInterface -> buildInterface.setBuildTestId (buildTestId));
        buildMapper.addBuildInterfaces (buildInterfaceEntities);
        return buildTestId;
    }

    /**
     * 遍历请求传来的要执行的cases复制属性返回buildCase集合
     *
     * @param caseEntities
     * @return
     */
    public List<BuildCaseEntity> copyPropertiesForCase(List<CaseEntity> caseEntities) {
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
        return buildCaseEntities;
    }

    /**
     * 遍历请求传来的要执行的interfaces复制属性返回buildCase集合
     *
     * @param httpEntities
     * @return
     */
    public List<BuildInterfaceEntity> copyPropertiesForInterface(List<HttpEntity> httpEntities) {
        List<BuildInterfaceEntity> buildInterfaceEntities = new ArrayList<BuildInterfaceEntity> ();
        Iterator<HttpEntity> it = httpEntities.iterator ();
        while (it.hasNext ()) {
            BuildInterfaceEntity buildInterfaceEntity = new BuildInterfaceEntity ();
            // copy属性 (第三个参数为不复制哪个属性）
            BeanUtils.copyProperties (it.next (), buildInterfaceEntity, new String[]{"id"});
            String buildInterfaceId = GenerateId.getUUID32 ();
            buildInterfaceEntity.setId (buildInterfaceId);
            buildInterfaceEntities.add (buildInterfaceEntity);
        }
        return buildInterfaceEntities;
    }
}
