package com.solplatform.entity.build;

import com.solplatform.entity.HttpEntity;
import lombok.Data;

import java.util.List;

/**
 * 构建接口
 *
 * @author sol
 * @create 2020-04-28  11:48
 */
@Data
public class BuildInterfaceEntity extends HttpEntity {
    // 构建任务id
    private String buildTestId;
    // 接口中case的通过率
    private double passRate;
    // 运行的用例数量
    private int caseSize;
    // 通过用例数
    private int passCaseSize;
    // 失败用例数量
    private int failedCaseSize;
    // 构建的接口下的用例数据
    private List<BuildCaseEntity> buildCaseEntities;

}
