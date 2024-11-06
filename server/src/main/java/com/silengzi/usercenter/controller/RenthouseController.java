package com.silengzi.usercenter.controller;

import com.silengzi.usercenter.common.BaseResponse;
import com.silengzi.usercenter.common.ErrorCode;
import com.silengzi.usercenter.common.ResultUtils;
import com.silengzi.usercenter.exception.BusinessException;
import com.silengzi.usercenter.model.domain.Renthouse;
import com.silengzi.usercenter.model.domain.User;
import com.silengzi.usercenter.model.dto.RenthouseSearchCriteria;
import com.silengzi.usercenter.model.result.PageResult;
import com.silengzi.usercenter.service.RenthouseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.silengzi.usercenter.contant.UserConstant.ADMIN_ROLE;
import static com.silengzi.usercenter.contant.UserConstant.USER_LOGIN_STATE;

@RestController
@RequestMapping("/renthouse")
public class RenthouseController {

    @Resource
    private RenthouseService renthouseService;

    @GetMapping("/list")
    public BaseResponse<PageResult<Renthouse>> searchRenthouseList(
            @RequestParam(value = "type", required = false, defaultValue = "") String type,
            @RequestParam(value = "floor", required = false, defaultValue = "") String floor,
            @RequestParam(value = "direction", required = false, defaultValue = "") String direction,
            @RequestParam(value = "mode", required = false, defaultValue = "") String mode,
            @RequestParam(value = "decoration", required = false, defaultValue = "") String decoration,
            @RequestParam(value = "minPrice", required = false, defaultValue = "") String minPrice,
            @RequestParam(value = "maxPrice", required = false, defaultValue = "") String maxPrice,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            HttpServletRequest request
    ) {
        isAdmin(request);

        RenthouseSearchCriteria renthouseSearchCriteria = new RenthouseSearchCriteria();
        renthouseSearchCriteria.setType(type);
        renthouseSearchCriteria.setFloor(floor);
        renthouseSearchCriteria.setDirection(direction);
        renthouseSearchCriteria.setMode(mode);
        renthouseSearchCriteria.setDecoration(decoration);
        renthouseSearchCriteria.setMinPrice(minPrice);
        renthouseSearchCriteria.setMaxPrice(maxPrice);

        PageResult<Renthouse> pageResult = renthouseService.renthouseList(renthouseSearchCriteria, page, size);

        return ResultUtils.success(pageResult);
    }

    @GetMapping("/renthouseDetail")
    public BaseResponse<Renthouse> renthouseDetail(
        @RequestParam(value = "id", required = true) Long id,
        HttpServletRequest request
    ) {
        isAdmin(request);

        Renthouse renthouse = renthouseService.renthouseDetail(id);
        return ResultUtils.success(renthouse);
    }

    @PostMapping("/renthouseUpdate")
    public BaseResponse<Renthouse> renthouseUpdate(
        @RequestBody Renthouse renthouse,
        HttpServletRequest request
    ) {
        isAdmin(request);

        Renthouse newRenthouse = renthouseService.renthouseUpdate(renthouse);
        return ResultUtils.success(newRenthouse);
    }

    @PostMapping("/renthouseAdd")
    public BaseResponse<Long> renthouseAdd(
        @RequestBody Renthouse renthouse,
        HttpServletRequest request
    ) {
        isAdmin(request);

        Long id = renthouseService.renthouseAdd(renthouse);
        return ResultUtils.success(id);
    }

    @PostMapping("/renthouseDelete")
    public BaseResponse<Boolean> renthouseDelete(
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

            boolean result = renthouseService.renthouseDelete(id);
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
