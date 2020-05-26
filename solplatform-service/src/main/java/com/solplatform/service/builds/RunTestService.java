package com.solplatform.service.builds;

import com.solplatform.constants.RunMode;
import com.solplatform.entity.HttpEntity;
import com.solplatform.entity.builds.BuildCaseEntity;
import com.solplatform.entity.builds.BuildInterfaceEntity;
import com.solplatform.entity.builds.BuildTestEntity;
import com.solplatform.exception.BusinessException;
import com.solplatform.factorys.asserts.AssertFactory;
import com.solplatform.factorys.asserts.AssertProcessor;
import com.solplatform.factorys.component.ComponentFactory;
import com.solplatform.factorys.component.ComponentProcessor;
import com.solplatform.mapper.BuildMapper;
import com.solplatform.service.AssertExpressionService;
import com.solplatform.util.LogInfoUtil;
import com.solplatform.vo.BuildContent;
import com.solplatform.vo.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 执行测试用例业务
 *
 * @author sol
 * @create 2020-05-06  4:51 下午
 */
@Service
@Slf4j
public class RunTestService {
    @Autowired
    BuildMapper buildMapper;
    @Autowired
    ComponentFactory componentFactory;
    @Autowired
    AssertExpressionService assertExpressionService;
    @Autowired
    AssertFactory assertFactory;

    /**
     * * 执行构建任务
     *
     * @param buildContent
     * @return
     */
    public BuildTestEntity runTest(BuildContent buildContent) {
        log.info ("进入方法【{}】", LogInfoUtil.getCurrentMethod ());
        String buildTestId = buildContent.getBuildTestEntity ().getId ();
        // 1、根据构建id查询出接口list和用例list的信息
        BuildTestEntity buildTestEntity = buildMapper.findBuildTestById (buildTestId);
        // 2、存入构建上下文
        buildContent.setBuildTestEntity (buildTestEntity);
        String runMode = buildTestEntity.getMode ();
        if (RunMode.MODEL.name ().equals (runMode)) {
            // 执行模块模式的构建
            this.runTestByModel (buildContent);
        } else if (RunMode.TESTPLAN.name ().equals (runMode)) {
            // 执行测试计划模式的构建
        }
        return buildTestEntity;
    }

    /**
     * 模块级别测试
     *
     * @param buildContent 构建上下文
     */
    public void runTestByModel(BuildContent buildContent) {
        log.info ("进入方法【{}】", LogInfoUtil.getCurrentMethod ());
        try {
            List<BuildInterfaceEntity> buildInterfaceEntities = buildContent.getBuildTestEntity ().getBuildInterfaceEntities ();
            Iterator<BuildInterfaceEntity> buildInterfaceEntityIterator = buildInterfaceEntities.iterator ();
            while (buildInterfaceEntityIterator.hasNext ()) {
                log.info ("遍历接口list,并存储到上下文中");
                BuildInterfaceEntity buildInterfaceEntity = buildInterfaceEntityIterator.next ();
                buildContent.setBuildInterfaceEntity (buildInterfaceEntity);
                String componentType = buildInterfaceEntity.getComponentType ();
                List<BuildCaseEntity> buildCaseEntities = buildInterfaceEntity.getBuildCaseEntities ();
                // 通过向下转型获得实现类的对象
                ComponentProcessor componentProcessor = componentFactory.getComponent (componentType);
                Iterator<BuildCaseEntity> buildCaseEntityIterator = buildCaseEntities.iterator ();
                while (buildCaseEntityIterator.hasNext ()) {
                    BuildCaseEntity buildCaseEntity = buildCaseEntityIterator.next ();
                    log.info ("遍历用例list,并存储到上下文中");
                    buildContent.setBuildCaseEntity (buildCaseEntity);
                    log.info ("开始通过工厂处理器执行 [runTest()],执行类型为 [{}]", componentType);
                    componentProcessor.runTest (buildContent);
                    String assertType = buildCaseEntity.getAssertType ();
                    log.info ("开始通过工厂处理器执行 [assertTest()],断言类型为 [{}]", assertType);
                    AssertProcessor assertProcessor = assertFactory.getAssert (assertType);
                    assertProcessor.assertTest (buildContent);
                    log.info ("将用例更新到数据库");
                    try {
                        buildMapper.updateBuildCaseById (buildContent.getBuildCaseEntity ());
                    } catch (Exception e) {
                        log.error ("更新构建用例失败：" + e.getMessage ());
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace ();
            System.out.println (e.getMessage ());
            throw new BusinessException ("执行测试用例异常");
        }
    }
}
