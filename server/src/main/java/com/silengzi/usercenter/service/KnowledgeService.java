package com.silengzi.usercenter.service;

import com.silengzi.usercenter.model.domain.Knowledge;
import com.baomidou.mybatisplus.extension.service.IService;
import com.silengzi.usercenter.model.domain.Knowledge;
import com.silengzi.usercenter.model.dto.KnowledgeSearchCriteria;
import com.silengzi.usercenter.model.result.PageResult;

/**
 *  知识科普
 */
public interface KnowledgeService extends IService<Knowledge> {
    /**
     * 查询知识科普列表
     *
     * @param criteria  知识科普搜索条件
     * @return  知识科普列表
     */
    PageResult<Knowledge> knowledgeList(KnowledgeSearchCriteria criteria, int page, int size);

    /**
     * 查询详情
     *
     * @param knowledgeId id
     * @return  知识科普详情
     */
    Knowledge knowledgeDetail(Long knowledgeId);

    /**
     * 修改
     */
    Knowledge knowledgeUpdate(Knowledge knowledge);

    /**
     * 新增
     */
    Long knowledgeAdd(Knowledge knowledge);

    /**
     * 删除
     */
    Boolean knowledgeDelete(Long id);
}
