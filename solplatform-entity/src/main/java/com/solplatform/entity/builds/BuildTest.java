package com.solplatform.entity.builds;

import com.solplatform.entity.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * 构建任务
 *
 * @author sol
 * @create 2020-04-28  12:03
 */
@Data
public class BuildTest extends BaseEntity {
    // 构建任务状态(主要区分是否执行完成)
    private String status;
    // 执行用例数
    private int caseSize;
    // 用例通过数
    private String passCaseSize;
    // 用例失败数
    private String failedCaseSize;
    // 用例通过率
    private double passRate;
    // 项目id
    private String projectId;
    // 任务模式(1按照接口用例运行 2.按照测试计划运行)
    private String mode;
    // 运行时间
    private String duration;
    // 开始运行时间
    private String startAt;
    // 结束时间
    private String endAt;
    // 构建计划名称
    private String testPlanName;
    // 构建计划id
    private String testPlanId;
    // 执行人
    private String excutionUser;
    // 构建任务下的接口数据
    private List<BuildInterfaceEntity> buildInterfaceEntities;

}
