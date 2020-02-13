package com.solplatform.controller.main;

import com.solplatform.common.CommonResult;
import com.solplatform.entity.ProjectEntity;
import com.solplatform.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * 项目管理接口
 *
 * @author sol
 * @create 2020-02-04  23:29
 */
@RestController
@RequestMapping("/main")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    /**
     * 创建新项目
     *
     * @param projectEntity 前端表单传入的project对象json
     * @param bindingResult 验证结果对象
     * @param response      即将返回给前端的响应对象
     * @return 返回封装的响应结果
     */
    @PostMapping("/createProject")
    public CommonResult<ProjectEntity> createProject(@Valid ProjectEntity projectEntity, BindingResult bindingResult, HttpServletResponse response) {
        //  判断是否字段有错误
        if (bindingResult.hasErrors ()) {
            System.err.println ("参数有问题");
            String errMsg = bindingResult.getFieldError ().getDefaultMessage ();
            response.setStatus (HttpServletResponse.SC_BAD_REQUEST);
            return CommonResult.failed (errMsg);
        } else {
            projectService.createProject (projectEntity);
            return CommonResult.success (projectEntity);
        }
    }

    /**
     * 修改项目
     *
     * @param projectEntity
     * @param bindingResult
     * @param response
     * @return
     */
    @PostMapping("/modifyProject")
    public CommonResult<ProjectEntity> modifyProject(@Valid ProjectEntity projectEntity, BindingResult bindingResult, HttpServletResponse response) {
        //  判断是否字段有错误
        if (bindingResult.hasErrors ()) {
            String errMsg = bindingResult.getFieldError ().getDefaultMessage ();
            response.setStatus (HttpServletResponse.SC_BAD_REQUEST);
            return CommonResult.failed (errMsg);
        } else {
            projectService.modifyProject (projectEntity);
            return CommonResult.success (projectEntity);
        }
    }

    @GetMapping("/getProjectList")
    public CommonResult<List<ProjectEntity>> getProjectList(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "2") int pageSize) {
        List<ProjectEntity> projectList = projectService.getProjectList (pageNo, pageSize);
        return CommonResult.success (projectList);
    }
}
