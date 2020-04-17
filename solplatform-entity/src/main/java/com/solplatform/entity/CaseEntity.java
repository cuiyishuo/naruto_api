package com.solplatform.entity;

import lombok.Data;

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
    private String assertHeaderList;
    private String assertResbodyList;
    // 接口实体类
    private HttpEntity httpEntity;
}
