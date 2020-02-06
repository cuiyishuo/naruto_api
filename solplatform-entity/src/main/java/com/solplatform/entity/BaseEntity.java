package com.solplatform.entity;

import lombok.Data;

/**
 * 公共实体类
 *
 * @author sol
 * @create 2020-01-16  23:43
 */
@Data
public class BaseEntity {
    private Integer id;
    private String createTime;
    private String updateTime;
    private int yn;
}
