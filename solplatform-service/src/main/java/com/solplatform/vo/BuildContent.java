package com.solplatform.vo;

import com.solplatform.entity.builds.BuildCaseEntity;
import com.solplatform.entity.builds.BuildInterfaceEntity;
import com.solplatform.entity.builds.BuildTestEntity;
import lombok.Data;

/**
 * 构建上下文
 *
 * @author sol
 * @create 2020-05-09  4:20 下午
 */
@Data
public class BuildContent {
    private BuildTestEntity buildTestEntity;
    private BuildInterfaceEntity buildInterfaceEntity;
    private BuildCaseEntity buildCaseEntity;
}
