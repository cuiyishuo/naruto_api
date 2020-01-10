package com.solplatform.common;

import lombok.Data;

/**
 * 通用返回对象
 *
 * @author sol
 * @create 2020-01-10  11:57
 */
// 此处的T代表该类是一个范型类
@Data
public class CommonResult<T> {

    private int code;
    private String message;
    private T data;

    protected CommonResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     */
    public static <T> CommonResult<T> success() {
        return new CommonResult<T> (CodeMsg.SUCCESS.getCode (), CodeMsg.SUCCESS.getMsg (), null);
    }

    /**
     * 成功返回结果-有响应数据
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T> (CodeMsg.SUCCESS.getCode (), CodeMsg.SUCCESS.getMsg (), data);
    }

    /**
     * 通用失败返回结果
     */
    public static <T> CommonResult<T> failed() {
        return new CommonResult<T> (CodeMsg.ERROR.getCode (), CodeMsg.ERROR.getMsg (), null);
    }

    /**
     * 通用失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<T> (CodeMsg.ERROR.getCode (), message, null);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> CommonResult<T> validateFailed() {
        return new CommonResult<T> (CodeMsg.VALIDATE_ERROR.getCode (), CodeMsg.VALIDATE_ERROR.getMsg (), null);
    }

    /**
     * 参数验证失败返回结果-自定义msg
     *
     * @param message 提示信息
     */
    public static <T> CommonResult<T> validateFailed(String message) {
        return new CommonResult<T> (CodeMsg.VALIDATE_ERROR.getCode (), message, null);
    }
}
