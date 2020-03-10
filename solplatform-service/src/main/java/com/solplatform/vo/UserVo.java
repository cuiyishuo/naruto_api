package com.solplatform.vo;

import lombok.Data;
import org.dozer.Mapping;


/**
 * 用户vo类
 *
 * @author sol
 * @create 2020-02-18  01:00
 */
@Data
public class UserVo {
    /**
     * 用户id
     */
    @Mapping("id")
    private String userId;
    /**
     * 创建时间
     */
    @Mapping("createTime")
    private String registerTime;
    /**
     * 用户名
     */
    private String userName;

}
