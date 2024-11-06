package com.silengzi.usercenter.service;

import com.silengzi.usercenter.model.domain.Renthouse;
import com.silengzi.usercenter.model.domain.Renthouse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.silengzi.usercenter.model.dto.RenthouseSearchCriteria;
import com.silengzi.usercenter.model.result.PageResult;

/**
 *  租房
 */
public interface RenthouseService extends IService<Renthouse> {
    /**
     * 查询租房列表
     *
     * @param criteria  租房搜索条件
     * @return  租房列表
     */
    PageResult<Renthouse> renthouseList(RenthouseSearchCriteria criteria, int page, int size);

    /**
     * 查询详情
     *
     * @param renthouseId id
     * @return  租房详情
     */
    Renthouse renthouseDetail(Long renthouseId);

    /**
     * 修改
     */
    Renthouse renthouseUpdate(Renthouse renthouse);

    /**
     * 新增
     */
    Long renthouseAdd(Renthouse renthouse);

    /**
     * 删除
     */
    Boolean renthouseDelete(Long id);
}
