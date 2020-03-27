package com.solplatform.entity;

import lombok.Builder;
import org.hibernate.validator.constraints.NotBlank;
import lombok.Data;

/**
 * 用户数据对象
 *
 * @author sol
 * @create 2019-12-30  23:40
 */
@Data
public class UserEntity extends BaseEntity {
    @NotBlank(message = "用户名不能为空")
    private String userName;
    @NotBlank(message = "密码不能为空")
    private String password;
    // 给用户设置默认的项目
    @Builder.Default
    private String lastProjectId = "9990edf061e011ea92e49181d1957489";
}
