package com.silengzi.usercenter.model.dto;

import lombok.Data;

// 知识科普查询条件
@Data
public class KnowledgeSearchCriteria {
    private String text; // 内容（包含标题、正文）
    private String tags; // 标签
    private String source; // 来源
}
