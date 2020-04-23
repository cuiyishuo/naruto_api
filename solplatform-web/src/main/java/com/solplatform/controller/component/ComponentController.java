package com.solplatform.controller.component;

import com.solplatform.common.CommonResult;
import com.solplatform.entity.CaseEntity;
import com.solplatform.entity.HttpEntity;
import com.solplatform.service.CaseService;
import com.solplatform.service.ComponentService;
import com.solplatform.util.DozerConvertor;
import com.solplatform.vo.TablePage;
import com.solplatform.vo.component.CaseVo;
import com.solplatform.vo.component.HttpVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * 组件接口
 *
 * @author sol
 * @create 2020-04-03  11:33
 */
@RestController
@RequestMapping("/component")
public class ComponentController {
    @Autowired
    ComponentService componentService;
    @Autowired
    DozerConvertor dozerConvertor;
    @Autowired
    CaseService caseService;

    @PostMapping("/addComponent")
    public CommonResult<HttpEntity> addComponent(@Valid HttpEntity httpEntity, BindingResult bindingResult, HttpServletResponse response) {
        //  判断是否字段有错误
        if (bindingResult.hasErrors ()) {
            System.err.println ("参数有问题");
            String errMsg = bindingResult.getFieldError ().getDefaultMessage ();
            response.setStatus (HttpServletResponse.SC_BAD_REQUEST);
            return CommonResult.failed (errMsg);
        } else {
            componentService.createComponent (httpEntity);
            return CommonResult.success (httpEntity);
        }
    }

    @GetMapping("/getComponent")
    public CommonResult getComponentList(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize, HttpEntity httpEntity, HttpServletResponse response) {
        TablePage tablePage = componentService.getComponetList (pageNo, pageSize, httpEntity);
        // 将total返回到响应头中
        response.setHeader ("total", tablePage.getTotal ().toString ());
        // 获取页面数据
        List componentList = tablePage.getCurrentPageData ();
        // 将po转vo
        List httpComponentListVo = dozerConvertor.convertor (componentList, HttpVo.class);
        return CommonResult.success (httpComponentListVo);
    }

    @PatchMapping("/updateComponent")
    public CommonResult updateComponent(@Valid HttpEntity httpEntity, BindingResult bindingResult, HttpServletResponse response) {
        //  判断是否字段有错误
        if (bindingResult.hasErrors ()) {
            System.err.println ("参数有问题");
            String errMsg = bindingResult.getFieldError ().getDefaultMessage ();
            response.setStatus (HttpServletResponse.SC_BAD_REQUEST);
            return CommonResult.failed (errMsg);
        } else {
            componentService.updateCOmponent (httpEntity);
            return CommonResult.success (httpEntity);
        }
    }

    @PostMapping("/case")
    public CommonResult<CaseVo> saveCase(@Valid @RequestBody CaseVo caseVo, BindingResult bindingResult, HttpServletResponse response) {
        //  判断是否字段有错误
        if (bindingResult.hasErrors ()) {
            System.err.println ("参数有问题");
            String errMsg = bindingResult.getFieldError ().getDefaultMessage ();
            response.setStatus (HttpServletResponse.SC_BAD_REQUEST);
            return CommonResult.failed (errMsg);
        } else {
            CaseEntity caseEntity = dozerConvertor.convertor (caseVo, CaseEntity.class);
            // 新建一个用例中的http对象
            HttpEntity httpEntity = caseEntity.getHttpEntity ();
            componentService.createComponent (httpEntity);
            // 调用新建用例方法
            caseService.addCase (caseEntity);
            return CommonResult.success (caseVo);
        }
    }

    @GetMapping("/cases")
    public CommonResult<List<CaseVo>> findCase(@RequestParam("interfaceId") String interfaceId1) {
        // 如果为空则给客户端抛出异常
        if ("".equals (interfaceId1)) {
            CommonResult.failed ("缺少所属接口id");
        }
        List<CaseEntity> caseEntities = caseService.findCaseByInterfaceId (interfaceId1);
        List<CaseVo> caseVos = dozerConvertor.convertor (caseEntities, CaseVo.class);
        return CommonResult.success (caseVos);
    }
}
