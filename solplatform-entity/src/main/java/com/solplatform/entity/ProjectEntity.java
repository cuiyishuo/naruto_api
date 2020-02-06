package com.solplatform.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 项目实体类
 *
 * @author sol
 * @create 2020-02-02  23:46
 */
@Data
public class ProjectEntity extends BaseEntity{
    @NotBlank(message = "项目名称不能为空")
    private String projectName;
}
