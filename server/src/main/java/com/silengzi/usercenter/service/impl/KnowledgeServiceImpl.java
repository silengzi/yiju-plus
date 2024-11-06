package com.silengzi.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.silengzi.usercenter.common.ErrorCode;
import com.silengzi.usercenter.exception.BusinessException;
import com.silengzi.usercenter.model.domain.Knowledge;
import com.silengzi.usercenter.model.domain.Knowledge;
import com.silengzi.usercenter.model.dto.KnowledgeSearchCriteria;
import com.silengzi.usercenter.model.result.PageResult;
import com.silengzi.usercenter.service.KnowledgeService;
import com.silengzi.usercenter.mapper.KnowledgeMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  知识科普
 */
@Service
public class KnowledgeServiceImpl extends ServiceImpl<KnowledgeMapper, Knowledge>
    implements KnowledgeService{

    @Override
    public PageResult<Knowledge> knowledgeList(KnowledgeSearchCriteria criteria, int page, int size) {
        QueryWrapper<Knowledge> queryWrapper = new QueryWrapper<>();
        if(criteria != null) {
            // 文本内容
            if (StringUtils.isNotBlank(criteria.getText())) {
                String text = criteria.getText();
                queryWrapper.like("title", text).or().like("text", text);
            }
            // 标签
            if (StringUtils.isNotBlank(criteria.getTags())) {
                queryWrapper.like("tags", criteria.getTags());
            }
            // 来源
            if (StringUtils.isNotBlank(criteria.getSource())) {
                queryWrapper.like("source", criteria.getSource());
            }
        }

        IPage<Knowledge> pageParam = new Page<>(page, size);
        IPage<Knowledge> knowledgeIPage = this.page(pageParam, queryWrapper);
        long total = knowledgeIPage.getTotal();
        List<Knowledge> knowledgelist = knowledgeIPage.getRecords();
        return new PageResult<>(knowledgelist, total);
    }

    @Override
    public Knowledge knowledgeDetail(Long id) {
        if(id == null || id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "id 不能为空且必须大于0");
        }
        Knowledge knowledge = this.getById(id);
        return knowledge;
    }

    @Override
    public Knowledge knowledgeUpdate(Knowledge newKnowledge) {
        if(newKnowledge == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        boolean result = this.updateById(newKnowledge);
        if(!result) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "修改失败");
        }
        Knowledge knowledge = this.knowledgeDetail(newKnowledge.getId());
        return knowledge;
    }

    @Override
    public Long knowledgeAdd(Knowledge knowledge) {
        if(knowledge == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }

        boolean saveResult = this.save(knowledge);
        if(!saveResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "插入失败");
        }

        return knowledge.getId();
    }

    @Override
    public Boolean knowledgeDelete(Long id) {
        if(id == null || id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "id 不能为空且必须大于0");
        }
        Boolean result = this.removeById(id);
        return result;
    }
}




