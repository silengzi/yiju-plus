package com.silengzi.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.silengzi.usercenter.common.ErrorCode;
import com.silengzi.usercenter.exception.BusinessException;
import com.silengzi.usercenter.model.domain.News;
import com.silengzi.usercenter.model.domain.News;
import com.silengzi.usercenter.model.dto.NewsSearchCriteria;
import com.silengzi.usercenter.model.result.PageResult;
import com.silengzi.usercenter.service.NewsService;
import com.silengzi.usercenter.mapper.NewsMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  快讯
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News>
    implements NewsService{

    @Override
    public PageResult<News> newsList(NewsSearchCriteria criteria, int page, int size) {
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        if(criteria != null) {
            // 文本内容
            if (StringUtils.isNotBlank(criteria.getText())) {
                String text = criteria.getText();
                queryWrapper.like("title", text).or().like("digest", text).or().like("text", text);
            }
            // 来源
            if (StringUtils.isNotBlank(criteria.getSource())) {
                queryWrapper.like("source", criteria.getSource());
            }
        }

        IPage<News> pageParam = new Page<>(page, size);
        IPage<News> newsIPage = this.page(pageParam, queryWrapper);
        long total = newsIPage.getTotal();
        List<News> newslist = newsIPage.getRecords();
        return new PageResult<>(newslist, total);
    }

    @Override
    public News newsDetail(Long id) {
        if(id == null || id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "id 不能为空且必须大于0");
        }
        News news = this.getById(id);
        return news;
    }

    @Override
    public News newsUpdate(News newNews) {
        if(newNews == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        boolean result = this.updateById(newNews);
        if(!result) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "修改失败");
        }
        News news = this.newsDetail(newNews.getId());
        return news;
    }

    @Override
    public Long newsAdd(News news) {
        if(news == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }

        boolean saveResult = this.save(news);
        if(!saveResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "插入失败");
        }

        return news.getId();
    }

    @Override
    public Boolean newsDelete(Long id) {
        if(id == null || id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "id 不能为空且必须大于0");
        }
        Boolean result = this.removeById(id);
        return result;
    }
}




