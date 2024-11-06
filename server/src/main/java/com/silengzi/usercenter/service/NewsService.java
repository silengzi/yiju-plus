package com.silengzi.usercenter.service;

import com.silengzi.usercenter.model.domain.News;
import com.baomidou.mybatisplus.extension.service.IService;
import com.silengzi.usercenter.model.dto.NewsSearchCriteria;
import com.silengzi.usercenter.model.result.PageResult;

/**
 *  要闻/快讯
 */
public interface NewsService extends IService<News> {
    /**
     * 查询快讯列表
     *
     * @param criteria  快讯搜索条件
     * @return  快讯列表
     */
    PageResult<News> newsList(NewsSearchCriteria criteria, int page, int size);

    /**
     * 查询详情
     *
     * @param newsId id
     * @return  快讯详情
     */
    News newsDetail(Long newsId);

    /**
     * 修改
     */
    News newsUpdate(News newNews);

    /**
     * 新增
     */
    Long newsAdd(News news);

    /**
     * 删除
     */
    Boolean newsDelete(Long id);
}
