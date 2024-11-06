package com.silengzi.usercenter.controller;

import com.silengzi.usercenter.common.BaseResponse;
import com.silengzi.usercenter.common.ErrorCode;
import com.silengzi.usercenter.common.ResultUtils;
import com.silengzi.usercenter.exception.BusinessException;
import com.silengzi.usercenter.model.domain.Oldhouse;
import com.silengzi.usercenter.model.domain.User;
import com.silengzi.usercenter.model.dto.OldhouseSearchCriteria;
import com.silengzi.usercenter.model.result.PageResult;
import com.silengzi.usercenter.service.OldhouseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.silengzi.usercenter.contant.UserConstant.ADMIN_ROLE;
import static com.silengzi.usercenter.contant.UserConstant.USER_LOGIN_STATE;

@RestController
@RequestMapping("/oldhouse")
public class OldhouseController {

    @Resource
    private OldhouseService oldhouseService;

    @GetMapping("/list")
    public BaseResponse<PageResult<Oldhouse>> searchOldhouseList(
            @RequestParam(value = "type", required = false, defaultValue = "") String type,
            @RequestParam(value = "floor", required = false, defaultValue = "") String floor,
            @RequestParam(value = "direction", required = false, defaultValue = "") String direction,
            @RequestParam(value = "year", required = false, defaultValue = "") String year,
            @RequestParam(value = "minPrice", required = false, defaultValue = "") String minPrice,
            @RequestParam(value = "maxPrice", required = false, defaultValue = "") String maxPrice,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            HttpServletRequest request
    ) {
        isAdmin(request);

        OldhouseSearchCriteria oldhouseSearchCriteria = new OldhouseSearchCriteria();
        oldhouseSearchCriteria.setType(type);
        oldhouseSearchCriteria.setFloor(floor);
        oldhouseSearchCriteria.setDirection(direction);
        oldhouseSearchCriteria.setYear(year);
        oldhouseSearchCriteria.setMinPrice(minPrice);
        oldhouseSearchCriteria.setMaxPrice(maxPrice);

        PageResult<Oldhouse> pageResult = oldhouseService.oldhouseList(oldhouseSearchCriteria, page, size);

        return ResultUtils.success(pageResult);
    }

    @GetMapping("/oldhouseDetail")
    public BaseResponse<Oldhouse> oldhouseDetail(
        @RequestParam(value = "id", required = true) Long id,
        HttpServletRequest request
    ) {
        isAdmin(request);

        Oldhouse oldhouse = oldhouseService.oldhouseDetail(id);
        return ResultUtils.success(oldhouse);
    }

    @PostMapping("/oldhouseUpdate")
    public BaseResponse<Oldhouse> oldhouseUpdate(
        @RequestBody Oldhouse oldhouse,
        HttpServletRequest request
    ) {
        isAdmin(request);

        Oldhouse newOldhouse = oldhouseService.oldhouseUpdate(oldhouse);
        return ResultUtils.success(newOldhouse);
    }

    @PostMapping("/oldhouseAdd")
    public BaseResponse<Long> oldhouseAdd(
        @RequestBody Oldhouse oldhouse,
        HttpServletRequest request
    ) {
        isAdmin(request);

        Long id = oldhouseService.oldhouseAdd(oldhouse);
        return ResultUtils.success(id);
    }

    @PostMapping("/oldhouseDelete")
    public BaseResponse<Boolean> oldhouseDelete(
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

            boolean result = oldhouseService.oldhouseDelete(id);
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
