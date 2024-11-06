package com.silengzi.usercenter.service;

import com.silengzi.usercenter.model.domain.Oldhouse;
import com.silengzi.usercenter.model.domain.Oldhouse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.silengzi.usercenter.model.dto.OldhouseSearchCriteria;
import com.silengzi.usercenter.model.result.PageResult;

/**
 *
 */
public interface OldhouseService extends IService<Oldhouse> {
    /**
     * 查询二手房列表
     *
     * @param criteria  二手房搜索条件
     * @return  二手房列表
     */
    PageResult<Oldhouse> oldhouseList(OldhouseSearchCriteria criteria, int page, int size);

    /**
     * 查询详情
     *
     * @param oldhouseId id
     * @return  二手房详情
     */
    Oldhouse oldhouseDetail(Long oldhouseId);

    /**
     * 修改
     */
    Oldhouse oldhouseUpdate(Oldhouse newOldhouse);

    /**
     * 新增
     */
    Long oldhouseAdd(Oldhouse oldhouse);

    /**
     * 删除
     */
    Boolean oldhouseDelete(Long id);
}
