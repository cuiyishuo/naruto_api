package com.solplatform.entity;

import org.hibernate.validator.constraints.NotBlank;
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
    @NotBlank(message = "用户名不能为空")
    private String userName;
    @NotBlank(message = "密码不能为空")
    private String password;
}
