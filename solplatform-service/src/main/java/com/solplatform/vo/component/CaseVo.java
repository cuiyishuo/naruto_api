package com.solplatform.vo.component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solplatform.entity.HttpEntity;
import lombok.Data;
import org.dozer.Mapping;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * 测试用例vo
 *
 * @author sol
 * @create 2020-04-15  10:23
 */
@Data
public class CaseVo {
    // 用例名称
    @NotBlank(message = "用例名称不能为空")
    private String caseName;
    // 用例级别
    private String caseLevel;
    // 断言类型
    private String assertType;
    // 断言表达式列表
    private List assertHeaderList;
    private List assertResbodyList;
    // 接口实体数据(注解映射到前端请求字段）
    @JsonProperty("HttpForm")
    @Mapping("httpEntity")
    private HttpVo httpVo;
    // 所属接口id
    private String id;
    private String createTime;
    private String updateTime;
    private String interfaceId;
}
