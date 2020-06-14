package com.solplatform.service.builds;

import com.solplatform.constants.BuildStatus;
import com.solplatform.constants.RunMode;
import com.solplatform.entity.builds.BuildCaseEntity;
import com.solplatform.entity.builds.BuildInterfaceEntity;
import com.solplatform.entity.builds.BuildTestEntity;
import com.solplatform.exception.BusinessException;
import com.solplatform.factorys.asserts.AssertFactory;
import com.solplatform.factorys.asserts.AssertProcessor;
import com.solplatform.factorys.component.ComponentFactory;
import com.solplatform.factorys.component.ComponentProcessor;
import com.solplatform.mapper.BuildMapper;
import com.solplatform.mapper.builds.BuildTestMapper;
import com.solplatform.service.AssertExpressionService;
import com.solplatform.util.Calculat;
import com.solplatform.util.DateUtil;
import com.solplatform.util.LogInfoUtil;
import com.solplatform.vo.BuildContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
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
    @Autowired
    BuildTestMapper buildTestMapper;

    /**
     * * 执行构建任务
     *
     * @param buildContent
     * @return
     */
    public void runTest(BuildContent buildContent) {
        log.info ("进入方法【{}】", LogInfoUtil.getCurrentMethod ());
        String buildTestId = buildContent.getBuildTestEntity ().getId ();
        // 1、根据构建id查询出接口list和用例list的信息
        BuildTestEntity buildTestEntity = buildTestMapper.findBuildTestById (buildTestId);
        // 2、存入构建上下文
        buildContent.setBuildTestEntity (buildTestEntity);
        String runMode = buildTestEntity.getMode ();
        if (RunMode.MODEL.name ().equals (runMode)) {
            // 执行模块模式的构建
            this.runTestByModel (buildContent);
        } else if (RunMode.TESTPLAN.name ().equals (runMode)) {
            // 执行测试计划模式的构建
        }
    }

    /**
     * 模块级别测试
     *
     * @param buildContent 构建上下文
     */
    public void runTestByModel(BuildContent buildContent) {
        log.info ("进入方法【{}】", LogInfoUtil.getCurrentMethod ());
        try {
            log.info ("更新构建任务状态为【执行中】");
            buildContent.getBuildTestEntity ().setStatus (BuildStatus.EXCUTING.name ());
            buildTestMapper.updateBuildTest (buildContent.getBuildTestEntity ());
            List<BuildInterfaceEntity> buildInterfaceEntities = buildContent.getBuildTestEntity ().getBuildInterfaceEntities ();
            Iterator<BuildInterfaceEntity> buildInterfaceEntityIterator = buildInterfaceEntities.iterator ();

            int passCaseSizeInterface = 0;
            int caseSizeInterface = 0;
            LocalDateTime startAt = null;
            LocalDateTime endAt = null;
            Duration duration = null;
            startAt = DateUtil.getCurrentDate ();
            while (buildInterfaceEntityIterator.hasNext ()) {
                log.info ("遍历接口list,并存储到上下文中");
                BuildInterfaceEntity buildInterfaceEntity = buildInterfaceEntityIterator.next ();
                buildContent.setBuildInterfaceEntity (buildInterfaceEntity);
                String componentType = buildInterfaceEntity.getComponentType ();
                List<BuildCaseEntity> buildCaseEntities = buildInterfaceEntity.getBuildCaseEntities ();
                // 通过向下转型获得实现类的对象
                ComponentProcessor componentProcessor = componentFactory.getComponent (componentType);
                Iterator<BuildCaseEntity> buildCaseEntityIterator = buildCaseEntities.iterator ();

                int passCaseSize = 0;
                int caseSize = buildCaseEntities.size ();
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
                    // 测试前端轮询，加入线程等待
                    Thread.sleep (3000);
                    try {
                        buildMapper.updateBuildCase (buildContent.getBuildCaseEntity ());
                    } catch (Exception e) {
                        log.error ("数据库更新构建用例失败：" + e.getMessage ());
                    }
                    if (BuildStatus.PASS.name ().equalsIgnoreCase (buildContent.getBuildCaseEntity ().getStatus ())) {
                        passCaseSize++;
                    }
                }
                log.info ("统计用例通过率");
                buildContent.getBuildInterfaceEntity ().setCaseSize (caseSize);
                buildContent.getBuildInterfaceEntity ().setPassCaseSize (passCaseSize);
                buildContent.getBuildInterfaceEntity ().setFailedCaseSize (caseSize - passCaseSize);
                double percentage = Calculat.percentage ((double) passCaseSize, (double) caseSize, 2);
                buildContent.getBuildInterfaceEntity ().setPassRate (percentage);
                log.info ("将接口数据存储到数据库");
                buildMapper.updateBuildInterface (buildContent.getBuildInterfaceEntity ());
                // 将每次
                caseSizeInterface += caseSize;
                passCaseSizeInterface += passCaseSize;
            }
            endAt = DateUtil.getCurrentDate ();
            duration = Duration.between (startAt, endAt);
            log.info ("统计所有接口中用例通过率");
            buildContent.getBuildTestEntity ().setCaseSize (caseSizeInterface);
            buildContent.getBuildTestEntity ().setPassCaseSize (passCaseSizeInterface);
            buildContent.getBuildTestEntity ().setFailedCaseSize (caseSizeInterface - passCaseSizeInterface);
            double percentage = Calculat.percentage ((double) passCaseSizeInterface, (double) caseSizeInterface, 2);
            buildContent.getBuildTestEntity ().setPassRate (percentage);
            buildContent.getBuildTestEntity ().setStartAt (startAt.toString ());
            buildContent.getBuildTestEntity ().setEndAt (endAt.toString ());
            buildContent.getBuildTestEntity ().setDuration (String.valueOf (duration.toMillis ()));
            if (passCaseSizeInterface == caseSizeInterface) {
                buildContent.getBuildTestEntity ().setStatus (BuildStatus.PASS.name ());
            } else if (passCaseSizeInterface == 0) {
                buildContent.getBuildTestEntity ().setStatus (BuildStatus.FAILED.name ());
            } else {
                buildContent.getBuildTestEntity ().setStatus (BuildStatus.PARTIALPASS.name ());
            }
            log.info ("将buildtest数据存储到数据库");
            buildTestMapper.updateBuildTest (buildContent.getBuildTestEntity ());
        } catch (Exception e) {
            e.printStackTrace ();
            System.out.println (e.getMessage ());
            throw new BusinessException ("执行测试用例异常");
        }
    }
}
