package com.solplatform.entity.builds;

import com.solplatform.entity.CaseEntity;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 构建测试用例
 *
 * @author sol
 * @create 2020-04-28  11:24
 */
@Data
public class BuildCaseEntity extends CaseEntity {
    // 运行时响应头
    private Map<String,String> responseHeader;
    // 运行时响应体
    private List responseBody;
    // 构建状态(主要区分是否执行完成)
    private String status;
    // 开始时间
    private String startAt;
    // 结束时间
    private String endAt;
    // 运行时长
    private String duration;
}
