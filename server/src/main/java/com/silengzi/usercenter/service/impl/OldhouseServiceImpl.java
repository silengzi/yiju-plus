package com.silengzi.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.silengzi.usercenter.common.ErrorCode;
import com.silengzi.usercenter.exception.BusinessException;
import com.silengzi.usercenter.model.domain.Oldhouse;
import com.silengzi.usercenter.model.domain.Oldhouse;
import com.silengzi.usercenter.model.dto.OldhouseSearchCriteria;
import com.silengzi.usercenter.model.result.PageResult;
import com.silengzi.usercenter.service.OldhouseService;
import com.silengzi.usercenter.mapper.OldhouseMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  二手房逻辑层
 */
@Service
public class OldhouseServiceImpl extends ServiceImpl<OldhouseMapper, Oldhouse>
    implements OldhouseService{

    @Override
    public PageResult<Oldhouse> oldhouseList(OldhouseSearchCriteria criteria, int page, int size) {
        QueryWrapper<Oldhouse> queryWrapper = new QueryWrapper<>();
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
            // 年份
            if (StringUtils.isNotBlank(criteria.getYear())) {
                queryWrapper.like("year", criteria.getYear());
            }
        }

        IPage<Oldhouse> pageParam = new Page<>(page, size);
        IPage<Oldhouse> oldhouseIPage = this.page(pageParam, queryWrapper);
        long total = oldhouseIPage.getTotal();
        List<Oldhouse> oldhouselist = oldhouseIPage.getRecords();
        return new PageResult<>(oldhouselist, total);
    }

    @Override
    public Oldhouse oldhouseDetail(Long id) {
        if(id == null || id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "id 不能为空且必须大于0");
        }
        Oldhouse oldhouse = this.getById(id);
        return oldhouse;
    }

    @Override
    public Oldhouse oldhouseUpdate(Oldhouse newOldHouse) {
        if(newOldHouse == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        boolean result = this.updateById(newOldHouse);
        if(!result) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "修改失败");
        }
        Oldhouse oldhouse = this.oldhouseDetail(newOldHouse.getOid());
        return oldhouse;
    }

    @Override
    public Long oldhouseAdd(Oldhouse oldhouse) {
        if(oldhouse == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }

        if(oldhouse.getPid() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "pid参数不能为空");
        }

        boolean saveResult = this.save(oldhouse);
        if(!saveResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "插入失败");
        }

        return oldhouse.getOid();
    }

    @Override
    public Boolean oldhouseDelete(Long id) {
        if(id == null || id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "id 不能为空且必须大于0");
        }
        Boolean result = this.removeById(id);
        return result;
    }
}




