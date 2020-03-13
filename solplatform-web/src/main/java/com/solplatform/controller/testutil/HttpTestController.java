package com.solplatform.controller.testutil;

import com.solplatform.common.CommonResult;
import com.solplatform.entity.HttpEntity;
import com.solplatform.service.UserService;
import com.solplatform.service.UtilService;
import com.solplatform.util.DozerConvertor;
import com.solplatform.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 测试工具接口
 *
 * @author sol
 * @create 2020-03-13  10:49
 */
@RestController
@RequestMapping("/util")
public class HttpTestController {
    @Autowired
    UtilService utilService;
    @Resource
    DozerConvertor dozerConvertor;

    @PostMapping("/testhttp")
    public CommonResult invoke(@Valid HttpEntity httpEntity, BindingResult bindingResult, HttpServletResponse response) {

        if (bindingResult.hasErrors ()) {
            System.err.println ("参数有问题");
            String errMsg = bindingResult.getFieldError ().getDefaultMessage ();
            response.setStatus (HttpServletResponse.SC_BAD_REQUEST);
            return CommonResult.failed (errMsg);
        }
        ResponseData responseData = new ResponseData ();
        if (httpEntity.getMethods ().equals ("get")) {
            responseData = utilService.invokeGet (httpEntity);
        } else if (httpEntity.getMethods ().equals ("post")) {
            responseData = utilService.invokePost (httpEntity);
        }
        return CommonResult.success (responseData);
    }
}
