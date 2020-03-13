package com.solplatform.exception;


/**
 * 自定义业务异常
 *
 * @author sol
 * @create 2020-03-10  16:38
 */
public class BusinessException extends RuntimeException{
    // 简单的传入异常信息即可
    public BusinessException(String message) {
        super (message);
    }

}
