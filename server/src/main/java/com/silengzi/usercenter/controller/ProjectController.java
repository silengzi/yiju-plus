package com.silengzi.usercenter.controller;

import com.silengzi.usercenter.common.BaseResponse;
import com.silengzi.usercenter.common.ErrorCode;
import com.silengzi.usercenter.common.ResultUtils;
import com.silengzi.usercenter.exception.BusinessException;
import com.silengzi.usercenter.model.domain.Project;
import com.silengzi.usercenter.model.domain.User;
import com.silengzi.usercenter.model.dto.ProjectSearchCriteria;
import com.silengzi.usercenter.model.request.UserLoginRequest;
import com.silengzi.usercenter.model.request.UserRegisterRequest;
import com.silengzi.usercenter.model.result.PageResult;
import com.silengzi.usercenter.service.ProjectService;
import com.silengzi.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.silengzi.usercenter.contant.UserConstant.ADMIN_ROLE;
import static com.silengzi.usercenter.contant.UserConstant.USER_LOGIN_STATE;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Resource
    private ProjectService projectService;

    @GetMapping("/list")
    public BaseResponse<PageResult<Project>> searchProjectList(
            @RequestParam(value = "xzqhCode", required = false, defaultValue = "") String xzqhCode,
            @RequestParam(value = "minPrice", required = false, defaultValue = "") String minPrice,
            @RequestParam(value = "maxPrice", required = false, defaultValue = "") String maxPrice,
            @RequestParam(value = "housetype", required = false, defaultValue = "") String housetype,
            @RequestParam(value = "projname", required = false, defaultValue = "") String projname,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            HttpServletRequest request
    ) {
        isAdmin(request);

        ProjectSearchCriteria projectSearchCriteria = new ProjectSearchCriteria();
        projectSearchCriteria.setXzqhCode(xzqhCode);
        projectSearchCriteria.setMinPrice(minPrice);
        projectSearchCriteria.setMaxPrice(maxPrice);
        projectSearchCriteria.setHousetype(housetype);
        projectSearchCriteria.setProjname(projname);

        PageResult<Project> pageResult = projectService.projectList(projectSearchCriteria, page, size);

        return ResultUtils.success(pageResult);
    }

    @GetMapping("/projectDetail")
    public BaseResponse<Project> projectDetail(
        @RequestParam(value = "id", required = true) Long id,
        HttpServletRequest request
    ) {
        isAdmin(request);

        Project project = projectService.projectDetail(id);
        return ResultUtils.success(project);
    }

    @PostMapping("/projectUpdate")
    public BaseResponse<Project> projectUpdate(
        @RequestBody Project project,
        HttpServletRequest request
    ) {
        isAdmin(request);

        Project newProject = projectService.projectUpdate(project);
        return ResultUtils.success(newProject);
    }

    @PostMapping("/projectAdd")
    public BaseResponse<Long> projectAdd(
        @RequestBody Project project,
        HttpServletRequest request
    ) {
        isAdmin(request);

        Long id = projectService.projectAdd(project);
        return ResultUtils.success(id);
    }

    @PostMapping("/projectDelete")
    public BaseResponse<Boolean> projectDelete(
        @RequestBody Long[] ids,
        HttpServletRequest request
    ) {
        isAdmin(request);
        if(ids == null || ids.length <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "id 不能为空");
        }

        for (long id: ids) {
            if(id <= 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "id 必须大于0");
            }

            boolean result = projectService.projectDelete(id);
            if(!result) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "删除失败");
            }
        }

        return ResultUtils.success(true);
    }


//    @PostMapping("delete")
//    public BaseResponse<Boolean> deleteUser(@RequestBody long[] ids, HttpServletRequest request) {
//        isAdmin(request);
//        if(ids == null || ids.length == 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR, "id 不能为空");
//        }
//
//        for (long id: ids) {
//            if(id <= 0) {
//                throw new BusinessException(ErrorCode.PARAMS_ERROR, "id 必须大于0");
//            }
//
//            boolean result = userService.deleteUser(id, request);
//            if(!result) {
//                throw new BusinessException(ErrorCode.PARAMS_ERROR, "删除失败");
//            }
//        }
//
//        return ResultUtils.success(true);
//    }
//
//    @PostMapping("update")
//    public BaseResponse<User> updateUser(@RequestBody User newUser, HttpServletRequest request) {
//        isAdmin(request);
//        if(newUser == null) {
//            throw new BusinessException(ErrorCode.NULL_ERROR);
//        }
//
//        User user = userService.updateUser(newUser, request);
//        return ResultUtils.success(user);
//    }

    public void isAdmin(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        if(user != null && user.getUserRole() == ADMIN_ROLE) {
            return;
        } else {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
    }
}
