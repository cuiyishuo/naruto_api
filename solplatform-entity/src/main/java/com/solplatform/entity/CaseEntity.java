package com.solplatform.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 用例实体
 *
 * @author sol
 * @create 2020-04-14  23:32
 */
@Data
public class CaseEntity extends BaseEntity {
    // 用例名称
    private String caseName;
    // 用例级别
    private String caseLevel;
    // 断言类型
    private String assertType;
    // 断言表达式列表
    private List assertHeaderList;
    private List assertResbodyList;
    // 用例中的接口数据（与接口模版数据有可能不同）
    private String interfaceInCaseId;
    // 接口实体类
    private HttpEntity httpEntity;
    // 所属接口id
    private String interfaceId;
}
