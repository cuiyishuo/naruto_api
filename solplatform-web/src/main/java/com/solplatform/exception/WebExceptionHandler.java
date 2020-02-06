package com.solplatform.exception;

import com.solplatform.common.CommonResult;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalTime;


/**
 * 拦截controller层的异常
 *
 * @author sol
 * @create 2020-01-17  09:30
 */
@RestControllerAdvice()
public class WebExceptionHandler {

    /**
     * 运行时异常
     *
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler()
    public CommonResult unauthorizedException(RuntimeException e) {
        System.err.println (" message2:" + e.getMessage () + LocalTime.now ());
        return CommonResult.validateFailed (e.getMessage ());
    }

    /**
     * 查询主键重复异常
     *
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler()
    public CommonResult uniqueVerifyException(DuplicateKeyException e) {
        System.err.println (" message2:" + e.getMessage () + LocalTime.now ());
        return CommonResult.failed (e.getMessage ());
    }

    /**
     * 处理所有不可知的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult handleException(Exception e) {
        System.err.println ("操作失败：" + LocalTime.now () + "异常类型：" + e);
        return CommonResult.failed ("操作失败");
    }
}
