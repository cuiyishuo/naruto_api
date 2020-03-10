package com.solplatform.exception;


/**
 * 自定义业务异常
 *
 * @author sol
 * @create 2020-03-10  16:38
 */
public class BusinessException extends RuntimeException{
    private Integer code;


    public BusinessException(String message, Integer code) {
        super (message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
