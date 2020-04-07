package com.solplatform.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;
import java.util.Map;

/**
 * http请求
 *
 * @author sol
 * @create 2020-03-12  15:05
 */
@Data
public class HttpEntity extends BaseEntity{
    private String interfaceName;
    @NotBlank(message = "请求方法不能为空")
    private String methods;
    @NotBlank(message = "host不能为空")
    private String host;
    @NotBlank(message = "接口地址不能为空")
    private String apiUrl;
    private String headers;
    @NotBlank(message = "超时时间不能为空")
    private String timeOut;
    private String params;
    private String body;
    private String componentType;
    private String projectId;
}
