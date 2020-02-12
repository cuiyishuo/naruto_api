package com.solplatform.controller.main;

import com.solplatform.common.CommonResult;
import com.solplatform.entity.UserEntity;
import com.solplatform.service.UserService;
import jdk.nashorn.internal.ir.ReturnNode;
import org.apache.coyote.http11.upgrade.UpgradeServletOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * 登录相关接口
 *
 * @author sol
 * @create 2020-01-17  14:40
 */
@RestController
@RequestMapping("/main")
public class LoginController {
    @Autowired
    UserService userService;

    /**
     * 登录接口
     *
     * @param userEntity
     * @param bindingResult
     * @param response
     * @return
     */
    @PostMapping("/login")
    public CommonResult<UserEntity> login(@Valid UserEntity userEntity, BindingResult bindingResult, HttpServletResponse response, HttpSession httpSession) {
        //  判断是否字段有错误
        if (bindingResult.hasErrors ()) {
            System.err.println ("参数有问题");
            String errMsg = bindingResult.getFieldError ().getDefaultMessage ();
            response.setStatus (HttpServletResponse.SC_BAD_REQUEST);
            return CommonResult.failed (errMsg);
        } else {
            String userId = userService.checkUser (userEntity);
            if (userId.isEmpty ()) {
                response.setStatus (HttpServletResponse.SC_BAD_REQUEST);
                return CommonResult.failed ("用户不存在");
            } else {
                System.err.println ("登录成功，将userId存储到session中");
                httpSession.setAttribute ("userId", userId);
                return CommonResult.success (userEntity);
            }
        }
    }
}
