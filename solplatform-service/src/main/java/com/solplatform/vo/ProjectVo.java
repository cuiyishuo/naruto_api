package com.solplatform.vo;

import lombok.Data;
import org.dozer.Mapping;

/**
 * 项目vo类
 *
 * @author sol
 * @create 2020-02-19  00:52
 */
@Data
public class ProjectVo {
    /**
     * 项目id
     */
    @Mapping("id")
    private String projectId;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 项目名称
     */
    private String projectName;
}
