package com.silengzi.usercenter.controller;

import com.silengzi.usercenter.common.BaseResponse;
import com.silengzi.usercenter.common.ErrorCode;
import com.silengzi.usercenter.common.ResultUtils;
import com.silengzi.usercenter.exception.BusinessException;
import com.silengzi.usercenter.model.domain.Knowledge;
import com.silengzi.usercenter.model.domain.User;
import com.silengzi.usercenter.model.dto.KnowledgeSearchCriteria;
import com.silengzi.usercenter.model.result.PageResult;
import com.silengzi.usercenter.service.KnowledgeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.silengzi.usercenter.contant.UserConstant.ADMIN_ROLE;
import static com.silengzi.usercenter.contant.UserConstant.USER_LOGIN_STATE;

@RestController
@RequestMapping("/knowledge")
public class KnowledgeController {

    @Resource
    private KnowledgeService knowledgeService;

    @GetMapping("/list")
    public BaseResponse<PageResult<Knowledge>> searchKnowledgeList(
            @RequestParam(value = "text", required = false, defaultValue = "") String text,
            @RequestParam(value = "tags", required = false, defaultValue = "") String tags,
            @RequestParam(value = "source", required = false, defaultValue = "") String source,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            HttpServletRequest request
    ) {
        isAdmin(request);

        KnowledgeSearchCriteria knowledgeSearchCriteria = new KnowledgeSearchCriteria();
        knowledgeSearchCriteria.setText(text);
        knowledgeSearchCriteria.setTags(tags);
        knowledgeSearchCriteria.setSource(source);

        PageResult<Knowledge> pageResult = knowledgeService.knowledgeList(knowledgeSearchCriteria, page, size);

        return ResultUtils.success(pageResult);
    }

    @GetMapping("/knowledgeDetail")
    public BaseResponse<Knowledge> knowledgeDetail(
        @RequestParam(value = "id", required = true) Long id,
        HttpServletRequest request
    ) {
        isAdmin(request);

        Knowledge knowledge = knowledgeService.knowledgeDetail(id);
        return ResultUtils.success(knowledge);
    }

    @PostMapping("/knowledgeUpdate")
    public BaseResponse<Knowledge> knowledgeUpdate(
        @RequestBody Knowledge knowledge,
        HttpServletRequest request
    ) {
        isAdmin(request);

        Knowledge newKnowledge = knowledgeService.knowledgeUpdate(knowledge);
        return ResultUtils.success(newKnowledge);
    }

    @PostMapping("/knowledgeAdd")
    public BaseResponse<Long> knowledgeAdd(
        @RequestBody Knowledge knowledge,
        HttpServletRequest request
    ) {
        isAdmin(request);

        Long id = knowledgeService.knowledgeAdd(knowledge);
        return ResultUtils.success(id);
    }

    @PostMapping("/knowledgeDelete")
    public BaseResponse<Boolean> knowledgeDelete(
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

            boolean result = knowledgeService.knowledgeDelete(id);
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
