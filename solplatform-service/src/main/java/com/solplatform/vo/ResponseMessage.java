package com.solplatform.vo;

import lombok.Data;

/**
 * 封装响应数据
 *
 * @author sol
 * @create 2020-01-08  23:01
 */
// 加在类后，该类下可以使用范型T
@Data
public class ResponseMessage<T> {
    // 响应是否成功
    private boolean isSuccess;
    // 响应失败时的失败信息
    private String message;
    // 响应状态码
    private Integer statusCode;
    // 请求成功放回的响应数据
    private T data;

    // 构造函数
    ResponseMessage(boolean isSuccess, String message, Integer statusCode, T data) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.statusCode = statusCode;
        this.data = data;
    }

    /**
     * 请求失败时的响应
     *
     * @param message
     * @param statusCode
     * @return
     */
    public static ResponseMessage errorResponse(String message, Integer statusCode) {
        return new ResponseMessage (Boolean.FALSE, message, statusCode, null);
    }

    /**
     * 请求成功的响应-没有响应数据
     *
     * @return
     */
    public static ResponseMessage successResponse() {
        return new ResponseMessage (Boolean.TRUE, null, 200, null);
    }

    /**
     * 请求成功的响应-有响应数据
     *
     * @param data
     * @return
     */
    public static ResponseMessage successResponse(Object data) {
        return new ResponseMessage (Boolean.TRUE, null, 200, data);
    }
}
