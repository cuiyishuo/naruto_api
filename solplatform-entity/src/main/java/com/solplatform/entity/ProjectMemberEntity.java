package com.solplatform.entity;

import lombok.Data;

/**
 * 项目成员类
 *
 * @author sol
 * @create 2020-02-10  22:53
 */
@Data
public class ProjectMemberEntity extends BaseEntity {
    private String userId;
    private String projectId;
}
