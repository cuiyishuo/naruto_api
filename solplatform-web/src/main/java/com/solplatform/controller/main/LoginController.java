package com.solplatform.controller.main;

import com.solplatform.common.CommonResult;
import com.solplatform.entity.UserEntity;
import com.solplatform.service.UserService;
import com.solplatform.util.DozerConvertor;
import com.solplatform.vo.UserVo;
import jdk.nashorn.internal.ir.ReturnNode;
import org.apache.coyote.http11.upgrade.UpgradeServletOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    @Resource
    DozerConvertor dozerConvertor;

    /**
     * 登录接口
     *
     * @param userEntity
     * @param bindingResult
     * @param response
     * @return
     */
    @PostMapping("/login")
    public CommonResult login(@Valid UserEntity userEntity, BindingResult bindingResult, HttpServletResponse response, HttpSession httpSession) {
        //  判断是否字段有错误
        if (bindingResult.hasErrors ()) {
            System.err.println ("参数有问题");
            String errMsg = bindingResult.getFieldError ().getDefaultMessage ();
            response.setStatus (HttpServletResponse.SC_BAD_REQUEST);
            return CommonResult.failed (errMsg);
        } else {
//            String userId = userService.checkUser (userEntity);
            if (null == userService.checkUser (userEntity)) {
                response.setStatus (HttpServletResponse.SC_BAD_REQUEST);
                return CommonResult.failed ("用户不存在");
            } else {
                UserEntity userEntityPo = userService.checkUser (userEntity);
                // 登录成功，将userId存储到session中
                httpSession.setAttribute ("userId", userEntityPo.getId ());
                // 设置超时时间，0为永久
                httpSession.setMaxInactiveInterval(0);
                // 登录成功后设置token,将token放到响应头中返回
                String authToken = "Bearer " + userEntityPo.getUserName () + userEntityPo.getPassword () + ".xxx.zzz";
                response.setHeader ("Authorization", authToken);
                // po转为vo
                UserVo userVo = dozerConvertor.convertor (userEntityPo, UserVo.class);

                // 将用户的lastprojectid保存到sesstion中
                httpSession.setAttribute ("lastProjectId",userEntity.getLastProjectId ());
                return CommonResult.success (userVo);
            }
        }
    }
}
