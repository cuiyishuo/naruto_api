package com.solplatform.controller.main;

import com.solplatform.common.CommonResult;
import com.solplatform.entity.UserEntity;
import com.solplatform.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * 登录相关接口
 *
 * @author sol
 * @create 2020-01-09  22:46
 */
@Slf4j
@RestController
@RequestMapping("/main")
public class RegisterController {
    @Autowired
    UserService userService;

    /**
     * 注册接口
     *
     * @param userEntity
     * @param bindingResult
     * @return
     */
    @PostMapping("/signup")
    public CommonResult<UserEntity> signup(@Valid UserEntity userEntity, BindingResult bindingResult, HttpServletResponse response) {
        //  判断是否字段有错误
        if (bindingResult.hasErrors ()) {
            System.err.println ("参数有问题");
            String errMsg = bindingResult.getFieldError ().getDefaultMessage ();
            return CommonResult.failed (errMsg, response);
        } else {
            try {
                CommonResult<UserEntity> commonResult = userService.registertUser (userEntity,response);
                return commonResult;
            } catch (Exception e) {
                return CommonResult.failed ();
            }
        }
    }
}
