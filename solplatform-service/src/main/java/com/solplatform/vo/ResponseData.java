package com.solplatform.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 接口响应结果
 *
 * @author sol
 * @create 2020-03-12  15:20
 */
@Data
public class ResponseData {
    // 响应头
    private Map headers;
    // 响应体
    private List<Map<String,String>> body;
    // 响应状态码
    private int statusCode;
}
