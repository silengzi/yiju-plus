package com.silengzi.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.silengzi.usercenter.common.ErrorCode;
import com.silengzi.usercenter.exception.BusinessException;
import com.silengzi.usercenter.model.domain.Renthouse;
import com.silengzi.usercenter.model.dto.RenthouseSearchCriteria;
import com.silengzi.usercenter.model.result.PageResult;
import com.silengzi.usercenter.service.RenthouseService;
import com.silengzi.usercenter.mapper.RenthouseMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class RenthouseServiceImpl extends ServiceImpl<RenthouseMapper, Renthouse>
    implements RenthouseService{

    @Override
    public PageResult<Renthouse> renthouseList(RenthouseSearchCriteria criteria, int page, int size) {
        QueryWrapper<Renthouse> queryWrapper = new QueryWrapper<>();
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
            // 朝向
            if (StringUtils.isNotBlank(criteria.getDirection())) {
                queryWrapper.like("direction", criteria.getDirection());
            }
            // 装修
            if (StringUtils.isNotBlank(criteria.getDecoration())) {
                queryWrapper.like("decoration", criteria.getDecoration());
            }
            // 出租方式
            if (StringUtils.isNotBlank(criteria.getMode())) {
                queryWrapper.like("mode", criteria.getMode());
            }
        }

        IPage<Renthouse> pageParam = new Page<>(page, size);
        IPage<Renthouse> renthouseIPage = this.page(pageParam, queryWrapper);
        long total = renthouseIPage.getTotal();
        List<Renthouse> renthouselist = renthouseIPage.getRecords();
        return new PageResult<>(renthouselist, total);
    }

    @Override
    public Renthouse renthouseDetail(Long id) {
        if(id == null || id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "id 不能为空且必须大于0");
        }
        Renthouse renthouse = this.getById(id);
        return renthouse;
    }

    @Override
    public Renthouse renthouseUpdate(Renthouse newOldHouse) {
        if(newOldHouse == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        boolean result = this.updateById(newOldHouse);
        if(!result) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "修改失败");
        }
        Renthouse renthouse = this.renthouseDetail(newOldHouse.getRid());
        return renthouse;
    }

    @Override
    public Long renthouseAdd(Renthouse renthouse) {
        if(renthouse == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }

        if(renthouse.getPid() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "pid参数不能为空");
        }

        boolean saveResult = this.save(renthouse);
        if(!saveResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "插入失败");
        }

        return renthouse.getRid();
    }

    @Override
    public Boolean renthouseDelete(Long id) {
        if(id == null || id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "id 不能为空且必须大于0");
        }
        Boolean result = this.removeById(id);
        return result;
    }
}




