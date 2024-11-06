package com.silengzi.usercenter.service;

import com.silengzi.usercenter.model.domain.Newhouse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.silengzi.usercenter.model.domain.Newhouse;
import com.silengzi.usercenter.model.dto.NewhouseSearchCriteria;
import com.silengzi.usercenter.model.result.PageResult;

/**
 * 新房
 */
public interface NewhouseService extends IService<Newhouse> {
    /**
     * 查询新房列表
     *
     * @param criteria  新房搜索条件
     * @return  新房列表
     */
    PageResult<Newhouse> newhouseList(NewhouseSearchCriteria criteria, int page, int size);

    /**
     * 查询详情
     *
     * @param newhouseId id
     * @return  新房详情
     */
    Newhouse newhouseDetail(Long newhouseId);

    /**
     * 修改
     */
    Newhouse newhouseUpdate(Newhouse newNewhouse);

    /**
     * 新增
     */
    Long newhouseAdd(Newhouse newhouse);

    /**
     * 删除
     */
    Boolean newhouseDelete(Long id);
}
