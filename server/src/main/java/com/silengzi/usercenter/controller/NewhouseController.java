package com.silengzi.usercenter.controller;

import com.silengzi.usercenter.common.BaseResponse;
import com.silengzi.usercenter.common.ErrorCode;
import com.silengzi.usercenter.common.ResultUtils;
import com.silengzi.usercenter.exception.BusinessException;
import com.silengzi.usercenter.model.domain.Newhouse;
import com.silengzi.usercenter.model.domain.User;
import com.silengzi.usercenter.model.dto.NewhouseSearchCriteria;
import com.silengzi.usercenter.model.result.PageResult;
import com.silengzi.usercenter.service.NewhouseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.silengzi.usercenter.contant.UserConstant.ADMIN_ROLE;
import static com.silengzi.usercenter.contant.UserConstant.USER_LOGIN_STATE;

@RestController
@RequestMapping("/newhouse")
public class NewhouseController {

    @Resource
    private NewhouseService newhouseService;

    @GetMapping("/list")
    public BaseResponse<PageResult<Newhouse>> searchNewhouseList(
            @RequestParam(value = "type", required = false, defaultValue = "") String type,
            @RequestParam(value = "floor", required = false, defaultValue = "") String floor,
            @RequestParam(value = "minPrice", required = false, defaultValue = "") String minPrice,
            @RequestParam(value = "maxPrice", required = false, defaultValue = "") String maxPrice,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            HttpServletRequest request
    ) {
        isAdmin(request);

        NewhouseSearchCriteria newhouseSearchCriteria = new NewhouseSearchCriteria();
        newhouseSearchCriteria.setType(type);
        newhouseSearchCriteria.setFloor(floor);
        newhouseSearchCriteria.setMinPrice(minPrice);
        newhouseSearchCriteria.setMaxPrice(maxPrice);

        PageResult<Newhouse> pageResult = newhouseService.newhouseList(newhouseSearchCriteria, page, size);

        return ResultUtils.success(pageResult);
    }

    @GetMapping("/newhouseDetail")
    public BaseResponse<Newhouse> newhouseDetail(
        @RequestParam(value = "id", required = true) Long id,
        HttpServletRequest request
    ) {
        isAdmin(request);

        Newhouse newhouse = newhouseService.newhouseDetail(id);
        return ResultUtils.success(newhouse);
    }

    @PostMapping("/newhouseUpdate")
    public BaseResponse<Newhouse> newhouseUpdate(
        @RequestBody Newhouse newhouse,
        HttpServletRequest request
    ) {
        isAdmin(request);

        Newhouse newNewhouse = newhouseService.newhouseUpdate(newhouse);
        return ResultUtils.success(newNewhouse);
    }

    @PostMapping("/newhouseAdd")
    public BaseResponse<Long> newhouseAdd(
        @RequestBody Newhouse newhouse,
        HttpServletRequest request
    ) {
        isAdmin(request);

        Long id = newhouseService.newhouseAdd(newhouse);
        return ResultUtils.success(id);
    }

    @PostMapping("/newhouseDelete")
    public BaseResponse<Boolean> newhouseDelete(
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

            boolean result = newhouseService.newhouseDelete(id);
            if(!result) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "删除失败");
            }
        }

        return ResultUtils.success(true);
    }

    public void isAdmin(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        if(user != null && user.getUserRole() == ADMIN_ROLE) {
            return;
        } else {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
    }
}
