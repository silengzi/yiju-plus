package com.silengzi.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.silengzi.usercenter.common.ErrorCode;
import com.silengzi.usercenter.exception.BusinessException;
import com.silengzi.usercenter.model.domain.Newhouse;
import com.silengzi.usercenter.model.dto.NewhouseSearchCriteria;
import com.silengzi.usercenter.model.result.PageResult;
import com.silengzi.usercenter.service.NewhouseService;
import com.silengzi.usercenter.mapper.NewhouseMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 新房
 */
@Service
public class NewhouseServiceImpl extends ServiceImpl<NewhouseMapper, Newhouse>
    implements NewhouseService{

    @Override
    public PageResult<Newhouse> newhouseList(NewhouseSearchCriteria criteria, int page, int size) {
        QueryWrapper<Newhouse> queryWrapper = new QueryWrapper<>();
        if(criteria != null) {
            // 最低价格区间
            if (StringUtils.isNotBlank(criteria.getMinPrice())) {
                queryWrapper.ge("CAST(price AS DECIMAL)", criteria.getMinPrice()); // 大于等于
            }
            // 最高价格区间
            if (StringUtils.isNotBlank(criteria.getMaxPrice())) {
                queryWrapper.le("CAST(price AS DECIMAL)", criteria.getMaxPrice()); // 小于等于
            }
            // 户型
            if (StringUtils.isNotBlank(criteria.getType())) {
                queryWrapper.like("type", criteria.getType());
            }
            // 楼层
            if (StringUtils.isNotBlank(criteria.getFloor())) {
                queryWrapper.like("floor", criteria.getFloor());
            }
        }

        IPage<Newhouse> pageParam = new Page<>(page, size);
        IPage<Newhouse> newhouseIPage = this.page(pageParam, queryWrapper);
        long total = newhouseIPage.getTotal();
        List<Newhouse> newhouselist = newhouseIPage.getRecords();
        return new PageResult<>(newhouselist, total);
    }

    @Override
    public Newhouse newhouseDetail(Long id) {
        if(id == null || id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "id 不能为空且必须大于0");
        }
        Newhouse newhouse = this.getById(id);
        return newhouse;
    }

    @Override
    public Newhouse newhouseUpdate(Newhouse newHouse) {
        if(newHouse == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        boolean result = this.updateById(newHouse);
        if(!result) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "修改失败");
        }
        Newhouse newhouse = this.newhouseDetail(newHouse.getHid());
        return newhouse;
    }

    @Override
    public Long newhouseAdd(Newhouse newhouse) {
        if(newhouse == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }

        if(newhouse.getPid() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "pid参数不能为空");
        }

        boolean saveResult = this.save(newhouse);
        if(!saveResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "插入失败");
        }

        return newhouse.getHid();
    }

    @Override
    public Boolean newhouseDelete(Long id) {
        if(id == null || id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "id 不能为空且必须大于0");
        }
        Boolean result = this.removeById(id);
        return result;
    }
}




