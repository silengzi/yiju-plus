package com.silengzi.usercenter.controller;

import com.silengzi.usercenter.common.BaseResponse;
import com.silengzi.usercenter.common.ResultUtils;
import com.silengzi.usercenter.model.domain.Region;
import com.silengzi.usercenter.service.RegionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("region")
@RestController
public class RegionController {

    @Resource
    private RegionService regionService;

    @GetMapping("/tree")
    public BaseResponse<List<Region>> getAllUser() {
        return ResultUtils.success(regionService.getRegionTree());
    }
}

