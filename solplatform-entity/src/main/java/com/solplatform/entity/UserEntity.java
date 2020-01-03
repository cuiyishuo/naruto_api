package com.solplatform.entity;


import lombok.Data;

/**
 * 用户数据对象
 *
 * @author sol
 * @create 2019-12-30  23:40
 */
@Data
public class UserEntity {
    private Integer id;
    private String userName;
    private String password;
}
