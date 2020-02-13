package com.solplatform.controller.main;

import com.solplatform.common.CommonResult;
import com.solplatform.entity.UserEntity;
import com.solplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * 注册相关接口
 *
 * @author sol
 * @create 2020-01-09  22:46
 */
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
    public CommonResult<UserEntity> signup(@Valid UserEntity userEntity, BindingResult bindingResult, HttpServletResponse response, HttpSession httpSession) {
        //  判断是否字段有错误
        if (bindingResult.hasErrors ()) {
            System.err.println ("参数有问题");
            String errMsg = bindingResult.getFieldError ().getDefaultMessage ();
            response.setStatus (HttpServletResponse.SC_BAD_REQUEST);
            return CommonResult.failed (errMsg);
        } else {
            userService.registertUser (userEntity);
            // 成功后将id保存到sesstion中
            String userId = userEntity.getId ();
            httpSession.setAttribute ("userId", userId);
            // 注册成功后设置token,将token放到响应头中返回
            String authToken = "Bearer " + userEntity.getUserName () + userEntity.getPassword () + ".xxx.zzz";
            response.setHeader ("Authorization", authToken);
            return CommonResult.success (userEntity);
        }
    }
}
