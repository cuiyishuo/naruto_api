package com.solplatform.vo.component;

import lombok.Data;
import org.dozer.Mapping;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author sol
 * @create 2020-04-07  16:17
 */
@Data
public class HttpVo {
    @Mapping("id")
    private String interfaceId;
    private String interfaceName;
    private String methods;
    private String host;
    private String apiUrl;
    private String headers;
    private String timeOut;
    private String params;
    private String body;
    private String componentType;
    private String createTime;
    private String updateTime;
}
