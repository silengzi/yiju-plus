package com.silengzi.usercenter.controller;

import com.silengzi.usercenter.common.BaseResponse;
import com.silengzi.usercenter.common.ErrorCode;
import com.silengzi.usercenter.common.ResultUtils;
import com.silengzi.usercenter.exception.BusinessException;
import com.silengzi.usercenter.model.domain.News;
import com.silengzi.usercenter.model.domain.User;
import com.silengzi.usercenter.model.dto.NewsSearchCriteria;
import com.silengzi.usercenter.model.result.PageResult;
import com.silengzi.usercenter.service.NewsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.silengzi.usercenter.contant.UserConstant.ADMIN_ROLE;
import static com.silengzi.usercenter.contant.UserConstant.USER_LOGIN_STATE;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Resource
    private NewsService newsService;

    @GetMapping("/list")
    public BaseResponse<PageResult<News>> searchNewsList(
            @RequestParam(value = "text", required = false, defaultValue = "") String text,
            @RequestParam(value = "source", required = false, defaultValue = "") String source,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            HttpServletRequest request
    ) {
        isAdmin(request);

        NewsSearchCriteria newsSearchCriteria = new NewsSearchCriteria();
        newsSearchCriteria.setText(text);
        newsSearchCriteria.setSource(source);

        PageResult<News> pageResult = newsService.newsList(newsSearchCriteria, page, size);

        return ResultUtils.success(pageResult);
    }

    @GetMapping("/newsDetail")
    public BaseResponse<News> newsDetail(
        @RequestParam(value = "id", required = true) Long id,
        HttpServletRequest request
    ) {
        isAdmin(request);

        News news = newsService.newsDetail(id);
        return ResultUtils.success(news);
    }

    @PostMapping("/newsUpdate")
    public BaseResponse<News> newsUpdate(
        @RequestBody News news,
        HttpServletRequest request
    ) {
        isAdmin(request);

        News newNews = newsService.newsUpdate(news);
        return ResultUtils.success(newNews);
    }

    @PostMapping("/newsAdd")
    public BaseResponse<Long> newsAdd(
        @RequestBody News news,
        HttpServletRequest request
    ) {
        isAdmin(request);

        Long id = newsService.newsAdd(news);
        return ResultUtils.success(id);
    }

    @PostMapping("/newsDelete")
    public BaseResponse<Boolean> newsDelete(
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

            boolean result = newsService.newsDelete(id);
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
